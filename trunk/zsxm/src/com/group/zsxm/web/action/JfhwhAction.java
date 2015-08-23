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
import com.group.core.common.Message;
import com.group.core.common.Page;
import com.group.core.common.TreeBean;
import com.group.core.common.TreeFactory;
import com.group.zsxm.entity.XtDict;
import com.group.zsxm.entity.Xtuser;
import com.group.zsxm.service.JfhService;
import com.group.zsxm.service.ZsdwService;
import com.group.zsxm.web.common.BaseAction;
import com.opensymphony.xwork2.Action;

@ParentPackage("appDefault")
@Scope("prototype")
@Controller
@Results( { 
			@Result(name = "input", value = "/WEB-INF/jsp/jfhwh/jfhwh.jsp"),
			@Result(name = "jfhsb1", value = "/WEB-INF/jsp/jfhwh/jfhwh1.jsp"),
			@Result(name = "jfhsb2", value = "/WEB-INF/jsp/jfhwh/jfhwh2.jsp"),
			@Result(name = "error", value = "/WEB-INF/jsp/jfhsb/jfhsb_e.jsp"),
			@Result(name = "jfhsh1_xg", value = "/WEB-INF/jsp/jfhwh/jfhsb1_xg.jsp"),
			@Result(name = "jfhsh2_xg", value = "/WEB-INF/jsp/jfhwh/jfhsb2_xg.jsp"),
			@Result(name = "pjfhsb", value = "/WEB-INF/jsp/jfhwh/jfhwh_d.jsp")
		})
public class JfhwhAction  extends BaseAction{
	@Autowired
	@Qualifier("jfhService")
	private JfhService jfhService;
	
	@Autowired
	@Qualifier("zsdwService")
	private ZsdwService zsdwService;
	
	
	private Map<Integer, List<XtDict>> xtdlbs;
	private List<XtDict> xtdict15; 
	private List<XtDict> xtdict25;
	private List<XtDict> xtdict16;
	private List<XtDict> xtdict26;
	private List<XtDict> xtdict14;
	private String ryid;
	private Map<String, String> zsdw;
	private Map<String, String> query;
	private Map<String, String> jfhsb;
	private Map<String, String> jfh;
	private String[] dwid;
	private List fields;
	private List<Map<String, String>> qlist;
	private Xtuser xtuser;
	private String opttype;
	private String treekey;
	private String thyy;
	private String dm;
	private String dwids;
	private String sbid;
	private String zzbz1,zzbz2,zzbz3;
	
	
	public String execute(){
		return Action.INPUT;
	}
	
	
	public void getJfhwhTree(){
		List<TreeBean> treeBeans = jfhService.getJfhTree();
		TreeFactory tf = new TreeFactory();	
		tf.setPdm("0");
		tf.setIsDisabledCheck(false);
		tf.setRootText("选择");
		renderText(tf.create(treeBeans));
	}
	

	public void getJfhwhTreebyShzf(){
		List<TreeBean> treeBeans = jfhService.getJfhTreeShzf();
		TreeFactory tf = new TreeFactory();	
		tf.setPdm("0");
		tf.setIsDisabledCheck(false);
		tf.setRootText("选择");
		renderText(tf.create(treeBeans));
	}
	

	public void getJfhwhTreebyAjf(){
		List<TreeBean> treeBeans = jfhService.getJfhTreeAjf();
		TreeFactory tf = new TreeFactory();	
		tf.setPdm("0");
		tf.setIsDisabledCheck(false);
		tf.setRootText("选择");
		renderText(tf.create(treeBeans));
	}
	
	public void getShzfDwTree(){
		List<TreeBean> treeBeans = jfhService.getShzfDwTree();
		TreeFactory tf = new TreeFactory();	
		tf.setPdm("0");
		tf.setIsDisabledCheck(false);
		tf.setRootText("选择");
		renderText(tf.create(treeBeans));
	}
	

	public void getAjfDwTree(){
		List<TreeBean> treeBeans = jfhService.getAjfDwTree();
		TreeFactory tf = new TreeFactory();	
		tf.setPdm("0");
		tf.setIsDisabledCheck(false);
		tf.setRootText("选择");
		renderText(tf.create(treeBeans));
	}

	public String getJfhsbList(){
		String[] dm =  treekey.split("@");
		if(dm[0].substring(4,dm[0].length()).equals("0001")){
			return "jfhsb1";
		}else if(dm[0].substring(4,dm[0].length()).equals("0002")){
			return "jfhsb2";
		}else{
			return "error";
		}
	}

	public void getShzfList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		getLimitPage().setRowAttributes(gridHandler.getTotalRowNum(), gridHandler.getPageNum(), gridHandler.getPageSize());
		Map param = getParameterSimpleMap();
		Page page = (Page) jfhService.getShzfList(getLimitPage(), param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo());
		gridHandler.setTotalRowNum(page.getTotalCount());
		gridHandler.setData((List) page.getData());
		renderText(gridHandler.getLoadResponseText());
	}

	public void exportShzfList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		Map param = gridHandler.getGridRequestParameters();
		List list = (List) jfhService.getShzfList(null, param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo());
		try {
			gridHandler.exportXLSfromMaps(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	public void getAjfList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		getLimitPage().setRowAttributes(gridHandler.getTotalRowNum(), gridHandler.getPageNum(), gridHandler.getPageSize());
		Map param = getParameterSimpleMap();
		Page page = (Page) jfhService.getAjfList(getLimitPage(), param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo());
		gridHandler.setTotalRowNum(page.getTotalCount());
		gridHandler.setData((List) page.getData());
		renderText(gridHandler.getLoadResponseText());
	}

	public void exportAjfList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		Map param = getParameterSimpleMap();
		List list = (List) jfhService.getAjfList(null, param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo());
		try {
			gridHandler.exportXLSfromMaps(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String preDataxg1(){
		zsdw = zsdwService.preRyxxU(dwid[0], ryid);
		xtdlbs = zsdwService.getDictListByLbid("14");
		xtdict14 = xtdlbs.get(14);
		xtdlbs = zsdwService.getDictListByLbid("15");
		xtdict15 = xtdlbs.get(15);
		xtdlbs = zsdwService.getDictListByLbid("25");
		xtdict25 = xtdlbs.get(25);
		xtdlbs = zsdwService.getDictListByLbid("26");
		xtdict26 = xtdlbs.get(26);
		//jfh = jfhsbService.preJfhshzf(xtuser.getUserid(), ryid);
		jfh = jfhService.preDatash1(sbid);
		
		jfh.put("dwid", dwid[0]);
		jfh.put("ryid", ryid);
		jfh.put("treekey", treekey);
		return "jfhsh1_xg";
	}

	public void doDataxg1(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			jfhService.doDataxg1(sbid, jfh);
			message = new Message("1","修改成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	

	public String preDataxg2(){
		zsdw = zsdwService.preRyxxU(dwid[0], ryid);
		xtdlbs = zsdwService.getDictListByLbid("14");
		xtdict14 = xtdlbs.get(14);
		xtdlbs = zsdwService.getDictListByLbid("15");
		xtdict15 = xtdlbs.get(15);
		xtdlbs = zsdwService.getDictListByLbid("25");
		xtdict25 = xtdlbs.get(25);
		xtdlbs = zsdwService.getDictListByLbid("26");
		xtdict26 = xtdlbs.get(26);
		jfh = jfhService.preDatash2(sbid);
		
		jfh.put("dwid", dwid[0]);
		jfh.put("ryid", ryid);
		jfh.put("treekey", treekey);
		return "jfhsh2_xg";
	}
	public void doDataxg2(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			jfhService.doDataxg2(sbid, jfh);
			message = new Message("1","修改成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	

	public String preJfhsb(){
		return "pjfhsb";
	}
	
	public void doAddJfhsb(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			jfhService.doAddJfhsb(jfhsb);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String repJfhsb(){
		jfhsb = jfhService.repJfhsb(dm);
		return "pjfhsb";
	}
	
	public void doRepJfhsb(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			jfhService.doRepJfhsb(jfhsb);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	} 
	
	public void doDelJfhsb(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			jfhService.doDelJfhsb(dm);
			message = new Message("1","删除成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	/***
	 * Mapping
	 * @return
	 */
	public String getTreekey() {
		return treekey;
	}


	public void setTreekey(String treekey) {
		this.treekey = treekey;
	}


	public JfhService getJfhService() {
		return jfhService;
	}


	public void setJfhService(JfhService jfhService) {
		this.jfhService = jfhService;
	}


	public Map<Integer, List<XtDict>> getXtdlbs() {
		return xtdlbs;
	}


	public void setXtdlbs(Map<Integer, List<XtDict>> xtdlbs) {
		this.xtdlbs = xtdlbs;
	}


	public List<XtDict> getXtdict15() {
		return xtdict15;
	}


	public void setXtdict15(List<XtDict> xtdict15) {
		this.xtdict15 = xtdict15;
	}


	public List<XtDict> getXtdict25() {
		return xtdict25;
	}


	public void setXtdict25(List<XtDict> xtdict25) {
		this.xtdict25 = xtdict25;
	}


	public List<XtDict> getXtdict16() {
		return xtdict16;
	}


	public void setXtdict16(List<XtDict> xtdict16) {
		this.xtdict16 = xtdict16;
	}


	public List<XtDict> getXtdict26() {
		return xtdict26;
	}


	public void setXtdict26(List<XtDict> xtdict26) {
		this.xtdict26 = xtdict26;
	}


	public List<XtDict> getXtdict14() {
		return xtdict14;
	}


	public void setXtdict14(List<XtDict> xtdict14) {
		this.xtdict14 = xtdict14;
	}


	public String getRyid() {
		return ryid;
	}


	public void setRyid(String ryid) {
		this.ryid = ryid;
	}


	public Map<String, String> getZsdw() {
		return zsdw;
	}


	public void setZsdw(Map<String, String> zsdw) {
		this.zsdw = zsdw;
	}


	public Map<String, String> getQuery() {
		return query;
	}


	public void setQuery(Map<String, String> query) {
		this.query = query;
	}


	public Map<String, String> getJfhsb() {
		return jfhsb;
	}


	public void setJfhsb(Map<String, String> jfhsb) {
		this.jfhsb = jfhsb;
	}


	public Map<String, String> getJfh() {
		return jfh;
	}


	public void setJfh(Map<String, String> jfh) {
		this.jfh = jfh;
	}


	public String[] getDwid() {
		return dwid;
	}


	public void setDwid(String[] dwid) {
		this.dwid = dwid;
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


	public Xtuser getXtuser() {
		return xtuser;
	}


	public void setXtuser(Xtuser xtuser) {
		this.xtuser = xtuser;
	}


	public String getOpttype() {
		return opttype;
	}


	public void setOpttype(String opttype) {
		this.opttype = opttype;
	}


	public String getThyy() {
		return thyy;
	}


	public void setThyy(String thyy) {
		this.thyy = thyy;
	}


	public String getDm() {
		return dm;
	}


	public void setDm(String dm) {
		this.dm = dm;
	}


	public String getDwids() {
		return dwids;
	}


	public void setDwids(String dwids) {
		this.dwids = dwids;
	}


	public String getSbid() {
		return sbid;
	}


	public void setSbid(String sbid) {
		this.sbid = sbid;
	}


	public String getZzbz1() {
		return zzbz1;
	}


	public void setZzbz1(String zzbz1) {
		this.zzbz1 = zzbz1;
	}


	public String getZzbz2() {
		return zzbz2;
	}


	public void setZzbz2(String zzbz2) {
		this.zzbz2 = zzbz2;
	}


	public String getZzbz3() {
		return zzbz3;
	}


	public void setZzbz3(String zzbz3) {
		this.zzbz3 = zzbz3;
	}


}
