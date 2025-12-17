package com.ktdsuniversity.edu.domain.pay.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.domain.campaign.service.CampaignService;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignVO;
import com.ktdsuniversity.edu.domain.pay.service.PayService;
import com.ktdsuniversity.edu.domain.pay.vo.request.RequestPaymentCampaignVO;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.global.common.AjaxResponse;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;
import com.ktdsuniversity.edu.global.util.AuthenticationUtil;

@RequestMapping("/api/v1/pay")
@RestController
public class PayApi {
	private static final Logger log = LoggerFactory.getLogger(PayController.class);
	
	@Autowired
    private PayService payService;
	
	@Autowired 
	private CampaignService campaignService;
	
	@PostMapping("/subscribeInfo")
    public AjaxResponse subscribePayPage() {
		
    	UserVO loginUser = AuthenticationUtil.getUserVO();
    	List<CommonCodeVO> commonCodeVoList = this.payService.payInfoServiceList();
    	
    	log.info("상품정보 : " + commonCodeVoList.toString());
    	AjaxResponse ajaxResponse = new AjaxResponse();
    	ajaxResponse.setBody(commonCodeVoList);
    	
    	return ajaxResponse;
    }
	
	@PostMapping("/subscribe/add")
    public AjaxResponse subscribePayAdd() {
		
    	UserVO loginUser = AuthenticationUtil.getUserVO();
    	
    	int commonCodeVoList = this.payService.subscribeAdd(loginUser.getUsrId());
    	
    	AjaxResponse ajaxResponse = new AjaxResponse();
    	ajaxResponse.setBody(commonCodeVoList);
    	
    	return ajaxResponse;
    }
	
	@PostMapping("/adv/pay/campaign")
	public AjaxResponse advCampaignPayPage(@RequestBody RequestSearchCampaignVO requestSearchCampaignVO) {
		UserVO loginUser = AuthenticationUtil.getUserVO();
		String cmpnId = requestSearchCampaignVO.getCmpnId();
    	ResponseCampaignVO detail = new ResponseCampaignVO(); 
    	if(loginUser != null) {
    		if(loginUser.getAutr().equals("1004")) {
        		detail = payService.readCampaignPayment(cmpnId, loginUser.getUsrId());    	
        	}
    	}
    	
    	log.info( "캠페인 결제정보 조회 결과 : " + detail.toString());
    	AjaxResponse ajaxResponse = new AjaxResponse();
    	ajaxResponse.setBody(detail);
    	
    	return ajaxResponse;
    }
	
    @GetMapping("/adv/pay/{cmpnId}")
    public String PayAdv(@PathVariable String cmpnId, Model model) {
    	
    	UserVO loginUser = AuthenticationUtil.getUserVO();

    	// cmpnId 로 결재내역 검색
    	String amount = this.payService.payInfoServiceCampaignAmount(cmpnId);
    	
    	
    	model.addAttribute("amount", amount );
    	model.addAttribute("usrId", loginUser.getUsrId());
    	model.addAttribute("cmpnId", cmpnId);
    	return "pay/checkoutcmpn";
    	
    }
    
    
    
	@ResponseBody
	@PostMapping("/adv/pay/InfoSave")
	public AjaxResponse advCampaignDateSave(@RequestBody RequestPaymentCampaignVO requestPaymentCampaignVO) {
		UserVO loginUser = AuthenticationUtil.getUserVO();
		requestPaymentCampaignVO.setUsrId(loginUser.getUsrId());
		
		log.info("결제 입력 파라미터 값 : " + requestPaymentCampaignVO.toString());
		
    	int count = this.payService.payInfoCampaignSave(requestPaymentCampaignVO);
    	
    	AjaxResponse ajaxResponse = new AjaxResponse();
    	ajaxResponse.setBody(count);
    	return ajaxResponse;
	}
}




