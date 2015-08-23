package com.group.zsxm.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fins.gt.model.FilterInfo;
import com.fins.gt.model.SortInfo;
import com.group.core.common.LimitPage;
import com.group.zsxm.service.common.BaseService;

@Service
public class QueryService extends BaseService {
	public Object getListForRcdaByName(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos) {
		String name = parMap.get("name") == null ? "" :  parMap.get("name");
		String where = parMap.get("where") == null ? "" :  parMap.get("where");
		String ls_sql = "select * from rc_pinfo_v where 1=1 " ;
		if(where != null && !where.equals("")){
			ls_sql += " and "+where+" like '%" + name + "%'";
		}
		String depNo = parMap.get("depNo") == null ? "" :  parMap.get("depNo");
		String cxtype = parMap.get("cxtype") == null ? "" :  parMap.get("cxtype");
		
		if(cxtype.equals("1")){
			ls_sql += " and rclb = '"+depNo+"' ";
		}else if(cxtype.equals("2")){
			ls_sql += " and xl = '"+depNo+"' ";
		}else if(cxtype.equals("3")){
			ls_sql += " and rclb = '"+depNo+"' ";
		}else if(cxtype.equals("4")){
			ls_sql += " and cszy = '"+depNo+"' ";
		}else if(cxtype.equals("5")){
			ls_sql += " and zgbm = '"+depNo+"' ";
		}else if(cxtype.equals("6")){
			ls_sql += " and zc = '"+depNo+"' ";
		}
		
		String sortCond = getGridSortString(sortInfo);
		String filterCond = getGridFilterString(filterInfos);
		if (filterCond != null) {
			ls_sql += filterCond;
		}
		if (sortCond != null) {
			ls_sql += " order by " + sortCond;
		} else {
			ls_sql += " order by rcid";
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
		
		String dmkey =  parMap.get("jfhdm")==null?"":parMap.get("jfhdm");
		
		String ls_sql = "" ;
		
		if(dmkey != null && !dmkey.equals("")){
			String[] dm = dmkey.split("@");//sbdm@dwid
			
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
					" b.SBXX, b.BZ, b.HTYY from dw_ryxx_v a, " +
					" (select * from jfhsb_shzf_wh where sbdm='"+dm[0]+"' and dwid='"+dm[1]+"' ) b where a.ryid=b.ryid " +
					" and a.dwid="+dm[1]+" and a.ryid in (select distinct ryid from DW_RYXX_JFHTYPE where dwid="+dm[1]+" and jfhtype in (1,2))" +
						" ";
			}else {

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
					" b.SBXX, b.BZ, b.HTYY from dw_ryxx_v a , " +
					" (select * from jfhsb_shzf_wh where sbdm like '"+dm[0]+"%' ) b where a.ryid=b.ryid " +
					" and a.ryid in (select distinct ryid from DW_RYXX_JFHTYPE where  jfhtype in (1,2))" +
					" ";
			}
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
				" b.SBXX, b.BZ, b.HTYY from dw_ryxx_v a , " +
				" jfhsb_shzf_wh b where a.ryid=b.ryid " +
				" and  a.ryid in (select distinct ryid from DW_RYXX_JFHTYPE where  jfhtype in (1,2))" +
				" ";
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
			ls_sql += " order by b.sbdm,a.dwid,a.ryid desc ";
		}
		if (limit != null) {
			return this.queryForListWithPage(ls_sql, limit);
		} else {
			return this.simpleJdbcTemplate.queryForList(ls_sql);
		}
	}
	
	


	public Object getAjfList(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos){
		String name = parMap.get("name") == null ? "" :  parMap.get("name");
		String where = parMap.get("where") == null ? "" :  parMap.get("where");
		
		String dmkey =  parMap.get("jfhdm")==null?"":parMap.get("jfhdm");
		String ls_sql = "" ;
		if(dmkey != null && !dmkey.equals("")){
			String[] dm = dmkey.split("@");//sbdm@dwid
			if(dm.length > 1){
				ls_sql = "select dbo.get_dwmc(a.dwid) DWMC ,a.*,b.SBID,b.DWID as DC,convert(varchar,GFSJ,23) GFSJ, " +
				"convert(varchar(20),convert(numeric(20,2),ZZBZ)) AS ZZBZ, GFDD, " +
				"convert(varchar(20),convert(numeric(20,2),FWMJ)) AS FWMJ, " +
				"convert(varchar(20),convert(numeric(20,2),FWZE)) AS FWZE," +
					"convert(varchar,JFSJ,23) JFSJ, DYNBT, BZ, HTYY from dw_ryxx_v a ," +
					" (select * from jfhsb_ajf_wh where sbdm='"+dm[0]+"' and dwid="+dm[1]+") b where a.ryid=b.ryid " +
					" and a.dwid='"+dm[1]+"'  and a.ryid in (select distinct ryid from DW_RYXX_JFHTYPE where dwid="+dm[1]+" and jfhtype in (3))" +
				" ";
			}else{
				ls_sql = "select dbo.get_dwmc(a.dwid) DWMC ,a.*,b.SBID,b.DWID as DC,convert(varchar,GFSJ,23) GFSJ," +
				"convert(varchar(20),convert(numeric(20,2),ZZBZ)) AS ZZBZ, GFDD, " +
				"convert(varchar(20),convert(numeric(20,2),FWMJ)) AS FWMJ, " +
				"convert(varchar(20),convert(numeric(20,2),FWZE)) AS FWZE," +
					"convert(varchar,JFSJ,23) JFSJ, DYNBT, BZ, HTYY from dw_ryxx_v a , " +
					" (select * from jfhsb_ajf_wh where sbdm like '"+dm[0]+"%' ) b where a.ryid=b.ryid " +
					" and  a.ryid in (select distinct ryid from DW_RYXX_JFHTYPE where jfhtype in (3))" +
				" ";
			}
		}else{
			ls_sql = "select dbo.get_dwmc(a.dwid) DWMC ,a.*,b.SBID,b.DWID as DC,convert(varchar,GFSJ,23) GFSJ," +
			"convert(varchar(20),convert(numeric(20,2),ZZBZ)) AS ZZBZ, GFDD, " +
			"convert(varchar(20),convert(numeric(20,2),FWMJ)) AS FWMJ, " +
			"convert(varchar(20),convert(numeric(20,2),FWZE)) AS FWZE," +
				"convert(varchar,JFSJ,23) JFSJ, DYNBT, BZ, HTYY from dw_ryxx_v a , " +
				" jfhsb_ajf_wh b where a.ryid=b.ryid " +
				" and  a.ryid in (select distinct ryid from DW_RYXX_JFHTYPE where jfhtype in (3))" +
			" ";
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
			ls_sql += " order by b.sbdm,a.ryid desc ";
		}
		if (limit != null) {
			return this.queryForListWithPage(ls_sql, limit);
		} else {
			return this.simpleJdbcTemplate.queryForList(ls_sql);
		}
	}
	
	public List getQjfwList(String t){
		String sql = " select qstrv+'-'+qendv dm ,qstrv+'-'+qendv mc from XT_QJFW where qtype="+t +" order by qxh;";
		return this.getSimpleJdbcTemplate().queryForList(sql);
	}
	
}
