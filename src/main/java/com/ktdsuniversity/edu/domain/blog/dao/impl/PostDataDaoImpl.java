package com.ktdsuniversity.edu.domain.blog.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.domain.blog.dao.PostDataDao;
import com.ktdsuniversity.edu.domain.blog.vo.BlogDetailStatVO;
import com.ktdsuniversity.edu.domain.blog.vo.BlogIndexVO;
import com.ktdsuniversity.edu.domain.blog.vo.PostDataVO;


@Repository
public class PostDataDaoImpl extends SqlSessionDaoSupport implements PostDataDao {

    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.blog.dao.impl.PostDataDaoImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Transactional
	@Override
	public int insertPostData(PostDataVO post) {
		return super.getSqlSession().insert(this.NAME_SPACE + "insertPostData", post);
	}

	@Override
	public List<BlogIndexVO> selectBlogIndex(String usrId) {
		return super.getSqlSession().selectList(this.NAME_SPACE+ "selectBlogIndex", usrId);
	}

	@Override
	public double selectRecentIndex(String usrId) {
	    Double result = super.getSqlSession().selectOne(this.NAME_SPACE + "selectRecentIndex", usrId);
	    return (result != null) ? result : 0.0;
	}

	@Override
	public List<BlogDetailStatVO> selectBlogDetailStat(String usrId) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectBlogDetailStat", usrId);
	}


}