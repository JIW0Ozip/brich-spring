package com.ktdsuniversity.edu.domain.campaign.vo.response;

import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public class ResponseAdminCampaignListVO {

	private List<ResponseAdminCampaignVO> responseAdminCampaignList;
	
	private List<CommonCodeVO> categoryList;
	
	private CampaignVO campaignInfo;

	public List<ResponseAdminCampaignVO> getResponseAdminCampaignList() {
		return this.responseAdminCampaignList;
	}

	public void setResponseAdminCampaignList(List<ResponseAdminCampaignVO> responseAdminCampaignList) {
		this.responseAdminCampaignList = responseAdminCampaignList;
	}

	public List<CommonCodeVO> getCategoryList() {
		return this.categoryList;
	}

	public void setCategoryList(List<CommonCodeVO> categoryList) {
		this.categoryList = categoryList;
	}

	public CampaignVO getCampaignInfo() {
		return this.campaignInfo;
	}

	public void setCampaignInfo(CampaignVO campaignInfo) {
		this.campaignInfo = campaignInfo;
	}

	@Override
	public String toString() {
		return "ResponseAdminCampaignListVO [responseAdminCampaignList=" + responseAdminCampaignList + ", categoryList="
				+ categoryList + ", campaignInfo=" + campaignInfo + "]";
	}
	
}
