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
import com.group.zsxm.service.ZsdwbyService;
import com.group.zsxm.web.common.BaseAction;
import com.opensymphony.xwork2.Action;


@ParentPackage("appDefault")
@Scope("prototype")
@Results( { 
		@Result(name = "input", value = "/WEB-INF/jsp/zsdw/zsdwby.jsp"),
		@Result(name = "dwfl", value = "/WEB-INF/jsp/zsdw/zsdwfl.jsp"),
		@Result(name = "detail", value = "/WEB-INF/jsp/zsdw/zsdwby_d.jsp"),
		@Result(name = "select", value = "/WEB-INF/jsp/zsdw/zsdwby_sel.jsp")
		 })
public class ZsdwbyAction extends BaseAction{
	
	@Autowired
	@Qualifier("zsdwbyService")
	private ZsdwbyService zsdwbyService;
	
	private Map<String, String> flmap;
	private List fields;
	private Map<String, String> query;
	private List<Map<String, String>> qlist;
	private String bydm;
	private String[] dwid;
	private String dwids;
	private String type;
	private Integer option;
	private Xtuser xtuser;
	public void prepare() {
		xtuser = (Xtuser)getSession().getAttribute(Constants.USER_SESSION_KEY);
	} 
	public String execute(){
		gheades = zsdwbyService.getGridHeaders("3");
		gcols = zsdwbyService.getGridCols("3");
		return Action.INPUT;
	}
	
	
	public String dwfl(){
		gheades = zsdwbyService.getGridHeaders("3");
		gcols = zsdwbyService.getGridCols("3");
		return "dwfl";
	}
	
	public void getDwbywhTree(){
		List<TreeBean> treeBeans = zsdwbyService.getDwbyTree();
		TreeFactory tf = new TreeFactory();	
		tf.setPdm("0");
		tf.setIsDisabledCheck(false);
		tf.setRootText("选择单位标引");
		renderText(tf.create(treeBeans));
	}
	
	public void getDwflTree(){
		List<TreeBean> treeBeans = zsdwbyService.getDwflTree();
		TreeFactory tf = new TreeFactory();	
		tf.setPdm("0");
		tf.setIsDisabledCheck(false);
		tf.setRootText("选择单位分类");
		renderText(tf.create(treeBeans));
	}
	
	public String getZsDwWaitSelect(){
		fields = ArrayToList(
				new String[] { "dwdm", "dwmc", "dwzt_mc", "dwlx_mc", "xm_mc" },
				new String[] { "组织机构代码", "单位名称", "单位状态", "单位类型" , "招资项目"});
		qlist = zsdwbyService.getZsdwWaitSelect(query,bydm);
		return "select";
	}
	
	public void doSelectZsDwmx(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwbyService.doSelectZsdwmx(bydm, dwid);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doDelSelectedDwmx(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwbyService.doDelSelectedDwmx(dwids,bydm);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);		
	}
	
	public void getZsDwSelected(){
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		getLimitPage().setRowAttributes(gridHandler.getTotalRowNum(), gridHandler.getPageNum(), gridHandler.getPageSize());
		Map param = getParameterSimpleMap();
		Page page = (Page) zsdwbyService.getZsdwSelected(getLimitPage(), param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo());
		gridHandler.setTotalRowNum(page.getTotalCount());
		gridHandler.setData((List) page.getData());
		renderText(gridHandler.getLoadResponseText());
	}
	
	public void doExportZsDwList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		Map param = gridHandler.getGridRequestParameters();
		List list = (List) zsdwbyService.getZsdwSelected(null, param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo());
		try {
			gridHandler.exportXLSfromMaps(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getZsDwfl(){
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		getLimitPage().setRowAttributes(gridHandler.getTotalRowNum(), gridHandler.getPageNum(), gridHandler.getPageSize());
		Map param = getParameterSimpleMap();
		Page page = (Page) zsdwbyService.getZsdwFlList(getLimitPage(), param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo());
		gridHandler.setTotalRowNum(page.getTotalCount());
		gridHandler.setData((List) page.getData());
		renderText(gridHandler.getLoadResponseText());
	}
	
	public void doExportZsDwFlList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		Map param = gridHandler.getGridRequestParameters();
		List list = (List) zsdwbyService.getZsdwFlList(null, param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo());
		try {
			gridHandler.exportXLSfromMaps(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String preAddDwby(){
		return "detail";
	}
	
	public void doAddBywh(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwbyService.doAddBywh(flmap);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doDelDwby(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwbyService.doDelDwby(bydm);
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
	public String preRepDwby(){
		flmap = zsdwbyService.preRepDwby(bydm);
		flmap.put("bydm", bydm);
		return "detail";
	}
	
	public void doRepDwby(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwbyService.doRepDwby(bydm, flmap);
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
	public String[] getDwid() {
		return dwid;
	}
	public void setDwid(String[] dwid) {
		this.dwid = dwid;
	}
	public String getDwids() {
		return dwids;
	}
	public void setDwids(String dwids) {
		this.dwids = dwids;
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
