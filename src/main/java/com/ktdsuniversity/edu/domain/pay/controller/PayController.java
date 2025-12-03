package com.ktdsuniversity.edu.domain.pay.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.domain.campaign.service.CampaignService;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignVO;
import com.ktdsuniversity.edu.domain.pay.service.PayService;
import com.ktdsuniversity.edu.domain.pay.vo.request.RequestPaymentCampaignVO;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.global.common.AjaxResponse;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

@Controller
public class PayController {
	
	private static final Logger log = LoggerFactory.getLogger(PayController.class);
	
	@Autowired
    private PayService payService;
	
	@Autowired 
	private CampaignService campaignService;
    
    @GetMapping("/blgr/pay/subscribe")
    public String subscribePayPage( Model model, @SessionAttribute(value = "__LOGIN_USER__") UserVO loginUser) {
    	
    	List<CommonCodeVO> commonCodeVoList = this.payService.payInfoServiceList();
    	model.addAttribute("payInfoList", commonCodeVoList);
    	
    	log.info("상품정보 : " + commonCodeVoList.toString());
    	
    	return "pay/subscribe";
    }
    
	
	@GetMapping("/adv/pay/campaign/{cmpnId}")
	public String advCampaignPayPage(@PathVariable String cmpnId, Model model, @SessionAttribute(value = "__LOGIN_USER__") UserVO loginUser) {

    	ResponseCampaignVO detail = new ResponseCampaignVO(); 
    	if(loginUser != null) {
    		if(loginUser.getAutr().equals("1004")) {
        		detail = payService.readCampaignPayment(cmpnId, loginUser.getUsrId());    	
        	}
    	}
    	
    	log.info( "캠페인 결제정보 조회 결과 : " + detail.toString());
    	model.addAttribute("detail", detail);
    	
    	return "pay/campaign";
    }
	
	@ResponseBody
	@PostMapping("/adv/pay/campaign/{cmpnId}")
	public AjaxResponse advCampaignDateSave(@PathVariable String cmpnId,RequestPaymentCampaignVO requestPaymentCampaignVO,
									@SessionAttribute(value = "__LOGIN_USER__") UserVO loginUser) {
		requestPaymentCampaignVO.setUsrId(loginUser.getUsrId());
		
		log.info("결제 입력 파라미터 값 : " + requestPaymentCampaignVO.toString());
		
    	int count = this.payService.payInfoCampaignSave(requestPaymentCampaignVO);
    	
    	AjaxResponse ajaxResponse = new AjaxResponse();
    	ajaxResponse.setBody(count);
    	return ajaxResponse;
	}

}
