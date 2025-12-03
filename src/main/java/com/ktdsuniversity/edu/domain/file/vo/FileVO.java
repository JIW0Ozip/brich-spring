package com.ktdsuniversity.edu.domain.file.vo;

import com.ktdsuniversity.edu.global.common.BaseVO;

/**
 * @TableName FL
 * @TableComment null
 */
public class FileVO extends BaseVO{

    /**
     * @ColumnName FL_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 첨부 파일 구분을 위한 고유 아이디
     */
    private String flId;

    /**
     * @ColumnName FL_GRP_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 첨부 파일 그룹을 구분지을 고유한 아이디
     */
    private String flGrpId;

    /**
     * @ColumnName FL_NM
     * @ColumnType VARCHAR2(100)
     * @ColumnComment 파일의 이름
     */
    private String flNm;

    /**
     * @ColumnName FL_SZ
     * @ColumnType NUMBER(10, 0)
     * @ColumnComment 파일의 크기 (byte)
     */
    private long flSz;

    /**
     * @ColumnName FL_TYP
     * @ColumnType VARCHAR2(30)
     * @ColumnComment 파일의 타입 (image/png....)
     */
    private String flTyp;

    /**
     * @ColumnName FL_PTH
     * @ColumnType VARCHAR2(200)
     * @ColumnComment 파일의 경로
     */
    private String flPth;

    /**
     * @ColumnName OBFSCTIN_FL_NM
     * @ColumnType VARCHAR2(100)
     * @ColumnComment 난독화 된 파일의 이름
     */
    private String obfsctinFlNm;

    /**
     * @ColumnName DWNLD_CNT
     * @ColumnType NUMBER(7, 0)
     * @ColumnComment 파일이 다운로드된 횟수
     */
    private int dwnldCnt;

    public String getFlId() {
        return this.flId;
    }
    
    public void setFlId(String flId) {
        this.flId = flId;
    }
    
    public String getFlGrpId() {
        return this.flGrpId;
    }
    
    public void setFlGrpId(String flGrpId) {
        this.flGrpId = flGrpId;
    }
    
    public String getFlNm() {
        return this.flNm;
    }
    
    public void setFlNm(String flNm) {
        this.flNm = flNm;
    }

    public long getFlSz() {
		return this.flSz;
	}

	public void setFlSz(long flSz) {
		this.flSz = flSz;
	}

	public String getFlTyp() {
        return this.flTyp;
    }
    
    public void setFlTyp(String flTyp) {
        this.flTyp = flTyp;
    }
    
    public String getFlPth() {
        return this.flPth;
    }
    
    public void setFlPth(String flPth) {
        this.flPth = flPth;
    }
    
    public String getObfsctinFlNm() {
        return this.obfsctinFlNm;
    }
    
    public void setObfsctinFlNm(String obfsctinFlNm) {
        this.obfsctinFlNm = obfsctinFlNm;
    }
    
    public int getDwnldCnt() {
        return this.dwnldCnt;
    }
    
    public void setDwnldCnt(int dwnldCnt) {
        this.dwnldCnt = dwnldCnt;
    }

	@Override
	public String toString() {
		return "FileVO [flId=" + flId + ", flGrpId=" + flGrpId + ", flNm=" + flNm + ", flSz=" + flSz + ", flTyp="
				+ flTyp + ", flPth=" + flPth + ", obfsctinFlNm=" + obfsctinFlNm + ", dwnldCnt=" + dwnldCnt
				+ ", toString()=" + super.toString() + "]";
	}
    
    
}