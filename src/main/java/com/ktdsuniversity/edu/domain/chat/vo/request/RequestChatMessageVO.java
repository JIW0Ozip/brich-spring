package com.ktdsuniversity.edu.domain.chat.vo.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class RequestChatMessageVO {

	private String chtMsgId;
	
    private String chtRmId;
    
    private String msgCn;
    
    private String usrId;
    
    private String attchGrpId;
    
    private String nm;
    
    private String cmpny;
    
    private String rdYn;
    
    private String crtDt;

    private String crtr;

    private String updtDt;

    private String mttr;

    private String dltYn;

    /**
     * 첨부파일 목록 (선택사항)
     */
    private List<MultipartFile> files;

	public String getChtMsgId() {
		return this.chtMsgId;
	}

	public void setChtMsgId(String chtMsgId) {
		this.chtMsgId = chtMsgId;
	}

	public String getChtRmId() {
		return this.chtRmId;
	}

	public void setChtRmId(String chtRmId) {
		this.chtRmId = chtRmId;
	}

	public String getMsgCn() {
		return this.msgCn;
	}

	public void setMsgCn(String msgCn) {
		this.msgCn = msgCn;
	}

	public String getUsrId() {
		return this.usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	public String getAttchGrpId() {
		return this.attchGrpId;
	}

	public void setAttchGrpId(String attchGrpId) {
		this.attchGrpId = attchGrpId;
	}

	public String getRdYn() {
		return this.rdYn;
	}

	public void setRdYn(String rdYn) {
		this.rdYn = rdYn;
	}

	public String getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(String crtDt) {
		this.crtDt = crtDt;
	}

	public String getCrtr() {
		return this.crtr;
	}

	public void setCrtr(String crtr) {
		this.crtr = crtr;
	}

	public String getUpdtDt() {
		return this.updtDt;
	}

	public void setUpdtDt(String updtDt) {
		this.updtDt = updtDt;
	}

	public String getMttr() {
		return this.mttr;
	}

	public void setMttr(String mttr) {
		this.mttr = mttr;
	}

	public String getDltYn() {
		return this.dltYn;
	}

	public void setDltYn(String dltYn) {
		this.dltYn = dltYn;
	}

	public List<MultipartFile> getFiles() {
		return this.files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	public String getNm() {
		return this.nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}

	public String getCmpny() {
		return this.cmpny;
	}

	public void setCmpny(String cmpny) {
		this.cmpny = cmpny;
	}

	@Override
	public String toString() {
		return "RequestChatMessageVO [chtMsgId=" + chtMsgId + ", chtRmId=" + chtRmId + ", msgCn=" + msgCn + ", usrId="
				+ usrId + ", attchGrpId=" + attchGrpId + ", nm=" + nm + ", cmpny=" + cmpny + ", rdYn=" + rdYn
				+ ", crtDt=" + crtDt + ", crtr=" + crtr + ", updtDt=" + updtDt + ", mttr=" + mttr + ", dltYn=" + dltYn
				+ ", files=" + files + "]";
	}

   
}
