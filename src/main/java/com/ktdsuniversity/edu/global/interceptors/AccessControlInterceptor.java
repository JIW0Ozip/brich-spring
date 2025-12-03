package com.ktdsuniversity.edu.global.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;

import com.ktdsuniversity.edu.domain.user.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AccessControlInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String uri = request.getRequestURI();  
        UserVO loginUser = (UserVO)request.getSession().getAttribute("__LOGIN_USER__");
        String auth = null;
        if(loginUser != null) {
        	auth = loginUser.getAutr();
        }
        if (uri.startsWith("/api/")) {
            return true; // 모든 /api/** 요청은 세션 없이 통과
        }
        
        //TODO 예외 걸렸을 때 처리할 페이지 혹은 화면에 보여줄 메시지
        //관리자 페이지는 관리자(1001)만 접근가능
        if (uri.startsWith("/admin") && !"1001".equals(auth)) {
            response.sendRedirect("/not-admin");
            return false;
        }
        //광고주 고유 권한은 광고주, 관리자(1004, 1001) 접근가능
        if(uri.startsWith("/adv") && (!"1001".equals(auth) && !"1004".equals(auth))) {
        	return false;
        }
        //블로거 고유권한은 블로거, 관리자(1002, 1003, 1001) 접근 가능
        if((uri.startsWith("/blog") || uri.startsWith("/blgr")) 
            && (!"1002".equals(auth) && !"1003".equals(auth) && !"1004".equals(auth))) {
        	return false;
        }
		
        return true;
	}
	
}
