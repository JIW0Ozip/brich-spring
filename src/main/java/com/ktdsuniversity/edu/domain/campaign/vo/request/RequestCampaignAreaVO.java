package com.ktdsuniversity.edu.domain.campaign.vo.request;

public class RequestCampaignAreaVO {

	private String cmpnArId;
	private String cmpnId;
	private String arCd;
	private String usrId;
	
	public String getCmpnArId() {
		return this.cmpnArId;
	}
	public void setCmpnArId(String cmpnArId) {
		this.cmpnArId = cmpnArId;
	}
	public String getCmpnId() {
		return this.cmpnId;
	}
	public void setCmpnId(String cmpnId) {
		this.cmpnId = cmpnId;
	}
	public String getArCd() {
		return this.arCd;
	}
	public void setArCd(String arCd) {
		this.arCd = arCd;
	}
	public String getUsrId() {
		return this.usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	
	@Override
	public String toString() {
		return "RequestCampaignAreaVO [cmpnArId=" + cmpnArId + ", cmpnId=" + cmpnId + ", arCd=" + arCd + ", usrId="
				+ usrId + "]";
	}
}
