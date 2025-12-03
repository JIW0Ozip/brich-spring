package com.ktdsuniversity.edu.domain.user.vo;

import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;

public class AdminBloggerDetailVO extends AdminUserBaseInfoVO {
	
	private int cmpnProgressCnt;
    private int cmpnCompletedCnt;
    private List<CampaignVO> cmpnProgressList;
    private List<CampaignVO> cmpnCompletedList;
	
	// 블로거 전용 데이터
	private String sbscrptnExprsDt;
    private String blgAddrs;
    private String rcntBlgCrtfctnDt;
    private List<AdminBloggerAreaInfoVO> usrAr;
    private List<AdminBloggerCategoryInfoVO> usrBlgCtg;
    private int avrgVstrCnt;
    private int blgNghbrCnt;
    private int scrpCnt;

    private List<String> checkedBlgCtg;
    
	public int getCmpnProgressCnt() {
		return this.cmpnProgressCnt;
	}
	public void setCmpnProgressCnt(int cmpnProgressCnt) {
		this.cmpnProgressCnt = cmpnProgressCnt;
	}
	public int getCmpnCompletedCnt() {
		return this.cmpnCompletedCnt;
	}
	public void setCmpnCompletedCnt(int cmpnCompletedCnt) {
		this.cmpnCompletedCnt = cmpnCompletedCnt;
	}
	public List<CampaignVO> getCmpnProgressList() {
		return this.cmpnProgressList;
	}
	public void setCmpnProgressList(List<CampaignVO> cmpnProgressList) {
		this.cmpnProgressList = cmpnProgressList;
	}
	public List<CampaignVO> getCmpnCompletedList() {
		return this.cmpnCompletedList;
	}
	public void setCmpnCompletedList(List<CampaignVO> cmpnCompletedList) {
		this.cmpnCompletedList = cmpnCompletedList;
	}
	public String getSbscrptnExprsDt() {
		return this.sbscrptnExprsDt;
	}
	public void setSbscrptnExprsDt(String sbscrptnExprsDt) {
		this.sbscrptnExprsDt = sbscrptnExprsDt;
	}
	public String getBlgAddrs() {
		return this.blgAddrs;
	}
	public void setBlgAddrs(String blgAddrs) {
		this.blgAddrs = blgAddrs;
	}
	public String getRcntBlgCrtfctnDt() {
		return this.rcntBlgCrtfctnDt;
	}
	public void setRcntBlgCrtfctnDt(String rcntBlgCrtfctnDt) {
		this.rcntBlgCrtfctnDt = rcntBlgCrtfctnDt;
	}
	public List<AdminBloggerAreaInfoVO> getUsrAr() {
		return this.usrAr;
	}
	public void setUsrAr(List<AdminBloggerAreaInfoVO> usrAr) {
		this.usrAr = usrAr;
	}
	public List<AdminBloggerCategoryInfoVO> getUsrBlgCtg() {
		return this.usrBlgCtg;
	}
	public void setUsrBlgCtg(List<AdminBloggerCategoryInfoVO> usrBlgCtg) {
		this.usrBlgCtg = usrBlgCtg;
	}
	public int getAvrgVstrCnt() {
		return this.avrgVstrCnt;
	}
	public void setAvrgVstrCnt(int avrgVstrCnt) {
		this.avrgVstrCnt = avrgVstrCnt;
	}
	public int getBlgNghbrCnt() {
		return this.blgNghbrCnt;
	}
	public void setBlgNghbrCnt(int blgNghbrCnt) {
		this.blgNghbrCnt = blgNghbrCnt;
	}
	public int getScrpCnt() {
		return this.scrpCnt;
	}
	public void setScrpCnt(int scrpCnt) {
		this.scrpCnt = scrpCnt;
	}
	public List<String> getCheckedBlgCtg() {
		return this.checkedBlgCtg;
	}
	public void setCheckedBlgCtg(List<String> checkedBlgCtg) {
		this.checkedBlgCtg = checkedBlgCtg;
	}
    
}
