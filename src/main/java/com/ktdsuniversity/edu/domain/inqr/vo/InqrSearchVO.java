package com.ktdsuniversity.edu.domain.inqr.vo;

import com.ktdsuniversity.edu.global.common.AbstractSearchVO;

public class InqrSearchVO extends AbstractSearchVO{

	private String inqrUsrId;
	
	public InqrSearchVO() {
		super();
		this.setListSize(10);
		this.setPageCountInGroup(5);
	}

	public String getInqrUsrId() {
		return this.inqrUsrId;
	}

	public void setInqrUsrId(String inqrUsrId) {
		this.inqrUsrId = inqrUsrId;
	}
	
	
}
