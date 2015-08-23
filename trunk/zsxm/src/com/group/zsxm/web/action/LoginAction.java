package com.group.zsxm.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import com.group.core.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.group.zsxm.entity.XtMenu;
import com.group.zsxm.entity.Xtuser;
import com.group.zsxm.service.common.SystemService;
import com.group.zsxm.web.common.BaseAction;
import com.opensymphony.xwork2.Action;


@Scope("prototype")
@Controller
@Results( { 
			@Result(name = "input", value = "/WEB-INF/jsp/frame/login.jsp"),
			@Result(name = "success", value = "/WEB-INF/jsp/frame/maingl.jsp"),
			@Result(name = "indexgl", value = "/WEB-INF/jsp/frame/indexgl.jsp"),
			@Result(name = "bottom", value = "/WEB-INF/jsp/frame/bottom.jsp")
		})
public class LoginAction extends BaseAction{
	
	@Autowired
	@Qualifier("systemService")
	private SystemService systemService;
	
	private Xtuser xtuser ;
	private Integer logintype; 
	private List<XtMenu> xtmenus;
	
	public String execute(){
		return Action.INPUT;
	}
	
	public String doGl(){
		return "inputgl";
	}
	public String doLogin(){
		
		try{
			if( !xtuser.getLoginname().trim().equals("")){
				if( !xtuser.getPassword().trim().equals("")){
					xtuser = systemService.doCheckLogin(xtuser);
					xtuser.setLoginbz(1);
					xtuser.setZcbz(1);
					xtmenus = systemService.getMenuList(xtuser.getUserid());
					
					
					Map<String,String> m = new HashMap();
					m.put("usrname", xtuser.getCnname());
					m.put("usrid", xtuser.getUserid());
					m.put("czsm", "工作员登入系统");
					m.put("czinfo", "");
					systemService.doSaveLog(m);
					getSession().setAttribute(Constants.USER_SESSION_KEY, xtuser);
					
				}else{
					this.addActionError("登入密码不能为空");
				}
			}else{
				this.addActionError("登入用户名不能为空");
			}
			if(this.getActionErrors().size() > 0){
				return Action.INPUT;
			}
			return Action.SUCCESS;
		}catch(Exception e){
			this.addActionError(e.getMessage());
			return Action.INPUT;
		}
	}
	
	public String doIndexgl(){
		xtuser = (Xtuser) getSession().getAttribute(Constants.USER_SESSION_KEY );
		return "indexgl";
	}
	
	public String bottom(){
		return "bottom";
	}
	/**
	 * Mapping
	 * @return
	 */
	public Xtuser getXtuser() {
		return xtuser;
	}

	public void setXtuser(Xtuser xtuser) {
		this.xtuser = xtuser;
	}

	public List<XtMenu> getXtmenus() {
		return xtmenus;
	}

	public void setXtmenus(List<XtMenu> xtmenus) {
		this.xtmenus = xtmenus;
	}

	public Integer getLogintype() {
		return logintype;
	}

	public void setLogintype(Integer logintype) {
		this.logintype = logintype;
	}

	
}
