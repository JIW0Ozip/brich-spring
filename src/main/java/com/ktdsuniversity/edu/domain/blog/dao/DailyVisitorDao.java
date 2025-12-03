package com.ktdsuniversity.edu.domain.blog.dao;


import com.ktdsuniversity.edu.domain.blog.vo.DailyVisitorVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

import java.util.List;

public interface DailyVisitorDao {


    List<DailyVisitorVO> selectDailyVisitor(String usrId);

    int selectTotalVisitor(String usrId);

}