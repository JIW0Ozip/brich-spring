package com.ktdsuniversity.edu.domain.faq.vo.response;

import java.util.List;

import com.ktdsuniversity.edu.domain.faq.vo.FaqVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public class ResponseFaqVO {

	private List<CommonCodeVO> codeList;
	
	private List<FaqVO> faqList;

	public List<CommonCodeVO> getCodeList() {
		return this.codeList;
	}

	public void setCodeList(List<CommonCodeVO> codeList) {
		this.codeList = codeList;
	}

	public List<FaqVO> getFaqList() {
		return this.faqList;
	}

	public void setFaqList(List<FaqVO> faqList) {
		this.faqList = faqList;
	}

	@Override
	public String toString() {
		return "ResponseFaqVO [codeList=" + codeList + ", faqList=" + faqList + "]";
	}	
}
