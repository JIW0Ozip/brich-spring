package com.ktdsuniversity.edu.domain.faq.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.faq.dao.FaqDao;
import com.ktdsuniversity.edu.domain.faq.vo.FaqVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

@Repository
public class FaqDaoImpl extends SqlSessionDaoSupport implements FaqDao {

    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.faq.dao.impl.FaqDaoImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

	@Override
	public List<CommonCodeVO> selectFaqCategory() {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectFaqCategory");
	}

	@Override
	public List<FaqVO> selectFaqList(String category) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectFaqList", category);
	}


}