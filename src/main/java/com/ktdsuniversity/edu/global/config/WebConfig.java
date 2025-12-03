package com.ktdsuniversity.edu.global.config;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ktdsuniversity.edu.global.interceptors.AccessControlInterceptor;
import com.ktdsuniversity.edu.global.interceptors.CheckSessionInterceptor;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CheckSessionInterceptor()).addPathPatterns(List.of("/**"))
				.excludePathPatterns(List.of(
						"/js/**",
						"/css/**",
						"/img/**",
						"/file/**",
						"/terms/**",
						"/choose-role",
						"/find/**",
						"/duplicate-id/check",
						"/regist/**",
						"/reset/**",
						"/email/**",
						"/campaignmain",
						"/campaigndetail/**",
						"/login",
						"/api/**"
					)); //비회원 기능, 회원가입, 로그인 등은 로그인이 안되어도 접근 되어야함

		//고유권한에 대한 접근을 차단하기 위한 인터셉터
		registry.addInterceptor(new AccessControlInterceptor())
				.addPathPatterns(List.of("/**"))
				.excludePathPatterns(List.of("/api/**"));// 해당url일때만 얘가 동작한다.
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
		registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
		registry.addResourceHandler("/checkout.html").addResourceLocations("classpath:/static/templates/");		
		registry.addResourceHandler("/fail.html").addResourceLocations("classpath:/static/templates/");		
		registry.addResourceHandler("/success.html").addResourceLocations("classpath:/static/templates/");		
	
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
}
