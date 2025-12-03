package com.ktdsuniversity.edu.domain.user.dao;

import java.util.Map;


import com.ktdsuniversity.edu.domain.blog.vo.RequestBlogInfoVO;
import com.ktdsuniversity.edu.domain.blog.vo.RequestBlogTitleVO;
import com.ktdsuniversity.edu.domain.blog.vo.RequestModifyBlogAddrsVO;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserAccountPasswordVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserFindIdVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserLoginVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserRegistVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserResetPasswordVO;

public interface UserDao {

	public UserVO selectUserByLogIdAndAutr(RequestUserLoginVO requestUserLoginVO);

	public int insertNewUser(RequestUserRegistVO requestUserRegistVO);

	public int selectUserCountByLogId(String logId);

	public int selectUnblockUserByLogId(String logId);

	public int updateLoginFailCountByLogId(String logId);

	public int updateBlockByLogid(String logId);

	public int updateLoginSuccessByLogId(String logId);

	public String selectUserLogIdByNameAndEmail(RequestUserFindIdVO requestUserFindIdVO);

	public int updatePswrdByLogIdAndPswrd(RequestUserResetPasswordVO resetPasswordInfo);

	public String selectUserIdByLogId(String logId);

	public int updateBlgAddrsById(RequestModifyBlogAddrsVO requestModifyBlogAddrsVO);

	public UserVO selectUserByLogId(String id);
	
	public UserVO selectUserByUserId(String usrId);

	public String selectUserPswrdByPswrd(Map<String, String> currentUserPswrd);

	public int updatePswrdByUsrId(RequestUserAccountPasswordVO requestUserAccountPasswordVO);

	public String selectSaltByUsrId(String usrId);

	public int updateBlogScrapNeighbor(RequestBlogInfoVO request);

	public int updateBlogTitle(RequestBlogTitleVO request);

	public int selectEmailCountByInputEmail(String email);




}