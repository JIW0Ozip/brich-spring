package com.ktdsuniversity.edu.domain.campaign.vo;

import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignwriteVO;

public class ResponseModifyCampaignVO {

	private ResponseCampaignwriteVO common;
	private ResponseCampaignVO campaign;
	
	public ResponseCampaignwriteVO getCommon() {
		return this.common;
	}
	public void setCommon(ResponseCampaignwriteVO common) {
		this.common = common;
	}
	public ResponseCampaignVO getCampaign() {
		return this.campaign;
	}
	public void setCampaign(ResponseCampaignVO campaign) {
		this.campaign = campaign;
	}
	
	@Override
	public String toString() {
		return "ResponseModifyCampaignVO [common=" + common + ", campaign=" + campaign + "]";
	}	
}
