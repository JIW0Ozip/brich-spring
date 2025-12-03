package com.ktdsuniversity.edu.domain.inqr.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.domain.inqr.service.AdminInqrService;
import com.ktdsuniversity.edu.domain.inqr.vo.AdminAnsrRegistVO;
import com.ktdsuniversity.edu.domain.inqr.vo.AdminInqrDetailVO;
import com.ktdsuniversity.edu.domain.inqr.vo.AdminInqrListVO;
import com.ktdsuniversity.edu.global.common.AjaxResponse;

@Controller
public class AdminInqrController {
	
	private static final Logger log = LoggerFactory.getLogger(AdminInqrController.class);
	
	@Autowired
	private AdminInqrService adminInqrService;
	
	/**
	 * 문의 관리 - 문의 목록 페이지
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/inqr_list")
	public String viewAdminInqrListPage(Model model) {
		
		List<AdminInqrListVO> inqrList = this.adminInqrService.readAdminInqrList();
		
		model.addAttribute("inqrList", inqrList);
		
		return "/inqr/admin_inqr_list";
	}
	
	/**
	 * 문의 관리 - 상세 정보 페이지
	 * @param inqrId
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/inqr_detail/{inqrId}")
	public String viewAdminInqrDetailPage(@PathVariable String inqrId, Model model) {
		
		AdminInqrDetailVO inqrInfo = this.adminInqrService.readAdminInqrDetailByInqrId(inqrId);
		
		model.addAttribute("inqrInfo", inqrInfo);
		
		return "/inqr/admin_inqr_detail";
	}
	
	/**
	 * 문의 관리 - 답변 등록 처리 (UPDATE, 파일 첨부할 경우 +INSERT)
	 * @param inqrId
	 * @param ansrInfo
	 * @return
	 */
	@ResponseBody
	@PostMapping("/admin/ansr_regist_process/{inqrId}")
	public AjaxResponse doAdminAnsrRegistProcessAction(@PathVariable String inqrId, 
													   @ModelAttribute AdminAnsrRegistVO ansrInfo) {
		
		boolean isSuccess = this.adminInqrService.updateInqrToAnswer(ansrInfo, ansrInfo.getFiles());
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		ajaxResponse.setBody(isSuccess);
		
		return ajaxResponse;
	}

}
