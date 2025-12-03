package com.ktdsuniversity.edu.domain.blog.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.domain.blog.dao.PostDataDao;
import com.ktdsuniversity.edu.domain.blog.service.BlogDataService;
import com.ktdsuniversity.edu.domain.blog.vo.PostDataInsertVO;
import com.ktdsuniversity.edu.domain.blog.vo.RequestBlogInfoVO;
import com.ktdsuniversity.edu.domain.blog.vo.RequestBlogTitleVO;
import com.ktdsuniversity.edu.domain.blog.vo.RequestModifyBlogAddrsVO;
import com.ktdsuniversity.edu.domain.blog.vo.RequestPostDataResultVO;
import com.ktdsuniversity.edu.domain.user.service.UserService;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class CrawlingController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogDataService blogDataService;
	
	
	
	@PostMapping("/results")
	public Map<String, Object> ResponseInitPostData(@RequestBody RequestPostDataResultVO request) {
		Map<String, Object> result = new HashMap<>();
	    for (PostDataInsertVO post : request.getPostList()) {
	        post.setBlgAddrs(request.getBlgAddrs());
	        boolean insert = this.blogDataService.insertPostData(post);
	    }
	    result.put("success", true);
	    return result;
	}
	
    @PostMapping("/verify-code")
    public Map<String, Object> generateVerificationCode(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        String code = this.blogDataService.generateVerificationCode();
        session.setAttribute("VERIFY_CODE", code); // 세션에 저장
        result.put("code", code);
        return result;
    }
	
	@PostMapping("/verify")
	public Map<String, Object> ResponseVerifyBlogger(@RequestBody Map<String, String> request, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        RequestModifyBlogAddrsVO blogInfo = new RequestModifyBlogAddrsVO();
        blogInfo.setBlgAddrs(request.get("blogUrl"));
        blogInfo.setId(request.get("usrId"));

        String code = (String) session.getAttribute("VERIFY_CODE");
        boolean verified = this.blogDataService.runPythonVerification(blogInfo, code);
        if (verified) {
        	UserVO loginUser = (UserVO) session.getAttribute("__LOGIN_USER__");
        	if (loginUser != null) {
        	    loginUser.setBlgAddrs(blogInfo.getBlgAddrs()); // ✅ 세션 안의 기존 객체 수정
        	}
            CompletableFuture.runAsync(() -> {
                this.blogDataService.runPythonInitialPostData(blogInfo.getBlgAddrs());
            });
            boolean update = this.blogDataService.runPythonBlogStats(blogInfo.getBlgAddrs());
            boolean updatetitle = this.blogDataService.runPythonBlogTitle(blogInfo.getBlgAddrs());
            
        }
        
        

        result.put("success", verified);
        return result;

	}
	
	@PostMapping("/stats")
	public Map<String, Object> responseBlogInfo(@RequestBody RequestBlogInfoVO request, HttpSession session){
		Map<String, Object> result = new HashMap<>();
		boolean update = this.userService.updateBlogInfo(request);
		result.put("success", true);
		return result;
		
	}
	
	@PostMapping("/title")
	public Map<String, Object> responseBlogTitle(@RequestBody RequestBlogTitleVO request){
		Map<String, Object> result = new HashMap<>();
		boolean update = this.userService.updateBlogTitle(request);
		if(update) {
		result.put("success", true);
		}
		return result;
	}
	
}
