package com.ktdsuniversity.edu.domain.campaign.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.campaign.dao.AdminCampaignDao;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestAdminCamapaignRejectVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestAdminCampaignAdopterVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestAdminCampaignApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestAdminCampaignApproveVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestAdminSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminAdopterPstReSubmitCnVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminAdopterPstRtrnRsnVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminCampaignAdopterVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminCampaignApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminCampaignVO;

@Repository
public class AdminCampaignDaoImpl extends SqlSessionDaoSupport implements AdminCampaignDao {
	
	private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.campaign.dao.impl.AdminCampaignDaoImpl.";
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public String selectAdminCategoryParent(String category) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectAdminCategoryParent", category);
	}

	@Override
	public List<ResponseAdminCampaignVO> selectAdminCampaignListCategoryAndSortBy(RequestAdminSearchCampaignVO requestAdminSearchCampaignVO) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectAdminCampaignListCategoryAndSortBy", requestAdminSearchCampaignVO);
	}

	@Override
	public ResponseAdminCampaignVO selectAdminCampaignDetailById(String cmpnId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectAdminCampaignDetailById", cmpnId);
	}

	@Override
	public CampaignVO selectAdminCampaignAllInfoById(String cmpnId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectAdminCampaignAllInfoById", cmpnId);
	}

	@Override
	public int updateAdminCampaignByRejectInfo(RequestAdminCamapaignRejectVO rejectInfo) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateAdminCampaignByRejectInfo", rejectInfo);
	}

	@Override
	public int updateAdminCampaignByApproveInfo(RequestAdminCampaignApproveVO approveInfo) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateAdminCampaignByApproveInfo", approveInfo);
	}

	@Override
	public int selectAdminCampaignApplicantCountByCmpnId(RequestAdminCampaignApplicantVO requestApplicantVO) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectAdminCampaignApplicantCountByCmpnId", requestApplicantVO);
	}

	@Override
	public List<ResponseAdminCampaignApplicantVO> selectAdminCampaignApplicantListByCmpnId(
			RequestAdminCampaignApplicantVO requestApplicantVO) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectAdminCampaignApplicantListByCmpnId", requestApplicantVO);
	}

	@Override
	public int selectAdminCampaignPostApproveCountByPostId(String cmpnId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectAdminCampaignPostApproveCountByPostId", cmpnId);
	}

	@Override
	public List<ResponseAdminCampaignAdopterVO> selectAdminCampaignAdopterListByPostId(
			RequestAdminCampaignAdopterVO requestAdminAdopterVO) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectAdminCampaignAdopterListByPostId", requestAdminAdopterVO);
	}

	@Override
	public List<ResponseAdminAdopterPstRtrnRsnVO> selectAdopterReturnReasonListByPostId(String postId) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectAdopterReturnReasonListByPostId", postId);
	}

	@Override
	public List<ResponseAdminAdopterPstReSubmitCnVO> selectAdopterPostReSubmitListByPostId(String postId) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectAdopterPostReSubmitListByPostId", postId);
	}

}
