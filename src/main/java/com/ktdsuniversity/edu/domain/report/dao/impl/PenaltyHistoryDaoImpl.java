package com.ktdsuniversity.edu.domain.report.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.report.dao.PenaltyHistoryDao;
import com.ktdsuniversity.edu.domain.report.vo.AdminPenaltyRequestVO;

@Repository
public class PenaltyHistoryDaoImpl extends SqlSessionDaoSupport implements PenaltyHistoryDao {

    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.report.dao.impl.PenaltyHistoryDaoImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

	@Override
	public int insertPenaltyHistoryInWarning(AdminPenaltyRequestVO requestVO) {
		return super.getSqlSession().insert(this.NAME_SPACE + "insertPenaltyHistoryInWarning", requestVO);
	}

	@Override
	public int insertPenaltyHistoryInBan(AdminPenaltyRequestVO requestVO) {
		return super.getSqlSession().insert(this.NAME_SPACE + "insertPenaltyHistoryInBan", requestVO);
	}

	@Override
	public int updateHistoryBanToPenaltyCount(AdminPenaltyRequestVO requestVO) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateHistoryBanToPenaltyCount", requestVO);
	}


}