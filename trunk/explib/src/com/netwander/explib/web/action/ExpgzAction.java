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
import com.netwander.core.common.Message;
import com.netwander.core.common.Page;
import com.netwander.core.common.TreeBean;
import com.netwander.core.common.TreeFactory;
import com.netwander.explib.entity.XtDict;
import com.netwander.core.Constants;
import com.netwander.explib.entity.Xtuser;
import com.netwander.explib.service.ExpService;
import com.netwander.explib.service.ExpgzService;
import com.netwander.explib.service.ExpwhService;
import com.netwander.explib.web.common.BaseAction;

@ParentPackage("appDefault")
@Scope("prototype")
@Controller
@Results( { 
	@Result(name = "gzwh", value = "/WEB-INF/pages/expgz/expgzwh.jsp"),
	@Result(name = "gzwhzj", value = "/WEB-INF/pages/expgz/expgzwh_zj.jsp"),
	@Result(name = "gzi", value = "/WEB-INF/pages/expgz/expgzwh_d.jsp"),
	@Result(name = "gzu", value = "/WEB-INF/pages/expgz/expgzwhrep_d.jsp"),
	@Result(name = "gzuzj", value = "/WEB-INF/pages/expgz/expgzwhrep_zj_d.jsp"),
	@Result(name = "gzgl", value = "/WEB-INF/pages/expgz/expgzgl.jsp"),
	@Result(name = "addDict", value = "/WEB-INF/pages/expgz/expgzgl_d.jsp"),
	@Result(name = "updateDict", value = "/WEB-INF/pages/expgz/expgzgl_d_e.jsp")
	})
public class ExpgzAction extends BaseAction{
	@Autowired
	@Qualifier("expgzService")
	private ExpgzService expgzService;
	
	@Autowired
	@Qualifier("expService")
	private ExpService expService;
	
	@Autowired
	@Qualifier("expwhService")
	private ExpwhService expwhService;
	
	
	private Map<String,String> parMap;
	private Map<String,String> wtMap; 
	private String rcid;
	private String kcid;
	private String wtkcid;
	private String gzid;
	private String bz;
    
	private Integer type;//1：专家评价 2:委托评价
	private List<XtDict> xtdict29;//
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
	private List<XtDict> xtdict35;//
	
	private List<Map<String,String>> outList;
	private Map<String,String> query;
	private Map<String,Object> listMap;
	private Xtuser xtuser;
	public void onPrepare() {
		xtuser = (Xtuser)getSession().getAttribute(Constants.USER_SESSION_KEY);
	}
	
	public String gzwh() {
		type = 2;
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
	    return "gzwh";
	}

	public String gzwhzj() {
		type = 2;
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
	    return "gzwhzj";
	}
	

	public void doGetListZj() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		getLimitPage().setRowAttributes(gridHandler.getTotalRowNum(), gridHandler.getPageNum(), gridHandler.getPageSize());
		Map param = getParameterSimpleMap();
		param.put("zjqbz", "1");
		Page page = (Page) expwhService.getListForRcdaByName(getLimitPage(), param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo(),xtuser);
		gridHandler.setTotalRowNum(page.getTotalCount());
		gridHandler.setData((List) page.getData());
		renderText(gridHandler.getLoadResponseText());
	}
	
	public void doExportListZj() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		Map param = gridHandler.getGridRequestParameters();
		List list = (List) expwhService.getListForRcdaByName(null, param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo(),xtuser);
		try {
			gridHandler.exportXLSfromMaps(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String gzgl(){
		return "gzgl";
	}
	
	public String preGzI() {
		xtdict29 = expService.getDictListWithRcgz();
		xtdict35 = expService.getDictListWithSelect(35);
	    return "gzi";
	}

	public void getGzTree(){
		List<TreeBean> treeBeans = expgzService.getGzTree(rcid,null,type);
		TreeFactory tf = new TreeFactory();	
		tf.setPdm("0");
		tf.setIsDisabledCheck(false);
		tf.setRootText("选择跟踪");
		renderText(tf.create(treeBeans));
	}
	public void getGzTreeZj(){
		List<TreeBean> treeBeans = expgzService.getGzTreeZj(rcid,null);
		TreeFactory tf = new TreeFactory();	
		tf.setPdm("0");
		tf.setIsDisabledCheck(false);
		tf.setRootText("选择跟踪");
		renderText(tf.create(treeBeans));
	}
	public void getGzkcByKcid(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		parMap = expgzService.getGzkcByKcid(gzid);
		mapOut.put("message", parMap);
		this.renderJson(mapOut);
	}
	
	public void getGzByGzid(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		parMap = expgzService.getGzByGzid(gzid);
		mapOut.put("message", parMap);
		this.renderJson(mapOut);
	}
	
	public void doGzI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			expgzService.doGzI(rcid, parMap,wtMap);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preGzU(){
		xtdict29 = expService.getDictListWithRcgz();
		xtdict35 = expService.getDictListWithSelect(35);
		return "gzu";
	}
	public String preGzUZj(){
		xtdict29 = expService.getDictListWithRcgz();
		xtdict35 = expService.getDictListWithSelect(35);
		return "gzuzj";
	}
	
	public void doGzU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			expgzService.doGzU(rcid, kcid, parMap , wtkcid,wtMap);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doKcD(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			expgzService.doKcD(kcid);
			message = new Message("1","删除成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doGzD(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			expgzService.doGzD(gzid);
			message = new Message("1","删除成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	
	
public void getDict() {
	    
		List<TreeBean> treeBeans = expgzService.getDicts();
		TreeFactory tf = new TreeFactory();	
		tf.setPdm("0");
		tf.setIsDisabledCheck(false);
		tf.setRootText("跟踪管理维护");
		renderText(tf.create(treeBeans));
		
	}
	
	
	public String getAddDict(){
		return "addDict";
	}
	
	public void saveDict(){
  		Message message = new Message();
  		Map mapOut = new HashMap();
  		message.setCode("1");
  		message.setText("保存成功！");
  		
  		try {
  		    	Map map = expgzService.addDict(query);
  		    	message.setMap(map);
  		} catch (Exception e) {
  		    	e.printStackTrace();
  			message.setCode("-1");
  			message.setText(e.getMessage());
  		}
  		mapOut.put("message",message);
  		this.renderJson(mapOut);
     }
	
	public String getUpdateDict(){
	    listMap = expgzService.getDictMap(query);
	    return "updateDict";
	}
	
	@SuppressWarnings({ "unchecked", "static-access" })
	public void updteDict(){
	  		Message message = new Message();
	  		Map mapOut = new HashMap();
	  		message.setCode("1");
	  		message.setText("保存成功！");
	  		
	  		try {
	  			expgzService.updateDict(query);
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
	  			expgzService.deletedict(query);
	  		} catch (Exception e) {
	  		    	e.printStackTrace();
	  			message.setCode("-1");
	  			message.setText(e.getMessage());
	  		}
	  		mapOut.put("message",message);
	  		this.renderJson(mapOut);
	     }
	
	/***
	 * Mapping
	 * @return
	 */
	public Map<String, String> getParMap() {
		return parMap;
	}
	public void setParMap(Map<String, String> parMap) {
		this.parMap = parMap;
	}
	public String getRcid() {
		return rcid;
	}
	public void setRcid(String rcid) {
		this.rcid = rcid;
	}
	public String getKcid() {
		return kcid;
	}
	public void setKcid(String kcid) {
		this.kcid = kcid;
	}
	public String getGzid() {
		return gzid;
	}
	public void setGzid(String gzid) {
		this.gzid = gzid;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public List<XtDict> getXtdict29() {
		return xtdict29;
	}
	public void setXtdict29(List<XtDict> xtdict29) {
		this.xtdict29 = xtdict29;
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
	public List<Map<String, String>> getOutList() {
		return outList;
	}
	public void setOutList(List<Map<String, String>> outList) {
		this.outList = outList;
	}

	public Map<String, String> getQuery() {
		return query;
	}

	public void setQuery(Map<String, String> query) {
		this.query = query;
	}

	public Map<String, Object> getListMap() {
		return listMap;
	}

	public void setListMap(Map<String, Object> listMap) {
		this.listMap = listMap;
	}

	public Map<String, String> getWtMap() {
		return wtMap;
	}

	public void setWtMap(Map<String, String> wtMap) {
		this.wtMap = wtMap;
	}

	public List<XtDict> getXtdict35() {
		return xtdict35;
	}

	public void setXtdict35(List<XtDict> xtdict35) {
		this.xtdict35 = xtdict35;
	}

	public String getWtkcid() {
		return wtkcid;
	}

	public void setWtkcid(String wtkcid) {
		this.wtkcid = wtkcid;
	}
	
}
