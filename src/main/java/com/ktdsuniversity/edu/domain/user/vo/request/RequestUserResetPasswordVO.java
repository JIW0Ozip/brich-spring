package com.ktdsuniversity.edu.domain.user.vo.request;

public class RequestUserResetPasswordVO {
	
	private String logId;
	
	private String salt;
	
	private String pswrd;
	
	public String getLogId() {
		return this.logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPswrd() {
		return this.pswrd;
	}

	public void setPswrd(String pswrd) {
		this.pswrd = pswrd;
	}

	@Override
	public String toString() {
		return "RequestUserResetPasswordVO [logId=" + logId + ", salt=" + salt + ", pswrd=" + pswrd + ", toString()="
				+ super.toString() + "]";
	}
}
