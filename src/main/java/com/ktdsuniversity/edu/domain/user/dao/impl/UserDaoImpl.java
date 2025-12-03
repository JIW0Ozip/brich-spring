package com.ktdsuniversity.edu.domain.user.dao.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.blog.vo.RequestBlogInfoVO;
import com.ktdsuniversity.edu.domain.blog.vo.RequestBlogTitleVO;
import com.ktdsuniversity.edu.domain.blog.vo.RequestModifyBlogAddrsVO;
import com.ktdsuniversity.edu.domain.user.dao.UserDao;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserAccountPasswordVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserFindIdVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserLoginVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserRegistVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserResetPasswordVO;

@Repository
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {
	

    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.user.dao.impl.UserDaoImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

	@Override
	public UserVO selectUserByLogIdAndAutr(RequestUserLoginVO requestUserLoginVO) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectUserByLogIdAndAutr", requestUserLoginVO);
	}

	@Override
	public int insertNewUser(RequestUserRegistVO requestUserRegistVO) {
		return super.getSqlSession().insert(this.NAME_SPACE + "insertNewUser", requestUserRegistVO);
	}

	@Override
	public int selectUserCountByLogId(String logId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectUserCountByLogId", logId);
	}

	@Override
	public int selectUnblockUserByLogId(String logId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectUnblockUserByLogId", logId);
	}

	@Override
	public int updateLoginFailCountByLogId(String logId) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateLoginFailCountByLogId", logId);
	}

	@Override
	public int updateBlockByLogid(String logId) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateBlockByLogid", logId);
	}

	@Override
	public int updateLoginSuccessByLogId(String logId) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateLoginSuccessByLogId", logId);
	}

	@Override
	public String selectUserLogIdByNameAndEmail(RequestUserFindIdVO requestUserFindIdVO) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectUserLogIdByNameAndEmail", requestUserFindIdVO);
	}

	@Override
	public int updatePswrdByLogIdAndPswrd(RequestUserResetPasswordVO resetPasswordInfo) {
		return super.getSqlSession().update(this.NAME_SPACE + "updatePswrdByLogIdAndPswrd", resetPasswordInfo);
	}

	@Override
	public String selectUserIdByLogId(String logId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectUserIdByLogId", logId);
	}

	@Override
	public int updateBlgAddrsById(RequestModifyBlogAddrsVO requestModifyBlogAddrsVO) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateBlgAddrsById" , requestModifyBlogAddrsVO);
	}

	@Override
	public UserVO selectUserByLogId(String id) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectUserByLogId", id);
	}
	
	@Override
	public UserVO selectUserByUserId(String usrId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectUserByUserId", usrId);
	}

	@Override

	public String selectUserPswrdByPswrd(Map<String, String> currentUserPswrd) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectUserPswrdByPswrd", currentUserPswrd);
	}

	@Override
	public int updatePswrdByUsrId(RequestUserAccountPasswordVO requestUserAccountPasswordVO) {
		return super.getSqlSession().update(this.NAME_SPACE + "requestUserAccountPasswordVO", requestUserAccountPasswordVO);
	}

	@Override
	public String selectSaltByUsrId(String usrId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectSaltByUsrId", usrId);
	}
	@Override
	public int updateBlogScrapNeighbor(RequestBlogInfoVO request) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateBlogScrapNeighbor", request);
	}

	@Override
	public int updateBlogTitle(RequestBlogTitleVO request) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateBlogTitle", request);
	}

	@Override
	public int selectEmailCountByInputEmail(String email) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectEmailCountByInputEmail", email);
	}


}