package com.ktdsuniversity.edu.domain.campaign.vo.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class RequestPostSubmitVO {
	
	private String postTitle;
	
	private String postUrl;
	
	private String cmpnId;
	
	private String blgId;
	
	private String postSubmitChgCn;
	
	private List<MultipartFile> file;
	
	private String postFlGrpId;
	
	public String getPostFlGrpId() {
		return this.postFlGrpId;
	}

	public void setPostFlGrpId(String postFlGrpId) {
		this.postFlGrpId = postFlGrpId;
	}

	public List<MultipartFile> getFile() {
		return this.file;
	}

	public void setFile(List<MultipartFile> file) {
		this.file = file;
	}

	public String getPostSubmitChgCn() {
		return postSubmitChgCn;
	}

	public void setPostSubmitChgCn(String postSubmitChgCn) {
		this.postSubmitChgCn = postSubmitChgCn;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostUrl() {
		return postUrl;
	}

	public void setPostUrl(String postUrl) {
		this.postUrl = postUrl;
	}

	public String getCmpnId() {
		return cmpnId;
	}

	public void setCmpnId(String cmpnId) {
		this.cmpnId = cmpnId;
	}

	public String getBlgId() {
		return blgId;
	}

	public void setBlgId(String blgId) {
		this.blgId = blgId;
	}

	
	@Override
	public String toString() {
		return "RequestPostSubmitVO [postTitle=" + postTitle + ", postUrl=" + postUrl + ", cmpnId=" + cmpnId
				+ ", blgId=" + blgId + ", postSubmitChgCn=" + postSubmitChgCn + ", file=" + file + ", postFlGrpId="
				+ postFlGrpId + "]";
	}

}
