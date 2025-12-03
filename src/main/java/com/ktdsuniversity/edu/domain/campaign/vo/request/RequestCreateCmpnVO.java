package com.ktdsuniversity.edu.domain.campaign.vo.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class RequestCreateCmpnVO {
	
	private String cmpnId;
	private String usrId;
	@NotEmpty(message="필수 입력입니다.")
	private String ctgCd;
	private String sttsCd;
	private String cmpnPrntId;
	@NotNull(message = "값을 입력해주세요.")
	private int rcrtPrsnn;
	private String addrs;
	private String attchGrpId;
	@NotEmpty(message="필수 입력입니다.")
	private String cmpnTitle;
	@NotEmpty(message="필수 입력입니다.")
	private String cmpnCn;
	@NotEmpty(message="필수 입력입니다.")
	private String offrCn;
	private int offrPrc;
	@NotEmpty(message="필수 입력입니다.")
	private String pstMssn;
	@NotEmpty(message="필수 입력입니다.")
	private String hstg;
	@NotEmpty(message="필수 입력입니다.")
	private String ntfcn;

	private String roadAddress;
	private String detailAddress;
	private List<String> area;
	private MultipartFile file;
	private String prevCmpnId;
	
	
	
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
	public String getCmpnPrntId() {
		return this.cmpnPrntId;
	}
	public void setCmpnPrntId(String cmpnPrntId) {
		this.cmpnPrntId = cmpnPrntId;
	}
	public int getRcrtPrsnn() {
		return this.rcrtPrsnn;
	}
	public void setRcrtPrsnn(int rcrtPrsnn) {
		this.rcrtPrsnn = rcrtPrsnn;
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
	public String getRoadAddress() {
		return this.roadAddress;
	}
	public void setRoadAddress(String roadAddress) {
		this.roadAddress = roadAddress;
	}
	public String getDetailAddress() {
		return this.detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public List<String> getArea() {
		return this.area;
	}
	public void setArea(List<String> area) {
		this.area = area;
	}
	public MultipartFile getFile() {
		return this.file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getPrevCmpnId() {
		return this.prevCmpnId;
	}
	public void setPrevCmpnId(String prevCmpnId) {
		this.prevCmpnId = prevCmpnId;
	}
	
	@Override
	public String toString() {
		return "RequestCreateCmpnVO [cmpnId=" + cmpnId + ", usrId=" + usrId + ", ctgCd=" + ctgCd + ", sttsCd=" + sttsCd
				+ ", cmpnPrntId=" + cmpnPrntId + ", rcrtPrsnn=" + rcrtPrsnn + ", addrs=" + addrs + ", attchGrpId="
				+ attchGrpId + ", cmpnTitle=" + cmpnTitle + ", cmpnCn=" + cmpnCn + ", offrCn=" + offrCn + ", offrPrc="
				+ offrPrc + ", pstMssn=" + pstMssn + ", hstg=" + hstg + ", ntfcn=" + ntfcn + ", roadAddress="
				+ roadAddress + ", detailAddress=" + detailAddress + ", area=" + area + ", file=" + file
				+ ", prevCmpnId=" + prevCmpnId + "]";
	}
}
