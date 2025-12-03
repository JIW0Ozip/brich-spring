package com.ktdsuniversity.edu.domain.campaign.vo.response;

import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.file.vo.FileVO;

public class ResponseCampaignVO extends CampaignVO{
	
	
	private String personPrice;
	private String dayPrice;


	 /**
	  * 파일 갯수
	  */
	private String flCnt;
	
	/**
	 *  파일 그룹 아이디
	 */
	private String flGrpId;
	
	/**
	 * 파일 리스트
	 */
	private List<FileVO> fileVoList;
	

	 /**
	  * 좋아요 수
	  */
	 private int likeCnt;
	 /**
	  * 현재 신청자 수 
	  */
	 private int adptCnt;
	 
	 /**
	  * 지역 이름
	  */
	 private List<String> area;
	 
	 /**
	  * 부모지역 이름
	  */
	 private String parentArea;
	 
	 /**
	  * 포스팅 상태 코드
	  */
	 private String pstSttsCd;
	 
	 /**
	  * 포스팅 상태 코드 이름
	  */
	 private String pstSttsCdNm;
	 
	 /**
	  * 좋아요 여부
	  */
	 private String favYn;
	 
	 /**
	  * 신청 여부
	  */
	 private String adptYn;
	 
	 /**
	  * 공통코드 이름
	  */
	 private String SttsCdNm;

	 public String getSttsCdNm() {
		return this.SttsCdNm;
	}

	 public void setSttsCdNm(String sttsCdNm) {
		 SttsCdNm = sttsCdNm;
	 }
	 
	 public String getPstSttsCdNm() {
			return this.pstSttsCdNm;
		}

	public void setPstSttsCdNm(String pstSttsCdNm) {
		this.pstSttsCdNm = pstSttsCdNm;
	}

	 public String getAdptYn() {
		return this.adptYn;
	}

	 public void setAdptYn(String adptYn) {
		 this.adptYn = adptYn;
	 }

	 public String getPstSttsCd() {
		return this.pstSttsCd;
	}

	 public void setPstSttsCd(String pstSttsCd) {
		 this.pstSttsCd = pstSttsCd;
	 }

	 public String getFavYn() {
		 return this.favYn;
	 }

	 public void setFavYn(String favYn) {
		 this.favYn = favYn;
	 }

	 public int getLikeCnt() {
		 return this.likeCnt;
	 }

	 public void setLikeCnt(int likeCnt) {
		 this.likeCnt = likeCnt;
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

	 public String getFlCnt() {
		return this.flCnt;
	}

	 public void setFlCnt(String flCnt) {
		 this.flCnt = flCnt;
	 }

	 public String getFlGrpId() {
		 return this.flGrpId;
	 }

	 public void setFlGrpId(String flGrpId) {
		 this.flGrpId = flGrpId;
	 }

	 public List<FileVO> getFileVoList() {
		 return this.fileVoList;
	 }

	 public void setFileVoList(List<FileVO> fileVoList) {
		 this.fileVoList = fileVoList;
	 }
	 public String getPersonPrice() {
		return personPrice;
	}

	public void setPersonPrice(String personPrice) {
		this.personPrice = personPrice;
	}

	public String getDayPrice() {
		return dayPrice;
	}

	public void setDayPrice(String dayPrice) {
		this.dayPrice = dayPrice;
	}

	 @Override
	public String toString() {
		return "ResponseCampaignVO [personPrice=" + personPrice + ", dayPrice=" + dayPrice + ", flCnt=" + flCnt
				+ ", flGrpId=" + flGrpId + ", fileVoList=" + fileVoList + ", likeCnt=" + likeCnt + ", adptCnt="
				+ adptCnt + ", area=" + area + ", parentArea=" + parentArea + ", pstSttsCd=" + pstSttsCd
				+ ", pstSttsCdNm=" + pstSttsCdNm + ", favYn=" + favYn + ", adptYn=" + adptYn + ", SttsCdNm=" + SttsCdNm
				+ "]";
	}
}
