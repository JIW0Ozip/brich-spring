package com.ktdsuniversity.edu.domain.inqr.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.inqr.dao.AdminInqrDao;
import com.ktdsuniversity.edu.domain.inqr.vo.AdminAnsrRegistVO;
import com.ktdsuniversity.edu.domain.inqr.vo.AdminInqrDetailVO;
import com.ktdsuniversity.edu.domain.inqr.vo.AdminInqrListVO;

@Repository
public class AdminInqrDaoImpl extends SqlSessionDaoSupport implements AdminInqrDao {

	private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.inqr.dao.impl.AdminInqrDaoImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

	@Override
	public List<AdminInqrListVO> selectAdminInqrList() {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectAdminInqrList");
	}

	@Override
	public AdminInqrDetailVO selectAdminInqrDetailByInqrId(String inqrId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectAdminInqrDetailByInqrId", inqrId);
	}

	@Override
	public int updateInqrToAnswer(AdminAnsrRegistVO ansrInfo) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateInqrToAnswer", ansrInfo);
	}
	
}
