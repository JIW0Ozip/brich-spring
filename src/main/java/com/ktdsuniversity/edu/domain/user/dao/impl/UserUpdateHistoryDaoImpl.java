package com.ktdsuniversity.edu.domain.user.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.user.dao.UserUpdateHistoryDao;

@Repository
public class UserUpdateHistoryDaoImpl extends SqlSessionDaoSupport implements UserUpdateHistoryDao {

    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.user.dao.impl.UserUpdateHistoryDaoImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }


}