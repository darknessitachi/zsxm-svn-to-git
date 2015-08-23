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
import com.group.zsxm.entity.XtDict;
import com.group.zsxm.entity.XtDlb;
import com.group.zsxm.exception.BusException;
import com.group.zsxm.service.common.BaseService;

@Service
public class JfhsbService extends BaseService{
	
	
	public Object getList(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos){
		String name = parMap.get("name") == null ? "" :  parMap.get("name");
		String where = parMap.get("where") == null ? "" :  parMap.get("where");
		String dmkey =  parMap.get("treekey");
		String[] dm = dmkey.split("@");
		String ls_sql = "" ;
		if(dm[0].substring(4,dm[0].length()).equals("0001")){
			ls_sql = "select c.DWMC,a.DWID,a.RYID,a.XM,a.SFZ,a.SEX_MC,a.BIRTHDAY,a.XL_MC,a.ZC_MC,a.ZJZ,b.SBID,b.DWID as DC," +
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
				" b.SBXX, b.BZ, b.HTYY from dw_ryxx_v a,jfhsb_shzf b,dw_info_v c" +
				" where a.ryid=b.ryid and a.dwid=b.dwid and a.dwid=c.dwid and (b.bz=3 or b.bz=5) and b.sbdm='"+dm[0]+"'" +
					" ";
			if(dm.length > 1){
				ls_sql += " and b.dwid="+dm[1];
			}
			//ls_sql = " select a.DWMC,b.* from dw_info_v a,jfhsb_shzf b where 1=1 and a.dwid=b.dwid and (b.bz=3 or b.bz=5) and b.sbdm='"+dm+"'";
		}else{
			ls_sql = "select c.DWMC,a.*,b.SBID,b.DWID as DC,convert(varchar,GFSJ,23) GFSJ, " +
			"convert(varchar(20),convert(numeric(20,2),ZZBZ)) AS ZZBZ, GFDD, " +
			"convert(varchar(20),convert(numeric(20,2),FWMJ)) AS FWMJ, " +
			"convert(varchar(20),convert(numeric(20,2),FWZE)) AS FWZE," +
				"convert(varchar,JFSJ,23) JFSJ, DYNBT, BZ, HTYY from dw_ryxx_v a,jfhsb_ajf b,dw_info_v c" +
			" where a.ryid=b.ryid and a.dwid=b.dwid and a.dwid=c.dwid and (b.bz=3 or b.bz=5) and b.sbdm='"+dm[0]+"'" +
				" ";
			if(dm.length > 1){
				ls_sql += " and b.dwid="+dm[1];
			}
			//ls_sql = " select a.DWMC,b.* from dw_info_v a,jfhsb_ajf b where 1=1 and a.dwid=b.dwid  and (b.bz=3 or b.bz=5) and b.sbdm='"+dm+"'";
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
	
	public List<TreeBean> getJfhsbTree(){
		List<TreeBean> l = new ArrayList();
		String sql_q = "";
		//sql_q = " select dm,pdm,(mc+'   '+case when bz=1 then '启用' else ' ' end) mc,'0' isc from jfhsb order by intime desc,dm ";
		sql_q = "select * from ( " +
				" select dm,pdm,(mc+'   '+case when bz=1 then '启用' else ' ' end) mc,'0' isc from jfhsb where bz!=7 and bz!=9" +
				" union " +
				"select distinct sbdm+'@'+convert(varchar(20),dwid) dm,sbdm pdm , (select dwmc from dw_info where dwid=jfhsb_shzf.dwid)+'  总数:'+convert(varchar(20),(select count( distinct ryid) from DW_RYXX_JFHTYPE where dwid=jfhsb_shzf.dwid and jfhtype in(1,2))) mc,'0' isc from jfhsb_shzf " +
				" union " +
				"select distinct sbdm+'@'+convert(varchar(20),dwid) dm,sbdm pdm , (select dwmc from dw_info where dwid=jfhsb_ajf.dwid)+'  总数:'+convert(varchar(20),(select count( distinct ryid) from DW_RYXX_JFHTYPE where dwid=jfhsb_ajf.dwid and jfhtype =3))  mc,'0' isc from jfhsb_ajf " +
				" ) b order by b.dm  " ;
		//sql_q = " select a.bydm as dm,a.bymc as mc,(select count(*) from dw_by b where b.bydm like a.bydm+'%' and a.bydm!=b.bydm)  as isc," +
			//	" case when len(bydm)=3 then '0' else substring(bydm,1,len(bydm)-3) end as pdm from dw_by a order by bydm ";
		l = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(TreeBean.class));
		return l;
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
		
		int bz = 0;
		if(m.get("bz")!= null && m.get("bz").equals("1")){
			bz = 1;
		}
		
		if(bz == 1){
			sql_o = " select count(*) from jfhsb where bz=1 ";
			if(this.getSimpleJdbcTemplate().queryForInt(sql_o) > 0){
				throw new BusException("已启用的企业填报请先结束，否则现有填报不能启用!");
			}
		}
		
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
		
		sql_o = " select count(*) from jfhsb where bz=7 and dm='"+m.get("dm")+"' ";
		if(this.getSimpleJdbcTemplate().queryForInt(sql_o) > 0){
			throw new BusException("企业填报已结束，不能修改!");
		}
		
		int bz = 0;
		if(m.get("bz")!= null && m.get("bz").equals("1")){
			bz = 1;
		}
		
		if(bz == 1){
			sql_o = " select count(*) from jfhsb where bz=1 ";
			if(this.getSimpleJdbcTemplate().queryForInt(sql_o) > 0){
				throw new BusException("已启用的企业填报请先结束，否则现有填报不能启用!");
			}
		}
		
		sql_o = " update jfhsb set mc='"+m.get("mc")+"',sm='"+m.get("sm")+"',bz="+bz+"," +
				"sbstrtime="+transToD(m.get("sbstrtime"))+",sbendtime="+transToD(m.get("sbendtime"))+" where dm = '"+m.get("dm")+"'";
		this.getSimpleJdbcTemplate().update(sql_o);
		
		sql_o = " update jfhsb set bz="+bz+"," +
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
		
		sql_o = " select count(*) from jfhsb where  dm='"+dm+"' and bz!=0";
		if(this.getSimpleJdbcTemplate().queryForInt(sql_o) > 0){
			throw new BusException("当前填报已经启用 或 结束 ，不能删除!");
		}
		sql_o = " delete from jfhsb where dm like '"+dm+"%'";
		this.getSimpleJdbcTemplate().update(sql_o);
		
		return 1;
	}
	

	public int checkWithEnd(String dm){
		String sql_o = "";
		sql_o = " select count(*) from jfhsb_shzf where sbdm like '"+dm+"%' and bz!=3 ";
		if(this.getSimpleJdbcTemplate().queryForInt(sql_o) > 0){
			throw new BusException("生活资助/住房补助 数据 还未审核通过，是否要结束 当前填报??");
		}
		sql_o = " select count(*) from jfhsb_ajf where sbdm like '"+dm+"%' and bz!=3 ";
		if(this.getSimpleJdbcTemplate().queryForInt(sql_o) > 0){
			throw new BusException("安家费 数据 还未审核通过，是否要结束 当前填报??");
		}
		return 1;
	}
	
	
	
	public int updateEnd(String dm){
		String sql_o = "";
		sql_o = " select count(*) from jfhsb where dm='"+dm+"' and bz=0";
		if(this.getSimpleJdbcTemplate().queryForInt(sql_o) > 0){
			throw new BusException("当前填报还未启用，不能结束！");
		}
		
		sql_o = " select count(*) from jfhsb where dm='"+dm+"' and bz=7";
		if(this.getSimpleJdbcTemplate().queryForInt(sql_o) > 0){
			throw new BusException("当前填报已经结束！");
		}
		
		sql_o = " update jfhsb set bz=7 where dm like '"+dm+"%'  ";
		this.getSimpleJdbcTemplate().update(sql_o);
		
		
		//实现数据同步  ，将结束后的金凤凰数据 同步 进数据 金凤凰项目 维护中
		sql_o = " insert into jfhsb_ajf_wh select * from jfhsb_ajf where sbdm like '"+dm+"%'";
		this.getSimpleJdbcTemplate().update(sql_o);
		
		sql_o = " insert into jfhsb_shzf_wh select * from jfhsb_shzf where sbdm like '"+dm+"%'";
		this.getSimpleJdbcTemplate().update(sql_o);
		
		return 1;
	}
	/**
	 * 根据LBID 获取
	 * @param id
	 * @return
	 */
	public Map<Integer, List<XtDict>> getDictListByLbid(String id){
		Map<Integer, List<XtDict>> ms = new HashMap();
		List<XtDict> xtdicts = new ArrayList();
		int lbid = 0;
		String sql_q = " select * from xt_dlb where lbframe!=1 and lbid="+id;
		List<XtDlb> xtdlbs = this.getSimpleJdbcTemplate().query(sql_q, resultBeanMapper(XtDlb.class));
		for(int i=0;i<xtdlbs.size();i++){
			lbid = xtdlbs.get(i).getLbid();
			ms.put(lbid, this.getSimpleJdbcTemplate().query(" select dictbh,dictname from xt_dict where lbid="+lbid+" order by dictbh", resultBeanMapper(XtDict.class)));
			
		}
		return ms;
	}
	
	public Map preDatash1(String sbid){
		String sql = "";
		sql = " select count(*) from jfhsb_shzf where sbid="+sbid;
		if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select " +
					"SBID, SBDM, DWID, RYID,convert(numeric(20), SHZZBZ) SHZZBZ, " +
					"convert(numeric(20,1),SHZCSJ ) SHZCSJ, SHZZZE, ZFZFD, convert(numeric(20), ZFBTBZ) ZFBTBZ," +
					"convert(numeric(20,1),ZFZCSJ) ZFZCSJ , ZFBTZE, ZE, SM, SBXX, BZ, HTYY, ZFZFD2, convert(numeric(20,0),ZFBTBZ2) ZFBTBZ2, convert(numeric(20,1),ZFZCSJ2) ZFZCSJ2 , ZFBTZE2" +
					" from jfhsb_shzf where sbid="+sbid;
			return this.getSimpleJdbcTemplate().queryForMap(sql);
		}else{
			return new HashMap();
		}
		//return map;
	}
	
	public Map preDatash2(String sbid){
		String sql = " select count(*) from jfhsb_ajf where sbid="+sbid;
		if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select SBID, SBDM, DWID, RYID, convert(varchar,GFSJ,23) GFSJ ," +
					" ZZBZ, GFDD, FWMJ, FWZE,convert(varchar,JFSJ,23) JFSJ, DYNBT, SM, BZ," +
					" HTYY from jfhsb_ajf where sbid="+sbid;
			return this.getSimpleJdbcTemplate().queryForMap(sql);
		}else{
			return new HashMap();
		}
	}
	
	
	public int doDatashWithTg1(String sbid,Map<String,String> m){
		String sql = "";
		sql = " select count(*) from jfhsb_shzf where sbid="+sbid+" and bz=5";
		if(this.getSimpleJdbcTemplate().queryForInt(sql)==0){
			throw new BusException("当前数据不是待审状态，不能审核");
		}
		
		sql = " update jfhsb_shzf set bz=3,htyy='',shzzbz='"+transToN(m.get("shzzbz"))+"'," +
				"SHZCSJ='"+transToN(m.get("shzcsj"))+"',SHZZZE='"+transToN(m.get("shzzze"))+"'," +
				"ZFZFD='"+transToN(m.get("zfzfd"))+"',ZFBTBZ='"+transToN(m.get("zfbtbz"))+"',ZFZCSJ='"+transToN(m.get("zfzcsj"))+"'," +
				"ZFBTZE='"+transToN(m.get("zfbtze"))+"',ZFZFD2='"+transToN(m.get("zfzfd2"))+"',ZFBTBZ2='"+transToN(m.get("zfbtbz2"))+"'," +
				"ZFZCSJ2='"+transToN(m.get("zfzcsj2"))+"',ZFBTZE2='"+transToN(m.get("zfbtze2"))+"',ZE='"+transToN(m.get("ze"))+"'," +
				"SM='"+m.get("sm")+"',sbxx='"+m.get("sbxx")+"' where sbid="+sbid+" and bz=5";
		this.getSimpleJdbcTemplate().update(sql);
		return 1;
	}
	
	public int doDataxg1(String sbid,Map<String,String> m){
		String sql = "";
		sql = " update jfhsb_shzf set shzzbz='"+transToN(m.get("shzzbz"))+"'," +
				"SHZCSJ='"+transToN(m.get("shzcsj"))+"',SHZZZE='"+transToN(m.get("shzzze"))+"'," +
				"ZFZFD='"+transToN(m.get("zfzfd"))+"',ZFBTBZ='"+transToN(m.get("zfbtbz"))+"',ZFZCSJ='"+transToN(m.get("zfzcsj"))+"'," +
				"ZFBTZE='"+transToN(m.get("zfbtze"))+"',ZFZFD2='"+transToN(m.get("zfzfd2"))+"',ZFBTBZ2='"+transToN(m.get("zfbtbz2"))+"'," +
				"ZFZCSJ2='"+transToN(m.get("zfzcsj2"))+"',ZFBTZE2='"+transToN(m.get("zfbtze2"))+"',ZE='"+transToN(m.get("ze"))+"'," +
				"SM='"+m.get("sm")+"',sbxx='"+m.get("sbxx")+"' where sbid="+sbid+" ";
		this.getSimpleJdbcTemplate().update(sql);
		return 1;
	}
	
	
	public int doDatashWithTh1(String sbid,String htyy){
		String sql = "";
		sql = " select count(*) from jfhsb_shzf where sbid="+sbid+" and bz=5";
		if(this.getSimpleJdbcTemplate().queryForInt(sql)==0){
			throw new BusException("当前数据不是待审状态，不能审核");
		}
		
		sql = " update jfhsb_shzf set bz=4,htyy='"+htyy+"' where sbid="+sbid+" and bz=5";
		this.getSimpleJdbcTemplate().update(sql);
		return 1;
	}
	
	public int doDataxg2(String sbid,Map<String,String> m){
		String sql = "";
		
		
		sql = " update jfhsb_ajf set GFSJ="+transToD(m.get("gfsj"))+",ZZBZ='"+transToN(m.get("zzbz"))+"'," +
				"GFDD='"+m.get("gfdd")+"',FWMJ='"+transToN(m.get("fwmj"))+"',FWZE='"+transToN(m.get("fwze"))+"'," +
				"JFSJ="+transToD(m.get("jfsj"))+",DYNBT='"+transToN(m.get("dynbt"))+"',SM='"+m.get("sm")+"' where sbid="+sbid+" ";
		this.getSimpleJdbcTemplate().update(sql);
		return 1;
	}
	
	
	public int doDatashWithTg2(String sbid,Map<String,String> m){
		String sql = "";
		sql = " select count(*) from jfhsb_ajf where sbid="+sbid+" and bz=5";
		if(this.getSimpleJdbcTemplate().queryForInt(sql)==0){
			throw new BusException("当前数据不是待审状态，不能审核");
		}
		
		sql = " update jfhsb_ajf set bz=3,htyy='',GFSJ="+transToD(m.get("gfsj"))+",ZZBZ='"+transToN(m.get("zzbz"))+"'," +
				"GFDD='"+m.get("gfdd")+"',FWMJ='"+transToN(m.get("fwmj"))+"',FWZE='"+transToN(m.get("fwze"))+"'," +
				"JFSJ="+transToD(m.get("jfsj"))+",DYNBT='"+transToN(m.get("dynbt"))+"',SM='"+m.get("sm")+"' where sbid="+sbid+" and bz=5";
		this.getSimpleJdbcTemplate().update(sql);
		return 1;
	}
	
	public int doDatashWithTh2(String sbid,String htyy){
		String sql = "";
		sql = " select count(*) from jfhsb_ajf where sbid="+sbid+" and bz=5";
		if(this.getSimpleJdbcTemplate().queryForInt(sql)==0){
			throw new BusException("当前数据不是待审状态，不能审核");
		}
		sql = " update jfhsb_ajf set bz=4,htyy='"+htyy+"' where sbid="+sbid+" and bz=5";
		this.getSimpleJdbcTemplate().update(sql);
		return 1;
	}
}
