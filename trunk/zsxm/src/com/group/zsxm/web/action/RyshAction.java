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
import com.group.zsxm.entity.XtDict;
import com.group.zsxm.entity.Xtuser;

import com.group.zsxm.service.RyshService;
import com.group.zsxm.web.common.BaseAction;
import com.opensymphony.xwork2.Action;
@ParentPackage("appDefault")
@Scope("prototype")
@Controller
@Results( { @Result(name = "input", value = "/WEB-INF/jsp/rysh/rysh.jsp"),
			@Result(name = "ryxz", value = "/WEB-INF/jsp/rysh/rysh_xz.jsp"),
			@Result(name = "ryxg", value = "/WEB-INF/jsp/rysh/rysh_xg.jsp"),
			@Result(name = "rysh", value = "/WEB-INF/jsp/rysh/rysh_sh.jsp"),
			@Result(name = "ryfh", value = "/WEB-INF/jsp/rysh/rysh_fh.jsp"),
			@Result(name = "ryth", value = "/WEB-INF/jsp/rysh/rysh_th.jsp"),
			@Result(name = "rydj", value = "/WEB-INF/jsp/rysh/rysh_dj.jsp"),
			@Result(name = "view", value = "/WEB-INF/jsp/rysh/rysh_view.jsp")
			})
public class RyshAction  extends BaseAction{
	@Autowired
	@Qualifier("ryshService")
	private RyshService ryshService;
	private Map<String,Object> maps;
	private String thyy;
	private String dwid;
	private String ryid;
	
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
	private List<XtDict> xtdict27;
	private List<XtDict> xtdict28;
	private List<XtDict> xtdict29;
	private List<XtDict> xtdict28list;
	
	private String[] xtdict28s;
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
	private List<Map<String, String>> fjs11;
	private List<Map<String, String>> fjs12;
	private List<Map<String, String>> fjs13;
	private List<Map<String, String>> fjs15;
	
	private String[] jfhsbtype;
	private List<Map<String, String>> jfhsbtypes;
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
	
	private List<Map<String, String>> fj3s11;
	private List<Map<String, String>> fj3s12;
	
	private String fjpath;
	private Integer isjfh;
	private String status;
	
	private String[] tt;
	private String[] xx;
	private String[] yy;
	private String[] fj;
	private String[] fjmc;
	private String[] ttype;
	
	
	private Xtuser xtuser;
	public void onPrepare() {
		xtuser = (Xtuser)getSession().getAttribute(Constants.USER_SESSION_KEY);
	} 
	public String execute(){
		return Action.INPUT;
	}
	
	public void doGetList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		getLimitPage().setRowAttributes(gridHandler.getTotalRowNum(), gridHandler.getPageNum(), gridHandler.getPageSize());
		Map param = getParameterSimpleMap();
		Page page = (Page) ryshService.getRyxxList(getLimitPage(), param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo(),dwid);
		gridHandler.setTotalRowNum(page.getTotalCount());
		gridHandler.setData((List) page.getData());
		renderText(gridHandler.getLoadResponseText());
	}
	
	public void doExportList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		Map param = gridHandler.getGridRequestParameters();
		List list = (List) ryshService.getRyxxList(null, param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo(),dwid);
		try {
			gridHandler.exportXLSfromMaps(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getDwinfoTree(){
		List<TreeBean> treeBeans = ryshService.getDwinfoTree();
		TreeFactory tf = new TreeFactory();	
		tf.setPdm("0");
		tf.setIsDisabledCheck(false);
		tf.setRootText("全部企业");
		renderText(tf.create(treeBeans));
	}
	

	public String preRyxz(){
		zsdw = new HashMap();
		isjfh = ryshService.getIsjfh(dwid);
		
		xtdlbs = ryshService.getDictListByLbid("14");
		xtdict14 = xtdlbs.get(14);
		xtdlbs = ryshService.getDictListByLbid("15");
		xtdict15 = xtdlbs.get(15);
		xtdlbs = ryshService.getDictListByLbid("25");
		xtdict25 = xtdlbs.get(25);
		xtdlbs = ryshService.getDictListByLbid("27");
		xtdict27 = xtdlbs.get(27);
		xtdlbs = ryshService.getDictListByLbid("28");
		xtdict28 = xtdlbs.get(28);
		return "ryxz";
	}
	
	
	public String preRyxg(){
		zsdw = ryshService.preRyxxU(dwid, ryid);
		jfhsbtypes = ryshService.getJfhtypeByRyid(dwid, ryid);
		isjfh = ryshService.getIsjfh(dwid);
		xtdlbs = ryshService.getDictListByLbid("14");
		xtdict14 = xtdlbs.get(14);
		xtdlbs = ryshService.getDictListByLbid("15");
		xtdict15 = xtdlbs.get(15);
		xtdlbs = ryshService.getDictListByLbid("25");
		xtdict25 = xtdlbs.get(25);
		xtdlbs = ryshService.getDictListByLbid("27");
		xtdict27 = xtdlbs.get(27);
		xtdlbs = ryshService.getDictListByLbid("28");
		xtdict28 = xtdlbs.get(28);
		
		fjs11 = ryshService.getRyxxFjList(dwid, ryid, "1", "1","1");
		fjs12 = ryshService.getRyxxFjList(dwid, ryid, "1", "2","1");
		fjs13 = ryshService.getRyxxFjList(dwid, ryid, "1", "3","1");
		//fjs15 = zsdwService.getRyxxFjList(dwid, ryid, "1", "5","1");
		
		fjs21 = ryshService.getRyxxFjList(dwid, ryid, "2", "1","1");
		fjs22 = ryshService.getRyxxFjList(dwid, ryid, "2", "2","1");
		fjs23 = ryshService.getRyxxFjList(dwid, ryid, "2", "3","1");
		fjs31 = ryshService.getRyxxFjList(dwid, ryid, "3", "1","1");
		fjs32 = ryshService.getRyxxFjList(dwid, ryid, "3", "2","1");
		fjs33 = ryshService.getRyxxFjList(dwid, ryid, "3", "3","1");
		fjs34 = ryshService.getRyxxFjList(dwid, ryid, "3", "4","1");
		
		fj2s11 = ryshService.getRyxxFjList(dwid, ryid, "1", "1","2");
		fj2s12 = ryshService.getRyxxFjList(dwid, ryid, "1", "2","2");
		fj2s21 = ryshService.getRyxxFjList(dwid, ryid, "2", "1","2");
		fj2s22 = ryshService.getRyxxFjList(dwid, ryid, "2", "2","2");
		
		fj3s11 = ryshService.getRyxxFjList(dwid, ryid, "1", "1","3");
		fj3s12 = ryshService.getRyxxFjList(dwid, ryid, "1", "2","3");
		
		xtdict28list = ryshService.getDwryxxTrList(dwid, ryid);
		
		return "ryxg";
	}
	
	public void doRyxg(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			ryshService.doRyxg(dwid, ryid, zsdw,jfhsbtype,xtdict28s);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doRyxz(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			ryshService.doRyxz(dwid, zsdw, tt, xx, yy, fj, fjmc, ttype, fjpath+"/zsxm", xtdict28s, jfhsbtype);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preRysh(){
		zsdw = ryshService.preRyxxU(dwid, ryid);
		jfhsbtypes = ryshService.getJfhtypeByRyid(dwid, ryid);
		isjfh = ryshService.getIsjfh(dwid);
		xtdlbs = ryshService.getDictListByLbid("14");
		xtdict14 = xtdlbs.get(14);
		xtdlbs = ryshService.getDictListByLbid("15");
		xtdict15 = xtdlbs.get(15);
		xtdlbs = ryshService.getDictListByLbid("25");
		xtdict25 = xtdlbs.get(25);
		xtdlbs = ryshService.getDictListByLbid("27");
		xtdict27 = xtdlbs.get(27);
		xtdlbs = ryshService.getDictListByLbid("28");
		xtdict28 = xtdlbs.get(28);
		
		fjs11 = ryshService.getRyxxFjList(dwid, ryid, "1", "1","1");
		fjs12 = ryshService.getRyxxFjList(dwid, ryid, "1", "2","1");
		fjs13 = ryshService.getRyxxFjList(dwid, ryid, "1", "3","1");
		//fjs15 = zsdwService.getRyxxFjList(dwid, ryid, "1", "5","1");
		
		fjs21 = ryshService.getRyxxFjList(dwid, ryid, "2", "1","1");
		fjs22 = ryshService.getRyxxFjList(dwid, ryid, "2", "2","1");
		fjs23 = ryshService.getRyxxFjList(dwid, ryid, "2", "3","1");
		fjs31 = ryshService.getRyxxFjList(dwid, ryid, "3", "1","1");
		fjs32 = ryshService.getRyxxFjList(dwid, ryid, "3", "2","1");
		fjs33 = ryshService.getRyxxFjList(dwid, ryid, "3", "3","1");
		fjs34 = ryshService.getRyxxFjList(dwid, ryid, "3", "4","1");
		
		fj2s11 = ryshService.getRyxxFjList(dwid, ryid, "1", "1","2");
		fj2s12 = ryshService.getRyxxFjList(dwid, ryid, "1", "2","2");
		fj2s21 = ryshService.getRyxxFjList(dwid, ryid, "2", "1","2");
		fj2s22 = ryshService.getRyxxFjList(dwid, ryid, "2", "2","2");
		
		fj3s11 = ryshService.getRyxxFjList(dwid, ryid, "1", "1","3");
		fj3s12 = ryshService.getRyxxFjList(dwid, ryid, "1", "2","3");
		
		xtdict28list = ryshService.getDwryxxTrList(dwid, ryid);
		
		return "rysh";
	}
	
	public void doShtg(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			ryshService.doShtg(dwid, ryid, zsdw,jfhsbtype);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	
	public void doShth(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			ryshService.doShth(dwid, ryid, thyy);
			message = new Message("1","退回成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doRyopt(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			ryshService.doRyopt(dwid, ryid, status);
			message = new Message("1","操作成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preShfh(){
		return "ryfh";
	}
	
	public String preShth(){
		return "ryth";
	}
	
	public void doShfh(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			ryshService.doShfh(dwid, ryid, thyy);
			message = new Message("1","发回成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	
	public String preRyth(){
		return "ryth";
	}
	
	public String preRydj(){
		xtdlbs = ryshService.getDictListByLbid("29");
		xtdict29 = xtdlbs.get(29);
		return "rydj";
	}
	
	public void doRydj(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			ryshService.doRydj(dwid, ryid,zsdw);
			message = new Message("1","发回成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String view(){
		zsdw = ryshService.preRyxxU(dwid, ryid);
		isjfh = ryshService.getIsjfh(dwid);
		xtdlbs = ryshService.getDictListByLbid("14");
		xtdict14 = xtdlbs.get(14);
		xtdlbs = ryshService.getDictListByLbid("15");
		xtdict15 = xtdlbs.get(15);
		xtdlbs = ryshService.getDictListByLbid("25");
		xtdict25 = xtdlbs.get(25);
		xtdlbs = ryshService.getDictListByLbid("27");
		xtdict27 = xtdlbs.get(27);
		xtdlbs = ryshService.getDictListByLbid("28");
		xtdict28 = xtdlbs.get(28);
		
		fjs11 = ryshService.getRyxxFjList(dwid, ryid, "1", "1","1");
		fjs12 = ryshService.getRyxxFjList(dwid, ryid, "1", "2","1");
		fjs13 = ryshService.getRyxxFjList(dwid, ryid, "1", "3","1");
		//fjs15 = zsdwService.getRyxxFjList(dwid, ryid, "1", "5","1");
		
		fjs21 = ryshService.getRyxxFjList(dwid, ryid, "2", "1","1");
		fjs22 = ryshService.getRyxxFjList(dwid, ryid, "2", "2","1");
		fjs23 = ryshService.getRyxxFjList(dwid, ryid, "2", "3","1");
		fjs31 = ryshService.getRyxxFjList(dwid, ryid, "3", "1","1");
		fjs32 = ryshService.getRyxxFjList(dwid, ryid, "3", "2","1");
		fjs33 = ryshService.getRyxxFjList(dwid, ryid, "3", "3","1");
		fjs34 = ryshService.getRyxxFjList(dwid, ryid, "3", "4","1");
		
		fj2s11 = ryshService.getRyxxFjList(dwid, ryid, "1", "1","2");
		fj2s12 = ryshService.getRyxxFjList(dwid, ryid, "1", "2","2");
		fj2s21 = ryshService.getRyxxFjList(dwid, ryid, "2", "1","2");
		fj2s22 = ryshService.getRyxxFjList(dwid, ryid, "2", "2","2");
		
		fj3s11 = ryshService.getRyxxFjList(dwid, ryid, "1", "1","3");
		fj3s12 = ryshService.getRyxxFjList(dwid, ryid, "1", "2","3");
		
		xtdict28list = ryshService.getDwryxxTrList(dwid, ryid);
		return "view";
	}
	
	public RyshService getRyshService() {
		return ryshService;
	}
	public void setRyshService(RyshService ryshService) {
		this.ryshService = ryshService;
	}
	public Map<String, Object> getMaps() {
		return maps;
	}
	public void setMaps(Map<String, Object> maps) {
		this.maps = maps;
	}
	public String getThyy() {
		return thyy;
	}
	public void setThyy(String thyy) {
		this.thyy = thyy;
	}
	public String getDwid() {
		return dwid;
	}
	public void setDwid(String dwid) {
		this.dwid = dwid;
	}
	public Xtuser getXtuser() {
		return xtuser;
	}
	public void setXtuser(Xtuser xtuser) {
		this.xtuser = xtuser;
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
	public List<XtDict> getXtdict27() {
		return xtdict27;
	}
	public void setXtdict27(List<XtDict> xtdict27) {
		this.xtdict27 = xtdict27;
	}
	public List<XtDict> getXtdict28() {
		return xtdict28;
	}
	public void setXtdict28(List<XtDict> xtdict28) {
		this.xtdict28 = xtdict28;
	}
	public List<XtDict> getXtdict28list() {
		return xtdict28list;
	}
	public void setXtdict28list(List<XtDict> xtdict28list) {
		this.xtdict28list = xtdict28list;
	}
	public String[] getXtdict28s() {
		return xtdict28s;
	}
	public void setXtdict28s(String[] xtdict28s) {
		this.xtdict28s = xtdict28s;
	}
	public String getCdhjje() {
		return cdhjje;
	}
	public void setCdhjje(String cdhjje) {
		this.cdhjje = cdhjje;
	}
	public String[] getDwsx() {
		return dwsx;
	}
	public void setDwsx(String[] dwsx) {
		this.dwsx = dwsx;
	}
	public String[] getCyfl() {
		return cyfl;
	}
	public void setCyfl(String[] cyfl) {
		this.cyfl = cyfl;
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
	public List<Map<String, String>> getFjs15() {
		return fjs15;
	}
	public void setFjs15(List<Map<String, String>> fjs15) {
		this.fjs15 = fjs15;
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
	public List<Map<String, String>> getFj3s11() {
		return fj3s11;
	}
	public void setFj3s11(List<Map<String, String>> fj3s11) {
		this.fj3s11 = fj3s11;
	}
	public List<Map<String, String>> getFj3s12() {
		return fj3s12;
	}
	public void setFj3s12(List<Map<String, String>> fj3s12) {
		this.fj3s12 = fj3s12;
	}
	public String getFjpath() {
		return fjpath;
	}
	public void setFjpath(String fjpath) {
		this.fjpath = fjpath;
	}
	public String[] getJfhsbtype() {
		return jfhsbtype;
	}
	public void setJfhsbtype(String[] jfhsbtype) {
		this.jfhsbtype = jfhsbtype;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getIsjfh() {
		return isjfh;
	}
	public void setIsjfh(Integer isjfh) {
		this.isjfh = isjfh;
	}
	public List<XtDict> getXtdict29() {
		return xtdict29;
	}
	public void setXtdict29(List<XtDict> xtdict29) {
		this.xtdict29 = xtdict29;
	}
	public List<Map<String, String>> getJfhsbtypes() {
		return jfhsbtypes;
	}
	public void setJfhsbtypes(List<Map<String, String>> jfhsbtypes) {
		this.jfhsbtypes = jfhsbtypes;
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
}
