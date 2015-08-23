<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
		<link type="text/css" href="styles/csstable.css"rel="stylesheet" />
	 	<script language="JavaScript"src="<s:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>
	 	<script type="text/javascript" src="<c:url value="/js/rc_init.js"/>" ></script>
	 	<script type="text/javascript" src="<c:url value="/js/comm.js"/>" ></script>		
	<style>
		th.thc {
						font: bold 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
						color: #4f6b72;
						border-right: 1px solid #C1DAD7;
						border-bottom: 1px solid #C1DAD7;
						border-top: 1px solid #C1DAD7;
						letter-spacing: 2px;
						text-transform: uppercase;
						text-align: left;
						padding: 6px 6px 6px 12px;
						background: #CAE8EA url(images/bg_header.jpg) no-repeat;
					}
			
			td.tdc {
				border-right: 1px solid #C1DAD7;
				border-bottom: 1px solid #C1DAD7;
				background: #fff;
				font-size:12px;
				padding: 6px 6px 6px 12px;
				color: #4f6b72;
			}		
	</style>
	</head>

	<body style="background:white">
	
	<s:form name="czrcForm" action='exp!classinfo.do' method="post" >
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="rcid"></s:hidden>
	<s:hidden name="xh"></s:hidden>
	<s:hidden name="xhtb"></s:hidden>
	<s:hidden name="winid" id="winid"></s:hidden>
	<s:hidden name="tbtname" value="EXP_BPZJ"></s:hidden>
 <!--个人信息 start-->
	
	<div class="tjtableContainer" id="fxtableContainer" style="overflow:auto;width:100%;heigth:100%">
    	<table id="tjtable" cellspacing="0" cellpadding="0" style="width:100%">
    		<thead>
    		<tr>
    			<th class="thc"  style="width:20px"><input type="checkbox" id="allcheckbox" onclick="COM.checkboxAll('dmkey',this.checked)"></th>
    			<th class="thc"  style="width:130px">字段</th>
    			<th class="thc" >征集专家信息</th>
    			<th class="thc" >现有专家信息</th>
    		</tr>
    		</thead>
    		
    		<tr>
    			<td class="tdc" ><input type="checkbox" name="dmkey" value="rxnf" /></td>
				<th class="spec">入选年份</th>
				<td class="tdc" ><s:select name="exptb.rxnf" id="exptb.rxnf" list="dmmcs" cssStyle="width:130" listKey="dm" listValue="mc" headerKey="" headerValue="--请选择--"/></td>
				<td class="tdc" ><s:property value="exp.rxnf"/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdc" ><input type="checkbox" name="dmkey" value="bpzjqk" /></td>
				<th class="spec">专家类别</th>
				<td class="tdc" ><s:property value="exptb.bpzjqk_mc"/>&nbsp;</td>
				<td class="tdc" ><s:property value="exp.bpzjqk_mc"/>&nbsp;</td>
			
			</tr>
			<tr>	
				<td class="tdc" ><input type="checkbox" name="dmkey" value="sm" /></td>
				<th class="spec">备注</th>
				<td class="tdc" ><s:textfield cssStyle="width:130px" name="exptb.sm"/></td>
				<td class="tdc" ><s:property value="exp.sm"/>&nbsp;</td>
			</tr>
    	</table>	
	</div>
	
	<div class="footer" style="background:#f5f5f5">
		<input class="button3" type="button" value="确定同步" onclick="javascript:doTB();"/>
		<input class="button3" type="button" value="退   出" onclick="closeWin();"/>
	</div>
	
	</s:form>
	</body>

	<script type="text/javascript">
		$('fxtableContainer').style.height = (getSize().h - 40)+"px"; 
		$('exptb.sm').focus();
		var reload = 0;
		
		function doTB(){
			var rcsel = COM.checkbox('dmkey');
				
			if(rcsel.length == 0 ){
				alert('请选择需要同步的信息');
				return false;
			}
			if(window.confirm("您确定要同步信息至专家库中?")){
				var ajax = new AppAjax("exptb!doExpTbMx.do",
				function (data){
					if(data != null && data.message.code > 0){
						alert('同步成功！');
						closeWin();
					}else{
						alert(data.message.text);
					}
				}).submitForm("czrcForm");
			}
		}
		
	</script>
</html>

