package com.netwander.explib.web.action;

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
import com.netwander.core.Constants;
import com.netwander.core.common.Message;
import com.netwander.core.common.Page;
import com.netwander.explib.entity.DmMc;
import com.netwander.explib.entity.XtDict;
import com.netwander.explib.entity.Xtuser;
import com.netwander.explib.service.ExpService;
import com.netwander.explib.service.ExptbService;
import com.netwander.explib.web.common.BaseAction;
import com.opensymphony.xwork2.Action;

@ParentPackage("appDefault")
@Scope("prototype")
@Controller
@Results( { 
	@Result(name = "input", value = "/WEB-INF/pages/exptb/exptb.jsp"),
	@Result(name = "exptbmx", value = "/WEB-INF/pages/exptb/exptb_mx.jsp"),
	@Result(name = "exptbbatch", value = "/WEB-INF/pages/exptb/exptb_batch.jsp"),
	@Result(name = "exp", value = "/WEB-INF/pages/exptb/exptb_main.jsp"),
	@Result(name = "jszc", value = "/WEB-INF/pages/exptb/exptb_jszc.jsp"),
	@Result(name = "jszcd", value = "/WEB-INF/pages/exptb/exptb_jszc_d.jsp"),
	@Result(name = "xxjl", value = "/WEB-INF/pages/exptb/exptb_xxjl.jsp"),
	@Result(name = "xxjld", value = "/WEB-INF/pages/exptb/exptb_xxjl_d.jsp"),
	@Result(name = "gzjl", value = "/WEB-INF/pages/exptb/exptb_gzjl.jsp"),
	@Result(name = "gzjld", value = "/WEB-INF/pages/exptb/exptb_gzjl_d.jsp"),
	@Result(name = "ktxm", value = "/WEB-INF/pages/exptb/exptb_ktxm.jsp"),
	@Result(name = "ktxmd", value = "/WEB-INF/pages/exptb/exptb_ktxm_d.jsp"),
	@Result(name = "ryhj", value = "/WEB-INF/pages/exptb/exptb_ryhj.jsp"),
	@Result(name = "ryhjd", value = "/WEB-INF/pages/exptb/exptb_ryhj_d.jsp"),
	@Result(name = "shjz", value = "/WEB-INF/pages/exptb/exptb_shjz.jsp"),
	@Result(name = "shjzd", value = "/WEB-INF/pages/exptb/exptb_shjz_d.jsp"),
	@Result(name = "bpzj", value = "/WEB-INF/pages/exptb/exptb_bpzj.jsp"),
	@Result(name = "bpzjd", value = "/WEB-INF/pages/exptb/exptb_bpzj_d.jsp"),
	@Result(name = "zylz", value = "/WEB-INF/pages/exptb/exptb_zylz.jsp"),
	@Result(name = "zylzd", value = "/WEB-INF/pages/exptb/exptb_zylz_d.jsp"),
	@Result(name = "zscq", value = "/WEB-INF/pages/exptb/exptb_zscq.jsp"),
	@Result(name = "zscqd", value = "/WEB-INF/pages/exptb/exptb_zscq_d.jsp"),
	@Result(name = "tbth", value = "/WEB-INF/pages/exptb/exptb_th.jsp"),
	@Result(name = "expsearch", value = "/WEB-INF/pages/exptb/exptb_search.jsp")
})
public class ExptbAction extends BaseAction{
	@Autowired
	@Qualifier("exptbService")
	private ExptbService exptbService;
	
	@Autowired
	@Qualifier("expService")
	private ExpService expService;
	
	private String thyy;
	private String sfzh;
	private String rcid;  //现有库中ID EXP_MAIN.RCID
	private String rcidtb;//专集库中ID EXP_MAIN_ZJ.RCID
	private String fldm;
	private String itype;
	private String alltbbz;  //如果 alltbbz =1 : 全部同步，不存在数据就新增，存在就把正式库中的覆盖
	private String tbtname; //同步数据表名
	
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
	/***
	 * 现有库里的专家信息
	 */
	private Map<String, String> exp;   
	private List<Map<String, Object>> qlist;
	
	/**
	 * 征集的专家信息
	 */
	private Map<String, String> exptb;
	private List<Map<String, Object>> qlisttb;
	private List<Map<String, Object>> expfllist;
	private List<XtDict> xtdict;
	private List<XtDict> xtdict1;//证件类别
	
	private List<XtDict> xtdict4;//性别
	private List<XtDict> xtdict6;//专家状态
	private List<XtDict> xtdict7;//专家性质
	private List<XtDict> xtdict8;//专家用途
	private List<XtDict> xtdict9;//太岗状态
	private List<XtDict> xtdict10;//所在地区
	private List<XtDict> xtdict11;//从事行业
	
	private List<XtDict> xtdict13;//学科专业
	private List<XtDict> xtdict14;//技术领域
	private List<XtDict> xtdict15;//专业
	private List<XtDict> xtdict16;//技术专长
	private List<XtDict> xtdict17;//知识产权情况
	private List<XtDict> xtdict18;//熟悉程度
	private List<XtDict> xtdict19;//完成情况
	private List<XtDict> xtdict20;//国籍
	
	private List<XtDict> xtdict24;//
	private List<XtDict> xtdict26;//
	private List<XtDict> xtdict31;
	private String expfl;
	private String[] dmkey;
	private String xh;
	private String xhtb;
	
	private Xtuser xtuser;
	
	private String tbtype;  // "WB"：EXP_MAIN_WB 同步
	
	public void onPrepare() {
		
		xtuser = (Xtuser)getSession().getAttribute(Constants.USER_SESSION_KEY);
	}
	
	public String execute(){
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
	
	public void doGetList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		getLimitPage().setRowAttributes(gridHandler.getTotalRowNum(), gridHandler.getPageNum(), gridHandler.getPageSize());
		Map param = getParameterSimpleMap();
		Page page = (Page) exptbService.getListForRcdaByName(getLimitPage(), param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo(),xtuser);
		gridHandler.setTotalRowNum(page.getTotalCount());
		gridHandler.setData((List) page.getData());
		renderText(gridHandler.getLoadResponseText());
	}
	
	public void doExportList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		Map param = gridHandler.getGridRequestParameters();
		List list = (List) exptbService.getListForRcdaByName(null, param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo(),xtuser);
		try {
			gridHandler.exportXLSfromMaps(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String preExptb(){
		if(sfzh != null && !sfzh.equals("")){
			rcid = rcidtb;//expService.getLibExpWithSfz(sfzh);
		}else{
			rcid = "";
		}
		return "exptbmx";
	}
	
	public String preExpbatchtb(){
		expfllist = expService.getExpFlList2(xtuser.getRoledm(), xtuser.getUserid(), rcid);
		return "exptbbatch";
	}
	
	public void doDeleteExp(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			exptbService.doDeleteExp(rcid);
			message = new Message("1","删除成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	
	public String preExp(){
		xtdlbs = expService.getDictListWithSelectAll();
		xtdict1 = xtdlbs.get(1);
		xtdict2 = xtdlbs.get(2);
		xtdict3 = xtdlbs.get(3);
		xtdict4 = xtdlbs.get(4);
		xtdict5 = xtdlbs.get(5);
		xtdict6 = xtdlbs.get(6);
		xtdict7 = xtdlbs.get(7);
		xtdict8 = xtdlbs.get(8);
		xtdict9 = xtdlbs.get(9);
		xtdict12 = xtdlbs.get(12);
		xtdict20 = xtdlbs.get(20);
		xtdict21 = xtdlbs.get(21);
		xtdict22 = xtdlbs.get(22);
		xtdict23 = xtdlbs.get(23);
		
		if(rcid != null && !rcid.equals("")){
			exp = expService.preExpMain_v(rcid);
			qlist = expService.getLxgjList(rcid);
		}else{
			exp = new HashMap();
			qlist = new ArrayList();
		}
		if(tbtype!= null && tbtype.equals("WB")){
			exptb = exptbService.preExpMainWb(rcidtb);
			qlisttb = exptbService.getLxgjListWb(rcidtb);
		}else{
			exptb = exptbService.preExpMain(rcidtb,itype);
			qlisttb = exptbService.getLxgjList(rcidtb);
		}
		
		return "exp";
	}
	
	public String preJszc(){
		if(rcid != null && !rcid.equals("")){
			qlist = expService.getJszcList(rcid);
		}else{
			qlist = new ArrayList();
		}
		if(tbtype!= null && tbtype.equals("WB")){
			qlisttb = exptbService.getJszcListWb(rcidtb);
		}else{
			qlisttb = exptbService.getJszcList(rcidtb);
		}
		
		return "jszc";
	}
	
	public void doJszcTb(){
		
	}
	
	public String preJszcT(){
		if(rcid != null && !rcid.equals("")){
			exp = expService.getJszcU(rcid, xh);
		}else{
			exp = new HashMap();
		}
		exptb = exptbService.getJszcU(rcidtb, xhtb);
		return "jszcd";
	}
	
	public String preXxjl(){
		if(rcid != null && !rcid.equals("")){
			qlist = expService.getXxjlList(rcid);
		}else{
			qlist = new ArrayList();
		}
		qlisttb = exptbService.getXxjlList(rcidtb);
		return "xxjl";
	}
	
	public String preXxjlT(){
		if(rcid != null && !rcid.equals("")){
			exp = expService.getXxjlU(rcid, xh);
		}else{
			exp = new HashMap();
		}
		xtdict2 = expService.getDictListWithSelect(2);
		xtdict3 = expService.getDictListWithSelect(3);
		exptb = exptbService.getXxjlU(rcidtb, xhtb);
		return "xxjld";
	}
	
	public String preGzjl(){
		if(rcid != null && !rcid.equals("")){
			qlist = expService.getGzjlList(rcid);
		}else{
			qlist = new ArrayList();
		}
		qlisttb = exptbService.getGzjlList(rcidtb);
		return "gzjl";
	}
	
	public String preGzjlT(){
		if(rcid != null && !rcid.equals("")){
			exp = expService.getGzjlU(rcid, xh);
		}else{
			exp = new HashMap();
		}
		exptb = exptbService.getGzjlU(rcidtb, xhtb);
		return "gzjld";
	}
	
	public String preKtxm(){
		if(rcid != null && !rcid.equals("")){
			qlist = expService.getKtxmList(rcid);
		}else{
			qlist = new ArrayList();
		}
		qlisttb = exptbService.getKtxmList(rcidtb);
		return "ktxm";
	}
	
	public String preKtxmT(){
		if(rcid != null && !rcid.equals("")){
			exp = expService.getKtxmU(rcid, xh);
		}else{
			exp = new HashMap();
		}
		xtdict19 = expService.getDictListWithSelect(19);
		xtdict24 = expService.getDictListWithSelect(24);
		xtdict26 = expService.getDictListWithSelect(26);
		xtdict28 = expService.getDictListWithSelect(28);
		exptb = exptbService.getKtxmU(rcidtb, xhtb);
		return "ktxmd";
	}
	
	public String preRyhj(){
		if(rcid != null && !rcid.equals("")){
			qlist = expService.getRyhjList(rcid);
		}else{
			qlist = new ArrayList();
		}
		qlisttb = exptbService.getRyhjList(rcidtb);
		return "ryhj";
	}
	public String preRyhjT(){
		if(rcid != null && !rcid.equals("")){
			exp = expService.getRyhjU(rcid, xh);
		}else{
			exp = new HashMap();
		}
		xtdict = expService.getDictListWithSelect(32);
		exptb = exptbService.getRyhjU(rcidtb, xhtb);
		return "ryhjd";
	}
	
	public String preShjz(){
		if(rcid != null && !rcid.equals("")){
			qlist = expService.getShjzList(rcid);
		}else{
			qlist = new ArrayList();
		}
		qlisttb = exptbService.getShjzList(rcidtb);
		return "shjz";
	}
	public String preShjzT(){
		if(rcid != null && !rcid.equals("")){
			exp = expService.getShjzU(rcid, xh);
		}else{
			exp = new HashMap();
		}
		exptb = exptbService.getShjzU(rcidtb, xhtb);
		return "shjzd";
	}
	
	public String preBpzj(){
		if(rcid != null && !rcid.equals("")){
			qlist = expService.getBpzjList(rcid);
		}else{
			qlist = new ArrayList();
		}
		qlisttb = exptbService.getBpzjList(rcidtb);
		return "bpzj";
	}
	public String preBpzjT(){
		if(rcid != null && !rcid.equals("")){
			exp = expService.getBpzjU(rcid, xh);
		}else{
			exp = new HashMap();
		}
		
		int x= Integer.parseInt(xtuser.getLogindate().substring(0,4))-1960;
		dmmcs = new ArrayList<DmMc>();
		for(int i=0;i<=x;i++){
			DmMc dm = new DmMc(String.valueOf(1960+i),String.valueOf(1960+i));
			dmmcs.add(dm);
		}
		exptb = exptbService.getBpzjU(rcidtb, xhtb);
		return "bpzjd";
	}
	
	public String preZylz(){
		if(rcid != null && !rcid.equals("")){
			qlist = expService.getZylzList(rcid);
		}else{
			qlist = new ArrayList();
		}
		qlisttb = exptbService.getZylzList(rcidtb);
		return "zylz";
	}
	public String preZylzT(){
		if(rcid != null && !rcid.equals("")){
			exp = expService.getZylzU(rcid, xh);
		}else{
			exp = new HashMap();
		}
		xtdict31 = expService.getDictListWithSelect(31);
		exptb = exptbService.getZylzU(rcidtb, xhtb);
		return "zylzd";
	}
	
	public String preZscq(){
		if(rcid != null && !rcid.equals("")){
			qlist = expService.getZscqList(rcid);
		}else{
			qlist = new ArrayList();
		}
		qlisttb = exptbService.getZscqList(rcidtb);
		return "zscq";
	}
	public String preZscqT(){
		if(rcid != null && !rcid.equals("")){
			exp = expService.getZscqU(rcid, xh);
		}else{
			exp = new HashMap();
		}
		xtdict17 = expService.getDictListWithSelect(17);
		exptb = exptbService.getZscqU(rcidtb, xhtb);
		return "zscqd";
	}
	
	public String preExpSearch(){
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
		return "expsearch";
	}
	
	public String preExpth(){
		return "tbth";
	}
	
	public void doExpshtg(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			if(tbtype != null && tbtype.equals("WB")){
				exptbService.doExpShtgWb(rcidtb);
			}else{
				exptbService.doExpShtg(rcidtb);
			}
			message = new Message("1","操作成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	
	public void doExpshth(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			exptbService.toExpShth(rcidtb,thyy);
			message = new Message("1","操作成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doExpTbMain(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			exptbService.doExpTbMain(rcidtb, rcid, dmkey, exptb, alltbbz,tbtype);
			message = new Message("1","操作成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doExpTbBatch(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			exptbService.doExpTbBatch( rcidtb,expfl, tbtype);
			message = new Message("1","操作成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doExpTbMx(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			exptbService.doExpTbMx(rcidtb, xhtb, rcid, xh, dmkey, exptb, tbtname);
			message = new Message("1","操作成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	
	public void doExpAppend(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			exptbService.doExpAppend(rcidtb, dmkey, rcid, tbtname,tbtype);
			message = new Message("1","操作成功");
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
	public String getRcid() {
		return rcid;
	}
	public void setRcid(String rcid) {
		this.rcid = rcid;
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

	public List<XtDict> getXtdict() {
		return xtdict;
	}

	public void setXtdict(List<XtDict> xtdict) {
		this.xtdict = xtdict;
	}

	public List<XtDict> getXtdict1() {
		return xtdict1;
	}

	public void setXtdict1(List<XtDict> xtdict1) {
		this.xtdict1 = xtdict1;
	}

	public List<XtDict> getXtdict4() {
		return xtdict4;
	}

	public void setXtdict4(List<XtDict> xtdict4) {
		this.xtdict4 = xtdict4;
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

	public List<XtDict> getXtdict24() {
		return xtdict24;
	}

	public void setXtdict24(List<XtDict> xtdict24) {
		this.xtdict24 = xtdict24;
	}

	public List<XtDict> getXtdict26() {
		return xtdict26;
	}

	public void setXtdict26(List<XtDict> xtdict26) {
		this.xtdict26 = xtdict26;
	}

	public List<XtDict> getXtdict31() {
		return xtdict31;
	}

	public void setXtdict31(List<XtDict> xtdict31) {
		this.xtdict31 = xtdict31;
	}

	public Map<String, String> getExp() {
		return exp;
	}

	public void setExp(Map<String, String> exp) {
		this.exp = exp;
	}

	public List<Map<String, Object>> getQlist() {
		return qlist;
	}

	public void setQlist(List<Map<String, Object>> qlist) {
		this.qlist = qlist;
	}


	public Map<String, String> getExptb() {
		return exptb;
	}

	public void setExptb(Map<String, String> exptb) {
		this.exptb = exptb;
	}

	public List<Map<String, Object>> getQlisttb() {
		return qlisttb;
	}

	public void setQlisttb(List<Map<String, Object>> qlisttb) {
		this.qlisttb = qlisttb;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXhtb() {
		return xhtb;
	}

	public void setXhtb(String xhtb) {
		this.xhtb = xhtb;
	}

	public String getRcidtb() {
		return rcidtb;
	}

	public void setRcidtb(String rcidtb) {
		this.rcidtb = rcidtb;
	}

	public String getThyy() {
		return thyy;
	}

	public void setThyy(String thyy) {
		this.thyy = thyy;
	}

	public List<DmMc> getDmmcs() {
		return dmmcs;
	}

	public void setDmmcs(List<DmMc> dmmcs) {
		this.dmmcs = dmmcs;
	}

	public String getAlltbbz() {
		return alltbbz;
	}

	public void setAlltbbz(String alltbbz) {
		this.alltbbz = alltbbz;
	}

	public String getTbtname() {
		return tbtname;
	}

	public void setTbtname(String tbtname) {
		this.tbtname = tbtname;
	}

	public String[] getDmkey() {
		return dmkey;
	}

	public void setDmkey(String[] dmkey) {
		this.dmkey = dmkey;
	}

	public String getTbtype() {
		return tbtype;
	}

	public void setTbtype(String tbtype) {
		this.tbtype = tbtype;
	}

	public List<Map<String, Object>> getExpfllist() {
		return expfllist;
	}

	public void setExpfllist(List<Map<String, Object>> expfllist) {
		this.expfllist = expfllist;
	}

	public String getExpfl() {
		return expfl;
	}

	public void setExpfl(String expfl) {
		this.expfl = expfl;
	}

	public String getFldm() {
		return fldm;
	}

	public void setFldm(String fldm) {
		this.fldm = fldm;
	}

	public String getItype() {
		return itype;
	}

	public void setItype(String itype) {
		this.itype = itype;
	}
	
}
