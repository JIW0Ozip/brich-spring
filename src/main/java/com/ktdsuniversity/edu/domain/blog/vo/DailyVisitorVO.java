package com.ktdsuniversity.edu.domain.blog.vo;

import com.ktdsuniversity.edu.global.common.BaseVO;

/**
 * @TableName DLY_VSTR
 * @TableComment null
 */
public class DailyVisitorVO extends BaseVO{

    /**
     * @ColumnName VST_DT
     * @ColumnType DATE
     * @ColumnComment 블로그에 방문이 발생한 날짜
     */
    private String vstDt;

    /**
     * @ColumnName USR_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 해당 블로그의 유저 아이디
     */
    private String usrId;

    /**
     * @ColumnName VSTR_CNT
     * @ColumnType NUMBER(7, 0)
     * @ColumnComment 블로그에 방문한 방문자의 수
     */
    private int vstrCnt;

    public String getVstDt() {
        return this.vstDt;
    }
    
    public void setVstDt(String vstDt) {
        this.vstDt = vstDt;
    }
    
    public String getUsrId() {
        return this.usrId;
    }
    
    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }
    
    public int getVstrCnt() {
        return this.vstrCnt;
    }
    
    public void setVstrCnt(int vstrCnt) {
        this.vstrCnt = vstrCnt;
    }

	@Override
	public String toString() {
		return "DailyVisitorVO [vstDt=" + vstDt + ", usrId=" + usrId + ", vstrCnt=" + vstrCnt + ", toString()="
				+ super.toString() + "]";
	}
    
    
}