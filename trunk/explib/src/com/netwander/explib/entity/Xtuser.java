package com.netwander.explib.entity;

public class Xtuser {
	private String userid;//用户ID 对应 XT_USER.USERID or xt_user_zj.dbid
	private String loginname;//登入名
	private String cnname;//中文名
	private String roledm;//角色代码
	private String password;
	private String logindate;
	private Integer loginbz ; //1: 工作人登入   2：专家登入
	
	private Integer expbz;  //主要用于专家登入使用 1：代表新增专家，还未审核通过   2：代表已经审核通过的人.  
	private Integer exprcid;//主要用于专家登入登用 匹配对应的专家
	
	private String bmmc;
	
	private String userfl;  //用户的分类
	
	public String getUserfl() {
		return userfl;
	}
	public void setUserfl(String userfl) {
		this.userfl = userfl;
	}
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
	public String getBmmc() {
		return bmmc;
	}
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}
	public Integer getExpbz() {
		return expbz;
	}
	public void setExpbz(Integer expbz) {
		this.expbz = expbz;
	}
	public Integer getExprcid() {
		return exprcid;
	}
	public void setExprcid(Integer exprcid) {
		this.exprcid = exprcid;
	}
	
}
