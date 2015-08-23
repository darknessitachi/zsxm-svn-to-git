package com.group.zsxm.web.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fins.gt.server.GridServerHandler;
import com.group.core.Constants;
import com.group.core.common.Page;
import com.group.zsxm.entity.Xtuser;
import com.group.zsxm.service.QueryService;
import com.group.zsxm.service.ZsdwwhService;
import com.group.zsxm.web.common.BaseAction;
import com.opensymphony.xwork2.Action;
@ParentPackage("appDefault")
@Scope("prototype")
@Controller
@Results( { @Result(name = "input", value = "/WEB-INF/jsp/query/queryzsdw.jsp"),
			@Result(name = "yqfw", value = "/WEB-INF/jsp/query/queryzsdw_yqfw.jsp")
			})
public class DwqueryAction  extends BaseAction{
	@Autowired
	@Qualifier("queryService")
	private QueryService queryService;
	
	@Autowired
	@Qualifier("zsdwwhService")
	private ZsdwwhService zsdwwhService;
	
	private Xtuser xtuser;
	public void prepare() {
		xtuser = (Xtuser)getSession().getAttribute(Constants.USER_SESSION_KEY);
	} 
	public String execute(){
		gheades = zsdwwhService.getGridHeaders("3");
		gcols = zsdwwhService.getGridCols("3");
		return Action.INPUT;
	}
	
	public String yqfwQuery(){
		gheades = zsdwwhService.getGridHeaders("6");
		gcols = zsdwwhService.getGridCols("6");
		return "yqfw";
	}
	
	public void doGetList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		getLimitPage().setRowAttributes(gridHandler.getTotalRowNum(), gridHandler.getPageNum(), gridHandler.getPageSize());
		Map param = getParameterSimpleMap();
		Page page = (Page) zsdwwhService.getListForZsdw(getLimitPage(), param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo());
		gridHandler.setTotalRowNum(page.getTotalCount());
		gridHandler.setData((List) page.getData());
		renderText(gridHandler.getLoadResponseText());
	}
	
	public void doExportList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		Map param = gridHandler.getGridRequestParameters();
		List list = (List) zsdwwhService.getListForZsdw(null, param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo());
		try {
			gridHandler.exportXLSfromMaps(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void doGetListCdxm() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		getLimitPage().setRowAttributes(gridHandler.getTotalRowNum(), gridHandler.getPageNum(), gridHandler.getPageSize());
		Map param = getParameterSimpleMap();
		Page page = (Page) zsdwwhService.getListForZsdwxm(getLimitPage(), param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo());
		gridHandler.setTotalRowNum(page.getTotalCount());
		gridHandler.setData((List) page.getData());
		renderText(gridHandler.getLoadResponseText());
	}
	
	public void doExportListCdxm() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		Map param = gridHandler.getGridRequestParameters();
		List list = (List) zsdwwhService.getListForZsdwxm(null, param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo());
		try {
			gridHandler.exportXLSfromMaps(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void doGetYqfwList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		getLimitPage().setRowAttributes(gridHandler.getTotalRowNum(), gridHandler.getPageNum(), gridHandler.getPageSize());
		Map param = getParameterSimpleMap();
		Page page = (Page) zsdwwhService.getListForZsdwYqfw(getLimitPage(), param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo());
		gridHandler.setTotalRowNum(page.getTotalCount());
		gridHandler.setData((List) page.getData());
		renderText(gridHandler.getLoadResponseText());
	}
	
	public void doExportYqfwList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		Map param = gridHandler.getGridRequestParameters();
		List list = (List) zsdwwhService.getListForZsdwYqfw(null, param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo());
		try {
			gridHandler.exportXLSfromMaps(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
