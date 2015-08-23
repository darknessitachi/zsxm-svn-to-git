package com.group.zsxm.service.common;
import  java.text.ParseException;
import  java.util.ArrayList;
import  java.util.Date;
import  java.util.List;
import  org.quartz.CronTrigger;
import  org.quartz.JobDetail;
import  org.quartz.Scheduler;
import  org.quartz.SchedulerException;
import  org.quartz.Trigger;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ScheduleManager {
	private   static   final  Log log  =  LogFactory.getLog(ScheduleManager. class );

	private  Scheduler scheduler;
	 
    public   void  start()  throws  SchedulerException   {
        if  (scheduler  ==   null )   {
          scheduler  =  getScheduler();
      } 
        if  (scheduler.isShutdown()  ||  scheduler.isInStandbyMode())   {
          scheduler.start();
      } 
  } 

    public   void  pause()  throws  SchedulerException   {
        if  (scheduler  ==   null )   {
          scheduler  =  getScheduler();
      } 
        if  (scheduler.isStarted())   {
          scheduler.standby();
      } 
  } 

    public   void  stop()  throws  SchedulerException   {
        if  (scheduler  ==   null )   {
          scheduler  =  getScheduler();
      } 
        if  (scheduler.isStarted()  ||  scheduler.isInStandbyMode())   {
          scheduler.shutdown();
      } 
  } 

    public  List listJobDetails()  throws  SchedulerException   {
      List rstList  =   new  ArrayList < JobDetail > ();
        if  (scheduler  ==   null )   {
          scheduler  =  getScheduler();
      } 
      String[] groupNames  =  scheduler.getJobGroupNames();
      log.info( " [INFO] the groups in current scheduler is :  " 
               +  groupNames.length);
        for  ( int  i  =   0 ; i  <  groupNames.length; i ++ )   {
          String[] jobNames  =  scheduler.getJobNames(groupNames[i]);
            for  ( int  j  =   0 ; j  <  jobNames.length; j ++ )   {
              JobDetail jobDetail  =  scheduler.getJobDetail(jobNames[j],
                      groupNames[i]);
                if  (jobDetail  !=   null )   {
                  rstList.add(jobDetail);
              } 
          } 
      } 
       return  rstList;
  } 

   public  JobDetail getJobDetail(String jobName, String jobGroupName)
            throws  SchedulerException   {
        if  (scheduler  ==   null )   {
          scheduler  =  getScheduler();
      } 
      JobDetail job  =  scheduler.getJobDetail(jobName, jobGroupName);

        if  (job  ==   null )   {
          log.info( " [INFO]none object relative the jobName :  "   +  jobName
                   +   "  and jobGroupName :  "   +  jobGroupName);
      } 
       return  job;
  } 

   public  List < JobDetail >  getJobDetailByGroup(String jobGroupName)
            throws  SchedulerException   {
      List < JobDetail >  list  =   new  ArrayList < JobDetail > ();
        if  (scheduler  ==   null )   {
          scheduler  =  getScheduler();
      }
      String[] jobNames  =  scheduler.getJobNames(jobGroupName);

      log.debug( " [DEBUG] jobNames with \" "  + jobGroupName 
               +   " \"  as groupName :  "  + jobNames); 

        for  ( int  i  =   0 ; i  <  jobNames.length; i ++ )   {
          JobDetail jobDetail  =  getJobDetail(jobNames[i], jobGroupName);
            if  (jobDetail  !=   null )   {
              list.add(jobDetail);
           }   else    {
              log
                      .debug( " [DEBUG] JobDetail with {\""
                               +  jobNames[i]
                               +   " \"  , \"" 
                               +  jobGroupName
                               +   " \" }  as the jobName and jobGroupName can not be found  ! " ); 
          } 
      } 
       return  list;
  } 

   public   boolean  addJobDetail(JobDetail jobDetail, String cronExpress)
            throws  SchedulerException, ParseException   {
        if  (scheduler  ==   null )   {
          scheduler  =  getScheduler();
      } 
        if  (jobDetail  !=   null   &&  cronExpress  !=   null )   {

          Trigger trigger  =   new  CronTrigger(jobDetail.getName(), jobDetail.getGroup(),
                  jobDetail.getName(), jobDetail.getGroup(), cronExpress);
          
          Date jobDate  =  scheduler.scheduleJob(jobDetail, trigger);
          //System.out.println(scheduler.getTriggerGroupNames());
          log
                  .info( " [INFO]jobDetail :  " 
                           +  jobDetail.getFullName()
                           +   "  has been added into the scheduler , and the firstFiredTime is :  " 
                           +  jobDate);
           return  (jobDate  ==   null )  ?   false  :  true ;
       }   else    {
          log
                  .info( " [INFO]add jobDetail failure, the parameters on jobDetail or trigger has null value ! " );
      } 
       return   false ;
  } 

   public  Trigger[] getJobTrigger(String jobName, String jobGroupName)
            throws  SchedulerException   {
        if  (scheduler  ==   null )   {
          scheduler  =  getScheduler();
      } 
       return  scheduler.getTriggersOfJob(jobName, jobGroupName);
  } 

    public  List < Trigger >  listTrigger()  throws  SchedulerException   {
      List < Trigger >  triggerList  =   new  ArrayList < Trigger > ();
        if  (scheduler  ==   null )   {
          scheduler  =  getScheduler();
      } 
      String[] triggerGroupNames  =  scheduler.getTriggerGroupNames();
      log
              .info( " [INFO] the trigger groups amounts which is registed within the system is :  " 
                       +  triggerGroupNames.length);
        for  (String triggerGroupName : triggerGroupNames)   {
            if  (triggerGroupName  !=   null )   {
              String[] triggerNames  =  scheduler
                      .getTriggerNames(triggerGroupName);
                for  (String triggerName : triggerNames)   {
                  Trigger trigger  =  scheduler.getTrigger(triggerName,
                          triggerGroupName);
                   if  (trigger  !=   null 
                           &&  scheduler.getTriggerState(triggerName,
                                   triggerGroupName)  ==  Trigger.STATE_NORMAL)   {
                      triggerList.add(trigger);
                   }   else    {
                      log.info( " [INFO] the trigger :  " 
                               +  ((trigger  ==   null )  ?  trigger.getFullName()
                                      :  "  null " )  +   " , not exists  " 
                               +   " or has the not normal status " );
                  } 
              } 
          } 
      } 
       return  triggerList;
  } 
   
    public boolean reScheduleJob(String triggerName,String cronExpression) throws SchedulerException, ParseException{
    	CronTriggerBean trigger  =  (CronTriggerBean) scheduler.getTrigger(
				 triggerName, Scheduler.DEFAULT_GROUP);
    	trigger.setCronExpression(cronExpression);
		scheduler.rescheduleJob(triggerName, Scheduler.DEFAULT_GROUP, trigger);
    	return false;
    }
   public   boolean  reScheduleJob(String jobName, String jobGroupName,
           String computedStr)  throws  SchedulerException, ParseException   {
        if  (scheduler  ==   null )   {
          scheduler  =  getScheduler();
      } 

      Trigger newTrigger  =   new  CronTrigger(jobName, jobGroupName, jobName,
              jobGroupName, computedStr);
      
      newTrigger.setJobName(jobName);
      newTrigger.setJobGroup(jobGroupName);
      
      Date triggerDate  =  scheduler.rescheduleJob(jobName, jobGroupName, newTrigger);
      log.info( " [INFO] the job  "   +  jobName
               +   "  has been update to fired on :  "   +  triggerDate);

       return  (triggerDate  ==   null )  ?   false  :  true ;
  } 

   
   public boolean deleteJob(String jobName, String jobGroupName){
	   try{
		   scheduler.deleteJob(jobName, jobGroupName);
		   return true;
	   }catch(Exception e){
		   log.info( " [INFO] the job  "   +  jobName
	               +   "  has been Delete jobGroupName :  "   +  jobGroupName);
	   }
	   return true;
   }
   

    private  String getWeekIdentify( short  weekDay)   {
      String value  =   null ;
        switch  (weekDay)   {
       case   1 :
          value  =   " SUN " ;
           break ;
       case   2 :
          value  =   " MON " ;
           break ;
       case   3 :
          value  =   " TUE " ;
           break ;
       case   4 :
          value  =   " WED " ;
           break ;
       case   5 :
          value  =   " THU " ;
           break ;
       case   6 :
          value  =   " FRI " ;
           break ;
       case   7 :
          value  =   " SAT " ;
           break ;
       default :
           break ;
      } 
       return  value;
  } 

    public List getCurrentJobList(){
    	List list = new ArrayList();
    	try{
    	    list = scheduler.getCurrentlyExecutingJobs();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return list;
    }
    
    public  Scheduler getScheduler()   {
       return  scheduler;
  } 

    public   void  setScheduler(Scheduler scheduler)   {
       this .scheduler  =  scheduler;
  } 

}
