package com.ktdsuniversity.edu.domain.campaign.vo.response;

import java.util.List;

import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public class ResponseCampaignwriteVO {

	private List<CommonCodeVO> categoryList;
	private List<CommonCodeVO> doAndCityList;
	private String personPrice;
	
	public List<CommonCodeVO> getCategoryList() {
		return this.categoryList;
	}
	public void setCategoryList(List<CommonCodeVO> categoryList) {
		this.categoryList = categoryList;
	}
	public List<CommonCodeVO> getDoAndCityList() {
		return this.doAndCityList;
	}
	public void setDoAndCityList(List<CommonCodeVO> doAndCityList) {
		this.doAndCityList = doAndCityList;
	}
	public String getPersonPrice() {
		return this.personPrice;
	}
	public void setPersonPrice(String personPrice) {
		this.personPrice = personPrice;
	}
	
	@Override
	public String toString() {
		return "ResponseCampaignwriteVO [categoryList=" + categoryList + ", doAndCityList=" + doAndCityList
				+ ", personPrice=" + personPrice + "]";
	}
}
