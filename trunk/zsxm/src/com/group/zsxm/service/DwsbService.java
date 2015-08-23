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
import com.group.zsxm.exception.BusException;
import com.group.zsxm.service.common.BaseService;

@Service
public class DwsbService extends BaseService{
	
	public Object getList(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos,String value,String treekey){
		String name = parMap.get("name") == null ? "" :  parMap.get("name");
		String where = parMap.get("where") == null ? "" :  parMap.get("where");
		String dm =  parMap.get("treekey")==null?"":parMap.get("treekey");
		String ls_sql = "" ;
		Integer v = 0;
		ls_sql = "select count(*) from dwsb where dm='"+dm+"' ";
		if(this.getSimpleJdbcTemplate().queryForInt(ls_sql) > 0){
			ls_sql = "select isnull(value,0) from dwsb where dm='"+dm+"'";
			v = this.getSimpleJdbcTemplate().queryForInt(ls_sql);
		}
		if(limit == null && value!= null && !value.equals("")){
			v = Integer.parseInt(String.valueOf(value));
			dm = treekey;
		}
		if( v== 1){
			ls_sql = "select a.*,b.DWDM,b.DWMC,b.DWZT_MC,b.DWLX_MC,b.DWPASSWORD from dwsb1_info_v a ,dw_info_v b where a.sbdm='"+dm+"' and a.dwid=b.dwid";
		}else if(v==2){
			ls_sql = "select a.*,b.DWDM,b.DWMC,b.DWZT_MC,b.DWLX_MC,b.DWPASSWORD from dwsb2_info_v a ,dw_info_v b where a.sbdm='"+dm+"' and a.dwid=b.dwid";
		}else if(v==3){
			ls_sql = "select a.*,b.DWDM,b.DWMC,b.DWZT_MC,b.DWLX_MC,b.DWPASSWORD from dwsb3_info_v a ,dw_info_v b where a.sbdm='"+dm+"' and a.dwid=b.dwid";
		}else{
			return null;
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
			ls_sql += " order by a." + sortCond;
		} else {
			ls_sql += " order by a.status desc ";
		}
		if (limit != null) {
			return this.queryForListWithPage(ls_sql, limit);
		} else {
			return this.simpleJdbcTemplate.queryForList(ls_sql);
		}
	}
	
	
	public List<Map<String, String>> getDwsbWaitSelect(Map<String,String> query,String dm){
		String sql_q = " ";
		List<Map<String, String>> list = new ArrayList();
		if(dm != null && !dm.equals("")){
			sql_q = " select a.* from dw_info_v a  where a.dwid not in ( select dwid from dwsb_mx where substring(dm,1,4)='"+dm.substring(0,4)+"') ";
		}else{
			sql_q = " select a.* from dw_info_v a  where 1=1 ";
		}
		
		if(query != null ){
			if(query.get("field")!=null && !query.get("field").equals("")){
				sql_q += " and "+query.get("field")+" like '%"+query.get("value")+"%'";
			}
		}
		sql_q += "  order by a.dwid ";
		list = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
		return list;
	}
	
	
	public int doSelectDwsbmx(String dm,String[] dwid,String dmlx){
		String sql_o = "";
		//sql_o = " delete from xm_bymx where bydm='"+bydm+"'";
		//this.getSimpleJdbcTemplate().update(sql_o);
		sql_o = " insert into dwsb_mx(dm,dwid,bz) select '"+dm+"',dwid,0 from dw_info where dwid in ("+ArrayToString(dwid)+") ";
		this.getSimpleJdbcTemplate().update(sql_o);
		if(dmlx.equals("1")){
			sql_o = " insert into dwsb1_info(sbdm,dwid,qymc,status) select '"+dm+"',dwid,dwmc,0 from dw_info where dwid in ("+ArrayToString(dwid)+") ";
		}else if(dmlx.equals("2")){
			sql_o = " insert into dwsb2_info(sbdm,dwid,qymc,status) select '"+dm+"',dwid,dwmc,0 from dw_info where dwid in ("+ArrayToString(dwid)+") ";
		}else if(dmlx.equals("3")){
			sql_o = " insert into dwsb3_info(sbdm,dwid,qymc,status) select '"+dm+"',dwid,dwmc,0 from dw_info where dwid in ("+ArrayToString(dwid)+") ";
		}
		this.getSimpleJdbcTemplate().update(sql_o);
		return 1;
	}
	
	public int doDelSelectedDwsb(String dwids, String dm,String dmlx){
		String sql_o = "";
		if(dwids != null && !dwids.equals("")){
			sql_o = " select count(*) from dwsb_mx where dwid in ("+dwids+") and dm='"+dm+"' and bz!=0";
			if(this.getSimpleJdbcTemplate().queryForInt(sql_o) > 0){
				throw new BusException("只有待报数据才可删除！");
			}
			sql_o = " delete from dwsb_mx where dwid in ("+dwids+") and dm='"+dm+"'";
			this.getSimpleJdbcTemplate().update(sql_o);
			
			if(dmlx.equals("1")){
				sql_o = " delete from dwsb1_info where dwid in ("+dwids+") and sbdm='"+dm+"'";
			}else if(dmlx.equals("2")){
				sql_o = " delete from dwsb2_info where dwid in ("+dwids+") and sbdm='"+dm+"'";
			}else if(dmlx.equals("3")){
				sql_o = " delete from dwsb3_info where dwid in ("+dwids+") and sbdm='"+dm+"'";
			}
			this.getSimpleJdbcTemplate().update(sql_o);
		}
		return 1;
	}
	public List<TreeBean> getDwsbTree(){
		List<TreeBean> l = new ArrayList();
		String sql_q = "";
		sql_q = " select dm,pdm,(mc+'   '+case when bz=1 then '启用' when bz=7 then '结束' else '新增' end) mc,'0' isc,isnull(value,0) bz from dwsb order by intime desc,dm ";
		//sql_q = " select a.bydm as dm,a.bymc as mc,(select count(*) from dw_by b where b.bydm like a.bydm+'%' and a.bydm!=b.bydm)  as isc," +
			//	" case when len(bydm)=3 then '0' else substring(bydm,1,len(bydm)-3) end as pdm from dw_by a order by bydm ";
		l = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(TreeBean.class));
		return l;
	}
	
	/**
	 * DWSB  bz : 0:新增： 1：启用   7：结束
	 * @param m
	 * @return
	 */
	public int doAddDwsb(Map<String, String> m){
		String sql_o = "";
		String maxid = "0001";
		String pdm = "";
		//新增本级
		sql_o = "select isnull(max(dm),0)+1 from dwsb where len(dm)=4 ";
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
			sql_o = " select count(*) from dwsb where bz=1 ";
			if(this.getSimpleJdbcTemplate().queryForInt(sql_o) > 0){
				throw new BusException("已启用的企业填报请先结束，否则现有填报不能启用!");
			}
		}
		
		sql_o = " select getDate() ";
		String date = this.getSimpleJdbcTemplate().queryForObject(sql_o,String.class);
		
		sql_o = " insert into dwsb(dm,pdm,mc,sm,bz,intime,value) values('"+maxid+"','0','"+m.get("mc")+"','"+m.get("sm")+"',"+bz+",'"+date+"',0)";
		this.getSimpleJdbcTemplate().update(sql_o);
		
		sql_o = " insert into dwsb(dm,pdm,mc,sm,bz,intime,value) values('"+maxid+"0001','"+maxid+"','在孵企业','',"+bz+",'"+date+"',1)";
		this.getSimpleJdbcTemplate().update(sql_o);
		
		sql_o = " insert into dwsb(dm,pdm,mc,sm,bz,intime,value) values('"+maxid+"0002','"+maxid+"','研发机构','',"+bz+",'"+date+"',2)";
		this.getSimpleJdbcTemplate().update(sql_o);

		sql_o = " insert into dwsb(dm,pdm,mc,sm,bz,intime,value) values('"+maxid+"0003','"+maxid+"','西区院校','',"+bz+",'"+date+"',3)";
		this.getSimpleJdbcTemplate().update(sql_o);
		return 1;
	}
	
	public Map repDwsb(String dm){
		Map m = new HashMap();
		String sql_q = " select * from dwsb where dm='"+dm+"'";
		m = this.getSimpleJdbcTemplate().queryForMap(sql_q);
		return m;
	}
	
	public int doRepDwsb(Map<String, String> m){
		String sql_o = "";
		
		sql_o = " select count(*) from dwsb where bz=7 and dm='"+m.get("dm")+"' ";
		if(this.getSimpleJdbcTemplate().queryForInt(sql_o) > 0){
			throw new BusException("企业填报已结束，不能修改!");
		}
		
		int bz = 0;
		if(m.get("bz")!= null && m.get("bz").equals("1")){
			bz = 1;
		}
		
		if(bz == 1){
			sql_o = " select count(*) from dwsb where bz=1 ";
			if(this.getSimpleJdbcTemplate().queryForInt(sql_o) > 0){
				throw new BusException("已启用的企业填报请先结束，否则现有填报不能启用!");
			}
		}
		
		sql_o = " update dwsb set mc='"+m.get("mc")+"',sm='"+m.get("sm")+"',bz="+bz+" where dm = '"+m.get("dm")+"'";
		this.getSimpleJdbcTemplate().update(sql_o);
		
		sql_o = " update dwsb set bz="+bz+" where dm like '"+m.get("dm")+"%'";
		this.getSimpleJdbcTemplate().update(sql_o);
		
		return 1;
	}
	
	/**
	 * DWSB_MX : bz:  0 :新选入   5： 上报   3： 审核通过  4: 审核退回
	 * @param m
	 * @return
	 */
	public int doDelDwsb(String dm){

		String sql_o = "";
		
		sql_o = " select count(*) from dwsb where  dm='"+dm+"' and bz=1";
		if(this.getSimpleJdbcTemplate().queryForInt(sql_o) > 0){
			throw new BusException("当前填报已经启用 ，不能删除!");
		}
		
		sql_o = " delete from dwsb where dm like '"+dm+"%'";
		this.getSimpleJdbcTemplate().update(sql_o);
		
		sql_o = " delete from dwsb_mx where dm like '"+dm+"%' ";
		this.getSimpleJdbcTemplate().update(sql_o);
		
		sql_o = " delete from dwsb1_info where sbdm like '"+dm+"%' ";
		this.getSimpleJdbcTemplate().update(sql_o);
		
		sql_o = " delete from dwsb2_info where sbdm like '"+dm+"%'  ";
		this.getSimpleJdbcTemplate().update(sql_o);
		
		sql_o = " delete from dwsb3_info where sbdm like '"+dm+"%' ";
		this.getSimpleJdbcTemplate().update(sql_o);
		
		
		return 1;
	}
	
	public int checkWithEnd(String dm){
		String sql_o = "";
		sql_o = " select count(*) from dwsb_mx where dm like '"+dm+"%' and bz!=3 ";
		if(this.getSimpleJdbcTemplate().queryForInt(sql_o) > 0){
			throw new BusException("有企业 数据 还未审核通过，是否要结束 当前填报??");
		}
		return 1;
	}
	
	
	
	public int updateEnd(String dm){
		String sql_o = "";
		
		/**
		sql_o = " select count(*) from dwsb where dm='"+dm+"' and bz=0";
		if(this.getSimpleJdbcTemplate().queryForInt(sql_o) > 0){
			throw new BusException("当前填报还未启用，不能结束！");
		}
		**/
		sql_o = " select count(*) from dwsb where dm='"+dm+"' and bz=7";
		if(this.getSimpleJdbcTemplate().queryForInt(sql_o) > 0){
			throw new BusException("当前填报已经结束！");
		}
		
		sql_o = " update dwsb set bz=7 where dm like '"+dm+"%'  ";
		this.getSimpleJdbcTemplate().update(sql_o);
		
		return 1;
	}
	

	public List getDwsblxList(){
		String sql = " select * from dwsb1_lx order by dm ";
		List list = this.getSimpleJdbcTemplate().queryForList(sql);
		return list;
	}
	

	public Map preDwsb1(String dwid,String sbdm){
		Map m = new HashMap();
		String sql = "";
		sql = " select count(*) from dwsb1_info where dwid='"+dwid+"' and sbdm='"+sbdm+"'";
		if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select SBDM,DWID,QYMC,QYFRDM,ZCZJ,FZR,FZRDH,convert(varchar,ZCSJ,23) zcsj,LXR,LXRDH,CZ," +
				"QYDJZCLX,ZRQYRS,CDGLGJDFXMS,HYLB,BSRS,GJJXMS,GXJSQY,SSRS,SJXMS,SFSSQY,YJSJYSXLS," +
				"SSJXMS,YFJGS,BKXLS,QJXMS,GXJSCPS,DZXLS,GLGJDFDZJF,GJJXMJF,SJXMJF,SSJXMJF,QJXMJF" +
				",CDGLQYXMS,GLQYXMDZJF,KJHDJFZCZE,YJSYFZZC,XCPKFJFZC,GJZCS,HGJWJZJRS,GJZCHBSRS" +
				",ZRQYZSR,ZRQYGYZCZ,ZRQYJLR,ZRQYSJSJ,ZRQYCKCH,ZBSQTDQKPXS,SQZLS,FMZLS,QZSQZLS,QZFMZLS" +
				",GMGWJSZLS,zzxls,jndxyjsxrs,jnyjbysrs,kjhdrys,qzyjfzrys from dwsb1_info where dwid='"+dwid+"' and sbdm='"+sbdm+"'";
			m = this.getSimpleJdbcTemplate().queryForMap(sql);
		}
		return m;
	}
	
	
	public Map preDwsb2(String dwid,String sbdm){
		Map m = new HashMap();
		String sql = "";
		sql = " select count(*) from dwsb2_info where dwid='"+dwid+"' and sbdm='"+sbdm+"'";
		if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select SBDM,DWID,QYMC,ZZJGDM,ZCZJ,FZR,FZRDH,convert(varchar,ZCSJ,23) zcsj,LXR,LXRDH,CZ,YFJGS,JYDLFRZG,SJYSYFJG,RHQYS," +
				"ZRQY,GMYSQY,GXJSQY,SSQY,RHQYGXJSCPS,ZSR,ZZC,JYJLR,SJSJ,CKCH,ZBSQTDQSR,ZYJSRYS,BS,SS,YJSXLJYS,BKXL,DZXL,GJZC,HGRCJWJZJRYS" +
				",GJZCHBSXWRYS,SQZLXS,FMZL,SSQZLXS,SFMZL,GMGWJSZLS,CDGLGJHDFXMS,GJJXM,SJXM,SSJXM,QJXM,GLGJHDFXMDZJF,SGJJXM,SJXM2" +
				",SSJXM2,SQJXM,CDGLQYXMS,GLQYXMDZJF,KJHDJFZCZE,YJYSYFZZC,XCPKFJFZC,zzxls,jndxyjsxrs,jnyjbysrs,kjhdrys,qzyjfzrys from dwsb2_info where dwid='"+dwid+"' and sbdm='"+sbdm+"'";
			m = this.getSimpleJdbcTemplate().queryForMap(sql);
		}
		return m;
	}
	
	
	public Map preDwsb3(String dwid,String sbdm){
		Map m = new HashMap();
		String sql = "";
		sql = " select count(*) from dwsb3_info where dwid='"+dwid+"' and sbdm='"+sbdm+"'";
		if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select SBDM,DWID,QYMC,ZZJGDM,ZCZJ,FZR,FZRDH,convert(varchar,ZCSJ,23) zcsj,LXR,LXRDH,CZ,YFJGS,JYDLFRZG,SJYSYFJG,RHQYS," +
				"ZRQY,GMYSQY,GXJSQY,SSQY,RHQYGXJSCPS,ZSR,ZZC,JYJLR,SJSJ,CKCH,ZBSQTDQSR,ZYJSRYS,BS,SS,YJSXLJYS,BKXL,DZXL,GJZC,HGRCJWJZJRYS" +
				",GJZCHBSXWRYS,SQZLXS,FMZL,SSQZLXS,SFMZL,GMGWJSZLS,CDGLGJHDFXMS,GJJXM,SJXM,SSJXM,QJXM,GLGJHDFXMDZJF,SGJJXM,SJXM2" +
				",SSJXM2,SQJXM,CDGLQYXMS,GLQYXMDZJF,KJHDJFZCZE,YJYSYFZZC,XCPKFJFZC,zzxls,jndxyjsxrs,jnyjbysrs,kjhdrys,qzyjfzrys from dwsb3_info where dwid='"+dwid+"' and sbdm='"+sbdm+"'";
			m = this.getSimpleJdbcTemplate().queryForMap(sql);
		}
		return m;
	}
	
	public int doDatashWithTg(String dwid,String sbdm){
		String sql = "";
		sql = " select count(*) from dwsb_mx where dwid='"+dwid+"' and dm='"+sbdm+"' and bz=5";
		if(this.getSimpleJdbcTemplate().queryForInt(sql)==0){
			throw new BusException("当前数据不是待审状态，不能审核");
		}
		sql = " update dwsb_mx set bz=3,yy='' where dwid='"+dwid+"' and dm='"+sbdm+"' and bz=5";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = " update dwsb1_info set status=3 where dwid='"+dwid+"' and sbdm='"+sbdm+"' and status=5";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = " update dwsb2_info set status=3 where dwid='"+dwid+"' and sbdm='"+sbdm+"' and status=5";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = " update dwsb3_info set status=3 where dwid='"+dwid+"' and sbdm='"+sbdm+"' and status=5";
		this.getSimpleJdbcTemplate().update(sql);
		return 1;
	}
	
	public int doDatashWithTh(String dwid,String sbdm,String thyy){
		String sql = "";
		sql = " select count(*) from dwsb_mx where dwid='"+dwid+"' and dm='"+sbdm+"' and bz=5";
		if(this.getSimpleJdbcTemplate().queryForInt(sql)==0){
			throw new BusException("当前数据不是待审状态，不能审核");
		}
		sql = " update dwsb_mx set bz=4,yy='"+thyy+"' where dwid='"+dwid+"' and dm='"+sbdm+"' and bz=5";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = " update dwsb1_info set status=4 where dwid='"+dwid+"' and sbdm='"+sbdm+"' and status=5";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = " update dwsb2_info set status=4 where dwid='"+dwid+"' and sbdm='"+sbdm+"' and status=5";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = " update dwsb3_info set status=4 where dwid='"+dwid+"' and sbdm='"+sbdm+"' and status=5";
		this.getSimpleJdbcTemplate().update(sql);
		return 1;
	}
}
