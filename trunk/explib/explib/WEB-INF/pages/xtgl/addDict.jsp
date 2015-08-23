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
		
		<s:hidden name="query.dictbh" id="query.dictbh"></s:hidden>	
		<s:hidden name="query.type" id="query.type"></s:hidden>	
		<s:hidden name="winid" id="winid"></s:hidden>	
	    <div id="xwincontent">
	   	    <div  style="display:block;border: 1px solid #C1DAD7;" id="fxTjDiv">
	    	<div class="title">
	    		<span style='margin-left:0px;float:left'>
	    			字典类别：&nbsp;&nbsp;
	    			<input type="hidden" name="query.lbid" value="<s:property value="listMap.lbmap.lbid"/>">
          		</span>
	    	</div>
	    	
	    	<div class="title">
		    	<span style='margin-left:0px;float:left'>
		    			字典名称：
		    			<input type="text" name="query.dictname" id="dictnameid">
	          	</span>
	    	</div>
	   </div>
	   </div>
	   
	   <div class="footer">
        	<input type="button" class="button3"  value="保   存" onclick="savedict()"/>
        	<input type="button" name="resetBtn" class="button3"  value="关  闭" onclick="closeWin();"/>
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
			var ajax = new AppAjax("xtgl!saveDict.do",save_Back);
			ajax.setAsyn(false);
			ajax.submitForm("emasForm");
		}
		
	function save_Back(data){
		if(data.message.code >0){
		    var bh = data.message.map.dictbh;
		    var pbh = 'root';
		    if(bh.length > 3){
		    	pbh = bh.substr(0,bh.length-3);
		    }
			window.parent.refreshother(1);
			closeWin();	
		}else{
			alert(data.message.text);
		}
	}
	</script>
</html>