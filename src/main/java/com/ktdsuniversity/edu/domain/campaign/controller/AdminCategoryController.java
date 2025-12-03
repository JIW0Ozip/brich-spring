package com.ktdsuniversity.edu.domain.campaign.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.domain.campaign.service.AdminCategoryService;
import com.ktdsuniversity.edu.domain.campaign.vo.AdminCampaignCategoryVO;
import com.ktdsuniversity.edu.global.common.AjaxResponse;

@Controller
public class AdminCategoryController {
	
	private static final Logger log = LoggerFactory.getLogger(AdminCategoryController.class);
	
	@Autowired
	private AdminCategoryService adminCategoryService;
	
	/**
	 * 카테고리 관리 페이지
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/category-manage")
	public String viewAdminCategoryManagePage(Model model) {
		
		List<AdminCampaignCategoryVO> campaignCategoryList = this.adminCategoryService.readCampaignCategoryList();
		
		model.addAttribute("campaignCategoryList", campaignCategoryList);
		
		return "/campaign/admin_category_manage";
	}
	
	/**
	 * 카테고리 분할 option List 받아오기 (자신의 하위 카테고리)
	 * @param parentCdId
	 * @return
	 */
	@ResponseBody
	@GetMapping("/admin/category-manage/modal-list/children")
	public List<AdminCampaignCategoryVO> getChildrenCategoryList(@RequestParam String parentCdId) {
		
		List<AdminCampaignCategoryVO> divTargetCategory = this.adminCategoryService.readChildrenCategoryList(parentCdId);
		
		return divTargetCategory;
	}
	
	/**
	 * 카테고리 병합 option List 받아오기 (자신 제외 상위 카테고리)
	 * @param excludeCdId
	 * @return
	 */
	@ResponseBody
	@GetMapping("/admin/category-manage/modal-list/parent")
	public List<AdminCampaignCategoryVO> getParentCategoryList(@RequestParam String excludeCdId) {
		
		List<AdminCampaignCategoryVO> mergeTargetCategory = this.adminCategoryService.readParentCategoryList(excludeCdId);
		
		return mergeTargetCategory;
	}
	
	/**
	 * 카테고리 추가 (SELECT/INSERT)
	 * @param adminCampaignCategoryVO
	 * @return
	 */
	@ResponseBody
	@PostMapping("/admin/category-manage/add")
	public AjaxResponse doAddCampaignCategoryAction(@RequestBody AdminCampaignCategoryVO adminCampaignCategoryVO) {
		
		boolean isSuccess = this.adminCategoryService.createNewCampaignCategory(adminCampaignCategoryVO);
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		ajaxResponse.setBody(isSuccess);
		
		return ajaxResponse;
	}
	
	/**
	 * 카테고리 분할 (UPDATE)
	 * @param adminCampaignCategoryVO
	 * @return
	 */
	@ResponseBody
	@PostMapping("/admin/category-manage/div")
	public AjaxResponse doDivCampaignCategoryAction(@RequestBody AdminCampaignCategoryVO adminCampaignCategoryVO) {
		
		boolean isSuccess = this.adminCategoryService.updateDivCampaignCategory(adminCampaignCategoryVO);
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		ajaxResponse.setBody(isSuccess);
		
		return ajaxResponse;
	}
	
	/**
	 * 카테고리 병합 (UPDATE)
	 * @param adminCampaignCategoryVO
	 * @return
	 */
	@ResponseBody
	@PostMapping("/admin/category-manage/merge")
	public AjaxResponse doMergeCampaignCategoryAction(@RequestBody AdminCampaignCategoryVO adminCampaignCategoryVO) {
		
		boolean isSuccess = this.adminCategoryService.updateMergeCampaignCategory(adminCampaignCategoryVO);
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		ajaxResponse.setBody(isSuccess);
		
		return ajaxResponse;
	}
	
	/**
	 * 카테고리 노출 순서 변경 (UPDATE)
	 * @param adminCampaignCategoryVO
	 * @return
	 */
	@ResponseBody
	@PostMapping("/admin/category-manage/change-order")
	public AjaxResponse doChangeOrderCampaignCategoryAction(@RequestBody AdminCampaignCategoryVO adminCampaignCategoryVO) {
		
		boolean isSuccess = this.adminCategoryService.updateChangeOrderCampaignCategory(adminCampaignCategoryVO);
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		ajaxResponse.setBody(isSuccess);
		
		return ajaxResponse;
		
	}
}
