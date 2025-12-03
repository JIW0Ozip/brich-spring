package com.ktdsuniversity.edu.domain.user.vo;

import com.ktdsuniversity.edu.global.common.BaseVO;

/**
 * @TableName USR_AR
 * @TableComment null
 */
public class UserAreaVO extends BaseVO{

    /**
     * @ColumnName USR_AR_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 유저 활동 지역을 구분하는 아이디
     */
    private String usrArId;

    /**
     * @ColumnName USR_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 유저의 아이디
     */
    private String usrId;

    /**
     * @ColumnName AR_ID
     * @ColumnType CHAR(4)
     * @ColumnComment 공통 테이블의 지역 코드
     */
    private String arId;

    public String getUsrArId() {
        return this.usrArId;
    }
    
    public void setUsrArId(String usrArId) {
        this.usrArId = usrArId;
    }
    
    public String getUsrId() {
        return this.usrId;
    }
    
    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }
    
    public String getArId() {
        return this.arId;
    }
    
    public void setArId(String arId) {
        this.arId = arId;
    }

	@Override
	public String toString() {
		return "UserAreaVO [usrArId=" + usrArId + ", usrId=" + usrId + ", arId=" + arId + ", toString()="
				+ super.toString() + "]";
	}
    
    
}