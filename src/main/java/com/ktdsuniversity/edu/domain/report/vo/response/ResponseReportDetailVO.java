package com.ktdsuniversity.edu.domain.report.vo.response;

import java.util.List;

import com.ktdsuniversity.edu.domain.file.vo.FileVO;

public class ResponseReportDetailVO {
	
	private String rptId;
	
	private String rptrUsrId;
	
	private String rptedUsrNm;
	
	private String rptedUsrCmpny;
	
	private String rptedUsrId;
	
	private String rptTitle;
	
	private String rptRsnNm;
	
	private String rptCn;
	
    private String rptFlGrpId;
	
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

	public String getRptedUsrNm() {
		return this.rptedUsrNm;
	}

	public void setRptedUsrNm(String rptedUsrNm) {
		this.rptedUsrNm = rptedUsrNm;
	}

	public String getRptedUsrCmpny() {
		return this.rptedUsrCmpny;
	}

	public void setRptedUsrCmpny(String rptedUsrCmpny) {
		this.rptedUsrCmpny = rptedUsrCmpny;
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

	public String getRptRsnNm() {
		return this.rptRsnNm;
	}

	public void setRptRsnNm(String rptRsnNm) {
		this.rptRsnNm = rptRsnNm;
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

	public List<FileVO> getFileList() {
		return this.fileList;
	}

	public void setFileList(List<FileVO> fileList) {
		this.fileList = fileList;
	}

	@Override
	public String toString() {
		return "ResponseReportDetailVO [rptId=" + rptId + ", rptrUsrId=" + rptrUsrId + ", rptedUsrNm=" + rptedUsrNm
				+ ", rptedUsrCmpny=" + rptedUsrCmpny + ", rptedUsrId=" + rptedUsrId + ", rptTitle=" + rptTitle
				+ ", rptRsnNm=" + rptRsnNm + ", rptCn=" + rptCn + ", rptFlGrpId=" + rptFlGrpId + ", fileList="
				+ fileList + "]";
	}
	
}
