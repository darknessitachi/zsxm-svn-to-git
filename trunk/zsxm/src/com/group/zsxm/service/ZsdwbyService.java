package com.group.zsxm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fins.gt.model.FilterInfo;
import com.fins.gt.model.SortInfo;
import com.group.core.common.LimitPage;
import com.group.core.common.Page;
import com.group.core.common.TreeBean;
import com.group.zsxm.service.common.BaseService;

@Service
public class ZsdwbyService extends BaseService{

	public Object getZsdwSelected(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos){
		String fldm = parMap.get("bydm") == null ? "" :  parMap.get("bydm");
		String selfldm = parMap.get("selbydm") == null ? "" :  parMap.get("selbydm");
		String ls_sql = "select a.*," +
				"convert(varchar(20),convert(numeric(20,2),((isnull(convert(numeric(20,2),SNXSSR),0)-isnull(SNXSSR_,0))/isnull(nullif(SNXSSR_,0),1))*100.00))+'%' XSSRBL," +
				"convert(varchar(20),convert(numeric(20,2),((isnull(convert(numeric(20,2),SNJNSS),0)-isnull(SNJNSS_,0))/isnull(nullif(SNJNSS_,0),1))*100.00))+'%' JNSSBL," +
				"convert(varchar(20),convert(numeric(20,2),((isnull(convert(numeric(20,2),SNDYGS),0)-isnull(SNDYGS_,0))/isnull(nullif(SNDYGS_,0),1))*100.00))+'%' YGSBL," +
				"dbo.GET_MUTSEL_MC(a.dwid,5,32) DWSX_MC,dbo.GET_MUTSEL_MC(a.dwid,2,11) CYFL_MC,dbo.GET_MUTSEL_MC(a.dwid,4,13) YJLB_MC" +
				" from dw_info_v a,dw_bymx b where a.dwid=b.dwid and b.bydm='"+fldm+"' ";
		if(selfldm != null && !selfldm.equals("")){
			String[] sel = selfldm.split(",");
			for(int i=0;i<sel.length;i++){
				ls_sql += " and b.dwid in( select dwid from dw_bymx where bydm='"+sel[i]+"' )"; 
			}
			//ls_sql = "  ";
		}
		
		String sortCond = getGridSortString(sortInfo);
		String filterCond = getGridFilterString(filterInfos);
		if (filterCond != null) {
			ls_sql += filterCond;
		}
		if (sortCond != null && !sortCond.trim().equals("asc") && !sortCond.trim().equals("desc")) {
			String[] sortConds = sortCond.trim().split(" ");
			if(sortConds[0].equals("XSSRBL")||sortConds[0].equals("JNSSBL")||sortConds[0].equals("YGSBL")){
				ls_sql += " order by CLRQ desc,DWZT" ;
			}else{
				ls_sql += " order by  case when isnull(convert(varchar(20),"+sortConds[0]+"),'')='' then 1 else 0 end , "+sortConds[0]+"  "+sortConds[1];
			}
			
		} else {
			ls_sql += " order by CLRQ desc,DWZT ";
		}
		if (limit != null) {
			return this.queryForListWithPage(ls_sql, limit);
		} else {
			return this.simpleJdbcTemplate.queryForList(ls_sql);
		}
	}
	
	/***
	 * 获取分类数据
	 * @param limit
	 * @param parMap
	 * @param sortInfo
	 * @param filterInfos
	 * @return
	 */
	public Object getZsdwFlList(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos){
		String dm = parMap.get("dm") == null ? "" :  parMap.get("id");
		String id = parMap.get("id") == null ? "" :  parMap.get("id");
		String ls_sql = "select a.*," +
				"convert(varchar(20),convert(numeric(20,2),((isnull(convert(numeric(20,2),SNXSSR),0)-isnull(SNXSSR_,0))/isnull(nullif(SNXSSR_,0),1))*100.00))+'%' XSSRBL," +
				"convert(varchar(20),convert(numeric(20,2),((isnull(convert(numeric(20,2),SNJNSS),0)-isnull(SNJNSS_,0))/isnull(nullif(SNJNSS_,0),1))*100.00))+'%' JNSSBL," +
				"convert(varchar(20),convert(numeric(20,2),((isnull(convert(numeric(20,2),SNDYGS),0)-isnull(SNDYGS_,0))/isnull(nullif(SNDYGS_,0),1))*100.00))+'%' YGSBL," +
				"dbo.GET_MUTSEL_MC(a.dwid,5,32) DWSX_MC,dbo.GET_MUTSEL_MC(a.dwid,2,11) CYFL_MC,dbo.GET_MUTSEL_MC(a.dwid,4,13) YJLB_MC" +
				" from dw_info_v a where 1=1 ";
		//4,5,9,10,11,12,13,32,91,92
		if(id != null && !id.equals("")){
			String[] ids = id.split("-");
			if(ids.length > 1){
				if(ids[0].equals("4")){
					ls_sql += " and dwzt = '"+ids[1]+"'";
				}else if(ids[0].equals("5")){
					ls_sql += " and nwz = '"+ids[1]+"'";
				}else if(ids[0].equals("9")){
					ls_sql += " and dwlx = '"+ids[1]+"'";
				}else if(ids[0].equals("33")){
					ls_sql += " and rzyqsj = ( select dictname from xt_dict where lbid=33 and dictbh='"+ids[1]+"')";
				}else if(ids[0].equals("34")){
					ls_sql += " and gxjsqy = '"+ids[1]+"'";
				}else if(ids[0].equals("35")){
					ls_sql += " and rjqy = '"+ids[1]+"'";
				}else if(ids[0].equals("36")){
					ls_sql += " and dmqy = '"+ids[1]+"'";
				}else if(ids[0].equals("37")){
					ls_sql += " and gjcxkjyq = '"+ids[1]+"'";
				}else if(ids[0].equals("38")){
					ls_sql += " and gjqdxxcy like '"+ids[1]+"%'";
				}else if(ids[0].equals("39")){
					ls_sql += " and xdfwy = '"+ids[1]+"'";
				}else if(ids[0].equals("40")){
					ls_sql += " and fwwb like '"+ids[1]+"%'";
				}else if(ids[0].equals("41")){
					ls_sql += " and lhrh = '"+ids[1]+"'";
				}else if(ids[0].equals("42")){
					ls_sql += " and cmmi = '"+ids[1]+"'";
				}else if(ids[0].equals("43")){
					ls_sql += " and dwsb = '"+ids[1]+"'";
				}else if(ids[0].equals("45")){
					ls_sql += " and sscpc = '"+ids[1]+"'";
				}else if(ids[0].equals("10")){
					ls_sql += " and dwid in ( select dwid from dw_mutsel where selid=1 and seldm like '"+ids[1]+"%')";
				}else if(ids[0].equals("11")){
					ls_sql += " and dwid in ( select dwid from dw_mutsel where selid=2 and seldm like '"+ids[1]+"%')";
				}else if(ids[0].equals("12")){
					ls_sql += " and dwid in ( select dwid from dw_mutsel where selid=3 and seldm like '"+ids[1]+"%')";
				}else if(ids[0].equals("13")){
					ls_sql += " and dwid in ( select dwid from dw_mutsel where selid=4 and seldm like '"+ids[1]+"%')";
				}else if(ids[0].equals("32")){
					ls_sql += " and dwid in ( select dwid from dw_mutsel where selid=5 and seldm like '"+ids[1]+"%')";
				}else if(ids[0].equals("91")){
					ls_sql += " and isnull(isjfh,1) = '"+ids[1]+"'";
				}else if(ids[0].equals("92")){
					if(ids[1].equals("1")){
						ls_sql += " and isnull(tdpc,'') != ''";
					}else{
						ls_sql += " and isnull(tdpc,'') = ''";
					}
					
				}
			}else{
				if(ids[0].equals("4")){
					ls_sql += " and dwzt in ( select dictbh from xt_dict where lbid=4 )";
				}else if(ids[0].equals("5")){
					ls_sql += " and nwz in ( select dictbh from xt_dict where lbid=5 )";
				}else if(ids[0].equals("9")){
					ls_sql += " and dwlx in ( select dictbh from xt_dict where lbid=9 )";
				}else if(ids[0].equals("33")){
					ls_sql += " and rzyqsj  in ( select dictname from xt_dict where lbid=33 )";
				}else if(ids[0].equals("34")){
					ls_sql += " and gxjsqy  in ( select dictbh from xt_dict where lbid=34 )";
				}else if(ids[0].equals("35")){
					ls_sql += " and rjqy  in ( select dictbh from xt_dict where lbid=35)";
				}else if(ids[0].equals("36")){
					ls_sql += " and dmqy  in ( select dictbh from xt_dict where lbid=36 )";
				}else if(ids[0].equals("37")){
					ls_sql += " and gjcxkjyq  in ( select dictbh from xt_dict where lbid=37 )";
				}else if(ids[0].equals("38")){
					ls_sql += " and gjqdxxcy  in ( select dictbh from xt_dict where lbid=38 )";
				}else if(ids[0].equals("39")){
					ls_sql += " and xdfwy  in ( select dictbh from xt_dict where lbid=39 )";
				}else if(ids[0].equals("40")){
					ls_sql += " and fwwb  in ( select dictbh from xt_dict where lbid=40 )";
				}else if(ids[0].equals("41")){
					ls_sql += " and lhrh  in ( select dictbh from xt_dict where lbid=41 )";
				}else if(ids[0].equals("42")){
					ls_sql += " and cmmi  in ( select dictbh from xt_dict where lbid=42 )";
				}else if(ids[0].equals("43")){
					ls_sql += " and dwsb  in ( select dictbh from xt_dict where lbid=43 )";
				}else if(ids[0].equals("45")){
					ls_sql += " and sscpc  in ( select dictbh from xt_dict where lbid=45 )";
				}else if(ids[0].equals("10")){
					ls_sql += " and dwid in ( select dwid from dw_mutsel where selid=1 and seldm in (select dictbh from xt_dict where lbid=10) )";
				}else if(ids[0].equals("11")){
					ls_sql += " and dwid in ( select dwid from dw_mutsel where selid=2 and seldm in (select dictbh from xt_dict where lbid=11) )";
				}else if(ids[0].equals("12")){
					ls_sql += " and dwid in ( select dwid from dw_mutsel where selid=3 and seldm in (select dictbh from xt_dict where lbid=12) )";
				}else if(ids[0].equals("13")){
					ls_sql += " and dwid in ( select dwid from dw_mutsel where selid=4 and seldm in (select dictbh from xt_dict where lbid=13) )";
				}else if(ids[0].equals("32")){
					ls_sql += " and dwid in ( select dwid from dw_mutsel where selid=5 and seldm in (select dictbh from xt_dict where lbid=32) )";
				}else if(ids[0].equals("91")){
					ls_sql += " and isnull(isjfh,1) in (1,0)";
				}else if(ids[0].equals("92")){
					//ls_sql += " and isnull(tdpc,'') in (1,2)";
					
				}
			}
		}else{
			return new Page();
		}
		
		String sortCond = getGridSortString(sortInfo);
		String filterCond = getGridFilterString(filterInfos);
		if (filterCond != null) {
			ls_sql += filterCond;
		}
		if (sortCond != null && !sortCond.trim().equals("asc") && !sortCond.trim().equals("desc")) {
			String[] sortConds = sortCond.trim().split(" ");
			if(sortConds[0].equals("XSSRBL")||sortConds[0].equals("JNSSBL")||sortConds[0].equals("YGSBL")){
				ls_sql += " order by CLRQ desc,DWZT" ;
			}else{
				ls_sql += " order by  case when isnull(convert(varchar(20),"+sortConds[0]+"),'')='' then 1 else 0 end , "+sortConds[0]+"  "+sortConds[1];
			}
			
		} else {
			ls_sql += " order by CLRQ desc,DWZT ";
		}
		if (limit != null) {
			return this.queryForListWithPage(ls_sql, limit);
		} else {
			return this.simpleJdbcTemplate.queryForList(ls_sql);
		}
	}
	/**
	 * 获取分类树
	 * @return
	 */
	public List<TreeBean> getDwbyTree(){
		List<TreeBean> l = new ArrayList();
		String sql_q = "";
		sql_q = " select a.bydm as dm,a.bymc as mc,(select count(*) from dw_by b where b.bydm like a.bydm+'%' and a.bydm!=b.bydm)  as isc," +
				" case when len(bydm)=3 then '0' else substring(bydm,1,len(bydm)-3) end as pdm from dw_by a order by bydm ";
		l = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(TreeBean.class));
		return l;
	}
	
	public List<TreeBean> getDwflTree(){
		List<TreeBean> l = new ArrayList();
		String sql_q = "";
		sql_q = " delete from base_Tree ";
		this.getSimpleJdbcTemplate().update(sql_q);
		
		sql_q = " insert into base_tree(dm,mc,bz,sx,pdm,id) " +
				" select lbid,lbname,'0',dbo.translen(convert(varchar(20),lbid),5),'0',lbid from xt_dlb where lbid in (9,4,5,13,32," +
				"33,34,35,36,37,38,39,40,41,42,43,45)";
		this.getSimpleJdbcTemplate().update(sql_q);
		
		sql_q = " insert into base_tree(dm,mc,bz,sx,pdm,id) " +
				" values(92,'是否双创团队',0,'00092',0,92)";
		this.getSimpleJdbcTemplate().update(sql_q);
		
		sql_q = " insert into base_tree(dm,mc,bz,sx,pdm,id) " +
			" values(1,'是',1,'00092-1',92,'92-1')";
		this.getSimpleJdbcTemplate().update(sql_q);
		
		sql_q = " insert into base_tree(dm,mc,bz,sx,pdm,id) " +
			" values(2,'否',1,'00092-2',92,'92-2')";
		this.getSimpleJdbcTemplate().update(sql_q);
		
		sql_q = " insert into base_tree(dm,mc,bz,sx,pdm,id) " +
			" values(91,'是否金凤凰单位',0,'00091',0,91)";
		this.getSimpleJdbcTemplate().update(sql_q);
		
		sql_q = " insert into base_tree(dm,mc,bz,sx,pdm,id) " +
			" values(1,'是',1,'00091-1',91,'91-1')";
		this.getSimpleJdbcTemplate().update(sql_q);
		
		sql_q = " insert into base_tree(dm,mc,bz,sx,pdm,id) " +
			" values(0,'否',1,'00091-2',91,'91-0')";
		this.getSimpleJdbcTemplate().update(sql_q);
		
		
		sql_q = "insert into base_tree(dm,mc,bz,sx,pdm,id) " +
					" select dictbh dm,dictname mc,'1',dbo.translen(convert(varchar(20),lbid),5)+'-'+dictbh,case when len(dictbh)=3  then convert(varchar,lbid) else convert(varchar,lbid)+'-'+convert(varchar,substring(dictbh,1,3)) end pdm,convert(varchar(10),lbid)+'-'+dictbh " +
					" from xt_dict where lbid in (9,4,5,13,32,33,34,35,36,37,38,39,40,41,42,43,45  ) ";
		this.getSimpleJdbcTemplate().update(sql_q);
		
		sql_q = "select id dm,mc,pdm,'0' isc,id from base_tree order by sx ";
		l = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(TreeBean.class));
		return l;
	}
	
	
	public int doAddBywh(Map<String, String> m){
		String sql_o = "";
		Integer type = Integer.parseInt(m.get("type"));
		String maxid = "001";
		String pfldm = "";
		if(type == 0){
			//新增下级
			sql_o = "select isnull(max(substring(bydm,"+(m.get("bydm").length()+1)+","+(m.get("bydm").length()+3)+")),0)+1 from dw_by where len(bydm)="+(m.get("bydm").length()+3)+" and bydm like '"+m.get("bydm")+"%'";
			pfldm = m.get("bydm");
		}else{
			//新增本级
			if(!m.get("bydm").equals("")){
				pfldm = m.get("bydm").substring(0,m.get("bydm").length()-3);
				sql_o = "select isnull(max(substring(bydm,"+(m.get("bydm").length()-2)+","+(m.get("bydm").length())+")),0)+1 from dw_by where len(bydm)="+(m.get("bydm").length())+" ";
			}else{
				pfldm = "";
				sql_o = "select isnull(max(bydm),0)+1 from dw_by where len(bydm)="+(m.get("bydm").length())+" ";
			}
			
			
		}
		Integer fldm = this.getSimpleJdbcTemplate().queryForInt(sql_o);
		maxid = String.valueOf(fldm);
		for(int i=String.valueOf(fldm).length();i<3;i++){
			maxid = "0"+maxid;
		}
		maxid = pfldm + maxid;
		sql_o = " insert into dw_by(bydm,bymc,bybs,bz,sm) values('"+maxid+"','"+m.get("bymc")+"',0,0,'"+m.get("sm")+"')";
		this.getSimpleJdbcTemplate().update(sql_o);
		return 1;
	}
	
	
	public List<Map<String, String>> getZsdwWaitSelect(Map<String,String> query,String bydm){
		String sql_q = " ";
		List<Map<String, String>> list = new ArrayList();
		if(bydm != null && !bydm.equals("")){
			sql_q = " select a.* from dw_info_v a  where a.dwid not in ( select dwid from dw_bymx where bydm='"+bydm+"') ";
		}else{
			sql_q = " select a.* from dw_info_v a  where 1=1 ";
		}
		
		if(query != null ){
			if(query.get("field")!=null && !query.get("field").equals("")){
				sql_q += " and "+query.get("field")+" like '%"+query.get("value")+"%'";
			}
			if(query.get("field2")!=null && !query.get("field2").equals("")){
				sql_q += " and "+query.get("field2")+" like '%"+query.get("value2")+"%'";
			}
		}
		sql_q += "  order by a.dwid ";
		list = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
		return list;
	}
	
	public int doSelectZsdwmx(String bydm,String[] dwid){
		String sql_o = "";
		//sql_o = " delete from xm_bymx where bydm='"+bydm+"'";
		//this.getSimpleJdbcTemplate().update(sql_o);
		sql_o = " insert into dw_bymx(bydm,dwid,bz) select '"+bydm+"',dwid,0 from dw_info where dwid in ("+ArrayToString(dwid)+") ";
		this.getSimpleJdbcTemplate().update(sql_o);
		return 1;
	}
	
	public int doDelDwby(String bydm){
		String sql_o = "";
		sql_o = "delete from dw_by where bydm like '"+bydm+"%'";
		this.getSimpleJdbcTemplate().update(sql_o);
		sql_o = "delete from dw_bymx where bydm like '"+bydm+"%'";
		this.getSimpleJdbcTemplate().update(sql_o);
		return 1;
	}
	
	public Map preRepDwby(String bydm){
		Map m = new HashMap();
		String sql_q = " select * from dw_by where bydm='"+bydm+"'";
		m = this.getSimpleJdbcTemplate().queryForMap(sql_q);
		return m;
	}
	
	public int doRepDwby(String bydm,Map<String, String> m){
		String sql_o = "";
		sql_o = " update dw_by set bymc='"+m.get("bymc")+"',sm='"+m.get("sm")+"' where bydm='"+m.get("bydm")+"'";
		this.getSimpleJdbcTemplate().update(sql_o);
		return 1;
	}
	
	
	public int doDelSelectedDwmx(String dwids, String bydm){
		String sql_o = "";
		if(dwids != null && !dwids.equals("")){
			sql_o = " delete from dw_bymx where dwid in ("+dwids+") and bydm='"+bydm+"'";
			this.getSimpleJdbcTemplate().update(sql_o);
		}
		return 1;
	}
	
}
