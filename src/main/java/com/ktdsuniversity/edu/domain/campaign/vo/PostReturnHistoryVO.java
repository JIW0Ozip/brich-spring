package com.ktdsuniversity.edu.domain.campaign.vo;

import com.ktdsuniversity.edu.global.common.BaseVO;

/**
 * @TableName POST_RTRN_HIST
 * @TableComment null
 */
public class PostReturnHistoryVO extends BaseVO{

    /**
     * @ColumnName POST_RTRN_HIST_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment null
     */
    private String postRtrnHistId;

    /**
     * @ColumnName POST_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment null
     */
    private String postId;

    /**
     * @ColumnName POST_RETN_RSN
     * @ColumnType CLOB
     * @ColumnComment null
     */
    private String postRetnRsn;

    /**
     * @ColumnName POST_SUBMIT_CHG_CN
     * @ColumnType CLOB
     * @ColumnComment null
     */
    private String postSubmitChgCn;

    /**
     * @ColumnName CRT_DT
     * @ColumnType DATE
     * @ColumnComment null
     */
    private String crtDt;

    /**
     * @ColumnName CRTR
     * @ColumnType VARCHAR2(50)
     * @ColumnComment null
     */
    private String crtr;

    /**
     * @ColumnName UPDT_DT
     * @ColumnType DATE
     * @ColumnComment null
     */
    private String updtDt;

    /**
     * @ColumnName MTTR
     * @ColumnType VARCHAR2(50)
     * @ColumnComment null
     */
    private String mttr;

    /**
     * @ColumnName DLT_YN
     * @ColumnType CHAR(1)
     * @ColumnComment null
     */
    private String dltYn;

    /**
     * @ColumnName RETN_FL_GRP_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment null
     */
    private String retnFlGrpId;

    /**
     * @ColumnName SUBMIT_FL_GRP_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment null
     */
    private String submitFlGrpId;

    public String getPostRtrnHistId() {
        return this.postRtrnHistId;
    }
    
    public void setPostRtrnHistId(String postRtrnHistId) {
        this.postRtrnHistId = postRtrnHistId;
    }
    
    public String getPostId() {
        return this.postId;
    }
    
    public void setPostId(String postId) {
        this.postId = postId;
    }
    
    public String getPostRetnRsn() {
        return this.postRetnRsn;
    }
    
    public void setPostRetnRsn(String postRetnRsn) {
        this.postRetnRsn = postRetnRsn;
    }
    
    public String getPostSubmitChgCn() {
        return this.postSubmitChgCn;
    }
    
    public void setPostSubmitChgCn(String postSubmitChgCn) {
        this.postSubmitChgCn = postSubmitChgCn;
    }
    
    public String getCrtDt() {
        return this.crtDt;
    }
    
    public void setCrtDt(String crtDt) {
        this.crtDt = crtDt;
    }
    
    public String getCrtr() {
        return this.crtr;
    }
    
    public void setCrtr(String crtr) {
        this.crtr = crtr;
    }
    
    public String getUpdtDt() {
        return this.updtDt;
    }
    
    public void setUpdtDt(String updtDt) {
        this.updtDt = updtDt;
    }
    
    public String getMttr() {
        return this.mttr;
    }
    
    public void setMttr(String mttr) {
        this.mttr = mttr;
    }
    
    public String getDltYn() {
        return this.dltYn;
    }
    
    public void setDltYn(String dltYn) {
        this.dltYn = dltYn;
    }
    
    public String getRetnFlGrpId() {
        return this.retnFlGrpId;
    }
    
    public void setRetnFlGrpId(String retnFlGrpId) {
        this.retnFlGrpId = retnFlGrpId;
    }
    
    public String getSubmitFlGrpId() {
        return this.submitFlGrpId;
    }
    
    public void setSubmitFlGrpId(String submitFlGrpId) {
        this.submitFlGrpId = submitFlGrpId;
    }
    
    @Override
    public String toString() {
        return "PostRtrnHistVO(postRtrnHistId: " + postRtrnHistId + ", postId: " + postId + ", postRetnRsn: " + postRetnRsn + ", postSubmitChgCn: " + postSubmitChgCn + ", crtDt: " + crtDt + ", crtr: " + crtr + ", updtDt: " + updtDt + ", mttr: " + mttr + ", dltYn: " + dltYn + ", retnFlGrpId: " + retnFlGrpId + ", submitFlGrpId: " + submitFlGrpId + ", )";
    }
}