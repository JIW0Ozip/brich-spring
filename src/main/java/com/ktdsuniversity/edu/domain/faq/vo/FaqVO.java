package com.ktdsuniversity.edu.domain.faq.vo;

import com.ktdsuniversity.edu.global.common.BaseVO;

/**
 * @TableName FAQ
 * @TableComment null
 */
public class FaqVO extends BaseVO{

    /**
     * @ColumnName FAQ_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 도움말을 구분지을 고유한 아이디
     */
    private String faqId;

    /**
     * @ColumnName FAQ_TITLE
     * @ColumnType VARCHAR2(100)
     * @ColumnComment 도움말의 제목(자주 들어오는 질문 내용)
     */
    private String faqTitle;

    /**
     * @ColumnName FAQ_CN
     * @ColumnType CLOB
     * @ColumnComment 도움말의 내용(질문에 대한 답변)
     */
    private String faqCn;

    /**
     * @ColumnName CTG_CD
     * @ColumnType CHAR(4)
     * @ColumnComment 도움말의 카테고리(결제, 서비스이용.....)
     */
    private String ctgCd;

    public String getFaqId() {
        return this.faqId;
    }
    
    public void setFaqId(String faqId) {
        this.faqId = faqId;
    }
    
    public String getFaqTitle() {
        return this.faqTitle;
    }
    
    public void setFaqTitle(String faqTitle) {
        this.faqTitle = faqTitle;
    }
    
    public String getFaqCn() {
        return this.faqCn;
    }
    
    public void setFaqCn(String faqCn) {
        this.faqCn = faqCn;
    }
    
    public String getCtgCd() {
        return this.ctgCd;
    }
    
    public void setCtgCd(String ctgCd) {
        this.ctgCd = ctgCd;
    }

	@Override
	public String toString() {
		return "FaqVO [faqId=" + faqId + ", faqTitle=" + faqTitle + ", faqCn=" + faqCn + ", ctgCd=" + ctgCd
				+ ", toString()=" + super.toString() + "]";
	}
 
   
}