package com.netwander.explib.web.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.netwander.core.Constants;
import com.netwander.core.common.TreeBean;
import com.netwander.core.common.TreeFactory;
import com.netwander.explib.entity.DmMc;
import com.netwander.explib.entity.Xtuser;
import com.netwander.explib.service.SearchService;
import com.netwander.explib.web.common.BaseAction;
import com.opensymphony.xwork2.Action;

@ParentPackage("appDefault")
@Scope("prototype")
@Controller
@Results( { 
	@Result(name = "input", value = "/WEB-INF/pages/search/search.jsp")
})
public class SearchAction  extends BaseAction{
	@Autowired
	@Qualifier("searchService")
	private SearchService searchService;
	
	private List<DmMc> sfields ;
	
	private String lbid;
	private Xtuser xtuser;
	public void onPrepare() {
		xtuser = (Xtuser)getSession().getAttribute(Constants.USER_SESSION_KEY);
	}
	
	public String execute(){
		sfields = ArrayToList(new String[]{"rcname","sex","xl","xw","jg","szdq","pjcs_","ztpj_"},
				new String[]{"姓名","性别","学历","学位","籍贯","所在地区","聘请次数","总体评价"});
		return Action.INPUT;
	}
	
	public void doSave(){
		
	}
	
	public void getQueryTree(){
		List<TreeBean> treeBeans = new ArrayList();
		treeBeans = searchService.getDictListWithTree(Integer.parseInt(lbid));
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
	public List<DmMc> getSfields() {
		return sfields;
	}

	public void setSfields(List<DmMc> sfields) {
		this.sfields = sfields;
	}

	public String getLbid() {
		return lbid;
	}

	public void setLbid(String lbid) {
		this.lbid = lbid;
	}
}
