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
</style>
	</head>

	<body>
	<s:component template="xtitle" theme="app" value='安家费申报数据审核'></s:component>
	<s:form name="zsdwForm" action='dwsb!save.do' method="post" >
	<s:hidden name="sbid"></s:hidden>
	<s:hidden name="jfh.ryid"></s:hidden>
	<s:hidden name="jfh.dwid"></s:hidden>
	<s:hidden name="jfh.treekey"></s:hidden>
 <!--个人信息 start-->
 <div style="width:100%">
 
	<div class="tableContainer" id="tableContainer" style="height:90%">
    	<table class="fxtable"  cellpadding="0">
			<tr>
    			<td colspan=4 align=left style="background:#efefef">
    				<font color="#8b4513"><b>申报人员基本信息</b></font>
    			</td>
    		</tr>
    		<tr>
				<td style="text-align:left;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">姓名</td>
				<td>
					<s:textfield  name="zsdw.xm" id="zsdw.xm" cssStyle="width:130;"></s:textfield>
				</td>
				
				<td style="text-align:left;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">性别</td>
				<td>
					<s:select name="zsdw.sex" id="zsdw.sex" list="xtdict14" cssStyle="width:130" listKey="dictbh" listValue="dictname"/>
				</td>
				
			</tr>
			<tr>
				
				<td style="text-align:left;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">身份证</td>
				<td>
					<s:textfield  name="zsdw.sfz" id="zsdw.sfz" cssStyle="width:130;"></s:textfield>
				</td>
				<td style="text-align:left;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">出生年月</td>
				<td>
					<input type="text" class="Wdate" name="zsdw.birthday" id="zsdw.birthday" style="text-align:left;width:130" value="<s:property value="zsdw.birthday"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>
				</td>
				
			</tr>
			<tr>
				
				<td style="text-align:left;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">专兼职</td>
				<td>
				<s:if test="zsdw.zjz==''">
					<input type="radio" name="zsdw.zjz" id="zsdw.zjz" value="1" checked/>专
					&nbsp&nbsp&nbsp&nbsp
					<input type="radio" name="zsdw.zjz" id="zsdw.zjz" value="2"/>兼				
				</s:if>
				<s:else>
					<input type="radio" name="zsdw.zjz" id="zsdw.zjz" value="1" <s:if test="zsdw.zjz==1">checked</s:if>/>专
					&nbsp&nbsp&nbsp&nbsp
					<input type="radio" name="zsdw.zjz" id="zsdw.zjz" value="2" <s:if test="zsdw.zjz==2">checked</s:if>/>兼				
				</s:else>
				</td>
				
				
				<td style="text-align:left;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">起聘时间</td>
				<td>
					<input type="text" class="Wdate" name="zsdw.qpsj" id="zsdw.qpsj" style="text-align:left;width:130" value="<s:property value="zsdw.qpsj"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>
				</td>
				
			</tr>
			<tr>
				<td style="text-align:left;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">学历/学位</td>
				<td>
					<s:select name="zsdw.xl" id="zsdw.xl" list="xtdict25" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" />
				</td>
				<td style="text-align:left;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">职称/职务</td>
				<td>
					<s:select name="zsdw.zc" id="zsdw.xl" list="xtdict15" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" />
				</td>
				
			</tr>
			
			<tr>	
				
				<td style="text-align:left;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">海外留学情况</td>
				<td>
					<s:textfield  name="zsdw.hwlxqk" id="zsdw.hwlxqk" cssStyle="width:130;"></s:textfield>
				</td>
				<td style="text-align:left;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">研究方向</td>
				<td>
					<s:textfield  name="zsdw.yjfx" id="zsdw.yjfx" cssStyle="width:230;"></s:textfield>
				</td>
				
			</tr>
			
			<tr>
    			<td colspan=4 align=left style="background:#efefef">
    				<font color="#8b4513"><b>安家费填报信息&nbsp;&nbsp;(资助标准：<s:property value="zsdw.ajfzzbz"/>)</b></font>
    			</td>
    		</tr>
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">购房时间</td>
				<td class="tdright">
					<input type="text" class="Wdate" name="jfh.gfsj" id="date" style="text-align:left;width:130" value="<s:property value="jfh.gfsj"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">资助标准</td>
				<td class="tdright">
					<s:textfield name="jfh.zzbz" cssStyle="width:150" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">购房地点</td>
				<td class="tdright">
					<s:textfield name="jfh.gfdd" cssStyle="width:150px"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">房屋面积</td>
				<td class="tdright">
					<s:textfield name="jfh.fwmj" cssStyle="width:150" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">房屋总额</td>
				<td class="tdright">
					<s:textfield name="jfh.fwze" cssStyle="width:150" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">交付时间</td>
				<td class="tdright">
					<input type="text" class="Wdate" name="jfh.jfsj" id="date" style="text-align:left;width:130" value="<s:property value="jfh.jfsj"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>
				</td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">第一年申请补贴</td>
				<td class="tdright" colspan=3>
					<s:textfield name="jfh.dynbt" cssStyle="width:150" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
				</td>
				
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">备注</td>
				<td class="tdright" colspan=3>
					<s:textarea cols="50" name="jfh.sm" rows="5"></s:textarea>
				</td>
			</tr>
			
			
		</table>
	</div>
	</div>
	<div class="footer" style="background:#f5f5f5">
		<input class="button3" type="button" value="保  存" onclick="doSave(0)"/>
		<input class="button3" type="button" value="取  消" onclick="javascript:closeWin(self.name);"/>
	</div>
	
	</s:form>
	</body>

	<script type="text/javascript">
		$('tableContainer').style.height = (getSize().h - 70)+"px"; 
		function doSave(type){
			
			var ajax = new AppAjax("jfhwh!doDataxg2.do?opttype="+type,function(data){save_Back(data,type)}).submitForm("zsdwForm");
		}
		
		function save_Back(data,type){
			if(data.message.code >0){
				alert('数据修改成功!');
				getOpener().refresh();
				closeWin(self.name);
			}else{
				alert(data.message.text);
			}
		}
	</script>
</html>

