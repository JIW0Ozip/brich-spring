package com.ktdsuniversity.edu.domain.user.vo;

import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.file.vo.FileGroupVO;

public class AdminAdvertiserDetailVO extends AdminUserBaseInfoVO {

	private int cmpnProgressCnt;
    private int cmpnCompletedCnt;
    private List<CampaignVO> cmpnProgressList;
    private List<CampaignVO> cmpnCompletedList;
	
	// 광고주 전용 데이터
	private String flGrpId;
	private FileGroupVO fileGroupVO;
	private String registAcpt;
	
	public int getCmpnProgressCnt() {
		return this.cmpnProgressCnt;
	}
	public void setCmpnProgressCnt(int cmpnProgressCnt) {
		this.cmpnProgressCnt = cmpnProgressCnt;
	}
	public int getCmpnCompletedCnt() {
		return this.cmpnCompletedCnt;
	}
	public void setCmpnCompletedCnt(int cmpnCompletedCnt) {
		this.cmpnCompletedCnt = cmpnCompletedCnt;
	}
	public List<CampaignVO> getCmpnProgressList() {
		return this.cmpnProgressList;
	}
	public void setCmpnProgressList(List<CampaignVO> cmpnProgressList) {
		this.cmpnProgressList = cmpnProgressList;
	}
	public List<CampaignVO> getCmpnCompletedList() {
		return this.cmpnCompletedList;
	}
	public void setCmpnCompletedList(List<CampaignVO> cmpnCompletedList) {
		this.cmpnCompletedList = cmpnCompletedList;
	}
	public String getFlGrpId() {
		return this.flGrpId;
	}
	public void setFlGrpId(String flGrpId) {
		this.flGrpId = flGrpId;
	}
	public FileGroupVO getFileGroupVO() {
		return this.fileGroupVO;
	}
	public void setFileGroupVO(FileGroupVO fileGroupVO) {
		this.fileGroupVO = fileGroupVO;
	}
	public String getRegistAcpt() {
		return this.registAcpt;
	}
	public void setRegistAcpt(String registAcpt) {
		this.registAcpt = registAcpt;
	}
	
}
