package com.ktdsuniversity.edu.domain.campaign.vo.response;

import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;

public class ResponseApplicantListVO {

	private List<ResponseApplicantVO> applicantList;
	
	private int adoptCount;
	
	private CampaignVO campaignInfo;
	
	public List<ResponseApplicantVO> getApplicantList() {
		return this.applicantList;
	}

	public void setApplicantList(List<ResponseApplicantVO> applicantList) {
		this.applicantList = applicantList;
	}

	public int getAdoptCount() {
		return this.adoptCount;
	}

	public void setAdoptCount(int adoptCount) {
		this.adoptCount = adoptCount;
	}

	public CampaignVO getCampaignInfo() {
		return this.campaignInfo;
	}

	public void setCampaignInfo(CampaignVO campaignInfo) {
		this.campaignInfo = campaignInfo;
	}

	@Override
	public String toString() {
		return "ApplicantListVO [applicantList=" + this.applicantList + campaignInfo + "]";
	}
}
