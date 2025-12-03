package com.ktdsuniversity.edu.domain.faq.dao;

import java.util.List;

import com.ktdsuniversity.edu.domain.faq.vo.FaqVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public interface FaqDao {

	public List<CommonCodeVO> selectFaqCategory();

	public List<FaqVO> selectFaqList(String category);

}