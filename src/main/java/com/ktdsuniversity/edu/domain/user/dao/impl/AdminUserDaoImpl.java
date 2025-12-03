package com.ktdsuniversity.edu.domain.user.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.report.vo.AdminPenaltyRequestVO;
import com.ktdsuniversity.edu.domain.user.dao.AdminUserDao;
import com.ktdsuniversity.edu.domain.user.vo.AdminAdvertiserDetailVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminBloggerAreaInfoVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminBloggerCategoryInfoVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminBloggerDetailVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminUserListVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminUserModifyInfoVO;
import com.ktdsuniversity.edu.domain.user.vo.UserUpdateHistoryVO;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

@Repository
public class AdminUserDaoImpl extends SqlSessionDaoSupport implements AdminUserDao {
	
	private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.user.dao.impl.AdminUserDaoImpl.";
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public List<AdminUserListVO> selectAdminUserList() {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectAdminUserList");
	}
	
	@Override
	public List<AdminUserListVO> selectAdminBloggerList() {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectAdminBloggerList");
	}

	@Override
	public List<AdminUserListVO> selectAdminAdvertiserList() {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectAdminAdvertiserList");
	}

	@Override
	public String selectAdminUserAutrById(String usrId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectAdminUserAutrById", usrId);
	}

	@Override
	public AdminBloggerDetailVO selectAdminBloggerDetailById(String usrId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectAdminBloggerDetailById", usrId);
	}

	@Override
	public List<CampaignVO> selectBloggerCmpnProgressList(String usrId) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectBloggerCmpnProgressList", usrId);
	}

	@Override
	public List<CampaignVO> selectBloggerCmpnCompletedList(String usrId) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectBloggerCmpnCompletedList", usrId);
	}

	@Override
	public List<AdminBloggerAreaInfoVO> selectBloggerAreaList(String usrId) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectBloggerAreaList", usrId);
	}

	@Override
	public List<AdminBloggerCategoryInfoVO> selectBloggerCategoryList(String usrId) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectBloggerCategoryList", usrId);
	}
	
	@Override
	public AdminAdvertiserDetailVO selectAdminAdvertiserDetailById(String usrId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectAdminAdvertiserDetailById", usrId);
	}

	@Override
	public List<CampaignVO> selectAdvertiserCmpnProgressList(String usrId) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectAdvertiserCmpnProgressList", usrId);
	}

	@Override
	public List<CampaignVO> selectAdvertiserCmpnCompletedList(String usrId) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectAdvertiserCmpnCompletedList", usrId);
	}

	@Override
	public int updateAdvertiserAuthCodeByApprove(Map<String, String> requestData) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateAdvertiserAuthCodeByApprove", requestData);
	}

	@Override
	public int updateAdvertiserAuthCodeByReject(Map<String, String> requestData) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateAdvertiserAuthCodeByReject", requestData);
	}

	@Override
	public List<CommonCodeVO> selectBlogCategoryList() {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectBlogCategoryList");
	}

	@Override
	public UserVO selectUserInfoById(String usrId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectUserInfoById", usrId);
	}

	@Override
	public int updateUserInfo(AdminUserModifyInfoVO adminUserModifyInfoVO) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateUserInfo", adminUserModifyInfoVO);
	}

	@Override
	public int updateAdvertiserInfo(AdminUserModifyInfoVO adminUserModifyInfoVO) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateAdvertiserInfo", adminUserModifyInfoVO);
	}

	@Override
	public int insertUpdateHistory(List<UserUpdateHistoryVO> historyList) {
		return super.getSqlSession().insert(this.NAME_SPACE + "insertUpdateHistory", historyList);
	}

	@Override
	public int updateBlogAddress(AdminUserModifyInfoVO adminUserModifyInfoVO) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateBlogAddress", adminUserModifyInfoVO);
	}

	@Override
	public int insertHistoryToBlogAddress(UserUpdateHistoryVO updateHistory) {
		return super.getSqlSession().insert(this.NAME_SPACE + "insertHistoryToBlogAddress", updateHistory);
	}

	@Override
	public int selectPenaltyCountById(String rptedUsrId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectPenaltyCountById", rptedUsrId);
	}

	@Override
	public int updateUserPenaltyCount(AdminPenaltyRequestVO requestVO) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateUserPenaltyCount", requestVO);
	}

	@Override
	public int insertNewHistoryByPenaltyCount(UserUpdateHistoryVO history) {
		return super.getSqlSession().insert(this.NAME_SPACE + "insertNewHistoryByPenaltyCount", history);
	}

}
