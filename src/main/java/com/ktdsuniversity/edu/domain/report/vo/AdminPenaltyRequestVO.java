package com.ktdsuniversity.edu.domain.report.vo;

public class AdminPenaltyRequestVO {
	
	private String pnltId;
	private String rptId;
	private String adminId;
	private String rptedUsrId;
	private String penaltyOption;
	private String penaltyKeyword;
	
	private String usrId;
	
	public String getPnltId() {
		return this.pnltId;
	}
	public void setPnltId(String pnltId) {
		this.pnltId = pnltId;
	}
	public String getRptId() {
		return this.rptId;
	}
	public void setRptId(String rptId) {
		this.rptId = rptId;
	}
	public String getAdminId() {
		return this.adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getRptedUsrId() {
		return this.rptedUsrId;
	}
	public void setRptedUsrId(String rptedUsrId) {
		this.rptedUsrId = rptedUsrId;
	}
	public String getPenaltyOption() {
		return this.penaltyOption;
	}
	public void setPenaltyOption(String penaltyOption) {
		this.penaltyOption = penaltyOption;
	}
	public String getPenaltyKeyword() {
		return this.penaltyKeyword;
	}
	public void setPenaltyKeyword(String penaltyKeyword) {
		this.penaltyKeyword = penaltyKeyword;
	}
	public String getUsrId() {
		return this.usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	
}
