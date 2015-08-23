package com.group.zsxm.web.action;

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

import com.group.core.Constants;
import com.group.core.common.Message;
import com.group.core.common.TreeBean;
import com.group.core.common.TreeFactory;

import com.group.zsxm.entity.DmMc;
import com.group.zsxm.entity.XtDict;
import com.group.zsxm.entity.Xtuser;
import com.group.zsxm.service.ZsdwService;
import com.group.zsxm.service.ZsxmbyService;
import com.group.zsxm.service.common.SystemService;
import com.group.zsxm.web.common.BaseAction;
import com.opensymphony.xwork2.Action;

@ParentPackage("appDefault")
@Scope("prototype")
@Controller
@Results( { @Result(name = "input", value = "/WEB-INF/jsp/zsdw/zsdw.jsp"),
			@Result(name = "update", value = "/WEB-INF/jsp/zsdw/zsdw_rep.jsp"),
			@Result(name = "rep", value = "/WEB-INF/jsp/zsdw/zsdw_rep.jsp"),
			@Result(name = "dwxx", value = "/WEB-INF/jsp/zsdw/zsdw_dwxx.jsp"),
			@Result(name = "gqbl", value = "/WEB-INF/jsp/zsdw/zsdw_gqbl.jsp"),
			@Result(name = "gqbld", value = "/WEB-INF/jsp/zsdw/zsdw_gqbl_d.jsp"),
			
			@Result(name = "cdxm", value = "/WEB-INF/jsp/zsdw/zsdw_cdxm.jsp"),
			@Result(name = "cdxmd", value = "/WEB-INF/jsp/zsdw/zsdw_cdxm_d.jsp"),

			@Result(name = "cdhxxm", value = "/WEB-INF/jsp/zsdw/zsdw_cdhxxm.jsp"),
			@Result(name = "cdhxxmd", value = "/WEB-INF/jsp/zsdw/zsdw_cdhxxm_d.jsp"),
			
			@Result(name = "zscq", value = "/WEB-INF/jsp/zsdw/zsdw_zscq.jsp"),
			@Result(name = "zscqd", value = "/WEB-INF/jsp/zsdw/zsdw_zscq_d.jsp"),
			
			@Result(name = "hjqk", value = "/WEB-INF/jsp/zsdw/zsdw_hjqk.jsp"),
			@Result(name = "hjqkd", value = "/WEB-INF/jsp/zsdw/zsdw_hjqk_d.jsp"),
			
			@Result(name = "lxr", value = "/WEB-INF/jsp/zsdw/zsdw_lxr.jsp"),
			@Result(name = "lxrd", value = "/WEB-INF/jsp/zsdw/zsdw_lxr_d.jsp"),
			
			@Result(name = "ryxx", value = "/WEB-INF/jsp/zsdw/zsdw_ryxx.jsp"),
			@Result(name = "ryxxd", value = "/WEB-INF/jsp/zsdw/zsdw_ryxx_d.jsp"),
			
			@Result(name = "sysjsqk", value = "/WEB-INF/jsp/zsdw/zsdw_sysjsqk.jsp"),
			@Result(name = "sysjsqkd", value = "/WEB-INF/jsp/zsdw/zsdw_sysjsqk_d.jsp"),
			
			@Result(name = "yfjgqk", value = "/WEB-INF/jsp/zsdw/zsdw_yfjgqk.jsp"),
			@Result(name = "yfjgqkd", value = "/WEB-INF/jsp/zsdw/zsdw_yfjgqk_d.jsp"),
			
			@Result(name = "lssj", value = "/WEB-INF/jsp/zsdw/zsdw_lssj.jsp"),
			@Result(name = "lssjd", value = "/WEB-INF/jsp/zsdw/zsdw_lssj_d.jsp"),
			
			@Result(name = "yqfw", value = "/WEB-INF/jsp/zsdw/zsdw_yqfw.jsp"),
			@Result(name = "yqfwd", value = "/WEB-INF/jsp/zsdw/zsdw_yqfw_d.jsp"),
			
			@Result(name = "yqyhtj", value = "/WEB-INF/jsp/zsdw/zsdw_yqyhtj.jsp"),
			@Result(name = "yqyhtjd", value = "/WEB-INF/jsp/zsdw/zsdw_yqyhtj_d.jsp"),
			
			@Result(name = "selectxm", value = "/WEB-INF/jsp/zsdw/zsdw_xm_sel.jsp"),
			@Result(name = "view", value = "/WEB-INF/jsp/zsdw/zsdw_view.jsp"),
			@Result(name = "treesel", value = "/WEB-INF/jsp/zsdw/zsdw_tree_sel.jsp"),
			@Result(name = "queryfj", value = "/WEB-INF/jsp/zsdw/zsdw_ryxx_fj.jsp")
			})
public class ZsdwAction extends BaseAction{

	@Autowired
	@Qualifier("zsdwService")
	private ZsdwService zsdwService;
	
	@Autowired
	@Qualifier("zsxmbyService")
	private ZsxmbyService zsxmbyService;
	

	@Autowired
	@Qualifier("systemService")
	private SystemService systemService;
	
	private Map<String, String> zsdw;
	
	private Map<Integer, List<XtDict>> xtdlbs;
	private List<XtDict> xtdict4; 
	private List<XtDict> xtdict5; 
	private List<XtDict> xtdict6; 
	private List<XtDict> xtdict7; 
	private List<XtDict> xtdict8; 
	private List<XtDict> xtdict9; 
	private List<XtDict> xtdict10; 
	private List<XtDict> xtdict11; 
	private List<XtDict> xtdict12; 
	private List<XtDict> xtdict13;
	private List<XtDict> xtdict14; 
	private List<XtDict> xtdict15; 
	private List<XtDict> xtdict16; 
	private List<XtDict> xtdict17; 
	private List<XtDict> xtdict18; 
	private List<XtDict> xtdict19;
	private List<XtDict> xtdict20;
	private List<XtDict> xtdict21;
	private List<XtDict> xtdict22;
	private List<XtDict> xtdict25;
	
	private List<XtDict> xtdict30;
	
	private List<XtDict> xtdict31;
	
	private List<Map<String, String>> xtusers;
	
	private List<XtDict> xtdict33;
	private List<XtDict> xtdict34; 
	private List<XtDict> xtdict35; 
	private List<XtDict> xtdict36; 
	private List<XtDict> xtdict37; 
	private List<XtDict> xtdict38; 
	private List<XtDict> xtdict39;
	private List<XtDict> xtdict40;
	private List<XtDict> xtdict41;
	private List<XtDict> xtdict42;
	private List<XtDict> xtdict43;
	private List<XtDict> xtdict44;
	private List<XtDict> xtdict45;
	private List<XtDict> xtdict46;
	
	private List<Map<String, String>> yhyjs;
	private List<XtDict> xtdict48;
	private String[] xtdict48s;
	private List<XtDict> xtdict49;
	
	//private List<XtDict> xtdict24;
	private String cdhjje;
	private String[] dwsx;//单位属性  XT_MUTSEL  SELID:1
	private String[] cyfl;//产业分类  XT_MUTSEL  SELID:2
	private String[] jszy;//技术专业  XT_MUTSEL  SELID:3
	private String[] yjlb;//引进类别  XT_MUTSEL  SELID:4
	private List<Map<String, String>> dwsxs;
	private List<Map<String, String>> cyfls;
	private List<Map<String, String>> jszys;
	private List<Map<String, String>> yjlbs;
	private List<Map<String, String>> dwgzrs;
	
	//附件
	private List<Map<String, String>> fjs11;
	private List<Map<String, String>> fjs12;
	private List<Map<String, String>> fjs13;
	private List<Map<String, String>> fjs21;
	private List<Map<String, String>> fjs22;
	private List<Map<String, String>> fjs23;
	private List<Map<String, String>> fjs31;
	private List<Map<String, String>> fjs32;
	private List<Map<String, String>> fjs33;
	private List<Map<String, String>> fjs34;
	
	private List<Map<String, String>> fj2s11;
	private List<Map<String, String>> fj2s12;
	private List<Map<String, String>> fj2s21;
	private List<Map<String, String>> fj2s22;
	
	private List<Map<String, String>> listnf;
	private String fjpath;
	/***
	 * 相关附件上传资料
	 * @return
	 */
	private String[] tt;
	private String[] xx;
	private String[] yy;
	private String[] fj;
	private String[] fjmc;
	private String[] ttype;
	private String fjname;
	
	private String jszyothermc;
	private List<Map<String, String>> qlist;
	private String dwid;
	private String ryid;
	private String xmid;
	private String xh;
	private String pname;
	private String opttype;
	private String[] dmkey;
	private Xtuser xtuser;
	private Map<String, String> qzzxm;
	private Map<String, String> query;
	private List<DmMc> fields;
	private Map<String,Object> maps;
	
	private String gqbl_zczb;//注册资本
	private String gqbl_tzze;//投资总额
	private String xmje_hj;
	private String dictbh;
	public void prepare() {
		xtuser = (Xtuser)getSession().getAttribute(Constants.USER_SESSION_KEY);
	} 
	public String execute(){
		return Action.INPUT;
	}
	
	public String preZsdwU(){
		return "update";
	}
	
	public String viewZsdw(){
		maps = zsdwService.preView(dwid);
		return "view";
	}
	
	public String preZsdw(){
		xtdlbs = zsdwService.getDictListWithSelectAll();
		xtdict4 = xtdlbs.get(4);
		xtdict5 = xtdlbs.get(5);
		xtdict6 = xtdlbs.get(6);
		xtdict7 = xtdlbs.get(7);
		xtdict8 = xtdlbs.get(8);
		xtdict9 = xtdlbs.get(9);
		xtdict10 = xtdlbs.get(10);
		xtdict11 = xtdlbs.get(11);
		xtdict12 = xtdlbs.get(12);
		xtdict13 = xtdlbs.get(13);
		xtdict14 = xtdlbs.get(14);
		xtdict15 = xtdlbs.get(15);
		
		xtdlbs = zsdwService.getDictListByLbid("30");
		xtdict30 = xtdlbs.get(30);
		
		xtdlbs = zsdwService.getDictListWithSelectAll(33,45);
		xtdict33 = xtdlbs.get(33);
		xtdict34 = xtdlbs.get(34);
		xtdict35 = xtdlbs.get(35);
		xtdict36 = xtdlbs.get(36);
		xtdict37 = xtdlbs.get(37);
		xtdict39 = xtdlbs.get(39);
		xtdict41 = xtdlbs.get(41);
		xtdict42 = xtdlbs.get(42);
		xtdict43 = xtdlbs.get(43);
		xtdict44 = xtdlbs.get(44);
		xtdict45 = xtdlbs.get(45);
		
		
		dwgzrs = zsdwService.getXtuserList();
		//xtdlbs = zsdwService.getDictListByLbid("24");
		//xtdict24 = xtdlbs.get(24);
		
		if(dwid != null && !dwid.equals("")){
			zsdw = zsdwService.getZsdwinfoBydwid(dwid);
			dwsxs = zsdwService.getZsdwMutSelListByDwid(dwid, "1");
			cyfls = zsdwService.getZsdwMutSelListByDwid(dwid, "2");
			jszys = zsdwService.getZsdwMutSelListByDwid(dwid, "3");
			yjlbs = zsdwService.getZsdwMutSelListByDwid(dwid, "4");
			zsdw.put("isljx", String.valueOf(zsdwService.getZsdwIsLjx(dwid)));
			zsdw.put("istzbl", String.valueOf(zsdwService.getZsdwIsTzbl(dwid)));
			String hyfl = "";
			String hymc = "";
			List<Map<String, String>> hyfls = zsdwService.getZsdwMutSelListByDwid(dwid, "5");
			for(int i=0;i<hyfls.size();i++){
				if(hyfl.equals("")){
					hyfl = hyfls.get(i).get("seldm");
				}else{
					hyfl += ","+hyfls.get(i).get("seldm");
				}
				if(hyfls.get(i).get("seldm").length() >= 3){
					hymc += " | "+hyfls.get(i).get("selmc");
				}
			}
			zsdw.put("hyfl", hyfl);
			zsdw.put("hymc", hymc);
			
			qzzxm = zsdwService.getDwxmByDwid(dwid);
			if(zsdw.get("dwlx") != null && !zsdw.get("dwlx").equals("")){
				zsdw.put("dwdm_8", zsdw.get("dwdm").substring(0,8));
				zsdw.put("dwdm_1", zsdw.get("dwdm").substring(9,10));
			}
			
		}else{
			zsdw = new HashMap<String, String>();
			zsdw.put("dabh", zsdwService.getZsdwMaxDabh());
			zsdw.put("sex", "001");
			zsdw.put("dwzt", "001");
			zsdw.put("zczb", "0.00");
			zsdw.put("jzmj", "0.00");
			zsdw.put("snxssr", "0.00");
			zsdw.put("snjnss", "0.00");
			zsdw.put("sndygs", "0");
			zsdw.put("nwz", "001");
			qzzxm = new HashMap();
			qzzxm.put("xmmc", "");
		}
		return "dwxx";
	}
	
	public String getZsxmWaitSelect(){
		fields = ArrayToList(
				new String[] { "xmmc", "xmlb_mc", "xmgs", "xmjdgs_mc" },
				new String[] { "项目名称", "项目类别", "项目概述", "项目进度概述" });
		qlist = zsdwService.getZsxmWaitSelect(query);
		return "selectxm";
	}
	
	public void doSaveZsdw(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			dwid = zsdwService.doSaveZsdw(dwid, zsdw,dwsx,cyfl,jszy,yjlb,jszyothermc,qzzxm);
			message = new Message("1",dwid);
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	/**
	 * 股权比例
	 * @return
	 */
	public String preGqbl(){
		gqbl_tzze = zsdwService.getGqblTzze(dwid);
		gqbl_zczb = zsdwService.getGqblZczb(dwid);
		qlist = zsdwService.getGqblList(dwid,gqbl_tzze);
		return "gqbl";
	}
	
	public String preGqblI(){
		zsdw = new HashMap();
		zsdw.put("tzje", "0.00");
		xtdlbs = zsdwService.getDictListByLbid("46");
		xtdict46 = xtdlbs.get(46);
		return "gqbld";
	}
	
	public void doGqblI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doGqblI(dwid, zsdw);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preGqblU(){
		zsdw = zsdwService.preGqblU(dwid, xh);
		xtdlbs = zsdwService.getDictListByLbid("46");
		xtdict46 = xtdlbs.get(46);
		return "gqbld";
	}
	
	public void doGqblU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doGqblU(dwid, xh, zsdw);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doGqblD(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doGqblD(dwid, dmkey);
			message = new Message("1","删除成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	/**
	 * 承担项目
	 */
	public String preCdxm(){
		qlist = zsdwService.getCdxmList(dwid);
		xmje_hj = zsdwService.getXmjehj(dwid);
		return "cdxm";
	}
	
	public String preCdxmI(){
		zsdw = new HashMap();
		zsdw.put("xmje", "0.00000");
		xtdlbs = zsdwService.getDictListByLbid("16");
		xtdict16 = xtdlbs.get(16);
		xtdlbs = zsdwService.getDictListByLbid("19");
		xtdict19 = xtdlbs.get(19);
		xtdict20 = new ArrayList<XtDict>();
		return "cdxmd";
	}
	
	public void doCdxmI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doCdxmI(dwid, zsdw);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preCdxmU(){
		zsdw = zsdwService.preCdxmU(dwid, xh);
		if(zsdw.get("lxrq") != null && !zsdw.get("lxrq").equals("") && !zsdw.get("lxrq").equals("null")){
			zsdw.put("lxrq", zsdw.get("lxrq").substring(0,4));
		}
		xtdlbs = zsdwService.getDictListByLbid("16");
		xtdict16 = xtdlbs.get(16);
		xtdlbs = zsdwService.getDictListByLbid("19");
		xtdict19 = xtdlbs.get(19);
		xtdict20 = zsdwService.getDictListByDlb("20", zsdw.get("xmjb"), "0");
		return "cdxmd";
	}
	
	public void doCdxmU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doCdxmU(dwid, xh, zsdw);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doCdxmD(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doCdxmD(dwid, dmkey);
			message = new Message("1","删除成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	/**
	 * 承担横向项目
	 */
	
	public String preCdhxxm(){
		qlist = zsdwService.getCdhxxmList(dwid);
		xmje_hj = zsdwService.getHxxmjehj(dwid);
		return "cdhxxm";
	}
	
	public String preCdhxxmI(){
		zsdw = new HashMap();
		zsdw.put("htje", "0.00000");
		zsdw.put("yjxx", "0.00000");
		xtdlbs = zsdwService.getDictListByLbid("21");
		xtdict21 = xtdlbs.get(21);
		xtdlbs = zsdwService.getDictListByLbid("22");
		xtdict22 = xtdlbs.get(22);
		xtdlbs = zsdwService.getDictListByLbid("19");
		xtdict19 = xtdlbs.get(19);
		return "cdhxxmd";
	}
	
	public void doCdhxxmI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doCdhxxmI(dwid, zsdw);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preCdhxxmU(){
		zsdw = zsdwService.preCdhxxmU(dwid, xh);
		xtdlbs = zsdwService.getDictListByLbid("21");
		xtdict21 = xtdlbs.get(21);
		xtdlbs = zsdwService.getDictListByLbid("22");
		xtdict22 = xtdlbs.get(22);
		xtdlbs = zsdwService.getDictListByLbid("19");
		xtdict19 = xtdlbs.get(19);
		return "cdhxxmd";
	}
	
	public void doCdhxxmU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doCdhxxmU(dwid, xh, zsdw);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doCdhxxmD(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doCdhxxmD(dwid, dmkey);
			message = new Message("1","删除成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	
	/**
	 * 实验室、研发中心建设情况表
	 */
	
	public String preSysjsqk(){
		qlist = zsdwService.getSysjsqkList(dwid);
		return "sysjsqk";
	}
	
	public String preSysjsqkI(){
		zsdw = new HashMap();
		
		xtdlbs = zsdwService.getDictListByLbid("19");
		xtdict19 = xtdlbs.get(19);
		return "sysjsqkd";
	}
	
	public void doSysjsqkI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doSysjsqkI(dwid, zsdw);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preSysjsqkU(){
		zsdw = zsdwService.preSysjsqkU(dwid, xh);
		xtdlbs = zsdwService.getDictListByLbid("19");
		xtdict19 = xtdlbs.get(19);
		return "sysjsqkd";
	}
	
	public void doSysjsqkU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doSysjsqkU(dwid, xh, zsdw);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doSysjsqkD(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doSysjsqkD(dwid, dmkey);
			message = new Message("1","删除成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	/**
	 * 研发机构孵化企业情况表
	 */
	
	public String preYfjgqk(){
		qlist = zsdwService.getYfjgqkList(dwid);
		return "yfjgqk";
	}
	
	public String preYfjgqkI(){
		zsdw = new HashMap();
	
		return "yfjgqkd";
	}
	
	public void doYfjgqkI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doYfjgqkI(dwid, zsdw);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preYfjgqkU(){
		zsdw = zsdwService.preYfjgqkU(dwid, xh);
		xtdlbs = zsdwService.getDictListByLbid("19");
		xtdict19 = xtdlbs.get(19);
		return "yfjgqkd";
	}
	
	public void doYfjgqkU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doYfjgqkU(dwid, xh, zsdw);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doYfjgqkD(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doYfjgqkD(dwid, dmkey);
			message = new Message("1","删除成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void getDictListByDlb(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		xtdict20 = zsdwService.getDictListByDlb("20", dictbh, "0");
		mapOut.put("info", xtdict20);
		this.renderJson(mapOut);
	}
	
	/**
	 * 单位历史数据
	 */
	
	public String preLssj(){
		qlist = zsdwService.getLssjList(dwid);
		return "lssj";
	}
	
	public String preLssjI(){
		zsdw = new HashMap();
		xtdlbs = zsdwService.getDictListByLbid("31");
		xtdict31 = xtdlbs.get(31);
		return "lssjd";
	}
	
	public void doLssjI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doLssjI(dwid, zsdw);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preLssjU(){
		zsdw = zsdwService.preLssjU(dwid, xh);
		xtdlbs = zsdwService.getDictListByLbid("31");
		xtdict31 = xtdlbs.get(31);
		return "lssjd";
	}
	
	public void doLssjU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doLssjU(dwid, xh, zsdw);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doLssjD(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doLssjD(dwid, dmkey);
			message = new Message("1","删除成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	

	/**
	 * 知识产权
	 */
	public String preZscq(){
		qlist = zsdwService.getZscqList(dwid);
		return "zscq";
	}
	
	public String preZscqI(){
		zsdw = new HashMap();
		xtdlbs = zsdwService.getDictListByLbid("18");
		xtdict18 = xtdlbs.get(18);
		
		listnf = new ArrayList();
		for(int i=1960;i<2020;i++){
			Map<String,String> m = new HashMap();
			m.put("dm", String.valueOf(i));
			m.put("mc", String.valueOf(i));
			listnf.add(m);
		}
		return "zscqd";
	}
	
	public void doZscqI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doZscqI(dwid, zsdw);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preZscqU(){
		zsdw = zsdwService.preZscqU(dwid, xh);
		xtdlbs = zsdwService.getDictListByLbid("18");
		xtdict18 = xtdlbs.get(18);
		
		listnf = new ArrayList();
		for(int i=1960;i<2020;i++){
			Map m = new HashMap();
			m.put("dm", i);
			m.put("mc", String.valueOf(i));
			listnf.add(m);
		}
		return "zscqd";
	}
	
	public void doZscqU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doZscqU(dwid, xh, zsdw);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doZscqD(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doZscqD(dwid, dmkey);
			message = new Message("1","删除成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	/**
	 * 奖励情况
	 */
	public String preHjqk(){
		qlist = zsdwService.getHjqkList(dwid);
		return "hjqk";
	}
	
	public String preHjqkI(){
		zsdw = new HashMap();
		xtdlbs = zsdwService.getDictListByLbid("16");
		xtdict16 = xtdlbs.get(16);
		xtdlbs = zsdwService.getDictListByLbid("17");
		xtdict17 = xtdlbs.get(17);
		zsdw.put("zzje", "0.00");
		return "hjqkd";
	}
	
	public void doHjqkI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doHjqkI(dwid, zsdw);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preHjqkU(){
		zsdw = zsdwService.preHjqkU(dwid, xh);
		xtdlbs = zsdwService.getDictListByLbid("16");
		xtdict16 = xtdlbs.get(16);
		xtdlbs = zsdwService.getDictListByLbid("17");
		xtdict17 = xtdlbs.get(17);
		return "hjqkd";
	}
	
	public void doHjqkU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doHjqkU(dwid, xh, zsdw);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doHjqkD(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doHjqkD(dwid, dmkey);
			message = new Message("1","删除成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	/**
	 * 联系人
	 */
	public String preLxr(){
		qlist = zsdwService.getLxrList(dwid);
		return "lxr";
	}
	
	public String preLxrI(){
		zsdw = new HashMap();
		xtdlbs = zsdwService.getDictListByLbid("14");
		xtdict14 = xtdlbs.get(14);
		xtdlbs = zsdwService.getDictListByLbid("15");
		xtdict15 = xtdlbs.get(15);
		zsdw.put("zc", "999");
		return "lxrd";
	}
	
	public void doLxrI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doLxrI(dwid, zsdw);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preLxrU(){
		zsdw = zsdwService.preLxrU(dwid, xh);
		xtdlbs = zsdwService.getDictListByLbid("14");
		xtdict14 = xtdlbs.get(14);
		xtdlbs = zsdwService.getDictListByLbid("15");
		xtdict15 = xtdlbs.get(15);
		return "lxrd";
	}
	
	public void doLxrU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doLxrU(dwid, xh, zsdw);
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
			zsdwService.doLxrD(dwid, dmkey);
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
			zsdwService.doSetLxrForDy(dwid, xh);
			message = new Message("1","设置成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	/**
	 * 机构人员情况
	 */
	public String preRyxx(){
		qlist = zsdwService.getRyxxList(dwid);
		return "ryxx";
	}
	
	public String preRyxxI(){
		zsdw = new HashMap();
		xtdlbs = zsdwService.getDictListByLbid("14");
		xtdict14 = xtdlbs.get(14);
		xtdlbs = zsdwService.getDictListByLbid("15");
		xtdict15 = xtdlbs.get(15);
		xtdlbs = zsdwService.getDictListByLbid("25");
		xtdict25 = xtdlbs.get(25);
		return "ryxxd";
	}
	
	public void doRyxxI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doRyxxI(dwid, zsdw,tt,xx,yy,fj,fjmc,ttype,fjpath+"/zsxm");
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preRyxxU(){
		zsdw = zsdwService.preRyxxU(dwid, ryid);
		xtdlbs = zsdwService.getDictListByLbid("14");
		xtdict14 = xtdlbs.get(14);
		xtdlbs = zsdwService.getDictListByLbid("15");
		xtdict15 = xtdlbs.get(15);
		xtdlbs = zsdwService.getDictListByLbid("25");
		xtdict25 = xtdlbs.get(25);
		
		fjs11 = zsdwService.getRyxxFjList(dwid, ryid, "1", "1","1");
		fjs12 = zsdwService.getRyxxFjList(dwid, ryid, "1", "2","1");
		fjs13 = zsdwService.getRyxxFjList(dwid, ryid, "1", "3","1");
		fjs21 = zsdwService.getRyxxFjList(dwid, ryid, "2", "1","1");
		fjs22 = zsdwService.getRyxxFjList(dwid, ryid, "2", "2","1");
		fjs23 = zsdwService.getRyxxFjList(dwid, ryid, "2", "3","1");
		fjs31 = zsdwService.getRyxxFjList(dwid, ryid, "3", "1","1");
		fjs32 = zsdwService.getRyxxFjList(dwid, ryid, "3", "2","1");
		fjs33 = zsdwService.getRyxxFjList(dwid, ryid, "3", "3","1");
		fjs34 = zsdwService.getRyxxFjList(dwid, ryid, "3", "4","1");
		
		fj2s11 = zsdwService.getRyxxFjList(dwid, ryid, "1", "1","2");
		fj2s12 = zsdwService.getRyxxFjList(dwid, ryid, "1", "2","2");
		fj2s21 = zsdwService.getRyxxFjList(dwid, ryid, "2", "1","2");
		fj2s22 = zsdwService.getRyxxFjList(dwid, ryid, "2", "2","2");
		
		return "ryxxd";
	}
	
	public void doRyxxU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doRyxxU(dwid, ryid, zsdw,tt,xx,yy,fj,fjmc,ttype,fjpath+"/zsxm");
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doRyxxD(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doRyxxD(dwid, dmkey);
			message = new Message("1","删除成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String queryFj(){
		fjs11 = zsdwService.getRyxxFjList(dwid, ryid, "1", "1","1");
		fjs12 = zsdwService.getRyxxFjList(dwid, ryid, "1", "2","1");
		fjs13 = zsdwService.getRyxxFjList(dwid, ryid, "1", "3","1");
		fjs21 = zsdwService.getRyxxFjList(dwid, ryid, "2", "1","1");
		fjs22 = zsdwService.getRyxxFjList(dwid, ryid, "2", "2","1");
		fjs23 = zsdwService.getRyxxFjList(dwid, ryid, "2", "3","1");
		fjs31 = zsdwService.getRyxxFjList(dwid, ryid, "3", "1","1");
		fjs32 = zsdwService.getRyxxFjList(dwid, ryid, "3", "2","1");
		fjs33 = zsdwService.getRyxxFjList(dwid, ryid, "3", "3","1");
		fjs34 = zsdwService.getRyxxFjList(dwid, ryid, "3", "4","1");
		
		fj2s11 = zsdwService.getRyxxFjList(dwid, ryid, "1", "1","2");
		fj2s12 = zsdwService.getRyxxFjList(dwid, ryid, "1", "2","2");
		fj2s21 = zsdwService.getRyxxFjList(dwid, ryid, "2", "1","2");
		fj2s22 = zsdwService.getRyxxFjList(dwid, ryid, "2", "2","2");
		return "queryfj";
	}
	
	
	/**
	 * 园区服务记录
	 */
	public String preYqfw(){
		qlist = zsdwService.getYqfwList(dwid);
		return "yqfw";
	}
	
	public String preYqfwI(){
		xtusers = systemService.getXtuserList();
		return "yqfwd";
	}
	
	public void doYqfwI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doYqfwI(dwid, zsdw);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preYqfwU(){
		zsdw = zsdwService.preYqfwU(dwid, xh);
		xtusers = systemService.getXtuserList();
		return "yqfwd";
	}
	
	public void doYqfwU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doYqfwU(dwid, xh, zsdw);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doYqfwD(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doYqfwD(dwid, dmkey);
			message = new Message("1","删除成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	/***
	 * 园区优惠条件
	 */
	public String preYqyhtj(){
		qlist = zsdwService.getYqyhtjList(dwid);
		cdhjje = zsdwService.getYqyhHjje(dwid);
		return "yqyhtj";
	}
	
	public String preYqyhtjI(){
		xtusers = systemService.getXtuserList();
		xtdlbs = zsdwService.getDictListByLbid("48");
		xtdict48 = xtdlbs.get(48);
		xtdlbs = zsdwService.getDictListByLbid("49");
		xtdict49 = xtdlbs.get(49);
		yhyjs = zsdwService.getZsdwMutSelListByDwid(dwid, "10");
		return "yqyhtjd";
	}
	
	public void doYqyhtjI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doYqyhtjI(dwid, zsdw,xtdict48s);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preYqyhtjU(){
		zsdw = zsdwService.preYqyhtjU(dwid, xh);
		xtusers = systemService.getXtuserList();
		xtdlbs = zsdwService.getDictListByLbid("48");
		xtdict48 = xtdlbs.get(48);
		xtdlbs = zsdwService.getDictListByLbid("49");
		xtdict49 = xtdlbs.get(49);
		yhyjs = zsdwService.getZsdwMutSelListByDwid(dwid, "10");
		return "yqyhtjd";
	}
	
	public void doYqyhtjU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doYqyhtjU(dwid, xh, zsdw,xtdict48s);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doYqyhtjD(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			zsdwService.doYqyhtjD(dwid, dmkey);
			message = new Message("1","删除成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	/**
	 * 获取自动生成的编号
	 */
	public void getAutoDwdm(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		String autodwdm = "";
		try{
			autodwdm = zsdwService.getAutoDwdm();
		}catch(Exception e){
			e.printStackTrace();
		}
		mapOut.put("autodwdm", autodwdm);
		this.renderJson(mapOut);
	}
	
	
	public void doLoadHyflTree(){
		List<TreeBean> treeBeans = zsdwService.doLoadHyflTree(dwid);
		TreeFactory tf = new TreeFactory();	
		tf.setPdm("0");
		tf.setIsDisabledCheck(false);
		tf.setRootText("选择数据");
		renderText(tf.create(treeBeans));
	}
	
	
	private String lbid;
	private String calldm;
	private String callmc;

	public String getCalldm() {
		return calldm;
	}
	public void setCalldm(String calldm) {
		this.calldm = calldm;
	}
	public String getCallmc() {
		return callmc;
	}
	public void setCallmc(String callmc) {
		this.callmc = callmc;
	}
	public String getLbid() {
		return lbid;
	}
	public void setLbid(String lbid) {
		this.lbid = lbid;
	}
	public String preloadtree(){
		return "treesel";
	}
	public void doLoadTree(){
		List<TreeBean> treeBeans = zsdwService.doLoadTreeByLbid(lbid);
		TreeFactory tf = new TreeFactory();	
		tf.setPdm("0");
		tf.setIsDisabledCheck(false);
		tf.setRootText("选择数据");
		renderText(tf.create(treeBeans));
	}
	
	
	/**
	 * Mapping
	 * @return
	 */
	public Map<String, String> getZsdw() {
		return zsdw;
	}
	public void setZsdw(Map<String, String> zsdw) {
		this.zsdw = zsdw;
	}
	public Map<Integer, List<XtDict>> getXtdlbs() {
		return xtdlbs;
	}
	public void setXtdlbs(Map<Integer, List<XtDict>> xtdlbs) {
		this.xtdlbs = xtdlbs;
	}
	public List<XtDict> getXtdict4() {
		return xtdict4;
	}
	public void setXtdict4(List<XtDict> xtdict4) {
		this.xtdict4 = xtdict4;
	}
	public List<XtDict> getXtdict5() {
		return xtdict5;
	}
	public void setXtdict5(List<XtDict> xtdict5) {
		this.xtdict5 = xtdict5;
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
	public List<XtDict> getXtdict9() {
		return xtdict9;
	}
	public void setXtdict9(List<XtDict> xtdict9) {
		this.xtdict9 = xtdict9;
	}
	public List<XtDict> getXtdict10() {
		return xtdict10;
	}
	public void setXtdict10(List<XtDict> xtdict10) {
		this.xtdict10 = xtdict10;
	}
	public List<XtDict> getXtdict11() {
		return xtdict11;
	}
	public void setXtdict11(List<XtDict> xtdict11) {
		this.xtdict11 = xtdict11;
	}
	public List<XtDict> getXtdict12() {
		return xtdict12;
	}
	public void setXtdict12(List<XtDict> xtdict12) {
		this.xtdict12 = xtdict12;
	}
	public List<XtDict> getXtdict13() {
		return xtdict13;
	}
	public void setXtdict13(List<XtDict> xtdict13) {
		this.xtdict13 = xtdict13;
	}
	public List<XtDict> getXtdict14() {
		return xtdict14;
	}
	public void setXtdict14(List<XtDict> xtdict14) {
		this.xtdict14 = xtdict14;
	}
	public List<XtDict> getXtdict15() {
		return xtdict15;
	}
	public void setXtdict15(List<XtDict> xtdict15) {
		this.xtdict15 = xtdict15;
	}

	public String getDwid() {
		return dwid;
	}
	public void setDwid(String dwid) {
		this.dwid = dwid;
	}
	public String[] getDwsx() {
		return dwsx;
	}
	public void setDwsx(String[] dwsx) {
		this.dwsx = dwsx;
	}
	public String[] getJszy() {
		return jszy;
	}
	public void setJszy(String[] jszy) {
		this.jszy = jszy;
	}
	public String[] getYjlb() {
		return yjlb;
	}
	public void setYjlb(String[] yjlb) {
		this.yjlb = yjlb;
	}
	public Xtuser getXtuser() {
		return xtuser;
	}
	public void setXtuser(Xtuser xtuser) {
		this.xtuser = xtuser;
	}
	public String getJszyothermc() {
		return jszyothermc;
	}
	public void setJszyothermc(String jszyothermc) {
		this.jszyothermc = jszyothermc;
	}
	public String[] getCyfl() {
		return cyfl;
	}
	public void setCyfl(String[] cyfl) {
		this.cyfl = cyfl;
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
	public List<Map<String, String>> getDwsxs() {
		return dwsxs;
	}
	public void setDwsxs(List<Map<String, String>> dwsxs) {
		this.dwsxs = dwsxs;
	}
	public List<Map<String, String>> getCyfls() {
		return cyfls;
	}
	public void setCyfls(List<Map<String, String>> cyfls) {
		this.cyfls = cyfls;
	}
	public List<Map<String, String>> getJszys() {
		return jszys;
	}
	public void setJszys(List<Map<String, String>> jszys) {
		this.jszys = jszys;
	}
	public List<Map<String, String>> getYjlbs() {
		return yjlbs;
	}
	public void setYjlbs(List<Map<String, String>> yjlbs) {
		this.yjlbs = yjlbs;
	}
	public String getXmid() {
		return xmid;
	}
	public void setXmid(String xmid) {
		this.xmid = xmid;
	}
	public Map<String, String> getQzzxm() {
		return qzzxm;
	}
	public void setQzzxm(Map<String, String> qzzxm) {
		this.qzzxm = qzzxm;
	}
	public Map<String, String> getQuery() {
		return query;
	}
	public void setQuery(Map<String, String> query) {
		this.query = query;
	}
	public List<DmMc> getFields() {
		return fields;
	}
	public void setFields(List<DmMc> fields) {
		this.fields = fields;
	}
	public List<XtDict> getXtdict16() {
		return xtdict16;
	}
	public void setXtdict16(List<XtDict> xtdict16) {
		this.xtdict16 = xtdict16;
	}
	public List<XtDict> getXtdict17() {
		return xtdict17;
	}
	public void setXtdict17(List<XtDict> xtdict17) {
		this.xtdict17 = xtdict17;
	}
	public Map<String, Object> getMaps() {
		return maps;
	}
	public void setMaps(Map<String, Object> maps) {
		this.maps = maps;
	}
	public String getGqbl_zczb() {
		return gqbl_zczb;
	}
	public void setGqbl_zczb(String gqbl_zczb) {
		this.gqbl_zczb = gqbl_zczb;
	}
	public String getGqbl_tzze() {
		return gqbl_tzze;
	}
	public void setGqbl_tzze(String gqbl_tzze) {
		this.gqbl_tzze = gqbl_tzze;
	}
	public List<XtDict> getXtdict18() {
		return xtdict18;
	}
	public void setXtdict18(List<XtDict> xtdict18) {
		this.xtdict18 = xtdict18;
	}
	public List<XtDict> getXtdict19() {
		return xtdict19;
	}
	public void setXtdict19(List<XtDict> xtdict19) {
		this.xtdict19 = xtdict19;
	}
	public List<XtDict> getXtdict20() {
		return xtdict20;
	}
	public void setXtdict20(List<XtDict> xtdict20) {
		this.xtdict20 = xtdict20;
	}
	public String getDictbh() {
		return dictbh;
	}
	public void setDictbh(String dictbh) {
		this.dictbh = dictbh;
	}
	public String getXmje_hj() {
		return xmje_hj;
	}
	public void setXmje_hj(String xmje_hj) {
		this.xmje_hj = xmje_hj;
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
	public String getCdhjje() {
		return cdhjje;
	}
	public void setCdhjje(String cdhjje) {
		this.cdhjje = cdhjje;
	}
	public List<XtDict> getXtdict25() {
		return xtdict25;
	}
	public void setXtdict25(List<XtDict> xtdict25) {
		this.xtdict25 = xtdict25;
	}
	public String getRyid() {
		return ryid;
	}
	public void setRyid(String ryid) {
		this.ryid = ryid;
	}
	public ZsdwService getZsdwService() {
		return zsdwService;
	}
	public void setZsdwService(ZsdwService zsdwService) {
		this.zsdwService = zsdwService;
	}
	public ZsxmbyService getZsxmbyService() {
		return zsxmbyService;
	}
	public void setZsxmbyService(ZsxmbyService zsxmbyService) {
		this.zsxmbyService = zsxmbyService;
	}
	public List<Map<String, String>> getFjs11() {
		return fjs11;
	}
	public void setFjs11(List<Map<String, String>> fjs11) {
		this.fjs11 = fjs11;
	}
	public List<Map<String, String>> getFjs12() {
		return fjs12;
	}
	public void setFjs12(List<Map<String, String>> fjs12) {
		this.fjs12 = fjs12;
	}
	public List<Map<String, String>> getFjs13() {
		return fjs13;
	}
	public void setFjs13(List<Map<String, String>> fjs13) {
		this.fjs13 = fjs13;
	}
	public List<Map<String, String>> getFjs21() {
		return fjs21;
	}
	public void setFjs21(List<Map<String, String>> fjs21) {
		this.fjs21 = fjs21;
	}
	public List<Map<String, String>> getFjs22() {
		return fjs22;
	}
	public void setFjs22(List<Map<String, String>> fjs22) {
		this.fjs22 = fjs22;
	}
	public List<Map<String, String>> getFjs23() {
		return fjs23;
	}
	public void setFjs23(List<Map<String, String>> fjs23) {
		this.fjs23 = fjs23;
	}
	public List<Map<String, String>> getFjs31() {
		return fjs31;
	}
	public void setFjs31(List<Map<String, String>> fjs31) {
		this.fjs31 = fjs31;
	}
	public List<Map<String, String>> getFjs32() {
		return fjs32;
	}
	public void setFjs32(List<Map<String, String>> fjs32) {
		this.fjs32 = fjs32;
	}
	public List<Map<String, String>> getFjs33() {
		return fjs33;
	}
	public void setFjs33(List<Map<String, String>> fjs33) {
		this.fjs33 = fjs33;
	}
	public List<Map<String, String>> getFjs34() {
		return fjs34;
	}
	public void setFjs34(List<Map<String, String>> fjs34) {
		this.fjs34 = fjs34;
	}
	public List<Map<String, String>> getFj2s11() {
		return fj2s11;
	}
	public void setFj2s11(List<Map<String, String>> fj2s11) {
		this.fj2s11 = fj2s11;
	}
	public List<Map<String, String>> getFj2s12() {
		return fj2s12;
	}
	public void setFj2s12(List<Map<String, String>> fj2s12) {
		this.fj2s12 = fj2s12;
	}
	public List<Map<String, String>> getFj2s21() {
		return fj2s21;
	}
	public void setFj2s21(List<Map<String, String>> fj2s21) {
		this.fj2s21 = fj2s21;
	}
	public List<Map<String, String>> getFj2s22() {
		return fj2s22;
	}
	public void setFj2s22(List<Map<String, String>> fj2s22) {
		this.fj2s22 = fj2s22;
	}
	public String[] getTt() {
		return tt;
	}
	public void setTt(String[] tt) {
		this.tt = tt;
	}
	public String[] getXx() {
		return xx;
	}
	public void setXx(String[] xx) {
		this.xx = xx;
	}
	public String[] getYy() {
		return yy;
	}
	public void setYy(String[] yy) {
		this.yy = yy;
	}
	public String[] getFj() {
		return fj;
	}
	public void setFj(String[] fj) {
		this.fj = fj;
	}
	public String[] getFjmc() {
		return fjmc;
	}
	public void setFjmc(String[] fjmc) {
		this.fjmc = fjmc;
	}
	public String[] getTtype() {
		return ttype;
	}
	public void setTtype(String[] ttype) {
		this.ttype = ttype;
	}
	public String getFjname() {
		return fjname;
	}
	public void setFjname(String fjname) {
		this.fjname = fjname;
	}
	public String getFjpath() {
		return fjpath;
	}
	public void setFjpath(String fjpath) {
		this.fjpath = fjpath;
	}
	public List<XtDict> getXtdict30() {
		return xtdict30;
	}
	public void setXtdict30(List<XtDict> xtdict30) {
		this.xtdict30 = xtdict30;
	}
	public List<Map<String, String>> getDwgzrs() {
		return dwgzrs;
	}
	public void setDwgzrs(List<Map<String, String>> dwgzrs) {
		this.dwgzrs = dwgzrs;
	}
	public List<Map<String, String>> getListnf() {
		return listnf;
	}
	public void setListnf(List<Map<String, String>> listnf) {
		this.listnf = listnf;
	}
	public List<XtDict> getXtdict31() {
		return xtdict31;
	}
	public void setXtdict31(List<XtDict> xtdict31) {
		this.xtdict31 = xtdict31;
	}
	public List<XtDict> getXtdict33() {
		return xtdict33;
	}
	public void setXtdict33(List<XtDict> xtdict33) {
		this.xtdict33 = xtdict33;
	}
	public List<XtDict> getXtdict34() {
		return xtdict34;
	}
	public void setXtdict34(List<XtDict> xtdict34) {
		this.xtdict34 = xtdict34;
	}
	public List<XtDict> getXtdict35() {
		return xtdict35;
	}
	public void setXtdict35(List<XtDict> xtdict35) {
		this.xtdict35 = xtdict35;
	}
	public List<XtDict> getXtdict36() {
		return xtdict36;
	}
	public void setXtdict36(List<XtDict> xtdict36) {
		this.xtdict36 = xtdict36;
	}
	public List<XtDict> getXtdict37() {
		return xtdict37;
	}
	public void setXtdict37(List<XtDict> xtdict37) {
		this.xtdict37 = xtdict37;
	}
	public List<XtDict> getXtdict38() {
		return xtdict38;
	}
	public void setXtdict38(List<XtDict> xtdict38) {
		this.xtdict38 = xtdict38;
	}
	public List<XtDict> getXtdict39() {
		return xtdict39;
	}
	public void setXtdict39(List<XtDict> xtdict39) {
		this.xtdict39 = xtdict39;
	}
	public List<XtDict> getXtdict40() {
		return xtdict40;
	}
	public void setXtdict40(List<XtDict> xtdict40) {
		this.xtdict40 = xtdict40;
	}
	public List<XtDict> getXtdict41() {
		return xtdict41;
	}
	public void setXtdict41(List<XtDict> xtdict41) {
		this.xtdict41 = xtdict41;
	}
	public List<XtDict> getXtdict42() {
		return xtdict42;
	}
	public void setXtdict42(List<XtDict> xtdict42) {
		this.xtdict42 = xtdict42;
	}
	public List<XtDict> getXtdict43() {
		return xtdict43;
	}
	public void setXtdict43(List<XtDict> xtdict43) {
		this.xtdict43 = xtdict43;
	}
	public List<XtDict> getXtdict44() {
		return xtdict44;
	}
	public void setXtdict44(List<XtDict> xtdict44) {
		this.xtdict44 = xtdict44;
	}
	public List<XtDict> getXtdict45() {
		return xtdict45;
	}
	public void setXtdict45(List<XtDict> xtdict45) {
		this.xtdict45 = xtdict45;
	}
	public List<XtDict> getXtdict46() {
		return xtdict46;
	}
	public void setXtdict46(List<XtDict> xtdict46) {
		this.xtdict46 = xtdict46;
	}
	public List<Map<String, String>> getXtusers() {
		return xtusers;
	}
	public void setXtusers(List<Map<String, String>> xtusers) {
		this.xtusers = xtusers;
	}
	public List<XtDict> getXtdict48() {
		return xtdict48;
	}
	public void setXtdict48(List<XtDict> xtdict48) {
		this.xtdict48 = xtdict48;
	}
	public List<XtDict> getXtdict49() {
		return xtdict49;
	}
	public void setXtdict49(List<XtDict> xtdict49) {
		this.xtdict49 = xtdict49;
	}
	public String[] getXtdict48s() {
		return xtdict48s;
	}
	public void setXtdict48s(String[] xtdict48s) {
		this.xtdict48s = xtdict48s;
	}
	
}
