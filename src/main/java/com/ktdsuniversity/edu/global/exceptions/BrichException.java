package com.ktdsuniversity.edu.global.exceptions;

public class BrichException extends RuntimeException{
	
	private static final long serialVersionUID = -254738828445974509L;
	
	private String viewName;
	private String modelKey;
	private Object modelValue;
	
	public BrichException(String message) {
		super(message);
	}
	
	public BrichException(String message, String viewName) {
		super(message);
		this.viewName = viewName;
	}
	
	public BrichException(String message, String viewName,
								String modelKey, Object modelValue) {
		super(message);
		this.viewName = viewName;
		this.modelKey = modelKey;
		this.modelValue = modelValue;
	}
	
	public String getViewName() {
		return this.viewName;
	}
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	public String getModelKey() {
		return this.modelKey;
	}
	public void setModelKey(String modelKey) {
		this.modelKey = modelKey;
	}
	public Object getModelValue() {
		return this.modelValue;
	}
	public void setModelValue(Object modelValue) {
		this.modelValue = modelValue;
	}

}
