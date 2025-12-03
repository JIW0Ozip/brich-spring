package com.ktdsuniversity.edu.domain.report.vo;

import com.ktdsuniversity.edu.global.common.BaseVO;

/**
 * @TableName RPT
 * @TableComment null
 */
public class ReportVO extends BaseVO{

    /**
     * @ColumnName RPT_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 신고 내역을 구분하기 위한 고유 아이디
     */
    private String rptId;

    /**
     * @ColumnName RPTR_USR_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 신고한 회원의 고유 아이디
     */
    private String rptrUsrId;

    /**
     * @ColumnName RPTED_USR_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 신고 당한 회원의 고유 아이디
     */
    private String rptedUsrId;

    /**
     * @ColumnName RPT_TITLE
     * @ColumnType VARCHAR2(100)
     * @ColumnComment 신고 제목
     */
    private String rptTitle;

    /**
     * @ColumnName RPT_RSN
     * @ColumnType CHAR(4)
     * @ColumnComment 신고 사유 카테고리
     */
    private String rptRsn;

    /**
     * @ColumnName RPT_CN
     * @ColumnType CLOB
     * @ColumnComment 신고 내용
     */
    private String rptCn;

    /**
     * @ColumnName RPT_FL_GRP_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 신고 첨부 파일 그룹을 구분하기 위한 고유 아이디
     */
    private String rptFlGrpId;

    /**
     * @ColumnName PRCN_YN
     * @ColumnType CHAR(1)
     * @ColumnComment 신고 건 처리 여부 (Y/N)
     */
    private String prcnYn;

    /**
     * @ColumnName PRCN_USR_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 신고 건을 처리한 관리자의 고유 아이디
     */
    private String prcnUsrId;

    public String getRptId() {
        return this.rptId;
    }
    
    public void setRptId(String rptId) {
        this.rptId = rptId;
    }
    
    public String getRptrUsrId() {
        return this.rptrUsrId;
    }
    
    public void setRptrUsrId(String rptrUsrId) {
        this.rptrUsrId = rptrUsrId;
    }
    
    public String getRptedUsrId() {
        return this.rptedUsrId;
    }
    
    public void setRptedUsrId(String rptedUsrId) {
        this.rptedUsrId = rptedUsrId;
    }
    
    public String getRptTitle() {
        return this.rptTitle;
    }
    
    public void setRptTitle(String rptTitle) {
        this.rptTitle = rptTitle;
    }
    
    public String getRptRsn() {
        return this.rptRsn;
    }
    
    public void setRptRsn(String rptRsn) {
        this.rptRsn = rptRsn;
    }
    
    public String getRptCn() {
        return this.rptCn;
    }
    
    public void setRptCn(String rptCn) {
        this.rptCn = rptCn;
    }
    
    public String getRptFlGrpId() {
        return this.rptFlGrpId;
    }
    
    public void setRptFlGrpId(String rptFlGrpId) {
        this.rptFlGrpId = rptFlGrpId;
    }
    
    public String getPrcnYn() {
        return this.prcnYn;
    }
    
    public void setPrcnYn(String prcnYn) {
        this.prcnYn = prcnYn;
    }
    
    public String getPrcnUsrId() {
        return this.prcnUsrId;
    }
    
    public void setPrcnUsrId(String prcnUsrId) {
        this.prcnUsrId = prcnUsrId;
    }

	@Override
	public String toString() {
		return "ReportVO [rptId=" + rptId + ", rptrUsrId=" + rptrUsrId + ", rptedUsrId=" + rptedUsrId + ", rptTitle="
				+ rptTitle + ", rptRsn=" + rptRsn + ", rptCn=" + rptCn + ", rptFlGrpId=" + rptFlGrpId + ", prcnYn="
				+ prcnYn + ", prcnUsrId=" + prcnUsrId + ", toString()=" + super.toString() + "]";
	}
    
    
}