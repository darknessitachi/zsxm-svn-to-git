package com.group.zsxm.service;

import java.text.ParseException;
import java.util.HashMap;

import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.group.core.common.CronExRelated;
import com.group.zsxm.service.common.BaseService;
import com.group.zsxm.service.common.ScheduleManager;
import com.group.zsxm.web.action.DwrSchedulePush;

@Service
public class SchedulerJobService extends BaseService{
	private ScheduleManager scheduleManager;
	private JobDetail executeJobDetail;
	
	/**
	 * 更新、新增 提醒信息
	 * @param triggerName
	 * @param triggerId
	 * @param cronExpression
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	public   void  updateNotificationInterval(String triggerName, String triggerId,String cronExpression) throws  SchedulerException, ParseException   {
		executeJobDetail.setName(triggerName);
		executeJobDetail.setGroup(triggerName);
		//scheduleManager.deleteJob("sdfsdfsdfsdf", "dfdf");
		JobDetail jd = scheduleManager.getJobDetail(executeJobDetail.getName(), executeJobDetail.getGroup());
		if(jd == null ){
			scheduleManager.addJobDetail(executeJobDetail, cronExpression);
		}else{
			scheduleManager.reScheduleJob(executeJobDetail.getName(), executeJobDetail.getGroup(),  cronExpression);
		}
	}
	

	
	/**\
	 * 定时清理过时的 提醒信息
	 */
	public void deleteScheduleJob(){
		String sql = "";
		
	}
	
	
	/** 
	  * 页面设置转为UNIX cron expressions 转换类
	  * CronExpConversion
	  */ 
	/** 
	 * 页面设置转为UNIX cron expressions 转换算法
	 * @param  everyWhat	传入的是何种类型()
	 * @param  Needs 包括 second minute hour  //秒 @param  monthlyNeeds 包括 第几个星期 星期几
	 * @param  weeklyNeeds  包括 星期几
	 * @param  userDefinedNeeds  包括具体时间点  如： 2007-01-02
	 * @param commonNeeds  秒 分 时 
	 * @return  cron expression	
	 */
	public   static  String convertDateToCronExp(String everyWhat,
			 String[] commonNeeds, String[] monthlyNeeds, String weeklyNeeds, String userDefinedNeeds){
		String cronEx  =   "" ;
		String commons  =  commonNeeds[ 0 ]  +   " "   +  commonNeeds[ 1 ]  +   " " +  commonNeeds[ 2 ]  +   " " ;
		String dayOfWeek  =   "" ;
		if  ( "monthly" .equals(everyWhat))   {
			dayOfWeek  =  monthlyNeeds[1] +  CronExRelated.specialCharacters.get(CronExRelated._THENTH)  +  monthlyNeeds[ 0 ];
			cronEx  =  (commons +  CronExRelated.specialCharacters.get(CronExRelated._ANY)+"" +  CronExRelated.specialCharacters.get(CronExRelated._EVERY) +""
					+  dayOfWeek  + "" ).trim();
			
		}else  if ( "weekly".equals(everyWhat)){
			 dayOfWeek  =  weeklyNeeds;  //1
			 cronEx  =  (commons  +  CronExRelated.specialCharacters.get(CronExRelated._ANY) + "" 
					 +  CronExRelated.specialCharacters.get(CronExRelated._EVERY)
					 +   ""   +  dayOfWeek  +   "" ).trim();

		}else if  ( "userDefined" .equals(everyWhat))   {
			String dayOfMonth  =  userDefinedNeeds.split("-")[2];
			if (dayOfMonth.startsWith( "0" )){
				dayOfMonth  =  dayOfMonth.replaceFirst( "0" , "" );
			}
			String month  =  userDefinedNeeds.split( "-" )[1];
		    if  (month.startsWith( "0" ))   {
			    month  =  month.replaceFirst( "0" ,  "" );
		    }
			String year  =  userDefinedNeeds.split( "-" )[ 0 ];
			cronEx  =  (commons  +  dayOfMonth  + " " +month  + " "+ 
					CronExRelated.specialCharacters.get(CronExRelated._ANY)+ "" ).trim();
		}
		return cronEx;
	}
	/**
	 * Mapping
	 * @return
	 */


	public JobDetail getExecuteJobDetail() {
		return executeJobDetail;
	}



	public void setExecuteJobDetail(JobDetail executeJobDetail) {
		this.executeJobDetail = executeJobDetail;
	}



	public ScheduleManager getScheduleManager() {
		return scheduleManager;
	}



	public void setScheduleManager(ScheduleManager scheduleManager) {
		this.scheduleManager = scheduleManager;
	}

	
}
