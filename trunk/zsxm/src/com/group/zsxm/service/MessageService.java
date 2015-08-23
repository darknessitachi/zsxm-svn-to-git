package com.group.zsxm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Service;

import com.group.core.common.LimitPage;
import com.group.core.common.Page;
import com.group.zsxm.service.common.BaseService;

@Service
public class MessageService extends BaseService{
	@SuppressWarnings("unchecked")
	public Page getMessagePage(LimitPage limit, Map parMap) {

		String query_sql = "select mid, mfromdm,mfromname,mtodm,mtoname,mtype, CONVERT(varchar(10) , mrq , 120 ) mrq,mtitle,mread " +
				   "from xt_message a where mtodm =? and isNull(bz,0)=0  order by mid desc";
		Page page = this.queryForListWithPage(query_sql, limit,parMap.get("mtodm"));
		
		return page;
	}
	
	public Page getSendMessagePage(LimitPage limit, Map parMap) {

		String query_sql = "select mid, mfromdm,mfromname,mtodm,mtoname,mtype, CONVERT(varchar(10) , mrq , 120 ) mrq,mtitle,mread " +
				   "from xt_message  where mfromdm =? and isNull(bz,0)=0  order by mid desc" ;
				   
	        Page page = this.queryForListWithPage(query_sql, limit,parMap.get("mfromdm"));
		return page;
	}
	
	
	public Page getZyMessagePage(LimitPage limit, Map parMap) {

		String query_sql = "select mid, mfromdm,mfromname,mtodm,mtoname,mtype, CONVERT(varchar(10) , mrq , 120 ) mrq,mtitle,mread " +
				   "from xt_message  where mtodm =? and isNull(bz,0)=5 order by mid desc" ;
				   
	        Page page = this.queryForListWithPage(query_sql, limit,parMap.get("mtodm"));
		return page;
	}
	
	/**
	 * 将邮件移至重要邮箱 ： 将BZ 更成 5
	 * @return
	 */
	public int doRemoveMessage(String mid){
		String sql = " update xt_message set bz=5 where mid in ("+mid+")";
		this.getSimpleJdbcTemplate().update(sql);
		return 1;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> getMessage(Long messgeId,String username){
	    try{
		this.getJdbcTemplate().update("update  xt_message set mread = 0 where mid=?",new Object[]{messgeId});
		return this.getJdbcTemplate().queryForMap("select * from xt_message where mid=? and (mtodm=? or mfromdm=? )",new Object[]{messgeId,username,username});
	    }catch(Exception e){
		return new HashMap<String,Object>();
	    }
	}
	
	public int getXxfj(Long messgeId){
		String sql = " select count(*) from info_xxfj where xxid='"+messgeId+"'";
		return this.getSimpleJdbcTemplate().queryForInt(sql);
	}
	
	public int getMessageSize(String username){
	    return this.getJdbcTemplate().queryForInt("select count(mid) from xt_message where mtodm = ? and mread=1",new Object[]{username});
	}

	@SuppressWarnings("unchecked")
	public Map<String,Object> getMessage(String username){
	    try{
		String sql ="select MID,MFROMDM,MFROMNAME,MTODM,MTONAME,MTITLE,MTYPE,MREAD,CONVERT(varchar(20) , MRQ , 120 ) MRQ,MNR from xt_message a where mid=(select max(mid) from xt_message where a.mtodm = mtodm and mread=1) and mtodm=? ";
		Map<String,Object> map  = this.getJdbcTemplate().queryForMap(sql,new Object[]{username});
		this.getJdbcTemplate().update("update  xt_message set mread = 0 where mid=?",new Object[]{map.get("MID")});
		int size = this.getJdbcTemplate().queryForInt("select count(mid) from xt_message where mtodm = ? and mread=1",new Object[]{username});
		map.put("msize", size);
		return map;
	    }catch(Exception e){
		return new HashMap<String,Object>();
	    }
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> queryFj(String xxid){
		String sql = "select * from info_xxfj where xxid='"+xxid+"'";
		return this.getJdbcTemplate().queryForList(sql);
	}
	
	@SuppressWarnings("unchecked")
	public String getFjName(String xxid,String fjid){
		return getSimpleJdbcTemplate().queryForObject("select fjmc from info_xxfj where xxid=? and fjxh=? ",String.class, new Object[]{xxid,fjid});
	}
	    /**
	     * 新增消息
	     * @param from_d	发送者
	     * @param to_d		接收者(ALL：全部)
	     * @param type		类型(1：内部通知	2：收件箱)
	     * @param message	消息内容
	     * @return
	     */ 
	    public int doAddMessage(Map<String, String> mess,Map<String, String> parMap,String path){
		
		List<Object[]> values = new ArrayList<Object[]>();
	    	String[] userdms = mess.get("userdms").split(",");
	    	String[] usermcs = mess.get("usermcs").split(",");
	    	int      len     = userdms.length;
		
	    	String OPTION_SQL = "";
		int maxID = (Integer) getMaxKey("XT_MESSAGE", len);
	
	    	OPTION_SQL = " insert into xt_message(mid,mtoname,mtodm,mfromname,mfromdm,mrq,mtitle,mread,mnr) values" +
	    	             "(?,?,?,?,?,GETDATE(),?,?,?)";
	    	
	    	for(int i = 0 ; i < len ; i++){
	    	    long id = maxID + i;
	    	    Object[] obj = new Object[]{id,String.valueOf(this.getSimpleJdbcTemplate().queryForObject("select cnname from xt_user where loginname='"+userdms[i]+"'",String.class)),userdms[i],mess.get("mfromname"),mess.get("mfromdm")
	    		     ,mess.get("mtitle"),"1",mess.get("mnr")};
	    	    values.add(obj);
	    	}

	    	this.getSimpleJdbcTemplate().batchUpdate(OPTION_SQL, values);
	    	saveFj(String.valueOf(maxID),parMap.get("fjList").toString(),path);
	    	return  maxID;
	    }
	    
		public int saveFj(String xxid,String fjList,String path){
			String[] fjs = fjList.split(":");
			this.getJdbcTemplate().update("delete from info_xxfj where xxid=?",new Object[]{xxid});
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
						sql[i] =  " insert into info_xxfj(xxid,fjxh,fj,fjpath,fjlx,fjmc,status) values("+xxid+","+maxid+",'"+fj+"','"+fjpath+"','"+fjlx+"','"+fjmc+"','1')";
						
					}
					this.getJdbcTemplate().batchUpdate(sql);
					
					
			}
			return 1;
		}
		
	    public void deleteMessage(String strid,String username){
		String[] ids = strid.split(",");
		List<Object[]> values = new ArrayList<Object[]>();
		for(String id : ids ){
		    values.add(new Object[]{Long.valueOf(id),username,username});
		}
		this.getSimpleJdbcTemplate().batchUpdate("delete xt_message where mid = ? and (mtodm=? or mfromdm=? )", values);
	    }
	    
	    public List<Map<String,String>> getAllUser(){
		return this.jdbcTemplate.queryForList("select loginname userdm,cnname usermc,bm  from xt_user where  loginname!='admin'   order by isnull(px,9999999)  ");
	    } 
	    
	    public List<Map<String,String>> getUser(Map<String,String> query){
	    	String sql = "";
	    	sql = " select loginname userdm,cnname usermc,isNull(bm,' ') bm  from xt_user where loginname!='admin' and 1=1 " ;
	    	if(query != null && !query.get("name").equals("")){
	    		sql += " and isnull("+query.get("name")+",'') like "+"'%"+query.get("value")+"%'";
	    	}
	    	sql += "  order by isnull(px,9999999) " ;
	    	return this.jdbcTemplate.queryForList(sql);
	    }
	    
	    public List<Map<String,Object>> getRcUser(){
		return this.jdbcTemplate.queryForList("select loginname userdm, rcname usermc  from rc_user order by rcid ");
	    } 
	    
	
}
