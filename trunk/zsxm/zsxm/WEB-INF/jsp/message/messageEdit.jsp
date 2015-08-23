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
		<script type="text/javascript" src="<c:url value="/js/czrc.js"/>" ></script>	
	  <script type="text/javascript" src="<c:url value="/js/kindeditor/kindeditor.js"/>" ></script>
	  <style type="text/css" rel="stylesheet">

		    .editor {
		    margin-top: 5px;
		    margin-bottom: 5px;
		    }
		    .button_s{height:28px;width:110px; background:url(images/skin0/other/04.gif) no-repeat ; TEXT-ALIGN: center; BORDER-RIGHT-WIDTH: 0px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; COLOR: #fff; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer;HEIGHT: 28px; FONT-SIZE: 14px; FONT-WEIGHT: bold}
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

<form   name="emasForm" action="message!saveMessage.do" method="post"  enctype="multipart/form-data">
	<body>
	    <s:hidden name="query.mnr" id="nr"></s:hidden>
	    <s:hidden name="query.mfromdm"  id="mfromdm"></s:hidden>
	    <s:hidden name="query.mfromname" ></s:hidden>
	    <div style="width:310px;float:left">
	    <div style="width:100%;background:#add8e6;text-align:right">
	    	<b>查询条件</b>
	    	<select name="query.name" style="width:80">
	    		<option value='cnname'>姓名</option>
	    		<option value='bm'>部门及职务</option>
	    	</select>
	    	<input name="query.value"  style="width:150" value=""/><br>
	    	<input type="button" class="button3" value="查    询" onclick="queryuser();">
	    	&nbsp&nbsp&nbsp&nbsp&nbsp
	    </div>
	    <div class="tableContainer" id="tableContainer" style="width:100%">
			<table id="dwTable" cellspacing="0">
				<thead>
					<tr>
						<td width="40">
						<input type="checkbox" id="allcheckbox" onclick="RC.checkboxAll('dmkey',this.checked)">
						</td>
						<td width="40%">
							(收件人)姓名
						</td>
						<td width="50%">
							部门及职务
						</td>
					</tr>
				</thead>
				<tbody id="ubodys">
					<s:iterator value="userlist">
						<tr>
							<td><input type="checkbox" name="dmkey" value='<s:property value="userdm"/>'/></td>
							<td><s:property value="usermc"/></td>
							<td><s:property value="bm"/></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
		</div>
	    <DIV id=main style="MARGIN: 20px auto;">
			<DIV class="gb_layout clearfix" style="width:100%">
					<DIV class="owner_blog_post" style="width:90%">
						<DIV class="blog_wrap_edit">
							<DIV  style=DISPLAY: block; CLEAR: both; CONTENT: "";ZOOM: 1>
							<!-- 王晓杰 2009-11-11 日去除
								<DIV style="FLOAT: left; FONT-SIZE: 12px; FONT-WEIGHT: normal; MARGIN-RIGHT: 30px">发给：<input type="text" id="textusermcs" style="width:450px" disabled>
								<input type="button" class="button1" value="选 择" onclick="userTree() ">
					    			<input type="hidden" name="query.usermcs" id="tosend" style="width:450px" >
					    			<input type="hidden" name="query.userdms" id="userdms"> </DIV>
					    	 -->		
					    	 	<input type="hidden" name="query.userdms" id="userdms">
					    	 	<input type="hidden" name="query.usermcs" id="tosend">
								<DIV style="FLOAT: left; FONT-SIZE: 12px; FONT-WEIGHT: normal; MARGIN-RIGHT: 30px">标题：<input type="text" name="query.mtitle"  id="mtitle" style="width:450px" class=input_txonchange="window.has_edit = true;">
								<input type="button" class="button_s" value="发   送" onclick="saveMessage()">
								<a href="javascript:sss();" style="margin-right:10px;">添加附件</a><a id="goAdd" href="javascript:addFj();" style="display:none">继续添加</a>
								<table>
									<tr id="fj" style="display:none">
									    <td  colspan='2' >
									    	<table id="fjTable" style="background:#F5FAFA;width:100%">
									    		<tr>
									    			<td width="400px"> 附件1：<s:file name="upload" cssStyle="width:250px" onchange="uploadFile(this);"/></td><td></td>
									    		</tr>
									    	</table>
									    </td>
								    </tr>
								</table>
								</DIV>
							</DIV>
							
							<DIV style="PADDING-BOTTOM: 0px; LINE-HEIGHT: 20px; TEXT-INDENT: 3px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px;  COLOR: #999; PADDING-TOP: 25px">
							    <div class="editor">
						      		<textarea id="content1" name="content" style="width:90%;height:340px;visibility:hidden;">
									</textarea>
						    	</div>
							</DIV>
						</DIV>
						<INPUT id=valid_input type=hidden name=valid_input> <INPUT id=post_hidden_from type=hidden name=post_hidden_from> <INPUT id=post_hidden_type type=hidden name=post_hidden_type> <INPUT id=post_hidden_draft_id type=hidden name=post_hidden_draft_id> 
				    </DIV>
				</DIV>
		  </DIV>
		  
		  
		  
	
	      
	
	</body>
	

<iframe name="uploadFrame" height="0px" width="opx">
</iframe>
</form>
<script type="text/javascript">
	$('main').style.width = getSize().w - 350;
	$('tableContainer').style.height = (getSize().h - 90)+"px"; 

	function sss(){
			var obj = document.getElementById("fj");
			if(obj.style.display=="none"){
				obj.style.display="";
				document.getElementById("goAdd").style.display = "";
			}else{
				obj.style.display = "none";
				document.getElementById("goAdd").style.display = "none";
			}
		}
		
	function addFj(){
		var table = document.getElementById("fjTable");
		var curRow = table.rows.length;
		var tr = table.insertRow(-1);
		var td1 = tr.insertCell(0);
		td1.style.width = "400px";
		td1.innerHTML="附件"+(curRow+1)+"："+'<s:file name="upload" cssStyle="width:250px" onchange="uploadFile(this);"/>';
		var td2 = tr.insertCell(1);
		td2.innerHTML="";
	}	
	
	var rowindex;
function uploadFile(obj){
			rowindex = (obj.parentNode.parentNode.rowIndex);
			var table = document.getElementById("fjTable");
			table.rows[rowindex].cells[0].style.width="50%";
			table.rows[rowindex].cells[1].innerHTML = "<img src='images/skin0/other/upload.gif'/>";
			document.emasForm.action = "xxgl!upload.do"
			document.emasForm.target = "uploadFrame";
			document.emasForm.submit();
		}
function setTdImage(uploadOkFileName){
	 	var table = document.getElementById("fjTable");
		var fileName = getFileName(table.rows[rowindex].cells[0].childNodes[1].value);

	 	table.rows[rowindex].setAttribute("upload","ok");
	 	table.rows[rowindex].setAttribute("fj",uploadOkFileName);
	 	table.rows[rowindex].setAttribute("fjmc",fileName);
		table.rows[rowindex].cells[0].innerHTML = "<div style='width:300px;margin-left:10px;'>"+fileName+"</div>";
		table.rows[rowindex].cells[0].style.width="50%";
		table.rows[rowindex].cells[1].innerHTML =  "<img src='images/skin0/other/del.jpg' title='删除此附件' style='cursor:hand;' onclick='delFj("+rowindex+");'/>";
}
  function delFj(ind){
	  $("fjTable").deleteRow(ind);
	  if(ind==0){
	  	var table = document.getElementById("fjTable");
		var curRow = table.rows.length;
		var tr = table.insertRow(-1);
		var td1 = tr.insertCell(0);
		td1.innerHTML="附件"+(curRow+1)+"："+'<s:file name="upload" cssStyle="width:400px" onchange="uploadFile(this);"/>';
		var td2 = tr.insertCell(1);
		td2.innerHTML="";
	  }
  }	
function getFjList(){
	var table = $("fjTable");
	var p=0;
	var fjList = "";
	for(var i=0;i<table.rows.length;i++){
		if(table.rows[i].getAttribute("upload") && table.rows[i].getAttribute("upload")=="ok"){
			if(p==0){
				fjList = table.rows[i].getAttribute("fj")+">"+table.rows[i].getAttribute("fjmc");
			}else{
				fjList += ":"+table.rows[i].getAttribute("fj")+">"+table.rows[i].getAttribute("fjmc");
			}
			p++;
		}
	
	}
	return fjList;

}
  function   getFileName(fullPath){   
	  var   str1=fullPath;   
	  var   regstr=/\\/;   
	  var   regresult=new   RegExp(regstr)   
	  var   sss=str1.split(regresult,'100');   
	  return sss[sss.length-1];
  }   
function getLxList(){
	var arr = new Array();
	var lxs = document.getElementsByName("lx");
	for(var i=0;i<lxs.length;i++){
		if(lxs[i].checked){
			arr.push(lxs[i].value);
			}
	}
	return arr.toString();
}
  	
	
	
	function saveMessage(){
		$('nr').value = KE.g['content1'].iframeDoc.body.innerHTML;
		var sel = RC.checkbox('dmkey');
		if(sel.length ==0){
			alert('请选择收件人！');
			return false;
		}
		
		/**
		if(!$('tosend').value){
			alert('请选择收件人!');
		    return;
		}
		**/
		if(!$('mtitle').value){
			alert('请输入短信标题!');
			return;
		}
		if(!$('nr').value){
			alert('请输入短信内容!');
			return;
		}
		var r = new Array();
		for(var i=0;i<sel.length;i++){
			r.push(sel[i].v);
		}
		$('userdms').value = r;
		var fjs = getFjList();
		var ajax = new AppAjax("message!saveMessage.do?parMap.fjList="+fjs,save_Back);
		ajax.setAsyn(false);
		ajax.submitForm("emasForm");
	}
	function save_Back(data){
		if(data.message.code >0){
			var obj = {mid:data.message.text,mtitle:$('mtitle').value};
			dwrDeclarePush.getMessage($('userdms').value,obj);
			alert("消息发送成功！");
		}else{
			alert(data.message.text);
		}
	}
	function userTree(){
		openWin('message!userTree.do',"200","400");
	}
	
	function queryuser(){
		var ajax = new AppAjax("message!getUser.do",user_Back).submitForm("emasForm");
	}
	
	function user_Back(data){
		var str = new StringBuffer();
		if(data != null ){
			var len = data.user.length;
			for(var i=0;i<len;i++){
				str.append('<tr>');
				str.append('<td><input type="checkbox" value="'+data.user[i].userdm+'"/></td>');
				str.append('<td>'+data.user[i].usermc+'</td>');
				str.append('<td>'+data.user[i].bm+'</td>');
				str.append('</tr>');
			}
		}
		$('ubodys').update(str.toString());
	}
</script>



</html>

