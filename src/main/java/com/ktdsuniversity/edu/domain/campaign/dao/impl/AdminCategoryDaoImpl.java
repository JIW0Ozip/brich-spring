package com.ktdsuniversity.edu.domain.campaign.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.campaign.dao.AdminCategoryDao;
import com.ktdsuniversity.edu.domain.campaign.vo.AdminCampaignCategoryVO;

@Repository
public class AdminCategoryDaoImpl extends SqlSessionDaoSupport implements AdminCategoryDao {
	
	private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.campaign.dao.impl.AdminCategoryDaoImpl.";
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public List<AdminCampaignCategoryVO> selectCampaignCategoryList() {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectCampaignCategoryList");
	}

	@Override
	public List<AdminCampaignCategoryVO> selectChildrenCategoryList(String parentCdId) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectChildrenCategoryList", parentCdId);
	}

	@Override
	public List<AdminCampaignCategoryVO> selectParentCategoryList(String excludeCdId) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectParentCategoryList", excludeCdId);
	}

	@Override
	public String selectLastCampaignCdId() {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectLastCampaignCdId");
	}

	@Override
	public int selectLastSrtNumber() {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectLastSrtNumber");
	}

	@Override
	public int insertNewCampaignCategory(AdminCampaignCategoryVO adminCampaignCategoryVO) {
		return super.getSqlSession().insert(this.NAME_SPACE + "insertNewCampaignCategory", adminCampaignCategoryVO);
	}

	@Override
	public int updateDivCampaignCategory(AdminCampaignCategoryVO adminCampaignCategoryVO) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateDivCampaignCategory", adminCampaignCategoryVO);
	}

	@Override
	public int updateMergeCampaignCategory(AdminCampaignCategoryVO adminCampaignCategoryVO) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateMergeCampaignCategory", adminCampaignCategoryVO);
	}

	@Override
	public int updateMergeAfterSrtValueOneMinus(AdminCampaignCategoryVO adminCampaignCategoryVO) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateMergeAfterSrtValueOneMinus", adminCampaignCategoryVO);
	}

	@Override
	public int updateChangeOrderCampaignCategory(AdminCampaignCategoryVO adminCampaignCategoryVO) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateChangeOrderCampaignCategory", adminCampaignCategoryVO);
	}

}
