package com.ktdsuniversity.edu.domain.report.vo.response;

public class ResponseMyReportInfoVO {

	private String rptId;
	
	private String nm;
	
	private String rptTitle;
	
	private String cmpny;
	
	private String prcnYn;
	
	private String crtDt;

	public String getRptId() {
		return this.rptId;
	}

	public void setRptId(String rptId) {
		this.rptId = rptId;
	}

	public String getNm() {
		return this.nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}

	public String getRptTitle() {
		return this.rptTitle;
	}

	public void setRptTitle(String rptTitle) {
		this.rptTitle = rptTitle;
	}

	public String getCmpny() {
		return this.cmpny;
	}

	public void setCmpny(String cmpny) {
		this.cmpny = cmpny;
	}

	public String getPrcnYn() {
		return this.prcnYn;
	}

	public void setPrcnYn(String prcnYn) {
		this.prcnYn = prcnYn;
	}

	public String getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(String crtDt) {
		this.crtDt = crtDt;
	}

	@Override
	public String toString() {
		return "ResponseMyReportInfoVO [rptId=" + rptId + ", nm=" + nm + ", rptTitle=" + rptTitle + ", cmpny=" + cmpny
				+ ", prcnYn=" + prcnYn + ", crtDt=" + crtDt + "]";
	}
	
	
}
