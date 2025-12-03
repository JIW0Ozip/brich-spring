package com.ktdsuniversity.edu.domain.inqr.vo;

import com.ktdsuniversity.edu.global.common.BaseVO;

/**
 * @TableName INQR
 * @TableComment null
 */
public class InqrVO extends BaseVO{

    /**
     * @ColumnName INQR_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 문의 내역을 구분하기 위한 고유 아이디
     */
    private String inqrId;

    /**
     * @ColumnName INQR_USR_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 문의한 회원의 고유 아이디
     */
    private String inqrUsrId;

    /**
     * @ColumnName INQR_TITLE
     * @ColumnType VARCHAR2(100)
     * @ColumnComment 문의 사항의 제목
     */
    private String inqrTitle;

    /**
     * @ColumnName INQR_CN
     * @ColumnType CLOB
     * @ColumnComment 문의 사항의 내용
     */
    private String inqrCn;

    /**
     * @ColumnName INQR_CTG
     * @ColumnType CHAR(4)
     * @ColumnComment 문의 사항의 카테고리
     */
    private String inqrCtg;

    /**
     * @ColumnName INQR_FL_GRP_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 문의 첨부 파일 그룹을 구분하기 위한 고유 아이디
     */
    private String inqrFlGrpId;

    /**
     * @ColumnName ANSR_CN
     * @ColumnType CLOB
     * @ColumnComment 답변 내용
     */
    private String ansrCn;

    /**
     * @ColumnName ANSR_FL_GRP_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 답변 첨부 파일 그룹을 구분하기 위한 고유 아이디
     */
    private String ansrFlGrpId;

    /**
     * @ColumnName ANSR_USR_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 답변을 등록한 관리자의 고유 아이디
     */
    private String ansrUsrId;

    public String getInqrId() {
        return this.inqrId;
    }
    
    public void setInqrId(String inqrId) {
        this.inqrId = inqrId;
    }
    
    public String getInqrUsrId() {
        return this.inqrUsrId;
    }
    
    public void setInqrUsrId(String inqrUsrId) {
        this.inqrUsrId = inqrUsrId;
    }
    
    public String getInqrTitle() {
        return this.inqrTitle;
    }
    
    public void setInqrTitle(String inqrTitle) {
        this.inqrTitle = inqrTitle;
    }
    
    public String getInqrCn() {
        return this.inqrCn;
    }
    
    public void setInqrCn(String inqrCn) {
        this.inqrCn = inqrCn;
    }
    
    public String getInqrCtg() {
        return this.inqrCtg;
    }
    
    public void setInqrCtg(String inqrCtg) {
        this.inqrCtg = inqrCtg;
    }
    
    public String getInqrFlGrpId() {
        return this.inqrFlGrpId;
    }
    
    public void setInqrFlGrpId(String inqrFlGrpId) {
        this.inqrFlGrpId = inqrFlGrpId;
    }
    
    public String getAnsrCn() {
        return this.ansrCn;
    }
    
    public void setAnsrCn(String ansrCn) {
        this.ansrCn = ansrCn;
    }
    
    public String getAnsrFlGrpId() {
        return this.ansrFlGrpId;
    }
    
    public void setAnsrFlGrpId(String ansrFlGrpId) {
        this.ansrFlGrpId = ansrFlGrpId;
    }
    
    public String getAnsrUsrId() {
        return this.ansrUsrId;
    }
    
    public void setAnsrUsrId(String ansrUsrId) {
        this.ansrUsrId = ansrUsrId;
    }

	@Override
	public String toString() {
		return "InqrVO [inqrId=" + inqrId + ", inqrUsrId=" + inqrUsrId + ", inqrTitle=" + inqrTitle + ", inqrCn="
				+ inqrCn + ", inqrCtg=" + inqrCtg + ", inqrFlGrpId=" + inqrFlGrpId + ", ansrCn=" + ansrCn
				+ ", ansrFlGrpId=" + ansrFlGrpId + ", ansrUsrId=" + ansrUsrId + ", toString()=" + super.toString()
				+ "]";
	}
   
  
}