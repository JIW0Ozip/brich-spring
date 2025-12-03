package com.ktdsuniversity.edu.domain.pay.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignVO;
import com.ktdsuniversity.edu.domain.pay.dao.PayDao;
import com.ktdsuniversity.edu.domain.pay.vo.request.RequestPaymentCampaignVO;
import com.ktdsuniversity.edu.domain.pay.vo.request.RequestPaymentVO;
import com.ktdsuniversity.edu.domain.pay.vo.response.ResponsePaymentVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

@Repository
public class PayDaoImpl extends SqlSessionDaoSupport implements PayDao{
	
    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.pay.dao.impl.PayDaoImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

	@Override
	public List<CommonCodeVO> selectPayInfoList() {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectPayInfoList");
	}

	@Override
	public CommonCodeVO selectPayInfo(String cdId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectPayInfo", cdId);
	}

	@Override
	public int insertBeforeSubscribePaymentInfoSave(RequestPaymentVO requestPaymentVO) {
		return super.getSqlSession().insert(this.NAME_SPACE + "insertBeforeSubscribePaymentInfoSave" , requestPaymentVO);
	}

	@Override
	public int insertBeforeCampaignPaymentInfoSave(RequestPaymentVO requestPaymentVO) {
		return super.getSqlSession().insert(this.NAME_SPACE + "insertBeforeCampaignPaymentInfoSave" , requestPaymentVO);
	}

	@Override
	public ResponsePaymentVO selectBeforeSaveInfo(String pKkey) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectBeforeSaveInfo" , pKkey);
	}

	@Override
	public int updatePaymentSuccessSubscribe(RequestPaymentVO requestPaymentVO) {
		return super.getSqlSession().update(this.NAME_SPACE + "updatePaymentSuccessSubscribe", requestPaymentVO);
	}

	@Override
	public String selectSbscrptnCd(String easyAmount) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectSbscrptnCd", easyAmount);
	}

	@Override
	public void updatePaymentFailSubscribe(String pKkey) {
		super.getSqlSession().update(this.NAME_SPACE + "updatePaymentFailSubscribe" , pKkey);
	}

	@Override
	public void updatePaymentFailCampaign(String pKkey) {
		super.getSqlSession().update(this.NAME_SPACE + "updatePaymentFailCampaign" , pKkey);
	}

	@Override
	public int updatePaymentSuccessDate(RequestPaymentVO requestPaymentVO) {
		return super.getSqlSession().update(this.NAME_SPACE + "updatePaymentSuccessDate" ,requestPaymentVO);
	}

	@Override
	public ResponseCampaignVO selectReadCampaignPayment(Map<String, String> param) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectReadCampaignPayment" , param);
	}

	@Override
	public int updatePayInfoCampaignSave(RequestPaymentCampaignVO requestPaymentCampaignVO) {
		return super.getSqlSession().update(this.NAME_SPACE + "updatePayInfoCampaignSave" , requestPaymentCampaignVO);
	}

	@Override
	public String selectCmpnPayment(RequestPaymentCampaignVO requestPaymentCampaignVO) {
		return super.getSqlSession().selectOne(this.NAME_SPACE+"selectCmpnPayment", requestPaymentCampaignVO);
	}

	@Override
	public int updatePaymentInfoCampaignSave(RequestPaymentCampaignVO requestPaymentCampaignVO) {
		return super.getSqlSession().update(this.NAME_SPACE + "updatePaymentInfoCampaignSave", requestPaymentCampaignVO);
	}

	@Override
	public int insertPaymentInfoCampaignSave(RequestPaymentCampaignVO requestPaymentCampaignVO) {
		return super.getSqlSession().insert(this.NAME_SPACE + "insertPaymentInfoCampaignSave", requestPaymentCampaignVO);
	}

	@Override
	public String selectPayInfoServiceCampaignAmount(String cmpnId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectPayInfoServiceCampaignAmount", cmpnId);
	}

	@Override
	public int updateBeforeCampaignPaymentInfoSave(RequestPaymentVO requestPaymentVO) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateBeforeCampaignPaymentInfoSave", requestPaymentVO);
	}

	@Override
	public String selectBeforeCampaigninfo(String clientCmpnId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectBeforeCampaigninfo" , clientCmpnId);
	}
    
	@Override
	public int updatePaymentSuccessCampaign(RequestPaymentVO requestPaymentVO) {
		return super.getSqlSession().update(this.NAME_SPACE + "updatePaymentSuccessCampaign", requestPaymentVO);
	}

	@Override
	public int updatePaymentCampaignStts(RequestPaymentVO requestPaymentVO) {
		return super.getSqlSession().update(this.NAME_SPACE + "updatePaymentCampaignStts", requestPaymentVO);
	}

	@Override
	public String selectPaymentCmpnId(RequestPaymentVO requestPaymentVO) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectPaymentCmpnId", requestPaymentVO);
	}    
}
