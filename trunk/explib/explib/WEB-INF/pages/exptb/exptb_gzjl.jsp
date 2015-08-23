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
	<s:hidden name="tbtname" value="EXP_GZJL"></s:hidden>

	<div id="fxtableContainer" class="tjtableContainer" style="width:100%;heigth:100%;overflow:auto;" >
    	<table id="tjtable" cellspacing="0" cellpadding="0">
    		<thead>
    		<tr>
    			<th class="thc" id="th1">征集专家信息</th>
    			<th class="thc" id="th2">现有专家信息</th>
    		</tr>
    		</thead>
    		<tr>
    			<td style="width:50%" id="td1">
	    			<div  class="tableContainer" id="tableContainer" style="width:100%">
						<table id="tac" style="width:600px">
							<thead>
								<tr>
									<td width="40px"><input type="checkbox" id="allcheckbox" onclick="COM.checkboxAll('dmkey',this.checked)"></td>
									<td width="40px">比对</td>
									<td width="100px">开始日期</td>
									<td width="100px">结束日期</td>
									<td>所在单位及部门</td>
									<td>从事岗位或职务</td>
								</tr>
							</thead>
							<s:iterator value="qlisttb">
								<tr>
									<td><input type="checkbox" id="dmkey<s:property value="xh"/>" name="dmkey" value='<s:property value="xh"/>'></td>
									<td>
										<input type="radio" id="xhtb<s:property value="xh"/>" name="xhtb" value='<s:property value="xh"/>'>
									</td>
									<td><s:property value="brq"/></td>
									<td>
									<s:if test="nowbz==1">
										至今
									</s:if>
									<s:else>
										<s:property value="erq"/>
									</s:else>
									<td><s:property value="dwbm"/></td>
									<td><s:property value="zw"/></td>
								</tr>				
							</s:iterator>
						</table>		
					</div>
    			</td>
    			
    			<td style="width:50%" id="td2">
	    			<div  class="tableContainer" id="tableContainertb" style="width:100%">
						<table id="tac" style="width:600px">
							<thead>
								<tr>
									<td width="40px">比对</td>
									<td width="100px">开始日期</td>
									<td width="100px">结束日期</td>
									<td>所在单位及部门</td>
									<td>从事岗位或职务</td>
								</tr>
							</thead>
							<s:iterator value="qlist">
								<tr>
									<td><input type="radio" id="xh<s:property value="xh"/>" name="xh" value='<s:property value="xh"/>'></td>
									<td><s:property value="brq"/></td>
									<td>
									<s:if test="nowbz==1">
										至今
									</s:if>
									<s:else>
										<s:property value="erq"/>
									</s:else>
									<td><s:property value="dwbm"/></td>
									<td><s:property value="zw"/></td>
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
			diag.MessageTitle = "工作简历信息同步";
			diag.Message = "需要同步的信息请打上勾";
			diag.URL = "exptb!preGzjlT.do?rcid="+$('rcid').value+"&xh="+xh+"&xhtb="+xhtb+"&rcidtb="+$('rcidtb').value+"&opttype=1&pname="+$('pname').value+"&winid=bdtbwindows";
			diag.show();
		}
		
		function addxx(){
			diag = new Dialog("jlinfo1");
			diag.Title = "新增";
			diag.Width = 570;
			diag.Height = 130;
			diag.ShowMessageRow=true;
			diag.MessageTitle = "新增工作简历";
			diag.Message = "请填写相应的工作简历信息";
			diag.URL = "exp!preGzjlI.do?rcid="+$('rcid').value+"&opttype=1&pname="+$('pname').value+"&winid=jlinfo1";
			diag.show();
		}
		
		function repxx(){
			var rcsel = COM.checkbox('dmkey');
			if(rcsel.length == 0){
				alert("请选择需要修改的信息!");
				return false;
			}
			if(rcsel.length > 1){
				alert("修改只能选择一条记录!");
				return false;
			}
			diag = new Dialog("xxjlinfo1");
			diag.Title = "修改";
			diag.Width = 570;
			diag.Height = 130;
			diag.ShowMessageRow=true;
			diag.MessageTitle = "修改工作简历";
			diag.Message = "请修改相应的工作简历信息";
			diag.URL = "exp!preGzjlU.do?rcid="+$('rcid').value+"&xh="+rcsel[0].v+"&opttype=3&pname="+$('pname').value+"&winid=xxjlinfo1";
			diag.show();
		}
		
		
		function delxx(){
			var rcsel = COM.checkbox('dmkey');
			if(rcsel.length == 0){
				alert("请选择需要删除的信息!");
				return false;
			}
			if(window.confirm("您确定要删除 "+rcsel.length+" 条专家信息?")){
				var ajax = new AppAjax("exp!doGzjlD.do",sava_back).submitForm("czrcForm");
			}
		}
		
		function sava_back(data){
			if(data.message.code >0){
				alert("删除成功!");
				refresh();
			}else{
				alert(data.message.text);
			}
		}
		
		
		function refresh(){
			document.all.czrcForm.submit();
		}
	</script>
</html>

