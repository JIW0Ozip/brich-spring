package com.ktdsuniversity.edu.domain.campaign.vo.response;

import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.PostReturnHistoryVO;
import com.ktdsuniversity.edu.domain.file.vo.FileVO;

public class ResponseDenyHistoryVO extends PostReturnHistoryVO {

	List<FileVO> retnFile;
	List<FileVO> submitFile;
	
	public List<FileVO> getRetnFile() {
		return this.retnFile;
	}
	public void setRetnFile(List<FileVO> retnFile) {
		this.retnFile = retnFile;
	}
	public List<FileVO> getSubmitFile() {
		return this.submitFile;
	}
	public void setSubmitFile(List<FileVO> submitFile) {
		this.submitFile = submitFile;
	}
	
	@Override
	public String toString() {
		return "ResponseDenyHistoryVO [retnFile=" + retnFile + ", submitFile=" + submitFile + super.toString() + "]";
	}
}
