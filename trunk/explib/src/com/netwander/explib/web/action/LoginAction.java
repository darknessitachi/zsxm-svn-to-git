package com.netwander.explib.web.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.netwander.core.Constants;
import com.netwander.explib.entity.XtMenu;
import com.netwander.explib.entity.Xtuser;
import com.netwander.explib.service.ExpwhService;
import com.netwander.explib.service.SystemService;
import com.netwander.explib.service.remote.RemoteServiceInterface;
import com.netwander.explib.web.common.BaseAction;
import com.opensymphony.xwork2.Action;


@Scope("prototype")
@Controller
@Results( { 
			@Result(name = "inputgl", value = "/WEB-INF/pages/frame/logingl.jsp"),
			@Result(name = "inputzj", value = "/WEB-INF/pages/frame/loginzj.jsp"),
			
			@Result(name = "maingl", value = "/WEB-INF/pages/frame/maingl.jsp"),
			@Result(name = "mainzj", value = "/WEB-INF/pages/frame/expzj/index.jsp"),
			
			@Result(name = "expzc", value = "/WEB-INF/pages/frame/expzj/expzc.jsp"),
			@Result(name = "view", value = "/WEB-INF/pages/expwh/expview.jsp")
		})
public class LoginAction  extends BaseAction{
	
	@Autowired
	@Qualifier("systemService")
	private SystemService systemService;
	
	@Autowired
	@Qualifier("expwhService")
	private ExpwhService expwhService;
	
	private String loginname;
	private String password;
	private String logintype;
	private String rcid;
	private Xtuser xtuser;
	private Integer shbz;
	private Map<String,Object> maps;
	
	private String wburl;
	private String wbuserid;
	private String wbfldm;
	private String wbappname;
	
	public String getWbfldm() {
		return wbfldm;
	}
	public void setWbfldm(String wbfldm) {
		this.wbfldm = wbfldm;
	}
	public String getWburl() {
		return wburl;
	}
	public void setWburl(String wburl) {
		this.wburl = wburl;
	}
	public String getWbuserid() {
		return wbuserid;
	}
	public void setWbuserid(String wbuserid) {
		this.wbuserid = wbuserid;
	}
	public Integer getShbz() {
		return shbz;
	}
	public void setShbz(Integer shbz) {
		this.shbz = shbz;
	}
	public String getLogintype() {
		return logintype;
	}
	public void setLogintype(String logintype) {
		this.logintype = logintype;
	}
	public String execute(){
		//systemService.notifySys(1);
		return "inputgl";
	}
	
	public void expTb(){
		systemService.expTb(wburl, rcid, wbuserid,wbfldm,wbappname);
	}
	
	public String checklogin(){
		try{
			if(!loginname.trim().equals("") && !password.trim().equals("")){
				xtuser = systemService.checkLogon(logintype, loginname, password);
				systemService.setXtuser(xtuser);
				systemService.setMenuid("");
				systemService.setOptionid("L");
				systemService.doSaveLog("");
				getSession().setAttribute(Constants.USER_SESSION_KEY, xtuser);
				return "maingl";
			}else{
				this.addActionError("用户名或密码不能为空!");
			}
		}catch(Exception e){e.printStackTrace();
			this.addActionError(e.getMessage());
		}
		return "inputgl";
	}
	
	public String checkloginzj(){
		try{
			if(!loginname.trim().equals("") && !password.trim().equals("")){
				xtuser = systemService.checkLogonWithZj(logintype, loginname, password);
				systemService.setXtuser(xtuser);
				systemService.setMenuid("");
				systemService.setOptionid("L");
				systemService.doSaveLog("");
				getSession().setAttribute(Constants.USER_SESSION_KEY, xtuser);
				if(xtuser.getExpbz()==1){
					rcid = String.valueOf(xtuser.getExprcid());
					shbz = systemService.getExpTbbz(rcid);
					return "expzc";
				}else{
					return "mainzj";
				}
			}else{
				this.addActionError("用户名或密码不能为空!");
			}
		}catch(Exception e){e.printStackTrace();
			this.addActionError(e.getMessage());
		}
		return "inputzj";
	}
	
	/***
	 * 专家征集系统
	 */
	
	public String exp(){
		return "inputzj";
	}
	
	/**
	 * 人才注册
	 * @return
	 */
	public String preExpzc(){
		xtuser = new Xtuser();
		xtuser.setLoginbz(2);
		xtuser.setExprcid(0);
		getSession().setAttribute(Constants.USER_SESSION_KEY, xtuser);
		return "expzc";
	}
	
	public String logoutZj(){
		getSession().removeAttribute(Constants.USER_SESSION_KEY);
		return "inputzj";
	}
	
	public String preView(){
		maps = expwhService.preView(rcid,"");
		return "view";
	}
	/**
	 * Mapping
	 * @return
	 */
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Xtuser getXtuser() {
		return xtuser;
	}
	public void setXtuser(Xtuser xtuser) {
		this.xtuser = xtuser;
	}
	public String getRcid() {
		return rcid;
	}
	public void setRcid(String rcid) {
		this.rcid = rcid;
	}
	public Map<String, Object> getMaps() {
		return maps;
	}
	public void setMaps(Map<String, Object> maps) {
		this.maps = maps;
	}
	public String getWbappname() {
		return wbappname;
	}
	public void setWbappname(String wbappname) {
		this.wbappname = wbappname;
	}
	
}
