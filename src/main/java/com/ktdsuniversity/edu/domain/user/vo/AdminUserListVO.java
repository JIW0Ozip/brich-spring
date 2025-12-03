package com.ktdsuniversity.edu.domain.user.vo;

/**
 * 회원 관리 - 회원 리스트
 */
public class AdminUserListVO {
	
    private String usrId;
	private String logId;
	private String nm;
	private String autr;
	private String rcntLgnScsDt;	// 최근 로그인 성공 일시
	private int pnltCnt;			// 징계 횟수
	
	private String blgAddrs;		// 블로그 주소
	private String rcntBlgCrtfctnDt; // 최근 블로그 인증 일시
	private String sbscrptnExprsDt;	// 구독 만료일
	
	private int cmpnAllCnt;		// 총 캠페인 진행 수
	private int cmpnPrgrssCnt;	// 진행 중인 캠페인 수
	private String registAcpt;	// 가입 승인 여부
	
	private String crtDt;	// 생성일
	
	public String getUsrId() {
		return this.usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getLogId() {
		return this.logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public String getNm() {
		return this.nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}
	public String getAutr() {
		return this.autr;
	}
	public void setAutr(String autr) {
		this.autr = autr;
	}
	public String getRcntLgnScsDt() {
		return this.rcntLgnScsDt;
	}
	public void setRcntLgnScsDt(String rcntLgnScsDt) {
		this.rcntLgnScsDt = rcntLgnScsDt;
	}
	public int getPnltCnt() {
		return this.pnltCnt;
	}
	public void setPnltCnt(int pnltCnt) {
		this.pnltCnt = pnltCnt;
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
	public String getSbscrptnExprsDt() {
		return this.sbscrptnExprsDt;
	}
	public void setSbscrptnExprsDt(String sbscrptnExprsDt) {
		this.sbscrptnExprsDt = sbscrptnExprsDt;
	}
	public int getCmpnAllCnt() {
		return this.cmpnAllCnt;
	}
	public void setCmpnAllCnt(int cmpnAllCnt) {
		this.cmpnAllCnt = cmpnAllCnt;
	}
	public int getCmpnPrgrssCnt() {
		return this.cmpnPrgrssCnt;
	}
	public void setCmpnPrgrssCnt(int cmpnPrgrssCnt) {
		this.cmpnPrgrssCnt = cmpnPrgrssCnt;
	}
	public String getRegistAcpt() {
		return this.registAcpt;
	}
	public void setRegistAcpt(String registAcpt) {
		this.registAcpt = registAcpt;
	}
	public String getCrtDt() {
		return this.crtDt;
	}
	public void setCrtDt(String crtDt) {
		this.crtDt = crtDt;
	}
	
}
