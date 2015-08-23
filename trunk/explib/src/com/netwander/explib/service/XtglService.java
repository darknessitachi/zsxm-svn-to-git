package com.netwander.explib.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.fins.gt.model.FilterInfo;
import com.fins.gt.model.SortInfo;
import com.netwander.core.common.LimitPage;
import com.netwander.explib.entity.Xtuser;
import com.netwander.explib.service.common.BaseService;
import com.sun.java_cup.internal.internal_error;

@Service
public class XtglService extends BaseService{
    
    @SuppressWarnings("unchecked")
    public List<Map<String,Object>> getAllLb(){
	return this.getJdbcTemplate().queryForList("select * from xt_dlb");
    }
   
    @SuppressWarnings("unchecked")
    public List<Map<String,Object>> getFlLb(String fldm){
    	return this.getJdbcTemplate().queryForList("select * from xt_dlb_fldm where fldm='"+fldm+"'");
    	//return this.getJdbcTemplate().queryForList("select * from xt_dlb_fldm where lbid in (13,14)");
    }
    public List<Map<String,Object>> getExpFl(String roledm,String userid){
    	String sql = "";
    	if(roledm.equals("01")){
    		sql = " select fldm,fldm+' '+flmc flmc from exp_fl order by fldm";
    	}else{
    		sql = " select fldm,fldm+' '+flmc flmc from exp_fl where fldm in (select userfl from xt_user_fl where userid="+userid+") order by fldm ";
    	}
    	return this.getJdbcTemplate().queryForList(sql);
    }
    
    @SuppressWarnings("unchecked")
    public List<Map<String,Object>> getDicts(String lb,String bh){
	int length = bh.length() + 3 ; 
	if(bh.equals("root")){
	    bh = "";
	    length = 3;
	}
	String sql = "select lbid,dictid,dictname,dictbh,enable,sort,(select count(lbid) from xt_dict " +
	    "where dictid <>a.dictid and lbid=a.lbid and dictbh like a.dictbh + '%' ) leaf from xt_dict a " +
	    "where lbid =? and dictbh like ? and len(dictbh)=? order by dictbh";
	List list =  this.getJdbcTemplate().queryForList(sql,
		new Object[]{Long.valueOf(lb),bh+"%",length});
	return list ;
    }
    
    @SuppressWarnings("unchecked")
    public List<Map<String,Object>> getDictfls(String lb,String bh,String fldm){
	int length = bh.length() + 3 ; 
	if(bh.equals("root")){
	    bh = "";
	    length = 3;
	}
	String sql = "select lbid,dictid,dictname,dictbh,enable,sort,(select count(lbid) from xt_dict_fldm " +
	    "where dictid <>a.dictid and lbid=a.lbid and dictbh like a.dictbh + '%' and fldm='"+fldm+"') leaf from xt_dict_fldm a " +
	    "where lbid =? and dictbh like ? and len(dictbh)=?  and fldm='"+fldm+"' order by dictbh";
	List list =  this.getJdbcTemplate().queryForList(sql,
		new Object[]{Long.valueOf(lb),bh+"%",length});
	return list ;
    }
    
    @SuppressWarnings("unchecked")
    public List<Map<String,Object>> getRclbTree(String bh){
	int length = bh.length() + 3 ; 
	if(bh.equals("root")){
	    bh = "0";
	    length = 3;
	}
	
	String sql = "select lbdm,lbmc,status ,(select count(lbdm) from rc_rclb where lbdm like a.lbdm+'%' and lbdm <> a.lbdm ) leaf " +
		    " from rc_rclb  a where  a.lbdm like ? and len(a.lbdm)=? order by a.lbdm";
	
	List list =  this.getJdbcTemplate().queryForList(sql,
		new Object[]{bh+"%",length});
	return list ;
    }
    
    @SuppressWarnings("unchecked")
    public Map<String , Object> getLbData(Map<String,String> query){
	String lb = query.get("lbid");
	return  this.jdbcTemplate.queryForMap("select * from xt_dlb where lbid =? ", new Object[]{Long.valueOf(lb)});
    }
    
    @SuppressWarnings("unchecked")
    public Map<String , Object> getAddDictData(Map<String,String> query){
	
	Map<String , Object>  map = new HashMap<String , Object>();
	String lb = query.get("lbid");
	Map<String,Object> lbmap = this.jdbcTemplate.queryForMap("select * from xt_dlb where lbid =? ", new Object[]{Long.valueOf(lb)});
	int frame = ((BigDecimal) lbmap.get("LBFRAME")).intValue();
	if(frame == 1){
	    String bh = query.get("dictbh");
	    int   len = bh.length();
	    String type=query.get("type");
	    if(type.equals("1")){
		bh = bh.substring(0,len -3);
	    }
	    if(bh.length() >= 3){
		 List dictlist = this.jdbcTemplate.queryForList("select * from xt_dict where lbid=? and dictbh=?",  new Object[]{Long.valueOf(lb),bh});
		 map.put("pdictmap", dictlist.get(0));
	    }else{
		map.put("pdictmap", new HashMap());
	    }
	}
	map.put("lbmap", lbmap);
	
	return map;
    }
    
    @SuppressWarnings("unchecked")
    public Map<String , Object> getAddDictDatafl(Map<String,String> query){
	
	Map<String , Object>  map = new HashMap<String , Object>();
	String lb = query.get("lbid");
	Map<String,Object> lbmap = this.jdbcTemplate.queryForMap("select * from xt_dlb where lbid =? ", new Object[]{Long.valueOf(lb)});
	int frame = ((BigDecimal) lbmap.get("LBFRAME")).intValue();
	if(frame == 1){
	    String bh = query.get("dictbh");
	    int   len = bh.length();
	    String type=query.get("type");
	    if(type.equals("1")){
		bh = bh.substring(0,len -3);
	    }
	    if(bh.length() >= 3){
		 List dictlist = this.jdbcTemplate.queryForList("select * from xt_dict_fldm where fldm='"+query.get("fldm")+"' and lbid=? and dictbh=?",  new Object[]{Long.valueOf(lb),bh});
		 map.put("pdictmap", dictlist.get(0));
	    }else{
		map.put("pdictmap", new HashMap());
	    }
	}
	map.put("lbmap", lbmap);
	
	return map;
    }
    
    public Map<String,String> addDict(Map<String,String> query){
	String type = query.get("type");
	String bh   = query.get("dictbh")==null?"":query.get("dictbh");
	String perbh = "";
	
	int bhlen = 3;
	if(type.equals("1")){//新增本级
	    if(bh.length() > 3){
		bhlen = bh.length();
		perbh = bh.substring(0,bh.length()  - 3);
	    }
	}else{//新增下级
	    bhlen = bh.length() + 3;
	    perbh = bh;
	}
	String sql ="select isnull(max(dictbh),0) + 1 from xt_dict where len(dictbh) = ? and dictbh like ? and lbid = ? ";
	String dictbh  = String.valueOf(this.getJdbcTemplate().queryForLong(sql, new Object[]{bhlen,perbh+"%",Long.valueOf(query.get("lbid"))}));
	String zero = "";
	int value = dictbh.length() % 3;
	if(value>0){
		for(int i=0;i<3-value;i++){
			zero += "0";
		}
	}
        dictbh = zero + dictbh;
        
        if(bhlen != dictbh.length())
            dictbh = bh + dictbh;
        int maxID = (Integer) getMaxKey("XT_DICT", 1);
        String isql ="insert into xt_dict(dictid,lbid,dictname,dictbh,enable,sort) values(?,?,?,?,?,0)";
        Object[] values = new Object[]{maxID,Long.valueOf(query.get("lbid")),query.get("dictname"),dictbh,1};
        this.jdbcTemplate.update(isql,values);
        query.put("dictbh", dictbh);
	return query;
    }
    

    public Map<String,String> addDictfl(Map<String,String> query){
	String type = query.get("type");
	String bh   = query.get("dictbh")==null?"":query.get("dictbh");
	String perbh = "";
	
	int bhlen = 3;
	if(type.equals("1")){//新增本级
	    if(bh.length() > 3){
		bhlen = bh.length();
		perbh = bh.substring(0,bh.length()  - 3);
	    }
	}else{//新增下级
	    bhlen = bh.length() + 3;
	    perbh = bh;
	}
	String sql ="select isnull(max(dictbh),0) + 1 from xt_dict_fldm where fldm='"+query.get("fldm")+"' and len(dictbh) = ? and dictbh like ? and lbid = ? ";
	String dictbh  = String.valueOf(this.getJdbcTemplate().queryForLong(sql, new Object[]{bhlen,perbh+"%",Long.valueOf(query.get("lbid"))}));
	String zero = "";
	int value = dictbh.length() % 3;
	if(value>0){
		for(int i=0;i<3-value;i++){
			zero += "0";
		}
	}
        dictbh = zero + dictbh;
        
        if(bhlen != dictbh.length())
            dictbh = bh + dictbh;
        int maxID = (Integer) getMaxKey("XT_DICT", 1);
        String isql ="insert into xt_dict_fldm(fldm,dictid,lbid,dictname,dictbh,enable,sort) values('"+query.get("fldm")+"',?,?,?,?,?,0)";
        Object[] values = new Object[]{maxID,Long.valueOf(query.get("lbid")),query.get("dictname"),dictbh,1};
        this.jdbcTemplate.update(isql,values);
        query.put("dictbh", dictbh);
	return query;
    }
    
    @SuppressWarnings("unchecked")
    public Map<String,Object> getDictMap(Map<String,String> query){

	return this.getJdbcTemplate().queryForMap("select * from xt_dict where lbid=? and dictbh=?",new Object[]{Long.valueOf(query.get("lbid")),query.get("dictbh")});
    }
    
    @SuppressWarnings("unchecked")
    public Map<String,Object> getDictflMap(Map<String,String> query){

	return this.getJdbcTemplate().queryForMap("select * from xt_dict_fldm where fldm='"+query.get("fldm")+"' and lbid=? and dictbh=?",new Object[]{Long.valueOf(query.get("lbid")),query.get("dictbh")});
    }
    
    public void updateDict(Map<String,String> map){
	  this.getJdbcTemplate().update("update xt_dict set dictname=? where lbid=? and dictbh=?" ,new Object[]{map.get("dictname"),Long.valueOf(map.get("lbid")),map.get("dictbh")});
    }
    
    public void updateDictfl(Map<String,String> map){
  	  this.getJdbcTemplate().update("update xt_dict_fldm set dictname=? where lbid=? and dictbh=? and fldm='"+map.get("fldm")+"'",new Object[]{map.get("dictname"),Long.valueOf(map.get("lbid")),map.get("dictbh")});
      }
    
    public void deletedict(Map<String,String> query){
	Long lbid = Long.valueOf(query.get("lbid"));
	String[] bhs = query.get("dictbh").split(",");
	List<Object[]> list = new ArrayList<Object[]>();
	for(String bh : bhs){
	    list.add(new Object[]{lbid,bh});
	}

	
	this.getSimpleJdbcTemplate().batchUpdate("delete xt_dict where lbid=? and dictbh=?", list);
    }
    
    public void deletedictfl(Map<String,String> query){
    	Long lbid = Long.valueOf(query.get("lbid"));
    	String[] bhs = query.get("dictbh").split(",");
    	List<Object[]> list = new ArrayList<Object[]>();
    	for(String bh : bhs){
    	    list.add(new Object[]{lbid,bh});
    	}

    	
    	this.getSimpleJdbcTemplate().batchUpdate("delete xt_dict_fldm where lbid=? and dictbh=? and fldm='"+query.get("fldm")+"'", list);
    }
    
	@SuppressWarnings("unchecked")
	public Object getAllUser(LimitPage limit, Map parMap, SortInfo sortInfo, List<FilterInfo> filterInfos) {

		String query_sql = "select userid,loginname,cnname ,rolemc,bmmc,zw,px from xt_user a,xt_role b where  " +
				" a.roledm=b.roledm and a.loginname!='admin'  " ;
		
		String sortCond = getGridSortString(sortInfo);
		String filterCond = getGridFilterString(filterInfos);
		
		if (filterCond != null) {
		    query_sql += filterCond;
		}
		if (sortCond != null) {
		    query_sql += " order by " + sortCond.split("_")[0];
		} else {
		    query_sql += " order by a.px ";
		}
		if (limit != null) {
			return this.queryForListWithPage(query_sql, limit);
		} else {
			return this.simpleJdbcTemplate.queryForList(query_sql);
		}
	}
	
	public void deleteUser(Map<String,String> query){
	    String[] bhs = query.get("userid").split(",");
    	    List<Object[]> list = new ArrayList<Object[]>();
    	    for(String bh : bhs){
    		list.add(new Object[]{Long.valueOf(bh)});
    	    }
		
    	    this.getSimpleJdbcTemplate().batchUpdate("delete xt_user where userid=? ", list);
    	    this.getSimpleJdbcTemplate().batchUpdate("delete xt_user_menu where userid=? ", list);
	}
	
	public void editUser(Map<String,String> query){
	    String userid = query.get("userid") == null ? "": query.get("userid").trim();
	    if(userid.equals("")){ //保存操作
	    	int maxID = (Integer) getMaxKey("XT_USER", 1);
			String sql ="insert into xt_user (userid,loginname,cnname,password,roledm,bmmc,zw,px) values (?,?,?,?,?,?,?,?)";
			this.jdbcTemplate.update(sql,new Object[]{maxID,query.get("loginname"),query.get("cnname"),
				query.get("password"),query.get("roledm"),query.get("bmmc"),query.get("zw"),
				query.get("px").trim().equals("")?null:query.get("px")});
			
			sql = " delete from xt_user_fl where userid="+maxID;
			this.getSimpleJdbcTemplate().update(sql);
			
			if(query.get("userfl") != null && !query.get("userfl").equals("")){
				String[] fldm = query.get("userfl").split(",");
				for(int i = 0;i<fldm.length;i++){
					if(fldm[i] != null && !fldm[i].trim().equals("") && !fldm[i].trim().equals("root")){
						sql = " insert into xt_user_fl(userid,userfl) values("+maxID+",'"+fldm[i]+"')";
						this.getSimpleJdbcTemplate().update(sql);
					}
				}
			}
			
	    }else{
	    	String sql ="update  xt_user set loginname = ?,cnname=?,password=?,roledm=?,bmmc=?,zw=?,px=? where userid="+userid+"";
	    	this.jdbcTemplate.update(sql,new Object[]{query.get("loginname"),query.get("cnname"),
				query.get("password"),query.get("roledm"),query.get("bmmc"),query.get("zw"),
				query.get("px").trim().equals("")?null:query.get("px")});
	    	
	    	sql = " delete from xt_user_fl where userid="+userid;
			this.getSimpleJdbcTemplate().update(sql);
			
			if(query.get("userfl") != null && !query.get("userfl").equals("")){
				String[] fldm = query.get("userfl").split(",");
				for(int i = 0;i<fldm.length;i++){
					if(fldm[i] != null && !fldm[i].trim().equals("") && !fldm[i].trim().equals("root")){
						sql = " insert into xt_user_fl(userid,userfl) values("+userid+",'"+fldm[i]+"')";
						this.getSimpleJdbcTemplate().update(sql);
					}
				}
			}
	    }
	    
	}
	
	public boolean checkLoginname(Map<String,String> query){
	    boolean flag = true;
	    String userid = query.get("userid") == null ? "": query.get("userid").trim();
	    int count = 0 ;
	    if(userid.equals("")){ //保存操作
		count =  this.getJdbcTemplate().queryForInt("select count(*) from xt_user where lower(loginname)= ? ",new Object[]{query.get("loginname").toLowerCase()});
	    }else{
		count =  this.getJdbcTemplate().queryForInt("select count(*) from xt_user where lower(loginname)= ? and userid !=? ",new Object[]{query.get("loginname").toLowerCase(),Long.valueOf(userid)});
	    }
	    if(count > 0){
		flag = false;
	    }
	    return flag;
	}
	
	@SuppressWarnings("unchecked")
	public Map getUser(Map<String,String> query){
	    try{
		 String userid = query.get("userid") == null ? "": query.get("userid").trim();
		 if(userid.equals("")){ //保存操作
		     return new HashMap<String,Object>();
		  }else{
		      return this.getJdbcTemplate().queryForMap("select * from xt_user where userid=? ",new Object[]{Long.valueOf(userid)});		  
		  }
	    }catch(EmptyResultDataAccessException e){
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("ERRORCODE", "0");
		 return map;
	    }
	}
	
	public List<Map<String,Object>> getRole(){
	    return this.getJdbcTemplate().queryForList("select * from xt_role where roledm!='000' order by roledm desc ");
	}
	
	public boolean checkPassword(String loginname,String password,Integer loginbz){
	    String checkSql = "";
	    if(loginbz == 1){
	    	checkSql = "select count(*) from xt_user where loginname=? and password=?";
	    }else{
	    	checkSql = "select count(*) from rc_user where loginname=? and password=?";
	    }
	    int count = this.getJdbcTemplate().queryForInt(checkSql,new Object[]{loginname,password});
	    boolean flag = true;
	    if(count == 0){
		flag = false;
	    }
	    return flag;
	}
	
	public void updatePassword(String loginname,String password,Integer loginbz){
		String updateSql = "";
		if(loginbz == 1){
			updateSql = " update xt_user set password=? where  loginname=?";
		}else{
			updateSql = " update rc_user set password=? where loginname=?";
		}
	   
	    this.getJdbcTemplate().update(updateSql,new Object[]{password,loginname});
	}
	

	@SuppressWarnings("unchecked")
	public Object getAllLog(LimitPage limit, Map parMap, SortInfo sortInfo, List<FilterInfo> filterInfos,Xtuser xtuser) {

		String query_sql = "select logid,loginname,cnname,czsm,czinfo,CONVERT(varchar(12) , intime , 23 ) intime,menuid_mc ,optionid  from xt_syslog_v			  where 1=1 " ;
		
		query_sql += "  #### ";
		String wSql      = " ";
		String czsm = (String) (parMap.get("czsm") == null ? "" :parMap.get("czsm"));
		if(!czsm.trim().equals("")){
		    wSql +=" and czsm like '%"+czsm.trim()+"%' ";
		}
		String czsj1 = (String) (parMap.get("czsj1") == null ? "" :parMap.get("czsj1"));
		if(!czsj1.trim().equals("")){
		    wSql +=" and intime >= Cast('"+czsj1+"' As DateTime) ";
		}
		String czsj2 = (String) (parMap.get("czsj2") == null ? "" :parMap.get("czsj2"));
		if(!czsj2.trim().equals("")){
		    wSql +=" and intime <= Cast('"+czsj2+"' As DateTime) ";
		}

		String sortCond = getGridSortString(sortInfo);
		String filterCond = getGridFilterString(filterInfos);
		
		if (filterCond != null) {
		    wSql += filterCond;
		}
		if (sortCond != null) {
		    query_sql += " order by " + sortCond.split("_")[0];
		} else {
		    query_sql += " order by logid desc";
		}
		query_sql = query_sql.replace("####", wSql);
		if (limit != null) {
			return this.queryForListWithPage(query_sql, limit);
		} else {
			return this.simpleJdbcTemplate.queryForList(query_sql);
		}

	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> getRclbMap(Map<String,String> query){
	    return this.getJdbcTemplate().queryForMap("select * from rc_rclb where lbdm=?",new Object[]{query.get("lbdm")});
	}
	
	
	
	  public Map<String,String> addRclb(Map<String,String> query){
		String type = query.get("type");
		String bh   = query.get("lbdm")==null?"":query.get("lbdm");
		String perbh = "0"; 
		int bhlen = 3;
		if(type.equals("1")){//新增本级
		    if(bh.length() > 3){
			bhlen = bh.length();
			perbh = bh.substring(0,bh.length()  - 3);
		    }
		}else{//新增下级
		    bhlen = bh.length() + 3;
		    perbh = bh;
		}
		String sql ="select isnull(max(lbdm),0) + 1 from rc_rclb where len(lbdm) = ? and lbdm like ? ";
		String dictbh  = String.valueOf(this.getJdbcTemplate().queryForLong(sql, new Object[]{bhlen,perbh+"%"}));
		String zero = "";
		int value = dictbh.length() % 3;
		if(value>0){
			for(int i=0;i<3-value;i++){
				zero += "0";
			}
		}
	        dictbh = zero + dictbh;
	        
	        if(bhlen != dictbh.length())
	            dictbh = bh + dictbh;
	
	        String isql ="insert into rc_rclb (lbdm,lbmc,status) values(?,?,1)";
	        Object[] values = new Object[]{dictbh,query.get("lbmc")};
	        this.jdbcTemplate.update(isql,values);
	        query.put("lbdm", dictbh);
		return query;
	    }
	  
	    public void updateRclb(Map<String,String> map){
		  this.getJdbcTemplate().update("update rc_rclb set lbmc=? where lbdm=?",new Object[]{map.get("lbmc"),map.get("lbdm")});
	    }
	    
	    @SuppressWarnings("unchecked")
	    public Map<String , Object> getAddRclbData(Map<String,String> query){
			
			Map<String , Object>  map = new HashMap<String , Object>();
		
			String bh = query.get("lbdm");
			int   len = bh.length();
			String type=query.get("type");
			if(type.equals("1")){
			    bh = bh.substring(0,len -3);
			}
			if(bh.length() >= 3){
			    List dictlist = this.jdbcTemplate.queryForList("select * from rc_rclb where lbdm=?",  new Object[]{bh});
			    map.put("pdictmap", dictlist.get(0));
			}else{
			    map.put("pdictmap", new HashMap());
			}
			return map;
	    }
	    
	    public void deleteRclb(Map<String,String> query){
		String[] bhs = query.get("lbdm").split(",");
		List<Object[]> list = new ArrayList<Object[]>();
		for(String bh : bhs){
		    list.add(new Object[]{bh});
		}
		
		this.getSimpleJdbcTemplate().batchUpdate("delete rc_rclb where lbdm=? ", list);
	    }
    
}
