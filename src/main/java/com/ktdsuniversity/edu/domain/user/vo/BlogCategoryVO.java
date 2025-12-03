package com.ktdsuniversity.edu.domain.user.vo;

import com.ktdsuniversity.edu.global.common.BaseVO;

/**
 * @TableName BLG_CTG
 * @TableComment null
 */
public class BlogCategoryVO extends BaseVO{

    /**
     * @ColumnName USR_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 유저를 구분하는 아이디
     */
    private String usrId;

    /**
     * @ColumnName CD_ID
     * @ColumnType CHAR(4)
     * @ColumnComment 공통 코드 PK
     */
    private String cdId;

    public String getUsrId() {
        return this.usrId;
    }
    
    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }
    
    public String getCdId() {
        return this.cdId;
    }
    
    public void setCdId(String cdId) {
        this.cdId = cdId;
    }

	@Override
	public String toString() {
		return "BlogCategoryVO [usrId=" + usrId + ", cdId=" + cdId + ", toString()=" + super.toString() + "]";
	}
    
    
    
}