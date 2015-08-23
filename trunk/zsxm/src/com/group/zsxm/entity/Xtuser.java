package com.group.zsxm.entity;

public class Xtuser {
	private String userid;//用户ID 对应 XT_USER.USERID
	private String loginname;//登入名
	private String cnname;//中文名
	private String roledm;//角色代码
	private String password;
	private String logindate;
	
	private Integer loginbz ; //1: 工作人登入   2：人才登入
	
	private Integer zcbz;    //注册标志   判别是否为人才注册信息  2: 人才注册信息
	
	private String xtrcid;  //主要是 人才登入后，锁定对应的 RC_PINF.RCID;
	private String rcid;    //对应 RC_USER.RCID
	
	private Integer bz;    //1: 启用，  2： 人才注册待启用
	private String rcname;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getRoledm() {
		return roledm;
	}
	public void setRoledm(String roledm) {
		this.roledm = roledm;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getCnname() {
		return cnname;
	}
	public void setCnname(String cnname) {
		this.cnname = cnname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLogindate() {
		return logindate;
	}
	public void setLogindate(String logindate) {
		this.logindate = logindate;
	}


	public Integer getLoginbz() {
		return loginbz;
	}
	public void setLoginbz(Integer loginbz) {
		this.loginbz = loginbz;
	}
	public String getXtrcid() {
		return xtrcid;
	}
	public void setXtrcid(String xtrcid) {
		this.xtrcid = xtrcid;
	}
	public String getRcid() {
		return rcid;
	}
	public void setRcid(String rcid) {
		this.rcid = rcid;
	}
	public Integer getZcbz() {
		return zcbz;
	}
	public void setZcbz(Integer zcbz) {
		this.zcbz = zcbz;
	}
	public Integer getBz() {
		return bz;
	}
	public void setBz(Integer bz) {
		this.bz = bz;
	}
	public String getRcname() {
		return rcname;
	}
	public void setRcname(String rcname) {
		this.rcname = rcname;
	}
	
}
