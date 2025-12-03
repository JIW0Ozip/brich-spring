package com.ktdsuniversity.edu.domain.user.vo.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class RequestUserRegistVO{
	
	private String usrId;
	
	@NotBlank(message="필수 입력입니다.")
	private String logId;
	
	@NotBlank(message="필수 입력입니다.")
	private String nm;
	
	@Email(message="이메일 형식으로 작성하세요.")
	@NotBlank(message="필수 입력입니다.")
	private String eml;
	
//	@Size(min=8, max=16, message="8자리에서 16자리까지 입력 가능합니다.")
	@NotBlank(message="필수 입력입니다.")
	private String pswrd;
	
	@NotBlank(message="필수 입력입니다.")
	private String pswrdConfirm;
	
	@NotBlank(message="필수 입력입니다.")
	private String emailConfirm;
	
	private String autr;
	
	private String cmpny;
	
	private String salt;
	
	private String flGrpId;
	
	private List<String> cdIdList;
	
	private List <String> areaList;

	private List<MultipartFile> file;

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

	public String getNm() {
		return this.nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
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

	public String getAutr() {
		return this.autr;
	}

	public void setAutr(String autr) {
		this.autr = autr;
	}

	public String getCmpny() {
		return this.cmpny;
	}

	public void setCmpny(String cmpny) {
		this.cmpny = cmpny;
	}

	public String getPswrdConfirm() {
		return this.pswrdConfirm;
	}

	public void setPswrdConfirm(String pswrdConfirm) {
		this.pswrdConfirm = pswrdConfirm;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmailConfirm() {
		return this.emailConfirm;
	}

	public void setEmailConfirm(String emailConfirm) {
		this.emailConfirm = emailConfirm;
	}
	
	public String getFlGrpId() {
		return this.flGrpId;
	}

	public void setFlGrpId(String flGrpId) {
		this.flGrpId = flGrpId;
	}

	public List<MultipartFile> getFile() {
		return this.file;
	}

	public void setFile(List<MultipartFile> file) {
		this.file = file;
	}

	public List<String> getCdIdList() {
		return this.cdIdList;
	}

	public void setCdIdList(List<String> cdIdList) {
		this.cdIdList = cdIdList;
	}

	public List<String> getAreaList() {
		return this.areaList;
	}

	public void setAreaList(List<String> areaList) {
		this.areaList = areaList;
	}

	@Override
	public String toString() {
		return "RequestUserRegistVO [usrId=" + usrId + ", logId=" + logId + ", nm=" + nm + ", eml=" + eml + ", pswrd="
				+ pswrd + ", pswrdConfirm=" + pswrdConfirm + ", emailConfirm=" + emailConfirm + ", autr=" + autr
				+ ", cmpny=" + cmpny + ", salt=" + salt + ", flGrpId=" + flGrpId + ", cdIdList=" + cdIdList
				+ ", areaList=" + areaList + ", file=" + file + "]";
	}


}
