package com.ktdsuniversity.edu.domain.pay.vo.request;

import org.json.simple.JSONObject;

import com.ktdsuniversity.edu.domain.user.vo.SubscriptionPaymentVO;

public class RequestPaymentVO {
	
	String clientCdId;		// 상품 id
	String clientOrderName;  //상품 이름
	String clientOrderId;	// 주문번호
	String clientPrice;		// 가격
	
	String clientId;		// 검색 응답값 캠페인/유저 
	String clientCmpnId;	// 주문 캠페인
	String clientUsrId ;	// 주문자 id
	
	String orderId;			// 주문번호
	String orderName;		// 상품명
	String paymentKey;		// 주문코드
	String easyAmount;		// 가격
	
	String PKkey;	// 테이블 PK값
	String sbscrptnCd;
	
	public String getSbscrptnCd() {
		return this.sbscrptnCd;
	}
	public void setSbscrptnCd(String sbscrptnCd) {
		this.sbscrptnCd = sbscrptnCd;
	}
	public String getClientId() {
		return this.clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getOrderId() {
		return this.orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderName() {
		return this.orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public String getPaymentKey() {
		return this.paymentKey;
	}
	public void setPaymentKey(String paymentKey) {
		this.paymentKey = paymentKey;
	}
	public String getEasyAmount() {
		return this.easyAmount;
	}
	public void setEasyAmount(String easyAmount) {
		this.easyAmount = easyAmount;
	}
	public String getPKkey() {
		return this.PKkey;
	}
	public void setPKkey(String pKkey) {
		PKkey = pKkey;
	}

	public String getClientCmpnId() {
		return this.clientCmpnId;
	}
	public void setClientCmpnId(String clientCmpnId) {
		this.clientCmpnId = clientCmpnId;
	}
	public String getClientCdId() {
		return this.clientCdId;
	}
	public void setClientCdId(String clientCdId) {
		this.clientCdId = clientCdId;
	}
	@Override
	public String toString() {
		return "RequestPaymentVO [clientCdId=" + clientCdId + ", clientOrderName=" + clientOrderName
				+ ", clientOrderId=" + clientOrderId + ", clientPrice=" + clientPrice + ", clientId=" + clientId
				+ ", clientCmpnId=" + clientCmpnId + ", clientUsrId=" + clientUsrId + ", orderId=" + orderId
				+ ", orderName=" + orderName + ", paymentKey=" + paymentKey + ", easyAmount=" + easyAmount + ", PKkey="
				+ PKkey + ", sbscrptnCd=" + sbscrptnCd + "]";
	}
	public String getClientOrderName() {
		return this.clientOrderName;
	}
	public void setClientOrderName(String clientOrderName) {
		this.clientOrderName = clientOrderName;
	}
	public String getClientUsrId() {
		return this.clientUsrId;
	}
	public void setClientUsrId(String clientUsrId) {
		this.clientUsrId = clientUsrId;
	}
	public String getClientOrderId() {
		return this.clientOrderId;
	}
	public void setClientOrderId(String clientOrderId) {
		this.clientOrderId = clientOrderId;
	}
	public String getClientPrice() {
		return this.clientPrice;
	}
	public void setClientPrice(String clientPrice) {
		this.clientPrice = clientPrice;
	}
	
	
	

}
