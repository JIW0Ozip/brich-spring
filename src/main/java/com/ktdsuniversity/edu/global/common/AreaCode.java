package com.ktdsuniversity.edu.global.common;

/**
 * @TableName AR_CD
 * @TableComment 지역 코드
 */
public class AreaCode extends BaseVO{

    /**
     * @ColumnName CD_ID
     * @ColumnType CHAR(4)
     * @ColumnComment 공통코드 PK
     */
    private String cdId;

    /**
     * @ColumnName CD_NM
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 공통코드명
     */
    private String cdNm;

    /**
     * @ColumnName PRNT_CD_ID
     * @ColumnType CHAR(4)
     * @ColumnComment 상위코드ID
     */
    private String prntCdId;

    public String getCdId() {
        return this.cdId;
    }
    
    public void setCdId(String cdId) {
        this.cdId = cdId;
    }
    
    public String getCdNm() {
        return this.cdNm;
    }
    
    public void setCdNm(String cdNm) {
        this.cdNm = cdNm;
    }
    
    public String getPrntCdId() {
        return this.prntCdId;
    }
    
    public void setPrntCdId(String prntCdId) {
        this.prntCdId = prntCdId;
    }

	@Override
	public String toString() {
		return "AreaCode [cdId=" + cdId + ", cdNm=" + cdNm + ", prntCdId=" + prntCdId + ", toString()="
				+ super.toString() + "]";
	}

    
}