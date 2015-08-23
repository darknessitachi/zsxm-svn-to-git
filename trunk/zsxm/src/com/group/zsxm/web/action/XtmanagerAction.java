package com.group.zsxm.web.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fins.gt.server.GridServerHandler;
import com.group.core.Constants;
import com.group.core.common.Message;
import com.group.core.common.Page;
import com.group.zsxm.entity.Xtuser;
import com.group.zsxm.service.XtmanagerService;
import com.group.zsxm.web.common.BaseAction;

@ParentPackage("appDefault")
@Scope("prototype")
@Controller
@Results( { @Result(name = "index", value = "/WEB-INF/jsp/xtmanager/dictindex.jsp"),
    	    @Result(name = "addDict", value = "/WEB-INF/jsp/xtmanager/addDict.jsp"),
    	    @Result(name = "updateDict", value = "/WEB-INF/jsp/xtmanager/updateDict.jsp"),
    	    @Result(name = "userindex", value = "/WEB-INF/jsp/xtmanager/userindex.jsp"),
    	    @Result(name = "getUserEdit", value = "/WEB-INF/jsp/xtmanager/edituser.jsp"),
    	    @Result(name = "getupdatepassword", value = "/WEB-INF/jsp/xtmanager/updatepassword.jsp"),
    	    @Result(name = "getlog", value = "/WEB-INF/jsp/xtmanager/syslogindex.jsp"),
    	    @Result(name = "rclbIndex", value = "/WEB-INF/jsp/xtmanager/rclbindex.jsp"),
    	    @Result(name = "addRclb", value = "/WEB-INF/jsp/xtmanager/addRclb.jsp"),
    	    @Result(name = "updateRclb", value = "/WEB-INF/jsp/xtmanager/updateRclb.jsp")
    	
    	
	    })
public class XtmanagerAction extends BaseAction{
    	private Map<String,Object> listMap;
	
	private Map<String,String> query;
	
	private Map<String,String> om;
	
	private String id;
	
	private XtmanagerService xtmanagerService;
	
	private Xtuser xtuser;
	public void prepare() {
		xtuser = (Xtuser)getSession().getAttribute(Constants.USER_SESSION_KEY);
	} 
	public String execute() {
	    listMap = new HashMap<String,Object>();
	    listMap.put("lbList", xtmanagerService.getAllLb());
	    return "index";
	}
	
	@SuppressWarnings({ "unchecked", "static-access" })
	public void saveDict(){
	  		Message message = new Message();
	  		Map mapOut = new HashMap();
	  		message.setCode("1");
	  		message.setText("保存成功！");
	  		
	  		try {
	  		    	Map map = xtmanagerService.addDict(query);
	  		    	message.setMap(map);
	  		} catch (Exception e) {
	  		    	e.printStackTrace();
	  			message.setCode("-1");
	  			message.setText(e.getMessage());
	  		}
	  		mapOut.put("message",message);
	  		this.renderJson(mapOut);
	     }
	
	@SuppressWarnings({ "unchecked", "static-access" })
	public void updteDict(){
	  		Message message = new Message();
	  		Map mapOut = new HashMap();
	  		message.setCode("1");
	  		message.setText("保存成功！");
	  		
	  		try {
	  		    	 xtmanagerService.updateDict(query);
	  		} catch (Exception e) {
	  		    	e.printStackTrace();
	  			message.setCode("-1");
	  			message.setText(e.getMessage());
	  		}
	  		mapOut.put("message",message);
	  		this.renderJson(mapOut);
	     }
	
	@SuppressWarnings({ "unchecked", "static-access" })
	public void deleteDict(){
	  		Message message = new Message();
	  		Map mapOut = new HashMap();
	  		message.setCode("1");
	  		message.setText("删除成功！");
	  		
	  		try {
	  		    	 xtmanagerService.deletedict(query);
	  		} catch (Exception e) {
	  		    	e.printStackTrace();
	  			message.setCode("-1");
	  			message.setText(e.getMessage());
	  		}
	  		mapOut.put("message",message);
	  		this.renderJson(mapOut);
	     }
	
	public String userindex(){
	    return "userindex";
	}
	
	
	public void getAlluser() {
	    try{
			GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
			getLimitPage().setRowAttributes(gridHandler.getTotalRowNum(), gridHandler.getPageNum(), gridHandler.getPageSize());
			Map param = getParameterSimpleMap();
			Page page = (Page) xtmanagerService.getAllUser(getLimitPage(), param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo());
			gridHandler.setTotalRowNum(page.getTotalCount());
			gridHandler.setData((List) page.getData());
			renderText(gridHandler.getLoadResponseText());

	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	}
	
	public void getExportAlluser() {
	    try{
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		getLimitPage().setRowAttributes(gridHandler.getTotalRowNum(), gridHandler.getPageNum(), gridHandler.getPageSize());
		Map param = getParameterSimpleMap();
		List list = (List) xtmanagerService.getAllUser(null, param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo());
		try {
			gridHandler.exportXLSfromMaps(list);
		} catch (IOException e) {
			e.printStackTrace();
		}

	    }catch(Exception e){
		e.printStackTrace();
	    }
	}
	
	
	@SuppressWarnings({ "unchecked", "static-access" })
	public void deleteUser(){
	  		Message message = new Message();
	  		Map mapOut = new HashMap();
	  		message.setCode("1");
	  		message.setText("删除成功！");
	  		
	  		try {
	  		    	 xtmanagerService.deleteUser(query);
	  		} catch (Exception e) {
	  		    	e.printStackTrace();
	  			message.setCode("-1");
	  			message.setText(e.getMessage());
	  		}
	  		mapOut.put("message",message);
	  		this.renderJson(mapOut);
	     }
	@SuppressWarnings({ "unchecked", "static-access" })
	public void editUser(){
	  		Message message = new Message();
	  		Map mapOut = new HashMap();
	  		message.setCode("1");
	  		message.setText("保存成功！");
	  		
	  		try {
	  		    	boolean flag = xtmanagerService.checkLoginname(query);
	  		    	if(flag){
	  		    	    xtmanagerService.editUser(query);
	  		    	}else{
	  		    	    message.setCode("0");
	  		    	    message.setText("登录名称已经存在！");
	  		    	}
	  		} catch (Exception e) {
	  		    	e.printStackTrace();
	  			message.setCode("-1");
	  			message.setText(e.getMessage());
	  		}
	  		mapOut.put("message",message);
	  		this.renderJson(mapOut);
	     }

	
	public String getUserEdit(){
	    listMap = xtmanagerService.getUser(query);
	    listMap.put("role", xtmanagerService.getRole());
	    return "getUserEdit";
	}
	
	
	public String getAddDict(){
	    listMap = xtmanagerService.getAddDictData(query);
	    return "addDict";
	}
	
	public String getUpdateDict(){
	    listMap = xtmanagerService.getDictMap(query);
	    return "updateDict";
	}
	
	public void getDict() {
	    try{
		//dhtmlXtree，id可以为String
		StringBuffer sb = new StringBuffer();
		Map lbmap = xtmanagerService.getLbData(query);
		if (id==null) {
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sb.append("<tree id=\"0\">");
			sb.append("<item child=\"1\" open=\"1\"  text=\""+lbmap.get("LBNAME").toString().trim()+"\" id=\"root\" ></item>");
			sb.append("</tree>");
		}else{
		    	List<Map<String, Object>> ldws = xtmanagerService.getDicts(query.get("lbid"),id);
			sb.append("<tree id=\""+id+"\">");
			for (Map map : ldws) {
				String isChild = "1";
				if (map.get("leaf").toString().equals("0")) isChild = "0";
				sb.append("<item child=\""+isChild+"\"  text=\""+map.get("dictname").toString().trim()+"\" id=\""+ map.get("dictbh").toString().trim()+"\" ></item>");
			}
			/*
			for(int i=30;i<100;i++){
				sb.append("<item child=\"1\" text=\""+user.getDwdm()+"  "+i+"\" id=\""+i+"\" ><userdata name='userid'>"+i+"</userdata></item>");
			}
			*/
			sb.append("</tree>");

		}
		renderXml(sb.toString());
	    }catch(Exception e){
		e.printStackTrace();
	    }
	}
        
	@SuppressWarnings({ "unchecked", "static-access" })
	public void updtePassword(){
	  		Message message = new Message();
	  		Map mapOut = new HashMap();
	  		message.setCode("1");
	  		message.setText("保存成功！");
	  		
	  		try {
	  		
	  		    	Xtuser xtuser = (Xtuser) this.getSession().getAttribute(Constants.USER_SESSION_KEY);
	  		    	boolean flag = xtmanagerService.checkPassword(xtuser.getLoginname(), query.get("oldpassword"));
	  		    	if(flag){
	  		    	    xtmanagerService.updatePassword(  xtuser.getLoginname(),query.get("newpassword"));
	  		    	}else{
	  		    	    message.setCode("-1");
	  		    	    message.setText("原始密码不正确！");
	  		    	}
	  		} catch (Exception e) {
	  		    	e.printStackTrace();
	  			message.setCode("-1");
	  			message.setText(e.getMessage());
	  		}
	  		mapOut.put("message",message);
	  		this.renderJson(mapOut);
	     }
	           
	public void getAllLog() {
	    try{
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		getLimitPage().setRowAttributes(gridHandler.getTotalRowNum(), gridHandler.getPageNum(), gridHandler.getPageSize());
		Map param = getParameterSimpleMap();
		Page page = (Page) xtmanagerService.getAllLog(getLimitPage(), param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo(),xtuser);
		gridHandler.setTotalRowNum(page.getTotalCount());
		gridHandler.setData((List) page.getData());
		renderText(gridHandler.getLoadResponseText());

	    }catch(Exception e){
		e.printStackTrace();
	    }
	}
	
	public void getExportAllLog() {
	    try{
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		getLimitPage().setRowAttributes(gridHandler.getTotalRowNum(), gridHandler.getPageNum(), gridHandler.getPageSize());
		Map param = getParameterSimpleMap();
		List list = (List) xtmanagerService.getAllLog(null, param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo(),xtuser);
		try {
			gridHandler.exportXLSfromMaps(list);
		} catch (IOException e) {
			e.printStackTrace();
		}

	    }catch(Exception e){
		e.printStackTrace();
	    }
	}
	
	
	public String getlog() {
	  return "getlog";
	}
	
	public String getupdatepassword(){
	    return "getupdatepassword";
	}
	
	public String rclbIndex(){
	    return "rclbIndex";
	}
	
	public void getRclbTree(){
	    try{
	    	StringBuffer sb = new StringBuffer();
		if (id==null) {
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sb.append("<tree id=\"0\">");
			sb.append("<item child=\"1\" open=\"1\"  text=\"人才类别\" id=\"root\" ></item>");
			sb.append("</tree>");
		}else{
		    	List<Map<String, Object>> ldws = xtmanagerService.getRclbTree(id);
			sb.append("<tree id=\""+id+"\">");
			for (Map map : ldws) {
				String isChild = "1";
				if (map.get("leaf").toString().equals("0")) isChild = "0";
				sb.append("<item child=\""+isChild+"\"  text=\""+map.get("lbmc").toString().trim()+"\" id=\""+ map.get("lbdm").toString().trim()+"\" ></item>");
			}
			/*
			for(int i=30;i<100;i++){
				sb.append("<item child=\"1\" text=\""+user.getDwdm()+"  "+i+"\" id=\""+i+"\" ><userdata name='userid'>"+i+"</userdata></item>");
			}
			*/
			sb.append("</tree>");
		}
		renderXml(sb.toString());
	    }catch(Exception e){
		e.printStackTrace();
	    }
	}
		
	public String getAddRclb(){
	    	    listMap = xtmanagerService.getAddRclbData(query);
		    return "addRclb";
	}
		
	public String getUpdateRclb(){
		    listMap = xtmanagerService.getRclbMap(query);
		    return "updateRclb";
	}
	
	public void saveRclb(){
		Message message = new Message();
		Map mapOut = new HashMap();
		message.setCode("1");
		message.setText("保存成功！");
		
		try {
		    	Map map = xtmanagerService.addRclb(query);
		    	message.setMap(map);
		} catch (Exception e) {
		    	e.printStackTrace();
			message.setCode("-1");
			message.setText(e.getMessage());
		}
		mapOut.put("message",message);
		this.renderJson(mapOut);
	}
	
	@SuppressWarnings({ "unchecked", "static-access" })
	public void updteRclb(){
		Message message = new Message();
		Map mapOut = new HashMap();
		message.setCode("1");
		message.setText("保存成功！");
		
		try {
		    	 xtmanagerService.updateRclb(query);
		} catch (Exception e) {
		    	e.printStackTrace();
			message.setCode("-1");
			message.setText(e.getMessage());
		}
		mapOut.put("message",message);
		this.renderJson(mapOut);
 }
	@SuppressWarnings({ "unchecked", "static-access" })
	public void deleteRclb(){
	  		Message message = new Message();
	  		Map mapOut = new HashMap();
	  		message.setCode("1");
	  		message.setText("删除成功！");
	  		
	  		try {
	  		    	 xtmanagerService.deleteRclb(query);
	  		} catch (Exception e) {
	  		    	e.printStackTrace();
	  			message.setCode("-1");
	  			message.setText(e.getMessage());
	  		}
	  		mapOut.put("message",message);
	  		this.renderJson(mapOut);
	     }
	
	public Map<String, Object> getListMap() {
	    return listMap;
	}

	public void setListMap(Map<String, Object> listMap) {
	    this.listMap = listMap;
	}

	public Map<String, String> getQuery() {
	    return query;
	}

	public void setQuery(Map<String, String> query) {
	    this.query = query;
	}

	public Map<String, String> getOm() {
	    return om;
	}

	public void setOm(Map<String, String> om) {
	    this.om = om;
	}

	public XtmanagerService getXtmanagerService() {
	    return xtmanagerService;
	}

	public void setXtmanagerService(XtmanagerService xtmanagerService) {
	    this.xtmanagerService = xtmanagerService;
	}

	public String getId() {
	    return id;
	}

	public void setId(String id) {
	    this.id = id;
	}

	


}
