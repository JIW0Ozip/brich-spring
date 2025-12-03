package com.ktdsuniversity.edu.domain.report.vo.response;

import java.util.List;

import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public class ResponseReportVO {
	
	private List<CommonCodeVO> rptCategory;
	
	private String targetName;
	
	private String targetId;

	public List<CommonCodeVO> getRptCategory() {
		return this.rptCategory;
	}

	public void setRptCategory(List<CommonCodeVO> rptCategory) {
		this.rptCategory = rptCategory;
	}

	public String getTargetName() {
		return this.targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getTargetId() {
		return this.targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	@Override
	public String toString() {
		return "ResponseReportVO [rptCategory=" + rptCategory + ", targetName=" + targetName + ", targetId=" + targetId
				+ "]";
	}
	
}
