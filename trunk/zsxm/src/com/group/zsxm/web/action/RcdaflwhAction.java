package com.group.zsxm.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import com.fins.gt.server.GridServerHandler;
import com.group.core.Constants;
import com.group.core.common.Message;
import com.group.core.common.Page;
import com.group.core.common.TreeBean;
import com.group.core.common.TreeFactory;
import com.group.zsxm.entity.Xtuser;
import com.group.zsxm.service.RcdaflwhService;
import com.group.zsxm.web.common.BaseAction;
import com.opensymphony.xwork2.Action;

@ParentPackage("appDefault")
@Scope("prototype")
@Results( { 
		@Result(name = "input", value = "/WEB-INF/jsp/rcda/rcdaFlwh.jsp"),
		@Result(name = "detail", value = "/WEB-INF/jsp/rcda/rcdaFlwh_d.jsp"),
		@Result(name = "select", value = "/WEB-INF/jsp/rcda/rcdaFlmx_sel.jsp")
		 })
public class RcdaflwhAction extends BaseAction{
	private Xtuser xtuser;
	@Autowired
	@Qualifier("rcdaflwhService")
	private RcdaflwhService rcdaflwhService;
	
	private Map<String, String> flmap;
	private String fldm;
	private String[] rcid;
	private String rcids;
	private List<Map<String, String>> qlist;
	private String type;
	private Integer option;
	private List fields;
	private Map<String, String> query;
	
	public void prepare() {
		xtuser = (Xtuser)getSession().getAttribute(Constants.USER_SESSION_KEY);
	} 
	public String execute(){
		return Action.INPUT;
	}
	
	public void getRcflwhTree(){
		List<TreeBean> treeBeans = rcdaflwhService.getFlwhTree();
		TreeFactory tf = new TreeFactory();	
		tf.setPdm("0");
		tf.setIsDisabledCheck(false);
		tf.setRootText("选择人才标引");
		renderText(tf.create(treeBeans));
	}
	
	public String getRcxxWaitSelect(){
		fields = ArrayToList(
				new String[] { "rcname", "sex_mc", "nation_mc", "rclb_mc", "zgbm_mc", "dwdq_mc", "xl_mc", "xw_mc" },
				new String[] { "姓名", "性别", "国籍", "人才类别", "主管部门", "单位所在地", "学历", "学位" });
		qlist = rcdaflwhService.getRcxxWaitSelect(query);
		return "select";
	}
	
	public void doSelectRcflmx(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaflwhService.doSelectRcflmx(fldm, rcid);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doDelSelectedflmx(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaflwhService.doDelSelectedflmx(rcids);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);		
	}
	
	public void getRcxxSelected(){
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		getLimitPage().setRowAttributes(gridHandler.getTotalRowNum(), gridHandler.getPageNum(), gridHandler.getPageSize());
		Map param = getParameterSimpleMap();
		Page page = (Page) rcdaflwhService.getRcxxSelected(getLimitPage(), param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo());
		gridHandler.setTotalRowNum(page.getTotalCount());
		gridHandler.setData((List) page.getData());
		renderText(gridHandler.getLoadResponseText());
	}
	
	public String preAddflwh(){
		return "detail";
	}
	
	public void doAddflwh(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaflwhService.doAddFlwh(flmap);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doDelflwh(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaflwhService.doDelFlwh(fldm);
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
	public String preRepflwh(){
		flmap = rcdaflwhService.preRepFlwh(fldm);
		flmap.put("fldm", fldm);
		return "detail";
	}
	
	public void doRepflwh(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaflwhService.doRepFlwh(fldm, flmap);
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
	public String getFldm() {
		return fldm;
	}
	public void setFldm(String fldm) {
		this.fldm = fldm;
	}
	public String[] getRcid() {
		return rcid;
	}
	public void setRcid(String[] rcid) {
		this.rcid = rcid;
	}
	public List<Map<String, String>> getQlist() {
		return qlist;
	}
	public void setQlist(List<Map<String, String>> qlist) {
		this.qlist = qlist;
	}
	public String getRcids() {
		return rcids;
	}
	public void setRcids(String rcids) {
		this.rcids = rcids;
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

	
}
