<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
		<script type="text/javascript" src="<c:url value="/js/czrc.js"/>" ></script>	
	<style>
		.fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
	</style>
	</head>

	<body>
	<s:form name="czrcForm" action='zsdw!preSysjsqk.do' method="post" >
	<s:hidden name="dwid"></s:hidden>
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="opttype"></s:hidden>
 <!--个人信息 start-->
	<div class="title"  onclick=""><h2> 实验室、研发中心建设情况表 &nbsp&nbsp&nbsp&nbsp&nbsp
	
	</h2>
	<div class="img_right" id="s1" ></div>
	</div>
	<div class="butbar" id="butbar">
		<div class="left">	
			
		</div>
	</div>	
	<div  class="tableContainer" id="tableContainer" style="width:100%">
		<table id="tac">
			<thead>
				<tr>
					<td width="20px"><input type="checkbox" id="allcheckbox" onclick="RC.checkboxAll('dmkey',this.checked)"></td>
					<td width="50px">序号</td>
					<td>建设年份</td>
					<td>名称</td>
					<td>合建单位</td>
					<td>备注</td>
				</tr>
			</thead>
			<s:iterator value="qlist" id="qlist" status="listStat">
				<tr>
					<td><input type="checkbox" id="dmkey" name="dmkey" value='<s:property value="xh"/>'></td>
						<td> <s:property value="#listStat.index+1"/></td>
						<td>
							<s:property value="jsnf"/>
						</td>
						<td>
							<s:property value="jsmc"/>
						</td>
						<td>
							<s:property value="hjdw"/>
						</td>
						
						<td>
							<s:property value="bz"/>
						</td>
				</tr>				
			</s:iterator>
		</table>		
	</div>
	<div class="footer">
		<input class="btn_submit1" type="button" value="下 一 步" onclick="javascript:parent.activeChange('rcdali10');"/>
		<s:if test="opttype==3">
				<input class="btn_submit1" type="button" value="退    出" onclick="javascript:doOut(4);"/>
			</s:if>
			<s:else>
				<input class="btn_submit1" type="button" value="填写完成" onclick="javascript:parent.setDwid('',1,true);"/>
		</s:else>
		
	</div>	
	</s:form>
	</body>

	<script type="text/javascript">
		$('tableContainer').style.height = (getSize().h - 120)+"px"; 
		
		function closeWindows(){closeWin(self.name);}
		function closeWin(sid){
			parent.getOpener().refresh();
			parent.closeWindows();
		}
		
		function doOut(type){
			closeWindows();
		}
		function addxx(){
			parent.openWin("zsdw!preSysjsqkI.do?dwid="+$('dwid').value+"&opttype=1&pname="+$('pname').value,"550","200");
		}
		
		function repxx(){
			var rcsel = RC.checkbox('dmkey');
			if(rcsel.length == 0){
				alert("请选择需要修改的信息!");
				return false;
			}
			if(rcsel.length > 1){
				alert("修改只能选择一条记录!");
				return false;
			}
			parent.openWin("zsdw!preSysjsqkU.do?dwid="+$('dwid').value+"&xh="+rcsel[0].v+"&opttype=3&pname="+$('pname').value,"550","200");
		}
		
		function delxx(){
			var rcsel = RC.checkbox('dmkey');
			if(rcsel.length == 0){
				alert("请选择需要删除的信息!");
				return false;
			}
			if(window.confirm("您确定要删除 "+rcsel.length+" 条信息?")){
				var ajax = new AppAjax("zsdw!doSysjsqkD.do",sava_back).submitForm("czrcForm");
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

