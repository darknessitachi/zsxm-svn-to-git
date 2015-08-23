package com.netwander.explib.web.action;

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
import com.netwander.core.Constants;
import com.netwander.core.common.Message;
import com.netwander.core.common.Page;
import com.netwander.explib.entity.XtDict;
import com.netwander.explib.entity.Xtuser;
import com.netwander.explib.service.ExpService;
import com.netwander.explib.service.FzglService;
import com.netwander.explib.web.common.BaseAction;


@ParentPackage("appDefault")
@Scope("prototype")
@Controller
@Results( { 
	@Result(name = "wb", value = "/WEB-INF/pages/fzgl/fzgl_wb.jsp"),
	@Result(name = "yr", value = "/WEB-INF/pages/fzgl/fzgl_yr.jsp"),
	@Result(name = "dr", value = "/WEB-INF/pages/fzgl/fzgl_dr.jsp"),
	@Result(name = "preyr", value = "/WEB-INF/pages/fzgl/fzgl_yr_p.jsp"),
	@Result(name = "predr", value = "/WEB-INF/pages/fzgl/fzgl_dr_p.jsp"),
	@Result(name = "prezjps", value = "/WEB-INF/pages/fzgl/fzgl_yr_zjps.jsp"),
	@Result(name = "preczrc", value = "/WEB-INF/pages/fzgl/fzgl_yr_czrc.jsp")
})
public class FzglAction extends BaseAction{
	@Autowired
	@Qualifier("fzglService")
	private FzglService fzglService;
	
	@Autowired
	@Qualifier("expService")
	private ExpService expService;
	
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
	
	private String drdata;
	private String yrdata;
	private Integer itype;
	
	private String keys;
	private Xtuser xtuser;
	public void onPrepare() {
		xtuser = (Xtuser)getSession().getAttribute(Constants.USER_SESSION_KEY);
	}
	public String datawb(){
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
		return "wb";
	}
	
	public String datayr(){
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
		return "yr";
	}
	
	
	public String datadr(){
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
		return "dr";
	}
	
	public void doGetList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		getLimitPage().setRowAttributes(gridHandler.getTotalRowNum(), gridHandler.getPageNum(), gridHandler.getPageSize());
		Map param = getParameterSimpleMap();
		Page page = (Page) fzglService.getListForRcdaByName(getLimitPage(), param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo(),xtuser,itype);
		gridHandler.setTotalRowNum(page.getTotalCount());
		gridHandler.setData((List) page.getData());
		renderText(gridHandler.getLoadResponseText());
	}
	
	public void doExportList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		Map param = gridHandler.getGridRequestParameters();
		List list = (List) fzglService.getListForRcdaByName(null, param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo(),xtuser,itype);
		try {
			gridHandler.exportXLSfromMaps(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String preyrdata(){
		return "preyr";
	}
	
	public String goyrdata(){
		if(yrdata.equals("zjps")){
			return "prezjps";
		}else if(yrdata.equals("czrc")){
			return "preczrc";
		}
		return "";
	}
	
	public void doGetZjpsList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		getLimitPage().setRowAttributes(gridHandler.getTotalRowNum(), gridHandler.getPageNum(), gridHandler.getPageSize());
		Map param = getParameterSimpleMap();
		Page page = (Page) fzglService.getListForZjps(getLimitPage(), param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo());
		gridHandler.setTotalRowNum(page.getTotalCount());
		gridHandler.setData((List) page.getData());
		renderText(gridHandler.getLoadResponseText());
	}
	
	public void doGetCzrcList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		getLimitPage().setRowAttributes(gridHandler.getTotalRowNum(), gridHandler.getPageNum(), gridHandler.getPageSize());
		Map param = getParameterSimpleMap();
		Page page = (Page) fzglService.getListForCzrc(getLimitPage(), param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo(), xtuser);
		gridHandler.setTotalRowNum(page.getTotalCount());
		gridHandler.setData((List) page.getData());
		renderText(gridHandler.getLoadResponseText());
	}
	
	
	public void doSelCzrc(){
		
	}
	
	public void doSelZjps(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			fzglService.doYrZjpsData(keys);
			message = new Message("1","引入成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String predrdata(){
		return "predr";
	}
	/***
	 * Mapping
	 * @return
	 */
	public Integer getItype() {
		return itype;
	}

	public void setItype(Integer itype) {
		this.itype = itype;
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

	public String getDrdata() {
		return drdata;
	}

	public void setDrdata(String drdata) {
		this.drdata = drdata;
	}

	public String getYrdata() {
		return yrdata;
	}

	public void setYrdata(String yrdata) {
		this.yrdata = yrdata;
	}

	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}
}
