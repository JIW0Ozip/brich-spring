package com.ktdsuniversity.edu.domain.file.vo.request;

public class RequestDownloadVO {
	
	private String id;

    private String flGrpId;
    private  String flId;
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFlGrpId() {
		return this.flGrpId;
	}
	public void setFlGrpId(String flGrpId) {
		this.flGrpId = flGrpId;
	}
	public String getFlId() {
		return this.flId;
	}
	public void setFlId(String flId) {
		this.flId = flId;
	}
	@Override
	public String toString() {
		return "RequestDownloadVO [id=" + id + ", flGrpId=" + flGrpId + ", flId=" + flId + "]";
	}
    

}
