package com.ktdsuniversity.edu.domain.campaign.vo;

import com.ktdsuniversity.edu.global.common.BaseVO;

/**
 * @TableName CMPN_UPDT_HIST
 * @TableComment 캠페인을 관리자가 수정한 기록
 */
public class CampaignUpdateHistoryVO extends BaseVO{

    /**
     * @ColumnName CMPN_UPDT_HIST_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 캠페인 수정 기록 아이디 (PK)
     */
    private String cmpnUpdtHistId;

    /**
     * @ColumnName CMPN_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 수정한 캠페인 ID
     */
    private String cmpnId;

    /**
     * @ColumnName UPDT_ITEM
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 수정된 칼럼명
     */
    private String updtItem;

    /**
     * @ColumnName BEF_UPDT_CN
     * @ColumnType VARCHAR2(200)
     * @ColumnComment 수정 전 내용
     */
    private String befUpdtCn;

    /**
     * @ColumnName AFT_UPDT_CN
     * @ColumnType VARCHAR2(200)
     * @ColumnComment 수정 후 내용
     */
    private String aftUpdtCn;

    /**
     * @ColumnName UPDT_ADMIN
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 수정한 관리자
     */
    private String updtAdmin;

    /**
     * @ColumnName UPDT_RSN
     * @ColumnType CLOB
     * @ColumnComment 수정 사유
     */
    private String updtRsn;

  

    public String getCmpnUpdtHistId() {
        return this.cmpnUpdtHistId;
    }
    
    public void setCmpnUpdtHistId(String cmpnUpdtHistId) {
        this.cmpnUpdtHistId = cmpnUpdtHistId;
    }
    
    public String getCmpnId() {
        return this.cmpnId;
    }
    
    public void setCmpnId(String cmpnId) {
        this.cmpnId = cmpnId;
    }
    
    public String getUpdtItem() {
        return this.updtItem;
    }
    
    public void setUpdtItem(String updtItem) {
        this.updtItem = updtItem;
    }
    
    public String getBefUpdtCn() {
        return this.befUpdtCn;
    }
    
    public void setBefUpdtCn(String befUpdtCn) {
        this.befUpdtCn = befUpdtCn;
    }
    
    public String getAftUpdtCn() {
        return this.aftUpdtCn;
    }
    
    public void setAftUpdtCn(String aftUpdtCn) {
        this.aftUpdtCn = aftUpdtCn;
    }
    
    public String getUpdtAdmin() {
        return this.updtAdmin;
    }
    
    public void setUpdtAdmin(String updtAdmin) {
        this.updtAdmin = updtAdmin;
    }
    
    public String getUpdtRsn() {
        return this.updtRsn;
    }
    
    public void setUpdtRsn(String updtRsn) {
        this.updtRsn = updtRsn;
    }

	@Override
	public String toString() {
		return "CampaignUpdateHistoryVO [cmpnUpdtHistId=" + cmpnUpdtHistId + ", cmpnId=" + cmpnId + ", updtItem="
				+ updtItem + ", befUpdtCn=" + befUpdtCn + ", aftUpdtCn=" + aftUpdtCn + ", updtAdmin=" + updtAdmin
				+ ", updtRsn=" + updtRsn + ", toString()=" + super.toString() + "]";
	}

    
}