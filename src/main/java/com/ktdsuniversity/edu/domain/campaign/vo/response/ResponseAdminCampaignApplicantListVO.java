package com.ktdsuniversity.edu.domain.campaign.vo.response;

import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;

public class ResponseAdminCampaignApplicantListVO {

	private List<ResponseAdminCampaignApplicantVO> applicantList;
	private CampaignVO campaignInfo;
	private int adoptCount;

	public List<ResponseAdminCampaignApplicantVO> getApplicantList() {
		return this.applicantList;
	}

	public void setApplicantList(List<ResponseAdminCampaignApplicantVO> applicantList) {
		this.applicantList = applicantList;
	}

	public CampaignVO getCampaignInfo() {
		return this.campaignInfo;
	}

	public void setCampaignInfo(CampaignVO campaignInfo) {
		this.campaignInfo = campaignInfo;
	}

	public int getAdoptCount() {
		return this.adoptCount;
	}

	public void setAdoptCount(int adoptCount) {
		this.adoptCount = adoptCount;
	}
	
}
