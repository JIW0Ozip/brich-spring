package com.ktdsuniversity.edu.domain.report.vo;

import com.ktdsuniversity.edu.global.common.BaseVO;

/**
 * @TableName PNLT_HIST
 * @TableComment null
 */
public class PanaltyHistoryVO extends BaseVO{

    /**
     * @ColumnName PNLT_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 징계 구분을 위한 고유 아이디
     */
    private String pnltId;

    /**
     * @ColumnName RPT_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 신고 내역을 구분하기 위한 고유 아이디
     */
    private String rptId;

    /**
     * @ColumnName PNLT_USR_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 징계 대상 회원의 고유 아이디
     */
    private String pnltUsrId;

    /**
     * @ColumnName START_DT
     * @ColumnType DATE
     * @ColumnComment 징계 시작 일시
     */
    private String startDt;

    /**
     * @ColumnName END_DT
     * @ColumnType DATE
     * @ColumnComment 징계 종료 일시
     */
    private String endDt;

    /**
     * @ColumnName EXPRS_YN
     * @ColumnType CHAR(1)
     * @ColumnComment 현재 징계 상태 만료 여부 (Y/N)
     */
    private String exprsYn;

    /**
     * @ColumnName PRCN_USR_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 징계 처리한 관리자의 고유 아이디
     */
    private String prcnUsrId;

    public String getPnltId() {
        return this.pnltId;
    }
    
    public void setPnltId(String pnltId) {
        this.pnltId = pnltId;
    }
    
    public String getRptId() {
        return this.rptId;
    }
    
    public void setRptId(String rptId) {
        this.rptId = rptId;
    }
    
    public String getPnltUsrId() {
        return this.pnltUsrId;
    }
    
    public void setPnltUsrId(String pnltUsrId) {
        this.pnltUsrId = pnltUsrId;
    }
    
    public String getStartDt() {
        return this.startDt;
    }
    
    public void setStartDt(String startDt) {
        this.startDt = startDt;
    }
    
    public String getEndDt() {
        return this.endDt;
    }
    
    public void setEndDt(String endDt) {
        this.endDt = endDt;
    }
    
    public String getExprsYn() {
        return this.exprsYn;
    }
    
    public void setExprsYn(String exprsYn) {
        this.exprsYn = exprsYn;
    }
    
    public String getPrcnUsrId() {
        return this.prcnUsrId;
    }
    
    public void setPrcnUsrId(String prcnUsrId) {
        this.prcnUsrId = prcnUsrId;
    }

	@Override
	public String toString() {
		return "PanaltyHistoryVO [pnltId=" + pnltId + ", rptId=" + rptId + ", pnltUsrId=" + pnltUsrId + ", startDt="
				+ startDt + ", endDt=" + endDt + ", exprsYn=" + exprsYn + ", prcnUsrId=" + prcnUsrId + ", toString()="
				+ super.toString() + "]";
	}
 
    
}