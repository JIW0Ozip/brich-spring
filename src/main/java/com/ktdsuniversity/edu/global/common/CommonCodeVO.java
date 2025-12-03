package com.ktdsuniversity.edu.global.common;

/**
 * @TableName CM_CD
 * @TableComment 공통코드
 */
public class CommonCodeVO {

    /**
     * @ColumnName CD_ID
     * @ColumnType CHAR(4)
     * @ColumnComment 공통코드PK
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

    /**
     * @ColumnName USE_YN
     * @ColumnType CHAR(1)
     * @ColumnComment 사용여부
     */
    private String useYn;

    /**
     * @ColumnName SRT
     * @ColumnType NUMBER(4, 0)
     * @ColumnComment 코드정렬순서
     */
    private int srt;

    /**
     * @ColumnName KEY1
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 코드의 키1
     */
    private String key1;

    /**
     * @ColumnName VALUE1
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 코드의 값1
     */
    private String value1;

    /**
     * @ColumnName KEY2
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 코드의 키2
     */
    private String key2;

    /**
     * @ColumnName VALUE2
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 코드의 값2
     */
    private String value2;

    /**
     * @ColumnName KEY3
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 코드의 키3
     */
    private String key3;

    /**
     * @ColumnName VALUE3
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 코드의 값3
     */
    private String value3;

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
    
    public String getUseYn() {
        return this.useYn;
    }
    
    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }
    
    public int getSrt() {
        return this.srt;
    }
    
    public void setSrt(int srt) {
        this.srt = srt;
    }
    
    public String getKey1() {
        return this.key1;
    }
    
    public void setKey1(String key1) {
        this.key1 = key1;
    }
    
    public String getValue1() {
        return this.value1;
    }
    
    public void setValue1(String value1) {
        this.value1 = value1;
    }
    
    public String getKey2() {
        return this.key2;
    }
    
    public void setKey2(String key2) {
        this.key2 = key2;
    }
    
    public String getValue2() {
        return this.value2;
    }
    
    public void setValue2(String value2) {
        this.value2 = value2;
    }
    
    public String getKey3() {
        return this.key3;
    }
    
    public void setKey3(String key3) {
        this.key3 = key3;
    }
    
    public String getValue3() {
        return this.value3;
    }
    
    public void setValue3(String value3) {
        this.value3 = value3;
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
    
    @Override
    public String toString() {
        return "CmCdVO(cdId: " + cdId + ", cdNm: " + cdNm + ", prntCdId: " + prntCdId + ", useYn: " + useYn + ", srt: " + srt + ", key1: " + key1 + ", value1: " + value1 + ", key2: " + key2 + ", value2: " + value2 + ", key3: " + key3 + ", value3: " + value3 + ", crtDt: " + crtDt + ", crtr: " + crtr + ", updtDt: " + updtDt + ", mttr: " + mttr + ", dltYn: " + dltYn + ", )";
    }
}