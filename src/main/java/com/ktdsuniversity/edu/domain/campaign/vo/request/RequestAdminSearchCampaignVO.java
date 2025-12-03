package com.ktdsuniversity.edu.domain.campaign.vo.request;

import com.ktdsuniversity.edu.global.common.AbstractSearchVO;

public class RequestAdminSearchCampaignVO extends AbstractSearchVO {
	
	private String category;
	private String sortBy;
	private String searchKeyword;
	private String cmpnId;
	
	public String getCategory() {
		return this.category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSortBy() {
		return this.sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public String getSearchKeyword() {
		return this.searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public String getCmpnId() {
		return this.cmpnId;
	}
	public void setCmpnId(String cmpnId) {
		this.cmpnId = cmpnId;
	}
	
	@Override
	public String toString() {
		return "RequestAdminSearchCampaignVO [category=" + category + ", sortBy=" + sortBy + ", searchKeyword="
				+ searchKeyword + ", cmpnId=" + cmpnId + "]";
	}
	
}
