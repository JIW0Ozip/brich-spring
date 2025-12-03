package com.ktdsuniversity.edu.domain.campaign.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.domain.campaign.dao.AdminCampaignDao;
import com.ktdsuniversity.edu.domain.campaign.dao.CampaignDao;
import com.ktdsuniversity.edu.domain.campaign.dao.CampaignUpdateHistoryDao;
import com.ktdsuniversity.edu.domain.campaign.service.AdminCampaignService;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignIndexStatVO;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignUpdateHistoryVO;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestAdminCamapaignRejectVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestAdminCampaignAdopterVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestAdminCampaignApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestAdminCampaignApproveVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestAdminSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminAdopterPstReSubmitCnVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminAdopterPstRtrnRsnVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminCampaignAdopterListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminCampaignAdopterVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminCampaignApplicantListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminCampaignApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminCampaignListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminCampaignVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

@Service
public class AdminCampaignServiceImpl implements AdminCampaignService {
	
	private static final Logger log = LoggerFactory.getLogger(AdminCampaignServiceImpl.class);
	
	@Autowired
	private AdminCampaignDao adminCampaignDao;
	
	@Autowired
	private CampaignUpdateHistoryDao campaginUpdateHistoryDao; 
	
	@Autowired
	private CampaignDao campaignDao;
	
	/**
	 * 캠페인 관리 - 목록 페이지
	 * @param requestAdminSearchCampaignVO
	 * @return
	 */
	@Override
	public ResponseAdminCampaignListVO readAdminCampaignListAndCategory(RequestAdminSearchCampaignVO requestAdminSearchCampaignVO) {
		
		ResponseAdminCampaignListVO responseAdminCampaignListVO = new ResponseAdminCampaignListVO();
		
		// 상위 카테고리 목록 구하기
		List<CommonCodeVO> categoryList = this.campaignDao.selectCategoryList();
		responseAdminCampaignListVO.setCategoryList(categoryList);
		log.info("어드민 카테고리 목록: "+ categoryList.toString());
		
		log.info("어드민 getCategory(): " + requestAdminSearchCampaignVO.getCategory());
		if(requestAdminSearchCampaignVO.getCategory() != null) {
			String searchCategory = this.adminCampaignDao.selectAdminCategoryParent(requestAdminSearchCampaignVO.getCategory());
			requestAdminSearchCampaignVO.setCategory(searchCategory);
			log.info("어드민 조회할 카테고리 번호: " + searchCategory);
		}
		
		requestAdminSearchCampaignVO.setListSize(16);
		
		// 초기 정렬 세팅
		if(requestAdminSearchCampaignVO.getSortBy() == null) {
			requestAdminSearchCampaignVO.setSortBy("latest");
		}
		
		responseAdminCampaignListVO.setResponseAdminCampaignList(this.adminCampaignDao.selectAdminCampaignListCategoryAndSortBy(requestAdminSearchCampaignVO));
		
		// 지역명 두 글자로 자르기
		List<ResponseAdminCampaignVO> campaignList = responseAdminCampaignListVO.getResponseAdminCampaignList();
		for(ResponseAdminCampaignVO vo : campaignList) {
			if(vo.getParentArea() != null) {
				vo.setParentArea(vo.getParentArea().substring(0, 2));
			}
		}
		
		return responseAdminCampaignListVO;
	}

	/**
	 * 캠페인 관리 - 상세 페이지
	 * @param cmpnId
	 * @return
	 */
	@Override
	public ResponseAdminCampaignVO readAdminCampaignDetail(String cmpnId) {
		
		// 캠페인 상세 정보 조회
		ResponseAdminCampaignVO detail = this.adminCampaignDao.selectAdminCampaignDetailById(cmpnId);
		
		// 지역명 두 글자로 자르기
		if(detail.getParentArea() != null) {
			detail.setParentArea(detail.getParentArea().substring(0, 2));
		}
		
		return detail;
	}

	/**
	 * 캠페인 반려 및 수정 이력 테이블에 기록
	 * @param rejectInfo
	 * @return
	 */
	@Transactional
	@Override
	public boolean updateAdminCampaignReject(RequestAdminCamapaignRejectVO rejectInfo) {
		
		CampaignVO beforeInfo = this.adminCampaignDao.selectAdminCampaignAllInfoById(rejectInfo.getCmpnId());
		
		// 1. 캠페인 수정 이력 테이블 들어갈 데이터를 준비한다. (이전 데이터)
		CampaignUpdateHistoryVO insertInfo = new CampaignUpdateHistoryVO();
		
		// 반려 사유 데이터
		insertInfo.setCmpnId(rejectInfo.getCmpnId());
		insertInfo.setUpdtItem("RTRN_RSN");
		insertInfo.setBefUpdtCn(beforeInfo.getRtrnRsn());
		insertInfo.setAftUpdtCn(rejectInfo.getRtrnRsn());
		insertInfo.setUpdtAdmin(rejectInfo.getAdminId());
		insertInfo.setUpdtRsn("[캠페인 반려] 사유: " + rejectInfo.getRtrnRsn());
		int insertCount = this.campaginUpdateHistoryDao.insertAdminCampaignHistoryByReject(insertInfo);
		
		// 상태 코드 데이터
		insertInfo.setUpdtItem("STTS_CD");
		insertInfo.setBefUpdtCn(beforeInfo.getSttsCd());
		insertInfo.setAftUpdtCn("2003");
		insertCount = this.campaginUpdateHistoryDao.insertAdminCampaignHistoryByReject(insertInfo);
		
		// 2. 캠페인 반려 정보를 업데이트한다.
		return this.adminCampaignDao.updateAdminCampaignByRejectInfo(rejectInfo) > 0;
	}

	/**
	 * 캠페인 승인 및 수정 이력 테이블에 기록
	 * @param approveInfo
	 * @return
	 */
	@Transactional
	@Override
	public boolean updateAdminCampaignApprove(RequestAdminCampaignApproveVO approveInfo) {
		
		CampaignVO beforeInfo = this.adminCampaignDao.selectAdminCampaignAllInfoById(approveInfo.getCmpnId());
		
		CampaignUpdateHistoryVO insertInfo = new CampaignUpdateHistoryVO();
		insertInfo.setCmpnId(approveInfo.getCmpnId());
		insertInfo.setUpdtItem("STTS_CD");
		insertInfo.setBefUpdtCn(beforeInfo.getSttsCd());
		insertInfo.setAftUpdtCn("2002");
		insertInfo.setUpdtAdmin(approveInfo.getAdminId());
		insertInfo.setUpdtRsn("[캠페인 승인]");
		int insertCount = this.campaginUpdateHistoryDao.insertAdminCampaignHistoryByApprove(insertInfo);
		
		insertInfo.setUpdtItem("CNFM_DT");
		insertInfo.setBefUpdtCn(beforeInfo.getCnfmDt());
		insertCount = this.campaginUpdateHistoryDao.insertAdminCampaignHistoryByApprove(insertInfo);
		
		return this.adminCampaignDao.updateAdminCampaignByApproveInfo(approveInfo) > 0;
	}

	/**
	 * 캠페인 신청자 목록
	 * @param requestApplicantVO
	 * @return
	 */
	@Override
	public ResponseAdminCampaignApplicantListVO readAdminCampaignApplicantListById(
			RequestAdminCampaignApplicantVO requestApplicantVO) {
		
		int applicantCount = this.adminCampaignDao.selectAdminCampaignApplicantCountByCmpnId(requestApplicantVO);
		requestApplicantVO.setPageCount(applicantCount);
		
		List<ResponseAdminCampaignApplicantVO> applicantList = this.adminCampaignDao.selectAdminCampaignApplicantListByCmpnId(requestApplicantVO);
		int adoptCount = this.campaignDao.selectAdoptCountByCmpnId(requestApplicantVO.getCmpnId());
		CampaignVO campaignInfo = this.campaignDao.selectCampaignInfoByCmpnId(requestApplicantVO.getCmpnId());
		
		ResponseAdminCampaignApplicantListVO applicantListVO = new ResponseAdminCampaignApplicantListVO();
		applicantListVO.setApplicantList(applicantList);
		applicantListVO.setAdoptCount(adoptCount);
		applicantListVO.setCampaignInfo(campaignInfo);
		
		return applicantListVO;
	}

	/**
	 * 캠페인 채택자 목록
	 * @param requestAdminAdopterVO
	 * @return
	 */
	@Override
	public ResponseAdminCampaignAdopterListVO readAdminCampaignAdopterListById(
			RequestAdminCampaignAdopterVO requestAdminAdopterVO) {
		
		log.info("서비스 캠페인 아이디: " + requestAdminAdopterVO.getCmpnId());
		
		int adopterCount = this.campaignDao.selectAdoptCountByCmpnId(requestAdminAdopterVO.getCmpnId());
		int postApproveCount = this.adminCampaignDao.selectAdminCampaignPostApproveCountByPostId(requestAdminAdopterVO.getCmpnId());
		requestAdminAdopterVO.setPageCount(postApproveCount);
		
		List<ResponseAdminCampaignAdopterVO> adopterList = this.adminCampaignDao.selectAdminCampaignAdopterListByPostId(requestAdminAdopterVO);
		CampaignVO campaignInfo = this.campaignDao.selectCampaignInfoByCmpnId(requestAdminAdopterVO.getCmpnId());
		
		ResponseAdminCampaignAdopterListVO adopterListVO = new ResponseAdminCampaignAdopterListVO();
		adopterListVO.setAdopterList(adopterList);
		adopterListVO.setAdopterCount(adopterCount);
		adopterListVO.setPostApproveCount(postApproveCount);
		adopterListVO.setCampaignInfo(campaignInfo);
		
		return adopterListVO;
	}

	/**
	 * 캠페인 채택자 반려 사유 목록
	 * @param postId
	 * @return
	 */
	@Override
	public List<ResponseAdminAdopterPstRtrnRsnVO> readAdopterReturnReasonListByPostId(String postId) {
		
		List<ResponseAdminAdopterPstRtrnRsnVO> list = this.adminCampaignDao.selectAdopterReturnReasonListByPostId(postId);
		
		log.info("반려 사유: " + list.toString());
		
		return list;
	}

	/**
	 * 캠페인 채택자 재제출 내용 목록
	 * @param postId
	 * @return
	 */
	@Override
	public List<ResponseAdminAdopterPstReSubmitCnVO> readAdopterReSubmitContentListByPostId(String postId) {
		
		List<ResponseAdminAdopterPstReSubmitCnVO> list = this.adminCampaignDao.selectAdopterPostReSubmitListByPostId(postId);
		
		log.info("재제출 이력: " + list.toString());
		
		return list;
	}

	@Override
	public Map<String, Object> readCampaignIndexStats(String cmpnId) {
		Map<String, Object> result = new HashMap<>();

	      // 참여자별 블로그 지수 DAO에서 직접 조회 (BlogDataService 의존 X)
	      List<CampaignIndexStatVO> list = this.campaignDao.selectCampaignIndexStats(cmpnId);
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

	      result.put("overallAvg", overallAvg);
	      result.put("max", max == Double.MIN_VALUE ? 0 : max);
	      result.put("min", min == Double.MAX_VALUE ? 0 : min);

	      log.info("어드민 맵: " + result);
	      
	      return result;
	}


}
