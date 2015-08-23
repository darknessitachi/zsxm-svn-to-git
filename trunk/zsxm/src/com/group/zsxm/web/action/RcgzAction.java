package com.group.zsxm.web.action;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import com.group.core.Constants;
import com.group.zsxm.entity.Xtuser;
import com.group.zsxm.service.RcgzService;
import com.group.zsxm.web.common.BaseAction;
import com.opensymphony.xwork2.Action;

@ParentPackage("appDefault")
@Scope("prototype")
@Results( { 
		@Result(name = "input", value = "/WEB-INF/jsp/rcda/rcgz.jsp")
		 })
public class RcgzAction extends BaseAction{
	@Autowired
	@Qualifier("rcgzService")
	private RcgzService rcgzService;
	private Xtuser xtuser;
	private String rcid;
	
	public void prepare() {
		xtuser = (Xtuser)getSession().getAttribute(Constants.USER_SESSION_KEY);
	} 
	public String execute(){
		if(xtuser.getLoginbz() == 2){
			//人才登入
			rcid = xtuser.getXtrcid();
		}
		return Action.INPUT;
	}
	
}
