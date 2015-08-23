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
import com.group.zsxm.exception.BusException;
import com.group.zsxm.service.common.BaseService;

@Service
public class JfhService  extends BaseService{
	
	
	/**
	 * BZ=7： 已经结束的数据
	 * BZ=9： 新增的数据
	 * @returnselect distinct ryid from DW_RYXX_JFHTYPE where dwid="+dm[1]+" and jfhtype in (1,2)
	 */
	public List<TreeBean> getJfhTree(){
		List<TreeBean> l = new ArrayList();
		String sql_q = "";
		sql_q = "select * from ( " +
				" select dm,pdm, mc,'0' isc from jfhsb where (bz=7 or bz=9) " +
				" union " +
				"select distinct sbdm+'@'+convert(varchar(20),dwid) dm,sbdm pdm , " +
				"(select dwmc from dw_info where dwid=jfhsb_shzf_wh.dwid ) mc,'0' isc from jfhsb_shzf_wh " +
				" union " +
				"select distinct sbdm+'@'+convert(varchar(20),dwid) dm,sbdm pdm , " +
				"(select dwmc from dw_info where dwid=jfhsb_ajf_wh.dwid ) mc,'0' isc from jfhsb_ajf_wh " +
				" ) b order by b.dm  " ;
		l = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(TreeBean.class));
		return l;
	}
	
	public List<TreeBean> getJfhTreeShzf(){
		List<TreeBean> l = new ArrayList();
		String sql_q = "";
		sql_q = "select * from ( " +
				" select dm,pdm, mc,'0' isc from jfhsb where bz!=0 and value!=2 " +
				" union " +
				"select distinct sbdm+'@'+convert(varchar(20),dwid) dm,sbdm pdm , " +
				"(select dwmc from dw_info where dwid=jfhsb_shzf_wh.dwid ) mc,'0' isc from jfhsb_shzf_wh " +
				" " +
				" ) b order by b.dm  " ;
		l = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(TreeBean.class));
		return l;
	}
	

	public List<TreeBean> getJfhTreeAjf(){
		List<TreeBean> l = new ArrayList();
		String sql_q = "";
		sql_q = "select * from ( " +
				" select dm,pdm, mc,'0' isc from jfhsb where bz!=0 and value!=1 " +
				" union " +
				"select distinct sbdm+'@'+convert(varchar(20),dwid) dm,sbdm pdm , " +
				"(select dwmc from dw_info where dwid=jfhsb_ajf_wh.dwid ) mc,'0' isc from jfhsb_ajf_wh " +
				" ) b order by b.dm  " ;
		l = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(TreeBean.class));
		return l;
	}
	

	public List<TreeBean> getShzfDwTree(){
		List<TreeBean> l = new ArrayList();
		String sql_q = " select dwid dm,dwmc mc,'0' pdm,'0' isc from dw_info where dwid in" +
				"( select distinct dwid from DW_RYXX_JFHTYPE where  jfhtype in (1,2) ) order by dwid ";
		
		l = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(TreeBean.class));
		return l;
	}
	

	public List<TreeBean> getAjfDwTree(){
		List<TreeBean> l = new ArrayList();
		String sql_q = " select dwid dm,dwmc mc,'0' pdm,'0' isc from dw_info where dwid in" +
				"( select distinct dwid from DW_RYXX_JFHTYPE where  jfhtype =3 ) order by dwid ";
		
		l = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(TreeBean.class));
		return l;
	}
	
	public Object getAjfList(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos){
		String name = parMap.get("name") == null ? "" :  parMap.get("name");
		String where = parMap.get("where") == null ? "" :  parMap.get("where");
		String jfhdm = parMap.get("jfhdm") == null ? "" :  parMap.get("jfhdm");
		
		String dmkey =  parMap.get("treekey");
		String[] dm = dmkey.split("@");//sbdm@dwid
		
		String ls_sql = "" ;
		
		if(dm.length > 1){
			ls_sql = "select dbo.get_dwmc(a.dwid) DWMC ,a.*,b.SBID,b.DWID as DC,convert(varchar,GFSJ,23) GFSJ, " +
					"convert(varchar(20),convert(numeric(20,2),ZZBZ)) AS ZZBZ, GFDD, " +
					"convert(varchar(20),convert(numeric(20,2),FWMJ)) AS FWMJ, " +
					"convert(varchar(20),convert(numeric(20,2),FWZE)) AS FWZE," +
				"convert(varchar,JFSJ,23) JFSJ, DYNBT, BZ, HTYY from dw_ryxx_v a left join" +
				" (select * from jfhsb_ajf_wh where sbdm='"+dm[0]+"' and dwid="+dm[1]+") b on a.ryid=b.ryid " +
				" where a.dwid='"+dm[1]+"'  and a.ryid in (select distinct ryid from DW_RYXX_JFHTYPE where dwid="+dm[1]+" and jfhtype in (3))" +
			" ";
		}else{
			ls_sql = "select dbo.get_dwmc(a.dwid) DWMC ,a.*,b.SBID,b.DWID as DC,convert(varchar,GFSJ,23) GFSJ," +
			"convert(varchar(20),convert(numeric(20,2),ZZBZ)) AS ZZBZ, GFDD, " +
			"convert(varchar(20),convert(numeric(20,2),FWMJ)) AS FWMJ, " +
			"convert(varchar(20),convert(numeric(20,2),FWZE)) AS FWZE," +
				"convert(varchar,JFSJ,23) JFSJ, DYNBT, BZ, HTYY from dw_ryxx_v a left join" +
				" (select * from jfhsb_ajf_wh where sbdm='"+dm[0]+"' ) b on a.ryid=b.ryid " +
				" where  a.ryid in (select distinct ryid from DW_RYXX_JFHTYPE where jfhtype in (3))" +
			" ";
			if(jfhdm != null && !jfhdm.equals("")){
				ls_sql += " and a.dwid ="+jfhdm;
			}
		}
	
		if(where != null && !where.equals("")){
			ls_sql += " and upper("+where+") like '%" + name.toUpperCase() + "%'";
		}
		
		String sortFieldName = "";
		if(sortInfo != null ){
			sortFieldName = sortInfo.getFieldName();
		}
		String sortCond = getGridSortString(sortInfo);
		String filterCond = getGridFilterString(filterInfos);
		if (filterCond != null) {
			ls_sql += filterCond;
		}
		if (sortCond != null ) {
			if(sortFieldName.equals("B_NL")){
				ls_sql += " order by birthday " ;
			}else{
				ls_sql += " order by " + sortCond.split("_")[0];
			}
		} else {
			ls_sql += " order by a.ryid desc ";
		}
		if (limit != null) {
			return this.queryForListWithPage(ls_sql, limit);
		} else {
			return this.simpleJdbcTemplate.queryForList(ls_sql);
		}
	}
	
	

	public Object getShzfList(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos){
		String name = parMap.get("name") == null ? "" :  parMap.get("name");
		String where = parMap.get("where") == null ? "" :  parMap.get("where");
		String jfhdm = parMap.get("jfhdm") == null ? "" :  parMap.get("jfhdm");
		String dmkey =  parMap.get("treekey");
		String[] dm = dmkey.split("@");//sbdm@dwid
		
		String ls_sql = "" ;
		
		if(dm.length > 1){

			ls_sql = "select dbo.get_dwmc(a.dwid) DWMC,a.DWID,a.RYID,a.XM,a.SFZ,a.SEX_MC,a.BIRTHDAY,a.XL_MC,a.ZC_MC,a.ZJZ,b.SBID,b.DWID as DC," +
					"convert(varchar(20),convert(numeric(20,2),b.SHZZBZ)) AS SHZZBZ," +
					" convert(varchar(20),convert(numeric(20,2),b.SHZCSJ)) AS SHZCSJ," +
					" convert(varchar(20),convert(numeric(20,2),b.SHZZZE)) AS SHZZZE, b.ZFZFD," +
					" convert(varchar(20),convert(numeric(20,2),b.ZFBTBZ)) AS ZFBTBZ," +
					"convert(varchar(20),convert(numeric(20,2),b.ZFZCSJ)) AS ZFZCSJ," +
					" convert(varchar(20),convert(numeric(20,2),b.ZFBTZE)) AS ZFBTZE," +
					"b.ZFZFD2,convert(varchar(20),convert(numeric(20,2),b.ZFBTBZ2)) AS ZFBTBZ2, " +
					"convert(varchar(20),convert(numeric(20,2),b.ZFZCSJ2)) AS ZFZCSJ2, " +
					"convert(varchar(20),convert(numeric(20,2),b.ZFBTZE2)) AS ZFBTZE2, " +
					"convert(varchar(20),convert(numeric(20,2),b.ZE)) AS ZE," +
					" b.SBXX, b.BZ, b.HTYY from dw_ryxx_v a left join " +
					" (select * from jfhsb_shzf_wh where sbdm='"+dm[0]+"' and dwid='"+dm[1]+"' ) b on a.ryid=b.ryid " +
					" where  a.dwid="+dm[1]+" and a.ryid in (select distinct ryid from DW_RYXX_JFHTYPE where dwid="+dm[1]+" and jfhtype in (1,2))" +
					" ";
		}else{

			ls_sql = "select dbo.get_dwmc(a.dwid) DWMC,a.DWID,a.RYID,a.XM,a.SFZ,a.SEX_MC,a.BIRTHDAY,a.XL_MC,a.ZC_MC,a.ZJZ,b.SBID,b.DWID as DC," +
				"convert(varchar(20),convert(numeric(20,2),b.SHZZBZ)) AS SHZZBZ," +
				" convert(varchar(20),convert(numeric(20,2),b.SHZCSJ)) AS SHZCSJ," +
				" convert(varchar(20),convert(numeric(20,2),b.SHZZZE)) AS SHZZZE, b.ZFZFD," +
				" convert(varchar(20),convert(numeric(20,2),b.ZFBTBZ)) AS ZFBTBZ," +
				"convert(varchar(20),convert(numeric(20,2),b.ZFZCSJ)) AS ZFZCSJ," +
				" convert(varchar(20),convert(numeric(20,2),b.ZFBTZE)) AS ZFBTZE," +
				"b.ZFZFD2,convert(varchar(20),convert(numeric(20,2),b.ZFBTBZ2)) AS ZFBTBZ2, " +
				"convert(varchar(20),convert(numeric(20,2),b.ZFZCSJ2)) AS ZFZCSJ2, " +
				"convert(varchar(20),convert(numeric(20,2),b.ZFBTZE2)) AS ZFBTZE2, " +
				"convert(varchar(20),convert(numeric(20,2),b.ZE)) AS ZE," +
				" b.SBXX, b.BZ, b.HTYY from dw_ryxx_v a left join " +
				" (select * from jfhsb_shzf_wh where sbdm='"+dm[0]+"' ) b on a.ryid=b.ryid " +
				" where  a.ryid in (select distinct ryid from DW_RYXX_JFHTYPE where  jfhtype in (1,2))" +
				" ";
			if(jfhdm != null && !jfhdm.equals("")){
				ls_sql += " and a.dwid ="+jfhdm;
			}
		}
		
		if(where != null && !where.equals("")){
			ls_sql += " and upper("+where+") like '%" + name.toUpperCase() + "%'";
		}
		
		String sortFieldName = "";
		if(sortInfo != null ){
			sortFieldName = sortInfo.getFieldName();
		}
		String sortCond = getGridSortString(sortInfo);
		String filterCond = getGridFilterString(filterInfos);
		if (filterCond != null) {
			ls_sql += filterCond;
		}
		if (sortCond != null ) {
			if(sortFieldName.equals("XM_MC")){
				ls_sql += " order by " + sortCond;
			}else{
				ls_sql += " order by " + sortCond.split("_")[0];
			}
		} else {
			ls_sql += " order by a.dwid,a.ryid desc ";
		}
		if (limit != null) {
			return this.queryForListWithPage(ls_sql, limit);
		} else {
			return this.simpleJdbcTemplate.queryForList(ls_sql);
		}
	}
	
	
	public Map preDatash1(String sbid){
		String sql = "";
		sql = " select count(*) from jfhsb_shzf_wh where sbid="+sbid;
		if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select " +
					"SBID, SBDM, DWID, RYID,convert(numeric(20), SHZZBZ) SHZZBZ, " +
					"convert(numeric(20,1),SHZCSJ ) SHZCSJ, SHZZZE, ZFZFD, convert(numeric(20), ZFBTBZ) ZFBTBZ," +
					"convert(numeric(20,1),ZFZCSJ) ZFZCSJ , ZFBTZE, ZE, SM, SBXX, BZ, HTYY, ZFZFD2, convert(numeric(20,0),ZFBTBZ2) ZFBTBZ2, convert(numeric(20,1),ZFZCSJ2) ZFZCSJ2 , ZFBTZE2" +
					" from jfhsb_shzf_wh where sbid="+sbid;
			return this.getSimpleJdbcTemplate().queryForMap(sql);
		}else{
			return new HashMap();
		}
		//return map;
	}
	
	public int doDataxg1(String sbid,Map<String,String> m){
		String sql = "";
		if(sbid != null && !sbid.equals("") && !sbid.toLowerCase().equals("null")){
			sql = " update jfhsb_shzf_wh set shzzbz='"+transToN(m.get("shzzbz"))+"'," +
				"SHZCSJ='"+transToN(m.get("shzcsj"))+"',SHZZZE='"+transToN(m.get("shzzze"))+"'," +
				"ZFZFD='"+transToN(m.get("zfzfd"))+"',ZFBTBZ='"+transToN(m.get("zfbtbz"))+"',ZFZCSJ='"+transToN(m.get("zfzcsj"))+"'," +
				"ZFBTZE='"+transToN(m.get("zfbtze"))+"',ZFZFD2='"+transToN(m.get("zfzfd2"))+"',ZFBTBZ2='"+transToN(m.get("zfbtbz2"))+"'," +
				"ZFZCSJ2='"+transToN(m.get("zfzcsj2"))+"',ZFBTZE2='"+transToN(m.get("zfbtze2"))+"',ZE='"+transToN(m.get("ze"))+"'," +
				"SM='"+m.get("sm")+"',sbxx='"+m.get("sbxx")+"' where sbid="+sbid+" ";
				this.getSimpleJdbcTemplate().update(sql);

		}else{
			sbid =  String.valueOf(this.getMaxKey("JFHSB_SHZF"));
			sql = " insert into jfhsb_shzf_wh(SBID,ryid,SBDM,DWID," +
					"SHZZBZ,SHZCSJ,SHZZZE,ZFZFD,ZFBTBZ,ZFZCSJ,ZFBTZE,ZFZFD2,ZFBTBZ2,ZFZCSJ2,ZFBTZE2,ZE,SM" +
				",SBXX,BZ) " +
				"values('"+sbid+"',"+m.get("ryid")+",'"+m.get("treekey").substring(0,4)+"0001','"+m.get("dwid")+"','"+transToN(m.get("shzzbz"))+"'," +
						"'"+transToN(m.get("shzcsj"))+"','"+transToN(m.get("shzzze"))+"','"+transToN(m.get("zfzfd"))+"','"+transToN(m.get("zfbtbz"))+"'," +
						"'"+transToN(m.get("zfzcsj"))+"','"+transToN(m.get("zfbtze"))+"','"+transToN(m.get("zfzfd2"))+"','"+transToN(m.get("zfbtbz2"))+"'," +
					"'"+transToN(m.get("zfzcsj2"))+"','"+transToN(m.get("zfbtze2"))+"','"+transToN(m.get("ze"))+"','"+m.get("sm")+"'," +
						"'"+m.get("sbxx")+"',0)";
			this.getSimpleJdbcTemplate().update(sql);
	
		}
		return 1;
	}
	
	
	public Map preDatash2(String sbid){
		String sql = " select count(*) from jfhsb_ajf_wh where sbid="+sbid;
		if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select SBID, SBDM, DWID, RYID, convert(varchar,GFSJ,23) GFSJ ," +
					" ZZBZ, GFDD, FWMJ, FWZE,convert(varchar,JFSJ,23) JFSJ, DYNBT, SM, BZ," +
					" HTYY from jfhsb_ajf_wh where sbid="+sbid;
			return this.getSimpleJdbcTemplate().queryForMap(sql);
		}else{
			return new HashMap();
		}
	}
	

	public int doDataxg2(String sbid,Map<String,String> m){
		String sql = "";
		if(sbid != null && !sbid.equals("") && !sbid.toLowerCase().equals("null")){
			sql = " update jfhsb_ajf_wh set GFSJ="+transToD(m.get("gfsj"))+",ZZBZ='"+transToN(m.get("zzbz"))+"'," +
				"GFDD='"+m.get("gfdd")+"',FWMJ='"+transToN(m.get("fwmj"))+"',FWZE='"+transToN(m.get("fwze"))+"'," +
				"JFSJ="+transToD(m.get("jfsj"))+",DYNBT='"+transToN(m.get("dynbt"))+"',SM='"+m.get("sm")+"' where sbid="+sbid+" ";
			this.getSimpleJdbcTemplate().update(sql);
	
		}else{
			sbid =  String.valueOf(this.getMaxKey("JFHSB_AJF"));
			
			sql = " insert into jfhsb_ajf_wh(SBID,RYID,SBDM,DWID,GFSJ,ZZBZ,GFDD,FWMJ,FWZE,JFSJ,DYNBT,SM,BZ) " +
					"values('"+sbid+"',"+m.get("ryid")+",'"+m.get("treekey").substring(0,4)+"0002','"+m.get("dwid")+"',"+transToD(m.get("gfsj"))+"," +
					"'"+transToN(m.get("zzbz"))+"','"+m.get("gfdd")+"','"+transToN(m.get("fwmj"))+"','"+transToN(m.get("fwze"))+"'," +
					""+transToD(m.get("jfsj"))+",'"+transToN(m.get("dynbt"))+"','"+m.get("sm")+"',0)";
			this.getSimpleJdbcTemplate().update(sql);
			
		}
		return 1;
	}
	

	/**
	 * JFHSB  bz : 0:新增： 1：启用   7：结束  9:维护中新增
	 * @param m
	 * @return
	 */
	public int doAddJfhsb(Map<String, String> m){
		String sql_o = "";
		String maxid = "0001";
		String pdm = "";
		//新增本级
		sql_o = "select isnull(max(dm),0)+1 from jfhsb where len(dm)=4 ";
		Integer dm = this.getSimpleJdbcTemplate().queryForInt(sql_o);
		maxid = String.valueOf(dm);
		for(int i=String.valueOf(dm).length();i<4;i++){
			maxid = "0"+maxid;
		}
		
		int bz = 9;
		
		
		sql_o = " select getDate() ";
		String date = this.getSimpleJdbcTemplate().queryForObject(sql_o,String.class);
		
		sql_o = " insert into jfhsb(dm,pdm,mc,sm,bz,intime,value,sbstrtime,sbendtime) values('"+maxid+"','0','"+m.get("mc")+"','"+m.get("sm")+"',"+bz+",'"+date+"',0,"+transToD(m.get("sbstrtime"))+","+transToD(m.get("sbendtime"))+")";
		this.getSimpleJdbcTemplate().update(sql_o);
		
		sql_o = " insert into jfhsb(dm,pdm,mc,sm,bz,intime,value,sbstrtime,sbendtime) values('"+maxid+"0001','"+maxid+"','生活资助/住房补助','',"+bz+",'"+date+"',1,"+transToD(m.get("sbstrtime"))+","+transToD(m.get("sbendtime"))+")";
		this.getSimpleJdbcTemplate().update(sql_o);
		
		sql_o = " insert into jfhsb(dm,pdm,mc,sm,bz,intime,value,sbstrtime,sbendtime) values('"+maxid+"0002','"+maxid+"','安家费','',"+bz+",'"+date+"',2,"+transToD(m.get("sbstrtime"))+","+transToD(m.get("sbendtime"))+")";
		this.getSimpleJdbcTemplate().update(sql_o);

		return 1;
	}
	

	public Map repJfhsb(String dm){
		Map m = new HashMap();
		String sql_q = " select DM, PDM, MC, SM, BZ, INTIME, VALUE, convert(varchar(20),SBSTRTIME,23) SBSTRTIME, convert(varchar(20),SBENDTIME,23)  SBENDTIME from jfhsb where dm='"+dm+"'";
		m = this.getSimpleJdbcTemplate().queryForMap(sql_q);
		return m;
	}

	public int doRepJfhsb(Map<String, String> m){
		String sql_o = "";
		
		
		sql_o = " update jfhsb set mc='"+m.get("mc")+"',sm='"+m.get("sm")+"'," +
				"sbstrtime="+transToD(m.get("sbstrtime"))+",sbendtime="+transToD(m.get("sbendtime"))+" where dm = '"+m.get("dm")+"'";
		this.getSimpleJdbcTemplate().update(sql_o);
		
		sql_o = " update jfhsb set " +
				"sbstrtime="+transToD(m.get("sbstrtime"))+",sbendtime="+transToD(m.get("sbendtime"))+" where dm like '"+m.get("dm")+"%'";
		this.getSimpleJdbcTemplate().update(sql_o);
		
		return 1;
	}
	
	
	/**
	 * DWSB_MX : bz:  0 :待报   5： 上报   3： 审核通过  4: 审核退回
	 * @param m
	 * @return
	 */
	public int doDelJfhsb(String dm){

		String sql_o = "";
		
		sql_o = " select count(*) from jfhsb where  dm='"+dm+"' and bz=7";
		if(this.getSimpleJdbcTemplate().queryForInt(sql_o) > 0){
			throw new BusException("当前填报非维护时新增 ，不能删除!");
		}
		sql_o = " delete from jfhsb where dm like '"+dm+"%'";
		this.getSimpleJdbcTemplate().update(sql_o);
		
		return 1;
	}
	
}
