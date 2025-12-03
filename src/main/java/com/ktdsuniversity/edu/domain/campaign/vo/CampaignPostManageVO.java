package com.ktdsuniversity.edu.domain.campaign.vo;

public class CampaignPostManageVO extends CampaignPostAdoptVO {

	private String cmpnTitle;
	private boolean hasReturn;
	
	public String getCmpnTitle() {
		return this.cmpnTitle;
	}
	public void setCmpnTitle(String cmpnTitle) {
		this.cmpnTitle = cmpnTitle;
	}
	public boolean isHasReturn() {
		return this.hasReturn;
	}
	public void setHasReturn(boolean hasReturn) {
		this.hasReturn = hasReturn;
	}
	
	
}
