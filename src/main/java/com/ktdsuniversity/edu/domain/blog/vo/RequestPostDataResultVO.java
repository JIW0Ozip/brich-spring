package com.ktdsuniversity.edu.domain.blog.vo;

import java.util.List;

public class RequestPostDataResultVO {

	private String blgAddrs;
	private List<PostDataInsertVO> postList;
	
	
	public String getBlgAddrs() {
		return blgAddrs;
	}
	public void setBlgAddrs(String usrId) {
		this.blgAddrs = usrId;
	}
	public List<PostDataInsertVO> getPostList() {
		return postList;
	}
	public void setPostList(List<PostDataInsertVO> postList) {
		this.postList = postList;
	}
	
	
	
}
