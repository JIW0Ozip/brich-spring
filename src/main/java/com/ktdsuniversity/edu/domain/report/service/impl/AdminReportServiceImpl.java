package com.ktdsuniversity.edu.domain.report.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.domain.report.dao.AdminReportDao;
import com.ktdsuniversity.edu.domain.report.dao.PenaltyHistoryDao;
import com.ktdsuniversity.edu.domain.report.service.AdminReportService;
import com.ktdsuniversity.edu.domain.report.vo.AdminPenaltyRequestVO;
import com.ktdsuniversity.edu.domain.report.vo.AdminReportDetailVO;
import com.ktdsuniversity.edu.domain.report.vo.AdminReportListVO;
import com.ktdsuniversity.edu.domain.user.dao.AdminUserDao;
import com.ktdsuniversity.edu.domain.user.vo.UserUpdateHistoryVO;

@Service
public class AdminReportServiceImpl implements AdminReportService {
	
	private static final Logger log = LoggerFactory.getLogger(AdminReportServiceImpl.class);
	
	@Autowired
	private AdminReportDao adminReportDao;
	
	@Autowired
	private PenaltyHistoryDao penaltyHistoryDao;
	
	@Autowired
	private AdminUserDao adminUserDao;

	@Override
	public List<AdminReportListVO> readAdminReportList() {
		return this.adminReportDao.selectAdminReportList();
	}

	@Override
	public AdminReportDetailVO readAdminReportDetailById(String rptId) {
		return this.adminReportDao.selectAdminReportDetailById(rptId);
	}

	@Transactional
	@Override
	public boolean updateReportAndPenaltyInfo(AdminPenaltyRequestVO requestVO) {
		
		int updateAndInsertCount = 0;
		
		// 신고 건 처리 사항에 대한 값을 받아온다.
		String penalty = requestVO.getPenaltyOption();
		
		// 신고 건 처리를 진행한다. (UPDATE)
		updateAndInsertCount = this.adminReportDao.updateReportInfo(requestVO);
		daoValidate(updateAndInsertCount, "updateReportInfo");
		
		// 처리 사항에 따라 분기 처리한다.
		if(penalty.equals("dismiss")) {
			return true;
		}
		
		// 신고 대상의 징계 횟수를 조회한다.
		int pnltCnt = this.adminUserDao.selectPenaltyCountById(requestVO.getRptedUsrId());
		
		if(penalty.equals("warning")) {
			updateAndInsertCount = this.penaltyHistoryDao.insertPenaltyHistoryInWarning(requestVO);
			daoValidate(updateAndInsertCount, "insertPenaltyHistoryInWarning");
		}
		else if(penalty.equals("ban")) {
			updateAndInsertCount = this.penaltyHistoryDao.insertPenaltyHistoryInBan(requestVO);
			daoValidate(updateAndInsertCount, "insertPenaltyHistoryInBan");
		}
		
		// 신고 대상의 징계 횟수를 1 증가시킨다. (+ 증가 시 3이상일 경우, 자동으로 BLOCK 처리한다.)
		updateAndInsertCount = this.adminUserDao.updateUserPenaltyCount(requestVO);
		daoValidate(updateAndInsertCount, "updateUserPenaltyCount");
		
		// 징계 횟수 +1 되므로, 수정 이력 테이블에 넣을 데이터를 준비한다.
	    UserUpdateHistoryVO history = new UserUpdateHistoryVO();
	    history.setUsrId(requestVO.getRptedUsrId());
	    history.setUpdtItem("PNLT_CNT");
	    history.setBefUpdtCn(pnltCnt + "");
	    history.setAftUpdtCn( (pnltCnt + 1) + "");
	    history.setUpdtAdmin(requestVO.getAdminId());
	    history.setUpdtRsn("징계 처리(" + requestVO.getPenaltyKeyword() + ")");
		
		// 이번 징계 처리로 징계 횟수가 3이상이 될 경우, 영구 정지 처리한다.
		if( (pnltCnt + 1) >= 3) {
			history.setUpdtRsn("징계 누적 횟수 3 이상 (최근 징계: " + requestVO.getPenaltyKeyword() + ")");
			
			// 징계 기록도 함께 갱신한다.
			updateAndInsertCount = this.penaltyHistoryDao.updateHistoryBanToPenaltyCount(requestVO);
			daoValidate(updateAndInsertCount, "updateHistoryBanToPenaltyCount");
		}
		
		// 회원 정보 수정 이력 또한 기록한다.
		return this.adminUserDao.insertNewHistoryByPenaltyCount(history) > 0;
	}
	
	/**
	 * INSERT/UPDATE Exception 처리 (임시)
	 * @param rows
	 * @param message
	 */
	private void daoValidate(int rows, String message) {
		if(rows == 0) {
			throw new RuntimeException("Database error: " + message + " 작업 결과 0건");
		}
	}

}
