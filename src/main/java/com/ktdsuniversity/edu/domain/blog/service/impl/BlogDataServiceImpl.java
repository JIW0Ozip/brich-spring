package com.ktdsuniversity.edu.domain.blog.service.impl;

import java.util.List;
import java.util.UUID;

import com.ktdsuniversity.edu.domain.blog.dao.DailyVisitorDao;
import com.ktdsuniversity.edu.domain.blog.dao.GoldenKeyWordDao;
import com.ktdsuniversity.edu.domain.blog.vo.*;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.domain.blog.dao.PostDataDao;
import com.ktdsuniversity.edu.domain.blog.service.BlogDataService;
import com.ktdsuniversity.edu.domain.campaign.dao.CampaignDao;
import com.ktdsuniversity.edu.domain.campaign.service.CampaignService;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignPostManageVO;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.PostReturnHistoryVO;
import com.ktdsuniversity.edu.domain.campaign.vo.ResponseExpireSoonListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseDenyHistoryVO;
import com.ktdsuniversity.edu.domain.user.dao.UserDao;
import com.ktdsuniversity.edu.global.util.PythonExecutor;



@Service
public class BlogDataServiceImpl implements BlogDataService{

	private final String NAME_SPACE = "C:\\Users\\User\\Desktop\\project\\brich-project\\src\\main\\resources\\static\\crawler\\";
	
	@Autowired
	private CampaignService campaignService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private DailyVisitorDao dailyVisitorDao;
	@Autowired
	private PostDataDao postDataDao;
	@Autowired
	private GoldenKeyWordDao goldenKeyWordDao;


	@Override
	public ResponseExpireSoonListVO readExpireSoonCampaignList(RequestExpireSoonCampaignVO request) {

	    List<CampaignVO> list = this.campaignService.readExpireSoonCampaignList(request);

	    ResponseExpireSoonListVO result = new ResponseExpireSoonListVO();
	    result.setList(list);
	    result.setCount(50);
	    return result;
	}


	@Transactional
	@Override
	public boolean runPythonVerification(RequestModifyBlogAddrsVO requestModifyBlogAddrsVO, String code) {
		String pythonOutput = PythonExecutor.runPython(this.NAME_SPACE + "verification-crawler.py", requestModifyBlogAddrsVO.getBlgAddrs(), code);
		int updateCount = 0;
		if(pythonOutput.contains("Verification successful")) {
			updateCount = userDao.updateBlgAddrsById(requestModifyBlogAddrsVO);
			}
		return updateCount > 0;
		
	}

	@Override
	public String generateVerificationCode() {
		return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
	}

	
	@Override
	public boolean runPythonInitialPostData(String blgAddrs) {
		String pythonOutput = PythonExecutor.runPython(this.NAME_SPACE + "post-data-crawler.py", blgAddrs);
		if(!pythonOutput.isEmpty()) {
			return true;
		}
		return false;
	}

	@Transactional
	@Override
	public boolean insertPostData(PostDataInsertVO post) {
		int insertCount = this.postDataDao.insertPostData(post); 
		
		return insertCount > 0;
	}

	@Override
	public List<BlogIndexVO> readBlogIndexList(String usrId) {
		List<BlogIndexVO> list = this.postDataDao.selectBlogIndex(usrId);
		
		return list;
	}

	@Override
	public List<PostDataVO> readPostStatsByDate(String usrId, String date) {
		return null;
	}

	@Override
	public List<BlogIndexVO> readDailyIndex(String usrId, String date) {
		
		return null;
	}

	@Transactional
	@Override
	public boolean runPythonBlogStats(String blgAddrs) {
		String pythonOutput = PythonExecutor.runPython(this.NAME_SPACE + "view-scrap-neighbor-count-crawler.py", blgAddrs);
		if(pythonOutput.contains("success")) {
			return true;
		}
		return false;
	}

	@Override
	public boolean runPythonBlogTitle(String blgAddrs) {
		String pythonOutput = PythonExecutor.runPython(this.NAME_SPACE + "blog-title-crawler.py", blgAddrs);
		if(pythonOutput.contains("success")) {
			return true;
		}
		return false;
	}

	@Override
	public double selectMostRecentIndex(String usrId) {
		
		return this.postDataDao.selectRecentIndex(usrId);
	}

	@Override
	public List<BlogDetailStatVO> readBlogDetailStat(String usrId) {
		List<BlogDetailStatVO> list  = this.postDataDao.selectBlogDetailStat(usrId);
		
		return list;
	}



	@Override
	public List<DailyVisitorVO> selectDailyVisitors(String usrId) {
		List<DailyVisitorVO> list = this.dailyVisitorDao.selectDailyVisitor(usrId);
		return list;
	}


	@Override
	public List<CommonCodeVO> selectUserCategoryKeywords(String usrId) {
		List<CommonCodeVO> result = this.goldenKeyWordDao.selectUserCategories(usrId);
		return result;
	}

	@Override
	public int selectTotalVisitor(String usrId) {
		return this.dailyVisitorDao.selectTotalVisitor(usrId);
	}

	@Override
	public List<CampaignPostManageVO> readCampaignPostByUsrId(String usrId) {
		return this.campaignService.readCampaignManageListByUsrId(usrId);
	}


	@Override
	public List<ResponseDenyHistoryVO> getReturnHistory(String postId) {
		
		return this.campaignService.readDenyHistoryByCmpnPstAdptId(postId);
		
	}


	@Override
	public List<CampaignVO> selectRecommendCampaign(String usrId) {
		return this.campaignService.readRecommendedCampaignByUsrId(usrId);
	}


	@Override
	public Double readRecentBlogIndex(String usrId) {
		return this.postDataDao.selectRecentIndex(usrId);
	}
}
