package com.group.zsxm.web.action;

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

import com.fins.gt.server.GridServerHandler;
import com.group.core.Constants;
import com.group.core.common.Message;
import com.group.core.common.Page;
import com.group.zsxm.entity.DmMc;
import com.group.zsxm.entity.Xtuser;
import com.group.zsxm.service.RcdaService;
import com.group.zsxm.service.RcdaxxwhService;
import com.group.zsxm.web.common.BaseAction;
import com.opensymphony.xwork2.Action;

@SuppressWarnings("unchecked")
@ParentPackage("appDefault")
@Scope("prototype")
@Results( { 
			@Result(name = "input", value = "/WEB-INF/jsp/rcda/rcdaXxwh.jsp"),
			@Result(name = "presh", value = "/WEB-INF/jsp/rcda/rcda_sh.jsp"),
			@Result(name = "view", value = "/WEB-INF/jsp/rcda/rcda_view.jsp")
		 })
public class RcdaxxwhAction extends BaseAction{
	private Xtuser xtuser;
	private Map<String,Object> maps;
	@Autowired
	@Qualifier("rcdaxxwhService")
	private RcdaxxwhService rcdaxxwhService;
	
	@Autowired
	@Qualifier("rcdaService")
	private RcdaService rcdaService;
	
	private String btgyy;
	
	private String rcid;
	public void prepare() {
		getRequest().setAttribute("STATUS", "111");
		xtuser = (Xtuser)getSession().getAttribute(Constants.USER_SESSION_KEY);
	}
	
	public String execute(){
		return Action.INPUT;
	}
	
	/**
	 * 判别人才信息是否修改过
	 */
	public void getBgbs(){
		Map<String, Object> mapOut = new HashMap();
		String bgbs = rcdaService.getBgbs( rcid );
		if(bgbs.equals("000000000000")){
			mapOut.put("bgbs", 0);
		}else{
			mapOut.put("bgbs", 1);
		}
		this.renderJson(mapOut);
	}
	
	public void doGetList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		getLimitPage().setRowAttributes(gridHandler.getTotalRowNum(), gridHandler.getPageNum(), gridHandler.getPageSize());
		Map param = getParameterSimpleMap();
		Page page = (Page) rcdaxxwhService.getListForRcdaByName(getLimitPage(), param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo());
		gridHandler.setTotalRowNum(page.getTotalCount());
		gridHandler.setData((List) page.getData());
		renderText(gridHandler.getLoadResponseText());
	}
	
	public void doExportList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		Map param = gridHandler.getGridRequestParameters();
		List list = (List) rcdaxxwhService.getListForRcdaByName(null, param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo());
		try {
			gridHandler.exportXLSfromMaps(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDeletercxx(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaxxwhService.doDeleteRcxx(rcid, null);
			message = new Message("1","删除成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preShrcxx(){
		maps = rcdaxxwhService.preShRcxx(rcid, null);
		return "presh";
	}
	
	public void doShxxtg(){
		Map<String, Object> mapOut = new HashMap();
		try{
			rcdaxxwhService.doShxxtg(rcid);
			message = new Message("1","操作成功！");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doShxxbtg(){ 
		Map<String, Object> mapOut = new HashMap();
		try{
			rcdaxxwhService.doShxxbtg(rcid,btgyy);
			message = new Message("1","操作成功！");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preView(){
		maps = rcdaxxwhService.preView(rcid);
		return "view";
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

	public Map<String, Object> getMaps() {
		return maps;
	}

	public void setMaps(Map<String, Object> maps) {
		this.maps = maps;
	}

	public String getBtgyy() {
		return btgyy;
	}

	public void setBtgyy(String btgyy) {
		this.btgyy = btgyy;
	}


	
}
