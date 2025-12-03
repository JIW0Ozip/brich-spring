package com.ktdsuniversity.edu.domain.chat.vo.response;

public class ResponseChatRoomInfoVO {
	private String chtRmId;
	
	private String cmpnId;
	
	private String cmpnTitle;
	
	private String sttsCd;
	
	private String cdNm;
	
	private String lastMsgCn;
	
	private String lastMsgCrtDt;
	
	private String lastMsgUsrId;
	
	private String nm;
	
	private String logId;
	
	private int unreadCnt;
	
	private String crtDt;
	
	private String targetId;

	public String getChtRmId() {
		return this.chtRmId;
	}

	public void setChtRmId(String chtRmId) {
		this.chtRmId = chtRmId;
	}

	public String getCmpnId() {
		return this.cmpnId;
	}

	public void setCmpnId(String cmpnId) {
		this.cmpnId = cmpnId;
	}

	public String getCmpnTitle() {
		return this.cmpnTitle;
	}

	public void setCmpnTitle(String cmpnTitle) {
		this.cmpnTitle = cmpnTitle;
	}

	public String getSttsCd() {
		return this.sttsCd;
	}

	public void setSttsCd(String sttsCd) {
		this.sttsCd = sttsCd;
	}

	public String getCdNm() {
		return this.cdNm;
	}

	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
	}

	public String getLastMsgCn() {
		return this.lastMsgCn;
	}
	

	public void setLastMsgCn(String lastMsgCn) {
		this.lastMsgCn = lastMsgCn;
	}

	public String getLastMsgCrtDt() {
		return this.lastMsgCrtDt;
	}

	public void setLastMsgCrtDt(String lastMsgCrtDt) {
		this.lastMsgCrtDt = lastMsgCrtDt;
	}

	public String getLastMsgUsrId() {
		return this.lastMsgUsrId;
	}

	public void setLastMsgUsrId(String lastMsgUsrId) {
		this.lastMsgUsrId = lastMsgUsrId;
	}

	public int getUnreadCnt() {
		return this.unreadCnt;
	}

	public void setUnreadCnt(int unreadCnt) {
		this.unreadCnt = unreadCnt;
	}

	
	public String getNm() {
		return this.nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}

	public String getLogId() {
		return this.logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	
	public String getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(String crtDt) {
		this.crtDt = crtDt;
	}

	public String getTargetId() {
		return this.targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	@Override
	public String toString() {
		return "ResponseChatRoomInfoVO [chtRmId=" + chtRmId + ", cmpnId=" + cmpnId + ", cmpnTitle=" + cmpnTitle
				+ ", sttsCd=" + sttsCd + ", cdNm=" + cdNm + ", lastMsgCn=" + lastMsgCn + ", lastMsgCrtDt="
				+ lastMsgCrtDt + ", lastMsgUsrId=" + lastMsgUsrId + ", nm=" + nm + ", logId=" + logId + ", unreadCnt="
				+ unreadCnt + ", crtDt=" + crtDt + "]";
	}

	
}
