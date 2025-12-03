package com.ktdsuniversity.edu.domain.campaign.dao;

import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.AdminCampaignCategoryVO;

public interface AdminCategoryDao {

	List<AdminCampaignCategoryVO> selectCampaignCategoryList();

	List<AdminCampaignCategoryVO> selectChildrenCategoryList(String parentCdId);

	List<AdminCampaignCategoryVO> selectParentCategoryList(String excludeCdId);

	String selectLastCampaignCdId();

	int selectLastSrtNumber();

	int insertNewCampaignCategory(AdminCampaignCategoryVO adminCampaignCategoryVO);

	int updateDivCampaignCategory(AdminCampaignCategoryVO adminCampaignCategoryVO);

	int updateMergeCampaignCategory(AdminCampaignCategoryVO adminCampaignCategoryVO);

	int updateMergeAfterSrtValueOneMinus(AdminCampaignCategoryVO adminCampaignCategoryVO);

	int updateChangeOrderCampaignCategory(AdminCampaignCategoryVO adminCampaignCategoryVO);

}
