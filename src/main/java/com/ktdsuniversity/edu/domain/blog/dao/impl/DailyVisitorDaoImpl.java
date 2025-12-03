package com.ktdsuniversity.edu.domain.blog.dao.impl;

import com.ktdsuniversity.edu.domain.blog.vo.DailyVisitorVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.blog.dao.DailyVisitorDao;

import java.util.List;


@Repository
public class DailyVisitorDaoImpl extends SqlSessionDaoSupport implements DailyVisitorDao {


    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.blog.dao.impl.DailyVisitorDaoImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public List<DailyVisitorVO> selectDailyVisitor(String usrId) {
        return super.getSqlSession().selectList(this.NAME_SPACE+ "selectDailyVisitor", usrId);
    }

    @Override
    public int selectTotalVisitor(String usrId) {
        return super.getSqlSession().selectOne(this.NAME_SPACE+ "selectTotalVisitor", usrId);
    }




}