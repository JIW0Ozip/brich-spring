package com.ktdsuniversity.edu.domain.faq.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.domain.faq.dao.FaqDao;
import com.ktdsuniversity.edu.domain.faq.service.FaqService;
import com.ktdsuniversity.edu.domain.faq.vo.FaqVO;
import com.ktdsuniversity.edu.domain.faq.vo.response.ResponseFaqVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

@Service
public class FaqServiceImpl implements FaqService {

    @Autowired
    private FaqDao faqDao;

	@Override
	public ResponseFaqVO selectFaqList(String category) {
		
		List<CommonCodeVO> codeList = this.faqDao.selectFaqCategory();
		
		List<FaqVO> faqList = this.faqDao.selectFaqList(category);
		
		ResponseFaqVO responseFaq = new ResponseFaqVO();
		
		responseFaq.setCodeList(codeList);
		responseFaq.setFaqList(faqList);
		
		return responseFaq;
	}

}