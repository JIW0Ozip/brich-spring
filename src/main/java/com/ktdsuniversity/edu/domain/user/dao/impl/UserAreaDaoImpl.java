package com.ktdsuniversity.edu.domain.user.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.user.dao.UserAreaDao;
import com.ktdsuniversity.edu.domain.user.vo.UserAreaVO;
import com.ktdsuniversity.edu.global.common.AreaCode;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

@Repository
public class UserAreaDaoImpl extends SqlSessionDaoSupport implements UserAreaDao {

    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.user.dao.impl.UserAreaDaoImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

	@Override
	public List<AreaCode> selectUserAreaByUserId(String usrId) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectUserAreaByUserId", usrId);
	}

	@Override
	public int updateDltYnByUsrId(String usrId) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateDltYnByUsrId", usrId);
	}

	@Override
	public int insertArea(UserAreaVO area) {
		return super.getSqlSession().insert(this.NAME_SPACE + "insertArea", area);
	}


}