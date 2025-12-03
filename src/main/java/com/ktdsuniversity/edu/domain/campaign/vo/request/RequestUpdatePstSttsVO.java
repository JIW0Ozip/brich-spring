package com.ktdsuniversity.edu.domain.campaign.vo.request;

public class RequestUpdatePstSttsVO {

	private String cmpnPstAdptId;
	private String stts;
	private String advId;
	
	public String getCmpnPstAdptId() {
		return this.cmpnPstAdptId;
	}
	public void setCmpnPstAdptId(String cmpnPstAdptId) {
		this.cmpnPstAdptId = cmpnPstAdptId;
	}
	public String getStts() {
		return this.stts;
	}
	public void setStts(String stts) {
		this.stts = stts;
	}
		public String getAdvId() {
		return this.advId;
	}
	public void setAdvId(String advId) {
		this.advId = advId;
	}
	
	@Override
	public String toString() {
		return "RequestUpdatePstSttsVO [cmpnPstAdptId=" + cmpnPstAdptId + ", stts=" + stts + ", advId=" + advId + "]";
	}
}
