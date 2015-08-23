<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<script src="Framework/Main.js" type="text/javascript"></script>
		<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
		<link type="text/css" href="styles/csstable.css"rel="stylesheet" />
		<script type="text/javascript" src="<c:url value="/js/comm.js"/>" ></script>	
	<style>
		.fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
	</style>
	</head>

	<body style="background:white">
	<s:form name="czrcForm" action='exp!preGzjl.do' method="post" >
	<s:hidden name="rcid"></s:hidden>
	<s:hidden name="rcidtb"></s:hidden>
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="opttype"></s:hidden>
	<s:hidden name="winid" id="winid"></s:hidden>
	<s:hidden name="tbtname" value="EXP_SHJZ"></s:hidden>
	<div id="fxtableContainer" class="tjtableContainer" style="width:100%;heigth:100%;overflow:auto;" >
    	<table id="tjtable" cellspacing="0" cellpadding="0">
    		<thead>
    		<tr>
    			<th class="thc" id="th1">征集社会兼职</th>
    			<th class="thc" id="th2">现有社会兼职</th>
    		</tr>
    		</thead>
    		<tr>
    			<td style="width:50%" id="td1">
	    			<div  class="tableContainer" id="tableContainer" style="width:100%">
						<table id="tac" style="width:750px">
							<thead>
								<tr>
									<td width="40px"><input type="checkbox" id="allcheckbox" onclick="COM.checkboxAll('dmkey',this.checked)"></td>
									<td width="40px">比对</td>
									<td width="100px">开始日期</td>
									<td width="100px">结束日期</td>
									<td>兼职（聘用）单位</td>
									<td>兼（聘）职身份</td>
									<td>备注</td>
								</tr>
							</thead>
							<s:iterator value="qlisttb">
								<tr>
									<td><input type="checkbox" id="dmkey<s:property value="xh"/>" name="dmkey" value='<s:property value="xh"/>'></td>
									<td>
										<input type="radio" id="xhtb<s:property value="xh"/>" name="xhtb" value='<s:property value="xh"/>'>
									</td>
									<td><s:property value="brq"/></td>
									<td><s:property value="erq"/></td>
									<td><s:property value="jsdw"/></td>
									<td><s:property value="jssf"/></td>
									<td><s:property value="sm"/></td>
								</tr>				
							</s:iterator>
						</table>		
					</div>
    			</td>
    			
    			<td style="width:50%" id="td2">
	    			<div  class="tableContainer" id="tableContainertb" style="width:100%">
						<table id="tac" style="width:750px">
							<thead>
								<tr>
									<td width="40px">比对</td>
									<td width="100px">开始日期</td>
									<td width="100px">结束日期</td>
									<td>兼职（聘用）单位</td>
									<td>兼（聘）职身份</td>
									<td>备注</td>
								</tr>
							</thead>
							<s:iterator value="qlist">
								<tr>
									<td><input type="radio" id="xh<s:property value="xh"/>" name="xh" value='<s:property value="xh"/>'></td>
									<td><s:property value="brq"/></td>
									<td><s:property value="erq"/></td>
									<td><s:property value="jsdw"/></td>
									<td><s:property value="jssf"/></td>
									<td><s:property value="sm"/></td>
								</tr>				
							</s:iterator>
						</table>		
					</div>    			
    			</td>
    		</tr>
    	</table>	
	</div>
	<div class="footer" style="background:#efefef;">
		<input class="btn_submit1" type="button" value="同步信息" onclick="javascript:doTb();"/>
		<input class="btn_submit1" type="button" value="比对同步" onclick="javascript:doBdTb();"/>
		<input class="btn_submit1" type="button" value="退    出" onclick="javascript:window.parent.closeWin();"/>
	</div>	
	</s:form>
	</body>

	<script type="text/javascript">
		$('fxtableContainer').style.height = (getSize().h - 35)+"px";
		$('td1').style.width = (getSize().w/2)+'px';
		$('td2').style.width = (getSize().w/2)+'px';
		$('tableContainer').style.height = (getSize().h - 85)+"px"; 
		$('tableContainer').style.width = (getSize().w/2-1)+'px';
		$('tableContainertb').style.height = (getSize().h - 85)+"px";
		$('tableContainertb').style.width = (getSize().w/2-1)+'px';
		
		function closeWindows(){
			window.parent.parent.refreshP();
			closeWin();
		}
				
		var diag = null;
		
		function doTb(){
			var rcsel = COM.checkbox('dmkey');
			if(rcsel.length == 0){
				alert('请选择需要同步的信息');
				return false;
			}
			if(window.confirm("您确定要同步 "+rcsel.length+" 条信息至专家库中?")){
				var ajax = new AppAjax("exptb!doExpAppend.do",
				function (data){
					if(data != null && data.message.code > 0){
						alert('同步成功！');
						document.location.reload();
					}else{
						alert(data.message.text);
					}
				}).submitForm("czrcForm");
			}
		}
		function doBdTb(){
			var xh = COM.getRadioV("xh");
			var xhtb = COM.getRadioV("xhtb");
			if(xh == '' || xhtb == ''){
				alert('请选择需要比对的信息');
				return false;
			}
			diag = new Dialog("bdtbwindows");
			diag.Title = "同步";
			diag.Width = 750;
			diag.Height = 230;
			diag.ShowMessageRow=true;
			diag.MessageTitle = "社会兼职信息同步";
			diag.Message = "需要同步的信息请打上勾";
			diag.URL = "exptb!preShjzT.do?rcid="+$('rcid').value+"&xh="+xh+"&xhtb="+xhtb+"&rcidtb="+$('rcidtb').value+"&opttype=1&pname="+$('pname').value+"&winid=bdtbwindows";
			diag.show();
		}
		
		
		function refresh(){
			document.all.czrcForm.submit();
		}
	</script>
</html>

