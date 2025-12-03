package com.ktdsuniversity.edu.domain.blog.controller;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktdsuniversity.edu.domain.blog.service.BlogDataService;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignPostManageVO;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.ResponseExpireSoonListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseDenyHistoryVO;
import com.ktdsuniversity.edu.domain.blog.vo.BlogDetailStatVO;
import com.ktdsuniversity.edu.domain.blog.vo.BlogIndexVO;
import com.ktdsuniversity.edu.domain.blog.vo.DailyVisitorVO;
import com.ktdsuniversity.edu.domain.blog.vo.RequestBlogIndexListVO;
import com.ktdsuniversity.edu.domain.blog.vo.RequestExpireSoonCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.service.CampaignService;
import com.ktdsuniversity.edu.domain.campaign.vo.ResponseExpireSoonListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignListVO;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class BlogDataController {

	private static final Logger log = LoggerFactory.getLogger(BlogDataController.class);

	@Autowired BlogDataService blogDataService;
	@Autowired CampaignService campaignService;

	@GetMapping("/blog/{usrId}/dashboard")
	public String viewBlogDashBoard(@PathVariable String usrId, HttpSession session, Model model, RequestExpireSoonCampaignVO requestExpireSoonCampaignVO, RequestBlogIndexListVO requestBlogIndexListVO) throws JsonProcessingException {
	    
		UserVO loginUser = (UserVO) session.getAttribute("__LOGIN_USER__");
	    if (loginUser == null || !loginUser.getUsrId().equals(usrId)) {
	        return "redirect:/access-denied";
	    }

		requestExpireSoonCampaignVO.setListSize(15);
		requestExpireSoonCampaignVO.setPageCount(1);

		ResponseExpireSoonListVO result =
				this.blogDataService.readExpireSoonCampaignList(requestExpireSoonCampaignVO);
		List<BlogIndexVO>indexResult = 
				this.blogDataService.readBlogIndexList(usrId);

		List<DailyVisitorVO> dailyVisitorsResult =
				this.blogDataService.selectDailyVisitors(usrId);
		List<CommonCodeVO> goldenKeywordList =
				this.blogDataService.selectUserCategoryKeywords(usrId);
		List<CampaignVO> recommendResult = this.blogDataService.selectRecommendCampaign(usrId);
		ObjectMapper mapper = new ObjectMapper();
		String goldenKeywordListJson = mapper.writeValueAsString(goldenKeywordList);
		ResponseCampaignListVO CampaignListAndCategory = 
				campaignService.readSubmittedMyCampaignByBlgId(usrId);
		double currentIndex = this.blogDataService.selectMostRecentIndex(usrId);
		int totalVisitor = this.blogDataService.selectTotalVisitor(usrId);

		model.addAttribute("user", loginUser);
		model.addAttribute("dailyVisitorsResult", dailyVisitorsResult);
		model.addAttribute("list", result);
		model.addAttribute("user", loginUser);
		model.addAttribute("paginator", requestExpireSoonCampaignVO);
		model.addAttribute("index", indexResult);
		model.addAttribute("goldenKeywordListJson", goldenKeywordListJson);
		model.addAttribute("currentIndex",currentIndex);
		model.addAttribute("totalVisitor", totalVisitor);
		model.addAttribute("recommended",recommendResult);

		model.addAttribute("campaignList", CampaignListAndCategory.getResponseCampaignList());
		return "blog/dashboard";
	}
	
	@GetMapping("/blog/{usrId}/manage")
	public String viewBlogManagePage(@PathVariable String usrId, HttpSession session, Model model, RequestExpireSoonCampaignVO requestExpireSoonCampaignVO) {
		UserVO loginUser = (UserVO) session.getAttribute("__LOGIN_USER__");
		if (loginUser == null || !loginUser.getUsrId().equals(usrId)) {
			System.out.println(usrId);
	        return "redirect:/access-denied";
	    }
		if(loginUser.getBlgAddrs() == null) {
			return "redirect:/blog/"+ loginUser.getUsrId() + "/verification";
		}
		
		
		List<CampaignPostManageVO> results = this.blogDataService.readCampaignPostByUsrId(usrId);
		model.addAttribute("list",results);
		
		return "/blog/manage";
	}
	
	@GetMapping("/blog/{usrId}/verification")
	public String viewBlogVerificationPage(@PathVariable String usrId, HttpSession session, Model model) {
		UserVO loginUser = (UserVO) session.getAttribute("__LOGIN_USER__");
	    if (loginUser == null || !loginUser.getUsrId().equals(usrId) || loginUser.getBlgAddrs() != null) {
	    	
	        return "redirect:/access-denied";
	    }
	    
	    
		return "/blog/verification";
	}
	
	@GetMapping("/api/blog/index/{usrId}/detail")
	@ResponseBody
	public List<BlogDetailStatVO> getBlogDetails(
	        @PathVariable String usrId) {
	    List<BlogDetailStatVO> list = blogDataService.readBlogDetailStat(usrId);
	    return list;
	}
	
	@GetMapping("/api/user/{postId}/return-reason")
	@ResponseBody
	public List<ResponseDenyHistoryVO> getReturnHistory(@PathVariable String postId) {
	    return blogDataService.getReturnHistory(postId);
	}

}
