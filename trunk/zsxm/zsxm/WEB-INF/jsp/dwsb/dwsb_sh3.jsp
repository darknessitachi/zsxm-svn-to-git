<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
        <script language="JavaScript"src="<s:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>		
        <script type="text/javascript" src="<c:url value="/js/czrc.js"/>" ></script>
<style>
			  .fxtable{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
			  .fxtable2{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
			  .fxtable2 td.cls {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;}
		      .fxtable td {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;}
		        a.button{background:url(images/button.gif);	display:block;color:#555555;font-weight:bold;height:30px;line-height:29px;margin-bottom:14px;text-decoration:none;width:191px;}
				a:hover.button{color:#0066CC;}
		      .lens{ no-repeat 10px 8px;text-indent:30px;display:block;}
		      .fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
		      .ddbox{background-color:#e5ecf9;border-bottom:1px solid #bacddc;padding:2px 5px 2px 5px;font-size:110%;height:25px}
		 		.t10 { 
				    BORDER-RIGHT: #ffff00 1px solid; PADDING-RIGHT: 5px; BORDER-TOP: #ffff00 1px solid; PADDING-LEFT: 5px; PADDING-BOTTOM: 5px; BORDER-LEFT: #ffff00 1px solid; COLOR: #ffff00; PADDING-TOP: 5px; BORDER-BOTTOM: #ffff00 1px solid; HEIGHT: 20px; BACKGROUND-COLOR:#2f4f4f 
				} 
				.t10:hover { 
				    BORDER-RIGHT: #0000ff 1px solid; PADDING-RIGHT: 5px; BORDER-TOP: #0000ff 1px solid; PADDING-LEFT: 5px; PADDING-BOTTOM: 5px; BORDER-LEFT: #0000ff 1px solid; COLOR: #333333; PADDING-TOP: 5px; BORDER-BOTTOM: #0000ff 1px solid; HEIGHT: 20px; BACKGROUND-COLOR: #c8d8f0 
				}
</style>
	</head>

	<body>
	<s:component template="xtitle" theme="app" value='数据审核'></s:component>
	<s:form name="zsdwForm" action='dwsb!doSaveDwsb.do' method="post" >
			<s:hidden name="dm"></s:hidden>
	<s:hidden name="dwids"></s:hidden>
	
	<div class="tableContainer" id="tableContainer" style="height:90%">
    	<table class="fxtable"  cellpadding="0">
    		<tr>
    			<td colspan=6 style="text-align:left;background:#b0c4de;"><font color="#8b4513"><b>单位基本信息</b></font></td>
    		</tr>
    		
			<tr>
				<td style="text-align:left;background:#E7F2FC;width:200px">机构名称(盖章)</td>
				<td colspan=2 class="tdright">
					<s:textfield name="dwsb.qymc" cssStyle="width:150px;background:#efefef" />
				</td>
				<td style="text-align:left;background:#E7F2FC;width:200px">组织机构代码</td>
				<td colspan=2>
					<s:textfield name="dwsb.zzjgdm" cssStyle="width:150px;background:#efefef" />
				</td>
				
			</tr>
			<tr>
				<td style="text-align:left;background:#E7F2FC;width:200px">注册资金(万元)</td>
				<td colspan=2>
					<s:textfield name="dwsb.zczj" value="%{dwsb.zczj==''?'0':dwsb.zczj}" cssStyle="width:150px" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				<td style="text-align:left;background:#E7F2FC;width:200px">注册时间</td>
				<td colspan=2>
					<input type="text" class="Wdate" name="dwsb.zcsj" id="dwsb.zcsj" style="text-align:left;width:130" value="<s:property value="dwsb.zcsj"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>
				</td>
				
			</tr>
			<tr>
				<td style="text-align:left;background:#E7F2FC;width:200px">负责人</td>
				<td colspan=2>
					<s:textfield name="dwsb.fzr" id="dwsb.fzr" cssStyle="width:150px"></s:textfield>
				</td>
				<td style="text-align:left;background:#E7F2FC;width:200px">联系电话</td>
				<td colspan=2>
					<s:textfield name="dwsb.fzrdh" id="dwsb.fzrdh" cssStyle="width:150px"></s:textfield>
				</td>
				
			</tr>
			<tr>
				<td style="text-align:left;background:#E7F2FC;width:200px">联系人</td>
				<td colspan=2>
					<s:textfield name="dwsb.lxr" id="dwsb.lxr" cssStyle="width:150px"></s:textfield>
				</td>
				<td style="text-align:left;background:#E7F2FC;width:200px">联系电话</td>
				<td colspan=2>
					<s:textfield name="dwsb.lxrdh" id="dwsb.lxrdh" cssStyle="width:150px"></s:textfield>
				</td>
				
			</tr>
			<tr>
				<td style="text-align:left;background:#E7F2FC;width:200px">传真</td>
				<td colspan=2>
					<s:textfield name="dwsb.cz" id="dwsb.cz" cssStyle="width:150px"></s:textfield>
				</td>
				<td colspan=3>
				&nbsp
				</td>
			</tr>
			<tr>
    			<td colspan=6 style="text-align:left;background:#b0c4de;"><font color="#8b4513"><b>一、基本概况（单位：个、万元）</b></font></td>
    		</tr>
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px">研发机构数</td>
				<td style="width:100px;background:#E7F2FC">TDF8J101</td>
				<td>
					<s:textfield name="dwsb.yfjgs" id="TDF8J101" value="%{dwsb.yfjgs==''?'0':dwsb.yfjgs}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				
				<td style="text-align:left;background:#E7F2FC;width:200px">孵化企业数</td>
				<td style="width:100px;background:#E7F2FC">TDF8J104</td>
				<td>
					<s:textfield name="dwsb.rhqys" id="TDF8J104" value="%{dwsb.rhqys==''?'0':dwsb.rhqys}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px;">&nbsp&nbsp&nbsp&nbsp&nbsp其中：具有独立法人资格</td>
				<td style="width:100px;background:#E7F2FC">TDF8J102</td>
				<td style="">
					<s:textfield name="dwsb.jydlfrzg" id="TDF8J102" value="%{dwsb.jydlfrzg==''?'0':dwsb.jydlfrzg}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				
				<td style="text-align:left;background:#E7F2FC;width:200px">&nbsp&nbsp&nbsp&nbsp&nbsp其中：在孵企业</td>
				<td style="width:100px;background:#E7F2FC">TDF8J105</td>
				<td>
					<s:textfield name="dwsb.zrqy" id="TDF8J105" value="%{dwsb.zrqy==''?'0':dwsb.zrqy}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				
    		</tr>
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px;">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp省级以上研发机构</td>
				<td style="width:100px;background:#E7F2FC">TDF8J103</td>
				<td style="">
					<s:textfield name="dwsb.sjysyfjg" id="TDF8J103" value="%{dwsb.sjysyfjg==''?'0':dwsb.sjysyfjg}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				
				<td style="text-align:left;background:#E7F2FC;width:200px">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp规模以上企业</td>
				<td style="width:100px;background:#E7F2FC">TDF8J106</td>
				<td>
					<s:textfield name="dwsb.gmysqy" id="TDF8J106" value="%{dwsb.gmysqy==''?'0':dwsb.gmysqy}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td colspan=3>
				</td>
    			<td style="text-align:left;background:#E7F2FC;width:200px;">&nbsp&nbsp&nbsp&nbsp&nbsp其中：高新技术企业</td>
				<td style="width:100px;background:#E7F2FC">TDF8J107</td>
				<td style="">
					<s:textfield name="dwsb.gxjsqy" id="TDF8J107" value="%{dwsb.gxjsqy==''?'0':dwsb.gxjsqy}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px">孵化企业高新技术产品数</td>
				<td style="width:100px;background:#E7F2FC">TDF8J109</td>
				<td>
					<s:textfield name="dwsb.rhqygxjscps" id="TDF8J109" value="%{dwsb.rhqygxjscps==''?'0':dwsb.rhqygxjscps}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				<td style="text-align:left;background:#E7F2FC;width:200px;">&nbsp&nbsp&nbsp&nbsp&nbsp其中：上市企业</td>
				<td style="width:100px;background:#E7F2FC">TDF8J108</td>
				<td style="">
					<s:textfield name="dwsb.ssqy" id="TDF8J108" value="%{dwsb.ssqy==''?'0':dwsb.ssqy}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		
    		<tr>
    			<td colspan=6 style="text-align:left;background:#b0c4de;"><font color="#8b4513"><b>二、孵化企业经济效益（单位：个、万元、万美元）</b></font></td>
    		</tr>
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px">总收入</td>
				<td style="width:100px;background:#E7F2FC">TDF8G210</td>
				<td>
					<s:textfield name="dwsb.zsr" id="TDF8G210" value="%{dwsb.zsr==''?'0':dwsb.zsr}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				<td style="text-align:left;background:#E7F2FC;width:200px">总支出</td>
				<td style="width:100px;background:#E7F2FC">TDF8G220</td>
				<td>
					<s:textfield name="dwsb.zzc" id="TDF8G220" value="%{dwsb.zzc==''?'0':dwsb.zzc}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px">结余(净利润)</td>
				<td style="width:100px;background:#E7F2FC">TDF8G230</td>
				<td>
					<s:textfield name="dwsb.jyjlr" id="TDF8G230" value="%{dwsb.jyjlr==''?'0':dwsb.jyjlr}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				<td style="text-align:left;background:#E7F2FC;width:200px">上缴税金</td>
				<td style="width:100px;background:#E7F2FC">TDF8G240</td>
				<td>
					<s:textfield name="dwsb.sjsj" id="TDF8G240" value="%{dwsb.sjsj==''?'0':dwsb.sjsj}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px">出口创汇</td>
				<td style="width:100px;background:#E7F2FC">TDF8G250</td>
				<td>
					<s:textfield name="dwsb.ckch" id="TDF8G250" value="%{dwsb.ckch==''?'0':dwsb.ckch}" cssStyle="width:100px;" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				<td style="text-align:left;background:#E7F2FC;width:200px">在本市其他地区收入</td>
				<td style="width:100px;background:#E7F2FC">TDF8G260</td>
				<td>
					<s:textfield name="dwsb.zbsqtdqsr" id="TDF8G260" value="%{dwsb.zbsqtdqsr==''?'0':dwsb.zbsqtdqsr}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
    		</tr>
    		
    		<tr>
    			<td colspan=6 style="text-align:left;background:#b0c4de;"><font color="#8b4513"><b>三、人员情况（单位：人）</b></font></td>
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px">专任教师数</td>
				<td style="width:100px;background:#E7F2FC">TDF8H300</td>
				<td colspan=4>
					<s:textfield name="dwsb.zyjsrys" id="TDF8H300" value="%{dwsb.zyjsrys==''?'0':dwsb.zyjsrys}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px">&nbsp&nbsp&nbsp&nbsp&nbsp其中：博士</td>
				<td style="width:100px;background:#E7F2FC">TDF8H311</td>
				<td>
					<s:textfield name="dwsb.bs" id="TDF8H311" value="%{dwsb.bs==''?'0':dwsb.bs}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				
				<td style="text-align:left;background:#E7F2FC;width:200px">&nbsp&nbsp&nbsp&nbsp&nbsp其中：研究生学历及以上</td>
				<td style="width:100px;background:#E7F2FC">TDF8H313</td>
				<td>
					<s:textfield name="dwsb.yjsxljys" id="TDF8H313" value="%{dwsb.yjsxljys==''?'0':dwsb.yjsxljys}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				
    		</tr>
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp硕士</td>
				<td style="width:100px;background:#E7F2FC">TDF8H312</td>
				<td>
					<s:textfield name="dwsb.ss" id="TDF8H312" value="%{dwsb.ss==''?'0':dwsb.ss}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				
				<td style="text-align:left;background:#E7F2FC;width:200px">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp本科学历</td>
				<td style="width:100px;background:#E7F2FC">TDF8H314</td>
				<td>
					<s:textfield name="dwsb.bkxl" id="TDF8H314" value="%{dwsb.bkxl==''?'0':dwsb.bkxl}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    		
    			<td colspan=3>
    			</td>
    			
    			<td style="text-align:left;background:#E7F2FC;width:200px">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp大专学历</td>
				<td style="width:100px;background:#E7F2FC">TDF8H315</td>
				<td>
					<s:textfield name="dwsb.dzxl" id="TDF8H315" value="%{dwsb.dzxl==''?'0':dwsb.dzxl}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				
    		</tr>
    		
    		<tr>
    			<td colspan=3>
    			</td>
    			<td style="text-align:left;background:#E7F2FC;width:200px">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp中专学历</td>
				<td style="width:100px;background:#E7F2FC">TDF8H316</td>
				<td>
					<s:textfield name="dwsb.zzxls" id="TDF8H316"  value="%{dwsb.zzxls==''?'0':dwsb.zzxls}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		
    		
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px">其中：高级职称</td>
				<td style="width:100px;background:#E7F2FC">TDF8H317</td>
				<td colspan=4>
					<s:textfield name="dwsb.gjzc" id="TDF8H317" value="%{dwsb.gjzc==''?'0':dwsb.gjzc}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px">其中：海归人才及外籍专家人员数</td>
				<td style="width:100px;background:#E7F2FC">TDF8H318</td>
				<td>
					<s:textfield name="dwsb.hgrcjwjzjrys" id="TDF8H318" value="%{dwsb.hgrcjwjzjrys==''?'0':dwsb.hgrcjwjzjrys}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				<td style="text-align:left;background:#E7F2FC;width:200px">其中：高级职称或博士学位人员数</td>
				<td style="width:100px;background:#E7F2FC">TDF8H319</td>
				<td>
					<s:textfield name="dwsb.gjzchbsxwrys" id="TDF8H319" value="%{dwsb.gjzchbsxwrys==''?'0':dwsb.gjzchbsxwrys}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px">接纳大学生、研究生实习人数</td>
				<td style="width:100px;background:#E7F2FC">TDF8H320</td>
				<td>
					<s:textfield name="dwsb.jndxyjsxrs" id="TDF8H320"  value="%{dwsb.jndxyjsxrs==''?'0':dwsb.jndxyjsxrs}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				<td style="text-align:left;background:#E7F2FC;width:200px">接纳应届毕业生就业人数</td>
				<td style="width:100px;background:#E7F2FC">TDF8H321</td>
				<td>
					<s:textfield name="dwsb.jnyjbysrs" id="TDF8H321"  value="%{dwsb.jnyjbysrs==''?'0':dwsb.jnyjbysrs}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px">科技活动人员数</td>
				<td style="width:100px;background:#E7F2FC">TDF8H322</td>
				<td colspan=4>
					<s:textfield name="dwsb.kjhdrys" id="TDF8H322"  value="%{dwsb.kjhdrys==''?'0':dwsb.kjhdrys}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px">&nbsp&nbsp&nbsp&nbsp&nbsp其中：研究与发展人员数</td>
				<td style="width:100px;background:#E7F2FC">TDF8H323</td>
				<td colspan=4>
					<s:textfield name="dwsb.qzyjfzrys" id="TDF8H323"  value="%{dwsb.qzyjfzrys==''?'0':dwsb.qzyjfzrys}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		
    		
    		<tr>
    			<td colspan=6 style="text-align:left;background:#b0c4de;"><font color="#8b4513"><b>四、知识产权情况（单位：项）</b></font></td>
    		<tr>
    		
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px">申请专利项数</td>
				<td style="width:100px;background:#E7F2FC">TDF8F400</td>
				<td>
					<s:textfield name="dwsb.sqzlxs" id="TDF8F400" value="%{dwsb.sqzlxs==''?'0':dwsb.sqzlxs}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				
				<td style="text-align:left;background:#E7F2FC;width:200px">授权专利项数</td>
				<td style="width:100px;background:#E7F2FC">TDF8F420</td>
				<td>
					<s:textfield name="dwsb.ssqzlxs" id="TDF8F420" value="%{dwsb.ssqzlxs==''?'0':dwsb.ssqzlxs}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px;">&nbsp&nbsp&nbsp&nbsp&nbsp其中：发明专利</td>
				<td style="width:100px;background:#E7F2FC">TDF8F410</td>
				<td style="">
					<s:textfield name="dwsb.fmzl" id="TDF8F410" value="%{dwsb.fmzl==''?'0':dwsb.fmzl}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				
				<td style="text-align:left;background:#E7F2FC;width:200px;">&nbsp&nbsp&nbsp&nbsp&nbsp其中：发明专利</td>
				<td style="width:100px;background:#E7F2FC">TDF8F430</td>
				<td style="">
					<s:textfield name="dwsb.sfmzl" id="TDF8F430" value="%{dwsb.sfmzl==''?'0':dwsb.sfmzl}" cssStyle="width:100px" onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px">购买国外技术专利数</td>
				<td style="width:100px;background:#E7F2FC">TDF8F440</td>
				<td colspan=4>
					<s:textfield name="dwsb.gmgwjszls" id="TDF8F440" value="%{dwsb.gmgwjszls==''?'0':dwsb.gmgwjszls}" cssStyle="width:100px" onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		
    		<tr>
    			<td colspan=6 style="text-align:left;background:#b0c4de;"><font color="#8b4513"><b>五、科技活动情况（单位：项、万元）</b></font></td>
    		<tr>
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px">承担各类国家和地方项目数</td>
				<td style="width:100px;background:#E7F2FC">TDF8k600</td>
				<td>
					<s:textfield name="dwsb.cdglgjhdfxms" id="TDF8k600" value="%{dwsb.cdglgjhdfxms==''?'0':dwsb.cdglgjhdfxms}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				
				<td style="text-align:left;background:#E7F2FC;width:200px">各类国家和地方项目到帐经费</td>
				<td style="width:100px;background:#E7F2FC">TDF8k605</td>
				<td>
					<s:textfield name="dwsb.glgjhdfxmdzjf" id="TDF8k605" value="%{dwsb.glgjhdfxmdzjf==''?'0':dwsb.glgjhdfxmdzjf}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px">&nbsp&nbsp&nbsp&nbsp&nbsp其中：国家级项目</td>
				<td style="width:100px;background:#E7F2FC">TDF8k601</td>
				<td>
					<s:textfield name="dwsb.gjjxm" id="TDF8k601" value="%{dwsb.gjjxm==''?'0':dwsb.gjjxm}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				<td style="text-align:left;background:#E7F2FC;width:200px">&nbsp&nbsp&nbsp&nbsp&nbsp其中：国家级项目</td>
				<td style="width:100px;background:#E7F2FC">TDF8k606</td>
				<td>
					<s:textfield name="dwsb.sgjjxm" id="TDF8k606" value="%{dwsb.sgjjxm==''?'0':dwsb.sgjjxm}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				
    		</tr>
    		
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp省级项目</td>
				<td style="width:100px;background:#E7F2FC">TDF8k602</td>
				<td>
					<s:textfield name="dwsb.sjxm" id="TDF8k602" value="%{dwsb.sjxm==''?'0':dwsb.sjxm}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				
				<td style="text-align:left;background:#E7F2FC;width:200px">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp省级项目</td>
				<td style="width:100px;background:#E7F2FC">TDF8k607</td>
				<td>
					<s:textfield name="dwsb.sjxm2" id="TDF8k607" value="%{dwsb.sjxm2==''?'0':dwsb.sjxm2}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				
    		</tr>
    		
    		<tr>
    			
				<td style="text-align:left;background:#E7F2FC;width:200px;">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp市级项目</td>
				<td style="width:100px;background:#E7F2FC">TDF8k603</td>
				<td style="">
					<s:textfield name="dwsb.ssjxm" id="TDF8k603" value="%{dwsb.ssjxm==''?'0':dwsb.ssjxm}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				<td style="text-align:left;background:#E7F2FC;width:200px;">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp市级项目</td>
				<td style="width:100px;background:#E7F2FC">TDF8k608</td>
				<td style="">
					<s:textfield name="dwsb.ssjxm2" id="TDF8k608" value="%{dwsb.ssjxm2==''?'0':dwsb.ssjxm2}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				
    		</tr>
    		
    		
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px;">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp区级项目</td>
				<td style="width:100px;background:#E7F2FC">TDF8k604</td>
				<td style="">
					<s:textfield name="dwsb.qjxm" id="TDF8k604" value="%{dwsb.qjxm==''?'0':dwsb.qjxm}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				<td style="text-align:left;background:#E7F2FC;width:200px;">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp区级项目</td>
				<td style="width:100px;background:#E7F2FC">TDF8k609</td>
				<td style="">
					<s:textfield name="dwsb.sqjxm" id="TDF8k609" value="%{dwsb.sqjxm==''?'0':dwsb.sqjxm}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px;">承担各类企业项目数</td>
				<td style="width:100px;background:#E7F2FC">TDF8k610</td>
				<td style="">
					<s:textfield name="dwsb.cdglqyxms" id="TDF8k610" value="%{dwsb.cdglqyxms==''?'0':dwsb.cdglqyxms}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				<td style="text-align:left;background:#E7F2FC;width:200px;">各类企业项目到帐经费</td>
				<td style="width:100px;background:#E7F2FC">TDF8k611</td>
				<td style="">
					<s:textfield name="dwsb.glqyxmdzjf" id="TDF8k611" value="%{dwsb.glqyxmdzjf==''?'0':dwsb.glqyxmdzjf}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px;">科技活动经费支出总额</td>
				<td style="width:100px;background:#E7F2FC">TDF8k620</td>
				<td colspan=4>
					<s:textfield name="dwsb.kjhdjfzcze" id="TDF8k620" value="%{dwsb.kjhdjfzcze==''?'0':dwsb.kjhdjfzcze}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px">&nbsp&nbsp&nbsp&nbsp&nbsp其中：研究与试验发展支出</td>
				<td style="width:100px;background:#E7F2FC">TDF8k630</td>
				<td colspan=4>
					<s:textfield name="dwsb.yjysyfzzc" id="TDF8k630" value="%{dwsb.yjysyfzzc==''?'0':dwsb.yjysyfzzc}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
			</tr>
			<tr>
				<td style="text-align:left;background:#E7F2FC;width:200px">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp新产品开发经费支出</td>
				<td style="width:100px;background:#E7F2FC">TDF8k640</td>
				<td colspan=4>
					<s:textfield name="dwsb.xcpkfjfzc" id="TDF8k640" value="%{dwsb.xcpkfjfzc==''?'0':dwsb.xcpkfjfzc}" cssStyle="width:100px" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
    		</tr>
    		
			<!-- ----- -->
			
		</table>
	</div>

	<div class="footer" style="background:#f5f5f5">
		<input class="button3" type="button" value="审核通过" onclick="doSave(0)"/>
		<input class="button3" type="button" value="不通过" onclick="doSave(1)"/>
		回退原因:<input type="text" name="thyy" value=""/>
	</div>

	</s:form>
	</body>

	<script type="text/javascript">
		$('tableContainer').style.height = (getSize().h - 70)+"px"; 
		

		function doSave(type){
			if(type==1){
				if($('thyy').value==''){
					alert('请填写回退原因!');
					return false;
				}
			}
			var ajax = new AppAjax("dwsb!doDataSh.do?opttype="+type,function(data){save_Back(data,type)}).submitForm("zsdwForm");
		}
		
		function save_Back(data,type){
			if(data.message.code >0){
			
				if(type == 1){
					alert('数据已退回');
				}else{
					alert('数据已审核通过');
				}
				getOpener().queryDw();
				closeWin(self.name);
			}else{
				alert(data.message.text);
			}
		}
		

	</script>
</html>

