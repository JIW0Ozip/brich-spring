package com.ktdsuniversity.edu.domain.campaign.vo.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class RequestDenyVO {

	private String postRtrnHistId;
	private String cmpnPstAdptId;
	private String cmpnId;
	private String advId;
	private String reason;
	private String pstDdln;
	private String fileGroupId;
	private List<MultipartFile> file;
	
	
	public String getPostRtrnHistId() {
		return this.postRtrnHistId;
	}
	public void setPostRtrnHistId(String postRtrnHistId) {
		this.postRtrnHistId = postRtrnHistId;
	}
	public String getCmpnPstAdptId() {
		return this.cmpnPstAdptId;
	}
	public void setCmpnPstAdptId(String cmpnPstAdptId) {
		this.cmpnPstAdptId = cmpnPstAdptId;
	}
	public String getCmpnId() {
		return this.cmpnId;
	}
	public void setCmpnId(String cmpnId) {
		this.cmpnId = cmpnId;
	}
	public String getAdvId() {
		return this.advId;
	}
	public void setAdvId(String advId) {
		this.advId = advId;
	}
	public String getReason() {
		return this.reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getPstDdln() {
		return this.pstDdln;
	}
	public void setPstDdln(String pstDdln) {
		this.pstDdln = pstDdln;
	}
	public String getFileGroupId() {
		return this.fileGroupId;
	}
	public void setFileGroupId(String fileGroupId) {
		this.fileGroupId = fileGroupId;
	}
	public List<MultipartFile> getFile() {
		return this.file;
	}
	public void setFile(List<MultipartFile> file) {
		this.file = file;
	}
	
	@Override
	public String toString() {
		return "RequestDenyVO [cmpnPstAdptId=" + cmpnPstAdptId + ", cmpnId=" + cmpnId + ", advId=" + advId + ", reason="
				+ reason + ", pstDdln=" + pstDdln + ", fileGroupId=" + fileGroupId + ", file=" + file + "]";
	}
}
