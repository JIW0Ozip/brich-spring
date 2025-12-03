package com.ktdsuniversity.edu.domain.inqr.dao;

import java.util.List;

import com.ktdsuniversity.edu.domain.inqr.vo.AdminAnsrRegistVO;
import com.ktdsuniversity.edu.domain.inqr.vo.AdminInqrDetailVO;
import com.ktdsuniversity.edu.domain.inqr.vo.AdminInqrListVO;

public interface AdminInqrDao {

	List<AdminInqrListVO> selectAdminInqrList();

	AdminInqrDetailVO selectAdminInqrDetailByInqrId(String inqrId);

	int updateInqrToAnswer(AdminAnsrRegistVO ansrInfo);

}
