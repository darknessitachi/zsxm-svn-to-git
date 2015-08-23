package com.group.zsxm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.jtds.jdbc.cache.SQLCacheKey;

import org.springframework.stereotype.Service;

import com.fins.gt.model.FilterInfo;
import com.fins.gt.model.SortInfo;
import com.group.core.common.LimitPage;
import com.group.core.common.TreeBean;
import com.group.zsxm.service.common.BaseService;

@Service
public class RcdaflwhService extends BaseService{
	
	/**
	 * 新增人才分类
	 * @param m
	 * @return
	 */
	public int doAddFlwh(Map<String, String> m){
		String sql_o = "";
		Integer type = Integer.parseInt(m.get("type"));
		String maxid = "001";
		String pfldm = "";
		if(type == 0){
			//新增下级
			sql_o = "select isnull(max(substring(fldm,"+(m.get("fldm").length()+1)+","+(m.get("fldm").length()+3)+")),0)+1 from rc_fl where len(fldm)="+(m.get("fldm").length()+3)+" and fldm like '"+m.get("fldm")+"%'";
			pfldm = m.get("fldm");
		}else{
			//新增本级
			if(!m.get("fldm").equals("")){
				pfldm = m.get("fldm").substring(0,m.get("fldm").length()-3);
				sql_o = "select isnull(max(substring(fldm,"+(m.get("fldm").length()-2)+","+(m.get("fldm").length())+")),0)+1 from rc_fl where len(fldm)="+(m.get("fldm").length())+" ";
			}else{
				pfldm = "";
				sql_o = "select isnull(max(fldm),0)+1 from rc_fl where len(fldm)="+(m.get("fldm").length())+" ";
			}
			
			
		}
		Integer fldm = this.getSimpleJdbcTemplate().queryForInt(sql_o);
		maxid = String.valueOf(fldm);
		for(int i=String.valueOf(fldm).length();i<3;i++){
			maxid = "0"+maxid;
		}
		maxid = pfldm + maxid;
		sql_o = " insert into rc_fl(fldm,flmc,flbs,bz,sm) values('"+maxid+"','"+m.get("flmc")+"',0,0,'"+m.get("sm")+"')";
		this.getSimpleJdbcTemplate().update(sql_o);
		return 1;
	}
	
	/**
	 * 修改人才分类取数
	 * @param fldm
	 * @return
	 */
	public Map preRepFlwh(String fldm){
		Map m = new HashMap();
		String sql_q = " select * from rc_fl where fldm='"+fldm+"'";
		m = this.getSimpleJdbcTemplate().queryForMap(sql_q);
		return m;
	}
	/**\
	 * 修改人才分类操作
	 */
	public int doRepFlwh(String fldm,Map<String, String> m){
		String sql_o = "";
		sql_o = " update rc_fl set flmc='"+m.get("flmc")+"',sm='"+m.get("sm")+"' where fldm='"+m.get("fldm")+"'";
		this.getSimpleJdbcTemplate().update(sql_o);
		return 1;
	}
	
	/**
	 * 删除人才分类
	 * @param fldm
	 * @return
	 */
	public int doDelFlwh(String fldm){
		String sql_o = "";
		sql_o = "delete from rc_fl where fldm like '"+fldm+"%'";
		this.getSimpleJdbcTemplate().update(sql_o);
		sql_o = "delete from rc_flmx where fldm like '"+fldm+"%'";
		this.getSimpleJdbcTemplate().update(sql_o);
		return 1;
	}
	
	/**
	 * 获取等待选择的人才信息
	 * @return
	 */
	public List<Map<String, String>> getRcxxWaitSelect(Map<String,String> query){
		String sql_q = " ";
		List<Map<String, String>> list = new ArrayList();
		sql_q = " select a.* from rc_pinfo_v a  ";
		if(query != null ){
			if(query.get("field")!=null && !query.get("field").equals("")){
				sql_q += " where "+query.get("field")+" like '%"+query.get("value")+"%'";
			}
		}
		sql_q += "  order by a.rcid ";
		list = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
		return list;
	}
	
	/**
	 * 获取已选择的人才信息
	 * @param fldm
	 * @return
	 */
	public Object getRcxxSelected(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos){
		String fldm = parMap.get("fldm") == null ? "" :  parMap.get("fldm");
		String selfldm = parMap.get("selfldm") == null ? "" :  parMap.get("selfldm");
		String ls_sql = "select a.* from rc_pinfo_v a,rc_flmx b where a.rcid=b.rcid and b.fldm='"+fldm+"' ";
		if(selfldm != null && !selfldm.equals("")){
			String[] sel = selfldm.split(",");
			for(int i=0;i<sel.length;i++){
				ls_sql += " and b.rcid in( select rcid from rc_flmx where fldm='"+sel[i]+"' )"; 
			}
			//ls_sql = "  ";
		}
		
		String sortCond = getGridSortString(sortInfo);
		String filterCond = getGridFilterString(filterInfos);
		if (filterCond != null) {
			ls_sql += filterCond;
		}
		if (sortCond != null) {
			ls_sql += " order by a." + sortCond.split("_")[0];
		} else {
			ls_sql += " order by a.rcid";
		}
		if (limit != null) {
			return this.queryForListWithPage(ls_sql, limit);
		} else {
			return this.simpleJdbcTemplate.queryForList(ls_sql);
		}
	}
	
	/**
	 * 选择人才明细数据
	 * @param fldm
	 * @param rcid
	 * @return
	 */
	public int doSelectRcflmx(String fldm,String[] rcid){
		String sql_o = "";
		//sql_o = " delete from rc_flmx where fldm='"+fldm+"'";
		//this.getSimpleJdbcTemplate().update(sql_o);
		sql_o = " insert into rc_flmx(fldm,rcid,bz) select '"+fldm+"',rcid,0 from rc_pinfo where rcid in ("+ArrayToString(rcid)+") and status=1";
		this.getSimpleJdbcTemplate().update(sql_o);
		return 1;
	}
	
	/**
	 * 删除已选择的人才分类
	 * @param rcids
	 * @return
	 */
	public int doDelSelectedflmx(String rcids ){
		String sql_o = "";
		if(rcids != null && !rcids.equals("")){
			sql_o = " delete from rc_flmx where rcid in ("+rcids+")";
			this.getSimpleJdbcTemplate().update(sql_o);
		}
		return 1;
	}
	/**
	 * 获取人才分类树
	 * @return
	 */
	public List<TreeBean> getFlwhTree(){
		List<TreeBean> l = new ArrayList();
		String sql_q = "";
		sql_q = " select a.fldm as dm,a.flmc as mc,(select count(*) from rc_fl b where b.fldm like a.fldm+'%' and a.fldm!=b.fldm)  as isc," +
				" case when len(fldm)=3 then '0' else substring(fldm,1,len(fldm)-3) end as pdm from rc_fl a order by fldm ";
		l = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(TreeBean.class));
		return l;
	}
}
