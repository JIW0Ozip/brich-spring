package com.ktdsuniversity.edu.domain.report.service;

import java.util.List;

import com.ktdsuniversity.edu.domain.report.vo.ReportSearchVO;
import com.ktdsuniversity.edu.domain.report.vo.request.RequestReportCreateVO;
import com.ktdsuniversity.edu.domain.report.vo.response.ResponseMyReportInfoVO;
import com.ktdsuniversity.edu.domain.report.vo.response.ResponseReportDetailVO;
import com.ktdsuniversity.edu.domain.report.vo.response.ResponseReportVO;

public interface ReportService {

	public ResponseReportVO readTargetInfoByTargetUsrId(String targetUsrId);

	public boolean createNewReport(RequestReportCreateVO requestReportCreateVO);

	public List<ResponseMyReportInfoVO> selectListByUsrId(String usrId);

	/**
	 * 나의 신고 목록 조회 (페이징)
	 */
	public List<ResponseMyReportInfoVO> readMyReportListWithPaging(ReportSearchVO reportSearchVO);

	public ResponseReportDetailVO readReportDetailByReportId(String reportId);


}