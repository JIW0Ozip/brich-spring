package com.ktdsuniversity.edu.domain.chat.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.chat.dao.ChatParticipantDao;

@Repository
public class ChatParticipantDaoImpl extends SqlSessionDaoSupport implements ChatParticipantDao {

    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.chat.dao.impl.ChatParticipantDaoImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }


}