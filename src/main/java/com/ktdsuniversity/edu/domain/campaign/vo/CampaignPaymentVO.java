package com.ktdsuniversity.edu.domain.campaign.vo;

import com.ktdsuniversity.edu.global.common.BaseVO;

/**
 * @TableName CMPN_PYMNT
 * @TableComment null
 */
public class CampaignPaymentVO extends BaseVO{

    /**
     * @ColumnName CMPN_PYMNT_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 캠페인 결제를  구분지을 고유한 아이디
     */
    private String cmpnPymntId;

    /**
     * @ColumnName CMPN_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 캠페인 테이블 PK
     */
    private String cmpnId;

    /**
     * @ColumnName PYMNT_CD
     * @ColumnType CHAR(4)
     * @ColumnComment 공통 테이블의 결제 상태코드
     */
    private String pymntCd;

    /**
     * @ColumnName AMNT
     * @ColumnType NUMBER(7, 0)
     * @ColumnComment 캠페인 결제 금액
     */
    private int amnt;

    private String orderId;
    
    private String paymentKey;
    
    public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPaymentKey() {
		return this.paymentKey;
	}

	public void setPaymentKey(String paymentKey) {
		this.paymentKey = paymentKey;
	}




  
    public String getCmpnPymntId() {
        return this.cmpnPymntId;
    }
    
    public void setCmpnPymntId(String cmpnPymntId) {
        this.cmpnPymntId = cmpnPymntId;
    }
    
    public String getCmpnId() {
        return this.cmpnId;
    }
    
    public void setCmpnId(String cmpnId) {
        this.cmpnId = cmpnId;
    }
    
    public String getPymntCd() {
        return this.pymntCd;
    }
    
    public void setPymntCd(String pymntCd) {
        this.pymntCd = pymntCd;
    }
    
    public int getAmnt() {
        return this.amnt;
    }
    
    public void setAmnt(int amnt) {
        this.amnt = amnt;
    }
    


	@Override
	public String toString() {
		return "CampaignPaymentVO [cmpnPymntId=" + cmpnPymntId + ", cmpnId=" + cmpnId + ", pymntCd=" + pymntCd
				+ ", amnt=" + amnt + ", orderId=" + orderId + ", paymentKey=" + paymentKey + "]";
	}
    
   
}