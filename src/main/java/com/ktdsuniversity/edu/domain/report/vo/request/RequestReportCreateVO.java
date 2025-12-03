package com.ktdsuniversity.edu.domain.report.vo.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class RequestReportCreateVO {

    private String rptId;

    private String rptrUsrId;
    
    private String rptedUsrId;

    private String rptTitle;

    private String rptRsn;
    
    private String rptCn;

    private String rptFlGrpId;
    
	private List<MultipartFile> file;

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

	public String getRptRsn() {
		return this.rptRsn;
	}

	public void setRptRsn(String rptRsn) {
		this.rptRsn = rptRsn;
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

	public List<MultipartFile> getFile() {
		return this.file;
	}

	public void setFile(List<MultipartFile> file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "RequestReportCreateVO [rptId=" + rptId + ", rptrUsrId=" + rptrUsrId + ", rptedUsrId=" + rptedUsrId
				+ ", rptTitle=" + rptTitle + ", rptRsn=" + rptRsn + ", rptCn=" + rptCn + ", rptFlGrpId=" + rptFlGrpId
				+ ", file=" + file + "]";
	}

}
