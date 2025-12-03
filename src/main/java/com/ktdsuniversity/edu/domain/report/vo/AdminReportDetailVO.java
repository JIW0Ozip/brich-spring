package com.ktdsuniversity.edu.domain.report.vo;

import java.util.List;

import com.ktdsuniversity.edu.domain.file.vo.FileVO;

public class AdminReportDetailVO {
	
	private String rptId;
	private String rptrUsrId;
	private String rptedUsrId;
	private String rptTitle;
	private String rptCn;
	private String rptFlGrpId;
	private String prcnYn;
	private String crtDt;
	
	private String prcnDt;
	
	private String rptrLogId;
	private String rptedLogId;

	private String cdId;
	private String cdNm;
	
	private List<FileVO> fileList;
	
	public String getRptId() {
		return this.rptId;
	}
	public void setRptId(String rptId) {
		this.rptId = rptId;
	}
	public String getRptrUsrId() {
		return this.rptrUsrId;
	}
	public void setRptrUsrId(String rptrUsrId) {
		this.rptrUsrId = rptrUsrId;
	}
	public String getRptedUsrId() {
		return this.rptedUsrId;
	}
	public void setRptedUsrId(String rptedUsrId) {
		this.rptedUsrId = rptedUsrId;
	}
	public String getRptTitle() {
		return this.rptTitle;
	}
	public void setRptTitle(String rptTitle) {
		this.rptTitle = rptTitle;
	}
	public String getRptCn() {
		return this.rptCn;
	}
	public void setRptCn(String rptCn) {
		this.rptCn = rptCn;
	}
	public String getRptFlGrpId() {
		return this.rptFlGrpId;
	}
	public void setRptFlGrpId(String rptFlGrpId) {
		this.rptFlGrpId = rptFlGrpId;
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
	public String getPrcnDt() {
		return this.prcnDt;
	}
	public void setPrcnDt(String prcnDt) {
		this.prcnDt = prcnDt;
	}
	public String getRptrLogId() {
		return this.rptrLogId;
	}
	public void setRptrLogId(String rptrLogId) {
		this.rptrLogId = rptrLogId;
	}
	public String getRptedLogId() {
		return this.rptedLogId;
	}
	public void setRptedLogId(String rptedLogId) {
		this.rptedLogId = rptedLogId;
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
	public List<FileVO> getFileList() {
		return this.fileList;
	}
	public void setFileList(List<FileVO> fileList) {
		this.fileList = fileList;
	}
	
}
