package com.group.zsxm.service;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.stereotype.Service;

import com.fins.gt.model.FilterInfo;
import com.fins.gt.model.SortInfo;
import com.group.core.common.LimitPage;

import com.group.zsxm.entity.DmMc;
import com.group.zsxm.exception.BusException;
import com.group.zsxm.service.common.BaseService;

@Service
public class ZsxmwhService extends BaseService{
	
	public Object getListForZsxm(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos,String xmgzr){
		String name = parMap.get("name") == null ? "" :  parMap.get("name");
		String where = parMap.get("where") == null ? "" :  parMap.get("where");
		String gsearch = parMap.get("gsearch") == null ? "" :  parMap.get("gsearch");
		
		String ls_sql = " select * from xm_info_v where 1=1 " ;
		
		if(xmgzr != null && !xmgzr.equals("")){
			ls_sql += " and xmgzr='"+xmgzr+"'";
		}
		
		if(where != null && !where.equals("")){
			ls_sql += " and upper("+where+") like '%" + name.toUpperCase().trim() + "%'";
		}
		
		String depNo = parMap.get("depNo") == null ? "" :  parMap.get("depNo");
		String cxtype = parMap.get("cxtype") == null ? "" :  parMap.get("cxtype");
		
		if(cxtype.equals("1")){
			ls_sql += " and xmlb = '"+depNo+"' ";
		}else if(cxtype.equals("2")){
			ls_sql += " and xmxj = '"+depNo+"' ";
		}else if(cxtype.equals("3")){
			ls_sql += " and xmjdgs = '"+depNo+"' ";
		}
		if(gsearch != null && !gsearch.equals("")){
			ls_sql += " and ("+gsearch+")";
		}
		String sortCond = getGridSortString(sortInfo);
		String filterCond = getGridFilterString(filterInfos);
		if (filterCond != null) {
			ls_sql += filterCond;
		}
		if (sortCond != null && !sortCond.trim().equals("asc") && !sortCond.trim().equals("desc")) {
			String[] sortConds = sortCond.trim().split(" ");
			
			ls_sql += " order by  case when isnull(convert(varchar(20),"+sortConds[0]+"),'')='' then 1 else 0 end , "+sortConds[0]+"  "+sortConds[1];
		} else {
			ls_sql += " order by rq desc,XMJDGS  ";
		}
		if (limit != null) {
			return this.queryForListWithPage(ls_sql, limit);
		} else {
			return this.simpleJdbcTemplate.queryForList(ls_sql);
		}
	}
	

	public Object getListForZsxmJzqk(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos,String xmgzr){
		String name = parMap.get("name") == null ? "" :  parMap.get("name");
		String where = parMap.get("where") == null ? "" :  parMap.get("where");
		String gsearch = parMap.get("gsearch") == null ? "" :  parMap.get("gsearch");
		
		String ls_sql = " select * from xm_jzqk_v where 1=1 " ;
		
		if(xmgzr != null && !xmgzr.equals("")){
			ls_sql += " and xmgzr='"+xmgzr+"'";
		}
		
		if(where != null && !where.equals("")){
			ls_sql += " and upper("+where+") like '%" + name.toUpperCase().trim() + "%'";
		}
		
		String depNo = parMap.get("depNo") == null ? "" :  parMap.get("depNo");
		String cxtype = parMap.get("cxtype") == null ? "" :  parMap.get("cxtype");
		
		if(gsearch != null && !gsearch.equals("")){
			ls_sql += " and ("+gsearch+")";
		}
		String sortCond = getGridSortString(sortInfo);
		String filterCond = getGridFilterString(filterInfos);
		if (filterCond != null) {
			ls_sql += filterCond;
		}
		if (sortCond != null && !sortCond.trim().equals("asc") && !sortCond.trim().equals("desc")) {
			String[] sortConds = sortCond.trim().split(" ");
			
			ls_sql += " order by  case when isnull(convert(varchar(20),"+sortConds[0]+"),'')='' then 1 else 0 end , "+sortConds[0]+"  "+sortConds[1];
		} else {
			ls_sql += " order by jzrq desc,dwid ";
		}
		if (limit != null) {
			return this.queryForListWithPage(ls_sql, limit);
		} else {
			return this.simpleJdbcTemplate.queryForList(ls_sql);
		}
	}
	
	public int doDeletezsxm(String xmid){
		if(xmid != null && !xmid.equals("")){
			String sql = " update xm_info set status=9 where xmid in ("+xmid+")";
			this.getSimpleJdbcTemplate().update(sql);
		}else{
			throw new BusException("请选择需要删除的数据！");
		}
		return 1;
	}
	
	/**
	 * 导出全部项目信息
	 */
	public void doExportExcel(OutputStream os,String xmgzr){
		WritableWorkbook wwb = null;
		//ByteArrayOutputStream os = new ByteArrayOutputStream();
		String sql = "";
		try{
			List<DmMc> headerlist = new ArrayList();
			headerlist.add(new DmMc("序号","XH",6));
			headerlist.add(new DmMc("项目名称","XMMC",50));
			headerlist.add(new DmMc("日期","RQ",18));
			headerlist.add(new DmMc("项目类别","XMLB_MC",22));
			headerlist.add(new DmMc("项目星级","XMXJ_MC",22));
			headerlist.add(new DmMc("项目概述","XMGS",50));
			headerlist.add(new DmMc("项目进度概述","XMJDGS_MC",30));
			headerlist.add(new DmMc("预计投入(万)","YJTR",20));
			headerlist.add(new DmMc("对方联系人","DFLXR",20));
			headerlist.add(new DmMc("对方联系人电话","DFLXRTEL",20));
			headerlist.add(new DmMc("对方联系人手机","DFLXRPHONE",20));
			headerlist.add(new DmMc("项目跟踪人","XMGZR",20));
			headerlist.add(new DmMc("项目进展情况","XMJZQK",50));
			headerlist.add(new DmMc("项目问题","XMWT",50));
			headerlist.add(new DmMc("备注","BZXX",50));
			
			wwb = Workbook.createWorkbook(os);
			
			WritableSheet ws = wwb.createSheet("科教城项目",0);
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
		    
		    sql = "  select *  from xm_info_v  where 1=1 ";
		    if(xmgzr != null && !xmgzr.equals("")){
		    	sql += " and xmgzr='"+xmgzr+"'";
			}
		    sql += " order by xmjdgs ";
		    
		    List<Map<String, Object>> xmlist = this.getJdbcTemplate().queryForList(sql);
		    String v = "";
		    for(int i=0;i<xmlist.size();i++){
		    	labelC = new Label(0,row,String.valueOf((i+1)),wcfFC);
		    	ws.addCell(labelC);
		    	for(int j=1;j<headerlist.size();j++){
		    		if(headerlist.get(j).getMc().equals("YJTR")){
						labelNF = new Number(j,row,Double.parseDouble(String.valueOf(xmlist.get(i).get(headerlist.get(j).getMc()))),wcfFCC);
  						ws.addCell(labelNF);
					}else{
						v = String.valueOf(xmlist.get(i).get(headerlist.get(j).getMc()));
						labelC = new Label(j,row,v.equals("null")?"":v,wcfFCC);
				    	ws.addCell(labelC);
					}
		    	}
		    	++row;
		    }
		    wwb.write();
	    	wwb.close();
		}catch(Exception e){
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
}
