package com.ktdsuniversity.edu.domain.report.service;

import java.util.List;

import com.ktdsuniversity.edu.domain.report.vo.AdminPenaltyRequestVO;
import com.ktdsuniversity.edu.domain.report.vo.AdminReportDetailVO;
import com.ktdsuniversity.edu.domain.report.vo.AdminReportListVO;

public interface AdminReportService {

	List<AdminReportListVO> readAdminReportList();

	AdminReportDetailVO readAdminReportDetailById(String rptId);

	boolean updateReportAndPenaltyInfo(AdminPenaltyRequestVO requestVO);

}
