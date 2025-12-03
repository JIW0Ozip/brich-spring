package com.ktdsuniversity.edu.domain.user.vo;

import com.ktdsuniversity.edu.domain.blog.vo.BlogIndexVO;
import com.ktdsuniversity.edu.global.common.BaseVO;

/**
 * @TableName USR
 * @TableComment null
 */
public class UserVO extends BaseVO{

    /**
     * @ColumnName USR_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 유저를 구분하는 아이디
     */
    private String usrId;

    /**
     * @ColumnName LOG_ID
     * @ColumnType VARCHAR2(15)
     * @ColumnComment 유저가 로그인에 사용할 아이디
     */
    private String logId;

    /**
     * @ColumnName EML
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 유저가 아이디 찾기에 사용할 이메일
     */
    private String eml;

    /**
     * @ColumnName PSWRD
     * @ColumnType VARCHAR2(20)
     * @ColumnComment 유저가 로그인에 사용할 비밀번호
     */
    private String pswrd;

    /**
     * @ColumnName NM
     * @ColumnType VARCHAR2(10)
     * @ColumnComment 유저의 이름
     */
    private String nm;

    /**
     * @ColumnName SALT
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 비밀번호 암호화에 사용한 SALT
     */
    private String salt;

    /**
     * @ColumnName LGN_PL_CNT
     * @ColumnType NUMBER(1, 0)
     * @ColumnComment 유저가 로그인에 실패한 횟수(5회 실패시 블락)
     */
    private int lgnPlCnt;

    /**
     * @ColumnName BLCK_YN
     * @ColumnType CHAR(1)
     * @ColumnComment 유저 계정의 로그인을 허용할지 여부
     */
    private String blckYn;

    /**
     * @ColumnName RCNT_LGN_BLCK_DT
     * @ColumnType DATE
     * @ColumnComment 유저가 최근 블락당한 일시
     */
    private String rcntLgnBlckDt;

    /**
     * @ColumnName RCNT_LGN_FL_DT
     * @ColumnType DATE
     * @ColumnComment 유저가 최근 로그인 실패한 일시
     */
    private String rcntLgnFlDt;

    /**
     * @ColumnName RCNT_LGN_SCS_DT
     * @ColumnType DATE
     * @ColumnComment 유저가 최근 로그인 성공한 일시
     */
    private String rcntLgnScsDt;

    /**
     * @ColumnName AUTR
     * @ColumnType CHAR(4)
     * @ColumnComment 유저의 유형을 구분짓는 코드
     */
    private String autr;

    /**
     * @ColumnName SBSCRPTN_EXPRS_DT
     * @ColumnType DATE
     * @ColumnComment 구독 유저의 구독 만료일
     */
    private String sbscrptnExprsDt;

    /**
     * @ColumnName PNLT_CNT
     * @ColumnType NUMBER(1, 0)
     * @ColumnComment 유저가 받은 징계 횟수
     */
    private int pnltCnt;

    /**
     * @ColumnName RCNT_BLG_CRTFCTN_DT
     * @ColumnType DATE
     * @ColumnComment 유저가 최근 블로그를 인증한 일시
     */
    private String rcntBlgCrtfctnDt;

    /**
     * @ColumnName BLG_ADDRS
     * @ColumnType VARCHAR2(1024)
     * @ColumnComment 유저가 인증한 블로그 주소
     */
    private String blgAddrs;

    /**
     * @ColumnName CRTFD_ADMIN
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 블로그를 인증한 관리자의 아이디
     */
    private String crtfdAdmin;

    /**
     * @ColumnName TTL_VSTR_CNT
     * @ColumnType NUMBER(9, 0)
     * @ColumnComment 유저의 블로그 전체 방문자 수
     */
    private int ttlVstrCnt;

    /**
     * @ColumnName AVRG_VSTR_CNT
     * @ColumnType NUMBER(6, 0)
     * @ColumnComment 유저의 블로그 5일 평균 방문자 수
     */
    private int avrgVstrCnt;

    /**
     * @ColumnName BLG_NGHBR_CNT
     * @ColumnType NUMBER(6, 0)
     * @ColumnComment 유저 블로그의 전체 이웃 수
     */
    private int blgNghbrCnt;

    /**
     * @ColumnName SCRP_CNT
     * @ColumnType NUMBER(6, 0)
     * @ColumnComment 유저 블로그의 전체 스크랩 수
     */
    private int scrpCnt;

    /**
     * @ColumnName CMPNY
     * @ColumnType VARCHAR2(30)
     * @ColumnComment 광고주의 회사 명
     */
    private String cmpny;

    /**
     * @ColumnName FL_GRP_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 사업자 등록증의 파일 그룹 아이디
     */
    private String flGrpId;
    
    private String blgTitle;

    
    private BlogIndexVO blogIndexVO;
    
    
    public BlogIndexVO getBlogIndexVO() {
		return this.blogIndexVO;
	}

	public void setBlogIndexVO(BlogIndexVO blogIndexVO) {
		this.blogIndexVO = blogIndexVO;
	}

	public String getUsrId() {
        return this.usrId;
    }
    
    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }
    
    public String getLogId() {
        return this.logId;
    }
    
    public void setLogId(String logId) {
        this.logId = logId;
    }
    
    public String getEml() {
        return this.eml;
    }
    
    public void setEml(String eml) {
        this.eml = eml;
    }
    
    public String getPswrd() {
        return this.pswrd;
    }
    
    public void setPswrd(String pswrd) {
        this.pswrd = pswrd;
    }
    
    public String getNm() {
        return this.nm;
    }
    
    public void setNm(String nm) {
        this.nm = nm;
    }
    
    public String getSalt() {
        return this.salt;
    }
    
    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    public int getLgnPlCnt() {
        return this.lgnPlCnt;
    }
    
    public void setLgnPlCnt(int lgnPlCnt) {
        this.lgnPlCnt = lgnPlCnt;
    }
    
    public String getBlckYn() {
        return this.blckYn;
    }
    
    public void setBlckYn(String blckYn) {
        this.blckYn = blckYn;
    }
    
    public String getRcntLgnBlckDt() {
        return this.rcntLgnBlckDt;
    }
    
    public void setRcntLgnBlckDt(String rcntLgnBlckDt) {
        this.rcntLgnBlckDt = rcntLgnBlckDt;
    }
    
    public String getRcntLgnFlDt() {
        return this.rcntLgnFlDt;
    }
    
    public void setRcntLgnFlDt(String rcntLgnFlDt) {
        this.rcntLgnFlDt = rcntLgnFlDt;
    }
    
    public String getRcntLgnScsDt() {
        return this.rcntLgnScsDt;
    }
    
    public void setRcntLgnScsDt(String rcntLgnScsDt) {
        this.rcntLgnScsDt = rcntLgnScsDt;
    }
    
    public String getAutr() {
        return this.autr;
    }
    
    public void setAutr(String autr) {
        this.autr = autr;
    }
    
    public String getSbscrptnExprsDt() {
        return this.sbscrptnExprsDt;
    }
    
    public void setSbscrptnExprsDt(String sbscrptnExprsDt) {
        this.sbscrptnExprsDt = sbscrptnExprsDt;
    }
    
    public int getPnltCnt() {
        return this.pnltCnt;
    }
    
    public void setPnltCnt(int pnltCnt) {
        this.pnltCnt = pnltCnt;
    }
    
    public String getRcntBlgCrtfctnDt() {
        return this.rcntBlgCrtfctnDt;
    }
    
    public void setRcntBlgCrtfctnDt(String rcntBlgCrtfctnDt) {
        this.rcntBlgCrtfctnDt = rcntBlgCrtfctnDt;
    }
    
    public String getBlgAddrs() {
        return this.blgAddrs;
    }
    
    public void setBlgAddrs(String blgAddrs) {
        this.blgAddrs = blgAddrs;
    }
    
    public String getCrtfdAdmin() {
        return this.crtfdAdmin;
    }
    
    public void setCrtfdAdmin(String crtfdAdmin) {
        this.crtfdAdmin = crtfdAdmin;
    }
    
    public int getTtlVstrCnt() {
        return this.ttlVstrCnt;
    }
    
    public void setTtlVstrCnt(int ttlVstrCnt) {
        this.ttlVstrCnt = ttlVstrCnt;
    }
    
    public int getAvrgVstrCnt() {
        return this.avrgVstrCnt;
    }
    
    public void setAvrgVstrCnt(int avrgVstrCnt) {
        this.avrgVstrCnt = avrgVstrCnt;
    }
    
    public int getBlgNghbrCnt() {
        return this.blgNghbrCnt;
    }
    
    public void setBlgNghbrCnt(int blgNghbrCnt) {
        this.blgNghbrCnt = blgNghbrCnt;
    }
    
    public int getScrpCnt() {
        return this.scrpCnt;
    }
    
    public void setScrpCnt(int scrpCnt) {
        this.scrpCnt = scrpCnt;
    }
    
    public String getCmpny() {
        return this.cmpny;
    }
    
    public void setCmpny(String cmpny) {
        this.cmpny = cmpny;
    }
    
    public String getFlGrpId() {
        return this.flGrpId;
    }
    
    public void setFlGrpId(String flGrpId) {
        this.flGrpId = flGrpId;
    }

	public String getBlgTitle() {
		return this.blgTitle;
	}

	public void setBlgTitle(String blgTitle) {
		this.blgTitle = blgTitle;
	}

	@Override
	public String toString() {
		return "UserVO [usrId=" + usrId + ", logId=" + logId + ", eml=" + eml + ", pswrd=" + pswrd + ", nm=" + nm
				+ ", salt=" + salt + ", lgnPlCnt=" + lgnPlCnt + ", blckYn=" + blckYn + ", rcntLgnBlckDt="
				+ rcntLgnBlckDt + ", rcntLgnFlDt=" + rcntLgnFlDt + ", rcntLgnScsDt=" + rcntLgnScsDt + ", autr=" + autr
				+ ", sbscrptnExprsDt=" + sbscrptnExprsDt + ", pnltCnt=" + pnltCnt + ", rcntBlgCrtfctnDt="
				+ rcntBlgCrtfctnDt + ", blgAddrs=" + blgAddrs + ", crtfdAdmin=" + crtfdAdmin + ", ttlVstrCnt="
				+ ttlVstrCnt + ", avrgVstrCnt=" + avrgVstrCnt + ", blgNghbrCnt=" + blgNghbrCnt + ", scrpCnt=" + scrpCnt
				+ ", cmpny=" + cmpny + ", flGrpId=" + flGrpId + ", toString()=" + super.toString() + "]";
	}
    
   
}