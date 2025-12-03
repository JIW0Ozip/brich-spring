package com.ktdsuniversity.edu.global.exceptions.handler;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.global.common.AjaxResponse;
import com.ktdsuniversity.edu.global.exceptions.AjaxException;
import com.ktdsuniversity.edu.global.exceptions.BrichException;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(AjaxException.class)
	@ResponseBody
	public AjaxResponse sendErrorMessage(AjaxException ae) {
		
		logger.error(ae.getMessage(), ae);
		
		String message = ae.getMessage();
		HttpStatus httpStatus = ae.getStatus();
		Object error = ae.getError();
		
		AjaxResponse errorResponse = new AjaxResponse();
		
		Map<String, Object> errorMap = new HashMap<>();
		errorMap.put("message", message);
		errorMap.put("status", httpStatus);
		errorMap.put("error", error);
		
		errorResponse.setError(errorMap);
		
		
		return errorResponse;
	}
	
	@ExceptionHandler(BrichException.class) 
	public String viewErrorPage(BrichException hse, Model model) {
		
		logger.error(hse.getMessage(), hse);
		logger.info("{}", hse.getModelValue());
		logger.info("{} ㅇㅇ", hse.getViewName());
		model.addAttribute("errorMessage", hse.getMessage());
		
		if(hse.getModelKey() != null) {
			model.addAttribute(hse.getModelKey(), hse.getModelValue());
		}
		
		return hse.getViewName();
	}
	
	@ExceptionHandler(RuntimeException.class) 
	public String viewRuntimeExceptionPage(
			RuntimeException re, Model model) {
		
		logger.error(re.getMessage(), re);
		
		model.addAttribute("errorMessage", "예기치 않은 에러가 발생했습니다.");
		
		return "error/500";
	}
}
