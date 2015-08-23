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
public class RcdaxxwhService extends BaseService{
	
	/**
	 * 获取人才信息
	 * @param limit
	 * @param parMap
	 * @param sortInfo
	 * @param filterInfos
	 * @return
	 */
	public Object getListForRcdaByName(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos) {
		String name = parMap.get("name") == null ? "" :  parMap.get("name");
		String where = parMap.get("where") == null ? "" :  parMap.get("where");
		String ls_sql = "select *,dbo.get_gz(rcid) as GZ from rc_pinfo_v where 1=1 " ;
		if(where != null && !where.equals("")){
			ls_sql += " and "+where+" like '%" + name + "%'";
		}
		String sortCond = getGridSortString(sortInfo);
		String filterCond = getGridFilterString(filterInfos);
		if (filterCond != null) {
			ls_sql += filterCond;
		}
		if (sortCond != null) {
			ls_sql += " order by " + sortCond.split("_")[0];
		} else {
			ls_sql += " order by rcid";
		}
		if (limit != null) {
			return this.queryForListWithPage(ls_sql, limit);
		} else {
			return this.simpleJdbcTemplate.queryForList(ls_sql);
		}
	}
	
	/**
	 * 删除 相应的人才信息
	 * @param rcid
	 * @param status
	 * @return
	 * 将人才标识 改为 RCBS=9, 不删除
	 */
	public int doDeleteRcxx(String rcid,String status){
		if(rcid != null && !rcid.trim().equals("")){
			String sql = "";
			sql = " select * from xt_const where cdm='001' order by cid ";
			List<Map<String, String>> l = this.getJdbcTemplate().queryForList(sql);
			
			for(int i=0;i<l.size();i++){
				sql = " delete from "+l.get(i).get("VALUE")+" where rcid in ("+rcid+") and status != 1";
				this.getJdbcTemplate().update(sql);
			}
			
			for(int i=0;i<l.size();i++){
				sql = " update "+l.get(i).get("VALUE")+" set rcbs=9,status=9 where rcid in ("+rcid+") and status = 1";
				this.getJdbcTemplate().update(sql);
			}
			
			sql = " delete from rc_user where xtrcid in ("+rcid+")";
			this.getJdbcTemplate().update(sql);
			
		}else{
			throw new BusException("请选择需要删除的数据！");
		}
		return 1;
	}
	
	/**
	 * 预览个人简历
	 * @param rcid
	 * @return
	 */
	public Map preView(String rcid){
		Map m = new HashMap();
		if(rcid != null && !rcid.equals("")){
			String sql_q = "SELECT     RCID, RCNAME,  OLDNAME, SEX, isnull(NATION,'') NATION, JG, isnull(CONVERT(varchar(100), BIRTHDAY, 23),'') AS birthday, ZJLB, ZJNO, RCLB, XL, ZC, ZW, XW, EMAIL, PTEL, XXZY, SXZY1,"+ 
                      "SXZY2, SXZY3, CSZY, BYXX, isnull(CONVERT(varchar(100), BYRQ, 23),'') AS BYRQ, WORKUNIT, ZGBM, DWDQ, DWXZ, DWADDR, OFFICETEL, DWCODE, FAX, JTADDR,"+ 
                      "JTCODE, JTTEL, ZGBS, XSJT, HDZZ, BGBS,isNUll(INFO,'') INFO ,isNull(BZ,'') bz,"+
                          "isNull((SELECT     DICTNAME "+
                            "FROM          dbo.XT_DICT AS b "+
                            "WHERE      (LBID = 1) AND (a.ZJLB = DICTBH)),'') AS ZJLB_MC,"+
                          "isNull((SELECT     DICTNAME "+
                          "  FROM          dbo.XT_DICT AS b "+
                          "  WHERE      (LBID = 2) AND (a.XL = DICTBH)),'') AS xl_mc,"+
                          "isNull((SELECT     DICTNAME "+
                          "  FROM          dbo.XT_DICT AS b "+
                          "  WHERE      (LBID = 3) AND (a.XW = DICTBH)),'') AS XW_MC,"+
                          "isNull((SELECT     DICTNAME "+
                          " FROM          dbo.XT_DICT AS b "+
                          " WHERE      (LBID = 4) AND (a.SEX = DICTBH)),'') AS SEX_MC,"+
                          "isNull((SELECT     DICTNAME "+
                          "  FROM          dbo.XT_DICT AS b "+
                          "  WHERE      (LBID = 5) AND (a.ZC = DICTBH)),'') AS ZC_MC,"+
                          " isNUll((SELECT     DICTNAME "+
                          "  FROM          dbo.XT_DICT AS b "+
                          "  WHERE      (LBID = 12) AND (a.DWXZ = DICTBH)),'') AS DWXZ_MC,"+
                          "isNUll((SELECT     DICTNAME "+
                            "FROM          dbo.XT_DICT AS b "+
                            "WHERE      (LBID = 9) AND (a.ZGBS = DICTBH)),'') AS ZGBS_MC,"+
                          "isNUll((SELECT     DICTNAME "+
                            "FROM          dbo.XT_DICT AS b "+
                            "WHERE      (LBID = 20) AND (a.NATION = DICTBH)),'') AS NATION_MC,"+
                          "isNUll((SELECT     LBMC"+
                          "  FROM          dbo.RC_RCLB AS b "+
                          "  WHERE      (a.RCLB = LBDM)),'') AS RCLB_MC,"+
                          "isNull((SELECT     DICTNAME "+
                          "  FROM          dbo.XT_DICT AS b "+
                          "  WHERE      (LBID = 13) AND (a.XXZY = DICTBH)),'') AS XXZY_MC,"+
                          "isNull((SELECT     DICTNAME "+
                          "  FROM          dbo.XT_DICT AS b "+
                          "  WHERE      (LBID = 13) AND (a.SXZY1 = DICTBH)),'') AS SXZY1_MC,"+
                          "isNull((SELECT     DICTNAME "+
                          "  FROM          dbo.XT_DICT AS b "+
                          "  WHERE      (LBID = 13) AND (a.SXZY2 = DICTBH)),'') AS SXZY2_MC,"+
                          "isNull((SELECT     DICTNAME "+
                          "  FROM          dbo.XT_DICT AS b "+
                          "  WHERE      (LBID = 13) AND (a.SXZY3 = DICTBH)),'') AS SXZY3_MC,"+
                          "isNUll((SELECT     DICTNAME "+
                          "  FROM          dbo.XT_DICT AS b "+
                          "  WHERE      (LBID = 13) AND (a.CSZY = DICTBH)),'') AS CSZY_MC , " +
                          "  isNUll((SELECT     DICTNAME  FROM dbo.XT_DICT AS b  WHERE  (LBID = 10) AND (a.JG = DICTBH)),'') AS JG_MC, " +
                          " isNUll((SELECT DICTNAME  FROM  dbo.XT_DICT AS b  WHERE (LBID = 21) AND (a.ZGBM = DICTBH)),'') AS ZGBM_MC, " +
                          " isNUll((SELECT     DICTNAME  FROM dbo.XT_DICT AS b  WHERE  (LBID = 22) AND (a.DWDQ = DICTBH)),'') AS DWDQ_MC "+
                          "  FROM  dbo.RC_PINFO AS a "+
                          " WHERE  (STATUS = 1) AND (ISNULL(RCBS, 0) = 0) and rcid="+rcid;
			//m = this.getSimpleJdbcTemplate().queryForMap(sql_q);
			m.put("rcdaxx1", this.getSimpleJdbcTemplate().queryForMap(sql_q));
			
			//留学国家
			List<Map<String, String>> lxgj = new ArrayList();
			sql_q = " select * from rc_lxgk where rcid="+rcid+" and status=1 order by xh,status";
			lxgj = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("rcdalxgj", lxgj);
			
			//技术专长
			List<Map<String, String>> jszc = new ArrayList();
			sql_q = " select a.*,(select dictname from xt_dict b where lbid=16 and a.ly=b.dictbh ) ly_mc," +
				"(select dictname from xt_dict b where lbid=16 and a.zly=b.dictbh ) zly_mc," +
				"(select dictname from xt_dict b where lbid=16 and a.fx=b.dictbh ) fx_mc," +
				"(select dictname from xt_dict b where lbid=18 and a.sxcd=b.dictbh ) sxcd_mc," +
				"substring(ly,len(ly)-2,len(ly)) lyother,substring(zly,len(zly)-2,len(zly)) zlyother,substring(fx,len(fx)-2,len(fx)) fxother" +
				" from rc_jszc a where a.rcid="+rcid+" and a.status=1  order by a.xh ";
			jszc = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("jszc", jszc);
			
			//获取学习简历数据
			List<Map<String, String>> xxjl = new ArrayList();
			sql_q = " select CONVERT(varchar(100), a.brq, 23) brq,CONVERT(varchar(100), a.erq, 23) erq," +
				"a.yx,a.sxzy,a.xl,a.xw,a.byjy,a.xh," +
				" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 2) AND (a.XL = DICTBH)) AS xl_mc," +
				" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 3) AND (a.XW = DICTBH)) AS xw_mc," +
				" (select dictname from xt_dict b where lbid=13 and a.sxzy=b.dictbh ) sxzy_mc from rc_xxjl a " +
				" where a.rcid="+rcid+" and a.status=1  order by a.xh ";
			xxjl = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("xxjl", xxjl);
			
			//获取工作简历数据
			List<Map<String, String>> gzjl = new ArrayList();
			sql_q = " select CONVERT(varchar(100), a.brq, 23) brq,CONVERT(varchar(100), a.erq, 23) erq,a.dwbm,a.zw,a.xh " +
			" from RC_WORDJL a " +
			" where a.rcid="+rcid+" and a.status=1 order by a.xh ";
			gzjl = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("gzjl", gzjl);
			
			//获取课题项目数据
			List<Map<String, String>> ktxm = new ArrayList();
			sql_q = " select a.xmmc,a.xmly,a.wcqk, CONVERT(varchar(100), a.wcrq, 23) wcrq,xh," +
			" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 19) AND (a.wcqk = DICTBH)) AS wcqk_mc " +
			" from RC_ZCDDYJ a " +
			" where rcid="+rcid+" and status=1 order by xh ";
			ktxm = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("ktxm", ktxm);
			
			//获取荣誉称号及获奖情况数据
			List<Map<String, String>> ryhj = new ArrayList();
			sql_q = " select XH,JXMC,HJDJ, CONVERT(varchar(100), HJRQ, 23) HJRQ " +
			" from RC_HJQK " +
			" where rcid="+rcid+" and status=1 order by xh ";
			ryhj = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("ryhj", ryhj);
			
			//获取产品或技术产业化情况数据
			List<Map<String, String>> cpjs = new ArrayList();
			sql_q = " select cpmc,scjd,scxsdw,xh " +
			" from RC_CPQSCYH " +
			" where rcid="+rcid+" and status=1 order by xh ";
			cpjs = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});	
			m.put("cpjs", cpjs);
			
			//获取社会兼、聘职情况数据
			List<Map<String, String>> shjz = new ArrayList();
			sql_q = " select CONVERT(varchar(100), a.brq, 23) brq,CONVERT(varchar(100), a.erq, 23) erq,a.jsdw,a.jssf,a.sm,a.xh " +
			" from RC_SHJZQK a " +
			" where a.rcid="+rcid+" and a.status=1 order by a.xh ";
			shjz = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("shjz", shjz);
			
			//获取被聘专家情况数据
			List<Map<String, String>> bpzj = new ArrayList();
			sql_q = " select a.rxnf,a.zjlb,a.sm,a.xh " +
			" from RC_BPZJQK a " +
			" where a.rcid="+rcid+" and a.status=1 order by a.xh ";
			bpzj = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("bpzj", bpzj);
			
			//获取主要论著论文数据
			List<Map<String, String>> zylz = new ArrayList();
			sql_q = " select xh,zzmc,smno,cbmc,CONVERT(varchar(100), cbrq, 23) cbrq " +
			" from RC_ZYLZLW  " +
			" where rcid="+rcid+" and status=1 order by xh ";
			zylz = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});	
			m.put("zylz", zylz);
			
			//获取知识产权情况数据
			List<Map<String, String>> zscq = new ArrayList();
			sql_q = " select a.xh,a.zsmc,a.zsno,a.sm ,CONVERT(varchar(100), a.hdrq, 23) hdrq," +
			" (SELECT  DICTNAME  FROM  XT_DICT AS b  WHERE   (LBID = 17) AND (a.zsno = DICTBH)) AS zsno_mc " +
			" from RC_ZSCQ  a " +
			" where a.rcid="+rcid+" and a.status=1 order by a.xh ";
			zscq = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("zscq", zscq);
			
			//获取参加学术团及任职情况数据
			List<Map<String, String>> xxrz = new ArrayList();
			sql_q = " select xh,dwbm,zw,CONVERT(varchar(100), brq, 23) brq,CONVERT(varchar(100), erq, 23) erq " +
			" from RC_XSRZ  " +
			" where rcid="+rcid+" and status=1 order by xh ";
			xxrz = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("xxrz", xxrz);
		}
		return m;
	}
	
	/**
	 * 获取人才信息，用于审核 
	 * @param rcid
	 * @param status
	 * @return
	 */
	public Map preShRcxx(String rcid,String status){
		Map m = new HashMap();
		if(rcid != null && !rcid.equals("")){
			String sql_q = "SELECT     RCID, RCNAME,  OLDNAME, SEX, isnull(NATION,'') NATION, JG, isnull(CONVERT(varchar(100), BIRTHDAY, 23),'') AS birthday, ZJLB, ZJNO, RCLB, XL, ZC, ZW, XW, EMAIL, PTEL, XXZY, SXZY1,"+ 
                      "SXZY2, SXZY3, CSZY, BYXX, isnull(CONVERT(varchar(100), BYRQ, 23),'') AS BYRQ, WORKUNIT, ZGBM, DWDQ, DWXZ, DWADDR, OFFICETEL, DWCODE, FAX, JTADDR,"+ 
                      "JTCODE, JTTEL, ZGBS, XSJT, HDZZ, BGBS,isNUll(INFO,'') INFO ,isNull(BZ,'') bz,rcbz,"+
                          "isNull((SELECT     DICTNAME "+
                            "FROM          dbo.XT_DICT AS b "+
                            "WHERE      (LBID = 1) AND (a.ZJLB = DICTBH)),'') AS ZJLB_MC,"+
                          "isNull((SELECT     DICTNAME "+
                          "  FROM          dbo.XT_DICT AS b "+
                          "  WHERE      (LBID = 2) AND (a.XL = DICTBH)),'') AS xl_mc,"+
                          "isNull((SELECT     DICTNAME "+
                          "  FROM          dbo.XT_DICT AS b "+
                          "  WHERE      (LBID = 3) AND (a.XW = DICTBH)),'') AS XW_MC,"+
                          "isNull((SELECT     DICTNAME "+
                          " FROM          dbo.XT_DICT AS b "+
                          " WHERE      (LBID = 4) AND (a.SEX = DICTBH)),'') AS SEX_MC,"+
                          "isNull((SELECT     DICTNAME "+
                          "  FROM          dbo.XT_DICT AS b "+
                          "  WHERE      (LBID = 5) AND (a.ZC = DICTBH)),'') AS ZC_MC,"+
                          " isNUll((SELECT     DICTNAME "+
                          "  FROM          dbo.XT_DICT AS b "+
                          "  WHERE      (LBID = 12) AND (a.DWXZ = DICTBH)),'') AS DWXZ_MC,"+
                          "isNUll((SELECT     DICTNAME "+
                            "FROM          dbo.XT_DICT AS b "+
                            "WHERE      (LBID = 9) AND (a.ZGBS = DICTBH)),'') AS ZGBS_MC,"+
                          "isNUll((SELECT     DICTNAME "+
                            "FROM          dbo.XT_DICT AS b "+
                            "WHERE      (LBID = 20) AND (a.NATION = DICTBH)),'') AS NATION_MC,"+
                          "isNUll((SELECT     LBMC"+
                          "  FROM          dbo.RC_RCLB AS b "+
                          "  WHERE      (a.RCLB = LBDM)),'') AS RCLB_MC,"+
                          "isNull((SELECT     DICTNAME "+
                          "  FROM          dbo.XT_DICT AS b "+
                          "  WHERE      (LBID = 13) AND (a.XXZY = DICTBH)),'') AS XXZY_MC,"+
                          "isNull((SELECT     DICTNAME "+
                          "  FROM          dbo.XT_DICT AS b "+
                          "  WHERE      (LBID = 13) AND (a.SXZY1 = DICTBH)),'') AS SXZY1_MC,"+
                          "isNull((SELECT     DICTNAME "+
                          "  FROM          dbo.XT_DICT AS b "+
                          "  WHERE      (LBID = 13) AND (a.SXZY2 = DICTBH)),'') AS SXZY2_MC,"+
                          "isNull((SELECT     DICTNAME "+
                          "  FROM          dbo.XT_DICT AS b "+
                          "  WHERE      (LBID = 13) AND (a.SXZY3 = DICTBH)),'') AS SXZY3_MC,"+
                          "isNUll((SELECT     DICTNAME "+
                          "  FROM          dbo.XT_DICT AS b "+
                          "  WHERE      (LBID = 13) AND (a.CSZY = DICTBH)),'') AS CSZY_MC , " +
                          "  isNUll((SELECT     DICTNAME  FROM dbo.XT_DICT AS b  WHERE  (LBID = 10) AND (a.JG = DICTBH)),'') AS JG_MC, " +
                          " isNUll((SELECT DICTNAME  FROM  dbo.XT_DICT AS b  WHERE (LBID = 21) AND (a.ZGBM = DICTBH)),'') AS ZGBM_MC, " +
                          " isNUll((SELECT     DICTNAME  FROM dbo.XT_DICT AS b  WHERE  (LBID = 22) AND (a.DWDQ = DICTBH)),'') AS DWDQ_MC "+
                          "  FROM  dbo.RC_PINFO AS a "+
                          " WHERE  (STATUS = 1) AND (ISNULL(RCBS, 0) = 0) and rcid="+rcid;
			//m = this.getSimpleJdbcTemplate().queryForMap(sql_q);
			m.put("rcdaxx1", this.getSimpleJdbcTemplate().queryForMap(sql_q));
			sql_q = " select count(*) from RC_PINFO where rcid="+rcid +" and (STATUS = 5) AND (ISNULL(RCBS, 0) = 0)";
			int jbxxcount5 = this.getSimpleJdbcTemplate().queryForInt(sql_q);
			m.put("jbxxcount5", jbxxcount5);
			if( jbxxcount5 > 0){

				sql_q = "SELECT     RCID, RCNAME,  OLDNAME, SEX, isnull(NATION,'') NATION, JG, isnull(CONVERT(varchar(100), BIRTHDAY, 23),'') AS birthday, ZJLB, ZJNO, RCLB, XL, ZC, ZW, XW, EMAIL, PTEL, XXZY, SXZY1,"+ 
	            "SXZY2, SXZY3, CSZY, BYXX, isnull(CONVERT(varchar(100), BYRQ, 23),'') AS BYRQ, WORKUNIT, ZGBM, DWDQ, DWXZ, DWADDR, OFFICETEL, DWCODE, FAX, JTADDR,"+ 
	            "JTCODE, JTTEL, ZGBS, XSJT, HDZZ, BGBS,isNUll(INFO,'') INFO ,isNull(BZ,'') bz,rcbz,"+
	                "isNull((SELECT     DICTNAME "+
	                  "FROM          dbo.XT_DICT AS b "+
	                  "WHERE      (LBID = 1) AND (a.ZJLB = DICTBH)),'') AS ZJLB_MC,"+
	                "isNull((SELECT     DICTNAME "+
	                "  FROM          dbo.XT_DICT AS b "+
	                "  WHERE      (LBID = 2) AND (a.XL = DICTBH)),'') AS xl_mc,"+
	                "isNull((SELECT     DICTNAME "+
	                "  FROM          dbo.XT_DICT AS b "+
	                "  WHERE      (LBID = 3) AND (a.XW = DICTBH)),'') AS XW_MC,"+
	                "isNull((SELECT     DICTNAME "+
	                " FROM          dbo.XT_DICT AS b "+
	                " WHERE      (LBID = 4) AND (a.SEX = DICTBH)),'') AS SEX_MC,"+
	                "isNull((SELECT     DICTNAME "+
	                "  FROM          dbo.XT_DICT AS b "+
	                "  WHERE      (LBID = 5) AND (a.ZC = DICTBH)),'') AS ZC_MC,"+
	                " isNUll((SELECT     DICTNAME "+
	                "  FROM          dbo.XT_DICT AS b "+
	                "  WHERE      (LBID = 12) AND (a.DWXZ = DICTBH)),'') AS DWXZ_MC,"+
	                "isNUll((SELECT     DICTNAME "+
	                  "FROM          dbo.XT_DICT AS b "+
	                  "WHERE      (LBID = 9) AND (a.ZGBS = DICTBH)),'') AS ZGBS_MC,"+
	                "isNUll((SELECT     DICTNAME "+
	                  "FROM          dbo.XT_DICT AS b "+
	                  "WHERE      (LBID = 20) AND (a.NATION = DICTBH)),'') AS NATION_MC,"+
	                "isNUll((SELECT     LBMC"+
	                "  FROM          dbo.RC_RCLB AS b "+
	                "  WHERE      (a.RCLB = LBDM)),'') AS RCLB_MC,"+
	                "isNull((SELECT     DICTNAME "+
	                "  FROM          dbo.XT_DICT AS b "+
	                "  WHERE      (LBID = 13) AND (a.XXZY = DICTBH)),'') AS XXZY_MC,"+
	                "isNull((SELECT     DICTNAME "+
	                "  FROM          dbo.XT_DICT AS b "+
	                "  WHERE      (LBID = 13) AND (a.SXZY1 = DICTBH)),'') AS SXZY1_MC,"+
	                "isNull((SELECT     DICTNAME "+
	                "  FROM          dbo.XT_DICT AS b "+
	                "  WHERE      (LBID = 13) AND (a.SXZY2 = DICTBH)),'') AS SXZY2_MC,"+
	                "isNull((SELECT     DICTNAME "+
	                "  FROM          dbo.XT_DICT AS b "+
	                "  WHERE      (LBID = 13) AND (a.SXZY3 = DICTBH)),'') AS SXZY3_MC,"+
	                "isNUll((SELECT     DICTNAME "+
	                "  FROM          dbo.XT_DICT AS b "+
	                "  WHERE      (LBID = 13) AND (a.CSZY = DICTBH)),'') AS CSZY_MC , " +
	                "  isNUll((SELECT     DICTNAME  FROM dbo.XT_DICT AS b  WHERE  (LBID = 10) AND (a.JG = DICTBH)),'') AS JG_MC, " +
	                " isNUll((SELECT DICTNAME  FROM  dbo.XT_DICT AS b  WHERE (LBID = 21) AND (a.ZGBM = DICTBH)),'') AS ZGBM_MC, " +
	                " isNUll((SELECT     DICTNAME  FROM dbo.XT_DICT AS b  WHERE  (LBID = 22) AND (a.DWDQ = DICTBH)),'') AS DWDQ_MC "+
	                "  FROM  dbo.RC_PINFO AS a "+
	                " WHERE  (STATUS = 5) AND (ISNULL(RCBS, 0) = 0) and rcid="+rcid;
				m.put("rcdaxx5", this.getSimpleJdbcTemplate().queryForMap(sql_q));
			}
			
			
			//留学国家
			List<Map<String, String>> lxgj = new ArrayList();
			sql_q = " select * from rc_lxgk where rcid="+rcid+" and status=1 order by xh,status";
			lxgj = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("rcdalxgj1", lxgj);
			
			sql_q = " select * from rc_lxgk where rcid="+rcid+" and status=5 order by xh,status";
			lxgj = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("rcdalxgj5", lxgj);
			
			
			//技术专长
			List<Map<String, String>> jszc = new ArrayList();
			sql_q = " select a.*,(select dictname from xt_dict b where lbid=16 and a.ly=b.dictbh ) ly_mc," +
				"(select dictname from xt_dict b where lbid=16 and a.zly=b.dictbh ) zly_mc," +
				"(select dictname from xt_dict b where lbid=16 and a.fx=b.dictbh ) fx_mc," +
				"(select dictname from xt_dict b where lbid=18 and a.sxcd=b.dictbh ) sxcd_mc," +
				"substring(ly,len(ly)-2,len(ly)) lyother,substring(zly,len(zly)-2,len(zly)) zlyother,substring(fx,len(fx)-2,len(fx)) fxother" +
				" from rc_jszc a where a.rcid="+rcid+" and a.status=1  order by a.xh ";
			jszc = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("jszc1", jszc);
			
			sql_q = " select a.*,(select dictname from xt_dict b where lbid=16 and a.ly=b.dictbh ) ly_mc," +
				"(select dictname from xt_dict b where lbid=16 and a.zly=b.dictbh ) zly_mc," +
				"(select dictname from xt_dict b where lbid=16 and a.fx=b.dictbh ) fx_mc," +
				"(select dictname from xt_dict b where lbid=18 and a.sxcd=b.dictbh ) sxcd_mc," +
				"substring(ly,len(ly)-2,len(ly)) lyother,substring(zly,len(zly)-2,len(zly)) zlyother,substring(fx,len(fx)-2,len(fx)) fxother" +
				" from rc_jszc a where a.rcid="+rcid+" and a.status=5  order by a.xh ";
			jszc = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("jszc5", jszc);
			
			//获取学习简历数据
			List<Map<String, String>> xxjl = new ArrayList();
			sql_q = " select CONVERT(varchar(100), a.brq, 23) brq,CONVERT(varchar(100), a.erq, 23) erq," +
				"a.yx,a.sxzy,a.xl,a.xw,a.byjy,a.xh," +
				" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 2) AND (a.XL = DICTBH)) AS xl_mc," +
				" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 3) AND (a.XW = DICTBH)) AS xw_mc," +
				" (select dictname from xt_dict b where lbid=13 and a.sxzy=b.dictbh ) sxzy_mc from rc_xxjl a " +
				" where a.rcid="+rcid+" and a.status=1  order by a.xh ";
			xxjl = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("xxjl1", xxjl);
			
			sql_q = " select CONVERT(varchar(100), a.brq, 23) brq,CONVERT(varchar(100), a.erq, 23) erq," +
				"a.yx,a.sxzy,a.xl,a.xw,a.byjy,a.xh," +
				" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 2) AND (a.XL = DICTBH)) AS xl_mc," +
				" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 3) AND (a.XW = DICTBH)) AS xw_mc," +
				" (select dictname from xt_dict b where lbid=13 and a.sxzy=b.dictbh ) sxzy_mc from rc_xxjl a " +
				" where a.rcid="+rcid+" and a.status=5  order by a.xh ";
			xxjl = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("xxjl5", xxjl);			
			
			
			//获取工作简历数据
			List<Map<String, String>> gzjl = new ArrayList();
			sql_q = " select CONVERT(varchar(100), a.brq, 23) brq,CONVERT(varchar(100), a.erq, 23) erq,a.dwbm,a.zw,a.xh " +
			" from RC_WORDJL a " +
			" where a.rcid="+rcid+" and a.status=1 order by a.xh ";
			gzjl = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("gzjl1", gzjl);
			
			sql_q = " select CONVERT(varchar(100), a.brq, 23) brq,CONVERT(varchar(100), a.erq, 23) erq,a.dwbm,a.zw,a.xh " +
			" from RC_WORDJL a " +
			" where a.rcid="+rcid+" and a.status=5 order by a.xh ";
			gzjl = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("gzjl5", gzjl);
			
			//获取课题项目数据
			List<Map<String, String>> ktxm = new ArrayList();
			sql_q = " select a.xmmc,a.xmly,a.wcqk, CONVERT(varchar(100), a.wcrq, 23) wcrq,xh," +
			" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 19) AND (a.wcqk = DICTBH)) AS wcqk_mc " +
			" from RC_ZCDDYJ a " +
			" where rcid="+rcid+" and status=1 order by xh ";
			ktxm = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("ktxm1", ktxm);
			
			sql_q = " select a.xmmc,a.xmly,a.wcqk, CONVERT(varchar(100), a.wcrq, 23) wcrq,xh," +
			" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 19) AND (a.wcqk = DICTBH)) AS wcqk_mc " +
			" from RC_ZCDDYJ a " +
			" where rcid="+rcid+" and status=5 order by xh ";
			ktxm = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("ktxm5", ktxm);
			
			
			//获取荣誉称号及获奖情况数据
			List<Map<String, String>> ryhj = new ArrayList();
			sql_q = " select XH,JXMC,HJDJ, CONVERT(varchar(100), HJRQ, 23) HJRQ " +
			" from RC_HJQK " +
			" where rcid="+rcid+" and status=1 order by xh ";
			ryhj = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("ryhj1", ryhj);
			
			sql_q = " select XH,JXMC,HJDJ, CONVERT(varchar(100), HJRQ, 23) HJRQ " +
			" from RC_HJQK " +
			" where rcid="+rcid+" and status=5 order by xh ";
			ryhj = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("ryhj5", ryhj);
			
			
			//获取产品或技术产业化情况数据
			List<Map<String, String>> cpjs = new ArrayList();
			sql_q = " select cpmc,scjd,scxsdw,xh " +
			" from RC_CPQSCYH " +
			" where rcid="+rcid+" and status=1 order by xh ";
			cpjs = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});	
			m.put("cpjs1", cpjs);
			
			sql_q = " select cpmc,scjd,scxsdw,xh " +
			" from RC_CPQSCYH " +
			" where rcid="+rcid+" and status=5 order by xh ";
			cpjs = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});	
			m.put("cpjs5", cpjs);
			
			
			//获取社会兼、聘职情况数据
			List<Map<String, String>> shjz = new ArrayList();
			sql_q = " select CONVERT(varchar(100), a.brq, 23) brq,CONVERT(varchar(100), a.erq, 23) erq,a.jsdw,a.jssf,a.sm,a.xh " +
			" from RC_SHJZQK a " +
			" where a.rcid="+rcid+" and a.status=1 order by a.xh ";
			shjz = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("shjz1", shjz);
			
			sql_q = " select CONVERT(varchar(100), a.brq, 23) brq,CONVERT(varchar(100), a.erq, 23) erq,a.jsdw,a.jssf,a.sm,a.xh " +
			" from RC_SHJZQK a " +
			" where a.rcid="+rcid+" and a.status=5 order by a.xh ";
			shjz = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("shjz5", shjz);
			
			//获取被聘专家情况数据
			List<Map<String, String>> bpzj = new ArrayList();
			sql_q = " select a.rxnf,a.zjlb,a.sm,a.xh " +
			" from RC_BPZJQK a " +
			" where a.rcid="+rcid+" and a.status=1 order by a.xh ";
			bpzj = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("bpzj1", bpzj);
			
			sql_q = " select a.rxnf,a.zjlb,a.sm,a.xh " +
			" from RC_BPZJQK a " +
			" where a.rcid="+rcid+" and a.status=5 order by a.xh ";
			bpzj = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("bpzj5", bpzj);
			
			
			//获取主要论著论文数据
			List<Map<String, String>> zylz = new ArrayList();
			sql_q = " select xh,zzmc,smno,cbmc,CONVERT(varchar(100), cbrq, 23) cbrq " +
			" from RC_ZYLZLW  " +
			" where rcid="+rcid+" and status=1 order by xh ";
			zylz = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});	
			m.put("zylz1", zylz);
			
			sql_q = " select xh,zzmc,smno,cbmc,CONVERT(varchar(100), cbrq, 23) cbrq " +
			" from RC_ZYLZLW  " +
			" where rcid="+rcid+" and status=5 order by xh ";
			zylz = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});	
			m.put("zylz5", zylz);
			
			
			//获取知识产权情况数据
			List<Map<String, String>> zscq = new ArrayList();
			sql_q = " select a.xh,a.zsmc,a.zsno,a.sm ,CONVERT(varchar(100), a.hdrq, 23) hdrq," +
			" (SELECT  DICTNAME  FROM  XT_DICT AS b  WHERE   (LBID = 17) AND (a.zsno = DICTBH)) AS zsno_mc " +
			" from RC_ZSCQ  a " +
			" where a.rcid="+rcid+" and a.status=1 order by a.xh ";
			zscq = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("zscq1", zscq);
			
			sql_q = " select a.xh,a.zsmc,a.zsno,a.sm ,CONVERT(varchar(100), a.hdrq, 23) hdrq," +
			" (SELECT  DICTNAME  FROM  XT_DICT AS b  WHERE   (LBID = 17) AND (a.zsno = DICTBH)) AS zsno_mc " +
			" from RC_ZSCQ  a " +
			" where a.rcid="+rcid+" and a.status=5 order by a.xh ";
			zscq = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("zscq5", zscq);
			
			
			//获取参加学术团及任职情况数据
			List<Map<String, String>> xxrz = new ArrayList();
			sql_q = " select xh,dwbm,zw,CONVERT(varchar(100), brq, 23) brq,CONVERT(varchar(100), erq, 23) erq " +
			" from RC_XSRZ  " +
			" where rcid="+rcid+" and status=1 order by xh ";
			xxrz = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("xxrz1", xxrz);
			
			sql_q = " select xh,dwbm,zw,CONVERT(varchar(100), brq, 23) brq,CONVERT(varchar(100), erq, 23) erq " +
			" from RC_XSRZ  " +
			" where rcid="+rcid+" and status=5 order by xh ";
			xxrz = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("xxrz5", xxrz);
			
		}
		return m;
	}

	/**
	 * 审核人才信息通过
	 * @return
	 */
	public int doShxxtg( String rcid ){
		//删除原有备份数据
		String sql = "";
		sql = " select * from xt_const where cdm='001' order by cid ";
		List<Map<String, String>> l = this.getJdbcTemplate().queryForList(sql);
		//清除原有数据
		for(int i=0;i<l.size();i++){
			sql = " delete from "+l.get(i).get("VALUE")+" where rcid="+rcid+" and status = 7";
			this.getJdbcTemplate().update(sql);
		}
		
		//将原来的数据 status=5  改成 status=7 (改成备份状态)
		for(int i=0;i<l.size();i++){
			sql = " update "+l.get(i).get("VALUE")+"  set status=7  where rcid="+rcid+" and status = 5";
			this.getJdbcTemplate().update(sql);
		}
		
		//将原来 xzbz =2  改成 xzbz=1
		for(int i=0;i<l.size();i++){
			sql = " update "+l.get(i).get("VALUE")+"  set xzbz=1  where rcid="+rcid+" and status = 1 ";
			this.getJdbcTemplate().update(sql);
		}
		//修改人才状态
		sql = " update rc_pinfo  set bgbs='000000000000' ,rcbz=1,btgyy='' where rcid="+rcid+" and status=1 ";
		this.getJdbcTemplate().update(sql);
		//BZ 改成 1 主要用于新注册人才
		sql = " update rc_user set bz=1 where xtrcid="+rcid;
		this.getJdbcTemplate().update(sql);
		
		return 1;
	}
	
	/**
	 * 审核人才信息不通过
	 * @return
	 */
	public int doShxxbtg(String rcid,String btgyy){
		String sql = "";
		sql = " select * from xt_const where cdm='001' order by cid ";
		List<Map<String, String>> l = this.getJdbcTemplate().queryForList(sql);
		
		sql = " select isnull(rcbz,2) from rc_pinfo where rcid="+rcid+" and status=1";
		int rcbz = this.getJdbcTemplate().queryForInt(sql);
		if(rcbz == 1){
			//清除原有数据
			for(int i=0;i<l.size();i++){
				sql = " delete from "+l.get(i).get("VALUE")+" where rcid="+rcid+" and status = 8";
				this.getJdbcTemplate().update(sql);
			}
			
			for(int i=0;i<l.size();i++){
				sql = " select count(*) from "+l.get(i).get("VALUE")+" where rcid="+rcid+" and status=5";
				if(this.getJdbcTemplate().queryForInt(sql) > 0){
					//将修改后的信息改成删除状态
					sql = " update "+l.get(i).get("VALUE")+" set status=8 where rcid="+rcid+" and status=1";
					this.getJdbcTemplate().update(sql);
					
					//将原有备份信息恢复 status=5 改成 status=1
					sql = " update "+l.get(i).get("VALUE")+" set status=1 where rcid="+rcid+" and status=5 ";
					this.getJdbcTemplate().update(sql);
				}
				sql = " update "+l.get(i).get("VALUE")+" set status=8 where rcid="+rcid+" and status=1 and isNull(xzbz,1)=2 ";
				this.getJdbcTemplate().update(sql);
			}
			
			//修改人才状态
			sql = " update rc_pinfo  set bgbs='000000000000' ,rcbz=1,btgyy='"+btgyy+"' where rcid="+rcid+" and status=1 ";
			this.getJdbcTemplate().update(sql);
			
			sql = " update rc_user set bz=1 where xtrcid="+rcid;
			this.getJdbcTemplate().update(sql);
			
		}else{
			//新注册信息 审核 未通过，直接 改成删除状态 status=9
			for(int i=0;i<l.size();i++){
				if(l.get(i).get("VALUE").equals("RC_PINFO")){
					sql = " update "+l.get(i).get("VALUE")+" set btgyy='"+btgyy+"', status=9 where rcid="+rcid+" and status = 1";
				}else{
					sql = " update "+l.get(i).get("VALUE")+" set status=9 where rcid="+rcid+" and status = 1";
				}
				this.getJdbcTemplate().update(sql);
			}
			sql = " delete from rc_user where xtrcid="+rcid;
			this.getJdbcTemplate().update(sql);
		}
		return 1;
	}
}
