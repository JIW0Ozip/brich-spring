package com.ktdsuniversity.edu.domain.inqr.vo.response;

import java.util.List;

import com.ktdsuniversity.edu.domain.file.vo.FileVO;

public class ResponseInqrVO {
	
	private String inqrTitle;
	
	private String inqrCn;
	
	private String inqrCtgNm;
	
	private String ansrCn;
	
	private String inqrFlGrpId;
	
	private String ansrFlGrpId;
	
	//문의할때 넣은 파일 
	private List<FileVO> myFile;
	
	//답변 파일
	private List<FileVO> answerFile;

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

	public String getInqrCtgNm() {
		return this.inqrCtgNm;
	}

	public void setInqrCtgNm(String inqrCtgNm) {
		this.inqrCtgNm = inqrCtgNm;
	}

	public String getAnsrCn() {
		return this.ansrCn;
	}

	public void setAnsrCn(String ansrCn) {
		this.ansrCn = ansrCn;
	}

	public String getInqrFlGrpId() {
		return this.inqrFlGrpId;
	}

	public void setInqrFlGrpId(String inqrFlGrpId) {
		this.inqrFlGrpId = inqrFlGrpId;
	}

	public String getAnsrFlGrpId() {
		return this.ansrFlGrpId;
	}

	public void setAnsrFlGrpId(String ansrFlGrpId) {
		this.ansrFlGrpId = ansrFlGrpId;
	}

	public List<FileVO> getMyFile() {
		return this.myFile;
	}

	public void setMyFile(List<FileVO> myFile) {
		this.myFile = myFile;
	}

	public List<FileVO> getAnswerFile() {
		return this.answerFile;
	}

	public void setAnswerFile(List<FileVO> answerFile) {
		this.answerFile = answerFile;
	}

	@Override
	public String toString() {
		return "ResponseInqrVO [inqrTitle=" + inqrTitle + ", inqrCn=" + inqrCn + ", inqrCtgNm=" + inqrCtgNm
				+ ", ansrCn=" + ansrCn + ", inqrFlGrpId=" + inqrFlGrpId + ", ansrFlGrpId=" + ansrFlGrpId + ", myFile="
				+ myFile + ", answerFile=" + answerFile + "]";
	}

}
