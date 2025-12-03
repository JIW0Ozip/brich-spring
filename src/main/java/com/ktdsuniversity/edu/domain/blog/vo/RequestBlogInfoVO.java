package com.ktdsuniversity.edu.domain.blog.vo;

public class RequestBlogInfoVO {

	private int scrpCnt;
	private int ttlVstrCnt;
	private String usrId;
	private int blgNghbrCnt;
	private String blgAddrs;
	
	public String getBlgAddrs() {
		return blgAddrs;
	}
	public void setBlgAddrs(String blgAddrs) {
		this.blgAddrs = blgAddrs;
	}
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public int getBlgNghbrCnt() {
		return blgNghbrCnt;
	}
	public void setBlgNghbrCnt(int blgNghbrCnt) {
		this.blgNghbrCnt = blgNghbrCnt;
	}
	public int getScrpCnt() {
		return scrpCnt;
	}
	public void setScrpCnt(int scrpCnt) {
		this.scrpCnt = scrpCnt;
	}
	public int getTtlVstrCnt() {
		return ttlVstrCnt;
	}
	public void setTtlVstrCnt(int ttlVstrCnt) {
		this.ttlVstrCnt = ttlVstrCnt;
	}
	
	
}
