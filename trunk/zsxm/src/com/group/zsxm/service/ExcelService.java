package com.group.zsxm.service;

import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.stereotype.Service;

import com.group.core.common.Page;
import com.group.zsxm.exception.BusException;
import com.group.zsxm.service.common.BaseService;

@Service
public class ExcelService extends BaseService{

	public void doExportDwjfh(OutputStream os,String dwid,String dwmc,String d){
		WritableWorkbook wwb = null;
		
		try{
			//创建 Excel 
			wwb = Workbook.createWorkbook(os);
			
			WritableSheet ws = wwb.createSheet("经费资助",0);

			jxl.write.Label labelC = null;
			Number labelNF = null;
			
			jxl.write.WritableFont wfcT = new jxl.write.WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
    	    jxl.write.WritableCellFormat wcfFCT = new jxl.write.WritableCellFormat(wfcT);
    	    wcfFCT.setAlignment(Alignment.CENTRE);
    	    

    	    jxl.write.WritableFont wfcT_ = new jxl.write.WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
    	    jxl.write.WritableCellFormat wcfFC_ = new jxl.write.WritableCellFormat(wfcT_);
    	    
			jxl.write.WritableFont wfc = new jxl.write.WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
    	    jxl.write.WritableCellFormat wcfFC = new jxl.write.WritableCellFormat(wfc);
    	    wcfFC.setAlignment(Alignment.CENTRE);
    	    wcfFC.setWrap(true);//自动换行
    	    
    	    jxl.write.WritableFont wfcc = new jxl.write.WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false,UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
    	    jxl.write.WritableCellFormat wcfFCC = new jxl.write.WritableCellFormat(wfcc);
    	    wcfFCC.setWrap(true);
    	    
    	    //数值
		    jxl.write.NumberFormat nf = new jxl.write.NumberFormat("0.00");
		    jxl.write.WritableCellFormat wcfN = new jxl.write.WritableCellFormat(nf);
		    
		    int mr = 0;
		    ws.mergeCells(mr, 0, mr+12, 0);//标题
		    ws.mergeCells(mr, 1, mr+12, 1);//副标题
		    ws.mergeCells(mr, 2, mr, 3); //字段名
		    ws.mergeCells(mr+1, 2, mr+1, 3); //字段名
		    ws.mergeCells(mr+2, 2, mr+2, 3); //字段名
		    ws.mergeCells(mr+3, 2, mr+3, 3); //字段名
		    ws.mergeCells(mr+4, 2, mr+6, 2); //字段名
		    ws.mergeCells(mr+7, 2, mr+10, 2); //字段名
		    ws.mergeCells(mr+11, 2, mr+11, 2); //字段名
		    ws.mergeCells(mr+12, 2, mr+12, 2); //字段名
		    
		    int row = 0;
		    labelC = new Label(mr,row,"金凤凰高层次人才”经费资助（奖励）登记表",wcfFCT);
	    	ws.addCell(labelC);
	    	
	    	++row;
	    	labelC = new Label(mr,row,"单位：（盖章） "+dwmc+"                                                                                                                                                     "+d,wcfFC_);
	    	ws.addCell(labelC);
	    	
	    	++row;
	    	labelC = new Label(mr,row,"序号",wcfFC);
	    	ws.addCell(labelC);
	    	
	    	labelC = new Label(mr+1,row,"姓名",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr+1, 10);
	    	
	    	labelC = new Label(mr+2,row,"学历/职称",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr+2, 10);
	    	
	    	labelC = new Label(mr+3,row,"专/兼职",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr+3, 10);
	    	
	    	labelC = new Label(mr+4,row,"生活资助经费(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	
	    	labelC = new Label(mr+7,row,"住房补贴费(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	
	    	labelC = new Label(mr+11,row,"安家费补贴总额(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr+11, 15);
	    	
	    	labelC = new Label(mr+12,row,"资助总额(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr+12, 15);
	    	
	    	++row;
	    	labelC = new Label(mr+4,row,"资助标准(万元/月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr+4, 10);
	    	
	    	labelC = new Label(mr+5,row,"在常时间(月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr+5, 10);
	    	
	    	
	    	labelC = new Label(mr+6,row,"资助总额(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr+6, 10);
	    	
	    	
	    	labelC = new Label(mr+7,row,"住宿地(城内/城外)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr+7, 10);
	    	
	    	labelC = new Label(mr+8,row,"补贴标准(万元/月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr+8, 10);
	    	
	    	labelC = new Label(mr+9,row,"在常时间(月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr+9, 10);
	    	
	    	labelC = new Label(mr+10,row,"资助总额(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr+10, 10);
	    	
	    	
			Map jfhsb = new HashMap();
			String sql = " select count(*) from jfhsb a where a.bz=1 and len(dm)=4 ";
			if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
				sql = " select * from jfhsb a where a.bz=1 and len(dm)=4 ";
				jfhsb = this.getSimpleJdbcTemplate().queryForMap(sql);
			}else{
				throw  new BusException("当前填报已结束，不能导出!");
			}
			String dm = (jfhsb.get("dm")==null?"":String.valueOf(jfhsb.get("dm")));
			
			sql = "select a.RYID,a.XM,a.SFZ,a.SEX_MC,isnull(a.XL_MC,' ') XL_MC,a.ZC_MC,a.ZJZ,b.DWID as DC," +
					"convert(numeric(20,2),b.SHZZBZ) SHZZBZ, b.SHZCSJ, convert(numeric(20,2),b.SHZZZE) SHZZZE, isnull(b.ZFZFD,0) ZFZFD, " +
					"convert(numeric(20,2),b.ZFBTBZ) ZFBTBZ, b.ZFZCSJ, convert(numeric(20,2),b.ZFBTZE) ZFBTZE," +
					" isnull(b.ZFZFD2,0) ZFZFD2, convert(numeric(20,2),b.ZFBTBZ2) ZFBTBZ2, b.ZFZCSJ2, " +
					"convert(numeric(20,2),b.ZFBTZE2) ZFBTZE2, convert(numeric(20,2),b.ZE) ZE, b.SBXX, b.BZ, b.HTYY from dw_ryxx_v a , " +
					" ( select * from jfhsb_shzf where sbdm='"+dm+"0001' and dwid='"+dwid+"' and bz=3 ) b where a.ryid=b.ryid " +
							"  and  a.dwid='"+dwid+"' and a.ryid in (select distinct ryid from DW_RYXX_JFHTYPE where dwid="+dwid+" and jfhtype in (1,2)) ";
			List<Map<String,Object>> list = this.getSimpleJdbcTemplate().queryForList(sql);
	    	for(int i=0;i<list.size();i++){
	    		++row; mr = 0;
	    		labelC = new Label(mr,row,String.valueOf(i+1),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("XM")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("XL_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("ZJZ")).equals("2")?"兼职":"专职",wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SHZZBZ")),wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SHZCSJ")),wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SHZZZE")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	String  ZFZFD = "";
		    	String  ZFBTBZ = "";
		    	String  ZFZCSJ = "";
		    	String  ZFBTZE = "";
		    	if(String.valueOf(list.get(i).get("ZFZFD")).equals("1")){
		    		ZFZFD = "城内";
		    		ZFBTBZ = String.valueOf(list.get(i).get("ZFBTBZ"));
		    		ZFZCSJ = String.valueOf(list.get(i).get("ZFZCSJ"));
		    		ZFBTZE = String.valueOf(list.get(i).get("ZFBTZE"));
		    	}
		    	
		    	if(String.valueOf(list.get(i).get("ZFZFD2")).equals("1")){
		    		ZFZFD += "  /  城外";
		    		ZFBTBZ += "  /  "+String.valueOf(list.get(i).get("ZFBTBZ2"));
		    		ZFZCSJ += "  /  "+String.valueOf(list.get(i).get("ZFZCSJ2"));
		    		ZFBTZE += "  /  "+String.valueOf(list.get(i).get("ZFBTZE2"));
		    	}
		    	labelC = new Label(++mr,row,ZFZFD,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,ZFBTBZ,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,ZFZCSJ,wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,ZFBTZE,wcfFCC);
		    	ws.addCell(labelC);
		    	
	    	}
	    	
	    	sql = "select convert(numeric(20,2),sum(isnull(b.SHZZZE,0))+sum(isnull(b.ZFBTZE,0))+sum(isnull(b.ZFBTZE2,0))) from dw_ryxx_v a , " +
			" ( select * from jfhsb_shzf where sbdm='"+dm+"0001' and dwid='"+dwid+"' and bz=3 ) b where a.ryid=b.ryid " +
					"  and  a.dwid='"+dwid+"' and a.ryid in (select distinct ryid from DW_RYXX_JFHTYPE where dwid="+dwid+" and jfhtype in (1,2)) ";
	    	String shzfze = this.getSimpleJdbcTemplate().queryForObject(sql, String.class);
	    	labelC = new Label(12,3,shzfze,wcfFCC);
	    	ws.addCell(labelC);
	    	
	    	sql = "select convert(numeric(20,2),sum(isnull(FWZE,0)))" +
			" from dw_ryxx_v a , " +
			" ( select * from jfhsb_ajf where sbdm='"+dm+"0002' and dwid='"+dwid+"' ) b where a.ryid=b.ryid " +
			"  and  a.dwid='"+dwid+"'  and a.ryid in (select distinct ryid from DW_RYXX_JFHTYPE where dwid="+dwid+" and jfhtype in (3))  ";
	    	String fwze = this.getSimpleJdbcTemplate().queryForObject(sql, String.class);
	    	labelC = new Label(11,3,fwze,wcfFCC);
	    	ws.addCell(labelC);
	    	
	    	
	    	++row;++row;
	    	ws.mergeCells(0, row, 12, row); //字段名
	    	labelC = new Label(0,row,"                                                                                                                                                                                              单位领导签字:",wcfFCC);
	    	ws.addCell(labelC);
	    	
	    	
	    	wwb.write();
	    	wwb.close();
		}catch(Exception e){
			e.printStackTrace();
			throw  new BusException("导出出现异常!");
		}
		
	}
	

	public void doExportjfh(OutputStream os,String sbdm ){
		WritableWorkbook wwb = null;
		
		String sql = "";
		try{
			//创建 Excel 
			wwb = Workbook.createWorkbook(os);
			
			
			String[] dm = sbdm.split("@");
			
			

			jxl.write.Label labelC = null;
			Number labelNF = null;
			
			jxl.write.WritableFont wfcT = new jxl.write.WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
    	    jxl.write.WritableCellFormat wcfFCT = new jxl.write.WritableCellFormat(wfcT);
    	    wcfFCT.setAlignment(Alignment.CENTRE);
    	    wcfFCT.setBorder(Border.ALL, BorderLineStyle.THIN);
    	    wcfFCT.setVerticalAlignment(VerticalAlignment.CENTRE);//垂直居中

    	    jxl.write.WritableFont wfcT_ = new jxl.write.WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
    	    jxl.write.WritableCellFormat wcfFC_ = new jxl.write.WritableCellFormat(wfcT_);
    	    wcfFC_.setBorder(Border.ALL, BorderLineStyle.THIN);
    	    wcfFC_.setVerticalAlignment(VerticalAlignment.CENTRE);//垂直居中
    	    
    	    
			jxl.write.WritableFont wfc = new jxl.write.WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
    	    jxl.write.WritableCellFormat wcfFC = new jxl.write.WritableCellFormat(wfc);
    	    wcfFC.setAlignment(Alignment.CENTRE);
    	    wcfFC.setWrap(true);//自动换行
    	    wcfFC.setBorder(Border.ALL, BorderLineStyle.THIN);
    	    wcfFC.setVerticalAlignment(VerticalAlignment.CENTRE);//垂直居中
    	    
    	    
    	    jxl.write.WritableFont wfcc = new jxl.write.WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false,UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
    	    jxl.write.WritableCellFormat wcfFCC = new jxl.write.WritableCellFormat(wfcc);
    	    wcfFCC.setWrap(true);
    	    wcfFCC.setBorder(Border.ALL, BorderLineStyle.THIN);
    	    wcfFCC.setVerticalAlignment(VerticalAlignment.CENTRE);//垂直居中
    	    
    	    //数值
		    jxl.write.NumberFormat nf = new jxl.write.NumberFormat("0.00");
		    jxl.write.WritableCellFormat wcfN = new jxl.write.WritableCellFormat(nf);
		    
		    int row = 0;
		    int mr = 0;//列
		    int rr = 0;//行
		    
		    WritableSheet ws1 = wwb.createSheet("新增安家费",0);
		    mr =0;rr =0;
		    ws1.mergeCells(mr, rr, 15, rr);//标题
		    //++rr;
		    //ws.mergeCells(mr, rr, 15, rr);//副标题
		    
		    ++rr;
		    ws1.mergeCells(mr, rr, mr, rr+1); //字段名
		    ws1.mergeCells(mr+1, rr, mr+1, rr+1); //字段名
		    ws1.mergeCells(mr+2, rr, mr+2, rr+1); //字段名
		    ws1.mergeCells(mr+3, rr, mr+3, rr+1); //字段名
		    ws1.mergeCells(mr+4, rr, mr+4, rr+1); //字段名
		    ws1.mergeCells(mr+5, rr, mr+5, rr+1); //字段名
		    ws1.mergeCells(mr+6, rr, mr+6, rr+1); //字段名
		    ws1.mergeCells(mr+7, rr, mr+7, rr+1); //字段名
		    ws1.mergeCells(mr+8, rr, mr+8, rr+1); //字段名
		    ws1.mergeCells(mr+9, rr, mr+9, rr+1); //字段名
		    ws1.mergeCells(mr+10, rr, mr+10, rr+1); //字段名
		    ws1.mergeCells(mr+11, rr, mr+11, rr+1); //字段名
		    ws1.mergeCells(mr+12, rr, mr+12, rr+1); //字段名
		    ws1.mergeCells(mr+13, rr, mr+13, rr+1); //字段名
		    ws1.mergeCells(mr+14, rr, mr+14, rr+1); //字段名
		    ws1.mergeCells(mr+15, rr, mr+15, rr+1); //字段名
		    
		    mr = 0;
		    row = 0;
		    labelC = new Label(mr,row,"常州科教城“金凤凰高层次人才引进计划”安家费申报表",wcfFCT);
	    	ws1.addCell(labelC);
	    	
	    	//++row;
	    	//labelC = new Label(mr,row,"未  变  化  人  员",wcfFCT);
	    	//ws.addCell(labelC);
	    	
	    	++row;
	    	labelC = new Label(mr,row,"序号",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 5);
	    	
	    	labelC = new Label(++mr,row,"申请人",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"性别",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 8);
	    	
	    	labelC = new Label(++mr,row,"年龄",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 8);
	    	
	    	labelC = new Label(++mr,row,"所在单位",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 28);
	    	
	    	labelC = new Label(++mr,row,"学历/学位",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"职称/职务",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 10);
	    	
	    	//labelC = new Label(++mr,row,"专业特长",wcfFC);
	    	//ws.addCell(labelC);
	    	//ws.setColumnView(mr, 18);
	    	
	    	labelC = new Label(++mr,row,"专/兼职",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"购房时间",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"资助标准总额(万元)",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"购房地",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"申请领取金额(万元)",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 12);
	    	
	    	labelC = new Label(++mr,row,"房屋面积(平米)",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 15);
	    	
	    	labelC = new Label(++mr,row,"总额(万元)",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"房屋交付时间",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"备注",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 10);
	    	
	    	//安家费
			sql = "select c.DWMC,a.RYID,a.XM,a.SFZ,a.SEX_MC,isnull(a.XL_MC,' ') XL_MC,convert(varchar(12),a.BIRTHDAY,23) BIRTHDAY," +
					"a.ZC_MC,a.ZJZ," +
					" GFDD, convert(numeric(18,2),(isnull(FWMJ,0))) FWMJ," +
					" convert(numeric(18,2),(isnull(FWZE,0))) FWZE, convert(varchar(12),JFSJ,23) JFSJ,convert(varchar(12),b.GFSJ,23) GFSJ," +
					" convert(numeric(18,2),(isnull(DYNBT,0))) DYNBT, convert(numeric(18,2),(isnull(ZZBZ,0))) ZZBZ, b.SM " +
				" from dw_ryxx_v a , jfhsb_ajf b,dw_info_v c " +
				" where a.ryid=b.ryid and a.dwid=b.dwid and a.dwid=c.dwid and (b.bz=3) and b.sbdm='"+dm[0].substring(0,4)+"0002'" +
				" order by c.dwid " ;
		    List<Map<String,Object>> listajf1 =  this.getSimpleJdbcTemplate().queryForList(sql);
		    ++row;
		    for(int i=0;i<listajf1.size();i++){
	    		++row; mr = 0;
	    		labelC = new Label(mr,row,String.valueOf(i+1),wcfFCC);
		    	ws1.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("XM")),wcfFCC);
		    	ws1.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("SEX_MC")),wcfFCC);
		    	ws1.addCell(labelC);
		    	
		    	if(String.valueOf(listajf1.get(i).get("BIRTHDAY"))==null || String.valueOf(listajf1.get(i).get("BIRTHDAY")).equals("") || String.valueOf(listajf1.get(i).get("BIRTHDAY")).equals("null")){
		    		labelC = new Label(++mr,row,"",wcfFCC);
			    	ws1.addCell(labelC);
		    	}else{
		    		labelC = new Label(++mr,row,String.valueOf(this.getAge(String.valueOf(listajf1.get(i).get("BIRTHDAY")))),wcfFCC);
			    	ws1.addCell(labelC);
		    	}
		    	
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("DWMC")),wcfFCC);
		    	ws1.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("XL_MC")),wcfFCC);
		    	ws1.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("ZC_MC")),wcfFCC);
		    	ws1.addCell(labelC);
		    	
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("ZJZ")).equals("2")?"兼职":"专职",wcfFCC);
		    	ws1.addCell(labelC);
		    	
		    	
		    	
		    //	购房时间	资助标准总额（万元）	购房地	申请领取金额（万元）	房屋面积（单位：平米）	总额（单位：万）	房屋交付时间	备注
				

		    	
		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("GFSJ")),wcfFCC);
		    	ws1.addCell(labelC);

		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("ZZBZ")),wcfFCC);
		    	ws1.addCell(labelC);

		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("GFDD")),wcfFCC);
		    	ws1.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("DYNBT")),wcfFCC);
		    	ws1.addCell(labelC);

		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("FWMJ")),wcfFCC);
		    	ws1.addCell(labelC);

		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("FWZE")),wcfFCC);
		    	ws1.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("JFSJ")),wcfFCC);
		    	ws1.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("SM")),wcfFCC);
		    	ws1.addCell(labelC);
	    	}
		    

			WritableSheet ws = wwb.createSheet("本季度生活和住房未变化和新增情况",1);
		    /////////////////////////////////////////第二个 SHEET////////////////////////////////////////////////////////////////////
		    mr =0;rr =0;
		    ws.mergeCells(mr, rr, 19, rr);//标题
		    ++rr;
		    ws.mergeCells(mr, rr, 19, rr);//副标题
		    
		    ++rr;
		    ws.mergeCells(mr, rr, mr, rr+1); //字段名
		    ws.mergeCells(mr+1, rr, mr+1, rr+1); //字段名
		    ws.mergeCells(mr+2, rr, mr+2, rr+1); //字段名
		    ws.mergeCells(mr+3, rr, mr+3, rr+1); //字段名
		    ws.mergeCells(mr+4, rr, mr+4, rr+1); //字段名
		    ws.mergeCells(mr+5, rr, mr+5, rr+1); //字段名
		    ws.mergeCells(mr+6, rr, mr+6, rr+1); //字段名
		    ws.mergeCells(mr+7, rr, mr+7, rr+1); //字段名
		   // ws.mergeCells(mr+8, 1, mr+8, 2); //字段名
		    
		    
		    ws.mergeCells(mr+8, rr, mr+10, rr); //字段名
		    ws.mergeCells(mr+11, rr, mr+18, rr); //字段名
		    ws.mergeCells(mr+19,rr, mr+19, rr+1); //字段名
		    
		    row = 0;
		    /****
		     * 获取未变化人员
		     */
		    mr = 0;
		    labelC = new Label(mr,row,"常州科教城“金凤凰高层次人才引进计划”生活资助和住房补贴申报表",wcfFCT);
	    	ws.addCell(labelC);
	    	
	    	++row;
	    	labelC = new Label(mr,row,"未  变  化  人  员",wcfFCT);
	    	ws.addCell(labelC);
	    	
	    	++row;
	    	labelC = new Label(mr,row,"序号",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 5);
	    	
	    	labelC = new Label(++mr,row,"申请人",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"性别",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 8);
	    	
	    	labelC = new Label(++mr,row,"年龄",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 8);
	    	
	    	labelC = new Label(++mr,row,"所在单位",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 28);
	    	
	    	labelC = new Label(++mr,row,"学历/学位",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"职称/职务",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	//labelC = new Label(++mr,row,"专业特长",wcfFC);
	    	//ws.addCell(labelC);
	    	//ws.setColumnView(mr, 18);
	    	
	    	labelC = new Label(++mr,row,"专/兼职",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"生活资助经费(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	
	    	++mr;++mr;++mr;
	    	labelC = new Label(mr,row,"住房补贴费(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	
	    	++mr;++mr;++mr;++mr;++mr;++mr;++mr;++mr;
	    	labelC = new Label(mr,row,"备注",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 19);
	    	
	    	
	    	
	    	++row;
	    	labelC = new Label(8,row,"资助标准(万元/月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(8, 10);
	    	
	    	labelC = new Label(9,row,"在常时间(月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(9, 10);
	    	
	    	
	    	labelC = new Label(10,row,"资助总额(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(10, 10);
	    	
	    	
	    	labelC = new Label(11,row,"住宿地(城内)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(11, 10);
	    	
	    	labelC = new Label(12,row,"补贴标准(万元/月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(12, 10);
	    	
	    	labelC = new Label(13,row,"在常时间(月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(13, 10);
	    	
	    	labelC = new Label(14,row,"资助总额(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(14, 10);
	    	

	    	labelC = new Label(15,row,"住宿地(城外)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(15, 10);
	    	
	    	labelC = new Label(16,row,"补贴标准(万元/月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(16, 10);
	    	
	    	labelC = new Label(17,row,"在常时间(月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(17, 10);
	    	
	    	labelC = new Label(18,row,"资助总额(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(18, 10);
	    	
	    	
	    	sql = "select c.DWMC,a.DWID,a.RYID,isnull(a.XM,' ') XM,a.SFZ,a.SEX_MC,a.BIRTHDAY," +
	    			"isnull(a.XL_MC,' ') XL_MC,isnull(a.ZC_MC,' ') ZC_MC,a.ZJZ,b.SBID,b.DWID as DC," +
	    			"convert(numeric(20,2),b.SHZZBZ) SHZZBZ, b.SHZCSJ, convert(numeric(20,2),b.SHZZZE) SHZZZE, b.ZFZFD," +
	    			"convert(numeric(20,2),b.ZFBTBZ) ZFBTBZ , b.ZFZCSJ, convert(numeric(20,2),b.ZFBTZE) ZFBTZE," +
			"b.ZFZFD2, convert(numeric(20,2),b.ZFBTBZ2) ZFBTBZ2, b.ZFZCSJ2, convert(numeric(20,2),b.ZFBTZE2) ZFBTZE2, convert(numeric(20,2),b.ZE) ZE,b.SM " +
			" from dw_ryxx_v a,jfhsb_shzf b,dw_info_v c" +
			" where a.ryid=b.ryid and a.dwid=b.dwid and a.dwid=c.dwid and (b.bz=3) and b.sbdm='"+dm[0].substring(0,4)+"0001'" +
			" and sbxx='001'" +//未变化人员
				" order by c.dwid ";
			List<Map<String,Object>> list = this.getSimpleJdbcTemplate().queryForList(sql);
	    	for(int i=0;i<list.size();i++){
	    		++row; mr = 0;
	    		labelC = new Label(mr,row,String.valueOf(i+1),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("XM")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SEX_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	if(String.valueOf(list.get(i).get("BIRTHDAY"))==null || String.valueOf(list.get(i).get("BIRTHDAY")).equals("") || String.valueOf(list.get(i).get("BIRTHDAY")).equals("null")){
		    		labelC = new Label(++mr,row,"",wcfFCC);
			    	ws.addCell(labelC);
		    	}else{
		    		labelC = new Label(++mr,row,String.valueOf(this.getAge(String.valueOf(list.get(i).get("BIRTHDAY")))),wcfFCC);
			    	ws.addCell(labelC);
		    	}
		    	
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("DWMC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("XL_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("ZC_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("ZJZ")).equals("2")?"兼职":"专职",wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SHZZBZ")),wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SHZCSJ")),wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SHZZZE")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	String  ZFZFD = "";
		    	String  ZFBTBZ = "";
		    	String  ZFZCSJ = "";
		    	String  ZFBTZE = "";
		    	if(String.valueOf(list.get(i).get("ZFZFD")).equals("1")){
		    		ZFZFD = "是";
		    		ZFBTBZ = String.valueOf(list.get(i).get("ZFBTBZ"));
		    		ZFZCSJ = String.valueOf(list.get(i).get("ZFZCSJ"));
		    		ZFBTZE = String.valueOf(list.get(i).get("ZFBTZE"));
		    	}else{
		    		ZFZFD = "否";
		    		ZFBTBZ = "0";
		    		ZFZCSJ = "0";
		    		ZFBTZE = "0";	
		    	}
		    	
		    	labelC = new Label(++mr,row,ZFZFD,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,ZFBTBZ,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,ZFZCSJ,wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,ZFBTZE,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	if(String.valueOf(list.get(i).get("ZFZFD2")).equals("1")){
		    		ZFZFD = "是";
		    		ZFBTBZ = String.valueOf(list.get(i).get("ZFBTBZ2"));
		    		ZFZCSJ = String.valueOf(list.get(i).get("ZFZCSJ2"));
		    		ZFBTZE = String.valueOf(list.get(i).get("ZFBTZE2"));
		    	}else{
		    		ZFZFD = "否";
		    		ZFBTBZ = "0";
		    		ZFZCSJ = "0";
		    		ZFBTZE = "0";	
		    	}
		    	labelC = new Label(++mr,row,ZFZFD,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,ZFBTBZ,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,ZFZCSJ,wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,ZFBTZE,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SM")),wcfFCC);
		    	ws.addCell(labelC);
	    	}
	    	
	    	/***
	    	 * 获取新增人员
	    	 */
	    	++row;++row;++row;
	    	mr = 0;//列
		    rr = row;//行
		    ws.mergeCells(mr, rr, 19, rr);//标题
		    ++rr;
		    ws.mergeCells(mr, rr, 19, rr);//副标题
		    
		    ++rr;
		    ws.mergeCells(mr, rr, mr, rr+1); //字段名
		    ws.mergeCells(mr+1, rr, mr+1, rr+1); //字段名
		    ws.mergeCells(mr+2, rr, mr+2, rr+1); //字段名
		    ws.mergeCells(mr+3, rr, mr+3, rr+1); //字段名
		    ws.mergeCells(mr+4, rr, mr+4, rr+1); //字段名
		    ws.mergeCells(mr+5, rr, mr+5, rr+1); //字段名
		    ws.mergeCells(mr+6, rr, mr+6, rr+1); //字段名
		    ws.mergeCells(mr+7, rr, mr+7, rr+1); //字段名
		   // ws.mergeCells(mr+8, 1, mr+8, 2); //字段名
		    
		    
		    ws.mergeCells(mr+8, rr, mr+10, rr); //字段名
		    ws.mergeCells(mr+11, rr, mr+18, rr); //字段名
		    ws.mergeCells(mr+19,rr, mr+19, rr+1); //字段名
	    	
		    mr = 0;
	    	labelC = new Label(mr,row,"常州科教城“金凤凰高层次人才引进计划”生活资助和住房补贴申报表",wcfFCT);
	    	ws.addCell(labelC);
	    	
	    	++row;
	    	labelC = new Label(mr,row,"新    增    人    员",wcfFCT);
	    	ws.addCell(labelC);
	    	
	    	++row;
	    	labelC = new Label(mr,row,"序号",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 5);
	    	
	    	labelC = new Label(++mr,row,"申请人",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"性别",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 8);
	    	
	    	labelC = new Label(++mr,row,"年龄",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 8);
	    	
	    	labelC = new Label(++mr,row,"所在单位",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 28);
	    	
	    	labelC = new Label(++mr,row,"学历/学位",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"职称/职务",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	//labelC = new Label(++mr,row,"专业特长",wcfFC);
	    	//ws.addCell(labelC);
	    	//ws.setColumnView(mr, 18);
	    	
	    	labelC = new Label(++mr,row,"专/兼职",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"生活资助经费(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	
	    	++mr;++mr;++mr;
	    	labelC = new Label(mr,row,"住房补贴费(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	
	    	++mr;++mr;++mr;++mr;++mr;++mr;++mr;++mr;
	    	labelC = new Label(mr,row,"备注",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 19);
	    	
	    	
	    	
	    	++row;
	    	labelC = new Label(8,row,"资助标准(万元/月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(8, 10);
	    	
	    	labelC = new Label(9,row,"在常时间(月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(9, 10);
	    	
	    	
	    	labelC = new Label(10,row,"资助总额(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(10, 10);
	    	

	    	labelC = new Label(11,row,"住宿地(城内)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(11, 10);
	    	
	    	labelC = new Label(12,row,"补贴标准(万元/月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(12, 10);
	    	
	    	labelC = new Label(13,row,"在常时间(月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(13, 10);
	    	
	    	labelC = new Label(14,row,"资助总额(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(14, 10);
	    	

	    	labelC = new Label(15,row,"住宿地(城外)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(15, 10);
	    	
	    	labelC = new Label(16,row,"补贴标准(万元/月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(16, 10);
	    	
	    	labelC = new Label(17,row,"在常时间(月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(17, 10);
	    	
	    	labelC = new Label(18,row,"资助总额(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(18, 10);
	    	
	    	//String[] dm = sbdm.split("@");
	    	sql = "select c.DWMC,a.DWID,a.RYID,isnull(a.XM,' ') XM,a.SFZ,a.SEX_MC,a.BIRTHDAY,isnull(a.XL_MC,' ') XL_MC," +
	    			"isnull(a.ZC_MC,' ') ZC_MC,a.ZJZ,b.SBID,b.DWID as DC,convert(numeric(20,2),b.SHZZBZ) SHZZBZ, " +
	    			"b.SHZCSJ, convert(numeric(20,2),b.SHZZZE) SHZZZE, b.ZFZFD, convert(numeric(20,2),b.ZFBTBZ) ZFBTBZ, b.ZFZCSJ," +
	    			"convert(numeric(20,2),b.ZFBTZE ) ZFBTZE," +
			"b.ZFZFD2, convert(numeric(20,2),b.ZFBTBZ2) ZFBTBZ2, b.ZFZCSJ2, convert(numeric(20,2),b.ZFBTZE2) ZFBTZE2, convert(numeric(20,2),b.ZE) ZE,b.SM " +
			" from dw_ryxx_v a,jfhsb_shzf b,dw_info_v c" +
			" where a.ryid=b.ryid and a.dwid=b.dwid and a.dwid=c.dwid and (b.bz=3 ) and b.sbdm='"+dm[0].substring(0,4)+"0001'" +
			" and sbxx='002'" +//新增人员
				"  order by c.dwid ";
			list = this.getSimpleJdbcTemplate().queryForList(sql);
	    	for(int i=0;i<list.size();i++){
	    		++row; mr = 0;
	    		labelC = new Label(mr,row,String.valueOf(i+1),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("XM")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SEX_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	if(String.valueOf(list.get(i).get("BIRTHDAY"))==null || String.valueOf(list.get(i).get("BIRTHDAY")).equals("") || String.valueOf(list.get(i).get("BIRTHDAY")).equals("null")){
		    		labelC = new Label(++mr,row,"",wcfFCC);
			    	ws.addCell(labelC);
		    	}else{
		    		labelC = new Label(++mr,row,String.valueOf(this.getAge(String.valueOf(list.get(i).get("BIRTHDAY")))),wcfFCC);
			    	ws.addCell(labelC);
		    	}
		    	
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("DWMC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("XL_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("ZC_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("ZJZ")).equals("2")?"兼职":"专职",wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SHZZBZ")),wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SHZCSJ")),wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SHZZZE")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	String  ZFZFD = "";
		    	String  ZFBTBZ = "";
		    	String  ZFZCSJ = "";
		    	String  ZFBTZE = "";
		    	if(String.valueOf(list.get(i).get("ZFZFD")).equals("1")){
		    		ZFZFD = "是";
		    		ZFBTBZ = String.valueOf(list.get(i).get("ZFBTBZ"));
		    		ZFZCSJ = String.valueOf(list.get(i).get("ZFZCSJ"));
		    		ZFBTZE = String.valueOf(list.get(i).get("ZFBTZE"));
		    	}else{
		    		ZFZFD = "否";
		    		ZFBTBZ = "0";
		    		ZFZCSJ = "0";
		    		ZFBTZE = "0";	
		    	}
		    	

		    	labelC = new Label(++mr,row,ZFZFD,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,ZFBTBZ,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,ZFZCSJ,wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,ZFBTZE,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	if(String.valueOf(list.get(i).get("ZFZFD2")).equals("1")){
		    		ZFZFD = "是";
		    		ZFBTBZ = String.valueOf(list.get(i).get("ZFBTBZ2"));
		    		ZFZCSJ = String.valueOf(list.get(i).get("ZFZCSJ2"));
		    		ZFBTZE = String.valueOf(list.get(i).get("ZFBTZE2"));
		    	}else{
		    		ZFZFD = "否";
		    		ZFBTBZ = "0";
		    		ZFZCSJ = "0";
		    		ZFBTZE = "0";	
		    	}
		    	labelC = new Label(++mr,row,ZFZFD,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,ZFBTBZ,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,ZFZCSJ,wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,ZFBTZE,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SM")),wcfFCC);
		    	ws.addCell(labelC);
	    	}
	    	
	    	
	    	/***
	    	 * 领导及学术带头人
	    	 */
	    	++row;++row;++row;
	    	mr = 0;//列
		    rr = row;//行
		    ws.mergeCells(mr, rr, 11, rr);//标题
		   // ++rr;
		   // ws.mergeCells(mr, rr, 15, rr);//副标题
		    
		    ++rr;
		    ws.mergeCells(mr, rr, mr, rr+1); //字段名
		    ws.mergeCells(mr+1, rr, mr+1, rr+1); //字段名
		    ws.mergeCells(mr+2, rr, mr+2, rr+1); //字段名
		    ws.mergeCells(mr+3, rr, mr+3, rr+1); //字段名
		    ws.mergeCells(mr+4, rr, mr+4, rr+1); //字段名
		    ws.mergeCells(mr+5, rr, mr+5, rr+1); //字段名
		    ws.mergeCells(mr+6, rr, mr+6, rr+1); //字段名
		    ws.mergeCells(mr+7, rr, mr+7, rr+1); //字段名
		   // ws.mergeCells(mr+8, 1, mr+8, 2); //字段名
		    
		    
		    ws.mergeCells(mr+8, rr, mr+10, rr); //字段名
		    //ws.mergeCells(mr+11, rr, mr+14, rr); //字段名
		    ws.mergeCells(mr+11,rr, mr+11, rr+1); //字段名
	    	
		    mr = 0;
	    	labelC = new Label(mr,row,"常州科教城“金凤凰高层次人才引进计划”经费资助（奖励）——领导及学术带头人",wcfFCT);
	    	ws.addCell(labelC);
	    	
	    	//++row;
	    	//labelC = new Label(mr,row,"新    增    人    员",wcfFCT);
	    	//ws.addCell(labelC);
	    	
	    	++row;
	    	labelC = new Label(mr,row,"序号",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 5);
	    	
	    	labelC = new Label(++mr,row,"申请人",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"性别",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 8);
	    	
	    	labelC = new Label(++mr,row,"年龄",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 8);
	    	
	    	labelC = new Label(++mr,row,"所在单位",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 28);
	    	
	    	labelC = new Label(++mr,row,"学历/学位",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"职称/职务",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	//labelC = new Label(++mr,row,"专业特长",wcfFC);
	    	//ws.addCell(labelC);
	    	//ws.setColumnView(mr, 18);
	    	
	    	labelC = new Label(++mr,row,"专/兼职",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"生活资助经费(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	
	    	++mr;++mr;++mr;
	    	
	    	labelC = new Label(mr,row,"备注",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 15);
	    	
	    	
	    	
	    	++row;
	    	labelC = new Label(8,row,"资助标准(万元/月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(8, 10);
	    	
	    	labelC = new Label(9,row,"在常时间(月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(9, 10);
	    	
	    	
	    	labelC = new Label(10,row,"资助总额(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(10, 10);
	    	
	    	
	    	
	    	//String[] dm = sbdm.split("@");
	    	sql = "select c.DWMC,a.DWID,a.RYID,isnull(a.XM,' ') XM,a.SFZ,a.SEX_MC,a.BIRTHDAY,isnull(a.XL_MC,' ') XL_MC,isnull(a.ZC_MC,' ') ZC_MC," +
	    	"a.ZJZ,b.SBID,b.DWID as DC,convert(numeric(20,2),b.SHZZBZ) SHZZBZ, b.SHZCSJ, convert(numeric(20,2),b.SHZZZE) SHZZZE, " +
	    	"b.ZFZFD, convert(numeric(20,2),b.ZFBTBZ) ZFBTBZ, b.ZFZCSJ, convert(numeric(20,2),b.ZFBTZE) ZFBTZE," +
			"b.ZFZFD2, convert(numeric(20,2),b.ZFBTBZ2) ZFBTBZ2, b.ZFZCSJ2, convert(numeric(20,2),b.ZFBTZE2) ZFBTZE2, convert(numeric(20,2),b.ZE) ZE,b.SM " +
			" from dw_ryxx_v a,jfhsb_shzf b,dw_info_v c" +
			" where a.ryid=b.ryid and a.dwid=b.dwid and a.dwid=c.dwid and (b.bz=3 ) and b.sbdm='"+dm[0].substring(0,4)+"0001'" +
			" and sbxx='004'" +//领导及学术带头人
				"  order by c.dwid ";
			list = this.getSimpleJdbcTemplate().queryForList(sql);
	    	for(int i=0;i<list.size();i++){
	    		++row; mr = 0;
	    		labelC = new Label(mr,row,String.valueOf(i+1),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("XM")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SEX_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	if(String.valueOf(list.get(i).get("BIRTHDAY"))==null || String.valueOf(list.get(i).get("BIRTHDAY")).equals("") || String.valueOf(list.get(i).get("BIRTHDAY")).equals("null")){
		    		labelC = new Label(++mr,row,"",wcfFCC);
			    	ws.addCell(labelC);
		    	}else{
		    		labelC = new Label(++mr,row,String.valueOf(this.getAge(String.valueOf(list.get(i).get("BIRTHDAY")))),wcfFCC);
			    	ws.addCell(labelC);
		    	}
		    	
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("DWMC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("XL_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("ZC_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("ZJZ")).equals("2")?"兼职":"专职",wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SHZZBZ")),wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SHZCSJ")),wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SHZZZE")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	/***
		    	String  ZFZFD = "";
		    	String  ZFBTBZ = "";
		    	String  ZFZCSJ = "";
		    	String  ZFBTZE = "";
		    	if(String.valueOf(list.get(i).get("ZFZFD")).equals("1")){
		    		ZFZFD = "城内";
		    		ZFBTBZ = String.valueOf(list.get(i).get("ZFBTBZ"));
		    		ZFZCSJ = String.valueOf(list.get(i).get("ZFZCSJ"));
		    		ZFBTZE = String.valueOf(list.get(i).get("ZFBTZE"));
		    	}else{
		    		ZFZFD = "无";
		    		ZFBTBZ = "0";
		    		ZFZCSJ = "0";
		    		ZFBTZE = "0";	
		    	}
		    	
		    	if(String.valueOf(list.get(i).get("ZFZFD2")).equals("1")){
		    		ZFZFD += "  /  城外";
		    		ZFBTBZ += "  /  "+String.valueOf(list.get(i).get("ZFBTBZ2"));
		    		ZFZCSJ += "  /  "+String.valueOf(list.get(i).get("ZFZCSJ2"));
		    		ZFBTZE += "  /  "+String.valueOf(list.get(i).get("ZFBTZE2"));
		    	}else{
		    		if(ZFZFD.equals("城内")){
		    			ZFZFD = ZFZFD+" / 无";
			    		ZFBTBZ += " / 0";
			    		ZFZCSJ += " / 0";
			    		ZFBTZE += " / 0";					    			
		    		}
		    	}
		    	labelC = new Label(++mr,row,ZFZFD,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,ZFBTBZ,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,ZFZCSJ,wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,ZFBTZE,wcfFCC);
		    	ws.addCell(labelC);
		    	**/
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SM")),wcfFCC);
		    	ws.addCell(labelC);
	    	}
	    	
	    	
	    	
	    	
	    	
	    	/***
	    	 * 总表
	    	 */
	    	++row;++row;++row;
	    	mr = 0;//列
		    rr = row;//行
		    ws.mergeCells(mr, rr, 19, rr);//标题
		   // ++rr;
		  //  ws.mergeCells(mr, rr, 15, rr);//副标题
		    
		    ++rr;
		    ws.mergeCells(mr, rr, mr, rr+1); //字段名
		    ws.mergeCells(mr+1, rr, mr+1, rr+1); //字段名
		    ws.mergeCells(mr+2, rr, mr+2, rr+1); //字段名
		    ws.mergeCells(mr+3, rr, mr+3, rr+1); //字段名
		    ws.mergeCells(mr+4, rr, mr+4, rr+1); //字段名
		    ws.mergeCells(mr+5, rr, mr+5, rr+1); //字段名
		    ws.mergeCells(mr+6, rr, mr+6, rr+1); //字段名
		    ws.mergeCells(mr+7, rr, mr+7, rr+1); //字段名
		   // ws.mergeCells(mr+8, 1, mr+8, 2); //字段名
		    
		    
		    ws.mergeCells(mr+8, rr, mr+10, rr); //字段名
		    ws.mergeCells(mr+11, rr, mr+18, rr); //字段名
		    ws.mergeCells(mr+19,rr, mr+19, rr+1); //字段名
	    	
		    mr = 0;
	    	labelC = new Label(mr,row,"常州科教城“金凤凰高层次人才引进计划”生活资助、住房补贴和安家费汇总表",wcfFCT);
	    	ws.addCell(labelC);
	    	
	    	
	    	++row;
	    	labelC = new Label(mr,row,"序号",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 5);
	    	
	    	labelC = new Label(++mr,row,"姓名",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"性别",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 8);
	    	
	    	labelC = new Label(++mr,row,"年龄",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 8);
	    	
	    	labelC = new Label(++mr,row,"所在单位",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 28);
	    	
	    	labelC = new Label(++mr,row,"学历/学位",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"职称/职务",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	//labelC = new Label(++mr,row,"专业特长",wcfFC);
	    	//ws.addCell(labelC);
	    	//ws.setColumnView(mr, 18);
	    	
	    	labelC = new Label(++mr,row,"专/兼职",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"生活资助经费(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	
	    	++mr;++mr;++mr;
	    	labelC = new Label(mr,row,"住房补贴费(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	
	    	++mr;++mr;++mr;++mr;++mr;++mr;++mr;++mr;
	    	labelC = new Label(mr,row,"安家费(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 15);
	    	
	    	
	    	
	    	++row;
	    	labelC = new Label(8,row,"资助标准(万元/月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(8, 10);
	    	
	    	labelC = new Label(9,row,"在常时间(月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(9, 10);
	    	
	    	
	    	labelC = new Label(10,row,"资助总额(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(10, 10);
	    	

	    	labelC = new Label(11,row,"住宿地(城内)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(11, 10);
	    	
	    	labelC = new Label(12,row,"补贴标准(万元/月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(12, 10);
	    	
	    	labelC = new Label(13,row,"在常时间(月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(13, 10);
	    	
	    	labelC = new Label(14,row,"资助总额(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(14, 10);
	    	

	    	labelC = new Label(15,row,"住宿地(城外)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(15, 10);
	    	
	    	labelC = new Label(16,row,"补贴标准(万元/月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(16, 10);
	    	
	    	labelC = new Label(17,row,"在常时间(月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(17, 10);
	    	
	    	labelC = new Label(18,row,"资助总额(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(18, 10);

			sql = "select c.DWMC,a.RYID,isnull(a.XM,' ') XM,a.SFZ,a.SEX_MC,a.BIRTHDAY,isnull(a.XL_MC,' ') XL_MC,isnull(a.ZC_MC,' ') ZC_MC,a.ZJZ" +
					" from dw_ryxx_v a,dw_info_v c " +
					" where a.dwid=c.dwid and (a.ryid in ( select ryid from jfhsb_ajf where sbdm='"+dm[0].substring(0,4)+"0002' and bz=3 ) " +
					" or a.ryid in ( select ryid from jfhsb_shzf where sbdm='"+dm[0].substring(0,4)+"0001'  and bz=3)) " +
						"  order by c.dwid ";
			List<Map<String,Object>> listry = this.getSimpleJdbcTemplate().queryForList(sql);
			
			
	    	//String[] dm = sbdm.split("@");
	    	sql = "select c.DWMC,a.DWID,a.RYID,isnull(a.XM,' ') XM,a.SFZ,a.SEX_MC,a.BIRTHDAY,isnull(a.XL_MC,' ') XL_MC,isnull(a.ZC_MC,' ') ZC_MC," +
	    	"a.ZJZ,b.SBID,b.DWID as DC,convert(numeric(20,2),b.SHZZBZ) SHZZBZ, b.SHZCSJ, convert(numeric(20,2),b.SHZZZE) SHZZZE, b.ZFZFD, " +
	    	"convert(numeric(20,2),b.ZFBTBZ) ZFBTBZ, b.ZFZCSJ, convert(numeric(20,2),b.ZFBTZE) ZFBTZE," +
			"b.ZFZFD2, convert(numeric(20,2),b.ZFBTBZ2) ZFBTBZ2, b.ZFZCSJ2, convert(numeric(20,2),b.ZFBTZE2) ZFBTZE2, convert(numeric(20,2),b.ZE) ZE " +
			" from dw_ryxx_v a,jfhsb_shzf b,dw_info_v c" +
			" where a.ryid=b.ryid and a.dwid=b.dwid and a.dwid=c.dwid and (b.bz=3) and b.sbdm='"+dm[0].substring(0,4)+"0001'" +
			"  order by c.dwid " ;
	    	List<Map<String,Object>> listzz = this.getSimpleJdbcTemplate().queryForList(sql);
	    	
	    	
	    	//安家费
			sql = "select a.RYID,a.XM,a.SFZ,a.SEX_MC,isnull(a.XL_MC,' ') XL_MC,a.ZC_MC,a.ZJZ, convert(numeric(18,2),(isnull(ZZBZ,0))) ZZBZ " +
				" from dw_ryxx_v a , jfhsb_ajf b,dw_info_v c " +
				" where a.ryid=b.ryid and a.dwid=b.dwid and a.dwid=c.dwid and (b.bz=3) and b.sbdm='"+dm[0].substring(0,4)+"0002'" +
				"  order by c.dwid " ;
		    List<Map<String,Object>> listajf =  this.getSimpleJdbcTemplate().queryForList(sql);
		    
	    	for(int i=0;i<listry.size();i++){
	    		++row; mr = 0;
	    		labelC = new Label(mr,row,String.valueOf(i+1),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listry.get(i).get("XM")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listry.get(i).get("SEX_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	if(String.valueOf(listry.get(i).get("BIRTHDAY"))==null || String.valueOf(listry.get(i).get("BIRTHDAY")).equals("") || String.valueOf(listry.get(i).get("BIRTHDAY")).equals("null")){
		    		labelC = new Label(++mr,row,"",wcfFCC);
			    	ws.addCell(labelC);
		    	}else{
		    		labelC = new Label(++mr,row,String.valueOf(this.getAge(String.valueOf(listry.get(i).get("BIRTHDAY")))),wcfFCC);
			    	ws.addCell(labelC);
		    	}
		    	
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listry.get(i).get("DWMC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listry.get(i).get("XL_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listry.get(i).get("ZC_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listry.get(i).get("ZJZ")).equals("2")?"兼职":"专职",wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	
		    	for(int j=0;j<listzz.size();j++){
		    		if(String.valueOf(listry.get(i).get("RYID")).equals(String.valueOf(listzz.get(j).get("RYID")))){

				    	labelC = new Label(++mr,row,String.valueOf(listzz.get(j).get("SHZZBZ")),wcfFCC);
				    	ws.addCell(labelC);

				    	labelC = new Label(++mr,row,String.valueOf(listzz.get(j).get("SHZCSJ")),wcfFCC);
				    	ws.addCell(labelC);

				    	labelC = new Label(++mr,row,String.valueOf(listzz.get(j).get("SHZZZE")),wcfFCC);
				    	ws.addCell(labelC);
				    	
				    	String  ZFZFD = "";
				    	String  ZFBTBZ = "";
				    	String  ZFZCSJ = "";
				    	String  ZFBTZE = "";
				    	if(String.valueOf(listzz.get(j).get("ZFZFD")).equals("1")){
				    		ZFZFD = "是";
				    		ZFBTBZ = String.valueOf(listzz.get(j).get("ZFBTBZ"));
				    		ZFZCSJ = String.valueOf(listzz.get(j).get("ZFZCSJ"));
				    		ZFBTZE = String.valueOf(listzz.get(j).get("ZFBTZE"));
				    	}else{
				    		ZFZFD = "否";
				    		ZFBTBZ = "0";
				    		ZFZCSJ = "0";
				    		ZFBTZE = "0";				    		
				    	}
				    	
				    	labelC = new Label(++mr,row,ZFZFD,wcfFCC);
				    	ws.addCell(labelC);
				    	
				    	labelC = new Label(++mr,row,ZFBTBZ,wcfFCC);
				    	ws.addCell(labelC);
				    	
				    	labelC = new Label(++mr,row,ZFZCSJ,wcfFCC);
				    	ws.addCell(labelC);

				    	labelC = new Label(++mr,row,ZFBTZE,wcfFCC);
				    	ws.addCell(labelC);
				    	
				    	if(String.valueOf(listzz.get(j).get("ZFZFD2")).equals("1")){
				    		ZFZFD = "是";
				    		ZFBTBZ = String.valueOf(listzz.get(j).get("ZFBTBZ2"));
				    		ZFZCSJ = String.valueOf(listzz.get(j).get("ZFZCSJ2"));
				    		ZFBTZE = String.valueOf(listzz.get(j).get("ZFBTZE2"));
				    	}else{
				    		ZFZFD = "否";
				    		ZFBTBZ = "0";
				    		ZFZCSJ = "0";
				    		ZFBTZE = "0";	
				    	}
				    	labelC = new Label(++mr,row,ZFZFD,wcfFCC);
				    	ws.addCell(labelC);
				    	
				    	labelC = new Label(++mr,row,ZFBTBZ,wcfFCC);
				    	ws.addCell(labelC);
				    	
				    	labelC = new Label(++mr,row,ZFZCSJ,wcfFCC);
				    	ws.addCell(labelC);

				    	labelC = new Label(++mr,row,ZFBTZE,wcfFCC);
				    	ws.addCell(labelC);
				    	
//				    	labelC = new Label(12,row,String.valueOf(listzz.get(j).get("ZZE")),wcfFCC);
//				    	ws.addCell(labelC);
				    	
				    	boolean hasAJF = false;
				    	for(int jj=0;jj<listajf.size();jj++){
				    		if(String.valueOf(listry.get(i).get("RYID")).equals(String.valueOf(listajf.get(jj).get("RYID")))){
				    			hasAJF = true;
						    	labelC = new Label(++mr,row,String.valueOf(listajf.get(jj).get("ZZBZ")),wcfFCC);
						    	ws.addCell(labelC);
						    	break;
						    	/**
						    	Float hj = Float.valueOf( String.valueOf( listajf.get(jj).get("ZZBZ") ) ) 
						    				+ Float.valueOf( String.valueOf( listzz.get(j).get("ZZE") ) ) ;						    	
						    	
						    	labelC = new Label(++mr,row,String.valueOf( hj ),wcfFCC);
						    	ws.addCell(labelC);			
						    	**/				    	
						    }
				    	}		    	
				    	if( !hasAJF ){
					    	labelC = new Label(++mr,row,"",wcfFCC);
					    	ws.addCell(labelC);
					    				    	
				    	}				    	
				    	
				    	
		    		}
		    	}
		    	
		    	
	    	}
	    	
	    	
	    	wwb.write();
	    	wwb.close();
		}catch(Exception e){
			throw  new BusException("导出出现异常!");
		}
		
	}
	
/**

	public void doExportjfh(OutputStream os,String sbdm ){
		WritableWorkbook wwb = null;
		
		String sql = "";
		try{
			//创建 Excel 
			wwb = Workbook.createWorkbook(os);
			
			
			String[] dm = sbdm.split("@");
			
			

			jxl.write.Label labelC = null;
			Number labelNF = null;
			
			jxl.write.WritableFont wfcT = new jxl.write.WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
    	    jxl.write.WritableCellFormat wcfFCT = new jxl.write.WritableCellFormat(wfcT);
    	    wcfFCT.setAlignment(Alignment.CENTRE);
    	    wcfFCT.setBorder(Border.ALL, BorderLineStyle.THIN);
    	    wcfFCT.setVerticalAlignment(VerticalAlignment.CENTRE);//垂直居中

    	    jxl.write.WritableFont wfcT_ = new jxl.write.WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
    	    jxl.write.WritableCellFormat wcfFC_ = new jxl.write.WritableCellFormat(wfcT_);
    	    wcfFC_.setBorder(Border.ALL, BorderLineStyle.THIN);
    	    wcfFC_.setVerticalAlignment(VerticalAlignment.CENTRE);//垂直居中
    	    
    	    
			jxl.write.WritableFont wfc = new jxl.write.WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
    	    jxl.write.WritableCellFormat wcfFC = new jxl.write.WritableCellFormat(wfc);
    	    wcfFC.setAlignment(Alignment.CENTRE);
    	    wcfFC.setWrap(true);//自动换行
    	    wcfFC.setBorder(Border.ALL, BorderLineStyle.THIN);
    	    wcfFC.setVerticalAlignment(VerticalAlignment.CENTRE);//垂直居中
    	    
    	    
    	    jxl.write.WritableFont wfcc = new jxl.write.WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false,UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
    	    jxl.write.WritableCellFormat wcfFCC = new jxl.write.WritableCellFormat(wfcc);
    	    wcfFCC.setWrap(true);
    	    wcfFCC.setBorder(Border.ALL, BorderLineStyle.THIN);
    	    wcfFCC.setVerticalAlignment(VerticalAlignment.CENTRE);//垂直居中
    	    
    	    //数值
		    jxl.write.NumberFormat nf = new jxl.write.NumberFormat("0.00");
		    jxl.write.WritableCellFormat wcfN = new jxl.write.WritableCellFormat(nf);
		    
		    int row = 0;
		    int mr = 0;//列
		    int rr = 0;//行
		    
		    WritableSheet ws1 = wwb.createSheet("新增安家费",0);
		    mr =0;rr =0;
		    ws1.mergeCells(mr, rr, 15, rr);//标题
		    //++rr;
		    //ws.mergeCells(mr, rr, 15, rr);//副标题
		    
		    ++rr;
		    ws1.mergeCells(mr, rr, mr, rr+1); //字段名
		    ws1.mergeCells(mr+1, rr, mr+1, rr+1); //字段名
		    ws1.mergeCells(mr+2, rr, mr+2, rr+1); //字段名
		    ws1.mergeCells(mr+3, rr, mr+3, rr+1); //字段名
		    ws1.mergeCells(mr+4, rr, mr+4, rr+1); //字段名
		    ws1.mergeCells(mr+5, rr, mr+5, rr+1); //字段名
		    ws1.mergeCells(mr+6, rr, mr+6, rr+1); //字段名
		    ws1.mergeCells(mr+7, rr, mr+7, rr+1); //字段名
		    ws1.mergeCells(mr+8, rr, mr+8, rr+1); //字段名
		    ws1.mergeCells(mr+9, rr, mr+9, rr+1); //字段名
		    ws1.mergeCells(mr+10, rr, mr+10, rr+1); //字段名
		    ws1.mergeCells(mr+11, rr, mr+11, rr+1); //字段名
		    ws1.mergeCells(mr+12, rr, mr+12, rr+1); //字段名
		    ws1.mergeCells(mr+13, rr, mr+13, rr+1); //字段名
		    ws1.mergeCells(mr+14, rr, mr+14, rr+1); //字段名
		    ws1.mergeCells(mr+15, rr, mr+15, rr+1); //字段名
		    
		    mr = 0;
		    row = 0;
		    labelC = new Label(mr,row,"常州科教城“金凤凰高层次人才引进计划”安家费申报表",wcfFCT);
	    	ws1.addCell(labelC);
	    	
	    	//++row;
	    	//labelC = new Label(mr,row,"未  变  化  人  员",wcfFCT);
	    	//ws.addCell(labelC);
	    	
	    	++row;
	    	labelC = new Label(mr,row,"序号",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 5);
	    	
	    	labelC = new Label(++mr,row,"申请人",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"性别",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 8);
	    	
	    	labelC = new Label(++mr,row,"年龄",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 8);
	    	
	    	labelC = new Label(++mr,row,"所在单位",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 28);
	    	
	    	labelC = new Label(++mr,row,"学历/学位",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"职称/职务",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 10);
	    	
	    	//labelC = new Label(++mr,row,"专业特长",wcfFC);
	    	//ws.addCell(labelC);
	    	//ws.setColumnView(mr, 18);
	    	
	    	labelC = new Label(++mr,row,"专/兼职",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"购房时间",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"资助标准总额（万元）",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"购房地",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"申请领取金额（万元）",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 12);
	    	
	    	labelC = new Label(++mr,row,"房屋面积（单位：平米）",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 15);
	    	
	    	labelC = new Label(++mr,row,"总额（单位：万）",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"房屋交付时间",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"备注",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 10);
	    	
	    	//安家费
			sql = "select c.DWMC,a.RYID,a.XM,a.SFZ,a.SEX_MC,isnull(a.XL_MC,' ') XL_MC,convert(varchar(12),a.BIRTHDAY,23) BIRTHDAY," +
					"a.ZC_MC,a.ZJZ," +
					" GFDD, convert(numeric(18,2),(isnull(FWMJ,0))) FWMJ," +
					" convert(numeric(18,2),(isnull(FWZE,0))) FWZE, convert(varchar(12),JFSJ,23) JFSJ," +
					" convert(numeric(18,2),(isnull(DYNBT,0))) DYNBT, convert(numeric(18,2),(isnull(ZZBZ,0))) ZZBZ, b.SM " +
				" from dw_ryxx_v a , jfhsb_ajf b,dw_info_v c " +
				" where a.ryid=b.ryid and a.dwid=b.dwid and a.dwid=c.dwid and (b.bz=3) and b.sbdm='"+dm[0].substring(0,4)+"0002'" +
				" " ;
		    List<Map<String,Object>> listajf1 =  this.getSimpleJdbcTemplate().queryForList(sql);
		    ++row;
		    for(int i=0;i<listajf1.size();i++){
	    		++row; mr = 0;
	    		labelC = new Label(mr,row,String.valueOf(i+1),wcfFCC);
		    	ws1.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("XM")),wcfFCC);
		    	ws1.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("SEX_MC")),wcfFCC);
		    	ws1.addCell(labelC);
		    	
		    	if(String.valueOf(listajf1.get(i).get("BIRTHDAY"))==null || String.valueOf(listajf1.get(i).get("BIRTHDAY")).equals("") || String.valueOf(listajf1.get(i).get("BIRTHDAY")).equals("null")){
		    		labelC = new Label(++mr,row,"",wcfFCC);
			    	ws1.addCell(labelC);
		    	}else{
		    		labelC = new Label(++mr,row,String.valueOf(this.getAge(String.valueOf(listajf1.get(i).get("BIRTHDAY")))),wcfFCC);
			    	ws1.addCell(labelC);
		    	}
		    	
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("DWMC")),wcfFCC);
		    	ws1.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("XL_MC")),wcfFCC);
		    	ws1.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("ZC_MC")),wcfFCC);
		    	ws1.addCell(labelC);
		    	
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("ZJZ")).equals("2")?"兼职":"专职",wcfFCC);
		    	ws1.addCell(labelC);
		    	
		    	
		    	
		    //	购房时间	资助标准总额（万元）	购房地	申请领取金额（万元）	房屋面积（单位：平米）	总额（单位：万）	房屋交付时间	备注
				

		    	
		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("GFSJ")),wcfFCC);
		    	ws1.addCell(labelC);

		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("ZZBZ")),wcfFCC);
		    	ws1.addCell(labelC);

		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("GFDD")),wcfFCC);
		    	ws1.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("DYNBT")),wcfFCC);
		    	ws1.addCell(labelC);

		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("FWMJ")),wcfFCC);
		    	ws1.addCell(labelC);

		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("FWZE")),wcfFCC);
		    	ws1.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("JFSJ")),wcfFCC);
		    	ws1.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("SM")),wcfFCC);
		    	ws1.addCell(labelC);
	    	}
		    

			WritableSheet ws = wwb.createSheet("本季度生活和住房未变化和新增情况",1);
		    /////////////////////////////////////////第二个 SHEET////////////////////////////////////////////////////////////////////
		    mr =0;rr =0;
		    ws.mergeCells(mr, rr, 15, rr);//标题
		    ++rr;
		    ws.mergeCells(mr, rr, 15, rr);//副标题
		    
		    ++rr;
		    ws.mergeCells(mr, rr, mr, rr+1); //字段名
		    ws.mergeCells(mr+1, rr, mr+1, rr+1); //字段名
		    ws.mergeCells(mr+2, rr, mr+2, rr+1); //字段名
		    ws.mergeCells(mr+3, rr, mr+3, rr+1); //字段名
		    ws.mergeCells(mr+4, rr, mr+4, rr+1); //字段名
		    ws.mergeCells(mr+5, rr, mr+5, rr+1); //字段名
		    ws.mergeCells(mr+6, rr, mr+6, rr+1); //字段名
		    ws.mergeCells(mr+7, rr, mr+7, rr+1); //字段名
		   // ws.mergeCells(mr+8, 1, mr+8, 2); //字段名
		    
		    
		    ws.mergeCells(mr+8, rr, mr+10, rr); //字段名
		    ws.mergeCells(mr+11, rr, mr+14, rr); //字段名
		    ws.mergeCells(mr+15,rr, mr+15, rr+1); //字段名
		    
		    row = 0;
		    /****
		     * 获取未变化人员
		     
		    mr = 0;
		    labelC = new Label(mr,row,"常州科教城“金凤凰高层次人才引进计划”生活资助和住房补贴申报表",wcfFCT);
	    	ws.addCell(labelC);
	    	
	    	++row;
	    	labelC = new Label(mr,row,"未  变  化  人  员",wcfFCT);
	    	ws.addCell(labelC);
	    	
	    	++row;
	    	labelC = new Label(mr,row,"序号",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 5);
	    	
	    	labelC = new Label(++mr,row,"申请人",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"性别",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 8);
	    	
	    	labelC = new Label(++mr,row,"年龄",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 8);
	    	
	    	labelC = new Label(++mr,row,"所在单位",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 28);
	    	
	    	labelC = new Label(++mr,row,"学历/学位",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"职称/职务",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	//labelC = new Label(++mr,row,"专业特长",wcfFC);
	    	//ws.addCell(labelC);
	    	//ws.setColumnView(mr, 18);
	    	
	    	labelC = new Label(++mr,row,"专/兼职",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"生活资助经费",wcfFC);
	    	ws.addCell(labelC);
	    	
	    	++mr;++mr;++mr;
	    	labelC = new Label(mr,row,"住房补贴费",wcfFC);
	    	ws.addCell(labelC);
	    	
	    	++mr;++mr;++mr;++mr;
	    	labelC = new Label(mr,row,"备注",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 15);
	    	
	    	
	    	
	    	++row;
	    	labelC = new Label(8,row,"资助标准(元/月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(8, 10);
	    	
	    	labelC = new Label(9,row,"在常时间(月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(9, 10);
	    	
	    	
	    	labelC = new Label(10,row,"资助总额(元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(10, 10);
	    	
	    	
	    	labelC = new Label(11,row,"住宿地(城内/城外)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(11, 10);
	    	
	    	labelC = new Label(12,row,"补贴标准(元/月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(12, 10);
	    	
	    	labelC = new Label(13,row,"在常时间(月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(13, 10);
	    	
	    	labelC = new Label(14,row,"资助总额(元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(14, 10);
	    	
	    	
	    	
	    	sql = "select c.DWMC,a.DWID,a.RYID,isnull(a.XM,' ') XM,a.SFZ,a.SEX_MC,a.BIRTHDAY,isnull(a.XL_MC,' ') XL_MC,isnull(a.ZC_MC,' ') ZC_MC,a.ZJZ,b.SBID,b.DWID as DC,b.SHZZBZ, b.SHZCSJ, b.SHZZZE, b.ZFZFD, b.ZFBTBZ, b.ZFZCSJ, b.ZFBTZE," +
			"b.ZFZFD2, b.ZFBTBZ2, b.ZFZCSJ2, b.ZFBTZE2, b.ZE,b.SM " +
			" from dw_ryxx_v a,jfhsb_shzf b,dw_info_v c" +
			" where a.ryid=b.ryid and a.dwid=b.dwid and a.dwid=c.dwid and (b.bz=3) and b.sbdm='"+dm[0].substring(0,4)+"0001'" +
			" and sbxx='001'" +//未变化人员
				" ";
			List<Map<String,Object>> list = this.getSimpleJdbcTemplate().queryForList(sql);
	    	for(int i=0;i<list.size();i++){
	    		++row; mr = 0;
	    		labelC = new Label(mr,row,String.valueOf(i+1),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("XM")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SEX_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	if(String.valueOf(list.get(i).get("BIRTHDAY"))==null || String.valueOf(list.get(i).get("BIRTHDAY")).equals("") || String.valueOf(list.get(i).get("BIRTHDAY")).equals("null")){
		    		labelC = new Label(++mr,row,"",wcfFCC);
			    	ws.addCell(labelC);
		    	}else{
		    		labelC = new Label(++mr,row,String.valueOf(this.getAge(String.valueOf(list.get(i).get("BIRTHDAY")))),wcfFCC);
			    	ws.addCell(labelC);
		    	}
		    	
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("DWMC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("XL_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("ZC_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("ZJZ")).equals("2")?"兼职":"专职",wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SHZZBZ")),wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SHZCSJ")),wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SHZZZE")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	String  ZFZFD = "";
		    	String  ZFBTBZ = "";
		    	String  ZFZCSJ = "";
		    	String  ZFBTZE = "";
		    	if(String.valueOf(list.get(i).get("ZFZFD")).equals("1")){
		    		ZFZFD = "城内";
		    		ZFBTBZ = String.valueOf(list.get(i).get("ZFBTBZ"));
		    		ZFZCSJ = String.valueOf(list.get(i).get("ZFZCSJ"));
		    		ZFBTZE = String.valueOf(list.get(i).get("ZFBTZE"));
		    	}else{
		    		ZFZFD = "无";
		    		ZFBTBZ = "0";
		    		ZFZCSJ = "0";
		    		ZFBTZE = "0";	
		    	}
		    	
		    	if(String.valueOf(list.get(i).get("ZFZFD2")).equals("1")){
		    		ZFZFD += "  /  城外";
		    		ZFBTBZ += "  /  "+String.valueOf(list.get(i).get("ZFBTBZ2"));
		    		ZFZCSJ += "  /  "+String.valueOf(list.get(i).get("ZFZCSJ2"));
		    		ZFBTZE += "  /  "+String.valueOf(list.get(i).get("ZFBTZE2"));
		    	}else{
		    		if(ZFZFD.equals("城内")){
		    			ZFZFD = ZFZFD+" / 无";
			    		ZFBTBZ += " / 0";
			    		ZFZCSJ += " / 0";
			    		ZFBTZE += " / 0";					    			
		    		}
		    	}
		    	labelC = new Label(++mr,row,ZFZFD,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,ZFBTBZ,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,ZFZCSJ,wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,ZFBTZE,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SM")),wcfFCC);
		    	ws.addCell(labelC);
	    	}
	    	
	    	/***
	    	 * 获取新增人员
	    	
	    	++row;++row;++row;
	    	mr = 0;//列
		    rr = row;//行
		    ws.mergeCells(mr, rr, 15, rr);//标题
		    ++rr;
		    ws.mergeCells(mr, rr, 15, rr);//副标题
		    
		    ++rr;
		    ws.mergeCells(mr, rr, mr, rr+1); //字段名
		    ws.mergeCells(mr+1, rr, mr+1, rr+1); //字段名
		    ws.mergeCells(mr+2, rr, mr+2, rr+1); //字段名
		    ws.mergeCells(mr+3, rr, mr+3, rr+1); //字段名
		    ws.mergeCells(mr+4, rr, mr+4, rr+1); //字段名
		    ws.mergeCells(mr+5, rr, mr+5, rr+1); //字段名
		    ws.mergeCells(mr+6, rr, mr+6, rr+1); //字段名
		    ws.mergeCells(mr+7, rr, mr+7, rr+1); //字段名
		   // ws.mergeCells(mr+8, 1, mr+8, 2); //字段名
		    
		    
		    ws.mergeCells(mr+8, rr, mr+10, rr); //字段名
		    ws.mergeCells(mr+11, rr, mr+14, rr); //字段名
		    ws.mergeCells(mr+15,rr, mr+15, rr+1); //字段名
	    	
		    mr = 0;
	    	labelC = new Label(mr,row,"常州科教城“金凤凰高层次人才引进计划”生活资助和住房补贴申报表",wcfFCT);
	    	ws.addCell(labelC);
	    	
	    	++row;
	    	labelC = new Label(mr,row,"新    增    人    员",wcfFCT);
	    	ws.addCell(labelC);
	    	
	    	++row;
	    	labelC = new Label(mr,row,"序号",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 5);
	    	
	    	labelC = new Label(++mr,row,"申请人",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"性别",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 8);
	    	
	    	labelC = new Label(++mr,row,"年龄",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 8);
	    	
	    	labelC = new Label(++mr,row,"所在单位",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 28);
	    	
	    	labelC = new Label(++mr,row,"学历/学位",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"职称/职务",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	//labelC = new Label(++mr,row,"专业特长",wcfFC);
	    	//ws.addCell(labelC);
	    	//ws.setColumnView(mr, 18);
	    	
	    	labelC = new Label(++mr,row,"专/兼职",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"生活资助经费",wcfFC);
	    	ws.addCell(labelC);
	    	
	    	++mr;++mr;++mr;
	    	labelC = new Label(mr,row,"住房补贴费",wcfFC);
	    	ws.addCell(labelC);
	    	
	    	++mr;++mr;++mr;++mr;
	    	labelC = new Label(mr,row,"备注",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 15);
	    	
	    	
	    	
	    	++row;
	    	labelC = new Label(8,row,"资助标准(元/月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(8, 10);
	    	
	    	labelC = new Label(9,row,"在常时间(月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(9, 10);
	    	
	    	
	    	labelC = new Label(10,row,"资助总额(元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(10, 10);
	    	
	    	
	    	labelC = new Label(11,row,"住宿地(城内/城外)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(11, 10);
	    	
	    	labelC = new Label(12,row,"补贴标准(元/月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(12, 10);
	    	
	    	labelC = new Label(13,row,"在常时间(月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(13, 10);
	    	
	    	labelC = new Label(14,row,"资助总额(元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(14, 10);
	    	
	    	
	    	//String[] dm = sbdm.split("@");
	    	sql = "select c.DWMC,a.DWID,a.RYID,isnull(a.XM,' ') XM,a.SFZ,a.SEX_MC,a.BIRTHDAY,isnull(a.XL_MC,' ') XL_MC,isnull(a.ZC_MC,' ') ZC_MC,a.ZJZ,b.SBID,b.DWID as DC,b.SHZZBZ, b.SHZCSJ, b.SHZZZE, b.ZFZFD, b.ZFBTBZ, b.ZFZCSJ, b.ZFBTZE," +
			"b.ZFZFD2, b.ZFBTBZ2, b.ZFZCSJ2, b.ZFBTZE2, b.ZE,b.SM " +
			" from dw_ryxx_v a,jfhsb_shzf b,dw_info_v c" +
			" where a.ryid=b.ryid and a.dwid=b.dwid and a.dwid=c.dwid and (b.bz=3 ) and b.sbdm='"+dm[0].substring(0,4)+"0001'" +
			" and sbxx='002'" +//新增人员
				" ";
			list = this.getSimpleJdbcTemplate().queryForList(sql);
	    	for(int i=0;i<list.size();i++){
	    		++row; mr = 0;
	    		labelC = new Label(mr,row,String.valueOf(i+1),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("XM")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SEX_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	if(String.valueOf(list.get(i).get("BIRTHDAY"))==null || String.valueOf(list.get(i).get("BIRTHDAY")).equals("") || String.valueOf(list.get(i).get("BIRTHDAY")).equals("null")){
		    		labelC = new Label(++mr,row,"",wcfFCC);
			    	ws.addCell(labelC);
		    	}else{
		    		labelC = new Label(++mr,row,String.valueOf(this.getAge(String.valueOf(list.get(i).get("BIRTHDAY")))),wcfFCC);
			    	ws.addCell(labelC);
		    	}
		    	
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("DWMC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("XL_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("ZC_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("ZJZ")).equals("2")?"兼职":"专职",wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SHZZBZ")),wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SHZCSJ")),wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SHZZZE")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	String  ZFZFD = "";
		    	String  ZFBTBZ = "";
		    	String  ZFZCSJ = "";
		    	String  ZFBTZE = "";
		    	if(String.valueOf(list.get(i).get("ZFZFD")).equals("1")){
		    		ZFZFD = "城内";
		    		ZFBTBZ = String.valueOf(list.get(i).get("ZFBTBZ"));
		    		ZFZCSJ = String.valueOf(list.get(i).get("ZFZCSJ"));
		    		ZFBTZE = String.valueOf(list.get(i).get("ZFBTZE"));
		    	}else{
		    		ZFZFD = "无";
		    		ZFBTBZ = "0";
		    		ZFZCSJ = "0";
		    		ZFBTZE = "0";	
		    	}
		    	
		    	if(String.valueOf(list.get(i).get("ZFZFD2")).equals("1")){
		    		ZFZFD += "  /  城外";
		    		ZFBTBZ += "  /  "+String.valueOf(list.get(i).get("ZFBTBZ2"));
		    		ZFZCSJ += "  /  "+String.valueOf(list.get(i).get("ZFZCSJ2"));
		    		ZFBTZE += "  /  "+String.valueOf(list.get(i).get("ZFBTZE2"));
		    	}else{
		    		if(ZFZFD.equals("城内")){
		    			ZFZFD = ZFZFD+" / 无";
			    		ZFBTBZ += " / 0";
			    		ZFZCSJ += " / 0";
			    		ZFBTZE += " / 0";					    			
		    		}
		    	}
		    	labelC = new Label(++mr,row,ZFZFD,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,ZFBTBZ,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,ZFZCSJ,wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,ZFBTZE,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SM")),wcfFCC);
		    	ws.addCell(labelC);
	    	}
	    	
	    	
	    	/***
	    	 * 领导及学术带头人
	    	 
	    	++row;++row;++row;
	    	mr = 0;//列
		    rr = row;//行
		    ws.mergeCells(mr, rr, 11, rr);//标题
		   // ++rr;
		   // ws.mergeCells(mr, rr, 15, rr);//副标题
		    
		    ++rr;
		    ws.mergeCells(mr, rr, mr, rr+1); //字段名
		    ws.mergeCells(mr+1, rr, mr+1, rr+1); //字段名
		    ws.mergeCells(mr+2, rr, mr+2, rr+1); //字段名
		    ws.mergeCells(mr+3, rr, mr+3, rr+1); //字段名
		    ws.mergeCells(mr+4, rr, mr+4, rr+1); //字段名
		    ws.mergeCells(mr+5, rr, mr+5, rr+1); //字段名
		    ws.mergeCells(mr+6, rr, mr+6, rr+1); //字段名
		    ws.mergeCells(mr+7, rr, mr+7, rr+1); //字段名
		   // ws.mergeCells(mr+8, 1, mr+8, 2); //字段名
		    
		    
		    ws.mergeCells(mr+8, rr, mr+10, rr); //字段名
		    //ws.mergeCells(mr+11, rr, mr+14, rr); //字段名
		    ws.mergeCells(mr+11,rr, mr+11, rr+1); //字段名
	    	
		    mr = 0;
	    	labelC = new Label(mr,row,"常州科教城“金凤凰高层次人才引进计划”经费资助（奖励）——领导及学术带头人",wcfFCT);
	    	ws.addCell(labelC);
	    	
	    	//++row;
	    	//labelC = new Label(mr,row,"新    增    人    员",wcfFCT);
	    	//ws.addCell(labelC);
	    	
	    	++row;
	    	labelC = new Label(mr,row,"序号",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 5);
	    	
	    	labelC = new Label(++mr,row,"申请人",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"性别",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 8);
	    	
	    	labelC = new Label(++mr,row,"年龄",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 8);
	    	
	    	labelC = new Label(++mr,row,"所在单位",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 28);
	    	
	    	labelC = new Label(++mr,row,"学历/学位",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"职称/职务",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	//labelC = new Label(++mr,row,"专业特长",wcfFC);
	    	//ws.addCell(labelC);
	    	//ws.setColumnView(mr, 18);
	    	
	    	labelC = new Label(++mr,row,"专/兼职",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"生活资助经费",wcfFC);
	    	ws.addCell(labelC);
	    	
	    	++mr;++mr;++mr;
	    	
	    	labelC = new Label(mr,row,"备注",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 15);
	    	
	    	
	    	
	    	++row;
	    	labelC = new Label(8,row,"资助标准(元/月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(8, 10);
	    	
	    	labelC = new Label(9,row,"在常时间(月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(9, 10);
	    	
	    	
	    	labelC = new Label(10,row,"资助总额(元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(10, 10);
	    	
	    	
	    	
	    	//String[] dm = sbdm.split("@");
	    	sql = "select c.DWMC,a.DWID,a.RYID,isnull(a.XM,' ') XM,a.SFZ,a.SEX_MC,a.BIRTHDAY,isnull(a.XL_MC,' ') XL_MC,isnull(a.ZC_MC,' ') ZC_MC,a.ZJZ,b.SBID,b.DWID as DC,b.SHZZBZ, b.SHZCSJ, b.SHZZZE, b.ZFZFD, b.ZFBTBZ, b.ZFZCSJ, b.ZFBTZE," +
			"b.ZFZFD2, b.ZFBTBZ2, b.ZFZCSJ2, b.ZFBTZE2, b.ZE,b.SM " +
			" from dw_ryxx_v a,jfhsb_shzf b,dw_info_v c" +
			" where a.ryid=b.ryid and a.dwid=b.dwid and a.dwid=c.dwid and (b.bz=3 ) and b.sbdm='"+dm[0].substring(0,4)+"0001'" +
			" and sbxx='004'" +//领导及学术带头人
				" ";
			list = this.getSimpleJdbcTemplate().queryForList(sql);
	    	for(int i=0;i<list.size();i++){
	    		++row; mr = 0;
	    		labelC = new Label(mr,row,String.valueOf(i+1),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("XM")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SEX_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	if(String.valueOf(list.get(i).get("BIRTHDAY"))==null || String.valueOf(list.get(i).get("BIRTHDAY")).equals("") || String.valueOf(list.get(i).get("BIRTHDAY")).equals("null")){
		    		labelC = new Label(++mr,row,"",wcfFCC);
			    	ws.addCell(labelC);
		    	}else{
		    		labelC = new Label(++mr,row,String.valueOf(this.getAge(String.valueOf(list.get(i).get("BIRTHDAY")))),wcfFCC);
			    	ws.addCell(labelC);
		    	}
		    	
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("DWMC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("XL_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("ZC_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("ZJZ")).equals("2")?"兼职":"专职",wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SHZZBZ")),wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SHZCSJ")),wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SHZZZE")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	/***
		    	String  ZFZFD = "";
		    	String  ZFBTBZ = "";
		    	String  ZFZCSJ = "";
		    	String  ZFBTZE = "";
		    	if(String.valueOf(list.get(i).get("ZFZFD")).equals("1")){
		    		ZFZFD = "城内";
		    		ZFBTBZ = String.valueOf(list.get(i).get("ZFBTBZ"));
		    		ZFZCSJ = String.valueOf(list.get(i).get("ZFZCSJ"));
		    		ZFBTZE = String.valueOf(list.get(i).get("ZFBTZE"));
		    	}else{
		    		ZFZFD = "无";
		    		ZFBTBZ = "0";
		    		ZFZCSJ = "0";
		    		ZFBTZE = "0";	
		    	}
		    	
		    	if(String.valueOf(list.get(i).get("ZFZFD2")).equals("1")){
		    		ZFZFD += "  /  城外";
		    		ZFBTBZ += "  /  "+String.valueOf(list.get(i).get("ZFBTBZ2"));
		    		ZFZCSJ += "  /  "+String.valueOf(list.get(i).get("ZFZCSJ2"));
		    		ZFBTZE += "  /  "+String.valueOf(list.get(i).get("ZFBTZE2"));
		    	}else{
		    		if(ZFZFD.equals("城内")){
		    			ZFZFD = ZFZFD+" / 无";
			    		ZFBTBZ += " / 0";
			    		ZFZCSJ += " / 0";
			    		ZFBTZE += " / 0";					    			
		    		}
		    	}
		    	labelC = new Label(++mr,row,ZFZFD,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,ZFBTBZ,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,ZFZCSJ,wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,ZFBTZE,wcfFCC);
		    	ws.addCell(labelC);
		    	*
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SM")),wcfFCC);
		    	ws.addCell(labelC);
	    	}
	    	
	    	
	    	
	    	
	    	
	    	/***
	    	 * 总表
	    	 
	    	++row;++row;++row;
	    	mr = 0;//列
		    rr = row;//行
		    ws.mergeCells(mr, rr, 15, rr);//标题
		   // ++rr;
		  //  ws.mergeCells(mr, rr, 15, rr);//副标题
		    
		    ++rr;
		    ws.mergeCells(mr, rr, mr, rr+1); //字段名
		    ws.mergeCells(mr+1, rr, mr+1, rr+1); //字段名
		    ws.mergeCells(mr+2, rr, mr+2, rr+1); //字段名
		    ws.mergeCells(mr+3, rr, mr+3, rr+1); //字段名
		    ws.mergeCells(mr+4, rr, mr+4, rr+1); //字段名
		    ws.mergeCells(mr+5, rr, mr+5, rr+1); //字段名
		    ws.mergeCells(mr+6, rr, mr+6, rr+1); //字段名
		    ws.mergeCells(mr+7, rr, mr+7, rr+1); //字段名
		   // ws.mergeCells(mr+8, 1, mr+8, 2); //字段名
		    
		    
		    ws.mergeCells(mr+8, rr, mr+10, rr); //字段名
		    ws.mergeCells(mr+11, rr, mr+14, rr); //字段名
		    ws.mergeCells(mr+15,rr, mr+15, rr+1); //字段名
	    	
		    mr = 0;
	    	labelC = new Label(mr,row,"常州科教城“金凤凰高层次人才引进计划”生活资助、住房补贴和安家费汇总表",wcfFCT);
	    	ws.addCell(labelC);
	    	
	    	
	    	++row;
	    	labelC = new Label(mr,row,"序号",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 5);
	    	
	    	labelC = new Label(++mr,row,"姓名",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"性别",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 8);
	    	
	    	labelC = new Label(++mr,row,"年龄",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 8);
	    	
	    	labelC = new Label(++mr,row,"所在单位",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 28);
	    	
	    	labelC = new Label(++mr,row,"学历/学位",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"职称/职务",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	//labelC = new Label(++mr,row,"专业特长",wcfFC);
	    	//ws.addCell(labelC);
	    	//ws.setColumnView(mr, 18);
	    	
	    	labelC = new Label(++mr,row,"专/兼职",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"生活资助经费",wcfFC);
	    	ws.addCell(labelC);
	    	
	    	++mr;++mr;++mr;
	    	labelC = new Label(mr,row,"住房补贴费",wcfFC);
	    	ws.addCell(labelC);
	    	
	    	++mr;++mr;++mr;++mr;
	    	labelC = new Label(mr,row,"安家费",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 15);
	    	
	    	
	    	
	    	++row;
	    	labelC = new Label(8,row,"资助标准(元/月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(8, 10);
	    	
	    	labelC = new Label(9,row,"在常时间(月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(9, 10);
	    	
	    	
	    	labelC = new Label(10,row,"资助总额(元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(10, 10);
	    	
	    	
	    	labelC = new Label(11,row,"住宿地(城内/城外)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(11, 10);
	    	
	    	labelC = new Label(12,row,"补贴标准(元/月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(12, 10);
	    	
	    	labelC = new Label(13,row,"在常时间(月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(13, 10);
	    	
	    	labelC = new Label(14,row,"资助总额(元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(14, 10);
	    	

			sql = "select c.DWMC,a.RYID,isnull(a.XM,' ') XM,a.SFZ,a.SEX_MC,a.BIRTHDAY,isnull(a.XL_MC,' ') XL_MC,isnull(a.ZC_MC,' ') ZC_MC,a.ZJZ" +
					" from dw_ryxx_v a,dw_info_v c " +
					" where a.dwid=c.dwid and (a.ryid in ( select ryid from jfhsb_ajf where sbdm='"+dm[0].substring(0,4)+"0002' and bz=3 ) " +
					" or a.ryid in ( select ryid from jfhsb_shzf where sbdm='"+dm[0].substring(0,4)+"0001'  and bz=3))  ";
			List<Map<String,Object>> listry = this.getSimpleJdbcTemplate().queryForList(sql);
			
			
	    	//String[] dm = sbdm.split("@");
	    	sql = "select c.DWMC,a.DWID,a.RYID,isnull(a.XM,' ') XM,a.SFZ,a.SEX_MC,a.BIRTHDAY,isnull(a.XL_MC,' ') XL_MC,isnull(a.ZC_MC,' ') ZC_MC,a.ZJZ,b.SBID,b.DWID as DC,b.SHZZBZ, b.SHZCSJ, b.SHZZZE, b.ZFZFD, b.ZFBTBZ, b.ZFZCSJ, b.ZFBTZE," +
			"b.ZFZFD2, b.ZFBTBZ2, b.ZFZCSJ2, b.ZFBTZE2, b.ZE " +
			" from dw_ryxx_v a,jfhsb_shzf b,dw_info_v c" +
			" where a.ryid=b.ryid and a.dwid=b.dwid and a.dwid=c.dwid and (b.bz=3) and b.sbdm='"+dm[0].substring(0,4)+"0001'" +
			" " ;
	    	List<Map<String,Object>> listzz = this.getSimpleJdbcTemplate().queryForList(sql);
	    	
	    	
	    	//安家费
			sql = "select a.RYID,a.XM,a.SFZ,a.SEX_MC,isnull(a.XL_MC,' ') XL_MC,a.ZC_MC,a.ZJZ, convert(numeric(18,2),(isnull(ZZBZ,0))) ZZBZ " +
				" from dw_ryxx_v a , jfhsb_ajf b,dw_info_v c " +
				" where a.ryid=b.ryid and a.dwid=b.dwid and a.dwid=c.dwid and (b.bz=3) and b.sbdm='"+dm[0].substring(0,4)+"0002'" +
				" " ;
		    List<Map<String,Object>> listajf =  this.getSimpleJdbcTemplate().queryForList(sql);
		    
	    	for(int i=0;i<listry.size();i++){
	    		++row; mr = 0;
	    		labelC = new Label(mr,row,String.valueOf(i+1),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listry.get(i).get("XM")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listry.get(i).get("SEX_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	if(String.valueOf(listry.get(i).get("BIRTHDAY"))==null || String.valueOf(listry.get(i).get("BIRTHDAY")).equals("") || String.valueOf(listry.get(i).get("BIRTHDAY")).equals("null")){
		    		labelC = new Label(++mr,row,"",wcfFCC);
			    	ws.addCell(labelC);
		    	}else{
		    		labelC = new Label(++mr,row,String.valueOf(this.getAge(String.valueOf(listry.get(i).get("BIRTHDAY")))),wcfFCC);
			    	ws.addCell(labelC);
		    	}
		    	
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listry.get(i).get("DWMC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listry.get(i).get("XL_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listry.get(i).get("ZC_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listry.get(i).get("ZJZ")).equals("2")?"兼职":"专职",wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	
		    	for(int j=0;j<listzz.size();j++){
		    		if(String.valueOf(listry.get(i).get("RYID")).equals(String.valueOf(listzz.get(j).get("RYID")))){

				    	labelC = new Label(++mr,row,String.valueOf(listzz.get(j).get("SHZZBZ")),wcfFCC);
				    	ws.addCell(labelC);

				    	labelC = new Label(++mr,row,String.valueOf(listzz.get(j).get("SHZCSJ")),wcfFCC);
				    	ws.addCell(labelC);

				    	labelC = new Label(++mr,row,String.valueOf(listzz.get(j).get("SHZZZE")),wcfFCC);
				    	ws.addCell(labelC);
				    	
				    	String  ZFZFD = "";
				    	String  ZFBTBZ = "";
				    	String  ZFZCSJ = "";
				    	String  ZFBTZE = "";
				    	if(String.valueOf(listzz.get(j).get("ZFZFD")).equals("1")){
				    		ZFZFD = "城内";
				    		ZFBTBZ = String.valueOf(listzz.get(j).get("ZFBTBZ"));
				    		ZFZCSJ = String.valueOf(listzz.get(j).get("ZFZCSJ"));
				    		ZFBTZE = String.valueOf(listzz.get(j).get("ZFBTZE"));
				    	}else{
				    		ZFZFD = "无";
				    		ZFBTBZ = "0";
				    		ZFZCSJ = "0";
				    		ZFBTZE = "0";				    		
				    	}
				    	
				    	if(String.valueOf(listzz.get(j).get("ZFZFD2")).equals("1")){
				    		ZFZFD += " / 城外";
				    		ZFBTBZ += " / "+String.valueOf(listzz.get(j).get("ZFBTBZ2"));
				    		ZFZCSJ += " / "+String.valueOf(listzz.get(j).get("ZFZCSJ2"));
				    		ZFBTZE += " / "+String.valueOf(listzz.get(j).get("ZFBTZE2"));
				    	}else{
				    		if(ZFZFD.equals("城内")){
				    			ZFZFD = ZFZFD+" / 无";
					    		ZFBTBZ += " / 0";
					    		ZFZCSJ += " / 0";
					    		ZFBTZE += " / 0";					    			
				    		}
				    	}
				    	labelC = new Label(++mr,row,ZFZFD,wcfFCC);
				    	ws.addCell(labelC);
				    	
				    	labelC = new Label(++mr,row,ZFBTBZ,wcfFCC);
				    	ws.addCell(labelC);
				    	
				    	labelC = new Label(++mr,row,ZFZCSJ,wcfFCC);
				    	ws.addCell(labelC);

				    	labelC = new Label(++mr,row,ZFBTZE,wcfFCC);
				    	ws.addCell(labelC);
				    	
//				    	labelC = new Label(12,row,String.valueOf(listzz.get(j).get("ZZE")),wcfFCC);
//				    	ws.addCell(labelC);
				    	
				    	boolean hasAJF = false;
				    	for(int jj=0;jj<listajf.size();jj++){
				    		if(String.valueOf(listry.get(i).get("RYID")).equals(String.valueOf(listajf.get(jj).get("RYID")))){
				    			hasAJF = true;
						    	labelC = new Label(++mr,row,String.valueOf(listajf.get(jj).get("ZZBZ")),wcfFCC);
						    	ws.addCell(labelC);
						    	break;
						    	/**
						    	Float hj = Float.valueOf( String.valueOf( listajf.get(jj).get("ZZBZ") ) ) 
						    				+ Float.valueOf( String.valueOf( listzz.get(j).get("ZZE") ) ) ;						    	
						    	
						    	labelC = new Label(++mr,row,String.valueOf( hj ),wcfFCC);
						    	ws.addCell(labelC);			
						    	*				    	
						    }
				    	}		    	
				    	if( !hasAJF ){
					    	labelC = new Label(++mr,row,"",wcfFCC);
					    	ws.addCell(labelC);
					    				    	
				    	}				    	
				    	
				    	
		    		}
		    	}
		    	
		    	
	    	}
	    	
	    	
	    	
	    	
	    	
	    	
	    	//++row;
	    	//ws.mergeCells(0, row, 15, row); //字段名
	    	//labelC = new Label(0,row,"                                                                                                                                                                    领导签字:",wcfFCC);
	    	//ws.addCell(labelC);
	    	
	    	wwb.write();
	    	wwb.close();
		}catch(Exception e){
			throw  new BusException("导出出现异常!");
		}
		
	}
	
**/
	public void doExportjfhwh(OutputStream os,String sbdm ){
		WritableWorkbook wwb = null;
		
		String sql = "";
		try{
			//创建 Excel 
			wwb = Workbook.createWorkbook(os);
			
			
			String[] dm = sbdm.split("@");
			
			

			jxl.write.Label labelC = null;
			Number labelNF = null;
			
			jxl.write.WritableFont wfcT = new jxl.write.WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
    	    jxl.write.WritableCellFormat wcfFCT = new jxl.write.WritableCellFormat(wfcT);
    	    wcfFCT.setAlignment(Alignment.CENTRE);
    	    wcfFCT.setBorder(Border.ALL, BorderLineStyle.THIN);
    	    wcfFCT.setVerticalAlignment(VerticalAlignment.CENTRE);//垂直居中

    	    jxl.write.WritableFont wfcT_ = new jxl.write.WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
    	    jxl.write.WritableCellFormat wcfFC_ = new jxl.write.WritableCellFormat(wfcT_);
    	    wcfFC_.setBorder(Border.ALL, BorderLineStyle.THIN);
    	    wcfFC_.setVerticalAlignment(VerticalAlignment.CENTRE);//垂直居中
    	    
    	    
			jxl.write.WritableFont wfc = new jxl.write.WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
    	    jxl.write.WritableCellFormat wcfFC = new jxl.write.WritableCellFormat(wfc);
    	    wcfFC.setAlignment(Alignment.CENTRE);
    	    wcfFC.setWrap(true);//自动换行
    	    wcfFC.setBorder(Border.ALL, BorderLineStyle.THIN);
    	    wcfFC.setVerticalAlignment(VerticalAlignment.CENTRE);//垂直居中
    	    
    	    
    	    jxl.write.WritableFont wfcc = new jxl.write.WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false,UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
    	    jxl.write.WritableCellFormat wcfFCC = new jxl.write.WritableCellFormat(wfcc);
    	    wcfFCC.setWrap(true);
    	    wcfFCC.setBorder(Border.ALL, BorderLineStyle.THIN);
    	    wcfFCC.setVerticalAlignment(VerticalAlignment.CENTRE);//垂直居中
    	    
    	    //数值
		    jxl.write.NumberFormat nf = new jxl.write.NumberFormat("0.00");
		    jxl.write.WritableCellFormat wcfN = new jxl.write.WritableCellFormat(nf);
		    
		    int row = 0;
		    int mr = 0;//列
		    int rr = 0;//行
		    
		    WritableSheet ws1 = wwb.createSheet("新增安家费",0);
		    mr =0;rr =0;
		    ws1.mergeCells(mr, rr, 15, rr);//标题
		    //++rr;
		    //ws.mergeCells(mr, rr, 15, rr);//副标题
		    
		    ++rr;
		    ws1.mergeCells(mr, rr, mr, rr+1); //字段名
		    ws1.mergeCells(mr+1, rr, mr+1, rr+1); //字段名
		    ws1.mergeCells(mr+2, rr, mr+2, rr+1); //字段名
		    ws1.mergeCells(mr+3, rr, mr+3, rr+1); //字段名
		    ws1.mergeCells(mr+4, rr, mr+4, rr+1); //字段名
		    ws1.mergeCells(mr+5, rr, mr+5, rr+1); //字段名
		    ws1.mergeCells(mr+6, rr, mr+6, rr+1); //字段名
		    ws1.mergeCells(mr+7, rr, mr+7, rr+1); //字段名
		    ws1.mergeCells(mr+8, rr, mr+8, rr+1); //字段名
		    ws1.mergeCells(mr+9, rr, mr+9, rr+1); //字段名
		    ws1.mergeCells(mr+10, rr, mr+10, rr+1); //字段名
		    ws1.mergeCells(mr+11, rr, mr+11, rr+1); //字段名
		    ws1.mergeCells(mr+12, rr, mr+12, rr+1); //字段名
		    ws1.mergeCells(mr+13, rr, mr+13, rr+1); //字段名
		    ws1.mergeCells(mr+14, rr, mr+14, rr+1); //字段名
		    ws1.mergeCells(mr+15, rr, mr+15, rr+1); //字段名
		    
		    mr = 0;
		    row = 0;
		    labelC = new Label(mr,row,"常州科教城“金凤凰高层次人才引进计划”安家费申报表",wcfFCT);
	    	ws1.addCell(labelC);
	    	
	    	//++row;
	    	//labelC = new Label(mr,row,"未  变  化  人  员",wcfFCT);
	    	//ws.addCell(labelC);
	    	
	    	++row;
	    	labelC = new Label(mr,row,"序号",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 5);
	    	
	    	labelC = new Label(++mr,row,"申请人",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"性别",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 8);
	    	
	    	labelC = new Label(++mr,row,"年龄",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 8);
	    	
	    	labelC = new Label(++mr,row,"所在单位",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 28);
	    	
	    	labelC = new Label(++mr,row,"学历/学位",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"职称/职务",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 10);
	    	
	    	//labelC = new Label(++mr,row,"专业特长",wcfFC);
	    	//ws.addCell(labelC);
	    	//ws.setColumnView(mr, 18);
	    	
	    	labelC = new Label(++mr,row,"专/兼职",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"购房时间",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"资助标准总额(万元)",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"购房地",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"申请领取金额(万元)",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 12);
	    	
	    	labelC = new Label(++mr,row,"房屋面积(平米)",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 15);
	    	
	    	labelC = new Label(++mr,row,"总额(万元)",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"房屋交付时间",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"备注",wcfFC);
	    	ws1.addCell(labelC);
	    	ws1.setColumnView(mr, 10);
	    	
	    	//安家费
			sql = "select c.DWMC,a.RYID,a.XM,a.SFZ,a.SEX_MC,isnull(a.XL_MC,' ') XL_MC,convert(varchar(12),a.BIRTHDAY,23) BIRTHDAY," +
					"a.ZC_MC,a.ZJZ," +
					" GFDD, convert(numeric(18,2),(isnull(FWMJ,0))) FWMJ," +
					" convert(numeric(18,2),(isnull(FWZE,0))) FWZE, convert(varchar(12),JFSJ,23) JFSJ,convert(varchar(12),b.GFSJ,23) GFSJ," +
					" convert(numeric(18,2),(isnull(DYNBT,0))) DYNBT, convert(numeric(18,2),(isnull(ZZBZ,0))) ZZBZ, b.SM " +
				" from dw_ryxx_v a , jfhsb_ajf_wh b,dw_info_v c " +
				" where a.ryid=b.ryid and a.dwid=b.dwid and a.dwid=c.dwid  and b.sbdm='"+dm[0].substring(0,4)+"0002'" +
				" " ;
		    List<Map<String,Object>> listajf1 =  this.getSimpleJdbcTemplate().queryForList(sql);
		    ++row;
		    for(int i=0;i<listajf1.size();i++){
	    		++row; mr = 0;
	    		labelC = new Label(mr,row,String.valueOf(i+1),wcfFCC);
		    	ws1.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("XM")),wcfFCC);
		    	ws1.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("SEX_MC")),wcfFCC);
		    	ws1.addCell(labelC);
		    	
		    	if(String.valueOf(listajf1.get(i).get("BIRTHDAY"))==null || String.valueOf(listajf1.get(i).get("BIRTHDAY")).equals("") || String.valueOf(listajf1.get(i).get("BIRTHDAY")).equals("null")){
		    		labelC = new Label(++mr,row,"",wcfFCC);
			    	ws1.addCell(labelC);
		    	}else{
		    		labelC = new Label(++mr,row,String.valueOf(this.getAge(String.valueOf(listajf1.get(i).get("BIRTHDAY")))),wcfFCC);
			    	ws1.addCell(labelC);
		    	}
		    	
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("DWMC")),wcfFCC);
		    	ws1.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("XL_MC")),wcfFCC);
		    	ws1.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("ZC_MC")),wcfFCC);
		    	ws1.addCell(labelC);
		    	
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("ZJZ")).equals("2")?"兼职":"专职",wcfFCC);
		    	ws1.addCell(labelC);
		    	
		    	
		    	
		    //	购房时间	资助标准总额（万元）	购房地	申请领取金额（万元）	房屋面积（单位：平米）	总额（单位：万）	房屋交付时间	备注
				

		    	
		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("GFSJ")),wcfFCC);
		    	ws1.addCell(labelC);

		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("ZZBZ")),wcfFCC);
		    	ws1.addCell(labelC);

		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("GFDD")),wcfFCC);
		    	ws1.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("DYNBT")),wcfFCC);
		    	ws1.addCell(labelC);

		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("FWMJ")),wcfFCC);
		    	ws1.addCell(labelC);

		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("FWZE")),wcfFCC);
		    	ws1.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("JFSJ")),wcfFCC);
		    	ws1.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listajf1.get(i).get("SM")),wcfFCC);
		    	ws1.addCell(labelC);
	    	}
		    

			WritableSheet ws = wwb.createSheet("本季度生活和住房未变化和新增情况",1);
		    /////////////////////////////////////////第二个 SHEET////////////////////////////////////////////////////////////////////
		    mr =0;rr =0;
		    ws.mergeCells(mr, rr, 19, rr);//标题
		    ++rr;
		    ws.mergeCells(mr, rr, 19, rr);//副标题
		    
		    ++rr;
		    ws.mergeCells(mr, rr, mr, rr+1); //字段名
		    ws.mergeCells(mr+1, rr, mr+1, rr+1); //字段名
		    ws.mergeCells(mr+2, rr, mr+2, rr+1); //字段名
		    ws.mergeCells(mr+3, rr, mr+3, rr+1); //字段名
		    ws.mergeCells(mr+4, rr, mr+4, rr+1); //字段名
		    ws.mergeCells(mr+5, rr, mr+5, rr+1); //字段名
		    ws.mergeCells(mr+6, rr, mr+6, rr+1); //字段名
		    ws.mergeCells(mr+7, rr, mr+7, rr+1); //字段名
		   // ws.mergeCells(mr+8, 1, mr+8, 2); //字段名
		    
		    
		    ws.mergeCells(mr+8, rr, mr+10, rr); //字段名
		    ws.mergeCells(mr+11, rr, mr+18, rr); //字段名
		    ws.mergeCells(mr+19,rr, mr+19, rr+1); //字段名
		    
		    row = 0;
		    /****
		     * 获取未变化人员
		     */
		    mr = 0;
		    labelC = new Label(mr,row,"常州科教城“金凤凰高层次人才引进计划”生活资助和住房补贴申报表",wcfFCT);
	    	ws.addCell(labelC);
	    	
	    	++row;
	    	labelC = new Label(mr,row,"未  变  化  人  员",wcfFCT);
	    	ws.addCell(labelC);
	    	
	    	++row;
	    	labelC = new Label(mr,row,"序号",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 5);
	    	
	    	labelC = new Label(++mr,row,"申请人",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"性别",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 8);
	    	
	    	labelC = new Label(++mr,row,"年龄",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 8);
	    	
	    	labelC = new Label(++mr,row,"所在单位",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 28);
	    	
	    	labelC = new Label(++mr,row,"学历/学位",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"职称/职务",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	//labelC = new Label(++mr,row,"专业特长",wcfFC);
	    	//ws.addCell(labelC);
	    	//ws.setColumnView(mr, 18);
	    	
	    	labelC = new Label(++mr,row,"专/兼职",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"生活资助经费(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	
	    	++mr;++mr;++mr;
	    	labelC = new Label(mr,row,"住房补贴费(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	
	    	++mr;++mr;++mr;++mr;++mr;++mr;++mr;++mr;
	    	labelC = new Label(mr,row,"备注",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 19);
	    	
	    	
	    	
	    	++row;
	    	labelC = new Label(8,row,"资助标准(万元/月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(8, 10);
	    	
	    	labelC = new Label(9,row,"在常时间(月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(9, 10);
	    	
	    	
	    	labelC = new Label(10,row,"资助总额(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(10, 10);
	    	
	    	
	    	labelC = new Label(11,row,"住宿地(城内)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(11, 10);
	    	
	    	labelC = new Label(12,row,"补贴标准(万元/月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(12, 10);
	    	
	    	labelC = new Label(13,row,"在常时间(月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(13, 10);
	    	
	    	labelC = new Label(14,row,"资助总额(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(14, 10);
	    	

	    	labelC = new Label(15,row,"住宿地(城外)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(15, 10);
	    	
	    	labelC = new Label(16,row,"补贴标准(万元/月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(16, 10);
	    	
	    	labelC = new Label(17,row,"在常时间(月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(17, 10);
	    	
	    	labelC = new Label(18,row,"资助总额(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(18, 10);
	    	
	    	
	    	sql = "select c.DWMC,a.DWID,a.RYID,isnull(a.XM,' ') XM,a.SFZ,a.SEX_MC,a.BIRTHDAY,isnull(a.XL_MC,' ') XL_MC,isnull(a.ZC_MC,' ') ZC_MC," +
	    	"a.ZJZ,b.SBID,b.DWID as DC,convert(numeric(20,2),b.SHZZBZ) SHZZBZ, b.SHZCSJ, convert(numeric(20,2),b.SHZZZE) SHZZZE, " +
	    	"b.ZFZFD, convert(numeric(20,2),b.ZFBTBZ) ZFBTBZ, b.ZFZCSJ, convert(numeric(20,2),b.ZFBTZE) ZFBTZE," +
			"b.ZFZFD2, convert(numeric(20,2),b.ZFBTBZ2) ZFBTBZ2, b.ZFZCSJ2, convert(numeric(20,2),b.ZFBTZE2) ZFBTZE2, convert(numeric(20,2),b.ZE) ZE,b.SM " +
			" from dw_ryxx_v a,jfhsb_shzf_wh b,dw_info_v c" +
			" where a.ryid=b.ryid and a.dwid=b.dwid and a.dwid=c.dwid  and b.sbdm='"+dm[0].substring(0,4)+"0001'" +
			" and sbxx='001'" +//未变化人员
				" ";
			List<Map<String,Object>> list = this.getSimpleJdbcTemplate().queryForList(sql);
	    	for(int i=0;i<list.size();i++){
	    		++row; mr = 0;
	    		labelC = new Label(mr,row,String.valueOf(i+1),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("XM")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SEX_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	if(String.valueOf(list.get(i).get("BIRTHDAY"))==null || String.valueOf(list.get(i).get("BIRTHDAY")).equals("") || String.valueOf(list.get(i).get("BIRTHDAY")).equals("null")){
		    		labelC = new Label(++mr,row,"",wcfFCC);
			    	ws.addCell(labelC);
		    	}else{
		    		labelC = new Label(++mr,row,String.valueOf(this.getAge(String.valueOf(list.get(i).get("BIRTHDAY")))),wcfFCC);
			    	ws.addCell(labelC);
		    	}
		    	
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("DWMC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("XL_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("ZC_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("ZJZ")).equals("2")?"兼职":"专职",wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SHZZBZ")),wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SHZCSJ")),wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SHZZZE")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	String  ZFZFD = "";
		    	String  ZFBTBZ = "";
		    	String  ZFZCSJ = "";
		    	String  ZFBTZE = "";
		    	if(String.valueOf(list.get(i).get("ZFZFD")).equals("1")){
		    		ZFZFD = "是";
		    		ZFBTBZ = String.valueOf(list.get(i).get("ZFBTBZ"));
		    		ZFZCSJ = String.valueOf(list.get(i).get("ZFZCSJ"));
		    		ZFBTZE = String.valueOf(list.get(i).get("ZFBTZE"));
		    	}else{
		    		ZFZFD = "否";
		    		ZFBTBZ = "0";
		    		ZFZCSJ = "0";
		    		ZFBTZE = "0";	
		    	}
		    	
		    	labelC = new Label(++mr,row,ZFZFD,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,ZFBTBZ,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,ZFZCSJ,wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,ZFBTZE,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	if(String.valueOf(list.get(i).get("ZFZFD2")).equals("1")){
		    		ZFZFD = "是";
		    		ZFBTBZ = String.valueOf(list.get(i).get("ZFBTBZ2"));
		    		ZFZCSJ = String.valueOf(list.get(i).get("ZFZCSJ2"));
		    		ZFBTZE = String.valueOf(list.get(i).get("ZFBTZE2"));
		    	}else{
		    		ZFZFD = "否";
		    		ZFBTBZ = "0";
		    		ZFZCSJ = "0";
		    		ZFBTZE = "0";	
		    	}
		    	labelC = new Label(++mr,row,ZFZFD,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,ZFBTBZ,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,ZFZCSJ,wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,ZFBTZE,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SM")),wcfFCC);
		    	ws.addCell(labelC);
	    	}
	    	
	    	/***
	    	 * 获取新增人员
	    	 */
	    	++row;++row;++row;
	    	mr = 0;//列
		    rr = row;//行
		    ws.mergeCells(mr, rr, 19, rr);//标题
		    ++rr;
		    ws.mergeCells(mr, rr, 19, rr);//副标题
		    
		    ++rr;
		    ws.mergeCells(mr, rr, mr, rr+1); //字段名
		    ws.mergeCells(mr+1, rr, mr+1, rr+1); //字段名
		    ws.mergeCells(mr+2, rr, mr+2, rr+1); //字段名
		    ws.mergeCells(mr+3, rr, mr+3, rr+1); //字段名
		    ws.mergeCells(mr+4, rr, mr+4, rr+1); //字段名
		    ws.mergeCells(mr+5, rr, mr+5, rr+1); //字段名
		    ws.mergeCells(mr+6, rr, mr+6, rr+1); //字段名
		    ws.mergeCells(mr+7, rr, mr+7, rr+1); //字段名
		   // ws.mergeCells(mr+8, 1, mr+8, 2); //字段名
		    
		    
		    ws.mergeCells(mr+8, rr, mr+10, rr); //字段名
		    ws.mergeCells(mr+11, rr, mr+18, rr); //字段名
		    ws.mergeCells(mr+19,rr, mr+19, rr+1); //字段名
	    	
		    mr = 0;
	    	labelC = new Label(mr,row,"常州科教城“金凤凰高层次人才引进计划”生活资助和住房补贴申报表",wcfFCT);
	    	ws.addCell(labelC);
	    	
	    	++row;
	    	labelC = new Label(mr,row,"新    增    人    员",wcfFCT);
	    	ws.addCell(labelC);
	    	
	    	++row;
	    	labelC = new Label(mr,row,"序号",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 5);
	    	
	    	labelC = new Label(++mr,row,"申请人",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"性别",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 8);
	    	
	    	labelC = new Label(++mr,row,"年龄",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 8);
	    	
	    	labelC = new Label(++mr,row,"所在单位",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 28);
	    	
	    	labelC = new Label(++mr,row,"学历/学位",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"职称/职务",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	//labelC = new Label(++mr,row,"专业特长",wcfFC);
	    	//ws.addCell(labelC);
	    	//ws.setColumnView(mr, 18);
	    	
	    	labelC = new Label(++mr,row,"专/兼职",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"生活资助经费(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	
	    	++mr;++mr;++mr;
	    	labelC = new Label(mr,row,"住房补贴费(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	
	    	++mr;++mr;++mr;++mr;++mr;++mr;++mr;++mr;
	    	labelC = new Label(mr,row,"备注",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 19);
	    	
	    	
	    	
	    	++row;
	    	labelC = new Label(8,row,"资助标准(万元/月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(8, 10);
	    	
	    	labelC = new Label(9,row,"在常时间(月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(9, 10);
	    	
	    	
	    	labelC = new Label(10,row,"资助总额(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(10, 10);
	    	

	    	labelC = new Label(11,row,"住宿地(城内)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(11, 10);
	    	
	    	labelC = new Label(12,row,"补贴标准(万元/月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(12, 10);
	    	
	    	labelC = new Label(13,row,"在常时间(月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(13, 10);
	    	
	    	labelC = new Label(14,row,"资助总额(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(14, 10);
	    	

	    	labelC = new Label(15,row,"住宿地(城外)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(15, 10);
	    	
	    	labelC = new Label(16,row,"补贴标准(万元/月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(16, 10);
	    	
	    	labelC = new Label(17,row,"在常时间(月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(17, 10);
	    	
	    	labelC = new Label(18,row,"资助总额(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(18, 10);
	    	
	    	//String[] dm = sbdm.split("@");
	    	sql = "select c.DWMC,a.DWID,a.RYID,isnull(a.XM,' ') XM,a.SFZ,a.SEX_MC,a.BIRTHDAY,isnull(a.XL_MC,' ') XL_MC,isnull(a.ZC_MC,' ') ZC_MC," +
	    	"a.ZJZ,b.SBID,b.DWID as DC,convert(numeric(20,2),b.SHZZBZ) SHZZBZ, b.SHZCSJ,  convert(numeric(20,2),b.SHZZZE) SHZZZE," +
	    	" b.ZFZFD, convert(numeric(20,2),b.ZFBTBZ) ZFBTBZ, b.ZFZCSJ, convert(numeric(20,2),b.ZFBTZE) ZFBTZE," +
			"b.ZFZFD2, convert(numeric(20,2),b.ZFBTBZ2) ZFBTBZ2, b.ZFZCSJ2,convert(numeric(20,2),b.ZFBTZE2) ZFBTZE2, " +
			"convert(numeric(20,2),b.ZE) ZE,b.SM " +
			" from dw_ryxx_v a,jfhsb_shzf_wh b,dw_info_v c" +
			" where a.ryid=b.ryid and a.dwid=b.dwid and a.dwid=c.dwid and b.sbdm='"+dm[0].substring(0,4)+"0001'" +
			" and sbxx='002'" +//新增人员
				" ";
			list = this.getSimpleJdbcTemplate().queryForList(sql);
	    	for(int i=0;i<list.size();i++){
	    		++row; mr = 0;
	    		labelC = new Label(mr,row,String.valueOf(i+1),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("XM")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SEX_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	if(String.valueOf(list.get(i).get("BIRTHDAY"))==null || String.valueOf(list.get(i).get("BIRTHDAY")).equals("") || String.valueOf(list.get(i).get("BIRTHDAY")).equals("null")){
		    		labelC = new Label(++mr,row,"",wcfFCC);
			    	ws.addCell(labelC);
		    	}else{
		    		labelC = new Label(++mr,row,String.valueOf(this.getAge(String.valueOf(list.get(i).get("BIRTHDAY")))),wcfFCC);
			    	ws.addCell(labelC);
		    	}
		    	
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("DWMC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("XL_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("ZC_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("ZJZ")).equals("2")?"兼职":"专职",wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SHZZBZ")),wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SHZCSJ")),wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SHZZZE")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	String  ZFZFD = "";
		    	String  ZFBTBZ = "";
		    	String  ZFZCSJ = "";
		    	String  ZFBTZE = "";
		    	if(String.valueOf(list.get(i).get("ZFZFD")).equals("1")){
		    		ZFZFD = "是";
		    		ZFBTBZ = String.valueOf(list.get(i).get("ZFBTBZ"));
		    		ZFZCSJ = String.valueOf(list.get(i).get("ZFZCSJ"));
		    		ZFBTZE = String.valueOf(list.get(i).get("ZFBTZE"));
		    	}else{
		    		ZFZFD = "否";
		    		ZFBTBZ = "0";
		    		ZFZCSJ = "0";
		    		ZFBTZE = "0";	
		    	}
		    	

		    	labelC = new Label(++mr,row,ZFZFD,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,ZFBTBZ,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,ZFZCSJ,wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,ZFBTZE,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	if(String.valueOf(list.get(i).get("ZFZFD2")).equals("1")){
		    		ZFZFD = "是";
		    		ZFBTBZ = String.valueOf(list.get(i).get("ZFBTBZ2"));
		    		ZFZCSJ = String.valueOf(list.get(i).get("ZFZCSJ2"));
		    		ZFBTZE = String.valueOf(list.get(i).get("ZFBTZE2"));
		    	}else{
		    		ZFZFD = "否";
		    		ZFBTBZ = "0";
		    		ZFZCSJ = "0";
		    		ZFBTZE = "0";	
		    	}
		    	labelC = new Label(++mr,row,ZFZFD,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,ZFBTBZ,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,ZFZCSJ,wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,ZFBTZE,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SM")),wcfFCC);
		    	ws.addCell(labelC);
	    	}
	    	
	    	
	    	/***
	    	 * 领导及学术带头人
	    	 */
	    	++row;++row;++row;
	    	mr = 0;//列
		    rr = row;//行
		    ws.mergeCells(mr, rr, 11, rr);//标题
		   // ++rr;
		   // ws.mergeCells(mr, rr, 15, rr);//副标题
		    
		    ++rr;
		    ws.mergeCells(mr, rr, mr, rr+1); //字段名
		    ws.mergeCells(mr+1, rr, mr+1, rr+1); //字段名
		    ws.mergeCells(mr+2, rr, mr+2, rr+1); //字段名
		    ws.mergeCells(mr+3, rr, mr+3, rr+1); //字段名
		    ws.mergeCells(mr+4, rr, mr+4, rr+1); //字段名
		    ws.mergeCells(mr+5, rr, mr+5, rr+1); //字段名
		    ws.mergeCells(mr+6, rr, mr+6, rr+1); //字段名
		    ws.mergeCells(mr+7, rr, mr+7, rr+1); //字段名
		   // ws.mergeCells(mr+8, 1, mr+8, 2); //字段名
		    
		    
		    ws.mergeCells(mr+8, rr, mr+10, rr); //字段名
		    //ws.mergeCells(mr+11, rr, mr+14, rr); //字段名
		    ws.mergeCells(mr+11,rr, mr+11, rr+1); //字段名
	    	
		    mr = 0;
	    	labelC = new Label(mr,row,"常州科教城“金凤凰高层次人才引进计划”经费资助（奖励）——领导及学术带头人",wcfFCT);
	    	ws.addCell(labelC);
	    	
	    	//++row;
	    	//labelC = new Label(mr,row,"新    增    人    员",wcfFCT);
	    	//ws.addCell(labelC);
	    	
	    	++row;
	    	labelC = new Label(mr,row,"序号",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 5);
	    	
	    	labelC = new Label(++mr,row,"申请人",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"性别",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 8);
	    	
	    	labelC = new Label(++mr,row,"年龄",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 8);
	    	
	    	labelC = new Label(++mr,row,"所在单位",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 28);
	    	
	    	labelC = new Label(++mr,row,"学历/学位",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"职称/职务",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	//labelC = new Label(++mr,row,"专业特长",wcfFC);
	    	//ws.addCell(labelC);
	    	//ws.setColumnView(mr, 18);
	    	
	    	labelC = new Label(++mr,row,"专/兼职",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"生活资助经费(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	
	    	++mr;++mr;++mr;
	    	
	    	labelC = new Label(mr,row,"备注",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 15);
	    	
	    	
	    	
	    	++row;
	    	labelC = new Label(8,row,"资助标准(万元/月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(8, 10);
	    	
	    	labelC = new Label(9,row,"在常时间(月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(9, 10);
	    	
	    	
	    	labelC = new Label(10,row,"资助总额(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(10, 10);
	    	
	    	
	    	
	    	//String[] dm = sbdm.split("@");
	    	sql = "select c.DWMC,a.DWID,a.RYID,isnull(a.XM,' ') XM,a.SFZ,a.SEX_MC,a.BIRTHDAY,isnull(a.XL_MC,' ') XL_MC,isnull(a.ZC_MC,' ') ZC_MC," +
	    	"a.ZJZ,b.SBID,b.DWID as DC,convert(numeric(20,2),b.SHZZBZ) SHZZBZ, b.SHZCSJ, convert(numeric(20,2),b.SHZZZE) SHZZZE, b.ZFZFD, " +
	    	"convert(numeric(20,2),b.ZFBTBZ) ZFBTBZ, b.ZFZCSJ, convert(numeric(20,2),b.ZFBTZE) ZFBTZE," +
			"b.ZFZFD2,  convert(numeric(20,2),b.ZFBTBZ2) ZFBTBZ2, b.ZFZCSJ2, convert(numeric(20,2),b.ZFBTZE2) ZFBTZE2, convert(numeric(20,2),b.ZE) ZE,b.SM " +
			" from dw_ryxx_v a,jfhsb_shzf_wh b,dw_info_v c" +
			" where a.ryid=b.ryid and a.dwid=b.dwid and a.dwid=c.dwid  and b.sbdm='"+dm[0].substring(0,4)+"0001'" +
			" and sbxx='004'" +//领导及学术带头人
				" ";
			list = this.getSimpleJdbcTemplate().queryForList(sql);
	    	for(int i=0;i<list.size();i++){
	    		++row; mr = 0;
	    		labelC = new Label(mr,row,String.valueOf(i+1),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("XM")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SEX_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	if(String.valueOf(list.get(i).get("BIRTHDAY"))==null || String.valueOf(list.get(i).get("BIRTHDAY")).equals("") || String.valueOf(list.get(i).get("BIRTHDAY")).equals("null")){
		    		labelC = new Label(++mr,row,"",wcfFCC);
			    	ws.addCell(labelC);
		    	}else{
		    		labelC = new Label(++mr,row,String.valueOf(this.getAge(String.valueOf(list.get(i).get("BIRTHDAY")))),wcfFCC);
			    	ws.addCell(labelC);
		    	}
		    	
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("DWMC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("XL_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("ZC_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("ZJZ")).equals("2")?"兼职":"专职",wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SHZZBZ")),wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SHZCSJ")),wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SHZZZE")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	/***
		    	String  ZFZFD = "";
		    	String  ZFBTBZ = "";
		    	String  ZFZCSJ = "";
		    	String  ZFBTZE = "";
		    	if(String.valueOf(list.get(i).get("ZFZFD")).equals("1")){
		    		ZFZFD = "城内";
		    		ZFBTBZ = String.valueOf(list.get(i).get("ZFBTBZ"));
		    		ZFZCSJ = String.valueOf(list.get(i).get("ZFZCSJ"));
		    		ZFBTZE = String.valueOf(list.get(i).get("ZFBTZE"));
		    	}else{
		    		ZFZFD = "无";
		    		ZFBTBZ = "0";
		    		ZFZCSJ = "0";
		    		ZFBTZE = "0";	
		    	}
		    	
		    	if(String.valueOf(list.get(i).get("ZFZFD2")).equals("1")){
		    		ZFZFD += "  /  城外";
		    		ZFBTBZ += "  /  "+String.valueOf(list.get(i).get("ZFBTBZ2"));
		    		ZFZCSJ += "  /  "+String.valueOf(list.get(i).get("ZFZCSJ2"));
		    		ZFBTZE += "  /  "+String.valueOf(list.get(i).get("ZFBTZE2"));
		    	}else{
		    		if(ZFZFD.equals("城内")){
		    			ZFZFD = ZFZFD+" / 无";
			    		ZFBTBZ += " / 0";
			    		ZFZCSJ += " / 0";
			    		ZFBTZE += " / 0";					    			
		    		}
		    	}
		    	labelC = new Label(++mr,row,ZFZFD,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,ZFBTBZ,wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,ZFZCSJ,wcfFCC);
		    	ws.addCell(labelC);

		    	labelC = new Label(++mr,row,ZFBTZE,wcfFCC);
		    	ws.addCell(labelC);
		    	**/
		    	labelC = new Label(++mr,row,String.valueOf(list.get(i).get("SM")),wcfFCC);
		    	ws.addCell(labelC);
	    	}
	    	
	    	
	    	
	    	
	    	
	    	/***
	    	 * 总表
	    	 */
	    	++row;++row;++row;
	    	mr = 0;//列
		    rr = row;//行
		    ws.mergeCells(mr, rr, 19, rr);//标题
		   // ++rr;
		  //  ws.mergeCells(mr, rr, 15, rr);//副标题
		    
		    ++rr;
		    ws.mergeCells(mr, rr, mr, rr+1); //字段名
		    ws.mergeCells(mr+1, rr, mr+1, rr+1); //字段名
		    ws.mergeCells(mr+2, rr, mr+2, rr+1); //字段名
		    ws.mergeCells(mr+3, rr, mr+3, rr+1); //字段名
		    ws.mergeCells(mr+4, rr, mr+4, rr+1); //字段名
		    ws.mergeCells(mr+5, rr, mr+5, rr+1); //字段名
		    ws.mergeCells(mr+6, rr, mr+6, rr+1); //字段名
		    ws.mergeCells(mr+7, rr, mr+7, rr+1); //字段名
		   // ws.mergeCells(mr+8, 1, mr+8, 2); //字段名
		    
		    
		    ws.mergeCells(mr+8, rr, mr+10, rr); //字段名
		    ws.mergeCells(mr+11, rr, mr+18, rr); //字段名
		    ws.mergeCells(mr+19,rr, mr+19, rr+1); //字段名
	    	
		    mr = 0;
	    	labelC = new Label(mr,row,"常州科教城“金凤凰高层次人才引进计划”生活资助、住房补贴和安家费汇总表",wcfFCT);
	    	ws.addCell(labelC);
	    	
	    	
	    	++row;
	    	labelC = new Label(mr,row,"序号",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 5);
	    	
	    	labelC = new Label(++mr,row,"姓名",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"性别",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 8);
	    	
	    	labelC = new Label(++mr,row,"年龄",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 8);
	    	
	    	labelC = new Label(++mr,row,"所在单位",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 28);
	    	
	    	labelC = new Label(++mr,row,"学历/学位",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"职称/职务",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	//labelC = new Label(++mr,row,"专业特长",wcfFC);
	    	//ws.addCell(labelC);
	    	//ws.setColumnView(mr, 18);
	    	
	    	labelC = new Label(++mr,row,"专/兼职",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 10);
	    	
	    	labelC = new Label(++mr,row,"生活资助经费(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	
	    	++mr;++mr;++mr;
	    	labelC = new Label(mr,row,"住房补贴费(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	
	    	++mr;++mr;++mr;++mr;++mr;++mr;++mr;++mr;
	    	labelC = new Label(mr,row,"安家费(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(mr, 15);
	    	
	    	
	    	
	    	++row;
	    	labelC = new Label(8,row,"资助标准(万元/月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(8, 10);
	    	
	    	labelC = new Label(9,row,"在常时间(月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(9, 10);
	    	
	    	
	    	labelC = new Label(10,row,"资助总额(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(10, 10);
	    	

	    	labelC = new Label(11,row,"住宿地(城内)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(11, 10);
	    	
	    	labelC = new Label(12,row,"补贴标准(万元/月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(12, 10);
	    	
	    	labelC = new Label(13,row,"在常时间(月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(13, 10);
	    	
	    	labelC = new Label(14,row,"资助总额(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(14, 10);
	    	

	    	labelC = new Label(15,row,"住宿地(城外)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(15, 10);
	    	
	    	labelC = new Label(16,row,"补贴标准(万元/月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(16, 10);
	    	
	    	labelC = new Label(17,row,"在常时间(月)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(17, 10);
	    	
	    	labelC = new Label(18,row,"资助总额(万元)",wcfFC);
	    	ws.addCell(labelC);
	    	ws.setColumnView(18, 10);

			sql = "select c.DWMC,a.RYID,isnull(a.XM,' ') XM,a.SFZ,a.SEX_MC,a.BIRTHDAY,isnull(a.XL_MC,' ') XL_MC,isnull(a.ZC_MC,' ') ZC_MC,a.ZJZ" +
					" from dw_ryxx_v a,dw_info_v c " +
					" where a.dwid=c.dwid and (a.ryid in ( select ryid from jfhsb_ajf_wh where sbdm='"+dm[0].substring(0,4)+"0002' ) " +
					" or a.ryid in ( select ryid from jfhsb_shzf_wh where sbdm='"+dm[0].substring(0,4)+"0001' ))  ";
			List<Map<String,Object>> listry = this.getSimpleJdbcTemplate().queryForList(sql);
			
			
	    	//String[] dm = sbdm.split("@");
	    	sql = "select c.DWMC,a.DWID,a.RYID,isnull(a.XM,' ') XM,a.SFZ,a.SEX_MC,a.BIRTHDAY,isnull(a.XL_MC,' ') XL_MC,isnull(a.ZC_MC,' ') ZC_MC,a.ZJZ,b.SBID,b.DWID as DC,convert(numeric(20,2),b.SHZZBZ) SHZZBZ, " +
	    			"b.SHZCSJ, convert(numeric(20,2),b.SHZZZE) SHZZZE, b.ZFZFD, convert(numeric(20,2),b.ZFBTBZ) ZFBTBZ, b.ZFZCSJ, convert(numeric(20,2),b.ZFBTZE) ZFBTZE," +
			"b.ZFZFD2, convert(numeric(20,2),b.ZFBTBZ2) ZFBTBZ2, b.ZFZCSJ2, convert(numeric(20,2),b.ZFBTZE2) ZFBTZE2, convert(numeric(20,2),b.ZE) ZE " +
			" from dw_ryxx_v a,jfhsb_shzf_wh b,dw_info_v c" +
			" where a.ryid=b.ryid and a.dwid=b.dwid and a.dwid=c.dwid  and b.sbdm='"+dm[0].substring(0,4)+"0001'" +
			" " ;
	    	List<Map<String,Object>> listzz = this.getSimpleJdbcTemplate().queryForList(sql);
	    	
	    	
	    	//安家费
			sql = "select a.RYID,a.XM,a.SFZ,a.SEX_MC,isnull(a.XL_MC,' ') XL_MC,a.ZC_MC,a.ZJZ, convert(numeric(18,2),(isnull(ZZBZ,0))) ZZBZ " +
				" from dw_ryxx_v a , jfhsb_ajf_wh b,dw_info_v c " +
				" where a.ryid=b.ryid and a.dwid=b.dwid and a.dwid=c.dwid  and b.sbdm='"+dm[0].substring(0,4)+"0002'" +
				" " ;
		    List<Map<String,Object>> listajf =  this.getSimpleJdbcTemplate().queryForList(sql);
		    
	    	for(int i=0;i<listry.size();i++){
	    		++row; mr = 0;
	    		labelC = new Label(mr,row,String.valueOf(i+1),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listry.get(i).get("XM")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listry.get(i).get("SEX_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	if(String.valueOf(listry.get(i).get("BIRTHDAY"))==null || String.valueOf(listry.get(i).get("BIRTHDAY")).equals("") || String.valueOf(listry.get(i).get("BIRTHDAY")).equals("null")){
		    		labelC = new Label(++mr,row,"",wcfFCC);
			    	ws.addCell(labelC);
		    	}else{
		    		labelC = new Label(++mr,row,String.valueOf(this.getAge(String.valueOf(listry.get(i).get("BIRTHDAY")))),wcfFCC);
			    	ws.addCell(labelC);
		    	}
		    	
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listry.get(i).get("DWMC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listry.get(i).get("XL_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listry.get(i).get("ZC_MC")),wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	labelC = new Label(++mr,row,String.valueOf(listry.get(i).get("ZJZ")).equals("2")?"兼职":"专职",wcfFCC);
		    	ws.addCell(labelC);
		    	
		    	
		    	for(int j=0;j<listzz.size();j++){
		    		if(String.valueOf(listry.get(i).get("RYID")).equals(String.valueOf(listzz.get(j).get("RYID")))){

				    	labelC = new Label(++mr,row,String.valueOf(listzz.get(j).get("SHZZBZ")),wcfFCC);
				    	ws.addCell(labelC);

				    	labelC = new Label(++mr,row,String.valueOf(listzz.get(j).get("SHZCSJ")),wcfFCC);
				    	ws.addCell(labelC);

				    	labelC = new Label(++mr,row,String.valueOf(listzz.get(j).get("SHZZZE")),wcfFCC);
				    	ws.addCell(labelC);
				    	
				    	String  ZFZFD = "";
				    	String  ZFBTBZ = "";
				    	String  ZFZCSJ = "";
				    	String  ZFBTZE = "";
				    	if(String.valueOf(listzz.get(j).get("ZFZFD")).equals("1")){
				    		ZFZFD = "是";
				    		ZFBTBZ = String.valueOf(listzz.get(j).get("ZFBTBZ"));
				    		ZFZCSJ = String.valueOf(listzz.get(j).get("ZFZCSJ"));
				    		ZFBTZE = String.valueOf(listzz.get(j).get("ZFBTZE"));
				    	}else{
				    		ZFZFD = "否";
				    		ZFBTBZ = "0";
				    		ZFZCSJ = "0";
				    		ZFBTZE = "0";				    		
				    	}
				    	
				    	labelC = new Label(++mr,row,ZFZFD,wcfFCC);
				    	ws.addCell(labelC);
				    	
				    	labelC = new Label(++mr,row,ZFBTBZ,wcfFCC);
				    	ws.addCell(labelC);
				    	
				    	labelC = new Label(++mr,row,ZFZCSJ,wcfFCC);
				    	ws.addCell(labelC);

				    	labelC = new Label(++mr,row,ZFBTZE,wcfFCC);
				    	ws.addCell(labelC);
				    	
				    	if(String.valueOf(listzz.get(j).get("ZFZFD2")).equals("1")){
				    		ZFZFD = "是";
				    		ZFBTBZ = String.valueOf(listzz.get(j).get("ZFBTBZ2"));
				    		ZFZCSJ = String.valueOf(listzz.get(j).get("ZFZCSJ2"));
				    		ZFBTZE = String.valueOf(listzz.get(j).get("ZFBTZE2"));
				    	}else{
				    		ZFZFD = "否";
				    		ZFBTBZ = "0";
				    		ZFZCSJ = "0";
				    		ZFBTZE = "0";	
				    	}
				    	labelC = new Label(++mr,row,ZFZFD,wcfFCC);
				    	ws.addCell(labelC);
				    	
				    	labelC = new Label(++mr,row,ZFBTBZ,wcfFCC);
				    	ws.addCell(labelC);
				    	
				    	labelC = new Label(++mr,row,ZFZCSJ,wcfFCC);
				    	ws.addCell(labelC);

				    	labelC = new Label(++mr,row,ZFBTZE,wcfFCC);
				    	ws.addCell(labelC);
				    	
//				    	labelC = new Label(12,row,String.valueOf(listzz.get(j).get("ZZE")),wcfFCC);
//				    	ws.addCell(labelC);
				    	
				    	boolean hasAJF = false;
				    	for(int jj=0;jj<listajf.size();jj++){
				    		if(String.valueOf(listry.get(i).get("RYID")).equals(String.valueOf(listajf.get(jj).get("RYID")))){
				    			hasAJF = true;
						    	labelC = new Label(++mr,row,String.valueOf(listajf.get(jj).get("ZZBZ")),wcfFCC);
						    	ws.addCell(labelC);
						    	break;
						    	/**
						    	Float hj = Float.valueOf( String.valueOf( listajf.get(jj).get("ZZBZ") ) ) 
						    				+ Float.valueOf( String.valueOf( listzz.get(j).get("ZZE") ) ) ;						    	
						    	
						    	labelC = new Label(++mr,row,String.valueOf( hj ),wcfFCC);
						    	ws.addCell(labelC);			
						    	**/				    	
						    }
				    	}		    	
				    	if( !hasAJF ){
					    	labelC = new Label(++mr,row,"",wcfFCC);
					    	ws.addCell(labelC);
					    				    	
				    	}				    	
				    	
				    	
		    		}
		    	}
		    	
		    	
	    	}
	    	
	    	
	    	wwb.write();
	    	wwb.close();
		}catch(Exception e){
			throw  new BusException("导出出现异常!");
		}
		
	}
	/**
	 * 根据生日算年龄
	 * @param birthDay
	 * @return
	 * @throws Exception
	 */
	public static int getAge(String d) throws Exception {
		if(d != null && !d.equals("")){
			Date birthDay = null;
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			birthDay = format.parse(d);  
	        Calendar cal = Calendar.getInstance();
	        
	        if (cal.before(birthDay)) {
	            throw new IllegalArgumentException(
	                "The birthDay is before Now.It's unbelievable!");
	        }
	        
	        int yearNow = cal.get(Calendar.YEAR);
	        int monthNow = cal.get(Calendar.MONTH);
	        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
	        cal.setTime(birthDay);

	        int yearBirth = cal.get(Calendar.YEAR);
	        int monthBirth = cal.get(Calendar.MONTH);
	        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
	        int age = yearNow - yearBirth;
	        if (monthNow <= monthBirth) {
	            if (monthNow == monthBirth) {
	                if (dayOfMonthNow < dayOfMonthBirth) {
	                    age--;
	                } else {
	                }
	            } else {
	                age--;
	            }
	        } else {
	        }
	        return age;
	    
		}
		return 0;
	}
}
