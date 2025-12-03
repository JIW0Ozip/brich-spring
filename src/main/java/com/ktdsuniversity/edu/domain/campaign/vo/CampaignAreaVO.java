package com.ktdsuniversity.edu.domain.campaign.vo;

import com.ktdsuniversity.edu.global.common.BaseVO;

/**
 * @TableName CMPN_AR
 * @TableComment null
 */
public class CampaignAreaVO extends BaseVO{

    /**
     * @ColumnName CMPN_AR_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 캠페인 활동 지역
     */
    private String cmpnArId;

    /**
     * @ColumnName CMPN_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 캠페인 테이블 PK
     */
    private String cmpnId;

    /**
     * @ColumnName AR_CD
     * @ColumnType CHAR(4)
     * @ColumnComment 공통 테이블의 지역 코드
     */
    private String arCd;


    public String getCmpnArId() {
        return this.cmpnArId;
    }
    
    public void setCmpnArId(String cmpnArId) {
        this.cmpnArId = cmpnArId;
    }
    
    public String getCmpnId() {
        return this.cmpnId;
    }
    
    public void setCmpnId(String cmpnId) {
        this.cmpnId = cmpnId;
    }
    
    public String getArCd() {
        return this.arCd;
    }
    
    public void setArCd(String arCd) {
        this.arCd = arCd;
    }

	@Override
	public String toString() {
		return "CampaignAreaVO [cmpnArId=" + cmpnArId + ", cmpnId=" + cmpnId + ", arCd=" + arCd + ", toString()="
				+ super.toString() + "]";
	}
    
    
    
}