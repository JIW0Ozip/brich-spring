package com.ktdsuniversity.edu.domain.campaign.vo.response;

import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public class ResponseCampaignListVO {
	
	
	/**
	 * 캠페인 리스트
	 */
	private List<ResponseCampaignVO> ResponseCampaignList;
	
	/**
	 * 카테고리 리스트
	 */
	private List<CommonCodeVO> CategoryList;
	
	private boolean isDeny;
	
	private CampaignVO campaignInfo;
	

	public List<CommonCodeVO> getCategoryList() {
		return this.CategoryList;
	}

	public void setCategoryList(List<CommonCodeVO> categoryList) {
		CategoryList = categoryList;
	}

	public List<ResponseCampaignVO> getResponseCampaignList() {
		return this.ResponseCampaignList;
	}

	public void setResponseCampaignList(List<ResponseCampaignVO> responseCampaignList) {
		ResponseCampaignList = responseCampaignList;
	}

	public boolean getIsDeny() {
		return this.isDeny;
	}

	public void setIsDeny(boolean isDeny) {
		this.isDeny = isDeny;
	}

	public CampaignVO getCampaignInfo() {
		return this.campaignInfo;
	}

	public void setCampaignInfo(CampaignVO campaignInfo) {
		this.campaignInfo = campaignInfo;
	}

	@Override
	public String toString() {
		return "ResponseCampaignListVO [ResponseCampaignList=" + ResponseCampaignList + ", CategoryList=" + CategoryList
				+ ", isDeny=" + isDeny + ", campaignInfo=" + campaignInfo + "]";
	}
}
