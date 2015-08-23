package com.netwander.explib.service.remote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.fins.gt.model.FilterInfo;
import com.fins.gt.model.SortInfo;
import com.netwander.core.Constants;
import com.netwander.core.common.LimitPage;
import com.netwander.core.common.Page;
import com.netwander.explib.entity.Xtuser;
import com.netwander.explib.exception.BusException;
import com.netwander.explib.service.common.BaseService;
import com.sun.corba.se.impl.orbutil.closure.Constant;
import com.sun.java_cup.internal.internal_error;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Service
public class RemoteService extends BaseService implements RemoteServiceInterface{
	protected static Log logger = LogFactory.getLog(RemoteService.class);
	
	public int isLink() {
		// TODO Auto-generated method stub
		return 1;
	}
	
	public Map doSEI(String user, String pass, String type,String wbfl, Map expMap) {
		// TODO Auto-generated method stub
		logger.debug("类型 ："+type+" 传入专家开始同步...");
		Map m = new HashMap();
		try{
			String sql = " select count(*) from wb_user where wbtype="+type+" and wbuser='"+user+"' and wbpass='"+pass+"'";
			if(this.getSimpleJdbcTemplate().queryForInt(sql)>0){
				String zjno = "";
				if(expMap.get("zjno") != null && !expMap.get("zjno").equals("")){
					zjno = String.valueOf(expMap.get("zjno")).replaceAll(" ", "").trim();
				}else{
					m.put("code", "-1");
					m.put("text", "证件号不能为空！");
				}
				String maxid = String.valueOf(expMap.get("expertid"));
				
				sql = "delete from exp_main_zj where itype="+type+" and rcid='"+maxid+"'";
				super.update(true, sql);
				
				//插入人才信息
				sql = " insert into exp_main_zj(rcid,status,rcname,oldname,sex,nation,jg,birthday,zjlb,zjno,rclb,xl,zc,zw,xw,email,ptel," +
					" xxzy,sxzy1,sxzy2,sxzy3,cszy,byxx,byrq,workunit,szdq,dwdq,dwxz,dwaddr,officetel,dwcode,fax,jtaddr,jtcode," +
					"jttel,zgbs,xsjt,hdzz,bgbs,rcbs,rcbz,xzbz,xzzw,zgshbz,fjpath,fjmc,info,expgjbs,fid,expjsdf,EXPHBDF,itype,fldm,tbbz,expertid_) " +
					"values('"+maxid+"',1,'"+expMap.get("rcname")+"','"+expMap.get("oldname")+"','"+expMap.get("sex")+"','"+expMap.get("nation")+"'," +
					"'"+expMap.get("jg")+"',"+transToD(String.valueOf(expMap.get("birthday")))+",'"+expMap.get("zjlb")+"','"+zjno+"'," +
					"'"+expMap.get("rclb")+"','"+expMap.get("xl")+"','"+expMap.get("zc")+"','"+expMap.get("zw")+"','"+expMap.get("xw")+"'," +
					"'"+expMap.get("email")+"','"+expMap.get("ptel")+"','"+expMap.get("xxzy")+"','"+expMap.get("sxzy1")+"','"+expMap.get("sxzy2")+"'," +
					"'"+expMap.get("sxzy3")+"','"+expMap.get("cszy")+"','"+expMap.get("byxx")+"',"+transToD(String.valueOf(expMap.get("byrq")))+",'"+expMap.get("workunit")+"'," +
					"'"+expMap.get("szdq")+"','"+expMap.get("dwdq")+"','"+expMap.get("dwxz")+"','"+expMap.get("dwaddr")+"','"+expMap.get("officetel")+"'," +
					"'"+expMap.get("dwcode")+"','"+expMap.get("fax")+"','"+expMap.get("jtaddr")+"','"+expMap.get("jtcode")+"','"+expMap.get("jttel")+"'," +
					"'"+expMap.get("zgbs")+"','"+expMap.get("xsjt")+"','"+expMap.get("hdzz")+"',0,0," +
					"0,0,'"+expMap.get("xzzw")+"',0,null,null,?,?,"+transToN(String.valueOf(expMap.get("fid")))+","+transToN(String.valueOf(expMap.get("expjsdf")))+"," +
							""+transToN(String.valueOf(expMap.get("exphbdf")))+","+type+",'"+wbfl+"',3,newid())";
				super.update(true,sql,new Object[]{expMap.get("info"),expMap.get("expgjbs")});
				
				sql = " delete from EXP_FL_PROTYPE_zj where fldm='"+wbfl+"' and rcid="+maxid;
				this.getSimpleJdbcTemplate().update(sql);
				
				if(type.equals("90")){//专家计划评审
					sql = "insert into EXP_FL_PROTYPE_zj(RCID, FLDM, SXLY, CSLY, SXLY1, SXLY2, SXLY3) " +
						" values("+maxid+",'"+wbfl+"','"+transToS(expMap.get("sxly"))+"','"+transToS(expMap.get("csly"))+"'," +
							"'"+transToS(expMap.get("sxly1"))+"','"+transToS(expMap.get("sxly2"))+"','"+transToS(expMap.get("sxly3"))+"')";
				}else if(type.equals("80")){//进步奖
					sql = "insert into EXP_FL_PROTYPE_zj(RCID, FLDM, SXZY, CSZY, SXZY1, SXZY2, SXZY3) " +
						" values("+maxid+",'"+wbfl+"','"+transToS(expMap.get("sxzy"))+"','"+transToS(expMap.get("cszy"))+"'," +
						"'"+transToS(expMap.get("sxzy1"))+"','"+transToS(expMap.get("sxzy2"))+"','"+transToS(expMap.get("sxzy3"))+"')";
				}else if(type.equals("70")){//领军型创新创业人才项目
					sql = "insert into EXP_FL_PROTYPE_zj(RCID, FLDM, SXLY, CSLY, SXLY1, SXLY2, SXLY3,SXLYO, CSLYO, SXLY1O, SXLY2O, SXLY3O) " +
					" values("+maxid+",'"+wbfl+"','"+transToS(expMap.get("sxly"))+"','"+transToS(expMap.get("csly"))+"'," +
					"'"+transToS(expMap.get("sxly1"))+"','"+transToS(expMap.get("sxly2"))+"','"+transToS(expMap.get("sxly3"))+"'," +
					"'"+transToS(expMap.get("sxlyo"))+"','"+transToS(expMap.get("cslyo"))+"'," +
					"'"+transToS(expMap.get("sxly1o"))+"','"+transToS(expMap.get("sxly2o"))+"','"+transToS(expMap.get("sxly3o"))+"'" +
					")";
				}else{
					
					sql = "insert into EXP_FL_PROTYPE_zj(RCID, FLDM, SXLY, CSLY, SXLY1, SXLY2, SXLY3,SXLYO, CSLYO, SXLY1O, SXLY2O, SXLY3O, SXZY, CSZY, SXZY1, SXZY2, SXZY3) " +
					" values("+maxid+",'"+wbfl+"','"+transToS(expMap.get("sxly"))+"','"+transToS(expMap.get("csly"))+"'," +
					"'"+transToS(expMap.get("sxly1"))+"','"+transToS(expMap.get("sxly2"))+"','"+transToS(expMap.get("sxly3"))+"'," +
					"'"+transToS(expMap.get("sxlyo"))+"','"+transToS(expMap.get("cslyo"))+"'," +
					"'"+transToS(expMap.get("sxly1o"))+"','"+transToS(expMap.get("sxly2o"))+"','"+transToS(expMap.get("sxly3o"))+"'," +
					"'"+transToS(expMap.get("sxzy"))+"','"+transToS(expMap.get("cszy"))+"'," +
					"'"+transToS(expMap.get("sxzy1"))+"','"+transToS(expMap.get("sxzy2"))+"','"+transToS(expMap.get("sxzy3"))+"'"+
					")";
				}
				
				this.getSimpleJdbcTemplate().update(sql);
				m.put("code", "1");
				m.put("text", "数据保存成功！");
				//sql = " delete from EXP_MAIN_WB where ";
			}else{
				m.put("code", "-1");
				m.put("text", "传入用户名或密码错误！");
			}
		}catch(Exception e){e.printStackTrace();
			m.put("code", "-2");
			m.put("text", "遇到异常 ："+e.toString());
		}
		return m;
	}

	
	public Map doSEIList(String user, String pass, String type, List<Map> les) {
		// TODO Auto-generated method stub
		logger.debug("类型 ："+type+" 传入专家开始同步...");
		Map m = new HashMap();
		try{
			String sql = " select count(*) from wb_user where wbtype="+type+" and wbuser='"+user+"' and wbpass='"+pass+"'";
			if(this.getSimpleJdbcTemplate().queryForInt(sql)>0){
				for(int i=0;i<les.size();i++){
					Map expMap = (Map)les.get(i);
					String zjno = "";
					if(expMap.get("zjno") != null && !expMap.get("zjno").equals("")){
						zjno = String.valueOf(expMap.get("zjno")).replaceAll(" ", "").trim();
					}else{
						m.put("code", "-1");
						m.put("text", "证件号不能为空！");
					}
					String maxid = String.valueOf(expMap.get("expertid"));
					
					sql = "delete from exp_main_wb where itype="+type+" and rcid='"+maxid+"'";
					super.update(true, sql);
					
					//String maxid = String.valueOf(this.getMaxKey("EXP_MAIN"));
					//插入人才信息
					sql = " insert into exp_main_wb(rcid,status,rcname,oldname,sex,nation,jg,birthday,zjlb,zjno,rclb,xl,zc,zw,xw,email,ptel," +
						" xxzy,sxzy1,sxzy2,sxzy3,cszy,byxx,byrq,workunit,szdq,dwdq,dwxz,dwaddr,officetel,dwcode,fax,jtaddr,jtcode," +
						"jttel,zgbs,xsjt,hdzz,bgbs,rcbs,rcbz,xzbz,xzzw,zgshbz,fjpath,fjmc,info,expgjbs,fid,expjsdf,EXPHBDF,itype,tbbz,expertid_) " +
						"values('"+maxid+"',1,'"+expMap.get("rcname")+"','"+expMap.get("oldname")+"','"+expMap.get("sex")+"','"+expMap.get("nation")+"'," +
						"'"+expMap.get("jg")+"',"+transToD(String.valueOf(expMap.get("birthday")))+",'"+expMap.get("zjlb")+"','"+zjno+"'," +
						"'"+expMap.get("rclb")+"','"+expMap.get("xl")+"','"+expMap.get("zc")+"','"+expMap.get("zw")+"','"+expMap.get("xw")+"'," +
						"'"+expMap.get("email")+"','"+expMap.get("ptel")+"','"+expMap.get("xxzy")+"','"+expMap.get("sxzy1")+"','"+expMap.get("sxzy2")+"'," +
						"'"+expMap.get("sxzy3")+"','"+expMap.get("cszy")+"','"+expMap.get("byxx")+"',"+transToD(String.valueOf(expMap.get("byrq")))+",'"+expMap.get("workunit")+"'," +
						"'"+expMap.get("szdq")+"','"+expMap.get("dwdq")+"','"+expMap.get("dwxz")+"','"+expMap.get("dwaddr")+"','"+expMap.get("officetel")+"'," +
						"'"+expMap.get("dwcode")+"','"+expMap.get("fax")+"','"+expMap.get("jtaddr")+"','"+expMap.get("jtcode")+"','"+expMap.get("jttel")+"'," +
						"'"+expMap.get("zgbs")+"','"+expMap.get("xsjt")+"','"+expMap.get("hdzz")+"',0,0," +
						"0,0,'"+expMap.get("xzzw")+"',0,null,null,?,?,"+transToN(String.valueOf(expMap.get("fid")))+","+transToN(String.valueOf(expMap.get("expjsdf")))+"," +
								""+transToN(String.valueOf(expMap.get("exphbdf")))+","+type+",3,newid())";
					super.update(true,sql,new Object[]{expMap.get("info"),expMap.get("expgjbs")});
					
				}
				m.put("code", "1");
				m.put("text", "数据保存成功！");
				//sql = " delete from EXP_MAIN_WB where ";
			}else{
				m.put("code", "-1");
				m.put("text", "传入用户名或密码错误！");
			}
		}catch(Exception e){e.printStackTrace();
			m.put("code", "-2");
			m.put("text", "遇到异常 ："+e.toString());
		}
		return m;
	}
	
	
	public Map getEI(String user, String pass, String type, String id) {
		// TODO Auto-generated method stub
		Map m = new HashMap();
		try{
			String sql = " select count(*) from wb_user where wbtype="+type+" and wbuser='"+user+"' and wbpass='"+pass+"'";
			if(this.getSimpleJdbcTemplate().queryForInt(sql)>0){
				sql = " select count(*) from exp_main where zjno='"+id+"'";
				if(this.getSimpleJdbcTemplate().queryForInt(sql)>0){
					sql = " select * from exp_main where zjno=?";
					m = this.getSimpleJdbcTemplate().queryForMap(sql,new Object[]{id});
				}else{
					m.put("code", "-1");
					m.put("text", "专家未找到！");
				}
			}else{
				m.put("code", "-1");
				m.put("text", "传入用户名或密码错误！");
			}
		}catch(Exception e){e.printStackTrace();
			m.put("code", "-2");
			m.put("text", "遇到异常 ："+e.toString());
		}
		
		return m;
	}


	public Map getBaseInfo(String user, String pass, String type,String lbid) {
		// TODO Auto-generated method stub
		Map m = new HashMap();
		try{
			String sql = " select count(*) from wb_user where wbtype="+type+" and wbuser='"+user+"' and wbpass='"+pass+"'";
			if(this.getSimpleJdbcTemplate().queryForInt(sql)>0){
				sql = " select dictname mc,dictbh dm from xt_dict where lbid= "+lbid+" order by dictbh ";
				List list = this.getSimpleJdbcTemplate().queryForList(sql);
				m.put("code", "1");
				m.put("text", list);
			}else{
				m.put("code", "-1");
				m.put("text", "传入用户名或密码错误！");
			}
		}catch(Exception e){e.printStackTrace();
			m.put("code", "-2");
			m.put("text", "遇到异常 ："+e.toString());
		}
		return m;
	}


	public int isBaseChange(String user, String pass, String type) {
		// TODO Auto-generated method stub
		return 1;
	}

	public Object getListForRcdaByName(Map  limit,
			Map<String, String> parMap, SortInfo sortInfo,
			List<FilterInfo> filterInfos,List list) {
		// TODO Auto-generated method stub
		LimitPage limit1 = new LimitPage();
		limit1.setRowAttributes(Integer.parseInt(limit.get("rowcount").toString()),
				Integer.parseInt(limit.get("pageno").toString()),
				Integer.parseInt(limit.get("pagesize").toString()));
		String sql = parMap.get("sql") == null ? "" :  parMap.get("sql");
		
		String zjqbz = parMap.get("zjqbz") == null ? "" :  parMap.get("zjqbz");
		String fldm = parMap.get("fldm") == null ? "" :  parMap.get("fldm");
		
		String ls_sql = "";
		if(zjqbz != null && zjqbz.equals("1")){
			ls_sql = "select exp_main_v.* from exp_main_v where 1=1 " ;
		}else{
			ls_sql = "select exp_main_v.* from exp_main_v where 1=1 " ;
		}
		ls_sql += "  and rcid in (  select distinct rcid from exp_flmx where fldm ='"+fldm+"')";
		
		String xl = parMap.get("xl") == null ?"":parMap.get("xl");
		String xw = parMap.get("xw") == null ?"":parMap.get("xw");
		String zc = parMap.get("zc") == null ?"":parMap.get("zc");
		String zw = parMap.get("zw") == null ?"":parMap.get("zw");
		String jspj = parMap.get("jspj") == null ?"":parMap.get("jspj");
		String hbpj = parMap.get("hbpj") == null ?"":parMap.get("hbpj");
		String dwxz = parMap.get("dwxz") == null ?"":parMap.get("dwxz");
		
		String strnl = parMap.get("strnl") == null ?"":parMap.get("strnl");
		String endnl = parMap.get("endnl") == null ?"":parMap.get("endnl");
		String v2 = parMap.get("v2") == null ?"":parMap.get("v2");
		
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
		
		if(!dwxz.equals("")){
			ls_sql += " and dwxz='"+dwxz+"'" ;
		}
		
		if(!jspj.equals("")){
			ls_sql += " and expjsdf="+jspj;
		}
		
		if(!hbpj.equals("")){
			ls_sql += " and exphbdf='"+hbpj+"'";
		}
		if(sql != null && !sql.equals("") && !sql.trim().toLowerCase().equals("null")){
			ls_sql += " and ("+sql+") ";
		}
		
		if(!v2.equals("") && !v2.equals("0")){
			String v = "",ver= "";
			if(list != null){
				for(int i=0;i<list.size();i++){
					Map map = (Map)list.get(i);
					if(v2.equals("1")){
						if(v.equals("")){
							v += "'"+String.valueOf(map.get("keyv"))+"'";
						}else{
							v += ",'"+String.valueOf(map.get("keyv"))+"'";
						}
					}else{
						if(v.equals("")){
							v += "'"+String.valueOf(map.get("expertid"))+"'";
							ver += "'"+String.valueOf(map.get("ver"))+"'";
						}else{
							v += ",'"+String.valueOf(map.get("expertid"))+"'";
							ver += ",'"+String.valueOf(map.get("ver"))+"'";
						}
					}
					
				}
			}
			if(!v.equals("")){
				if(v2.equals("1")){
					ls_sql += " and rcid not in ("+v+")";
				}else if(v2.equals("2")){
					ls_sql += " and rcid in ("+v+") and isnull(ver,0) not in ("+ver+")";
				}
			}
		}
		
		if(strnl!= null && !strnl.trim().equals("") && !strnl.trim().equals("0")){
			ls_sql += " and convert(varchar(100),birthday,23)<='"+this.getBirthday(Integer.parseInt(strnl), 0)+"'" ;
		}
		if(endnl!= null && !endnl.trim().equals("") && !endnl.trim().equals("0")){
			ls_sql += " and convert(varchar(100),birthday,23)>='"+this.getBirthday(Integer.parseInt(endnl), 1)+"'" ;
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
			return this.queryForListWithPage(ls_sql, limit1);
		} else {
			return this.simpleJdbcTemplate.queryForList(ls_sql);
		}
	}
	
	/***
	 * sbz :=111 以专家ID为基础引入
	 * sbz :=222 以专家条件批量引入
	 */
	public Map getEIList(String user, String pass, String type, String fldm,Integer sbz,
			List list) {
		Map m = new HashMap();
		try{
			String sql = " select count(*) from wb_user where wbtype="+type+" and wbuser='"+user+"' and wbpass='"+pass+"'";
			if(this.getSimpleJdbcTemplate().queryForInt(sql)>0){
				if(sbz == 111){
					String v = "";
					if(list != null && list.size() > 0){
						Map mm = (HashMap)list.get(0);
						v = String.valueOf(mm.get("keyv"));
					}
					sql = " select exp_main_v.*," +
						"isnull((select SXZY from EXP_FL_PROTYPE where rcid=exp_main_v.rcid and fldm='"+fldm+"'),'') SXZY_FL," +
						"isnull((select CSZY from EXP_FL_PROTYPE where rcid=exp_main_v.rcid and fldm='"+fldm+"'),'') CSZY_FL," +
						"isnull((select SXZY1 from EXP_FL_PROTYPE where rcid=exp_main_v.rcid and fldm='"+fldm+"'),'') SXZY1_FL," +
						"isnull((select SXZY2 from EXP_FL_PROTYPE where rcid=exp_main_v.rcid and fldm='"+fldm+"'),'') SXZY2_FL," +
						"isnull((select SXZY3 from EXP_FL_PROTYPE where rcid=exp_main_v.rcid and fldm='"+fldm+"'),'') SXZY3_FL, " +
						
						"isnull((select SXLY from EXP_FL_PROTYPE where rcid=exp_main_v.rcid and fldm='"+fldm+"'),'') SXLY_FL," +
						"isnull((select CSLY from EXP_FL_PROTYPE where rcid=exp_main_v.rcid and fldm='"+fldm+"'),'') CSLY_FL," +
						"isnull((select SXLY1 from EXP_FL_PROTYPE where rcid=exp_main_v.rcid and fldm='"+fldm+"'),'') SXLY1," +
						"isnull((select SXLY2 from EXP_FL_PROTYPE where rcid=exp_main_v.rcid and fldm='"+fldm+"'),'') SXLY2," +
						"isnull((select SXLY3 from EXP_FL_PROTYPE where rcid=exp_main_v.rcid and fldm='"+fldm+"'),'') SXLY3, " +
						
						"isnull((select SXLYO from EXP_FL_PROTYPE where rcid=exp_main_v.rcid and fldm='"+fldm+"'),'') SXLYO_FL," +
						"isnull((select CSLYO from EXP_FL_PROTYPE where rcid=exp_main_v.rcid and fldm='"+fldm+"'),'') CSLYO_FL," +
						"isnull((select SXLY1O from EXP_FL_PROTYPE where rcid=exp_main_v.rcid and fldm='"+fldm+"'),'') SXLY1O," +
						"isnull((select SXLY2O from EXP_FL_PROTYPE where rcid=exp_main_v.rcid and fldm='"+fldm+"'),'') SXLY2O," +
						"isnull((select SXLY3O from EXP_FL_PROTYPE where rcid=exp_main_v.rcid and fldm='"+fldm+"'),'') SXLY3O " +
						
							" from exp_main_v where rcid in ("+v+") ";
					List l = this.getSimpleJdbcTemplate().queryForList(sql);
					m.put("code", "1");
					m.put("text", l);
				}else if(sbz == 222){
					String v = "";
					if(list != null && list.size() > 0){
						Map mm = (HashMap)list.get(0);
						v = String.valueOf(mm.get("keyv"));
					}
					if(v == null || v=="null"){
						v = "";
					}
					sql = " select exp_main_v.*," +
							"isnull((select SXZY from EXP_FL_PROTYPE where rcid=exp_main_v.rcid and fldm='"+fldm+"'),'') SXZY_FL," +
							"isnull((select CSZY from EXP_FL_PROTYPE where rcid=exp_main_v.rcid and fldm='"+fldm+"'),'') CSZY_FL," +
							"isnull((select SXZY1 from EXP_FL_PROTYPE where rcid=exp_main_v.rcid and fldm='"+fldm+"'),'') SXZY1_FL," +
							"isnull((select SXZY2 from EXP_FL_PROTYPE where rcid=exp_main_v.rcid and fldm='"+fldm+"'),'') SXZY2_FL," +
							"isnull((select SXZY3 from EXP_FL_PROTYPE where rcid=exp_main_v.rcid and fldm='"+fldm+"'),'') SXZY3_FL, " +
							
							"isnull((select SXLY from EXP_FL_PROTYPE where rcid=exp_main_v.rcid and fldm='"+fldm+"'),'') SXLY_FL," +
							"isnull((select CSLY from EXP_FL_PROTYPE where rcid=exp_main_v.rcid and fldm='"+fldm+"'),'') CSLY_FL," +
							"isnull((select SXLY1 from EXP_FL_PROTYPE where rcid=exp_main_v.rcid and fldm='"+fldm+"'),'') SXLY1," +
							"isnull((select SXLY2 from EXP_FL_PROTYPE where rcid=exp_main_v.rcid and fldm='"+fldm+"'),'') SXLY2," +
							"isnull((select SXLY3 from EXP_FL_PROTYPE where rcid=exp_main_v.rcid and fldm='"+fldm+"'),'') SXLY3,  " +
							
							"isnull((select SXLYO from EXP_FL_PROTYPE where rcid=exp_main_v.rcid and fldm='"+fldm+"'),'') SXLYO_FL," +
							"isnull((select CSLYO from EXP_FL_PROTYPE where rcid=exp_main_v.rcid and fldm='"+fldm+"'),'') CSLYO_FL," +
							"isnull((select SXLY1O from EXP_FL_PROTYPE where rcid=exp_main_v.rcid and fldm='"+fldm+"'),'') SXLY1O," +
							"isnull((select SXLY2O from EXP_FL_PROTYPE where rcid=exp_main_v.rcid and fldm='"+fldm+"'),'') SXLY2O," +
							"isnull((select SXLY3O from EXP_FL_PROTYPE where rcid=exp_main_v.rcid and fldm='"+fldm+"'),'') SXLY3O " +
							
							" from exp_main_v where 1=1 ";
					if( v!= null && !v.equals("")){
						sql += " and "+v;
					}
					sql += "  and rcid in (  select distinct rcid from exp_flmx where fldm ='"+fldm+"')";
					
					List l = this.getSimpleJdbcTemplate().queryForList(sql);
					m.put("code", "1");
					m.put("text", l);
				}
				
			}else{
				m.put("code", "-1");
				m.put("text", "传入用户名或密码错误！");
			}
		}catch(Exception e){e.printStackTrace();
			m.put("code", "-2");
			m.put("text", "遇到异常 ："+e.toString());
		}
		return m;
	}

	public Object getGzList(String user, String pass, String type, Map limit,
			Map<String, String> parMap, SortInfo sortInfo,
			List<FilterInfo> filterInfos, List list) {
		// TODO Auto-generated method stub
		String sql = " select count(*) from wb_user where wbtype="+type+" and wbuser='"+user+"' and wbpass='"+pass+"'";
		if(this.getSimpleJdbcTemplate().queryForInt(sql)>0){
			
			LimitPage limit1 = new LimitPage();
			limit1.setRowAttributes(Integer.parseInt(limit.get("rowcount").toString()),
					Integer.parseInt(limit.get("pageno").toString()),
					Integer.parseInt(limit.get("pagesize").toString()));
			
			String name = parMap.get("name") == null ? "" :  parMap.get("name");
			String where = parMap.get("where") == null ? "" :  parMap.get("where");
			String zjqbz = parMap.get("zjqbz") == null ? "" :  parMap.get("zjqbz");
			String ls_sql = "";
			String gznr = parMap.get("gznr") == null ?"":parMap.get("gznr");
			
			ls_sql = "select exp_main_v.*,dbo.get_gz_wb(rcid,'"+gznr+"') as GZ from exp_main_v where 1=1 " ;
			
			String xl = parMap.get("xl") == null ?"":parMap.get("xl");
			String xw = parMap.get("xw") == null ?"":parMap.get("xw");
			String zc = parMap.get("zc") == null ?"":parMap.get("zc");
			String zw = parMap.get("zw") == null ?"":parMap.get("zw");
			String jspj = parMap.get("jspj") == null ?"":parMap.get("jspj");
			String hbpj = parMap.get("hbpj") == null ?"":parMap.get("hbpj");
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
			
			if(!dwxz.equals("")){
				ls_sql += " and dwxz='"+dwxz+"'" ;
			}
			
			if(!jspj.equals("")){
				ls_sql += " and expjsdf="+jspj;
			}
			
			if(!hbpj.equals("")){
				ls_sql += " and exphbdf='"+hbpj+"'";
			}
			String v = "";
			if(list != null){
				for(int i=0;i<list.size();i++){
					Map map = (Map)list.get(i);
					if(v.equals("")){
						v += "'"+String.valueOf(map.get("keyv"))+"'";
					}else{
						v += ",'"+String.valueOf(map.get("keyv"))+"'";
					}
					
				}
			}
			ls_sql += " and rcid in ("+v+")";
			
			
			if(strnl!= null && !strnl.trim().equals("") && !strnl.trim().equals("0")){
				ls_sql += " and convert(varchar(100),birthday,23)<='"+this.getBirthday(Integer.parseInt(strnl), 0)+"'" ;
			}
			if(endnl!= null && !endnl.trim().equals("") && !endnl.trim().equals("0")){
				ls_sql += " and convert(varchar(100),birthday,23)>='"+this.getBirthday(Integer.parseInt(endnl), 1)+"'" ;
			}
			
			if(where != null && !where.equals("") && !name.equals("")){
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
			if (limit1 != null) {
				return this.queryForListWithPage(ls_sql, limit1);
			} else {
				return this.simpleJdbcTemplate.queryForList(ls_sql);
			}
		}else{
			return new Page();
		}
	}
	
	
	public Map doSaveGz(String user, String pass, String type, Map parMap) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map doSaveGzFl(String user, String pass, String type, Map parMap) {
		// TODO Auto-generated method stub
		String sql = "";
		//sql = " delete from exp_gzgl_bm where dictbh = '"+parMap.get("dictbh")+"' and bz='"+type+"'";
		//this.getSimpleJdbcTemplate().update(sql);
		
		//sql ="insert into exp_gzgl_bm(dictbh,dictname,bmdm,bz) values('"+parMap.get("dictbh")+"','"+parMap.get("dictname")+"',null,'"+type+"')";
        //this.jdbcTemplate.update(sql);
		return null;
	}

	public boolean checkLogin(String user, String password) {
		// TODO Auto-generated method stub
		String sql = " select count(*) from XT_USER where lower(loginname)=? and lower(password)=? ";
		if(queryForInt(sql, new Object[]{user.trim().toLowerCase(),password.trim().toLowerCase()}) > 0){
			return true;
		}else{
			return false;
		}
	}

	public boolean doTbGz(String wbfl, String wbflmc, String gzlx,String type,
			List<Map<String, Object>> list) {
		// TODO Auto-generated method stub
		String dictbh = "";
		String sql = " select count(*) from EXP_GZGL_BM where dictbh like '"+gzlx+"%' and WBDICTBH='"+wbfl+"' and bz='"+type+"'";
		if(this.getSimpleJdbcTemplate().queryForInt(sql) == 0){
			String bh = gzlx, perbh = "";
			Integer bhlen = bh.length() + 3;
    	    perbh = bh;
    	    sql ="select isnull(max(dictbh),0) + 1 from exp_gzgl_bm where len(dictbh) = ? and dictbh like ?";
        	dictbh  = String.valueOf(this.getJdbcTemplate().queryForLong(sql, new Object[]{bhlen,perbh+"%" }));
        	String zero = "";
        	int value = dictbh.length() % 3;
        	if(value>0){
        		for(int i=0;i<3-value;i++){
        			zero += "0";
        		}
        	}
            dictbh = zero + dictbh;
            
            if(bhlen != dictbh.length())
            {
            	dictbh = bh + dictbh;
            }
            
			sql = " insert into EXP_GZGL_BM(DICTBH, DICTNAME, BMDM, BZ, WBDICTBH) " +
					"values('"+dictbh+"','"+wbflmc+"',null,'"+type+"','"+wbfl+"')";
			this.getSimpleJdbcTemplate().update(sql);
		}else{
			sql = "select dictbh from EXP_GZGL_BM where dictbh like '"+gzlx+"%' and WBDICTBH='"+wbfl+"' and bz='"+type+"'";
			dictbh = this.getSimpleJdbcTemplate().queryForObject(sql, String.class);
		}
		
		String gzqk = "";
		if(list != null && list.size() > 0){
			Integer maxid = Integer.parseInt(getMaxKey("exp_gz", list.size()*2).toString());
			for(int i=0;i<list.size();i++){
				gzqk = "";
				sql = "delete from EXP_GZ where gztype=1 and rcid="+list.get(i).get("expertid")+" and gzmc='"+dictbh+"'";
				this.getSimpleJdbcTemplate().update(sql);
				
				List<Map<String, Object>> listmx = (ArrayList)list.get(i).get("psqk");
				if(type.equals("90")){//计划
					for(int z=0;z<listmx.size();z++){
						gzqk += (z+1)+" 项目组："+listmx.get(z).get("projgrmc")+"(参评项目:"+listmx.get(z).get("xmcount")+" ，技术领域："+listmx.get(z).get("spec_mc")+"，计划类别："+listmx.get(z).get("lev_mc")+") \n";
					}
				}else if(type.equals("80")){//进步奖
					for(int z=0;z<listmx.size();z++){
						gzqk += (z+1)+" 项目组："+listmx.get(z).get("projgrmc")+"(参评项目:"+listmx.get(z).get("xmcount")+" ，评审专业组："+listmx.get(z).get("zyz_mc")+") \n";
					}
				}else if(type.equals("70")){//海归
					for(int z=0;z<listmx.size();z++){
						gzqk += (z+1)+" 项目组："+listmx.get(z).get("projgrmc")+"(参评项目:"+listmx.get(z).get("xmcount")+" ，项目批次："+listmx.get(z).get("xmpc_mc")+") \n";
					}
				}
				
				sql = "insert into EXP_GZ(GZID, GZMC, GZSTRDATE, GZENDDATE, HDDD, HDZBDW,  RCID,  GZQK,gztype) " +
						" values("+(maxid)+",'"+dictbh+"',"+transToD(String.valueOf(list.get(i).get("strdate")))+","+transToD(String.valueOf(list.get(i).get("enddate")))+",'专家网络评审','科技信息中心评估中心'," +
								"'"+list.get(i).get("expertid")+"', '"+gzqk+"',1 )";
				this.getSimpleJdbcTemplate().update(sql);
				
				++maxid;
				
			}
		}
		
		return false;
	}
	
}
