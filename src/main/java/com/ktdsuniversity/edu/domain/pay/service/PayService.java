package com.ktdsuniversity.edu.domain.pay.service;

import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignVO;
import com.ktdsuniversity.edu.domain.pay.vo.request.RequestPaymentCampaignVO;
import com.ktdsuniversity.edu.domain.pay.vo.request.RequestPaymentVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

import jakarta.servlet.http.HttpSession;

public interface PayService {

	List<CommonCodeVO> payInfoServiceList();

	boolean paymentValidationCheck(RequestPaymentVO requestPaymentVO);

	int paymentSuccessUpdate(RequestPaymentVO requestPaymentVO);

	CommonCodeVO payInfoService(String cdId);

	String beforePaymentInfoSave(RequestPaymentVO requestPaymentVO);

	ResponseCampaignVO readCampaignPayment(String cmpnId, String usrId);

	int payInfoCampaignSave(RequestPaymentCampaignVO requestPaymentCampaignVO);

	String payInfoServiceCampaignAmount(String cmpnId);

	String beforeCampaigninfo(String clientCmpnId);


}
