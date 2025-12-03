package com.ktdsuniversity.edu.domain.pay.dao;

import java.util.List;
import java.util.Map;

import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignVO;
import com.ktdsuniversity.edu.domain.pay.vo.request.RequestPaymentCampaignVO;
import com.ktdsuniversity.edu.domain.pay.vo.request.RequestPaymentVO;
import com.ktdsuniversity.edu.domain.pay.vo.response.ResponsePaymentVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public interface PayDao {

	List<CommonCodeVO> selectPayInfoList();

	CommonCodeVO selectPayInfo(String cdId);

	int insertBeforeSubscribePaymentInfoSave(RequestPaymentVO requestPaymentVO);

	int insertBeforeCampaignPaymentInfoSave(RequestPaymentVO requestPaymentVO);

	ResponsePaymentVO selectBeforeSaveInfo(String pKkey);

	int updatePaymentSuccessSubscribe(RequestPaymentVO requestPaymentVO);

	String selectSbscrptnCd(String easyAmount);

	void updatePaymentFailSubscribe(String pKkey);

	void updatePaymentFailCampaign(String pKkey);

	int updatePaymentSuccessDate(RequestPaymentVO requestPaymentVO);

	ResponseCampaignVO selectReadCampaignPayment(Map<String, String> param);

	int updatePayInfoCampaignSave(RequestPaymentCampaignVO requestPaymentCampaignVO);

	String selectCmpnPayment(RequestPaymentCampaignVO requestPaymentCampaignVO);

	int updatePaymentInfoCampaignSave(RequestPaymentCampaignVO requestPaymentCampaignVO);

	int insertPaymentInfoCampaignSave(RequestPaymentCampaignVO requestPaymentCampaignVO);

	String selectPayInfoServiceCampaignAmount(String cmpnId);

	int updateBeforeCampaignPaymentInfoSave(RequestPaymentVO requestPaymentVO);

	String selectBeforeCampaigninfo(String clientCmpnId);
	
	int updatePaymentSuccessCampaign(RequestPaymentVO requestPaymentVO);

	int updatePaymentCampaignStts(RequestPaymentVO requestPaymentVO);

	String selectPaymentCmpnId(RequestPaymentVO requestPaymentVO);



}
