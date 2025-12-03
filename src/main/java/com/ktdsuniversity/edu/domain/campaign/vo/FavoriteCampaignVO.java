package com.ktdsuniversity.edu.domain.campaign.vo;

import com.ktdsuniversity.edu.global.common.BaseVO;

/**
 * @TableName FAV_CMPN
 * @TableComment 관심 캠페인
 */
public class FavoriteCampaignVO extends BaseVO{

    /**
     * @ColumnName BLG_USR_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 관심 누른 블로거 아이디
     */
    private String blgUsrId;

    /**
     * @ColumnName CMPN_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment  캠페인 ID
     */
    private String cmpnId;

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

	@Override
	public String toString() {
		return "FavoriteCampaignVO [blgUsrId=" + blgUsrId + ", cmpnId=" + cmpnId + ", toString()=" + super.toString()
				+ "]";
	}
    
    
}