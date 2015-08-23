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
	<s:form name="emasForm" action="xtmanager!updteDict.do" method="post">
	<body >
		<input type="hidden" name="query.type" value="${query.type}">
		<input type="hidden" name="query.lbdm" value="${query.lbdm}">
		<s:component template="xtitle" theme="app" value='修改人才类别'></s:component>
	    <div id="xwincontent">
	
	    	<div class="title">
		    	<span style='margin-left:0px;float:left'>
		    			类别名称：&nbsp;&nbsp;
		    			<input type="text" id="lbmc" name="query.lbmc" value="${listMap.LBMC}">
	          	</span>
	    	</div>
	    	 
	   </div>
	   
	   <div class="footer">
        	<input type="button" class="btn" value="保存" onclick="editrclb()"/>
        	<input type="button" name="resetBtn" class="btn" value="关  闭" onclick="closeWin(self.name);"/>
       </div>
	</body>
	</s:form>
	<script type="text/javascript">
		function editrclb(){
			if(!document.getElementById('lbmc').value){
				alert('请输入人才类别名称！');
				return;
			}
			var ajax = new AppAjax("xtmanager!updteRclb.do",save_Back);
			ajax.submitForm("emasForm");
		}
		
	function save_Back(data){
		if(data.message.code >0){
			parent.mainFrameContainer.document.all.codename.value = document.getElementById("lbmc").value;
			parent.mainFrameContainer.document.all.updateTree.onclick();
			closeWin(self.name);	
		}else{
			alert(data.message.text);
		}
	}
	</script>
</html>