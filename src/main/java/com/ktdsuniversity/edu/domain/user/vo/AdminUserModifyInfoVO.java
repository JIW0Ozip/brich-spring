package com.ktdsuniversity.edu.domain.user.vo;

import java.util.List;

public class AdminUserModifyInfoVO {
	
	// 관리자 아이디
	private String adminId;
	
	// 회원 테이블 공용 데이터
	private String usrId;
	private String logId;
	private String eml;
	private String nm;
	private String autr;
	
	// 블로거 전용 블로그 카테고리 테이블 데이터
    private List<String> usrBlgCtg;
	private String befAddrs;
	private String blgAddrs;
	private String befBlgCrtfctnDt;
	private boolean isBlogAddressChange;
	
	// 광고주 전용 데이터
	private String cmpny;
	private String flGrpId;
	private List<String> existFileIds;
	private List<String> deleteFileIds;
	
	// 수정 이력 테이블 전용 데이터
	private String updtRsn;
	
	public String getAdminId() {
		return this.adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getUsrId() {
		return this.usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getLogId() {
		return this.logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public String getEml() {
		return this.eml;
	}
	public void setEml(String eml) {
		this.eml = eml;
	}
	public String getNm() {
		return this.nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}
	public String getAutr() {
		return this.autr;
	}
	public void setAutr(String autr) {
		this.autr = autr;
	}
	public List<String> getUsrBlgCtg() {
		return this.usrBlgCtg;
	}
	public void setUsrBlgCtg(List<String> usrBlgCtg) {
		this.usrBlgCtg = usrBlgCtg;
	}
	public String getBefAddrs() {
		return this.befAddrs;
	}
	public void setBefAddrs(String befAddrs) {
		this.befAddrs = befAddrs;
	}
	public String getBlgAddrs() {
		return this.blgAddrs;
	}
	public void setBlgAddrs(String blgAddrs) {
		this.blgAddrs = blgAddrs;
	}
	public boolean getIsBlogAddressChange() {
		return this.isBlogAddressChange;
	}
	public void setIsBlogAddressChange(boolean isBlogAddressChange) {
		this.isBlogAddressChange = isBlogAddressChange;
	}
	public void setBlogAddressChange(boolean isBlogAddressChange) {
		this.isBlogAddressChange = isBlogAddressChange;
	}
	public String getBefBlgCrtfctnDt() {
		return this.befBlgCrtfctnDt;
	}
	public void setBefBlgCrtfctnDt(String befBlgCrtfctnDt) {
		this.befBlgCrtfctnDt = befBlgCrtfctnDt;
	}
	public String getCmpny() {
		return this.cmpny;
	}
	public void setCmpny(String cmpny) {
		this.cmpny = cmpny;
	}
	public String getFlGrpId() {
		return this.flGrpId;
	}
	public void setFlGrpId(String flGrpId) {
		this.flGrpId = flGrpId;
	}
	public List<String> getExistFileIds() {
		return this.existFileIds;
	}
	public void setExistFileIds(List<String> existFileIds) {
		this.existFileIds = existFileIds;
	}
	public List<String> getDeleteFileIds() {
		return this.deleteFileIds;
	}
	public void setDeleteFileIds(List<String> deleteFileIds) {
		this.deleteFileIds = deleteFileIds;
	}
	public String getUpdtRsn() {
		return this.updtRsn;
	}
	public void setUpdtRsn(String updtRsn) {
		this.updtRsn = updtRsn;
	}
	
}
