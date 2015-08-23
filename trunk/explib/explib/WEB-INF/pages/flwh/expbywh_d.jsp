<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
       <%@ include file="/common/meta.jsp"%>
		<style type="text/css">
			
			  .title {	background:#B8CDE3;	color:#003770;line-height:26px;height:26px;padding-left:15px;border:1px solid #fff;font-weight:bold;text-align:left;}
			  .fxtable{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
			  .fxtable2{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
			  .fxtable2 td.cls {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;height:20px;}
		      .fxtable td {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;}
		        a.button{background:url(images/button.gif);	display:block;color:#555555;font-weight:bold;height:30px;line-height:29px;margin-bottom:14px;text-decoration:none;width:191px;}
				a:hover.button{color:#0066CC;}
		      .lens{ no-repeat 10px 8px;text-indent:30px;display:block;}
		      .btn {
		         height:22px;
		      }
		    .editor {
			    margin-top: 5px;
			    margin-bottom: 5px;
		    }
		</style>
	</head>
	<s:form name="czrcForm" action="expflwh!doAddflwh.do" method="post">
	<body >
		<s:hidden name="flmap.fldm"></s:hidden>
		<s:hidden name="flmap.type"></s:hidden>
		<s:hidden name="flbz" value="1"></s:hidden>
		<s:hidden name="option"></s:hidden>
		<s:hidden name="winid" id="winid"></s:hidden>
	    <div id="fxtableContainer">
	    	<table cellspacing="0" class="fxtable"  style="margin-bottom:10px;">
	        	<tr>
	        		<td class="bt">专家标引名称<font color=red>*</font></td>
	        		<td><s:textfield name="flmap.flmc" id="flmap.flmc"/></td>
	        	</tr>
				<tr>
	        		<td class="bt">备注信息</td>
	        		<td><s:textarea name="flmap.sm" cols="20" rows="4"></s:textarea></td>
	        	</tr>	        	
	        </table>
	   </div>
	   
	   
	   <div class="footer">			
	       <input type="button" class="button" value="保  存" onclick="doSave();"/>
           <input type="button" name="resetBtn" class="button" value="关  闭" onclick="closeWin();"/>
           &nbsp&nbsp
       </div>
	</body>
	</s:form>
	<script type="text/javascript">
	
	$('flmap.flmc').focus();
	
	function doSave(){
		if($('option').value == 1){
			var ajax = new AppAjax("expflwh!addbywh.do",save_Back).submitForm("czrcForm");
		}else{
			var ajax = new AppAjax("expflwh!repbywh.do",save_Back).submitForm("czrcForm");
		}
	}
		
	function save_Back(data){
		if(data.message.code >0){
			alert("保存成功！");
			window.parent.refreshother(1);
			closeWin();	
		}else{
			alert(data.message.text);
		}
	}
	</script>
</html>