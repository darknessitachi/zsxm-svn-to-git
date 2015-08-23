package com.group.zsxm.web.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fins.gt.server.GridServerHandler;
import com.group.core.Constants;
import com.group.core.common.Message;
import com.group.core.common.Page;
import com.group.zsxm.entity.DmMc;
import com.group.zsxm.entity.Xtuser;
import com.group.zsxm.service.ScheduleService;
import com.group.zsxm.service.SchedulerJobService;
import com.group.zsxm.web.common.BaseAction;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.inject.util.Function;

@ParentPackage("appDefault")
@Scope("prototype")
@Controller
@Results( { @Result(name = "input", value = "/WEB-INF/jsp/schedule/schedule.jsp"),
			@Result(name = "schd", value = "/WEB-INF/jsp/schedule/schedule_d.jsp"),
			@Result(name = "schb", value = "/WEB-INF/jsp/schedule/schedule_b.jsp"),
			@Result(name = "schwh", value = "/WEB-INF/jsp/schedule/schedulewh.jsp"),
			@Result(name = "view", value = "/WEB-INF/jsp/schedule/schedule_view.jsp")
			})
public class ScheduleAction extends BaseAction{
    @Autowired
	@Qualifier("scheduleService")
	private ScheduleService scheduleService;
    
    @Autowired
	@Qualifier("schedulerJobService")
	private SchedulerJobService schedulerJobService;
    
    private Map<String, String> mt;
    private String sid;
    private String tsbz; //提醒标志 
    private List<DmMc> listhour;
    private List<DmMc> listminute;
    private String day;
    private Xtuser xtuser;
    private Integer opttype;
    private List<Map<String, String>> slist;
    
	public void prepare() {
		xtuser = (Xtuser)getSession().getAttribute(Constants.USER_SESSION_KEY);
	} 
	
    public String execute() {
		return Action.INPUT;
	}
    
    public void getScheduleByDay(){
    	Map<String, Object> mapOut = new HashMap<String, Object>();
    	slist = scheduleService.getScheduleByDay(xtuser.getUserid(), day);
    	mapOut.put("info", slist);
		this.renderJson(mapOut);
    }
    
    public void getWeekByDay(){
    	Map<String, Object> mapOut = new HashMap<String, Object>();
    	Map<String,String> m = scheduleService.getWeekByDay(day);
    	slist = scheduleService.getScheduleWithWeekListByDay(xtuser.getUserid(),day);
    	mapOut.put("week", m);
    	mapOut.put("info", slist);
    	this.renderJson(mapOut);
    }
    
    
    public void getMonthByDay(){
    	Map<String, Object> mapOut = new HashMap<String, Object>();
    	slist = scheduleService.getScheduleWithMonthListByDay(xtuser.getUserid(),day);
    	mapOut.put("info", slist);
    	//存入当前月的总天数
    	mapOut.put("cmonth", scheduleService.getActualMaximumByDay(day));
    	//存入当前月的起始周
    	int strweek = scheduleService.getDateInWeek(day.substring(0,8)+"01");
    	if( strweek == 0) strweek=7;
    	mapOut.put("strweek", strweek);
    	this.renderJson(mapOut);
    }
    
    
    public String preScheduleI(){
    	String[] hdm = new String[24];
    	String[] mdm = new String[60];
    	for(int i=0;i<24;i++){
    		if(i<10)
    			{hdm[i] = "0"+i;}
    		else {
				hdm[i] = ""+i;
			}
    	}
    	for(int i=0;i<60;i++){
    		if(i<10)
    			{mdm[i] = "0"+i;}
    		else {
    			mdm[i] = ""+i;
			}
    	}
    	listhour = ArrayToList(hdm, hdm);
    	listminute = ArrayToList(mdm, mdm);
    	
    	mt = new HashMap();
    	mt.put("endhour", "02");
    	mt.put("strdate", xtuser.getLogindate());
    	mt.put("enddate", xtuser.getLogindate());
    	
    	return "schd";
    }
    
    /**
     * JOB 名字： = XT_SCHEDULE.SID+"_"+XTUSER.USERID
     */
    public void doScheduleI(){
    	Map<String, Object> mapOut = new HashMap<String, Object>();
    	try{
    		int reSid = scheduleService.doScheduleI(mt, xtuser.getLoginname(), xtuser.getUserid(), xtuser.getCnname());
    		if(mt.get("tsbz") != null && mt.get("tsbz").equals("1")){
    			//需要提醒的
    			Map<String ,String> m = scheduleService.getScheduleWithStrTstimeBySID(String.valueOf(reSid));
    			String[] hm = m.get("HM").split(":");
    			String corE = schedulerJobService.convertDateToCronExp("userDefined",new String[]{"00",hm[1],hm[0]}, null, null, m.get("D"));
        		schedulerJobService.updateNotificationInterval(reSid+"_"+xtuser.getUserid(),null ,corE);
        	}
    		message = new Message("1","保存成功");
    	}catch(Exception e){
    		//e.printStackTrace();
    		message = new Message("-1",e.getMessage());
    	}
    	mapOut.put("message", message);
		this.renderJson(mapOut);
    }
    
    public String preScheduleU(){
    	String[] hdm = new String[24];
    	String[] mdm = new String[60];
    	for(int i=0;i<24;i++){
    		if(i<10)
    			{hdm[i] = "0"+i;}
    		else {
				hdm[i] = ""+i;
			}
    	}
    	for(int i=0;i<60;i++){
    		if(i<10)
    			{mdm[i] = "0"+i;}
    		else {
    			mdm[i] = ""+i;
			}
    	}
    	listhour = ArrayToList(hdm, hdm);
    	listminute = ArrayToList(mdm, mdm);
    	mt = scheduleService.preScheduleU(sid);
    	return "schd";
    }
    
    public void doScheduleU(){
    	Map<String, Object> mapOut = new HashMap<String, Object>();
    	try{
    		int reSid = scheduleService.doScheduleU(mt, xtuser.getLoginname(), xtuser.getUserid(), xtuser.getCnname());
    		if(mt.get("tsbz") != null && mt.get("tsbz").equals("1")){
    			//需要提醒的
    			Map<String ,String> m = scheduleService.getScheduleWithStrTstimeBySID(String.valueOf(reSid));
    			String[] hm = m.get("HM").split(":");
    			String corE = schedulerJobService.convertDateToCronExp("userDefined",new String[]{"00",hm[1],hm[0]}, null, null, m.get("D"));
        		schedulerJobService.updateNotificationInterval(reSid+"_"+xtuser.getUserid(),null ,corE);
        		
    		}
    		message = new Message("1","保存成功");
    	}catch(Exception e){
    		//e.printStackTrace();
    		message = new Message("-1",e.getMessage());
    	}
    	mapOut.put("message", message);
		this.renderJson(mapOut);
    }
    
    public String preScheduleB(){
    	String[] hdm = new String[24];
    	String[] mdm = new String[60];
    	for(int i=0;i<24;i++){
    		if(i<10)
    			{hdm[i] = "0"+i;}
    		else {
				hdm[i] = ""+i;
			}
    	}
    	for(int i=0;i<60;i++){
    		if(i<10)
    			{mdm[i] = "0"+i;}
    		else {
    			mdm[i] = ""+i;
			}
    	}
    	listhour = ArrayToList(hdm, hdm);
    	listminute = ArrayToList(mdm, mdm);
    	mt = scheduleService.preScheduleU(sid);
    	return "schb";
    }
    
    public void doScheduleB(){
    	Map<String, Object> mapOut = new HashMap<String, Object>();
    	try{
    		int reSid = scheduleService.doScheduleB(mt, xtuser.getLoginname(), xtuser.getUserid(), xtuser.getCnname());
    		
    		message = new Message("1","保存成功");
    	}catch(Exception e){
    		//e.printStackTrace();
    		message = new Message("-1",e.getMessage());
    	}
    	mapOut.put("message", message);
		this.renderJson(mapOut);
    }
    public String preScheduleWh(){
    	return "schwh";
    }
    
	public void doGetList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		getLimitPage().setRowAttributes(gridHandler.getTotalRowNum(), gridHandler.getPageNum(), gridHandler.getPageSize());
		Map param = getParameterSimpleMap();
		Page page = (Page) scheduleService.getListForSch(getLimitPage(), param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo(),xtuser.getUserid());
		gridHandler.setTotalRowNum(page.getTotalCount());
		gridHandler.setData((List) page.getData());
		renderText(gridHandler.getLoadResponseText());
	}
	
	public void doExportList() {
		GridServerHandler gridHandler = new GridServerHandler(getRequest(), getResponse());
		Map param = gridHandler.getGridRequestParameters();
		List list = (List) scheduleService.getListForSch(null, param, gridHandler.getSingleSortInfo(), gridHandler.getFilterInfo(),xtuser.getUserid());
		try {
			gridHandler.exportXLSfromMaps(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    public void doScheduleD(){
    	Map<String, Object> mapOut = new HashMap<String, Object>();
    	try{
    		scheduleService.doScheduleD(sid);
    		message = new Message("1","删除成功");
    		String[] jobs = sid.split(",");
    		for(int i=0;i<jobs.length;i++){
    			//清除定时提醒任务
    			schedulerJobService.getScheduleManager().deleteJob(jobs[i]+"_"+xtuser.getUserid(), jobs[i]+"_"+xtuser.getUserid());
    		}
    	}catch(Exception e){
    		message = new Message("-1",e.getMessage());
    	}
    	mapOut.put("message", message);
		this.renderJson(mapOut);
    }
	
	
    public String preView(){
    	mt = scheduleService.preScheduleU(sid);
    	return "view";
    }
    
    
    /**
     * Mapping
     * @return
     */
	public Map<String, String> getMt() {
		return mt;
	}

	public void setMt(Map<String, String> mt) {
		this.mt = mt;
	}

	public String getTsbz() {
		return tsbz;
	}

	public void setTsbz(String tsbz) {
		this.tsbz = tsbz;
	}

	public List<DmMc> getListhour() {
		return listhour;
	}

	public void setListhour(List<DmMc> listhour) {
		this.listhour = listhour;
	}

	public List<DmMc> getListminute() {
		return listminute;
	}

	public void setListminute(List<DmMc> listminute) {
		this.listminute = listminute;
	}

	public Xtuser getXtuser() {
		return xtuser;
	}

	public void setXtuser(Xtuser xtuser) {
		this.xtuser = xtuser;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public List<Map<String, String>> getSlist() {
		return slist;
	}

	public void setSlist(List<Map<String, String>> slist) {
		this.slist = slist;
	}

	public Integer getOpttype() {
		return opttype;
	}

	public void setOpttype(Integer opttype) {
		this.opttype = opttype;
	}
    
}
