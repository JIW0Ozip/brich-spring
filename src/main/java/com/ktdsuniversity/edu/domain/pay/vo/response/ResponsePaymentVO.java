package com.ktdsuniversity.edu.domain.pay.vo.response;

public class ResponsePaymentVO {
	
	/**
	 * 결제정보 응답값과 DB 저장정보를 비교할 때
	 * DB 저장정보를 가져오기 위한 VO
	 */
	
	String orderId;
	String amount;
	
	public String getOrderId() {
		return this.orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getAmount() {
		return this.amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "ResponsePaymentVO [orderId=" + orderId + ", amount=" + amount + "]";
	}

}


