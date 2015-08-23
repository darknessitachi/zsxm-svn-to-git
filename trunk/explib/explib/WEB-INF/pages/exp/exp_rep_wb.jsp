<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<title>专家维护</title>
		<%@ include file="/common/meta.jsp"%>
		<script src="<c:url value="/Framework/Main.js"/>" type="text/javascript"></script>
		<style>
			
		</style>		
	</head>

	<body style="margin: 0px; overflow: hidden; margin: 2px;text-align:center">
		<s:form name="spForm" action="" method="post">
     			<s:hidden name="rcid" id="rcid"></s:hidden>
     			<s:hidden name="wburl" id="wburl"></s:hidden>
     			<s:hidden name="wbuserid" id="wbuserid"></s:hidden>
     			<s:hidden name="wbfldm" id="wbfldm"></s:hidden>
     			<s:hidden name="wbappname" id="wbappname"></s:hidden>
     			<table style="width:100%;height:100%">
     			<tr>
     				<td align="center">
     					<input type="button" value="重新修改" onclick="reprow()"/>
     				</td>
     			</tr>
     			
     			</table>
     	</s:form>
	</body>
	<script>
	Event.observe(window, "load", function() {
		reprow();
	}, true);

	
	var diag = null;
	function reprow(){
		var h = document.body.clientHeight;
		diag = new Dialog("expwindows");
		diag.Title = '专家信息修改';
		diag.Width = 990;
		diag.Height = getSize().h-100;
		diag.isClose = false;
		diag.URL = "exp!preUpdate.do?rcid="+$('rcid').value+"&opttype=3"+"&winid=expwindows";
		diag.show();
	}
	

	function refreshmm(){
		var iflen = document.body.getElementsByTagName("iframe").length;
		var iswh = false;
		for(var i=0;i<iflen ;i++){
			if (document.body.getElementsByTagName("iframe")[i].name=='_DialogFrame_expwindows'){
				iswh = true;
				break;
			}
		}
		if(iswh){
			document._DialogFrame_expwindows.refresh();
		}else{
			document.mainframe.mainframe.refresh();
		}
	}
	
	function refreshP(){
		var ajax = new AppAjax("login!expTb.do?wburl="+$('wburl').value+"&rcid="+$('rcid').value+"&wbuserid="+$('wbuserid').value+"&wbfldm="+$('wbfldm').value+"&wbappname="+$('wbappname').value,
			function (data){
				window.close();
			}
		).submit();
		
	}
	
	function closeWin(id){
		Dialog.getInstance(id).CancelButton.onclick.apply(Dialog.getInstance(id).CancelButton,[]);
	}
	</script>
</html>
