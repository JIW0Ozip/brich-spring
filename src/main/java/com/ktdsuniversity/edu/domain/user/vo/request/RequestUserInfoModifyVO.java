package com.ktdsuniversity.edu.domain.user.vo.request;

import java.util.List;

public class RequestUserInfoModifyVO {
	
	private String usrId;
	
	//지역 리스트
	private List<String> area;

	//카테고리 리스트
	private List<String> cdIdList;

	public String getUsrId() {
		return this.usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	public List<String> getArea() {
		return this.area;
	}

	public void setArea(List<String> area) {
		this.area = area;
	}

	public List<String> getCdIdList() {
		return this.cdIdList;
	}

	public void setCdIdList(List<String> cdIdList) {
		this.cdIdList = cdIdList;
	}

	@Override
	public String toString() {
		return "RequestUserInfoModifyVO [usrId=" + usrId + ", area=" + area + ", cdIdList=" + cdIdList + "]";
	}


}
