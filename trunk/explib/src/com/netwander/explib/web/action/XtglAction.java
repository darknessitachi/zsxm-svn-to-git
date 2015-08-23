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
import com.netwander.core.common.Message;
import com.netwander.core.common.Page;
import com.netwander.explib.entity.XtDict;
import com.netwander.core.Constants;
import com.netwander.explib.entity.Xtuser;
import com.netwander.explib.service.ExpService;
import com.netwander.explib.service.XtglService;
import com.netwander.explib.web.common.BaseAction;

@ParentPackage("appDefault")
@Scope("prototype")
@Controller
@Results( { @Result(name = "comdata", value = "/WEB-INF/pages/xtgl/dictindex.jsp"),
			@Result(name = "comdatafl", value = "/WEB-INF/pages/xtgl/dictflindex.jsp"),
    	    @Result(name = "addDict", value = "/WEB-INF/pages/xtgl/addDict.jsp"),
    	    @Result(name = "updateDict", value = "/WEB-INF/pages/xtgl/updateDict.jsp"),
    	    @Result(name = "addDictfl", value = "/WEB-INF/pages/xtgl/addDictfl.jsp"),
    	    @Result(name = "updateDictfl", value = "/WEB-INF/pages/xtgl/updateDictfl.jsp"),
    	    @Result(name = "userindex", value = "/WEB-INF/pages/xtgl/userindex.jsp"),
    	    @Result(name = "getUserEdit", value = "/WEB-INF/pages/xtgl/edituser.jsp"),
    	    @Result(name = "getupdatepassword", value = "/WEB-INF/pages/xtgl/updatepassword.jsp"),
    	    @Result(name = "updatepasszj", value = "/WEB-INF/pages/xtgl/updatepassword_zj.jsp"),
    	    @Result(name = "getlog", value = "/WEB-INF/pages/xtgl/syslogindex.jsp"),
    	    @Result(name = "rclbIndex", value = "/WEB-INF/pages/xtgl/rclbindex.jsp"),
    	    @Result(name = "addRclb", value = "/WEB-INF/pages/xtgl/addRclb.jsp"),
    	    @Result(name = "updateRclb", value = "/WEB-INF/pages/xtgl/updateRclb.jsp"),
    	    @Result(name = "dlbfldm", value = "/WEB-INF/pages/xtgl/dlb_fldm.jsp")
	    })
public class XtglAction extends BaseAction{
	@Autowired
	@Qualifier("xtglService")
	private XtglService xtglService;
	
	@Autowired
	@Qualifier("expService")
	private ExpService expService;
	
	private Map<String,Object> listMap;
	
	private Map<String,String> query;
	
	private Map<String,String> om;
	private String fldm;
	
	private String id;
	
	private List<XtDict> xtdict;//市属部门,,主管部门
	private String[] nl1;
	private String[] nl2;
	private String[] bz;
	private String[] dmkey;
	
	private List<Map<String, Object>> qlist;
	private List<Map<String, Object>> flqlist;
	
	private Xtuser xtuser;
	public void onPrepare() {
		xtuser = (Xtuser)getSession().getAttribute(Constants.USER_SESSION_KEY);
	} 
	
	
	public String comdata() {
	    listMap = new HashMap<String,Object>();
	    listMap.put("lbList", xtglService.getAllLb());
	    return "comdata";
	}
	
	
	public String comdatafl() {
	    listMap = new HashMap<String,Object>();
	    List<Map<String,Object>> list = xtglService.getExpFl(xtuser.getRoledm(), xtuser.getUserid());
	    listMap.put("flList",list );
	    //listMap.put("lbList", xtglService.getFlLb(String.valueOf(list.get(0).get("fldm"))));
	    return "comdatafl";
	}
	
	public void getLbidByFldm(){
		Map mapOut = new HashMap();
		List<Map<String,Object>> list = xtglService.getFlLb(fldm);
		mapOut.put("data",list);
  		this.renderJson(mapOut);
	}
	
	@SuppressWarnings({ "unchecked", "static-access" })
	public void saveDict(){
	  		Message message = new Message();
	  		Map mapOut = new HashMap();
	  		message.setCode("1");
	  		message.setText("保存成功！");
	  		
	  		try {
	  		    	Map map = xtglService.addDict(query);
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
	  			xtglService.updateDict(query);
	  		} catch (Exception e) {
	  		    	e.printStackTrace();
	  			message.setCode("-1");
	  			message.setText(e.getMessage());
	  		}
	  		mapOut.put("message",message);
	  		this.renderJson(mapOut);
	     }
	

	@SuppressWarnings({ "unchecked", "static-access" })
	public void saveDictfl(){
	  		Message message = new Message();
	  		Map mapOut = new HashMap();
	  		message.setCode("1");
	  		message.setText("保存成功！");
	  		
	  		try {
	  		    	Map map = xtglService.addDictfl(query);
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
	public void updteDictfl(){
	  		Message message = new Message();
	  		Map mapOut = new HashMap();
	  		message.setCode("1");
	  		message.setText("保存成功！");
	  		
	  		try {
	  			xtglService.updateDictfl(query);
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
	  			xtglService.deletedict(query);
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
			Page page = (Page) xtglService.getAllUser(getLimitPage(), param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo());
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
		List list = (List) xtglService.getAllUser(null, param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo());
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
	  		    	 xtglService.deleteUser(query);
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
	  		    	boolean flag = xtglService.checkLoginname(query);
	  		    	if(flag){
	  		    	    xtglService.editUser(query);
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
	    query = xtglService.getUser(query);
	    listMap = new HashMap();
	    listMap.put("role", xtglService.getRole());
	    return "getUserEdit";
	}
	
	
	public String getAddDict(){
		listMap = xtglService.getAddDictData(query);
		return "addDict";
	}
	
	

	public String getUpdateDict(){
	    listMap = xtglService.getDictMap(query);
	    return "updateDict";
	}
	
	public String getAddDictfl(){
		listMap = xtglService.getAddDictDatafl(query);
		return "addDictfl";
	}
	
	public String getUpdateDictfl(){
	    listMap = xtglService.getDictflMap(query);
	    return "updateDictfl";
	}
	@SuppressWarnings({ "unchecked", "static-access" })
	public void deleteDictfl(){
	  		Message message = new Message();
	  		Map mapOut = new HashMap();
	  		message.setCode("1");
	  		message.setText("删除成功！");
	  		
	  		try {
	  			xtglService.deletedictfl(query);
	  		} catch (Exception e) {
	  		    	e.printStackTrace();
	  			message.setCode("-1");
	  			message.setText(e.getMessage());
	  		}
	  		mapOut.put("message",message);
	  		this.renderJson(mapOut);
	     }
	public void getDict() {
	    try{
		//dhtmlXtree，id可以为String
		StringBuffer sb = new StringBuffer();
		Map lbmap = xtglService.getLbData(query);
		if (id==null) {
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sb.append("<tree id=\"0\">");
			sb.append("<item child=\"1\" open=\"1\"  text=\""+lbmap.get("LBNAME").toString().trim()+"\" id=\"root\" ></item>");
			sb.append("</tree>");
		}else{
		    	List<Map<String, Object>> ldws = xtglService.getDicts(query.get("lbid"),id);
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
        

	public void getDictfl() {
	    try{
		//dhtmlXtree，id可以为String
		StringBuffer sb = new StringBuffer();
		Map lbmap = xtglService.getLbData(query);
		if (id==null) {
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sb.append("<tree id=\"0\">");
			sb.append("<item child=\"1\" open=\"1\"  text=\""+lbmap.get("LBNAME").toString().trim()+"\" id=\"root\" ></item>");
			sb.append("</tree>");
		}else{
		    	List<Map<String, Object>> ldws = xtglService.getDictfls(query.get("lbid"),id,fldm);
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
	  		    	boolean flag = xtglService.checkPassword(xtuser.getLoginname(), query.get("oldpassword"),xtuser.getLoginbz());
	  		    	if(flag){
	  		    	    xtglService.updatePassword(  xtuser.getLoginname(),query.get("newpassword"),xtuser.getLoginbz());
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
		Page page = (Page) xtglService.getAllLog(getLimitPage(), param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo(),xtuser);
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
		List list = (List) xtglService.getAllLog(null, param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo(),xtuser);
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
		    	List<Map<String, Object>> ldws = xtglService.getRclbTree(id);
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
	    	    listMap = xtglService.getAddRclbData(query);
		    return "addRclb";
	}
		
	public String getUpdateRclb(){
		    listMap = xtglService.getRclbMap(query);
		    return "updateRclb";
	}
	
	public void saveRclb(){
		Message message = new Message();
		Map mapOut = new HashMap();
		message.setCode("1");
		message.setText("保存成功！");
		
		try {
		    	Map map = xtglService.addRclb(query);
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
		    	 xtglService.updateRclb(query);
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
	  		    	 xtglService.deleteRclb(query);
	  		} catch (Exception e) {
	  		    	e.printStackTrace();
	  			message.setCode("-1");
	  			message.setText(e.getMessage());
	  		}
	  		mapOut.put("message",message);
	  		this.renderJson(mapOut);
	     }
	
	
	public void getBmdmList(){
		Map mapOut = new HashMap();
		
		mapOut.put("info",xtdict);
  		this.renderJson(mapOut);
	}
	
	
	public void getNlfwList(){
		Map mapOut = new HashMap();
		mapOut.put("info",expService.getNlfwList());
  		this.renderJson(mapOut);
	}
	
	public void doSaveNlfw(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			expService.doSaveNlfw(nl1, nl2, bz);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	
	public String preDlbFldm(){
		qlist = xtglService.getAllLb();
		flqlist = xtglService.getFlLb(fldm);
		return "dlbfldm";
	}
	
	public void doSaveDlbFldm(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			expService.doSaveDlbFldm(fldm, dmkey);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String updatepasswordzj(){
		return "updatepasszj";
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<XtDict> getXtdict() {
		return xtdict;
	}

	public void setXtdict(List<XtDict> xtdict) {
		this.xtdict = xtdict;
	}

	public String[] getNl1() {
		return nl1;
	}

	public void setNl1(String[] nl1) {
		this.nl1 = nl1;
	}

	public String[] getNl2() {
		return nl2;
	}

	public void setNl2(String[] nl2) {
		this.nl2 = nl2;
	}

	public String[] getBz() {
		return bz;
	}

	public void setBz(String[] bz) {
		this.bz = bz;
	}


	public String getFldm() {
		return fldm;
	}


	public void setFldm(String fldm) {
		this.fldm = fldm;
	}


	public String[] getDmkey() {
		return dmkey;
	}


	public void setDmkey(String[] dmkey) {
		this.dmkey = dmkey;
	}


	public List<Map<String, Object>> getQlist() {
		return qlist;
	}


	public void setQlist(List<Map<String, Object>> qlist) {
		this.qlist = qlist;
	}


	public List<Map<String, Object>> getFlqlist() {
		return flqlist;
	}


	public void setFlqlist(List<Map<String, Object>> flqlist) {
		this.flqlist = flqlist;
	}
	
	
}
