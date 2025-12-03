package com.ktdsuniversity.edu.domain.user.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.file.dao.FileDao;
import com.ktdsuniversity.edu.domain.file.util.MultipartFileHandler;
import com.ktdsuniversity.edu.domain.file.vo.FileVO;
import com.ktdsuniversity.edu.domain.report.dao.PenaltyHistoryDao;
import com.ktdsuniversity.edu.domain.report.vo.AdminPenaltyRequestVO;
import com.ktdsuniversity.edu.domain.user.dao.AdminUserDao;
import com.ktdsuniversity.edu.domain.user.dao.BlogCategoryDao;
import com.ktdsuniversity.edu.domain.user.service.AdminUserService;
import com.ktdsuniversity.edu.domain.user.vo.AdminAdvertiserDetailVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminBloggerAreaInfoVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminBloggerCategoryInfoVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminBloggerDetailVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminUserBaseInfoVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminUserListVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminUserModifyInfoVO;
import com.ktdsuniversity.edu.domain.user.vo.BlogCategoryVO;
import com.ktdsuniversity.edu.domain.user.vo.UserUpdateHistoryVO;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

@Service
public class AdminUserServiceImpl implements AdminUserService {

	private static final Logger log = LoggerFactory.getLogger(AdminUserServiceImpl.class);
	
	@Autowired
	private AdminUserDao adminUserDao;
	
	@Autowired
	private BlogCategoryDao blogCategoryDao;
	
	@Autowired
	private FileDao fileDao;
	
	@Autowired
	private PenaltyHistoryDao penaltyHistoryDao;
	
	@Autowired
	private MultipartFileHandler multipartFileHandler;
	
	/**
	 * 회원 관리 목록
	 * @param tab
	 * @return
	 */
	@Override
	public List<AdminUserListVO> readAdminUserList(String tab) {
		
		if(tab.equals("all")) {
			return this.adminUserDao.selectAdminUserList();
		}
		else if(tab.equals("blogger")) {
			return this.adminUserDao.selectAdminBloggerList();
		}
		else if(tab.equals("advertiser")) {
			return this.adminUserDao.selectAdminAdvertiserList();
		}
		else {
			throw new IllegalArgumentException("error");
		}
		
	}

	/**
	 * 회원 상세 정보 
	 * @param usrId
	 * @return
	 */
	@Override
	public AdminUserBaseInfoVO readAdminUserDetailById(String usrId) {
		
		String userAutr = this.adminUserDao.selectAdminUserAutrById(usrId);
		
		if(userAutr.equals("1002") || userAutr.equals("1003")) {
			AdminBloggerDetailVO info = this.adminUserDao.selectAdminBloggerDetailById(usrId);
			
			List<CampaignVO> progressList = this.adminUserDao.selectBloggerCmpnProgressList(usrId);
		    List<CampaignVO> completedList = this.adminUserDao.selectBloggerCmpnCompletedList(usrId);
		    List<AdminBloggerAreaInfoVO> usrAr = this.adminUserDao.selectBloggerAreaList(usrId);
		    List<AdminBloggerCategoryInfoVO> usrBlgCtg = this.adminUserDao.selectBloggerCategoryList(usrId);
		    
		    info.setCmpnProgressList(progressList);
		    info.setCmpnCompletedList(completedList);
		    info.setUsrAr(usrAr);
		    info.setUsrBlgCtg(usrBlgCtg);
		    
		    /*
	        if (usrAr != null) {
	            List<String> checkedAreaIds = usrAr.stream()
	                .map(AdminBloggerAreaInfoVO::getArId)
	                .collect(Collectors.toList());
	            info.setCheckedAreaIds(checkedAreaIds);
	        }
	        */
		    
	        if (usrBlgCtg != null) {
	            List<String> checkedCategory = 
	            		usrBlgCtg.stream()
	            				 .map(AdminBloggerCategoryInfoVO::getCdId)
	            				 .collect(Collectors.toList());
	            info.setCheckedBlgCtg(checkedCategory);
	            
	            // log.info("기존 카테고리 " + info.getCheckedBlgCtg());
	        }
	        
			return info;
		}
		else if(userAutr.equals("1004") || userAutr.equals("1007")) {
			AdminAdvertiserDetailVO info = this.adminUserDao.selectAdminAdvertiserDetailById(usrId);
			
			List<CampaignVO> progressList = this.adminUserDao.selectAdvertiserCmpnProgressList(usrId);
		    List<CampaignVO> completedList = this.adminUserDao.selectAdvertiserCmpnCompletedList(usrId);
		    
		    info.setCmpnProgressList(progressList);
		    info.setCmpnCompletedList(completedList);
			
			return info;
		}
		else {
			// 임시 코드
			throw new IllegalArgumentException("error");
		}
		
	}

	/**
	 * 광고주 가입 승인/반려
	 * @param requestData
	 * @return
	 */
	@Transactional
	@Override
	public boolean updateAdvertiserRegistAuthCode(Map<String, String> requestData) {
		
		String changeAutr = requestData.get("autr");
		
		if(changeAutr.equals("1004")) {
			return this.adminUserDao.updateAdvertiserAuthCodeByApprove(requestData) > 0;
		}
		else if(changeAutr.equals("1008")) {
			return this.adminUserDao.updateAdvertiserAuthCodeByReject(requestData) > 0;
		}
		else {
			// 임시 코드
			throw new IllegalArgumentException("error");
		}
	}

	/**
	 * 블로거 카테고리 받아오기 (List)
	 * @return
	 */
	@Override
	public List<CommonCodeVO> readBlogCategoryList() {
		return this.adminUserDao.selectBlogCategoryList();
	}

	/**
	 * 회원 정보 수정
	 * @param adminUserModifyInfoVO
	 * @param newFiles
	 * @return
	 */
	@Transactional
	@Override
	public boolean updateUserInfo(AdminUserModifyInfoVO adminUserModifyInfoVO, List<MultipartFile> newFiles) {
		
		String adminId = adminUserModifyInfoVO.getAdminId();
		String usrId = adminUserModifyInfoVO.getUsrId();
		
		// 수정 전 회원 정보 백업
		UserVO beforeInfo = this.adminUserDao.selectUserInfoById(usrId);

		// 회원 정보 UPDATE (기본 정보만)
		int updateCount = this.adminUserDao.updateUserInfo(adminUserModifyInfoVO);
		daoValidate(updateCount, "updateUserInfo");
		
		// 업데이트된 회원 정보를 UserVO로 변환 (기존 정보와 비교할 준비)
	    UserVO afterInfo = convertToUserVO(adminUserModifyInfoVO);
		
		// 수정할 회원의 권한 가져오기
		String modifyUserAutr = beforeInfo.getAutr();
		
		// 블로거/광고주 분기 처리
		if(modifyUserAutr.equals("1002") || modifyUserAutr.equals("1003")) {
			
			updateOrInsertBlogCategories(adminUserModifyInfoVO.getUsrBlgCtg(), usrId, adminId);
		}
		else if(modifyUserAutr.equals("1004")) {
			
			// 상호명
			updateCount = this.adminUserDao.updateAdvertiserInfo(adminUserModifyInfoVO);
			daoValidate(updateCount, "updateAdvertiserInfo");
			
			// 사업자 등록증 파일 INSERT or UPDATE 구분 처리
			updateOrInsertFiles(adminUserModifyInfoVO, newFiles);
		}
		
		// UPDATE 전 데이터와, UPDATE 후 데이터를 비교하여 변경 목록 추출?
	    List<UserUpdateHistoryVO> historyList = compareBeforeAndAfterData(beforeInfo, afterInfo, adminUserModifyInfoVO.getUpdtRsn(), adminId);
	    
	    // UPDATE 될 데이터가 있다면, 
	    if(!historyList.isEmpty()) {
	    	
	    	// 회원 정보 수정 이력 테이블에 INSERT
	    	updateCount = this.adminUserDao.insertUpdateHistory(historyList);
	    	daoValidate(updateCount, "insertUpdateHistory");
	    }
	    
	    return true;
	}
	
	/**
	 * 수정될 회원 정보 UserVO에 매핑 시키기 (수정 전/수정 후 비교를 위해)
	 * (UPDATE 후의 UserVO ==> afterInfo)
	 * @param adminUserModifyInfoVO
	 * @return
	 */
	public UserVO convertToUserVO(AdminUserModifyInfoVO adminUserModifyInfoVO) {
		
		UserVO userVO = new UserVO();
		
		// 수정 정보 매핑
		userVO.setUsrId(adminUserModifyInfoVO.getUsrId());
		userVO.setLogId(adminUserModifyInfoVO.getLogId());
		userVO.setEml(adminUserModifyInfoVO.getEml());
		userVO.setNm(adminUserModifyInfoVO.getNm());
		userVO.setAutr(adminUserModifyInfoVO.getAutr());
		userVO.setMttr(adminUserModifyInfoVO.getAdminId());
		
		// 광고주 전용 정보 매핑
		if(adminUserModifyInfoVO.getAutr().equals("1004")) {
			userVO.setCmpny(adminUserModifyInfoVO.getCmpny());
			userVO.setFlGrpId(adminUserModifyInfoVO.getFlGrpId());
		}
		
		return userVO;
	}

	/**
	 * 블로거 카테고리 추가/삭제/재활성화 대상 판별 후 UPATE/INSERT
	 * @param usrBlgCtg
	 * @param usrId
	 * @param adminId
	 */
	public void updateOrInsertBlogCategories(List<String> usrBlgCtg, String usrId, String adminId) {
		
		int updateAndInsertCount = 0;
		
		// 비교할 때 순서 상관없는 Set이 나을 것 같아서 Set 사용 (+ 중복 제거)
		// UPDATE될 블로거의 블로그 카테고리 (checkbox에서 체크된 항목들)
		Set<String> newCategorySet = new HashSet<>(usrBlgCtg);
		
		// 현재 블로거의 블로그 카테고리 가져오기 (UPDATE 전)
		List<BlogCategoryVO> existBlogCategoryList = this.blogCategoryDao.selectUserBlogCategoryById(usrId);
		
		// UPDATE될 블로거의 카테고리 ID Set (cdId)
		Set<String> existIdSet = new HashSet<>();
		
		// 삭제될 카테고리 ID List
		List<String> deleteTargetList = new ArrayList<>();
		
		// 삭제될 카테고리 찾기
		for(BlogCategoryVO existBlogCategoryVO : existBlogCategoryList) {
			
			// UPDATE 전 블로그 카테고리 코드 아이디를
			String categoryId = existBlogCategoryVO.getCdId();
			
			// UPDATE될 블로그 카테고리 코드에 추가
			existIdSet.add(categoryId.trim());
			
			// DB엔 있지만, 변경 정보 VO에 없는 경우
			// ==> UPDATE 전엔 있는 카테고리지만, 
			//     UPDATE 될 카테고리 목록에 해당 카테고리가 존재하지 않을 경우
			if(!newCategorySet.contains(categoryId.trim())) {
				
				// 삭제 대상으로 간주하여 해당 카테고리 아이디를 List에 담음
				deleteTargetList.add(categoryId.trim());
			}
		}
		
		// 삭제 대상이 있다면,
		if(!deleteTargetList.isEmpty()) {
			
			Map<String, Object> deleteParamMap = new HashMap<>();
			deleteParamMap.put("deleteList", deleteTargetList);
			deleteParamMap.put("usrId", usrId);
			deleteParamMap.put("adminId", adminId);
			
			// 해당 ROW -> DLT_YN = 'Y' (UPDATE)
			updateAndInsertCount = this.blogCategoryDao.updateBlogCategoryAsDelete(deleteParamMap);
		}
		
		// 추가될 카테고리 혹은 재활성화 카테고리 찾기
		List<String> insertOrReactiveTarget = new ArrayList<>();
		
		// 기존에 있던 카테고리와 UPDATE될 카테고리를 비교
		for(String newCategoryId : newCategorySet) {
			
			// UPDATE될 카테고리에 기존 카테고리가 존재하지 않는다면,
			if(!existIdSet.contains(newCategoryId)) {
				
				// 추가(INSERT) or 재활성화(UPDATE) 대상으로 간주
				insertOrReactiveTarget.add(newCategoryId);
			}
		}
		
		// 추가 or 재활성화 대상이 있는 경우,
		if(!insertOrReactiveTarget.isEmpty()) {
			
			Map<String, Object> searchParamMap = new HashMap<>();
			searchParamMap.put("searchList", insertOrReactiveTarget);
			searchParamMap.put("usrId", usrId);
			
			// 추가/재활성화 대상 중 비활성화(DLT_YN = 'Y') 상태인 카테고리 조회
			List<String> deletedList = this.blogCategoryDao.selectDeletedCategoryById(searchParamMap);
			
			// 재활성화(DLT_YN = 'N' UPDATE 예정) 대상으로 간주
			List<String> reactiveCategoryList = new ArrayList<>(deletedList);
			
			// 새롭게 추가(INSERT)될 대상 (추가/재활성화 카테고리에서 재활성화 대상만 제외)
			Set<String> insertCategorySet = new HashSet<>(insertOrReactiveTarget);
			insertCategorySet.removeAll(reactiveCategoryList);
			
			// 재활성화 대상이 있다면, ROW UPDATE (DLT_YN = 'N')
			if(!reactiveCategoryList.isEmpty()) {
				
				Map<String, Object> reactiveParamMap = new HashMap<>();
				reactiveParamMap.put("reactiveList", reactiveCategoryList);
				reactiveParamMap.put("usrId", usrId);
				reactiveParamMap.put("adminId", adminId);
				
				updateAndInsertCount = this.blogCategoryDao.updateCategoryAsReactive(reactiveParamMap);
				daoValidate(updateAndInsertCount, "updateCategoryAsReactive");
			}
			
			// 새롭게 추가될 대상이 있다면, ROW INSERT
			if(!insertCategorySet.isEmpty()) {
				for(String insertId : insertCategorySet) {
					
					Map<String, Object> insertParamMap = new HashMap<>();
					insertParamMap.put("insertId", insertId);
					insertParamMap.put("usrId", usrId);
					insertParamMap.put("adminId", adminId);
					
					updateAndInsertCount = this.blogCategoryDao.insertNewBlogCategory(insertParamMap);
				}
			}
		}
		
	}

	/**
	 * 기존 파일 추가/삭제 구분 및 새 파일 업로드 처리 (UPDATE/INSERT)
	 * @param adminUserModifyInfoVO
	 * @param newFiles
	 */
	public void updateOrInsertFiles(AdminUserModifyInfoVO adminUserModifyInfoVO, List<MultipartFile> newFiles) {

		int updateAndInsertCount = 0;
		
		String adminId = adminUserModifyInfoVO.getAdminId();
		
		// 기존 파일 중 삭제될 파일의 flId를 가져옴
		List<String> deleteFiles = adminUserModifyInfoVO.getDeleteFileIds();
		
		// 기존 파일 중 삭제될 항목이 있다면, UPDATE (DLT_YN = 'Y')
		if(deleteFiles != null && !deleteFiles.isEmpty()) {
			
			Map<String, Object> deleteParamMap = new HashMap<>();
			deleteParamMap.put("deleteFiles", deleteFiles);
			deleteParamMap.put("adminId", adminId);
			
			updateAndInsertCount = this.fileDao.updateFilesAsDelete(deleteParamMap);
			// daoValidate(updateAndInsertCount, "updateFilesAsDelete");
		}
		
		// 추가된 파일이 있다면 INSERT
		if(newFiles != null && !newFiles.isEmpty()) {
			List<FileVO> insertResult = this.multipartFileHandler.upload(newFiles);
			
			// 해당 사용자의 flGrpId를 가져옴
			String flGrpId = adminUserModifyInfoVO.getFlGrpId();
			
			// flGrpId가 없다면 새로 발급 (...근데 이건 굳이 없어도 될 것 같아서 일단 비울게요)
			
			// 업로드 된 파일의 flGrpId를 지정
			for(FileVO fileVO : insertResult) {
				fileVO.setFlGrpId(flGrpId);
				updateAndInsertCount = this.fileDao.insertFile(fileVO);
				daoValidate(updateAndInsertCount, "insertFile");
			}
		}
		
	}
	
	/**
	 * 기존 정보와 수정 후 정보를 비교하여, 
	 * 변경된 정보에 대한 데이터를 UserUpdateHistoryVO에 매핑해 주기 위한 메소드
	 * @param beforeInfo
	 * @param afterInfo
	 * @param updtRsn
	 * @param adminId
	 * @return
	 */
	public List<UserUpdateHistoryVO> compareBeforeAndAfterData(UserVO beforeInfo, UserVO afterInfo, String updtRsn, String adminId) {
		
		List<UserUpdateHistoryVO> historyList = new ArrayList<>();
		String usrId = afterInfo.getUsrId();
		
		// logId 비교
		if(!(afterInfo.getLogId().equals(beforeInfo.getLogId()))) {
			historyList.add(createHistory(usrId, "LOG_ID", beforeInfo.getLogId(), afterInfo.getLogId(), adminId, updtRsn));
		}
		
		// eml 비교
		if(!(afterInfo.getEml().equals(beforeInfo.getEml()))) {
			historyList.add(createHistory(usrId, "EML", beforeInfo.getEml(), afterInfo.getEml(), adminId, updtRsn));
		}
		
		// nm 비교
		if(!(afterInfo.getNm().equals(beforeInfo.getNm()))) {
			historyList.add(createHistory(usrId, "NM", beforeInfo.getNm(), afterInfo.getNm(), adminId, updtRsn));
		}
		
		// 광고주의 경우
		if(beforeInfo.getAutr().equals("1004")) {
			
			// cmpny 비교
			if(!(afterInfo.getCmpny().equals(beforeInfo.getCmpny()))) {
				historyList.add(createHistory(usrId, "CMPNY", beforeInfo.getCmpny(), afterInfo.getCmpny(), adminId, updtRsn));
			}
			
			// flGrpId 비교 (이거 굳이 안 해도 될 것 같아서 그냥 안 넣을게요...)
		}
		
		return historyList;
	}

	/**
	 * 수정 기록 테이블에 INSERT할 데이터를 매핑해 주는 메소드
	 * @param usrId
	 * @param updtItem
	 * @param befUpdtCn
	 * @param aftUpdtCn
	 * @param updtAdmin
	 * @param updtRsn
	 * @return
	 */
	public UserUpdateHistoryVO createHistory(String usrId, String updtItem, String befUpdtCn, String aftUpdtCn, String updtAdmin, String updtRsn) {
		
		UserUpdateHistoryVO updateHistory = new UserUpdateHistoryVO();
		
		updateHistory.setUsrId(usrId);
		updateHistory.setUpdtItem(updtItem);
		updateHistory.setBefUpdtCn(befUpdtCn);
		updateHistory.setAftUpdtCn(aftUpdtCn);
		updateHistory.setUpdtAdmin(updtAdmin);
		updateHistory.setUpdtRsn(updtRsn);
		
		return updateHistory;
	}
	
	/**
	 * INSERT/UPDATE Exception 처리 (임시)
	 * @param rows
	 * @param message
	 */
	private void daoValidate(int rows, String message) {
		if(rows == 0) {
			throw new RuntimeException("Database error: " + message + " 작업 결과 0건");
		}
	}

	/**
	 * 블로그 주소 수동 인증 (UPDATE)
	 * @param adminUserModifyInfoVO
	 * @return
	 */
	@Transactional
	@Override
	public boolean updateBlogAddress(AdminUserModifyInfoVO adminUserModifyInfoVO) {
		
		if(adminUserModifyInfoVO.getIsBlogAddressChange()) {
			
			int updateCount = this.adminUserDao.updateBlogAddress(adminUserModifyInfoVO);
			
			UserUpdateHistoryVO updateHistory = new UserUpdateHistoryVO();
			
			updateHistory.setUsrId(adminUserModifyInfoVO.getUsrId());
			updateHistory.setUpdtItem("BLG_ADDRS");
			updateHistory.setBefUpdtCn(adminUserModifyInfoVO.getBefAddrs());
			updateHistory.setAftUpdtCn(adminUserModifyInfoVO.getBlgAddrs());
			updateHistory.setUpdtAdmin(adminUserModifyInfoVO.getAdminId());
			updateHistory.setUpdtRsn("[블로그 수동 인증]");
			updateCount = this.adminUserDao.insertHistoryToBlogAddress(updateHistory);
			
			updateHistory.setUpdtItem("RCNT_BLG_CRTFCTN_DT");
			updateHistory.setBefUpdtCn(adminUserModifyInfoVO.getBefBlgCrtfctnDt());
			updateCount = this.adminUserDao.insertHistoryToBlogAddress(updateHistory);
			
			return updateCount > 0;
		}
		else {
			throw new IllegalArgumentException("error");
		}
	}

	/**
	 * 회원 경고/정지 처리
	 * @param adminPanaltyRequestVO
	 * @return
	 */
	@Transactional
	@Override
	public boolean updateUserPenaltyInfo(AdminPenaltyRequestVO adminPanaltyRequestVO) {
		
		int updateAndInsertCount = 0;
		
		String penalty = adminPanaltyRequestVO.getPenaltyOption();
		
		if(penalty.equals("warning")) {
			updateAndInsertCount = this.penaltyHistoryDao.insertPenaltyHistoryInWarning(adminPanaltyRequestVO);
			daoValidate(updateAndInsertCount, "insertPenaltyHistoryInWarning");
		}
		else if(penalty.equals("ban")) {
			updateAndInsertCount = this.penaltyHistoryDao.insertPenaltyHistoryInBan(adminPanaltyRequestVO);
			daoValidate(updateAndInsertCount, "insertPenaltyHistoryInBan");
		}
		
		int pnltCnt = this.adminUserDao.selectPenaltyCountById(adminPanaltyRequestVO.getUsrId());
		
		updateAndInsertCount = this.adminUserDao.updateUserPenaltyCount(adminPanaltyRequestVO);
		daoValidate(updateAndInsertCount, "updateUserPenaltyCount");
		
		UserUpdateHistoryVO history = new UserUpdateHistoryVO();
	    history.setUsrId(adminPanaltyRequestVO.getUsrId());
	    history.setUpdtItem("PNLT_CNT");
	    history.setBefUpdtCn(pnltCnt + "");
	    history.setAftUpdtCn( (pnltCnt + 1) + "");
	    history.setUpdtAdmin(adminPanaltyRequestVO.getAdminId());
	    history.setUpdtRsn("징계 처리(" + adminPanaltyRequestVO.getPenaltyKeyword() + ")");
		
		if( (pnltCnt + 1) >= 3 ) {
			history.setUpdtRsn("징계 누적 횟수 3 이상 (최근 징계: " + adminPanaltyRequestVO.getPenaltyKeyword() + ")");
			
			// 징계 기록도 함께 갱신한다.
			updateAndInsertCount = this.penaltyHistoryDao.updateHistoryBanToPenaltyCount(adminPanaltyRequestVO);
			daoValidate(updateAndInsertCount, "updateHistoryBanToPenaltyCount");
		}
		
		return this.adminUserDao.insertNewHistoryByPenaltyCount(history) > 0;
		
	}

}
