package com.group.zsxm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fins.gt.model.FilterInfo;
import com.fins.gt.model.SortInfo;
import com.group.core.common.LimitPage;
import com.group.core.common.TreeBean;
import com.group.zsxm.service.common.BaseService;

@Service
public class ZsxmbyService extends BaseService{

	public Object getZsxmSelected(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos){
		String fldm = parMap.get("bydm") == null ? "" :  parMap.get("bydm");
		String selfldm = parMap.get("selbydm") == null ? "" :  parMap.get("selbydm");
		String ls_sql = "select a.* from xm_info_v a,xm_bymx b where a.xmid=b.xmid and b.bydm='"+fldm+"' ";
		if(selfldm != null && !selfldm.equals("")){
			String[] sel = selfldm.split(",");
			for(int i=0;i<sel.length;i++){
				ls_sql += " and b.xmid in( select xmid from xm_bymx where bydm='"+sel[i]+"' )"; 
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
			ls_sql += " order by  case when isnull(convert(varchar(20),"+sortConds[0]+"),'')='' then 1 else 0 end , "+sortConds[0]+"  "+sortConds[1];
		} else {
			ls_sql += " order by a.xmid";
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
	public List<TreeBean> getXmbyTree(){
		List<TreeBean> l = new ArrayList();
		String sql_q = "";
		sql_q = " select a.bydm as dm,a.bymc as mc,(select count(*) from xm_by b where b.bydm like a.bydm+'%' and a.bydm!=b.bydm)  as isc," +
				" case when len(bydm)=3 then '0' else substring(bydm,1,len(bydm)-3) end as pdm from xm_by a order by bydm ";
		l = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(TreeBean.class));
		return l;
	}
	
	
	public List<Map<String, String>> getZsxmWaitSelect(Map<String,String> query,String bydm,String xmgzr){
		String sql_q = " ";
		List<Map<String, String>> list = new ArrayList();
		if(bydm != null && !bydm.equals("")){
			sql_q = " select a.* from xm_info_v a  where a.xmid not in ( select xmid from xm_bymx where bydm='"+bydm+"') ";
		}else{
			sql_q = " select a.* from xm_info_v a  where 1=1 ";
		}
		if(xmgzr != null && !xmgzr.equals("")){
			sql_q += " and xmgzr='"+xmgzr+"'";
		}
		if(query != null ){
			if(query.get("field")!=null && !query.get("field").equals("")){
				sql_q += " and "+query.get("field")+" like '%"+query.get("value")+"%'";
			}
		}
		sql_q += "  order by a.xmid ";
		list = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
		return list;
	}
	
	
	public int doSelectZsxmmx(String bydm,String[] xmid){
		String sql_o = "";
		//sql_o = " delete from xm_bymx where bydm='"+bydm+"'";
		//this.getSimpleJdbcTemplate().update(sql_o);
		sql_o = " insert into xm_bymx(bydm,xmid,bz) select '"+bydm+"',xmid,0 from xm_info where xmid in ("+ArrayToString(xmid)+") ";
		this.getSimpleJdbcTemplate().update(sql_o);
		return 1;
	}
	
	public int doDelSelectedXmmx(String xmids, String bydm){
		String sql_o = "";
		if(xmids != null && !xmids.equals("")){
			sql_o = " delete from xm_bymx where xmid in ("+xmids+") and bydm='"+bydm+"'";
			this.getSimpleJdbcTemplate().update(sql_o);
		}
		return 1;
	}
	
	public int doAddBywh(Map<String, String> m){
		String sql_o = "";
		Integer type = Integer.parseInt(m.get("type"));
		String maxid = "001";
		String pfldm = "";
		if(type == 0){
			//新增下级
			sql_o = "select isnull(max(substring(bydm,"+(m.get("bydm").length()+1)+","+(m.get("bydm").length()+3)+")),0)+1 from xm_by where len(bydm)="+(m.get("bydm").length()+3)+" and bydm like '"+m.get("bydm")+"%'";
			pfldm = m.get("bydm");
		}else{
			//新增本级
			if(!m.get("bydm").equals("")){
				pfldm = m.get("bydm").substring(0,m.get("bydm").length()-3);
				sql_o = "select isnull(max(substring(bydm,"+(m.get("bydm").length()-2)+","+(m.get("bydm").length())+")),0)+1 from xm_by where len(bydm)="+(m.get("bydm").length())+" ";
			}else{
				pfldm = "";
				sql_o = "select isnull(max(bydm),0)+1 from xm_by where len(bydm)="+(m.get("bydm").length())+" ";
			}
			
			
		}
		Integer fldm = this.getSimpleJdbcTemplate().queryForInt(sql_o);
		maxid = String.valueOf(fldm);
		for(int i=String.valueOf(fldm).length();i<3;i++){
			maxid = "0"+maxid;
		}
		maxid = pfldm + maxid;
		sql_o = " insert into xm_by(bydm,bymc,bybs,bz,sm) values('"+maxid+"','"+m.get("bymc")+"',0,0,'"+m.get("sm")+"')";
		this.getSimpleJdbcTemplate().update(sql_o);
		return 1;
	}
	
	public int doDelXmby(String bydm){
		String sql_o = "";
		sql_o = "delete from xm_by where bydm like '"+bydm+"%'";
		this.getSimpleJdbcTemplate().update(sql_o);
		sql_o = "delete from xm_bymx where bydm like '"+bydm+"%'";
		this.getSimpleJdbcTemplate().update(sql_o);
		return 1;
	}
	
	public Map preRepXmby(String bydm){
		Map m = new HashMap();
		String sql_q = " select * from xm_by where bydm='"+bydm+"'";
		m = this.getSimpleJdbcTemplate().queryForMap(sql_q);
		return m;
	}
	
	public int doRepXmby(String bydm,Map<String, String> m){
		String sql_o = "";
		sql_o = " update xm_by set bymc='"+m.get("bymc")+"',sm='"+m.get("sm")+"' where bydm='"+m.get("bydm")+"'";
		this.getSimpleJdbcTemplate().update(sql_o);
		return 1;
	}
	
}
