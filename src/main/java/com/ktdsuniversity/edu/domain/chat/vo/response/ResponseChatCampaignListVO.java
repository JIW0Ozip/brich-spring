package com.ktdsuniversity.edu.domain.chat.vo.response;

public class ResponseChatCampaignListVO {
	
	private String cmpnTitle;
	
	private String cmpnId;
	
	private String sttsCd;
	
	private String cdNm;

	public String getCmpnTitle() {
		return this.cmpnTitle;
	}

	public void setCmpnTitle(String cmpnTitle) {
		this.cmpnTitle = cmpnTitle;
	}

	public String getCmpnId() {
		return this.cmpnId;
	}

	public void setCmpnId(String cmpnId) {
		this.cmpnId = cmpnId;
	}

	public String getSttsCd() {
		return this.sttsCd;
	}

	public void setSttsCd(String sttsCd) {
		this.sttsCd = sttsCd;
	}

	public String getCdNm() {
		return this.cdNm;
	}

	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
	}

	@Override
	public String toString() {
		return "ResponseChatCampaignListVO [cmpnTitle=" + cmpnTitle + ", cmpnId=" + cmpnId + ", sttsCd=" + sttsCd
				+ ", cdNm=" + cdNm + "]";
	}
	
	
}
