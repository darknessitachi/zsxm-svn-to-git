package com.netwander.core.common;



public class TreeBean {
	private String key;
	//代码
	private String dm;
	//名称
	private String mc;
	//父结点代码
	private String pdm;
	//是否已提取
	private String isc;  //>0: 已存在，<=0：不存在
	
	private String fano;
	
	private String fanoold;
	
	//主要用于组成符合 MZTREE 的 ID 格式 PDM_DM
	private String dmp;
	
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getPdm() {
		return pdm;
	}
	public void setPdm(String pdm) {
		this.pdm = pdm;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getDmp() {
		return pdm+"_"+dm;
	}
	public String getIsc() {
		return isc;
	}
	public void setIsc(String isc) {
		this.isc = isc;
	}
	public String getFano() {
		return fano;
	}
	public void setFano(String fano) {
		this.fano = fano;
	}
	public String getFanoold() {
		return fanoold;
	}
	public void setFanoold(String fanoold) {
		this.fanoold = fanoold;
	}

}
