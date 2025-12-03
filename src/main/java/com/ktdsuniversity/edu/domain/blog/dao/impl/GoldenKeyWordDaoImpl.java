package com.ktdsuniversity.edu.domain.blog.dao.impl;

import com.ktdsuniversity.edu.domain.blog.dao.GoldenKeyWordDao;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

@Repository
public class GoldenKeyWordDaoImpl extends SqlSessionDaoSupport implements GoldenKeyWordDao {
    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.blog.dao.impl.GoldenKeyWordDaoImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public List<CommonCodeVO> selectUserCategories(String usrId) {
        return super.getSqlSession().selectList(this.NAME_SPACE + "selectUserCategories", usrId);
    }

}
