package com.ktdsuniversity.edu.domain.user.vo.response;

public class ResponseUserSubscriptionInfoVO {

	private String cdNm;
	
	private String expireDate;

	public String getCdNm() {
		return this.cdNm;
	}

	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
	}

	public String getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	@Override
	public String toString() {
		return "ResponseUserSubscriptionInfoVO [cdNm=" + cdNm + ", expireDate=" + expireDate + "]";
	}
	
	
}
