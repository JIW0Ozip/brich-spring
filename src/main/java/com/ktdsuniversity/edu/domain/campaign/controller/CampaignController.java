	package com.ktdsuniversity.edu.domain.campaign.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktdsuniversity.edu.domain.blog.service.BlogDataService;
import com.ktdsuniversity.edu.domain.blog.vo.BlogIndexVO;
import com.ktdsuniversity.edu.domain.blog.vo.DailyVisitorVO;
import com.ktdsuniversity.edu.domain.blog.vo.RequestBlogIndexListVO;
import com.ktdsuniversity.edu.domain.blog.vo.RequestExpireSoonCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.service.CampaignService;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignIndexStatVO;
import com.ktdsuniversity.edu.domain.campaign.vo.ResponseExpireSoonListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.ResponseModifyCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestCreateCmpnVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestDenyVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestPostSubmitVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdoptListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseApplicantListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignwriteVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseDenyHistoryVO;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.global.common.AjaxResponse;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;
import com.ktdsuniversity.edu.global.exceptions.BrichException;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class CampaignController {
	
	private static final Logger log = LoggerFactory.getLogger(CampaignController.class);

    @Autowired
    private CampaignService campaignService;
    
    @Autowired
    private BlogDataService blogDataService;
    
    
    @GetMapping("/campaigndetail/{campaignId}")
    public String campaignDetailPage(@PathVariable String campaignId, Model model,
    							 @SessionAttribute(value = "__LOGIN_USER__", required = false) UserVO loginUser ) {
    	ResponseCampaignVO detail = new ResponseCampaignVO(); 
    	if(loginUser != null) {
    		if(loginUser.getAutr().equals("1002") || loginUser.getAutr().equals("1003") ) {
        		detail = campaignService.readCampaignDetail(campaignId, loginUser.getUsrId());    	
        		String returnReason =  campaignService.postReturnReason(campaignId, loginUser.getUsrId());
        		model.addAttribute("returnReason", returnReason);
        	}else {    		
        		detail = campaignService.readCampaignDetail(campaignId);
        	}
    	}else {    		
    		detail = campaignService.readCampaignDetail(campaignId);
    	}
        String usrId = (loginUser != null) ? loginUser.getUsrId() : null;
        

        // 블로그 지수 (BlogDataService에서 직접)
        Double myIndex = (usrId != null) ? blogDataService.readRecentBlogIndex(usrId) : 0.0;

        // 캠페인 참여자 통계
        Map<String, Object> stats = campaignService.readCampaignIndexStats(campaignId, usrId);

        model.addAttribute("indexStats", stats.get("list"));
        model.addAttribute("myIndex", stats.get("myIndex"));
    	
    	log.info( "캠페인 상세조회 결과 : " + detail.toString());
    	model.addAttribute("detail", detail);
    	model.addAttribute("userInfo", loginUser);
    	return "campaign/campaigndetail";
    }
    
    @GetMapping("/campaignmain")
    public String campaignMainPage(RequestSearchCampaignVO requestSearchCampaignVO, Model model,
    						   @SessionAttribute(value = "__LOGIN_USER__", required = false) UserVO loginUser){
    	if(loginUser != null) {
    		requestSearchCampaignVO.setLoginId(loginUser.getUsrId());
    	}
    	log.info( "입력 파라미터 값 : " + requestSearchCampaignVO.toString());
    	ResponseCampaignListVO CampaignListAndCategory = campaignService.readCampaignListAndCategory(requestSearchCampaignVO);
    	
    	model.addAttribute("category", CampaignListAndCategory.getCategoryList());
    	model.addAttribute("campaignList", CampaignListAndCategory.getResponseCampaignList());
    	model.addAttribute("search", requestSearchCampaignVO);
    	
    	log.info( "캠페인 리스트 조회결과 : " + CampaignListAndCategory.getResponseCampaignList().toString());
    	return "campaign/campaignmain";
    }  
    
    
    @GetMapping("/blgr/submittedmycampaign")
    public String submittedmycampaign(Model model,@SessionAttribute(value = "__LOGIN_USER__") 
    						UserVO loginUser) {
    	String blgId = loginUser.getUsrId();
    	ResponseCampaignListVO CampaignListAndCategory = campaignService.readSubmittedMyCampaignByBlgId(blgId);
    	model.addAttribute("campaignList", CampaignListAndCategory.getResponseCampaignList());
    	
    	log.info( "캠페인 리스트 조회결과 : " + CampaignListAndCategory.getResponseCampaignList().toString());
    	return "campaign/submittedmycampaign";
    }
    
    @GetMapping("/blgr/campaignongoing")
    public String campaignongoing(Model model,@SessionAttribute(value = "__LOGIN_USER__") 
    							UserVO loginUser) {
    	String blgId = loginUser.getUsrId();
    	ResponseCampaignListVO CampaignListAndCategory = campaignService.readOnGoingMyCampaignByBlgId(blgId);
    	model.addAttribute("campaignList", CampaignListAndCategory.getResponseCampaignList());
    	
    	log.info( "캠페인 리스트 조회결과 : " + CampaignListAndCategory.getResponseCampaignList().toString());
    	return "campaign/campaignongoing";
    }
    
    @GetMapping("/blgr/closedcampaign")
    public String closedcampaign(Model model,@SessionAttribute(value = "__LOGIN_USER__") 
    							UserVO loginUser) {
    	String blgId = loginUser.getUsrId();
    	ResponseCampaignListVO CampaignListAndCategory = campaignService.readClosedMyCampaignByBlgId(blgId);
    	model.addAttribute("campaignList", CampaignListAndCategory.getResponseCampaignList());
    	
    	log.info( "캠페인 리스트 조회결과 : " + CampaignListAndCategory.getResponseCampaignList().toString());
    	return "campaign/closedcampaign";
    }
    
    @GetMapping("/blgr/favcampaign")
    public String favcampaign(Model model,@SessionAttribute(value = "__LOGIN_USER__") 
    							UserVO loginUser) {
    	String blgId = loginUser.getUsrId();
    	ResponseCampaignListVO CampaignListAndCategory = campaignService.readFavMyCampaignByBlgId(blgId);
    	model.addAttribute("campaignList", CampaignListAndCategory.getResponseCampaignList());
    	
    	log.info( "캠페인 리스트 조회결과 : " + CampaignListAndCategory.getResponseCampaignList().toString());
    	return "campaign/favcampaign";
    }
    
    /**
     * 좋아요 
     * @param loginUser
     * @param campaignId
     * @return
     */
    @ResponseBody
    @PostMapping("/blgr/love/{campaignId}")
    public AjaxResponse favCampaignDo(@SessionAttribute(value = "__LOGIN_USER__") UserVO loginUser, 
    						@PathVariable String campaignId) {
    	String blgId = loginUser.getUsrId();
    	int count = campaignService.favCampaignDo(blgId, campaignId);
    	
    	AjaxResponse ajaxResponse = new AjaxResponse();
    	ajaxResponse.setBody(count);
    	return ajaxResponse;
    }
    
    /**
     * 캠페인 신청, 취소 하기
     * @param loginUser
     * @param campaignId
     * @return
     */
    @ResponseBody
    @PostMapping("/blgr/apply/{campaignId}")
    public AjaxResponse applyCampaign(@SessionAttribute(value = "__LOGIN_USER__") UserVO loginUser, 
    						@PathVariable String campaignId) {
    	String blgId = loginUser.getUsrId();
    	int count = this.campaignService.applyCampaign(campaignId, blgId);
    	
    	AjaxResponse ajaxResponse = new AjaxResponse();
    	ajaxResponse.setBody(count);
    	
    	return ajaxResponse;
    }
    
    /**
     * 포스팅 작성
     * @param requestPostSubmitVO
     * @param loginUser
     * @param campaignId
     * @return
     */
    @ResponseBody
    @PostMapping("/blgr/pstsubmit/{campaignId}")
    public AjaxResponse postSubmit(RequestPostSubmitVO requestPostSubmitVO ,@SessionAttribute(value = "__LOGIN_USER__") UserVO loginUser, 
    						@PathVariable String campaignId) {
    	requestPostSubmitVO.setBlgId(loginUser.getUsrId());
    	requestPostSubmitVO.setCmpnId(campaignId);
    	int count = this.campaignService.postSubmit(requestPostSubmitVO);
    	log.info( "포스팅 input 정보 : " + requestPostSubmitVO.toString());
    
    	
    	AjaxResponse ajaxResponse = new AjaxResponse();
    	ajaxResponse.setBody(count);
    	
    	return ajaxResponse;
    }
    
    /**
     * 포스팅 재 제출
     * @param requestPostSubmitVO
     * @param loginUser
     * @param campaignId
     * @return
     */
    @ResponseBody
    @PostMapping("/blgr/repstsubmit/{campaignId}")
    public AjaxResponse rePostSubmit(RequestPostSubmitVO requestPostSubmitVO ,@SessionAttribute(value = "__LOGIN_USER__") UserVO loginUser, 
    		@PathVariable String campaignId) {
    	
    	requestPostSubmitVO.setBlgId(loginUser.getUsrId());
    	requestPostSubmitVO.setCmpnId(campaignId);
    	int count = this.campaignService.rePostSubmit(requestPostSubmitVO);
    	log.info( "포스팅 input 정보 : " + requestPostSubmitVO.toString());
    	
    	
    	AjaxResponse ajaxResponse = new AjaxResponse();
    	ajaxResponse.setBody(1);
    	
    	return ajaxResponse;
    }
    


    
    @GetMapping("/adv/campaign/applicant/{cmpnId}")
    public String readApplicantList(Model model, @PathVariable String cmpnId,
    								RequestApplicantVO requestApplicantVO,
    								@SessionAttribute(value="__LOGIN_USER__") UserVO loginUser) {
    	
    	if (!loginUser.getAutr().equals("1004")) {
			throw new BrichException("잘못된 접근입니다.", "error/403");
		}
    	
    	// TODO 캠페인 주인과 세션이 다를 때 접근 막을 것
//    	if (!board.getEmail().equals(loginUser.getEmail())) {
//			throw new HelloSpringException("잘못된 접근입니다.", "error/403");
//		}
    	requestApplicantVO.setListSize(10);
    	requestApplicantVO.setPageCountInGroup(10);
    	requestApplicantVO.setCmpnId(cmpnId);

    	if (requestApplicantVO.getOrder() != null) {
    		requestApplicantVO.setOrder(requestApplicantVO.getOrder().toUpperCase());
    	}
    	ResponseApplicantListVO applicantList = this.campaignService.readApplicantListById(requestApplicantVO);
    	model.addAttribute("applicantList", applicantList);
    	model.addAttribute("search", requestApplicantVO);
    	
    	return "campaign/applicant";
    }
    
    @GetMapping("/adv/adoptChange")
    @ResponseBody
    public boolean doUpdateAdptYnAction(RequestApplicantVO requestApplicantVO,
    									@SessionAttribute(value="__LOGIN_USER__") UserVO loginUser) {
    	requestApplicantVO.setUsrId(loginUser.getUsrId());
    	boolean update = this.campaignService.updateAdptYnByCmpnPstAdptId(requestApplicantVO);
    	
    	if (update) {
    		return true;
    	}
    	
    	else {
    		return false;
    	}
    }
    
    @GetMapping("/adv/campaign/adopt/{cmpnId}")
    public String readAdoptList(Model model
    							, @PathVariable String cmpnId
    							,RequestApplicantVO requestApplicantVO) {
    	requestApplicantVO.setListSize(10);
    	requestApplicantVO.setPageCountInGroup(10);
		requestApplicantVO.setCmpnId(cmpnId);
    	
    	ResponseAdoptListVO adoptList = this.campaignService.readResponseAdoptListByCmpnId(requestApplicantVO);
    	model.addAttribute("adoptList", adoptList);
    	model.addAttribute("search",requestApplicantVO);
    	log.info("adoptList---:" + adoptList);
    	return "campaign/adopt";
    }

	@GetMapping("/adv/postapprove/{cmpnPstAdoptId}")
    @ResponseBody
    public boolean doUpdatePstSttsApproveAction(@PathVariable String cmpnPstAdoptId,
    											@SessionAttribute(value="__LOGIN_USER__") UserVO loginUser) {
    	RequestApplicantVO requestApplicantVO = new RequestApplicantVO();
    	requestApplicantVO.setCmpnPstAdptId(cmpnPstAdoptId);
    	requestApplicantVO.setUsrId(loginUser.getUsrId());
    	boolean update = this.campaignService.updatePstSttsApproveByCmpnPstAdoptId(requestApplicantVO);
    	
    	if (update) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

	@PostMapping("/adv/deny/{cmpnPstAdptId}/{cmpnId}")
	@ResponseBody
	public boolean doCreateDenyAction(RequestDenyVO requestDenyVO,
									  @SessionAttribute(value="__LOGIN_USER__") UserVO loginUser) {
		requestDenyVO.setAdvId(loginUser.getUsrId());
		boolean insert = this.campaignService.createDenyByCmpnPstAdoptId(requestDenyVO);
		
		if (insert) {
    		return true;
    	}
    	else {
    		return false;
    	}
	}
	
	@GetMapping("/adv/campaign/write")
	public String createCampaign(Model model,
								 @SessionAttribute(value="__LOGIN_USER__") UserVO loginUser) {
		if (!loginUser.getAutr().equals("1004")) {
			throw new BrichException("잘못된 접근입니다.", "error/403");
		}
		
		ResponseCampaignwriteVO common = this.campaignService.createCampaign();
		model.addAttribute("common", common);
		return "campaign/write";
	}
	
	@GetMapping("/adv/campaign/write/{cdId}")
	@ResponseBody
	public AjaxResponse doReadDistrictAction(@PathVariable String cdId) {
		AjaxResponse response = new AjaxResponse();
		
		List<CommonCodeVO> districtList = this.campaignService.readDistrictByCdId(cdId);
		response.setBody(districtList);
		return response;
	}
	
	@PostMapping("/adv/campaign/write")
	public String doCreateNewCampaignAction(@Valid RequestCreateCmpnVO requestCreateCmpnVO,
											BindingResult bindingResult,
											Model model,
											@SessionAttribute(value="__LOGIN_USER__") UserVO loginUser) {
		if (!loginUser.getAutr().equals("1004")) {
			throw new BrichException("잘못된 접근입니다.", "error/403");
		}
		
		if (bindingResult.hasErrors()) {
			throw new BrichException("인자가 부족합니다.", "error/403");
		}
		
		requestCreateCmpnVO.setUsrId(loginUser.getUsrId());
		
		boolean insert = this.campaignService.createNewCampaign(requestCreateCmpnVO);
		if (insert) {
			return "redirect:/adv/campaign/list";
		}
		
		else {
			return "";
		}
	}
	
	@GetMapping("/adv/campaign/modify")
	public String modifyCampaign(Model model
								 , String cmpnId) {
		ResponseModifyCampaignVO responseModifyCampaignVO = this.campaignService.readModifyInfoByCmpnId(cmpnId);
//		
		model.addAttribute("common", responseModifyCampaignVO.getCommon());
		model.addAttribute("campaign", responseModifyCampaignVO.getCampaign());
		return "campaign/modify";
	}
	
	@PostMapping("/adv/campaign/modify")
	public String doModifyCampaignAction(@Valid RequestCreateCmpnVO requestCreateCmpnVO,
										 BindingResult bindingResult,
										 @SessionAttribute(value="__LOGIN_USER__") UserVO loginUser) {
		if (!loginUser.getAutr().equals("1004")) {
			throw new BrichException("잘못된 접근입니다.", "error/403");
		}
		
		if (bindingResult.hasErrors()) {
			throw new BrichException("인자가 부족합니다.", "error/500");
		}
		
		requestCreateCmpnVO.setUsrId(loginUser.getUsrId());
		boolean modify = this.campaignService.modifyNewCampaign(requestCreateCmpnVO);
		if (modify) {
			return "redirect:/adv/campaign/list";
		}
		
		else {
			return "";
		}
	}
	
	@GetMapping("/adv/campaign/list")
	public String readCampaignListByUsrId(@SessionAttribute(value="__LOGIN_USER__") UserVO loginUser
										  , Model model
										  , RequestSearchCampaignVO requestSearchCampaignVO) {
		requestSearchCampaignVO.setListSize(8);
		requestSearchCampaignVO.setPageCountInGroup(5);
		requestSearchCampaignVO.setLoginId(loginUser.getUsrId());
		
		if (!loginUser.getAutr().equals("1004")) {
			throw new BrichException("잘못된 접근입니다.", "error/403");
		}
		
		ResponseCampaignListVO campaignList = this.campaignService.readCampaignListByUsrId(requestSearchCampaignVO);
		model.addAttribute("campaignList", campaignList);
		model.addAttribute("search", requestSearchCampaignVO);
		log.info("campaignFilecheck : " + campaignList.getResponseCampaignList().toString());
		return "campaign/list";
	}
	
	@GetMapping("/adv/campaign/deny-history/{cmpnId}")
	public String readDenyHistory(Model model, 
								  RequestSearchCampaignVO requestSearchCampaignVO) {
		
		ResponseCampaignListVO denyList = this.campaignService.readDenyHistoryByCmpnId(requestSearchCampaignVO);
		denyList.setIsDeny(true);
		log.info("---denyList:" + denyList.toString());
		model.addAttribute("campaignList", denyList);
		model.addAttribute("search", requestSearchCampaignVO);
		
		return "campaign/list";
	}
	
	@PostMapping("/adv/campaign/temporary")
	public String doCreateTemporaryCampaignAction(RequestCreateCmpnVO requestCreateCmpnVO
												  , @SessionAttribute(value="__LOGIN_USER__") UserVO loginUser) {
		requestCreateCmpnVO.setUsrId(loginUser.getUsrId());
		log.info("--temporary--" + requestCreateCmpnVO.toString());
		boolean create = this.campaignService.createTemporaryCampaign(requestCreateCmpnVO);
		
		return "redirect:/adv/campaign/list";
	}
	
	@GetMapping("/adv/post-deny-history/{postId}")
	@ResponseBody
	public AjaxResponse doReadDenyHistoryAction(@PathVariable String postId) {
		List<ResponseDenyHistoryVO> history = this.campaignService.readDenyHistoryByCmpnPstAdptId(postId);

		AjaxResponse response = new AjaxResponse();
		response.setBody(history);
		
		return response;
	}
	
	@GetMapping("/adv/blog-info/{usrId}")
	public String viewBlogDashBoard(@PathVariable String usrId, HttpSession session, Model model, RequestExpireSoonCampaignVO requestExpireSoonCampaignVO, RequestBlogIndexListVO requestBlogIndexListVO) throws JsonProcessingException {

		List<BlogIndexVO>indexResult = 
				this.blogDataService.readBlogIndexList(usrId);

		List<DailyVisitorVO> dailyVisitorsResult =
				this.blogDataService.selectDailyVisitors(usrId);

		double currentIndex = this.blogDataService.selectMostRecentIndex(usrId);
		int totalVisitor = this.blogDataService.selectTotalVisitor(usrId);
		
		model.addAttribute("user", usrId);
		model.addAttribute("dailyVisitorsResult", dailyVisitorsResult);
		model.addAttribute("index", indexResult);
		model.addAttribute("currentIndex",currentIndex);
		model.addAttribute("totalVisitor", totalVisitor);


		return "campaign/userDetail";
	}
}