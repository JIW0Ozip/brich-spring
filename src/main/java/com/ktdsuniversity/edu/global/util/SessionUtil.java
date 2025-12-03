package com.ktdsuniversity.edu.global.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ktdsuniversity.edu.domain.user.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionUtil {
	
	public static UserVO getLoginObject() {
		ServletRequestAttributes requestAttributes = 
				(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();	
		HttpServletRequest request = requestAttributes.getRequest();
		HttpSession httpSession = request.getSession();
		
		UserVO loginUser = (UserVO)httpSession.getAttribute("__LOGIN_USER__");
		
		return loginUser;
	}
}
