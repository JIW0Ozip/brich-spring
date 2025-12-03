package com.ktdsuniversity.edu.domain.campaign.vo.response;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignPostAdoptVO;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;

public class ResponseAdoptVO extends CampaignPostAdoptVO {

	private String postCdNm;
	private UserVO userInfo;
	private String chtRmId;
	private int denyCount;
	
	public String getPostCdNm() {
		return this.postCdNm;
	}
	public void setPostCdNm(String postCdNm) {
		this.postCdNm = postCdNm;
	}
	public UserVO getUserInfo() {
		return this.userInfo;
	}
	public void setUserInfo(UserVO userInfo) {
		this.userInfo = userInfo;
	}
	public String getChtRmId() {
		return this.chtRmId;
	}
	public void setChtRmId(String chtRmId) {
		this.chtRmId = chtRmId;
	}
	public int getDenyCount() {
		return this.denyCount;
	}
	public void setDenyCount(int denyCount) {
		this.denyCount = denyCount;
	}
	
	@Override
	public String toString() {
		return "ResponseAdoptVO [postCdNm=" + postCdNm + ", userInfo=" + userInfo + ", chtRmId=" + chtRmId
				+ ", denyCount=" + denyCount + "]";
	}
}
