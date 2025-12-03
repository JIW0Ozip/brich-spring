package com.ktdsuniversity.edu.domain.report.dao;

import java.util.List;

import com.ktdsuniversity.edu.domain.report.vo.AdminPenaltyRequestVO;
import com.ktdsuniversity.edu.domain.report.vo.AdminReportDetailVO;
import com.ktdsuniversity.edu.domain.report.vo.AdminReportListVO;

public interface AdminReportDao {

	List<AdminReportListVO> selectAdminReportList();

	AdminReportDetailVO selectAdminReportDetailById(String rptId);

	int updateReportInfo(AdminPenaltyRequestVO requestVO);

}
