package com.netwander.explib.service;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.components.Form;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.stereotype.Service;

import com.netwander.core.common.TreeBean;
import com.netwander.explib.entity.XtDict;
import com.netwander.explib.entity.XtDlb;
import com.netwander.explib.exception.BusException;
import com.netwander.explib.service.common.BaseService;

@Service
public class ExpService  extends BaseService{
	
	/**
	 * 根据身份证号在主表中找当前专家是否已经存在
	 */
	public String getLibExpWithSfz(String sfzh){
		String sql = " select count(*) from exp_main where ZJNO ='"+sfzh+"'";
		if(super.queryForInt(sql) > 0){
			sql = "select rcid from exp_main where ZJNO ='"+sfzh+"'";
			return String.valueOf(queryForInt(sql));
		}
		return "";
	}
	
	public Map preExpMain_v(String rcid){
		Map m = new HashMap();
		if(rcid != null && !rcid.equals("")){
			String sql_q = " select cc.* ," +
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
					" from (" +
			"select a.*," +
			"isnull((select SXLY from EXP_FL_PROTYPE where rcid=a.rcid and fldm='000000001'),'') SXLY_FL_1," +
			"isnull((select CSLY from EXP_FL_PROTYPE where rcid=a.rcid and fldm='000000001'),'') CSLY_FL_1," +
			"isnull((select SXLY1 from EXP_FL_PROTYPE where rcid=a.rcid and fldm='000000001'),'') SXLY1_FL_1," +
			"isnull((select SXLY2 from EXP_FL_PROTYPE where rcid=a.rcid and fldm='000000001'),'') SXLY2_FL_1," +
			"isnull((select SXLY3 from EXP_FL_PROTYPE where rcid=a.rcid and fldm='000000001'),'') SXLY3_FL_1, " +
			
			"isnull((select SXZY from EXP_FL_PROTYPE where rcid=a.rcid and fldm='000000003'),'') SXZY_FL," +
			"isnull((select CSZY from EXP_FL_PROTYPE where rcid=a.rcid and fldm='000000003'),'') CSZY_FL," +
			"isnull((select SXZY1 from EXP_FL_PROTYPE where rcid=a.rcid and fldm='000000003'),'') SXZY1_FL," +
			"isnull((select SXZY2 from EXP_FL_PROTYPE where rcid=a.rcid and fldm='000000003'),'') SXZY2_FL," +
			"isnull((select SXZY3 from EXP_FL_PROTYPE where rcid=a.rcid and fldm='000000003'),'') SXZY3_FL, " +
			
			"isnull((select SXLY from EXP_FL_PROTYPE where rcid=a.rcid and fldm='000000004'),'') SXLY_FL_4," +
			"isnull((select CSLY from EXP_FL_PROTYPE where rcid=a.rcid and fldm='000000004'),'') CSLY_FL_4," +
			"isnull((select SXLY1 from EXP_FL_PROTYPE where rcid=a.rcid and fldm='000000004'),'') SXLY1_FL_4," +
			"isnull((select SXLY2 from EXP_FL_PROTYPE where rcid=a.rcid and fldm='000000004'),'') SXLY2_FL_4," +
			"isnull((select SXLY3 from EXP_FL_PROTYPE where rcid=a.rcid and fldm='000000004'),'') SXLY3_FL_4, " +
			
			"isnull((select SXLYO from EXP_FL_PROTYPE where rcid=a.rcid and fldm='000000004'),'') SXLYO_FL_4," +
			"isnull((select CSLYO from EXP_FL_PROTYPE where rcid=a.rcid and fldm='000000004'),'') CSLYO_FL_4," +
			"isnull((select SXLY1O from EXP_FL_PROTYPE where rcid=a.rcid and fldm='000000004'),'') SXLY1O_FL_4," +
			"isnull((select SXLY2O from EXP_FL_PROTYPE where rcid=a.rcid and fldm='000000004'),'') SXLY2O_FL_4," +
			"isnull((select SXLY3O from EXP_FL_PROTYPE where rcid=a.rcid and fldm='000000004'),'') SXLY3O_FL_4 " +
			" from exp_Main_v a where a.rcid="+rcid+" ) cc ";
			m = super.queryForMap(sql_q);			
		}
		return m;
	}
	
	public Integer getExpTbbz(String rcid){
		String sql = "select count(*) from exp_main_zj where rcid="+rcid;
		if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = "select isnull(tbbz,0) from exp_main_zj where rcid="+rcid;
			return this.getSimpleJdbcTemplate().queryForInt(sql);
		}else{
			return 0;
		}
	}
	
	
	
	public String getExpFlList(String userid,String roledm,String rcid ){
		StringBuffer sb = new StringBuffer();
		String sql = " select fldm,flmc from exp_fl where fldm in (select userfl from XT_USER_FL where userid= "+userid+" ) order by fldm ";
		if(roledm.equals("01")){
			if(rcid != null && !rcid.equals("")){
				sql = " select fldm,flmc from exp_fl where fldm in ( select distinct fldm from exp_flmx where rcid="+rcid+") order by fldm  ";
			}
		}
		List<Map<String, Object>> list= this.getSimpleJdbcTemplate().queryForList(sql);
		for(int i=0;i<list.size();i++){
			String fldm = String.valueOf(list.get(i).get("fldm"));
			sb.append("<tr>" +
					"<td style=\"background:'#efefef'\" colspan=4><b><font color='#a52a2a'>"+(String.valueOf(list.get(i).get("flmc")))+"</font></b>" +
							"<input type='hidden' name='fldms' value='"+(String.valueOf(list.get(i).get("fldm")))+"'/>" +
							"</td></tr>");
			if(rcid != null && !rcid.equals("")){
				sql = " select count(*) from EXP_FL_PROTYPE where rcid="+rcid+" and fldm='"+(fldm)+"'";
				if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
					sql = " select isnull(sxly,'') sxly,isnull(csly,'') csly,isnull(sxly1,'') sxly1,isnull(sxly2,'') sxly2,isnull(sxly3,'') sxly3," +
							"isnull(sxzy,'') sxzy,isnull(cszy,'') cszy,isnull(sxzy1,'') sxzy1,isnull(sxzy2,'') sxzy2,isnull(sxzy3,'') sxzy3, " +
							" isnull((SELECT  DICTNAME FROM dbo.XT_DICT_FLDM AS b  WHERE (LBID = 14) AND (a.sxly = DICTBH) and fldm='"+fldm+"'),'') AS sxly_mc," +
							" isnull((SELECT  DICTNAME FROM dbo.XT_DICT_FLDM AS b  WHERE (LBID = 14) AND (a.csly = DICTBH) and fldm='"+fldm+"'),'') AS csly_mc," +
							"isnull((SELECT  DICTNAME FROM dbo.XT_DICT_FLDM AS b  WHERE (LBID = 14) AND (a.sxly1 = DICTBH) and fldm='"+fldm+"'),'') AS sxly1_mc," +
							"isnull((SELECT  DICTNAME FROM dbo.XT_DICT_FLDM AS b  WHERE (LBID = 14) AND (a.sxly2 = DICTBH) and fldm='"+fldm+"'),'') AS sxly2_mc," +
							"isnull((SELECT  DICTNAME FROM dbo.XT_DICT_FLDM AS b  WHERE (LBID = 14) AND (a.sxly3 = DICTBH) and fldm='"+fldm+"'),'') AS sxly3_mc," +
							
							" isnull((SELECT  DICTNAME FROM dbo.XT_DICT_FLDM AS b  WHERE (LBID = 14) AND (substring(a.sxly,1,3) = DICTBH) and fldm='"+fldm+"'),'') AS sxly_mc_," +
							" isnull((SELECT  DICTNAME FROM dbo.XT_DICT_FLDM AS b  WHERE (LBID = 14) AND (substring(a.csly,1,3) = DICTBH) and fldm='"+fldm+"'),'') AS csly_mc_," +
							"isnull((SELECT  DICTNAME FROM dbo.XT_DICT_FLDM AS b  WHERE (LBID = 14) AND  (substring(a.sxly1,1,3) = DICTBH) and fldm='"+fldm+"'),'') AS sxly1_mc_," +
							"isnull((SELECT  DICTNAME FROM dbo.XT_DICT_FLDM AS b  WHERE (LBID = 14) AND  (substring(a.sxly2,1,3) = DICTBH) and fldm='"+fldm+"'),'') AS sxly2_mc_," +
							"isnull((SELECT  DICTNAME FROM dbo.XT_DICT_FLDM AS b  WHERE (LBID = 14) AND  (substring(a.sxly3,1,3) = DICTBH) and fldm='"+fldm+"'),'') AS sxly3_mc_," +
							
							" isnull((SELECT  DICTNAME FROM dbo.XT_DICT_FLDM AS b  WHERE (LBID = 13) AND (a.sxzy = DICTBH) and fldm='"+fldm+"'),'') AS sxzy_mc," +
							" isnull((SELECT  DICTNAME FROM dbo.XT_DICT_FLDM AS b  WHERE (LBID = 13) AND (a.cszy = DICTBH) and fldm='"+fldm+"'),'') AS cszy_mc," +
							"isnull((SELECT  DICTNAME FROM dbo.XT_DICT_FLDM AS b  WHERE (LBID = 13) AND (a.sxzy1 = DICTBH) and fldm='"+fldm+"'),'') AS sxzy1_mc," +
							"isnull((SELECT  DICTNAME FROM dbo.XT_DICT_FLDM AS b  WHERE (LBID = 13) AND (a.sxzy2 = DICTBH) and fldm='"+fldm+"'),'') AS sxzy2_mc," +
							"isnull((SELECT  DICTNAME FROM dbo.XT_DICT_FLDM AS b  WHERE (LBID = 13) AND (a.sxzy3 = DICTBH) and fldm='"+fldm+"'),'') AS sxzy3_mc," +
							"isnull(substring(a.sxly,4,3),'') sxly_,isnull(substring(a.csly,4,3),'') csly_," +
							"isnull(substring(a.sxly1,4,3),'') sxly1_,isnull(substring(a.sxly2,4,3),'') sxly2_," +
							"isnull(substring(a.sxly3,4,3),'') sxly3_ " +
							",isnull(sxlyo,'') sxlyo,isnull(cslyo,'') cslyo,isnull(sxly1o,'') sxly1o,isnull(sxly2o,'') sxly2o,isnull(sxly3o,'') sxly3o " +
							" from EXP_FL_PROTYPE a where rcid="+rcid+" and fldm='"+(fldm)+"'";
					Map<String,Object> flobj = this.getSimpleJdbcTemplate().queryForMap(sql); 
					
					sql = " select count(*) from xt_dlb_fldm where fldm='"+fldm+"' and lbid=13";
					if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
						sb.append("<tr>");
						sb.append("<td class=\"bt\">所学专业</td>");
						sb.append("<td>");
						sb.append("<INPUT id='expfl.sxzy_"+fldm+"_button' class=selectBut2  title=所学专业 value='"+String.valueOf(flobj.get("sxzy_mc"))+"' type=button  onclick=\"O_D('expfl.sxzy_"+fldm+"_button','xtdlbfl13','center');setFl13WithTreeID('expfl.sxzy_"+fldm+"');loadFlZyTree(13,'"+fldm+"');\">");
						sb.append("<input type='hidden' name='sxzy' id='expfl.sxzy_"+fldm+"' value='"+String.valueOf(flobj.get("sxzy"))+"'>");
						sb.append("</td>");
						sb.append("<td class=\"bt\">从事专业</td>");
						sb.append("<td>");
						sb.append("<INPUT id='expfl.cszy_"+fldm+"_button' class=selectBut2  title=从事专业 value='"+String.valueOf(flobj.get("cszy_mc"))+"' type=button  onclick=\"O_D('expfl.cszy_"+fldm+"_button','xtdlbfl13','center');setFl13WithTreeID('expfl.cszy_"+fldm+"');loadFlZyTree(13,'"+fldm+"');\">");
						sb.append("<input type='hidden' name='cszy' id='expfl.cszy_"+fldm+"' value='"+String.valueOf(flobj.get("cszy"))+"'>");
						sb.append("</td>");
						sb.append("</tr>");
						sb.append("<tr>");
						sb.append("<td class=\"bt\">熟悉专业</td>");
						sb.append("<td colspan=3>");
						sb.append("<INPUT id='expfl.sxzy1_"+fldm+"_button' class=selectBut2  title=熟悉专业1 value='"+String.valueOf(flobj.get("sxzy1_mc"))+"' type=button  onclick=\"O_D('expfl.sxzy1_"+fldm+"_button','xtdlbfl13','center');setFl13WithTreeID('expfl.sxzy1_"+fldm+"');loadFlZyTree(13,'"+fldm+"');\">");
						sb.append("<input type='hidden' name='sxzy1' id='expfl.sxzy1_"+fldm+"' value='"+String.valueOf(flobj.get("sxzy1"))+"'>&nbsp");
						sb.append("<INPUT id='expfl.sxzy2_"+fldm+"_button' class=selectBut2  title=熟悉专业2 value='"+String.valueOf(flobj.get("sxzy2_mc"))+"' type=button  onclick=\"O_D('expfl.sxzy2_"+fldm+"_button','xtdlbfl13','center');setFl13WithTreeID('expfl.sxzy2_"+fldm+"');loadFlZyTree(13,'"+fldm+"');\">");
						sb.append("<input type='hidden' name='sxzy2' id='expfl.sxzy2_"+fldm+"' value='"+String.valueOf(flobj.get("sxzy2"))+"'>&nbsp");
						sb.append("<INPUT id='expfl.sxzy3_"+fldm+"_button' class=selectBut2  title=熟悉专业3 value='"+String.valueOf(flobj.get("sxzy3_mc"))+"' type=button  onclick=\"O_D('expfl.sxzy3_"+fldm+"_button','xtdlbfl13','center');setFl13WithTreeID('expfl.sxzy3_"+fldm+"');loadFlZyTree(13,'"+fldm+"');\">");
						sb.append("<input type='hidden' name='sxzy3' id='expfl.sxzy3_"+fldm+"' value='"+String.valueOf(flobj.get("sxzy3"))+"'>");
						sb.append("</td>");
						sb.append("</tr>");
					}else{
						sb.append("<input type='hidden' name='sxzy' value=''/>" +
								"<input type='hidden' name='cszy' value=''/>" +
								"<input type='hidden' name='sxzy1' value=''/>" +
								"<input type='hidden' name='sxzy2' value=''/>" +
								"<input type='hidden' name='sxzy3' value=''/>");
					}
					
					
					sql = " select count(*) from xt_dlb_fldm where fldm='"+fldm+"' and lbid=14";
					if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
						if(fldm.equals("000000004")){ 
							/////////////////////////////////////
							//   COMPOMENT:     海归领域特殊需求
							//   DATE:			2011-06-11
							///////////////////////////////////
							sb.append("<tr>");
							sb.append("<td class=\"bt\">所学领域</td>");
							sb.append("<td colspan=3>");
							sb.append("<INPUT id='expfl.sxly_"+fldm+"_button_' class=selectBut2  title=所学领域 value='选择领域' type=button  onclick=\"O_D('expfl.sxly_"+fldm+"_button_','xtdlbfl14','center');setFl14WithTreeID('expfl.sxly_"+fldm+"');loadFlLyTree(14,'"+fldm+"');\">");
							sb.append("<input type='hidden' name='sxly' id='expfl.sxly_"+fldm+"' value='"+String.valueOf(flobj.get("sxly"))+"'>");
							sb.append("<div id='expfl.sxly_"+fldm+"_button'>");
							sb.append(String.valueOf(flobj.get("sxly_mc_"))+"-"+String.valueOf(flobj.get("sxly_mc")));
							if(String.valueOf(flobj.get("sxly_")) != null && String.valueOf(flobj.get("sxly_")).equals("999")){
								sb.append("<input type=text name='exp.sxlyo' value='"+String.valueOf(flobj.get("sxlyo"))+"'>");
							}else{
								sb.append("<input type=hidden name='exp.sxlyo'> ");
							}
							sb.append("</div>");
							sb.append("</td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td class=\"bt\">从事领域</td>");
							sb.append("<td colspan=3>");
							sb.append("<INPUT id='expfl.csly_"+fldm+"_button_' class=selectBut2  title=从事领域 value='选择领域' type=button  onclick=\"O_D('expfl.csly_"+fldm+"_button_','xtdlbfl14','center');setFl14WithTreeID('expfl.csly_"+fldm+"');loadFlLyTree(14,'"+fldm+"');\">");
							sb.append("<input type='hidden' name='csly' id='expfl.csly_"+fldm+"' value='"+String.valueOf(flobj.get("csly"))+"'>");
							sb.append("<div id='expfl.csly_"+fldm+"_button'>");
							sb.append(String.valueOf(flobj.get("csly_mc_"))+"-"+String.valueOf(flobj.get("csly_mc")));
							if(String.valueOf(flobj.get("csly_")) != null && String.valueOf(flobj.get("csly_")).equals("999")){
								sb.append("<input type=text name='exp.cslyo' value='"+String.valueOf(flobj.get("cslyo"))+"'>");
							}else{
								sb.append("<input type=hidden name='exp.cslyo'> ");
							}
							sb.append("</div>");
							
							sb.append("</td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td class=\"bt\">熟悉领域一</td>");
							sb.append("<td colspan=3>");
							sb.append("<INPUT id='expfl.sxly1_"+fldm+"_button_' class=selectBut2  title=熟悉领域1 value='选择领域' type=button  onclick=\"O_D('expfl.sxly1_"+fldm+"_button_','xtdlbfl14','center');setFl14WithTreeID('expfl.sxly1_"+fldm+"');loadFlLyTree(14,'"+fldm+"');\">");
							sb.append("<input type='hidden' name='sxly1' id='expfl.sxly1_"+fldm+"' value='"+String.valueOf(flobj.get("sxly1"))+"'>&nbsp");
							sb.append("<div id='expfl.sxly1_"+fldm+"_button'>");
							sb.append(String.valueOf(flobj.get("sxly1_mc_"))+"-"+String.valueOf(flobj.get("sxly1_mc")));
							if(String.valueOf(flobj.get("sxly_")) != null && String.valueOf(flobj.get("sxly1_")).equals("999")){
								sb.append("<input type=text name='exp.sxly1o' value='"+String.valueOf(flobj.get("sxly1o"))+"'>");
							}else{
								sb.append("<input type=hidden name='exp.sxly1o'> ");
							}
							sb.append("</div>");
							
							sb.append("</td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td class=\"bt\">熟悉领域二</td>");
							sb.append("<td colspan=3>");
							sb.append("<INPUT id='expfl.sxly2_"+fldm+"_button_' class=selectBut2  title=熟悉领域2 value='选择领域' type=button  onclick=\"O_D('expfl.sxly2_"+fldm+"_button_','xtdlbfl14','center');setFl14WithTreeID('expfl.sxly2_"+fldm+"');loadFlLyTree(14,'"+fldm+"');\">");
							sb.append("<input type='hidden' name='sxly2' id='expfl.sxly2_"+fldm+"' value='"+String.valueOf(flobj.get("sxly2"))+"'>&nbsp");
							sb.append("<div id='expfl.sxly2_"+fldm+"_button'>");
							sb.append(String.valueOf(flobj.get("sxly2_mc_"))+"-"+String.valueOf(flobj.get("sxly2_mc")));
							if(String.valueOf(flobj.get("sxly2_")) != null && String.valueOf(flobj.get("sxly2_")).equals("999")){
								sb.append("<input type=text name='exp.sxly2o' value='"+String.valueOf(flobj.get("sxly2o"))+"'>");
							}else{
								sb.append("<input type=hidden name='exp.sxly2o'> ");
							}
							sb.append("</div>");
							
							sb.append("</td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td class=\"bt\">熟悉领域三</td>");
							sb.append("<td colspan=3>");
							sb.append("<INPUT id='expfl.sxly3_"+fldm+"_button_' class=selectBut2  title=熟悉领域3 value='选择领域' type=button  onclick=\"O_D('expfl.sxly3_"+fldm+"_button_','xtdlbfl14','center');setFl14WithTreeID('expfl.sxly3_"+fldm+"');loadFlLyTree(14,'"+fldm+"');\">");
							sb.append("<input type='hidden' name='sxly3' id='expfl.sxly3_"+fldm+"' value='"+String.valueOf(flobj.get("sxly3"))+"'>");
							sb.append("<div id='expfl.sxly3_"+fldm+"_button'>");
							sb.append(String.valueOf(flobj.get("sxly3_mc_"))+"-"+String.valueOf(flobj.get("sxly3_mc")));
							if(String.valueOf(flobj.get("sxly3_")) != null && String.valueOf(flobj.get("sxly3_")).equals("999")){
								sb.append("<input type=text name='exp.sxly3o' value='"+String.valueOf(flobj.get("sxly3o"))+"'>");
							}else{
								sb.append("<input type=hidden name='exp.sxly3o'> ");
							}
							sb.append("</div>");
							
							sb.append("</td>");
							sb.append("</tr>");
						}else{
							sb.append("<tr>");
							sb.append("<td class=\"bt\">所学领域</td>");
							sb.append("<td>");
							sb.append("<INPUT id='expfl.sxly_"+fldm+"_button' class=selectBut2  title=所学领域 value='"+String.valueOf(flobj.get("sxly_mc"))+"' type=button  onclick=\"O_D('expfl.sxly_"+fldm+"_button','xtdlbfl14','center');setFl14WithTreeID('expfl.sxly_"+fldm+"');loadFlLyTree(14,'"+fldm+"');\">");
							sb.append("<input type='hidden' name='sxly' id='expfl.sxly_"+fldm+"' value='"+String.valueOf(flobj.get("sxly"))+"'>");
							sb.append("</td>");
							sb.append("<td class=\"bt\">从事领域</td>");
							sb.append("<td>");
							sb.append("<INPUT id='expfl.csly_"+fldm+"_button' class=selectBut2  title=从事领域 value='"+String.valueOf(flobj.get("csly_mc"))+"' type=button  onclick=\"O_D('expfl.csly_"+fldm+"_button','xtdlbfl14','center');setFl14WithTreeID('expfl.csly_"+fldm+"');loadFlLyTree(14,'"+fldm+"');\">");
							sb.append("<input type='hidden' name='csly' id='expfl.csly_"+fldm+"' value='"+String.valueOf(flobj.get("csly"))+"'>");
							sb.append("</td>");
							sb.append("</tr>");
							sb.append("<tr>");
							sb.append("<td class=\"bt\">熟悉领域</td>");
							sb.append("<td colspan=3>");
							sb.append("<INPUT id='expfl.sxly1_"+fldm+"_button' class=selectBut2  title=熟悉领域1 value='"+String.valueOf(flobj.get("sxly1_mc"))+"' type=button  onclick=\"O_D('expfl.sxly1_"+fldm+"_button','xtdlbfl14','center');setFl14WithTreeID('expfl.sxly1_"+fldm+"');loadFlLyTree(14,'"+fldm+"');\">");
							sb.append("<input type='hidden' name='sxly1' id='expfl.sxly1_"+fldm+"' value='"+String.valueOf(flobj.get("sxly1"))+"'>&nbsp");
							sb.append("<INPUT id='expfl.sxly2_"+fldm+"_button' class=selectBut2  title=熟悉领域2 value='"+String.valueOf(flobj.get("sxly2_mc"))+"' type=button  onclick=\"O_D('expfl.sxly2_"+fldm+"_button','xtdlbfl14','center');setFl14WithTreeID('expfl.sxly2_"+fldm+"');loadFlLyTree(14,'"+fldm+"');\">");
							sb.append("<input type='hidden' name='sxly2' id='expfl.sxly2_"+fldm+"' value='"+String.valueOf(flobj.get("sxly2"))+"'>&nbsp");
							sb.append("<INPUT id='expfl.sxly3_"+fldm+"_button' class=selectBut2  title=熟悉领域3 value='"+String.valueOf(flobj.get("sxly3_mc"))+"' type=button  onclick=\"O_D('expfl.sxly3_"+fldm+"_button','xtdlbfl14','center');setFl14WithTreeID('expfl.sxly3_"+fldm+"');loadFlLyTree(14,'"+fldm+"');\">");
							sb.append("<input type='hidden' name='sxly3' id='expfl.sxly3_"+fldm+"' value='"+String.valueOf(flobj.get("sxly3"))+"'>");
							sb.append("</td>");
							sb.append("</tr>");
						}
						
					}else{
						sb.append("<input type='hidden' name='sxly' value=''/>" +
								"<input type='hidden' name='csly' value=''/>" +
								"<input type='hidden' name='sxly1' value=''/>" +
								"<input type='hidden' name='sxly2' value=''/>" +
								"<input type='hidden' name='sxly3' value=''/>");
					}
					
				}else{
					sql = " select count(*) from xt_dlb_fldm where fldm='"+fldm+"' and lbid=13";
					if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
						sb.append("<tr>");
						sb.append("<td class=\"bt\">所学专业</td>");
						sb.append("<td>");
						sb.append("<INPUT id='expfl.sxzy_"+fldm+"_button' class=selectBut2  title=所学专业 value='' type=button  onclick=\"O_D('expfl.sxzy_"+fldm+"_button','xtdlbfl13','center');setFl13WithTreeID('expfl.sxzy_"+fldm+"');loadFlZyTree(13,'"+fldm+"');\">");
						sb.append("<input type='hidden' name='sxzy' id='expfl.sxzy_"+fldm+"' value=''>");
						sb.append("</td>");
						sb.append("<td class=\"bt\">从事专业</td>");
						sb.append("<td>");
						sb.append("<INPUT id='expfl.cszy_"+fldm+"_button' class=selectBut2  title=从事专业 value='' type=button  onclick=\"O_D('expfl.cszy_"+fldm+"_button','xtdlbfl13','center');setFl13WithTreeID('expfl.cszy_"+fldm+"');loadFlZyTree(13,'"+fldm+"');\">");
						sb.append("<input type='hidden' name='cszy' id='expfl.cszy_"+fldm+"' value=''>");
						sb.append("</td>");
						sb.append("</tr>");
						sb.append("<tr>");
						sb.append("<td class=\"bt\">熟悉专业</td>");
						sb.append("<td colspan=3>");
						sb.append("<INPUT id='expfl.sxzy1_"+fldm+"_button' class=selectBut2  title=熟悉专业1 value='' type=button  onclick=\"O_D('expfl.sxzy1_"+fldm+"_button','xtdlbfl13','center');setFl13WithTreeID('expfl.sxzy1_"+fldm+"');loadFlZyTree(13,'"+fldm+"');\">");
						sb.append("<input type='hidden' name='sxzy1' id='expfl.sxzy1_"+fldm+"' value=''>&nbsp");
						sb.append("<INPUT id='expfl.sxzy2_"+fldm+"_button' class=selectBut2  title=熟悉专业2 value='' type=button  onclick=\"O_D('expfl.sxzy2_"+fldm+"_button','xtdlbfl13','center');setFl13WithTreeID('expfl.sxzy2_"+fldm+"');loadFlZyTree(13,'"+fldm+"');\">");
						sb.append("<input type='hidden' name='sxzy2' id='expfl.sxzy2_"+fldm+"' value=''>&nbsp");
						sb.append("<INPUT id='expfl.sxzy3_"+fldm+"_button' class=selectBut2  title=熟悉专业3 value='' type=button  onclick=\"O_D('expfl.sxzy3_"+fldm+"_button','xtdlbfl13','center');setFl13WithTreeID('expfl.sxzy3_"+fldm+"');loadFlZyTree(13,'"+fldm+"');\">");
						sb.append("<input type='hidden' name='sxzy3' id='expfl.sxzy3_"+fldm+"' value=''>");
						sb.append("</td>");
						sb.append("</tr>");
					}else{
						sb.append("<input type='hidden' name='sxzy' value=''/>" +
								"<input type='hidden' name='cszy' value=''/>" +
								"<input type='hidden' name='sxzy1' value=''/>" +
								"<input type='hidden' name='sxzy2' value=''/>" +
								"<input type='hidden' name='sxzy3' value=''/>");
					}
					
					sql = " select count(*) from xt_dlb_fldm where fldm='"+fldm+"' and lbid=14";
					if(this.getSimpleJdbcTemplate().queryForInt(sql) >0){
						if(fldm.equals("000000004")){ 
							/////////////////////////////////////
							//   COMPOMENT:     海归领域特殊需求
							//   DATE:			2011-06-11
							///////////////////////////////////
							sb.append("<tr>");
							sb.append("<td class=\"bt\">所学领域</td>");
							sb.append("<td colspan=3>");
							sb.append("<INPUT id='expfl.sxly_"+fldm+"_button_' class=selectBut2  title=所学领域 value='选择领域' type=button  onclick=\"O_D('expfl.sxly_"+fldm+"_button_','xtdlbfl14','center');setFl14WithTreeID('expfl.sxly_"+fldm+"');loadFlLyTree(14,'"+fldm+"');\">");
							sb.append("<input type='hidden' name='sxly' id='expfl.sxly_"+fldm+"' value=''>");
							sb.append("<div id='expfl.sxly_"+fldm+"_button'>");
							sb.append("<input type=hidden name='exp.sxlyo'> ");
							sb.append("</div>");
							sb.append("</td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td class=\"bt\">从事领域</td>");
							sb.append("<td colspan=3>");
							sb.append("<INPUT id='expfl.csly_"+fldm+"_button_' class=selectBut2  title=从事领域 value='选择领域' type=button  onclick=\"O_D('expfl.csly_"+fldm+"_button_','xtdlbfl14','center');setFl14WithTreeID('expfl.csly_"+fldm+"');loadFlLyTree(14,'"+fldm+"');\">");
							sb.append("<input type='hidden' name='csly' id='expfl.csly_"+fldm+"' value=''>");
							sb.append("<div id='expfl.csly_"+fldm+"_button'>");
							sb.append("<input type=hidden name='exp.cslyo'> ");
							sb.append("</div>");
							
							sb.append("</td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td class=\"bt\">熟悉领域一</td>");
							sb.append("<td colspan=3>");
							sb.append("<INPUT id='expfl.sxly1_"+fldm+"_button_' class=selectBut2  title=熟悉领域1 value='选择领域' type=button  onclick=\"O_D('expfl.sxly1_"+fldm+"_button_','xtdlbfl14','center');setFl14WithTreeID('expfl.sxly1_"+fldm+"');loadFlLyTree(14,'"+fldm+"');\">");
							sb.append("<input type='hidden' name='sxly1' id='expfl.sxly1_"+fldm+"' value=''>&nbsp");
							sb.append("<div id='expfl.sxly1_"+fldm+"_button'>");
							sb.append("<input type=hidden name='exp.sxly1o'> ");
							sb.append("</div>");
							
							sb.append("</td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td class=\"bt\">熟悉领域二</td>");
							sb.append("<td colspan=3>");
							sb.append("<INPUT id='expfl.sxly2_"+fldm+"_button_' class=selectBut2  title=熟悉领域2 value='选择领域' type=button  onclick=\"O_D('expfl.sxly2_"+fldm+"_button_','xtdlbfl14','center');setFl14WithTreeID('expfl.sxly2_"+fldm+"');loadFlLyTree(14,'"+fldm+"');\">");
							sb.append("<input type='hidden' name='sxly2' id='expfl.sxly2_"+fldm+"' value=''>&nbsp");
							sb.append("<div id='expfl.sxly2_"+fldm+"_button'>");
							sb.append("<input type=hidden name='exp.sxly2o'> ");
							sb.append("</div>");
							
							sb.append("</td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td class=\"bt\">熟悉领域三</td>");
							sb.append("<td colspan=3>");
							sb.append("<INPUT id='expfl.sxly3_"+fldm+"_button_' class=selectBut2  title=熟悉领域3 value='选择领域' type=button  onclick=\"O_D('expfl.sxly3_"+fldm+"_button_','xtdlbfl14','center');setFl14WithTreeID('expfl.sxly3_"+fldm+"');loadFlLyTree(14,'"+fldm+"');\">");
							sb.append("<input type='hidden' name='sxly3' id='expfl.sxly3_"+fldm+"' value=''>");
							sb.append("<div id='expfl.sxly3_"+fldm+"_button'>");
							sb.append("<input type=hidden name='exp.sxly3o'> ");
							sb.append("</div>");
							
							sb.append("</td>");
							sb.append("</tr>");
						}else{
							sb.append("<tr>");
							sb.append("<td class=\"bt\">所学领域</td>");
							sb.append("<td>");
							sb.append("<INPUT id='expfl.sxly_"+fldm+"_button' class=selectBut2  title=所学领域 value='' type=button  onclick=\"O_D('expfl.sxly_"+fldm+"_button','xtdlbfl14','center');setFl14WithTreeID('expfl.sxly_"+fldm+"');loadFlLyTree(14,'"+fldm+"');\">");
							sb.append("<input type='hidden' name='sxly' id='expfl.sxly_"+fldm+"' value=''>");
							sb.append("</td>");
							sb.append("<td class=\"bt\">从事领域</td>");
							sb.append("<td>");
							sb.append("<INPUT id='expfl.csly_"+fldm+"_button' class=selectBut2  title=从事领域 value='' type=button  onclick=\"O_D('expfl.csly_"+fldm+"_button','xtdlbfl14','center');setFl14WithTreeID('expfl.csly_"+fldm+"');loadFlLyTree(14,'"+fldm+"');\">");
							sb.append("<input type='hidden' name='csly' id='expfl.csly_"+fldm+"' value=''>");
							sb.append("</td>");
							sb.append("</tr>");
							sb.append("<tr>");
							sb.append("<td class=\"bt\">熟悉领域</td>");
							sb.append("<td colspan=3>");
							sb.append("<INPUT id='expfl.sxly1_"+fldm+"_button' class=selectBut2  title=熟悉领域1 value='' type=button  onclick=\"O_D('expfl.sxly1_"+fldm+"_button','xtdlbfl14','center');setFl14WithTreeID('expfl.sxly1_"+fldm+"');loadFlLyTree(14,'"+fldm+"');\">");
							sb.append("<input type='hidden' name='sxly1' id='expfl.sxly1_"+fldm+"' value=''>&nbsp");
							sb.append("<INPUT id='expfl.sxly2_"+fldm+"_button' class=selectBut2  title=熟悉领域2 value='' type=button  onclick=\"O_D('expfl.sxly2_"+fldm+"_button','xtdlbfl14','center');setFl14WithTreeID('expfl.sxly2_"+fldm+"');loadFlLyTree(14,'"+fldm+"');\">");
							sb.append("<input type='hidden' name='sxly2' id='expfl.sxly2_"+fldm+"' value=''>&nbsp");
							sb.append("<INPUT id='expfl.sxly3_"+fldm+"_button' class=selectBut2  title=熟悉领域3 value='' type=button  onclick=\"O_D('expfl.sxly3_"+fldm+"_button','xtdlbfl14','center');setFl14WithTreeID('expfl.sxly3_"+fldm+"');loadFlLyTree(14,'"+fldm+"');\">");
							sb.append("<input type='hidden' name='sxly3' id='expfl.sxly3_"+fldm+"' value=''>");
							sb.append("</td>");
							sb.append("</tr>");
						}
						
					}else{
						sb.append("<input type='hidden' name='sxly' value=''/>" +
								"<input type='hidden' name='csly' value=''/>" +
								"<input type='hidden' name='sxly1' value=''/>" +
								"<input type='hidden' name='sxly2' value=''/>" +
								"<input type='hidden' name='sxly3' value=''/>");
					}
					
				}
			}else{
				
				sql = " select count(*) from xt_dlb_fldm where fldm='"+fldm+"' and lbid=13";
				if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
					sb.append("<tr>");
					sb.append("<td class=\"bt\">所学专业</td>");
					sb.append("<td>");
					sb.append("<INPUT id='expfl.sxzy_"+fldm+"_button' class=selectBut2  title=所学专业 value='' type=button  onclick=\"O_D('expfl.sxzy_"+fldm+"_button','xtdlbfl13','center');setFl13WithTreeID('expfl.sxzy_"+fldm+"');loadFlZyTree(13,'"+fldm+"');\">");
					sb.append("<input type='hidden' name='sxzy' id='expfl.sxzy_"+fldm+"' value=''>");
					sb.append("</td>");
					sb.append("<td class=\"bt\">从事专业</td>");
					sb.append("<td>");
					sb.append("<INPUT id='expfl.cszy_"+fldm+"_button' class=selectBut2  title=从事专业 value='' type=button  onclick=\"O_D('expfl.cszy_"+fldm+"_button','xtdlbfl13','center');setFl13WithTreeID('expfl.cszy_"+fldm+"');loadFlZyTree(13,'"+fldm+"');\">");
					sb.append("<input type='hidden' name='cszy' id='expfl.cszy_"+fldm+"' value=''>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td class=\"bt\">熟悉专业</td>");
					sb.append("<td colspan=3>");
					sb.append("<INPUT id='expfl.sxzy1_"+fldm+"_button' class=selectBut2  title=熟悉专业1 value='' type=button  onclick=\"O_D('expfl.sxzy1_"+fldm+"_button','xtdlbfl13','center');setFl13WithTreeID('expfl.sxzy1_"+fldm+"');loadFlZyTree(13,'"+fldm+"');\">");
					sb.append("<input type='hidden' name='sxzy1' id='expfl.sxzy1_"+fldm+"' value=''>&nbsp");
					sb.append("<INPUT id='expfl.sxzy2_"+fldm+"_button' class=selectBut2  title=熟悉专业2 value='' type=button  onclick=\"O_D('expfl.sxzy2_"+fldm+"_button','xtdlbfl13','center');setFl13WithTreeID('expfl.sxzy2_"+fldm+"');loadFlZyTree(13,'"+fldm+"');\">");
					sb.append("<input type='hidden' name='sxzy2' id='expfl.sxzy2_"+fldm+"' value=''>&nbsp");
					sb.append("<INPUT id='expfl.sxzy3_"+fldm+"_button' class=selectBut2  title=熟悉专业3 value='' type=button  onclick=\"O_D('expfl.sxzy3_"+fldm+"_button','xtdlbfl13','center');setFl13WithTreeID('expfl.sxzy3_"+fldm+"');loadFlZyTree(13,'"+fldm+"');\">");
					sb.append("<input type='hidden' name='sxzy3' id='expfl.sxzy3_"+fldm+"' value=''>");
					sb.append("</td>");
					sb.append("</tr>");
				}else{
					sb.append("<input type='hidden' name='sxzy' value=''/>" +
							"<input type='hidden' name='cszy' value=''/>" +
							"<input type='hidden' name='sxzy1' value=''/>" +
							"<input type='hidden' name='sxzy2' value=''/>" +
							"<input type='hidden' name='sxzy3' value=''/>");
				}
				
				sql = " select count(*) from xt_dlb_fldm where fldm='"+fldm+"' and lbid=14";
				if(this.getSimpleJdbcTemplate().queryForInt(sql) >0){
					if(fldm.equals("000000004")){ 
						/////////////////////////////////////
						//   COMPOMENT:     海归领域特殊需求
						//   DATE:			2011-06-11
						///////////////////////////////////
						sb.append("<tr>");
						sb.append("<td class=\"bt\">所学领域</td>");
						sb.append("<td colspan=3>");
						sb.append("<INPUT id='expfl.sxly_"+fldm+"_button_' class=selectBut2  title=所学领域 value='选择领域' type=button  onclick=\"O_D('expfl.sxly_"+fldm+"_button_','xtdlbfl14','center');setFl14WithTreeID('expfl.sxly_"+fldm+"');loadFlLyTree(14,'"+fldm+"');\">");
						sb.append("<input type='hidden' name='sxly' id='expfl.sxly_"+fldm+"' value=''>");
						sb.append("<div id='expfl.sxly_"+fldm+"_button'>");
						sb.append("<input type=hidden name='exp.sxlyo'> ");
						sb.append("</div>");
						sb.append("</td>");
						sb.append("</tr>");
						
						sb.append("<tr>");
						sb.append("<td class=\"bt\">从事领域</td>");
						sb.append("<td colspan=3>");
						sb.append("<INPUT id='expfl.csly_"+fldm+"_button_' class=selectBut2  title=从事领域 value='选择领域' type=button  onclick=\"O_D('expfl.csly_"+fldm+"_button_','xtdlbfl14','center');setFl14WithTreeID('expfl.csly_"+fldm+"');loadFlLyTree(14,'"+fldm+"');\">");
						sb.append("<input type='hidden' name='csly' id='expfl.csly_"+fldm+"' value=''>");
						sb.append("<div id='expfl.csly_"+fldm+"_button'>");
						sb.append("<input type=hidden name='exp.cslyo'> ");
						sb.append("</div>");
						
						sb.append("</td>");
						sb.append("</tr>");
						
						sb.append("<tr>");
						sb.append("<td class=\"bt\">熟悉领域一</td>");
						sb.append("<td colspan=3>");
						sb.append("<INPUT id='expfl.sxly1_"+fldm+"_button_' class=selectBut2  title=熟悉领域1 value='选择领域' type=button  onclick=\"O_D('expfl.sxly1_"+fldm+"_button_','xtdlbfl14','center');setFl14WithTreeID('expfl.sxly1_"+fldm+"');loadFlLyTree(14,'"+fldm+"');\">");
						sb.append("<input type='hidden' name='sxly1' id='expfl.sxly1_"+fldm+"' value=''>&nbsp");
						sb.append("<div id='expfl.sxly1_"+fldm+"_button'>");
						sb.append("<input type=hidden name='exp.sxly1o'> ");
						sb.append("</div>");
						
						sb.append("</td>");
						sb.append("</tr>");
						
						sb.append("<tr>");
						sb.append("<td class=\"bt\">熟悉领域二</td>");
						sb.append("<td colspan=3>");
						sb.append("<INPUT id='expfl.sxly2_"+fldm+"_button_' class=selectBut2  title=熟悉领域2 value='选择领域' type=button  onclick=\"O_D('expfl.sxly2_"+fldm+"_button_','xtdlbfl14','center');setFl14WithTreeID('expfl.sxly2_"+fldm+"');loadFlLyTree(14,'"+fldm+"');\">");
						sb.append("<input type='hidden' name='sxly2' id='expfl.sxly2_"+fldm+"' value=''>&nbsp");
						sb.append("<div id='expfl.sxly2_"+fldm+"_button'>");
						sb.append("<input type=hidden name='exp.sxly2o'> ");
						sb.append("</div>");
						
						sb.append("</td>");
						sb.append("</tr>");
						
						sb.append("<tr>");
						sb.append("<td class=\"bt\">熟悉领域三</td>");
						sb.append("<td colspan=3>");
						sb.append("<INPUT id='expfl.sxly3_"+fldm+"_button_' class=selectBut2  title=熟悉领域3 value='选择领域' type=button  onclick=\"O_D('expfl.sxly3_"+fldm+"_button_','xtdlbfl14','center');setFl14WithTreeID('expfl.sxly3_"+fldm+"');loadFlLyTree(14,'"+fldm+"');\">");
						sb.append("<input type='hidden' name='sxly3' id='expfl.sxly3_"+fldm+"' value=''>");
						sb.append("<div id='expfl.sxly3_"+fldm+"_button'>");
						sb.append("<input type=hidden name='exp.sxly3o'> ");
						sb.append("</div>");
						
						sb.append("</td>");
						sb.append("</tr>");
					}else{

						sb.append("<tr>");
						sb.append("<td class=\"bt\">所学领域</td>");
						sb.append("<td>");
						sb.append("<INPUT id='expfl.sxly_"+fldm+"_button' class=selectBut2  title=所学领域 value='' type=button  onclick=\"O_D('expfl.sxly_"+fldm+"_button','xtdlbfl14','center');setFl14WithTreeID('expfl.sxly_"+fldm+"');loadFlLyTree(14,'"+fldm+"');\">");
						sb.append("<input type='hidden' name='sxly' id='expfl.sxly_"+fldm+"' value=''>");
						sb.append("</td>");
						sb.append("<td class=\"bt\">从事领域</td>");
						sb.append("<td>");
						sb.append("<INPUT id='expfl.csly_"+fldm+"_button' class=selectBut2  title=从事领域 value='' type=button  onclick=\"O_D('expfl.csly_"+fldm+"_button','xtdlbfl14','center');setFl14WithTreeID('expfl.csly_"+fldm+"');loadFlLyTree(14,'"+fldm+"');\">");
						sb.append("<input type='hidden' name='csly' id='expfl.csly_"+fldm+"' value=''>");
						sb.append("</td>");
						sb.append("</tr>");
						sb.append("<tr>");
						sb.append("<td class=\"bt\">熟悉领域</td>");
						sb.append("<td colspan=3>");
						sb.append("<INPUT id='expfl.sxly1_"+fldm+"_button' class=selectBut2  title=熟悉领域1 value='' type=button  onclick=\"O_D('expfl.sxly1_"+fldm+"_button','xtdlbfl14','center');setFl14WithTreeID('expfl.sxly1_"+fldm+"');loadFlLyTree(14,'"+fldm+"');\">");
						sb.append("<input type='hidden' name='sxly1' id='expfl.sxly1_"+fldm+"' value=''>&nbsp");
						sb.append("<INPUT id='expfl.sxly2_"+fldm+"_button' class=selectBut2  title=熟悉领域2 value='' type=button  onclick=\"O_D('expfl.sxly2_"+fldm+"_button','xtdlbfl14','center');setFl14WithTreeID('expfl.sxly2_"+fldm+"');loadFlLyTree(14,'"+fldm+"');\">");
						sb.append("<input type='hidden' name='sxly2' id='expfl.sxly2_"+fldm+"' value=''>&nbsp");
						sb.append("<INPUT id='expfl.sxly3_"+fldm+"_button' class=selectBut2  title=熟悉领域3 value='' type=button  onclick=\"O_D('expfl.sxly3_"+fldm+"_button','xtdlbfl14','center');setFl14WithTreeID('expfl.sxly3_"+fldm+"');loadFlLyTree(14,'"+fldm+"');\">");
						sb.append("<input type='hidden' name='sxly3' id='expfl.sxly3_"+fldm+"' value=''>");
						sb.append("</td>");
						sb.append("</tr>");
					}
						
				}else{
					sb.append("<input type='hidden' name='sxly' value=''/>" +
							"<input type='hidden' name='csly' value=''/>" +
							"<input type='hidden' name='sxly1' value=''/>" +
							"<input type='hidden' name='sxly2' value=''/>" +
							"<input type='hidden' name='sxly3' value=''/>");
				}
				
			}
		}
		return sb.toString();
	}
	
	

	
	public String getExpFlListView(String userid,String roledm,String rcid ){
		StringBuffer sb = new StringBuffer();
		String sql = " select fldm,flmc from exp_fl where fldm in (select userfl from XT_USER_FL where userid= "+userid+" ) order by fldm ";
		if(roledm.equals("01")){
			if(rcid != null && !rcid.equals("")){
				sql = " select fldm,flmc from exp_fl where fldm in ( select distinct fldm from exp_flmx where rcid="+rcid+") order by fldm  ";
			}
		}
		List<Map<String, Object>> list= this.getSimpleJdbcTemplate().queryForList(sql);
		for(int i=0;i<list.size();i++){
			String fldm = String.valueOf(list.get(i).get("fldm"));
			sb.append("<tr>" +
					"<td style=\"background:'#efefef'\" colspan=4><b><font color='#a52a2a'>"+(String.valueOf(list.get(i).get("flmc")))+"</font></b>" +
							"<input type='hidden' name='fldms' value='"+(String.valueOf(list.get(i).get("fldm")))+"'/>" +
							"</td></tr>");
			if(rcid != null && !rcid.equals("")){
				sql = " select count(*) from EXP_FL_PROTYPE where rcid="+rcid+" and fldm='"+(fldm)+"'";
				if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
					sql = " select isnull(sxly,'') sxly,isnull(csly,'') csly,isnull(sxly1,'') sxly1,isnull(sxly2,'') sxly2,isnull(sxly3,'') sxly3," +
							"isnull(sxzy,'') sxzy,isnull(cszy,'') cszy,isnull(sxzy1,'') sxzy1,isnull(sxzy2,'') sxzy2,isnull(sxzy3,'') sxzy3, " +
							" isnull((SELECT  DICTNAME FROM dbo.XT_DICT_FLDM AS b  WHERE (LBID = 14) AND (a.sxly = DICTBH) and fldm='"+fldm+"'),'') AS sxly_mc," +
							" isnull((SELECT  DICTNAME FROM dbo.XT_DICT_FLDM AS b  WHERE (LBID = 14) AND (a.csly = DICTBH) and fldm='"+fldm+"'),'') AS csly_mc," +
							"isnull((SELECT  DICTNAME FROM dbo.XT_DICT_FLDM AS b  WHERE (LBID = 14) AND (a.sxly1 = DICTBH) and fldm='"+fldm+"'),'') AS sxly1_mc," +
							"isnull((SELECT  DICTNAME FROM dbo.XT_DICT_FLDM AS b  WHERE (LBID = 14) AND (a.sxly2 = DICTBH) and fldm='"+fldm+"'),'') AS sxly2_mc," +
							"isnull((SELECT  DICTNAME FROM dbo.XT_DICT_FLDM AS b  WHERE (LBID = 14) AND (a.sxly3 = DICTBH) and fldm='"+fldm+"'),'') AS sxly3_mc," +
							
							" isnull((SELECT  DICTNAME FROM dbo.XT_DICT_FLDM AS b  WHERE (LBID = 14) AND (substring(a.sxly,1,3) = DICTBH) and fldm='"+fldm+"'),'') AS sxly_mc_," +
							" isnull((SELECT  DICTNAME FROM dbo.XT_DICT_FLDM AS b  WHERE (LBID = 14) AND (substring(a.csly,1,3) = DICTBH) and fldm='"+fldm+"'),'') AS csly_mc_," +
							"isnull((SELECT  DICTNAME FROM dbo.XT_DICT_FLDM AS b  WHERE (LBID = 14) AND  (substring(a.sxly1,1,3) = DICTBH) and fldm='"+fldm+"'),'') AS sxly1_mc_," +
							"isnull((SELECT  DICTNAME FROM dbo.XT_DICT_FLDM AS b  WHERE (LBID = 14) AND  (substring(a.sxly2,1,3) = DICTBH) and fldm='"+fldm+"'),'') AS sxly2_mc_," +
							"isnull((SELECT  DICTNAME FROM dbo.XT_DICT_FLDM AS b  WHERE (LBID = 14) AND  (substring(a.sxly3,1,3) = DICTBH) and fldm='"+fldm+"'),'') AS sxly3_mc_," +
							
							" isnull((SELECT  DICTNAME FROM dbo.XT_DICT_FLDM AS b  WHERE (LBID = 13) AND (a.sxzy = DICTBH) and fldm='"+fldm+"'),'') AS sxzy_mc," +
							" isnull((SELECT  DICTNAME FROM dbo.XT_DICT_FLDM AS b  WHERE (LBID = 13) AND (a.cszy = DICTBH) and fldm='"+fldm+"'),'') AS cszy_mc," +
							"isnull((SELECT  DICTNAME FROM dbo.XT_DICT_FLDM AS b  WHERE (LBID = 13) AND (a.sxzy1 = DICTBH) and fldm='"+fldm+"'),'') AS sxzy1_mc," +
							"isnull((SELECT  DICTNAME FROM dbo.XT_DICT_FLDM AS b  WHERE (LBID = 13) AND (a.sxzy2 = DICTBH) and fldm='"+fldm+"'),'') AS sxzy2_mc," +
							"isnull((SELECT  DICTNAME FROM dbo.XT_DICT_FLDM AS b  WHERE (LBID = 13) AND (a.sxzy3 = DICTBH) and fldm='"+fldm+"'),'') AS sxzy3_mc," +
							"isnull(substring(a.sxly,4,3),'') sxly_,isnull(substring(a.csly,4,3),'') csly_," +
							"isnull(substring(a.sxly1,4,3),'') sxly1_,isnull(substring(a.sxly2,4,3),'') sxly2_," +
							"isnull(substring(a.sxly3,4,3),'') sxly3_ " +
							",isnull(sxlyo,'') sxlyo,isnull(cslyo,'') cslyo,isnull(sxly1o,'') sxly1o,isnull(sxly2o,'') sxly2o,isnull(sxly3o,'') sxly3o " +
							" from EXP_FL_PROTYPE a where rcid="+rcid+" and fldm='"+(fldm)+"'";
					Map<String,Object> flobj = this.getSimpleJdbcTemplate().queryForMap(sql); 
					
					sql = " select count(*) from xt_dlb_fldm where fldm='"+fldm+"' and lbid=13";
					if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
						sb.append("<tr>");
						sb.append("<td class=\"bt\">所学专业</td>");
						sb.append("<td>");
						sb.append(String.valueOf(flobj.get("sxzy_mc")));
						sb.append("<input type='hidden' name='sxzy' id='expfl.sxzy_"+fldm+"' value='"+String.valueOf(flobj.get("sxzy"))+"'>");
						sb.append("</td>");
						sb.append("<td class=\"bt\">从事专业</td>");
						sb.append("<td>");
						sb.append(String.valueOf(flobj.get("cszy_mc")));
						sb.append("<input type='hidden' name='cszy' id='expfl.cszy_"+fldm+"' value='"+String.valueOf(flobj.get("cszy"))+"'>");
						sb.append("</td>");
						sb.append("</tr>");
						sb.append("<tr>");
						sb.append("<td class=\"bt\">熟悉专业</td>");
						sb.append("<td colspan=3>");
						sb.append(String.valueOf(flobj.get("sxzy1_mc")));
						sb.append("<input type='hidden' name='sxzy1' id='expfl.sxzy1_"+fldm+"' value='"+String.valueOf(flobj.get("sxzy1"))+"'>&nbsp");
						sb.append(String.valueOf(flobj.get("sxzy2_mc")));
						sb.append("<input type='hidden' name='sxzy2' id='expfl.sxzy2_"+fldm+"' value='"+String.valueOf(flobj.get("sxzy2"))+"'>&nbsp");
						sb.append(String.valueOf(flobj.get("sxzy3_mc")));
						sb.append("<input type='hidden' name='sxzy3' id='expfl.sxzy3_"+fldm+"' value='"+String.valueOf(flobj.get("sxzy3"))+"'>");
						sb.append("</td>");
						sb.append("</tr>");
					}else{
						sb.append("<input type='hidden' name='sxzy' value=''/>" +
								"<input type='hidden' name='cszy' value=''/>" +
								"<input type='hidden' name='sxzy1' value=''/>" +
								"<input type='hidden' name='sxzy2' value=''/>" +
								"<input type='hidden' name='sxzy3' value=''/>");
					}
					
					
					sql = " select count(*) from xt_dlb_fldm where fldm='"+fldm+"' and lbid=14";
					if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
						if(fldm.equals("000000004")){ 
							/////////////////////////////////////
							//   COMPOMENT:     海归领域特殊需求
							//   DATE:			2011-06-11
							///////////////////////////////////
							sb.append("<tr>");
							sb.append("<td class=\"bt\">所学领域</td>");
							sb.append("<td colspan=3>");
							sb.append("");
							sb.append("<input type='hidden' name='sxly' id='expfl.sxly_"+fldm+"' value='"+String.valueOf(flobj.get("sxly"))+"'>");
							sb.append("<div id='expfl.sxly_"+fldm+"_button'>");
							sb.append(String.valueOf(flobj.get("sxly_mc_"))+"-"+String.valueOf(flobj.get("sxly_mc")));
							if(String.valueOf(flobj.get("sxly_")) != null && String.valueOf(flobj.get("sxly_")).equals("999")){
								sb.append("<input type=text name='exp.sxlyo' value='"+String.valueOf(flobj.get("sxlyo"))+"'>");
							}else{
								sb.append("<input type=hidden name='exp.sxlyo'> ");
							}
							sb.append("</div>");
							sb.append("</td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td class=\"bt\">从事领域</td>");
							sb.append("<td colspan=3>");
							sb.append("");
							sb.append("<input type='hidden' name='csly' id='expfl.csly_"+fldm+"' value='"+String.valueOf(flobj.get("csly"))+"'>");
							sb.append("<div id='expfl.csly_"+fldm+"_button'>");
							sb.append(String.valueOf(flobj.get("csly_mc_"))+"-"+String.valueOf(flobj.get("csly_mc")));
							if(String.valueOf(flobj.get("csly_")) != null && String.valueOf(flobj.get("csly_")).equals("999")){
								sb.append("<input type=text name='exp.cslyo' value='"+String.valueOf(flobj.get("cslyo"))+"'>");
							}else{
								sb.append("<input type=hidden name='exp.cslyo'> ");
							}
							sb.append("</div>");
							
							sb.append("</td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td class=\"bt\">熟悉领域一</td>");
							sb.append("<td colspan=3>");
							sb.append("");
							sb.append("<input type='hidden' name='sxly1' id='expfl.sxly1_"+fldm+"' value='"+String.valueOf(flobj.get("sxly1"))+"'>&nbsp");
							sb.append("<div id='expfl.sxly1_"+fldm+"_button'>");
							sb.append(String.valueOf(flobj.get("sxly1_mc_"))+"-"+String.valueOf(flobj.get("sxly1_mc")));
							if(String.valueOf(flobj.get("sxly_")) != null && String.valueOf(flobj.get("sxly1_")).equals("999")){
								sb.append("<input type=text name='exp.sxly1o' value='"+String.valueOf(flobj.get("sxly1o"))+"'>");
							}else{
								sb.append("<input type=hidden name='exp.sxly1o'> ");
							}
							sb.append("</div>");
							
							sb.append("</td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td class=\"bt\">熟悉领域二</td>");
							sb.append("<td colspan=3>");
							sb.append("");
							sb.append("<input type='hidden' name='sxly2' id='expfl.sxly2_"+fldm+"' value='"+String.valueOf(flobj.get("sxly2"))+"'>&nbsp");
							sb.append("<div id='expfl.sxly2_"+fldm+"_button'>");
							sb.append(String.valueOf(flobj.get("sxly2_mc_"))+"-"+String.valueOf(flobj.get("sxly2_mc")));
							if(String.valueOf(flobj.get("sxly2_")) != null && String.valueOf(flobj.get("sxly2_")).equals("999")){
								sb.append("<input type=text name='exp.sxly2o' value='"+String.valueOf(flobj.get("sxly2o"))+"'>");
							}else{
								sb.append("<input type=hidden name='exp.sxly2o'> ");
							}
							sb.append("</div>");
							
							sb.append("</td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td class=\"bt\">熟悉领域三</td>");
							sb.append("<td colspan=3>");
							sb.append("");
							sb.append("<input type='hidden' name='sxly3' id='expfl.sxly3_"+fldm+"' value='"+String.valueOf(flobj.get("sxly3"))+"'>");
							sb.append("<div id='expfl.sxly3_"+fldm+"_button'>");
							sb.append(String.valueOf(flobj.get("sxly3_mc_"))+"-"+String.valueOf(flobj.get("sxly3_mc")));
							if(String.valueOf(flobj.get("sxly3_")) != null && String.valueOf(flobj.get("sxly3_")).equals("999")){
								sb.append("<input type=text name='exp.sxly3o' value='"+String.valueOf(flobj.get("sxly3o"))+"'>");
							}else{
								sb.append("<input type=hidden name='exp.sxly3o'> ");
							}
							sb.append("</div>");
							
							sb.append("</td>");
							sb.append("</tr>");
						}else{
							sb.append("<tr>");
							sb.append("<td class=\"bt\">所学领域</td>");
							sb.append("<td>");
							sb.append(String.valueOf(flobj.get("sxly_mc")));
							sb.append("<input type='hidden' name='sxly' id='expfl.sxly_"+fldm+"' value='"+String.valueOf(flobj.get("sxly"))+"'>");
							sb.append("</td>");
							sb.append("<td class=\"bt\">从事领域</td>");
							sb.append("<td>");
							sb.append(String.valueOf(flobj.get("csly_mc")));
							sb.append("<input type='hidden' name='csly' id='expfl.csly_"+fldm+"' value='"+String.valueOf(flobj.get("csly"))+"'>");
							sb.append("</td>");
							sb.append("</tr>");
							sb.append("<tr>");
							sb.append("<td class=\"bt\">熟悉领域</td>");
							sb.append("<td colspan=3>");
							sb.append(String.valueOf(flobj.get("sxly1_mc")));
							sb.append("<input type='hidden' name='sxly1' id='expfl.sxly1_"+fldm+"' value='"+String.valueOf(flobj.get("sxly1"))+"'>&nbsp");
							sb.append(String.valueOf(flobj.get("sxly2_mc")));
							sb.append("<input type='hidden' name='sxly2' id='expfl.sxly2_"+fldm+"' value='"+String.valueOf(flobj.get("sxly2"))+"'>&nbsp");
							sb.append(String.valueOf(flobj.get("sxly3_mc")));
							sb.append("<input type='hidden' name='sxly3' id='expfl.sxly3_"+fldm+"' value='"+String.valueOf(flobj.get("sxly3"))+"'>");
							sb.append("</td>");
							sb.append("</tr>");
						}
						
					}else{
						sb.append("<input type='hidden' name='sxly' value=''/>" +
								"<input type='hidden' name='csly' value=''/>" +
								"<input type='hidden' name='sxly1' value=''/>" +
								"<input type='hidden' name='sxly2' value=''/>" +
								"<input type='hidden' name='sxly3' value=''/>");
					}
					
				}else{
					sql = " select count(*) from xt_dlb_fldm where fldm='"+fldm+"' and lbid=13";
					if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
						sb.append("<tr>");
						sb.append("<td class=\"bt\">所学专业</td>");
						sb.append("<td>");
						sb.append("");
						sb.append("<input type='hidden' name='sxzy' id='expfl.sxzy_"+fldm+"' value=''>");
						sb.append("</td>");
						sb.append("<td class=\"bt\">从事专业</td>");
						sb.append("<td>");
						sb.append("");
						sb.append("<input type='hidden' name='cszy' id='expfl.cszy_"+fldm+"' value=''>");
						sb.append("</td>");
						sb.append("</tr>");
						sb.append("<tr>");
						sb.append("<td class=\"bt\">熟悉专业</td>");
						sb.append("<td colspan=3>");
						sb.append("");
						sb.append("<input type='hidden' name='sxzy1' id='expfl.sxzy1_"+fldm+"' value=''>&nbsp");
						sb.append("");
						sb.append("<input type='hidden' name='sxzy2' id='expfl.sxzy2_"+fldm+"' value=''>&nbsp");
						sb.append("");
						sb.append("<input type='hidden' name='sxzy3' id='expfl.sxzy3_"+fldm+"' value=''>");
						sb.append("</td>");
						sb.append("</tr>");
					}else{
						sb.append("<input type='hidden' name='sxzy' value=''/>" +
								"<input type='hidden' name='cszy' value=''/>" +
								"<input type='hidden' name='sxzy1' value=''/>" +
								"<input type='hidden' name='sxzy2' value=''/>" +
								"<input type='hidden' name='sxzy3' value=''/>");
					}
					
					sql = " select count(*) from xt_dlb_fldm where fldm='"+fldm+"' and lbid=14";
					if(this.getSimpleJdbcTemplate().queryForInt(sql) >0){
						if(fldm.equals("000000004")){ 
							/////////////////////////////////////
							//   COMPOMENT:     海归领域特殊需求
							//   DATE:			2011-06-11
							///////////////////////////////////
							sb.append("<tr>");
							sb.append("<td class=\"bt\">所学领域</td>");
							sb.append("<td colspan=3>");
							sb.append("");
							sb.append("<input type='hidden' name='sxly' id='expfl.sxly_"+fldm+"' value=''>");
							sb.append("<div id='expfl.sxly_"+fldm+"_button'>");
							sb.append("<input type=hidden name='exp.sxlyo'> ");
							sb.append("</div>");
							sb.append("</td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td class=\"bt\">从事领域</td>");
							sb.append("<td colspan=3>");
							sb.append("");
							sb.append("<input type='hidden' name='csly' id='expfl.csly_"+fldm+"' value=''>");
							sb.append("<div id='expfl.csly_"+fldm+"_button'>");
							sb.append("<input type=hidden name='exp.cslyo'> ");
							sb.append("</div>");
							
							sb.append("</td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td class=\"bt\">熟悉领域一</td>");
							sb.append("<td colspan=3>");
							sb.append("");
							sb.append("<input type='hidden' name='sxly1' id='expfl.sxly1_"+fldm+"' value=''>&nbsp");
							sb.append("<div id='expfl.sxly1_"+fldm+"_button'>");
							sb.append("<input type=hidden name='exp.sxly1o'> ");
							sb.append("</div>");
							
							sb.append("</td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td class=\"bt\">熟悉领域二</td>");
							sb.append("<td colspan=3>");
							sb.append("");
							sb.append("<input type='hidden' name='sxly2' id='expfl.sxly2_"+fldm+"' value=''>&nbsp");
							sb.append("<div id='expfl.sxly2_"+fldm+"_button'>");
							sb.append("<input type=hidden name='exp.sxly2o'> ");
							sb.append("</div>");
							
							sb.append("</td>");
							sb.append("</tr>");
							
							sb.append("<tr>");
							sb.append("<td class=\"bt\">熟悉领域三</td>");
							sb.append("<td colspan=3>");
							sb.append("");
							sb.append("<input type='hidden' name='sxly3' id='expfl.sxly3_"+fldm+"' value=''>");
							sb.append("<div id='expfl.sxly3_"+fldm+"_button'>");
							sb.append("<input type=hidden name='exp.sxly3o'> ");
							sb.append("</div>");
							
							sb.append("</td>");
							sb.append("</tr>");
						}else{
							sb.append("<tr>");
							sb.append("<td class=\"bt\">所学领域</td>");
							sb.append("<td>");
							sb.append("");
							sb.append("<input type='hidden' name='sxly' id='expfl.sxly_"+fldm+"' value=''>");
							sb.append("</td>");
							sb.append("<td class=\"bt\">从事领域</td>");
							sb.append("<td>");
							sb.append("");
							sb.append("<input type='hidden' name='csly' id='expfl.csly_"+fldm+"' value=''>");
							sb.append("</td>");
							sb.append("</tr>");
							sb.append("<tr>");
							sb.append("<td class=\"bt\">熟悉领域</td>");
							sb.append("<td colspan=3>");
							sb.append("");
							sb.append("<input type='hidden' name='sxly1' id='expfl.sxly1_"+fldm+"' value=''>&nbsp");
							sb.append("");
							sb.append("<input type='hidden' name='sxly2' id='expfl.sxly2_"+fldm+"' value=''>&nbsp");
							sb.append("");
							sb.append("<input type='hidden' name='sxly3' id='expfl.sxly3_"+fldm+"' value=''>");
							sb.append("</td>");
							sb.append("</tr>");
						}
						
					}else{
						sb.append("<input type='hidden' name='sxly' value=''/>" +
								"<input type='hidden' name='csly' value=''/>" +
								"<input type='hidden' name='sxly1' value=''/>" +
								"<input type='hidden' name='sxly2' value=''/>" +
								"<input type='hidden' name='sxly3' value=''/>");
					}
					
				}
			}else{
				
				sql = " select count(*) from xt_dlb_fldm where fldm='"+fldm+"' and lbid=13";
				if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
					sb.append("<tr>");
					sb.append("<td class=\"bt\">所学专业</td>");
					sb.append("<td>");
					sb.append("");
					sb.append("<input type='hidden' name='sxzy' id='expfl.sxzy_"+fldm+"' value=''>");
					sb.append("</td>");
					sb.append("<td class=\"bt\">从事专业</td>");
					sb.append("<td>");
					sb.append("");
					sb.append("<input type='hidden' name='cszy' id='expfl.cszy_"+fldm+"' value=''>");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td class=\"bt\">熟悉专业</td>");
					sb.append("<td colspan=3>");
					sb.append("");
					sb.append("<input type='hidden' name='sxzy1' id='expfl.sxzy1_"+fldm+"' value=''>&nbsp");
					sb.append("");
					sb.append("<input type='hidden' name='sxzy2' id='expfl.sxzy2_"+fldm+"' value=''>&nbsp");
					sb.append("");
					sb.append("<input type='hidden' name='sxzy3' id='expfl.sxzy3_"+fldm+"' value=''>");
					sb.append("</td>");
					sb.append("</tr>");
				}else{
					sb.append("<input type='hidden' name='sxzy' value=''/>" +
							"<input type='hidden' name='cszy' value=''/>" +
							"<input type='hidden' name='sxzy1' value=''/>" +
							"<input type='hidden' name='sxzy2' value=''/>" +
							"<input type='hidden' name='sxzy3' value=''/>");
				}
				
				sql = " select count(*) from xt_dlb_fldm where fldm='"+fldm+"' and lbid=14";
				if(this.getSimpleJdbcTemplate().queryForInt(sql) >0){
					if(fldm.equals("000000004")){ 
						/////////////////////////////////////
						//   COMPOMENT:     海归领域特殊需求
						//   DATE:			2011-06-11
						///////////////////////////////////
						sb.append("<tr>");
						sb.append("<td class=\"bt\">所学领域</td>");
						sb.append("<td colspan=3>");
						sb.append("");
						sb.append("<input type='hidden' name='sxly' id='expfl.sxly_"+fldm+"' value=''>");
						sb.append("<div id='expfl.sxly_"+fldm+"_button'>");
						sb.append("<input type=hidden name='exp.sxlyo'> ");
						sb.append("</div>");
						sb.append("</td>");
						sb.append("</tr>");
						
						sb.append("<tr>");
						sb.append("<td class=\"bt\">从事领域</td>");
						sb.append("<td colspan=3>");
						sb.append("");
						sb.append("<input type='hidden' name='csly' id='expfl.csly_"+fldm+"' value=''>");
						sb.append("<div id='expfl.csly_"+fldm+"_button'>");
						sb.append("<input type=hidden name='exp.cslyo'> ");
						sb.append("</div>");
						
						sb.append("</td>");
						sb.append("</tr>");
						
						sb.append("<tr>");
						sb.append("<td class=\"bt\">熟悉领域一</td>");
						sb.append("<td colspan=3>");
						sb.append("");
						sb.append("<input type='hidden' name='sxly1' id='expfl.sxly1_"+fldm+"' value=''>&nbsp");
						sb.append("<div id='expfl.sxly1_"+fldm+"_button'>");
						sb.append("<input type=hidden name='exp.sxly1o'> ");
						sb.append("</div>");
						
						sb.append("</td>");
						sb.append("</tr>");
						
						sb.append("<tr>");
						sb.append("<td class=\"bt\">熟悉领域二</td>");
						sb.append("<td colspan=3>");
						sb.append("");
						sb.append("<input type='hidden' name='sxly2' id='expfl.sxly2_"+fldm+"' value=''>&nbsp");
						sb.append("<div id='expfl.sxly2_"+fldm+"_button'>");
						sb.append("<input type=hidden name='exp.sxly2o'> ");
						sb.append("</div>");
						
						sb.append("</td>");
						sb.append("</tr>");
						
						sb.append("<tr>");
						sb.append("<td class=\"bt\">熟悉领域三</td>");
						sb.append("<td colspan=3>");
						sb.append("");
						sb.append("<input type='hidden' name='sxly3' id='expfl.sxly3_"+fldm+"' value=''>");
						sb.append("<div id='expfl.sxly3_"+fldm+"_button'>");
						sb.append("<input type=hidden name='exp.sxly3o'> ");
						sb.append("</div>");
						
						sb.append("</td>");
						sb.append("</tr>");
					}else{

						sb.append("<tr>");
						sb.append("<td class=\"bt\">所学领域</td>");
						sb.append("<td>");
						sb.append("");
						sb.append("<input type='hidden' name='sxly' id='expfl.sxly_"+fldm+"' value=''>");
						sb.append("</td>");
						sb.append("<td class=\"bt\">从事领域</td>");
						sb.append("<td>");
						sb.append("");
						sb.append("<input type='hidden' name='csly' id='expfl.csly_"+fldm+"' value=''>");
						sb.append("</td>");
						sb.append("</tr>");
						sb.append("<tr>");
						sb.append("<td class=\"bt\">熟悉领域</td>");
						sb.append("<td colspan=3>");
						sb.append("");
						sb.append("<input type='hidden' name='sxly1' id='expfl.sxly1_"+fldm+"' value=''>&nbsp");
						sb.append("");
						sb.append("<input type='hidden' name='sxly2' id='expfl.sxly2_"+fldm+"' value=''>&nbsp");
						sb.append("");
						sb.append("<input type='hidden' name='sxly3' id='expfl.sxly3_"+fldm+"' value=''>");
						sb.append("</td>");
						sb.append("</tr>");
					}
						
				}else{
					sb.append("<input type='hidden' name='sxly' value=''/>" +
							"<input type='hidden' name='csly' value=''/>" +
							"<input type='hidden' name='sxly1' value=''/>" +
							"<input type='hidden' name='sxly2' value=''/>" +
							"<input type='hidden' name='sxly3' value=''/>");
				}
				
			}
		}
		return sb.toString();
	}
	/**
	 * 获取专家信息
	 * @param rcid
	 * @return
	 */
	public Map preExpMain(String rcid){
		Map m = new HashMap();
		if(rcid != null && !rcid.equals("")){
			String sql_q = "select a.rcname,a.oldname,a.xzzw,a.sex,a.nation,a.jg,CONVERT(varchar(100), a.birthday, 23) birthday,a.zjlb,a.zjno," +
					"a.rclb,a.xl,a.zc,a.zw,a.xw,a.email,a.ptel,a.xxzy,a.sxzy1,a.sxzy2,a.sxzy3,a.cszy,a.sxly,a.csly,a.byxx,CONVERT(varchar(100), a.byrq, 23) byrq," +
					"a.workunit,a.szdq,a.dwdq,a.dwxz,a.dwaddr,a.officetel,a.dwcode,a.fax,a.jtaddr,a.jtcode,a.jttel,a.zgbs,a.xsjt,a.hdzz,bgbs,isnull(btgyy,'') btgyy,expjsdf,exphbdf," +
					"isnull(ybkh,'') ybkh,isnull(jhkh,'') jhkh,isnull(sbkh,'') sbkh,fjpath,fjmc ,xzbz,info,"+
					"(select dictname from xt_dict b where lbid=20 and a.nation=b.dictbh ) nationmc,"+
					"(select dictname from xt_dict b where lbid=13 and a.xxzy=b.dictbh ) xxzymc ,"+
					"(select dictname from xt_dict b where lbid=13 and a.sxzy1=b.dictbh ) sxzy1mc,"+   
					"(select dictname from xt_dict b where lbid=13 and a.sxzy2=b.dictbh ) sxzy2mc,"+
					"(select dictname from xt_dict b where lbid=13 and a.sxzy3=b.dictbh ) sxzy3mc,"+  
					"(select dictname from xt_dict b where lbid=13 and a.cszy=b.dictbh ) cszymc," +
					"(select dictname from xt_dict b where lbid=14 and a.sxly=b.dictbh ) sxlymc," +
					"(select dictname from xt_dict b where lbid=14 and a.csly=b.dictbh ) cslymc," +
					" (SELECT DICTNAME  FROM XT_DICT AS b WHERE (LBID = 10) AND (a.JG = DICTBH)) AS jgmc," +
					"(SELECT DICTNAME  FROM XT_DICT AS b WHERE (LBID = 10) AND (a.SZDQ = DICTBH)) AS szdqmc," +
					" (select count(*) from exp_bpzj where bpzjqk in ('007','008','009') " +
					" and rcid="+rcid+") isdis_rs,a.fid,expgjbs,a.sctzhy,a.grry " +
					"  from exp_Main a where a.rcid="+rcid+"";
			m = super.queryForMap(sql_q);			
		}
		return m;
	}
	
	public Map preExpMainZj(String rcid){
		Map m = new HashMap();
		if(rcid != null && !rcid.equals("")){
			String sql_q = "select a.rcname,a.oldname,a.xzzw,a.sex,a.nation,a.jg,CONVERT(varchar(100), a.birthday, 23) birthday,a.zjlb,a.zjno," +
					"a.rclb,a.xl,a.zc,a.zw,a.xw,a.email,a.ptel,a.xxzy,a.sxzy1,a.sxzy2,a.sxzy3,a.cszy,a.byxx,CONVERT(varchar(100), a.byrq, 23) byrq," +
					"a.workunit,a.szdq,a.dwdq,a.dwxz,a.dwaddr,a.officetel,a.dwcode,a.fax,a.jtaddr,a.jtcode,a.jttel,a.zgbs,a.xsjt,a.hdzz,bgbs,isnull(btgyy,'') btgyy,expjsdf,exphbdf," +
					"isnull(ybkh,'') ybkh,isnull(jhkh,'') jhkh,isnull(sbkh,'') sbkh,fjpath,fjmc ,xzbz,info,"+
					"(select dictname from xt_dict b where lbid=20 and a.nation=b.dictbh ) nationmc,"+
					"(select dictname from xt_dict b where lbid=13 and a.xxzy=b.dictbh ) xxzymc ,"+
					"(select dictname from xt_dict b where lbid=13 and a.sxzy1=b.dictbh ) sxzy1mc,"+   
					"(select dictname from xt_dict b where lbid=13 and a.sxzy2=b.dictbh ) sxzy2mc,"+
					"(select dictname from xt_dict b where lbid=13 and a.sxzy3=b.dictbh ) sxzy3mc,"+  
					"(select dictname from xt_dict b where lbid=13 and a.cszy=b.dictbh ) cszymc," +
					" (SELECT DICTNAME  FROM XT_DICT AS b WHERE (LBID = 10) AND (a.JG = DICTBH)) AS jgmc," +
					"(SELECT DICTNAME  FROM XT_DICT AS b WHERE (LBID = 10) AND (a.SZDQ = DICTBH)) AS szdqmc," +
					" (select count(*) from exp_bpzj_zj where bpzjqk in ('007','008','009') " +
					" and rcid="+rcid+") isdis_rs,a.fid,expgjbs " +
					"  from exp_Main_zj a where a.rcid="+rcid+"";
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
			sql_q = " select * from exp_lxgj where rcid="+rcid+"  order by xh";
			list = super.queryForList(sql_q,new Object[]{});
		}
		return list;
	}
	
	public List<Map<String, Object>> getLxgjListZj(String rcid){
		String sql_q = " ";
		List<Map<String, Object>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select * from exp_lxgj_zj where rcid="+rcid+"  order by xh";
			list = super.queryForList(sql_q,new Object[]{});
		}
		return list;
	}
	
	/**
	 * 专家信息保存
	 * @return
	 */
	public int doSaveExpMain(String rcid ,String roledm ,String userid,Map<String,String> expMap,String[] lxgjgjmc,String[] fldms,String[] sxly
			,String[] csly,String[] sxly1,String[] sxly2,String[] sxly3,String[] sxzy
			,String[] cszy,String[] sxzy1,String[] sxzy2,String[] sxzy3, String[] expfls){
		String sql_q = "",sql_o ="";
		String maxid = rcid;
		
		String zjno = "";
		if(expMap.get("zjno") != null && !expMap.get("zjno").equals("")){
			zjno = expMap.get("zjno").replaceAll(" ", "").trim();
		}else{
			throw new BusException("证件号码不能为空，请输入！");
		}
		
		if(rcid !=null && !rcid.equals("")){//修改操作
			sql_q = " select count(*) from exp_main where zjno='"+zjno+"' and rcid!="+rcid+"  ";
			if(super.queryForInt(sql_q)>0){
				throw new BusException("当前的证件号码已存在，请重输！");
			}

			sql_o = " delete from exp_lxgj where rcid="+rcid+"";
			super.update(false,sql_o);
			
			sql_o = " update exp_main set rcname='"+expMap.get("rcname")+"',oldname='"+expMap.get("oldname")+"',sex='"+expMap.get("sex")+"'" +
					",nation='"+expMap.get("nation")+"',jg='"+expMap.get("jg")+"',birthday="+transToD(expMap.get("birthday"))+"" +
					",zjlb='"+expMap.get("zjlb")+"',zjno='"+zjno+"',rclb='"+expMap.get("rclb")+"',xl='"+expMap.get("xl")+"'" +
					",zc='"+expMap.get("zc")+"',zw='"+expMap.get("zw")+"',xw='"+expMap.get("xw")+"',email='"+expMap.get("email")+"'" +
					",ptel='"+expMap.get("ptel")+"', xxzy='"+expMap.get("xxzy")+"',sxzy1='"+expMap.get("sxzy1")+"'" +
					",sxzy2='"+expMap.get("sxzy2")+"',sxzy3='"+expMap.get("sxzy3")+"',cszy='"+expMap.get("cszy")+"'" +
					",sxly='"+expMap.get("sxly")+"',csly='"+expMap.get("csly")+"'" +
					",byxx='"+expMap.get("byxx")+"',byrq="+transToD(expMap.get("byrq"))+",workunit='"+expMap.get("workunit")+"'" +
					",szdq='"+expMap.get("szdq")+"',dwdq='"+expMap.get("dwdq")+"',dwxz='"+expMap.get("dwxz")+"'" +
					",dwaddr='"+expMap.get("dwaddr")+"',officetel='"+expMap.get("officetel")+"',dwcode='"+expMap.get("dwcode")+"'" +
					",fax='"+expMap.get("fax")+"',jtaddr='"+expMap.get("jtaddr")+"',jtcode='"+expMap.get("jtcode")+"'" +
					",jttel='"+expMap.get("jttel")+"',zgbs='"+expMap.get("zgbs")+"',xsjt='"+expMap.get("xsjt")+"'" +
					",hdzz='"+expMap.get("hdzz")+"',xzzw='"+expMap.get("xzzw")+"'" +
					",ybkh='"+expMap.get("ybkh")+"',sbkh='"+expMap.get("sbkh")+"',jhkh='"+expMap.get("jhkh")+"',fid="+transToN(expMap.get("fid"))+"" +
					",expjsdf="+transToN(expMap.get("expjsdf"))+",EXPHBDF="+transToN(expMap.get("exphbdf"))+",info=? ,expgjbs=?" +
					",sctzhy='"+(expMap.get("sctzhy")==null?"":expMap.get("sctzhy").replaceAll(" ", ""))+"'" +
					",grry='"+(expMap.get("grry")==null?"":expMap.get("grry").replaceAll(" ", ""))+"' " +
					",ver=isnull(ver,0)+1 where rcid="+rcid;

			super.update(true,sql_o,new Object[]{expMap.get("info"),expMap.get("expgjbs")});
			//插入 人才留国信息
			int xh =0;
			if(lxgjgjmc != null && lxgjgjmc.length > 0){
				for(int i=0 ;i<lxgjgjmc.length;i++){
					if(!lxgjgjmc[i].trim().equals("")){
						sql_o = " insert into exp_lxgj(rcid,xh,status,gjdm,gjmc,xzbz) " +
								" values('"+rcid+"','"+(++xh)+"',1,'','"+lxgjgjmc[i]+"',"+1+")";
						super.update(false,sql_o);
					}
				}
			}
			

			if(expMap.get("exptype") != null && !expMap.get("exptype").equals("")){
				sql_o = " delete from exp_exptype where rcid="+rcid;
				this.getSimpleJdbcTemplate().update(sql_o);
				
				String[] exptypes = expMap.get("exptype").split(",");
				for(int i=0 ;i<exptypes.length;i++){
					sql_o = " insert into exp_exptype(rcid,exptype,bz) " +
							" values("+rcid+",'"+exptypes[i].trim()+"',1)";
					this.getSimpleJdbcTemplate().update(sql_o);
				}
			}
			
			if(expfls != null ){
				sql_o =  "delete from exp_flmx where rcid="+rcid;
				if(!roledm.equals("01")){
					sql_o += " and fldm in ( select userfl from xt_user_FL where userid="+userid+") ";
				}
				this.getSimpleJdbcTemplate().update(sql_o);
				for(int i=0;i<expfls.length;i++){
					sql_o = "insert into exp_flmx(fldm,rcid,bz) " +
							"values('"+expfls[i]+"',"+rcid+",1)";
					this.getSimpleJdbcTemplate().update(sql_o);
				}
			}
			
			if(fldms != null ){
				for(int i=0;i<fldms.length;i++){
					sql_o = "delete from EXP_FL_PROTYPE where rcid="+rcid+" and fldm='"+fldms[i]+"'";
					this.getSimpleJdbcTemplate().update(sql_o);
					
					if(fldms[i].equals("000000004")){
						sql_o = "insert into EXP_FL_PROTYPE(rcid,fldm,sxly,csly,sxly1,sxly2,sxly3,sxzy,cszy,sxzy1,sxzy2,sxzy3" +
								",sxlyo,cslyo,sxly1o,sxly2o,sxly3o) " +
						"values("+rcid+",'"+fldms[i]+"','"+sxly[i]+"','"+csly[i]+"','"+sxly1[i]+"','"+sxly2[i]+"','"+sxly3[i]+"'" +
								",'"+sxzy[i]+"','"+cszy[i]+"','"+sxzy1[i]+"','"+sxzy2[i]+"','"+sxzy3[i]+"'," +
								"'"+expMap.get("sxlyo")+"','"+expMap.get("cslyo")+"','"+expMap.get("sxly1o")+"'," +
										"'"+expMap.get("sxly2o")+"','"+expMap.get("sxly3o")+"')";
					}else{
						sql_o = "insert into EXP_FL_PROTYPE(rcid,fldm,sxly,csly,sxly1,sxly2,sxly3,sxzy,cszy,sxzy1,sxzy2,sxzy3) " +
						"values("+rcid+",'"+fldms[i]+"','"+sxly[i]+"','"+csly[i]+"','"+sxly1[i]+"','"+sxly2[i]+"','"+sxly3[i]+"'" +
								",'"+sxzy[i]+"','"+cszy[i]+"','"+sxzy1[i]+"','"+sxzy2[i]+"','"+sxzy3[i]+"')";
					}
					
					this.getSimpleJdbcTemplate().update(sql_o);
				}
			}
			
			if(!roledm.equals("01")){
				sql_q = " select count(*) from xt_user_FL where userid="+userid+" and userfl='000000004'";
				if(this.getSimpleJdbcTemplate().queryForInt(sql_q) > 0){
					sql_q = " select count(*) from EXP_FL_PROTYPE where rcid="+rcid+" and fldm='000000004' and isnull(csly,'')!=''";
					if(this.getSimpleJdbcTemplate().queryForInt(sql_q) == 0){
						throw new BusException("<领军型创新创业人才项目专家> 从事领域不能为空！");
					}
				}
			}
			
			//用户处理
			sql_q = "select count(*) from xt_user_exp where rcid="+rcid;
			if(this.getSimpleJdbcTemplate().queryForInt(sql_q) > 0){
				sql_q = "select loginname from xt_user_exp where rcid="+rcid;
				String zjno_zj = this.getSimpleJdbcTemplate().queryForObject(sql_q,String.class);
				if(!zjno_zj.equals(zjno)){
					String pass = "";
					if(zjno.length() > 6){
						pass = zjno.substring(zjno.length()-6,zjno.length());
					}else{
						pass = zjno;
					}
					sql_o = " update xt_user_exp set loginname='"+zjno+"',password='"+pass+"' where rcid="+rcid;
				}
			}else{
				String dbid_user = String.valueOf(this.getMaxKey("XT_USER_EXP"));
				String pass = "";
				if(zjno.length() > 6){
					pass = zjno.substring(zjno.length()-6,zjno.length());
				}else{
					pass = zjno;
				}
				sql_o = "insert into XT_USER_EXP(DBID, RCNAME, LOGINNAME, PASSWORD, RCID, BZ, JHBZ, SBCOUNT)" +
						" values("+dbid_user+",'"+expMap.get("rcname")+"','"+zjno+"','"+pass+"',"+maxid+",2,1,0)";
				update(true, sql_o);
			}
			
		}else{//新增操作
			sql_q = " select count(*) from exp_main where zjno='"+zjno+"' ";
			if(this.getSimpleJdbcTemplate().queryForInt(sql_q)>0){
				throw new BusException("当前的证件号码已存在，请重输！");
			}
			
			maxid = String.valueOf(this.getMaxKey("EXP_MAIN"));
			//插入人才信息
			sql_o = " insert into exp_main(rcid,status,rcname,oldname,sex,nation,jg,birthday,zjlb,zjno,rclb,xl,zc,zw,xw,email,ptel," +
				" xxzy,sxzy1,sxzy2,sxzy3,cszy,sxly,csly,byxx,byrq,workunit,szdq,dwdq,dwxz,dwaddr,officetel,dwcode,fax,jtaddr,jtcode," +
				"jttel,zgbs,xsjt,hdzz,bgbs,rcbs,rcbz,xzbz,xzzw,zgshbz,fjpath,fjmc,info,expgjbs,fid,expjsdf,EXPHBDF,EXPERTID_,sctzhy,GRRY,VER) " +
				"values('"+maxid+"',1,'"+expMap.get("rcname")+"','"+expMap.get("oldname")+"','"+expMap.get("sex")+"','"+expMap.get("nation")+"'," +
				"'"+expMap.get("jg")+"',"+transToD(expMap.get("birthday"))+",'"+expMap.get("zjlb")+"','"+zjno+"'," +
				"'"+expMap.get("rclb")+"','"+expMap.get("xl")+"','"+expMap.get("zc")+"','"+expMap.get("zw")+"','"+expMap.get("xw")+"'," +
				"'"+expMap.get("email")+"','"+expMap.get("ptel")+"','"+expMap.get("xxzy")+"','"+expMap.get("sxzy1")+"','"+expMap.get("sxzy2")+"'," +
				"'"+expMap.get("sxzy3")+"','"+expMap.get("cszy")+"','"+expMap.get("sxly")+"','"+expMap.get("csly")+"'," +
				"'"+expMap.get("byxx")+"',"+transToD(expMap.get("byrq"))+",'"+expMap.get("workunit")+"'," +
				"'"+expMap.get("szdq")+"','"+expMap.get("dwdq")+"','"+expMap.get("dwxz")+"','"+expMap.get("dwaddr")+"','"+expMap.get("officetel")+"'," +
				"'"+expMap.get("dwcode")+"','"+expMap.get("fax")+"','"+expMap.get("jtaddr")+"','"+expMap.get("jtcode")+"','"+expMap.get("jttel")+"'," +
				"'"+expMap.get("zgbs")+"','"+expMap.get("xsjt")+"','"+expMap.get("hdzz")+"',0,0," +
				"0,0,'"+expMap.get("xzzw")+"',0,null,null,?,?,"+transToN(expMap.get("fid"))+","+transToN(expMap.get("expjsdf"))+"," +
				""+transToN(expMap.get("exphbdf"))+",newid(),'"+(expMap.get("sctzhy")==null?"":expMap.get("sctzhy").replaceAll(" ", ""))+"'," +
				"'"+(expMap.get("grry")==null?"":expMap.get("grry").replaceAll(" ", ""))+"',1)";
			super.update(true,sql_o,new Object[]{expMap.get("info"),expMap.get("expgjbs")});
			

			//插入 人才留国信息
			int xh = 0;
			if(lxgjgjmc != null && lxgjgjmc.length > 0){
				for(int i=0 ;i<lxgjgjmc.length;i++){
					if(!lxgjgjmc[i].trim().equals("")){
						sql_o = " insert into exp_lxgj(rcid,xh,status,gjdm,gjmc,xzbz) " +
								" values('"+maxid+"','"+(++xh)+"',1,'','"+lxgjgjmc[i]+"',"+1+")";
						this.getSimpleJdbcTemplate().update(sql_o);
					}
				}
			}
			
			if(expMap.get("exptype") != null && !expMap.get("exptype").equals("")){
				String[] exptypes = expMap.get("exptype").split(",");
				for(int i=0 ;i<exptypes.length;i++){
					sql_o = " insert into exp_exptype(rcid,exptype,bz) " +
							" values("+maxid+",'"+exptypes[i].trim()+"',1)";
					this.getSimpleJdbcTemplate().update(sql_o);
				}
			}
			
			if(expfls != null ){
				for(int i=0;i<expfls.length;i++){
					sql_o = "insert into exp_flmx(fldm,rcid,bz) " +
							"values('"+expfls[i]+"',"+maxid+",1)";
					this.getSimpleJdbcTemplate().update(sql_o);
				}
			}
			
			if(fldms != null){
				for(int i=0;i<fldms.length;i++){

					if(fldms[i].equals("000000004")){
						sql_o = "insert into EXP_FL_PROTYPE(rcid,fldm,sxly,csly,sxly1,sxly2,sxly3,sxzy,cszy,sxzy1,sxzy2,sxzy3" +
								",sxlyo,cslyo,sxly1o,sxly2o,sxly3o) " +
						"values("+maxid+",'"+fldms[i]+"','"+sxly[i]+"','"+csly[i]+"','"+sxly1[i]+"','"+sxly2[i]+"','"+sxly3[i]+"'" +
								",'"+sxzy[i]+"','"+cszy[i]+"','"+sxzy1[i]+"','"+sxzy2[i]+"','"+sxzy3[i]+"'," +
								"'"+expMap.get("sxlyo")+"','"+expMap.get("cslyo")+"','"+expMap.get("sxly1o")+"'," +
										"'"+expMap.get("sxly2o")+"','"+expMap.get("sxly3o")+"')";
					}else{
						sql_o = "insert into EXP_FL_PROTYPE(rcid,fldm,sxly,csly,sxly1,sxly2,sxly3,sxzy,cszy,sxzy1,sxzy2,sxzy3) " +
						"values("+maxid+",'"+fldms[i]+"','"+sxly[i]+"','"+csly[i]+"','"+sxly1[i]+"','"+sxly2[i]+"','"+sxly3[i]+"'" +
								",'"+sxzy[i]+"','"+cszy[i]+"','"+sxzy1[i]+"','"+sxzy2[i]+"','"+sxzy3[i]+"')";
					}
					this.getSimpleJdbcTemplate().update(sql_o);
					
				}
			}
			

			if(!roledm.equals("01")){
				sql_q = " select count(*) from xt_user_FL where userid="+userid+" and userfl='000000004'";
				if(this.getSimpleJdbcTemplate().queryForInt(sql_q) > 0){
					sql_q = " select count(*) from EXP_FL_PROTYPE where rcid="+maxid+" and fldm='000000004' and isnull(csly,'')!=''";
					if(this.getSimpleJdbcTemplate().queryForInt(sql_q) == 0){
						throw new BusException("<领军型创新创业人才项目专家> 从事领域不能为空！");
					}
				}
			} 
			
			//用户处理
			String dbid_user = String.valueOf(this.getMaxKey("XT_USER_EXP"));
			String pass = "";
			if(zjno.length() > 6){
				pass = zjno.substring(zjno.length()-6,zjno.length());
			}else{
				pass = zjno;
			}
			
			//未激活，已生效。
			sql_o = "insert into XT_USER_EXP(DBID, RCNAME, LOGINNAME, PASSWORD, RCID, BZ, JHBZ, SBCOUNT)" +
					" values("+dbid_user+",'"+expMap.get("rcname")+"','"+zjno+"','"+pass+"',"+maxid+",2,1,0)";
			update(true, sql_o);
		}
		return Integer.parseInt(maxid);
	}
	
	public int doSaveExpMainZj(String rcid ,Map<String,String> expMap,String[] lxgjgjmc){
		String sql_q = "",sql_o ="";
		String maxid = rcid;
		
		String zjno = "";
		if(expMap.get("zjno") != null && !expMap.get("zjno").equals("")){
			zjno = expMap.get("zjno").replaceAll(" ", "").trim();
		}else{
			throw new BusException("证件号码不能为空，请输入！");
		}
		
		if(rcid !=null && !rcid.equals("")){//修改操作
			sql_q = " select count(*) from exp_main where zjno='"+zjno+"' and rcid!="+rcid+"  ";
			if(super.queryForInt(sql_q)>0){
				throw new BusException("当前的证件号码已存在，请重输！");
			}
			
			sql_q = " select count(*) from exp_main_zj where zjno='"+zjno+"' and rcid!="+rcid+"  ";
			if(super.queryForInt(sql_q)>0){
				throw new BusException("当前的证件号码已存在，请重输！");
			}
			
			sql_o = " delete from exp_lxgj_zj where rcid="+rcid+"";
			super.update(false,sql_o);
			
			sql_o = " update exp_main_zj set rcname='"+expMap.get("rcname")+"',oldname='"+expMap.get("oldname")+"',sex='"+expMap.get("sex")+"'" +
					",nation='"+expMap.get("nation")+"',jg='"+expMap.get("jg")+"',birthday="+transToD(expMap.get("birthday"))+"" +
					",zjlb='"+expMap.get("zjlb")+"',zjno='"+zjno+"',rclb='"+expMap.get("rclb")+"',xl='"+expMap.get("xl")+"'" +
					",zc='"+expMap.get("zc")+"',zw='"+expMap.get("zw")+"',xw='"+expMap.get("xw")+"',email='"+expMap.get("email")+"'" +
					",ptel='"+expMap.get("ptel")+"', xxzy='"+expMap.get("xxzy")+"',sxzy1='"+expMap.get("sxzy1")+"'" +
					",sxzy2='"+expMap.get("sxzy2")+"',sxzy3='"+expMap.get("sxzy3")+"',cszy='"+expMap.get("cszy")+"'" +
					",byxx='"+expMap.get("byxx")+"',byrq="+transToD(expMap.get("byrq"))+",workunit='"+expMap.get("workunit")+"'" +
					",szdq='"+expMap.get("szdq")+"',dwdq='"+expMap.get("dwdq")+"',dwxz='"+expMap.get("dwxz")+"'" +
					",dwaddr='"+expMap.get("dwaddr")+"',officetel='"+expMap.get("officetel")+"',dwcode='"+expMap.get("dwcode")+"'" +
					",fax='"+expMap.get("fax")+"',jtaddr='"+expMap.get("jtaddr")+"',jtcode='"+expMap.get("jtcode")+"'" +
					",jttel='"+expMap.get("jttel")+"',zgbs='"+expMap.get("zgbs")+"',xsjt='"+expMap.get("xsjt")+"'" +
					",hdzz='"+expMap.get("hdzz")+"',xzzw='"+expMap.get("xzzw")+"'" +
					",ybkh='"+expMap.get("ybkh")+"',sbkh='"+expMap.get("sbkh")+"',jhkh='"+expMap.get("jhkh")+"',fid="+transToN(expMap.get("fid"))+"" +
					",expjsdf="+transToN(expMap.get("expjsdf"))+",EXPHBDF="+transToN(expMap.get("exphbdf"))+",info=? ,expgjbs=? " +
					" where rcid="+rcid;

			super.update(true,sql_o,new Object[]{expMap.get("info"),expMap.get("expgjbs")});
			//插入 人才留国信息
			int xh =0;
			if(lxgjgjmc != null && lxgjgjmc.length > 0){
				for(int i=0 ;i<lxgjgjmc.length;i++){
					if(!lxgjgjmc[i].trim().equals("")){
						sql_o = " insert into exp_lxgj_zj(rcid,xh,status,gjdm,gjmc,xzbz) " +
								" values('"+rcid+"','"+(++xh)+"',1,'','"+lxgjgjmc[i]+"',"+1+")";
						super.update(false,sql_o);
					}
				}
			}
			

			//用户处理
			sql_q = "select count(*) from xt_user_exp where rcid="+rcid;
			if(this.getSimpleJdbcTemplate().queryForInt(sql_q) > 0){
				sql_q = "select loginname from xt_user_exp where rcid="+rcid;
				String zjno_zj = this.getSimpleJdbcTemplate().queryForObject(sql_q,String.class);
				if(!zjno_zj.equals(zjno)){
					String pass = "";
					if(zjno.length() > 6){
						pass = zjno.substring(zjno.length()-6,zjno.length());
					}else{
						pass = zjno;
					}
					sql_o = " update xt_user_exp set loginname='"+zjno+"',password='"+pass+"' where rcid="+rcid;
				}
			}else{
				String dbid_user = String.valueOf(this.getMaxKey("XT_USER_EXP"));
				String pass = "";
				if(zjno.length() > 6){
					pass = zjno.substring(zjno.length()-6,zjno.length());
				}else{
					pass = zjno;
				}
				sql_o = "insert into XT_USER_EXP(DBID, RCNAME, LOGINNAME, PASSWORD, RCID, BZ, JHBZ, SBCOUNT)" +
						" values("+dbid_user+",'"+expMap.get("rcname")+"','"+zjno+"','"+pass+"',"+maxid+",1,2,0)";
				update(true, sql_o);
			}
			
		}else{//新增操作
			sql_q = " select count(*) from exp_main where zjno='"+zjno+"' ";
			if(this.getSimpleJdbcTemplate().queryForInt(sql_q)>0){
				throw new BusException("当前的证件号码已存在，请重输！");
			}
			
			sql_q = " select count(*) from exp_main_zj where zjno='"+zjno+"' ";
			if(this.getSimpleJdbcTemplate().queryForInt(sql_q)>0){
				throw new BusException("当前的证件号码已存在，请重输！");
			}
			
			maxid = String.valueOf(this.getMaxKey("EXP_MAIN"));
			//插入人才信息
			sql_o = " insert into exp_main_zj(rcid,status,rcname,oldname,sex,nation,jg,birthday,zjlb,zjno,rclb,xl,zc,zw,xw,email,ptel," +
				" xxzy,sxzy1,sxzy2,sxzy3,cszy,byxx,byrq,workunit,szdq,dwdq,dwxz,dwaddr,officetel,dwcode,fax,jtaddr,jtcode," +
				"jttel,zgbs,xsjt,hdzz,bgbs,rcbs,rcbz,xzbz,xzzw,zgshbz,fjpath,fjmc,info,expgjbs,fid,expjsdf,EXPHBDF) " +
				"values('"+maxid+"',1,'"+expMap.get("rcname")+"','"+expMap.get("oldname")+"','"+expMap.get("sex")+"','"+expMap.get("nation")+"'," +
				"'"+expMap.get("jg")+"',"+transToD(expMap.get("birthday"))+",'"+expMap.get("zjlb")+"','"+zjno+"'," +
				"'"+expMap.get("rclb")+"','"+expMap.get("xl")+"','"+expMap.get("zc")+"','"+expMap.get("zw")+"','"+expMap.get("xw")+"'," +
				"'"+expMap.get("email")+"','"+expMap.get("ptel")+"','"+expMap.get("xxzy")+"','"+expMap.get("sxzy1")+"','"+expMap.get("sxzy2")+"'," +
				"'"+expMap.get("sxzy3")+"','"+expMap.get("cszy")+"','"+expMap.get("byxx")+"',"+transToD(expMap.get("byrq"))+",'"+expMap.get("workunit")+"'," +
				"'"+expMap.get("szdq")+"','"+expMap.get("dwdq")+"','"+expMap.get("dwxz")+"','"+expMap.get("dwaddr")+"','"+expMap.get("officetel")+"'," +
				"'"+expMap.get("dwcode")+"','"+expMap.get("fax")+"','"+expMap.get("jtaddr")+"','"+expMap.get("jtcode")+"','"+expMap.get("jttel")+"'," +
				"'"+expMap.get("zgbs")+"','"+expMap.get("xsjt")+"','"+expMap.get("hdzz")+"',0,0," +
				"0,0,'"+expMap.get("xzzw")+"',0,null,null,?,?,"+transToN(expMap.get("fid"))+","+transToN(expMap.get("expjsdf"))+"," +
						""+transToN(expMap.get("exphbdf"))+")";
			super.update(true,sql_o,new Object[]{expMap.get("info"),expMap.get("expgjbs")});
			

			//插入 人才留国信息
			int xh = 0;
			if(lxgjgjmc != null && lxgjgjmc.length > 0){
				for(int i=0 ;i<lxgjgjmc.length;i++){
					if(!lxgjgjmc[i].trim().equals("")){
						sql_o = " insert into exp_lxgj_zj(rcid,xh,status,gjdm,gjmc,xzbz) " +
								" values('"+maxid+"','"+(++xh)+"',1,'','"+lxgjgjmc[i]+"',"+1+")";
						this.getSimpleJdbcTemplate().update(sql_o);
					}
				}
			}
			
			//用户处理
			String dbid_user = String.valueOf(this.getMaxKey("XT_USER_EXP"));
			String pass = "";
			if(zjno.length() > 6){
				pass = zjno.substring(zjno.length()-6,zjno.length());
			}else{
				pass = zjno;
			}
			//已激活，未生效。
			sql_o = "insert into XT_USER_EXP(DBID, RCNAME, LOGINNAME, PASSWORD, RCID, BZ, JHBZ, SBCOUNT)" +
					" values("+dbid_user+",'"+expMap.get("rcname")+"','"+zjno+"','"+pass+"',"+maxid+",1,2,0)";
			update(true, sql_o);
		}
		return Integer.parseInt(maxid);
	}
	/**
	 * 获取技术专长数据
	 */
	public List<Map<String, Object>> getJszcList(String rcid){
		String sql_q = " ";
		List<Map<String, Object>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select a.*,(select dictname from xt_dict b where lbid=14 and a.ly=b.dictbh ) ly_mc," +
					"(select dictname from xt_dict b where lbid=16 and a.zly=b.dictbh ) zly_mc," +
					"(select dictname from xt_dict b where lbid=16 and a.fx=b.dictbh ) fx_mc," +
					"(select dictname from xt_dict b where lbid=18 and a.sxcd=b.dictbh ) sxcd_mc," +
					"substring(ly,len(ly)-2,len(ly)) lyother,substring(zly,len(zly)-2,len(zly)) zlyother,substring(fx,len(fx)-2,len(fx)) fxother" +
					" from exp_jszc a where a.rcid="+rcid+"   order by a.xh ";
			list = super.queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	
	public List<Map<String, Object>> getJszcListZj(String rcid){
		String sql_q = " ";
		List<Map<String, Object>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select a.*,(select dictname from xt_dict b where lbid=14 and a.ly=b.dictbh ) ly_mc," +
					"(select dictname from xt_dict b where lbid=16 and a.zly=b.dictbh ) zly_mc," +
					"(select dictname from xt_dict b where lbid=16 and a.fx=b.dictbh ) fx_mc," +
					"(select dictname from xt_dict b where lbid=18 and a.sxcd=b.dictbh ) sxcd_mc," +
					"substring(ly,len(ly)-2,len(ly)) lyother,substring(zly,len(zly)-2,len(zly)) zlyother,substring(fx,len(fx)-2,len(fx)) fxother" +
					" from exp_jszc_zj a where a.rcid="+rcid+"   order by a.xh ";
			list = super.queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	/**
	 * 技术专长插入操作
	 * @param rcid
	 * @param m
	 * @param loginbz
	 * @return
	 */
	public int doJszcI(String rcid,Map<String, String > m ){
		String sql = "";
		//获取 XH 最大值
		Integer maxxh = 1;
		sql = "select count(*) from exp_jszc where rcid="+rcid +" ";
		if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select max(xh) from exp_jszc where rcid="+rcid +" and status=1 ";
			maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
		}
		sql = " insert into exp_jszc(rcid,xh,status,sxcd,fx,fsmc,ly,zly,zlymc,lymc,xzbz) " +
			" values('"+rcid+"','"+maxxh+"',1,'"+m.get("sxcd")+"','"+m.get("fx")+"'," +
			"'"+m.get("fsmc")+"','"+m.get("ly")+"','"+m.get("zly")+"','"+m.get("zlymc")+"','"+m.get("lymc")+"',1)";
		super.update(true,sql);
		return 1;
	}
	
	public int doJszcIZj(String rcid,Map<String, String > m ){
		String sql = "";
		//获取 XH 最大值
		Integer maxxh = 1;
		sql = "select count(*) from exp_jszc_zj where rcid="+rcid +" ";
		if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select max(xh) from exp_jszc_zj where rcid="+rcid +" and status=1 ";
			maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
		}
		sql = " insert into exp_jszc_zj(rcid,xh,status,sxcd,fx,fsmc,ly,zly,zlymc,lymc,xzbz) " +
			" values('"+rcid+"','"+maxxh+"',1,'"+m.get("sxcd")+"','"+m.get("fx")+"'," +
			"'"+m.get("fsmc")+"','"+m.get("ly")+"','"+m.get("zly")+"','"+m.get("zlymc")+"','"+m.get("lymc")+"',1)";
		super.update(true,sql);
		return 1;
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
		sql_q = " select a.*,(select dictname from xt_dict b where lbid=14 and a.ly=b.dictbh ) ly_mc," +
			"(select dictname from xt_dict b where lbid=14 and a.zly=b.dictbh ) zly_mc," +
			"(select dictname from xt_dict b where lbid=16 and a.fx=b.dictbh ) fx_mc," +
			"(select dictname from xt_dict b where lbid=18 and a.sxcd=b.dictbh ) sxcd_mc," +
			"substring(ly,len(ly)-2,len(ly)) lyother,substring(zly,len(zly)-2,len(zly)) zlyother,substring(fx,len(fx)-2,len(fx)) fxother" +
			" from exp_jszc a where a.rcid="+rcid+"  and a.xh="+xh ;
		map = this.getSimpleJdbcTemplate().queryForMap(sql_q);
		return map;
	}
	
	public Map getJszcUZj(String rcid,String xh){
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
	 * 技术专长修改操作
	 * @param rcid
	 * @param xh
	 * @param loginbz
	 * @return
	 */
	public int doJszcU(String rcid,String xh,Map<String, String > m ){
		String sql = "";
		
		sql = " update exp_jszc set sxcd='"+m.get("sxcd")+"'," +
				" fx='"+m.get("fx")+"',fsmc='"+m.get("fsmc")+"',ly='"+m.get("ly")+"'," +
				"zly='"+m.get("zly")+"',zlymc='"+m.get("zlymc")+"',lymc='"+m.get("lymc")+"' where rcid="+rcid+" and xh="+xh+"";
		super.update(true,sql);
		
		return 1;
	}
	
	public int doJszcUZj(String rcid,String xh,Map<String, String > m ){
		String sql = "";
		
		sql = " update exp_jszc_zj set sxcd='"+m.get("sxcd")+"'," +
				" fx='"+m.get("fx")+"',fsmc='"+m.get("fsmc")+"',ly='"+m.get("ly")+"'," +
				"zly='"+m.get("zly")+"',zlymc='"+m.get("zlymc")+"',lymc='"+m.get("lymc")+"' where rcid="+rcid+" and xh="+xh+"";
		super.update(true,sql);
		
		return 1;
	}
	/**
	 * 删除技术专长操作
	 * @param rcid
	 * @param xh
	 * @param status
	 * @param loginbz
	 * @return
	 */
	public int doJszcD(String rcid,String[] xh){
		String sql = "";
		
		if(xh != null && xh.length > 0){
			sql = " delete from exp_jszc where rcid="+rcid+" and xh in ("+ArrayToString(xh)+")";
			update(true,sql);
		}
		
		return 1;
	}
	
	public int doJszcDZj(String rcid,String[] xh){
		String sql = "";
		
		if(xh != null && xh.length > 0){
			sql = " delete from exp_jszc_zj where rcid="+rcid+" and xh in ("+ArrayToString(xh)+")";
			update(true,sql);
		}
		
		return 1;
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
					" (select dictname from xt_dict b where lbid=13 and a.sxzy=b.dictbh ) sxzy_mc from exp_xxjl a " +
					" where a.rcid="+rcid+" order by a.xh ";
			list = queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	
	public List<Map<String, Object>> getXxjlListZj(String rcid){
		String sql_q = " ";
		List<Map<String, Object>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select CONVERT(varchar(100), a.brq, 23) brq,CONVERT(varchar(100), a.erq, 23) erq," +
					"a.yx,a.sxzy,a.xl,a.xw,a.byjy,a.xh," +
					" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 2) AND (a.XL = DICTBH)) AS xl_mc," +
					" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 3) AND (a.XW = DICTBH)) AS xw_mc," +
					" (select dictname from xt_dict b where lbid=13 and a.sxzy=b.dictbh ) sxzy_mc from exp_xxjl_ZJ a " +
					" where a.rcid="+rcid+" order by a.xh ";
			list = queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	
	/**
	 * 学习简历新增操作
	 * @param rcid
	 * @param m
	 * @param loginbz
	 * @return
	 */
	public int doXxjlI(String rcid,Map<String, String > m ){
		String sql = "";
		
		Integer maxxh = 1;
		sql = "select count(*) from exp_xxjl where rcid="+rcid +"";
		if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select max(xh) from exp_xxjl where rcid="+rcid +" ";
			maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
		}
		sql = " insert into exp_xxjl(rcid,xh,status,brq,erq,yx,sxzy,xl,xw,byjy,xzbz) " +
			" values('"+rcid+"','"+maxxh+"',1,"+transToD(m.get("brq"))+","+transToD(m.get("erq"))+"," +
			"'"+m.get("yx")+"','"+m.get("sxzy")+"','"+m.get("xl")+"','"+m.get("xw")+"','"+m.get("byjy")+"',1)";
		super.update(true,sql);
		return 1;
	}
	
	public int doXxjlIZj(String rcid,Map<String, String > m ){
		String sql = "";
		
		Integer maxxh = 1;
		sql = "select count(*) from exp_xxjl_zj where rcid="+rcid +"";
		if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select max(xh) from exp_xxjl_zj where rcid="+rcid +" ";
			maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
		}
		sql = " insert into exp_xxjl_zj(rcid,xh,status,brq,erq,yx,sxzy,xl,xw,byjy,xzbz) " +
			" values('"+rcid+"','"+maxxh+"',1,"+transToD(m.get("brq"))+","+transToD(m.get("erq"))+"," +
			"'"+m.get("yx")+"','"+m.get("sxzy")+"','"+m.get("xl")+"','"+m.get("xw")+"','"+m.get("byjy")+"',1)";
		super.update(true,sql);
		return 1;
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
			" (select dictname from xt_dict b where lbid=13 and a.sxzy=b.dictbh ) sxzy_mc," +
			"(SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 2) AND (a.XL = DICTBH)) AS xl_mc," +
			"(SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 3) AND (a.XW = DICTBH)) AS xw_mc from exp_xxjl a " +
			" where a.rcid="+rcid+" and a.xh='"+xh+"' ";
		map = queryForMap(sql_q);
		return map;
	}
	
	public Map getXxjlUZj(String rcid,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select CONVERT(varchar(100), a.brq, 23) brq,CONVERT(varchar(100), a.erq, 23) erq,a.yx,a.sxzy,a.xl,a.xw,a.byjy," +
			" (select dictname from xt_dict b where lbid=13 and a.sxzy=b.dictbh ) sxzy_mc," +
			"(SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 2) AND (a.XL = DICTBH)) AS xl_mc," +
			"(SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 3) AND (a.XW = DICTBH)) AS xw_mc from exp_xxjl_zj a " +
			" where a.rcid="+rcid+" and a.xh='"+xh+"' ";
		map = queryForMap(sql_q);
		return map;
	}
	
	/**
	 * 学习简历修改操作
	 * @param rcid
	 * @param xh
	 * @param loginbz
	 * @return
	 */
	public int doXxjlU(String rcid,String xh,Map<String, String > m ){
		String sql = "";
		
		sql = " update exp_xxjl set brq="+transToD(m.get("brq"))+"," +
				" erq="+transToD(m.get("erq"))+",yx='"+m.get("yx")+"',sxzy='"+m.get("sxzy")+"'," +
				"xl='"+m.get("xl")+"',xw='"+m.get("xw")+"',byjy='"+m.get("byjy")+"' where rcid="+rcid+" and xh="+xh+"";
		update(true,sql);
		return 1;
	}
	
	public int doXxjlUZj(String rcid,String xh,Map<String, String > m ){
		String sql = "";
		
		sql = " update exp_xxjl_zj set brq="+transToD(m.get("brq"))+"," +
				" erq="+transToD(m.get("erq"))+",yx='"+m.get("yx")+"',sxzy='"+m.get("sxzy")+"'," +
				"xl='"+m.get("xl")+"',xw='"+m.get("xw")+"',byjy='"+m.get("byjy")+"' where rcid="+rcid+" and xh="+xh+"";
		update(true,sql);
		return 1;
	}
	/**
	 * 删除学习简历
	 * @param rcid
	 * @param xh
	 * @param status
	 * @param loginbz
	 * @return
	 */
	public int doXxjlD(String rcid,String[] xh){
		String sql = "";
		
		if(xh != null && xh.length > 0){
			sql = " delete from exp_xxjl where rcid="+rcid+"  and xh in ("+ArrayToString(xh)+")";
			update(true,sql);
		}
		
		return 1;
	}
	
	public int doXxjlDZj(String rcid,String[] xh){
		String sql = "";
		
		if(xh != null && xh.length > 0){
			sql = " delete from exp_xxjl_zj where rcid="+rcid+"  and xh in ("+ArrayToString(xh)+")";
			update(true,sql);
		}
		
		return 1;
	}
	
	/**
	 * 获取工作简历数据
	 */
	public List<Map<String, Object>> getGzjlList(String rcid){
		String sql_q = " ";
		List<Map<String, Object>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select CONVERT(varchar(100), a.brq, 23) brq,CONVERT(varchar(100), a.erq, 23) erq,a.dwbm,a.zw,a.xh,nowbz " +
					" from EXP_GZJL a " +
					" where a.rcid="+rcid+"  order by a.xh ";
			list = queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	
	public List<Map<String, Object>> getGzjlListZj(String rcid){
		String sql_q = " ";
		List<Map<String, Object>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select CONVERT(varchar(100), a.brq, 23) brq,CONVERT(varchar(100), a.erq, 23) erq,a.dwbm,a.zw,a.xh,nowbz " +
					" from EXP_GZJL_Zj a " +
					" where a.rcid="+rcid+"  order by a.xh ";
			list = queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	/**
	 * 工作简历新增操作
	 * @param rcid
	 * @param m
	 * @param loginbz
	 * @return
	 */
	public int doGzjlI(String rcid,Map<String, String > m ){
		String sql = "";
		
		Integer maxxh = 1;
		
		String nowbz = "0";
		if(m.get("nowbz") !=null ){
			nowbz = m.get("nowbz");
		}
		sql = "select count(*) from EXP_GZJL where rcid="+rcid +"  ";
		if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select max(xh) from EXP_GZJL where rcid="+rcid +" ";
			maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
		}
		sql = " insert into EXP_GZJL(rcid,xh,status,brq,erq,dwbm,zw,xzbz,nowbz) " +
			" values('"+rcid+"','"+maxxh+"',1,"+transToD(m.get("brq"))+","+transToD(m.get("erq"))+"," +
			"'"+m.get("dwbm")+"','"+m.get("zw")+"',1,"+nowbz+")";
		update(true,sql);
		return 1;
	}
	
	public int doGzjlIZj(String rcid,Map<String, String > m ){
		String sql = "";
		
		Integer maxxh = 1;
		
		String nowbz = "0";
		if(m.get("nowbz") !=null ){
			nowbz = m.get("nowbz");
		}
		sql = "select count(*) from EXP_GZJL_zj where rcid="+rcid +"  ";
		if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select max(xh) from EXP_GZJL_ZJ where rcid="+rcid +" ";
			maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
		}
		sql = " insert into EXP_GZJL_ZJ(rcid,xh,status,brq,erq,dwbm,zw,xzbz,nowbz) " +
			" values('"+rcid+"','"+maxxh+"',1,"+transToD(m.get("brq"))+","+transToD(m.get("erq"))+"," +
			"'"+m.get("dwbm")+"','"+m.get("zw")+"',1,"+nowbz+")";
		update(true,sql);
		return 1;
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
			" from EXP_GZJL a " +
			" where a.rcid="+rcid+" and a.xh="+xh;
		map = super.queryForMap(sql_q);
		return map;
	}
	
	public Map getGzjlUZj(String rcid,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select CONVERT(varchar(100), a.brq, 23) brq,CONVERT(varchar(100), a.erq, 23) erq,a.dwbm,a.zw,nowbz " +
			" from EXP_GZJL_zj a " +
			" where a.rcid="+rcid+" and a.xh="+xh;
		map = super.queryForMap(sql_q);
		return map;
	}
	
	/**
	 * 工作简历修改操作
	 * @param rcid
	 * @param xh
	 * @param loginbz
	 * @return
	 */
	public int doGzjlU(String rcid,String xh,Map<String, String > m ){
		String sql = "";
		
		String nowbz = "0";
		if(m.get("nowbz") !=null ){
			nowbz = m.get("nowbz");
		}
		
		sql = " update EXP_GZJL set brq="+transToD(m.get("brq"))+"," +
				" erq="+transToD(m.get("erq"))+",dwbm='"+m.get("dwbm")+"',zw='"+m.get("zw")+"',nowbz="+nowbz +
				"  where rcid="+rcid+" and xh="+xh+"";
		super.update(true,sql);
		return 1;
	}
	
	public int doGzjlUZj(String rcid,String xh,Map<String, String > m ){
		String sql = "";
		
		String nowbz = "0";
		if(m.get("nowbz") !=null ){
			nowbz = m.get("nowbz");
		}
		
		sql = " update EXP_GZJL_zj set brq="+transToD(m.get("brq"))+"," +
				" erq="+transToD(m.get("erq"))+",dwbm='"+m.get("dwbm")+"',zw='"+m.get("zw")+"',nowbz="+nowbz +
				"  where rcid="+rcid+" and xh="+xh+"";
		super.update(true,sql);
		return 1;
	}
	/**
	 * 删除工作简历
	 * @param rcid
	 * @param xh
	 * @param status
	 * @param loginbz
	 * @return
	 */
	public int doGzjlD(String rcid,String[] xh){
		String sql = "";
		
		if(xh != null && xh.length > 0){
			sql = " delete from EXP_GZJL where rcid="+rcid+" and xh in ("+ArrayToString(xh)+")";
			update(true,sql);
		}

		return 1;
	}	
	
	public int doGzjlDZj(String rcid,String[] xh){
		String sql = "";
		
		if(xh != null && xh.length > 0){
			sql = " delete from EXP_GZJL_ZJ where rcid="+rcid+" and xh in ("+ArrayToString(xh)+")";
			update(true,sql);
		}

		return 1;
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
					"  from EXP_KTXM a " +
					" where rcid="+rcid+" order by xh ";
			list = queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	public List<Map<String, Object>> getKtxmListZj(String rcid){
		String sql_q = " ";
		List<Map<String, Object>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select a.xmmc,a.xmly,a.wcqk, CONVERT(varchar(100), a.wcrq, 23) wcrq,CONVERT(varchar(100), a.wcendrq, 23) wcendrq,xh,zzje,kylws,kyzzs,kycz,kyzls,kyqtcg," +
					" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 19) AND (a.wcqk = DICTBH)) AS wcqk_mc, " +
					" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 24) AND (a.brzy = DICTBH)) AS brzy_mc," +
					" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 26) AND (a.xmly = DICTBH)) AS xmly_mc," +
					" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 28) AND (a.zzxmlb = DICTBH)) AS zzxmlb_mc" +
					"  from EXP_KTXM_Zj a " +
					" where rcid="+rcid+" order by xh ";
			list = queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	

	/**
	 * 课题项目新增操作
	 * @param rcid
	 * @param m
	 * @param loginbz
	 * @return
	 */
	public int doKtxmI(String rcid,Map<String, String > m ){
		String sql = "";
		
		Integer maxxh = 1;
		sql = "select count(*) from EXP_KTXM where rcid="+rcid +" ";
		if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select max(xh) from EXP_KTXM where rcid="+rcid +"  ";
			maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
		}
		sql = " insert into EXP_KTXM(rcid,xh,status,xmmc,xmly,wcqk,wcrq,lx,xzbz,brzy,wcendrq,zzxmlb,zzje,kylws,kyzzs,kycz,kyzls,kyqtcg) " +
			" values('"+rcid+"','"+maxxh+"',1,'"+m.get("xmmc")+"','"+m.get("xmly")+"'," +
			"'"+m.get("wcqk")+"',"+transToD(m.get("wcrq"))+",null,1,'"+m.get("brzy")+"'," +
					""+transToD(m.get("wcendrq"))+",'"+m.get("zzxmlb")+"','"+transToN(m.get("zzje"))+"'," +
					"'"+transToN(m.get("kylws"))+"','"+transToN(m.get("kyzzs"))+"','"+transToN(m.get("kycz"))+"','"+transToN(m.get("kyzls"))+"','"+m.get("kyqtcg")+"')";
		update(true,sql);
		return 1;
	}
	
	public int doKtxmIZj(String rcid,Map<String, String > m ){
		String sql = "";
		
		Integer maxxh = 1;
		sql = "select count(*) from EXP_KTXM_zj where rcid="+rcid +" ";
		if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select max(xh) from EXP_KTXM_zj where rcid="+rcid +"  ";
			maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
		}
		sql = " insert into EXP_KTXM_zj(rcid,xh,status,xmmc,xmly,wcqk,wcrq,lx,xzbz,brzy,wcendrq,zzxmlb,zzje,kylws,kyzzs,kycz,kyzls,kyqtcg) " +
			" values('"+rcid+"','"+maxxh+"',1,'"+m.get("xmmc")+"','"+m.get("xmly")+"'," +
			"'"+m.get("wcqk")+"',"+transToD(m.get("wcrq"))+",null,1,'"+m.get("brzy")+"'," +
					""+transToD(m.get("wcendrq"))+",'"+m.get("zzxmlb")+"','"+transToN(m.get("zzje"))+"'," +
					"'"+transToN(m.get("kylws"))+"','"+transToN(m.get("kyzzs"))+"','"+transToN(m.get("kycz"))+"','"+transToN(m.get("kyzls"))+"','"+m.get("kyqtcg")+"')";
		update(true,sql);
		return 1;
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
				"CONVERT(varchar(100), wcendrq, 23) wcendrq,zzxmlb,zzje,kylws,kyzzs,kycz,kyzls,kyqtcg, " +
		" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 19) AND (a.wcqk = DICTBH)) AS wcqk_mc, " +
		" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 24) AND (a.brzy = DICTBH)) AS brzy_mc," +
		" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 26) AND (a.xmly = DICTBH)) AS xmly_mc," +
		" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 28) AND (a.zzxmlb = DICTBH)) AS zzxmlb_mc" +
			" from EXP_KTXM a " +
			" where rcid="+rcid+"  and xh="+xh;
		map = queryForMap(sql_q);
		return map;
	}
	
	public Map getKtxmUZj(String rcid,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select xmmc,xmly,wcqk, CONVERT(varchar(100), wcrq, 23) wcrq,brzy," +
				"CONVERT(varchar(100), wcendrq, 23) wcendrq,zzxmlb,zzje,kylws,kyzzs,kycz,kyzls,kyqtcg, " +
		" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 19) AND (a.wcqk = DICTBH)) AS wcqk_mc, " +
		" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 24) AND (a.brzy = DICTBH)) AS brzy_mc," +
		" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 26) AND (a.xmly = DICTBH)) AS xmly_mc," +
		" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 28) AND (a.zzxmlb = DICTBH)) AS zzxmlb_mc" +
			" from EXP_KTXM_ZJ a " +
			" where rcid="+rcid+"  and xh="+xh;
		map = queryForMap(sql_q);
		return map;
	}
	/**
	 * 课题项目修改操作
	 * @param rcid
	 * @param xh
	 * @param loginbz
	 * @return
	 */
	public int doKtxmU(String rcid,String xh,Map<String, String > m ){
		String sql = "";
		sql = " update EXP_KTXM set wcrq="+transToD(m.get("wcrq"))+"," +
			" xmmc='"+m.get("xmmc")+"',xmly='"+m.get("xmly")+"',wcqk='"+m.get("wcqk")+"',brzy='"+m.get("brzy")+"'," +
			" wcendrq="+transToD(m.get("wcendrq"))+",zzxmlb='"+m.get("zzxmlb")+"'," +
			" zzje='"+transToN(m.get("zzje"))+"',kylws='"+transToN(m.get("kylws"))+"'," +
			" kyzzs='"+transToN(m.get("kyzzs"))+"',kycz='"+transToN(m.get("kycz"))+"',kyzls='"+transToN(m.get("kyzls"))+"'," +
			" kyqtcg='"+m.get("kyqtcg")+"'" +
			"  where rcid="+rcid+" and xh="+xh+"";
		super.update(true,sql);
		return 1;
	}
	
	public int doKtxmUZj(String rcid,String xh,Map<String, String > m ){
		String sql = "";
		sql = " update EXP_KTXM_ZJ set wcrq="+transToD(m.get("wcrq"))+"," +
			" xmmc='"+m.get("xmmc")+"',xmly='"+m.get("xmly")+"',wcqk='"+m.get("wcqk")+"',brzy='"+m.get("brzy")+"'," +
			" wcendrq="+transToD(m.get("wcendrq"))+",zzxmlb='"+m.get("zzxmlb")+"'," +
			" zzje='"+transToN(m.get("zzje"))+"',kylws='"+transToN(m.get("kylws"))+"'," +
			" kyzzs='"+transToN(m.get("kyzzs"))+"',kycz='"+transToN(m.get("kycz"))+"',kyzls='"+transToN(m.get("kyzls"))+"'," +
			" kyqtcg='"+m.get("kyqtcg")+"'" +
			"  where rcid="+rcid+" and xh="+xh+"";
		super.update(true,sql);
		return 1;
	}
	/**
	 * 删除课题项目
	 * @param rcid
	 * @param xh
	 * @param status
	 * @param loginbz
	 * @return
	 */
	public int doKtxmD(String rcid,String[] xh){
		String sql = "";
		
		if(xh != null && xh.length > 0){
			sql = " delete from EXP_KTXM where rcid="+rcid+" and xh in ("+ArrayToString(xh)+")";
			update(true,sql);
		}
		return 1;
	}		
	
	public int doKtxmDZj(String rcid,String[] xh){
		String sql = "";
		
		if(xh != null && xh.length > 0){
			sql = " delete from EXP_KTXM_ZJ where rcid="+rcid+" and xh in ("+ArrayToString(xh)+")";
			update(true,sql);
		}
		return 1;
	}		
	/**
	 * 获取荣誉称号及获奖情况数据
	 */
	public List<Map<String, Object>> getRyhjList(String rcid){
		String sql_q = " ";
		List<Map<String, Object>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select XH,JXMC,HJDJ,(select dictname from xt_dict " +
					"where lbid=32 and dictbh=EXP_RYHJ.hjdj) HJDJ_MC ,CONVERT(varchar(100), HJRQ, 23) HJRQ,BJBM " +
					" from EXP_RYHJ " +
					" where rcid="+rcid+" order by xh ";
			list = queryForList(sql_q, new Object[]{});
		}
		return list;
	}

	public List<Map<String, Object>> getRyhjListZj(String rcid){
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
	 * 获取荣誉称号及获奖情况新增操作
	 * @param rcid
	 * @param m
	 * @param loginbz
	 * @return
	 */
	public int doRyhjI(String rcid,Map<String, String > m){
		String sql = "";
		
		Integer maxxh = 1;
		sql = "select count(*) from EXP_RYHJ where rcid="+rcid +"";
		if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select max(xh) from EXP_RYHJ where rcid="+rcid +"  ";
			maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
		}
		sql = " insert into EXP_RYHJ(rcid,xh,status,jxmc,hjdj,hjrq,bjbm) " +
			" values('"+rcid+"','"+maxxh+"',1,'"+m.get("jxmc")+"','"+m.get("hjdj")+"'," +
			""+transToD(m.get("hjrq"))+",'"+m.get("bjbm")+"' )";
		update(true,sql);
		return 1;
	}
	
	public int doRyhjIZj(String rcid,Map<String, String > m){
		String sql = "";
		
		Integer maxxh = 1;
		sql = "select count(*) from EXP_RYHJ_ZJ where rcid="+rcid +"";
		if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select max(xh) from EXP_RYHJ_ZJ where rcid="+rcid +"  ";
			maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
		}
		sql = " insert into EXP_RYHJ_ZJ(rcid,xh,status,jxmc,hjdj,hjrq,bjbm) " +
			" values('"+rcid+"','"+maxxh+"',1,'"+m.get("jxmc")+"','"+m.get("hjdj")+"'," +
			""+transToD(m.get("hjrq"))+",'"+m.get("bjbm")+"' )";
		update(true,sql);
		return 1;
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
		sql_q = " select JXMC,HJDJ, CONVERT(varchar(100), HJRQ, 23) HJRQ,BJBM ," +
				" (select dictname from xt_dict where lbid=32 and dictbh=EXP_RYHJ.hjdj) HJDJ_MC " +
			" from EXP_RYHJ " +
			" where rcid="+rcid+" and  xh="+xh;
		map = queryForMap(sql_q);
		return map;
	}
	
	public Map getRyhjUZj(String rcid,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select JXMC,HJDJ, CONVERT(varchar(100), HJRQ, 23) HJRQ,BJBM ," +
				" (select dictname from xt_dict where lbid=32 and dictbh=EXP_RYHJ_ZJ.hjdj) HJDJ_MC " +
			" from EXP_RYHJ_ZJ " +
			" where rcid="+rcid+" and  xh="+xh;
		map = queryForMap(sql_q);
		return map;
	}
	/**
	 * 获取荣誉称号及获奖情况修改操作
	 * @param rcid
	 * @param xh
	 * @param loginbz
	 * @return
	 */
	public int doRyhjU(String rcid,String xh,Map<String, String > m){
		String sql = "";

		sql = " update EXP_RYHJ set hjrq="+transToD(m.get("hjrq"))+"," +
				" jxmc='"+m.get("jxmc")+"',hjdj='"+m.get("hjdj")+"',bjbm='"+m.get("bjbm")+"' " +
				"  where rcid="+rcid+" and xh="+xh+" ";
		update(true,sql);
		return 1;
	}
	
	public int doRyhjUZj(String rcid,String xh,Map<String, String > m){
		String sql = "";

		sql = " update EXP_RYHJ_zj set hjrq="+transToD(m.get("hjrq"))+"," +
				" jxmc='"+m.get("jxmc")+"',hjdj='"+m.get("hjdj")+"',bjbm='"+m.get("bjbm")+"' " +
				"  where rcid="+rcid+" and xh="+xh+" ";
		update(true,sql);
		return 1;
	}
	/**
	 * 删除获取荣誉称号及获奖情况
	 * @param rcid
	 * @param xh
	 * @param status
	 * @param loginbz
	 * @return
	 */
	public int doRyhjD(String rcid,String[] xh ){
		String sql = "";
		
		if(xh != null && xh.length > 0){
			sql = " delete from EXP_RYHJ where rcid="+rcid+"  and xh in ("+ArrayToString(xh)+")";
			update(true,sql);
		}
		
		return 1;
	}		
	
	public int doRyhjDZj(String rcid,String[] xh ){
		String sql = "";
		
		if(xh != null && xh.length > 0){
			sql = " delete from EXP_RYHJ_zj where rcid="+rcid+"  and xh in ("+ArrayToString(xh)+")";
			update(true,sql);
		}
		
		return 1;
	}		
	/**
	 * 获取社会兼、聘职情况数据
	 */
	public List<Map<String, Object>> getShjzList(String rcid){
		String sql_q = " ";
		List<Map<String, Object>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select CONVERT(varchar(100), a.brq, 23) brq,CONVERT(varchar(100), a.erq, 23) erq,a.jsdw,a.jssf,a.sm,a.xh " +
					" from EXP_SHJZ a " +
					" where a.rcid="+rcid+" order by a.xh ";
			list = queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	
	public List<Map<String, Object>> getShjzListZj(String rcid){
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
	 * 社会兼、聘职情况新增操作
	 * @param rcid
	 * @param m
	 * @param loginbz
	 * @return
	 */
	public int doShjzI(String rcid,Map<String, String > m ){
		String sql = "";
		
		Integer maxxh = 1;
		sql = "select count(*) from EXP_SHJZ where rcid="+rcid +" and status=1 ";
		if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select max(xh) from EXP_SHJZ where rcid="+rcid +" and status=1 ";
			maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
		}
		sql = " insert into EXP_SHJZ(rcid,xh,status,brq,erq,jsdw,jssf,sm,xzbz) " +
			" values('"+rcid+"','"+maxxh+"',1,"+transToD(m.get("brq"))+","+transToD(m.get("erq"))+"," +
					"'"+m.get("jsdw")+"','"+m.get("jssf")+"','"+m.get("sm")+"',1)";
		update(true,sql);
		
		return 1;
	}
	
	public int doShjzIZj(String rcid,Map<String, String > m ){
		String sql = "";
		
		Integer maxxh = 1;
		sql = "select count(*) from EXP_SHJZ_ZJ where rcid="+rcid +" and status=1 ";
		if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select max(xh) from EXP_SHJZ_ZJ where rcid="+rcid +" and status=1 ";
			maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
		}
		sql = " insert into EXP_SHJZ_ZJ(rcid,xh,status,brq,erq,jsdw,jssf,sm,xzbz) " +
			" values('"+rcid+"','"+maxxh+"',1,"+transToD(m.get("brq"))+","+transToD(m.get("erq"))+"," +
					"'"+m.get("jsdw")+"','"+m.get("jssf")+"','"+m.get("sm")+"',1)";
		update(true,sql);
		
		return 1;
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
			" from EXP_SHJZ a " +
			" where a.rcid="+rcid+" and a.xh="+xh;
		map = queryForMap(sql_q);
		return map;
	}
	
	public Map getShjzUZj(String rcid,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select CONVERT(varchar(100), a.brq, 23) brq,CONVERT(varchar(100), a.erq, 23) erq,a.jsdw,a.jssf,a.sm " +
			" from EXP_SHJZ_ZJ a " +
			" where a.rcid="+rcid+" and a.xh="+xh;
		map = queryForMap(sql_q);
		return map;
	}
	
	/**
	 * 社会兼、聘职情况修改操作
	 * @param rcid
	 * @param xh
	 * @param loginbz
	 * @return
	 */
	public int doShjzU(String rcid,String xh,Map<String, String > m){
		String sql = "";
		sql = " update EXP_SHJZ set brq="+transToD(m.get("brq"))+"," +
			" erq="+transToD(m.get("erq"))+",jsdw='"+m.get("jsdw")+"',jssf='"+m.get("jssf")+"'" +
				",sm='"+m.get("sm")+"' " +
				"  where rcid="+rcid+" and xh="+xh+" ";
		update(true,sql);
		return 1;
	}
	
	public int doShjzUZj(String rcid,String xh,Map<String, String > m){
		String sql = "";
		sql = " update EXP_SHJZ_zj set brq="+transToD(m.get("brq"))+"," +
			" erq="+transToD(m.get("erq"))+",jsdw='"+m.get("jsdw")+"',jssf='"+m.get("jssf")+"'" +
				",sm='"+m.get("sm")+"' " +
				"  where rcid="+rcid+" and xh="+xh+" ";
		update(true,sql);
		return 1;
	}
	/**
	 * 删除社会兼、聘职情况
	 * @param rcid
	 * @param xh
	 * @param status
	 * @param loginbz
	 * @return
	 */
	public int doShjzD(String rcid,String[] xh){
		String sql = "";
		
		if(xh != null && xh.length > 0){
			sql = " delete from EXP_SHJZ where rcid="+rcid+"  and xh in ("+ArrayToString(xh)+")";
			update(true,sql);
		}
		
		return 1;
	}		
	
	public int doShjzDZj(String rcid,String[] xh){
		String sql = "";
		
		if(xh != null && xh.length > 0){
			sql = " delete from EXP_SHJZ_zj where rcid="+rcid+"  and xh in ("+ArrayToString(xh)+")";
			update(true,sql);
		}
		
		return 1;
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
					" from EXP_BPZJ a " +
					" where a.rcid="+rcid+"  order by a.xh ";
			list = queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	public List<Map<String, Object>> getBpzjListZj(String rcid){
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
	 * 检查创新 、创业中是否存在信息
	 * @param rcid
	 * @param xh
	 * @return
	 */
	public int preCheckCxCyrcxx(String rcid,String[] xh,String status){
		int i=0;
		String sql = "select count(*) from rc_cxrcxx where rcid='"+rcid+"' and " +
				" lbdm in (select bpzjqk from EXP_BPZJ where rcid='"+rcid+"' and" +
						"  xh in ("+ArrayToString(xh)+") and status="+status+") and status="+status+" ";
		i = this.getSimpleJdbcTemplate().queryForInt(sql);
		if(i==0){
			sql = "select count(*) from rc_cyrcxx where rcid='"+rcid+"' and " +
			" lbdm in (select bpzjqk from EXP_BPZJ where rcid='"+rcid+"' and" +
					"  xh in ("+ArrayToString(xh)+") and status="+status+") and status="+status+" ";
			i = this.getSimpleJdbcTemplate().queryForInt(sql);
		}
		return i;
	}
	
	/**
	 * 被聘专家情况数据新增操作
	 * @param rcid
	 * @param m
	 * @param loginbz
	 * SHBZ  2: 是已审的， 0 ：待审的 
	 * loginbz =1: SHBZ 直接2（代表已审）， 人才 就 0 ：需要待审
	 * @return  
	 */
	public int doBpzjI(String rcid,Map<String, String > m ,String[] bpzjqk){
		String sql = "";
		for( int i=0;i<bpzjqk.length;i++){
			if(bpzjqk[i].equals("011") || bpzjqk[i].equals("012")){
				sql = " select count(*) from EXP_BPZJ where rcid="+rcid+" and bpzjqk in ('011','012')";
				if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
					throw new BusException("同一层次的创业创新人才不能同时复选");
				}
			}
			if(bpzjqk[i].equals("013") || bpzjqk[i].equals("014")){
				sql = " select count(*) from EXP_BPZJ where rcid="+rcid+" and bpzjqk in ('013','014')";
				if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
					throw new BusException("同一层次的创业创新人才不能同时复选");
				}			
			}
			if(bpzjqk[i].equals("015") || bpzjqk[i].equals("016")){
				sql = " select count(*) from EXP_BPZJ where rcid="+rcid+" and bpzjqk in ('015','016')";
				if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
					throw new BusException("同一层次的创业创新人才不能同时复选");
				}
			}
			
			Integer maxxh = 1;
			sql = "select count(*) from EXP_BPZJ where rcid="+rcid +" and status=1 ";
			if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
				sql = " select max(xh) from EXP_BPZJ where rcid="+rcid +" and status=1 ";
				maxxh = queryForInt(sql)+1;
			}
			sql = " insert into EXP_BPZJ(rcid,xh,status,rxnf,zjlb,sm,xzbz,bpzjqk,SHBZ) " +
				" values('"+rcid+"','"+maxxh+"',1,'"+m.get("rxnf")+"','"+m.get("zjlb")+"'," +
						"'"+m.get("sm")+"',1,'"+bpzjqk[i]+"',1)";
			update(true,sql);
		}
		
		return 1;
	}
	
	public int doBpzjIZj(String rcid,Map<String, String > m ,String[] bpzjqk){
		String sql = "";
		for( int i=0;i<bpzjqk.length;i++){
			if(bpzjqk[i].equals("011") || bpzjqk[i].equals("012")){
				sql = " select count(*) from EXP_BPZJ_zj where rcid="+rcid+" and bpzjqk in ('011','012')";
				if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
					throw new BusException("同一层次的创业创新人才不能同时复选");
				}
			}
			if(bpzjqk[i].equals("013") || bpzjqk[i].equals("014")){
				sql = " select count(*) from EXP_BPZJ_zj where rcid="+rcid+" and bpzjqk in ('013','014')";
				if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
					throw new BusException("同一层次的创业创新人才不能同时复选");
				}			
			}
			if(bpzjqk[i].equals("015") || bpzjqk[i].equals("016")){
				sql = " select count(*) from EXP_BPZJ_zj where rcid="+rcid+" and bpzjqk in ('015','016')";
				if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
					throw new BusException("同一层次的创业创新人才不能同时复选");
				}
			}
			
			Integer maxxh = 1;
			sql = "select count(*) from EXP_BPZJ_zj where rcid="+rcid +" and status=1 ";
			if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
				sql = " select max(xh) from EXP_BPZJ_zj where rcid="+rcid +" and status=1 ";
				maxxh = queryForInt(sql)+1;
			}
			sql = " insert into EXP_BPZJ_zj(rcid,xh,status,rxnf,zjlb,sm,xzbz,bpzjqk,SHBZ) " +
				" values('"+rcid+"','"+maxxh+"',1,'"+m.get("rxnf")+"','"+m.get("zjlb")+"'," +
						"'"+m.get("sm")+"',1,'"+bpzjqk[i]+"',1)";
			update(true,sql);
		}
		
		return 1;
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
		sql_q = " select a.rxnf,a.zjlb,a.sm,a.bpzjqk,(select dictname from xt_dict b where a.bpzjqk=b.dictbh and b.lbid=25) bpzjqk_mc " +
			" from EXP_BPZJ a " +
			" where a.rcid="+rcid+" and a.xh ="+xh;
		map = queryForMap(sql_q);
		return map;
	}
	
	public Map getBpzjUZj(String rcid,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select a.rxnf,a.zjlb,a.sm,a.bpzjqk,(select dictname from xt_dict b where a.bpzjqk=b.dictbh and b.lbid=25) bpzjqk_mc " +
			" from EXP_BPZJ_zj a " +
			" where a.rcid="+rcid+" and a.xh ="+xh;
		map = queryForMap(sql_q);
		return map;
	}
	/**
	 * 被聘专家情况数据修改操作
	 * @param rcid
	 * @param xh
	 * @param loginbz
	 * @return
	 */
	public int doBpzjU(String rcid,String xh,Map<String, String > m, String[] bpzjqk){
		String sql = "";
		
		if(bpzjqk[0].equals("011") || bpzjqk[0].equals("012")){
			sql = " select count(*) from EXP_BPZJ where rcid="+rcid+" and bpzjqk in ('011','012') and bpzjqk!='"+bpzjqk[0]+"'";
			if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
				throw new BusException("同一层次的创业创新人才不能同时复选");
			}
		}
		if(bpzjqk[0].equals("013") || bpzjqk[0].equals("014")){
			sql = " select count(*) from EXP_BPZJ where rcid="+rcid+" and bpzjqk in ('013','014') and bpzjqk!='"+bpzjqk[0]+"'";
			if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
				throw new BusException("同一层次的创业创新人才不能同时复选");
			}			
		}
		if(bpzjqk[0].equals("015") || bpzjqk[0].equals("016")){
			sql = " select count(*) from EXP_BPZJ where rcid="+rcid+" and bpzjqk in ('015','016') and bpzjqk!='"+bpzjqk[0]+"'";
			if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
				throw new BusException("同一层次的创业创新人才不能同时复选");
			}
		}
		sql = " update EXP_BPZJ set rxnf='"+m.get("rxnf")+"',zjlb='"+m.get("zjlb")+"'" +
			",sm='"+m.get("sm")+"',bpzjqk='"+bpzjqk[0]+"' " +
			"  where rcid="+rcid+" and xh="+xh+"";
		update(true,sql);
		return 1;
	}
	
	public int doBpzjUZj(String rcid,String xh,Map<String, String > m, String[] bpzjqk){
		String sql = "";
		
		if(bpzjqk[0].equals("011") || bpzjqk[0].equals("012")){
			sql = " select count(*) from EXP_BPZJ_ZJ where rcid="+rcid+" and bpzjqk in ('011','012') and bpzjqk!='"+bpzjqk[0]+"'";
			if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
				throw new BusException("同一层次的创业创新人才不能同时复选");
			}
		}
		if(bpzjqk[0].equals("013") || bpzjqk[0].equals("014")){
			sql = " select count(*) from EXP_BPZJ_ZJ where rcid="+rcid+" and bpzjqk in ('013','014') and bpzjqk!='"+bpzjqk[0]+"'";
			if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
				throw new BusException("同一层次的创业创新人才不能同时复选");
			}			
		}
		if(bpzjqk[0].equals("015") || bpzjqk[0].equals("016")){
			sql = " select count(*) from EXP_BPZJ_ZJ where rcid="+rcid+" and bpzjqk in ('015','016') and bpzjqk!='"+bpzjqk[0]+"'";
			if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
				throw new BusException("同一层次的创业创新人才不能同时复选");
			}
		}
		sql = " update EXP_BPZJ_ZJ set rxnf='"+m.get("rxnf")+"',zjlb='"+m.get("zjlb")+"'" +
			",sm='"+m.get("sm")+"',bpzjqk='"+bpzjqk[0]+"' " +
			"  where rcid="+rcid+" and xh="+xh+"";
		update(true,sql);
		return 1;
	}
	/**
	 * 删除被聘专家情况数据
	 * @param rcid
	 * @param xh
	 * @param status
	 * @param loginbz
	 * @return
	 */
	public int doBpzjD(String rcid,String[] xh){
		String sql = "";
		if(xh != null && xh.length > 0){
			sql = " delete from EXP_BPZJ where rcid="+rcid+"  and xh in ("+ArrayToString(xh)+")";
			update(true,sql);
		}
		return 1;
	}	
	
	public int doBpzjDZj(String rcid,String[] xh){
		String sql = "";
		if(xh != null && xh.length > 0){
			sql = " delete from EXP_BPZJ_ZJ where rcid="+rcid+"  and xh in ("+ArrayToString(xh)+")";
			update(true,sql);
		}
		return 1;
	}	
	/**
	 * 获取主要论著论文数据
	 */
	public List<Map<String, Object>> getZylzList(String rcid){
		String sql_q = " ";
		List<Map<String, Object>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select xh,zzmc,smno,cbmc,CONVERT(varchar(100), cbrq, 23) cbrq ," +
					" (select dictname from xt_dict where lbid=31 and dictbh=EXP_ZYLZ.smno) smno_mc" +
					" from EXP_ZYLZ  " +
					" where rcid="+rcid+" order by xh ";
			list = queryForList(sql_q, new Object[]{});
		}
		return list;
	}

	public List<Map<String, Object>> getZylzListZj(String rcid){
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
	 * 主要论著论文新增操作
	 * @param rcid
	 * @param m
	 * @param loginbz
	 * @return
	 */
	public int doZylzI(String rcid,Map<String, String > m ){
		String sql = "";

		Integer maxxh = 1;
		sql = "select count(*) from EXP_ZYLZ where rcid="+rcid +" ";
		if( queryForInt(sql) > 0){
			sql = " select max(xh) from EXP_ZYLZ where rcid="+rcid +" ";
			maxxh = queryForInt(sql)+1;
		}
		sql = " insert into EXP_ZYLZ(rcid,xh,status,zzmc,smno,cbmc,cbrq,xzbz) " +
			" values('"+rcid+"','"+maxxh+"',1,?,'"+m.get("smno")+"'," +
					"?,"+transToD(m.get("cbrq"))+","+1+")";
		update(true,sql,new Object[]{m.get("zzmc"),m.get("cbmc")});
		return 1;
	}
	
	public int doZylzIZj(String rcid,Map<String, String > m ){
		String sql = "";

		Integer maxxh = 1;
		sql = "select count(*) from EXP_ZYLZ_ZJ where rcid="+rcid +" ";
		if( queryForInt(sql) > 0){
			sql = " select max(xh) from EXP_ZYLZ_ZJ where rcid="+rcid +" ";
			maxxh = queryForInt(sql)+1;
		}
		sql = " insert into EXP_ZYLZ_ZJ(rcid,xh,status,zzmc,smno,cbmc,cbrq,xzbz) " +
			" values('"+rcid+"','"+maxxh+"',1,?,'"+m.get("smno")+"'," +
					"?,"+transToD(m.get("cbrq"))+","+1+")";
		update(true,sql,new Object[]{m.get("zzmc"),m.get("cbmc")});
		return 1;
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
			",(select dictname from xt_dict where lbid=31 and dictbh=EXP_ZYLZ.smno) smno_mc from EXP_ZYLZ  " +
			" where rcid="+rcid+" and xh="+xh;
		map = queryForMap(sql_q);
		return map;
	}
	
	public Map getZylzUZj(String rcid,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select zzmc,smno,cbmc,CONVERT(varchar(100), cbrq, 23) cbrq " +
			",(select dictname from xt_dict where lbid=31 and dictbh=EXP_ZYLZ_ZJ.smno) smno_mc from EXP_ZYLZ_ZJ  " +
			" where rcid="+rcid+" and xh="+xh;
		map = queryForMap(sql_q);
		return map;
	}
	
	/**
	 * 主要论著论文修改操作
	 * @param rcid
	 * @param xh
	 * @param loginbz
	 * @return
	 */
	public int doZylzU(String rcid,String xh,Map<String, String > m ){
		String sql = "";
		
		sql = " update EXP_ZYLZ set zzmc=?,smno='"+m.get("smno")+"'" +
						",cbmc=?,cbrq="+transToD(m.get("cbrq"))+" " +
				"  where rcid="+rcid+" and xh="+xh+" ";
		update(true,sql,new Object[]{m.get("zzmc"),m.get("cbmc")});
		return 1;
	}
	
	public int doZylzUZj(String rcid,String xh,Map<String, String > m ){
		String sql = "";
		
		sql = " update EXP_ZYLZ_ZJ set zzmc=?,smno='"+m.get("smno")+"'" +
						",cbmc=?,cbrq="+transToD(m.get("cbrq"))+" " +
				"  where rcid="+rcid+" and xh="+xh+" ";
		update(true,sql,new Object[]{m.get("zzmc"),m.get("cbmc")});
		return 1;
	}
	/**
	 * 删除主要论著论文
	 * @param rcid
	 * @param xh
	 * @param status
	 * @param loginbz
	 * @return
	 */
	public int doZylzD(String rcid,String[] xh){
		String sql = "";
		if(xh != null && xh.length > 0){
			sql = " delete from EXP_ZYLZ where rcid="+rcid+"  and xh in ("+ArrayToString(xh)+")";
			update(true,sql);
		}
		return 1;
	}	
	
	public int doZylzDZj(String rcid,String[] xh){
		String sql = "";
		if(xh != null && xh.length > 0){
			sql = " delete from EXP_ZYLZ_ZJ where rcid="+rcid+"  and xh in ("+ArrayToString(xh)+")";
			update(true,sql);
		}
		return 1;
	}	
	
	
	/**
	 * 获取知识产权情况数据
	 */
	public List<Map<String, Object>> getZscqList(String rcid){
		String sql_q = " ";
		List<Map<String, Object>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select a.xh,a.zsbh,a.sqr,a.qlr,a.zsmc,a.zsno,a.sm ,CONVERT(varchar(100), a.hdrq, 23) hdrq,iscz,zcdd," +
					" (SELECT  DICTNAME  FROM  XT_DICT AS b  WHERE   (LBID = 17) AND (a.zsno = DICTBH)) AS zsno_mc " +
					" from EXP_ZSCQ  a " +
					" where a.rcid="+rcid+"  order by a.xh ";
			list = queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	
	public List<Map<String, Object>> getZscqListZj(String rcid){
		String sql_q = " ";
		List<Map<String, Object>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select a.xh,a.zsbh,a.sqr,a.qlr,a.zsmc,a.zsno,a.sm ,CONVERT(varchar(100), a.hdrq, 23) hdrq,iscz,zcdd," +
					" (SELECT  DICTNAME  FROM  XT_DICT AS b  WHERE   (LBID = 17) AND (a.zsno = DICTBH)) AS zsno_mc " +
					" from EXP_ZSCQ_ZJ  a " +
					" where a.rcid="+rcid+"  order by a.xh ";
			list = queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	
	/**
	 * 存知识产权情况新增操作
	 * @param rcid
	 * @param m
	 * @param loginbz
	 * @return
	 */
	public int doZscqI(String rcid,Map<String, String > m ){
		String sql = "";
		
		Integer maxxh = 1;
		sql = "select count(*) from EXP_ZSCQ where rcid="+rcid +" ";
		if( queryForInt(sql) > 0){
			sql = " select max(xh) from EXP_ZSCQ where rcid="+rcid +"  ";
			maxxh = queryForInt(sql)+1;
		}
		sql = " insert into EXP_ZSCQ(rcid,xh,status,zsbh,sqr,qlr,zsmc,zsno,hdrq,sm,xzbz,zcdd,iscz) " +
			" values('"+rcid+"','"+maxxh+"',1,'"+m.get("zsbh")+"','"+m.get("sqr")+"','"+m.get("qlr")+"','"+m.get("zsmc")+"','"+m.get("zsno")+"'," +
					" "+transToD(m.get("hdrq"))+",'"+m.get("sm")+"',1,'"+m.get("zcdd")+"','"+m.get("iscz")+"')";
		update(true,sql);
		return 1;
	}
	
	public int doZscqIZj(String rcid,Map<String, String > m ){
		String sql = "";
		
		Integer maxxh = 1;
		sql = "select count(*) from EXP_ZSCQ_ZJ where rcid="+rcid +" ";
		if( queryForInt(sql) > 0){
			sql = " select max(xh) from EXP_ZSCQ_ZJ where rcid="+rcid +"  ";
			maxxh = queryForInt(sql)+1;
		}
		sql = " insert into EXP_ZSCQ_ZJ(rcid,xh,status,zsbh,sqr,qlr,zsmc,zsno,hdrq,sm,xzbz,zcdd,iscz) " +
			" values('"+rcid+"','"+maxxh+"',1,'"+m.get("zsbh")+"','"+m.get("sqr")+"','"+m.get("qlr")+"','"+m.get("zsmc")+"','"+m.get("zsno")+"'," +
					" "+transToD(m.get("hdrq"))+",'"+m.get("sm")+"',1,'"+m.get("zcdd")+"','"+m.get("iscz")+"')";
		update(true,sql);
		return 1;
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
		sql_q = " select xh,zsbh,sqr,qlr,zsmc,zsno,sm ,CONVERT(varchar(100), hdrq, 23) hdrq,zcdd,iscz," +
				"(SELECT  DICTNAME  FROM  XT_DICT AS b  WHERE   (LBID = 17) AND (a.zsno = DICTBH)) AS zsno_mc " +
			" from EXP_ZSCQ a " +
			" where rcid="+rcid+" and  xh="+xh;
		map = queryForMap(sql_q);
		return map;
	}
	
	public Map getZscqUZj(String rcid,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select xh,zsmc,zsbh,sqr,qlr,zsno,sm ,CONVERT(varchar(100), hdrq, 23) hdrq,zcdd,iscz," +
				"(SELECT  DICTNAME  FROM  XT_DICT AS b  WHERE   (LBID = 17) AND (a.zsno = DICTBH)) AS zsno_mc " +
			" from EXP_ZSCQ_ZJ a " +
			" where rcid="+rcid+" and  xh="+xh;
		map = queryForMap(sql_q);
		return map;
	}
	
	/**
	 * 存知识产权情况修改操作
	 * @param rcid
	 * @param xh
	 * @param loginbz
	 * @return
	 */
	public int doZscqU(String rcid,String xh,Map<String, String > m ){
		String sql = "";
		
		sql = " update EXP_ZSCQ set zsbh='"+m.get("zsbh")+"',sqr='"+m.get("sqr")+"',qlr='"+m.get("qlr")+"'," +
					"zsmc='"+m.get("zsmc")+"',zsno='"+m.get("zsno")+"'" +
						",sm='"+m.get("sm")+"',hdrq="+transToD(m.get("hdrq"))+"" +
						",zcdd='"+m.get("zcdd")+"',iscz='"+m.get("iscz")+"' " +
				"  where rcid="+rcid+" and xh="+xh+" ";
		update(true,sql);
		return 1;
	}
	
	public int doZscqUZj(String rcid,String xh,Map<String, String > m ){
		String sql = "";
		
		sql = " update EXP_ZSCQ_ZJ set zsbh='"+m.get("zsbh")+"',sqr='"+m.get("sqr")+"',qlr='"+m.get("qlr")+"'," +
					"zsmc='"+m.get("zsmc")+"',zsno='"+m.get("zsno")+"'" +
						",sm='"+m.get("sm")+"',hdrq="+transToD(m.get("hdrq"))+"" +
						",zcdd='"+m.get("zcdd")+"',iscz='"+m.get("iscz")+"' " +
				"  where rcid="+rcid+" and xh="+xh+" ";
		update(true,sql);
		return 1;
	}
	/**
	 * 删除存知识产权情况
	 * @param rcid
	 * @param xh
	 * @param status
	 * @param loginbz
	 * @return
	 */
	public int doZscqD(String rcid,String[] xh){
		String sql = "";
		
		if(xh != null && xh.length > 0){
			sql = " delete from EXP_ZSCQ where rcid="+rcid+" and xh in ("+ArrayToString(xh)+")";
			update(true,sql);
		}
		return 1;
	}	
	
	public int doZscqDZj(String rcid,String[] xh){
		String sql = "";
		
		if(xh != null && xh.length > 0){
			sql = " delete from EXP_ZSCQ_ZJ where rcid="+rcid+" and xh in ("+ArrayToString(xh)+")";
			update(true,sql);
		}
		return 1;
	}	
	/**
	 * 获取参加学术团及任职情况数据
	 */
	public List<Map<String, String>> getXsrzList(String rcid,String status){
		String sql_q = " ";
		List<Map<String, String>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select xh,dwbm,zw,CONVERT(varchar(100), brq, 23) brq,CONVERT(varchar(100), erq, 23) erq " +
					" from RC_XSRZ  " +
					" where rcid="+rcid+" and status=1 order by xh ";
			list = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	

	/**
	 * 参加学术团及任职情况新增操作
	 * @param rcid
	 * @param m
	 * @param loginbz
	 * @return
	 */
	public int doXsrzI(String rcid,Map<String, String > m , Integer loginbz,Integer zcbz){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from RC_XSRZ where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into RC_XSRZ(rcid,xh,status,brq,erq,dwbm,zw,xzbz ) " +
						" select rcid,xh,5,brq,erq,dwbm,zw,xzbz from RC_XSRZ where rcid="+rcid+" and status=1 and isnull(xzbz,1)=1 ";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		Integer maxxh = 1;
		sql = "select count(*) from RC_XSRZ where rcid="+rcid +" and status=1 ";
		if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select max(xh) from RC_XSRZ where rcid="+rcid +" and status=1 ";
			maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
		}
		sql = " insert into RC_XSRZ(rcid,xh,status,brq,erq,dwbm,zw,xzbz ) " +
			" values('"+rcid+"','"+maxxh+"',1,"+(m.get("brq").equals("")?"null":"'"+m.get("brq")+"'")+"," +
					""+(m.get("erq").equals("")?"null":"'"+m.get("erq")+"'")+",'"+m.get("dwbm")+"'," +
					" '"+m.get("zw")+"',"+loginbz+")";
		this.getSimpleJdbcTemplate().update(sql);
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_XSRZ'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
		}

		
		return 1;
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
			" from RC_XSRZ  " +
			" where rcid="+rcid+" and status=1 and xh="+xh;
		map = this.getSimpleJdbcTemplate().queryForMap(sql_q);
		return map;
	}
	
	/**
	 * 参加学术团及任职情况修改操作
	 * @param rcid
	 * @param xh
	 * @param loginbz
	 * @return
	 */
	public int doXsrzU(String rcid,String xh,Map<String, String > m , Integer loginbz,Integer zcbz){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from RC_XSRZ where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into RC_XSRZ(rcid,xh,status,brq,erq,dwbm,zw,xzbz ) " +
						" select rcid,xh,5,brq,erq,dwbm,zw,xzbz from RC_XSRZ where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		sql = " update RC_XSRZ set dwbm='"+m.get("dwbm")+"',zw='"+m.get("zw")+"'," +
						" brq="+(m.get("brq").equals("")?"null":"'"+m.get("brq")+"'")+"," +
						" erq="+(m.get("erq").equals("")?"null":"'"+m.get("erq")+"'")+" " +
				"  where rcid="+rcid+" and xh="+xh+" and status=1";
		this.getSimpleJdbcTemplate().update(sql);
		
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_XSRZ'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
		}

		
		return 1;
	}
	/**
	 * 删除参加学术团及任职情况
	 * @param rcid
	 * @param xh
	 * @param status
	 * @param loginbz
	 * @return
	 */
	public int doXsrzD(String rcid,String[] xh,Integer loginbz,Integer zcbz ){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from RC_XSRZ where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into RC_XSRZ(rcid,xh,status,brq,erq,dwbm,zw,xzbz ) " +
						" select rcid,xh,5,brq,erq,dwbm,zw,xzbz from RC_XSRZ where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		if(xh != null && xh.length > 0){
			sql = " delete from RC_XSRZ where rcid="+rcid+" and status=1 and xh in ("+ArrayToString(xh)+")";
			this.getSimpleJdbcTemplate().update(sql);
		}
		
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_XSRZ'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
		}

		
		return 1;
	}
	
	/**
	 * 保存参加学术团及任职情况
	 * @param rcid
	 * @param rcxx1 开始日期
	 * @param rcxx2 结束日期
	 * @param rcxx3 所在单位及部门
	 * @param rcxx4 职 务
	 * @return 
	 */
	public int doSaveXsrz(String rcid,Integer status,String[] rcxx1,String[] rcxx2,String[] rcxx3,String[] rcxx4){
		String sql_q = "",sql_o = "";
		int xh = 0;
		
		sql_q = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
		String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql_q,Object.class));
		
		sql_o = " delete from RC_XSRZ where rcid="+rcid+" and status="+status;
		this.getSimpleJdbcTemplate().update(sql_o);
		/**
		sql_q = " select count(*) from RC_XSRZ where rcid="+rcid;
		if(this.getSimpleJdbcTemplate().queryForInt(sql_q) >0){
			sql_q = " select max(xh) from RC_XSRZ where rcid="+rcid;
			xh = this.getSimpleJdbcTemplate().queryForInt(sql_q);
		}
		*/
		for(int i=0;i<rcxx1.length;i++){
			if(!rcxx3[i].trim().equals("")){//所在单位及部门非空才保存
				++xh;
				sql_o = " insert into RC_XSRZ(rcid,xh,status,brq,erq,dwbm,zw) " +
						" values('"+rcid+"','"+xh+"',"+status+","+(rcxx1[i].equals("")?"null":("'"+rcxx1[i]+"'"))+"," +
								""+(rcxx2[i].equals("")?"null":("'"+rcxx2[i]+"'"))+"," +
						"'"+rcxx3[i]+"','"+rcxx4[i]+"')";
				this.getSimpleJdbcTemplate().update(sql_o);
			}
		}
		if(status == 2){
			Map<String, Object> m = new HashMap();
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_XSRZ'");
			m.put("RCID", rcid);
			m.put("TABLEXH", cxh);
			Object cc = this.callProc("{call SET_TABLEBYRC(?,?,?)}", m);
			if( Integer.parseInt(cc.toString())==0){
				//没有变动
				sql_o = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"0"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			}else{
				sql_o = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			}
			this.getSimpleJdbcTemplate().update(sql_o);
			//this.callProcedure("{call SET_TABLEBYRC(?,?)}", m);
		}
		return 1;
	}
	
	
	/**
	 * 获取产品或技术产业化情况数据
	 */
	public List<Map<String, String>> getCxrcxxList(String rcid,String status){
		String sql_q = " ";
		List<Map<String, String>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select RCID,XH,STATUS,LBDM,CXXMMC,convert(varchar(100),RXSJ,23) RXSJ" +
					",ZZJE,convert(varchar(100),LCSJ,23) LCSJ,QYNX,QYGQ,QYJXTC," +
					"(select dictname from xt_dict where dictbh=rc_cxrcxx.lbdm and lbid=25 ) lbdm_mc  " +
					" from rc_cxrcxx  " +
					" where rcid="+rcid+" and status="+status+" order by xh ";
			list = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	
	public Map viewCxrcxxU(String rcid,String status,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select RCID,XH,STATUS,LBDM,CXXMMC,convert(varchar(100),RXSJ,23) RXSJ" +
					",ZZJE,convert(varchar(100),LCSJ,23) LCSJ,QYNX,QYGQ,QYJXTC," +
					"(select dictname from xt_dict where dictbh=rc_cxrcxx.lbdm and lbid=25 ) lbdm_mc  " +
			" from rc_cxrcxx " +
			" where rcid="+rcid+" and status="+status+" and xh="+xh;
		map = this.getSimpleJdbcTemplate().queryForMap(sql_q);
		return map;
	}
	
	public int doCxrcxxI(String rcid,Map<String, String > m , Integer loginbz,Integer zcbz ,String status ){
		String sql = "";
		
		Integer maxxh = 1;
		
		if(status.equals("1")){
			sql = "select count(*) from rc_cxrcxx where rcid="+rcid +" and status=1 ";
			if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
				sql = " select max(xh) from rc_cxrcxx where rcid="+rcid +" and status=1 ";
				maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
			}
			sql = " insert into rc_cxrcxx(RCID,XH,STATUS,LBDM,CXXMMC, RXSJ" +
						",ZZJE, LCSJ,QYNX,QYGQ,QYJXTC,xzbz) " +
				" values('"+rcid+"','"+maxxh+"',1,'"+m.get("lbdm")+"','"+m.get("cxxmmc")+"', "+transToD(m.get("rxsj"))+"," +
						"'"+transToN(m.get("zzje"))+"',"+transToD(m.get("lcsj"))+",'"+transToN(m.get("qynx"))+"'," +
						"'"+transToN(m.get("qygq"))+"','"+transToN(m.get("qyjxtc"))+"',"+loginbz+")";
			this.getSimpleJdbcTemplate().update(sql);
			
			sql = "select count(*) from rc_cxrcxx where rcid="+rcid +" and status=5 ";
			if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
				sql = " select max(xh) from rc_cxrcxx where rcid="+rcid +" and status=5 ";
				maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
			}
			sql = " insert into rc_cxrcxx(RCID,XH,STATUS,LBDM,CXXMMC, RXSJ" +
						",ZZJE, LCSJ,QYNX,QYGQ,QYJXTC,xzbz) " +
				" values('"+rcid+"','"+maxxh+"',5,'"+m.get("lbdm")+"','"+m.get("cxxmmc")+"', "+transToD(m.get("rxsj"))+"," +
						"'"+transToN(m.get("zzje"))+"',"+transToD(m.get("lcsj"))+",'"+transToN(m.get("qynx"))+"'," +
						"'"+transToN(m.get("qygq"))+"','"+transToN(m.get("qyjxtc"))+"',"+loginbz+")";
			this.getSimpleJdbcTemplate().update(sql);
			
		}else{
			sql = "select count(*) from rc_cxrcxx where rcid="+rcid +" and status=5 ";
			if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
				sql = " select max(xh) from rc_cxrcxx where rcid="+rcid +" and status=5 ";
				maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
			}
			sql = " insert into rc_cxrcxx(RCID,XH,STATUS,LBDM,CXXMMC, RXSJ" +
						",ZZJE, LCSJ,QYNX,QYGQ,QYJXTC,xzbz) " +
				" values('"+rcid+"','"+maxxh+"',5,'"+m.get("lbdm")+"','"+m.get("cxxmmc")+"', "+transToD(m.get("rxsj"))+"," +
						"'"+transToN(m.get("zzje"))+"',"+transToD(m.get("lcsj"))+",'"+transToN(m.get("qynx"))+"'," +
						"'"+transToN(m.get("qygq"))+"','"+transToN(m.get("qyjxtc"))+"',"+loginbz+")";
			this.getSimpleJdbcTemplate().update(sql);
		}
		
		return 1;
	}
	
	public Map getCxrcxxU(String rcid,String status,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select RCID,XH,STATUS,LBDM,CXXMMC,convert(varchar(100),RXSJ,23) RXSJ" +
					",ZZJE,convert(varchar(100),LCSJ,23) LCSJ,QYNX,QYGQ,QYJXTC " +
			" from rc_cxrcxx " +
			" where rcid="+rcid+" and status="+status+" and xh="+xh;
		map = this.getSimpleJdbcTemplate().queryForMap(sql_q);
		return map;
	}
	
	public int doCxrcxxU(String rcid,String xh,Map<String, String > m , Integer loginbz,Integer zcbz,String status){
		String sql = "";
		
		sql = " update rc_cxrcxx set LBDM='"+m.get("lbdm")+"',CXXMMC='"+m.get("cxxmmc")+"', RXSJ=" +transToD(m.get("rxsj"))+
					",ZZJE='"+transToN(m.get("zzje"))+"', LCSJ="+transToD(m.get("lcsj"))+",QYNX='"+transToN(m.get("qynx"))+"'" +
							",QYGQ='"+transToN(m.get("qygq"))+"',QYJXTC='"+transToN(m.get("qyjxtc"))+"' " +
				"  where rcid="+rcid+" and xh="+xh+" and status="+status;
		this.getSimpleJdbcTemplate().update(sql);
		
		if(status.equals("1")){
			
			sql = " update rc_cxrcxx set LBDM='"+m.get("lbdm")+"',CXXMMC='"+m.get("cxxmmc")+"', RXSJ=" +transToD(m.get("rxsj"))+
						",ZZJE='"+transToN(m.get("zzje"))+"', LCSJ="+transToD(m.get("lcsj"))+",QYNX='"+transToN(m.get("qynx"))+"'" +
								",QYGQ='"+transToN(m.get("qygq"))+"',QYJXTC='"+transToN(m.get("qyjxtc"))+"' " +
					"  where rcid="+rcid+" and xh="+xh+" and status=5 ";
			this.getSimpleJdbcTemplate().update(sql);
		}
		return 1;
	}
	
	public int doCxrcxxD(String rcid,String[] xh,Integer loginbz ,Integer zcbz,String status){
		String sql = "";
		
		if(xh != null && xh.length > 0){
			sql = " delete from rc_cxrcxx where rcid="+rcid+" and status=1 and xh in ("+ArrayToString(xh)+")";
			this.getSimpleJdbcTemplate().update(sql);
		}
		
		if(status.equals("1")){
			
			if(xh != null && xh.length > 0){
				sql = " delete from rc_cxrcxx where rcid="+rcid+" and status=5 and xh in ("+ArrayToString(xh)+")";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		return 1;
	}	
	
	
	/**
	 * 创业人才信息
	 */
	public List<Map<String, String>> getCyrcxxList(String rcid,String status){
		String sql_q = " ";
		List<Map<String, String>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select RCID,XH,STATUS,lbdm,CYXMMC,convert(varchar(100),RXSJ,23) RXSJ,ZZJE,convert(varchar(100),LCSJ,23) LCSJ" +
					",QYMC,convert(varchar(100),QYZCSJ,23) QYZCSJ,QYFDR,QYZCZB" +
					",QYXZ,convert(varchar(100),QYZZZJDZSJ,23) QYZZZJDZSJ,QYXMZE,QYYCZJTR,QYCYRS,QYJYFW,QYNCZ,QYNXSSR,QYNLS" +
					",QYZGBZJ,QYYJRCZJTR,QYZSCQZJ,FXGSMC,FXGSTZJE,FXGSCGBL,QTTZJE,QTZGBL,XZBZ," +
					"(select dictname from xt_dict where dictbh=rc_cyrcxx.lbdm and lbid=25 ) lbdm_mc " +
					" from RC_CYRCXX " +
					" where rcid="+rcid+" and status="+status+" order by xh ";
			list = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	
	public Map viewCyrcxxU(String rcid,String status,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select RCID,XH,STATUS,lbdm,CYXMMC,convert(varchar(100),RXSJ,23) RXSJ,ZZJE,convert(varchar(100),LCSJ,23) LCSJ" +
					",QYMC,convert(varchar(100),QYZCSJ,23) QYZCSJ,QYFDR,QYZCZB" +
					",QYXZ,convert(varchar(100),QYZZZJDZSJ,23) QYZZZJDZSJ,QYXMZE,QYYCZJTR,QYCYRS,QYJYFW,QYNCZ,QYNXSSR,QYNLS" +
					",QYZGBZJ,QYYJRCZJTR,QYZSCQZJ,FXGSMC,FXGSTZJE,FXGSCGBL,QTTZJE,QTZGBL,XZBZ," +
					" (select dictname from xt_dict where dictbh=rc_cyrcxx.lbdm and lbid=25 ) lbdm_mc  " +
			" from RC_CYRCXX " +
			" where rcid="+rcid+" and status="+status+" and xh="+xh;
		map = this.getSimpleJdbcTemplate().queryForMap(sql_q);
		return map;
	}
	
	public int doCyrcxxI(String rcid,Map<String, String > m , Integer loginbz,Integer zcbz,String[] fxgsmc,String[] fxgstzje,String[] fxgscgbl ,String status ){
		String sql = "";
		
		Integer maxxh = 1;
		
		if(status.equals("1")){
			sql = "select count(*) from RC_CYRCXX where rcid="+rcid +" and status=1 ";
			if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
				sql = " select max(xh) from RC_CYRCXX where rcid="+rcid +" and status=1 ";
				maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
			}
			sql = " insert into RC_CYRCXX(RCID,XH,STATUS,lbdm,CYXMMC,RXSJ,ZZJE,LCSJ" +
						",QYMC,QYZCSJ,QYFDR,QYZCZB" +
						",QYXZ,QYZZZJDZSJ,QYXMZE,QYYCZJTR,QYCYRS,QYJYFW,QYNCZ,QYNXSSR,QYNLS" +
						",QYZGBZJ,QYYJRCZJTR,QYZSCQZJ,FXGSMC,FXGSTZJE,FXGSCGBL,QTTZJE,QTZGBL,XZBZ) " +
				" values('"+rcid+"','"+maxxh+"',1,'"+m.get("lbdm")+"', '"+m.get("cyxmmc")+"'" +
						","+transToD(m.get("rxsj"))+",'"+transToN(m.get("zzje"))+"', "+transToD(m.get("lcsj"))+"" +
						",'"+m.get("qymc")+"',"+transToD(m.get("qyzcsj"))+", '"+m.get("qyfdr")+"'" +
						",'"+transToN(m.get("qyzczb"))+"','"+transToN(m.get("qyxz"))+"', "+transToD(m.get("qyzzzjdzsj"))+"" +
						",'"+transToN(m.get("qyxmze"))+"','"+transToN(m.get("qyyczjtr"))+"', '"+transToN(m.get("qycyrs"))+"'" +
						",'"+m.get("qyjyfw")+"','"+m.get("qyncz")+"', '"+m.get("qynxssr")+"'" +
						",'"+m.get("qynls")+"','"+transToN(m.get("qyzgbzj"))+"', '"+transToN(m.get("qyyjrczjtr"))+"'" +
						",'"+transToN(m.get("qyzscqzj"))+"','"+(m.get("fxgsmc")==null?"":m.get("fxgsmc"))+"', '"+transToN(m.get("fxgstzje"))+"'" +
						",'"+transToN(m.get("fxgscgbl"))+"','"+transToN(m.get("qttzje"))+"', '"+transToN(m.get("qtzgbl"))+"',"+loginbz+")";
			this.getSimpleJdbcTemplate().update(sql);
			
			
			if(fxgsmc != null && fxgsmc.length > 0){
				for(int i=0;i<fxgsmc.length;i++){
					if(!fxgsmc[i].trim().equals("")){
						sql = "insert into RC_CYRCXX_FX(RCID,XH,STATUS,FXGSMC,FXGSTZJE,FXGSCGBL)" +
						" values("+rcid+",'"+maxxh+"',1,'"+fxgsmc[i]+"','"+transToN(fxgstzje[i])+"','"+transToN(fxgscgbl[i])+"')";
						this.getSimpleJdbcTemplate().update(sql);
					}
				}
			}
			
			
			sql = "select count(*) from RC_CYRCXX where rcid="+rcid +" and status=5 ";
			if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
				sql = " select max(xh) from RC_CYRCXX where rcid="+rcid +" and status=5 ";
				maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
			}
			sql = " insert into RC_CYRCXX(RCID,XH,STATUS,lbdm,CYXMMC,RXSJ,ZZJE,LCSJ" +
						",QYMC,QYZCSJ,QYFDR,QYZCZB" +
						",QYXZ,QYZZZJDZSJ,QYXMZE,QYYCZJTR,QYCYRS,QYJYFW,QYNCZ,QYNXSSR,QYNLS" +
						",QYZGBZJ,QYYJRCZJTR,QYZSCQZJ,FXGSMC,FXGSTZJE,FXGSCGBL,QTTZJE,QTZGBL,XZBZ) " +
				" values('"+rcid+"','"+maxxh+"',5,'"+m.get("lbdm")+"', '"+m.get("cyxmmc")+"'" +
						","+transToD(m.get("rxsj"))+",'"+transToN(m.get("zzje"))+"', "+transToD(m.get("lcsj"))+"" +
						",'"+m.get("qymc")+"',"+transToD(m.get("qyzcsj"))+", '"+m.get("qyfdr")+"'" +
						",'"+transToN(m.get("qyzczb"))+"','"+transToN(m.get("qyxz"))+"', "+transToD(m.get("qyzzzjdzsj"))+"" +
						",'"+transToN(m.get("qyxmze"))+"','"+transToN(m.get("qyyczjtr"))+"', '"+transToN(m.get("qycyrs"))+"'" +
						",'"+m.get("qyjyfw")+"','"+m.get("qyncz")+"', '"+m.get("qynxssr")+"'" +
						",'"+m.get("qynls")+"','"+transToN(m.get("qyzgbzj"))+"', '"+transToN(m.get("qyyjrczjtr"))+"'" +
						",'"+transToN(m.get("qyzscqzj"))+"','"+(m.get("fxgsmc")==null?"":m.get("fxgsmc"))+"', '"+transToN(m.get("fxgstzje"))+"'" +
						",'"+transToN(m.get("fxgscgbl"))+"','"+transToN(m.get("qttzje"))+"', '"+transToN(m.get("qtzgbl"))+"',"+loginbz+")";
			this.getSimpleJdbcTemplate().update(sql);
			
			
			if(fxgsmc != null && fxgsmc.length > 0){
				for(int i=0;i<fxgsmc.length;i++){
					if(!fxgsmc[i].trim().equals("")){
						sql = "insert into RC_CYRCXX_FX(RCID,XH,STATUS,FXGSMC,FXGSTZJE,FXGSCGBL)" +
						" values("+rcid+",'"+maxxh+"',5,'"+fxgsmc[i]+"','"+transToN(fxgstzje[i])+"','"+transToN(fxgscgbl[i])+"')";
						this.getSimpleJdbcTemplate().update(sql);
					}
				}
			}
			
		}else{
			
			sql = "select count(*) from RC_CYRCXX where rcid="+rcid +" and status=5 ";
			if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
				sql = " select max(xh) from RC_CYRCXX where rcid="+rcid +" and status=5 ";
				maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
			}
			sql = " insert into RC_CYRCXX(RCID,XH,STATUS,lbdm,CYXMMC,RXSJ,ZZJE,LCSJ" +
						",QYMC,QYZCSJ,QYFDR,QYZCZB" +
						",QYXZ,QYZZZJDZSJ,QYXMZE,QYYCZJTR,QYCYRS,QYJYFW,QYNCZ,QYNXSSR,QYNLS" +
						",QYZGBZJ,QYYJRCZJTR,QYZSCQZJ,FXGSMC,FXGSTZJE,FXGSCGBL,QTTZJE,QTZGBL,XZBZ) " +
				" values('"+rcid+"','"+maxxh+"',5,'"+m.get("lbdm")+"', '"+m.get("cyxmmc")+"'" +
						","+transToD(m.get("rxsj"))+",'"+transToN(m.get("zzje"))+"', "+transToD(m.get("lcsj"))+"" +
						",'"+m.get("qymc")+"',"+transToD(m.get("qyzcsj"))+", '"+m.get("qyfdr")+"'" +
						",'"+transToN(m.get("qyzczb"))+"','"+transToN(m.get("qyxz"))+"', "+transToD(m.get("qyzzzjdzsj"))+"" +
						",'"+transToN(m.get("qyxmze"))+"','"+transToN(m.get("qyyczjtr"))+"', '"+transToN(m.get("qycyrs"))+"'" +
						",'"+m.get("qyjyfw")+"','"+m.get("qyncz")+"', '"+m.get("qynxssr")+"'" +
						",'"+m.get("qynls")+"','"+transToN(m.get("qyzgbzj"))+"', '"+transToN(m.get("qyyjrczjtr"))+"'" +
						",'"+transToN(m.get("qyzscqzj"))+"','"+(m.get("fxgsmc")==null?"":m.get("fxgsmc"))+"', '"+transToN(m.get("fxgstzje"))+"'" +
						",'"+transToN(m.get("fxgscgbl"))+"','"+transToN(m.get("qttzje"))+"', '"+transToN(m.get("qtzgbl"))+"',"+loginbz+")";
			this.getSimpleJdbcTemplate().update(sql);
			
			
			if(fxgsmc != null && fxgsmc.length > 0){
				for(int i=0;i<fxgsmc.length;i++){
					if(!fxgsmc[i].trim().equals("")){
						sql = "insert into RC_CYRCXX_FX(RCID,XH,STATUS,FXGSMC,FXGSTZJE,FXGSCGBL)" +
						" values("+rcid+",'"+maxxh+"',5,'"+fxgsmc[i]+"','"+transToN(fxgstzje[i])+"','"+transToN(fxgscgbl[i])+"')";
						this.getSimpleJdbcTemplate().update(sql);
					}
				}
			}
			
		}
		
		return 1;
	}
	
	
	public Map getCyrcxxU(String rcid,String status,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select RCID,XH,STATUS,lbdm,CYXMMC,convert(varchar(100),RXSJ,23) RXSJ,ZZJE,convert(varchar(100),LCSJ,23) LCSJ" +
					",QYMC,convert(varchar(100),QYZCSJ,23) QYZCSJ,QYFDR,QYZCZB" +
					",QYXZ,convert(varchar(100),QYZZZJDZSJ,23) QYZZZJDZSJ,QYXMZE,QYYCZJTR,QYCYRS,QYJYFW,QYNCZ,QYNXSSR,QYNLS" +
					",QYZGBZJ,QYYJRCZJTR,QYZSCQZJ,FXGSMC,FXGSTZJE,FXGSCGBL,QTTZJE,QTZGBL,XZBZ " +
			" from RC_CYRCXX " +
			" where rcid="+rcid+" and status="+status+" and xh="+xh;
		map = this.getSimpleJdbcTemplate().queryForMap(sql_q);
		return map;
	}
	
	public List getCyrcxxFxU(String rcid,String status,String xh){
		String sql = "";
		sql = " select * from rc_cyrcxx_fx where  rcid="+rcid+" and status="+status+" and xh="+xh;
		return this.getSimpleJdbcTemplate().queryForList(sql);
	}

	public int doCyrcxxU(String rcid,String xh,Map<String, String > m , Integer loginbz,Integer zcbz,String[] fxgsmc,String[] fxgstzje,String[] fxgscgbl,String status){
		String sql = "";
		
		if(status.equals("1")){
			sql = " update rc_cyrcxx set lbdm='"+m.get("lbdm")+"', cyxmmc='"+m.get("cyxmmc")+"'" +
				",rxsj="+transToD(m.get("rxsj"))+",zzje='"+transToN(m.get("zzje"))+"', lcsj="+transToD(m.get("lcsj"))+"" +
				",qymc='"+m.get("qymc")+"',qyzcsj="+transToD(m.get("qyzcsj"))+", qyfdr='"+m.get("qyfdr")+"'" +
				",qyzczb='"+transToN(m.get("qyzczb"))+"',qyxz='"+transToN(m.get("qyxz"))+"', qyzzzjdzsj="+transToN(transToD(m.get("qyzzzjdzsj")))+"" +
				",qyxmze='"+transToN(m.get("qyxmze"))+"',qyyczjtr='"+transToN(m.get("qyyczjtr"))+"',qycyrs= '"+m.get("qycyrs")+"'" +
				",qyjyfw='"+m.get("qyjyfw")+"',qyncz='"+m.get("qyncz")+"', qynxssr='"+m.get("qynxssr")+"'" +
				",qynls='"+m.get("qynls")+"',qyzgbzj='"+m.get("qyzgbzj")+"',qyyjrczjtr= '"+m.get("qyyjrczjtr")+"'" +
				",qyzscqzj='"+m.get("qyzscqzj")+"'" +
				",qttzje='"+m.get("qttzje")+"', qtzgbl='"+m.get("qtzgbl")+"' " +
			"  where rcid="+rcid+" and xh="+xh+" and status=1";
			this.getSimpleJdbcTemplate().update(sql);
			
			sql = " delete from rc_cyrcxx_fx where rcid="+rcid+" and xh="+xh+" and status=1";
			this.getSimpleJdbcTemplate().update(sql);
			if(fxgsmc != null && fxgsmc.length > 0){
				for(int i=0;i<fxgsmc.length;i++){
					if(!fxgsmc[i].trim().equals("")){
						sql = "insert into RC_CYRCXX_FX(RCID,XH,STATUS,FXGSMC,FXGSTZJE,FXGSCGBL)" +
						" values("+rcid+",'"+xh+"',1,'"+fxgsmc[i]+"','"+transToN(fxgstzje[i])+"','"+transToN(fxgscgbl[i])+"')";
						this.getSimpleJdbcTemplate().update(sql);
					}
					
				}
			}

			sql = " update rc_cyrcxx set lbdm='"+m.get("lbdm")+"', cyxmmc='"+m.get("cyxmmc")+"'" +
				",rxsj="+transToD(m.get("rxsj"))+",zzje='"+transToN(m.get("zzje"))+"', lcsj="+transToD(m.get("lcsj"))+"" +
				",qymc='"+m.get("qymc")+"',qyzcsj="+transToD(m.get("qyzcsj"))+", qyfdr='"+m.get("qyfdr")+"'" +
				",qyzczb='"+transToN(m.get("qyzczb"))+"',qyxz='"+transToN(m.get("qyxz"))+"', qyzzzjdzsj="+transToN(transToD(m.get("qyzzzjdzsj")))+"" +
				",qyxmze='"+transToN(m.get("qyxmze"))+"',qyyczjtr='"+transToN(m.get("qyyczjtr"))+"',qycyrs= '"+m.get("qycyrs")+"'" +
				",qyjyfw='"+m.get("qyjyfw")+"',qyncz='"+m.get("qyncz")+"', qynxssr='"+m.get("qynxssr")+"'" +
				",qynls='"+m.get("qynls")+"',qyzgbzj='"+m.get("qyzgbzj")+"',qyyjrczjtr= '"+m.get("qyyjrczjtr")+"'" +
				",qyzscqzj='"+m.get("qyzscqzj")+"'" +
				",qttzje='"+m.get("qttzje")+"', qtzgbl='"+m.get("qtzgbl")+"' " +
			"  where rcid="+rcid+" and xh="+xh+" and status=5";
			this.getSimpleJdbcTemplate().update(sql);
			
			sql = " delete from rc_cyrcxx_fx where rcid="+rcid+" and xh="+xh+" and status=5";
			this.getSimpleJdbcTemplate().update(sql);
			if(fxgsmc != null && fxgsmc.length > 0){
				for(int i=0;i<fxgsmc.length;i++){
					if(!fxgsmc[i].trim().equals("")){
						sql = "insert into RC_CYRCXX_FX(RCID,XH,STATUS,FXGSMC,FXGSTZJE,FXGSCGBL)" +
						" values("+rcid+",'"+xh+"',5,'"+fxgsmc[i]+"','"+transToN(fxgstzje[i])+"','"+transToN(fxgscgbl[i])+"')";
						this.getSimpleJdbcTemplate().update(sql);
					}
					
				}
			}
		}else{

			sql = " update rc_cyrcxx set lbdm='"+m.get("lbdm")+"', cyxmmc='"+m.get("cyxmmc")+"'" +
				",rxsj="+transToD(m.get("rxsj"))+",zzje='"+transToN(m.get("zzje"))+"', lcsj="+transToD(m.get("lcsj"))+"" +
				",qymc='"+m.get("qymc")+"',qyzcsj="+transToD(m.get("qyzcsj"))+", qyfdr='"+m.get("qyfdr")+"'" +
				",qyzczb='"+transToN(m.get("qyzczb"))+"',qyxz='"+transToN(m.get("qyxz"))+"', qyzzzjdzsj="+transToN(transToD(m.get("qyzzzjdzsj")))+"" +
				",qyxmze='"+transToN(m.get("qyxmze"))+"',qyyczjtr='"+transToN(m.get("qyyczjtr"))+"',qycyrs= '"+m.get("qycyrs")+"'" +
				",qyjyfw='"+m.get("qyjyfw")+"',qyncz='"+m.get("qyncz")+"', qynxssr='"+m.get("qynxssr")+"'" +
				",qynls='"+m.get("qynls")+"',qyzgbzj='"+m.get("qyzgbzj")+"',qyyjrczjtr= '"+m.get("qyyjrczjtr")+"'" +
				",qyzscqzj='"+m.get("qyzscqzj")+"'" +
				",qttzje='"+m.get("qttzje")+"', qtzgbl='"+m.get("qtzgbl")+"' " +
			"  where rcid="+rcid+" and xh="+xh+" and status=5";
			this.getSimpleJdbcTemplate().update(sql);
			
			sql = " delete from rc_cyrcxx_fx where rcid="+rcid+" and xh="+xh+" and status=5";
			this.getSimpleJdbcTemplate().update(sql);
			if(fxgsmc != null && fxgsmc.length > 0){
				for(int i=0;i<fxgsmc.length;i++){
					if(!fxgsmc[i].trim().equals("")){
						sql = "insert into RC_CYRCXX_FX(RCID,XH,STATUS,FXGSMC,FXGSTZJE,FXGSCGBL)" +
						" values("+rcid+",'"+xh+"',5,'"+fxgsmc[i]+"','"+transToN(fxgstzje[i])+"','"+transToN(fxgscgbl[i])+"')";
						this.getSimpleJdbcTemplate().update(sql);
					}
					
				}
			}
		}
		
		return 1;
	}
	
	public int doCyrcxxD(String rcid,String[] xh,Integer loginbz ,Integer zcbz,String status){
		String sql = "";
		
		if(xh != null && xh.length > 0){
			sql = " delete from RC_CYRCXX where rcid="+rcid+" and status="+status+" and xh in ("+ArrayToString(xh)+")";
			this.getSimpleJdbcTemplate().update(sql);
		}
		
		sql = "delete from rc_cyrcxx_fx where rcid="+rcid+" and status="+status+" and xh in ("+ArrayToString(xh)+")";
		this.getSimpleJdbcTemplate().update(sql);
		
		if(status.equals("1")){
			if(xh != null && xh.length > 0){
				sql = " delete from RC_CYRCXX where rcid="+rcid+" and status=5 and xh in ("+ArrayToString(xh)+")";
				this.getSimpleJdbcTemplate().update(sql);
			}
			
			sql = "delete from rc_cyrcxx_fx where rcid="+rcid+" and status=5 and xh in ("+ArrayToString(xh)+")";
			this.getSimpleJdbcTemplate().update(sql);
		}
		return 1;
	}		
	/**
	 * 获取人才信息简介、备注
	 * @param rcid
	 * @return
	 */
	public Map getJjbzInfo(String rcid,String status){
		Map m = new HashMap();
		if(rcid != null && !rcid.equals("")){
			String sql_q = "select a.info,a.bz from rc_pinfo a where a.rcid="+rcid+" and status=1";
			m = this.getSimpleJdbcTemplate().queryForMap(sql_q);			
		}
		return m;
	}
	
	public Map getJjbzInfoZj(String rcid,String status){
		Map m = new HashMap();
		if(rcid != null && !rcid.equals("")){
			String sql_q = "select a.info,a.bz from rc_pinfo a where a.rcid="+rcid+" and status=1";
			m = this.getSimpleJdbcTemplate().queryForMap(sql_q);			
		}
		return m;
	}
	/**
	 * 保存简介、备注
	 * @param rcid
	 * @param rcdarcxx
	 * @return
	 */
	public int doSaveJjbz(String rcid,Integer status,Map<String, String> rcdarcxx){
		String sql_o  = "";
		sql_o = "update RC_PINFO set info='"+rcdarcxx.get("info")+"',bz='"+rcdarcxx.get("bz")+"' where rcid="+rcid+" and status=1";
		this.getSimpleJdbcTemplate().update(sql_o);
		return 1;
	}
	
	public int doSaveJjbzZj(String rcid,Integer status,Map<String, String> rcdarcxx){
		String sql_o  = "";
		sql_o = "update RC_PINFO_ZJ set info='"+rcdarcxx.get("info")+"',bz='"+rcdarcxx.get("bz")+"' where rcid="+rcid+" and status=1";
		this.getSimpleJdbcTemplate().update(sql_o);
		return 1;
	}
	/**
	 * 获取人才信息其它信息
	 * @param rcid
	 * @return
	 */
	public Map getOtherInfo(String rcid,String status){
		Map m = new HashMap();
		if(rcid != null && !rcid.equals("")){
			String sql_q = "select a.otherinfo from exp_main a where a.rcid="+rcid+" and status=1";
			m = this.getSimpleJdbcTemplate().queryForMap(sql_q);			
		}
		return m;
	}
	
	public Map getOtherInfoZj(String rcid,String status){
		Map m = new HashMap();
		if(rcid != null && !rcid.equals("")){
			String sql_q = "select a.otherinfo from exp_main_Zj a where a.rcid="+rcid+" and status=1";
			m = this.getSimpleJdbcTemplate().queryForMap(sql_q);			
		}
		return m;
	}
	/**
	 * 保存其它信息
	 * @param rcid
	 * @param rcdarcxx
	 * @return
	 */
	public int doSaveOtherInfo(String rcid,Integer status,Map<String, String> rcdarcxx){
		String sql_o  = "";
		sql_o = "update EXP_MAIN set otherinfo='"+rcdarcxx.get("otherinfo")+"'  where rcid="+rcid+" and status=1";
		this.getSimpleJdbcTemplate().update(sql_o);
		return 1;
	}
	
	public int doSaveOtherInfoZj(String rcid,Integer status,Map<String, String> rcdarcxx){
		String sql_o  = "";
		sql_o = "update EXP_MAIN_ZJ set otherinfo='"+rcdarcxx.get("otherinfo")+"'  where rcid="+rcid+" and status=1";
		this.getSimpleJdbcTemplate().update(sql_o);
		return 1;
	}
	/**
	 * 获取所有的基础数据
	 * @return
	 */
	public Map<Integer, List<XtDict>> getDictListWithSelectAll(){
		Map<Integer, List<XtDict>> ms = new HashMap();
		List<XtDict> xtdicts = new ArrayList();
		int lbid = 0;
		String sql_q = " select * from xt_dlb where lbframe!=1 ";
		List<XtDlb> xtdlbs = this.getSimpleJdbcTemplate().query(sql_q, resultBeanMapper(XtDlb.class));
		for(int i=0;i<xtdlbs.size();i++){
			lbid = xtdlbs.get(i).getLbid();
			ms.put(lbid, this.getSimpleJdbcTemplate().query(" select dictbh,dictname from xt_dict where lbid="+lbid+" and enable=1 order by dictbh", resultBeanMapper(XtDict.class)));
			
		}
		return ms;
	}
	

	public List<XtDict> getDictListWithSelectByLen(Integer lbid,Integer len){
		List<XtDict> ls = new ArrayList();
		String sql_q = "select dictbh,dictname from xt_dict_Fldm where fldm='000000004' and lbid="+lbid+" " +
				"and len(dictbh)="+len+" order by dictbh ";
		ls = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(XtDict.class));
		return ls;
	}
	
	
	public List<XtDict> getDictWithSel( String rcid ){
		List<XtDict> ls = new ArrayList();
		if(rcid != null && !rcid.equals("")){
			String sql = " select exptype dictbh from exp_exptype where rcid="+rcid;
			ls = this.getSimpleJdbcTemplate().query(sql, resultBeanMapper(XtDict.class));
		}
		return ls;
	}
	
	/**
	 * 获取基础数据 传入 数组
	 */
	public Map<Integer, List<XtDict>> getDictListWithSelectByArray(String[] dlbs){
		Map<Integer, List<XtDict>> ms = new HashMap();
		List<XtDict> xtdicts = new ArrayList();
		int lbid = 0;
		String sql_q = " select * from xt_dlb where lbid in ("+ArrayToString(dlbs)+") ";
		List<XtDlb> xtdlbs = this.getSimpleJdbcTemplate().query(sql_q, resultBeanMapper(XtDlb.class));
		for(int i=0;i<xtdlbs.size();i++){
			lbid = xtdlbs.get(i).getLbid();
			ms.put(lbid, this.getSimpleJdbcTemplate().query(" select dictbh,dictname from xt_dict where lbid="+lbid+" and enable=1 order by dictbh", resultBeanMapper(XtDict.class)));
			
		}
		return ms;
	}
	
	/**
	 * 获取人才的基础数据
	 * 主要用下拉框
	 * @param lbid
	 */
	public List<XtDict> getDictListWithSelect(Integer lbid){
		List<XtDict> ls = new ArrayList();
		String sql_q = "select dictbh,dictname from xt_dict where lbid="+lbid+"  order by dictbh ";
		ls = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(XtDict.class));
		return ls;
	}
	
	public List<XtDict> getDictListWithBpzjSelect(Integer lbid,String rcid ){
		List<XtDict> ls = new ArrayList();
		String sql_q = "select dictbh,dictname,len(dictbh) sortlen  from xt_dict where lbid=25 "+
				" order by sortlen,dictbh ";
		ls = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(XtDict.class));
		return ls;
	}
	
	
	public List<XtDict> getDictListWithRcgz(){
		List<XtDict> ls = new ArrayList();
		String sql_q = "select dictbh,dictname from exp_gzgl_bm   order by dictbh ";
		ls = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(XtDict.class));
		return ls;
	}
	
	//创新人才
	public int getCountDLWithCxForDis(Integer lbid,String rcid,String status){
		String sql_q = "select count(*) from xt_dict where lbid="+lbid+" " +
		" and dictbh in ( select bpzjqk from EXP_BPZJ where rcid="+rcid+" and status="+status+")" +
		" and dictbh in ('011','013','016') ";
		return this.getSimpleJdbcTemplate().queryForInt(sql_q);
	}
	
	//创业人才
	public int getCountDLWithCyForDis(Integer lbid,String rcid,String status){
		String sql_q = "select count(*) from xt_dict where lbid="+lbid+" " +
		" and dictbh in ( select bpzjqk from EXP_BPZJ where rcid="+rcid+" and status="+status+")" +
		" and dictbh in ('012','014','015') ";
		return this.getSimpleJdbcTemplate().queryForInt(sql_q);
	}
	/**
	 * 针对创新人才信息
	 * @param lbid
	 * @return
	 */
	public List<XtDict> getDictListWithCxrcxx(Integer lbid,String rcid){
		List<XtDict> ls = new ArrayList();
		String sql_q = "select dictbh,dictname from xt_dict where lbid="+lbid+" " +
				" and dictbh in ( select bpzjqk from EXP_BPZJ where rcid="+rcid+")" +
				" and dictbh in ('011','013','016') order by dictbh ";
		ls = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(XtDict.class));
		return ls;
	}
	
	/**
	 * 针对创业人才信息
	 * @param lbid
	 * @return
	 */
	public List<XtDict> getDictListWithCyrcxx(Integer lbid,String rcid,String status){
		List<XtDict> ls = new ArrayList();
		String sql_q = "select dictbh,dictname from xt_dict where lbid="+lbid+" " +
				" and dictbh in ( select bpzjqk from EXP_BPZJ where rcid="+rcid+" and status="+status+")" +
				" and dictbh in ('012','014','015') order by dictbh ";
		ls = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(XtDict.class));
		return ls;
	}
	
	public void doExpZjTj(String rcid){
		String sql = "update exp_main_zj set tbbz=3 where rcid="+rcid;
		update(true, sql);
		sql = "update xt_user_exp set sbcount=isnull(sbcount,0)+1 where rcid="+rcid;
		update(true, sql);
	}
	
	public String getExpHtyy(String rcid){
		String sql = "select sbcount from xt_user_exp where rcid="+rcid;
		Integer sbc = this.getSimpleJdbcTemplate().queryForInt(sql);
		sql = " select count(*) from exp_htyy where rcid="+rcid+" and sbcount="+sbc;
		if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select htyy from exp_htyy where rcid="+rcid+" and sbcount="+sbc;
			return this.getSimpleJdbcTemplate().queryForObject(sql, String.class);
		}else{
			return "";
		}
	}
	
	
	/***
	 * 获取分类
	 * @param lbid
	 * @return
	 */
	public List getExpFlList2(String roledm,String userid,String rcid ){

		
		List ls = new ArrayList();
		String sql  = "";
		if(rcid != null && !rcid.equals("")){
			if(roledm.equals("01")){
				sql = " select a.fldm as dm,a.flmc as mc,(select count(*) from exp_flmx b where rcid="+rcid+" " +
						" and b.fldm=a.fldm )  as isc," +
				" case when len(fldm)=9 then '0' else substring(fldm,1,len(fldm)-9) end as pdm from exp_fl a  order by fldm ";
			}else{
				sql = " select fldm dm,flmc mc" +
						",(select count(*) from exp_flmx b where rcid="+rcid+" " +
						" and b.fldm=a.fldm )  as isc," +
						"  case when len(fldm)=9 then '0' else substring(fldm,1,len(fldm)-9) end as pdm  from exp_fl a where fldm in (select userfl from XT_USER_FL where userid= "+userid+" ) order by fldm ";
			}
		}else{
			if(roledm.equals("01")){
				sql = " select a.fldm as dm,a.flmc as mc,0 as isc," +
				" case when len(fldm)=9 then '0' else substring(fldm,1,len(fldm)-9) end as pdm from exp_fl a  order by fldm ";
			}else{
				sql = " select fldm dm,flmc mc" +
						",0  as isc," +
						"  case when len(fldm)=9 then '0' else substring(fldm,1,len(fldm)-9) end as pdm  from exp_fl a where fldm in (select userfl from XT_USER_FL where userid= "+userid+" ) order by fldm ";
			}
		}
		ls = this.getSimpleJdbcTemplate().queryForList(sql);
		return ls;
	}
	
	/**
	 * 获取人才的基础数据
	 * 主要用于树形
	 * @param lbid
	 * @return
	 */
	public List<TreeBean> getDictListWithTree(Integer lbid){
		List<TreeBean> ls = new ArrayList();
		String sql  = "";
		if(lbid == -99){
			sql = "select case when isnull(bz,1)=1 then dictname else dictname+'(外部)' end mc ," +
					" dictbh dm,'0' isc,case when len(dictbh)=3 then '0' else substring(dictbh,1,len(dictbh)-3) end" +
					" as pdm from exp_gzgl_bm  order by dictbh";
			ls = this.getSimpleJdbcTemplate().query(sql, resultBeanMapper(TreeBean.class));
		}else if(lbid == -98){
					sql = "select case when isnull(bz,1)=1 then dictname else dictname+'(外部)' end mc ," +
					" dictbh dm,'0' isc,case when len(dictbh)=3 then '0' else substring(dictbh,1,len(dictbh)-3) end" +
					" as pdm from exp_gzgl_bm where isnull(bz,1)=1 order by dictbh";
			ls = this.getSimpleJdbcTemplate().query(sql, resultBeanMapper(TreeBean.class));
		}else{
			ls = this.getSimpleJdbcTemplate().query(" select a.dictbh as dm ,a.dictname as mc ," +
					" (select count(*) from xt_dict b where b.dictbh like a.dictbh+'%' and a.dictbh!=b.dictbh) as isc," +
					" case when len(dictbh)=3 then '0' else substring(dictbh,1,len(dictbh)-3) end as pdm " +
					" from xt_dict a where a.lbid="+lbid+" and a.enable=1 order by a.dictbh", resultBeanMapper(TreeBean.class));
		}
		
		return ls;
	}
	
	public List<TreeBean> getFlDictListWithTree(Integer lbid,String fldm){
		List<TreeBean> ls = new ArrayList();
		String sql  = " select a.dictbh as dm ,a.dictname as mc ," +
		"'0' as isc," +
		" case when len(dictbh)=3 then '0' else substring(dictbh,1,len(dictbh)-3) end as pdm " +
		" from xt_dict_FLDM a where a.fldm='"+fldm+"' and a.lbid="+lbid+" and a.enable=1 order by a.dictbh";
		ls = this.getSimpleJdbcTemplate().query(sql, resultBeanMapper(TreeBean.class));
		
		return ls;
	}
	/**
	 * 逐级获取树形
	 */
	public List<XtDict> getDictTreeWithStep(Integer i,String dm){
		List<XtDict> maps = new ArrayList();
		String sql_q = "";
		if(dm != null && !dm.equals("")){
			sql_q = " select * from xt_dict where lbid="+i+" " +
					" and len(dictbh)="+(dm.length()+3)+" and dictbh like '"+dm+"%'";
		}else{
			sql_q = " select * from xt_dict where lbid="+i+" and len(dictbh)=3 ";
		}
		maps = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(XtDict.class));
		return maps;
	}
	
	/**
	 * 用于分步获取人才信息的树形
	 * @param i
	 * @param dm
	 * @return
	 */
	public List<TreeBean> getRcxxTreeWithStep(Integer i,String dm){
		List<TreeBean> l = new ArrayList();
		String sql_q = "";
		if(dm != null && !dm.equals("")){
			sql_q = " select a.dictbh as dm ,a.dictname as mc ," +
				" '0' as isc," +
				" '0' as pdm " +
				" from xt_dict a where lbid="+i+" and len(a.dictbh)='"+(dm.length()+3)+"' and a.dictbh like '"+dm+"%' order by a.dictbh";
		}else{
			sql_q = " select a.dictbh as dm ,a.dictname as mc ," +
				" '0' as isc," +
				" '0' as pdm " +
				" from xt_dict a where a.lbid="+i+" and len(a.dictbh)=3 order by a.dictbh";
		}
		l = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(TreeBean.class));
		return l;
	}
	
	/**
	 * 获取人才类别 树形
	 * @return
	 */
	public List<TreeBean> getRclbTree(){
		List<TreeBean> l = new ArrayList();
		String sql_q = "";
		sql_q = " select a.lbdm as dm ,a.lbmc as mc ," +
			" (select count(*) from rc_rclb b where b.lbdm like a.lbdm+'%' and a.lbdm!=b.lbdm) as isc," +
			" case when len(lbdm)=3 then '0' else substring(lbdm,1,len(lbdm)-3) end as pdm " +
			" from rc_rclb a  order by a.lbdm";
		l = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(TreeBean.class));
		return l;
	}
	
	 public List<TreeBean> getDicts(String bmdm){
    	List<TreeBean> l = new ArrayList();
    	String sql = "select dictname mc ,dictbh dm,'0' isc,'0' pdm from rc_gzgl_bm where bmdm= '"+bmdm+"' order by dictbh";
    	l =  this.getJdbcTemplate().query(sql,resultBeanMapper(TreeBean.class));
    	return l ;
    }
	
	
	/**
	 * 对存储过程调用进行了简单封装  带返回值
	 */
	public Object callProc(String procn,final Map<String, Object> par){
		return getJdbcTemplate().execute("{call SET_TABLEBYRC(?,?,?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
				cs.setInt(1, Integer.parseInt(par.get("RCID").toString()));
				cs.setInt(2, Integer.parseInt(par.get("TABLEXH").toString()));
				cs.registerOutParameter(3, Types.INTEGER);
				cs.execute();
				return cs.getInt(3);
			}
		});
	}
	
	public List getNlfwList(){
		String sql = " select * from exp_nlfw order by isnull(nl1,0) ";
		return this.getSimpleJdbcTemplate().queryForList(sql);
	}
	
	public void doSaveNlfw(String[] nl1,String[] nl2,String[] bz){
		String sql = "";
		if(nl1.length > 0){
			sql ="delete from exp_nflw";
			this.getSimpleJdbcTemplate().update(sql);
		}else{
			throw new BusException("请填写相应的年龄区间");
		}
		
		for(int i=0;i<nl1.length;i++){
			sql = "insert into exp_nflw(dm,mc,nl1,nl2,bz) values('"+(i+1)+"',";
			if(bz[i]!=null && bz[i].equals("1")){
				sql += "'"+nl2[i]+"以下',0,'"+nl2[i]+"',1)";
			}else if(bz[i]!=null && bz[i].equals("2")){
				sql += "'"+nl1[i]+"以上','"+nl1[i]+"',0,2)";
			}else{
				sql += "'"+(nl1[i]+"-"+nl2[i])+"','"+nl1[i]+"','"+nl2[i]+"',0)";
			}
			this.getSimpleJdbcTemplate().update(sql);
		}
	}
	
	public void doGlyth(String rcid,String thsel){
		String sql = "select isnull(zgshbz,0) from rc_pinfo where rcid='"+rcid+"' and isnull(status,1)=5";
		Integer zjshbz = this.getSimpleJdbcTemplate().queryForInt(sql);
		
		sql = " select isnull(sbcount,0) from rc_user where xtrcid="+rcid;
		int sbcount = this.getSimpleJdbcTemplate().queryForInt(sql);
		
		sql = " select count(*) from rc_htyy where rcid="+rcid+" and sbcount="+sbcount+" and bmdm like '8%' ";
		if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			throw new BusException("当前人才有相应市属部门已经审核过，所以不能退回!");
		}
		
		if(thsel.equals("1")){//退回主管部门
			if(zjshbz == 1){
				throw new BusException("当前人才是等待主管审核中，无需退回主管!");
			}
			//
			sql = "update rc_pinfo set zgshbz=1 where rcid="+rcid+" and status=5 ";
			this.getSimpleJdbcTemplate().update(sql);
		}else{//退回高层人才
			
			if(zjshbz == 0){
				throw new BusException("当前人才正在人才状态中，无需退回人才!");
			}
			sql = " insert into rc_htyy(rcid,sbcount,bmdm,bmmc,htyy,bz,intime) values("+rcid+","+sbcount+",'000','','管理员退回!',3,getdate())";
			this.getJdbcTemplate().update(sql);
			//
			sql = "update rc_pinfo set zgshbz=0 where rcid="+rcid+" and status=5 ";
			this.getSimpleJdbcTemplate().update(sql);
		}
	}
	
	public void doSaveDlbFldm(String fldm,String[] dmkey){
		String sql = "";
		sql = " delete from xt_dlb_fldm where fldm='"+fldm+"'";
		this.getSimpleJdbcTemplate().update(sql);
		if(dmkey != null && dmkey.length > 0){
			for(int i=0;i<dmkey.length;i++){
				sql = "insert into xt_dlb_fldm(FLDM, LBID, LBNAME, LBFRAME, STATUS)" +
						" select '"+fldm+"', LBID, LBNAME, LBFRAME, STATUS from xt_dlb where lbid="+dmkey[i];
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
	}
}
