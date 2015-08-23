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
import com.group.zsxm.service.ZsxmwhService;
import com.group.zsxm.web.common.BaseAction;
import com.opensymphony.xwork2.Action;

@ParentPackage("appDefault")
@Scope("prototype")
@Controller
@Results( { @Result(name = "input", value = "/WEB-INF/jsp/query/queryzsxm.jsp"),
	 		@Result(name = "xmjzquery", value = "/WEB-INF/jsp/query/queryzsxm_xmjz.jsp")
			})
public class XmqueryAction extends BaseAction{
	@Autowired
	@Qualifier("zsxmwhService")
	private ZsxmwhService zsxmwhService;
	private Xtuser xtuser;
	public void prepare() {
		xtuser = (Xtuser)getSession().getAttribute(Constants.USER_SESSION_KEY);
	} 
	public String execute(){
		gheades = zsxmwhService.getGridHeaders("1");
		gcols = zsxmwhService.getGridCols("1");
		return Action.INPUT;
	}
	
	public String xmjzQuery(){
		gheades = zsxmwhService.getGridHeaders("5");
		gcols = zsxmwhService.getGridCols("5");
		return "xmjzquery";
	}
	
	public void doGetList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		getLimitPage().setRowAttributes(gridHandler.getTotalRowNum(), gridHandler.getPageNum(), gridHandler.getPageSize());
		Map param = getParameterSimpleMap();
		Page page = (Page) zsxmwhService.getListForZsxm(getLimitPage(), param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo(),null);
		gridHandler.setTotalRowNum(page.getTotalCount());
		gridHandler.setData((List) page.getData());
		renderText(gridHandler.getLoadResponseText());
	}
	
	public void doExportList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		Map param = gridHandler.getGridRequestParameters();
		List list = (List) zsxmwhService.getListForZsxm(null, param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo(),null);
		try {
			gridHandler.exportXLSfromMaps(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void doGetJzqkList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		getLimitPage().setRowAttributes(gridHandler.getTotalRowNum(), gridHandler.getPageNum(), gridHandler.getPageSize());
		Map param = getParameterSimpleMap();
		Page page = (Page) zsxmwhService.getListForZsxmJzqk(getLimitPage(), param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo(),null);
		gridHandler.setTotalRowNum(page.getTotalCount());
		gridHandler.setData((List) page.getData());
		renderText(gridHandler.getLoadResponseText());
	}
	
	public void doExportJzqkList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		Map param = gridHandler.getGridRequestParameters();
		List list = (List) zsxmwhService.getListForZsxmJzqk(null, param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo(),null);
		try {
			gridHandler.exportXLSfromMaps(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Xtuser getXtuser() {
		return xtuser;
	}
	public void setXtuser(Xtuser xtuser) {
		this.xtuser = xtuser;
	}
	
}
