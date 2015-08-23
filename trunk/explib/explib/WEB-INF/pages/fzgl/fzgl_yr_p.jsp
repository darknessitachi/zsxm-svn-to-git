<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
		<script src="Framework/Main.js" type="text/javascript"></script>
				<script type="text/javascript" src="<c:url value="/js/comm.js"/>" ></script>
	<style>
		.fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
	</style>
	</head>

	<body>

	<s:form name="czrcForm" action='exp!classinfo.do' method="post" >
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="rcid"></s:hidden>
	<s:hidden name="xh"></s:hidden>
	<s:hidden name="exp.zjlb" ></s:hidden>
	<s:hidden name="winid" id="winid"></s:hidden>
 <!--个人信息 start-->

	<div class="fxtableContainer" id="fxtableContainer" style="overflow:auto;">

				       	<table align="center" class="fxtable" cellpadding="0" cellspacing="0" id="tab9"  style="">
				       			<tr>
									<td class="bt">选择数据库<font color=red>*</font></td>
									<td class="tdright">
										<select name='yrdata' id="yrdata" style="width:200px">
											<option value=""> --请选择相应数据库--</option>
											<option value="zjps"> 专家系统引入数据</option>
											<option value="czrc"> 高层人层引入数据</option>
										</select>
									</td>
									
								</tr>
								
						</table>					
					
	</div>
	<div class="footer" style="background:#f5f5f5">
		<input class="button3" type="button" value="确   定" onclick="javascript:qsel();"/>
		<input class="button3" type="button" value="退   出" onclick="closeWin();"/>
	</div>

	</s:form>
	</body>

	<script type="text/javascript">

		$('fxtableContainer').style.height = (getSize().h - 35)+"px"; 
		var reload = 0;
		
		function qsel(){
			showwaitform('开始引入数据！请稍候......');
			var ajax = new AppAjax("fzgl!doSelZjps.do",function(data){
				if(data.message.code > 0){
					alert('引入成功！');
				}else{
					alert(data.message.text);
				}
				hidewaitform();
			}).submit();
		}
		/**
		function qsel(){
			if($('yrdata').value == ''){
				alert('请选择需要引入的库!');
				return false;
			}
			var diag = new Dialog("yrdatawindows");
			diag.Title = '引入专家';
			diag.Width = 1000;
			diag.Height = parent.getSize().h-150 ;
			diag.ShowMessageRow=true;
			diag.MessageTitle = "请选择相应的数据";
			diag.Message = " 请选择需导入专家库的数据!";
			diag.URL = "fzgl!goyrdata.do?yrdata="+$('yrdata').value+"&winid=yrdatawindows";
			diag.show();
		}
		**/
	</script>
</html>

