package com.ktdsuniversity.edu.domain.report.vo;

import com.ktdsuniversity.edu.global.common.AbstractSearchVO;

public class ReportSearchVO extends AbstractSearchVO {
	
	private String rptrUsrId;

	public ReportSearchVO() {
		super();
		this.setListSize(10);
		this.setPageCountInGroup(5);
	}

	public String getRptrUsrId() {
		return rptrUsrId;
	}

	public void setRptrUsrId(String rptrUsrId) {
		this.rptrUsrId = rptrUsrId;
	}
}