package com.ktdsuniversity.edu.domain.campaign.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.campaign.dao.CampaignAreaDao;

@Repository
public class CampaignAreaDaoImpl extends SqlSessionDaoSupport implements CampaignAreaDao {

    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.campaign.dao.impl.CampaignAreaDaoImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }


}