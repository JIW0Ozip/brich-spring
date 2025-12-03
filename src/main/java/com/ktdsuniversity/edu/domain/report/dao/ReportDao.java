package com.ktdsuniversity.edu.domain.report.dao;

import java.util.List;

import com.ktdsuniversity.edu.domain.report.vo.ReportSearchVO;
import com.ktdsuniversity.edu.domain.report.vo.request.RequestReportCreateVO;
import com.ktdsuniversity.edu.domain.report.vo.response.ResponseMyReportInfoVO;
import com.ktdsuniversity.edu.domain.report.vo.response.ResponseReportDetailVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public interface ReportDao {

	public List<CommonCodeVO> selectReportCategoryList();

	public int insertReport(RequestReportCreateVO requestReportCreateVO);

	public List<ResponseMyReportInfoVO> selectReportListByUsrId(String usrId);

	public int selectMyReportCount(ReportSearchVO reportSearchVO);

	public List<ResponseMyReportInfoVO> selectMyReportListWithPaging(ReportSearchVO reportSearchVO);

	public ResponseReportDetailVO selectReportDetailByReportId(String reportId);

}