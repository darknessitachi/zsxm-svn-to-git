package com.group.zsxm.web.action;

import java.io.IOException;
import java.util.ArrayList;
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
import com.group.zsxm.service.DwsbService;
import com.group.zsxm.service.ZsdwbyService;
import com.group.zsxm.web.common.BaseAction;
import com.opensymphony.xwork2.Action;

@ParentPackage("appDefault")
@Scope("prototype")
@Controller
@Results( { @Result(name = "input", value = "/WEB-INF/jsp/dwsb/dwsb.jsp"),
			@Result(name = "pdwsb", value = "/WEB-INF/jsp/dwsb/dwsb_d.jsp"),
			@Result(name = "dwsb1", value = "/WEB-INF/jsp/dwsb/dwsb_sh1.jsp"),
			@Result(name = "dwsb2", value = "/WEB-INF/jsp/dwsb/dwsb_sh2.jsp"),
			@Result(name = "dwsb3", value = "/WEB-INF/jsp/dwsb/dwsb_sh3.jsp"),
			@Result(name = "view1", value = "/WEB-INF/jsp/dwsb/dwsb1view.jsp"),
			@Result(name = "view2", value = "/WEB-INF/jsp/dwsb/dwsb2view.jsp"),
			@Result(name = "view3", value = "/WEB-INF/jsp/dwsb/dwsb3view.jsp"),
			@Result(name = "dwsel", value = "/WEB-INF/jsp/dwsb/dwsb_sel.jsp")
		})
public class DwsbAction extends BaseAction{
	@Autowired
	@Qualifier("dwsbService")
	private DwsbService dwsbService;
	
	private Map<String, String> query;
	private Map<String, String> dwsb;
	private String[] dwid;
	private List fields;
	private List<Map<String, String>> qlist;
	private Xtuser xtuser;
	private String opttype;
	private String value;
	private String treekey;
	private String dm;
	private String dmlx;
	private String sbdm;
	private String dwids;
	private String thyy;
	private Map<String, String> jbxx;
	private List<Map<String, String>> lxs;
	public List<Map<String, String>> getLxs() {
		return lxs;
	}
	public void setLxs(List<Map<String, String>> lxs) {
		this.lxs = lxs;
	}
	public Map<String, String> getJbxx() {
		return jbxx;
	}
	public void setJbxx(Map<String, String> jbxx) {
		this.jbxx = jbxx;
	}
	public String getDwids() {
		return dwids;
	}
	public void setDwids(String dwids) {
		this.dwids = dwids;
	}
	public Xtuser getXtuser() {
		return xtuser;
	}
	public void setXtuser(Xtuser xtuser) {
		this.xtuser = xtuser;
	}
	public void prepare() {
		xtuser = (Xtuser)getSession().getAttribute(Constants.USER_SESSION_KEY);
	} 
	
	public String execute(){
		return Action.INPUT;
	}
	

	public void doGetList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		getLimitPage().setRowAttributes(gridHandler.getTotalRowNum(), gridHandler.getPageNum(), gridHandler.getPageSize());
		Map param = getParameterSimpleMap();
		Page page = (Page) dwsbService.getList(getLimitPage(), param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo(),null,null);
		gridHandler.setTotalRowNum(page.getTotalCount());
		gridHandler.setData((List) page.getData());
		renderText(gridHandler.getLoadResponseText());
	}

	public void doExportList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		Map param = gridHandler.getGridRequestParameters();
		param = getParameterSimpleMap();
		List list = (List) dwsbService.getList(null, param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo(),value,treekey);
		try {
			gridHandler.exportXLSfromMaps(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getDwsbWaitSelect(){
		fields = ArrayToList(
				new String[] { "dwdm", "dwmc", "dwzt_mc", "dwlx_mc", "xm_mc" },
				new String[] { "组织机构代码", "单位名称", "单位状态", "单位类型" , "招资项目"});
		qlist = dwsbService.getDwsbWaitSelect(query,dm);
		return "dwsel";
	}
	
	public void doSelectDwsbmx(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			dwsbService.doSelectDwsbmx(dm, dwid,dmlx);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doDelSelectedDwsb(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			dwsbService.doDelSelectedDwsb(dwids, dm,dmlx);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	
	public void getDwsbTree(){
		List<TreeBean> treeBeans = dwsbService.getDwsbTree();
		TreeFactory tf = new TreeFactory();	
		tf.setPdm("0");
		tf.setIsDisabledCheck(false);
		tf.setRootText("选择");
		renderText(tf.create(treeBeans));
	}
	
	public String preDwsb(){
		return "pdwsb";
	}
	
	public void doAddDwsb(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			dwsbService.doAddDwsb(dwsb);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String repDwsb(){
		dwsb = dwsbService.repDwsb(dm);
		return "pdwsb";
	}
	
	public void doRepDwsb(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			dwsbService.doRepDwsb(dwsb);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	} 
	
	public void doDelDwsb(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			dwsbService.doDelDwsb(dm);
			message = new Message("1","删除成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void checkWithEnd(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			dwsbService.checkWithEnd(dm);
			message = new Message("1","成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void updateEnd(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			dwsbService.updateEnd(dm);
			message = new Message("1","更新成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	
	public String preDatash(){
		
		if(dmlx.equals("1")){
			lxs = dwsbService.getDwsblxList();
			dwsb = dwsbService.preDwsb1(dwids, dm);
			return "dwsb1";
		}else if(dmlx.equals("2")){
			dwsb = dwsbService.preDwsb2(dwids, dm);
			return "dwsb2";
		}else if(dmlx.equals("3")){
			dwsb = dwsbService.preDwsb3(dwids, dm);
			return "dwsb3";
		}
		return null;
	}
	
	public void doDataSh(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			if(opttype != null && opttype.equals("1")){
				dwsbService.doDatashWithTh(dwids, dm, thyy);
			}else{
				dwsbService.doDatashWithTg(dwids, dm);
			}
			message = new Message("1","审核成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	

	public String dwsb1view(){
		lxs = dwsbService.getDwsblxList();
		dwsb = dwsbService.preDwsb1(xtuser.getUserid(), sbdm);
		return "view1";
	}
	public String dwsb2view(){
		dwsb = dwsbService.preDwsb2(xtuser.getUserid(), sbdm);
		return "view2";
	}
	public String dwsb3view(){
		dwsb = dwsbService.preDwsb3(xtuser.getUserid(), sbdm);
		return "view3";
	}
	
	/**
	 * Mapping
	 * @return
	 */
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public DwsbService getDwsbService() {
		return dwsbService;
	}
	public void setDwsbService(DwsbService dwsbService) {
		this.dwsbService = dwsbService;
	}
	public Map<String, String> getDwsb() {
		return dwsb;
	}
	public void setDwsb(Map<String, String> dwsb) {
		this.dwsb = dwsb;
	}
	public String getOpttype() {
		return opttype;
	}
	public void setOpttype(String opttype) {
		this.opttype = opttype;
	}
	public List getFields() {
		return fields;
	}
	public void setFields(List fields) {
		this.fields = fields;
	}
	public List<Map<String, String>> getQlist() {
		return qlist;
	}
	public void setQlist(List<Map<String, String>> qlist) {
		this.qlist = qlist;
	}

	public Map<String, String> getQuery() {
		return query;
	}
	public void setQuery(Map<String, String> query) {
		this.query = query;
	}
	public String[] getDwid() {
		return dwid;
	}
	public void setDwid(String[] dwid) {
		this.dwid = dwid;
	}
	public String getThyy() {
		return thyy;
	}
	public void setThyy(String thyy) {
		this.thyy = thyy;
	}
	public String getDmlx() {
		return dmlx;
	}
	public void setDmlx(String dmlx) {
		this.dmlx = dmlx;
	}
	public String getSbdm() {
		return sbdm;
	}
	public void setSbdm(String sbdm) {
		this.sbdm = sbdm;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getTreekey() {
		return treekey;
	}
	public void setTreekey(String treekey) {
		this.treekey = treekey;
	}
}
