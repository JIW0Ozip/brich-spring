package com.ktdsuniversity.edu.domain.campaign.service;



import java.util.List;
import java.util.Map;

import com.ktdsuniversity.edu.domain.blog.vo.RequestExpireSoonCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignIndexStatVO;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignPostManageVO;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.ResponseModifyCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestCreateCmpnVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestDenyVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestPostSubmitVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdoptListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseApplicantListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignwriteVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseDenyHistoryVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;


public interface CampaignService {

	ResponseCampaignVO readCampaignDetail(String campaignId);

	ResponseCampaignVO readCampaignDetail(String campaignId, String usrId);
	
	ResponseCampaignListVO readCampaignListAndCategory(RequestSearchCampaignVO requestSearchCampaignVO);

	ResponseApplicantListVO readApplicantListById(RequestApplicantVO requestApplicantVO);

	boolean updateAdptYnByCmpnPstAdptId(RequestApplicantVO requestApplicantVO);

	ResponseAdoptListVO readResponseAdoptListByCmpnId(RequestApplicantVO requestApplicantVO);

	ResponseCampaignListVO readSubmittedMyCampaignByBlgId(String blgId);

	int favCampaignDo(String blgId, String campaignId);

	ResponseCampaignListVO readOnGoingMyCampaignByBlgId(String blgId);

	ResponseCampaignListVO readClosedMyCampaignByBlgId(String blgId);

	ResponseCampaignListVO readFavMyCampaignByBlgId(String blgId);

	int applyCampaign(String campaignId, String blgId);

	boolean updatePstSttsApproveByCmpnPstAdoptId(RequestApplicantVO requestApplicantVO);

	boolean createDenyByCmpnPstAdoptId(RequestDenyVO requestDenyVO);

	ResponseCampaignwriteVO createCampaign();

	List<CommonCodeVO> readDistrictByCdId(String cdId);

	boolean createNewCampaign(RequestCreateCmpnVO requestCreateCmpnVO);

	ResponseCampaignListVO readCampaignListByUsrId(RequestSearchCampaignVO requestSearchCampaignVO);

	ResponseCampaignListVO readDenyHistoryByCmpnId(RequestSearchCampaignVO requestSearchCampaignVO);
	int postSubmit(RequestPostSubmitVO requestPostSubmitVO);

	int rePostSubmit(RequestPostSubmitVO requestPostSubmitVO);

	String postReturnReason(String campaignId, String usrId);

	boolean modifyNewCampaign(RequestCreateCmpnVO requestCreateCmpnVO);

	boolean createTemporaryCampaign(RequestCreateCmpnVO requestCreateCmpnVO);

	ResponseModifyCampaignVO readModifyInfoByCmpnId(String cmpnId);

	List<ResponseDenyHistoryVO> readDenyHistoryByCmpnPstAdptId(String postId);

	List<CampaignVO> readExpireSoonCampaignList(RequestExpireSoonCampaignVO requestExpireSoonCampaignVO);

	List<CampaignPostManageVO> readCampaignManageListByUsrId(String usrId);

	List<CampaignVO> readRecommendedCampaignByUsrId(String usrId);

	Map<String, Object> readCampaignIndexStats(String cmpnId, String usrID);


}