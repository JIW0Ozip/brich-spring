package com.ktdsuniversity.edu.domain.campaign.service;

import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.AdminCampaignCategoryVO;

public interface AdminCategoryService {

	List<AdminCampaignCategoryVO> readCampaignCategoryList();

	List<AdminCampaignCategoryVO> readChildrenCategoryList(String parentCdId);

	List<AdminCampaignCategoryVO> readParentCategoryList(String excludeCdId);

	boolean createNewCampaignCategory(AdminCampaignCategoryVO adminCampaignCategoryVO);

	boolean updateDivCampaignCategory(AdminCampaignCategoryVO adminCampaignCategoryVO);

	boolean updateMergeCampaignCategory(AdminCampaignCategoryVO adminCampaignCategoryVO);

	boolean updateChangeOrderCampaignCategory(AdminCampaignCategoryVO adminCampaignCategoryVO);

}
