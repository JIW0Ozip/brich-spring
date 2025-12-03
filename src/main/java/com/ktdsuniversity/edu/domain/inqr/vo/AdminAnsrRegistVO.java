package com.ktdsuniversity.edu.domain.inqr.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class AdminAnsrRegistVO {
	
	private String inqrId;
	private String ansrUsrId;
	private String ansrCn;
	
	private String ansrFlGrpId;
	private List<MultipartFile> files;
	
	public String getInqrId() {
		return this.inqrId;
	}
	public void setInqrId(String inqrId) {
		this.inqrId = inqrId;
	}
	public String getAnsrUsrId() {
		return this.ansrUsrId;
	}
	public void setAnsrUsrId(String ansrUsrId) {
		this.ansrUsrId = ansrUsrId;
	}
	public String getAnsrCn() {
		return this.ansrCn;
	}
	public void setAnsrCn(String ansrCn) {
		this.ansrCn = ansrCn;
	}
	public String getAnsrFlGrpId() {
		return this.ansrFlGrpId;
	}
	public void setAnsrFlGrpId(String ansrFlGrpId) {
		this.ansrFlGrpId = ansrFlGrpId;
	}
	public List<MultipartFile> getFiles() {
		return this.files;
	}
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
	
}
