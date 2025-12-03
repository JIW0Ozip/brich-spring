package com.ktdsuniversity.edu.domain.campaign.dao;


import java.util.List;
import java.util.Map;

import com.ktdsuniversity.edu.domain.blog.vo.RequestExpireSoonCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignIndexStatVO;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignPostAdoptVO;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignPostManageVO;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.PostReturnHistoryVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestCampaignAreaVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestCreateCmpnVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestDenyVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestPostSubmitVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestUpdatePstSttsVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminCampaignApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdoptVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseDenyHistoryVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public interface CampaignDao {

	ResponseCampaignVO selectCampaignDetailById(Map<String, String> param);

	List<CommonCodeVO> selectCategoryList();

	List<ResponseApplicantVO> selectApplicantListByCmpnId(RequestApplicantVO requestApplicantVO);

	int updateAdptYnByCmpnPstAdptId(RequestApplicantVO requestApplicantVO);

	int selectAdoptCountByCmpnId(String cmpnId);

	ResponseCampaignVO selectCampaignInfoByCmpnId(String cmpnId);

	int selectApplicantCountByCmpnId(RequestApplicantVO requestApplicantVO);

	CampaignVO selectCampaignStateByCmpnPstAdptId(String cmpnPstAdptId);

	List<ResponseAdoptVO> selectAdoptListByCmpnId(RequestApplicantVO requestApplicantVO);

	List<ResponseCampaignVO> selectCampaignListCategoryAndSortBy(RequestSearchCampaignVO requestSearchCampaignVO);

	String selectCategoryParent(String selectCategroy);

	List<CampaignVO> selectExpireSoonCampaign(RequestExpireSoonCampaignVO requestExpireSoonCampaignVO);

	String selectCampaignChangeSttsCd(String sttsCd);

	List<ResponseCampaignVO> selectMyCampaignByBlgId(Map<String, Object> param);

	String selectFavCamapignExists(Map<String, String> param);

	int insertFavCamapign(Map<String, String> param);

	int updateFavCamapignOn(Map<String, String> param);

	int updateFavCamapignOff(Map<String, String> param);

	String selectFavDltYn(Map<String, String> param);

	List<ResponseCampaignVO> selectMyFavCampaignByBlgId(Map<String, Object> param);

	int insertApplyCampaign(Map<String, String> param);

	int updatePstSttsByCmpnPstAdoptId(RequestUpdatePstSttsVO requestUpdatePstSttsVO);

	int selectAdoptPaginationCount(RequestApplicantVO requestApplicantVO);

	String selectStateNameByStateCode(String sttsCd);

	int insertDenyByCmpnPstAdoptId(RequestDenyVO requestDenyVO);

	List<CommonCodeVO> selectDoAndCityList();

	List<CommonCodeVO> selectDistrictByCdId(String cdId);

	String selectPersonPrice();

	int insertNewCampaign(RequestCreateCmpnVO requestCreateCmpnVO);

	int insertCampaignCategory(RequestCampaignAreaVO requestCampaignAreaVO);

	int updateDdlnByCmpnPstAdoptId(RequestDenyVO requestDenyVO);

	int udpateCmpnDateByCmpnId(RequestDenyVO requestDenyVO);
	String selecthasAdoptYn(Map<String, String> param);

	String selectAdoptDltYn(Map<String, String> param);

	int updateCancelApplyCampaign(Map<String, String> param);

	int updateApplyCampaign(Map<String, String> param);

	List<ResponseCampaignVO> selectCampaignListByUsrId(RequestSearchCampaignVO requestSearchCampaignVO);

	int selectCampaignListCountByusrId(RequestSearchCampaignVO requestSearchCampaignVO);

	List<ResponseCampaignVO> selectDenyListByCmpnId(RequestSearchCampaignVO requestSearchCampaignVO);
	int updatePostSubmit(RequestPostSubmitVO requestPostSubmitVO);

	int updatePostSubmitStts(RequestPostSubmitVO requestPostSubmitVO);

	int updateRePostSubmit(RequestPostSubmitVO requestPostSubmitVO);

	int updateRePostSubmitStts(RequestPostSubmitVO requestPostSubmitVO);

	String selectReturnReason(Map<String, String> param);

	int updateCmpnPrntIdByCmpnId(RequestCreateCmpnVO requestCreateCmpnVO);

	int updateTemporaryCampaignByCmpnId(RequestCreateCmpnVO requestCreateCmpnVO);

	List<ResponseDenyHistoryVO> selectDenyHistoryByCmpnPstAdptId(String postId);

	List<CampaignPostManageVO> selectCampaignPostListByUsrId(String usrId);

	List<CampaignVO> selectRecommendedCampaignByUsrId(String usrId);

	CampaignPostAdoptVO selectCampaignPostByPstAdptId(String postId);

	List<CampaignIndexStatVO> selectCampaignIndexStats(String cmpnId);

	Double selectMyBlogIndexInCampaign(String cmpnId, String usrId);


}