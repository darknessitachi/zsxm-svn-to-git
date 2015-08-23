package com.netwander.explib.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fins.gt.model.FilterInfo;
import com.fins.gt.model.SortInfo;
import com.netwander.explib.exception.BusException;
import com.netwander.core.common.LimitPage;
import com.netwander.explib.entity.Xtuser;
import com.netwander.explib.service.common.BaseService;

@Service
public class ExpwhService  extends BaseService{
	/**
	 * 获取人才信息
	 * @param limit
	 * @param parMap
	 * @param sortInfo
	 * @param filterInfos
	 * @return
	 */
	public Object getListForRcdaByName(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos,Xtuser xtuser) {
		String name = parMap.get("name") == null ? "" :  parMap.get("name");
		String where = parMap.get("where") == null ? "" :  parMap.get("where");
		String zjqbz = parMap.get("zjqbz") == null ? "" :  parMap.get("zjqbz");
		
		String name2 = parMap.get("name2") == null ? "" :  parMap.get("name2");
		
		String sql =  parMap.get("sql") == null ? "" :  parMap.get("sql");
		
		String ls_sql = "";
		if(zjqbz != null && zjqbz.equals("1")){
			ls_sql = "select exp_main_v.*,dbo.get_gz_zj(rcid) as GZ from exp_main_v where 1=1 " ;
		}else{
			ls_sql = "select exp_main_v.*,dbo.get_gz(rcid) as GZ from exp_main_v where 1=1 " ;
		}
		
		if(!xtuser.getRoledm().equals("01")){
			ls_sql += " and rcid in ( select distinct rcid from exp_flmx where fldm in ( select userfl from xt_user_FL where userid="+xtuser.getUserid()+")) ";
		}
		
		if(where != null && !where.equals("") && !name.equals("")){
			String[] ws =  where.split("#");
			if(ws[0].equals("gzmc")){
				ls_sql += " and rcid in ( select distinct rcid from EXP_GZ where gzmc like '"+name+"%')";
			}else{
				
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
				}else{
					ls_sql += " and "+ws[0]+" = '" + name + "'";
				}
			}
			
		}
		
		if(name2 != null && !name2.equals("")){
			ls_sql += " and isclbz="+name2+" ";
		}
		
		if(sql != null && !sql.equals("")){
			ls_sql += " and ("+sql+") ";
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
	public int doDeleteExp(String rcid){
		if(rcid != null && !rcid.trim().equals("")){
			String sql = "";
			sql = " select * from xt_const where cdm='001' order by cid ";
			List<Map<String, String>> l = this.getJdbcTemplate().queryForList(sql);
			
			for(int i=0;i<l.size();i++){
				sql = " delete from "+l.get(i).get("VALUE")+" where rcid in ("+rcid+") ";
				this.getJdbcTemplate().update(sql);
			}
			
		}else{
			throw new BusException("请选择需要删除的数据！");
		}
		return 1;
	}
	
	public int doQropt(String rcid){
		if(rcid != null && !rcid.trim().equals("")){
			String sql = "";
			sql = " update exp_main set  isclbz=1 where rcid in ("+rcid+") ";
			this.getJdbcTemplate().update(sql);
			
		}else{
			throw new BusException("请选择需要确认的数据！");
		}
		return 1;
	}

	/**
	 * 预览个人简历
	 * @param rcid
	 * @return
	 */
	public Map preView(String rcid,String bz){
		Map m = new HashMap();
		if(rcid != null && !rcid.equals("")){
			String sql_q = "SELECT     RCID, RCNAME, OLDNAME, SEX, NATION, JG, isnull(CONVERT(varchar(100), BIRTHDAY, 23),'') AS BIRTHDAY, ZJLB, ZJNO, RCLB, XL, ZC, ZW, XW, EMAIL, PTEL, XXZY," +
					"  SXZY1, SXZY2, SXZY3, CSZY, BYXX, isnull(CONVERT(varchar(100), BYRQ, 23),'') AS BYRQ, WORKUNIT, ZGBM, DWDQ, DWXZ, DWADDR, OFFICETEL, DWCODE, FAX,GRRY,sctzhy," +
					"  JTADDR, JTCODE, JTTEL, ZGBS, XSJT, HDZZ, BGBS, RCBZ, ISNULL(ZGSHBZ, 0) AS ZGSHBZ, FJMC, FJPATH, CSLY, SXLY," +
					" isnull((SELECT     DICTNAME   FROM          dbo.XT_DICT AS b  WHERE      (LBID = 1) AND (a.ZJLB = DICTBH)),'') AS ZJLB_MC," +
					" isnull((SELECT     DICTNAME FROM          dbo.XT_DICT AS b  WHERE      (LBID = 2) AND (a.XL = DICTBH)),'') AS XL_MC," +
					" isnull((SELECT     DICTNAME FROM          dbo.XT_DICT AS b WHERE      (LBID = 3) AND (a.XW = DICTBH)),'') AS XW_MC," +
					" isnull((SELECT     DICTNAME FROM          dbo.XT_DICT AS b WHERE      (LBID = 23) AND (a.ZW = DICTBH)),'') AS ZW_MC," +
					" isnull((SELECT     DICTNAME  FROM          dbo.XT_DICT AS b  WHERE      (LBID = 4) AND (a.SEX = DICTBH)),'') AS SEX_MC," +
					" isnull( (SELECT     DICTNAME  FROM          dbo.XT_DICT AS b WHERE      (LBID = 5) AND (a.ZC = DICTBH)),'') AS ZC_MC," +
					" isnull((SELECT     DICTNAME FROM          dbo.XT_DICT AS b WHERE      (LBID = 12) AND (a.DWXZ = DICTBH)),'') AS DWXZ_MC," +
					"isnull((SELECT     DICTNAME FROM          dbo.XT_DICT AS b WHERE      (LBID = 9) AND (a.ZGBS = DICTBH)),'') AS ZGBS_MC," +
					"isnull( (SELECT     DICTNAME FROM          dbo.XT_DICT AS b  WHERE      (LBID = 20) AND (a.NATION = DICTBH)),'') AS NATION_MC," +
					" isnull((SELECT     DICTNAME FROM          dbo.XT_DICT AS b  WHERE      (LBID = 13) AND (a.XXZY = DICTBH)),'') AS XXZY_MC," +
					" isnull((SELECT     DICTNAME FROM          dbo.XT_DICT AS b WHERE      (LBID = 13) AND (a.SXZY1 = DICTBH)),'') AS SXZY1_MC," +
					" isnull((SELECT     DICTNAME FROM          dbo.XT_DICT AS b WHERE      (LBID = 13) AND (a.SXZY2 = DICTBH)),'') AS SXZY2_MC," +
					" isnull((SELECT     DICTNAME FROM          dbo.XT_DICT AS b WHERE      (LBID = 13) AND (a.SXZY3 = DICTBH)),'') AS SXZY3_MC," +
					" isnull((SELECT     DICTNAME FROM          dbo.XT_DICT AS b WHERE      (LBID = 13) AND (a.CSZY = DICTBH)),'') AS CSZY_MC," +
					"isnull( (SELECT     DICTNAME FROM          dbo.XT_DICT AS b WHERE      (LBID = 10) AND (a.JG = DICTBH)),'') AS JG_MC," +
					"isnull((SELECT     DICTNAME FROM          dbo.XT_DICT AS b  WHERE      (LBID = 10) AND (a.SZDQ = DICTBH)),'') AS SZDQ_MC," +
					" isnull((SELECT     DICTNAME FROM          dbo.XT_DICT AS b WHERE      (LBID = 21) AND (a.ZGBM = DICTBH)),'') AS ZGBM_MC," +
					" isnull((SELECT     DICTNAME   FROM          dbo.XT_DICT AS b  WHERE      (LBID = 22) AND (a.DWDQ = DICTBH)),'') AS DWDQ_MC,  " +
					" SZDQ, EXPJSDF, EXPHBDF, EXPGJBS, XZZW,INFO from EXP_MAIN  a  where a.rcid="+rcid+"";
			//m = this.getSimpleJdbcTemplate().queryForMap(sql_q);
			m.put("rcdaxx1", this.getSimpleJdbcTemplate().queryForMap(sql_q));
			
			//留学国家
			List<Map<String, String>> lxgj = new ArrayList();
			sql_q = " select * from exp_lxgj where rcid="+rcid+"  order by xh,status";
			lxgj = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("rcdalxgj", lxgj);
			
			//专业领域
			List<Map<String, String>> jszc = new ArrayList();
			sql_q = " select a.*,(select dictname from xt_dict b where lbid=16 and a.ly=b.dictbh ) ly_mc," +
			"(select dictname from xt_dict b where lbid=16 and a.zly=b.dictbh ) zly_mc," +
			"(select dictname from xt_dict b where lbid=16 and a.fx=b.dictbh ) fx_mc," +
			"(select dictname from xt_dict b where lbid=18 and a.sxcd=b.dictbh ) sxcd_mc," +
			"substring(ly,len(ly)-2,len(ly)) lyother,substring(zly,len(zly)-2,len(zly)) zlyother,substring(fx,len(fx)-2,len(fx)) fxother" +
			" from exp_jszc a where a.rcid="+rcid+"   order by a.xh ";
			jszc = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("jszc", jszc);
			
			//获取学习简历数据
			List<Map<String, String>> xxjl = new ArrayList();
			sql_q = " select CONVERT(varchar(100), a.brq, 23) brq,CONVERT(varchar(100), a.erq, 23) erq," +
			"a.yx,a.sxzy,a.xl,a.xw,a.byjy,a.xh," +
			" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 2) AND (a.XL = DICTBH)) AS xl_mc," +
			" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 3) AND (a.XW = DICTBH)) AS xw_mc," +
			" (select dictname from xt_dict b where lbid=13 and a.sxzy=b.dictbh ) sxzy_mc from exp_xxjl a " +
			" where a.rcid="+rcid+" order by a.xh ";
			xxjl = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("xxjl", xxjl);
			
			//获取工作简历数据
			List<Map<String, String>> gzjl = new ArrayList();
			sql_q = " select CONVERT(varchar(100), a.brq, 23) brq,CONVERT(varchar(100), a.erq, 23) erq,a.dwbm,a.zw,a.xh,nowbz " +
			" from EXP_GZJL a " +
			" where a.rcid="+rcid+"  order by a.xh ";
			gzjl = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("gzjl", gzjl);
			
			//获取科研项目
			List<Map<String, String>> ktxm = new ArrayList();
			sql_q = " select a.xmmc,a.xmly,a.wcqk, CONVERT(varchar(100), a.wcrq, 23) wcrq,CONVERT(varchar(100), a.wcendrq, 23) wcendrq,xh,zzje,kylws,kyzzs,kycz,kyzls,kyqtcg," +
			" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 19) AND (a.wcqk = DICTBH)) AS wcqk_mc, " +
			" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 24) AND (a.brzy = DICTBH)) AS brzy_mc," +
			" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 26) AND (a.xmly = DICTBH)) AS xmly_mc," +
			" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 28) AND (a.zzxmlb = DICTBH)) AS zzxmlb_mc" +
			"  from EXP_KTXM a " +
			" where rcid="+rcid+" order by xh ";
			ktxm = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("ktxm", ktxm);
			
			//获取荣誉称号及获奖情况数据
			List<Map<String, String>> ryhj = new ArrayList();
			sql_q = " select XH,JXMC,HJDJ,(select dictname from xt_dict " +
			"where lbid=32 and dictbh=EXP_RYHJ.hjdj) HJDJ_MC ,CONVERT(varchar(100), HJRQ, 23) HJRQ,BJBM " +
			" from EXP_RYHJ " +
			" where rcid="+rcid+" order by xh ";
			ryhj = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("ryhj", ryhj);
			
			//获取社会兼、聘职情况数据
			List<Map<String, String>> shjz = new ArrayList();
			sql_q = " select CONVERT(varchar(100), a.brq, 23) brq,CONVERT(varchar(100), a.erq, 23) erq,a.jsdw,a.jssf,a.sm,a.xh " +
			" from EXP_SHJZ a " +
			" where a.rcid="+rcid+" order by a.xh ";
			shjz = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("shjz", shjz);
			
			//获取被聘专家情况数据===》专家类别
			List<Map<String, String>> bpzj = new ArrayList();
			sql_q = " select a.rxnf,a.zjlb,a.sm,a.xh,a.bpzjqk," +
			"(select dictname from xt_dict b where a.bpzjqk=b.dictbh and b.lbid=25) bpzjqk_mc " +
			" from EXP_BPZJ a " +
			" where a.rcid="+rcid+"  order by a.xh ";
			bpzj = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("bpzj", bpzj);
			
			//获取主要论著论文数据
			List<Map<String, String>> zylz = new ArrayList();
			sql_q = " select xh,zzmc,smno,cbmc,CONVERT(varchar(100), cbrq, 23) cbrq ," +
			" (select dictname from xt_dict where lbid=31 and dictbh=EXP_ZYLZ.smno) smno_mc" +
			" from EXP_ZYLZ  " +
			" where rcid="+rcid+" order by xh ";
			zylz = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});	
			m.put("zylz", zylz);
			
			//获取知识产权情况数据
			List<Map<String, String>> zscq = new ArrayList();
			sql_q = " select a.xh,a.zsbh,a.sqr,a.qlr,a.zsmc,a.zsno,a.sm ,CONVERT(varchar(100), a.hdrq, 23) hdrq,iscz,zcdd," +
			" (SELECT  DICTNAME  FROM  XT_DICT AS b  WHERE   (LBID = 17) AND (a.zsno = DICTBH)) AS zsno_mc " +
			" from EXP_ZSCQ  a " +
			" where a.rcid="+rcid+"  order by a.xh ";
			zscq = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
			m.put("zscq", zscq);
			
			
			
		}
		return m;
	}
}
