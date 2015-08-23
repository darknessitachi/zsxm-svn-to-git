package com.group.zsxm.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fins.gt.server.GridServerHandler;
import com.group.zsxm.service.AnalysService;
import com.group.zsxm.web.common.BaseAction;
import com.opensymphony.xwork2.Action;

@SuppressWarnings("serial")
@ParentPackage("appDefault")
@Scope("prototype")
@Controller
@Results( { @Result(name = "input", value = "/WEB-INF/jsp/query/analys.jsp") })
public class AnalysAction extends BaseAction {

	@Autowired
	private AnalysService analysService;

	public String execute() {
		return Action.INPUT;
	}
/**
	@SuppressWarnings("unchecked")
	public void doQueryListByType() {
		String type = getRequest().getParameter("type");
		Map<String,String> map = new HashMap<String,String>();
		map.put("type", type);
		List list = analysService.getListForType(map);
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		gridHandler.setTotalRowNum(list != null ? list.size() : 0);
		gridHandler.setData(list);
		renderText(gridHandler.getLoadResponseText());
	}*/
}
