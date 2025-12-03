package com.ktdsuniversity.edu.domain.report.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.report.dao.AdminReportDao;
import com.ktdsuniversity.edu.domain.report.vo.AdminPenaltyRequestVO;
import com.ktdsuniversity.edu.domain.report.vo.AdminReportDetailVO;
import com.ktdsuniversity.edu.domain.report.vo.AdminReportListVO;

@Repository
public class AdminReportDaoImpl extends SqlSessionDaoSupport implements AdminReportDao {
	
	private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.report.dao.impl.AdminReportDaoImpl.";
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public List<AdminReportListVO> selectAdminReportList() {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectAdminReportList");
	}

	@Override
	public AdminReportDetailVO selectAdminReportDetailById(String rptId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectAdminReportDetailById", rptId);
	}

	@Override
	public int updateReportInfo(AdminPenaltyRequestVO requestVO) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateReportInfo", requestVO);
	}

}
