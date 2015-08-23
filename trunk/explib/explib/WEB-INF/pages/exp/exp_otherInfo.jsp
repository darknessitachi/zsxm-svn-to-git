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
	<s:form name="czrcForm" action='exp!preZscq.do' method="post" >
	<s:hidden name="rcid"></s:hidden>
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="opttype"></s:hidden>
	<s:hidden name="winid" id="winid"></s:hidden>
 <!--个人信息 start-->
	<div class="title"  onclick=""><h2> 填写个人信息&nbsp&nbsp&nbsp&nbsp&nbsp
	
	</h2>
	<div class="img_right" id="s1" ></div>
	</div>
	<div class="butbar" id="butbar">
		<div class="left">	
			<input type="button" class="button3"  value="保  存" onclick="savexx()"/>
		</div>
	</div>	
	<div  class="tableContainer" id="tableContainer" style="width:100%">
		<s:textarea rows="25" cols="85" name="exp.otherinfo"></s:textarea>
	</div>
	<div class="footer" style="background:#efefef;">
	<s:if test="opttype==3">
		<input class="btn_submit1" type="button" value="退     出" onclick="javascript:closeWindows();"/>
	</s:if>
	<s:else>
		<s:if test="xtuser.loginbz == 1">
				<input class="btn_submit1" type="button" value="填写完成" onclick="javascript:parent.setRcid('',1,'exp!preExp.do',true);"/>
			</s:if>	
		</s:else>
	</div>	
	</s:form>
	</body>

	<script type="text/javascript">
		$('tableContainer').style.height = (getSize().h - 110)+"px"; 
		function closeWindows(){
			window.parent.parent.refreshP();
			closeWin();
		}
		var diag = null;
		
		function savexx(){
			var ajax = new AppAjax("exp!doSaveOtherinfo.do",sava_back).submitForm("czrcForm");
		}
		
		function sava_back(data){
			if(data.message.code >0){
				alert("保存成功!");
			}else{
				alert(data.message.text);
			}
		}
		
		function refresh(){
			document.all.czrcForm.submit();
		}
	</script>
</html>

