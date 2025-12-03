package com.ktdsuniversity.edu.domain.campaign.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.blog.vo.RequestExpireSoonCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.dao.CampaignDao;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignIndexStatVO;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignPostAdoptVO;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignPostManageVO;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestCampaignAreaVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestCreateCmpnVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestDenyVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestPostSubmitVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestUpdatePstSttsVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdoptVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseDenyHistoryVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;


@Repository
public class CampaignDaoImpl extends SqlSessionDaoSupport implements CampaignDao {

    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.campaign.dao.impl.CampaignDaoImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

	@Override
	public ResponseCampaignVO selectCampaignDetailById(Map<String, String> param) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectCampaignDetailById", param);
	}

	@Override
	public List<CommonCodeVO> selectCategoryList() {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectCategoryList");
	}
	
	@Override
	public List<ResponseApplicantVO> selectApplicantListByCmpnId(RequestApplicantVO requestApplicantVO) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectApplicantListByCmpnId", requestApplicantVO);
	}

	@Override
	public int updateAdptYnByCmpnPstAdptId(RequestApplicantVO requestApplicantVO) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateAdptYnByCmpnPstAdptId", requestApplicantVO);
	}

	@Override
	public int selectAdoptCountByCmpnId(String cmpnId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectAdoptCountByCmpnId", cmpnId);
	}

	@Override
	public ResponseCampaignVO selectCampaignInfoByCmpnId(String cmpnId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectCampaignInfoByCmpnId", cmpnId);
	}

	@Override
	public int selectApplicantCountByCmpnId(RequestApplicantVO requestApplicantVO) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectApplicantCountByCmpnId", requestApplicantVO);
	}
	
	public List<ResponseCampaignVO> selectCampaignListCategoryAndSortBy(RequestSearchCampaignVO requestSearchCampaignVO) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectCampaignListCategoryAndSortBy", requestSearchCampaignVO);
	}

	@Override
	public String selectCategoryParent(String selectCategroy) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectCategoryParent", selectCategroy);
	}

	@Override
	public List<CampaignVO> selectExpireSoonCampaign(RequestExpireSoonCampaignVO requestExpireSoonCampaignVO) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectExpireSoonCampaign", requestExpireSoonCampaignVO);
	}


	@Override
	public CampaignVO selectCampaignStateByCmpnPstAdptId(String cmpnPstAdptId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectCampaignStateByCmpnPstAdptId", cmpnPstAdptId);
	}

	@Override
	public List<ResponseAdoptVO> selectAdoptListByCmpnId(RequestApplicantVO requestApplicantVO) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectAdoptListByCmpnId", requestApplicantVO);
	}

	@Override
	public int selectAdoptPaginationCount(RequestApplicantVO requestApplicantVO) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectAdoptPaginationCount", requestApplicantVO);
	}

	@Override
	public String selectStateNameByStateCode(String sttsCd) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectStateNameByStateCode", sttsCd);
	}

	@Override
	public int updatePstSttsByCmpnPstAdoptId(RequestUpdatePstSttsVO requestUpdatePstSttsVO) {
		return super.getSqlSession().update(this.NAME_SPACE + "updatePstSttsByCmpnPstAdoptId", requestUpdatePstSttsVO);
	}
	
	/**
	 * 공통코드 이름 출력
	 */
	@Override
	public String selectCampaignChangeSttsCd(String sttsCd) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectCampaignChangeSttsCd", sttsCd);
	}

	@Override
	public List<ResponseCampaignVO> selectMyCampaignByBlgId(Map<String, Object> param) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectMyCampaignByBlgId", param);
	}

	
	@Override
	public String selectFavCamapignExists(Map<String, String> param) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectFavCamapignExists", param);
	}

	@Override
	public int insertFavCamapign(Map<String, String> param) {
		return super.getSqlSession().insert(this.NAME_SPACE +"insertFavCamapign", param);
	}

	@Override
	public int updateFavCamapignOn(Map<String, String> param) {
		return super.getSqlSession().update(this.NAME_SPACE +"updateFavCamapignOn", param);
	}

	@Override
	public int updateFavCamapignOff(Map<String, String> param) {
		return super.getSqlSession().update(this.NAME_SPACE +"updateFavCamapignOff", param);
	}

	@Override
	public String selectFavDltYn(Map<String, String> param) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectFavDltYn" , param);
	}

	@Override
	public List<ResponseCampaignVO> selectMyFavCampaignByBlgId(Map<String, Object> param) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectMyFavCampaignByBlgId" , param);
	}

	@Override
	public int insertApplyCampaign(Map<String, String> param) {
		
		return super.getSqlSession().insert(this.NAME_SPACE + "insertApplyCampaign", param);
	}

	@Override
	public int insertDenyByCmpnPstAdoptId(RequestDenyVO requestDenyVO) {
		return super.getSqlSession().insert(this.NAME_SPACE + "insertDenyByCmpnPstAdoptId", requestDenyVO);
	}

	@Override
	public List<CommonCodeVO> selectDoAndCityList() {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectDoAndCityList");
	}

	@Override
	public List<CommonCodeVO> selectDistrictByCdId(String cdId) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectDistrictByCdId", cdId);
	}

	@Override
	public String selectPersonPrice() {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectPersonPrice");
	}

	@Override
	public int insertNewCampaign(RequestCreateCmpnVO requestCreateCmpnVO) {
		return super.getSqlSession().insert(this.NAME_SPACE + "insertNewCampaign", requestCreateCmpnVO);
	}

	@Override
	public int insertCampaignCategory(RequestCampaignAreaVO requestCampaignAreaVO) {
		return super.getSqlSession().insert(this.NAME_SPACE + "insertCampaignCategory", requestCampaignAreaVO);
	}

	@Override
	public int updateDdlnByCmpnPstAdoptId(RequestDenyVO requestDenyVO) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateDdlnByCmpnPstAdoptId", requestDenyVO);
	}

	@Override
	public int udpateCmpnDateByCmpnId(RequestDenyVO requestDenyVO) {
		return super.getSqlSession().update(this.NAME_SPACE + "udpateCmpnDateByCmpnId", requestDenyVO);
	}
	@Override
	public String selecthasAdoptYn(Map<String, String> param) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selecthasAdoptYn" ,param);
	}

	@Override
	public String selectAdoptDltYn(Map<String, String> param) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectAdoptDltYn" ,param);
	}

	@Override
	public int updateCancelApplyCampaign(Map<String, String> param) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateCancelApplyCampaign" ,param);
	}

	@Override
	public int updateApplyCampaign(Map<String, String> param) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateApplyCampaign" , param);
	}

	@Override
	public List<ResponseCampaignVO> selectCampaignListByUsrId(RequestSearchCampaignVO requestSearchCampaignVO) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectCampaignListByUsrId", requestSearchCampaignVO);
	}

	@Override
	public int selectCampaignListCountByusrId(RequestSearchCampaignVO requestSearchCampaignVO) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectCampaignListCountByusrId", requestSearchCampaignVO);
	}

	@Override
	public List<ResponseCampaignVO> selectDenyListByCmpnId(RequestSearchCampaignVO requestSearchCampaignVO) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectDenyListByCmpnId", requestSearchCampaignVO);
	}

	@Override
	public int updatePostSubmit(RequestPostSubmitVO requestPostSubmitVO) {
		return super.getSqlSession().update(this.NAME_SPACE + "updatePostSubmit" ,requestPostSubmitVO );
	}
	
	@Override
	public int updateRePostSubmit(RequestPostSubmitVO requestPostSubmitVO) {
		return  super.getSqlSession().update(this.NAME_SPACE + "updateRePostSubmit" ,requestPostSubmitVO );
	}
	
	@Override
	public int updateRePostSubmitStts(RequestPostSubmitVO requestPostSubmitVO) {
		return  super.getSqlSession().update(this.NAME_SPACE + "updateRePostSubmitStts" ,requestPostSubmitVO );
	}

	@Override
	public int updatePostSubmitStts(RequestPostSubmitVO requestPostSubmitVO) {
		return 0;
	}

	@Override
	public String selectReturnReason(Map<String, String> param) {
		return  super.getSqlSession().selectOne(this.NAME_SPACE + "selectReturnReason" ,param );
	}

	@Override
	public int updateCmpnPrntIdByCmpnId(RequestCreateCmpnVO requestCreateCmpnVO) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateCmpnPrntIdByCmpnId", requestCreateCmpnVO);
	}

	@Override
	public int updateTemporaryCampaignByCmpnId(RequestCreateCmpnVO requestCreateCmpnVO) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateTemporaryCampaignByCmpnId", requestCreateCmpnVO);
	}

	@Override
	public List<ResponseDenyHistoryVO> selectDenyHistoryByCmpnPstAdptId(String postId) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectDenyHistoryByCmpnPstAdptId", postId);
	}

	@Override
	public List<CampaignPostManageVO> selectCampaignPostListByUsrId(String usrId) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectCampaignPostListByUsrId", usrId);
	}

	@Override
	public List<CampaignVO> selectRecommendedCampaignByUsrId(String usrId) {
		return super.getSqlSession().selectList(this.NAME_SPACE+"selectRecommendedCampaignByUsrId",usrId);
	}

	@Override
	public CampaignPostAdoptVO selectCampaignPostByPstAdptId(String postId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE+"selectCampaignPostByPstAdptId",postId);
	}

	@Override
	public List<CampaignIndexStatVO> selectCampaignIndexStats(String cmpnId){ 
		return super.getSqlSession().selectList(this.NAME_SPACE+"selectCampaignIndexStats", cmpnId);
	}

	@Override
	public Double selectMyBlogIndexInCampaign(String cmpnId, String usrId) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("cmpnId", cmpnId);
	    params.put("usrId", usrId);

	    return super.getSqlSession().selectOne(this.NAME_SPACE + "selectMyBlogIndexInCampaign", params);
	}
}