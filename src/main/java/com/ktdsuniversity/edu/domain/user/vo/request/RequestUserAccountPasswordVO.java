package com.ktdsuniversity.edu.domain.user.vo.request;

public class RequestUserAccountPasswordVO {

	private String usrId;
	
	private String currentPswrd;
	
	private String newPswrd;
	
	private String newPswrdConfirm;
	
	private String salt;

	public String getUsrId() {
		return this.usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	public String getCurrentPswrd() {
		return this.currentPswrd;
	}

	public void setCurrentPswrd(String currentPswrd) {
		this.currentPswrd = currentPswrd;
	}

	public String getNewPswrd() {
		return this.newPswrd;
	}

	public void setNewPswrd(String newPswrd) {
		this.newPswrd = newPswrd;
	}

	public String getNewPswrdConfirm() {
		return this.newPswrdConfirm;
	}

	public void setNewPswrdConfirm(String newPswrdConfirm) {
		this.newPswrdConfirm = newPswrdConfirm;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return "RequestUserAccountPasswordVO [usrId=" + usrId + ", currentPswrd=" + currentPswrd + ", newPswrd="
				+ newPswrd + ", newPswrdConfirm=" + newPswrdConfirm + ", salt=" + salt + "]";
	}


	
	
}
