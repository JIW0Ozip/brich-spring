package com.ktdsuniversity.edu.domain.report.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.domain.report.service.AdminReportService;
import com.ktdsuniversity.edu.domain.report.vo.AdminPenaltyRequestVO;
import com.ktdsuniversity.edu.domain.report.vo.AdminReportDetailVO;
import com.ktdsuniversity.edu.domain.report.vo.AdminReportListVO;
import com.ktdsuniversity.edu.global.common.AjaxResponse;

@Controller
public class AdminReportController {

	private static final Logger log = LoggerFactory.getLogger(AdminReportController.class);
	
	@Autowired
	private AdminReportService adminReportService;
	
	/**
	 * 신고 관리 - 신고 목록 페이지
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/report_list")
	public String viewAdminReportListPage(Model model) {
		
		List<AdminReportListVO> reportList = adminReportService.readAdminReportList();
		
		model.addAttribute("reportList", reportList);
		
		return "/report/admin_report_list";
	}
	
	/**
	 * 신고 관리 - 신고 상세 정보 페이지
	 * @param rptId
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/report_detail/{rptId}")
	public String viewAdminReportDetailPage(@PathVariable String rptId, Model model) {
		
		AdminReportDetailVO reportInfo = this.adminReportService.readAdminReportDetailById(rptId);
		
		model.addAttribute("reportInfo", reportInfo);
		
		return "/report/admin_report_detail";
	}
	
	/**
	 * 신고 관리 - 신고 건 기각/경고/정지 처리
	 * @param rptId
	 * @param requestVO
	 * @return
	 */
	@ResponseBody
	@PostMapping("/admin/report_penalty_process/{rptId}")
	public AjaxResponse doAdminReportToPanaltyProcessAction(@PathVariable String rptId, @RequestBody AdminPenaltyRequestVO requestVO) {
		
		boolean isSuccess = this.adminReportService.updateReportAndPenaltyInfo(requestVO);
		
		log.info("제발: " + requestVO.getAdminId());
		log.info("제발: " + requestVO.getRptId());
		log.info("제발: " + requestVO.getRptedUsrId());
		log.info("제발: " + requestVO.getPenaltyOption());
		log.info("제발: " + requestVO.getPenaltyKeyword());
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		ajaxResponse.setBody(isSuccess);
		
		return ajaxResponse;
	}
	
}
