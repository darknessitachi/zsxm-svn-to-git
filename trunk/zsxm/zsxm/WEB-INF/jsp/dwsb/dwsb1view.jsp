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
				
.tab2{width:100%; height:100%; text-align:center;}
.tab2 td{ height:28px; line-height:28px;font-size:12px; color:#006699;}
.tab2 td.tdleft{ width:80px; text-align:right;padding-right:10px;}
.tab2 td.tdright{ width:170px; text-align:left}
				
fieldset {
padding:2px;
margin-top:1px;
border:1px solid #1E7ACE;
background:#fff;
}
fieldset legend {
color:#1E7ACE;
font-weight:bold;
padding:3px 20px 3px 20px;
border:1px solid #1E7ACE;
background:#fff;
}
fieldset label {
float:left;
width:120px;
text-align:right;
padding:4px;
margin:1px;
}
</style>
	</head>

	<body  style="overflow:auto">
	<s:form name="zsdwForm" action='dwsb!doSaveDwsb.do' method="post" >
	
 <!--个人信息 start-->
 <div style="width:100%">
 

	<div class="tableContainer" id="tableContainer" style="height:90%">
    	<table class="fxtable"  cellpadding="0">
    		<tr>
    			<td colspan=4 style="text-align:left;background:#b0c4de;font:bold 12px 'lucida Grande',Verdana;"><font color="#8b4513">单位基本信息</font></td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">企业名称(盖章)</td>
				<td class="tdright">
					<s:if test="dwsb.qymc==''||dwsb.qymc==null">
						<input type="text" name="dwsb.qymc" style="width:150px" value="<s:property value="xtuser.cnname"/>"/>
					</s:if>
					<s:else>
						<s:textfield name="dwsb.qymc" cssStyle="width:150px"></s:textfield>
					</s:else>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">企业法人代码</td>
				<td>
					<s:textfield name="dwsb.qyfrdm" cssStyle="width:150px"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">注册资金(万元)</td>
				<td>
					<s:textfield name="dwsb.zczj" cssStyle="width:150px" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">注册时间</td>
				<td>
					<input type="text" class="Wdate" name="dwsb.zcsj" id="date" style="text-align:left;width:130" value="<s:property value="dwsb.zcsj"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>
				</td>
    		</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">负责人</td>
				<td>
					<s:textfield name="dwsb.fzr" cssStyle="width:150px"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">联系电话</td>
				<td>
					<s:textfield name="dwsb.fzrdh" cssStyle="width:150px"></s:textfield>
				</td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">联系人</td>
				<td>
					<s:textfield name="dwsb.lxr" cssStyle="width:150px"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">联系电话</td>
				<td>
					<s:textfield name="dwsb.lxrdh" cssStyle="width:150px"></s:textfield>
				</td>
				
			</tr>
			<tr>
				
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">传真</td>
				<td>
					<s:textfield name="dwsb.cz" cssStyle="width:150px"></s:textfield>
				</td>
				<td colspan=2>&nbsp</td>
			</tr>
			
			<tr>
    			<td colspan=4 style="text-align:left;background:#b0c4de;font:bold 12px 'lucida Grande',Verdana;"><font color="#8b4513">一、在孵企业基本概况（单位：个、万元）</font></td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px"><span class="red fn">*</span>企业登记注册类型</td>
				<td>
					<div id="qydjzclxdiv"></div>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">行业类别</td>
				<td>
					<s:textfield name="dwsb.hylb" cssStyle="width:150px"></s:textfield>
				</td>
    		</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">经认定高新技术企业</td>
				<td>
					<input type="radio" name="dwsb.gxjsqy" value="1" <s:if test="dwsb.gxjsqy==1">checked</s:if>/>是
					&nbsp&nbsp&nbsp&nbsp
					<input type="radio" name="dwsb.gxjsqy" value="2" <s:if test="dwsb.gxjsqy==2">checked</s:if>/>否
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">是否为上市企业</td>
				<td>
					<input type="radio" name="dwsb.sfssqy" value="1" <s:if test="dwsb.sfssqy==1">checked</s:if>/>是
					&nbsp&nbsp&nbsp&nbsp
					<input type="radio" name="dwsb.sfssqy" value="2" <s:if test="dwsb.sfssqy==2">checked</s:if>/>否
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">研发机构数</td>
				<td>
					<s:textfield name="dwsb.yfjgs" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">高新技术产品数</td>
				<td>
					<s:textfield name="dwsb.gxjscps" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		
			<tr>
    			<td colspan=4 style="text-align:left;background:#b0c4de;font:bold 12px 'lucida Grande',Verdana;"><font color="#8b4513">二、在孵企业经济效益（单位：个、万元、万美元）</font></td>
    		</tr>
    		
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">在孵企业总收入</td>
				<td>
					<s:textfield name="dwsb.zrqyzsr" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">在孵企业工业总产值</td>
				<td>
					<s:textfield name="dwsb.zrqygyzcz" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">在孵企业净利润</td>
				<td>
					<s:textfield name="dwsb.zrqyjlr" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">在孵企业上缴税金</td>
				<td>
					<s:textfield name="dwsb.zrqysjsj" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
    		</tr>
    			
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">在孵企业出口创汇</td>
				<td>
					<s:textfield name="dwsb.zrqyckch" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">在本市其他地区开票销售</td>
				<td>
					<s:textfield name="dwsb.zbsqtdqkpxs" cssStyle="width:150px" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
    		</tr>
    		
    		<tr>
    			
    			<td colspan=4 style="text-align:left;background:#b0c4de;font:bold 12px 'lucida Grande',Verdana;"><font color="#8b4513">三、在孵企业从业人员情况(单位：人)</font></td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">在孵企业从业人员数</td>
				<td colspan=3>
					<s:textfield name="dwsb.zrqyrs" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">其中：博士</td>
				<td>
					<s:textfield name="dwsb.bsrs" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">硕士</td>
				<td>
					<s:textfield name="dwsb.ssrs" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">其中：研究生学历及以上</td>
				<td>
					<s:textfield name="dwsb.yjsjysxls" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">本科学历</td>
				<td>
					<s:textfield name="dwsb.bkxls" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">大专学历</td>
				<td colspan=3>
					<s:textfield name="dwsb.dzxls" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">其中：高级职称</td>
				<td colspan=3>
					<s:textfield name="dwsb.gjzcs" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">其中：海归人才及外籍专家人员数</td>
				<td colspan=3>
					<s:textfield name="dwsb.hgjwjzjrs" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">其中：高级职称或博士学位人员数</td>
				<td colspan=3>
					<s:textfield name="dwsb.gjzchbsrs" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		
    		<tr>
    			
    			<td colspan=4 style="text-align:left;background:#b0c4de;font:bold 12px 'lucida Grande',Verdana;"><font color="#8b4513">四、在孵企业知识产权情况（单位：项）</font></td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px;">申请专利项数</td>
				<td colspan=3>
					<s:textfield name="dwsb.sqzls" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px;border-bottom: 2px solid #778899;">其中：发明专利</td>
				<td colspan=3 style="border-bottom: 2px solid #778899;">
					<s:textfield name="dwsb.fmzls" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">授权专利项数</td>
				<td colspan=3>
					<s:textfield name="dwsb.qzsqzls" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px;border-bottom: 2px solid #778899;">其中：发明专利</td>
				<td colspan=3 style="border-bottom: 2px solid #778899;">
					<s:textfield name="dwsb.qzfmzls" cssStyle="width:150px" onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">购买国外技术专利数</td>
				<td colspan=3>
					<s:textfield name="dwsb.gmgwjszls" cssStyle="width:150px" onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		
    		<tr>
    			
    			<td colspan=4 style="text-align:left;background:#b0c4de;font:bold 12px 'lucida Grande',Verdana;"><font color="#8b4513">五、在孵企业科技活动情况（单位：项、万元）</font></td>
    		</tr>
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">承担各类国家和地方项目数</td>
				<td colspan=3>
					<s:textfield name="dwsb.cdglgjdfxms" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">其中：国家级项目</td>
				<td>
					<s:textfield name="dwsb.gjjxms" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">省级项目</td>
				<td>
					<s:textfield name="dwsb.sjxms" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px;border-bottom: 2px solid #778899;">市级项目</td>
				<td style="border-bottom: 2px solid #778899;">
					<s:textfield name="dwsb.ssjxms" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px;border-bottom: 2px solid #778899;">区级项目</td>
				<td style="border-bottom: 2px solid #778899;">
					<s:textfield name="dwsb.qjxms" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
			</tr>
    		<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">各类国家和地方项目到帐经费</td>
				<td colspan=3>
					<s:textfield name="dwsb.glgjdfdzjf" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">其中：国家级项目</td>
				<td>
					<s:textfield name="dwsb.gjjxmjf" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">省级项目</td>
				<td>
					<s:textfield name="dwsb.sjxmjf" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px;border-bottom: 2px solid #778899;">市级项目</td>
				<td style="border-bottom: 2px solid #778899;">
					<s:textfield name="dwsb.ssjxmjf" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px;border-bottom: 2px solid #778899;">区级项目</td>
				<td style="border-bottom: 2px solid #778899;">
					<s:textfield name="dwsb.qjxmjf" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				
			</tr>
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px;border-bottom: 2px solid #778899;">承担各类企业项目数</td>
				<td  style="border-bottom: 2px solid #778899;">
					<s:textfield name="dwsb.cdglqyxms" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px;border-bottom: 2px solid #778899;">各类企业项目到帐经费</td>
				<td  style="border-bottom: 2px solid #778899;">
					<s:textfield name="dwsb.glqyxmdzjf" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				
				
			</tr>
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">科技活动经费支出总额</td>
				<td colspan=3>
					<s:textfield name="dwsb.kjhdjfzcze" cssStyle="width:150px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
			</tr>
			<tr>
				
					
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">其中：研究与试验发展支出</td>
				<td>
					<s:textfield name="dwsb.yjsyfzzc" cssStyle="width:150px" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
			
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:200px">其中：新产品开发经费支出</td>
				<td>
					<s:textfield name="dwsb.xcpkfjfzc" cssStyle="width:150px" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				
			</tr>
    		<!-- --------------------- -->
			
			
		</table>
	</div>
	</div>
	

	</s:form>
	</body>

	<script type="text/javascript">
		//$('tableContainer').style.height = "100%px"; 
		if(getSize().w > 960){
			$('tableContainer').style.width = 960+'px';
			$('grid_m_container').style.width = 955+'px';
			//$('butdiv').style.width = 960+'px';
		}
		var str = new StringBuffer("");
		str.append("<select name='dwsb.qydjzclx' id='dwsb.qydjzclx' style='width:150px'><option value=''>--请选择--</option>");
		<s:iterator value="lxs">
			if('<s:property value="bz"/>'=='0'){
				str.append('<optgroup label=\'<s:property value="mc"/>\'></optgroup> ');
			}else{
				str.append('<option value=\'<s:property value="dm"/>\'>&nbsp&nbsp&nbsp<s:property value="mc"/></option> ');
			}
		</s:iterator>
		str.append('</select>');
		$('qydjzclxdiv').innerHTML = str.toString();
		$('dwsb.qydjzclx').value = '<s:property value="dwsb.qydjzclx"/>';
		
		
		function doPrint(){
			
		}
	</script>
</html>

