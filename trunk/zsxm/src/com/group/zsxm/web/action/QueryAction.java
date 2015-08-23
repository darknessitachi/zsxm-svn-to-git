package com.group.zsxm.web.action;

import java.io.IOException;
import java.util.ArrayList;
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
import com.group.core.common.Page;
import com.group.zsxm.entity.XtDict;
import com.group.zsxm.entity.Xtuser;
import com.group.zsxm.service.QueryService;
import com.group.zsxm.service.ZsdwService;
import com.group.zsxm.service.ZsxmService;
import com.group.zsxm.service.common.SystemService;
import com.group.zsxm.web.common.BaseAction;

@SuppressWarnings("unchecked")
@ParentPackage("appDefault")
@Scope("prototype")
@Results( { 
	@Result(name = "input", value = "/WEB-INF/jsp/query/query.jsp"),
	@Result(name = "xmhquery", value = "/WEB-INF/jsp/query/xmsearch.jsp"),
	@Result(name = "dwhquery", value = "/WEB-INF/jsp/query/dwsearch.jsp"),
	@Result(name = "dwcdxmquery", value = "/WEB-INF/jsp/query/dwcdxmsearch.jsp"),
	@Result(name = "jzqkquery", value = "/WEB-INF/jsp/query/xmjzqksearch.jsp"),
	@Result(name = "dwyqfw", value = "/WEB-INF/jsp/query/dwyqfwsearch.jsp")
})
public class QueryAction extends BaseAction {

	private static final long serialVersionUID = -3219526825178265051L;
	@Autowired
	@Qualifier("zsxmService")
	private ZsxmService zsxmService;
	
	@Autowired
	@Qualifier("zsdwService")
	private ZsdwService zsdwService;
	
	@Autowired
	@Qualifier("systemService")
	private SystemService systemService;
	
    @Autowired
	private QueryService queryService;
    
    private List<Map<String, String>> xmlbs;
	private List<Map<String, String>> xmxjs;
	private List<Map<String, String>> xmjds;
	private List<Map<String, String>> xtusers;
	
	private Map<Integer, List<XtDict>> xtdlbs;
	private List<Map<String, String>> xtdict47;
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
	private List<XtDict> xtdict32;
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
	
	private List<Map<String, String>> zczbs;
	private List<Map<String, String>> jzmzs; 
	private List<Map<String, String>> snsssrs;
	private List<Map<String, String>> sssrzzs;//上年销售收入
	private List<Map<String, String>> snjnss;//上年缴纳税收
	private List<Map<String, String>> jnsszc;//缴纳税收增长率
	private List<Map<String, String>> sndygs;//上年底员工数
	private List<Map<String, String>> ygszzl;//员工数增长率率
	
	
	
	private List<XtDict> xtdict48;
	private String[] xtdict48s;
	private List<XtDict> xtdict49;
    private Xtuser xtuser;
	public void prepare() {
		xtuser = (Xtuser)getSession().getAttribute(Constants.USER_SESSION_KEY);
	} 
	
	public String execute() {
		return INPUT;
	}
	
	public String xmHQuery(){
		xmlbs = zsxmService.getDictList(1);
		xmxjs = zsxmService.getDictList(2);
		xmjds = zsxmService.getDictList(3);
		
		xtusers = systemService.getXtuserList();
		return "xmhquery";
	}
	
	public String dwHQuery(){
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
		
		xtdlbs = zsdwService.getDictListByLbid("38");
		xtdict38 = xtdlbs.get(38);
		
		xtdlbs = zsdwService.getDictListByLbid("40");
		xtdict40 = xtdlbs.get(40);
		
		xtdlbs = zsdwService.getDictListByLbid("16");
		xtdict16 = xtdlbs.get(16);
		xtdlbs = zsdwService.getDictListByLbid("19");
		xtdict19 = xtdlbs.get(19);
		
		xtdlbs = zsdwService.getDictListByLbid("20");
		xtdict20 = xtdlbs.get(20);
		
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
		xtusers = systemService.getXtuserList();
		
		zczbs = queryService.getQjfwList("1");
		jzmzs = queryService.getQjfwList("1"); 
		snsssrs = queryService.getQjfwList("2");
		sssrzzs = queryService.getQjfwList("3");//
		snjnss = queryService.getQjfwList("4");//上年缴纳税收
		jnsszc = queryService.getQjfwList("5");//缴纳税收增长率
		sndygs = queryService.getQjfwList("6");//上年底员工数
		ygszzl = queryService.getQjfwList("7");
		
		return "dwhquery";
	}
	
	public String dwcdxmQuery(){
		xtdlbs = zsdwService.getDictListByLbid("16");
		xtdict16 = xtdlbs.get(16);
		xtdlbs = zsdwService.getDictListByLbid("19");
		xtdict19 = xtdlbs.get(19);
		xtdlbs = zsdwService.getDictListByLbid("20");
		xtdict20 = xtdlbs.get(20);
		return "dwcdxmquery";
	}
	
	public String xmjzqkQuery(){
		xmlbs = zsxmService.getDictList(1);
		xmxjs = zsxmService.getDictList(2);
		xmjds = zsxmService.getDictList(3);
		
		xtusers = systemService.getXtuserList();
		return "jzqkquery";
	}
	
	public String dwyqfwHQuery(){
		xtdlbs = zsdwService.getDictListWithSelectAll();
		xtdict4 = xtdlbs.get(4);
		xtdict9 = xtdlbs.get(9);
		xtdlbs = zsdwService.getDictListByLbid("32");
		xtdict32 = xtdlbs.get(32);
		xtusers = systemService.getXtuserList();
		return "dwyqfw";
	}
	
	public void doGetList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		getLimitPage().setRowAttributes(gridHandler.getTotalRowNum(), gridHandler.getPageNum(), gridHandler.getPageSize());
		Map param = getParameterSimpleMap();
		Page page = (Page) queryService.getListForRcdaByName(getLimitPage(), param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo());
		gridHandler.setTotalRowNum(page.getTotalCount());
		gridHandler.setData((List) page.getData());
		renderText(gridHandler.getLoadResponseText());
	}

	public void doExportList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		Map param = gridHandler.getGridRequestParameters();
		List list = (List) queryService.getListForRcdaByName(null, param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo());
		try {
			gridHandler.exportXLSfromMaps(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ZsxmService getZsxmService() {
		return zsxmService;
	}

	public void setZsxmService(ZsxmService zsxmService) {
		this.zsxmService = zsxmService;
	}

	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	public QueryService getQueryService() {
		return queryService;
	}

	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
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

	public List<Map<String, String>> getXtusers() {
		return xtusers;
	}

	public void setXtusers(List<Map<String, String>> xtusers) {
		this.xtusers = xtusers;
	}

	public List<Map<String, String>> getXtdict47() {
		return xtdict47;
	}

	public void setXtdict47(List<Map<String, String>> xtdict47) {
		this.xtdict47 = xtdict47;
	}

	public Xtuser getXtuser() {
		return xtuser;
	}

	public void setXtuser(Xtuser xtuser) {
		this.xtuser = xtuser;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
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

	public List<XtDict> getXtdict30() {
		return xtdict30;
	}

	public void setXtdict30(List<XtDict> xtdict30) {
		this.xtdict30 = xtdict30;
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

	public List<Map<String, String>> getYhyjs() {
		return yhyjs;
	}

	public void setYhyjs(List<Map<String, String>> yhyjs) {
		this.yhyjs = yhyjs;
	}

	public List<XtDict> getXtdict48() {
		return xtdict48;
	}

	public void setXtdict48(List<XtDict> xtdict48) {
		this.xtdict48 = xtdict48;
	}

	public String[] getXtdict48s() {
		return xtdict48s;
	}

	public void setXtdict48s(String[] xtdict48s) {
		this.xtdict48s = xtdict48s;
	}

	public List<XtDict> getXtdict49() {
		return xtdict49;
	}

	public void setXtdict49(List<XtDict> xtdict49) {
		this.xtdict49 = xtdict49;
	}

	public Map<Integer, List<XtDict>> getXtdlbs() {
		return xtdlbs;
	}

	public void setXtdlbs(Map<Integer, List<XtDict>> xtdlbs) {
		this.xtdlbs = xtdlbs;
	}

	public ZsdwService getZsdwService() {
		return zsdwService;
	}

	public void setZsdwService(ZsdwService zsdwService) {
		this.zsdwService = zsdwService;
	}

	public List<Map<String, String>> getZczbs() {
		return zczbs;
	}

	public void setZczbs(List<Map<String, String>> zczbs) {
		this.zczbs = zczbs;
	}

	public List<Map<String, String>> getJzmzs() {
		return jzmzs;
	}

	public void setJzmzs(List<Map<String, String>> jzmzs) {
		this.jzmzs = jzmzs;
	}

	public List<Map<String, String>> getSnsssrs() {
		return snsssrs;
	}

	public void setSnsssrs(List<Map<String, String>> snsssrs) {
		this.snsssrs = snsssrs;
	}

	public List<Map<String, String>> getSssrzzs() {
		return sssrzzs;
	}

	public void setSssrzzs(List<Map<String, String>> sssrzzs) {
		this.sssrzzs = sssrzzs;
	}

	public List<Map<String, String>> getSnjnss() {
		return snjnss;
	}

	public void setSnjnss(List<Map<String, String>> snjnss) {
		this.snjnss = snjnss;
	}

	public List<Map<String, String>> getJnsszc() {
		return jnsszc;
	}

	public void setJnsszc(List<Map<String, String>> jnsszc) {
		this.jnsszc = jnsszc;
	}

	public List<Map<String, String>> getSndygs() {
		return sndygs;
	}

	public void setSndygs(List<Map<String, String>> sndygs) {
		this.sndygs = sndygs;
	}

	public List<Map<String, String>> getYgszzl() {
		return ygszzl;
	}

	public void setYgszzl(List<Map<String, String>> ygszzl) {
		this.ygszzl = ygszzl;
	}

	public List<XtDict> getXtdict32() {
		return xtdict32;
	}

	public void setXtdict32(List<XtDict> xtdict32) {
		this.xtdict32 = xtdict32;
	}
}
