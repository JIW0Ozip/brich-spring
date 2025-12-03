package com.ktdsuniversity.edu.domain.blog.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ktdsuniversity.edu.domain.blog.service.BlogRankService;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/************************************************
 *
 * 1. 블로그 검색 API 호출 및 사용자의 블로그 순위 조회
 * 2. 블로그 일일 방문자 수 조회 API 호출 및 DB 저장
 *
 ************************************************/
@RestController
public class SearchBlogController {
	
	private static final Logger log = LoggerFactory.getLogger(SearchBlogController.class);
	
	    @Autowired
	    BlogRankService rankService;


	    /**
	     * 블로그 검색 API 호출 및 사용자의 블로그 순위 조회
	     * @param query 검색어
	     * @param userBlogLink 사용자의 블로그 링크
	     * @return 사용자의 블로그 순위와 API 응답 결과
	     */
	    @GetMapping(value="/blog/{usrId}/rank")
	    public ObjectNode getBlogRank(@PathVariable String usrId,
									  HttpSession session,
									  Model model,
									  @RequestParam String query,
									  @RequestParam String userBlogLink) {
			UserVO loginUser = (UserVO) session.getAttribute("__LOGIN_USER__");
			if (loginUser == null || !loginUser.getUsrId().equals(usrId)) {
				return null;
			}

	        // API 응답 전체와 블로그 순위를 반환
	        ObjectNode result = rankService.searchBlogAndFindUserRank(query, userBlogLink);

	        return result;  // JSON 응답으로 전체 API 결과와 순위 정보 반환
	    }
	
	
}
