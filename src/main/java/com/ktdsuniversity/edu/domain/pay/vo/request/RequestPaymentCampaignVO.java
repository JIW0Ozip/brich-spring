package com.ktdsuniversity.edu.domain.pay.vo.request;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;

public class RequestPaymentCampaignVO extends CampaignVO{
	
	private String usrId;
	private String totalPay;
	
	public String getTotalPay() {
		return this.totalPay;
	}
	public void setTotalPay(String totalPay) {
		this.totalPay = totalPay;
	}
	public String getUsrId() {
		return this.usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	@Override
	public String toString() {
		return "RequestPaymentCampaignVO [usrId=" + usrId + ", totalPay=" + totalPay + "]";
	}
	
	
}
