package com.ktdsuniversity.edu.domain.campaign.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.campaign.dao.CampaignUpdateHistoryDao;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignUpdateHistoryVO;

@Repository
public class CampaignUpdateHistoryDaoImpl extends SqlSessionDaoSupport implements CampaignUpdateHistoryDao {

    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.campaign.dao.impl.CampaignUpdateHistoryDaoImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
	public int insertAdminCampaignHistoryByReject(CampaignUpdateHistoryVO insertInfo) {
		return super.getSqlSession().insert(this.NAME_SPACE + "insertAdminCampaignHistoryByReject", insertInfo);
	}

	@Override
	public int insertAdminCampaignHistoryByApprove(CampaignUpdateHistoryVO insertInfo) {
		return super.getSqlSession().insert(this.NAME_SPACE + "insertAdminCampaignHistoryByApprove", insertInfo);
	}


}