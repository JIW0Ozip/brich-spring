package com.ktdsuniversity.edu.global.exceptions;

import org.springframework.http.HttpStatus;

public class AjaxException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	private String message; 
	private HttpStatus status;
	private Object error;
	
	public AjaxException(String message) {
		this.message = message;
	}
	
	public AjaxException(String message, HttpStatus status) {
		this.message = message;
		this.status = status;
	}
	
	public AjaxException(String message, HttpStatus status, Object error) {
		this.message = message;
		this.status = status;
		this.error = error;
	}

	public String getMessage() {
		return this.message;
	}


	public HttpStatus getStatus() {
		return this.status;
	}

	public Object getError() {
		return this.error;
	}

}
