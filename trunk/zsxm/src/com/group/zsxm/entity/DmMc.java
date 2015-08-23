package com.group.zsxm.entity;

public class DmMc {
	private String dm;
	private String mc;
	private Integer width;
	public DmMc(){}
	public DmMc(String d,String m){
		this.dm = d;
		this.mc = m;
	}
	public DmMc(String d,String m,Integer width){
		this.dm = d;
		this.mc = m;
		this.width = width;
	}
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
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}

	
}
