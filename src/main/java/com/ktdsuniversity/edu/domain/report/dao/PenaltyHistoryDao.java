package com.ktdsuniversity.edu.domain.report.dao;

import com.ktdsuniversity.edu.domain.report.vo.AdminPenaltyRequestVO;

public interface PenaltyHistoryDao {

	int insertPenaltyHistoryInWarning(AdminPenaltyRequestVO requestVO);

	int insertPenaltyHistoryInBan(AdminPenaltyRequestVO requestVO);

	int updateHistoryBanToPenaltyCount(AdminPenaltyRequestVO requestVO);

}