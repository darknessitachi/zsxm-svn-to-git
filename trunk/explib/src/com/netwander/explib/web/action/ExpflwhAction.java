package com.netwander.explib.web.action;

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

import com.netwander.core.Constants;
import com.netwander.core.common.Message;
import com.netwander.core.common.Page;
import com.netwander.core.common.TreeBean;
import com.netwander.core.common.TreeFactory;
import com.netwander.explib.entity.Xtuser;
import com.netwander.explib.service.ExpflwhService;
import com.netwander.explib.web.common.BaseAction;
import com.opensymphony.xwork2.Action;

@ParentPackage("appDefault")
@Scope("prototype")
@Controller
@Results({ 
	@Result(name = "input", value = "/WEB-INF/pages/flwh/expflwh.jsp"),
	@Result(name = "fltree", value = "/WEB-INF/pages/flwh/expflwh_tree.jsp"),
	@Result(name = "detail", value = "/WEB-INF/pages/flwh/expflwh_d.jsp"),
	@Result(name = "select", value = "/WEB-INF/pages/flwh/expflwhmx_sel.jsp"),
	
	@Result(name = "by", value = "/WEB-INF/pages/flwh/expbywh_tree.jsp"),
	@Result(name = "bytree", value = "/WEB-INF/pages/flwh/expbywh_tree.jsp"),
	@Result(name = "detailby", value = "/WEB-INF/pages/flwh/expbywh_d.jsp"),
	@Result(name = "selectby", value = "/WEB-INF/pages/flwh/expbywhmx_sel.jsp")
})
public class ExpflwhAction extends BaseAction{
	
	@Autowired
	@Qualifier("expflwhService")
	private ExpflwhService expflwhService;
	
	private Map<String, String> flmap;
	private String tkey;
	private String fldm;
	private Integer flbz;
	private String[] rcid;
	private String rcids;
	private List<Map<String, String>> qlist;
	private String type;
	private Integer option;
	private List fields;
	private Map<String, String> query;
	
	private Xtuser xtuser;
	public void prepare() {
		xtuser = (Xtuser)getSession().getAttribute(Constants.USER_SESSION_KEY);
	} 
	public String execute(){
		return Action.INPUT;
	}
	
	public void getRcflwhTree(){
		List<TreeBean> treeBeans = expflwhService.getFlwhTree(xtuser.getRoledm(),flbz);
		TreeFactory tf = new TreeFactory();	
		tf.setPdm("0");
		tf.setIsDisabledCheck(false);
		tf.setRootText("选择专家分类");
		renderText(tf.create(treeBeans));
	}
	
	public void getRcUserflwhTree(){
		String userid = query.get("userid")==null?"":query.get("userid");
		List<TreeBean> treeBeans = expflwhService.getFlwhUserTree(xtuser.getRoledm(),flbz,userid);
		TreeFactory tf = new TreeFactory();	
		tf.setPdm("0");
		tf.setIsDisabledCheck(false);
		tf.setRootText("选择专家分类");
		renderText(tf.create(treeBeans));
	}

	public void getRcbywhTree(){
		List<TreeBean> treeBeans = expflwhService.getBywhTree(xtuser.getRoledm(),flbz);
		TreeFactory tf = new TreeFactory();	
		tf.setPdm("0");
		tf.setIsDisabledCheck(false);
		tf.setRootText("选择专家标引");
		renderText(tf.create(treeBeans));
	}
	
	
	public String getRcxxWaitSelect(){
		fields = ArrayToList(
				new String[] { "rcname", "sex_mc", "szdq_mc",  "dwdq_mc", "xl_mc", "xw_mc" },
				new String[] { "姓名", "性别",  "所在地区",  "单位所在地", "学历", "学位" });
		//qlist = expflwhService.getRcxxWaitSelect(query,fldm);
		return "select";
	}
	
	public void getRcxxWaitSelected(){
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		getLimitPage().setRowAttributes(gridHandler.getTotalRowNum(), gridHandler.getPageNum(), gridHandler.getPageSize());
		Map param = getParameterSimpleMap();
		Page page = (Page) expflwhService.getRcxxWaitSelected(getLimitPage(), param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo(),xtuser);
		gridHandler.setTotalRowNum(page.getTotalCount());
		gridHandler.setData((List) page.getData());
		renderText(gridHandler.getLoadResponseText());
	}
	
	public void doSelectRcflmx(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			expflwhService.doSelectRcflmx(fldm, rcid);
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
			expflwhService.doDelSelectedflmx(rcids);
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
		Page page = (Page) expflwhService.getRcxxSelected(getLimitPage(), param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo(),xtuser);
		gridHandler.setTotalRowNum(page.getTotalCount());
		gridHandler.setData((List) page.getData());
		renderText(gridHandler.getLoadResponseText());
	}
	
	public void exportList(){
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		getLimitPage().setRowAttributes(gridHandler.getTotalRowNum(), gridHandler.getPageNum(), gridHandler.getPageSize());
		Map param = getParameterSimpleMap();
		Page page = (Page) expflwhService.getRcxxSelected(null, param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo(),xtuser);
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
			expflwhService.doAddFlwh(flmap,flbz);
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
			expflwhService.doDelFlwh(fldm,flbz);
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
		flmap = expflwhService.preRepFlwh(fldm);
		flmap.put("fldm", fldm);
		return "detail";
	}
	
	public void doRepflwh(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			expflwhService.doRepFlwh(fldm, flmap,flbz);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void getTabpanel(){
		String reXml = expflwhService.getTabPanel(tkey,null);
		renderXML(reXml);
	}
	
	
	public String getExpflTree(){
		return "fltree";
	}
	

	public String by(){
		return "by";
	}
	
	public void getRcxxWaitSelectedBy(){
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		getLimitPage().setRowAttributes(gridHandler.getTotalRowNum(), gridHandler.getPageNum(), gridHandler.getPageSize());
		Map param = getParameterSimpleMap();
		Page page = (Page) expflwhService.getRcxxWaitSelectedBy(getLimitPage(), param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo(),xtuser);
		gridHandler.setTotalRowNum(page.getTotalCount());
		gridHandler.setData((List) page.getData());
		renderText(gridHandler.getLoadResponseText());
	}

	public void doAddbywh(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			expflwhService.doAddBywh(flmap,flbz);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doDelbywh(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			expflwhService.doDelBywh(fldm,flbz);
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
	public String preRepbywh(){
		flmap = expflwhService.preRepBywh(fldm);
		flmap.put("fldm", fldm);
		return "detailby";
	}
	
	public void doRepbywh(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			expflwhService.doRepBywh(fldm, flmap,flbz);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	

	public String preAddbywh(){
		return "detailby";
	}
	

	public void getRcxxSelectedby(){
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		getLimitPage().setRowAttributes(gridHandler.getTotalRowNum(), gridHandler.getPageNum(), gridHandler.getPageSize());
		Map param = getParameterSimpleMap();
		Page page = (Page) expflwhService.getRcxxSelectedBy(getLimitPage(), param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo(),xtuser);
		gridHandler.setTotalRowNum(page.getTotalCount());
		gridHandler.setData((List) page.getData());
		renderText(gridHandler.getLoadResponseText());
	}
	
	public void exportListby(){
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		getLimitPage().setRowAttributes(gridHandler.getTotalRowNum(), gridHandler.getPageNum(), gridHandler.getPageSize());
		Map param = getParameterSimpleMap();
		Page page = (Page) expflwhService.getRcxxSelectedBy(null, param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo(),xtuser);
		gridHandler.setTotalRowNum(page.getTotalCount());
		gridHandler.setData((List) page.getData());
		renderText(gridHandler.getLoadResponseText());
	}
	

	public String getRcxxWaitSelectBy(){
		fields = ArrayToList(
				new String[] { "rcname", "sex_mc", "szdq_mc",  "dwdq_mc", "xl_mc", "xw_mc" },
				new String[] { "姓名", "性别",  "所在地区",  "单位所在地", "学历", "学位" });
		//qlist = expflwhService.getRcxxWaitSelectBy(query,fldm);
		return "selectby";
	}
	
	public void doSelectRcbymx(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			expflwhService.doSelectRcbymx(fldm, rcid);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doDelSelectedbymx(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			expflwhService.doDelSelectedbymx(rcids,fldm);
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
	public Integer getFlbz() {
		return flbz;
	}
	public void setFlbz(Integer flbz) {
		this.flbz = flbz;
	}
	public String[] getRcid() {
		return rcid;
	}
	public void setRcid(String[] rcid) {
		this.rcid = rcid;
	}
	public String getRcids() {
		return rcids;
	}
	public void setRcids(String rcids) {
		this.rcids = rcids;
	}
	public List<Map<String, String>> getQlist() {
		return qlist;
	}
	public void setQlist(List<Map<String, String>> qlist) {
		this.qlist = qlist;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public Integer getOption() {
		return option;
	}
	public void setOption(Integer option) {
		this.option = option;
	}
	public String getTkey() {
		return tkey;
	}
	public void setTkey(String tkey) {
		this.tkey = tkey;
	}
}
