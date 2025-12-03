package com.ktdsuniversity.edu.domain.chat.vo.request;

public class RequestChatRoomFindVO {
	
	private String usrId;
	
	private String cmpnId;

	public String getUsrId() {
		return this.usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	public String getCmpnId() {
		return this.cmpnId;
	}

	public void setCmpnId(String cmpnId) {
		this.cmpnId = cmpnId;
	}

	@Override
	public String toString() {
		return "RequestChatRoomFindVO [usrId=" + usrId + ", cmpnId=" + cmpnId + "]";
	}
	
}
