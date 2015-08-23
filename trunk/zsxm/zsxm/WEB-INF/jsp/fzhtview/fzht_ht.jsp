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
	<s:form name="zsdwForm" action='zsdw.do' method="post" >
	<input type="hidden" name="autodwdm" id="autodwdm" value=""/>
	<s:hidden name="htid"></s:hidden>
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="opttype"></s:hidden>
 <!--个人信息 start-->
 <div style="width:100%">
 
	<div class="title "onclick=""><h2><span class="red fn">*</span>房租合同基本信息</h2><div class="img_right" id="s1" ></div></div>
	
	<div class="tableContainer" id="tableContainer" style="height:90%">
    	<table class="fxtable"  cellpadding="0">
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px"><span class="red fn">*</span>合同类型</td>
				<td class="tdright"><s:select name="fzht.htlx" list="xtdict23" cssStyle="width:150" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择合同类型--"/></td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px"><span class="red fn">*</span>合同编号</td>
				<td class="tdright">
					<s:textfield cssStyle="width:130px" name="fzht.htbh"></s:textfield>
				</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px"><span class="red fn">*</span>合同份数</td>
				<td>
					<s:textfield cssStyle="width:130px" name="fzht.htfs" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"/>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px"><span class="red fn">*</span>企业</td>
				<td>
					<INPUT id="zzxm_button" class="selectBut2"  title="选择企业" value="<s:property value="fzht.dwid_mc==''?'选择企业':fzht.dwid_mc"/>" type=button onclick="selDw()">
					<s:hidden name="fzht.dwid"></s:hidden>
				</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">合同起止时间</td>
				<td colspan=3>
				
					<input type="text" class="Wdate" name="fzht.htstrdate" id="date" style="text-align:left;width:130" value="<s:property value="fzht.htstrdate"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>
					--
					<input type="text" class="Wdate" name="fzht.htenddate" id="date" style="text-align:left;width:130" value="<s:property value="fzht.htenddate"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>
					
				</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">租房地址</td>
				<td colspan=3>
				常州科教城
				<s:select name="fzht.bgdd1" list="xtdict6" cssStyle="width:140" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" />
				<s:select name="fzht.bgdd2" list="xtdict7" cssStyle="width:50" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--选--" />
				座
				<s:select name="fzht.bgdd3" list="xtdict8" cssStyle="width:50" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--选--" />
				层
				<s:textfield name="fzht.bgdd4" cssStyle="width:50"></s:textfield>
				房间
				</td>
			</tr>	
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">租房面积</td>
				<td class="tdright"><s:textfield cssStyle="width:130px" name="fzht.fzmj" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);isCheckSum();"/><span class="red fn">(平方米)</span></td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">单位租金</td>
				<td class="tdright"><s:textfield cssStyle="width:130px" name="fzht.dwzj" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);isCheckSum();"/><span class="red fn">(元/M2/年)</span></td>
			</tr>
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">全面积年房租</td>
				<td colspan=3>
					<s:textfield cssStyle="width:130px" name="fzht.qmjnfz" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"/><span class="red fn">(元)</span>
				</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">优惠政策</td>
				<td colspan=3>
					<s:textarea name="fzht.yhzc" cols="40" rows="2"></s:textarea>
				</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">优惠政策后房租</td>
				<td class="tdright"><s:textfield cssStyle="width:130px" name="fzht.yhzcfz" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"/><span class="red fn">(元)</span></td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">享受政策的房租</td>
				<td class="tdright"><s:textfield cssStyle="width:130px" name="fzht.xszcfz" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"/><span class="red fn">(元)</span></td>
			</tr>
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">备注</td>
				<td colspan=3><s:textarea name="fzht.bz" cols="40" rows="4"></s:textarea></td>
			</tr>
		</table>
	</div>
	</div>
	<div  style="width:770px;text-align:center">
		
	</div>
	

	
	</s:form>
	</body>

	<script type="text/javascript">
		$('tableContainer').style.height = (getSize().h - 80)+"px"; 
		
	
		init();
		function init(){
			var xx = document.getElementsByTagName("input");
			for(var i=0;i<xx.length;i++){
				xx[i].disabled = true;
			}
			xx = document.getElementsByTagName("select");
			for(var i=0;i<xx.length;i++){
				xx[i].disabled = true;
			} 
			xx = document.getElementsByTagName("textarea");
			for(var i=0;i<xx.length;i++){
				xx[i].disabled = true;
			}
		}
	</script>
</html>

