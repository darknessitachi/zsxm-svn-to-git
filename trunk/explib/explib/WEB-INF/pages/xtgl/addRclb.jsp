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
	<s:form name="emasForm" action="xtmanager!saveDict.do" method="post">
	<body >
		<input type="hidden" name="query.type" value="${query.type}">
		<input type="hidden" name="query.lbdm" value="${query.lbdm}">
		<s:component template="xtitle" theme="app" value='新增专家类别'></s:component>
	    <div id="xwincontent">
	   	    <div  style="display:block;border: 1px solid #C1DAD7;" id="fxTjDiv">
	    		<div class="title">
		    		<span style='margin-left:0px;float:left'>
		    			上级字典：&nbsp;&nbsp;${listMap.pdictmap.LBMC}
	          		</span>
	    		</div>
	    	<div class="title">
		    	<span style='margin-left:0px;float:left'>
		    			字典名称：&nbsp;&nbsp;
		    			<input type="text" name="query.lbmc" id="lbmcid">
	          	</span>
	    	</div>
	    	

	   </div>
	   </div>
	   
	   <div class="footer">
        	<input type="button" class="btn" value="保存" onclick="savedict()"/>
        	<input type="button" name="resetBtn" class="btn" value="关  闭" onclick="closeWin(self.name);"/>
       </div>
	</body>
	</s:form>
	<script type="text/javascript">
		function savedict(){
			
			if(!document.getElementById('lbmcid').value){
				alert('请输入字典名称');
				return;
			}
			var ajax = new AppAjax("xtmanager!saveRclb.do",save_Back);
			ajax.setAsyn(false);
			ajax.submitForm("emasForm");
		}
		
	function save_Back(data){
		if(data.message.code >0){
		    var bh = data.message.map.lbdm;
		    var pbh = 'root';
		    if(bh.length > 3){
		    	pbh = bh.substr(0,bh.length-3);
		    }
			parent.mainFrameContainer.document.all.code.value = bh;
			parent.mainFrameContainer.document.all.pcode.value = pbh;
			parent.mainFrameContainer.document.all.codename.value = data.message.map.lbmc;
			parent.mainFrameContainer.document.all.insertTree.onclick();
			closeWin(self.name);	
		}else{
			alert(data.message.text);
		}
	}
	</script>
</html>