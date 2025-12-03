package com.ktdsuniversity.edu.domain.chat.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.chat.dao.ChatRoomDao;

@Repository
public class ChatRoomDaoImpl extends SqlSessionDaoSupport implements ChatRoomDao {

    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.chat.dao.impl.ChatRoomDaoImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }


}