package com.ktdsuniversity.edu.domain.campaign.vo.response;

import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.file.vo.FileVO;

public class ResponseAdminCampaignVO extends CampaignVO {

	// 목록 페이지
	private String flGrpId;    // 파일 그룹 아이디
	private String flCnt;    // 파일 갯수
	private List<FileVO> fileVoList;    // 파일 리스트
	private int adptCnt;    // 현재 신청자 수
	private List<String> area;    // 지역 이름
	private String parentArea;    // 부모 지역 이름
	private String sttsCdNm;    // 공통 코드 이름
	private String rtrnRsn;    // 반려 사유
	
	// 상세 페이지
	private String logId;    // 회원 아이디 (USR)
	private String cmpnPymntId;    // 캠페인 결제 PK (일단 넣어둠)
	private String pymntCd;
	private String pymntCrtDt;    // 결제 일시 (CMPN_PYMNT)
	private String orderId;    // 주문 번호 (CMPN_PYMNT)
	// private int amnt;    // 총 결제 금액 (CMPN_PYMNT)
	private String amnt;   // 포맷 때문에 일단 String으로 받음 ㅇㅇ
	
	// 캠페인 Entity
	// 인원 당 가격
	private String cmpnPrsnnPrc;    // 숫자 포맷
	// 기간 당 가격
	private String cmpnDrtnPrc;    // 숫자 포맷
	// 인원 할인율
	// 기간 할인율
	// 캠페인 승인일
	// 모집 시작일 (시작일)
	// 모집 마감일 (인원 모집 마감일)
	// 모집 인원
	// 진행 기간
	// 포스팅 마감일 (포스팅 데드라인)
	// 캠페인 종료일
	
	public String getFlGrpId() {
		return this.flGrpId;
	}
	public void setFlGrpId(String flGrpId) {
		this.flGrpId = flGrpId;
	}
	public String getFlCnt() {
		return this.flCnt;
	}
	public void setFlCnt(String flCnt) {
		this.flCnt = flCnt;
	}
	public List<FileVO> getFileVoList() {
		return this.fileVoList;
	}
	public void setFileVoList(List<FileVO> fileVoList) {
		this.fileVoList = fileVoList;
	}
	public int getAdptCnt() {
		return this.adptCnt;
	}
	public void setAdptCnt(int adptCnt) {
		this.adptCnt = adptCnt;
	}
	public List<String> getArea() {
		return this.area;
	}
	public void setArea(List<String> area) {
		this.area = area;
	}
	public String getParentArea() {
		return this.parentArea;
	}
	public void setParentArea(String parentArea) {
		this.parentArea = parentArea;
	}
	public String getSttsCdNm() {
		return this.sttsCdNm;
	}
	public void setSttsCdNm(String sttsCdNm) {
		this.sttsCdNm = sttsCdNm;
	}
	public String getRtrnRsn() {
		return this.rtrnRsn;
	}
	public void setRtrnRsn(String rtrnRsn) {
		this.rtrnRsn = rtrnRsn;
	}
	public String getLogId() {
		return this.logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public String getCmpnPymntId() {
		return this.cmpnPymntId;
	}
	public void setCmpnPymntId(String cmpnPymntId) {
		this.cmpnPymntId = cmpnPymntId;
	}
	public String getPymntCd() {
		return this.pymntCd;
	}
	public void setPymntCd(String pymntCd) {
		this.pymntCd = pymntCd;
	}
	public String getPymntCrtDt() {
		return this.pymntCrtDt;
	}
	public void setPymntCrtDt(String pymntCrtDt) {
		this.pymntCrtDt = pymntCrtDt;
	}
	public String getOrderId() {
		return this.orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/*
	public int getAmnt() {
		return this.amnt;
	}
	public void setAmnt(int amnt) {
		this.amnt = amnt;
	}
	*/
	public String getAmnt() {
		return this.amnt;
	}
	public void setAmnt(String amnt) {
		this.amnt = amnt;
	}
	public String getCmpnPrsnnPrc() {
		return this.cmpnPrsnnPrc;
	}
	public void setCmpnPrsnnPrc(String cmpnPrsnnPrc) {
		this.cmpnPrsnnPrc = cmpnPrsnnPrc;
	}
	public String getCmpnDrtnPrc() {
		return this.cmpnDrtnPrc;
	}
	public void setCmpnDrtnPrc(String cmpnDrtnPrc) {
		this.cmpnDrtnPrc = cmpnDrtnPrc;
	}
	
	@Override
	public String toString() {
		return "ResponseAdminCampaignVO [flGrpId=" + flGrpId + ", flCnt=" + flCnt + ", fileVoList=" + fileVoList
				+ ", adptCnt=" + adptCnt + ", area=" + area + ", parentArea=" + parentArea + ", sttsCdNm=" + sttsCdNm
				+ ", rtrnRsn=" + rtrnRsn + ", logId=" + logId + ", cmpnPymntId=" + cmpnPymntId + ", pymntCd=" + pymntCd
				+ ", pymntCrtDt=" + pymntCrtDt + ", orderId=" + orderId + ", amnt=" + amnt + ", cmpnPrsnnPrc="
				+ cmpnPrsnnPrc + ", cmpnDrtnPrc=" + cmpnDrtnPrc + "]";
	}
	
}
