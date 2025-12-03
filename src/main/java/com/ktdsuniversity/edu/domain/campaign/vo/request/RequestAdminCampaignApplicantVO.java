package com.ktdsuniversity.edu.domain.campaign.vo.request;

import com.ktdsuniversity.edu.global.common.AbstractSearchVO;

public class RequestAdminCampaignApplicantVO extends AbstractSearchVO {

	private String cmpnPstAdptId;
	private String cmpnId;
	private String adptYn;
	private String usrId;
	
	private String order;
	private String sortCol;
	private String searchKeyword;
	
	public String getCmpnPstAdptId() {
		return this.cmpnPstAdptId;
	}
	public void setCmpnPstAdptId(String cmpnPstAdptId) {
		this.cmpnPstAdptId = cmpnPstAdptId;
	}
	public String getCmpnId() {
		return this.cmpnId;
	}
	public void setCmpnId(String cmpnId) {
		this.cmpnId = cmpnId;
	}
	public String getAdptYn() {
		return this.adptYn;
	}
	public void setAdptYn(String adptYn) {
		this.adptYn = adptYn;
	}
	public String getUsrId() {
		return this.usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getOrder() {
		return this.order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSortCol() {
		return this.sortCol;
	}
	public void setSortCol(String sortCol) {
		this.sortCol = sortCol;
	}
	public String getSearchKeyword() {
		return this.searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	
}
