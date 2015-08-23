package com.netwander.explib.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fins.gt.model.FilterInfo;
import com.fins.gt.model.SortInfo;
import com.netwander.explib.entity.DmMc;
import com.netwander.core.common.LimitPage;
import com.netwander.explib.entity.Xtuser;
import com.netwander.explib.service.common.BaseService;

@Service
public class ExpBbService  extends BaseService{
	public Object getListForRcdaByName(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos,Xtuser xtuser) {
		String name = parMap.get("name") == null ? "" :  parMap.get("name");
		String where = parMap.get("where") == null ? "" :  parMap.get("where");
		String ls_sql = "select * from exp_main_v where  1=1 " ;

		String sql =  parMap.get("sql") == null ? "" :  parMap.get("sql");
		
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
				}
			}
		}
		
		if(sql != null && !sql.equals("")){
			ls_sql += " and ("+sql+") ";
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
	
	public Object getListForRcda5(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos,Xtuser xtuser) {
		String name = parMap.get("name") == null ? "" :  parMap.get("name");
		String where = parMap.get("where") == null ? "" :  parMap.get("where");
		String where1 = parMap.get("where1") == null ? "" :  parMap.get("where1");
		String ls_sql = "select * from exp_main_v where  1=1 " ;

		String xl = parMap.get("xl") == null ?"":parMap.get("xl");
		String xw = parMap.get("xw") == null ?"":parMap.get("xw");
		String zc = parMap.get("zc") == null ?"":parMap.get("zc");
		String zw = parMap.get("zw") == null ?"":parMap.get("zw");
		
		String dwxz = parMap.get("dwxz") == null ?"":parMap.get("dwxz");
		
		String strnl = parMap.get("strnl") == null ?"":parMap.get("strnl");
		String endnl = parMap.get("endnl") == null ?"":parMap.get("endnl");
		
		ls_sql += " and (STATUS = 5) ";
		
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
		
		if(!strnl.trim().equals("") && !strnl.trim().equals("0")){
			ls_sql += " and convert(varchar(100),birthday,23)<='"+this.getBirthday(Integer.parseInt(strnl), 0)+"'" ;
		}
		if(!endnl.trim().equals("") && !endnl.trim().equals("0")){
			ls_sql += " and convert(varchar(100),birthday,23)>='"+this.getBirthday(Integer.parseInt(endnl), 1)+"'" ;
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
		
		if(where1!= null && !where1.equals("")){
			if(where1.equals("1")){
				ls_sql += " and rcid in (select distinct rcid from rc_htyy where  bz=3 and  " +
					" convert(varchar,rcid)+'&'+convert(varchar,isnull(sbcount,0)) " +
					"  in ( select convert(varchar,xtrcid)+'&'+convert(varchar,isnull(sbcount,0)) from rc_user )  and bmdm like '8%' ) " +
					"";
			}else{
				ls_sql += " and rcid in (select distinct rcid from rc_htyy where  bz=3 and  " +
					" convert(varchar,rcid)+'&'+convert(varchar,isnull(sbcount,0)) " +
					"  in ( select convert(varchar,xtrcid)+'&'+convert(varchar,isnull(sbcount,0)) from rc_user )  and bmdm like '0%' ) " +
					"";
			}
		}else{
			ls_sql += " and rcid in (select distinct rcid from rc_htyy where bz=3 and  " +
				" convert(varchar,rcid)+'&'+convert(varchar,isnull(sbcount,0)) " +
				"  in ( select convert(varchar,xtrcid)+'&'+convert(varchar,isnull(sbcount,0)) from rc_user ) ) " +
				"";
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
	
	
	
	@SuppressWarnings("unchecked")
	public List getListForType(Map<String, String> parmMap) {
		return this.getSimpleJdbcTemplate().queryForList("{call QRY_FLTJ(:type)}",parmMap);
	}
	
	
	public List queryInfo(Map<String, String> parMap,Xtuser xtuser){
		List list = new ArrayList();
		
		String ls_sql = "select * from exp_main_v where 1=1 " ;
		
		String xm = parMap.get("xm") == null ? "" :  parMap.get("xm");
		String nation = parMap.get("nation") == null ? "" :  parMap.get("nation");
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
		
		if(!xm.trim().equals("")){
			ls_sql += " and RCNAME like '%"+xm+"%'";
		}
		if(!nation.equals("")){
			ls_sql += " and nation='"+nation+"'";
		}
		
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
		
		ls_sql +=" order by rcid";
		list = this.getSimpleJdbcTemplate().queryForList(ls_sql);
		return list;
	}
	
	
	/**
	 * 获取统计的基本信息
	 */
	public List<DmMc> getTjjbxx(String tid){
		List list = new ArrayList();
		String sql = " select dm,mc from t_field where tid="+tid;
		list = this.getSimpleJdbcTemplate().queryForList(sql);
		return list;
	}
	
	public String doTjV(String[] tjfield,Xtuser xtuser){
		StringBuffer sb = new StringBuffer();
		String sql = "";
		if(tjfield != null && tjfield.length > 0){
			sql = " select * from t_field where tid=1 and dm in ("+ArrayToString(tjfield)+")";
			List<Map<String, Object>> tList = this.getSimpleJdbcTemplate().queryForList(sql);

			String where = " where 1=1 ";
			
			sb.append("<table id='tjtable' cellspacing='0' style='width:100%'><thead><tr>");
			sb.append("<tr><th style='width:50px;' >序号</th>");
			String fields = "";
			String t_mc = "";
			for(int i=0;i<tjfield.length;i++){
				if(!tjfield[i].equals("")){
					sb.append("<th>"+tranM(tList, "dm", tjfield[i], "mc")+"</th>");
					if(fields.equals("")){
						fields += tjfield[i];
					}else{
						fields += ","+tjfield[i];
					}
					where += " and isnull("+tjfield[i]+",'') != '' ";
					
					if(tjfield[i].indexOf("nlfw") >=0){
						doInitNlfw();
						t_mc += ",isNull((SELECT MC DICTNAME FROM dbo.EXP_NLFW AS b WHERE   (d."+tjfield[i]+" = dm)),'') AS "+tjfield[i]+"_mc";
					}else{
						t_mc += ",isNull((SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = "+tranM(tList, "dm", tjfield[i], "tcmcid")+") AND (d."+tjfield[i]+" = DICTBH)),'') AS "+tjfield[i]+"_mc";
					}
					
				}
			}
			sb.append("<th>人数(位)</th>");
			sb.append("</tr></thead>");
			
			sql = " select count(distinct a.rcid ) cc,"+fields+" from exp_main a ";
			
			sql =  sql + where +" group by "+fields;
			sql = " select d.*"+t_mc+" from ("+sql+") d order by "+fields;
			
			List<Map<String, Object>> tlist = this.getSimpleJdbcTemplate().queryForList(sql);
			for(int i=0;i<tlist.size();i++){
				sb.append("<tr>");
				sb.append("<th class='spec'>"+(i+1)+"</th>");
				
				for(int j=0;j<tjfield.length;j++){
					if(!tjfield[j].equals("")){
						sb.append("<td>"+tlist.get(i).get(tjfield[j]+"_mc")+"</td>");
					}
					
				}
				sb.append("<td>"+String.valueOf(tlist.get(i).get("cc"))+"</td>");
				sb.append("</tr>");
			}
			sb.append("</table>");
		}
		return sb.toString();
	}
	
	
	public List getXYfList(String xy,Xtuser xtuser){
		List list = new ArrayList();
		String sql = "" ;
		
		if(xy.indexOf("nlfw")>=0){
			sql = " select dm dictbh,mc dictname from exp_nlfw order by isnull(nl1,0) ";
		}else{
			sql = " select distinct "+xy+"  from exp_main a  ";
			String where = "";
			
			sql = sql + " where 1=1 "+where;
			
			sql = " select dictbh,dictname from xt_dict where dictbh in("+sql+") " +
					"and lbid=( select tcmcid from t_field where tid=1 and dm='"+xy+"')order by dictbh";
		}
		list = this.getSimpleJdbcTemplate().queryForList(sql);
		return list;
	}
	/**
	 * 多维统计
	 * @param xf:横向
	 * @param yf:纵向
	 * @param xtuser
	 * @return
	 */
	public List doDwtj(String xf,String yf,Map<String, String> parMap,Xtuser xtuser){
		List list = new ArrayList();
		String sql = "";
		String where = "";
		
		if(xf.indexOf("nlfw")>=0 || yf.indexOf("nlfw") >=0){
			//初始化年龄范围
			doInitNlfw();
		}
		
		sql = " select count(distinct a.rcid ) cc,"+xf+" aa,"+yf+" bb from EXP_MAIN a ";
		
		sql += "  where 1=1 ";
		String xm = parMap.get("xm") == null ? "" :  parMap.get("xm");
		String nation = parMap.get("nation") == null ? "" :  parMap.get("nation");
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
		//String nlfw = parMap.get("nlfw") == null ?"":parMap.get("nlfw");
		if(!xm.trim().equals("")){
			sql += " and a.RCNAME like '%"+xm+"%'";
		}
		if(!nation.equals("")){
			sql += " and a.nation='"+nation+"'";
		}
		if(!xl.equals("")){
			sql += " and a.xl='"+xl+"'";
		}
		if(!xw.equals("")){
			sql += " and a.xw='"+xw+"'";
		}
		if(!zc.equals("")){
			sql += " and a.zc='"+zc+"'" ;
		}
		if(!zw.equals("")){
			sql += " and a.zw='"+zw+"'";
		}
		if(!zgbm.equals("")){
			sql += " and a.zgbm='"+zgbm+"'" ;
		}
		if(!dwxz.equals("")){
			sql += " and a.dwxz='"+dwxz+"'" ;
		}
		
		if(!strnl.trim().equals("") && !strnl.trim().equals("0")){
			sql += " and convert(varchar(100),a.birthday,23)<='"+this.getBirthday(Integer.parseInt(strnl), 0)+"'" ;
		}
		if(!endnl.trim().equals("") && !endnl.trim().equals("0")){
			sql += " and convert(varchar(100),a.birthday,23)>='"+this.getBirthday(Integer.parseInt(endnl), 1)+"'" ;
		}
		
		
		
		sql =  sql + where +" group by "+xf+","+yf;
		sql = " select d.cc,d.aa+'xy'+d.bb as bh from ("+sql+") d where isnull(d.aa,'')!='' and isnull(d.bb,'')!='' order by aa,bb";
		list = this.getSimpleJdbcTemplate().queryForList(sql);
		return list;
	}
	
	/**
	 * 对年龄范围进行初始化
	 * 便于统计
	 * @return
	 */
	public int doInitNlfw(){
		String sql = "";
		sql = " select DM,MC,isnull(NL1,0) NL1,isnull(NL2,0) NL2,isnull(BZ,0) BZ from exp_nlfw ";
		List<Map<String, Object>> list = new ArrayList();
		list = this.getJdbcTemplate().queryForList(sql, new Object[]{});
		
		for(int i=0;i<list.size();i++){
			String strnl = String.valueOf(list.get(i).get("NL1"));
			String endnl = String.valueOf(list.get(i).get("NL2"));
			sql = " update EXP_MAIN set nlfw="+String.valueOf(list.get(i).get("DM"))+" where 1=1 ";
			if(!strnl.trim().equals("") && !strnl.trim().equals("0")){
				sql += " and convert(varchar(100),birthday,23)<='"+this.getBirthday(Integer.parseInt(strnl), 0)+"'" ;
			}
			if(!endnl.trim().equals("") && !endnl.trim().equals("0")){
				sql += " and convert(varchar(100),birthday,23)>='"+this.getBirthday(Integer.parseInt(endnl), 1)+"'" ;
			}
			this.getSimpleJdbcTemplate().update(sql);
		}
		return 1;
	}
	
	public Object tranM(List<Map<String, Object>> l,String ff,String tV,String rf){
		for(int i=0;i<l.size();i++){
			if(l.get(i).get(ff).equals(tV)){
				return String.valueOf(l.get(i).get(rf));
			}
		}
		return "";
	}
	
}
