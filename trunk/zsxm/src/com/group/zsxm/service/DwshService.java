package com.group.zsxm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fins.gt.model.FilterInfo;
import com.fins.gt.model.SortInfo;
import com.group.core.common.LimitPage;
import com.group.zsxm.service.common.BaseService;

@Service
public class DwshService extends BaseService{
	public Object getListForZsdw(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos){
		String name = parMap.get("name") == null ? "" :  parMap.get("name");
		String where = parMap.get("where") == null ? "" :  parMap.get("where");
		String ls_sql = "select *  from dw_info_sb_v where 1=1 " ;
		
		if(where != null && !where.equals("")){
			ls_sql += " and upper("+where+") like '%" + name.toUpperCase() + "%'";
		}
		
		String depNo = parMap.get("depNo") == null ? "" :  parMap.get("depNo");
		String cxtype = parMap.get("cxtype") == null ? "" :  parMap.get("cxtype");
		
		if(cxtype.equals("1")){
			ls_sql += " and dwzt = '"+depNo+"' ";
		}else if(cxtype.equals("2")){
			ls_sql += " and nwz = '"+depNo+"' ";
		}else if(cxtype.equals("3")){
			ls_sql += " and dwlx = '"+depNo+"' ";
		}else if(cxtype.equals("5")){
			ls_sql += " and zc = '"+depNo+"' ";
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
				ls_sql += " order by " + sortCond;
			}
		} else {
			ls_sql += " order by dwid ";
		}
		if (limit != null) {
			return this.queryForListWithPage(ls_sql, limit);
		} else {
			return this.simpleJdbcTemplate.queryForList(ls_sql);
		}
	}
	
	public Map preDataSh(String dwid){
		Map m = new HashMap();
		String sql = "";
		sql = "SELECT     DWID, DWDM, DWZT, DWMC, isNUll(LOGINNAME,'') LOGINNAME, isNull(PASSWORD,'') PASSWORD, isNull(FRDB,'') FRDB, isNull(CONVERT(varchar(100), CLRQ, 23),'') AS CLRQ, NWZ, isNull(ZCZB,0) ZCZB, isnull(DWJJ,'') DWJJ, BGDD1," +
				" BGDD2, BGDD3, isNull(BGDD4,'') BGDD4, isNull(JZMJ,0) JZMJ, LXRXM, SEX, ZC, ZW, TEL, PHONE, SSBM, DWLX,isnull(SM,'') SM,isnull(SWGLM,'') SWGLM,isnull(NSRSBH,'') NSRSBH,isnull(HGTDPC,'') HGTDPC," +
				" isNull((SELECT     DICTNAME  FROM          dbo.XT_DICT AS b WHERE      (LBID = 4) AND (a.DWZT = DICTBH)),'') AS DWZT_MC," +
				" isNull((SELECT     DICTNAME FROM          dbo.XT_DICT AS b WHERE      (LBID = 5) AND (a.NWZ = DICTBH)),'') AS NWZ_MC," +
				" isNUll((SELECT     DICTNAME  FROM          dbo.XT_DICT AS b WHERE      (LBID = 6) AND (a.BGDD1 = DICTBH)),'') AS BGDD1_MC," +
				" isNull((SELECT     DICTNAME FROM          dbo.XT_DICT AS b WHERE      (LBID = 7) AND (a.BGDD2 = DICTBH)),'') AS BGDD2_MC," +
				" isNull((SELECT     DICTNAME FROM          dbo.XT_DICT AS b WHERE      (LBID = 8) AND (a.BGDD3 = DICTBH)),'') AS BGDD3_MC," +
				" isNull((SELECT     DICTNAME FROM          dbo.XT_DICT AS b WHERE      (LBID = 9) AND (a.DWLX = DICTBH)),'') AS DWLX_MC," +
				" isNull((SELECT     DICTNAME FROM          dbo.XT_DICT AS b WHERE      (LBID = 14) AND (a.SEX = DICTBH)),'') AS SEX_MC," +
				" isNull((SELECT     DICTNAME  FROM          dbo.XT_DICT AS b  WHERE      (LBID = 15) AND (a.ZC = DICTBH)),'') AS ZC_MC," +
				" isNull((SELECT     XMMC FROM    dbo.XM_INFO WHERE      (XMID = (SELECT     XMID FROM dbo.DW_XM WHERE      (DWID = a.DWID)))),'') AS XM_MC" +
				" FROM         dbo.DW_INFO_SB AS a WHERE     ISNULL(STATUS, 1) = 1 and a.dwid="+dwid;
		m.put("dwxx", this.getSimpleJdbcTemplate().queryForMap(sql));
		
		
		m.put("dwsxs", this.getZsdwMutSelWithViewByDwid(dwid, "1","10"));
		m.put("cyfls", this.getZsdwMutSelWithViewByDwid(dwid, "2","11"));
		m.put("jszys", this.getZsdwMutSelWithViewByDwid(dwid, "3","12"));
		m.put("yjlbs", this.getZsdwMutSelWithViewByDwid(dwid, "4","13"));
		
		
		List<Map<String, String>> ryxx = new ArrayList();
		sql = " select a.ryid,a.dwid,a.xh,a.xm,a.sex,convert(varchar(100),a.birthday,23) birthday," +
			" a.xl,a.xw,a.zc,a.zw,a.hwlxqk,a.yjfx,convert(varchar(100),a.qpsj,23) qpsj,a.zjz,a.sm, " +
			" (select b.dictname from xt_dict b where b.lbid=14 and b.dictbh=a.sex) as sex_mc ," +
			" (select b.dictname from xt_dict b where b.lbid=15 and b.dictbh=a.zc) as zc_mc," +
			" (select b.dictname from xt_dict b where b.lbid=25 and b.dictbh=a.xl) as xl_mc," +
			" (select count(*) from DW_RYXX_FJ x where a.ryid=x.ryid and a.dwid=x.dwid ) fjcount " +
			" from dw_ryxx_sb a where a.dwid='"+dwid+"'  and isNull(a.status,1)=1 ";
		ryxx = this.getJdbcTemplate().queryForList(sql, new Object[]{});
		m.put("ryxx", ryxx);
		
		return m;
	}
	
	public List getZsdwMutSelWithViewByDwid(String dwid,String selid,String lbid){
		List list = new ArrayList();
		String sql = " select a.*," +
				"(select b.dictname from xt_dict b where b.lbid="+lbid+" and b.dictbh=a.seldm) as seldm_mc " +
				" from dw_mutsel_sb a where a.dwid="+dwid+" and a.selid="+selid;
		list = this.getSimpleJdbcTemplate().queryForList(sql);
		return list;
	}
	
	/**
	 * 数据审核通过
	 * @param dwid
	 */
	public void doShtg(String dwid){
		String sql = "";
		
		///////////////////////////
		//删除现有正式信息
		///////////////////////////
		//sql =" delete from dw_info where dwid="+dwid+" and isnull(status,1)=1";
		//this.getSimpleJdbcTemplate().update(sql);
		
		sql =" delete from DW_MUTSEL where dwid="+dwid+" and isnull(status,1)=1";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql =" delete from DW_XM where dwid="+dwid+" and isnull(status,1)=1";
		this.getSimpleJdbcTemplate().update(sql);
		/**
		sql =" delete from DW_RYXX where dwid="+dwid+" and isnull(status,1)=1";
		this.getSimpleJdbcTemplate().update(sql);
		**/
		///////////////////////////////////
		//将上报信息更新为正式信息
		///////////////////////////////
		sql = "update dw_info set DWDM=b.dwdm,DWZT=b.dwzt,DWMC=b.dwmc,FRDB=b.frdb,CLRQ=b.clrq,NWZ=b.nwz,ZCZB=b.zczb," +
				"DWJJ=b.dwjj,BGDD1=b.bgdd1,BGDD2=b.bgdd2,BGDD3=b.bgdd3" +
				",BGDD4=b.bgdd4,JZMJ=b.jzmj,LXRXM=b.lxrxm,SEX=b.sex,ZC=b.zc,ZW=b.zw,TEL=b.tel," +
				"PHONE=b.phone,SSBM=b.ssbm,DWLX=b.dwlx,DWSX=b.dwsx,SM=b.sm,SWGLM=b.swglm" +
				",QYZCBZ=b.qyzcbz,SYZCBZ=b.syzcbz,FFRJGBZ=b.ffrjgbz,DAH=b.dah,SBBZ=2,isjfh=b.isjfh," +
				"sscpc=b.sscpc,tdpc=b.tdpc,gxjsqy=b.gxjsqy," +
				"lxsqy=b.lxsqy,rjqy=b.rjqy,dmqy=b.dmqy," +
				"cmmi=b.cmmi,dwsb=b.dwsb,gjqdxxcy=b.gjqdxxcy," +
				"gjcxkjyq=b.gjcxkjyq,xdfwy=b.xdfwy,fwwb=b.fwwb," +
				"lhrh=b.lhrh "+
				" from dw_info_sb b where b.dwid=dw_info.dwid and b.dwid="+dwid+" and dw_info.dwid="+dwid;
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = "update dw_info_sb set sbbz=2 where dwid="+dwid+" and isnull(status,1)=1";
		this.getSimpleJdbcTemplate().update(sql);
		
		//////////////////////////////
		sql = " delete from dw_gqbl where dwid="+dwid;
		this.getSimpleJdbcTemplate().update(sql);
		sql = " insert into dw_gqbl select * from dw_gqbl_sb where dwid="+dwid;
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = " delete from DW_CDHXXM where dwid="+dwid;
		this.getSimpleJdbcTemplate().update(sql);
		sql = " insert into DW_CDHXXM select * from DW_CDHXXM_SB where dwid="+dwid;
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = " delete from DW_HJQK where dwid="+dwid;
		this.getSimpleJdbcTemplate().update(sql);
		sql = " insert into DW_HJQK select * from DW_HJQK_SB where dwid="+dwid;
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = " delete from dw_LXR where dwid="+dwid;
		this.getSimpleJdbcTemplate().update(sql);
		sql = " insert into dw_LXR select * from DW_LXR_SB where dwid="+dwid;
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = " delete from DW_YFJGRHQYQKB where dwid="+dwid;
		this.getSimpleJdbcTemplate().update(sql);
		sql = " insert into DW_YFJGRHQYQKB select * from DW_YFJGRHQYQKB_SB where dwid="+dwid;
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = " delete from DW_ZSCQ where dwid="+dwid;
		this.getSimpleJdbcTemplate().update(sql);
		sql = " insert into DW_ZSCQ select * from DW_ZSCQ_SB where dwid="+dwid;
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = " delete from DW_SYSYFJSQKB where dwid="+dwid;
		this.getSimpleJdbcTemplate().update(sql);
		sql = " insert into DW_SYSYFJSQKB select * from DW_SYSYFJSQKB_SB where dwid="+dwid;
		this.getSimpleJdbcTemplate().update(sql);
		
		
		/**
		sql = " insert into dw_info(DWID,DWDM,DWZT,DWMC,LOGINNAME,PASSWORD,FRDB,CLRQ,NWZ,ZCZB,DWJJ,BGDD1,BGDD2,BGDD3" +
				",BGDD4,JZMJ,LXRXM,SEX,ZC,ZW,TEL,PHONE,SSBM,DWLX,DWSX,STATUS,SM,HGTDPC,SWGLM,NSRSBH" +
				",QYZCBZ,SYZCBZ,FFRJGBZ,DWPASSWORD,DAH,SBBZ,isjfh) select DWID,DWDM,DWZT,DWMC,LOGINNAME,PASSWORD,FRDB,CLRQ,NWZ,ZCZB,DWJJ,BGDD1,BGDD2,BGDD3" +
				",BGDD4,JZMJ,LXRXM,SEX,ZC,ZW,TEL,PHONE,SSBM,DWLX,DWSX,STATUS,SM,HGTDPC,SWGLM,NSRSBH" +
				",QYZCBZ,SYZCBZ,FFRJGBZ,DWPASSWORD,DAH,2,isjfh from dw_info_sb where dwid="+dwid;
		this.getSimpleJdbcTemplate().update(sql);
		**/
		sql = " insert into DW_MUTSEL select * from DW_MUTSEL_SB where  dwid="+dwid+" and isnull(status,1)=1";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = " insert into dw_xm select * from dw_xm_sb where dwid="+dwid+" and isnull(status,1)=1";
		this.getSimpleJdbcTemplate().update(sql);
		
		/**
		sql = " insert into dw_ryxx select * from dw_ryxx_sb where dwid="+dwid+" and isnull(status,1)=1";
		this.getSimpleJdbcTemplate().update(sql);
		**/
		
		
		/**
		sql = "update  DW_RYXX_FJ set shbz=2 where dwid="+dwid+" ";
		this.getSimpleJdbcTemplate().update(sql);
		**/
	}
	
	/**
	 * 审核退回
	 * @param dwid
	 */
	public void doShth(String dwid,String thyy){
		String sql = "";
		sql = " update DW_INFO_SB set sbbz=3 ,thyy='"+thyy+"' where  dwid="+dwid+" and isnull(status,1)=1";
		this.getSimpleJdbcTemplate().update(sql);
	}
}
