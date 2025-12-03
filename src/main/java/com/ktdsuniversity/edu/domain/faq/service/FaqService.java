package com.ktdsuniversity.edu.domain.faq.service;

import com.ktdsuniversity.edu.domain.faq.vo.response.ResponseFaqVO;

public interface FaqService {

	public ResponseFaqVO selectFaqList(String category);

}