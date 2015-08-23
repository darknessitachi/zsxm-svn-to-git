package com.group.zsxm.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.fins.gt.model.FilterInfo;
import com.fins.gt.model.SortInfo;
import com.group.core.common.LimitPage;
import com.group.core.common.Page;
import com.group.zsxm.entity.Xtuser;
import com.group.zsxm.service.common.BaseService;

@Service
public class XtmanagerService extends BaseService{
    
    @SuppressWarnings("unchecked")
    public List<Map<String,Object>> getAllLb(){
	return this.getJdbcTemplate().queryForList("select * from xt_dlb");
    }
    
    @SuppressWarnings("unchecked")
    public List<Map<String,Object>> getDicts(String lb,String bh){
	int length = bh.length() + 3 ; 
	if(bh.equals("root")){
	    bh = "0";
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
    
    public Map<String,String> addDict(Map<String,String> query){
	String type = query.get("type");
	String bh   = query.get("dictbh")==null?"":query.get("dictbh");
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
	String sql ="select isnull(max(dictbh),0) + 1 from xt_dict where len(dictbh) = ? and dictbh like ? and lbid = ? and sort != 1";
	String dictbh  = String.valueOf(this.getJdbcTemplate().queryForLong(sql, new Object[]{bhlen,perbh+"%",Long.valueOf(query.get("lbid"))}));
	String zero = "";
	int value = dictbh.length() % 3;
	if(value>0){
		for(int i=0;i<3-value;i++){
			zero += "0";
		}
	}
        dictbh = zero + dictbh;
        String sort = transToS(query.get("sort")).equals("")?"NULL":transToS(query.get("sort"));
        String pch = transToS(query.get("pch")).equals("")?"":transToS(query.get("pch"));
        if(bhlen != dictbh.length())
            dictbh = bh + dictbh;
        int maxID = (Integer) getMaxKey("XT_DICT", 1);
        String isql ="insert into xt_dict(dictid,lbid,dictname,dictbh,enable,sort) values(?,?,?,?,?,"+sort+")";
        Object[] values = new Object[]{maxID,Long.valueOf(query.get("lbid")),query.get("dictname"),dictbh,1};
        this.jdbcTemplate.update(isql,values);
        query.put("dictbh", dictbh);
	return query;
    }
    
    @SuppressWarnings("unchecked")
    public Map<String,Object> getDictMap(Map<String,String> query){

	return this.getJdbcTemplate().queryForMap("select * from xt_dict where lbid=? and dictbh=?",new Object[]{Long.valueOf(query.get("lbid")),query.get("dictbh")});
    }
    
    public void updateDict(Map<String,String> map){
    	 String sort = transToS(map.get("sort")).equals("")?"NULL":transToS(map.get("sort"));
    	 String pch = transToS(map.get("pch")).equals("")?"":transToS(map.get("pch"));
	  this.getJdbcTemplate().update("update xt_dict set dictname=?,sort="+sort+"  where lbid=? and dictbh=?",new Object[]{map.get("dictname"),Long.valueOf(map.get("lbid")),map.get("dictbh")});
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
    
    
	@SuppressWarnings("unchecked")
	public Object getAllUser(LimitPage limit, Map parMap, SortInfo sortInfo, List<FilterInfo> filterInfos) {

		String query_sql = "select userid,loginname,cnname,bm,zw,px ,rolemc from xt_user a,xt_role b  where a.loginname!='admin' and a.roledm=b.roledm  " ;
		
		String sortCond = getGridSortString(sortInfo);
		String filterCond = getGridFilterString(filterInfos);
		
		if (filterCond != null) {
		    query_sql += filterCond;
		}
		if (sortCond != null) {
		    query_sql += " order by " + sortCond.split("_")[0];
		} else {
		    query_sql += " order by isnull(px,9999999)";
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
			String sql ="insert into xt_user (userid,loginname,cnname,password,roledm,bm,zw,px) values (?,?,?,?,?,?,?,?)";
			this.jdbcTemplate.update(sql,new Object[]{maxID,query.get("loginname"),query.get("cnname"),query.get("password"),query.get("roledm"),query.get("bm"),query.get("zw"),query.get("px")});
			if(query.get("roledm") != null && !query.get("roledm").equals("") && query.get("roledm").equals("000")){
				sql = "insert into xt_user_menu(userid,menuid) select "+maxID+",menuid from xt_menu ";
				this.getSimpleJdbcTemplate().update(sql);
			}
	    }else{
			String sql ="update  xt_user set loginname = ?,cnname=?,password=?,roledm=?,bm=?,zw=?,px=? where userid=?";
			this.jdbcTemplate.update(sql,new Object[]{query.get("loginname"),query.get("cnname"),query.get("password"),query.get("roledm"),query.get("bm"),query.get("zw"),query.get("px"),Long.valueOf(userid)});
	    }
	}
	
	public boolean checkLoginname(Map<String,String> query){
	    boolean flag = true;
	    String userid = query.get("userid") == null ? "": query.get("userid").trim();
	    int count = 0 ;
	    if(userid.equals("")){ //保存操作
		count =  this.getJdbcTemplate().queryForInt("select count(*) from xt_user where loginname= ? ",new Object[]{query.get("loginname")});
	    }else{
	    	count =  this.getJdbcTemplate().queryForInt("select count(*) from xt_user where loginname= ? and userid !=? ",new Object[]{query.get("loginname"),Long.valueOf(userid)});
	    }
	    if(count > 0){
	    	flag = false;
	    }
	    return flag;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> getUser(Map<String,String> query){
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
	    return this.getJdbcTemplate().queryForList("select * from xt_role");
	}
	
	public boolean checkPassword(String loginname,String password){
	    String checkSql = "select count(*) from xt_user where loginname=? and password=?";
	    int count = this.getJdbcTemplate().queryForInt(checkSql,new Object[]{loginname,password});
	    boolean flag = true;
	    if(count == 0){
		flag = false;
	    }
	    return flag;
	}
	
	public void updatePassword(String loginname,String password){
	    String updateSql = "update xt_user set password=? where  loginname=?";
	    this.getJdbcTemplate().update(updateSql,new Object[]{password,loginname});
	}
	

	@SuppressWarnings("unchecked")
	public Object getAllLog(LimitPage limit, Map parMap, SortInfo sortInfo, List<FilterInfo> filterInfos,Xtuser xtuser) {

		String query_sql = "select logid,usrname,czsm,czinfo,CONVERT(varchar(12) , rq , 23 ) rq  from xt_syslog  where 1=1 " ;
		if(xtuser.getLoginbz() == 2){
			query_sql += " and usrid="+xtuser.getUserid();
		}
		query_sql += "  #### ";
		String wSql      = " ";
		String czsm = (String) (parMap.get("czsm") == null ? "" :parMap.get("czsm"));
		if(!czsm.trim().equals("")){
		    wSql +=" and czsm like '%"+czsm.trim()+"%' ";
		}
		String czsj1 = (String) (parMap.get("czsj1") == null ? "" :parMap.get("czsj1"));
		if(!czsj1.trim().equals("")){
		    wSql +=" and rq >= Cast('"+czsj1+"' As DateTime) ";
		}
		String czsj2 = (String) (parMap.get("czsj2") == null ? "" :parMap.get("czsj2"));
		if(!czsj2.trim().equals("")){
		    wSql +=" and rq <= Cast('"+czsj2+"' As DateTime) ";
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
