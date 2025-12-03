package com.ktdsuniversity.edu.domain.campaign.dao;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignUpdateHistoryVO;

public interface CampaignUpdateHistoryDao {

	int insertAdminCampaignHistoryByReject(CampaignUpdateHistoryVO insertInfo);

	int insertAdminCampaignHistoryByApprove(CampaignUpdateHistoryVO insertInfo);

}