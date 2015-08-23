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
import com.group.zsxm.entity.DmMc;
import com.group.zsxm.entity.XtDict;
import com.group.zsxm.entity.Xtuser;
import com.group.zsxm.service.FzhtService;
import com.group.zsxm.service.ZsdwService;
import com.group.zsxm.web.common.BaseAction;
import com.opensymphony.xwork2.Action;
@ParentPackage("appDefault")
@Scope("prototype")
@Controller
@Results( { @Result(name = "input", value = "/WEB-INF/jsp/fzhtview/fzht.jsp"),
			@Result(name = "update", value = "/WEB-INF/jsp/fzhtview/fzht_rep.jsp"),
			@Result(name = "rep", value = "/WEB-INF/jsp/fzhtview/fzht_rep.jsp"),
			@Result(name = "ht", value = "/WEB-INF/jsp/fzhtview/fzht_ht.jsp"),
			@Result(name = "fzsq", value = "/WEB-INF/jsp/fzhtview/fzht_sq.jsp"),
			@Result(name = "fzsqd", value = "/WEB-INF/jsp/fzhtview/fzht_sq_d.jsp"),
			
			@Result(name = "selectdw", value = "/WEB-INF/jsp/fzhtview/fzht_dw_sel.jsp"),
			@Result(name = "view", value = "/WEB-INF/jsp/fzhtview/fzht_view.jsp")
			})
public class FzhtviewAction  extends BaseAction{
	@Autowired
	@Qualifier("fzhtService")
	private FzhtService fzhtService;
	@Autowired
	@Qualifier("zsdwService")
	private ZsdwService zsdwService;
	private Map<String, String> fzht;
	private List<Map<String, String>> qlist;
	private Map<String, String> query;
	private String htid;
	private String xh;
	private String pname;
	private Map<Integer, List<XtDict>> xtdlbs;
	private List<XtDict> xtdict23;
	private List<XtDict> xtdict6; 
	private List<XtDict> xtdict7; 
	private List<XtDict> xtdict8; 
	private List<XtDict> xtdict33; 
	private List<DmMc> fields;
	private String[] dmkey;
	private Xtuser xtuser;
	private String opttype;
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
	
	public String preFzhtU(){
		return "update";
	}
	
	public String viewZsdw(){
		//maps = zsdwService.preView(dwid);
		return "view";
	}
	
	public String preFzht(){
		xtdlbs = zsdwService.getDictListByLbid("23");
		xtdict23 = xtdlbs.get(23);
		
		Map<Integer, List<XtDict>> xtdlbs_ht = zsdwService.getDictListWithSelectAll();
		xtdict6 = xtdlbs_ht.get(6);
		xtdict7 = xtdlbs_ht.get(7);
		xtdict8 = xtdlbs_ht.get(8);

		
		if(htid != null && !htid.equals("")){
			fzht = fzhtService.getFzhtinfoByhtid(htid);
		}else{
			fzht = new HashMap<String, String>();
			fzht.put("htfs", "0");
			fzht.put("fzmj", "0.00");
			fzht.put("dwzj", "0.00");
			fzht.put("qmjnfz", "0.00");
			fzht.put("yhzcfz", "0.00");
			fzht.put("xszcfz", "0.00");
			fzht.put("dwid_mc", "");
		}
		return "ht";
	}
	
	public String getDwWaitSelect(){
		fields = ArrayToList(
				new String[] { "dwdm", "dwmc" },
				new String[] { "组织机构代码", "单位名称" });
		qlist = fzhtService.getDwWaitSelect(query);
		return "selectdw";
	}
	
	
	public void doSaveFzht(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			htid = fzhtService.doSaveFzht(htid, fzht);
			message = new Message("1",htid);
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	/**
	 * 房租收取
	 * @return
	 */
	public String preFzsq(){
		qlist = fzhtService.getFzsqList(htid);
		return "fzsq";
	}
	
	public String preFzsqI(){
		fzht = new HashMap();
		fzht.put("ysfz", "0.00");
		fzht.put("wsfz", "0.00");
		fzht.put("ysfzz", "0.00");
		xtdlbs = zsdwService.getDictListByLbid("33");
		xtdict33 = xtdlbs.get(33);
		return "fzsqd";
	}
	
	public void doFzsqI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			fzhtService.doFzsqI(htid, fzht);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preFzsqU(){
		fzht = fzhtService.preFzsqU(htid, xh);
		xtdlbs = zsdwService.getDictListByLbid("33");
		xtdict33 = xtdlbs.get(33);
		return "fzsqd";
	}
	
	public void doFzsqU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			fzhtService.doFzsqU(htid, xh, fzht);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doFzsqD(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			fzhtService.doFzsqD(htid, dmkey);
			message = new Message("1","删除成功");
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
	public FzhtService getFzhtService() {
		return fzhtService;
	}
	public void setFzhtService(FzhtService fzhtService) {
		this.fzhtService = fzhtService;
	}
	public Map<String, String> getFzht() {
		return fzht;
	}
	public void setFzht(Map<String, String> fzht) {
		this.fzht = fzht;
	}
	public List<Map<String, String>> getQlist() {
		return qlist;
	}
	public void setQlist(List<Map<String, String>> qlist) {
		this.qlist = qlist;
	}
	public String getHtid() {
		return htid;
	}
	public void setHtid(String htid) {
		this.htid = htid;
	}
	public List<XtDict> getXtdict23() {
		return xtdict23;
	}
	public void setXtdict23(List<XtDict> xtdict23) {
		this.xtdict23 = xtdict23;
	}
	public Map<Integer, List<XtDict>> getXtdlbs() {
		return xtdlbs;
	}
	public void setXtdlbs(Map<Integer, List<XtDict>> xtdlbs) {
		this.xtdlbs = xtdlbs;
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
	public List<DmMc> getFields() {
		return fields;
	}
	public void setFields(List<DmMc> fields) {
		this.fields = fields;
	}
	public Map<String, String> getQuery() {
		return query;
	}
	public void setQuery(Map<String, String> query) {
		this.query = query;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public ZsdwService getZsdwService() {
		return zsdwService;
	}
	public void setZsdwService(ZsdwService zsdwService) {
		this.zsdwService = zsdwService;
	}
	public String getOpttype() {
		return opttype;
	}
	public void setOpttype(String opttype) {
		this.opttype = opttype;
	}
	public List<XtDict> getXtdict6() {
		return xtdict6;
	}
	public void setXtdict6(List<XtDict> xtdict6) {
		this.xtdict6 = xtdict6;
	}
	public List<XtDict> getXtdict7() {
		return xtdict7;
	}
	public void setXtdict7(List<XtDict> xtdict7) {
		this.xtdict7 = xtdict7;
	}
	public List<XtDict> getXtdict8() {
		return xtdict8;
	}
	public void setXtdict8(List<XtDict> xtdict8) {
		this.xtdict8 = xtdict8;
	}
	public List<XtDict> getXtdict33() {
		return xtdict33;
	}
	public void setXtdict33(List<XtDict> xtdict33) {
		this.xtdict33 = xtdict33;
	}
}
