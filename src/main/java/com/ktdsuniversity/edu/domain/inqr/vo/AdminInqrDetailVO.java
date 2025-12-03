package com.ktdsuniversity.edu.domain.inqr.vo;

import java.util.List;

import com.ktdsuniversity.edu.domain.file.vo.FileVO;

public class AdminInqrDetailVO {
	
	private InqrVO inqrVO;
	
	private String logId;
	
	private String cdId;
	private String cdNm;
	
	private String ansrYn;
	private String ansrDt;
	
	private List<FileVO> inqrFileList;
	private List<FileVO> ansrFileList;
	
	public InqrVO getInqrVO() {
		return this.inqrVO;
	}
	public void setInqrVO(InqrVO inqrVO) {
		this.inqrVO = inqrVO;
	}
	public String getLogId() {
		return this.logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
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
	public String getAnsrYn() {
		return this.ansrYn;
	}
	public void setAnsrYn(String ansrYn) {
		this.ansrYn = ansrYn;
	}
	public String getAnsrDt() {
		return this.ansrDt;
	}
	public void setAnsrDt(String ansrDt) {
		this.ansrDt = ansrDt;
	}
	public List<FileVO> getInqrFileList() {
		return this.inqrFileList;
	}
	public void setInqrFileList(List<FileVO> inqrFileList) {
		this.inqrFileList = inqrFileList;
	}
	public List<FileVO> getAnsrFileList() {
		return this.ansrFileList;
	}
	public void setAnsrFileList(List<FileVO> ansrFileList) {
		this.ansrFileList = ansrFileList;
	}
	
}
