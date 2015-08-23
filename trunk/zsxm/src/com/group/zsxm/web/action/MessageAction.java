package com.group.zsxm.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.group.core.Constants;
import com.group.core.common.Message;
import com.group.zsxm.entity.Xtuser;
import com.group.zsxm.service.MessageService;
import com.group.zsxm.web.common.BaseAction;

@ParentPackage("appDefault")
@Scope("prototype")
@Controller
@Results( { @Result(name = "index", value = "/WEB-INF/jsp/message/messageindex.jsp"),
	    @Result(name = "sendMessage", value = "/WEB-INF/jsp/message/messageEdit.jsp"),
	    @Result(name = "userTree", value = "/WEB-INF/jsp/message/userTree.jsp"),
	    @Result(name = "showMessage", value = "/WEB-INF/jsp/message/showMessage.jsp")
	    })
public class MessageAction extends BaseAction{
    
    private Map<String,Object> listMap;
	
    private List<Map<String, String>> userlist;
	
    private Map<String,String> query;
    private Map<String,String> parMap;
	private Map<String,String> om;
	
	private MessageService messageService;
	
	private String id ;
	private String mid;
	public String getId() {
	    return id;
	}

	public void setId(String id) {
	    this.id = id;
	}
	private Xtuser xtuser;
	public void prepare() {
		xtuser = (Xtuser)getSession().getAttribute(Constants.USER_SESSION_KEY);
	} 
	
	public String execute() {
	    if(query == null)
		query = new HashMap<String,String>();
	    
	    Xtuser xtuser = (Xtuser)this.getSession().getAttribute(Constants.USER_SESSION_KEY);
	    query.put("mtodm", xtuser.getLoginname());
	    return "index";
	}
	
	public void getmessage() {
	    try{
	          renderJson(messageService.getMessagePage(getLimitPage(), query));
	    }catch(Exception e){
		e.printStackTrace();
	    }
	}
	
	public void getNextMessage() {
	    try{
	          renderJson(messageService.getMessage(query.get("userdm")));
	    }catch(Exception e){
		e.printStackTrace();
	    }
	}
	
	public void getMessageSize() {
	    try{
	          renderJson(messageService.getMessageSize(query.get("userdm")));
	    }catch(Exception e){
		e.printStackTrace();
	    }
	}
	public void getsendmessage() {
	    try{
	          renderJson(messageService.getSendMessagePage(getLimitPage(), query));
	    }catch(Exception e){
		e.printStackTrace();
	    }
	}
	
	
	public void doRemoveMessage(){
		Map mapOut = new HashMap();
		message = new Message();
		try{
			messageService.doRemoveMessage(mid);
			message.setCode("1");
	  		message.setText("信件已移至成功！");
		}catch(Exception e){
			message.setCode("-1");
  			message.setText(e.getMessage());
		}
		mapOut.put("message",message);
  		this.renderJson(mapOut);
	}
	
	public void getremovemessage() {
	    try{
	          renderJson(messageService.getZyMessagePage(getLimitPage(), query));
	    }catch(Exception e){
		e.printStackTrace();
	    }
	}
	@SuppressWarnings({ "unchecked", "static-access" })
	public void deleteMessage(){
	  		Message message = new Message();
	  		Map mapOut = new HashMap();
	  		message.setCode("1");
	  		message.setText("短信删除成功！");
	  		
	  		try {
	  		    	messageService.deleteMessage(query.get("strid"),query.get("userdm"));		    	
	  		} catch (Exception e) {
	  		    	e.printStackTrace();
	  			message.setCode("-1");
	  			message.setText(e.getMessage());
	  		}
	  		mapOut.put("message",message);
	  		this.renderJson(mapOut);
	     }
	
	@SuppressWarnings({ "unchecked", "static-access" })
	public void saveMessage(){
	  		Message message = new Message();
	  		Map mapOut = new HashMap();
	  		message.setCode("1");
	  		message.setText("短信保存成功！");
	  		String realPath = ServletActionContext.getRequest().getRealPath("/upload");
	  		try {
	  		    	int id = messageService.doAddMessage(query,parMap,realPath);
	  		    	message.setText(String.valueOf(id));
	  		} catch (Exception e) {
	  		    	e.printStackTrace();
	  			message.setCode("-1");
	  			message.setText(e.getMessage());
	  		}
	  		mapOut.put("message",message);
	  		this.renderJson(mapOut);
	     }
	
	
	public String sendMessage(){
	    query = new HashMap();
	    userlist = messageService.getAllUser();
	    Xtuser xtuser = (Xtuser)this.getSession().getAttribute(Constants.USER_SESSION_KEY);
	    query.put("mfromdm", xtuser.getLoginname());
	    query.put("mfromname", xtuser.getCnname());
	    return "sendMessage";
	}
	
	
	public void getUser(){
		Map mapOut = new HashMap();
		userlist = messageService.getUser(query);
		mapOut.put("user",userlist);
		this.renderJson(mapOut);
	}
	
	public String showMessage(){
	    
	    if(query.get("messageid") != null){
		  listMap = messageService.getMessage(Long.valueOf(query.get("messageid")),query.get("userdm"));
	    }else{
		  listMap = messageService.getMessage(query.get("userdm"));
	    }
	    listMap.put("fj", String.valueOf(messageService.getXxfj(Long.valueOf(query.get("messageid")))));
	    return "showMessage";
	}
    @SuppressWarnings("unchecked")
	public void doShowFj(){
    	Map m = new HashMap();
    	m.put("fjList", messageService.queryFj(parMap.get("xxid").toString()));
		renderText(JSONObject.fromMap(m).toString());
    }
	@SuppressWarnings("unchecked")
	public String userTree(){
	    return "userTree";
	}
	
	public void getUserTree() {
	    try{
		//dhtmlXtree，id可以为String
		StringBuffer sb = new StringBuffer();
		
		if(id == null){
        		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        		sb.append("<tree id=\"0\">");
        		sb.append("<item child=\"1\" open=\"1\"  text=\"工作人员\" id=\"#1#\" ></item>");
        		/**
        		if(xtuser.getLoginbz() ==1 ){
        			sb.append("<item child=\"1\" text=\"人才\" id=\"#2#\" ></item>");
        		}*/
        		sb.append("</tree>");
	    	}else{
	    	    if(id.equals("#1#")){
	    		List<Map<String,String>> w = messageService.getAllUser();
			sb.append("<tree id=\"#1#\">");
				for (Map map : w) {
					sb.append("<item child=\"0\"  text=\""+map.get("USERMC").toString().trim()+"\" id=\""+ map.get("USERDM").toString().trim()+"\" ></item>");
				}
			sb.append("</tree>");	
	    	    }else{
	    	    	/*
	    	    	if(xtuser.getLoginbz()==1){
	    	    		List<Map<String,Object>> rc = messageService.getRcUser();
	    	    		sb.append("<tree id=\"#2#\">");
	            	    		for (Map map : rc) {
	            				sb.append("<item child=\"0\"  text=\""+map.get("USERMC").toString().trim()+"\" id=\""+ map.get("USERDM").toString().trim()+"\" ></item>");
	            			}
	            	    sb.append("</tree>");	
	    	    	}
	    		*/
	    	    }
	    	}
	
	

		renderXml(sb.toString());
	    }catch(Exception e){
		e.printStackTrace();
	    }
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

	public MessageService getMessageService() {
	    return messageService;
	}

	public void setMessageService(MessageService messageService) {
	    this.messageService = messageService;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public List<Map<String, String>> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<Map<String, String>> userlist) {
		this.userlist = userlist;
	}

	public Map<String, String> getParMap() {
		return parMap;
	}

	public void setParMap(Map<String, String> parMap) {
		this.parMap = parMap;
	}
    
}
