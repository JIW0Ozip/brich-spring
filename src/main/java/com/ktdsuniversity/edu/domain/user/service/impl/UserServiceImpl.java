package com.ktdsuniversity.edu.domain.user.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.ktdsuniversity.edu.domain.blog.controller.SearchBlogController;
import com.ktdsuniversity.edu.domain.blog.vo.RequestBlogInfoVO;
import com.ktdsuniversity.edu.domain.blog.vo.RequestBlogTitleVO;
import com.ktdsuniversity.edu.domain.campaign.dao.CampaignDao;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignwriteVO;
import com.ktdsuniversity.edu.domain.file.dao.FileDao;
import com.ktdsuniversity.edu.domain.file.dao.FileGroupDao;
import com.ktdsuniversity.edu.domain.file.util.MultipartFileHandler;
import com.ktdsuniversity.edu.domain.file.vo.FileGroupVO;
import com.ktdsuniversity.edu.domain.file.vo.FileVO;
import com.ktdsuniversity.edu.domain.user.dao.BlogCategoryDao;
import com.ktdsuniversity.edu.domain.user.dao.SubscriptionPaymentDao;
import com.ktdsuniversity.edu.domain.user.dao.UserAreaDao;
import com.ktdsuniversity.edu.domain.user.dao.UserDao;
import com.ktdsuniversity.edu.domain.user.service.UserService;
import com.ktdsuniversity.edu.domain.user.vo.BlogCategoryVO;
import com.ktdsuniversity.edu.domain.user.vo.UserAreaVO;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserAccountPasswordVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserFindIdVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserInfoModifyVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserLoginVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserRegistVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserResetPasswordVO;
import com.ktdsuniversity.edu.domain.user.vo.response.ResponseUserInfoVO;
import com.ktdsuniversity.edu.domain.user.vo.response.ResponseUserSubscriptionInfoVO;
import com.ktdsuniversity.edu.global.common.AreaCode;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;
import com.ktdsuniversity.edu.global.exceptions.AjaxException;
import com.ktdsuniversity.edu.global.exceptions.BrichException;
import com.ktdsuniversity.edu.global.util.SHAEncrypter;
import com.ktdsuniversity.edu.global.util.SessionUtil;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;  
    
    @Autowired
    private UserAreaDao userAreaDao; 
    
    @Autowired
    private SubscriptionPaymentDao subscriptionPaymentDao;
    
    @Autowired
	private MultipartFileHandler multipartFileHandler;
	@Autowired
	private FileGroupDao fileGroupDao;
	@Autowired
	private FileDao fileDao;
	@Autowired
	private CampaignDao campaignDao;
	@Autowired 
	private BlogCategoryDao blogCategoryDao;
    
	private static final Logger log = LoggerFactory.getLogger(SearchBlogController.class);

    @Override
	public UserVO readUser(RequestUserLoginVO requestUserLoginVO) {
    	if(requestUserLoginVO.getLogId().equals("")
    	|| requestUserLoginVO.getPswrd().equals("")) {
    		throw new BrichException("아이디와 비밀번호를 모두 입력해주세요.", "/WEB-INF/views/user/login.jsp");
    	}
		//1. LOG_ID, autr(1001,1002,1003,1004) 이용해 유저 정보 조회
    	UserVO userVO = this.userDao.selectUserByLogIdAndAutr(requestUserLoginVO);
    	
    	log.info("loginUser:{}", userVO);
		//2. 조회된 유저가 없을 경우
    	if(userVO == null) {
    		throw new BrichException("아이디 또는 비밀번호가 잘못되었습니다.", "/WEB-INF/views/user/login.jsp");
    	}
    	
    	if(userVO.getAutr().equals("1007")) {
    		throw new BrichException("승인대기 중인 계정입니다.", "/WEB-INF/views/user/login.jsp");
    	}
    	
    	//블럭 케이스 
    	//1) 조회된 유저의 블럭 여부가 "Y"일때 예외를 던진다.
    	//2) 블럭 여부가 "Y"인 회원이 로그인이 가능할 떄 
    	// 마지막 블럭 날짜에서 60분 후라면 로그인이 가능하다. 
    	if(userVO.getBlckYn().equals("Y")) {
    		int count = this.userDao.selectUnblockUserByLogId(requestUserLoginVO.getLogId());
    		if(count == 0) {
        		throw new BrichException("아이디 또는 비밀번호가 잘못되었습니다.", "/WEB-INF/views/user/login.jsp");
    		}
    	}
    	
    	//3. 조회된 회원이 있을 경우 SALT 값만 가져온다. 
    	String salt = userVO.getSalt();
    	//4. SALT를 이용해 사용자가 입력한 비밀번호를 암호화한다.
    	String encryptedPassword = SHAEncrypter.getEncrypt(requestUserLoginVO.getPswrd(), salt);
    	//5. 암호화된 비밀번호와 조회한 회원의 비밀번호가 일치하는지 검사한다. 
    	if(!userVO.getPswrd().equals(encryptedPassword)) {
    		//실패 트랜잭션
    		//1.로그인 실패한 이메일의 실패횟수를 1증가시킨다.
    		//2.동시에 마지막 실패 날짜를 현재로 변경한다.
    		int updateCount = this.userDao.updateLoginFailCountByLogId(requestUserLoginVO.getLogId());
    		//3.실패 횟수가 지정 횟수 이상이 되면 BLOCK 여부를 "Y"로 변경한다.
    		//4.동시에 마지막 블럭 날짜를 현재로 변경한다.
    		updateCount = this.userDao.updateBlockByLogid(requestUserLoginVO.getLogId());
    		
    		throw new BrichException("아이디 또는 비밀번호가 잘못되었습니다.", "/WEB-INF/views/user/login.jsp");
    	}
    	
    	
    	//로그인 성공 케이스 
    	//1) 로그인 실패 횟수를 0으로 초기화한다. 
    	//2) 블럭 여부를 "N"으로 변경한다. 
    	//3) 마지막 로그인 성공 날짜를 현재로 변경한다.
    	int updateCount = this.userDao.updateLoginSuccessByLogId(requestUserLoginVO.getLogId());
    	
		return userVO;
		
	}

    @Transactional
	@Override
	public boolean createNewUser(RequestUserRegistVO requestUserRegistVO) {
    	
    	List<FileVO> uploadResult = this.multipartFileHandler.upload(requestUserRegistVO.getFile());
    	
    	int logIdCount = this.userDao.selectUserCountByLogId(requestUserRegistVO.getLogId());
    	
    	if(uploadResult != null && uploadResult.size() > 0) {
			//1.File Group Insert
			FileGroupVO fileGroupVO = new FileGroupVO();
			fileGroupVO.setFlCnt(uploadResult != null ? uploadResult.size(): 0);
			int insertGroupCount = this.fileGroupDao.insertFileGroup(fileGroupVO);
			
			//2.File Insert
			
			for(FileVO result : uploadResult) {
				result.setFlGrpId(fileGroupVO.getFlGrpId());
				int insertFileCount = this.fileDao.insertFile(result);
			}
			//게시글에 첨부되어있는 파일 그룹의 아이디가 무엇인지 알수있다.
			requestUserRegistVO.setFlGrpId(fileGroupVO.getFlGrpId());
			
		}
    
    	
    	if(logIdCount == 1) {
    		//추후 Custom Exception으로 전환 예정
    		throw new IllegalArgumentException("이미 존재하는 이메일 입니다.");
    	}
    	
    	//비밀번호 암호화 
    	//SALT 발급
    	String salt = SHAEncrypter.generateSalt();
    	//SALT를 이용해 비밀번호 암호화 
    	String password = requestUserRegistVO.getPswrd();
    	if(password == null) {
    		return false;
    	}
    	
    	String encryptedPassword = SHAEncrypter.getEncrypt(password, salt);
    	requestUserRegistVO.setPswrd(encryptedPassword);
    	requestUserRegistVO.setSalt(salt);
    	
		int insertResult= this.userDao.insertNewUser(requestUserRegistVO);
		
		//블로거가 카테고리를 선택했을 떄의 로직
		if(insertResult == 1 && requestUserRegistVO.getCdIdList() != null) {
			String createdUserId = this.userDao.selectUserIdByLogId(requestUserRegistVO.getLogId());
			BlogCategoryVO blogCategoryVO = new BlogCategoryVO();
			UserAreaVO userAreaVO = new UserAreaVO();
			blogCategoryVO.setUsrId(createdUserId);
			userAreaVO.setUsrId(createdUserId);
    		for(String cdId : requestUserRegistVO.getCdIdList()) {
    			blogCategoryVO.setCdId(cdId);
    			int insertCategoryResult = this.blogCategoryDao.insertBlogCategory(blogCategoryVO);
    		}
    	    for(String areaId : requestUserRegistVO.getAreaList()) {
    	    	userAreaVO.setArId(areaId);
    	    	int insertAreaResult = this.userAreaDao.insertArea(userAreaVO);
    	    }
    	}
		
		return insertResult > 0;
	}

	@Override
	public int readUserIdByLogId(String logId) {
		int userCount = this.userDao.selectUserCountByLogId(logId);
		log.info("logId count {}", userCount);
		return userCount;
	}

	@Override
	public String readLogIdByNameAndEmail(RequestUserFindIdVO requestUserFindIdVO) {
		return this.userDao.selectUserLogIdByNameAndEmail(requestUserFindIdVO);
	}

    @Transactional
	@Override
	public boolean updatePswrdByLogIdAndPswrd(RequestUserResetPasswordVO resetPasswordInfo) {
		//재설정할 비밀번호를 암호화한다. 
    	String salt = SHAEncrypter.generateSalt();
    	String password = resetPasswordInfo.getPswrd();
    	
    	String encryptedPassword = SHAEncrypter.getEncrypt(password, salt);
		//암호화한 재설정 비밀번호를 다시 resetPassword에 set해준다.
    	resetPasswordInfo.setPswrd(encryptedPassword);
    	resetPasswordInfo.setSalt(salt);
    	
		int count = this.userDao.updatePswrdByLogIdAndPswrd(resetPasswordInfo);
		return count > 0;
	}

	@Override
	public List<CommonCodeVO> readCategoryList() {
		return this.campaignDao.selectCategoryList();
	}

	@Override
	public UserVO readUserByLogId(String id) {
		return this.userDao.selectUserByLogId(id);
	}
   @Override
	public ResponseUserInfoVO readUserByUserId(String usrId) {
		
		ResponseUserInfoVO userInfo = new ResponseUserInfoVO();
		ResponseCampaignwriteVO common = new ResponseCampaignwriteVO();
		common.setDoAndCityList(this.campaignDao.selectDoAndCityList());
		common.setCategoryList(this.campaignDao.selectCategoryList());
		UserVO userVO = this.userDao.selectUserByUserId(usrId);
		List<AreaCode> areaList = this.userAreaDao.selectUserAreaByUserId(usrId);
		List<CommonCodeVO> categoryList = this.blogCategoryDao.selectUserCategoryByUserId(usrId);
		
		userInfo.setResponseCampaignwriteVO(common);
		userInfo.setUserVO(userVO);
		userInfo.setAreaList(areaList);
		userInfo.setCategoryList(categoryList);
		
		return userInfo;
	}

  @Override
  public ResponseUserSubscriptionInfoVO readSubscriptionInfoByUserId(String usrId) {
	return this.subscriptionPaymentDao.selectSubscriptionInfoByUserId(usrId);
  }

  /*
   * 계정 관리 - 비밀번호 재설정용
   */
  @Transactional
  @Override
  public boolean updatePswrdByUsrId(RequestUserAccountPasswordVO requestUserAccountPasswordVO) {
	  //우선 입력한 현재 비밀번호와 나의 비밀번호가 일치하는지 확인 해야함
	  
	  //salt는 난수이기 때문에 DB에서 값을 가져와 줘야함
	  String salt = this.userDao.selectSaltByUsrId(requestUserAccountPasswordVO.getUsrId());
	  String currentPassword = requestUserAccountPasswordVO.getCurrentPswrd();
	  String encryptedPassword = SHAEncrypter.getEncrypt(currentPassword, salt);
	  Map<String, String> currentUserPswrd = Map.of("usrId", requestUserAccountPasswordVO.getUsrId()
			  									  , "currentPassword", encryptedPassword);
	  
	  log.info("enc {}", currentUserPswrd);
	  String originalPassword = this.userDao.selectUserPswrdByPswrd(currentUserPswrd);
	  
	  if(!encryptedPassword.equals(originalPassword) || originalPassword == null) {
		  log.info("일치하는지 확인");
		  return false;
	  }
	  
	  //재설정할 비밀번호를 암호화한다. 
	  salt = SHAEncrypter.generateSalt();
	  String password = requestUserAccountPasswordVO.getNewPswrd(); 
	  	
	  encryptedPassword = SHAEncrypter.getEncrypt(password, salt);
	  	
	  //암호화한 재설정 비밀번호를 다시 newPswrd에 set해준다.
	  requestUserAccountPasswordVO.setNewPswrd(encryptedPassword);
	  requestUserAccountPasswordVO.setSalt(salt);
	  
	  int updateResult = this.userDao.updatePswrdByUsrId(requestUserAccountPasswordVO);
  	
	  return updateResult > 0;
  }

	 @Override
	 @Transactional
	 public boolean updateUserInfoByUsrId(RequestUserInfoModifyVO requestUserInfoModifyVO) {
		 List<String> cdIdList = requestUserInfoModifyVO.getCdIdList();
		 List<String> areaList = requestUserInfoModifyVO.getArea();
		 String usrId = requestUserInfoModifyVO.getUsrId();
		//카테고리가 있다면 기존 카테고리 N
		if(cdIdList != null) {
			int deleteCategoryReulst = this.blogCategoryDao.updateDltYnByUsrId(usrId);
			
			//카테고리 삽입
			
			for(String cdId : cdIdList) {
				log.info("카테고리 삽입");
				BlogCategoryVO category = new BlogCategoryVO();
				category.setCdId(cdId);
				category.setUsrId(usrId);
				int categoryInsert = this.blogCategoryDao.mergeBlogCategory(category);
				if(categoryInsert != 1) {
					return false;
				}
			}
			
		}
		//지역이 있다면 기존 카테고리 N
		if(areaList != null) {
			int deleteAreaResult = this.userAreaDao.updateDltYnByUsrId(usrId);
			
			log.info("지역 삽입 ");
			//지역 삽입
			for(String areaId : areaList) {
				UserAreaVO area = new UserAreaVO();
				area.setArId(areaId);
				area.setUsrId(usrId);
				int areaInsert = this.userAreaDao.insertArea(area);
				if(areaInsert != 1) {
					return false;
				}
			}
			
		}
		return true;
	 }


  @Transactional
  @Override
  public boolean updateBlogInfo(RequestBlogInfoVO request) {

	  int updateCount = this.userDao.updateBlogScrapNeighbor(request);
	  return updateCount > 0;
  }

  @Transactional
  @Override
  public boolean updateBlogTitle(RequestBlogTitleVO request) {
	  int updateCount = this.userDao.updateBlogTitle(request);
	  
	return updateCount > 0;
  }

  @Override
  public boolean readEmailByInputEmail(String email) {
	int count = this.userDao.selectEmailCountByInputEmail(email);
	  
	return count < 1;
  }


}