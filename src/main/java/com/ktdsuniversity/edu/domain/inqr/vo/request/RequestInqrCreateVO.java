package com.ktdsuniversity.edu.domain.inqr.vo.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class RequestInqrCreateVO {
	
    private String inqrId;

    private String inqrUsrId;

    private String inqrTitle;

    private String inqrCn;

    private String inqrCtg;

    private String inqrFlGrpId;
    
	private List<MultipartFile> file;

	public String getInqrId() {
		return this.inqrId;
	}

	public void setInqrId(String inqrId) {
		this.inqrId = inqrId;
	}

	public String getInqrUsrId() {
		return this.inqrUsrId;
	}

	public void setInqrUsrId(String inqrUsrId) {
		this.inqrUsrId = inqrUsrId;
	}

	public String getInqrTitle() {
		return this.inqrTitle;
	}

	public void setInqrTitle(String inqrTitle) {
		this.inqrTitle = inqrTitle;
	}

	public String getInqrCn() {
		return this.inqrCn;
	}

	public void setInqrCn(String inqrCn) {
		this.inqrCn = inqrCn;
	}

	public String getInqrCtg() {
		return this.inqrCtg;
	}

	public void setInqrCtg(String inqrCtg) {
		this.inqrCtg = inqrCtg;
	}

	public String getInqrFlGrpId() {
		return this.inqrFlGrpId;
	}

	public void setInqrFlGrpId(String inqrFlGrpId) {
		this.inqrFlGrpId = inqrFlGrpId;
	}

	public List<MultipartFile> getFile() {
		return this.file;
	}

	public void setFile(List<MultipartFile> file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "RequestInqrCreateVO [inqrId=" + inqrId + ", inqrUsrId=" + inqrUsrId + ", inqrTitle=" + inqrTitle
				+ ", inqrCn=" + inqrCn + ", inqrCtg=" + inqrCtg + ", inqrFlGrpId=" + inqrFlGrpId + ", file=" + file
				+ "]";
	}
	

    
}
