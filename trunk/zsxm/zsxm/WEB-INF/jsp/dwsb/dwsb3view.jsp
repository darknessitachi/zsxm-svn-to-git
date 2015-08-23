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

	<body style="overflow:auto">
	<s:form name="zsdwForm" action='dwsb!doSaveDwsb.do' method="post" >
	
 <!--个人信息 start-->
 <div style="width:100%">
 

	<div class="tableContainer" id="tableContainer" style="height:90%">
    	<table class="fxtable"  cellpadding="0">
    		<tr>
    			<td colspan=4 style="text-align:left;background:#b0c4de;font:bold 12px 'lucida Grande',Verdana;"><font color="#8b4513">单位基本信息</font></td>
    		</tr>
    		
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">机构名称(盖章)</td>
				<td class="tdright">
					<s:if test="dwsb.qymc==''||dwsb.qymc==null">
						<input type="text" name="dwsb.qymc" style="width:150px" value="<s:property value="xtuser.cnname"/>"/>
					</s:if>
					<s:else>
						<s:textfield name="dwsb.qymc" cssStyle="width:150px"></s:textfield>
					</s:else>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">组织机构代码</td>
				<td>
					<s:textfield name="dwsb.zzjgdm" cssStyle="width:150px"></s:textfield>
				</td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">注册资金(万元)</td>
				<td>
					<s:textfield name="dwsb.zczj" cssStyle="width:150px" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">注册时间</td>
				<td>
					<input type="text" class="Wdate" name="dwsb.zcsj" id="date" style="text-align:left;width:130" value="<s:property value="dwsb.zcsj"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>
				</td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">负责人</td>
				<td>
					<s:textfield name="dwsb.fzr" cssStyle="width:150px"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">联系电话</td>
				<td>
					<s:textfield name="dwsb.fzrdh" cssStyle="width:150px"></s:textfield>
				</td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">联系人</td>
				<td>
					<s:textfield name="dwsb.lxr" cssStyle="width:150px"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">联系电话</td>
				<td>
					<s:textfield name="dwsb.lxrdh" cssStyle="width:150px"></s:textfield>
				</td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">传真</td>
				<td>
					<s:textfield name="dwsb.cz" cssStyle="width:150px"></s:textfield>
				</td>
				<td colspan=2>
				&nbsp
				</td>
			</tr>
			<tr>
    			<td colspan=4 style="text-align:left;background:#b0c4de;font:bold 12px 'lucida Grande',Verdana;"><font color="#8b4513">一、基本概况（单位：个、万元）</font></td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%"><span class="red fn">*</span>研发机构数</td>
				<td colspan=3>
					<s:textfield name="dwsb.yfjgs" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%;border-bottom: 2px solid #778899;">其中：具有独立法人资格</td>
				<td style="border-bottom: 2px solid #778899;">
					<s:textfield name="dwsb.jydlfrzg" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%;border-bottom: 2px solid #778899;">省级以上研发机构</td>
				<td style="border-bottom: 2px solid #778899;">
					<s:textfield name="dwsb.sjysyfjg" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">孵化企业数</td>
				<td colspan=3>
					<s:textfield name="dwsb.rhqys" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">其中：在孵企业</td>
				<td>
					<s:textfield name="dwsb.zrqy" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">规模以上企业</td>
				<td>
					<s:textfield name="dwsb.gmysqy" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%;border-bottom: 2px solid #778899;">其中：高新技术企业</td>
				<td style="border-bottom: 2px solid #778899;">
					<s:textfield name="dwsb.gxjsqy" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%;border-bottom: 2px solid #778899;">其中：上市企业</td>
				<td style="border-bottom: 2px solid #778899;">
					<s:textfield name="dwsb.ssqy" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">孵化企业高新技术产品数</td>
				<td>
					<s:textfield name="dwsb.rhqygxjscps" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				<td colspan=2>
				&nbsp
				</td>
    		</tr>
    		
    		<tr>
    			<td colspan=4 style="text-align:left;background:#b0c4de;font:bold 12px 'lucida Grande',Verdana;"><font color="#8b4513">二、孵化企业经济效益（单位：个、万元、万美元）</font></td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">总收入</td>
				<td>
					<s:textfield name="dwsb.zsr" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">总支出</td>
				<td>
					<s:textfield name="dwsb.zzc" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">结余(净利润)</td>
				<td>
					<s:textfield name="dwsb.jyjlr" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">上缴税金</td>
				<td>
					<s:textfield name="dwsb.sjsj" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">出口创汇</td>
				<td>
					<s:textfield name="dwsb.ckch" cssStyle="width:150px" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">在本市其他地区收入</td>
				<td>
					<s:textfield name="dwsb.zbsqtdqsr" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
    		</tr>
    		
    		<tr>
    			<td colspan=4 style="text-align:left;background:#b0c4de;font:bold 12px 'lucida Grande',Verdana;"><font color="#8b4513">三、人员情况（单位：人）</font></td>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">专任教师数</td>
				<td colspan=3>
					<s:textfield name="dwsb.zyjsrys" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">其中：博士</td>
				<td>
					<s:textfield name="dwsb.bs" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">硕士</td>
				<td>
					<s:textfield name="dwsb.ss" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">其中：研究生学历及以上</td>
				<td>
					<s:textfield name="dwsb.yjsxljys" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">本科学历</td>
				<td>
					<s:textfield name="dwsb.bkxl" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">大专学历</td>
				<td>
					<s:textfield name="dwsb.dzxl" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">其中：高级职称</td>
				<td>
					<s:textfield name="dwsb.gjzc" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">其中：海归人才及外籍专家人员数</td>
				<td>
					<s:textfield name="dwsb.hgrcjwjzjrys" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">其中：高级职称或博士学位人员数</td>
				<td>
					<s:textfield name="dwsb.gjzchbsxwrys" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		
    		<tr>
    			<td colspan=4 style="text-align:left;background:#b0c4de;font:bold 12px 'lucida Grande',Verdana;"><font color="#8b4513">四、知识产权情况（单位：项）</font></td>
    		<tr>
    		
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">申请专利项数</td>
				<td colspan=3>
					<s:textfield name="dwsb.sqzlxs" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%;border-bottom: 2px solid #778899;">其中：发明专利</td>
				<td colspan=3 style="border-bottom: 2px solid #778899;">
					<s:textfield name="dwsb.fmzl" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">授权专利项数</td>
				<td colspan=3>
					<s:textfield name="dwsb.ssqzlxs" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%;border-bottom: 2px solid #778899;">其中：发明专利</td>
				<td colspan=3 style="border-bottom: 2px solid #778899;">
					<s:textfield name="dwsb.sfmzl" cssStyle="width:150px" onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">购买国外技术专利数</td>
				<td colspan=3>
					<s:textfield name="dwsb.gmgwjszls" cssStyle="width:150px" onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		
    		<tr>
    			<td colspan=4 style="text-align:left;background:#b0c4de;font:bold 12px 'lucida Grande',Verdana;"><font color="#8b4513">五、科技活动情况（单位：项、万元）</font></td>
    		<tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">承担各类国家和地方项目数</td>
				<td colspan=3>
					<s:textfield name="dwsb.cdglgjhdfxms" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">其中：国家级项目</td>
				<td>
					<s:textfield name="dwsb.gjjxm" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">省级项目</td>
				<td>
					<s:textfield name="dwsb.sjxm" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%;border-bottom: 2px solid #778899;">市级项目</td>
				<td style="border-bottom: 2px solid #778899;">
					<s:textfield name="dwsb.ssjxm" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%;border-bottom: 2px solid #778899;">区级项目</td>
				<td style="border-bottom: 2px solid #778899;">
					<s:textfield name="dwsb.qjxm" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">各类国家和地方项目到帐经费</td>
				<td colspan=3>
					<s:textfield name="dwsb.glgjhdfxmdzjf" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">其中：国家级项目</td>
				<td>
					<s:textfield name="dwsb.sgjjxm" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">省级项目</td>
				<td>
					<s:textfield name="dwsb.sjxm2" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%;border-bottom: 2px solid #778899;">市级项目</td>
				<td style="border-bottom: 2px solid #778899;">
					<s:textfield name="dwsb.ssjxm2" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%;border-bottom: 2px solid #778899;">区级项目</td>
				<td style="border-bottom: 2px solid #778899;">
					<s:textfield name="dwsb.sqjxm" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%;border-bottom: 2px solid #778899;">承担各类企业项目数</td>
				<td style="border-bottom: 2px solid #778899;">
					<s:textfield name="dwsb.cdglqyxms" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%;border-bottom: 2px solid #778899;">各类企业项目到帐经费</td>
				<td style="border-bottom: 2px solid #778899;">
					<s:textfield name="dwsb.glqyxmdzjf" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%;">科技活动经费支出总额</td>
				<td colspan=3>
					<s:textfield name="dwsb.kjhdjfzcze" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">其中：研究与试验发展支出</td>
				<td>
					<s:textfield name="dwsb.yjysyfzzc" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
			
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:20%">其中：新产品开发经费支出</td>
				<td>
					<s:textfield name="dwsb.xcpkfjfzc" cssStyle="width:150px" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
    		</tr>
    		
			<!-- ----- -->
			
		</table>
	</div>
	</div>
	

	</s:form>
	</body>

	<script type="text/javascript">
		//$('tableContainer').style.height = (getSize().h - 130)+"px"; 
		if(getSize().w > 960){
			$('tableContainer').style.width = 960+'px';
			$('grid_m_container').style.width = 955+'px';
			//$('butdiv').style.width = 960+'px';
		}
		

	</script>
</html>

