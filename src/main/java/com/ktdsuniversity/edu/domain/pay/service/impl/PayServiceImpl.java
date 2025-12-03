package com.ktdsuniversity.edu.domain.pay.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignVO;
import com.ktdsuniversity.edu.domain.pay.dao.PayDao;
import com.ktdsuniversity.edu.domain.pay.service.PayService;
import com.ktdsuniversity.edu.domain.pay.vo.request.RequestPaymentCampaignVO;
import com.ktdsuniversity.edu.domain.pay.vo.request.RequestPaymentVO;
import com.ktdsuniversity.edu.domain.pay.vo.response.ResponsePaymentVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;
import com.ktdsuniversity.edu.global.util.SessionUtil;

@Service
public class PayServiceImpl implements PayService{
	
	private static final Logger log = LoggerFactory.getLogger(PayServiceImpl.class);

    @Autowired
    private PayDao payDao;
	@Override
	public List<CommonCodeVO> payInfoServiceList() {
		
		List<CommonCodeVO> commonCodeVoList = this.payDao.selectPayInfoList();
		
		return commonCodeVoList;
	}
	
	
	@Override
	public boolean paymentValidationCheck(RequestPaymentVO requestPaymentVO) {
		log.info("====== 결제정보 유효성 검사 저장 start ======");

		// 테이블 조회 (주문번호, (유저아이디/캠페인번호), 가격)
		
		String PKkey = requestPaymentVO.getPKkey();
		ResponsePaymentVO saveInfo = this.payDao.selectBeforeSaveInfo(PKkey);  //union all
		
		log.info(saveInfo.getOrderId() + " : " + requestPaymentVO.getOrderId());
		log.info(saveInfo.getAmount() + " : " + requestPaymentVO.getEasyAmount());
		// DB 값과 응답값 유효성 검사
		if(saveInfo.getOrderId().equals(requestPaymentVO.getOrderId()) 
				&& saveInfo.getAmount().equals(requestPaymentVO.getEasyAmount())) {
			log.info("====== 결제정보 유효성 검사 SUCCESS !! ======");
			return true;
		}
		
		log.info("====== 결제정보 유효성 검사 FAIL !! ======");
		// 실패 상태 업데이트
		String autr = SessionUtil.getLoginObject().getAutr();
		if((autr.equals("1002") || autr.equals("1003"))) {
			this.payDao.updatePaymentFailSubscribe(PKkey);
		} else if (autr.equals("1004")){
			this.payDao.updatePaymentFailCampaign(PKkey);
		}
		
		return false;
	}
	
	@Transactional
	@Override
	public int paymentSuccessUpdate(RequestPaymentVO requestPaymentVO) {
		log.info("====== 결제정보 업데이트 저장 start ======");

		// 결제 정보 업데이트
		String autr = SessionUtil.getLoginObject().getAutr();
		requestPaymentVO.setClientUsrId(SessionUtil.getLoginObject().getUsrId());
		int count = 0;
		int counttest = 0;
		
		if((autr.equals("1002") || autr.equals("1003"))) {
				// 상품코드 검색
				requestPaymentVO.setSbscrptnCd(this.payDao.selectSbscrptnCd(requestPaymentVO.getEasyAmount()));
				// 결제 테이블 수정
				count = this.payDao.updatePaymentSuccessSubscribe(requestPaymentVO);
				
				// 구독기간, 권한 수정
				counttest = this.payDao.updatePaymentSuccessDate(requestPaymentVO);
				log.info(String.valueOf(counttest));
				SessionUtil.getLoginObject().setAutr("1002");
		} else if (autr.equals("1004")){
				// 결제 테이블 수정
				log.info("결제 테이블 수정 들어갑니다 : " + requestPaymentVO.toString());
				
				 count = this.payDao.updatePaymentSuccessCampaign(requestPaymentVO);
				 String cmpnId = this.payDao.selectPaymentCmpnId(requestPaymentVO);
				 requestPaymentVO.setClientId(cmpnId);
				 count = this.payDao.updatePaymentCampaignStts(requestPaymentVO);
				// 캠페인 저장 정보 가져오기
				
				// 모집 시작일, 모집 마감일, 캠페인 마감일, 캠페인 상태  수정
		} else {
			log.info("====== 결제정보 업데이트 저장 FAIL !! ======");
			return 0 ;
		}
		if (count == 1) {
			log.info("====== 결제정보 업데이트 저장 SUCCESS !! ======");
		}
		return count;
	}


	@Override
	public CommonCodeVO payInfoService(String cdId) {
		CommonCodeVO commonCodeVO = this.payDao.selectPayInfo(cdId);		

		return commonCodeVO;
	}

	@Transactional
	@Override
	public String beforePaymentInfoSave(RequestPaymentVO requestPaymentVO) {
		log.info("====== 결제정보 사전 저장 start ======");
		// 구독인지 캠페인 결제인지 확인
		int count;
		String autr = SessionUtil.getLoginObject().getAutr();
		if(autr.equals("1002") || autr.equals("1003")) {  //구독
			count = this.payDao.insertBeforeSubscribePaymentInfoSave(requestPaymentVO);
		} else if (autr.equals("1004")){
//			count = this.payDao.insertBeforeCampaignPaymentInfoSave(requestPaymentVO);
			count = this.payDao.updateBeforeCampaignPaymentInfoSave(requestPaymentVO);
		} else {
			return null ;
		}
		
		return requestPaymentVO.getPKkey();
	}


	@Override
	public ResponseCampaignVO readCampaignPayment(String cmpnId, String usrId) {
		Map<String,String> param = new HashMap<>();
		param.put("cmpnId",cmpnId);	
		param.put("usrId",usrId);	
		ResponseCampaignVO detail = this.payDao.selectReadCampaignPayment(param);
		return detail;
	}


	@Override
	public int payInfoCampaignSave(RequestPaymentCampaignVO requestPaymentCampaignVO) {
		int count = this.payDao.updatePayInfoCampaignSave(requestPaymentCampaignVO);
		
		// 결제정보 있는지 확인
		String isYN = this.payDao.selectCmpnPayment(requestPaymentCampaignVO);
		if(isYN.equals("Y")) {
			count = this.payDao.updatePaymentInfoCampaignSave(requestPaymentCampaignVO);
		}else {
			int count2 = this.payDao.insertPaymentInfoCampaignSave(requestPaymentCampaignVO);
		}
		return count;
	}


	@Override
	public String payInfoServiceCampaignAmount(String cmpnId) {
		return this.payDao.selectPayInfoServiceCampaignAmount(cmpnId);
	}


	@Override
	public String beforeCampaigninfo(String clientCmpnId) {
		return this.payDao.selectBeforeCampaigninfo(clientCmpnId);
	}
	
	
}
