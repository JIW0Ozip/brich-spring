package com.ktdsuniversity.edu.domain.user.vo.request;

public class RequestUserFindIdVO {
	
	private String eml;
	
	private String nm;

	public String getEml() {
		return this.eml;
	}

	public void setEml(String eml) {
		this.eml = eml;
	}

	public String getNm() {
		return this.nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}

	@Override
	public String toString() {
		return "RequestUserFindIdVO [eml=" + eml + ", nm=" + nm + ", toString()=" + super.toString() + "]";
	}

}
