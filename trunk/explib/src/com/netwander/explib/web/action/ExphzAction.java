package com.netwander.explib.web.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fins.gt.server.GridServerHandler;
import com.netwander.explib.entity.DmMc;
import com.netwander.explib.entity.XtDict;
import com.netwander.core.Constants;
import com.netwander.explib.entity.Xtuser;
import com.netwander.explib.service.ExpBbService;
import com.netwander.explib.service.ExpService;
import com.netwander.explib.web.common.BaseAction;
import com.opensymphony.xwork2.Action;

@ParentPackage("appDefault")
@Scope("prototype")
@Controller
@Results( { 
	@Result(name = "input", value = "/WEB-INF/pages/report/tjinfo.jsp"),
	@Result(name = "tjjbxx", value = "/WEB-INF/pages/report/tjjbxx.jsp"),
	@Result(name = "dwtj", value = "/WEB-INF/pages/report/dwtjjbxx.jsp")
})
public class ExphzAction extends BaseAction{
	@Autowired
	@Qualifier("expBbService")
	private ExpBbService expBbService;
	

	@Autowired
	@Qualifier("expService")
	private ExpService expService;
	
	private List<Map<String, String>> qlist;
	private Map<String ,String > query;
	private Map<Integer, List<XtDict>> xtdlbs;//基础数据集合
	private List<XtDict> xtdict2;//学历
	private List<XtDict> xtdict3;//学位
	private List<XtDict> xtdict5;//职称
	private List<XtDict> xtdict23;
	private List<XtDict> xtdict12;//单位性质
	private List<XtDict> xtdict21;//
	private List<XtDict> xtdict22;//
	private List<XtDict> xtdict25;//
	private List<XtDict> xtdict28;//
	private List<DmMc> dmmcs;
	private String[] field;
   
    private String xf;
    private String yf;
    
	private Xtuser xtuser;
	public void onPrepare() {
		xtuser = (Xtuser)getSession().getAttribute(Constants.USER_SESSION_KEY);
	}
	
	
	public String execute() {
		xtdlbs = expService.getDictListWithSelectByArray(new String[]{"2","3","5","23","12","21","22","25","28"});
		xtdict2 = xtdlbs.get(2);
		xtdict3 = xtdlbs.get(3);
		xtdict5 = xtdlbs.get(5);
		xtdict12 = xtdlbs.get(12);
		xtdict21 = xtdlbs.get(21);
		xtdict22 = xtdlbs.get(22);
		xtdict23 = xtdlbs.get(23);
		xtdict25 = xtdlbs.get(25);
		xtdict28 = xtdlbs.get(28);
		return Action.INPUT;
	}
	
	public void queryInfo(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		qlist = expBbService.queryInfo(query, xtuser);
		mapOut.put("info", qlist);
		this.renderJson(mapOut);
	}
	
	@SuppressWarnings("unchecked")
	public void doQueryListByType() {
		String type = getRequest().getParameter("type");
		Map<String,String> map = new HashMap<String,String>();
		map.put("type", type);
		List list = expBbService.getListForType(map);
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		gridHandler.setTotalRowNum(list != null ? list.size() : 0);
		gridHandler.setData(list);
		renderText(gridHandler.getLoadResponseText());
	}
	
	@SuppressWarnings("unchecked")
	public void doExportListByType() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		Map param = getParameterSimpleMap();
		
		String ajaxin = String.valueOf(param.get("_gt_json"));
		JSONObject js = JSONObject.fromBean(param.get("_gt_json"));
		Object type = JSONObject.fromBean(js.get("parameters")).get("type");
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("type", String.valueOf(type));
		List list = expBbService.getListForType(map);
		try {
			gridHandler.exportXLSfromMaps(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String preTj(){
		dmmcs = expBbService.getTjjbxx("1");
		return "tjjbxx";
	}
	
	public void doTj(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			mapOut.put("code","1");
			mapOut.put("info", expBbService.doTjV(field,xtuser));
		}catch(Exception e){
			mapOut.put("code","-1");
			mapOut.put("info", "不能选择两个以上相同的统计字段，请重新选择！");
		}
		this.renderJson(mapOut);
	}
	
	public String preDwtj(){
		dmmcs = expBbService.getTjjbxx("1");
		return "dwtj";
	}
	
	public void doDwtj(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		mapOut.put("xf", expBbService.getXYfList(xf, xtuser));
		mapOut.put("yf", expBbService.getXYfList(yf, xtuser));
		mapOut.put("info", expBbService.doDwtj(xf, yf, query,xtuser));
		this.renderJson(mapOut);
	}
	
	
	/***
	 * Mapping
	 */
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
	public Map<Integer, List<XtDict>> getXtdlbs() {
		return xtdlbs;
	}
	public void setXtdlbs(Map<Integer, List<XtDict>> xtdlbs) {
		this.xtdlbs = xtdlbs;
	}
	public List<XtDict> getXtdict2() {
		return xtdict2;
	}
	public void setXtdict2(List<XtDict> xtdict2) {
		this.xtdict2 = xtdict2;
	}
	public List<XtDict> getXtdict3() {
		return xtdict3;
	}
	public void setXtdict3(List<XtDict> xtdict3) {
		this.xtdict3 = xtdict3;
	}
	public List<XtDict> getXtdict5() {
		return xtdict5;
	}
	public void setXtdict5(List<XtDict> xtdict5) {
		this.xtdict5 = xtdict5;
	}
	public List<XtDict> getXtdict23() {
		return xtdict23;
	}
	public void setXtdict23(List<XtDict> xtdict23) {
		this.xtdict23 = xtdict23;
	}
	public List<XtDict> getXtdict12() {
		return xtdict12;
	}
	public void setXtdict12(List<XtDict> xtdict12) {
		this.xtdict12 = xtdict12;
	}
	public List<XtDict> getXtdict21() {
		return xtdict21;
	}
	public void setXtdict21(List<XtDict> xtdict21) {
		this.xtdict21 = xtdict21;
	}
	public List<XtDict> getXtdict22() {
		return xtdict22;
	}
	public void setXtdict22(List<XtDict> xtdict22) {
		this.xtdict22 = xtdict22;
	}
	public List<XtDict> getXtdict25() {
		return xtdict25;
	}
	public void setXtdict25(List<XtDict> xtdict25) {
		this.xtdict25 = xtdict25;
	}
	public List<XtDict> getXtdict28() {
		return xtdict28;
	}
	public void setXtdict28(List<XtDict> xtdict28) {
		this.xtdict28 = xtdict28;
	}
	public List<DmMc> getDmmcs() {
		return dmmcs;
	}
	public void setDmmcs(List<DmMc> dmmcs) {
		this.dmmcs = dmmcs;
	}
	public String[] getField() {
		return field;
	}
	public void setField(String[] field) {
		this.field = field;
	}
	public String getXf() {
		return xf;
	}
	public void setXf(String xf) {
		this.xf = xf;
	}
	public String getYf() {
		return yf;
	}
	public void setYf(String yf) {
		this.yf = yf;
	}
	
	
}
