package com.group.zsxm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;

import com.group.zsxm.service.common.BaseService;
import com.group.zsxm.service.common.ScheduleManager;
import com.group.zsxm.web.action.DwrDeclarePush;
import com.group.zsxm.web.action.DwrSchedulePush;

public class ExecuteScheduleService extends BaseService{
	private DwrSchedulePush dwrSchedulePush;
	private ScheduleManager scheduleManager;
	
	public void executeCleanSchedule(){
		System.out.println("定时任务！待定");
	}
	
	public void executeSchedule(){
		try{
			System.out.println("start.....");
			HashMap map = new HashMap();
			List l = scheduleManager.getCurrentJobList();
			JobDetail jDetail = new JobDetail();
			for(int i=0;i<l.size();i++){
				JobExecutionContext jec = (JobExecutionContext)l.get(i);
				jDetail = jec.getJobDetail();
				String sbt = String.valueOf(this.getSimpleJdbcTemplate().queryForObject("select isnull(sbt,'') from xt_schedule where sid="+jDetail.getName().split("_")[0],String.class));
				map.put("title", sbt);
				map.put("sid", jDetail.getName().split("_")[0]);
				dwrSchedulePush.NotifyInfo(jDetail.getName().split("_")[1], map);
				System.out.println(jDetail.getName());
				scheduleManager.deleteJob(jDetail.getName(), jDetail.getGroup());
			}
			//System.out.println(scheduleManager.);
			
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		
	}

	public DwrSchedulePush getDwrSchedulePush() {
		return dwrSchedulePush;
	}

	public void setDwrSchedulePush(DwrSchedulePush dwrSchedulePush) {
		this.dwrSchedulePush = dwrSchedulePush;
	}

	public ScheduleManager getScheduleManager() {
		return scheduleManager;
	}

	public void setScheduleManager(ScheduleManager scheduleManager) {
		this.scheduleManager = scheduleManager;
	}
	
}
