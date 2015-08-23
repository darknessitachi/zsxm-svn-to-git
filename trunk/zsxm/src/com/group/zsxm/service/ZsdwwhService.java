package com.group.zsxm.service;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.ldap.SortControl;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.stereotype.Service;
import jxl.write.Number;
import com.fins.gt.model.FilterInfo;
import com.fins.gt.model.SortInfo;
import com.group.core.common.LimitPage;
import com.group.zsxm.entity.DmMc;
import com.group.zsxm.exception.BusException;
import com.group.zsxm.service.common.BaseService;

@Service
public class ZsdwwhService extends BaseService{
	public Object getListForZsdw(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos){
		String name = parMap.get("name") == null ? "" :  parMap.get("name");
		String where = parMap.get("where") == null ? "" :  parMap.get("where");
		String gsearch = parMap.get("gsearch") == null ? "" :  parMap.get("gsearch");
		
		String ls_sql = "select dw_info_v.*," +
				"convert(varchar(20),convert(numeric(20,2),((isnull(convert(numeric(20,2),SNXSSR),0)-isnull(SNXSSR_,0))/isnull(nullif(SNXSSR_,0),1))*100.00))+'%' XSSRBL," +
				"convert(varchar(20),convert(numeric(20,2),((isnull(convert(numeric(20,2),SNJNSS),0)-isnull(SNJNSS_,0))/isnull(nullif(SNJNSS_,0),1))*100.00))+'%' JNSSBL," +
				"convert(varchar(20),convert(numeric(20,2),((isnull(convert(numeric(20,2),SNDYGS),0)-isnull(SNDYGS_,0))/isnull(nullif(SNDYGS_,0),1))*100.00))+'%' YGSBL," +
				"dbo.GET_MUTSEL_MC(dw_info_v.dwid,5,32) DWSX_MC,dbo.GET_MUTSEL_MC(dw_info_v.dwid,2,11) CYFL_MC,dbo.GET_MUTSEL_MC(dw_info_v.dwid,4,13) YJLB_MC  from dw_info_v where 1=1 " ;
		
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
			ls_sql += " and gjcxkjyq = '"+depNo+"' ";
		}else if(cxtype.equals("10")){
			ls_sql += " and gxjsqy = '"+depNo+"' ";
		}else if(cxtype.equals("11")){
			ls_sql += " and rjqy = '"+depNo+"' ";
		}else if(cxtype.equals("12")){
			ls_sql += " and dmqy = '"+depNo+"' ";
		}else if(cxtype.equals("13")){
			ls_sql += " and cmmi = '"+depNo+"' ";
		}else if(cxtype.equals("14")){
			ls_sql += " and dwsb = '"+depNo+"' ";
		}else if(cxtype.equals("15")){
			ls_sql += " and gjqdxxcy = '"+depNo+"' ";
		}else if(cxtype.equals("16")){
			ls_sql += " and xdfwy = '"+depNo+"' ";
		}else if(cxtype.equals("17")){
			ls_sql += " and fwwb = '"+depNo+"' ";
		}else if(cxtype.equals("18")){
			ls_sql += " and lhrh = '"+depNo+"' ";
		}else if(cxtype.indexOf('A')>= 0){
			String var  = cxtype.substring(1,2);
			String[] sv = depNo.split("-");
			if(var.equals("1")){
				ls_sql += " and (isnull(convert(numeric(20,2),zczb),0)>="+sv[0];
				if(!sv[1].equals("以上")){
					ls_sql += " and isnull(convert(numeric(20,2),zczb),0)<="+sv[1];
				}
				ls_sql+= ")";
			}else if(var.equals("2")){
				ls_sql += " and (isnull(convert(numeric(20,2),SNXSSR),0)>="+sv[0];
				if(!sv[1].equals("以上")){
					ls_sql += " and isnull(convert(numeric(20,2),SNXSSR),0)<="+sv[1];
				}
				ls_sql+= ")";
			}else if(var.equals("3")){
				ls_sql += " and (" +
						" ((isnull(convert(numeric(20,2),SNXSSR),0)-isnull(SNXSSR_,0))/isnull(nullif(SNXSSR_,0),1))*100.00>=convert(numeric(20,2),'"+sv[0]+"')";
				if(!sv[1].equals("以上")){
					ls_sql += " and ((isnull(convert(numeric(20,2),SNXSSR),0)-isnull(SNXSSR_,0))/isnull(nullif(SNXSSR_,0),1))*100.00<=convert(numeric(20,2),'"+sv[1]+"')";
				}
				ls_sql+= ")";
			}else if(var.equals("4")){
				ls_sql += " and (isnull(convert(numeric(20,2),SNJNSS),0)>="+sv[0];
				if(!sv[1].equals("以上")){
					ls_sql += " and isnull(convert(numeric(20,2),SNJNSS),0)<="+sv[1];
				}
				ls_sql+= ")";
			}else if(var.equals("5")){
				ls_sql += " and (" +
						" ((isnull(convert(numeric(20,2),SNJNSS),0)-isnull(SNJNSS_,0))/isnull(nullif(SNJNSS_,0),1))*100.00>=convert(numeric(20,2),'"+sv[0]+"')";
				if(!sv[1].equals("以上")){
					ls_sql += " and ((isnull(convert(numeric(20,2),SNJNSS),0)-isnull(SNJNSS_,0))/isnull(nullif(SNJNSS_,0),1))*100.00<=convert(numeric(20,2),'"+sv[1]+"')";
				}
				ls_sql+= ")";
			}else if(var.equals("6")){
				ls_sql += " and (isnull(convert(numeric(20,2), SNDYGS),0)>="+sv[0];
				if(!sv[1].equals("以上")){
					ls_sql += " and isnull(convert(numeric(20,2), SNDYGS),0)<="+sv[1];
				}
				ls_sql+= ")";
			}else if(var.equals("7")){
				ls_sql += " and (" +
						" ((isnull(convert(numeric(20,2),SNDYGS),0)-isnull(SNDYGS_,0))/isnull(nullif(SNDYGS_,0),1))*100.00>=convert(numeric(20,2),'"+sv[0]+"')";
				if(!sv[1].equals("以上")){
					ls_sql += " and ((isnull(convert(numeric(20,2),SNDYGS),0)-isnull(SNDYGS_,0))/isnull(nullif(SNDYGS_,0),1))*100.00<=convert(numeric(20,2),'"+sv[1]+"')";
				}
				ls_sql+= ")";
			}
		}
		
		if(gsearch != null && !gsearch.equals("")){
			ls_sql += " and ("+gsearch+")";
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
		if (sortCond != null && !sortCond.trim().equals("asc") && !sortCond.trim().equals("desc")) {
			String[] sortConds = sortCond.trim().split(" ");
			if(sortConds[0].equals("XSSRBL")||sortConds[0].equals("JNSSBL")||sortConds[0].equals("YGSBL")){
				ls_sql += " order by CLRQ desc,DWZT" ;
			}else{
				ls_sql += " order by  case when isnull(convert(varchar(20),"+sortConds[0]+"),'')='' then 1 else 0 end , "+sortConds[0]+"  "+sortConds[1];
			}
			
		} else {
			ls_sql += " order by CLRQ desc,DWZT ";
		}
		if (limit != null) {
			return this.queryForListWithPage(ls_sql, limit);
		} else {
			return this.simpleJdbcTemplate.queryForList(ls_sql);
		}
	}
	
	public Object getListForZsdwYqfw(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos){
		String name = parMap.get("name") == null ? "" :  parMap.get("name");
		String where = parMap.get("where") == null ? "" :  parMap.get("where");
		String gsearch = parMap.get("gsearch") == null ? "" :  parMap.get("gsearch");
		
		String ls_sql = "select dw_yqfw_v.* ," +
				"dbo.GET_MUTSEL_MC(dw_yqfw_v.dwid,5,32) DWSX_MC  from dw_yqfw_v where 1=1 " ;
		
		if(where != null && !where.equals("")){
			ls_sql += " and upper("+where+") like '%" + name.toUpperCase() + "%'";
		}
		
		String depNo = parMap.get("depNo") == null ? "" :  parMap.get("depNo");
		String cxtype = parMap.get("cxtype") == null ? "" :  parMap.get("cxtype");
		
		if(gsearch != null && !gsearch.equals("")){
			ls_sql += " and ("+gsearch+")";
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
		if (sortCond != null && !sortCond.trim().equals("asc") && !sortCond.trim().equals("desc")) {
			String[] sortConds = sortCond.trim().split(" ");
			if(sortConds[0].equals("XSSRBL")||sortConds[0].equals("JNSSBL")||sortConds[0].equals("YGSBL")){
				ls_sql += " order by CLRQ desc,DWZT" ;
			}else{
				ls_sql += " order by  case when isnull(convert(varchar(20),"+sortConds[0]+"),'')='' then 1 else 0 end , "+sortConds[0]+"  "+sortConds[1];
			}
			
		} else {
			ls_sql += " order by CLRQ desc ";
		}
		if (limit != null) {
			return this.queryForListWithPage(ls_sql, limit);
		} else {
			return this.simpleJdbcTemplate.queryForList(ls_sql);
		}
	}
	
	
	public Object getListForZsdwxm(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos){
		String name = parMap.get("name") == null ? "" :  parMap.get("name");
		String where = parMap.get("where") == null ? "" :  parMap.get("where");
		String gsearch = parMap.get("gsearch") == null ? "" :  parMap.get("gsearch");
		String ls_sql = " select * from dw_cdxm_v where 1=1  ";
		
		if(where != null && !where.equals("")){
			ls_sql += " and upper("+where+") like '%" + name.toUpperCase() + "%'";
		}
		
		String depNo = parMap.get("depNo") == null ? "" :  parMap.get("depNo");
		String cxtype = parMap.get("cxtype") == null ? "" :  parMap.get("cxtype");
		
		if(cxtype.equals("1")){
			ls_sql += " and xmjb = '"+depNo+"' ";
		}else if(cxtype.equals("2")){
			ls_sql += " and jhlb = '"+depNo+"' ";
		}
		
		if(gsearch != null && !gsearch.equals("")){
			ls_sql += " and ("+gsearch+")";
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
	
	public int doDeletezsdw(String dwid){
		if(dwid != null && !dwid.equals("")){
			
			String sql = " update dw_info set status=9 where dwid in ("+dwid+")";
			this.getSimpleJdbcTemplate().update(sql);
			
			sql = " update dw_gqbl set status=9 where dwid in ("+dwid+")";
			this.getSimpleJdbcTemplate().update(sql);
			
			sql = " update dw_mutsel set status=9 where dwid in ("+dwid+")";
			this.getSimpleJdbcTemplate().update(sql);
			
			sql = " update dw_xm set status=9 where dwid in ("+dwid+")";
			this.getSimpleJdbcTemplate().update(sql);
			
		}else{
			throw new BusException("请选择需要删除的数据！");
		}
		return 1;
	}
	
	/**
	 * 导出EXCEL 
	 */
	public void doExportExcel(OutputStream os){
		//Long l = System.currentTimeMillis();
		//System.out.println(l);
		WritableWorkbook wwb = null;
		//ByteArrayOutputStream os = new ByteArrayOutputStream();
		String sql = "";
		//String xx = "";
		try{
			List<DmMc> headerlist = new ArrayList();
			/**
			 * 
			 
			headerlist.add(new DmMc("序号","XH",6));
			headerlist.add(new DmMc("机构名称","DWMC",50));
			headerlist.add(new DmMc("组织机构代码","DWDM",18));
			headerlist.add(new DmMc("单位类型","DWLX_MC",30));
			headerlist.add(new DmMc("单位属性","DWSX_MC",50));
			headerlist.add(new DmMc("产业分类","CYFL_MC",50));
			headerlist.add(new DmMc("技术专业","JSZY_MC",50));
			headerlist.add(new DmMc("是否注册","DWZT_MC",15));
			headerlist.add(new DmMc("内/外资","NWZ_MC",15));
			headerlist.add(new DmMc("注册资本(万元)","ZCZB",15));
			headerlist.add(new DmMc("建筑面积(平方/米)","JZMJ",18));
			headerlist.add(new DmMc("成立日期","CLRQ",18));
			headerlist.add(new DmMc("法人代表","FRDB",12));
			headerlist.add(new DmMc("引进类别","YJLB_MC",50));
			headerlist.add(new DmMc("招资项目","XM_MC",25));
			headerlist.add(new DmMc("海归团队批次","HGTDPC",20));
			headerlist.add(new DmMc("组织机构代码","SWGLM",20));
			headerlist.add(new DmMc("纳税人识别号","NSRSBH",20));
			headerlist.add(new DmMc("主要联系人","#LXRXM",12));
			headerlist.add(new DmMc("性别","#SEX_MC",10));
			headerlist.add(new DmMc("职称","#ZC_MC",13));
			headerlist.add(new DmMc("职务","#ZW",13));
			headerlist.add(new DmMc("联系电话","#TEL",15));
			headerlist.add(new DmMc("手机","#PHONE",15));
			headerlist.add(new DmMc("邮箱","#EMAIL",15));
			headerlist.add(new DmMc("QQ","#QQ",15));
			headerlist.add(new DmMc("所属部门","#SSBM",15));
			headerlist.add(new DmMc("用户登入名","LOGINNAME",12));
			headerlist.add(new DmMc("密码","PASSWORD",12));
			headerlist.add(new DmMc("办公地点","BGDD_MC",50));
			headerlist.add(new DmMc("单位简介","DWJJ",50));
			headerlist.add(new DmMc("备注","SM",50));
			*/
			headerlist.add(new DmMc("序号","XH",6));
			headerlist.add(new DmMc("机构名称","DWMC",50));
			headerlist.add(new DmMc("组织机构代码","DWDM",18));
			headerlist.add(new DmMc("法人代表","FRDB",12));
			headerlist.add(new DmMc("单位状态","DWZT_MC",15));
			headerlist.add(new DmMc("单位类型","DWLX_MC",30));
			headerlist.add(new DmMc("内/外资","NWZ_MC",15));
			headerlist.add(new DmMc("注册资本(万元)","ZCZB",15));
			headerlist.add(new DmMc("成立日期","CLRQ",18));
			headerlist.add(new DmMc("是否双创团队","HGTDPC",18));
			headerlist.add(new DmMc("团队批次","TDPC_MC",18));
			
			headerlist.add(new DmMc("联系人","#LXRXM",12));
			headerlist.add(new DmMc("联系电话","#TEL",15));
			headerlist.add(new DmMc("备注","SM",50));
			//Start EXCEL 
			//创建 Excel 
			wwb = Workbook.createWorkbook(os);
			
			WritableSheet ws = wwb.createSheet("科教城单位",0);
			 
			jxl.write.Label labelC = null;
			Number labelNF = null;
			
			//文本
    	    jxl.write.WritableFont wfc = new jxl.write.WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLUE);
    	    jxl.write.WritableCellFormat wcfFC = new jxl.write.WritableCellFormat(wfc);
    	   
    	    
    	    jxl.write.WritableFont wfcc = new jxl.write.WritableFont(WritableFont.ARIAL, 11, WritableFont.NO_BOLD, false,UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
    	    jxl.write.WritableCellFormat wcfFCC = new jxl.write.WritableCellFormat(wfcc);
    	    wcfFCC.setWrap(true);
    	    
    	    //数值
		    jxl.write.NumberFormat nf = new jxl.write.NumberFormat("0.00");
		    jxl.write.WritableCellFormat wcfN = new jxl.write.WritableCellFormat(nf);
		    
		    int row =0; 
		    for(int i=0;i<headerlist.size();i++){
		         labelC = new Label(i,row,headerlist.get(i).getDm(),wcfFC);
		    	 ws.addCell(labelC);
		    	 ws.setColumnView(i, headerlist.get(i).getWidth());//
		    }
		    ++row;
		    
			sql = "SELECT     DWID, DWDM, DWZT, DWMC, isNUll(LOGINNAME,'') LOGINNAME, isNull(PASSWORD,'') PASSWORD, isNull(FRDB,'') FRDB, isNull(CONVERT(varchar(100), CLRQ, 23),'') AS CLRQ, NWZ, CONVERT(varchar(20),isNull(ZCZB,0)) ZCZB, isnull(DWJJ,'') DWJJ, BGDD1," +
				" BGDD2, BGDD3, CONVERT(varchar(20),isNull(JZMJ,0)) JZMJ, LXRXM, SEX, ZC, ZW, TEL, PHONE, SSBM, DWLX,isnull(SM,'') SM,isnull(SWGLM,'') SWGLM,isnull(NSRSBH,'') NSRSBH,isnull(HGTDPC,'2') HGTDPC,isnull(TDPC,'') TDPC," +
				" isNull((SELECT     DICTNAME  FROM          dbo.XT_DICT AS b WHERE      (LBID = 4) AND (a.DWZT = DICTBH)),'') AS DWZT_MC," +
				" isNull((SELECT     DICTNAME  FROM          dbo.XT_DICT AS b WHERE      (LBID = 5) AND (a.NWZ = DICTBH)),'') AS NWZ_MC," +
				" ('常州科教城-'+isNUll((SELECT     DICTNAME  FROM          dbo.XT_DICT AS b WHERE      (LBID = 6) AND (a.BGDD1 = DICTBH)),'')+" +
				" isNull((SELECT     DICTNAME  FROM          dbo.XT_DICT AS b WHERE      (LBID = 7) AND (a.BGDD2 = DICTBH)),'')+'-'+" +
				" isNull((SELECT     DICTNAME  FROM          dbo.XT_DICT AS b WHERE      (LBID = 8) AND (a.BGDD3 = DICTBH)),'')+'-'+isNUll(BGDD4,'')) AS BGDD_MC," +
				" isNull((SELECT     DICTNAME  FROM          dbo.XT_DICT AS b WHERE      (LBID = 9) AND (a.DWLX = DICTBH)),'') AS DWLX_MC," +
				" isNull((SELECT     DICTNAME  FROM          dbo.XT_DICT AS b WHERE      (LBID = 14) AND (a.SEX = DICTBH)),'') AS SEX_MC," +
				" isNull((SELECT     DICTNAME  FROM          dbo.XT_DICT AS b WHERE      (LBID = 15) AND (a.ZC = DICTBH)),'') AS ZC_MC," +
				" isNull((SELECT     DICTNAME  FROM          dbo.XT_DICT AS b WHERE      (LBID = 30) AND (a.TDPC = DICTBH)),'') AS TDPC_MC," +
				" isNull((SELECT     XMMC FROM    dbo.XM_INFO WHERE      (XMID = (SELECT     XMID FROM dbo.DW_XM WHERE      (DWID = a.DWID)))),'') AS XM_MC" +
				" FROM  dbo.DW_INFO AS a WHERE     ISNULL(STATUS, 1) = 1 order by dwid";
			List<Map<String, Object>> dwxxlist = this.getJdbcTemplate().queryForList(sql);
			/**
			sql = " select a.DWID,a.XH,a.LXRXM,a.SEX,a.ZW,a.TEL,a.PHONE,a.SSBM,isnull(dylxrbz,0) dylxrbz,a.EMAIL,a.QQ, " +
				" (select b.dictname from xt_dict b where b.lbid=14 and b.dictbh=a.sex) as SEX_MC ," +
				" (select b.dictname from xt_dict b where b.lbid=15 and b.dictbh=a.zc) as ZC_MC " +
				" from dw_lxr a where  and isNull(a.status,1)=1 and isnull(dylxrbz,0)=1 order by xh";
			List<Map<String, String>> lxrlist = this.getJdbcTemplate().queryForList(sql);
			**/
			for(int i=0;i<dwxxlist.size();i++){
				labelC = new Label(0,row,String.valueOf((i+1)),wcfFC);
		    	ws.addCell(labelC);
				for(int j=1;j<headerlist.size();j++){
					//xx = headerlist.get(j).getMc();
					if(headerlist.get(j).getMc().indexOf("#")==0){
						sql = " select a.DWID,a.XH,a.LXRXM,a.SEX,a.ZW,a.TEL,a.PHONE,a.SSBM,isnull(dylxrbz,0) dylxrbz,a.EMAIL,a.QQ, " +
							" (select b.dictname from xt_dict b where b.lbid=14 and b.dictbh=a.sex) as SEX_MC ," +
							" (select b.dictname from xt_dict b where b.lbid=15 and b.dictbh=a.zc) as ZC_MC " +
							" from dw_lxr a where  isNull(a.status,1)=1 and isnull(dylxrbz,0)=1 and dwid="+String.valueOf(dwxxlist.get(i).get("DWID"));
						List<Map<String, String>> lxrlist = this.getJdbcTemplate().queryForList(sql);
						if(lxrlist.size() > 0){
							labelC = new Label(j,row,lxrlist.get(0).get(headerlist.get(j).getMc().substring(1,headerlist.get(j).getMc().length())),wcfFCC);
					    	ws.addCell(labelC);
						}else{
							labelC = new Label(j,row,"",wcfFCC);
					    	ws.addCell(labelC);
						}
					}else if(headerlist.get(j).getMc().equals("DWSX_MC")){
						List<Map<String, String>> list = this.getZsdwMutSelWithViewByDwid(String.valueOf(dwxxlist.get(i).get("DWID")), "1","10");
						String mc = "";
						for(int k=0;k<list.size();k++){
							mc += "<"+(k+1)+">: "+list.get(k).get("SELDM_MC")+"   ";
						}
						labelC = new Label(j,row,mc,wcfFCC);
				    	ws.addCell(labelC);
				    	
					}else if(headerlist.get(j).getMc().equals("CYFL_MC")){
						List<Map<String, String>> list = this.getZsdwMutSelWithViewByDwid(String.valueOf(dwxxlist.get(i).get("DWID")), "2","11");
						String mc = "";
						for(int k=0;k<list.size();k++){
							mc += "<"+(k+1)+">: "+list.get(k).get("SELDM_MC")+"   ";
						}
						labelC = new Label(j,row,mc,wcfFCC);
				    	ws.addCell(labelC);
					}else if(headerlist.get(j).getMc().equals("JSZY_MC")){
						List<Map<String, String>> list = this.getZsdwMutSelWithViewByDwid(String.valueOf(dwxxlist.get(i).get("DWID")), "3","12");
						String mc = "";
						for(int k=0;k<list.size();k++){
							mc += "<"+(k+1)+">: "+list.get(k).get("SELDM_MC")+"   ";
						}
						labelC = new Label(j,row,mc,wcfFCC);
				    	ws.addCell(labelC);
					}else if(headerlist.get(j).getMc().equals("YJLB_MC")){
						List<Map<String, String>> list = this.getZsdwMutSelWithViewByDwid(String.valueOf(dwxxlist.get(i).get("DWID")), "4","13");
						String mc = "";
						for(int k=0;k<list.size();k++){
							mc += "<"+(k+1)+">: "+list.get(k).get("SELDM_MC")+"   ";
						}
						labelC = new Label(j,row,mc,wcfFCC);
				    	ws.addCell(labelC);
					}else{
						if(headerlist.get(j).getMc().equals("ZCZB") || headerlist.get(j).getMc().equals("JZMJ")){
							labelNF = new Number(j,row,Double.parseDouble(String.valueOf(dwxxlist.get(i).get(headerlist.get(j).getMc()))),wcfFCC);
	  						ws.addCell(labelNF);
						}else if(headerlist.get(j).getMc().equals("HGTDPC") ){
							labelC = new Label(j,row,String.valueOf(!dwxxlist.get(i).get("TDPC").equals("")?"是":"否"),wcfFCC);
					    	ws.addCell(labelC);
						}else{
							labelC = new Label(j,row,String.valueOf(dwxxlist.get(i).get(headerlist.get(j).getMc())),wcfFCC);
					    	ws.addCell(labelC);
						}
					}
					
				}
				++row;
			}
			
		    wwb.write();
	    	wwb.close();
		   // System.out.println(System.currentTimeMillis() - l);
		}catch(Exception e){
			//System.out.println(xx);
			throw new BusException(e.toString());
		}finally{
			try{
				if(os != null){
					//os.flush();
					os.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 获取多选框
	 * @param dwid
	 * @param selid
	 * @param lbid
	 * @return
	 */
	public List getZsdwMutSelWithViewByDwid(String dwid,String selid,String lbid){
		List list = new ArrayList();
		String sql = " select a.*," +
				"(select b.dictname from xt_dict b where b.lbid="+lbid+" and b.dictbh=a.seldm) as SELDM_MC " +
				" from dw_mutsel a where a.dwid="+dwid+" and a.selid="+selid;
		list = this.getSimpleJdbcTemplate().queryForList(sql);
		return list;
	}
}
