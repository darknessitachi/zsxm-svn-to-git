package com.group.zsxm.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.group.core.Constants;
import com.group.zsxm.entity.Xtuser;
import com.group.zsxm.service.GzService;
import com.group.zsxm.service.XxglService;
import com.group.zsxm.web.common.BaseAction;

@ParentPackage("appDefault")
@Scope("prototype")
@Controller
@Results( { @Result(name = "index", value = "/WEB-INF/jsp/rcgz/query.jsp"),
			@Result(name = "gzdetail", value = "/WEB-INF/jsp/rcgz/gz_detail.jsp")
			})
public class GzAction extends BaseAction{

	/**信息撰稿
	 * xufeng
	 * 2009.09.26
	 */
	private static final long serialVersionUID = 7792435474087396405L;
	
	private Xtuser xtuser;
	
	private Map<String,String> parMap;
	
	private String rcid;
	
	private String bz;
    
    @Autowired
	@Qualifier("gzService")
	private GzService gzService;
	
	private List<Map<String,String>> outList;
	
	public void onPrepare(){
		xtuser = (Xtuser)getSession().getAttribute(Constants.USER_SESSION_KEY);
	} 
	

	public String execute() {
	    return "index";
	}
	
	public String doGzDetail() {
	    return "gzdetail";
	}
	
	@SuppressWarnings("unchecked")
	public void doSaveKc(){
		Map m = new HashMap();		
    	try {
    		m.put("kcid", gzService.saveKc(parMap));
    		m.put("text", "考察跟踪保存成功！");
       		m.put("code", "1");
		} catch (Exception e) {
			m.put("code", "-1");
			m.put("text", "保存失败，导致的原因："+e.getMessage());
		}
		renderText(JSONObject.fromMap(m).toString());
		
	}
	
	@SuppressWarnings("unchecked")
	public void doSavePx(){
		Map m = new HashMap();		
    	try {
    		m.put("pxid", gzService.savePx(parMap));
    		m.put("text", "培训跟踪保存成功！");
       		m.put("code", "1");
		} catch (Exception e) {
			m.put("code", "-1");
			m.put("text", "保存失败，导致的原因："+e.getMessage());
		}
		renderText(JSONObject.fromMap(m).toString());
		
	}
	
	
	@SuppressWarnings("unchecked")
	public void doLoadGz(){
		Map m = new HashMap();
		if(bz.equals("px")){
			m.put("list",gzService.loadPx(rcid));
		}
		if(bz.equals("kc")){
			m.put("list",gzService.loadKc(rcid));
		}
		
		renderJson(JSONObject.fromMap(m).toString());
		
	}

	public List<Map<String, String>> getOutList() {
		return outList;
	}

	public void setOutList(List<Map<String, String>> outList) {
		this.outList = outList;
	}

	public Map<String, String> getParMap() {
		return parMap;
	}

	public void setParMap(Map<String, String> parMap) {
		this.parMap = parMap;
	}

	public Xtuser getXtuser() {
		return xtuser;
	}

	public void setXtuser(Xtuser xtuser) {
		this.xtuser = xtuser;
	}


	public String getRcid() {
		return rcid;
	}


	public void setRcid(String rcid) {
		this.rcid = rcid;
	}


	public String getBz() {
		return bz;
	}


	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
