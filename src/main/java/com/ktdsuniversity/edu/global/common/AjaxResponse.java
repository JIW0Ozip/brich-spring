package com.ktdsuniversity.edu.global.common;

import java.util.Map;

public class AjaxResponse {

	private Map<String, Object> error;
	private Object body;

	public Map<String, Object> getError() {
		return this.error;
	}

	public void setError(Map<String, Object> error) {
		this.error = error;
	}

	public Object getBody() {
		return this.body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "AjaxResponse [error=" + error + ", body=" + body + "]";
	}
}
