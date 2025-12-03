package com.ktdsuniversity.edu.domain.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.domain.blog.controller.SearchBlogController;
import com.ktdsuniversity.edu.domain.campaign.service.CampaignService;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignwriteVO;
import com.ktdsuniversity.edu.domain.user.service.UserService;
import com.ktdsuniversity.edu.domain.user.vo.BlogCategoryVO;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserFindIdVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserLoginVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserRegistVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserResetPasswordVO;
import com.ktdsuniversity.edu.global.common.AjaxResponse;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;
import com.ktdsuniversity.edu.global.exceptions.BrichException;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private CampaignService campaignService;
    
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

    /**
     * 로그인 페이지 
     * @return 로그인 페이지
     */
    @GetMapping("/login")
    public String viewLoginPage() {
    	return "/user/login";
    }
    
    /**
     * @param httpSession 로그인 정보를 담아줄 세션 
     * @param nextUrl 로그인 이후 원래 보려 했던 페이지로 이동하기 위한 url정보
     * @param requestUserLoginVO 로그인에 필요한 아이디와 이메일
     * @return 직접 로그인 경로에 로그인 한 경우는 각 권한별 메인페이지, 
     * 		   서비스를 이용하다가 세션이 끊긴 경우라면 원래 보던 url로 이동
     */
    @PostMapping("/login")
    public String doUserLoginAction(@Valid RequestUserLoginVO requestUserLoginVO
    		                      , BindingResult bindingResult
    		                      , Model model
    		                      , HttpSession httpSession
    		                      , @RequestParam String nextUrl) {
    	
    	if(bindingResult.hasErrors()) {
    		throw new BrichException("아이디와 비밀번호를 모두 입력해주세요.", "/WEB-INF/views/user/login.jsp");
    	}
    	UserVO loginUser = this.userService.readUser(requestUserLoginVO);
    	log.info("{}",loginUser);
    	
    	if(loginUser != null) {
    		httpSession.setAttribute("__LOGIN_USER__", loginUser);
    	}
    	//권한 별 redirect를 위한 auth 변수
    	String auth = loginUser.getAutr();
    	//dashboard로 redirect해주기 위한 usrId
    	String usrId = loginUser.getUsrId();
  
    	if(nextUrl.equals("")) {
    		//관리자라면 관리자 메인 페이지 
    		if(auth.equals("1001")) {
    			return "redirect:/admin/user_list";
    		}
    		//블로거라면 대쉬보드 페이지
    		if(auth.equals("1002") || auth.equals("1003")) {
    			return "redirect:/blog/" + usrId + "/dashboard";
    		}
    		//광고주라면 캠페인 메인 페이지
    		if(auth.equals("1004")) {
    			return "redirect:/campaignmain";
    		}
    	}
    	
    	log.info("nextURL : {}", nextUrl);
    	
    	return "redirect:" + nextUrl;
    }

    /**
     * 로그아웃 
     * @param httpSession 로그인 정보를 담은 세션 
     * @return 로그인 페이지로 이동(추후 수정) 
     */
    @GetMapping("/logout")
	public String doLogoutAction(HttpSession httpSession) {
		httpSession.invalidate();
		return "redirect:/login";
	}
    
    /**
     * 회원 가입 유형 선택 페이지 (광고주, 블로거)
     * @return 회원 가입 유형 선택 view
     */
    @GetMapping("/choose-role")
    public String viewChooseRolePage() {
    	System.out.println("choose-role");
    	return "/user/chooserole";
    }
    
    /**
     * 이용약관 동의 페이지 
     * @param role 블로거, 광고주 어떤 유형으로 가입하려는지 담은 파라미터
     * @param model 권한 데이터를 담아줄 Model 
     * @return 이용약관 동의 view 
     */
    @GetMapping("/terms/{role}")
    public String viewTermPage(@PathVariable String role, Model model) {
    	if(role.equals("blogger") || role.equals("advertiser")) {
    		model.addAttribute("role", role);
    		return "/user/term";
    	}
    	//에러 처리 할 예정
    	return null;
    	
    }
    
    /**
     * 회원가입 페이지
     * @param role 블로거, 광고주 어떤 유형으로 가입하려는지 담은 파라미터
     * @param model 권한 데이터를 담아줄 Model 
     * @return 회원가입 view
     */
    @GetMapping("/regist/{role}")
    public String viewRegistPage(@PathVariable String role, Model model) {
    	//List<CommonCodeVO> blogCategory = this.userService.readCategoryList();
    	ResponseCampaignwriteVO common = this.campaignService.createCampaign();
    	if(role.equals("blogger") || role.equals("advertiser")) {
    		model.addAttribute("common", common);
    		model.addAttribute("role", role);
    	} else {
    		return null;
    	}
    	return"/user/regist";
    }
    
    /**
     * 회원가입
     * @param requestUserRegistVO 회원가입에 필요한 데이터를 담은 VO
     * @return 각 권한별 메인 페이지로 이동시켜줄 예정
     */
    @PostMapping("/regist")
    public String doUserRegistAction(@Valid RequestUserRegistVO requestUserRegistVO
    							   , BindingResult bindingResult
    							   , Model model
    							   , RedirectAttributes redirectAttributes) {
    	
	   	log.info("requestUserRegistVO: {}", requestUserRegistVO);

	   	// 광고주인 경우 cmpny 필수 체크 추가
	    if(requestUserRegistVO.getAutr().equals("1007")) {
	        if(requestUserRegistVO.getCmpny() == null || requestUserRegistVO.getCmpny().isBlank()) {
	            bindingResult.rejectValue("cmpny", "required", "필수 입력입니다.");
	        }
	    }
    	//서버단 Validation
    	if(bindingResult.hasErrors()) {
    	   	log.info("requestUserRegistVO: {}", requestUserRegistVO);
    		model.addAttribute("registData", requestUserRegistVO);
    		
    		//블로거 회원 가입 검증 실패
    		if(requestUserRegistVO.getAutr().equals("1003")) {
    			ResponseCampaignwriteVO common = this.campaignService.createCampaign();
    	        model.addAttribute("common", common); 

    			model.addAttribute("role", "blogger");
    			
    			log.info("{}",model.getAttribute("registData"));
    			return "/user/regist";	
    		}
    		//광고주 회원 가입 검증 실패
    		else if(requestUserRegistVO.getAutr().equals("1007")){
   			 model.addAttribute("role", "advertiser");
    			return "/user/regist";	
    		}
    	}
    	//password 일치 검사 
    	if(!requestUserRegistVO.getPswrd().equals(requestUserRegistVO.getPswrdConfirm())) {
    		//블로거 비밀번호 확인 실패
    		if(requestUserRegistVO.getAutr().equals("1002")) {
    			model.addAttribute("role", "blogger");
    			model.addAttribute("passwordError", "비밀번호가 일치하지 않습니다.");
    			return "/user/regist";	
    		}
    		//광고주 비밀번호 확인 실패
    		else if(requestUserRegistVO.getAutr().equals("1007")){
   			 model.addAttribute("role", "advertiser");
   			 model.addAttribute("passwordError", "비밀번호가 일치하지 않습니다.");
    			return "/user/regist";	
    		}
    	}
    	
    	boolean registResult = this.userService.createNewUser(requestUserRegistVO);
    	//가입이 완료되면 로그인 페이지로 이동시킨다.
    	
    	// 회원가입 성공 메시지를 모델에 담기
    	if(requestUserRegistVO.getAutr().equals("1007")) {
    	    model.addAttribute("successMessage", "회원가입 신청이 완료되었습니다!");
    	    model.addAttribute("role", "advertiser");
    	} else {
    	    model.addAttribute("successMessage", "회원가입이 완료되었습니다!");
    	    model.addAttribute("role", "blogger");
    	}
        
    	return "/user/regist";
    }
    
    /**
     * 이미 등록된 아이디인지 체크
     * @param logId
     * @return 0: 일치하는 아이디 없음(성공), 1: 일치하는 아이디 있음(실패), 2: 요구하는 형식에 부합하지 않음
     */
    @GetMapping("/duplicate-id/check")
    @ResponseBody
    public AjaxResponse doDuplicateEmailCheckAction (@RequestParam String logId) {
    	log.info("logId : {}", logId);
    	int count = this.userService.readUserIdByLogId(logId);
    	AjaxResponse ajaxResponse = new AjaxResponse();
    	ajaxResponse.setBody(count);
    	
    	if (!logId.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d!@#$%^&*]{8,16}$")) {
    		ajaxResponse.setBody(2);
    	} 
    	
    	return ajaxResponse;
    }
    
    @GetMapping("/find/id")
    public String viewFindIdPage() {
    	return "user/findid";
    }
    
    @GetMapping("/find/id/{nm}")
    public String viewFindIdSuccess(@PathVariable(required=false) String nm
    							  , HttpSession httpSession
    							  , Model model) {
    	//세션에 있는 인증되어 있는 이메일을 가져온다.
    	String email = (String)httpSession.getAttribute("verifiedEmail");
    	log.info("email: {}", email);
 

    	
    	RequestUserFindIdVO requestUserFindIdVO = new RequestUserFindIdVO();
    	requestUserFindIdVO.setEml(email);
    	requestUserFindIdVO.setNm(nm);
    	String logId = this.userService.readLogIdByNameAndEmail(requestUserFindIdVO);
    	
    	if(email == null || logId == null || nm == "non") {
    		model.addAttribute("checked", "해당하는 정보가 없습니다.");
    		return "user/findid";
    	}
    	int start = 3; 
    	int end = 7;   

    	String masked = logId.substring(0, start)   // 앞부분 그대로
    	               + "****"                   // 중간은 마스킹
    	               + logId.substring(end);      // 나머지 그대로
    	model.addAttribute("findedId", masked);
    	
    	return "user/findresult";
    }
    
    @GetMapping("/reset/password")
    public String viewResetPasswordCheckPage() {
    	return "/user/resetpassword";
    }
    
    @PostMapping("/reset/password")
    public String doPasswordResetAction(RequestUserResetPasswordVO resetPasswordInfo) {
    	boolean updateResult = this.userService.updatePswrdByLogIdAndPswrd(resetPasswordInfo);   	
    	return "redirect:/login";
    }
    
    @GetMapping("/regist/area/{id}")
    @ResponseBody
    public AjaxResponse getArea(@PathVariable String id) {
    	AjaxResponse response = new AjaxResponse();
		List<CommonCodeVO> districtList = this.campaignService.readDistrictByCdId(id);
		response.setBody(districtList);
		return response;
    }
}