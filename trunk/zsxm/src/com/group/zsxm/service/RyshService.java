package com.group.zsxm.service;

import java.sql.SQLException;
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
import com.sun.java_cup.internal.internal_error;
import com.sun.org.apache.bcel.internal.generic.LADD;
@Service
public class RyshService  extends BaseService{
	public Object getRyxxList(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos,String dwid){
		String sql = " ";
		//List<Map<String, String>> list = new ArrayList();
		String name = parMap.get("name") == null ? "" :  parMap.get("name");
		String where = parMap.get("where") == null ? "" :  parMap.get("where");
		
		sql = " select * from dw_ryxx_sb_v a where 1=1 and sbbz in (1,2,3,4) ";
		if(dwid != null && !dwid.equals("") && !dwid.equals("root")){
			if(dwid.equals("a1")){
				sql += " and dwid in (select dwid from dw_info where isnull(isjfh,0) =1) ";
			}else if(dwid.equals("a2")){
				sql += " and dwid in (select dwid from dw_info where isnull(isjfh,0) =0) ";
			}else{
				sql += " and dwid="+dwid;
			}
			
		}
		if(where != null && !where.equals("") && !name.equals("")){
			sql += " and a."+where+" like '"+name.replaceAll("'", "\'")+"%'";
		}
		String sortCond = getGridSortString(sortInfo);
		String filterCond = getGridFilterString(filterInfos);
		if (filterCond != null) {
			sql += filterCond;
		}
		if (sortCond != null) {
			sql += " order by " + sortCond.split("_")[0];
		} else {
			sql += " order by dwid,RYID";
		}
		if (limit != null) {
			return this.queryForListWithPage(sql, limit);
		} else {
			return this.simpleJdbcTemplate.queryForList(sql);
		}
		
	}
	
	public List<TreeBean> getDwinfoTree(){
		List<TreeBean> l = new ArrayList();
		String sql_q = "";
		TreeBean t1 = new TreeBean();
		t1.setDm("a1");
		t1.setPdm("0");
		t1.setMc("金凤凰资助企业");
		t1.setIsc("0");
		
		TreeBean t2 = new TreeBean();
		t2.setDm("a2");
		t2.setPdm("0");
		t2.setMc("其它企业");
		t2.setIsc("0");
		
		sql_q = "select * from (" +
				" select dwid dm,'a1' pdm,dwmc mc,'0' isc from dw_info " +
				" where dwid in ( select distinct dwid from dw_ryxx_sb where sbbz in (1,2,3,4))" +
				" and isnull(isjfh,0) =1 " +
				" union " +
				" select dwid dm,'a2' pdm,dwmc mc,'0' isc from dw_info " +
				" where dwid in ( select distinct dwid from dw_ryxx_sb where sbbz in (1,2,3,4))" +
				" and isnull(isjfh,0) =0 " +
				" ) b order by  b.pdm,b.dm ";
		//sql_q = " select a.bydm as dm,a.bymc as mc,(select count(*) from dw_by b where b.bydm like a.bydm+'%' and a.bydm!=b.bydm)  as isc," +
			//	" case when len(bydm)=3 then '0' else substring(bydm,1,len(bydm)-3) end as pdm from dw_by a order by bydm ";
		l = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(TreeBean.class));
		if(l.size() > 0){
			
			sql_q = " select count(*) from dw_info where dwid in ( select distinct dwid from dw_ryxx_sb where sbbz in (1,2,3,4)) and isnull(isjfh,0) =1 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql_q);
			l.add(0,t1);
			l.add(c+1,t2);
		}
		
		return l;
	}
	
	public Map preRyxxU(String dwid,String ryid){
		Map map = new HashMap();
		String sql = " select * from dw_ryxx_sb_v a where a.dwid='"+dwid+"' and ryid="+ryid+" ";
		map = this.getSimpleJdbcTemplate().queryForMap(sql);
		return map;
	}
	
	public List getJfhtypeByRyid(String dwid,String ryid){
		String sql = " select * from DW_RYXX_JFHTYPE where dwid="+dwid+" and ryid="+ryid;
		return this.getSimpleJdbcTemplate().queryForList(sql);
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
	
	/**
	 * 人员新增
	 * @return
	 */
	public int doRyxz(String dwid,Map<String, String > m,String[] tt,String[] xx,String[] yy,String[] fj,String[] fjmc,String[] ttype,String fjpath,String[] xtdict28s,String[] jt){
		String sql = "";
		//获取 XH 最大值
		if(m.get("sfz") == null || m.get("sfz").trim().equals("")){
			throw new BusException("身份证号不能为空!");
		}
		sql = " select count(*) from dw_ryxx_sb where sfz='"+m.get("sfz")+"' and isnull(status,1) in (1,2)";
		if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			throw new BusException("身份证号已经存在!");
		}
		
		sql = " select count(*) from dw_ryxx where sfz='"+m.get("sfz")+"' and isnull(status,1) in (1,2)";
		if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			throw new BusException("身份证号已经存在!");
		}
		
		Integer maxxh = 1;
		sql = "select count(*) from dw_ryxx_sb where dwid='"+dwid+"'  and isNull(status,1)=1";
		if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select max(xh) from dw_ryxx_sb where dwid='"+dwid+"'  and isNull(status,1)=1";
			maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
		}
		
		String ryid = String.valueOf(this.getMaxKey("DW_RYXX"));
		sql = " insert into dw_ryxx_sb(ryid,dwid,xh,xm,sex,birthday, xl,xw,zc,zw," +
				"hwlxqk,yjfx,qpsj,zjz,sm,sfz,status,intime,zzmm,jg,hjd,ryjl,fjmc,fjpath,sbbz,SHZZBZ,ZFZZBZ,AJFZZBZ,JFHKSTIME,JFHJSTIME) " +
			" values("+ryid+",'"+dwid+"','"+maxxh+"','"+m.get("xm")+"','"+m.get("sex")+"'," +
			""+transToD(m.get("birthday"))+",'"+m.get("xl")+"','','"+m.get("zc")+"',''," +
			"'"+m.get("hwlxqk")+"','"+m.get("yjfx")+"',"+transToD(m.get("qpsj"))+",'"+m.get("zjz")+"','"+m.get("sm")+"','"+m.get("sfz")+"',1,getdate()," +
			"'"+m.get("zzmm")+"','"+m.get("jg")+"','"+m.get("hjd")+"','"+m.get("ryjl")+"','"+m.get("fjmc")+"'" +
					",'"+fjpath+"',2,"+transToN(m.get("shzzbz"))+","+transToN(m.get("zfzzbz"))+","+transToN(m.get("ajfzzbz"))+"" +
					","+transToD(m.get("jfhkstime"))+","+transToD(m.get("jfhjstime"))+")";
		this.getSimpleJdbcTemplate().update(sql);
		
		if(fj != null && fj.length > 0){
			for(int i=0;i<fj.length;i++){
				sql = " insert into dw_ryxx_fj(dwid,ryid,tt,xx,yy,fj,fjmc,type,intime,fjpath,shbz)" +
						" values("+dwid+","+ryid+","+tt[i]+","+xx[i]+","+yy[i]+",'"+fj[i]+"','"+fjmc[i]+"','"+ttype[i]+"',getdate(),'"+fjpath+"',2)";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		
		if(xtdict28s != null ){
			for(int i=0;i<xtdict28s.length;i++){
				sql = "insert into dw_ryxx_tr_sb(dwid,ryid,dictbh) values("+dwid+","+ryid+",'"+xtdict28s[i]+"')";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		
		
		//////////////////////////////////////////////////////////////////////////
		//数据备份 STATUS： 1：正常备份    2：归档：  3：冻结备份
		String jfts = "";
		sql =" select jfhtype from DW_RYXX_JFHTYPE where dwid="+dwid+" and ryid="+ryid;
		List<Map<String, Object>> jfhtypes = this.getSimpleJdbcTemplate().queryForList(sql);
		for(int i=0;i<jfhtypes.size();i++){
			if(i==0){
				jfts = String.valueOf(jfhtypes.get(i).get("jfhtype"));
			}else{
				jfts += ","+String.valueOf(jfhtypes.get(i).get("jfhtype"));
			}
		}
		
		String jfrs = "";
		sql = " select dictbh from DW_RYXX_TR where dwid="+dwid +" and ryid="+ryid;
		List<Map<String, Object>> jfhtrs = this.getSimpleJdbcTemplate().queryForList(sql);
		for(int i=0;i<jfhtrs.size();i++){
			if(i==0){
				jfrs = String.valueOf(jfhtrs.get(i).get("dictbh"));
			}else{
				jfrs += ","+String.valueOf(jfhtrs.get(i).get("dictbh"));
			}
		}
		
		
		//以审核过的新数据为准
		sql = " insert into dw_ryxx select * from dw_ryxx_sb where dwid="+dwid+" and ryid="+ryid ;
		this.getSimpleJdbcTemplate().update(sql);
		
		for(int i=0;i<jt.length;i++){
			sql = " insert into DW_RYXX_JFHTYPE(DWID, RYID, JFHTYPE)" +
					"values("+dwid+","+ryid+","+jt[i]+")";
			this.getSimpleJdbcTemplate().update(sql);
		}
		
		return 1;
	}
	/***
	 * 修改人员信息 
	 * 注： SBBZ：1：企业上报   2：审核通过   3：审核退回   4：数据发回
	 * @param dwid
	 * @param ryid
	 * @param m
	 * @param jt
	 * @return
	 */
	public int doRyxg(String dwid,String ryid,Map<String, String > m,String[] jt,String[] xtdict28s){
		String sql = "";
		if(m.get("sfz") == null || m.get("sfz").trim().equals("")){
			throw new BusException("身份证号不能为空!");
		}
		sql = " select count(*) from dw_ryxx_sb where sfz='"+m.get("sfz")+"' and ryid != "+ryid+" and isnull(status,1) in (1,2) ";
		if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			throw new BusException("身份证号已经存在!");
		}
		
		sql = " update dw_ryxx_sb set xm='"+m.get("xm")+"',sex='"+m.get("sex")+"',birthday="+transToD(m.get("birthday"))+"," +
		" xl='"+m.get("xl")+"',xw='',zc='"+m.get("zc")+"',zw='',hwlxqk='"+m.get("hwlxqk")+"'," +
				"yjfx='"+m.get("yjfx")+"',qpsj="+transToD(m.get("qpsj"))+",zjz='"+m.get("zjz")+"',sm='"+m.get("sm")+"'" +
						",sfz='"+m.get("sfz")+"',zzmm='"+m.get("zzmm")+"',jg='"+m.get("jg")+"',hjd='"+m.get("hjd")+"'" +
						",ryjl='"+m.get("ryjl")+"', "
						+
						"SHZZBZ="+transToN(m.get("shzzbz"))+",ZFZZBZ="+transToN(m.get("zfzzbz"))+"" +
						",AJFZZBZ="+transToN(m.get("ajfzzbz"))+",JFHKSTIME="+transToD(m.get("jfhkstime"))
						+",JFHJSTIME="+transToD(m.get("jfhjstime"))+
				" where dwid='"+dwid+"' and ryid="+ryid+"   ";
		this.getSimpleJdbcTemplate().update(sql);

		
		sql = "delete from dw_ryxx_tr_sb where dwid="+dwid+" and ryid="+ryid;
		this.getSimpleJdbcTemplate().update(sql);
		if(xtdict28s != null ){
			for(int i=0;i<xtdict28s.length;i++){
				sql = "insert into dw_ryxx_tr_sb(dwid,ryid,dictbh) values("+dwid+","+ryid+",'"+xtdict28s[i]+"')";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		
		
		//////////////////////////////////////////////////////////////////////////
		//数据备份 STATUS： 1：正常备份    2：归档：  3：冻结备份
		String jfts = "";
		sql =" select jfhtype from DW_RYXX_JFHTYPE where dwid="+dwid+" and ryid="+ryid;
		List<Map<String, Object>> jfhtypes = this.getSimpleJdbcTemplate().queryForList(sql);
		for(int i=0;i<jfhtypes.size();i++){
			if(i==0){
				jfts = String.valueOf(jfhtypes.get(i).get("jfhtype"));
			}else{
				jfts += ","+String.valueOf(jfhtypes.get(i).get("jfhtype"));
			}
		}
		
		String jfrs = "";
		sql = " select dictbh from DW_RYXX_TR where dwid="+dwid +" and ryid="+ryid;
		List<Map<String, Object>> jfhtrs = this.getSimpleJdbcTemplate().queryForList(sql);
		for(int i=0;i<jfhtrs.size();i++){
			if(i==0){
				jfrs = String.valueOf(jfhtrs.get(i).get("dictbh"));
			}else{
				jfrs += ","+String.valueOf(jfhtrs.get(i).get("dictbh"));
			}
		}
		
		//删除原有数据
		sql ="delete from dw_ryxx where dwid="+dwid +" and ryid="+ryid;
		this.getSimpleJdbcTemplate().update(sql);
		
		//以审核过的新数据为准
		sql = " insert into dw_ryxx select * from dw_ryxx_sb where dwid="+dwid+" and ryid="+ryid ;
		this.getSimpleJdbcTemplate().update(sql);
		
		//人员金凤上报类型
		sql = " delete from DW_RYXX_JFHTYPE where dwid="+dwid+" and ryid="+ryid;
		this.getSimpleJdbcTemplate().update(sql);
		if(jt != null ){
			for(int i=0;i<jt.length;i++){
				sql = " insert into DW_RYXX_JFHTYPE(DWID, RYID, JFHTYPE)" +
						"values("+dwid+","+ryid+","+jt[i]+")";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		
		return 1;
	}
	
	/***
	 * 审核通过  
	 * 注： SBBZ：1：企业上报   2：审核通过   3：审核退回   4：数据发回
	 * @param dwid
	 * @param ryid
	 * @param m
	 * @param jt
	 * @return
	 */
	public int doShtg(String dwid,String ryid,Map<String, String > m,String[] jt){
		String sql = "";
		sql = " update dw_ryxx_sb set sbbz=2,SHZZBZ="+transToN(m.get("shzzbz"))+",ZFZZBZ="+transToN(m.get("zfzzbz"))+"" +
				",AJFZZBZ="+transToN(m.get("ajfzzbz"))+",JFHKSTIME="+transToD(m.get("jfhkstime"))+"" +
						",JFHJSTIME="+transToD(m.get("jfhjstime"))+" where dwid="+dwid+" and ryid="+ryid;
		this.getSimpleJdbcTemplate().update(sql);
		
		
		
		//////////////////////////////////////////////////////////////////////////
		//数据备份 STATUS： 1：正常备份    2：归档：  3：冻结备份
		String jfts = "";
		sql =" select jfhtype from DW_RYXX_JFHTYPE where dwid="+dwid+" and ryid="+ryid;
		List<Map<String, Object>> jfhtypes = this.getSimpleJdbcTemplate().queryForList(sql);
		for(int i=0;i<jfhtypes.size();i++){
			if(i==0){
				jfts = String.valueOf(jfhtypes.get(i).get("jfhtype"));
			}else{
				jfts += ","+String.valueOf(jfhtypes.get(i).get("jfhtype"));
			}
		}
		
		String jfrs = "";
		sql = " select dictbh from DW_RYXX_TR where dwid="+dwid +" and ryid="+ryid;
		List<Map<String, Object>> jfhtrs = this.getSimpleJdbcTemplate().queryForList(sql);
		for(int i=0;i<jfhtrs.size();i++){
			if(i==0){
				jfrs = String.valueOf(jfhtrs.get(i).get("dictbh"));
			}else{
				jfrs += ","+String.valueOf(jfhtrs.get(i).get("dictbh"));
			}
		}
		
		sql = " insert into dw_ryxx_back(DWID, XH, STATUS, RYID, XM, SEX, BIRTHDAY, XL, XW, ZC, ZW, HWLXQK, YJFX, QPSJ, ZJZ, SM, SFZ, INTIME, ZZMM, RYJL, JG, HJD, FJMC, FJPATH, SBBZ, TCRC, SHZZBZ, ZFZZBZ, AJFZZBZ, JFHKSTIME,JFHJSTIME, THYY, FHYY, BTIME,JFHTYPES,JFHTRS) " +
				" select DWID, XH, 1, RYID, XM, SEX, BIRTHDAY, XL, XW, ZC, ZW, HWLXQK, YJFX, QPSJ, ZJZ, SM, SFZ, INTIME, ZZMM, RYJL, JG, HJD, FJMC, FJPATH, SBBZ, TCRC, SHZZBZ, ZFZZBZ, AJFZZBZ, JFHKSTIME,JFHJSTIME, THYY, FHYY, getdate(),'"+jfts+"','"+jfrs+"' from dw_ryxx where dwid="+dwid+" and ryid="+ryid;
		this.getSimpleJdbcTemplate().update(sql);
		////////////////////////////////////////////////////////////////////////////////////
		
		//删除原有数据
		sql ="delete from dw_ryxx where dwid="+dwid +" and ryid="+ryid;
		this.getSimpleJdbcTemplate().update(sql);
		
		//以审核过的新数据为准
		sql = " insert into dw_ryxx select * from dw_ryxx_sb where dwid="+dwid+" and ryid="+ryid ;
		this.getSimpleJdbcTemplate().update(sql);
		
		//人员金凤上报类型
		sql = " delete from DW_RYXX_JFHTYPE where dwid="+dwid+" and ryid="+ryid;
		this.getSimpleJdbcTemplate().update(sql);
		if(jt != null ){
			for(int i=0;i<jt.length;i++){
				sql = " insert into DW_RYXX_JFHTYPE(DWID, RYID, JFHTYPE)" +
						"values("+dwid+","+ryid+","+jt[i]+")";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		
		sql = "insert into DW_RYXX_FJ_BACK(DWID, RYID, TT, XX, YY, FJ, FJMC, INTIME, TYPE, SHBZ, FJPATH, BTIME)" +
				" select DWID, RYID, TT, XX, YY, FJ, FJMC, INTIME, TYPE, SHBZ, FJPATH, getdate() from dw_ryxx_fj where dwid="+dwid+" and ryid="+ryid;
		this.getSimpleJdbcTemplate().update(sql);
		
		sql =" update DW_RYXX_FJ set shbz=2 where dwid="+dwid+" and ryid="+ryid;
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = " delete from DW_RYXX_TR where dwid="+dwid+" and ryid="+ryid;
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = " insert into DW_RYXX_TR select * from DW_RYXX_TR_sb where dwid="+dwid+" and ryid="+ryid;
		this.getSimpleJdbcTemplate().update(sql);
		
		return 1;
	}
	
	/**
	 * 数据退回
	 * @param dwid
	 * @param ryid
	 * @param thyy
	 * @return
	 */
	public int doShth(String dwid,String ryid,String thyy){
		String sql = "";
		sql = " update dw_ryxx_sb set sbbz=3 ,thyy='"+thyy+"' where dwid in ("+dwid+") and ryid in ("+ryid+") and sbbz=1 ";
		this.getSimpleJdbcTemplate().update(sql);
		return 1;
	}
	
	/**
	 * 数据发回
	 * @param dwid
	 * @param ryid
	 * @param thyy
	 * @return
	 */
	public int doShfh(String dwid,String ryid,String thyy){
		String sql = "";
		sql = " update dw_ryxx_sb set sbbz=4 ,fhyy='"+thyy+"' where dwid in ("+dwid+") and ryid in ("+ryid+") and sbbz=2";
		this.getSimpleJdbcTemplate().update(sql);
		return 1;
	}
	
	/***
	 * 
	 * @param dwid
	 * @param ryid
	 * @param status 1: 正常    2：归档    5：冻结   
	 * 操作传入参数status： 8:取消归档   9：取消冻结
	 * @return
	 */
	public int doRyopt(String dwid,String ryid,String status){
		String sql = "";
		if(!status.equals("1")){
			sql = " select count(*) from dw_ryxx_sb where ryid in ("+ryid+") and dwid in ("+dwid+") and sbbz != 2";
			if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
				throw new BusException("有相应的数据不是审核通过的数据，不可以操作!");
			}
			if(status.equals("2")){
				sql = " select count(*) from dw_ryxx_sb where ryid in ("+ryid+") and dwid in ("+dwid+") and status=5";
				if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
					throw new BusException("有相应的数据已冻结，不可以归档!");
				}
			}
		}
		if(status.equals("8")){
			sql = " update dw_ryxx_sb set status=1 where ryid in ("+ryid+") and dwid in ("+dwid+") and status=2";
			this.getSimpleJdbcTemplate().update(sql);
			sql = " update dw_ryxx set status=1 where ryid in ("+ryid+") and dwid in ("+dwid+") and status=2";
			this.getSimpleJdbcTemplate().update(sql);
		}else if(status.equals("9")){
			sql = " update dw_ryxx_sb set status=1,djyy='',djsm='' where ryid in ("+ryid+") and dwid in ("+dwid+") and status=5";
			this.getSimpleJdbcTemplate().update(sql);
			sql = " update dw_ryxx set status=1,djyy='',djsm='' where ryid in ("+ryid+") and dwid in ("+dwid+") and status=5";
			this.getSimpleJdbcTemplate().update(sql);
		}else{
			sql = " update dw_ryxx_sb set status="+status +" where ryid in ("+ryid+") and dwid in ("+dwid+")";
			this.getSimpleJdbcTemplate().update(sql);
			sql = " update dw_ryxx set status="+status +" where ryid in ("+ryid+") and dwid in ("+dwid+")";
			this.getSimpleJdbcTemplate().update(sql);
		}
		return 1;
	}
	
	public int doRydj(String dwid,String ryid,Map<String, String> m){
		String sql = "";
		sql = " select count(*) from dw_ryxx_sb where ryid in ("+ryid+") and dwid in ("+dwid+") and sbbz != 2";
		if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			throw new BusException("有相应的数据不是审核通过的数据，不可以操作!");
		}
		sql = " update dw_ryxx_sb set status=5,djyy='"+m.get("djyy")+"',djsm='"+m.get("djsm")+"' where ryid in ("+ryid+") and dwid in ("+dwid+")";
		this.getSimpleJdbcTemplate().update(sql);
		sql = " update dw_ryxx set status=5,djyy='"+m.get("djyy")+"',djsm='"+m.get("djsm")+"' where ryid in ("+ryid+") and dwid in ("+dwid+")";
		this.getSimpleJdbcTemplate().update(sql);
		return 1;
	}
	
	public int getIsjfh(String dwid){
		String sql = "";
		sql = " select isnull(isjfh,0) from dw_info where dwid="+dwid;
		return this.getSimpleJdbcTemplate().queryForInt(sql);
	}
	/***
	 * 
	 * @param dwid
	 * @param ryid
	 * @param tt //附件类型
	 * @param xx //附件类型 中 的种类
	 * yy //附件类型中的种类中的序号
	 */
	public List getRyxxFjList(String dwid,String ryid,String tt,String xx,String type){
		String sql = " select * from dw_ryxx_fj where dwid="+dwid+" and ryid="+ryid+" and tt="+tt+" and xx="+xx+" and type="+type+" order by yy";
		List<Map<String, Object>> list = this.getSimpleJdbcTemplate().queryForList(sql);
		for(int i=0;i<list.size();i++){
			String fj = String.valueOf(list.get(i).get("fj"));
			if(fj != null && !fj.equals("") && fj.split(",").length > 1){
				list.get(i).put("fj", fj.split(",")[(fj.split(",").length-1)].trim());
			}
		}
		return list;
	}
	
	
	public List<XtDict> getDwryxxTrList(String dwid,String ryid){
		String sql = " select * from xt_dict where lbid=28 and dictbh in " +
				" ( select dictbh from dw_ryxx_tr_sb where dwid="+dwid+" and ryid="+ryid+")";
		return this.getSimpleJdbcTemplate().query(sql,resultBeanMapper(XtDict.class));
	}
}
