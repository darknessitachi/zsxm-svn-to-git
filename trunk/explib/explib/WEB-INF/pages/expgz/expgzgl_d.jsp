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
		<s:hidden name="query.type"></s:hidden>
		<input type="hidden" name="query.dictbh" value="${query.dictbh}">
		<s:hidden name="winid" id="winid"></s:hidden>
	    <div id="fxtableContainer" class="fxtableContainer">
	    	<table cellpadding="0" class="fxtable" cellspacing="0" >
			  	<tr>
			  	<td class="bt">
			  		字典类别：
			  	</td>
			  	<td>
			  		${listMap.lbmap.LBNAME}
			  		<input type="hidden" name="query.lbid" value="${listMap.lbmap.LBID}">
			  	</td>
			  	</tr>
			  	<c:if test="${listMap.lbmap.LBFRAME == 1}">
			  		<tr>
				  		<td class="bt">
				  			上级字典：
					  	</td>
					  	<td>
					  		跟踪内容管理
					  	</td>
			  		</tr>
			  	</c:if>
			  	<tr>
			  		<td class="bt">
			  			字典名称：
				  	</td>
				  	<td>
				  		<input type="text" name="query.dictname" id="dictnameid">
				  	</td>
			  	</tr>
			</table>  	
	   	   
	   </div>
	   
	   <div class="footer">
        	<input type="button" class="button3" value="保  存" onclick="savedict()"/>
        	<input type="button"  class="button3" value="关  闭" onclick="closeWin();"/>
        	&nbsp;&nbsp;
       </div>
	</body>
	</s:form>
	<script type="text/javascript">
	$('dictnameid').focus();
	function savedict(){
		
		if(!document.getElementById('dictnameid').value){
			alert('请输入字典名称');
			return;
		}
		var ajax = new AppAjax("expgz!saveDict.do",save_Back);
		ajax.submitForm("emasForm");
	}
		
	function save_Back(data){
		if(data.message.code >0){
		    
			window.parent.refreshmm();
			closeWin();	
		}else{
			alert(data.message.text);
		}
	}
	</script>
</html>