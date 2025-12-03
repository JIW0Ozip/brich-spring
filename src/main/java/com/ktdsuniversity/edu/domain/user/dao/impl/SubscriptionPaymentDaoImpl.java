package com.ktdsuniversity.edu.domain.user.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.user.dao.SubscriptionPaymentDao;
import com.ktdsuniversity.edu.domain.user.vo.response.ResponseUserSubscriptionInfoVO;

@Repository
public class SubscriptionPaymentDaoImpl extends SqlSessionDaoSupport implements SubscriptionPaymentDao {

    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.user.dao.impl.SubscriptionPaymentDaoImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

	@Override
	public ResponseUserSubscriptionInfoVO selectSubscriptionInfoByUserId(String usrId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectSubscriptionInfoByUserId", usrId);
	}


}