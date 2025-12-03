package com.ktdsuniversity.edu.domain.campaign.vo;

import java.util.List;

public class AdminCampaignCategoryVO {
	
	private String adminId;

	private int level;
	
	private String cdId;
	private String cdNm;
	private String prntCdId;
	private String useYn;
	private int srt;
	private String crtDt;
	private String crtr;
	private String updtDt;
	private String mttr;
	private String dltYn;
	
	private String prntCdNm;
	
	// 노출 순서가 변경된 결과대로 cdId를 담은 리스트
	private List<String> orderedCdIdList;
	
	public String getAdminId() {
		return this.adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getCdId() {
		return this.cdId;
	}

	public void setCdId(String cdId) {
		this.cdId = cdId;
	}

	public String getCdNm() {
		return this.cdNm;
	}

	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
	}

	public String getPrntCdId() {
		return this.prntCdId;
	}

	public void setPrntCdId(String prntCdId) {
		this.prntCdId = prntCdId;
	}

	public String getUseYn() {
		return this.useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public int getSrt() {
		return this.srt;
	}

	public void setSrt(int srt) {
		this.srt = srt;
	}

	public String getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(String crtDt) {
		this.crtDt = crtDt;
	}

	public String getCrtr() {
		return this.crtr;
	}

	public void setCrtr(String crtr) {
		this.crtr = crtr;
	}

	public String getUpdtDt() {
		return this.updtDt;
	}

	public void setUpdtDt(String updtDt) {
		this.updtDt = updtDt;
	}

	public String getMttr() {
		return this.mttr;
	}

	public void setMttr(String mttr) {
		this.mttr = mttr;
	}

	public String getDltYn() {
		return this.dltYn;
	}

	public void setDltYn(String dltYn) {
		this.dltYn = dltYn;
	}

	public String getPrntCdNm() {
		return this.prntCdNm;
	}

	public void setPrntCdNm(String prntCdNm) {
		this.prntCdNm = prntCdNm;
	}

	public List<String> getOrderedCdIdList() {
		return this.orderedCdIdList;
	}

	public void setOrderedCdIdList(List<String> orderedCdIdList) {
		this.orderedCdIdList = orderedCdIdList;
	}
	
}
