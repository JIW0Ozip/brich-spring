package com.ktdsuniversity.edu.domain.campaign.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.domain.campaign.dao.CampaignPaymentDao;
import com.ktdsuniversity.edu.domain.campaign.service.CampaignPaymentService;

@Service
public class CampaignPaymentServiceImpl implements CampaignPaymentService {

    @Autowired
    private CampaignPaymentDao campaignPaymentDao;

}