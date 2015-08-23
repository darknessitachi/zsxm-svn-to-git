package com.group.zsxm.web.action;

import java.io.IOException;
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
import com.group.core.common.TreeBean;
import com.group.core.common.TreeFactory;
import com.group.zsxm.entity.Xtuser;
import com.group.zsxm.service.ZsxmbyService;
import com.group.zsxm.web.common.BaseAction;
import com.opensymphony.xwork2.Action;

@ParentPackage("appDefault")
@Scope("prototype")
@Results( { 
		@Result(name = "input", value = "/WEB-INF/jsp/zsxm/zsxmby.jsp"),
		@Result(name = "detail", value = "/WEB-INF/jsp/zsxm/zsxmby_d.jsp"),
		@Result(name = "select", value = "/WEB-INF/jsp/zsxm/zsxmby_sel.jsp")
		 })
public class ZsxmbyAction extends BaseAction{
	@Autowired
	@Qualifier("zsxmbyService")
	private ZsxmbyService zsxmbyService;
	
	private Map<String, String> flmap;
	private List fields;
	private Map<String, String> query;
	private List<Map<String, String>> qlist;
	private String bydm;
	private String[] xmid;
	private String xmids;
	private String type;
	private Integer option;
	
	private Xtuser xtuser;
	public void prepare() {
		xtuser = (Xtuser)getSession().getAttribute(Constants.USER_SESSION_KEY);
	} 
	public String execute(){
		gheades = zsxmbyService.getGridHeaders("1");
		gcols = zsxmbyService.getGridCols("1");
		return Action.INPUT;
	}
	
	
	public void getXmbywhTree(){
		List<TreeBean> treeBeans = zsxmbyService.getXmbyTree();
		TreeFactory tf = new TreeFactory();	
		tf.setPdm("0");
		tf.setIsDisabledCheck(false);
		tf.setRootText("选择项目标引");
		renderText(tf.create(treeBeans));
	}
	
	public String getZsxmWaitSelect(){
		fields = ArrayToList(
				new String[] { "xmmc", "xmlb_mc", "xmgs", "xmjdgs_mc" },
				new String[] { "项目名称", "项目类别", "项目概述", "项目进度概述" });
		qlist = zsxmbyService.getZsxmWaitSelect(query,bydm,xtuser.getRoledm().equals("000")?null:xtuser.getUserid());
		return "select";
	}
	
	public void doSelectZsxmmx(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsxmbyService.doSelectZsxmmx(bydm, xmid);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doDelSelectedXmmx(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsxmbyService.doDelSelectedXmmx(xmids,bydm);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);		
	}
	
	public void getZsxmSelected(){
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		getLimitPage().setRowAttributes(gridHandler.getTotalRowNum(), gridHandler.getPageNum(), gridHandler.getPageSize());
		Map param = getParameterSimpleMap();
		Page page = (Page) zsxmbyService.getZsxmSelected(getLimitPage(), param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo());
		gridHandler.setTotalRowNum(page.getTotalCount());
		gridHandler.setData((List) page.getData());
		renderText(gridHandler.getLoadResponseText());
	}
	
	public void doExportList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		Map param = gridHandler.getGridRequestParameters();
		List list = (List) zsxmbyService.getZsxmSelected(null, param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo());
		try {
			gridHandler.exportXLSfromMaps(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String preAddXmby(){
		return "detail";
	}
	
	public void doAddBywh(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsxmbyService.doAddBywh(flmap);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doDelXmby(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsxmbyService.doDelXmby(bydm);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	/**
	 * 修改
	 * @return
	 */
	public String preRepXmby(){
		flmap = zsxmbyService.preRepXmby(bydm);
		flmap.put("bydm", bydm);
		return "detail";
	}
	
	public void doRepXmby(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsxmbyService.doRepXmby(bydm, flmap);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	
	/**
	 * Mapping
	 * @return
	 */
	public Map<String, String> getFlmap() {
		return flmap;
	}
	public void setFlmap(Map<String, String> flmap) {
		this.flmap = flmap;
	}
	public List getFields() {
		return fields;
	}
	public void setFields(List fields) {
		this.fields = fields;
	}
	public Map<String, String> getQuery() {
		return query;
	}
	public void setQuery(Map<String, String> query) {
		this.query = query;
	}
	public List<Map<String, String>> getQlist() {
		return qlist;
	}
	public void setQlist(List<Map<String, String>> qlist) {
		this.qlist = qlist;
	}
	public String getBydm() {
		return bydm;
	}
	public void setBydm(String bydm) {
		this.bydm = bydm;
	}
	public String[] getXmid() {
		return xmid;
	}
	public void setXmid(String[] xmid) {
		this.xmid = xmid;
	}
	public String getXmids() {
		return xmids;
	}
	public void setXmids(String xmids) {
		this.xmids = xmids;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getOption() {
		return option;
	}
	public void setOption(Integer option) {
		this.option = option;
	}
	
	
}
