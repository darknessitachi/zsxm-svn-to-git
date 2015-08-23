package com.netwander.explib.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.netwander.core.Constants;
import com.netwander.core.common.TreeBean;
import com.netwander.core.common.TreeFactory;
import com.netwander.explib.entity.Xtuser;
import com.netwander.explib.service.SystemService;
import com.netwander.explib.web.common.BaseAction;

@ParentPackage("appDefault")
@Scope("prototype")
@Controller
@Results( { 
	
})
public class SysAction  extends BaseAction {
	@Autowired
	@Qualifier("systemService")
	private SystemService systemService;
	
	private List<Map<String, Object>> qlist;
	private String lbid;
	
	private Xtuser xtuser;
	public void onPrepare() {
		xtuser = (Xtuser)getSession().getAttribute(Constants.USER_SESSION_KEY);
	}
	
	public void getSearchField(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		qlist = systemService.getSearchFieldList();
		mapOut.put("data", qlist);
		this.renderJson(mapOut);
	}
	
	public void getChangeSel(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		qlist = systemService.getChangeSel(xtuser.getUserid(), xtuser.getUserfl(),lbid );
		mapOut.put("data", qlist);
		this.renderJson(mapOut);
	}
	
	public void getChangeTree(){
		List<TreeBean> treeBeans = systemService.getChangeTree(xtuser.getUserid(), xtuser.getUserfl(),lbid );
		TreeFactory tf = new TreeFactory();	
		tf.setPdm("0");
		tf.setIsDisabledCheck(false);
		tf.setRootText("选择数据");
		renderText(tf.create(treeBeans));
	}
	
	/**
	 * Mapping
	 * @return
	 */
	public List<Map<String, Object>> getQlist() {
		return qlist;
	}

	public void setQlist(List<Map<String, Object>> qlist) {
		this.qlist = qlist;
	}

	public String getLbid() {
		return lbid;
	}

	public void setLbid(String lbid) {
		this.lbid = lbid;
	}
	
	
}
