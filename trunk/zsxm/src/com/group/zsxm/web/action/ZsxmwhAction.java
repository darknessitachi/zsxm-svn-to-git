package com.group.zsxm.web.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
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
import com.group.core.common.Message;
import com.group.core.common.Page;
import com.group.zsxm.entity.Xtuser;
import com.group.zsxm.service.ZsxmwhService;
import com.group.zsxm.web.common.BaseAction;
import com.opensymphony.xwork2.Action;

@ParentPackage("appDefault")
@Scope("prototype")
@Controller
@Results( { @Result(name = "input", value = "/WEB-INF/jsp/zsxm/zsxmwh.jsp")
			})
public class ZsxmwhAction extends BaseAction{
	@Autowired
	@Qualifier("zsxmwhService")
	private ZsxmwhService zsxmwhService;
	
	private String xmid;
	private Xtuser xtuser;
	public void prepare() {
		xtuser = (Xtuser)getSession().getAttribute(Constants.USER_SESSION_KEY);
	} 
	public String execute(){
		gheades = zsxmwhService.getGridHeaders("1");
		gcols = zsxmwhService.getGridCols("1");
		return Action.INPUT;
	}
	
	public void doGetList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		getLimitPage().setRowAttributes(gridHandler.getTotalRowNum(), gridHandler.getPageNum(), gridHandler.getPageSize());
		Map param = getParameterSimpleMap();
		Page page = (Page) zsxmwhService.getListForZsxm(getLimitPage(), param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo(),xtuser.getRoledm().equals("000")?null:xtuser.getUserid());
		gridHandler.setTotalRowNum(page.getTotalCount());
		gridHandler.setData((List) page.getData());
		renderText(gridHandler.getLoadResponseText());
	}
	
	public void doExportList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		Map param = gridHandler.getGridRequestParameters();
		List list = (List) zsxmwhService.getListForZsxm(null, param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo(),xtuser.getRoledm().equals("000")?null:xtuser.getUserid());
		try {
			gridHandler.exportXLSfromMaps(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void doDeletezsxm(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsxmwhService.doDeletezsxm(xmid);
			message = new Message("1","删除成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doExportExcel(){
		OutputStream os = null;
		try{
			String bt = "科教城项目信息("+xtuser.getLogindate()+").xls";
			this.getResponse().reset();
			os = getResponse().getOutputStream();
			this.getResponse().setContentType("application/msexcel;charsert=GBK");
			this.getResponse().setHeader("Content-Disposition","attachment; filename="+new String (bt.getBytes(),"ISO8859-1"));
			zsxmwhService.doExportExcel(os,xtuser.getRoledm().equals("000")?null:xtuser.getUserid());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(os != null){
				try{
					os.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Mapping
	 * @return
	 */
	public String getXmid() {
		return xmid;
	}
	public void setXmid(String xmid) {
		this.xmid = xmid;
	}
	public Xtuser getXtuser() {
		return xtuser;
	}
	public void setXtuser(Xtuser xtuser) {
		this.xtuser = xtuser;
	}
	
}
