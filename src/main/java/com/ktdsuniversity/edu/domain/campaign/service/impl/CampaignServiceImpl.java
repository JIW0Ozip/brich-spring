package com.ktdsuniversity.edu.domain.campaign.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.domain.blog.service.BlogDataService;
import com.ktdsuniversity.edu.domain.blog.vo.RequestExpireSoonCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.dao.CampaignDao;
import com.ktdsuniversity.edu.domain.campaign.service.CampaignService;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignIndexStatVO;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignPostAdoptVO;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignPostManageVO;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.ResponseModifyCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestCampaignAreaVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestCreateCmpnVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestDenyVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestPostSubmitVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestUpdatePstSttsVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdoptListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdoptVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseApplicantListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignwriteVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseDenyHistoryVO;
import com.ktdsuniversity.edu.domain.file.dao.FileDao;
import com.ktdsuniversity.edu.domain.file.dao.FileGroupDao;
import com.ktdsuniversity.edu.domain.file.util.MultipartFileHandler;
import com.ktdsuniversity.edu.domain.file.vo.FileGroupVO;
import com.ktdsuniversity.edu.domain.file.vo.FileVO;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;
import com.ktdsuniversity.edu.global.config.WebSocketConfig;
import com.ktdsuniversity.edu.global.exceptions.AjaxException;
import com.ktdsuniversity.edu.global.exceptions.BrichException;
import com.ktdsuniversity.edu.global.util.SessionUtil;

@Service
public class CampaignServiceImpl implements CampaignService {

    private final WebSocketConfig webSocketConfig;
	
	private static final Logger log = LoggerFactory.getLogger(CampaignServiceImpl.class);

    @Autowired
    private CampaignDao campaignDao;
    
    @Autowired
    private FileGroupDao fileGroupDao;
    
    @Autowired
    private FileDao fileDao;
    
    @Autowired
    private MultipartFileHandler multipartFileHandler;

    CampaignServiceImpl(WebSocketConfig webSocketConfig) {
        this.webSocketConfig = webSocketConfig;
    }
    
    /**
     * 캠페인 메인
     * 켐페인 상세 조회
     * 조회조건 : 캠페인 아이디
     */
	@Override
	public ResponseCampaignVO readCampaignDetail(String campaignId) {
		
		Map<String, String> param = new HashMap<>();
		param.put("campaignId",campaignId);		
		// 상세조회
		ResponseCampaignVO detail = campaignDao.selectCampaignDetailById(param);
		
		// 공통코드 이름 출력
		String changeSttsCdNm = campaignDao.selectCampaignChangeSttsCd(detail.getSttsCd());
		String changePstSttsCdNm = campaignDao.selectCampaignChangeSttsCd(detail.getPstSttsCd());
		detail.setSttsCdNm(changeSttsCdNm);
		detail.setPstSttsCdNm(changePstSttsCdNm);
		
    	// 부모지역명 자르기 // 서울특별시 -> 서울
    	if(detail.getParentArea() != null) {
    		detail.setParentArea(detail.getParentArea().substring(0, 2));
    	}
		
		return detail;
	}
	
	@Override
	public ResponseCampaignVO readCampaignDetail(String campaignId, String usrId) {
		
		Map<String, String> param = new HashMap<>();
		param.put("blgId", usrId);
		param.put("campaignId",campaignId);
		
		// 상세조회
		ResponseCampaignVO detail = campaignDao.selectCampaignDetailById(param);
		
		// 공통코드 이름 출력
		String changeSttsCdNm = campaignDao.selectCampaignChangeSttsCd(detail.getSttsCd());
		String changePstSttsCdNm = campaignDao.selectCampaignChangeSttsCd(detail.getPstSttsCd());
		detail.setSttsCdNm(changeSttsCdNm);
		detail.setPstSttsCdNm(changePstSttsCdNm);
		
    	// 부모지역명 자르기 // 서울특별시 -> 서울
    	if(detail.getParentArea() != null) {
    		detail.setParentArea(detail.getParentArea().substring(0, 2));
    	}
		
		return detail;
	}
	
	/**
	 * 캠페인 메인 
	 * 캠페인 목록 조회
	 * 조회조건 : 카테고리, 검색어, 정렬순
	 */
	@Override
	public ResponseCampaignListVO readCampaignListAndCategory (RequestSearchCampaignVO requestSearchCampaignVO) {
		
		ResponseCampaignListVO responseCampaignListVO = new ResponseCampaignListVO();
		
		// Level 1 카테고리 목록 구하기
		List<CommonCodeVO> CategoryList = campaignDao.selectCategoryList();
		CategoryList.get(0).setCdNm("전체");
		responseCampaignListVO.setCategoryList(CategoryList);
		log.info("카테고리 목록 : "+ CategoryList.toString());
		
		// Level 2 조회조건 세팅
		if(requestSearchCampaignVO.getCategory() != null ) {
			// 부모 카테고리 조회
			String searchCatagory = campaignDao.selectCategoryParent(requestSearchCampaignVO.getCategory());
			requestSearchCampaignVO.setCategory(searchCatagory);
			log.info("조회할 카테고리 번호 : " + searchCatagory);
		}
		requestSearchCampaignVO.setListSize(16);
		if(requestSearchCampaignVO.getSortBy() == null) {
			// 초기 정렬 세팅
			requestSearchCampaignVO.setSortBy("latest");
		}
		// Level 3 조회
		responseCampaignListVO.setResponseCampaignList(campaignDao.selectCampaignListCategoryAndSortBy(requestSearchCampaignVO));
			
    	// 부모지역명 자르기 // 서울특별시 -> 서울
		List<ResponseCampaignVO> list = responseCampaignListVO.getResponseCampaignList();
		for(ResponseCampaignVO vo : list) {
			if(vo.getParentArea() != null) {
				vo.setParentArea(vo.getParentArea().substring(0, 2));				
			}
		}
		
		return responseCampaignListVO;
	}
	
	/**
	 * 신청한 캠페인
	 */
	
	@Override
	public ResponseCampaignListVO readSubmittedMyCampaignByBlgId(String blgId) {
		ResponseCampaignListVO responseCampaignListVO = new ResponseCampaignListVO();
		
		// ('2005', '2006')  -- 모집중 캠페인
		List<String> code = List.of("2005","2006");
		Map<String, Object> param = new HashMap<>();
		param.put("blgId", blgId);
		param.put("statuses", code);
		
		responseCampaignListVO.setResponseCampaignList(campaignDao.selectMyCampaignByBlgId(param));
		
		return responseCampaignListVO;
	}
	
	/**
	 * 
	 * 진행중 캠페인
	 */
	@Override
	public ResponseCampaignListVO readOnGoingMyCampaignByBlgId(String blgId) {
		ResponseCampaignListVO responseCampaignListVO = new ResponseCampaignListVO();
		
		// ('2007')  -- 진행중 캠페인
		List<String> code = List.of("2007");
		Map<String, Object> param = new HashMap<>();
		param.put("blgId", blgId);
		param.put("statuses", code);
		
		responseCampaignListVO.setResponseCampaignList(campaignDao.selectMyCampaignByBlgId(param));
		
		return responseCampaignListVO;
	}
	
	/**
	 * 
	 * 마감된 캠페인
	 */
	@Override
	public ResponseCampaignListVO readClosedMyCampaignByBlgId(String blgId) {
		ResponseCampaignListVO responseCampaignListVO = new ResponseCampaignListVO();		
		// ('2009')  -- 종료 캠페인
		List<String> code = List.of("2009");
		Map<String, Object> param = new HashMap<>();
		param.put("blgId", blgId);
		param.put("statuses", code);
		
		responseCampaignListVO.setResponseCampaignList(campaignDao.selectMyCampaignByBlgId(param));
		
		return responseCampaignListVO;
	}	
	
	/**
	 * 좋아요캠페인
	 */
	@Override
	public ResponseCampaignListVO readFavMyCampaignByBlgId(String blgId) {
		
		ResponseCampaignListVO responseCampaignListVO = new ResponseCampaignListVO();		
		// ('2005')  -- 모집중
		List<String> code = List.of("2005");
		Map<String, Object> param = new HashMap<>();
		param.put("blgId", blgId);
		param.put("statuses", code);
		
		responseCampaignListVO.setResponseCampaignList(campaignDao.selectMyFavCampaignByBlgId(param));
		
		return responseCampaignListVO;
	}	

	public ResponseApplicantListVO readApplicantListById(RequestApplicantVO requestApplicantVO) {
		int applicantCount = this.campaignDao.selectApplicantCountByCmpnId(requestApplicantVO);
		requestApplicantVO.setPageCount(applicantCount);
		
		List<ResponseApplicantVO> applicant = this.campaignDao.selectApplicantListByCmpnId(requestApplicantVO);
		int adoptCount = this.campaignDao.selectAdoptCountByCmpnId(requestApplicantVO.getCmpnId());
		CampaignVO campaignInfo = this.campaignDao.selectCampaignInfoByCmpnId(requestApplicantVO.getCmpnId());
		
		UserVO userId = SessionUtil.getLoginObject();
		if (!campaignInfo.getUsrId().equals(userId.getUsrId())) {
			throw new BrichException("잘못된 접근입니다.", "error/403");
		}
		
		String[] banList = {"2007", "2008"};
		if (Arrays.asList(banList).contains(campaignInfo.getSttsCd())) {
			throw new BrichException("잘못된 접근입니다.", "error/403");
		}
		
		ResponseApplicantListVO applicantList = new ResponseApplicantListVO();
		applicantList.setApplicantList(applicant);
		applicantList.setAdoptCount(adoptCount);
		applicantList.setCampaignInfo(campaignInfo);
		
		return applicantList;
	}
	
	@Override
	@Transactional
	public boolean updateAdptYnByCmpnPstAdptId(RequestApplicantVO requestApplicantVO) {
		CampaignVO campaign = this.campaignDao.selectCampaignStateByCmpnPstAdptId(requestApplicantVO.getCmpnPstAdptId());
		if (!campaign.getSttsCd().equals("2006")) {
			throw new AjaxException("잘못된 접근입니다.", HttpStatus.NOT_FOUND);
		}
		
		UserVO userId = SessionUtil.getLoginObject();
		if (!campaign.getUsrId().equals(userId.getUsrId())) {
			throw new AjaxException("잘못된 접근입니다.", HttpStatus.NOT_FOUND);
		}
		
		int updateCount = this.campaignDao.updateAdptYnByCmpnPstAdptId(requestApplicantVO);
		
		return updateCount > 0;
	}

	@Override
	public ResponseAdoptListVO readResponseAdoptListByCmpnId(RequestApplicantVO requestApplicantVO) {
		List<ResponseAdoptVO> adopt = this.campaignDao.selectAdoptListByCmpnId(requestApplicantVO);
		int adoptCount = this.campaignDao.selectAdoptPaginationCount(requestApplicantVO);
		requestApplicantVO.setPageCount(adoptCount);
		
		ResponseCampaignVO campaign = this.campaignDao.selectCampaignInfoByCmpnId(requestApplicantVO.getCmpnId());
		UserVO userId = SessionUtil.getLoginObject();
		if (!campaign.getUsrId().equals(userId.getUsrId())) {
			throw new BrichException("잘못된 접근입니다.", "error/403");
		}
		
		String[] whiteList = {"2007", "2009"};
		if (!Arrays.asList(whiteList).contains(campaign.getSttsCd())) {
			throw new BrichException("잘못된 접근입니다.", "error/403");
		}
		
		ResponseAdoptListVO adoptList = new ResponseAdoptListVO();
		adoptList.setAdoptList(adopt);
		adoptList.setCampaignInfo(campaign);
		adoptList.setCmpnCdNm(this.campaignDao.selectStateNameByStateCode(adoptList.getCampaignInfo().getSttsCd()));
		
		return adoptList;
	}
	/**
	 * 사랑해요
	 */
	@Override
	public int favCampaignDo(String blgId, String campaignId) {
		int updateCount = 0 ;
		Map<String, String> param = new HashMap<>();
		param.put("blgId",blgId);
		param.put("campaignId",campaignId);
		
		// 최초 생성인지 확인
		String favExists = this.campaignDao.selectFavCamapignExists(param);
		if(favExists.equals("0")) { 
			// 없다면 최초 생성
			updateCount = this.campaignDao.insertFavCamapign(param);			
		} else {
			// 하트가 제거되었는지 확인
			String favDltYn = this.campaignDao.selectFavDltYn(param);
			if(favDltYn.equals("N")) {
				// 있다면 하트제거
				updateCount = this.campaignDao.updateFavCamapignOff(param);				
			}else {
				// 있는데 삭제라면 하트생성
				updateCount = this.campaignDao.updateFavCamapignOn(param);							
			}
		}
		
		
		
		return updateCount ;
	}
	
	/**
	 * 캠페인 신청하기
	 */
	@Override
	public int applyCampaign(String campaignId, String blgId) {
		
		Map<String, String> param = new HashMap<>();
		param.put("blgId",blgId);
		param.put("campaignId",campaignId);
		int count = 0;
		//캠페인 모집중 여부 확인	
		ResponseCampaignVO detail = campaignDao.selectCampaignDetailById(param);
		if(detail.getSttsCd().equals("2005")) {
			// 캠페인 신청 이력 여부 확인
			    String hasAdoptYn = campaignDao.selecthasAdoptYn(param);
			    if(hasAdoptYn.equals("N")) {
			    	// 없다면 캠페인 생성 (insert)
			    	count = this.campaignDao.insertApplyCampaign(param);			    	
			    } else {
			    	String AdoptDltYn = campaignDao.selectAdoptDltYn(param);
			    	if(AdoptDltYn.equals("N")) {   // 신청 취소
			    		count = this.campaignDao.updateCancelApplyCampaign(param);
			    	}else {						  // 신청
			    		count = this.campaignDao.updateApplyCampaign(param);			    		
			    	}
			    }
		} else {
		}
		return count;
	}
	
	@Override
	public int postSubmit(RequestPostSubmitVO requestPostSubmitVO) {
		
		int postSubmitCount = campaignDao.updatePostSubmit(requestPostSubmitVO);
		
		return postSubmitCount;
	}
	
	@Transactional
	@Override
	public int rePostSubmit(RequestPostSubmitVO requestPostSubmitVO) {
		
    	List<FileVO> uploadResult = this.multipartFileHandler.upload(requestPostSubmitVO.getFile());
    	
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
			requestPostSubmitVO.setPostFlGrpId(fileGroupVO.getFlGrpId());
		}
    	
    	
		int count = 0;
		// 재재출 변경내용 업데이트
		int postSubmitCount = campaignDao.updateRePostSubmit(requestPostSubmitVO);
		
		// 재재출 변경 제목 url 업데이트
		int postSubmitCnCount = campaignDao.updatePostSubmit(requestPostSubmitVO);
		
		// 포스트 상태 변경    반려 -> 검토중    6003 -> 6002
		int postSttsCount = campaignDao.updateRePostSubmitStts(requestPostSubmitVO);
		
		
		if (postSubmitCount == 1 && postSttsCount == 1 && postSubmitCnCount  == 1) {
			log.info("정상처리");
			count = 1;
		}
		
		return count;
	}

	@Transactional
	@Override
	public boolean updatePstSttsApproveByCmpnPstAdoptId(RequestApplicantVO requestApplicantVO) {
		CampaignVO campaign = this.campaignDao.selectCampaignStateByCmpnPstAdptId(requestApplicantVO.getCmpnPstAdptId());
		if (!campaign.getSttsCd().equals("2007")) {
			throw new AjaxException("잘못된 접근입니다.", HttpStatus.NOT_FOUND);
		}
		
		UserVO userId = SessionUtil.getLoginObject();
		if (!campaign.getUsrId().equals(userId.getUsrId())) {
			throw new AjaxException("잘못된 접근입니다.", HttpStatus.NOT_FOUND);
		}
		
		RequestUpdatePstSttsVO requestUpdatePstSttsVO = new RequestUpdatePstSttsVO();
		requestUpdatePstSttsVO.setCmpnPstAdptId(requestApplicantVO.getCmpnPstAdptId());
		requestUpdatePstSttsVO.setStts("6004");
		requestUpdatePstSttsVO.setAdvId(requestApplicantVO.getUsrId());
		int updateCount = this.campaignDao.updatePstSttsByCmpnPstAdoptId(requestUpdatePstSttsVO);
		return updateCount == 1;
	}
	
	@Transactional
	@Override
	public boolean createDenyByCmpnPstAdoptId(RequestDenyVO requestDenyVO) {
		CampaignVO campaign = this.campaignDao.selectCampaignStateByCmpnPstAdptId(requestDenyVO.getCmpnPstAdptId());
		if (!campaign.getSttsCd().equals("2007")) {
			throw new AjaxException("잘못된 접근입니다.", HttpStatus.NOT_FOUND);
		}
		
		UserVO userId = SessionUtil.getLoginObject();
		if (!campaign.getUsrId().equals(userId.getUsrId())) {
			throw new AjaxException("잘못된 접근입니다.", HttpStatus.NOT_FOUND);
		}
		
		List<FileVO> uploadResult = this.multipartFileHandler.upload(requestDenyVO.getFile());
		RequestUpdatePstSttsVO requestUpdatePstSttsVO = new RequestUpdatePstSttsVO();
		requestUpdatePstSttsVO.setCmpnPstAdptId(requestDenyVO.getCmpnPstAdptId());
		requestUpdatePstSttsVO.setStts("6003");
		requestUpdatePstSttsVO.setAdvId(requestDenyVO.getAdvId());
		
		RequestDenyVO requestDdlnVO = new RequestDenyVO();
		requestDdlnVO.setCmpnPstAdptId(requestDenyVO.getCmpnPstAdptId());
		requestDdlnVO.setPstDdln(requestDenyVO.getPstDdln());
		requestDdlnVO.setAdvId(requestDenyVO.getAdvId());
		
		if (uploadResult != null && uploadResult.size() > 0) {
			// 1. File Group Insert
			FileGroupVO fileGroupVO = new FileGroupVO();
			fileGroupVO.setFlCnt(uploadResult.size());
			int insertGroupCount = this.fileGroupDao.insertFileGroup(fileGroupVO);
			
			// 2. File Insert
			for(FileVO result: uploadResult) {
				result.setFlGrpId(fileGroupVO.getFlGrpId());
				int insertFileCount = this.fileDao.insertFile(result);
			}
			
			requestDenyVO.setFileGroupId(fileGroupVO.getFlGrpId());
		}
		
		int insertCount = this.campaignDao.insertDenyByCmpnPstAdoptId(requestDenyVO);
		int updateCount = 0;
		int updateDateCount = 0;
		int updateCmpnDateCount = 0;
		
		if (insertCount == 1) {
			updateCount = this.campaignDao.updatePstSttsByCmpnPstAdoptId(requestUpdatePstSttsVO);
			updateDateCount = this.campaignDao.updateDdlnByCmpnPstAdoptId(requestDdlnVO);
		}
		
		if (updateDateCount == 1) {
			updateCmpnDateCount = this.campaignDao.udpateCmpnDateByCmpnId(requestDenyVO);
		}
		
		return insertCount == 1 && updateCount == 1 && updateDateCount == 1 && updateCmpnDateCount == 1;
	}

	@Override
	public ResponseCampaignwriteVO createCampaign() {
		ResponseCampaignwriteVO common = new ResponseCampaignwriteVO();
		common.setDoAndCityList(this.campaignDao.selectDoAndCityList());
		common.setCategoryList(this.campaignDao.selectCategoryList());
		common.setPersonPrice(this.campaignDao.selectPersonPrice());
		return common;
	}

	@Override
	public List<CommonCodeVO> readDistrictByCdId(String cdId) {
		List<CommonCodeVO> districtList = this.campaignDao.selectDistrictByCdId(cdId);
		return districtList;
	}

	@Override
	@Transactional
	public boolean createNewCampaign(RequestCreateCmpnVO requestCreateCmpnVO) {
		if (requestCreateCmpnVO.getRoadAddress() != null || requestCreateCmpnVO.getDetailAddress() != null) {
			String addr = requestCreateCmpnVO.getRoadAddress() + " " + requestCreateCmpnVO.getDetailAddress();
			requestCreateCmpnVO.setAddrs(addr);
		}
		
		FileVO uploadResult = this.multipartFileHandler.upload(requestCreateCmpnVO.getFile());
		
		if (uploadResult != null) {
			// 1. File Group Insert
			FileGroupVO fileGroupVO = new FileGroupVO();
			fileGroupVO.setFlCnt(1);
			int insertGroupCount = this.fileGroupDao.insertFileGroup(fileGroupVO);
			
			// 2. File Insert
			uploadResult.setFlGrpId(fileGroupVO.getFlGrpId());
			int insertFileCount = this.fileDao.insertFile(uploadResult);
			
			requestCreateCmpnVO.setAttchGrpId(fileGroupVO.getFlGrpId());
		}
		
		requestCreateCmpnVO.setSttsCd("2001");
		int insertCmpnCount = this.campaignDao.insertNewCampaign(requestCreateCmpnVO);
		
		if (requestCreateCmpnVO.getArea() != null && insertCmpnCount == 1) {
			RequestCampaignAreaVO requestCampaignAreaVO = new RequestCampaignAreaVO();
			requestCampaignAreaVO.setCmpnId(requestCreateCmpnVO.getCmpnId());
			requestCampaignAreaVO.setUsrId(requestCreateCmpnVO.getUsrId());
			
			for(String arCd : requestCreateCmpnVO.getArea()) {
				requestCampaignAreaVO.setArCd(arCd);
				int insertAreaCount = this.campaignDao.insertCampaignCategory(requestCampaignAreaVO);
			}
		}
		
		return insertCmpnCount == 1;
	}

	@Override
	public ResponseCampaignListVO readCampaignListByUsrId(RequestSearchCampaignVO requestSearchCampaignVO) {
		int campaignCount = this.campaignDao.selectCampaignListCountByusrId(requestSearchCampaignVO);
		requestSearchCampaignVO.setPageCount(campaignCount);
		
		System.out.println(requestSearchCampaignVO.toString());
		
		List<ResponseCampaignVO> campaignList = this.campaignDao.selectCampaignListByUsrId(requestSearchCampaignVO);
		
		ResponseCampaignListVO campaign = new ResponseCampaignListVO();
		campaign.setResponseCampaignList(campaignList);
		return campaign;
	}

	@Override
	public ResponseCampaignListVO readDenyHistoryByCmpnId(RequestSearchCampaignVO requestSearchCampaignVO) {
		CampaignVO campaignInfo = this.campaignDao.selectCampaignInfoByCmpnId(requestSearchCampaignVO.getCmpnId());
		
		UserVO userId = SessionUtil.getLoginObject();
		if (!campaignInfo.getUsrId().equals(userId.getUsrId())) {
			throw new BrichException("잘못된 접근입니다.", "error/403");
		}
		
		List<ResponseCampaignVO> denyList = this.campaignDao.selectDenyListByCmpnId(requestSearchCampaignVO);
		
		ResponseCampaignListVO deny = new ResponseCampaignListVO();
		deny.setResponseCampaignList(denyList);
		
		deny.setCampaignInfo(this.campaignDao.selectCampaignInfoByCmpnId(requestSearchCampaignVO.getCmpnId()));
		
		return deny;
  }
  
  @Override
	public String postReturnReason(String campaignId, String usrId) {
		
		Map<String, String> param = new HashMap<>();
		param.put("blgId", usrId);
		param.put("cmpnId",campaignId);
		
		String returnReason = this.campaignDao.selectReturnReason(param);
		return returnReason;
	}

  @Transactional
  @Override
  public boolean modifyNewCampaign(RequestCreateCmpnVO requestCreateCmpnVO) {
	int modify = 0;
	if (requestCreateCmpnVO.getSttsCd().equals("2008")) {
		if (requestCreateCmpnVO.getRoadAddress() != null || requestCreateCmpnVO.getDetailAddress() != null) {
			String addr = requestCreateCmpnVO.getRoadAddress() + " " + requestCreateCmpnVO.getDetailAddress();
			requestCreateCmpnVO.setAddrs(addr);
		}
		
		FileVO uploadResult = this.multipartFileHandler.upload(requestCreateCmpnVO.getFile());
		
		if (uploadResult != null) {
			// 1. File Group Insert
			FileGroupVO fileGroupVO = new FileGroupVO();
			fileGroupVO.setFlCnt(1);
			int insertGroupCount = this.fileGroupDao.insertFileGroup(fileGroupVO);
			
			// 2. File Insert
			uploadResult.setFlGrpId(fileGroupVO.getFlGrpId());
			int insertFileCount = this.fileDao.insertFile(uploadResult);
			
			requestCreateCmpnVO.setAttchGrpId(fileGroupVO.getFlGrpId());
		}
		requestCreateCmpnVO.setSttsCd("2001");
		modify = this.campaignDao.updateTemporaryCampaignByCmpnId(requestCreateCmpnVO);
		
		if (requestCreateCmpnVO.getArea() != null && modify == 1) {
			RequestCampaignAreaVO requestCampaignAreaVO = new RequestCampaignAreaVO();
			requestCampaignAreaVO.setCmpnId(requestCreateCmpnVO.getPrevCmpnId());
			requestCampaignAreaVO.setUsrId(requestCreateCmpnVO.getUsrId());
			
			for(String arCd : requestCreateCmpnVO.getArea()) {
				requestCampaignAreaVO.setArCd(arCd);
				int insertAreaCount = this.campaignDao.insertCampaignCategory(requestCampaignAreaVO);
			}
		}
	}
	else {
		requestCreateCmpnVO.setSttsCd("2001");
		boolean insert = this.createNewCampaign(requestCreateCmpnVO);
		if (insert) {
			modify = this.campaignDao.updateCmpnPrntIdByCmpnId(requestCreateCmpnVO);
		}
	}
	return modify == 1;
  }

  @Transactional
  @Override
  public boolean createTemporaryCampaign(RequestCreateCmpnVO requestCreateCmpnVO) {
	int count = 0;
	if (requestCreateCmpnVO.getSttsCd() == null) {
		requestCreateCmpnVO.setSttsCd("2008");
		count = this.campaignDao.insertNewCampaign(requestCreateCmpnVO);
	}
	else if (requestCreateCmpnVO.getSttsCd().equals("2008")) {
		count = this.campaignDao.updateTemporaryCampaignByCmpnId(requestCreateCmpnVO);
	}
	else if (requestCreateCmpnVO.getSttsCd().equals("2003")) {
		requestCreateCmpnVO.setSttsCd("2008");
		count = this.campaignDao.insertNewCampaign(requestCreateCmpnVO);
		if (count == 1) {
			count = this.campaignDao.updateCmpnPrntIdByCmpnId(requestCreateCmpnVO);
		}
	}
	return count == 1;
  }

  @Override
  public ResponseModifyCampaignVO readModifyInfoByCmpnId(String cmpnId) {
	ResponseCampaignwriteVO common = this.createCampaign();
	ResponseCampaignVO campaign = this.readCampaignDetail(cmpnId);
	
	ResponseModifyCampaignVO modifyInfo = new ResponseModifyCampaignVO();
	modifyInfo.setCampaign(campaign);
	modifyInfo.setCommon(common);
	return modifyInfo;
  }

  @Override
  public List<ResponseDenyHistoryVO> readDenyHistoryByCmpnPstAdptId(String postId) {
	CampaignVO campaign = this.campaignDao.selectCampaignStateByCmpnPstAdptId(postId);
	CampaignPostAdoptVO post = this.campaignDao.selectCampaignPostByPstAdptId(postId);
	if (!campaign.getSttsCd().equals("2007")) {
		System.out.println("현재 상태코드: " + campaign.getSttsCd());
		System.out.println("캠페인 작성자: " + campaign.getUsrId());
		throw new AjaxException("잘못된 접근입니다.", HttpStatus.NOT_FOUND);
	}
	
	UserVO userId = SessionUtil.getLoginObject();
	if (!post.getBlgUsrId().equals(userId.getUsrId()) && !campaign.getUsrId().equals(userId.getUsrId())) {
		System.out.println("현재 상태코드: " + campaign.getSttsCd());
		System.out.println("캠페인 작성자: " + campaign.getUsrId());
		System.out.println("로그인 사용자: " + userId.getUsrId());
		throw new AjaxException("잘못된 접근입니다.", HttpStatus.NOT_FOUND);
	}


	return this.campaignDao.selectDenyHistoryByCmpnPstAdptId(postId);
  }

  @Override
  public List<CampaignVO> readExpireSoonCampaignList(RequestExpireSoonCampaignVO requestExpireSoonCampaignVO) {
	  
	return this.campaignDao.selectExpireSoonCampaign(requestExpireSoonCampaignVO);
  }

  @Override
  public List<CampaignPostManageVO> readCampaignManageListByUsrId(String usrId) {
	return this.campaignDao.selectCampaignPostListByUsrId(usrId);
  }

  @Override
  public List<CampaignVO> readRecommendedCampaignByUsrId(String usrId) {
	return this.campaignDao.selectRecommendedCampaignByUsrId(usrId);
  }

  @Override
  public Map<String, Object> readCampaignIndexStats(String cmpnId, String usrId) {
      Map<String, Object> result = new HashMap<>();

      // 참여자별 블로그 지수 DAO에서 직접 조회 (BlogDataService 의존 X)
      List<CampaignIndexStatVO> list = campaignDao.selectCampaignIndexStats(cmpnId);
      result.put("list", list);

      // 통계 계산
      double sum = 0;
      double max = Double.MIN_VALUE;
      double min = Double.MAX_VALUE;

      for (CampaignIndexStatVO vo : list) {
          double avg = vo.getAvgIndx();
          sum += avg;
          if (avg > max) max = avg;
          if (avg < min) min = avg;
      }

      double overallAvg = list.isEmpty() ? 0 : Math.round((sum / list.size()) * 100.0) / 100.0;

      // ✅ 내 지수는 DB 쿼리에서 같이 가져오기 (별도 필드)
      Double myIndex = campaignDao.selectMyBlogIndexInCampaign(cmpnId, usrId);

      result.put("myIndex", myIndex != null ? myIndex : 0.0);
      result.put("overallAvg", overallAvg);
      result.put("max", max == Double.MIN_VALUE ? 0 : max);
      result.put("min", min == Double.MAX_VALUE ? 0 : min);

      return result;
  }


}
