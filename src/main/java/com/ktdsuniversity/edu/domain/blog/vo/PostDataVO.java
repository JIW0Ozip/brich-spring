package com.ktdsuniversity.edu.domain.blog.vo;

import com.ktdsuniversity.edu.global.common.BaseVO;

/**
 * @TableName PST_DA
 * @TableComment null
 */
public class PostDataVO extends BaseVO{

    /**
     * @ColumnName PST_DA_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 포스팅 데이터의 고유 아이디
     */
    private String pstDaId;

    /**
     * @ColumnName USR_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 포스트가 게재된 블로그의 유저 아이디
     */
    private String usrId;

    /**
     * @ColumnName PST_URL
     * @ColumnType VARCHAR2(2048)
     * @ColumnComment 포스트의 주소 url
     */
    private String pstUrl;

    /**
     * @ColumnName PST_CMNT
     * @ColumnType NUMBER(6, 0)
     * @ColumnComment 포스트의 댓글 수
     */
    private int pstCmnt;

    /**
     * @ColumnName PST_LK
     * @ColumnType NUMBER(6, 0)
     * @ColumnComment 포스트의 공감 수
     */
    private int pstLk;

    /**
     * @ColumnName PSTD_DT
     * @ColumnType DATE
     * @ColumnComment 해당 포스트를 게재한 날짜
     */
    private String pstdDt;

    public String getPstDaId() {
        return this.pstDaId;
    }
    
    public void setPstDaId(String pstDaId) {
        this.pstDaId = pstDaId;
    }
    
    public String getUsrId() {
        return this.usrId;
    }
    
    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }
    
    public String getPstUrl() {
        return this.pstUrl;
    }
    
    public void setPstUrl(String pstUrl) {
        this.pstUrl = pstUrl;
    }
    
    public int getPstCmnt() {
        return this.pstCmnt;
    }
    
    public void setPstCmnt(int pstCmnt) {
        this.pstCmnt = pstCmnt;
    }
    
    public int getPstLk() {
        return this.pstLk;
    }
    
    public void setPstLk(int pstLk) {
        this.pstLk = pstLk;
    }
    
    public String getPstdDt() {
        return this.pstdDt;
    }
    
    public void setPstdDt(String pstdDt) {
        this.pstdDt = pstdDt;
    }

	@Override
	public String toString() {
		return "PostDataVO [pstDaId=" + pstDaId + ", usrId=" + usrId + ", pstUrl=" + pstUrl + ", pstCmnt=" + pstCmnt
				+ ", pstLk=" + pstLk + ", pstdDt=" + pstdDt + ", toString()=" + super.toString() + "]";
	}
 
    
}