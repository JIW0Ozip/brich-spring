package com.ktdsuniversity.edu.domain.campaign.service;

import java.util.List;
import java.util.Map;

import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestAdminCamapaignRejectVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestAdminCampaignAdopterVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestAdminCampaignApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestAdminCampaignApproveVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestAdminSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminAdopterPstReSubmitCnVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminAdopterPstRtrnRsnVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminCampaignAdopterListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminCampaignApplicantListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminCampaignListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminCampaignVO;

public interface AdminCampaignService {

	ResponseAdminCampaignListVO readAdminCampaignListAndCategory(RequestAdminSearchCampaignVO requestAdminSearchCampaignVO);

	ResponseAdminCampaignVO readAdminCampaignDetail(String cmpnId);

	boolean updateAdminCampaignReject(RequestAdminCamapaignRejectVO rejectInfo);

	boolean updateAdminCampaignApprove(RequestAdminCampaignApproveVO approveInfo);

	ResponseAdminCampaignApplicantListVO readAdminCampaignApplicantListById(RequestAdminCampaignApplicantVO requestApplicantVO);

	ResponseAdminCampaignAdopterListVO readAdminCampaignAdopterListById(
			RequestAdminCampaignAdopterVO requestAdminAdopterVO);

	List<ResponseAdminAdopterPstRtrnRsnVO> readAdopterReturnReasonListByPostId(String postId);

	List<ResponseAdminAdopterPstReSubmitCnVO> readAdopterReSubmitContentListByPostId(String postId);

	Map<String, Object> readCampaignIndexStats(String cmpnId);

}
