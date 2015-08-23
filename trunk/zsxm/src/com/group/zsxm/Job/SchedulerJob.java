package com.group.zsxm.Job;

import java.text.ParseException;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.CronTriggerBean;

public class SchedulerJob implements Job{
	private Scheduler scheduler;
	
	public void execute(JobExecutionContext jctx) throws JobExecutionException {
		// TODO Auto-generated method stub
		Map dataMap = jctx.getJobDetail().getJobDataMap();//①获取JobDetail关联的JobDataMap
		String size =(String)dataMap.get("size");//②
		ApplicationContext ctx = (ApplicationContext)dataMap.get("applicationContext");//③
		System.out.println("size:"+size);
		dataMap.put("size",size+"0");//④对JobDataMap所做的更改是否被会持久，取决于任务的类型
		//
	}
}
