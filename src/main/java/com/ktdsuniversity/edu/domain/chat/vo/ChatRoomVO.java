package com.ktdsuniversity.edu.domain.chat.vo;

import com.ktdsuniversity.edu.global.common.BaseVO;

/**
 * @TableName CHT_RM
 * @TableComment null
 */
public class ChatRoomVO extends BaseVO{

    /**
     * @ColumnName CHT_RM_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 채팅방을 구분지을 고유한 아이디
     */
    private String chtRmId;

    /**
     * @ColumnName CMPN_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 캠페인 테이블 PK
     */
    private String cmpnId;


    public String getChtRmId() {
        return this.chtRmId;
    }
    
    public void setChtRmId(String chtRmId) {
        this.chtRmId = chtRmId;
    }
    
    public String getCmpnId() {
        return this.cmpnId;
    }
    
    public void setCmpnId(String cmpnId) {
        this.cmpnId = cmpnId;
    }

	@Override
	public String toString() {
		return "ChatRoomVO [chtRmId=" + chtRmId + ", cmpnId=" + cmpnId + ", toString()=" + super.toString() + "]";
	}
    
    
}