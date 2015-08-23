package com.group.zsxm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.group.core.common.TreeBean;
import com.group.zsxm.service.common.BaseService;
@Service
public class ZsxmService extends BaseService{
	
	/**
	 * 获取选择项
	 * @param lbid
	 * @return
	 */
	public List getDictList(Integer lbid){
		List l = new ArrayList();
		l = this.getSimpleJdbcTemplate().
			queryForList("select * from xt_dict where lbid="+lbid+" order by dictbh ");
		return l;
	}
	
	public Map preZsxmU(String xmid){
		Map m = new HashMap();
		if(xmid != null && !xmid.equals("")){
			String sql = "SELECT    NDWMC, XMID,xmbh, CONVERT(varchar(100), RQ, 23) AS RQ, XMLB, XMMC, XMXJ, XMGS, XMJDGS, XMJZQK, YJTR, XMWT, DFLXR, DFLXRDH, DFLXRSJ, XMGZR, BZXX," +
					"(SELECT     DICTNAME FROM   dbo.XT_DICT AS b  WHERE   (LBID = 1) AND (a.XMLB = DICTBH)) AS XMLB_MC," +
					"(SELECT     DICTNAME FROM   dbo.XT_DICT AS b  WHERE      (LBID = 2) AND (a.XMXJ = DICTBH)) AS XMXJ_MC," +
					"(SELECT     DICTNAME FROM   dbo.XT_DICT AS b  WHERE      (LBID = 3) AND (a.XMJDGS = DICTBH)) AS XMJDGS_MC," +
					"(select cnname from xt_user b where a.xmgzr=CONVERT(varchar(20), b.USERID) ) XMGZR_MC " +
					" FROM       XM_INFO AS a where xmid="+xmid+"   and isNull(status,1)=1";
			m = this.getSimpleJdbcTemplate().queryForMap(sql);
		}
		return m;
	}
	
	public Map viewZsxm(String xmid){
		Map m = new HashMap();
		if(xmid != null && !xmid.equals("")){
			String sql = "SELECT    NDWMC, XMID, CONVERT(varchar(100), RQ, 23) AS RQ, XMLB, XMMC, XMXJ, XMGS, XMJDGS, XMJZQK, YJTR, XMWT, DFLXR, DFLXRDH, DFLXRSJ, XMGZR, BZXX," +
				"(SELECT     DICTNAME FROM   dbo.XT_DICT AS b  WHERE   (LBID = 1) AND (a.XMLB = DICTBH)) AS XMLB_MC," +
				"(SELECT     DICTNAME FROM   dbo.XT_DICT AS b  WHERE      (LBID = 2) AND (a.XMXJ = DICTBH)) AS XMXJ_MC," +
				"(SELECT     DICTNAME FROM   dbo.XT_DICT AS b  WHERE      (LBID = 3) AND (a.XMJDGS = DICTBH)) AS XMJDGS_MC," +
				"(select cnname from xt_user b where a.xmgzr=CONVERT(varchar(20), b.USERID) ) XMGZR_MC  " +
			" FROM       XM_INFO AS a where xmid="+xmid+"   and isNull(status,1)=1";
			m = this.getSimpleJdbcTemplate().queryForMap(sql);
		}
		return m;
	}
	
	public String doSaveZsxm(String xmid,Map<String, String> zsxm){
		String sql = "";
		if(xmid != null && !xmid.equals("")){//
			sql = " delete from xm_info where xmid="+xmid+"   and isNull(status,1)=1";
			this.getSimpleJdbcTemplate().update(sql);
			sql = " insert into xm_info(xmid,rq,xmlb,xmmc,xmxj,xmgs,xmjdgs,xmjzqk,yjtr,xmwt,dflxr,dflxrdh,dflxrsj,xmgzr,bzxx,status,xmbh,ndwmc) " +
				" values("+xmid+","+(zsxm.get("rq").equals("")?"null":("'"+zsxm.get("rq")+"'"))+",'"+zsxm.get("xmlb")+"'," +
				"'"+zsxm.get("xmmc")+"','"+zsxm.get("xmxj")+"','"+zsxm.get("xmgs")+"','"+zsxm.get("xmjdgs")+"'," +
				"'"+zsxm.get("xmjzqk")+"','"+(zsxm.get("yjtr").trim().equals("")?"0":zsxm.get("yjtr").trim())+"','"+zsxm.get("xmwt")+"','"+zsxm.get("dflxr")+"','"+zsxm.get("dflxrdh")+"'," +
				"'"+zsxm.get("dflxrsj")+"','"+zsxm.get("xmgzr")+"','"+zsxm.get("bzxx")+"',1,'"+zsxm.get("xmbh")+"','"+zsxm.get("ndwmc")+"')";
			this.getSimpleJdbcTemplate().update(sql);
		}else {
			xmid = String.valueOf(this.getMaxKey("XM_INFO"));
			sql = " insert into xm_info(xmid,rq,xmlb,xmmc,xmxj,xmgs,xmjdgs,xmjzqk,yjtr,xmwt,dflxr,dflxrdh,dflxrsj,xmgzr,bzxx,status,xmbh,ndwmc) " +
				" values("+xmid+","+(zsxm.get("rq").equals("")?"null":("'"+zsxm.get("rq")+"'"))+",'"+zsxm.get("xmlb")+"'," +
				"'"+zsxm.get("xmmc")+"','"+zsxm.get("xmxj")+"','"+zsxm.get("xmgs")+"','"+zsxm.get("xmjdgs")+"'," +
				"'"+zsxm.get("xmjzqk")+"','"+(zsxm.get("yjtr").trim().equals("")?"0":zsxm.get("yjtr").trim())+"','"+zsxm.get("xmwt")+"','"+zsxm.get("dflxr")+"','"+zsxm.get("dflxrdh")+"'," +
				"'"+zsxm.get("dflxrsj")+"','"+zsxm.get("xmgzr")+"','"+zsxm.get("bzxx")+"',1,'"+zsxm.get("xmbh")+"','"+zsxm.get("ndwmc")+"')";
			this.getSimpleJdbcTemplate().update(sql);
		}
		return xmid;
	}
	
	public List<TreeBean> getXmTree(String lbid){
		List<TreeBean> l = new ArrayList();
		String sql_q = "";
		sql_q = " select a.dictbh as dm ,a.dictname as mc ," +
			" '0' as isc," +
			" '0' as pdm " +
			" from xt_dict a where a.lbid="+lbid+" and len(a.dictbh)=3 order by a.dictbh";
		l = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(TreeBean.class));
		return l;
	}
	
	/***
	 * 获取项目编号
	 */
	public Integer getXmbh(){
		String sql = "";
		sql = " select isnull(max(xmbh),0)+1 from xm_info ";
		return this.getSimpleJdbcTemplate().queryForInt(sql);
	}
	
	/****
	 * 进展情况
	 * @param xmid
	 * @return
	 */
	public List getJzqkList(String xmid){
		String sql = " ";
		List<Map<String, String>> list = new ArrayList();
		if(xmid != null && !xmid.equals("")){
			sql = "select XMID, XH, STATUS, convert(varchar(20),RQ,23) RQ, YQJDR, LKFR, STNR, SM, " +
			"(SELECT     cnname FROM          dbo.xt_user AS b  WHERE  xm_jzqk.YQJDR = userid ) AS yqjdr_mc" +
			"  from xm_jzqk where xmid='"+xmid+"'  and isNull(status,1)=1";
			list = this.getJdbcTemplate().queryForList(sql);
		}
		return list;
	}
	
	public int doJzqkI(String xmid,Map<String, String > m){
		String sql = "";
		//获取 XH 最大值
		Integer maxxh = 1;
		sql = "select count(*) from xm_jzqk where xmid='"+xmid+"'  and isNull(status,1)=1";
		if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select max(xh) from xm_jzqk where xmid='"+xmid+"'  and isNull(status,1)=1 ";
			maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
		}
		sql = " insert into xm_jzqk(XMID, XH, STATUS, RQ, YQJDR, LKFR, STNR, SM) " +
			" values('"+xmid+"','"+maxxh+"',1,"+transToD(m.get("rq"))+",'"+m.get("yqjdr")+"'," +
			"'"+m.get("lkfr")+"','"+m.get("stnr")+"','"+m.get("sm")+"')";
		this.getSimpleJdbcTemplate().update(sql);
		
		return 1;
	}
	

	public Map preJzqkU(String xmid,String xh){
		Map map = new HashMap();
		String sql = " select XMID, XH, STATUS, convert(varchar(20),RQ,23) RQ, YQJDR, LKFR, STNR, SM from xm_jzqk where xmid='"+xmid+"' and xh="+xh+" and isNull(status,1)=1";
		map = this.getSimpleJdbcTemplate().queryForMap(sql);
		return map;
	}
	
	public int doJzqkU(String xmid,String xh,Map<String, String > m){
		String sql = " update xm_jzqk set RQ="+transToD(m.get("rq"))+", YQJDR='"+m.get("yqjdr")+"'," +
				" LKFR='"+m.get("lkfr")+"', STNR='"+m.get("stnr")+"', SM='"+m.get("sm")+"'" +
						" where xmid='"+xmid+"' and xh="+xh+"   and isNull(status,1)=1 ";
		this.getSimpleJdbcTemplate().update(sql);
		return 1;
	}
	
	public int doJzqkD(String xmid,String[] xh){
		String sql = " delete from xm_jzqk where xmid="+xmid+" and xh in("+ArrayToString(xh)+")  and isNull(status,1)=1 ";
		this.getSimpleJdbcTemplate().update(sql);
		return 1;
	}
	
	

	/****
	 * 对方联系人
	 * @param xmid
	 * @return
	 */
	public List getLxrList(String xmid){
		String sql = " ";
		List<Map<String, String>> list = new ArrayList();
		if(xmid != null && !xmid.equals("")){
			sql = " select XMID, XH, STATUS, DFLXR, DFTEL, DFPHONE, DFEMAIL, SM,DYLXRBZ " +
			"  from xm_dflxr where xmid='"+xmid+"'  and isNull(status,1)=1";
			list = this.getJdbcTemplate().queryForList(sql);
		}
		return list;
	}
	
	public int doLxrI(String xmid,Map<String, String > m){
		String sql = "";
		//获取 XH 最大值
		Integer maxxh = 1;
		sql = "select count(*) from xm_dflxr where xmid='"+xmid+"'  and isNull(status,1)=1";
		if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select max(xh) from xm_dflxr where xmid='"+xmid+"'  and isNull(status,1)=1 ";
			maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
		}
		sql = " insert into xm_dflxr(XMID, XH, STATUS, DFLXR, DFTEL, DFPHONE, DFEMAIL, SM) " +
			" values('"+xmid+"','"+maxxh+"',1,'"+m.get("dflxr")+"','"+m.get("dftel")+"'," +
			"'"+m.get("dfphone")+"','"+m.get("dfemail")+"','"+m.get("sm")+"')";
		this.getSimpleJdbcTemplate().update(sql);
		
		return 1;
	}
	

	public Map preLxrU(String xmid,String xh){
		Map map = new HashMap();
		String sql = " select * from xm_dflxr where xmid='"+xmid+"' and xh="+xh+" and isNull(status,1)=1";
		map = this.getSimpleJdbcTemplate().queryForMap(sql);
		return map;
	}
	
	public int doLxrU(String xmid,String xh,Map<String, String > m){
		String sql = " update xm_dflxr set DFLXR='"+m.get("dflxr")+"', DFTEL='"+m.get("dftel")+"', " +
				"DFPHONE='"+m.get("dfphone")+"', DFEMAIL='"+m.get("dfemail")+"', SM='"+m.get("sm")+"'" +
						" where xmid='"+xmid+"' and xh="+xh+"   and isNull(status,1)=1 ";
		this.getSimpleJdbcTemplate().update(sql);
		return 1;
	}
	
	public int doLxrD(String xmid,String[] xh){
		String sql = " delete from xm_dflxr where xmid="+xmid+" and xh in("+ArrayToString(xh)+")  and isNull(status,1)=1 ";
		this.getSimpleJdbcTemplate().update(sql);
		return 1;
	}
	
	public int doSetLxrForDy(String xmid,String xh){
		String sql = " update XM_DFLXR set DYLXRBZ=0 where xmid="+xmid;
		this.getSimpleJdbcTemplate().update(sql);
		sql = " update XM_DFLXR set DYLXRBZ=1 where xmid="+xmid+" and xh="+xh;
		this.getSimpleJdbcTemplate().update(sql); 
		return 1;
	}
	
}
