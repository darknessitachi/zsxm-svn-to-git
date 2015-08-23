package com.group.zsxm.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.stereotype.Service;

import com.group.core.common.LimitPage;
import com.group.core.common.Page;
import com.group.zsxm.entity.Info_zg;
import com.group.zsxm.exception.BusException;
import com.group.zsxm.service.common.BaseService;

@Service
public class XxglService extends BaseService{
	@Autowired
	@Qualifier("lobHandler")
	private DefaultLobHandler lobHandler ;
	
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> getLmList(){
		return this.getJdbcTemplate().queryForList("select * from info_lm ", new Object[]{});
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> getIndexListByLx(String lmid){
		String sql = "select top 6 info_zg.xxid,xxbt,convert(char(10),info_zg.rq,20) as rq from info_zg,info_lmzg where info_zg.xxid=info_lmzg.xxid and info_zg.spbz='2' and info_lmzg.lmid="+lmid+"  order by info_zg.rq desc ";
		return this.getJdbcTemplate().queryForList(sql);
	}
	
	public List<Map<String,String>> getIndexListBySchedule(String userid){
		String sql = " select top 6 sid,username,userid,cname,sbt,snr,CONVERT(varchar(100), strdate, 23) AS strdate,strhour,strminute,CONVERT(varchar(100), enddate, 23) AS enddate,endhour,endminute,tsbz," +
			" remindtime,CONVERT(varchar(100),intime,23) as intime,istsbz,ztbz from xt_schedule where CONVERT(varchar(100), strdate, 23)=CONVERT(varchar(100), getdate(), 23) and userid="+userid+" order by strdate  ";
		return this.getJdbcTemplate().queryForList(sql);
	}
	
	@SuppressWarnings("unchecked")
	public String getFjName(String xxid,String fjid){
		return getSimpleJdbcTemplate().queryForObject("select fjmc from info_zgfj where xxid=? and fjxh=? ",String.class, new Object[]{xxid,fjid});
	}
	    
	public void saveXxzg(String lxList,String fjList,Info_zg info_zg,String userid,String path){
		String[] lxDatas = lxList.split(",");
		int len = lxDatas.length;
		String sql_i = "insert into info_zg(xxid,xxbt,fbrid,fbxx,spbz,status,rq) values(?,?,?,?,?,?,getdate())";
		if(info_zg.getXxid()!=null && !info_zg.getXxid().equals("")){
			//String sql_u = "update info_zg set xxbt=?,fbwid=?,fbxx=?,spbz=?,status=? where xxid=?";
			this.getJdbcTemplate().update("delete from info_zg where xxid=?",new Object[]{info_zg.getXxid()});
		}else{
			info_zg.setXxid(getMaxKey("INFO_ZG").toString());
		}
		info_zg.setFbrid(userid);
		info_zg.setSpbz("1");
		insertXxgl(sql_i,info_zg);
		if(lxList != null && !lxList.equals("")){
			this.getJdbcTemplate().update("delete from info_lmzg where xxid=?",new Object[]{info_zg.getXxid()});
			for( int i=0;i< len; i++){
				saveLmzg(info_zg.getXxid(),lxDatas[i]);
			}
		}else{
			throw new BusException("没有选择需要撰写的类型！");
		}
		saveFj(info_zg.getXxid(),fjList,path);
	}
	
	
	public int insertXxgl(String sql,final Info_zg info_zg)
	throws DataAccessException {		
		this.getJdbcTemplate().execute(
				sql,
				new AbstractLobCreatingPreparedStatementCallback(
						this.lobHandler) {
					protected void setValues(PreparedStatement ps,
							LobCreator lobCreator) throws SQLException {
						ps.setString(1, info_zg.getXxid());
						ps.setString(2,info_zg.getXxbt());
						ps.setString(3,info_zg.getFbrid());
						lobCreator.setClobAsString(ps, 4,info_zg.getFbxx());
						ps.setString(5, info_zg.getSpbz());
						ps.setString(6, info_zg.getStatus());	
					}
				});
		return 0;
	}
	
	public int saveFj(String xxid,String fjList,String path){
		String[] fjs = fjList.split(":");
		this.getJdbcTemplate().update("delete from info_zgfj where xxid=?",new Object[]{xxid});
			if(fjList != null && !fjList.equals("")){
				int len = fjs.length;
				String sql[] = new String[len];
				for( int i=0;i< len; i++){
					String f = fjs[i];
					String fjpath =path;
					String fjmc = fjs[i].split(">")[1];
					String fj =fjs[i].split(">")[0];
					String fjlx = "";
					String maxid = getMaxKey("INFO_ZG").toString();
					sql[i] =  " insert into info_zgfj(xxid,fjxh,fj,fjpath,fjlx,fjmc,status) values("+xxid+","+maxid+",'"+fj+"','"+fjpath+"','"+fjlx+"','"+fjmc+"','1')";
					
				}
				this.getJdbcTemplate().batchUpdate(sql);
				
				
		}
		return 1;
	}
	
	public int saveLmzg(String xxid,String lmid){
		
		this.getJdbcTemplate().update("insert into info_lmzg(xxid,lmid,status) values("+xxid+","+lmid+",'1')",new Object[]{});
		return 1;
	}
	
	@SuppressWarnings("unchecked")
	public Page queryZgList(LimitPage limit,String _sql){
		String temp_sql = "";
		if(!_sql.equals("")){
			temp_sql = _sql;
		}
		String sql = "select info_zg.xxid,info_zg.xxbt,info_zg.fbrid,info_lmzg.lmid,dbo.GET_NAME(info_zg.fbrid) as fbrname,info_zg.fbxx,info_zg.spbz,convert(char(10),info_zg.rq,20) as rq,info_zg.sprq," +
		"info_zg.status,dbo.get_lmmc(info_lmzg.lmid) as lmmc,dbo.GET_ZGFJ(info_zg.XXID) AS fj " +
		"from info_zg left join info_lmzg on info_lmzg.xxid = info_zg.xxid  where  1=1  "+temp_sql+" order by info_zg.rq desc ";
		return this.queryForListWithPage(sql, limit);
	}
	
	
	@SuppressWarnings("unchecked")
	public Page queryXxglList(LimitPage limit,String _sql){
		String temp_sql = "";
		if(!_sql.equals("")){
			temp_sql = _sql;
		}
		String sql = "select info_zg.xxid,info_zg.xxbt,info_lmzg.lmid,info_zg.fbrid,dbo.GET_NAME(info_zg.fbrid) as fbrname,info_zg.fbxx,info_zg.spbz,convert(char(10),info_zg.rq,20) as rq,info_zg.sprq," +
				"info_zg.status,dbo.get_lmmc(info_lmzg.lmid) as lmmc,dbo.GET_ZGFJ(info_zg.XXID) AS fj " +
				"from info_zg left join info_lmzg on info_lmzg.xxid = info_zg.xxid where 1=1  "+temp_sql+" order by info_zg.rq desc ";
		return this.queryForListWithPage(sql, limit);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> queryAllLm(){
		String sql = "select * from info_lm";
		return this.getJdbcTemplate().queryForList(sql, new Object[]{});
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> queryLmgl(){
		String sql = "select lmid,lmmc,dbo.GET_LMQX(lmid) as lmqx from info_lm";
		return this.getJdbcTemplate().queryForList(sql, new Object[]{});
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> queryLmqx(String lmid){
		String sql = "";
		if(lmid.equals("")){
			sql = "select userid,cnname,'0' as ischecked from xt_user";
		}else{
			sql = "select userid,cnname,(select count(*) from xt_userlm where lmid="+lmid+" and userid=xt_user.userid) as ischecked from xt_user";
		}
		return this.getJdbcTemplate().queryForList(sql, new Object[]{});
	}
	
	@SuppressWarnings("unchecked")
	public Map queryLmmc(String lmid){
		String sql = "select lmid,lmmc from info_lm where lmid=?";
		return this.getJdbcTemplate().queryForMap(sql, new Object[]{lmid});
	}
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> queryFj(String xxid){
		String sql = "select * from info_zgfj where xxid=?";
		return this.getJdbcTemplate().queryForList(sql, new Object[]{xxid});
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> queryLmzg(String xxid){
		String sql = "select * from info_lmzg where xxid=?";
		return this.getJdbcTemplate().queryForList(sql, new Object[]{xxid});
	}
	
	public void saveZgsp(String spList,String userid){
		String[] xxids = spList.split(",");
		int len = xxids.length;
		String sql[] = new String[len];
		for( int i=0;i< len; i++){
			String xxid = xxids[i];
			
			sql[i] =  " update info_zg set spbz='2' , sprid='"+userid+"' , sprq=getdate() where xxid="+xxid+"";
			
		}
		this.getJdbcTemplate().batchUpdate(sql);
	}
	
	public void saveZght(String spList,String userid){
		String[] xxids = spList.split(",");
		int len = xxids.length;
		String sql[] = new String[len];
		for( int i=0;i< len; i++){
			String xxid = xxids[i];
			
			sql[i] =  " update info_zg set spbz='3' , sprid='"+userid+"' , htrq=getdate() where xxid="+xxid+"";
			
		}
		this.getJdbcTemplate().batchUpdate(sql);
	}
	
	public void saveLmgl(String userList,String lmid,String lmmc){
		String usql = "";
		String curLmid = "";
		if(lmid.equals("")){
			
			curLmid = getMaxKey("INFO_LM").toString();
			usql = "insert into info_lm(lmid,lmmc) values("+curLmid+",'"+lmmc+"')";
		}else{
			curLmid = lmid;
			usql = "update info_lm set lmmc='"+lmmc+"' where lmid="+lmid+"";
			this.getJdbcTemplate().update("delete from xt_userlm where lmid="+lmid+"");
		}
		this.getJdbcTemplate().update(usql);
		
		
		if(!userList.trim().equals("")){
		String[] userids = userList.split(",");
		int len = userids.length;
		String sql[] = new String[len];
	
		for( int i=0;i< len; i++){
			String userid = userids[i];
			sql[i] =  " insert into  xt_userlm(lmid,userid) values("+curLmid+","+userid+")";			
		}
		this.getJdbcTemplate().batchUpdate(sql);
		}
		
	}
	
	public void deleteLmgl(String lmid){
		this.getJdbcTemplate().update("delete from xt_userlm where lmid="+lmid+"");
		this.getJdbcTemplate().update("delete from info_lm where lmid="+lmid+"");
	}
	
	@SuppressWarnings("unchecked")
	public Map queryXxzg(String xxid){
		String sql = "select info_zg.xxid,info_zg.xxbt,info_zg.fbrid,dbo.GET_NAME(info_zg.fbrid) as fbrname,info_zg.fbxx,info_zg.spbz,convert(char(10),info_zg.rq,20) as rq,info_zg.sprq," +
		"info_zg.status,dbo.GET_ZGFJ(info_zg.XXID) AS fj " +
		"from info_zg where info_zg.xxid=? order by info_zg.xxid";
		return this.getJdbcTemplate().queryForMap(sql, new Object[]{xxid});
	}
	
	@SuppressWarnings("unchecked")
	public int checkLmmc(String lmmc){
		String sql = "select count(*) from info_lm where lmmc='"+lmmc+"'";
		return this.getJdbcTemplate().queryForInt(sql);
	}
	public void checkXxglUpdateOpt(String xxid,String userid,String menuid,String lmid){
		String sql = "";
		//判断是否已经审批的信息
		sql = "select count(*) from info_zg where xxid=? and spbz='2'";
		if(this.getJdbcTemplate().queryForInt(sql,new Object[]{xxid})>0){
			throw new BusException("信息已经审批，不能修改！");
		}
			
		//判断是否为自己撰写的信息
		sql = " select fbrid from info_zg where xxid=?";
		String fbrid = 	getSimpleJdbcTemplate().queryForObject(sql,String.class, new Object[]{xxid});
		if(fbrid.equals(userid)){
			return;
		}
		
		//判断是否有修改权限
		sql = "select count(*) from xt_umro where userid=? and menuid=? and opdm='002'";
		if(this.getJdbcTemplate().queryForInt(sql,new Object[]{userid,menuid})<=0){
			throw new BusException("您没有修改操作权限，不能进行修改！");
		}
		
		
		//判断此人是否具有此栏目的修改权限
		sql = "select count(*) from xt_userlm where userid=? and lmid=? ";
		if(this.getJdbcTemplate().queryForInt(sql,new Object[]{userid,lmid})<=0){
			throw new BusException("您没有此栏目的修改操作权限，不能进行修改！");
		}
		
	}
	
	public void checkXxglDeleteOpt(String xxid,String userid,String menuid,String lmid){
		String sql = "";
		//判断是否已经审批的信息
		sql = "select count(*) from info_zg where xxid=? and spbz='2'";
		if(this.getJdbcTemplate().queryForInt(sql,new Object[]{xxid})>0){
			throw new BusException("信息已经审批，不能删除！");
		}
			
		//判断是否为自己撰写的信息
		sql = "select fbrid from info_zg where xxid=?";
		String fbrid = 	getSimpleJdbcTemplate().queryForObject(sql,String.class, new Object[]{xxid});
		if(fbrid.equals(userid)){
			return;
		}
		
		//判断是否有删除权限
		sql = "select count(*) from xt_umro where userid=? and menuid=? and opdm='003'";
		if(this.getJdbcTemplate().queryForInt(sql,new Object[]{userid,menuid})<=0){
			throw new BusException("您没有删除操作权限，不能进行删除！");
		}
		
		
		//判断此人是否具有此栏目的修改权限
		sql = "select count(*) from xt_userlm where userid=? and lmid=? ";
		if(this.getJdbcTemplate().queryForInt(sql,new Object[]{userid,lmid})<=0){
			throw new BusException("您没有此栏目的删除操作权限，不能进行修改！");
		}
		
	}
	
	public void deleteXxzg(String xxid){
		this.getJdbcTemplate().update("delete from info_zg where xxid="+xxid+"");
		this.getJdbcTemplate().update("delete from info_zgfj where xxid="+xxid+"");
		this.getJdbcTemplate().update("delete from info_lmzg where xxid="+xxid+"");
	
	}
}
