package com.netwander.explib.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Service;

import com.netwander.core.common.LimitPage;
import com.netwander.core.common.Page;
import com.netwander.explib.entity.Xtuser;
import com.netwander.explib.service.common.BaseService;

@Service
public class MessageService extends BaseService{
	@SuppressWarnings("unchecked")
	public Page getMessagePage(LimitPage limit, Map parMap) {

		String query_sql = "select mid, mfromdm,mfromname,mtodm,mtoname,mtype, CONVERT(varchar(10) , mrq , 120 ) mrq,mtitle,mread " +
				   "from xt_message a where mtodm =? and isNull(bz,0)=0 order by mid desc";
		Page page = this.queryForListWithPage(query_sql, limit,parMap.get("mtodm"));
		
		return page;
	}
	
	public Page getSendMessagePage(LimitPage limit, Map parMap) {

		String query_sql = "select mid, mfromdm,mfromname,mtodm,mtoname,mtype, CONVERT(varchar(10) , mrq , 120 ) mrq,mtitle,mread " +
				   "from xt_message  where mfromdm =? and isNull(bz,0)=0 order by mid desc" ;
				   
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
	
	    /**
	     * 新增消息
	     * @param from_d	发送者
	     * @param to_d		接收者(ALL：全部)
	     * @param type		类型(1：内部通知	2：收件箱)
	     * @param message	消息内容
	     * @return
	     */ 
	    public int doAddMessage(Map<String, String> mess){
		
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
	    	    Object[] obj = new Object[]{id,usermcs[i],userdms[i],mess.get("mfromname"),mess.get("mfromdm")
	    		     ,mess.get("mtitle"),"1",mess.get("mnr")};
	    	    values.add(obj);
	    	}

	    	this.getSimpleJdbcTemplate().batchUpdate(OPTION_SQL, values);
	    	return  maxID;
	    }
	    
	    public void deleteMessage(String strid,String username){
		String[] ids = strid.split(",");
		List<Object[]> values = new ArrayList<Object[]>();
		for(String id : ids ){
		    values.add(new Object[]{Long.valueOf(id),username,username});
		}
		this.getSimpleJdbcTemplate().batchUpdate("delete xt_message where mid = ? and (mtodm=? or mfromdm=? )", values);
	    }
	    
	    public List<Map<String,Object>> getAllUser(){
		return this.jdbcTemplate.queryForList("select loginname userdm,cnname usermc  from xt_user order by isnull(px,99999) ");
	    } 
	    
	    public List<Map<String,Object>> getRcUser(Xtuser xtuser){
	    	String sql = "select loginname userdm, rcname usermc  from rc_user 1=1 ";
	    	
	    	
	    	
	    	sql += "  order by rcid ";
	    	return this.jdbcTemplate.queryForList(sql);
	    } 
	    
	    public List<Map<String,Object>> getRcUserByBm(String roledm,String bmtype,String bmdm){
	    	String sql = "";
	    	if(!roledm.equals("000")){
	    		if(bmtype.equals("1")){
	    			sql = " select loginname userdm, rcname usermc  from rc_user where xtrcid in" +
	    					" ( select rcid from rc_pinfo where rcid in ( select distinct rcid from rc_bpzjqk where " +
						" bpzjqk in ( select zjfldm from gx_bmzjqk where bmdm='"+bmdm+"') ) and status=1 ) ";
	    		}else{
	    			sql = " select loginname userdm, rcname usermc  from rc_user where xtrcid in ( select rcid from rc_pinfo where zgbm ='"+bmdm+"' and status=1 ) ";
	    		}
	    	}else{
	    		sql = "select loginname userdm, rcname usermc  from rc_user order by rcid ";
	    	}
	    	return this.jdbcTemplate.queryForList(sql);
	    }
	    
	    public List<Map<String,Object>> getRcUserByWhere(Map<String,String> parMap,Xtuser xtuser){
	    	String name = parMap.get("name") == null ? "" :  parMap.get("name");
			String where = parMap.get("where") == null ? "" :  parMap.get("where");
	    	String ls_sql = "select rcname usermc,rcid userdm from exp_main_v where 1=1 " ;
	    	
	    	if(where != null && !where.equals("") && !name.equals("")){
				if(where.equals("gznr")){
					ls_sql += " and rcid in ( select distinct rcid from EXP_GZ where gzmc like '"+name+"%')";
				}else{
					String[] ws =  where.split("#");
					if(ws[1].equals("text")){
						if(ws[0].equals("info")){
							ls_sql += " and rcid in (select rcid from exp_main where info like '%" + name + "%')";
						}else{
							ls_sql += " and "+ws[0]+" like '%" + name + "%'";
						}
						
					}else if(ws[1].equals("select")){
						if(ws[0].split("&").length > 1 ){
							ls_sql += " and rcid in ( select distinct rcid from "+ws[0].split("&")[0]+" where "+ws[0].split("&")[1]+" like '"+name+"%')";
						}else{
							ls_sql += " and "+ws[0]+" like '" + name + "%'";
						}
					}else if(ws[1].equals("date")){
						ls_sql += " and convert(varchar(100),"+ws[0]+",23) <= '" + name + "'";
					}else if(ws[1].equals("other")){
						if(ws[0].equals("fldm")){
							ls_sql += " and rcid in ( select distinct rcid from exp_bymx where fldm like '"+name+"%') ";
						}
					}else {
						ls_sql += " and "+ws[0]+" = '" + name + "'";
					}
				}
				
			}
	    	
	    	ls_sql += " order by rcid ";
	    	return this.getJdbcTemplate().queryForList(ls_sql);
	    }
	    
	
}
