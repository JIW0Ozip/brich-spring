package com.ktdsuniversity.edu.domain.blog.service;


import java.util.List;
import java.util.Map;

import com.ktdsuniversity.edu.domain.blog.vo.*;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignPostManageVO;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.PostReturnHistoryVO;
import com.ktdsuniversity.edu.domain.campaign.vo.ResponseExpireSoonListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseDenyHistoryVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public interface BlogDataService {

	ResponseExpireSoonListVO readExpireSoonCampaignList(RequestExpireSoonCampaignVO requestExpireSoonCampaignVO);

	boolean runPythonVerification(RequestModifyBlogAddrsVO request, String code);

	String generateVerificationCode();

	boolean runPythonInitialPostData(String blgAddrs);

	boolean insertPostData(PostDataInsertVO post);

	List<BlogIndexVO> readBlogIndexList(String usrId);

	List<PostDataVO> readPostStatsByDate(String usrId, String date);

	List<BlogIndexVO> readDailyIndex(String usrId, String date);

	boolean runPythonBlogStats(String blgAddrs);

	boolean runPythonBlogTitle(String blgAddrs);

	double selectMostRecentIndex(String usrId);

	List<BlogDetailStatVO> readBlogDetailStat(String usrId);

	List<DailyVisitorVO> selectDailyVisitors(String usrId);

	List<CommonCodeVO> selectUserCategoryKeywords(String usrId);

	int selectTotalVisitor(String usrId);

	List<CampaignPostManageVO> readCampaignPostByUsrId(String usrId);

	List<ResponseDenyHistoryVO> getReturnHistory(String postId);

	List<CampaignVO> selectRecommendCampaign(String usrId);

	Double readRecentBlogIndex(String usrId);

}
