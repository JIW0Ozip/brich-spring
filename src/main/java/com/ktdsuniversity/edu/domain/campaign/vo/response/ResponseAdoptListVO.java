package com.ktdsuniversity.edu.domain.campaign.vo.response;

import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignPostAdoptVO;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;

public class ResponseAdoptListVO {

	private List<ResponseAdoptVO> adoptList;
	private ResponseCampaignVO campaignInfo;
	private String cmpnCdNm;
	
	public List<ResponseAdoptVO> getAdoptList() {
		return this.adoptList;
	}
	public void setAdoptList(List<ResponseAdoptVO> adoptList) {
		this.adoptList = adoptList;
	}
	public ResponseCampaignVO getCampaignInfo() {
		return this.campaignInfo;
	}
	public void setCampaignInfo(ResponseCampaignVO campaignInfo) {
		this.campaignInfo = campaignInfo;
	}
	public String getCmpnCdNm() {
		return this.cmpnCdNm;
	}
	public void setCmpnCdNm(String cmpnCdNm) {
		this.cmpnCdNm = cmpnCdNm;
	}
	
	@Override
	public String toString() {
		return "ResponseAdoptListVO [adoptList=" + adoptList + ", campaignInfo=" + campaignInfo + ", cmpnCdNm="
				+ cmpnCdNm + "]";
	}
}
