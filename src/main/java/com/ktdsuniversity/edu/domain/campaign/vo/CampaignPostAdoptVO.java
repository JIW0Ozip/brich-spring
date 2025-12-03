package com.ktdsuniversity.edu.domain.campaign.vo;

import com.ktdsuniversity.edu.global.common.BaseVO;

/**
 * @TableName CMPN_PST_ADPT
 * @TableComment 캠페인의 대한 신청자(블로거)와 제출 포스트 관리 테이블
 */
public class CampaignPostAdoptVO extends BaseVO{

	private String crtDt;
	
    /**
     * @ColumnName CMPN_PST_ADPT_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 캠페인 포스트 채택 테이블의 PK
     */
    private String cmpnPstAdptId;

    /**
     * @ColumnName BLG_USR_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 캠페인 신청한 블로거 ID
     */
    private String blgUsrId;

    /**
     * @ColumnName CMPN_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 캠페인 아이디
     */
    private String cmpnId;

    /**
     * @ColumnName ADPT_YN
     * @ColumnType CHAR(1)
     * @ColumnComment 신청자 채택 여부(Y/N)
     */
    private String adptYn;

    /**
     * @ColumnName PST_URL
     * @ColumnType VARCHAR2(1024)
     * @ColumnComment 채택 되었을 시 제출한 포스팅 URL
     */
    private String pstUrl;

    /**
     * @ColumnName PST_STTS_CD
     * @ColumnType CHAR(4)
     * @ColumnComment 포스팅 상태 코드(CM_CD.CD_ID)
     */
    private String pstSttsCd;

    /**
     * @ColumnName PST_DDLN
     * @ColumnType DATE
     * @ColumnComment 포스팅 마감일
     */
    private String pstDdln;

    /**
     * @ColumnName PST_SBMT_DT
     * @ColumnType DATE
     * @ColumnComment 포스팅 제출일
     */
    private String pstSbmtDt;

    /**
     * @ColumnName PST_TITLE
     * @ColumnType VARCHAR2(200)
     * @ColumnComment 포스팅 제목
     */
    private String pstTitle;

    
    
    public String getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(String crtDt) {
		this.crtDt = crtDt;
	}

	public String getCmpnPstAdptId() {
        return this.cmpnPstAdptId;
    }
    
    public void setCmpnPstAdptId(String cmpnPstAdptId) {
        this.cmpnPstAdptId = cmpnPstAdptId;
    }
    
    public String getBlgUsrId() {
        return this.blgUsrId;
    }
    
    public void setBlgUsrId(String blgUsrId) {
        this.blgUsrId = blgUsrId;
    }
    
    public String getCmpnId() {
        return this.cmpnId;
    }
    
    public void setCmpnId(String cmpnId) {
        this.cmpnId = cmpnId;
    }
    
    public String getAdptYn() {
        return this.adptYn;
    }
    
    public void setAdptYn(String adptYn) {
        this.adptYn = adptYn;
    }
    
    public String getPstUrl() {
        return this.pstUrl;
    }
    
    public void setPstUrl(String pstUrl) {
        this.pstUrl = pstUrl;
    }
    
    public String getPstSttsCd() {
        return this.pstSttsCd;
    }
    
    public void setPstSttsCd(String pstSttsCd) {
        this.pstSttsCd = pstSttsCd;
    }
    
    public String getPstDdln() {
        return this.pstDdln;
    }
    
    public void setPstDdln(String pstDdln) {
        this.pstDdln = pstDdln;
    }
    
    public String getPstSbmtDt() {
        return this.pstSbmtDt;
    }
    
    public void setPstSbmtDt(String pstSbmtDt) {
        this.pstSbmtDt = pstSbmtDt;
    }
    
    public String getPstTitle() {
        return this.pstTitle;
    }
    
    public void setPstTitle(String pstTitle) {
        this.pstTitle = pstTitle;
    }

	@Override
	public String toString() {
		return "CampaignPostAdoptVO [cmpnPstAdptId=" + cmpnPstAdptId + ", blgUsrId=" + blgUsrId + ", cmpnId=" + cmpnId
				+ ", adptYn=" + adptYn + ", pstUrl=" + pstUrl + ", pstSttsCd=" + pstSttsCd + ", pstDdln=" + pstDdln
				+ ", pstSbmtDt=" + pstSbmtDt + ", pstTitle=" + pstTitle + ", toString()=" + super.toString() + "]";
	}
    
    
}