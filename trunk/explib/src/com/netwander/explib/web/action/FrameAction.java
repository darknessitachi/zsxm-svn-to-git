package com.netwander.explib.web.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.netwander.core.Constants;
import com.netwander.explib.entity.XtMenu;
import com.netwander.explib.entity.Xtuser;
import com.netwander.explib.service.SystemService;
import com.netwander.explib.web.common.BaseAction;

@ParentPackage("appDefault")
@Scope("prototype")
@Controller
@Results( { 
			@Result(name = "inputgl", value = "/WEB-INF/pages/frame/logingl.jsp"),
			@Result(name = "maingl", value = "/WEB-INF/pages/frame/maintest.jsp"),
			@Result(name = "topgl", value = "/WEB-INF/pages/frame/topgl.jsp"),
			@Result(name = "centergl", value = "/WEB-INF/pages/frame/centergl.jsp"),
			@Result(name = "middlegl", value = "/WEB-INF/pages/frame/middlegl.jsp"),
			@Result(name = "leftgl", value = "/WEB-INF/pages/frame/leftgl.jsp"),
			@Result(name = "bottom", value = "/WEB-INF/pages/frame/bottom.jsp"),
			@Result(name = "indexgl", value = "/WEB-INF/pages/frame/indexgl.jsp"),
			@Result(name = "bottomgl", value = "/WEB-INF/pages/frame/bottomgl.jsp"),
			@Result(name = "bottom", value = "/WEB-INF/pages/frame/bottom.jsp"),
			
			@Result(name = "indexzj", value = "/WEB-INF/pages/frame/expzj/index.jsp"),
		})
public class FrameAction extends BaseAction {
	@Autowired
	@Qualifier("systemService")
	private SystemService systemService;
	private List<Map<String, String>> lms;
	private Xtuser xtuser;
	
	private List<XtMenu> xtmenus;
	
	public void onPrepare() {
		xtuser = (Xtuser)getSession().getAttribute(Constants.USER_SESSION_KEY);
	}
	
	public List<XtMenu> getXtmenus() {
		return xtmenus;
	}
	public void setXtmenus(List<XtMenu> xtmenus) {
		this.xtmenus = xtmenus;
	}
	public String doLogin(){
		return "maingl";
	}
	public String topgl(){
		return "topgl";
	}
	public String centergl(){
		return "centergl";
	}
	public String middlegl(){
		return "middlegl";
	}
	public String leftgl(){
		Xtuser xtuser = (Xtuser) getSession().getAttribute(Constants.USER_SESSION_KEY );
		xtmenus = systemService.getMenuList(xtuser.getUserid());
		return "leftgl";
	}
	
	
	public String logout(){
		getSession().removeAttribute(Constants.USER_SESSION_KEY);
		return "inputgl";
	}
	
	public String bottomgl(){
		return "bottomgl";
	}
	public String indexgl(){
		lms = systemService.getInfoLmList();
		return "indexgl";
	}
	
	
	public String indexzj(){
		return "indexzj";
	}
	
	public Xtuser getXtuser() {
		return xtuser;
	}
	public void setXtuser(Xtuser xtuser) {
		this.xtuser = xtuser;
	}

	public List<Map<String, String>> getLms() {
		return lms;
	}

	public void setLms(List<Map<String, String>> lms) {
		this.lms = lms;
	}

	
}
