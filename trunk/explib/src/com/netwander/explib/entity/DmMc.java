package com.netwander.explib.entity;

public class DmMc {
	private String dm;
	private String mc;
	public DmMc(){}
	public DmMc(String d,String m){
		this.dm = d;
		this.mc = m;
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
	
}
