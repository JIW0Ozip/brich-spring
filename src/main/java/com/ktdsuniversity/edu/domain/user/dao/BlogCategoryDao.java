package com.ktdsuniversity.edu.domain.user.dao;

import java.util.List;
import java.util.Map;

import com.ktdsuniversity.edu.domain.user.vo.BlogCategoryVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public interface BlogCategoryDao {

	public int insertBlogCategory(BlogCategoryVO blogCategoryVO);

	List<BlogCategoryVO> selectUserBlogCategoryById(String usrId);

	int updateBlogCategoryAsDelete(Map<String, Object> deleteParamMap);

	List<String> selectDeletedCategoryById(Map<String, Object> searchParamMap);

	int updateCategoryAsReactive(Map<String, Object> reactiveParamMap);

	int insertNewBlogCategory(Map<String, Object> insertParamMap);

	public List<CommonCodeVO> selectUserCategoryByUserId(String usrId);

	public int updateDltYnByUsrId(String usrId);

	public int mergeBlogCategory(BlogCategoryVO category);

	

}