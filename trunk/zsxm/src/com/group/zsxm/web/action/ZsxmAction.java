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
import org.springframework.stereotype.Controller;

import com.group.core.Constants;
import com.group.core.common.Message;
import com.group.core.common.TreeBean;
import com.group.core.common.TreeFactory;
import com.group.zsxm.entity.Xtuser;
import com.group.zsxm.service.ZsxmService;
import com.group.zsxm.service.common.SystemService;
import com.group.zsxm.web.common.BaseAction;
import com.opensymphony.xwork2.Action;

@ParentPackage("appDefault")
@Scope("prototype")
@Controller
@Results( { 
			@Result(name = "input", value = "/WEB-INF/jsp/zsxm/zsxm.jsp"),
			@Result(name = "rep", value = "/WEB-INF/jsp/zsxm/zsxm_rep.jsp"),
			@Result(name = "zsxm", value = "/WEB-INF/jsp/zsxm/zsxm_info.jsp"),
			@Result(name = "jzqk", value = "/WEB-INF/jsp/zsxm/zsxm_jzqk.jsp"),
			@Result(name = "jzqkd", value = "/WEB-INF/jsp/zsxm/zsxm_jzqk_d.jsp"),
			@Result(name = "lxr", value = "/WEB-INF/jsp/zsxm/zsxm_dflxr.jsp"),
			@Result(name = "lxrd", value = "/WEB-INF/jsp/zsxm/zsxm_dflxr_d.jsp"),
			@Result(name = "view", value = "/WEB-INF/jsp/zsxmview/zsxm_rep.jsp"),
			@Result(name = "allview", value = "/WEB-INF/jsp/zsxmview/zsxm_view_all.jsp")
			})
public class ZsxmAction extends BaseAction{

	@Autowired
	@Qualifier("zsxmService")
	private ZsxmService zsxmService;
	
	@Autowired
	@Qualifier("systemService")
	private SystemService systemService;
	
	private String querydm;
	private Map<String, String> zsxm;
	private List<Map<String, String>> xmlbs;
	private List<Map<String, String>> xmxjs;
	private List<Map<String, String>> xmjds;
	private List<Map<String, String>> xtusers;
	private List<Map<String, String>> xtdict47;
	private String pname;
	private String opttype;
	private List<Map<String, String>> qlist;
	private List<Map<String, String>> qlist2;
	private String xh;
	private String[] dmkey;
	private String xmid;
	private Xtuser xtuser;
	public void prepare() {
		xtuser = (Xtuser)getSession().getAttribute(Constants.USER_SESSION_KEY);
	} 
	public String execute(){
		
		return Action.INPUT;
	}

	public String preZsxmU(){
		return "rep";
	}
	
	public String preZsxm(){
		xmlbs = zsxmService.getDictList(1);
		xmxjs = zsxmService.getDictList(2);
		xmjds = zsxmService.getDictList(3);
		
		xtusers = systemService.getXtuserList();
		if(xmid != null && !xmid.equals("")){
			
			zsxm = zsxmService.preZsxmU(xmid);
		}else{
			zsxm = new HashMap<String, String>();
			zsxm.put("xmlb", "001");
			zsxm.put("xmxj", "001");
			zsxm.put("yjtr", "0.00");
			zsxm.put("xmgzr", xtuser.getUserid());
			zsxm.put("xmbh", String.valueOf(zsxmService.getXmbh()));
		}
		return "zsxm";
	}
	
	public void doSaveZsxm(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			xmid = zsxmService.doSaveZsxm(xmid, zsxm);
			message = new Message("1",xmid);
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	
	public String viewZsxm(){
		zsxm = zsxmService.viewZsxm(xmid);
		if(zsxm.get("XMJZQK")!= null ){
			zsxm.put("XMJZQK", zsxm.get("XMJZQK").replaceAll("\\r\\n", "####"));
		}
		return "view";
	}
	
	public void getXmTree(){
		List<TreeBean> treeBeans = zsxmService.getXmTree(querydm);
		TreeFactory tf = new TreeFactory();	
		tf.setPdm("0");
		tf.setIsDisabledCheck(false);
		tf.setRootText("选择数据");
		renderText(tf.create(treeBeans));
	}
	
	
	/**
	 * 进展情况
	 * @return
	 */
	public String preJzqk(){
		qlist = zsxmService.getJzqkList(xmid);
		return "jzqk";
	}
	
	public String preJzqkI(){
		xtusers = systemService.getXtuserList();
		return "jzqkd";
	}
	
	public void doJzqkI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsxmService.doJzqkI(xmid, zsxm);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preJzqkU(){
		zsxm = zsxmService.preJzqkU(xmid, xh);
		xtusers = systemService.getXtuserList();
		return "jzqkd";
	}
	
	public void doJzqkU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsxmService.doJzqkU(xmid, xh, zsxm);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doJzqkD(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsxmService.doJzqkD(xmid, dmkey);
			message = new Message("1","删除成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	/***
	 * 对应联系人
	 * @return
	 */
	public String preLxr(){
		qlist = zsxmService.getLxrList(xmid);
		return "lxr";
	}
	
	public String preLxrI(){
		return "lxrd";
	}
	
	public void doLxrI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsxmService.doLxrI(xmid, zsxm);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preLxrU(){
		zsxm = zsxmService.preLxrU(xmid, xh);
		return "lxrd";
	}
	
	public void doLxrU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsxmService.doLxrU(xmid, xh, zsxm);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doLxrD(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsxmService.doLxrD(xmid, dmkey);
			message = new Message("1","删除成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doSetLxrForDy(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsxmService.doSetLxrForDy(xmid, xh);
			message = new Message("1","设置成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	

	public String getZsxmAllView(){
		zsxm = zsxmService.preZsxmU(xmid);
		qlist = zsxmService.getJzqkList(xmid);
		qlist2 = zsxmService.getLxrList(xmid);
		return "allview";
	}
	/**
	 * Maping
	 * @return
	 */
	public Map<String, String> getZsxm() {
		return zsxm;
	}
	public void setZsxm(Map<String, String> zsxm) {
		this.zsxm = zsxm;
	}
	public List<Map<String, String>> getXmlbs() {
		return xmlbs;
	}
	public void setXmlbs(List<Map<String, String>> xmlbs) {
		this.xmlbs = xmlbs;
	}
	public List<Map<String, String>> getXmxjs() {
		return xmxjs;
	}
	public void setXmxjs(List<Map<String, String>> xmxjs) {
		this.xmxjs = xmxjs;
	}
	public List<Map<String, String>> getXmjds() {
		return xmjds;
	}
	public void setXmjds(List<Map<String, String>> xmjds) {
		this.xmjds = xmjds;
	}
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
	public List<Map<String, String>> getXtusers() {
		return xtusers;
	}
	public void setXtusers(List<Map<String, String>> xtusers) {
		this.xtusers = xtusers;
	}
	public String getQuerydm() {
		return querydm;
	}
	public void setQuerydm(String querydm) {
		this.querydm = querydm;
	}
	public List<Map<String, String>> getXtdict47() {
		return xtdict47;
	}
	public void setXtdict47(List<Map<String, String>> xtdict47) {
		this.xtdict47 = xtdict47;
	}
	public List<Map<String, String>> getQlist() {
		return qlist;
	}
	public void setQlist(List<Map<String, String>> qlist) {
		this.qlist = qlist;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String[] getDmkey() {
		return dmkey;
	}
	public void setDmkey(String[] dmkey) {
		this.dmkey = dmkey;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getOpttype() {
		return opttype;
	}
	public void setOpttype(String opttype) {
		this.opttype = opttype;
	}
	public List<Map<String, String>> getQlist2() {
		return qlist2;
	}
	public void setQlist2(List<Map<String, String>> qlist2) {
		this.qlist2 = qlist2;
	}
	
}
