package com.ktdsuniversity.edu.global.common;

/**
 * 공통 컬럼 
 */
public class BaseVO {

	 /**
     * @ColumnName CRT_DT
     * @ColumnType DATE
     * @ColumnComment 데이터 생성일
     */
    private String crtDt;

    /**
     * @ColumnName CRTR
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 데이터 생성자
     */
    private String crtr;

    /**
     * @ColumnName UPDT_DT
     * @ColumnType DATE
     * @ColumnComment 데이터 수정일
     */
    private String updtDt;

    /**
     * @ColumnName MTTR
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 데이터 수정자
     */
    private String mttr;

    /**
     * @ColumnName DLT_YN
     * @ColumnType CHAR(1)
     * @ColumnComment 데이터 삭제 여부
     */
    private String dltYn;

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
		return "BaseVO [crtDt=" + crtDt + ", crtr=" + crtr + ", updtDt=" + updtDt + ", mttr=" + mttr + ", dltYn="
				+ dltYn + "]";
	}
	
}
