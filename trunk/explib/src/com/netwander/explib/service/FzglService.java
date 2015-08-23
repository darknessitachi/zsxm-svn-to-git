package com.netwander.explib.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fins.gt.model.FilterInfo;
import com.fins.gt.model.SortInfo;
import com.netwander.core.common.LimitPage;
import com.netwander.explib.entity.Xtuser;
import com.netwander.explib.service.common.BaseService;
import com.sun.java_cup.internal.internal_error;

@Service
public class FzglService extends BaseService{
	/**
	 * 获取专家信息
	 * @param limit
	 * @param parMap
	 * @param sortInfo
	 * @param filterInfos
	 * @return
	 */
	public Object getListForRcdaByName(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos,Xtuser xtuser,Integer itype) {
		String name = parMap.get("name") == null ? "" :  parMap.get("name");
		String where = parMap.get("where") == null ? "" :  parMap.get("where");
		String ls_sql = "select * from exp_main_wb_v where 1=1 and isnull(tbbz,0)=3  " ;

		String xl = parMap.get("xl") == null ?"":parMap.get("xl");
		String xw = parMap.get("xw") == null ?"":parMap.get("xw");
		String zc = parMap.get("zc") == null ?"":parMap.get("zc");
		String zw = parMap.get("zw") == null ?"":parMap.get("zw");
		String szdq = parMap.get("szdq") == null ?"":parMap.get("szdq");
		String dwxz = parMap.get("dwxz") == null ?"":parMap.get("dwxz");
		
		String strnl = parMap.get("strnl") == null ?"":parMap.get("strnl");
		String endnl = parMap.get("endnl") == null ?"":parMap.get("endnl");
		
		
		if(!xl.equals("")){
			ls_sql += " and xl='"+xl+"'";
		}
		if(!xw.equals("")){
			ls_sql += " and xw='"+xw+"'";
		}
		if(!zc.equals("")){
			ls_sql += " and zc='"+zc+"'" ;
		}
		if(!zw.equals("")){
			ls_sql += " and zw='"+zw+"'";
		}
		if(!szdq.equals("")){
			ls_sql += " and szdq='"+szdq+"'" ;
		}
		if(!dwxz.equals("")){
			ls_sql += " and dwxz='"+dwxz+"'" ;
		}
		
		if(!strnl.trim().equals("") && !strnl.trim().equals("0")){
			ls_sql += " and convert(varchar(100),birthday,23)<='"+this.getBirthday(Integer.parseInt(strnl), 0)+"'" ;
		}
		if(!endnl.trim().equals("") && !endnl.trim().equals("0")){
			ls_sql += " and convert(varchar(100),birthday,23)>='"+this.getBirthday(Integer.parseInt(endnl), 1)+"'" ;
		}
		
		if(where != null && !where.equals("") && !name.equals("")){
			String[] ws =  where.split("#");
			if(ws[1].equals("text")){
				if(ws[0].equals("info")){
					ls_sql += " and rcid in (select rcid from exp_main_wb where info like '%" + name + "%')";
				}else{
					ls_sql += " and "+ws[0]+" like '%" + name + "%'";
				}
				
			}else if(ws[1].equals("select")){
				if(ws[0].split("&").length > 1 ){
					ls_sql += " and rcid in ( select distinct rcid from "+ws[0].split("&")[0]+"_WB where "+ws[0].split("&")[1]+" like '"+name+"%')";
				}else{
					ls_sql += " and "+ws[0]+" like '" + name + "%'";
				}
			}else if(ws[1].equals("date")){
				ls_sql += " and convert(varchar(100),"+ws[0]+",23) <= '" + name + "'";
			}
			
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
	
	

	public Object getListForCzrc(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos,Xtuser xtuser) {
		String name = parMap.get("name") == null ? "" :  parMap.get("name");
		String where = parMap.get("where") == null ? "" :  parMap.get("where");
		String where1 = parMap.get("where1") == null ? "" :  parMap.get("where1");
		String ls_sql = "select * from czrc.dbo.rc_pinfo_v where  1=1 " ;

		String xl = parMap.get("xl") == null ?"":parMap.get("xl");
		String xw = parMap.get("xw") == null ?"":parMap.get("xw");
		String zc = parMap.get("zc") == null ?"":parMap.get("zc");
		String zw = parMap.get("zw") == null ?"":parMap.get("zw");
		String zgbm = parMap.get("zgbm") == null ?"":parMap.get("zgbm");
		String dwxz = parMap.get("dwxz") == null ?"":parMap.get("dwxz");
		
		String strnl = parMap.get("strnl") == null ?"":parMap.get("strnl");
		String endnl = parMap.get("endnl") == null ?"":parMap.get("endnl");
		String zzxmlb = parMap.get("zzxmlb") == null ?"":parMap.get("zzxmlb");
		String bpzjqk = parMap.get("bpzjqk") == null ?"":parMap.get("bpzjqk");
		
		if(!xl.equals("")){
			ls_sql += " and xl='"+xl+"'";
		}
		if(!xw.equals("")){
			ls_sql += " and xw='"+xw+"'";
		}
		if(!zc.equals("")){
			ls_sql += " and zc='"+zc+"'" ;
		}
		if(!zw.equals("")){
			ls_sql += " and zw='"+zw+"'";
		}
		if(!zgbm.equals("")){
			ls_sql += " and zgbm='"+zgbm+"'" ;
		}
		if(!dwxz.equals("")){
			ls_sql += " and dwxz='"+dwxz+"'" ;
		}
		
		if(!strnl.trim().equals("") && !strnl.trim().equals("0")){
			ls_sql += " and convert(varchar(100),birthday,23)<='"+this.getBirthday(Integer.parseInt(strnl), 0)+"'" ;
		}
		if(!endnl.trim().equals("") && !endnl.trim().equals("0")){
			ls_sql += " and convert(varchar(100),birthday,23)>='"+this.getBirthday(Integer.parseInt(endnl), 1)+"'" ;
		}
		
		if(!zzxmlb.equals("")){
			ls_sql += " and rcid in ( select distinct rcid from RC_ZCDDYJ where zzxmlb ='"+zzxmlb+"')" ;
		}
		if(!bpzjqk.equals("")){
			ls_sql += " and rcid in ( select distinct rcid from RC_BPZJQK where bpzjqk in ("+bpzjqk+") ) " ;
		}
		
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
	
	
	public Object getListForZjps(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos){
		String name = parMap.get("name") == null ? "" :  parMap.get("name");
		String where = parMap.get("where") == null ? "" :  parMap.get("where");
		String expertid = parMap.get("expertid") == null ? "" :  parMap.get("expertid");
		String name2 = parMap.get("name2") == null ? "" :  parMap.get("name2");

		String ls_sql = " select EXPERTID, EXPERTMC, IDTYPE, IDCARD, SEX, BIRTHDAY," +
				" EDUCATION,DEGREE, TECHNICAL, TEL, PHONE, EMAIL, POST, ADRESS, " +
				"CODE,SZDW, CODETJ, TJDW, CODEXX, XXDW, STATE, SPECIALTYSX, " +
				"COUNTY,SPECIALTYSC, SPECIALTYSB, SPECIALTYSA, SPECIALTYCS," +
				" LINK1, LINK2, LINK3, CZR, CZRQ, PAYTYPE, BANK, PAYPOST," +
				" PAYADRES, BANKCARD, LY, ZTYY, OTHERLINK,EDUCATION_MC, " +
				"DEGREE_MC, TECHNICAL_MC, SPECIALTYSA_MC, SPECIALTYSB_MC," +
				" SPECIALTYCS_MC from zjps.dbo.main_expert_v where 1=1 " ;
		
		
		if(where != null && !where.equals("")){
			if(where.equals("expertmc") || where.equals("szdw")|| where.equals("summary") || where.equals("idcard")|| where.equals("phone")){
				ls_sql += " and "+where+" like '%" + name.toUpperCase().trim() + "%'";
			}else if(where.equals("birthday_x")){
				if(name != null && !name.equals("")){
					ls_sql += " and birthday <= '" + name.trim() + "'";
				}
			}else{
				ls_sql += " and "+where+" = '" + name.trim() + "'";
			}
		}
		
		if(name2 != null && !name2.equals("")){
			ls_sql += " and isnull(state,0)="+name2;
		}
		
		if(expertid != null && !expertid.equals("")){
			ls_sql += " and expertid="+expertid;
		}
		
		String depNo = parMap.get("depNo") == null ? "" :  parMap.get("depNo");
		String cxtype = parMap.get("cxtype") == null ? "" :  parMap.get("cxtype");
		
		String sortCond = getGridSortString(sortInfo);
		String filterCond = getGridFilterString(filterInfos);
		if (filterCond != null) {
			ls_sql += filterCond;
		}
		if (sortCond != null) {
			ls_sql += " order by " + sortCond.split("_")[0];
		} else {
			ls_sql += " order by EXPERTMC  ";
		}
		if (limit != null) {
			return this.queryForListWithPage(ls_sql, limit);
		} else {
			return this.simpleJdbcTemplate.queryForList(ls_sql);
		}
	}
	
	
	public void doYrZjpsData(String keys){
		System.out.println("Start Base Data....");
		doInitBaseData();
		
		System.out.println("End Base Data...");
		/**
		 * 获取正常数据
		 */
		String sql = "update xt_maxid set maxid=(select max(EXPERTID)+1 from zjps.dbo.main_expert ) where tablename='EXP_MAIN' ";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = "insert into exp_main(STATUS,RCID, RCNAME,ZJLB, ZJNO, INFO,SEX,  BIRTHDAY," +
				" XL, XW,  ZC,OFFICETEL,PTEL, EMAIL, JTCODE,DWADDR,SXLY, CSLY,SZDQ,expertid_) " +
				"  select 1,EXPERTID, EXPERTMC, IDTYPE, IDCARD, SUMMARY, SEX, " +
				"BIRTHDAY, EDUCATION, DEGREE, TECHNICAL, TEL, PHONE, EMAIL, POST, ADRESS," +
				" SPECIALTYSX, SPECIALTYCS, COUNTY,newid() from zjps.dbo.main_expert where STATE=1 ";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = " update exp_main set zjno='NO'+convert(varchar(30),rcid) where isnull(zjno,'')=''" ;
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = "insert into exp_jszc(rcid,xh,status,ly,sxcd)" +
				" select expertid,1,1,specialtySa,'熟悉' from zjps.dbo.main_expert where state=1 and isnull(specialtySa,'') !='' ";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = "insert into exp_jszc(rcid,xh,status,ly,sxcd)" +
			" select expertid,2,1,specialtySb,'熟悉' from zjps.dbo.main_expert where state=1 and isnull(specialtySb,'') !='' ";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = "insert into exp_jszc(rcid,xh,status,ly,sxcd)" +
			" select expertid,3,1,specialtySc,'熟悉' from zjps.dbo.main_expert where state=1 and isnull(specialtySc,'') !='' ";
		this.getSimpleJdbcTemplate().update(sql);
		
		
		//////////////////////////////////////////////
		
		sql = " insert into exp_main_wb(STATUS,RCID, RCNAME,ZJLB, ZJNO,INFO, SEX,  BIRTHDAY," +
				" XL, XW,  ZC,OFFICETEL,PTEL, EMAIL, JTCODE,DWADDR,SXLY, CSLY,SZDQ,tbbz,expertid_) " +
				"  select 1,EXPERTID, EXPERTMC, IDTYPE, IDCARD, SUMMARY, SEX, " +
				"BIRTHDAY, EDUCATION, DEGREE, TECHNICAL, TEL, PHONE, EMAIL, POST, ADRESS," +
				" SPECIALTYSX, SPECIALTYCS, COUNTY,3,newid() from zjps.dbo.main_expert where STATE != 1 and state!=7";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = " update exp_main_wb set zjno='NO'+convert(varchar(30),rcid) where isnull(zjno,'')=''" ;
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = "insert into exp_jszc_wb(rcid,xh,status,ly,sxcd,itype)" +
				" select expertid,1,1,specialtySa,'熟悉',99 from zjps.dbo.main_expert where STATE != 1 and state!=7 and isnull(specialtySa,'') !='' ";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = "insert into exp_jszc_wb(rcid,xh,status,ly,sxcd,itype)" +
			" select expertid,2,1,specialtySb,'熟悉',99 from zjps.dbo.main_expert where STATE != 1 and state!=7 and isnull(specialtySb,'') !='' ";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = "insert into exp_jszc_wb(rcid,xh,status,ly,sxcd,itype)" +
			" select expertid,3,1,specialtySc,'熟悉',99 from zjps.dbo.main_expert where STATE != 1 and state!=7 and isnull(specialtySc,'') !='' ";
		this.getSimpleJdbcTemplate().update(sql);
		
		
		//////////////////////////////////////////////////////
		sql = " update exp_main set ZJLB=b.baseremote from exp_main a ,zjps.dbo.base_info_config_mx b where" +
				" a.ZJLB=b.baselocal and b.basetype=1 ";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = " update exp_main set sex=b.baseremote from exp_main a ,zjps.dbo.base_info_config_mx b where" +
				" a.sex=b.baselocal and b.basetype=2 ";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = " update exp_main set XL=b.baseremote from exp_main a ,zjps.dbo.base_info_config_mx b where" +
				" a.XL=b.baselocal and b.basetype=3 ";
		this.getSimpleJdbcTemplate().update(sql);

		sql = " update exp_main set xw=b.baseremote from exp_main a ,zjps.dbo.base_info_config_mx b where" +
				" a.xw=b.baselocal and b.basetype=4 ";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = " update exp_main set zc=b.baseremote from exp_main a ,zjps.dbo.base_info_config_mx b where" +
				" a.zc=b.baselocal and b.basetype=5 ";
		this.getSimpleJdbcTemplate().update(sql);	
		
		sql = " update exp_main set SXLY=b.baseremote from exp_main a ,zjps.dbo.base_info_config_mx b where" +
		" a.SXLY=b.baselocal and b.basetype=6 ";
		this.getSimpleJdbcTemplate().update(sql);		

		sql = " update exp_main set CSLY=b.baseremote from exp_main a ,zjps.dbo.base_info_config_mx b where" +
		" a.CSLY=b.baselocal and b.basetype=6 ";
		this.getSimpleJdbcTemplate().update(sql);	
		
		sql = " update exp_main set SZDQ=b.baseremote from exp_main a ,zjps.dbo.base_info_config_mx b where" +
		" a.SZDQ=b.baselocal and b.basetype=7 ";
		this.getSimpleJdbcTemplate().update(sql);	
		
		sql = " update exp_jszc set ly=b.baseremote from exp_jszc a ,zjps.dbo.base_info_config_mx b where" +
		" a.ly=b.baselocal and b.basetype=6 ";
		this.getSimpleJdbcTemplate().update(sql);	
		
		///////////////WB////////////////
		
		
		sql = " update exp_main_wb set ZJLB=b.baseremote from exp_main_wb a ,zjps.dbo.base_info_config_mx b where" +
				" a.ZJLB=b.baselocal and b.basetype=1 ";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = " update exp_main_wb set sex=b.baseremote from exp_main_wb a ,zjps.dbo.base_info_config_mx b where" +
				" a.sex=b.baselocal and b.basetype=2 ";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = " update exp_main_wb set XL=b.baseremote from exp_main_wb a ,zjps.dbo.base_info_config_mx b where" +
				" a.XL=b.baselocal and b.basetype=3 ";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = " update exp_main_wb set xw=b.baseremote from exp_main_wb a ,zjps.dbo.base_info_config_mx b where" +
				" a.xw=b.baselocal and b.basetype=4 ";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = " update exp_main_wb set zc=b.baseremote from exp_main_wb a ,zjps.dbo.base_info_config_mx b where" +
				" a.zc=b.baselocal and b.basetype=5 ";
		this.getSimpleJdbcTemplate().update(sql);	
		
		sql = " update exp_main_wb set SXLY=b.baseremote from exp_main_wb a ,zjps.dbo.base_info_config_mx b where" +
		" a.SXLY=b.baselocal and b.basetype=6 ";
		this.getSimpleJdbcTemplate().update(sql);		
		
		sql = " update exp_main_wb set CSLY=b.baseremote from exp_main_wb a ,zjps.dbo.base_info_config_mx b where" +
		" a.CSLY=b.baselocal and b.basetype=6 ";
		this.getSimpleJdbcTemplate().update(sql);	
		
		sql = " update exp_main_wb set SZDQ=b.baseremote from exp_main_wb a ,zjps.dbo.base_info_config_mx b where" +
		" a.SZDQ=b.baselocal and b.basetype=7 ";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = " update exp_jszc_wb set ly=b.baseremote from exp_jszc a ,zjps.dbo.base_info_config_mx b where" +
		" a.ly=b.baselocal and b.basetype=6 ";
		this.getSimpleJdbcTemplate().update(sql);

	}
	
	public void doInitBaseData(){
		String sql = "";
		int xx = 0;
		//证件类别
		sql = " delete from XT_DICT where lbid=1 ";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = "delete from zjps.dbo.base_info_config_mx where basetype=1";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = "select idtype dm,idtypemc mc from zjps.dbo.base_idtype order by idtype ";
		List<Map<String, Object>> listzjlb = this.getSimpleJdbcTemplate().queryForList(sql);
		for(int i=0;i<listzjlb.size();i++){
			String dictbh = tranLen(String.valueOf((i+1)), 3);
			sql = "insert into xt_dict(lbid,dictid,dictname,dictbh,enable,sort,leaf)" +
					"values(1,"+(i+1)+",'"+listzjlb.get(i).get("mc")+"','"+dictbh+"',1,0,1)";
			this.getSimpleJdbcTemplate().update(sql);
			
			sql = "insert into zjps.dbo.base_info_config_mx(basetype,baseremote,baselocal,bz)" +
					"values(1,'"+dictbh+"','"+listzjlb.get(i).get("dm")+"',1)";
			this.getSimpleJdbcTemplate().update(sql);
			
		}
		
		
		//性别
		sql = " delete from XT_DICT where lbid=4 ";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = "delete from zjps.dbo.base_info_config_mx where basetype=2";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = "insert into xt_dict(lbid,dictid,dictname,dictbh,enable,sort,leaf)" +
				"values(4,1,'男','001',1,0,1)";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = "insert into zjps.dbo.base_info_config_mx(basetype,baseremote,baselocal,bz)" +
				"values(2,'001','1',1)";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = "insert into xt_dict(lbid,dictid,dictname,dictbh,enable,sort,leaf)" +
				"values(4,2,'女','002',1,0,1)";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = "insert into zjps.dbo.base_info_config_mx(basetype,baseremote,baselocal,bz)" +
				"values(2,'002','2',1)";
		this.getSimpleJdbcTemplate().update(sql);
		
		//学历
		sql = " delete from XT_DICT where lbid=2 ";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = "delete from zjps.dbo.base_info_config_mx where basetype=3";
		this.getSimpleJdbcTemplate().update(sql);

		sql = "select education dm,educationmc mc from zjps.dbo.base_education order by education ";
		List<Map<String, Object>> listxl = this.getSimpleJdbcTemplate().queryForList(sql);
		for(int i=0;i<listxl.size();i++){
			String dictbh = tranLen(String.valueOf((i+1)), 3);
			sql = "insert into xt_dict(lbid,dictid,dictname,dictbh,enable,sort,leaf)" +
					"values(2,"+(i+1)+",'"+listxl.get(i).get("mc")+"','"+dictbh+"',1,0,1)";
			this.getSimpleJdbcTemplate().update(sql);
			
			sql = "insert into zjps.dbo.base_info_config_mx(basetype,baseremote,baselocal,bz)" +
					"values(3,'"+dictbh+"','"+listxl.get(i).get("dm")+"',1)";
			this.getSimpleJdbcTemplate().update(sql);
			
		}
		
		//学位
		sql = " delete from XT_DICT where lbid=3 ";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = "delete from zjps.dbo.base_info_config_mx where basetype=4";
		this.getSimpleJdbcTemplate().update(sql);

		sql = "select degree  dm,degreemc  mc from  zjps.dbo.base_degree   order by degree ";
		List<Map<String, Object>> listxw = this.getSimpleJdbcTemplate().queryForList(sql);
		for(int i=0;i<listxw.size();i++){
			String dictbh = tranLen(String.valueOf((i+1)), 3);
			sql = "insert into xt_dict(lbid,dictid,dictname,dictbh,enable,sort,leaf)" +
					"values(3,"+(i+1)+",'"+listxw.get(i).get("mc")+"','"+dictbh+"',1,0,1)";
			this.getSimpleJdbcTemplate().update(sql);
			
			sql = "insert into zjps.dbo.base_info_config_mx(basetype,baseremote,baselocal,bz)" +
					"values(4,'"+dictbh+"','"+listxw.get(i).get("dm")+"',1)";
			this.getSimpleJdbcTemplate().update(sql);
			
		}
		
		//职称
		sql = " delete from XT_DICT where lbid=5 ";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = "delete from zjps.dbo.base_info_config_mx where basetype=5";
		this.getSimpleJdbcTemplate().update(sql);

		sql = "select distinct tech dm,techmc mc from  zjps.dbo.base_technical order by tech ";
		List<Map<String, Object>> listzc = this.getSimpleJdbcTemplate().queryForList(sql);
		xx = 0;
		for(int i=0;i<listzc.size();i++){
			++xx;
			String dictbh = tranLen(String.valueOf((i+1)), 3);
			sql = "insert into xt_dict(lbid,dictid,dictname,dictbh,enable,sort,leaf)" +
					"values(5,"+(xx)+",'"+listzc.get(i).get("mc")+"','"+dictbh+"',1,0,0)";
			this.getSimpleJdbcTemplate().update(sql);
			
			sql = "insert into zjps.dbo.base_info_config_mx(basetype,baseremote,baselocal,bz)" +
					"values(5,'"+dictbh+"','"+listzc.get(i).get("dm")+"',1)";
			this.getSimpleJdbcTemplate().update(sql);
			
			sql = " select technical dm ,technicalmc mc  from  zjps.dbo.base_technical where tech='"+listzc.get(i).get("dm")+"'" +
					"  order by technical  ";
			List<Map<String, Object>> listzcmx = this.getSimpleJdbcTemplate().queryForList(sql);
			for(int k=0;k<listzcmx.size();k++){
				++xx;
				String dictbhmx = dictbh+tranLen(String.valueOf((k+1)), 3);
				sql = "insert into xt_dict(lbid,dictid,dictname,dictbh,enable,sort,leaf)" +
						"values(5,"+(xx)+",'"+listzcmx.get(k).get("mc")+"','"+dictbhmx+"',1,0,1)";
				this.getSimpleJdbcTemplate().update(sql);
				
				sql = "insert into zjps.dbo.base_info_config_mx(basetype,baseremote,baselocal,bz)" +
						"values(5,'"+dictbhmx+"','"+listzcmx.get(k).get("dm")+"',1)";
				this.getSimpleJdbcTemplate().update(sql);
			}
			
		}
		
		

		//技术领域
		sql = " delete from XT_DICT where lbid=14 ";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = "delete from zjps.dbo.base_info_config_mx where basetype=6";
		this.getSimpleJdbcTemplate().update(sql);

		sql = "select distinct specialtya dm,specialtyamc mc from  zjps.dbo.base_specialty where type=1 order by specialtya ";
		List<Map<String, Object>> listly = this.getSimpleJdbcTemplate().queryForList(sql);
		xx= 0;
		for(int i=0;i<listly.size();i++){
			++xx;
			String dictbh = tranLen(String.valueOf((i+1)), 3);
			sql = "insert into xt_dict(lbid,dictid,dictname,dictbh,enable,sort,leaf)" +
					"values(14,"+(xx)+",'"+listly.get(i).get("mc")+"','"+dictbh+"',1,0,0)";
			this.getSimpleJdbcTemplate().update(sql);
			
			sql = "insert into zjps.dbo.base_info_config_mx(basetype,baseremote,baselocal,bz)" +
					"values(6,'"+dictbh+"','"+listly.get(i).get("dm")+"',1)";
			this.getSimpleJdbcTemplate().update(sql);
			
			sql = " select specialtyb dm,specialtybmc mc  from  zjps.dbo.base_specialty where specialtya='"+listly.get(i).get("dm")+"' " +
					"  order by specialtyb   ";
			List<Map<String, Object>> listlymx = this.getSimpleJdbcTemplate().queryForList(sql);
			for(int k=0;k<listlymx.size();k++){
				++xx;
				String dictbhmx = dictbh+tranLen(String.valueOf((k+1)), 3);
				sql = "insert into xt_dict(lbid,dictid,dictname,dictbh,enable,sort,leaf)" +
						"values(14,"+(xx)+",'"+listlymx.get(k).get("mc")+"','"+dictbhmx+"',1,0,1)";
				this.getSimpleJdbcTemplate().update(sql);
				
				sql = "insert into zjps.dbo.base_info_config_mx(basetype,baseremote,baselocal,bz)" +
						"values(6,'"+dictbhmx+"','"+listlymx.get(k).get("dm")+"',1)";
				this.getSimpleJdbcTemplate().update(sql);
			}
			
		}
		

		//所在地区
		sql = " delete from XT_DICT where lbid=10 ";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = "delete from zjps.dbo.base_info_config_mx where basetype=7";
		this.getSimpleJdbcTemplate().update(sql);

		sql = "  select distinct province dm,provincemc mc  from  zjps.dbo.base_city order by province ";
		List<Map<String, Object>> listdq = this.getSimpleJdbcTemplate().queryForList(sql);
		xx = 0;
		for(int i=0;i<listdq.size();i++){
			++xx;
			String dictbh = tranLen(String.valueOf((i+1)), 3);
			sql = "insert into xt_dict(lbid,dictid,dictname,dictbh,enable,sort,leaf)" +
					"values(10,"+(xx)+",'"+listdq.get(i).get("mc")+"','"+dictbh+"',1,0,0)";
			this.getSimpleJdbcTemplate().update(sql);
			
			sql = "insert into zjps.dbo.base_info_config_mx(basetype,baseremote,baselocal,bz)" +
					"values(7,'"+dictbh+"','"+listdq.get(i).get("dm")+"',1)";
			this.getSimpleJdbcTemplate().update(sql);
			
			sql = "  select distinct city dm,citymc mc  from  zjps.dbo.base_city where province='"+listdq.get(i).get("dm")+"' " +
					"  order by city   ";
			List<Map<String, Object>> listdqmx = this.getSimpleJdbcTemplate().queryForList(sql);
			for(int k=0;k<listdqmx.size();k++){
				++xx;
				String dictbhmx = dictbh+tranLen(String.valueOf((k+1)), 3);
				sql = "insert into xt_dict(lbid,dictid,dictname,dictbh,enable,sort,leaf)" +
						"values(10,"+(xx)+",'"+listdqmx.get(k).get("mc")+"','"+dictbhmx+"',1,0,0)";
				this.getSimpleJdbcTemplate().update(sql);
				
				sql = "insert into zjps.dbo.base_info_config_mx(basetype,baseremote,baselocal,bz)" +
						"values(7,'"+dictbhmx+"','"+listdqmx.get(k).get("dm")+"',1)";
				this.getSimpleJdbcTemplate().update(sql);
				
				sql = "  select county dm,countymc mc  from  zjps.dbo.base_city where province='"+listdq.get(i).get("dm")+"' and city='"+listdqmx.get(k).get("dm")+"' " +
						"  order by county   ";
				List<Map<String, Object>> listdqmxmx = this.getSimpleJdbcTemplate().queryForList(sql);
				for(int x=0;x<listdqmxmx.size();x++){
					++xx;
					String dictbhmxmx = dictbhmx+tranLen(String.valueOf((x+1)), 3);
					sql = "insert into xt_dict(lbid,dictid,dictname,dictbh,enable,sort,leaf)" +
							"values(10,"+(xx)+",'"+listdqmxmx.get(x).get("mc")+"','"+dictbhmxmx+"',1,0,1)";
					this.getSimpleJdbcTemplate().update(sql);
					
					sql = "insert into zjps.dbo.base_info_config_mx(basetype,baseremote,baselocal,bz)" +
							"values(7,'"+dictbhmxmx+"','"+listdqmxmx.get(x).get("dm")+"',1)";
					this.getSimpleJdbcTemplate().update(sql);
				}
			}
			
		}
	}
	
	
	public String tranLen(String d,int l){
		String bh = d;
		int  len = d.length();
		for(int i=len ;i<l;i++){
			bh = "0"+bh;
		}
		return bh;
	}
}
