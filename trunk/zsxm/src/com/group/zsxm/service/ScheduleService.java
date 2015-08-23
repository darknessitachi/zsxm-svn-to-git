package com.group.zsxm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fins.gt.model.FilterInfo;
import com.fins.gt.model.SortInfo;
import com.group.core.common.LimitPage;
import com.group.zsxm.exception.BusException;
import com.group.zsxm.service.common.BaseService;
import com.sun.corba.se.spi.orb.StringPair;

@Service
public class ScheduleService extends BaseService{

	/**
	 * 获取日程 提醒时间
	 * @param sid
	 * @return
	 */
	public Map getScheduleWithStrTstimeBySID(String sid){
		Map m = new HashMap();
		String sql = " select CONVERT(varchar(100), strtime-remindtime , 23) D ,CONVERT(varchar(100), strtime-remindtime , 24) HM from xt_schedule where sid="+sid+" ";
		m = this.getSimpleJdbcTemplate().queryForMap(sql);
		return m;
	}
	
	/**
	 * 获取用户当天的日程
	 * @param userid
	 * @param day
	 * @return
	 */
	public List getScheduleByDay(String userid,String day){
		String sql = " select sid,username,userid,cname,sbt,strhour,strminute,tsbz from xt_schedule where userid="+userid+" and CONVERT(varchar(100), strtime, 23)='"+day+"' order by strtime ";
		return this.getJdbcTemplate().queryForList(sql);
	}
	
	/**
	 * 获取当天所在的周(从一至七)
	 * @param day
	 * @return
	 */
	public Map getWeekByDay(String day){
		int dayinweek = this.getDateInWeek(day);
		if(dayinweek == 0) dayinweek=7;// i=0 ： 代表星期天，为方便计算 替换 成 7 
		String sql = "";
		for(int i=0;i<7;i++){
			if(i==0){
				sql = "select CONVERT(varchar(100),CONVERT(datetime,'"+day+"',0)-("+(dayinweek-i)+"-1),23) AS week"+(i+1);
			}else{
				sql += ",CONVERT(varchar(100),CONVERT(datetime,'"+day+"',0)-("+(dayinweek-i)+"-1),23) AS week"+(i+1);
			}
			
		}
		return this.getSimpleJdbcTemplate().queryForMap(sql);
	}
	
	/**
	 * 获取当前用户的周程
	 * @param userid
	 * @param day
	 * @return
	 */
	public List getScheduleWithWeekListByDay(String userid,String day){
		int i = this.getDateInWeek(day);
		if(i == 0) i=7;// i=0 ： 代表星期天，为方便计算 替换 成 7 
		String sql = " select sid,username,userid,cname,sbt,CONVERT(varchar(100), strdate, 23) AS strdate,strhour,strminute,tsbz from xt_schedule where " +
				" userid="+userid+" and strdate>=CONVERT(datetime,'"+day+"',0)-("+i+"-1) and " +
						" strdate<=CONVERT(datetime,'"+day+"',0)-("+i+"-7) order by strtime  ";
		return this.getJdbcTemplate().queryForList(sql);
	}
	
	/**
	 * 获取当前用户的月程
	 */
	public List getScheduleWithMonthListByDay(String userid,String day){
		int cmonth = this.getActualMaximumByDay(day);
		String sql = " select sid,username,userid,cname,sbt,CONVERT(varchar(100), strdate, 23) AS strdate,strhour,strminute,tsbz from xt_schedule where " +
		" userid="+userid+" and strdate>=CONVERT(datetime,'"+(day.substring(0, 8)+"01")+"',0) and " +
				" strdate<=CONVERT(datetime,'"+(day.substring(0, 8)+cmonth)+"',0) order by strtime ";
		return this.getJdbcTemplate().queryForList(sql);
	}
	
	/**
	 * 获取当前用户的所有 日程
	 * @param userid
	 * @return
	 */
	public List getScheduleListByUser(String userid){
		List l = new ArrayList();
		String sql = " select sid,username,userid,cname,sbt,snr,CONVERT(varchar(100), strdate, 23) AS strdate,strhour,strminute,CONVERT(varchar(100), enddate, 23) AS enddate,endhour,endminute,tsbz," +
				" remindtime,CONVERT(varchar(100),intime,23) as intime,istsbz,ztbz from xt_schedule where userid="+userid;
		l = this.getSimpleJdbcTemplate().queryForList(sql);
		return l;
	}
	
	/**
	 * 新增 日程
	 * @return
	 * tsbz: 0:不需要提醒   1：需要提醒
	 * istsbz: 0:还未提醒： 1：已提醒过
	 * ztbz:   0：待办事宜   1： 已提醒过  5：已办事宜  7：已过时
	 */
	public int doScheduleI(Map<String,String> mt,String loginname,String userid,String cnname){
		String sql = "";
		/** 2010-01-26  去除，为了可以后补日程
		sql = " select datediff(minute,CONVERT(datetime,'"+mt.get("strdate")+" "+mt.get("strhour")+":"+mt.get("strminute")+":"+"00"+"',0),getdate()) ";
		if(this.getSimpleJdbcTemplate().queryForInt(sql) >= 0){
			//日程时间已过时
			throw new BusException("您的日程安排时间已过时,请重新安排时间！");
		}
		**/
		sql = " select datediff(minute,CONVERT(datetime,'"+mt.get("strdate")+" "+mt.get("strhour")+":"+mt.get("strminute")+":"+"00"+"',0),CONVERT(datetime,'"+mt.get("enddate")+" "+mt.get("endhour")+":"+mt.get("endminute")+":"+"00"+"',0)) ";
		if(this.getSimpleJdbcTemplate().queryForInt(sql) < 0){
			//起始时间 与 结束时间比较
			throw new BusException("日程时间安排错误(结束时间 比 开始时间早),请重新安排时间！");
		}
		if(mt.get("tsbz") != null && mt.get("tsbz").equals("1")){
			sql = " select datediff(minute,CONVERT(datetime,'"+mt.get("strdate")+" "+mt.get("strhour")+":"+mt.get("strminute")+":"+"00"+"',0)-"+mt.get("remindtime")+",getdate()) ";
			if(this.getSimpleJdbcTemplate().queryForInt(sql) >= 0){
				//日程时间已过时
				throw new BusException("您的日程提醒时间已过时,请重新安排提醒时间！");
			}
		}
		
		
		Integer sid = Integer.parseInt(String.valueOf((this.getMaxKey("XT_SCHEDULE"))));
		sql = " insert into xt_schedule(sid,username,userid,cname,sbt,snr,strtime,strdate,strhour,strminute,endtime,enddate,endhour,endminute,tsbz," +
			"remindtime,intime,istsbz,ztbz)" +
			" values("+sid+",'"+loginname+"','"+userid+"','"+cnname+"','"+mt.get("sbt")+"','"+mt.get("snr")+"','"+mt.get("strdate")+" "+mt.get("strhour")+":"+mt.get("strminute")+":"+"00"+"'," +
			"'"+mt.get("strdate")+"','"+mt.get("strhour")+"','"+mt.get("strminute")+"','"+mt.get("enddate")+" "+mt.get("endhour")+":"+mt.get("endminute")+":"+"00"+"',"+
			"'"+mt.get("enddate")+"','"+mt.get("endhour")+"'," +
			"'"+mt.get("endminute")+"','"+(mt.get("tsbz") == null?"0":mt.get("tsbz"))+"','"+mt.get("remindtime")+"',getdate(),0,0)"; 
		this.getSimpleJdbcTemplate().update(sql);
		return sid;
	}
	
	/** 
	 * 修改日程取数
	 * @param sid
	 * @return
	 */
	public Map preScheduleU(String sid){
		Map m = new HashMap();
		String sql = " select sid,username,userid,cname,sbt,snr,CONVERT(varchar(100), strdate, 23) AS strdate,strhour,strminute,CONVERT(varchar(100), enddate, 23) AS enddate,endhour,endminute,tsbz," +
			" remindtime,intime,istsbz,ztbz from xt_schedule where sid="+sid;
		m = this.getSimpleJdbcTemplate().queryForMap(sql);
		return m;
	}
	
	/**
	 * 修改日程
	 */
	public int doScheduleU(Map<String,String> mt,String loginname,String userid,String cnname){
		Integer sid = 0;
		if(mt.get("sid") != null ){
			String sql = "";
			/**
			sql = " select datediff(minute,CONVERT(datetime,'"+mt.get("strdate")+" "+mt.get("strhour")+":"+mt.get("strminute")+":"+"00"+"',0),getdate()) ";
			if(this.getSimpleJdbcTemplate().queryForInt(sql) >= 0){
				//日程时间已过时
				throw new BusException("您的日程安排时间已过时,请重新安排时间！");
			}
			**/
			sql = " select datediff(minute,CONVERT(datetime,'"+mt.get("strdate")+" "+mt.get("strhour")+":"+mt.get("strminute")+":"+"00"+"',0),CONVERT(datetime,'"+mt.get("enddate")+" "+mt.get("endhour")+":"+mt.get("endminute")+":"+"00"+"',0)) ";
			if(this.getSimpleJdbcTemplate().queryForInt(sql) < 0){
				//起始时间 与 结束时间比较
				throw new BusException("日程时间安排错误(结束时间 比 开始时间早),请重新安排时间！");
			}
			if(mt.get("tsbz") != null && mt.get("tsbz").equals("1")){
				sql = " select datediff(minute,CONVERT(datetime,'"+mt.get("strdate")+" "+mt.get("strhour")+":"+mt.get("strminute")+":"+"00"+"',0)-"+mt.get("remindtime")+",getdate()) ";
				if(this.getSimpleJdbcTemplate().queryForInt(sql) >= 0){
					//日程时间已过时
					throw new BusException("您的日程提醒时间已过时,请重新安排提醒时间！");
				}
			}
			sid = Integer.parseInt(mt.get("sid"));
			sql = " delete from xt_schedule where sid="+sid;
			this.getSimpleJdbcTemplate().update(sql);
			sql = " insert into xt_schedule(sid,username,userid,cname,sbt,snr,strtime,strdate,strhour,strminute,endtime,enddate,endhour,endminute,tsbz," +
				"remindtime,intime,istsbz,ztbz)" +
				" values("+sid+",'"+loginname+"','"+userid+"','"+cnname+"','"+mt.get("sbt")+"','"+mt.get("snr")+"','"+mt.get("strdate")+" "+mt.get("strhour")+":"+mt.get("strminute")+":"+"00"+"'," +
				"'"+mt.get("strdate")+"','"+mt.get("strhour")+"','"+mt.get("strminute")+"','"+mt.get("enddate")+" "+mt.get("endhour")+":"+mt.get("endminute")+":"+"00"+"',"+
				"'"+mt.get("enddate")+"','"+mt.get("endhour")+"'," +
				"'"+mt.get("endminute")+"','"+(mt.get("tsbz") == null?"0":mt.get("tsbz"))+"','"+mt.get("remindtime")+"',getdate(),0,0)"; 
			this.getSimpleJdbcTemplate().update(sql);
		}
		return sid;
	}
	/**
	 * 修改日程
	 */
	public int doScheduleB(Map<String,String> mt,String loginname,String userid,String cnname){
		Integer sid = 0;
		if(mt.get("sid") != null ){
			String sql = "";
			
			sid = Integer.parseInt(mt.get("sid"));
			
			sql = " update xt_schedule set sbt='"+mt.get("sbt")+"',snr='"+mt.get("snr")+"' where sid="+sid;
			 
			this.getSimpleJdbcTemplate().update(sql);
		}
		return sid;
	}	
	public Object getListForSch(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos,String userid){
		String name = parMap.get("name") == null ? "" :  parMap.get("name");
		String where = parMap.get("where") == null ? "" :  parMap.get("where");
		String ls_sql = " select SID,SBT,SNR,CONVERT(varchar(100), STRTIME, 20) AS STRTIME,CONVERT(varchar(100), ENDTIME, 20) AS ENDTIME,isnull(TSBZ,0) TSBZ," +
			" CONVERT(varchar(100),INTIME,20) as INTIME from xt_schedule where userid="+userid;
		
		if(where != null && !where.equals("")){
			ls_sql += " and "+where+" like '%" + name + "%'";
		}
		
		
		String sortCond = getGridSortString(sortInfo);
		String filterCond = getGridFilterString(filterInfos);
		if (filterCond != null) {
			ls_sql += filterCond;
		}
		if (sortCond != null) {
			ls_sql += " order by " + sortCond.split("_")[0];
		} else {
			ls_sql += " order by strtime desc ";
		}
		if (limit != null) {
			return this.queryForListWithPage(ls_sql, limit);
		} else {
			return this.simpleJdbcTemplate.queryForList(ls_sql);
		}
	}
	
	/**
	 * 删除日程
	 */
	public int doScheduleD(String sid){
		String sql =  " delete from xt_schedule where sid in ("+sid+")";
		this.getSimpleJdbcTemplate().update(sql);
		return 1;
	}
	
	/**
	 * 更新状态
	 */
	public int doScheduleZtbzByID(String[] sid,String ztbz){
		String sql = " update xt_schedule set ztbz="+ztbz+" where sid in ("+ArrayToString(sid)+")";
		this.getSimpleJdbcTemplate().update(sql);
		return 1;
	}
}
