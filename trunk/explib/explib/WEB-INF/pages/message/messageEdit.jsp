<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<script type='text/javascript' src='dwr/engine.js'></script>
    	<script type='text/javascript' src='dwr/interface/dwrDeclarePush.js'> </script>
  		<script type='text/javascript' src='dwr/util.js'> </script>
  		<LINK rel=stylesheet type=text/css href="styles/common/app_blog_d_out.css" media=screen>
  		<LINK rel=stylesheet type=text/css href="styles/common/gb_color.css" media=screen>
		<script src="Framework/Main.js" type="text/javascript"></script>
	  <script type="text/javascript" src="<c:url value="/js/kindeditor/kindeditor.js"/>" ></script>
	  <style type="text/css" rel="stylesheet">

		    .editor {
		    margin-top: 5px;
		    margin-bottom: 5px;
		    }
	  </style>
	<script type="text/javascript">
    KE.show({
        id : 'content1',
        cssPath : './index.css',
        items : [
        'undo', 'redo', 'fontname', 'fontsize', 'textcolor', 'bgcolor', 'bold', 'italic', 'underline',
        'removeformat', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
        'insertunorderedlist']
    });

  </script>
	</head>
<s:form name="emasForm" action="message!sendMessage.do" method="post">
	<body>
	    <s:hidden name="query.mnr" id="nr"></s:hidden>
	    <s:hidden name="query.mfromdm"  id="mfromdm"></s:hidden>
	    <s:hidden name="query.mfromname" ></s:hidden>
	    <s:hidden name="winid" id="winid"></s:hidden>
	    <DIV id=main style="MARGIN: 10px auto;width:75%">
			<DIV class="gb_layout clearfix">
					<DIV class=owner_blog_post>
						<DIV class=blog_wrap_edit>
					
							<DIV  style=DISPLAY: block; CLEAR: both; CONTENT: "";ZOOM: 1>
						
								<DIV style="FLOAT: left; FONT-SIZE: 12px; FONT-WEIGHT: normal; MARGIN-RIGHT: 30px">发给：<input type="text" id="textusermcs" style="width:450px" disabled>
								<input type="button" class="button3" value="选    择" onclick="userTree() ">
					    			<input type="hidden" name="query.usermcs" id="tosend" style="width:450px" >
					    			    <input type="hidden" name="query.userdms" id="userdms"> </DIV>
								<DIV style="FLOAT: left; FONT-SIZE: 12px; FONT-WEIGHT: normal; MARGIN-RIGHT: 30px">标题：<input type="text" name="query.mtitle"  id="mtitle" style="width:450px" class=input_txonchange="window.has_edit = true;">
								<input type="button" class="button3" value="发    送" onclick="saveMessage()"></DIV>
								
							
							</DIV>
							
							<DIV style="PADDING-BOTTOM: 0px; LINE-HEIGHT: 20px; TEXT-INDENT: 3px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px;  COLOR: #999; PADDING-TOP: 25px">
									    <div class="editor">
								      		<textarea id="content1" name="content" style="width:85%;height:340px;visibility:hidden;">
											</textarea>
								    	</div>
								
							</DIV>
						</DIV>
						<INPUT id=valid_input type=hidden name=valid_input> <INPUT id=post_hidden_from type=hidden name=post_hidden_from> <INPUT id=post_hidden_type type=hidden name=post_hidden_type> <INPUT id=post_hidden_draft_id type=hidden name=post_hidden_draft_id> 
				    </DIV>
				</DIV>
		  </DIV>
		  
		  
		  
	
	      
	
	</body>
</s:form>	

<script type="text/javascript">
	
	function saveMessage(){
		$('nr').value = KE.g['content1'].iframeDoc.body.innerHTML;
		
		if(!$('tosend').value){
			alert('请选择收件人!');
		    return;
		}
		if(!$('mtitle').value){
			alert('请输入短信标题!');
			return;
		}
		if(!$('nr').value){
			alert('请输入短信内容!');
			return;
		}
		
		var ajax = new AppAjax("message!saveMessage.do",save_Back);
		ajax.setAsyn(false);
		ajax.submitForm("emasForm");
	}
	function save_Back(data){
		if(data.message.code >0){
			var obj = {mid:data.message.text,mtitle:$('mtitle').value};
			dwrDeclarePush.getMessage($('userdms').value,obj);
			alert("消息发送成功！");
			document.forms[0].submit();
		}else{
			alert(data.message.text);
		}
	}
	
	var diag = null;
	function userTree(){

		if('<s:property value="xtuser.loginbz"/>'=='1'){
			diag = new Dialog("messageSend");
			diag.Title = "选择用户";
			diag.Width = 900;
			diag.Height = 600;
			diag.ShowMessageRow=false;
			diag.URL = 'message!getAllUser.do?winid=messageSend';
		}else{
			diag = new Dialog("messageSend");
			diag.Title = "选择用户";
			diag.Width = 200;
			diag.Height = 400;
			diag.ShowMessageRow=false;
			diag.URL = 'message!userTree.do?winid=messageSend';
		}
		diag.show();
			
	}
	
</script>



</html>

