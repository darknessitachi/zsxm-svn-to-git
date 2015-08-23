package com.group.zsxm.web.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fins.gt.server.GridServerHandler;
import com.group.zsxm.service.FzhtService;
import com.group.zsxm.web.common.BaseAction;
import com.opensymphony.xwork2.Action;

@ParentPackage("appDefault")
@Scope("prototype")
@Controller
@Results( { @Result(name = "input", value = "/WEB-INF/jsp/fzht/fzhtanalys.jsp") })
public class FzhtanalysAction extends BaseAction{
	@Autowired
	@Qualifier("fzhtService")
	private FzhtService fzhtService;
	
	public String execute() {
		gheades = fzhtService.getGridHeaders("7");
		gcols = fzhtService.getGridCols("7");
		return Action.INPUT;
	}

	@SuppressWarnings("unchecked")
	public void doQueryListByType() {
		String type = getRequest().getParameter("type");
		Map<String,String> map = new HashMap<String,String>();
		map.put("type", type);
		List list = fzhtService.getListForXm(map);
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		gridHandler.setTotalRowNum(list != null ? list.size() : 0);
		gridHandler.setData(list);
		renderText(gridHandler.getLoadResponseText());
	}
	
	public void doExportList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		Map param = getParameterSimpleMap();
		String ajaxin = String.valueOf(param.get("_gt_json"));
		JSONObject js = JSONObject.fromBean(param.get("_gt_json"));
		Object type = JSONObject.fromBean(js.get("parameters")).get("type");
		Map<String,String> map = new HashMap<String,String>();
		map.put("type", String.valueOf(type));
		List list = fzhtService.getListForXm(map);
		try {
			gridHandler.exportXLSfromMaps(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
