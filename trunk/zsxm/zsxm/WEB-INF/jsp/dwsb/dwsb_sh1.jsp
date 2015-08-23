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
 <!--个人信息 start-->
 <div style="width:100%">
 
	<div class="tableContainer" id="tableContainer" style="height:90%">
    	<table class="fxtable"  cellpadding="0">
    		<tr>
    			<td colspan=6 style="text-align:left;background:#b0c4de;"><font color="#8b4513"><b>单位基本信息</b></font></td>
    		</tr>
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px">企业名称(盖章)</td>
				<td colspan=2 class="tdright">
					<s:textfield   name="dwsb.qymc" cssStyle="width:150px;background:#efefef" />
				</td>
				<td style="text-align:left;background:#E7F2FC;width:200px">组织机构代码</td>
				<td colspan=2 >
					<s:textfield   name="dwsb.qyfrdm" cssStyle="width:150px;background:#efefef" />
				</td>
    		</tr>
    		<tr>
    			<td  style="text-align:left;background:#E7F2FC;width:200px">注册资金(万元)</td>
				<td colspan=2>
					<s:textfield name="dwsb.zczj" value="%{dwsb.zczj==''?'0':dwsb.zczj}" cssStyle="width:150px" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				<td  style="text-align:left;background:#E7F2FC;width:200px">注册时间</td>
				<td colspan=2>
					<input type="text" class="Wdate" name="dwsb.zcsj" id="dwsb.zcsj" style="text-align:left;width:130" value="<s:property value="dwsb.zcsj"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>
				</td>
    		</tr>
			<tr>
				<td  style="text-align:left;background:#E7F2FC;width:200px">负责人</td>
				<td colspan=2>
					<s:textfield name="dwsb.fzr" id="dwsb.fzr" cssStyle="width:150px"></s:textfield>
				</td>
				<td style="text-align:left;background:#E7F2FC;width:200px">联系电话</td>
				<td colspan=2 >
					<s:textfield name="dwsb.fzrdh" id="dwsb.fzrdh" cssStyle="width:150px"></s:textfield>
				</td>
				
			</tr>
			<tr>
				<td  style="text-align:left;background:#E7F2FC;width:200px">联系人</td>
				<td colspan=2>
					<s:textfield name="dwsb.lxr" id="dwsb.lxr" cssStyle="width:150px"></s:textfield>
				</td>
				<td  style="text-align:left;background:#E7F2FC;width:200px">联系电话</td>
				<td colspan=2>
					<s:textfield name="dwsb.lxrdh" id="dwsb.lxrdh" cssStyle="width:150px"></s:textfield>
				</td>
				
			</tr>
			<tr>
				
				<td  style="text-align:left;background:#E7F2FC;width:200px">传真</td>
				<td colspan=2>
					<s:textfield name="dwsb.cz" id="dwsb.cz" cssStyle="width:150px"></s:textfield>
				</td>
				<td colspan=4>&nbsp</td>
			</tr>
			
			<tr>
    			<td colspan=6 style="text-align:left;background:#b0c4de;"><font color="#8b4513"><b>一、在孵企业基本概况（单位：个、万元）</b></font></td>
    		</tr>
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px">企业登记注册类型</td>
    			<td style="width:100px;background:#E7F2FC">TDF8J100</td>
				<td>
					<div id="qydjzclxdiv"></div>
				</td>
				<td style="text-align:left;background:#E7F2FC;width:200px">行业类别</td>
				<td style="width:100px;background:#E7F2FC">TDF8J101</td>
				<td>
					<s:textfield name="dwsb.hylb" cssStyle="width:100px"></s:textfield>
				</td>
    		</tr>
			<tr>
				<td style="text-align:left;background:#E7F2FC;width:200px">经认定高新技术企业</td>
				<td style="width:100px;background:#E7F2FC">TDF8J102</td>
				<td>
					<input type="radio" name="dwsb.gxjsqy" value="1" <s:if test="dwsb.gxjsqy==1">checked</s:if>/>是
					&nbsp&nbsp&nbsp&nbsp
					<input type="radio" name="dwsb.gxjsqy" value="2" <s:if test="dwsb.gxjsqy==2">checked</s:if>/>否
				</td>
				<td style="text-align:left;background:#E7F2FC;width:200px">是否为上市企业</td>
				<td style="width:100px;background:#E7F2FC">TDF8J103</td>
				<td>
					<input type="radio" name="dwsb.sfssqy" value="1" <s:if test="dwsb.sfssqy==1">checked</s:if>/>是
					&nbsp&nbsp&nbsp&nbsp
					<input type="radio" name="dwsb.sfssqy" value="2" <s:if test="dwsb.sfssqy==2">checked</s:if>/>否
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px">研发机构数</td>
				<td style="width:100px;background:#E7F2FC">TDF8J104</td>
				<td>
					<s:textfield name="dwsb.yfjgs"  value="%{dwsb.yfjgs==''?'0':dwsb.yfjgs}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				<td style="text-align:left;background:#E7F2FC;width:200px">高新技术产品数</td>
				<td style="width:100px;background:#E7F2FC">TDF8J105</td>
				<td>
					<s:textfield name="dwsb.gxjscps" value="%{dwsb.gxjscps==''?'0':dwsb.gxjscps}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		
			<tr>
    			<td colspan=6 style="text-align:left;background:#b0c4de;"><font color="#8b4513"><b>二、在孵企业经济效益（单位：个、万元、万美元）</b></font></td>
    		</tr>
    		
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px">在孵企业总收入</td>
				<td style="width:100px;background:#E7F2FC">TDF8G210</td>
				<td>
					<s:textfield name="dwsb.zrqyzsr"  value="%{dwsb.zrqyzsr==''?'0':dwsb.zrqyzsr}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				<td style="text-align:left;background:#E7F2FC;width:200px">在孵企业工业总产值</td>
				<td style="width:100px;background:#E7F2FC">TDF8G220</td>
				<td>
					<s:textfield name="dwsb.zrqygyzcz"  value="%{dwsb.zrqygyzcz==''?'0':dwsb.zrqygyzcz}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px">在孵企业净利润</td>
				<td style="width:100px;background:#E7F2FC">TDF8G230</td>
				<td>
					<s:textfield name="dwsb.zrqyjlr"  value="%{dwsb.zrqyjlr==''?'0':dwsb.zrqyjlr}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				<td style="text-align:left;background:#E7F2FC;width:200px">在孵企业上缴税金</td>
				<td style="width:100px;background:#E7F2FC">TDF8G240</td>
				<td>
					<s:textfield name="dwsb.zrqysjsj"  value="%{dwsb.zrqysjsj==''?'0':dwsb.zrqysjsj}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
    		</tr>
    			
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px">在孵企业出口创汇</td>
				<td style="width:100px;background:#E7F2FC">TDF8G250</td>
				<td>
					<s:textfield name="dwsb.zrqyckch"  value="%{dwsb.zrqyckch==''?'0':dwsb.zrqyckch}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				<td style="text-align:left;background:#E7F2FC;width:200px">在本市其他地区开票销售</td>
				<td style="width:100px;background:#E7F2FC">TDF8G260</td>
				<td>
					<s:textfield name="dwsb.zbsqtdqkpxs"  value="%{dwsb.zbsqtdqkpxs==''?'0':dwsb.zbsqtdqkpxs}" cssStyle="width:100px" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
    		</tr>
    		
    		<tr>
    			
    			<td colspan=6 style="text-align:left;background:#b0c4de;"><font color="#8b4513"><b>三、在孵企业从业人员情况(单位：人)</b></font></td>
    		</tr>
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px">在孵企业从业人员数</td>
				<td style="width:100px;background:#E7F2FC">TDF8H300</td>
				<td colspan=4>
					<s:textfield name="dwsb.zrqyrs" id="TDF8H300"  value="%{dwsb.zrqyrs==''?'0':dwsb.zrqyrs}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px">&nbsp&nbsp&nbsp&nbsp&nbsp其中：博士</td>
				<td style="width:100px;background:#E7F2FC">TDF8H311</td>
				<td>
					<s:textfield name="dwsb.bsrs" id="TDF8H311"  value="%{dwsb.bsrs==''?'0':dwsb.bsrs}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				<td style="text-align:left;background:#E7F2FC;width:200px">&nbsp&nbsp&nbsp&nbsp&nbsp其中：研究生学历及以上</td>
				<td style="width:100px;background:#E7F2FC">TDF8H313</td>
				<td>
					<s:textfield name="dwsb.yjsjysxls" id="TDF8H313"  value="%{dwsb.yjsjysxls==''?'0':dwsb.yjsjysxls}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				
    		</tr>
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp硕士</td>
				<td style="width:100px;background:#E7F2FC">TDF8H312</td>
				<td>
					<s:textfield name="dwsb.ssrs" id="TDF8H312"  value="%{dwsb.ssrs==''?'0':dwsb.ssrs}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				<td style="text-align:left;background:#E7F2FC;width:200px">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp本科学历</td>
				<td style="width:100px;background:#E7F2FC">TDF8H314</td>
				<td>
					<s:textfield name="dwsb.bkxls" id="TDF8H314"  value="%{dwsb.bkxls==''?'0':dwsb.bkxls}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td colspan=3>
    			</td>
    			<td style="text-align:left;background:#E7F2FC;width:200px">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp大专学历</td>
				<td style="width:100px;background:#E7F2FC">TDF8H315</td>
				<td>
					<s:textfield name="dwsb.dzxls" id="TDF8H315"  value="%{dwsb.dzxls==''?'0':dwsb.dzxls}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
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
					<s:textfield name="dwsb.gjzcs" id="TDF8H317"  value="%{dwsb.gjzcs==''?'0':dwsb.gjzcs}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px">其中：海归人才及外籍专家人员</td>
				<td style="width:100px;background:#E7F2FC">TDF8H318</td>
				<td>
					<s:textfield name="dwsb.hgjwjzjrs" id="TDF8H318"  value="%{dwsb.hgjwjzjrs==''?'0':dwsb.hgjwjzjrs}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				<td style="text-align:left;background:#E7F2FC;width:200px">其中：高级职称或博士学位人员</td>
				<td style="width:100px;background:#E7F2FC">TDF8H319</td>
				<td>
					<s:textfield name="dwsb.gjzchbsrs" id="TDF8H319"  value="%{dwsb.gjzchbsrs==''?'0':dwsb.gjzchbsrs}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
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
    			
    			<td colspan=6 style="text-align:left;background:#b0c4de;"><font color="#8b4513"><b>四、在孵企业知识产权情况（单位：项）</b></font></td>
    		</tr>
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px;">申请专利项数</td>
				<td style="width:100px;background:#E7F2FC">TDF8F400</td>
				<td>
					<s:textfield name="dwsb.sqzls" id="TDF8F400"  value="%{dwsb.sqzls==''?'0':dwsb.sqzls}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				
				<td style="text-align:left;background:#E7F2FC;width:200px">授权专利项数</td>
				<td style="width:100px;background:#E7F2FC">TDF8F420</td>
				<td>
					<s:textfield name="dwsb.qzsqzls" id="TDF8F420"  value="%{dwsb.qzsqzls==''?'0':dwsb.qzsqzls}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				
    		</tr>
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px;border-bottom: 2px solid #778899;">&nbsp&nbsp&nbsp&nbsp&nbsp其中：发明专利</td>
				<td style="width:100px;background:#E7F2FC;border-bottom: 2px solid #778899;">TDF8F410</td>
				<td style="border-bottom: 2px solid #778899;">
					<s:textfield name="dwsb.fmzls" id="TDF8F410"  value="%{dwsb.fmzls==''?'0':dwsb.fmzls}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				
				<td style="text-align:left;background:#E7F2FC;width:200px;border-bottom: 2px solid #778899;">&nbsp&nbsp&nbsp&nbsp&nbsp其中：发明专利</td>
				<td style="width:100px;background:#E7F2FC;border-bottom: 2px solid #778899;">TDF8F430</td>
				<td style="border-bottom: 2px solid #778899;">
					<s:textfield name="dwsb.qzfmzls" id="TDF8F430"  value="%{dwsb.qzfmzls==''?'0':dwsb.qzfmzls}" cssStyle="width:100px" onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				
    		</tr>
    		
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px">购买国外技术专利数</td>
				<td style="width:100px;background:#E7F2FC">TDF8F440</td>
				<td colspan=4>
					<s:textfield name="dwsb.gmgwjszls"  id="TDF8F440"  value="%{dwsb.gmgwjszls==''?'0':dwsb.gmgwjszls}" cssStyle="width:100px" onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		
    		<tr>
    			
    			<td colspan=6 style="text-align:left;background:#b0c4de;"><font color="#8b4513"><b>五、在孵企业科技活动情况（单位：项、万元）</b></font></td>
    		</tr>
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px">承担各类国家和地方项目数</td>
				<td style="width:100px;background:#E7F2FC">TDF8k600</td>
				<td>
					<s:textfield name="dwsb.cdglgjdfxms" id="TDF8k600"  value="%{dwsb.cdglgjdfxms==''?'0':dwsb.cdglgjdfxms}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				
				<td style="text-align:left;background:#E7F2FC;width:200px">各类国家和地方项目到帐经费</td>
				<td style="width:100px;background:#E7F2FC">TDF8k605</td>
				<td>
					<s:textfield name="dwsb.glgjdfdzjf" id="TDF8k605"  value="%{dwsb.glgjdfdzjf==''?'0':dwsb.glgjdfdzjf}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
    			
				<td style="text-align:left;background:#E7F2FC;width:200px">&nbsp&nbsp&nbsp&nbsp&nbsp其中：国家级项目</td>
				<td style="width:100px;background:#E7F2FC">TDF8k601</td>
				<td>
					<s:textfield name="dwsb.gjjxms" id="TDF8k601"  value="%{dwsb.gjjxms==''?'0':dwsb.gjjxms}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				
				<td style="text-align:left;background:#E7F2FC;width:200px">&nbsp&nbsp&nbsp&nbsp&nbsp其中：国家级项目</td>
				<td style="width:100px;background:#E7F2FC">TDF8k606</td>
				<td>
					<s:textfield name="dwsb.gjjxmjf" id="TDF8k606"  value="%{dwsb.gjjxmjf==''?'0':dwsb.gjjxmjf}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				
				
    		</tr>
    		
    		<tr>
    			<td style="text-align:left;background:#E7F2FC;width:200px">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp省级项目</td>
				<td style="width:100px;background:#E7F2FC">TDF8k602</td>
				<td>
					<s:textfield name="dwsb.sjxms" id="TDF8k602"  value="%{dwsb.sjxms==''?'0':dwsb.sjxms}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				
				
				<td style="text-align:left;background:#E7F2FC;width:200px">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp省级项目</td>
				<td style="width:100px;background:#E7F2FC">TDF8k607</td>
				<td>
					<s:textfield name="dwsb.sjxmjf" id="TDF8k607"  value="%{dwsb.sjxmjf==''?'0':dwsb.sjxmjf}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
    		</tr>
    		<tr>
				<td style="text-align:left;background:#E7F2FC;width:200px;">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp市级项目</td>
				<td style="width:100px;background:#E7F2FC">TDF8k603</td>
				<td style="">
					<s:textfield name="dwsb.ssjxms" id="TDF8k603"  value="%{dwsb.ssjxms==''?'0':dwsb.ssjxms}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				
				<td style="text-align:left;background:#E7F2FC;width:200px;">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp市级项目</td>
				<td style="width:100px;background:#E7F2FC">TDF8k608</td>
				<td style="">
					<s:textfield name="dwsb.ssjxmjf" id="TDF8k608"  value="%{dwsb.ssjxmjf==''?'0':dwsb.ssjxmjf}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				
			</tr>
    		<tr>
				<td style="text-align:left;background:#E7F2FC;width:200px;border-bottom: 2px solid #778899;">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp区级项目</td>
				<td style="width:100px;background:#E7F2FC;border-bottom: 2px solid #778899;">TDF8k604</td>
				<td style="border-bottom: 2px solid #778899;">
					<s:textfield name="dwsb.qjxms" id="TDF8k604"  value="%{dwsb.qjxms==''?'0':dwsb.qjxms}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				
				<td style="text-align:left;background:#E7F2FC;width:200px;border-bottom: 2px solid #778899;">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp区级项目</td>
				<td style="width:100px;background:#E7F2FC;border-bottom: 2px solid #778899;">TDF8k609</td>
				<td style="border-bottom: 2px solid #778899;">
					<s:textfield name="dwsb.qjxmjf" id="TDF8k609"  value="%{dwsb.qjxmjf==''?'0':dwsb.qjxmjf}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				
			</tr>
			
			<tr>
				<td style="text-align:left;background:#E7F2FC;width:200px;border-bottom: 2px solid #778899;">承担各类企业项目数</td>
				<td style="width:100px;background:#E7F2FC;border-bottom: 2px solid #778899;">TDF8k610</td>
				<td  style="border-bottom: 2px solid #778899;">
					<s:textfield name="dwsb.cdglqyxms" id="TDF8k610"  value="%{dwsb.cdglqyxms==''?'0':dwsb.cdglqyxms}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNumInt(this.id);"></s:textfield>
				</td>
				<td style="text-align:left;background:#E7F2FC;width:200px;border-bottom: 2px solid #778899;">各类企业项目到帐经费</td>
				<td style="width:100px;background:#E7F2FC;border-bottom: 2px solid #778899;">TDF8k611</td>
				<td  style="border-bottom: 2px solid #778899;">
					<s:textfield name="dwsb.glqyxmdzjf" id="TDF8k611"  value="%{dwsb.glqyxmdzjf==''?'0':dwsb.glqyxmdzjf}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				
				
			</tr>
			
			<tr>
				<td style="text-align:left;background:#E7F2FC;width:200px">科技活动经费支出总额</td>
				<td style="width:100px;background:#E7F2FC">TDF8k620</td>
				<td colspan=4>
					<s:textfield name="dwsb.kjhdjfzcze" id="TDF8k620"  value="%{dwsb.kjhdjfzcze==''?'0':dwsb.kjhdjfzcze}" cssStyle="width:100px"  onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
			</tr>
			<tr>
				
					
				<td style="text-align:left;background:#E7F2FC;width:200px">&nbsp&nbsp&nbsp&nbsp&nbsp其中：研究与试验发展支出</td>
				<td style="width:100px;background:#E7F2FC">TDF8k630</td>
				<td colspan=4>
					<s:textfield name="dwsb.yjsyfzzc" id="TDF8k630"  value="%{dwsb.yjsyfzzc==''?'0':dwsb.yjsyfzzc}" cssStyle="width:100px" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
			
				
			</tr>
			<tr>
				
				<td style="text-align:left;background:#E7F2FC;width:200px">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp新产品开发经费支出</td>
				<td style="width:100px;background:#E7F2FC">TDF8k640</td>
				<td colspan=4>
					<s:textfield name="dwsb.xcpkfjfzc" id="TDF8k640"  value="%{dwsb.xcpkfjfzc==''?'0':dwsb.xcpkfjfzc}" cssStyle="width:100px" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td> 
			</tr>
    		<!-- --------------------- -->
			
			
		</table>
	</div>
	</div>
	<div class="footer" style="background:#f5f5f5">
		<input class="button3" type="button" value="审核通过" onclick="doSave(0)"/>
		<input class="button3" type="button" value="不通过" onclick="doSave(1)"/>
		回退原因:<input type="text" name="thyy" style="width:250" value=""/>
	</div>

	</s:form>
	</body>

	<script type="text/javascript">
		$('tableContainer').style.height = (getSize().h - 70)+"px"; 
		
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

