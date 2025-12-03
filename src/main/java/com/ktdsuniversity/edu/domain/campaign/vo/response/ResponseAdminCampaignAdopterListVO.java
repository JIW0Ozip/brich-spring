package com.ktdsuniversity.edu.domain.campaign.vo.response;

import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;

public class ResponseAdminCampaignAdopterListVO {

	private List<ResponseAdminCampaignAdopterVO> adopterList;
	private CampaignVO campaignInfo;
	private int adopterCount;
	private int postApproveCount;
	
	public List<ResponseAdminCampaignAdopterVO> getAdopterList() {
		return this.adopterList;
	}
	public void setAdopterList(List<ResponseAdminCampaignAdopterVO> adopterList) {
		this.adopterList = adopterList;
	}
	public CampaignVO getCampaignInfo() {
		return this.campaignInfo;
	}
	public void setCampaignInfo(CampaignVO campaignInfo) {
		this.campaignInfo = campaignInfo;
	}
	public int getAdopterCount() {
		return this.adopterCount;
	}
	public void setAdopterCount(int adopterCount) {
		this.adopterCount = adopterCount;
	}
	public int getPostApproveCount() {
		return this.postApproveCount;
	}
	public void setPostApproveCount(int postApproveCount) {
		this.postApproveCount = postApproveCount;
	}
	
}
