package com.ktdsuniversity.edu.domain.chat.vo;

import com.ktdsuniversity.edu.global.common.BaseVO;


/**
 * @TableName CHT_PRTCPNT
 * @TableComment null
 */
public class ChatParticipantVO extends BaseVO{

    /**
     * @ColumnName CHT_RM_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 채팅방을 구분지을 고유한 아이디
     */
    private String chtRmId;

    /**
     * @ColumnName USR_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 유저를 구분하는 아이디
     */
    private String usrId;

    /**
     * @ColumnName LFT_YN
     * @ColumnType CHAR(1)
     * @ColumnComment 회원이 채팅방을 나갔는지 여부
     */
    private String lftYn;

    public String getChtRmId() {
        return this.chtRmId;
    }
    
    public void setChtRmId(String chtRmId) {
        this.chtRmId = chtRmId;
    }
    
    public String getUsrId() {
        return this.usrId;
    }
    
    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }
    
    public String getLftYn() {
        return this.lftYn;
    }
    
    public void setLftYn(String lftYn) {
        this.lftYn = lftYn;
    }

	@Override
	public String toString() {
		return "ChtParticipantVO [chtRmId=" + chtRmId + ", usrId=" + usrId + ", lftYn=" + lftYn + ", toString()="
				+ super.toString() + "]";
	}
    
   
}