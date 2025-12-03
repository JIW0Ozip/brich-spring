package com.ktdsuniversity.edu.domain.campaign.vo;

import java.util.List;

public class ResponseExpireSoonListVO {

	private List<CampaignVO> list;
	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<CampaignVO> getList() {
		return this.list;
	}

	public void setList(List<CampaignVO> list) {
		this.list = list;
	}
	
	
}
