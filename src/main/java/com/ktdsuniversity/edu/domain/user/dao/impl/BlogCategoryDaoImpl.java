package com.ktdsuniversity.edu.domain.user.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.user.dao.BlogCategoryDao;
import com.ktdsuniversity.edu.domain.user.vo.BlogCategoryVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

@Repository
public class BlogCategoryDaoImpl extends SqlSessionDaoSupport implements BlogCategoryDao {

    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.user.dao.impl.BlogCategoryDaoImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

	@Override
	public int insertBlogCategory(BlogCategoryVO blogCategoryVO) {
		return super.getSqlSession().insert(this.NAME_SPACE + "insertBlogCategory", blogCategoryVO);
	}



	public List<BlogCategoryVO> selectUserBlogCategoryById(String usrId) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectUserBlogCategoryById", usrId);
	}

	@Override
	public int updateBlogCategoryAsDelete(Map<String, Object> deleteParamMap) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateBlogCategoryAsDelete", deleteParamMap);
	}

	@Override
	public List<String> selectDeletedCategoryById(Map<String, Object> searchParamMap) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectDeletedCategoryById", searchParamMap);
	}

	@Override
	public int updateCategoryAsReactive(Map<String, Object> reactiveParamMap) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateCategoryAsReactive", reactiveParamMap);
	}

	@Override
	public int insertNewBlogCategory(Map<String, Object> insertParamMap) {
		return super.getSqlSession().insert(this.NAME_SPACE + "insertNewBlogCategory", insertParamMap);
	}

	@Override
	public List<CommonCodeVO> selectUserCategoryByUserId(String usrId) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectUserCategoryByUserId", usrId);
	}

	@Override
	public int updateDltYnByUsrId(String usrId) {
		return super.getSqlSession().update(this.NAME_SPACE + "updateDltYnByUsrId", usrId);
	}

	@Override
	public int mergeBlogCategory(BlogCategoryVO category) {
		return super.getSqlSession().insert(this.NAME_SPACE + "mergeBlogCategory", category);
	}

}