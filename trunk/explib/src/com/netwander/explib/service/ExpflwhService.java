package com.netwander.explib.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.registry.DeleteException;

import org.springframework.stereotype.Service;

import com.fins.gt.model.FilterInfo;
import com.fins.gt.model.SortInfo;
import com.netwander.explib.entity.TabPanel;
import com.netwander.core.common.LimitPage;
import com.netwander.core.common.TreeBean;
import com.netwander.explib.entity.Xtuser;
import com.netwander.explib.exception.BusException;
import com.netwander.explib.service.common.BaseService;
import com.sun.java_cup.internal.internal_error;

@Service
public class ExpflwhService extends BaseService{

	/**
	 * 新增人才分类
	 * 2010-03-28 新增分上下级
	 * @param m
	 * @return
	 */
	public int doAddFlwh(Map<String, String> m,Integer flbz){
		String sql_o = "";
		Integer type = Integer.parseInt(m.get("type"));
		String maxid = "000000001";
		String pfldm = "";
		if(type == 0){
			//新增下级
			sql_o = "select isnull(max(substring(fldm,"+(m.get("fldm").length()+1)+","+(m.get("fldm").length()+9)+")),0)+1 from exp_fl where len(fldm)="+(m.get("fldm").length()+9)+" and fldm like '"+m.get("fldm")+"%'";
			pfldm = m.get("fldm");
		}else{
			//新增本级
			if(!m.get("fldm").equals("")){
				pfldm = m.get("fldm").substring(0,m.get("fldm").length()-9);
				sql_o = "select isnull(max(substring(fldm,"+(m.get("fldm").length()-2)+","+(m.get("fldm").length())+")),0)+1 from exp_fl where len(fldm)="+(m.get("fldm").length())+" ";
			}else{
				pfldm = "";
				sql_o = "select isnull(max(fldm),0)+1 from exp_fl where len(fldm)="+(maxid.length())+" ";
			}
		}
		Integer fldm = this.getSimpleJdbcTemplate().queryForInt(sql_o);
		maxid = String.valueOf(fldm);
		for(int i=String.valueOf(fldm).length();i<9;i++){
			maxid = "0"+maxid;
		}
		maxid = pfldm + maxid;
		sql_o = " insert into exp_fl(fldm,flmc,flbs,bz,sm,flbz,rcid) values('"+maxid+"','"+m.get("flmc")+"',0,0,'"+m.get("sm")+"',"+flbz+",null)";
		this.getSimpleJdbcTemplate().update(sql_o);
		return 1;
	}
	
	public int doAddBywh(Map<String, String> m,Integer flbz){
		String sql_o = "";
		Integer type = Integer.parseInt(m.get("type"));
		String maxid = "000000001";
		String pfldm = "";
		if(type == 0){
			//新增下级
			sql_o = "select isnull(max(substring(fldm,"+(m.get("fldm").length()+1)+","+(m.get("fldm").length()+9)+")),0)+1 from exp_by where len(fldm)="+(m.get("fldm").length()+9)+" and fldm like '"+m.get("fldm")+"%'";
			pfldm = m.get("fldm");
		}else{
			//新增本级
			if(!m.get("fldm").equals("")){
				pfldm = m.get("fldm").substring(0,m.get("fldm").length()-9);
				sql_o = "select isnull(max(substring(fldm,"+(m.get("fldm").length()-2)+","+(m.get("fldm").length())+")),0)+1 from exp_by where len(fldm)="+(m.get("fldm").length())+" ";
			}else{
				pfldm = "";
				sql_o = "select isnull(max(fldm),0)+1 from exp_by where len(fldm)="+(maxid.length())+" ";
			}
		}
		Integer fldm = this.getSimpleJdbcTemplate().queryForInt(sql_o);
		maxid = String.valueOf(fldm);
		for(int i=String.valueOf(fldm).length();i<9;i++){
			maxid = "0"+maxid;
		}
		maxid = pfldm + maxid;
		sql_o = " insert into exp_by(fldm,flmc,flbs,bz,sm,flbz) values('"+maxid+"','"+m.get("flmc")+"',0,0,'"+m.get("sm")+"',"+flbz+")";
		this.getSimpleJdbcTemplate().update(sql_o);
		return 1;
	}
	/**
	 * 只新增本级
	 * @param m
	 * @param flbz 公共分类，个人分类
	 * @return
	 */
	public int doAddFlwhBj(Map<String, String> m,Integer flbz){
		String sql_o = "";
		
		String maxid = "000000001";

		sql_o = "select isnull(max(fldm),0)+1 from exp_fl where len(fldm)=9 ";
		Integer fldm = this.getSimpleJdbcTemplate().queryForInt(sql_o);
		maxid = String.valueOf(fldm);
		for(int i=String.valueOf(fldm).length();i<9;i++){
			maxid = "0"+maxid;
		}
		
		sql_o = " insert into exp_fl(fldm,flmc,flbs,bz,sm,flbz,rcid) values('"+maxid+"','"+m.get("flmc")+"',0,0,'"+m.get("sm")+"',"+flbz+",null)";
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
		String sql_q = " select * from exp_fl where fldm='"+fldm+"'";
		m = this.getSimpleJdbcTemplate().queryForMap(sql_q);
		return m;
	}
	public Map preRepBywh(String fldm){
		Map m = new HashMap();
		String sql_q = " select * from exp_by where fldm='"+fldm+"'";
		m = this.getSimpleJdbcTemplate().queryForMap(sql_q);
		return m;
	}
	/**\
	 * 修改人才分类操作
	 */
	public int doRepFlwh(String fldm_,Map<String, String> m,Integer flbz){
		String sql_o = "";
		sql_o = " update exp_fl set flmc='"+m.get("flmc")+"',sm='"+m.get("sm")+"' where fldm='"+m.get("fldm")+"' ";
		this.getSimpleJdbcTemplate().update(sql_o);
		return 1;
	}
	public int doRepBywh(String fldm_,Map<String, String> m,Integer flbz){
		String sql_o = "";
		sql_o = " update exp_by set flmc='"+m.get("flmc")+"',sm='"+m.get("sm")+"' where fldm='"+m.get("fldm")+"' ";
		this.getSimpleJdbcTemplate().update(sql_o);
		return 1;
	}
	/**
	 * 删除人才分类
	 * @param fldm
	 * @return
	 */
	public int doDelFlwh(String fldm,Integer flbz){
		String sql_o = "";
		
		sql_o = "delete from exp_fl where fldm like '"+fldm+"%' ";
		this.getSimpleJdbcTemplate().update(sql_o);
		
		sql_o = "delete from exp_flmx where fldm like '"+fldm+"%'";
		this.getSimpleJdbcTemplate().update(sql_o);
		
		return 1;
	}
	public int doDelBywh(String fldm,Integer flbz){
		String sql_o = "";
		
		sql_o = "delete from exp_by where fldm like '"+fldm+"%' ";
		this.getSimpleJdbcTemplate().update(sql_o);
		
		sql_o = "delete from exp_bymx where fldm like '"+fldm+"%'";
		this.getSimpleJdbcTemplate().update(sql_o);
		
		return 1;
	}
	/**
	 * 获取等待选择的人才信息
	 * @return
	 */
	public List<Map<String, String>> getRcxxWaitSelect(Map<String,String> query,String fldm){
		String sql_q = " ";
		List<Map<String, String>> list = new ArrayList();
		sql_q = " select a.* from exp_main_v a where a.rcid not in ( select rcid from exp_flmx where fldm='"+fldm+"') ";
		if(query != null ){
			if(query.get("field")!=null && !query.get("field").equals("")){
				sql_q += " and "+query.get("field")+" like '%"+query.get("value")+"%'";
			}
		}
		sql_q += "  order by a.rcid ";
		list = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
		return list;
	}
	public List<Map<String, String>> getRcxxWaitSelectBy(Map<String,String> query,String fldm){
		String sql_q = " ";
		List<Map<String, String>> list = new ArrayList();
		sql_q = " select a.* from exp_main_v a where a.rcid not in ( select rcid from exp_bymx where fldm='"+fldm+"') ";
		if(query != null ){
			if(query.get("field")!=null && !query.get("field").equals("")){
				sql_q += " and "+query.get("field")+" like '%"+query.get("value")+"%'";
			}
		}
		sql_q += "  order by a.rcid ";
		list = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
		return list;
	}
	
	public Object getRcxxWaitSelected(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos,Xtuser xtuser){
		String fldm = (parMap.get("fldm") == null || parMap.get("fldm").equals("")) ? "" :  parMap.get("fldm");
		if(fldm.length() < 9){
			fldm = "999999999";
		}
		String name = parMap.get("name") == null ? "" :  parMap.get("name");
		String where = parMap.get("where") == null ? "" :  parMap.get("where");
		String plcx = parMap.get("plvalue") == null ? "" :  parMap.get("plvalue");
		
		
		
		String ls_sql = "select a.* from exp_main_v a where a.rcid not in ( select distinct rcid from exp_flmx where fldm = '"+fldm+"') ";
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
				}
			}
			
		}
		
		if(plcx != null && !plcx.trim().equals("")){
			String[] pxcxs = (plcx.
			replaceAll("\n", " ").
			replaceAll("\r", " ").
			replaceAll(",", " ").
			replaceAll("，", " ").
			replaceAll("、", " ").
			replaceAll("\'", "").
			replaceAll("%", "").
			replaceAll("\"", "").
			replaceAll("　", " ").
			replaceAll(" {2,}", " ").replaceAll(" ", ",")).split(",");
			
			String yy = "";
			if(pxcxs != null && pxcxs.length > 0){
				for(int i=0;i<pxcxs.length;i++){
					if(yy.equals("")){
						yy = "'"+pxcxs[i].trim()+"'";
					}else{
						yy += ",'"+pxcxs[i].trim()+"'";
					}
				}
				ls_sql += " and (rcname in ("+yy+") or zjno in ("+yy+"))";
			}
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
	 * 获取已选择的人才信息
	 * @param fldm
	 * @return
	 */
	public Object getRcxxSelected(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos,Xtuser xtuser){
		String fldm = (parMap.get("fldm") == null || parMap.get("fldm").equals("")) ? "" :  parMap.get("fldm");
		if(fldm.length() < 9){
			fldm = "999999999";
		}
		String selfldm = parMap.get("selfldm") == null ? "" :  parMap.get("selfldm");
		
		String ls_sql = "select a.* from exp_main_v a where a.rcid in ( select distinct rcid from exp_flmx where fldm like '"+fldm+"%') ";
		
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
	

	public Object getRcxxWaitSelectedBy(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos,Xtuser xtuser){
		String fldm = (parMap.get("fldm") == null || parMap.get("fldm").equals("")) ? "" :  parMap.get("fldm");
		if(fldm.length() < 9){
			fldm = "999999999";
		}
		String name = parMap.get("name") == null ? "" :  parMap.get("name");
		String where = parMap.get("where") == null ? "" :  parMap.get("where");
		String plcx = parMap.get("plvalue") == null ? "" :  parMap.get("plvalue");
		
		
		
		String ls_sql = "select a.* from exp_main_v a where a.rcid not in ( select distinct rcid from exp_bymx where fldm like '"+fldm+"%') ";
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
				}
			}
			
		}
		
		if(plcx != null && !plcx.trim().equals("")){
			String[] pxcxs = (plcx.
			replaceAll("\n", " ").
			replaceAll("\r", " ").
			replaceAll(",", " ").
			replaceAll("，", " ").
			replaceAll("、", " ").
			replaceAll("\'", "").
			replaceAll("%", "").
			replaceAll("\"", "").
			replaceAll("　", " ").
			replaceAll(" {2,}", " ").replaceAll(" ", ",")).split(",");
			
			String yy = "";
			if(pxcxs != null && pxcxs.length > 0){
				for(int i=0;i<pxcxs.length;i++){
					if(yy.equals("")){
						yy = "'"+pxcxs[i].trim()+"'";
					}else{
						yy += ",'"+pxcxs[i].trim()+"'";
					}
				}
				ls_sql += " and (rcname in ("+yy+") or zjno in ("+yy+"))";
			}
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
	
	
	
	public Object getRcxxSelectedBy(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos,Xtuser xtuser){
		String fldm = (parMap.get("fldm") == null || parMap.get("fldm").equals("")) ? "" :  parMap.get("fldm");
		if(fldm.length() < 9){
			fldm = "999999999";
		}
		String selfldm = parMap.get("selfldm") == null ? "" :  parMap.get("selfldm");
		
		String ls_sql = "select a.* from exp_main_v a where a.rcid in ( select distinct rcid from exp_bymx where fldm like '"+fldm+"%') ";
		
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
		//sql_o = " delete from exp_flmx where fldm='"+fldm+"'";
		//this.getSimpleJdbcTemplate().update(sql_o);
		sql_o = " insert into exp_flmx(fldm,rcid,bz) select '"+fldm+"',rcid,0 from exp_main where rcid in ("+ArrayToString(rcid)+") ";
		this.getSimpleJdbcTemplate().update(sql_o);
		return 1;
	}
	public int doSelectRcbymx(String fldm,String[] rcid){
		String sql_o = "";
		//sql_o = " delete from exp_flmx where fldm='"+fldm+"'";
		//this.getSimpleJdbcTemplate().update(sql_o);
		sql_o = " insert into exp_bymx(fldm,rcid,bz) select '"+fldm+"',rcid,0 from exp_main where rcid in ("+ArrayToString(rcid)+") ";
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
			sql_o = " delete from exp_flmx where rcid in ("+rcids+")";
			this.getSimpleJdbcTemplate().update(sql_o);
		}
		return 1;
	}
	
	public int doDelSelectedbymx(String rcids ,String fldm){
		String sql_o = "";
		if(rcids != null && !rcids.equals("")){
			sql_o = " delete from exp_bymx where rcid in ("+rcids+") and fldm='"+fldm+"'";
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
		sql_q = " select a.fldm as dm,a.flmc as mc,(select count(*) from exp_fl b where b.fldm like a.fldm+'%' and a.fldm!=b.fldm)  as isc," +
				" case when len(fldm)=9 then '0' else substring(fldm,1,len(fldm)-3) end as pdm from exp_fl a order by fldm ";
		l = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(TreeBean.class));
		return l;
	}
	
	public List<TreeBean> getFlwhTree(String roledm,Integer flbz){
		List<TreeBean> l = new ArrayList();
		String sql_q = "";
		
		sql_q = " select a.fldm as dm,a.flmc as mc,(select count(*) from exp_fl b where b.fldm like a.fldm+'%' and a.fldm!=b.fldm)  as isc," +
		" case when len(fldm)=9 then '0' else substring(fldm,1,len(fldm)-9) end as pdm from exp_fl a where flbz="+flbz+" order by fldm ";

		l = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(TreeBean.class));
		return l;
	}
	
	public List<TreeBean> getFlwhUserTree(String roledm,Integer flbz,String userid){
		List<TreeBean> l = new ArrayList();
		String sql_q = "";
		if(userid != null && !userid.equals("")){
			sql_q = " select a.fldm as dm,a.flmc as mc,(select count(*) from XT_USER_FL b where b.userfl = a.fldm and b.userid="+userid+")  as isc," +
			" case when len(fldm)=9 then '0' else substring(fldm,1,len(fldm)-9) end as pdm from exp_fl a  order by fldm ";
		}else{
			sql_q = " select a.fldm as dm,a.flmc as mc,0  as isc," +
			" case when len(fldm)=9 then '0' else substring(fldm,1,len(fldm)-9) end as pdm from exp_fl a   order by fldm ";
		
		}
		
		l = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(TreeBean.class));
		return l;
	}
	
	
	public List<TreeBean> getBywhTree(String roledm,Integer flbz){
		List<TreeBean> l = new ArrayList();
		String sql_q = "";
		
		sql_q = " select a.fldm as dm,a.flmc as mc,(select count(*) from exp_by b where b.fldm like a.fldm+'%' and a.fldm!=b.fldm)  as isc," +
		" case when len(fldm)=9 then '0' else substring(fldm,1,len(fldm)-9) end as pdm from exp_by a where flbz="+flbz+" order by fldm ";

		l = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(TreeBean.class));
		return l;
	}
	
	/**
	 * 获取TAB
	 * @param key
	 * @param parFrm
	 * @return
	 */
	public String getTabPanel(String tkey,String id ) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		
		List<TabPanel> tabPanel = this.getJdbcTemplate().query("select * from tabpanel where tkey="+tkey+" order by tid", resultBeanMapper(TabPanel.class));
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<tabbar hrefmode=\"iframe\"><row>");
		for (TabPanel tab : tabPanel) {
			sb.append("<tab id=\"k_");
			sb.append(tab.getTid());
			sb.append("\" ");
			if ( tab.getSelected()!= null && tab.getSelected().trim().equals("1")) {
				sb.append("selected=\"1\" ");
			}
			sb.append("width='*' href=\"");
			sb.append(tab.getAction()+"");
			
			sb.append("\">");
			sb.append(tab.getMc());
			sb.append("</tab>");
		}
		sb.append("</row></tabbar>");
		return sb.toString();
	}
}
