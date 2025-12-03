package com.ktdsuniversity.edu.domain.blog.vo;

public class BlogIndexVO {

	private String usrId;
	private String logId;
	private String statDt;
	private double indxVal;
	private double indxValAvg5d;
	private double ovllBlgIndx;
	
	
	
	public double getOvllBlgIndx() {
		return ovllBlgIndx;
	}
	public void setOvllBlgIndx(double ovllBlgIndx) {
		this.ovllBlgIndx = ovllBlgIndx;
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
	public String getStatDt() {
		return this.statDt;
	}
	public void setStatDt(String statDt) {
		this.statDt = statDt;
	}
	public double getIndxVal() {
		return this.indxVal;
	}
	public void setIndxVal(double indxVal) {
		this.indxVal = indxVal;
	}
	public double getIndxValAvg5d() {
		return this.indxValAvg5d;
	}
	public void setIndxValAvg5d(double indxValAvg5d) {
		this.indxValAvg5d = indxValAvg5d;
	}
	
	@Override
	public String toString() {
		return "BlogIndexVO [usrId=" + usrId + ", logId=" + logId + ", statDt=" + statDt + ", indxVal=" + indxVal
				+ ", indxValAvg5d=" + indxValAvg5d + ", ovllBlgIndx=" + ovllBlgIndx + "]";
	}
}
