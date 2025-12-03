package com.ktdsuniversity.edu.domain.chat.vo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ktdsuniversity.edu.domain.file.vo.FileVO;

/**
* @TableName CHT_MSG (MongoDB)
* @TableComment 채팅 메시지 테이블
*/
@Document(collection = "CHT_MSG")
public class ChatMessageVO {

   /**
    * @ColumnName CHT_MSG_ID
    * @ColumnType VARCHAR2(50)
    * @ColumnComment 채팅 메시지 ID
    */
   @Id
   @Field("CHT_MSG_ID")
   private String chtMsgId;

   /**
    * @ColumnName CHT_RM_ID
    * @ColumnType VARCHAR2(50)
    * @ColumnComment 채팅방 ID
    */
   @Field("CHT_RM_ID")
   private String chtRmId;

   /**
    * @ColumnName USR_ID
    * @ColumnType VARCHAR2(50)
    * @ColumnComment 사용자 ID
    */
   @Field("USR_ID")
   private String usrId;

   /**
    * @ColumnName ATTCH_GRP_ID
    * @ColumnType VARCHAR2(50)
    * @ColumnComment 첨부파일 그룹 ID
    */
   @Field("ATTCH_GRP_ID")
   private String attchGrpId;

   /**
    * @ColumnName RD_YN
    * @ColumnType CHAR(1)
    * @ColumnComment 읽음 여부
    */
   @Field("RD_YN")
   private String rdYn;

   /**
    * @ColumnName MSG_CN
    * @ColumnType VARCHAR2(4000)
    * @ColumnComment 메시지 내용
    */
   @Field("MSG_CN")
   private String msgCn;

   /**
    * @ColumnName CRT_DT
    * @ColumnType DATE
    * @ColumnComment 생성일시
    */
   @Field("CRT_DT")
   private String crtDt;

   /**
    * @ColumnName CRTR
    * @ColumnType VARCHAR2(50)
    * @ColumnComment 생성자
    */
   @Field("CRTR")
   private String crtr;

   /**
    * @ColumnName UPDT_DT
    * @ColumnType DATE
    * @ColumnComment 수정일시
    */
   @Field("UPDT_DT")
   private String updtDt;

   /**
    * @ColumnName MTTR
    * @ColumnType VARCHAR2(50)
    * @ColumnComment 수정자
    */
   @Field("MTTR")
   private String mttr;

   /**
    * @ColumnName DLT_YN
    * @ColumnType CHAR(1)
    * @ColumnComment 삭제 여부
    */
   @Field("DLT_YN")
   private String dltYn;

   @Field("NM")
   private String usrNm;
   
   @Field("CMPN_ID")
   private String cmpnId;

   @Field("CMPN_TITLE")
   private String cmpnTitle;
   
   @Field("CMPNY")
   private String cmpny;
   
   private List<FileVO> fileList;
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
   public String getMsgCn() {
	return this.msgCn;
   }
   public void setMsgCn(String msgCn) {
	this.msgCn = msgCn;
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
   public String getUsrNm() {
	return this.usrNm;
   }
   public void setUsrNm(String usrNm) {
	this.usrNm = usrNm;
   }
   public String getCmpnId() {
	return this.cmpnId;
   }
   public void setCmpnId(String cmpnId) {
	this.cmpnId = cmpnId;
   }
   public String getCmpnTitle() {
	return this.cmpnTitle;
   }
   public void setCmpnTitle(String cmpnTitle) {
	this.cmpnTitle = cmpnTitle;
   }
   public String getCmpny() {
	return this.cmpny;
   }
   public void setCmpny(String cmpny) {
	this.cmpny = cmpny;
   }
   public List<FileVO> getFileList() {
	return this.fileList;
   }
   public void setFileList(List<FileVO> fileList) {
	this.fileList = fileList;
   }
   @Override
   public String toString() {
	return "ChatMessageVO [chtMsgId=" + chtMsgId + ", chtRmId=" + chtRmId + ", usrId=" + usrId + ", attchGrpId="
			+ attchGrpId + ", rdYn=" + rdYn + ", msgCn=" + msgCn + ", crtDt=" + crtDt + ", crtr=" + crtr + ", updtDt="
			+ updtDt + ", mttr=" + mttr + ", dltYn=" + dltYn + ", usrNm=" + usrNm + ", cmpnId=" + cmpnId
			+ ", cmpnTitle=" + cmpnTitle + ", cmpny=" + cmpny + ", fileList=" + fileList + "]";
   }
      
}
