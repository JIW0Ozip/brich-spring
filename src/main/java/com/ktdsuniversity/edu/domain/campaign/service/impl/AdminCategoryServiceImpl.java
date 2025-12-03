package com.ktdsuniversity.edu.domain.campaign.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.domain.campaign.dao.AdminCategoryDao;
import com.ktdsuniversity.edu.domain.campaign.service.AdminCategoryService;
import com.ktdsuniversity.edu.domain.campaign.vo.AdminCampaignCategoryVO;

@Service
public class AdminCategoryServiceImpl implements AdminCategoryService {
	
	private static final Logger log = LoggerFactory.getLogger(AdminCategoryServiceImpl.class);
	
	@Autowired
	private AdminCategoryDao adminCategoryDao;

	/**
	 * 캠페인 카테고리 리스트 출력
	 */
	@Override
	public List<AdminCampaignCategoryVO> readCampaignCategoryList() {
		return this.adminCategoryDao.selectCampaignCategoryList();
	}

	/**
	 * 대상 상위 카테고리의 하위 카테고리만 출력
	 */
	@Override
	public List<AdminCampaignCategoryVO> readChildrenCategoryList(String parentCdId) {
		return this.adminCategoryDao.selectChildrenCategoryList(parentCdId);
	}

	/**
	 * 자신을 제외한 상위 카테고리만 출력
	 */
	@Override
	public List<AdminCampaignCategoryVO> readParentCategoryList(String excludeCdId) {
		return this.adminCategoryDao.selectParentCategoryList(excludeCdId);
	}

	/**
	 * 카테고리 추가 (SELECT/INSERT)
	 * srt 맨 뒷 번호 +1 값으로 넣음, row 신경 안 씀.
	 */
	@Transactional
	@Override
	public boolean createNewCampaignCategory(AdminCampaignCategoryVO adminCampaignCategoryVO) {
		
		// cdId, srt (제일 큰 값으로 받아오기, DLT_YN 상관없이, SELECT)
		String lastCdId = this.adminCategoryDao.selectLastCampaignCdId();
		lastCdId = (Integer.parseInt(lastCdId) + 1) + "";
		adminCampaignCategoryVO.setCdId(lastCdId);
		
		int lastSrt = this.adminCategoryDao.selectLastSrtNumber();
		lastSrt += 1;
		adminCampaignCategoryVO.setSrt(lastSrt);
		
		int insertCount = this.adminCategoryDao.insertNewCampaignCategory(adminCampaignCategoryVO);
		
		// 카테고리 추가 (INSERT)
		return insertCount > 0;
	}

	/**
	 * 카테고리 분할
	 * srt 맨 뒷 번호 +1 값으로 넣음
	 */
	@Transactional
	@Override
	public boolean updateDivCampaignCategory(AdminCampaignCategoryVO adminCampaignCategoryVO) {
		
		int lastSrt = this.adminCategoryDao.selectLastSrtNumber();
		lastSrt += 1;
		adminCampaignCategoryVO.setSrt(lastSrt);
		
		return this.adminCategoryDao.updateDivCampaignCategory(adminCampaignCategoryVO) > 0;
	}

	/**
	 * 카테고리 병합
	 * 선택된 상위 카테고리의 하위 카테고리로 병합
	 */
	@Transactional
	@Override
	public boolean updateMergeCampaignCategory(AdminCampaignCategoryVO adminCampaignCategoryVO) {
		
		int beforeSrt = adminCampaignCategoryVO.getSrt();
		
		// 병합 후
		int updateCount = this.adminCategoryDao.updateMergeCampaignCategory(adminCampaignCategoryVO);
		
		adminCampaignCategoryVO.setSrt(beforeSrt);
		
		// 병합된 row의 SRT값 기준으로 보다 더 큰 SRT들 값 - 1
		updateCount = this.adminCategoryDao.updateMergeAfterSrtValueOneMinus(adminCampaignCategoryVO);
		
		return updateCount > 0;
	}

	@Transactional
	@Override
	public boolean updateChangeOrderCampaignCategory(AdminCampaignCategoryVO adminCampaignCategoryVO) {
		return this.adminCategoryDao.updateChangeOrderCampaignCategory(adminCampaignCategoryVO) > 0;
	}

}
