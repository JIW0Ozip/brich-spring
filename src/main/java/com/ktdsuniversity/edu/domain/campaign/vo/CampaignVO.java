package com.ktdsuniversity.edu.domain.campaign.vo;

import java.util.List;

import com.ktdsuniversity.edu.global.common.BaseVO;

/**
 * @TableName CMPN
 * @TableComment null
 */
public class CampaignVO extends BaseVO{

    /**
     * @ColumnName CMPN_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 캠페인 테이블 PK
     */
    private String cmpnId;

    /**
     * @ColumnName USR_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 회원 테이블 PK
     */
    private String usrId;

    /**
     * @ColumnName CTG_CD
     * @ColumnType CHAR(4)
     * @ColumnComment 공통 테이블의 카테고리 코드
     */
    private String ctgCd;

    /**
     * @ColumnName STTS_CD
     * @ColumnType CHAR(4)
     * @ColumnComment 공통 테이블의 캠페인 상태코드
     */
    private String sttsCd;

    /**
     * @ColumnName PRSNN_PRC
     * @ColumnType NUMBER(6, 0)
     * @ColumnComment 신청 당시 인원당 가격
     */
    private int prsnnPrc;

    /**
     * @ColumnName DRTN_PRC
     * @ColumnType NUMBER(6, 0)
     * @ColumnComment 신청 당시 일당 가격
     */
    private int drtnPrc;

    /**
     * @ColumnName PRSNN_DSCNT
     * @ColumnType NUMBER(3, 1)
     * @ColumnComment 신청 당시 인원에 대한 할인율
     */
    private int prsnnDscnt;

    /**
     * @ColumnName DRTN_DSCNT
     * @ColumnType NUMBER(3, 1)
     * @ColumnComment 신청 당시 기간에 대한 할인율
     */
    private int drtnDscnt;

    /**
     * @ColumnName CMPN_PRNT_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 반려에 의한 다회차 신청 캠페인일 경우, 처음 등록했던 캠페인의 아이디를 부모아이디로 저장
     */
    private String cmpnPrntId;

    /**
     * @ColumnName RCRT_STRT_DT
     * @ColumnType DATE
     * @ColumnComment 캠페인 시작일
     */
    private String rcrtStrtDt;

    /**
     * @ColumnName RCRT_PRSNN
     * @ColumnType NUMBER(3, 0)
     * @ColumnComment 캠페인 모집 인원
     */
    private int rcrtPrsnn;

    /**
     * @ColumnName PRGRSS_DRTN
     * @ColumnType NUMBER(3, 0)
     * @ColumnComment 캠페인 진행 기간
     */
    private int prgrssDrtn;

    /**
     * @ColumnName RCRT_END_DT
     * @ColumnType DATE
     * @ColumnComment 캠페인 인원 모집 마감일
     */
    private String rcrtEndDt;

    /**
     * @ColumnName PST_END_DT
     * @ColumnType DATE
     * @ColumnComment 블로거 포스팅 데드라인
     */
    private String pstEndDt;

    /**
     * @ColumnName CMPN_END_DT
     * @ColumnType DATE
     * @ColumnComment 캠페인 종료일
     */
    private String cmpnEndDt;

    /**
     * @ColumnName RTRN_RSN
     * @ColumnType CLOB
     * @ColumnComment 캠페인 신청 후 반려 당했을 때, 반려 사유
     */
    private String rtrnRsn;

    /**
     * @ColumnName ADDRS
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 캠페인의 주소. 있을 경우 화면에 지도 표출
     */
    private String addrs;

    /**
     * @ColumnName ATTCH_GRP_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 캠페인의 파일이 있을 경우 해당 파일의 그룹 아이디
     */
    private String attchGrpId;

    /**
     * @ColumnName CMPN_TITLE
     * @ColumnType VARCHAR2(200)
     * @ColumnComment 사용자가 작성한 캠페인의 제목
     */
    private String cmpnTitle;

    /**
     * @ColumnName CMPN_CN
     * @ColumnType CLOB
     * @ColumnComment 사용자가 작성한 캠페인의 상세 내용
     */
    private String cmpnCn;

    /**
     * @ColumnName OFFR_CN
     * @ColumnType VARCHAR2(4000)
     * @ColumnComment 캠페인에서 블로거에게 제공할 내역
     */
    private String offrCn;

    /**
     * @ColumnName OFFR_PRC
     * @ColumnType NUMBER(8, 0)
     * @ColumnComment 캠페인에서 블로거에게 제공할 내역의 금액
     */
    private int offrPrc;

    /**
     * @ColumnName PST_MSSN
     * @ColumnType VARCHAR2(4000)
     * @ColumnComment 블로거에게 원하는 미션
     */
    private String pstMssn;

    /**
     * @ColumnName HSTG
     * @ColumnType VARCHAR2(200)
     * @ColumnComment 캠페인 소개 해시태그
     */
    private String hstg;

    /**
     * @ColumnName NTFCN
     * @ColumnType CLOB
     * @ColumnComment 상세 내용과 관련없는 추가 안내 사항
     */
    private String ntfcn;

    /**
     * @ColumnName VIEW
     * @ColumnType NUMBER(6, 0)
     * @ColumnComment 조회수
     */
    private int view;

    /**
     * @ColumnName CNFM_DT
     * @ColumnType DATE
     * @ColumnComment 캠페인 승인일
     */
    private String cnfmDt;
    
    
	public String getCmpnId() {
        return this.cmpnId;
    }
    
    public void setCmpnId(String cmpnId) {
        this.cmpnId = cmpnId;
    }
    
    public String getUsrId() {
        return this.usrId;
    }
    
    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }
    
    public String getCtgCd() {
        return this.ctgCd;
    }
    
    public void setCtgCd(String ctgCd) {
        this.ctgCd = ctgCd;
    }
    
    public String getSttsCd() {
        return this.sttsCd;
    }
    
    public void setSttsCd(String sttsCd) {
        this.sttsCd = sttsCd;
    }
    
    public int getPrsnnPrc() {
        return this.prsnnPrc;
    }
    
    public void setPrsnnPrc(int prsnnPrc) {
        this.prsnnPrc = prsnnPrc;
    }
    
    public int getDrtnPrc() {
        return this.drtnPrc;
    }
    
    public void setDrtnPrc(int drtnPrc) {
        this.drtnPrc = drtnPrc;
    }
    
    public int getPrsnnDscnt() {
        return this.prsnnDscnt;
    }
    
    public void setPrsnnDscnt(int prsnnDscnt) {
        this.prsnnDscnt = prsnnDscnt;
    }
    
    public int getDrtnDscnt() {
        return this.drtnDscnt;
    }
    
    public void setDrtnDscnt(int drtnDscnt) {
        this.drtnDscnt = drtnDscnt;
    }
    
    public String getCmpnPrntId() {
        return this.cmpnPrntId;
    }
    
    public void setCmpnPrntId(String cmpnPrntId) {
        this.cmpnPrntId = cmpnPrntId;
    }
    
    public String getRcrtStrtDt() {
        return this.rcrtStrtDt;
    }
    
    public void setRcrtStrtDt(String rcrtStrtDt) {
        this.rcrtStrtDt = rcrtStrtDt;
    }
    
    public int getRcrtPrsnn() {
        return this.rcrtPrsnn;
    }
    
    public void setRcrtPrsnn(int rcrtPrsnn) {
        this.rcrtPrsnn = rcrtPrsnn;
    }
    
    public int getPrgrssDrtn() {
        return this.prgrssDrtn;
    }
    
    public void setPrgrssDrtn(int prgrssDrtn) {
        this.prgrssDrtn = prgrssDrtn;
    }
    
    public String getRcrtEndDt() {
        return this.rcrtEndDt;
    }
    
    public void setRcrtEndDt(String rcrtEndDt) {
        this.rcrtEndDt = rcrtEndDt;
    }
    
    public String getPstEndDt() {
        return this.pstEndDt;
    }
    
    public void setPstEndDt(String pstEndDt) {
        this.pstEndDt = pstEndDt;
    }
    
    public String getCmpnEndDt() {
        return this.cmpnEndDt;
    }
    
    public void setCmpnEndDt(String cmpnEndDt) {
        this.cmpnEndDt = cmpnEndDt;
    }
    
    public String getRtrnRsn() {
        return this.rtrnRsn;
    }
    
    public void setRtrnRsn(String rtrnRsn) {
        this.rtrnRsn = rtrnRsn;
    }
    
    public String getAddrs() {
        return this.addrs;
    }
    
    public void setAddrs(String addrs) {
        this.addrs = addrs;
    }
    
    public String getAttchGrpId() {
        return this.attchGrpId;
    }
    
    public void setAttchGrpId(String attchGrpId) {
        this.attchGrpId = attchGrpId;
    }
    
    public String getCmpnTitle() {
        return this.cmpnTitle;
    }
    
    public void setCmpnTitle(String cmpnTitle) {
        this.cmpnTitle = cmpnTitle;
    }
    
    public String getCmpnCn() {
        return this.cmpnCn;
    }
    
    public void setCmpnCn(String cmpnCn) {
        this.cmpnCn = cmpnCn;
    }
    
    public String getOffrCn() {
        return this.offrCn;
    }
    
    public void setOffrCn(String offrCn) {
        this.offrCn = offrCn;
    }
    
    public int getOffrPrc() {
        return this.offrPrc;
    }
    
    public void setOffrPrc(int offrPrc) {
        this.offrPrc = offrPrc;
    }
    
    public String getPstMssn() {
        return this.pstMssn;
    }
    
    public void setPstMssn(String pstMssn) {
        this.pstMssn = pstMssn;
    }
    
    public String getHstg() {
        return this.hstg;
    }
    
    public void setHstg(String hstg) {
        this.hstg = hstg;
    }
    
    public String getNtfcn() {
        return this.ntfcn;
    }
    
    public void setNtfcn(String ntfcn) {
        this.ntfcn = ntfcn;
    }
    
    public int getView() {
        return this.view;
    }
    
    public void setView(int view) {
        this.view = view;
    }
    
    public String getCnfmDt() {
        return this.cnfmDt;
    }
    
    public void setCnfmDt(String cnfmDt) {
        this.cnfmDt = cnfmDt;
    }

	@Override
	public String toString() {
		return "CampaignVO [cmpnId=" + cmpnId + ", usrId=" + usrId + ", ctgCd=" + ctgCd + ", sttsCd=" + sttsCd
				+ ", prsnnPrc=" + prsnnPrc + ", drtnPrc=" + drtnPrc + ", prsnnDscnt=" + prsnnDscnt + ", drtnDscnt="
				+ drtnDscnt + ", cmpnPrntId=" + cmpnPrntId + ", rcrtStrtDt=" + rcrtStrtDt + ", rcrtPrsnn=" + rcrtPrsnn
				+ ", prgrssDrtn=" + prgrssDrtn + ", rcrtEndDt=" + rcrtEndDt + ", pstEndDt=" + pstEndDt + ", cmpnEndDt="
				+ cmpnEndDt + ", rtrnRsn=" + rtrnRsn + ", addrs=" + addrs + ", attchGrpId=" + attchGrpId
				+ ", cmpnTitle=" + cmpnTitle + ", cmpnCn=" + cmpnCn + ", offrCn=" + offrCn + ", offrPrc=" + offrPrc
				+ ", pstMssn=" + pstMssn + ", hstg=" + hstg + ", ntfcn=" + ntfcn + ", view=" + view + ", cnfmDt="
				+ cnfmDt + "]";
	}
    
    
}