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
	<s:form name="emasForm" action="expgz!saveDict.do" method="post">
	<body >
		<s:hidden name="fldm"></s:hidden>
		<s:hidden name="winid" id="winid"></s:hidden>
	    <div id="fxtableContainer" class="fxtableContainer">
	    	<table cellpadding="0" class="fxtable" cellspacing="0" >
	    		<tr>
						<td width=40px>选</td>
						<td width=50px>代码</td>
						<td>
							名称
						</td>
				</tr>
				
				<s:iterator value="qlist">
					<tr>
						<td width=40px>
							<input name="dmkey" id="dmkey<s:property value="lbid"/>" type="checkbox" value="<s:property value="lbid"/>"/>
						</td>
						<td><s:property value="lbid"/></td>
						<td><s:property value="lbname"/></td>
					</tr>
				</s:iterator>
	    	</table>
	   </div>
	   
	   <div class="footer">
        	<input type="button" class="button3" value="保  存" onclick="saveDlb()"/>
        	<input type="button"  class="button3" value="关  闭" onclick="closeWin();"/>
        	&nbsp;&nbsp;
       </div>
	</body>
	</s:form>
	<script type="text/javascript">
	$('fxtableContainer').style.height = getSize().h - 50;
	
	Event.observe(window, "load", function() {
		<s:iterator value="flqlist">
			$('dmkey<s:property value="lbid"/>').checked = true;
		</s:iterator>
	}, true);
	
	function saveDlb(){
		var ajax = new AppAjax("xtgl!doSaveDlbFldm.do",save_Back);
		ajax.submitForm("emasForm");
	}
		
	function save_Back(data){
		if(data.message.code >0){
		    alert('保存成功');
			window.parent.refreshP();
			closeWin();	
		}else{
			alert(data.message.text);
		}
	}
	</script>
</html>