package com.netwander.explib.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fins.gt.model.FilterInfo;
import com.fins.gt.model.SortInfo;
import com.netwander.core.common.LimitPage;
import com.netwander.explib.entity.Xtuser;
import com.netwander.explib.exception.BusException;
import com.netwander.explib.service.common.BaseService;

@Service
public class ExptbService  extends BaseService{
	
	/**
	 * 获取专家信息
	 * @param limit
	 * @param parMap
	 * @param sortInfo
	 * @param filterInfos
	 * @return
	 */
	public Object getListForRcdaByName(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos,Xtuser xtuser) {
		String name = parMap.get("name") == null ? "" :  parMap.get("name");
		String where = parMap.get("where") == null ? "" :  parMap.get("where");
		String ls_sql = "select * from exp_main_zj_v where 1=1 and isnull(tbbz,0)=3 " ;

		String sql =  parMap.get("sql") == null ? "" :  parMap.get("sql");
		
		String strnl = parMap.get("strnl") == null ?"":parMap.get("strnl");
		String endnl = parMap.get("endnl") == null ?"":parMap.get("endnl");
		
		if(!strnl.trim().equals("") && !strnl.trim().equals("0")){
			ls_sql += " and convert(varchar(100),birthday,23)<='"+this.getBirthday(Integer.parseInt(strnl), 0)+"'" ;
		}
		if(!endnl.trim().equals("") && !endnl.trim().equals("0")){
			ls_sql += " and convert(varchar(100),birthday,23)>='"+this.getBirthday(Integer.parseInt(endnl), 1)+"'" ;
		}
		

		if(!xtuser.getRoledm().equals("01")){
			ls_sql += " and fldm in (  select userfl from xt_user_FL where userid="+xtuser.getUserid()+" ) ";
		}
		
		
		if(where != null && !where.equals("") && !name.equals("")){
			String[] ws =  where.split("#");
			if(ws[1].equals("text")){
				if(ws[0].equals("info")){
					ls_sql += " and rcid in (select rcid from exp_main_zj where info like '%" + name + "%')";
				}else{
					ls_sql += " and "+ws[0]+" like '%" + name + "%'";
				}
				
			}else if(ws[1].equals("select")){
				if(ws[0].split("&").length > 1 ){
					ls_sql += " and rcid in ( select distinct rcid from "+ws[0].split("&")[0]+"_ZJ where "+ws[0].split("&")[1]+" like '"+name+"%')";
				}else{
					ls_sql += " and "+ws[0]+" like '" + name + "%'";
				}
			}else if(ws[1].equals("date")){
				ls_sql += " and convert(varchar(100),"+ws[0]+",23) <= '" + name + "'";
			}
			
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
				sql = " delete from "+l.get(i).get("VALUE")+"_ZJ"+" where convert(varchar(20),rcid)+'#'+convert(varchar(20),itype) in ("+rcid+") ";
				this.getJdbcTemplate().update(sql);
			}
			
		}else{
			throw new BusException("请选择需要删除的数据！");
		}
		return 1;
	}
	
	////////////////////////////////////////////////////////////////
	public Map preExpMain(String rcid,String itype ){
		Map m = new HashMap();
		if(rcid != null && !rcid.equals("")){
			String sql_q = " select cc.*," +
					"isnull(( select dictname from xt_dict_fldm where lbid=14 and fldm='000000001' and dictbh=cc.sxly_fl_1 ),'') sxly_fl_1_mc, " +
					"isnull(( select dictname from xt_dict_fldm where lbid=14 and fldm='000000001' and dictbh=cc.csly_fl_1 ),'') csly_fl_1_mc, " +
					"isnull(( select dictname from xt_dict_fldm where lbid=14 and fldm='000000001' and dictbh=cc.sxly1_fl_1 ),'') sxly1_fl_1_mc, " +
					"isnull(( select dictname from xt_dict_fldm where lbid=14 and fldm='000000001' and dictbh=cc.sxly2_fl_1 ),'') sxly2_fl_1_mc, " +
					"isnull(( select dictname from xt_dict_fldm where lbid=14 and fldm='000000001' and dictbh=cc.sxly3_fl_1 ),'') sxly3_fl_1_mc, " +
					
					"isnull(( select dictname from xt_dict_fldm where lbid=13 and fldm='000000003' and dictbh=cc.sxzy_fl ),'') sxzy_fl_mc, " +
					"isnull(( select dictname from xt_dict_fldm where lbid=13 and fldm='000000003' and dictbh=cc.cszy_fl ),'') cszy_fl_mc, " +
					"isnull(( select dictname from xt_dict_fldm where lbid=13 and fldm='000000003' and dictbh=cc.sxzy1_fl ),'') sxzy1_fl_mc, " +
					"isnull(( select dictname from xt_dict_fldm where lbid=13 and fldm='000000003' and dictbh=cc.sxzy2_fl ),'') sxzy2_fl_mc, " +
					"isnull(( select dictname from xt_dict_fldm where lbid=13 and fldm='000000003' and dictbh=cc.sxzy3_fl ),'') sxzy3_fl_mc, " +
					
					"isnull(( select dictname from xt_dict_fldm where lbid=14 and fldm='000000004' and dictbh=cc.sxly_fl_4 ),'') sxly_fl_4_mc, " +
					"isnull(( select dictname from xt_dict_fldm where lbid=14 and fldm='000000004' and dictbh=cc.csly_fl_4 ),'') csly_fl_4_mc, " +
					"isnull(( select dictname from xt_dict_fldm where lbid=14 and fldm='000000004' and dictbh=cc.sxly1_fl_4 ),'') sxly1_fl_4_mc, " +
					"isnull(( select dictname from xt_dict_fldm where lbid=14 and fldm='000000004' and dictbh=cc.sxly2_fl_4 ),'') sxly2_fl_4_mc, " +
					"isnull(( select dictname from xt_dict_fldm where lbid=14 and fldm='000000004' and dictbh=cc.sxly3_fl_4 ),'') sxly3_fl_4_mc " +
					
					
					" from ( " +
					"select a.fldm,a.rcname,a.oldname,a.xzzw,a.sex,a.nation,a.jg,CONVERT(varchar(100), a.birthday, 23) birthday,a.zjlb,a.zjno," +
					"a.rclb,a.xl,a.zc,a.zw,a.xw,a.email,a.ptel,a.xxzy,a.sxzy1,a.sxzy2,a.sxzy3,a.cszy,a.byxx,CONVERT(varchar(100), a.byrq, 23) byrq," +
					"a.workunit,a.szdq,a.dwdq,a.dwxz,a.dwaddr,a.officetel,a.dwcode,a.fax,a.jtaddr,a.jtcode,a.jttel,a.zgbs,a.xsjt,a.hdzz,bgbs,isnull(btgyy,'') btgyy," +
					"isnull(ybkh,'') ybkh,isnull(jhkh,'') jhkh,isnull(sbkh,'') sbkh,fjpath,fjmc ,xzbz,info,itype,"+
					
					"isnull((select SXLY from EXP_FL_PROTYPE_ZJ where rcid=a.rcid and fldm='000000001'),'') SXLY_FL_1," +
					"isnull((select CSLY from EXP_FL_PROTYPE_ZJ where rcid=a.rcid and fldm='000000001'),'') CSLY_FL_1," +
					"isnull((select SXLY1 from EXP_FL_PROTYPE_ZJ where rcid=a.rcid and fldm='000000001'),'') SXLY1_FL_1," +
					"isnull((select SXLY2 from EXP_FL_PROTYPE_ZJ where rcid=a.rcid and fldm='000000001'),'') SXLY2_FL_1," +
					"isnull((select SXLY3 from EXP_FL_PROTYPE_ZJ where rcid=a.rcid and fldm='000000001'),'') SXLY3_FL_1, " +
					
					"isnull((select SXZY from EXP_FL_PROTYPE_ZJ where rcid=a.rcid and fldm='000000003'),'') SXZY_FL," +
					"isnull((select CSZY from EXP_FL_PROTYPE_ZJ where rcid=a.rcid and fldm='000000003'),'') CSZY_FL," +
					"isnull((select SXZY1 from EXP_FL_PROTYPE_ZJ where rcid=a.rcid and fldm='000000003'),'') SXZY1_FL," +
					"isnull((select SXZY2 from EXP_FL_PROTYPE_ZJ where rcid=a.rcid and fldm='000000003'),'') SXZY2_FL," +
					"isnull((select SXZY3 from EXP_FL_PROTYPE_ZJ where rcid=a.rcid and fldm='000000003'),'') SXZY3_FL, " +
					
					"isnull((select SXLY from EXP_FL_PROTYPE_ZJ where rcid=a.rcid and fldm='000000004'),'') SXLY_FL_4," +
					"isnull((select CSLY from EXP_FL_PROTYPE_ZJ where rcid=a.rcid and fldm='000000004'),'') CSLY_FL_4," +
					"isnull((select SXLY1 from EXP_FL_PROTYPE_ZJ where rcid=a.rcid and fldm='000000004'),'') SXLY1_FL_4," +
					"isnull((select SXLY2 from EXP_FL_PROTYPE_ZJ where rcid=a.rcid and fldm='000000004'),'') SXLY2_FL_4," +
					"isnull((select SXLY3 from EXP_FL_PROTYPE_ZJ where rcid=a.rcid and fldm='000000004'),'') SXLY3_FL_4, " +
					
					"isnull((select SXLYO from EXP_FL_PROTYPE_ZJ where rcid=a.rcid and fldm='000000004'),'') SXLYO_FL_4," +
					"isnull((select CSLYO from EXP_FL_PROTYPE_ZJ where rcid=a.rcid and fldm='000000004'),'') CSLYO_FL_4," +
					"isnull((select SXLY1O from EXP_FL_PROTYPE_ZJ where rcid=a.rcid and fldm='000000004'),'') SXLY1O_FL_4," +
					"isnull((select SXLY2O from EXP_FL_PROTYPE_ZJ where rcid=a.rcid and fldm='000000004'),'') SXLY2O_FL_4," +
					"isnull((select SXLY3O from EXP_FL_PROTYPE_ZJ where rcid=a.rcid and fldm='000000004'),'') SXLY3O_FL_4 " +
					
					"  " +
					"  from exp_Main_ZJ a where a.rcid="+rcid+" and itype='"+itype+"' ) cc ";
			m = super.queryForMap(sql_q);			
		}
		return m;
	}
	
	////////////////////////////////////////////////////////////////
	public Map preExpMainWb(String rcid){
		Map m = new HashMap();
		if(rcid != null && !rcid.equals("")){
			String sql_q = "select a.rcname,a.oldname,a.xzzw,a.sex,a.nation,a.jg,CONVERT(varchar(100), a.birthday, 23) birthday,a.zjlb,a.zjno," +
					"a.rclb,a.xl,a.zc,a.zw,a.xw,a.email,a.ptel,a.xxzy,a.sxzy1,a.sxzy2,a.sxzy3,a.cszy,a.byxx,CONVERT(varchar(100), a.byrq, 23) byrq," +
					"a.workunit,a.szdq,a.dwdq,a.dwxz,a.dwaddr,a.officetel,a.dwcode,a.fax,a.jtaddr,a.jtcode,a.jttel,a.zgbs,a.xsjt,a.hdzz,bgbs,isnull(btgyy,'') btgyy," +
					"isnull(ybkh,'') ybkh,isnull(jhkh,'') jhkh,isnull(sbkh,'') sbkh,fjpath,fjmc ,xzbz,info,"+
					"(select dictname from xt_dict b where lbid=20 and a.nation=b.dictbh ) nationmc,"+
					"(select dictname from xt_dict b where lbid=13 and a.xxzy=b.dictbh ) xxzymc ,"+
					"(select dictname from xt_dict b where lbid=13 and a.sxzy1=b.dictbh ) sxzy1mc,"+   
					"(select dictname from xt_dict b where lbid=13 and a.sxzy2=b.dictbh ) sxzy2mc,"+
					"(select dictname from xt_dict b where lbid=13 and a.sxzy3=b.dictbh ) sxzy3mc,"+  
					"(select dictname from xt_dict b where lbid=13 and a.cszy=b.dictbh ) cszymc," +
					" (SELECT DICTNAME  FROM XT_DICT AS b WHERE (LBID = 10) AND (a.JG = DICTBH)) AS jgmc," +
					" (select count(*) from exp_bpzj where bpzjqk in ('007','008','009') " +
					" and rcid="+rcid+") isdis_rs,a.fid " +
					"  from exp_Main_Wb a where a.rcid="+rcid+"";
			m = super.queryForMap(sql_q);			
		}
		return m;
	}
	/**
	 * 获取留学国家数据
	 */
	public List<Map<String, Object>> getLxgjList(String rcid){
		String sql_q = " ";
		List<Map<String, Object>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select * from exp_lxgj_zj where rcid="+rcid+"  order by xh";
			list = super.queryForList(sql_q,new Object[]{});
		}
		return list;
	}
	
	public List<Map<String, Object>> getLxgjListWb(String rcid){
		String sql_q = " ";
		List<Map<String, Object>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select * from exp_lxgj_Wb where rcid="+rcid+"  order by xh";
			list = super.queryForList(sql_q,new Object[]{});
		}
		return list;
	}
	
	/**
	 * 获取技术专长数据
	 */
	public List<Map<String, Object>> getJszcList(String rcid){
		String sql_q = " ";
		List<Map<String, Object>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select a.*,(select dictname from xt_dict b where lbid=16 and a.ly=b.dictbh ) ly_mc," +
					"(select dictname from xt_dict b where lbid=16 and a.zly=b.dictbh ) zly_mc," +
					"(select dictname from xt_dict b where lbid=16 and a.fx=b.dictbh ) fx_mc," +
					"(select dictname from xt_dict b where lbid=18 and a.sxcd=b.dictbh ) sxcd_mc," +
					"substring(ly,len(ly)-2,len(ly)) lyother,substring(zly,len(zly)-2,len(zly)) zlyother,substring(fx,len(fx)-2,len(fx)) fxother" +
					" from exp_jszc_zj a where a.rcid="+rcid+"   order by a.xh ";
			list = super.queryForList(sql_q, new Object[]{});
		}
		return list;
	}

	public List<Map<String, Object>> getJszcListWb(String rcid){
		String sql_q = " ";
		List<Map<String, Object>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select a.*,(select dictname from xt_dict b where lbid=16 and a.ly=b.dictbh ) ly_mc," +
					"(select dictname from xt_dict b where lbid=16 and a.zly=b.dictbh ) zly_mc," +
					"(select dictname from xt_dict b where lbid=16 and a.fx=b.dictbh ) fx_mc," +
					"(select dictname from xt_dict b where lbid=18 and a.sxcd=b.dictbh ) sxcd_mc," +
					"substring(ly,len(ly)-2,len(ly)) lyother,substring(zly,len(zly)-2,len(zly)) zlyother,substring(fx,len(fx)-2,len(fx)) fxother" +
					" from exp_jszc_wb a where a.rcid="+rcid+"   order by a.xh ";
			list = super.queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	/**
	 * 修改技术专长取数
	 * @param rcid
	 * @param status
	 * @param xh
	 * @return
	 */
	public Map getJszcU(String rcid,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select a.*,(select dictname from xt_dict b where lbid=16 and a.ly=b.dictbh ) ly_mc," +
			"(select dictname from xt_dict b where lbid=16 and a.zly=b.dictbh ) zly_mc," +
			"(select dictname from xt_dict b where lbid=16 and a.fx=b.dictbh ) fx_mc," +
			"(select dictname from xt_dict b where lbid=18 and a.sxcd=b.dictbh ) sxcd_mc," +
			"substring(ly,len(ly)-2,len(ly)) lyother,substring(zly,len(zly)-2,len(zly)) zlyother,substring(fx,len(fx)-2,len(fx)) fxother" +
			" from exp_jszc_zj a where a.rcid="+rcid+"  and a.xh="+xh ;
		map = this.getSimpleJdbcTemplate().queryForMap(sql_q);
		return map;
	}
	
	/**
	 * 获取学习简历数据
	 */
	public List<Map<String, Object>> getXxjlList(String rcid){
		String sql_q = " ";
		List<Map<String, Object>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select CONVERT(varchar(100), a.brq, 23) brq,CONVERT(varchar(100), a.erq, 23) erq," +
					"a.yx,a.sxzy,a.xl,a.xw,a.byjy,a.xh," +
					" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 2) AND (a.XL = DICTBH)) AS xl_mc," +
					" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 3) AND (a.XW = DICTBH)) AS xw_mc," +
					" (select dictname from xt_dict b where lbid=13 and a.sxzy=b.dictbh ) sxzy_mc from exp_xxjl_zj a " +
					" where a.rcid="+rcid+" order by a.xh ";
			list = queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	
	/**
	 * 修改取数
	 * @param rcid
	 * @param status
	 * @param xh
	 * @return
	 */
	public Map getXxjlU(String rcid,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select CONVERT(varchar(100), a.brq, 23) brq,CONVERT(varchar(100), a.erq, 23) erq,a.yx,a.sxzy,a.xl,a.xw,a.byjy," +
			" (select dictname from xt_dict b where lbid=13 and a.sxzy=b.dictbh ) sxzy_mc from exp_xxjl_zj a " +
			" where a.rcid="+rcid+" and a.xh='"+xh+"' ";
		map = queryForMap(sql_q);
		return map;
	}
	
	
	/**
	 * 获取工作简历数据
	 */
	public List<Map<String, Object>> getGzjlList(String rcid){
		String sql_q = " ";
		List<Map<String, Object>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select CONVERT(varchar(100), a.brq, 23) brq,CONVERT(varchar(100), a.erq, 23) erq,a.dwbm,a.zw,a.xh,nowbz " +
					" from EXP_GZJL_ZJ a " +
					" where a.rcid="+rcid+"  order by a.xh ";
			list = queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	/**
	 * 修改取数
	 * @param rcid
	 * @param status
	 * @param xh
	 * @return
	 */
	public Map getGzjlU(String rcid,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select CONVERT(varchar(100), a.brq, 23) brq,CONVERT(varchar(100), a.erq, 23) erq,a.dwbm,a.zw,nowbz " +
			" from EXP_GZJL_ZJ a " +
			" where a.rcid="+rcid+" and a.xh="+xh;
		map = super.queryForMap(sql_q);
		return map;
	}
	

	/**
	 * 获取课题项目数据
	 */
	public List<Map<String, Object>> getKtxmList(String rcid){
		String sql_q = " ";
		List<Map<String, Object>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select a.xmmc,a.xmly,a.wcqk, CONVERT(varchar(100), a.wcrq, 23) wcrq,CONVERT(varchar(100), a.wcendrq, 23) wcendrq,xh,zzje,kylws,kyzzs,kycz,kyzls,kyqtcg," +
					" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 19) AND (a.wcqk = DICTBH)) AS wcqk_mc, " +
					" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 24) AND (a.brzy = DICTBH)) AS brzy_mc," +
					" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 26) AND (a.xmly = DICTBH)) AS xmly_mc," +
					" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 28) AND (a.zzxmlb = DICTBH)) AS zzxmlb_mc" +
					"  from EXP_KTXM_zj a " +
					" where rcid="+rcid+" order by xh ";
			list = queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	/**
	 * 修改取数
	 * @param rcid
	 * @param status
	 * @param xh
	 * @return
	 */
	public Map getKtxmU(String rcid,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select xmmc,xmly,wcqk, CONVERT(varchar(100), wcrq, 23) wcrq,brzy," +
				"CONVERT(varchar(100), wcendrq, 23) wcendrq,zzxmlb,zzje,kylws,kyzzs,kycz,kyzls,kyqtcg " +
			" from EXP_KTXM_zj " +
			" where rcid="+rcid+"  and xh="+xh;
		map = queryForMap(sql_q);
		return map;
	}
	
	/**
	 * 获取荣誉称号及获奖情况数据
	 */
	public List<Map<String, Object>> getRyhjList(String rcid){
		String sql_q = " ";
		List<Map<String, Object>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select XH,JXMC,HJDJ,(select dictname from xt_dict " +
					"where lbid=32 and dictbh=EXP_RYHJ_ZJ.hjdj) HJDJ_MC ,CONVERT(varchar(100), HJRQ, 23) HJRQ,BJBM " +
					" from EXP_RYHJ_ZJ " +
					" where rcid="+rcid+" order by xh ";
			list = queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	/**
	 * 修改取数
	 * @param rcid
	 * @param status
	 * @param xh
	 * @return
	 */
	public Map getRyhjU(String rcid,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select JXMC,HJDJ, CONVERT(varchar(100), HJRQ, 23) HJRQ,BJBM " +
			" from EXP_RYHJ_ZJ " +
			" where rcid="+rcid+" and  xh="+xh;
		map = queryForMap(sql_q);
		return map;
	}
	
	/**
	 * 获取社会兼、聘职情况数据
	 */
	public List<Map<String, Object>> getShjzList(String rcid){
		String sql_q = " ";
		List<Map<String, Object>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select CONVERT(varchar(100), a.brq, 23) brq,CONVERT(varchar(100), a.erq, 23) erq,a.jsdw,a.jssf,a.sm,a.xh " +
					" from EXP_SHJZ_ZJ a " +
					" where a.rcid="+rcid+" order by a.xh ";
			list = queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	/**
	 *社会兼、聘职情况情况取数
	 * @param rcid
	 * @param status
	 * @param xh
	 * @return
	 */
	public Map getShjzU(String rcid,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select CONVERT(varchar(100), a.brq, 23) brq,CONVERT(varchar(100), a.erq, 23) erq,a.jsdw,a.jssf,a.sm " +
			" from EXP_SHJZ_ZJ a " +
			" where a.rcid="+rcid+" and a.xh="+xh;
		map = queryForMap(sql_q);
		return map;
	}
	
	/**
	 * 获取被聘专家情况数据
	 */
	public List<Map<String, Object>> getBpzjList(String rcid){
		String sql_q = " ";
		List<Map<String, Object>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select a.rxnf,a.zjlb,a.sm,a.xh,a.bpzjqk," +
					"(select dictname from xt_dict b where a.bpzjqk=b.dictbh and b.lbid=25) bpzjqk_mc " +
					" from EXP_BPZJ_ZJ a " +
					" where a.rcid="+rcid+"  order by a.xh ";
			list = queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	
	/**
	 *被聘专家情况数据取数
	 * @param rcid
	 * @param status
	 * @param xh
	 * @return
	 */
	public Map getBpzjU(String rcid,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select a.rxnf,a.zjlb,a.sm,a.bpzjqk,(select dictname from xt_dict b where a.bpzjqk=b.dictbh and b.lbid=25) bpzjqk_mc  " +
			" from EXP_BPZJ_ZJ a " +
			" where a.rcid="+rcid+" and a.xh ="+xh;
		map = queryForMap(sql_q);
		return map;
	}
	

	/**
	 * 获取主要论著论文数据
	 */
	public List<Map<String, Object>> getZylzList(String rcid){
		String sql_q = " ";
		List<Map<String, Object>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select xh,zzmc,smno,cbmc,CONVERT(varchar(100), cbrq, 23) cbrq ," +
					" (select dictname from xt_dict where lbid=31 and dictbh=EXP_ZYLZ_ZJ.smno) smno_mc" +
					" from EXP_ZYLZ_ZJ  " +
					" where rcid="+rcid+" order by xh ";
			list = queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	/**
	 * 主要论著论文取数
	 * @param rcid
	 * @param status
	 * @param xh
	 * @return
	 */
	public Map getZylzU(String rcid,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select zzmc,smno,cbmc,CONVERT(varchar(100), cbrq, 23) cbrq " +
			" from EXP_ZYLZ_ZJ  " +
			" where rcid="+rcid+" and xh="+xh;
		map = queryForMap(sql_q);
		return map;
	}

	
	/**
	 * 获取知识产权情况数据
	 */
	public List<Map<String, Object>> getZscqList(String rcid){
		String sql_q = " ";
		List<Map<String, Object>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select a.xh,a.zsmc,a.zsno,a.sm ,CONVERT(varchar(100), a.hdrq, 23) hdrq,iscz,zcdd," +
					" (SELECT  DICTNAME  FROM  XT_DICT AS b  WHERE   (LBID = 17) AND (a.zsno = DICTBH)) AS zsno_mc " +
					" from EXP_ZSCQ_ZJ  a " +
					" where a.rcid="+rcid+"  order by a.xh ";
			list = queryForList(sql_q, new Object[]{});
		}
		return list;
	}

	/**
	 *存知识产权情况取数
	 * @param rcid
	 * @param status
	 * @param xh
	 * @return
	 */
	public Map getZscqU(String rcid,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select xh,zsmc,zsno,sm ,CONVERT(varchar(100), hdrq, 23) hdrq,zcdd,iscz " +
			" from EXP_ZSCQ_ZJ  " +
			" where rcid="+rcid+" and  xh="+xh;
		map = queryForMap(sql_q);
		return map;
	}
	/**
	 * 获取参加学术团及任职情况数据
	 */
	public List<Map<String, String>> getXsrzList(String rcid,String status){
		String sql_q = " ";
		List<Map<String, String>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select xh,dwbm,zw,CONVERT(varchar(100), brq, 23) brq,CONVERT(varchar(100), erq, 23) erq " +
					" from RC_XSRZ_ZJ  " +
					" where rcid="+rcid+" and status=1 order by xh ";
			list = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	/**
	 *参加学术团及任职情况取数
	 * @param rcid
	 * @param status
	 * @param xh
	 * @return
	 */
	public Map getXsrzU(String rcid,Integer status,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select dwbm,zw,CONVERT(varchar(100), brq, 23) brq,CONVERT(varchar(100), erq, 23) erq " +
			" from RC_XSRZ_ZJ  " +
			" where rcid="+rcid+" and status=1 and xh="+xh;
		map = this.getSimpleJdbcTemplate().queryForMap(sql_q);
		return map;
	}
	
	/***
	 * TBBZ: 1: 审核 通过
	 * TBBZ: 2: 审核 退回
	 * @param rcid
	 */
	public void doExpShtg(String rcid){
		String sql = "update exp_main_zj set tbbz = 1 where convert(varchar(20),rcid)+'#'+convert(varchar(20),itype)='"+rcid+"'";
		super.update(true, sql);
		
		//sql = "update xt_user_exp set bz = 2 where convert(varchar(20),rcid)+'#'+convert(varchar(20),itype)='"+rcid+"'";
		//super.update(true, sql);
		Integer sbcount = 0;
		sql = " select count(*) from xt_user_exp where rcid='"+rcid.replaceAll("'", "").split("#")[0]+"'";
		if(this.getSimpleJdbcTemplate().queryForInt(sql) == 0){
			sbcount = 1;
		}else{

			sql = " select sbcount from xt_user_exp where rcid='"+rcid.replaceAll("'", "").split("#")[0]+"'";
			sbcount = queryForInt(sql);
		}
		
		
		sql = " insert into exp_htyy(rcid,sbcount,htyy,bz,intime) " +
				" values("+rcid.replaceAll("'", "").split("#")[0]+","+sbcount+",?,1,getDate())";
		super.update(true, sql, new Object[]{"信息审核通过"});
		
	}
	
	public void doExpShtgWb(String rcid){
		String sql = "update exp_main_wb set tbbz = 1 where rcid="+rcid;
		super.update(true, sql);
		
	}
	/**
	 * 审核 退回
	 * @param rcid
	 */
	public void toExpShth(String rcid,String htyy){
		String sql = "update exp_main_zj set tbbz=2 where rcid="+rcid;
		super.update(true,sql);
		
		sql = " select sbcount from xt_user_exp where rcid="+rcid;
		Integer sbcount = queryForInt(sql);
		
		sql = " insert into exp_htyy(rcid,sbcount,htyy,bz,intime) " +
				" values("+rcid+","+sbcount+",?,2,getDate())";
		super.update(true, sql, new Object[]{htyy});
	}
	
	
	
	/***
	 * 主信息同步
	 * rcidtb: EXP_MAIN_ZJ.RCID
	 * rcid:   EXP_MAIN.rcid
	 * dmkey:  EXP_MAIN_ZJ 中需同步的字段数据
	 * alltbbz: 1: 所有数据同步
	 * m:同步中的住处
	 */
	public void doExpTbMain(String rcidtb,String rcid,String[] dmkey,Map<String, String> m,String alltbbz,String tbtype ){
		String sql  = "";
		String tabletype = "ZJ";
		if(tbtype != null && tbtype.equals("WB")){
			tabletype = "WB";
		}
		if(alltbbz != null && alltbbz.equals("1")){//同步所有
			
			if(rcid != null && !rcid.equals("")){
				sql  = " select a.rcname,a.oldname,a.xzzw,a.sex,a.nation,a.jg,CONVERT(varchar(100), " +
						"a.birthday, 23) birthday,a.zjlb,a.zjno," +
					"a.rclb,a.xl,a.zc,a.zw,a.xw,a.email,a.ptel,a.xxzy,a.sxzy1,a.sxzy2," +
					"a.sxzy3,a.cszy,a.byxx,CONVERT(varchar(100), a.byrq, 23) byrq," +
					"a.workunit,a.szdq,a.dwdq,a.dwxz,a.dwaddr,a.officetel,a.dwcode," +
					"a.fax,a.jtaddr,a.jtcode,a.jttel,a.zgbs,a.xsjt,a.hdzz,bgbs,isnull(btgyy,'') btgyy,expjsdf,exphbdf," +
					"isnull(ybkh,'') ybkh,isnull(jhkh,'') jhkh,isnull(sbkh,'') sbkh ," +
					"xzbz,info  from exp_main_"+tabletype+" a where rcid="+rcidtb+" and itype="+m.get("itype");
				Map< String ,Object> expMap = this.getSimpleJdbcTemplate().queryForMap(sql);
				
				sql = " update exp_main set rcname='"+expMap.get("rcname")+"',sex='"+expMap.get("sex")+"'" +
					",birthday="+transToD(String.valueOf(expMap.get("birthday")))+"" +
					",zjlb='"+expMap.get("zjlb")+"',zjno='"+expMap.get("zjno")+"',rclb='"+expMap.get("rclb")+"',xl='"+expMap.get("xl")+"'" +
					",zc='"+expMap.get("zc")+"',xw='"+expMap.get("xw")+"',email='"+expMap.get("email")+"'" +
					",ptel='"+expMap.get("ptel")+"',workunit='"+expMap.get("workunit")+"'" +
					",szdq='"+expMap.get("szdq")+"'" +
					",officetel='"+expMap.get("officetel")+"'" +
					",jtaddr='"+expMap.get("jtaddr")+"',jtcode='"+expMap.get("jtcode")+"'" +
					",ver=isnull(ver,0)+1 where rcid="+rcid+"";
				super.update(true,sql);
				
				sql = " delete from EXP_FL_PROTYPE where rcid="+rcid +" and fldm='"+m.get("fldm")+"'";
				this.getSimpleJdbcTemplate().update(sql);
				
				sql = " insert into EXP_FL_PROTYPE(RCID, FLDM, SXLY, CSLY, SXLY1, SXLY2, SXLY3, SXZY, CSZY, SXZY1, SXZY2, SXZY3, SXLYO, CSLYO, SXLY1O, SXLY2O, SXLY3O)" +
						" (select "+rcid+", FLDM, SXLY, CSLY, SXLY1, SXLY2, SXLY3, SXZY, CSZY, SXZY1, SXZY2, SXZY3, SXLYO, CSLYO, SXLY1O, SXLY2O, SXLY3O from EXP_FL_PROTYPE_"+tabletype+" where rcid="+rcidtb+" and fldm='"+m.get("fldm")+"' )";
				this.getSimpleJdbcTemplate().update(sql);
				
				/** 暂时 循环封闭
				sql = " select value from xt_const where cdm='001' order by cxh";
				List<Map<String, Object>> list = this.getSimpleJdbcTemplate().queryForList(sql);
				for(int i=0;i<list.size();i++){
					if(String.valueOf(list.get(i).get("value")).toLowerCase().equals("exp_main")){
						sql  = " select a.rcname,a.oldname,a.xzzw,a.sex,a.nation,a.jg,CONVERT(varchar(100), " +
								"a.birthday, 23) birthday,a.zjlb,a.zjno," +
							"a.rclb,a.xl,a.zc,a.zw,a.xw,a.email,a.ptel,a.xxzy,a.sxzy1,a.sxzy2," +
							"a.sxzy3,a.cszy,a.byxx,CONVERT(varchar(100), a.byrq, 23) byrq," +
							"a.workunit,a.szdq,a.dwdq,a.dwxz,a.dwaddr,a.officetel,a.dwcode," +
							"a.fax,a.jtaddr,a.jtcode,a.jttel,a.zgbs,a.xsjt,a.hdzz,bgbs,isnull(btgyy,'') btgyy,expjsdf,exphbdf," +
							"isnull(ybkh,'') ybkh,isnull(jhkh,'') jhkh,isnull(sbkh,'') sbkh ," +
							"xzbz,info  from exp_main_"+tbtype+" a where rcid="+rcidtb+" and itype="+m.get("itype");
						Map< String ,Object> expMap = this.getSimpleJdbcTemplate().queryForMap(sql);
						
						sql = " update exp_main set rcname='"+expMap.get("rcname")+"',sex='"+expMap.get("sex")+"'" +
							",birthday="+transToD(String.valueOf(expMap.get("birthday")))+"" +
							",zjlb='"+expMap.get("zjlb")+"',zjno='"+expMap.get("zjno")+"',rclb='"+expMap.get("rclb")+"',xl='"+expMap.get("xl")+"'" +
							",zc='"+expMap.get("zc")+"',xw='"+expMap.get("xw")+"',email='"+expMap.get("email")+"'" +
							",ptel='"+expMap.get("ptel")+"',workunit='"+expMap.get("workunit")+"'" +
							",szdq='"+expMap.get("szdq")+"'" +
							",officetel='"+expMap.get("officetel")+"'," +
							",jtaddr='"+expMap.get("jtaddr")+"',jtcode='"+expMap.get("jtcode")+"'" +
							",ver=isnull(ver,0)+1 where rcid="+rcid+"";
						super.update(true,sql);
					}else{
						//EXP_MAIN 数据清除
						sql = "delete from "+String.valueOf(list.get(i).get("value"))+" where rcid="+rcid;
						this.getSimpleJdbcTemplate().update(sql);
						
						//插入EXP_MAIN_ZJ表中 数据
						sql = "insert into "+String.valueOf(list.get(i).get("value"))+" select * from "+String.valueOf(list.get(i).get("value"))+"_"+tabletype+" where rcid="+rcidtb;
						update(true,sql);
					}
					
				}
				**/
			}else{
				sql = " select value from xt_const where cdm='001' order by cxh";
				List<Map<String, Object>> list = this.getSimpleJdbcTemplate().queryForList(sql);
				for(int i=0;i<list.size();i++){
					
					sql = "insert into "+String.valueOf(list.get(i).get("value"))+" select * from "+String.valueOf(list.get(i).get("value"))+"_"+tabletype+" where rcid="+rcidtb;
					update(true,sql);
				}
				
				sql = " insert into EXP_FL_PROTYPE(RCID, FLDM, SXLY, CSLY, SXLY1, SXLY2, SXLY3, SXZY, CSZY, SXZY1, SXZY2, SXZY3, SXLYO, CSLYO, SXLY1O, SXLY2O, SXLY3O)" +
						" (select RCID, FLDM, SXLY, CSLY, SXLY1, SXLY2, SXLY3, SXZY, CSZY, SXZY1, SXZY2, SXZY3, SXLYO, CSLYO, SXLY1O, SXLY2O, SXLY3O from EXP_FL_PROTYPE_"+tabletype+" where rcid="+rcidtb+" and fldm='"+m.get("fldm")+"' )";
				this.getSimpleJdbcTemplate().update(sql);
				
				if(tbtype.equals("WB")){
					//专家处理
					String zjno = m.get("zjno").replaceAll(" ", "").trim();
					//用户处理
					String dbid_user = String.valueOf(this.getMaxKey("XT_USER_EXP"));
					String pass = "";
					if(zjno.length() > 6){
						pass = zjno.substring(zjno.length()-6,zjno.length());
					}else{
						pass = zjno;
					}
					
					//未激活，已生效。
					sql = "insert into XT_USER_EXP(DBID, RCNAME, LOGINNAME, PASSWORD, RCID, BZ, JHBZ, SBCOUNT)" +
							" values("+dbid_user+",'"+m.get("rcname")+"','"+zjno+"','"+pass+"',"+rcidtb+",2,1,0)";
					update(true, sql);
				}
			}
		}else{
			if(rcid == null || rcid.equals("")){
				throw new BusException("专家正式库中没有对应的专家数据！");
			}
			if(dmkey != null && dmkey.length > 0){
				String fileds = "";
				String flfileds = "";
				for(int i=0;i<dmkey.length;i++){
					String[] vvs = dmkey[i].split("_");
					if(vvs.length > 1 && vvs[1].toLowerCase().equals("fl")){
						if(vvs[0].equals("sxly")||vvs[0].equals("csly")||vvs[0].equals("sxly1")||vvs[0].equals("sxly2")||vvs[0].equals("sxly3")){
							if(flfileds.equals("")){
								flfileds += ""+vvs[0]+"=b."+vvs[0]+","+vvs[0]+"o=b."+vvs[0]+"o";
							}else{
								flfileds += ","+vvs[0]+"=b."+vvs[0]+","+vvs[0]+"o=b."+vvs[0]+"o";
							}
							
						}else{
							if(flfileds.equals("")){
								flfileds += ""+vvs[0]+"=b."+vvs[0];
							}else{
								flfileds += ","+vvs[0]+"=b."+vvs[0];
							}
							
						}
						
					}else{
						if(fileds.equals("")){
							fileds = dmkey[i]+"='"+m.get(dmkey[i])+"'";
						}else{
							fileds += ","+dmkey[i]+"='"+m.get(dmkey[i])+"'";
						}
					}
				}
				if(fileds != null && !fileds.equals("")){
					sql = " update exp_main set "+fileds+" where rcid="+rcid;
					this.update(true, sql);
				}
				
				
				if(flfileds != null && !flfileds.equals("")){
					sql = "select count(*) from EXP_FL_PROTYPE where fldm='"+m.get("fldm")+"' and rcid="+rcid;
					if(this.getSimpleJdbcTemplate().queryForInt(sql) == 0){
						sql = " insert into EXP_FL_PROTYPE(rcid,fldm) " +
								" values("+rcid+",'"+m.get("fldm")+"')";
						this.getSimpleJdbcTemplate().update(sql);
					}
					sql = " update EXP_FL_PROTYPE  set "+flfileds+" from EXP_FL_PROTYPE a,EXP_FL_PROTYPE_"+tabletype+" b" +
						" where a.rcid="+rcid+" and b.rcid="+rcidtb+" and a.fldm=b.fldm and a.fldm='"+m.get("fldm")+"'";
					this.update(true, sql);
				}
				
			}else{
				throw new BusException("请选择需同步的数据！");
			}
		}
	}
	
	public void doExpTbBatch(String rcidtbs,String expfl,String tbtype ){
		String sql  = "";
		String sfzh = "";
		String tabletype = "ZJ";
		Integer count = 0,rcid = 0;
		if(tbtype != null && tbtype.equals("WB")){
			tabletype = "WB";
		}
		if(rcidtbs != null && !rcidtbs.equals("")){
			
			String[] rcidtb_ = rcidtbs.split(",");
			for(int k=0;k<rcidtb_.length;k++){
				count = 0;
				sfzh = "";
				rcid = 0;
				sql = " select isnull(zjno,'') zjno from exp_main_"+tbtype+" where rcid="+rcidtb_[k];
				sfzh = this.getSimpleJdbcTemplate().queryForObject(sql, String.class);
				if(sfzh != null && !sfzh.equals("") && !sfzh.toLowerCase().equals("null")){
					count = this.getSimpleJdbcTemplate().queryForInt("select count(*) from exp_main where zjno='"+sfzh+"'");
					if(count > 0){
						rcid = this.getSimpleJdbcTemplate().queryForInt("select rcid from exp_main where zjno='"+sfzh+"'");
					}
				}
				
				sql = " select value from xt_const where cdm='001' order by cxh";
				List<Map<String, Object>> list = this.getSimpleJdbcTemplate().queryForList(sql);
				for(int i=0;i<list.size();i++){
					
					if( rcid > 0){
						if(String.valueOf(list.get(i).get("value")).toLowerCase().equals("exp_main")){
							sql  = " select a.rcname,a.oldname,a.xzzw,a.sex,a.nation,a.jg,CONVERT(varchar(100), " +
									"a.birthday, 23) birthday,a.zjlb,a.zjno," +
								"a.rclb,a.xl,a.zc,a.zw,a.xw,a.email,a.ptel,a.xxzy,a.sxzy1,a.sxzy2," +
								"a.sxzy3,a.cszy,a.byxx,CONVERT(varchar(100), a.byrq, 23) byrq," +
								"a.workunit,a.szdq,a.dwdq,a.dwxz,a.dwaddr,a.officetel,a.dwcode," +
								"a.fax,a.jtaddr,a.jtcode,a.jttel,a.zgbs,a.xsjt,a.hdzz,bgbs,isnull(btgyy,'') btgyy,expjsdf,exphbdf," +
								"isnull(ybkh,'') ybkh,isnull(jhkh,'') jhkh,isnull(sbkh,'') sbkh ," +
								"xzbz,info  from exp_main_"+tbtype+" a where rcid="+rcidtb_[k];
							Map< String ,Object> expMap = this.getSimpleJdbcTemplate().queryForMap(sql);
							
							sql = " update exp_main set rcname='"+expMap.get("rcname")+"',oldname='"+expMap.get("oldname")+"',sex='"+expMap.get("sex")+"'" +
								",nation='"+expMap.get("nation")+"',jg='"+expMap.get("jg")+"',birthday="+transToD(String.valueOf(expMap.get("birthday")))+"" +
								",zjlb='"+expMap.get("zjlb")+"',zjno='"+expMap.get("zjno")+"',rclb='"+expMap.get("rclb")+"',xl='"+expMap.get("xl")+"'" +
								",zc='"+expMap.get("zc")+"',zw='"+expMap.get("zw")+"',xw='"+expMap.get("xw")+"',email='"+expMap.get("email")+"'" +
								",ptel='"+expMap.get("ptel")+"', xxzy='"+expMap.get("xxzy")+"',sxzy1='"+expMap.get("sxzy1")+"'" +
								",sxzy2='"+expMap.get("sxzy2")+"',sxzy3='"+expMap.get("sxzy3")+"',cszy='"+expMap.get("cszy")+"'" +
								",byxx='"+expMap.get("byxx")+"',byrq="+transToD(String.valueOf(expMap.get("byrq")))+",workunit='"+expMap.get("workunit")+"'" +
								",szdq='"+expMap.get("szdq")+"',dwdq='"+expMap.get("dwdq")+"',dwxz='"+expMap.get("dwxz")+"'" +
								",dwaddr='"+expMap.get("dwaddr")+"',officetel='"+expMap.get("officetel")+"',dwcode='"+expMap.get("dwcode")+"'" +
								",fax='"+expMap.get("fax")+"',jtaddr='"+expMap.get("jtaddr")+"',jtcode='"+expMap.get("jtcode")+"'" +
								",jttel='"+expMap.get("jttel")+"',xsjt='"+expMap.get("xsjt")+"'" +
								",hdzz='"+expMap.get("hdzz")+"',xzzw='"+expMap.get("xzzw")+"'" +
								", info=?   " +
								",ver=isnull(ver,0)+1 where rcid="+rcid;
							super.update(true,sql,new Object[]{ expMap.get("info") });
						}else{
							//EXP_MAIN 数据清除
							sql = "delete from "+String.valueOf(list.get(i).get("value"))+" where rcid="+rcid;
							this.getSimpleJdbcTemplate().update(sql);
							
							//插入EXP_MAIN_ZJ表中 数据
							sql = "insert into "+String.valueOf(list.get(i).get("value"))+" select * from "+String.valueOf(list.get(i).get("value"))+"_"+tabletype+" where rcid="+rcidtb_[k];
							update(true,sql);
						}
						
						
						sql =  "delete from exp_flmx where rcid="+rcid;
						this.getSimpleJdbcTemplate().update(sql);
						
						sql = "insert into exp_flmx(fldm,rcid,bz) " +
							"values('"+expfl+"',"+rcid+",1)";
						this.getSimpleJdbcTemplate().update(sql);
						
					}else{

						sql = "insert into "+String.valueOf(list.get(i).get("value"))+" select * from "+String.valueOf(list.get(i).get("value"))+"_"+tabletype+" where rcid="+rcidtb_[k];
						update(true,sql);
						

						sql = "insert into exp_flmx(fldm,rcid,bz) " +
							"values('"+expfl+"',"+rcidtb_[k]+",1)";
						this.getSimpleJdbcTemplate().update(sql);
						
						if(tbtype.equals("WB") && i == 0){
							//专家处理
							String zjno = sfzh.replaceAll(" ", "").trim();
							//用户处理
							String dbid_user = String.valueOf(this.getMaxKey("XT_USER_EXP"));
							String pass = "";
							if(zjno.length() > 6){
								pass = zjno.substring(zjno.length()-6,zjno.length());
							}else{
								pass = zjno;
							}
							
							sql = " select isnull(rcname,'') rcname from exp_main_"+tbtype+" where rcid="+rcidtb_[k];
							String exp_name = this.getSimpleJdbcTemplate().queryForObject(sql, String.class);
							
							//未激活，已生效。
							sql = "insert into XT_USER_EXP(DBID, RCNAME, LOGINNAME, PASSWORD, RCID, BZ, JHBZ, SBCOUNT)" +
									" values("+dbid_user+",'"+exp_name+"','"+zjno+"','"+pass+"',"+rcidtb_[k]+",2,1,0)";
							update(true, sql);
						}
					}
					
				}
			}
			sql = "update exp_main_wb set tbbz = 1 where rcid in ("+rcidtbs+")";
			super.update(true, sql);
		}
	}
	
	public void doExpTbMx(String rcidtb,String xhtb,String rcid,String xh,String[] dmkey,Map<String, String> m,String tbtname){
		String sql = "";
		if(rcid == null || rcid.equals("")  ){
			throw new BusException("专家正式库中没有对应的专家数据！");
		}
		if(tbtname == null || tbtname.equals("")  ){
			throw new BusException("没有对应的需同步的表名！");
		}
		
		
		if(dmkey != null && dmkey.length > 0){
			String fileds = "";
			for(int i=0;i<dmkey.length;i++){
				if(fileds.equals("")){
					fileds = dmkey[i]+"='"+m.get(dmkey[i])+"'";
				}else{
					fileds = ","+dmkey[i]+"='"+m.get(dmkey[i])+"'";
				}
			}
			
			sql = " update "+tbtname+" set "+fileds+" where rcid="+rcid+" and xh="+xh;
			this.update(true, sql);
		}else{
			throw new BusException("请选择需同步的数据！");
		}
	}
	
	public void doExpAppend(String rcidtb,String[] dmkey ,String rcid,String tbtname,String tbtype){
		if(rcid == null || rcid.equals("")  ){
			throw new BusException("专家正式库中没有对应的专家数据！");
		}
		if(tbtname == null || tbtname.equals("")  ){
			throw new BusException("没有对应的需同步的表名！");
		}
		String tabletype = "ZJ";
		if(tbtype != null && tbtype.equals("WB")){
			tabletype = "WB";
		}
		String sql = "";
		if(dmkey != null && dmkey.length > 0){
			String fileds = "";
			sql = "select * from "+tbtname+"_"+tabletype+" where  rcid="+rcidtb +" and xh in ("+ArrayToString(dmkey)+")";
			List<Map<String, Object>> list =  this.getSimpleJdbcTemplate().queryForList(sql);
			Map<String, Object> map = list.get(0);
			for(Map.Entry<String, Object> m : map.entrySet()){
				if(fileds.equals("")){
					fileds = m.getKey().toLowerCase();
				}else{
					fileds += ","+m.getKey().toLowerCase();
				}
			}
			sql = " select isnull(max(xh),0) from "+tbtname+" where rcid= "+rcid;
			Integer maxxh = (this.getSimpleJdbcTemplate().queryForInt(sql));
			for(int i=0;i<dmkey.length;i++){
				sql = " insert into "+tbtname+"("+fileds+") " +
						"select "+(fileds.replaceAll("rcid,", rcid+",").replaceAll("xh,", (++maxxh)+","))+" from "+tbtname+"_"+tabletype+" where rcid="+rcidtb+" and xh="+dmkey[i];
				this.update(true, sql);
			}
			
		}else{
			throw new BusException("请选择需同步的数据！");
		}
	}
	
}
