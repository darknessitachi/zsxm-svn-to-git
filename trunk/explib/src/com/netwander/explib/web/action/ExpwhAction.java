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
import com.netwander.explib.entity.XtDict;
import com.netwander.core.common.Message;
import com.netwander.core.common.Page;
import com.netwander.core.Constants;
import com.netwander.explib.entity.Xtuser;
import com.netwander.explib.service.ExpService;
import com.netwander.explib.service.ExpwhService;
import com.netwander.explib.web.common.BaseAction;
import com.opensymphony.xwork2.Action;

@ParentPackage("appDefault")
@Scope("prototype")
@Controller
@Results( { 
	@Result(name = "input", value = "/WEB-INF/pages/expwh/expwh.jsp"),
	@Result(name = "view", value = "/WEB-INF/pages/expwh/expview.jsp")
})

public class ExpwhAction extends BaseAction {
	
	@Autowired
	@Qualifier("expwhService")
	private ExpwhService expwhService;
	
	@Autowired
	@Qualifier("expService")
	private ExpService expService;
	
	private String rcid;
	private Map<Integer, List<XtDict>> xtdlbs;//基础数据集合
	private List<XtDict> xtdictsel;
	private String[] dmkey;
	private String[] dmkey2;
	private List<XtDict> xtdict;
	private List<XtDict> xtdict2;//学历
	private List<XtDict> xtdict3;//学位
	private List<XtDict> xtdict5;//职称
	private List<XtDict> xtdict23;
	private List<XtDict> xtdict12;//单位性质
	private List<XtDict> xtdict21;//
	private List<XtDict> xtdict22;//
	private List<XtDict> xtdict25;//
	private List<XtDict> xtdict28;//
	private List<XtDict> xtdict31;
	private List<XtDict> xtdict36;
	//人才信息查询结果集
	private List<Map<String, Object>> qlist;
	private Map<String,Object> maps;
	private Xtuser xtuser;
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
		Page page = (Page) expwhService.getListForRcdaByName(getLimitPage(), param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo(),xtuser);
		gridHandler.setTotalRowNum(page.getTotalCount());
		gridHandler.setData((List) page.getData());
		renderText(gridHandler.getLoadResponseText());
	}
	
	public void doExportList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		Map param = gridHandler.getGridRequestParameters();
		List list = (List) expwhService.getListForRcdaByName(null, param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo(),xtuser);
		try {
			gridHandler.exportXLSfromMaps(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDeleteExp(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			expwhService.doDeleteExp(rcid);
			message = new Message("1","删除成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doQropt(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			expwhService.doQropt(rcid);
			message = new Message("1","确认成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preView(){
		xtdlbs = expService.getDictListWithSelectAll();
		xtdict36 = xtdlbs.get(36);
		xtdict = expService.getDictListWithSelectByLen(14, 3);
		maps = expwhService.preView(rcid,"");
		
		qlist = expService.getLxgjList(rcid);
		Map m_ = (Map) maps.get("rcdaxx1");
		if(m_.get("sctzhy") != null && !String.valueOf(m_.get("sctzhy")).equals("")){
			dmkey2 = String.valueOf(m_.get("sctzhy")).split(",");
		}
		
		xtdictsel = expService.getDictWithSel(rcid);
		if(xtdictsel != null && xtdictsel.size() > 0){
			dmkey = new String[xtdictsel.size()];
			for(int i=0;i<xtdictsel.size();i++){
				dmkey[i] = xtdictsel.get(i).getDictbh();
			}
		}
		return "view";
	}
	/**
	 * Mapping
	 */
	
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

	public String getRcid() {
		return rcid;
	}

	public void setRcid(String rcid) {
		this.rcid = rcid;
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

	public Map<String, Object> getMaps() {
		return maps;
	}

	public void setMaps(Map<String, Object> maps) {
		this.maps = maps;
	}

	public List<XtDict> getXtdict31() {
		return xtdict31;
	}

	public void setXtdict31(List<XtDict> xtdict31) {
		this.xtdict31 = xtdict31;
	}

	public List<XtDict> getXtdict36() {
		return xtdict36;
	}

	public void setXtdict36(List<XtDict> xtdict36) {
		this.xtdict36 = xtdict36;
	}

	public List<XtDict> getXtdict() {
		return xtdict;
	}

	public void setXtdict(List<XtDict> xtdict) {
		this.xtdict = xtdict;
	}

	public List<XtDict> getXtdictsel() {
		return xtdictsel;
	}

	public void setXtdictsel(List<XtDict> xtdictsel) {
		this.xtdictsel = xtdictsel;
	}

	public String[] getDmkey() {
		return dmkey;
	}

	public void setDmkey(String[] dmkey) {
		this.dmkey = dmkey;
	}

	public String[] getDmkey2() {
		return dmkey2;
	}

	public void setDmkey2(String[] dmkey2) {
		this.dmkey2 = dmkey2;
	}

	public List<Map<String, Object>> getQlist() {
		return qlist;
	}

	public void setQlist(List<Map<String, Object>> qlist) {
		this.qlist = qlist;
	}
	
}
