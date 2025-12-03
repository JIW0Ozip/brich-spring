package com.ktdsuniversity.edu.domain.user.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.domain.report.vo.AdminPenaltyRequestVO;
import com.ktdsuniversity.edu.domain.user.service.AdminUserService;
import com.ktdsuniversity.edu.domain.user.vo.AdminUserBaseInfoVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminUserListVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminUserModifyInfoVO;
import com.ktdsuniversity.edu.global.common.AjaxResponse;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AdminUserController {
	
	private static final Logger log = LoggerFactory.getLogger(AdminUserController.class);

	@Autowired
	private AdminUserService adminUserService;
	
	/**
	 * 회원 관리 - 회원(전체/블로거/광고주) 목록
	 * @param tab
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/user_list")
	public String viewAdminUserListPage(
			@RequestParam(required = false, defaultValue = "all") String tab, Model model) {
		
		if (tab == null || tab.isEmpty()) {
	        tab = "all";
	    }
	    
	    List<AdminUserListVO> userList = this.adminUserService.readAdminUserList(tab);
	    
	    model.addAttribute("userList", userList);
	    model.addAttribute("currentTab", tab); 
	    
	    return "/user/admin_user_list";
	}
	
	/**
	 * 회원 관리 - 상세 정보 페이지
	 * @param usrId
	 * @param model
	 * @return 
	 */
	@GetMapping("/admin/user_detail/{usrId}")
	public String viewAdminUserDetailPage(@PathVariable String usrId, Model model, 
														HttpServletRequest request) {
		
		AdminUserBaseInfoVO userInfo = this.adminUserService.readAdminUserDetailById(usrId);
		
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("classType", userInfo.getClass().getSimpleName());
		model.addAttribute("pathInfo", request.getServletPath());
		
		return "/user/admin_user_detail";
	}
	
	/**
	 * 회원 관리 - 광고주 가입 승인/반려 처리
	 * @param requestData
	 * @return Map<usrId, autr>
	 */
	@ResponseBody
	@PostMapping("/admin/advertiser_regist_process")
    public AjaxResponse doAdminAdvertiserRegistProcessAction(@RequestBody Map<String, String> requestData) {
		
    	boolean isSuccess = this.adminUserService.updateAdvertiserRegistAuthCode(requestData);
    	
    	AjaxResponse ajaxResponse = new AjaxResponse();
    	ajaxResponse.setBody(isSuccess);
    	
    	return ajaxResponse;
    }
	
	/**
	 * 회원 관리 - 정보 수정 페이지
	 * @return
	 */
	@GetMapping("/admin/user_modify/{usrId}")
	public String viewAdminUserInfoModifyPage(@PathVariable String usrId, Model model, 
															HttpServletRequest request) {
		
		AdminUserBaseInfoVO userInfo = this.adminUserService.readAdminUserDetailById(usrId);
		
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("classType", userInfo.getClass().getSimpleName());
		model.addAttribute("pathInfo", request.getServletPath());

		List<CommonCodeVO> BlogcategoryList = this.adminUserService.readBlogCategoryList();
		model.addAttribute("BlogcategoryList", BlogcategoryList);
		
		return "/user/admin_user_info_modify";
	}
	
	/**
	 * 회원 관리 - 회원 정보 수정(UPDATE/INSERT) 처리
	 * @param usrId
	 * @param adminUserModifyInfoVO
	 * @param newFiles
	 * @return
	 */
	@ResponseBody
	@PostMapping("/admin/user_modify/{usrId}")
	public AjaxResponse doAdminUserInfoModifyAction(@PathVariable String usrId, 
													@ModelAttribute AdminUserModifyInfoVO adminUserModifyInfoVO,
													@RequestParam(name="file", required=false) List<MultipartFile> newFiles) {
		
		boolean isSuccess = this.adminUserService.updateUserInfo(adminUserModifyInfoVO, newFiles);
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		ajaxResponse.setBody(isSuccess);
		
		return ajaxResponse;
	}
	
	/**
	 * 회원 관리 - 블로그 주소 수동 인증
	 * @param usrId
	 * @param adminUserModifyInfoVO
	 * @return
	 */
	@ResponseBody
	@PostMapping("/admin/blog_passivity_certify/{usrId}")
	public AjaxResponse doAdminBlogAddressCertifyAction(@PathVariable String usrId, 
														@RequestBody AdminUserModifyInfoVO adminUserModifyInfoVO) {
		
		boolean isSuccess = this.adminUserService.updateBlogAddress(adminUserModifyInfoVO);
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		ajaxResponse.setBody(isSuccess);
		
		return ajaxResponse;
	}
	
	/**
	 * 회원 관리 - 경고/정지 처리
	 * @param usrId
	 * @param adminPanaltyRequestVO
	 * @return
	 */
	@ResponseBody
	@PostMapping("/admin/user_penalty_process/{usrId}")
	public AjaxResponse doAdminUserPenaltyProcessAction(@PathVariable String usrId, 
														@RequestBody AdminPenaltyRequestVO adminPanaltyRequestVO) {
		
		boolean isSuccess = this.adminUserService.updateUserPenaltyInfo(adminPanaltyRequestVO);
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		ajaxResponse.setBody(isSuccess);
		
		return ajaxResponse;
	}
	
	
}
