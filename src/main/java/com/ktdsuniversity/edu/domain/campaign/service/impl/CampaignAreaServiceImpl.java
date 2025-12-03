package com.ktdsuniversity.edu.domain.campaign.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.domain.campaign.dao.CampaignAreaDao;
import com.ktdsuniversity.edu.domain.campaign.service.CampaignAreaService;

@Service
public class CampaignAreaServiceImpl implements CampaignAreaService {

    @Autowired
    private CampaignAreaDao campaignAreaDao;

}