package com.ktdsuniversity.edu.domain.blog.dao;

import com.ktdsuniversity.edu.global.common.CommonCodeVO;

import java.util.List;

public interface GoldenKeyWordDao {


    List<CommonCodeVO> selectUserCategories(String usrId);
}
