package com.group.zsxm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fins.gt.model.FilterInfo;
import com.fins.gt.model.SortInfo;
import com.group.core.common.LimitPage;
import com.group.zsxm.exception.BusException;
import com.group.zsxm.service.common.BaseService;

@Service
public class FzhtService extends BaseService {
	
	public List<Map<String, String>> getDwWaitSelect(Map<String,String> query){
		String sql_q = " ";
		List<Map<String, String>> list = new ArrayList();
		sql_q = " select a.* from dw_info_v a where 1=1 ";
		
		if(query != null ){
			if(query.get("field")!=null && !query.get("field").equals("")){
				sql_q += " and "+query.get("field")+" like '%"+query.get("value")+"%'";
			}
		}
		sql_q += "  order by a.dwid ";
		list = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
		return list;
	}
	
	public Map getFzhtinfoByhtid(String htid){
		Map m = new HashMap();
		if(htid != null && !htid.equals("")){
			String sql = " select a.HTID,a.HTLX,a.HTBH,a.HTFS,a.DWID,CONVERT(varchar(100), " +
					"a.HTSTRDATE, 23) HTSTRDATE,CONVERT(varchar(100), a.HTENDDATE, 23) HTENDDATE,a.ZFDZ,a.FZMJ,a.DWZJ," +
					" a.QMJNFZ,a.YHZC,a.YHZCFZ,a.XSZCFZ,a.BZ,BGDD1,BGDD2,BGDD3,BGDD4," +
					" (select b.dictname from xt_dict b where b.lbid=23 and b.dictbh=a.HTLX) as HTLX_MC," +
					" (select b.dwmc from dw_info b where b.dwid=a.dwid ) DWID_MC " +
					" from fzht_info a where a.htid='"+htid+"'  and isNull(a.status,1)=1 ";
			m = this.getSimpleJdbcTemplate().queryForMap(sql);		
		}
		return m;
	}
	
	
	
	public String doSaveFzht(String htid,Map<String, String> fzht){
		String sql = "";
		if(htid != null && !htid.equals("")){//
			if(!fzht.get("htbh").trim().equals("")){
				sql = " select count(*) from fzht_info where htbh='"+fzht.get("htbh")+"' and htid!='"+htid+"' and isNull(status,1)=1" ;
				if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
					throw new BusException("合同编号:<"+fzht.get("htbh")+"> 已经存在！");
				}
			}
			
			sql = " delete from fzht_info where htid='"+htid+"' and isNull(status,1)=1";
			this.getSimpleJdbcTemplate().update(sql);

			sql = " insert into fzht_info(HTID,HTLX,HTBH,HTFS,DWID,HTSTRDATE,HTENDDATE,ZFDZ,FZMJ,DWZJ, " +
				" QMJNFZ,YHZC,YHZCFZ,XSZCFZ,BZ,status,bgdd1,bgdd2,bgdd3,bgdd4) " +
				" values("+htid+",'"+fzht.get("htlx")+"','"+(fzht.get("htbh"))+"','"+(fzht.get("htfs").trim().equals("")?"0":fzht.get("htfs").trim())+"'," +
				"'"+fzht.get("dwid")+"',"+(fzht.get("htstrdate").equals("")?"null":("'"+fzht.get("htstrdate")+"'"))+","+(fzht.get("htenddate").equals("")?"null":("'"+fzht.get("htenddate")+"'"))+"," +
				"'"+fzht.get("zfdz")+"',"+(fzht.get("fzmj").trim().equals("")?"0":fzht.get("fzmj").trim())+"," +
				"'"+(fzht.get("dwzj").trim().equals("")?"0":fzht.get("dwzj").trim())+"','"+(fzht.get("qmjnfz").trim().equals("")?"0":fzht.get("qmjnfz").trim())+"'," +
				"'"+fzht.get("yhzc")+"','"+(fzht.get("yhzcfz").trim().equals("")?"0":fzht.get("yhzcfz").trim())+"','"+(fzht.get("xszcfz").trim().equals("")?"0":fzht.get("xszcfz").trim())+"'," +
				"'"+fzht.get("bz")+"',1,'"+fzht.get("bgdd1")+"','"+fzht.get("bgdd2")+"','"+fzht.get("bgdd3")+"','"+fzht.get("bgdd4")+"')";
			this.getSimpleJdbcTemplate().update(sql);
			
		}else {
			if(!fzht.get("htbh").trim().equals("")){
				sql = " select count(*) from fzht_info where htbh='"+fzht.get("htbh")+"' and isNull(status,1)=1" ;
				if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
					throw new BusException("合同编号:<"+fzht.get("htbh")+"> 已经存在！");
				}
			}
			
			htid = String.valueOf(this.getMaxKey("FZHT_INFO"));
			sql = " insert into fzht_info(HTID,HTLX,HTBH,HTFS,DWID,HTSTRDATE,HTENDDATE,ZFDZ,FZMJ,DWZJ, " +
				" QMJNFZ,YHZC,YHZCFZ,XSZCFZ,BZ,status,bgdd1,bgdd2,bgdd3,bgdd4) " +
				" values("+htid+",'"+fzht.get("htlx")+"','"+(fzht.get("htbh"))+"','"+(fzht.get("htfs").trim().equals("")?"0":fzht.get("htfs").trim())+"'," +
				"'"+fzht.get("dwid")+"',"+(fzht.get("htstrdate").equals("")?"null":("'"+fzht.get("htstrdate")+"'"))+","+(fzht.get("htenddate").equals("")?"null":("'"+fzht.get("htenddate")+"'"))+"," +
				"'"+fzht.get("zfdz")+"',"+(fzht.get("fzmj").trim().equals("")?"0":fzht.get("fzmj").trim())+"," +
				"'"+(fzht.get("dwzj").trim().equals("")?"0":fzht.get("dwzj").trim())+"','"+(fzht.get("qmjnfz").trim().equals("")?"0":fzht.get("qmjnfz").trim())+"'," +
				"'"+fzht.get("yhzc")+"','"+(fzht.get("yhzcfz").trim().equals("")?"0":fzht.get("yhzcfz").trim())+"','"+(fzht.get("xszcfz").trim().equals("")?"0":fzht.get("xszcfz").trim())+"'," +
				"'"+fzht.get("bz")+"',1,'"+fzht.get("bgdd1")+"','"+fzht.get("bgdd2")+"','"+fzht.get("bgdd3")+"','"+fzht.get("bgdd4")+"')";
			this.getSimpleJdbcTemplate().update(sql);
			
		}
		return htid;
	}
	
	
	public List getFzsqList(String htid){
		String sql = " ";
		List<Map<String, String>> list = new ArrayList();
		sql = " select htid,xh,CONVERT(varchar(100),sjdate, 23) sjdate,CONVERT(varchar(100),sjenddate, 23) sjenddate," +
				"ysfz,wsfz,bz,status,sjnf,YSFZZ  from fzht_fzsq where htid='"+htid+"'  and isNull(status,1)=1 order by xh";
		list = this.getJdbcTemplate().queryForList(sql);
		return list;
	}
	
	public int doFzsqI(String htid,Map<String, String > m){
		String sql = "";
		//获取 XH 最大值
		Integer maxxh = 1;
		sql = "select count(*) from fzht_fzsq where htid='"+htid+"'  and isNull(status,1)=1";
		if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select max(xh) from fzht_fzsq where htid='"+htid+"'  and isNull(status,1)=1 ";
			maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
		}
		sql = " insert into fzht_fzsq(htid,xh,sjdate,sjenddate,ysfz,wsfz,bz,sjnf,ysfzz,status) " +
			" values('"+htid+"','"+maxxh+"',"+(m.get("sjdate").equals("")?"null":("'"+m.get("sjdate")+"'"))+"," +
					""+(m.get("sjenddate").equals("")?"null":("'"+m.get("sjenddate")+"'"))+"," +
			"'"+(m.get("ysfz").trim().equals("")?"0":m.get("ysfz").trim())+"'," +
			"'"+(m.get("wsfz").trim().equals("")?"0":m.get("wsfz").trim())+"','"+m.get("bz")+"','"+m.get("sjnf")+"'," +
					"'"+(m.get("ysfzz").trim().equals("")?"0":m.get("ysfzz").trim())+"',1)";
		this.getSimpleJdbcTemplate().update(sql);
		
		return 1;
	}
	
	
	public Map preFzsqU(String htid,String xh){
		Map map = new HashMap();
		String sql = " select htid,xh,CONVERT(varchar(100),sjdate, 23) sjdate,CONVERT(varchar(100),sjenddate, 23) sjenddate," +
				"ysfz,wsfz,bz,sjnf,ysfzz from fzht_fzsq where htid='"+htid+"' and xh="+xh+" and isNull(status,1)=1";
		map = this.getSimpleJdbcTemplate().queryForMap(sql);
		return map;
	}
	
	public int doFzsqU(String htid,String xh,Map<String, String > m){
		String sql = " update fzht_fzsq set sjdate='"+m.get("sjdate")+"',ysfz='"+(m.get("ysfz").trim().equals("")?"0":m.get("ysfz").trim())+"'," +
				"wsfz='"+(m.get("wsfz").trim().equals("")?"0":m.get("wsfz").trim())+"',bz='"+m.get("bz")+"'," +
				" sjenddate='"+m.get("sjenddate")+"'," +
				" ysfzz='"+(m.get("ysfzz").trim().equals("")?"0":m.get("ysfzz").trim())+"'," +
				"sjnf='"+m.get("sjnf")+"'" +
				" where htid='"+htid+"' and xh="+xh+"   and isNull(status,1)=1 ";
		this.getSimpleJdbcTemplate().update(sql);
		return 1;
	}
	
	
	public int doFzsqD(String htid,String[] xh){
		String sql = " delete from fzht_fzsq where htid="+htid+" and xh in("+ArrayToString(xh)+")  and isNull(status,1)=1 ";
		this.getSimpleJdbcTemplate().update(sql);
		return 1;
	}
	
	
	/**
	 * 房租合同
	 * @param limit
	 * @param parMap
	 * @param sortInfo
	 * @param filterInfos
	 * @return
	 */
	public Object getListForFzht(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos){
		String name = parMap.get("name") == null ? "" :  parMap.get("name");
		String where = parMap.get("where") == null ? "" :  parMap.get("where");
		String ls_sql = "select *  from fzht_info_v where 1=1 " ;
		
		if(where != null && !where.equals("")){
			ls_sql += " and upper("+where+") like '%" + name.toUpperCase() + "%'";
		}
		
		String depNo = parMap.get("depNo") == null ? "" :  parMap.get("depNo");
		String cxtype = parMap.get("cxtype") == null ? "" :  parMap.get("cxtype");
		
		if(cxtype.equals("1")){
			ls_sql += " and htlx = '"+depNo+"' ";
		}else if(cxtype.equals("2")){
			ls_sql += " and dwid = '"+depNo+"' ";
		}else if(cxtype.equals("3")){
			ls_sql += " and fzmj = '"+depNo+"' ";
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
			ls_sql += " order by " + sortCond.split("_")[0];
		} else {
			ls_sql += " order by htid ";
		}
		if (limit != null) {
			return this.queryForListWithPage(ls_sql, limit);
		} else {
			return this.simpleJdbcTemplate.queryForList(ls_sql);
		}
	}
	
	public int doDeletefzht(String htid){
		if(htid != null && !htid.equals("")){
			
			String sql = " update fzht_info set status=9 where htid in ("+htid+")";
			this.getSimpleJdbcTemplate().update(sql);
			
			sql = " update fzht_fzsq set status=9 where htid in ("+htid+")";
			this.getSimpleJdbcTemplate().update(sql);
			
		}else{
			throw new BusException("请选择需要删除的数据！");
		}
		return 1;
	}
	
	@SuppressWarnings("unchecked")
	public List getListForXm(Map<String, String> parmMap) {
		return this.getSimpleJdbcTemplate().queryForList("{call QRY_FZHT(:type)}",parmMap);
	}
}
