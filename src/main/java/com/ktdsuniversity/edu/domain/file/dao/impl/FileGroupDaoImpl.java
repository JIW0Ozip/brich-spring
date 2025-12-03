package com.ktdsuniversity.edu.domain.file.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.file.dao.FileGroupDao;
import com.ktdsuniversity.edu.domain.file.vo.FileGroupVO;

@Repository
public class FileGroupDaoImpl extends SqlSessionDaoSupport implements FileGroupDao {

    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.file.dao.impl.FileGroupDaoImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

	@Override
	public int insertFileGroup(FileGroupVO fileGroupVO) {
		return super.getSqlSession().insert(this.NAME_SPACE + "insertFileGroup", fileGroupVO);
	}


}