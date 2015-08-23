<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri = "http://fckeditor.net/tags-fckeditor" prefix="fck"%>

<html>
	<head>
		 <%@ include file="/common/meta.jsp"%>
		 <style>
		 .top{ background:url(images/skin0/index/top_bg.jpg); height:70px; width:960}
		.topbar{ background:url(images/skin0/index/time_bg.jpg); height:31px; width:100%}
		.fxtable{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
		 .fxtable td {border-right: 0px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;}
		 .zgTable{margin:1px;}
		 .td1{border: 1px solid #C1DAD7;
	background: #DEE5F8;
	font-size:12px;
	padding: 6px 6px 6px 12px;
	color: #4f6b72;}
		 .td2{border-bottom:1px dashed #CBCBCB;}
		 </style>
	</head>
	<s:if test="parMap.xxid!=null && parMap.xxid!= ''">
	   <s:hidden name="xwinFlag" value="y"/>
    </s:if>
	<body  style="overflow:auto;TEXT-ALIGN: center;background:#f0f8ff">
		<div style="width:980px;heigth:100%;background:white;" >
		<div class="top" style="MARGIN: 0px auto 0px;width:980px">
	      	<img src="images/skin0/index/top_lg.jpg" style="float:left" />
	   </div>
	   <table class="fxtable" cellspacing="0" cellpadding="0">
			<tr id ='lms'>
				
				<s:iterator value="lmlxList">
					<s:if test="lmid==parMap.lmid">
						<td style="text-align:left;background:#778899;font:bold 12px 'lucida Grande',Verdana;width:60;height:40;border-left: 1px solid #C1DAD7;border-right: 1px solid #C1DAD7;">
							<a href='rcxx!rcxxMore.do?parMap.lmid=<s:property value="lmid"/>' style="color: white;"><s:property value="lmmc"/></a>
						</td>
					</s:if>
					<s:else>
						<td style="text-align:left;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:60;height:40;border-left: 1px solid #C1DAD7;border-right: 1px solid #C1DAD7;">
							<a href='rcxx!rcxxMore.do?parMap.lmid=<s:property value="lmid"/>'><s:property value="lmmc"/></a>
						</td>
					</s:else>
				</s:iterator>
				
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;">
					<a href="javascript:;"></a>
				</td>
			</tr>
		</table>
	
		
		<div id="fckeditor" style="width:100%;overflow-y:auto;overflow-x:hidden;">
		   <form   name="xxzgForm" method="post"  enctype="multipart/form-data">
			<s:hidden name="parMap.xxid"/>
		   <table width="100%" class="zgTable">
		   	<tr>
		    	<td width="15%" class="td1">
		    		撰写栏目：    
		    	</td>
		    	<td  class="td2">
			    	<s:iterator value="outList">
								<input type="checkBox" name="lx" value='<s:property value="LMID"/>' <s:if test="LMID==parMap.lmid">checked</s:if>><s:property value="LMMC"/>
				     </s:iterator>
			    	    
		   		 </td>
		    </tr>
		     <tr>
			    <td class="td1">
			    	 标题：
			    </td>
			    <td class="td2">
			    	<input type="text" id="xxbt" value="<s:property value='parMap.xxbt'/>" style="width:400px"/>              
			    	 	<a href="javascript:sss();" style="margin-right:10px;">添加附件</a><a id="goAdd" href="javascript:addFj();" style="display:none">继续添加</a>
			    </td>
		    </tr>
			    <tr id="fj" style="display:none">
				    <td  colspan='2' >
				    	<table id="fjTable" style="background:#F5FAFA;width:100%">
				    		<tr>
				    			<td width="400px"> 附件1：<s:file name="upload" cssStyle="width:250px" onchange="uploadFile(this);"/></td><td></td>
				    		</tr>
				    	</table>
				    </td>
			    </tr>
			    
			
		      <tr>
			      <td colspan='2' id="abc">
						<fck:editor id="_fbxx" enableXHTML="true" height="450px;" basePath="widgets/fck/" 
							imageBrowserURL="../../../../widgets/fck/editor/filemanager/browser/default/browser.html?Type=Image&Connector=connectors/jsp/connector"
							linkBrowserURL="../../../../widgets/fck/editor/filemanager/browser/default/browser.html?Connector=connectors/jsp/connector"
							flashBrowserURL="../../../../widgets/fck/editor/filemanager/browser/default/browser.html?Type=Flash&Connector=connectors/jsp/connector"
							imageUploadURL="../../../../widgets/fck/editor/filemanager/upload/simpleuploader?Type=Image"
							linkUploadURL="../../../../widgets/fck/editor/filemanager/upload/simpleuploader?Type=File"
							flashUploadURL="../../../../widgets/fck/editor/filemanager/upload/simpleuploader?Type=Flash" >
						</fck:editor>
				  </td>
			</tr>
			
			 <tr>
			      <td colspan='2' align="center">
						<input type="button" value="&nbsp;&nbsp;&nbsp;保 存&nbsp;&nbsp;&nbsp;" style="margin-right:10px;" onclick="saveXxzg();">
						<input type="button" value="继 续 新 增" id="go_add" onclick="add();">
				  </td>
			</tr>
			</table>
		   </form>
		</div>
		<div id="hiddenDiv" style="display: none">
		   <TEXTAREA id="t_area">
									<s:property value='parMap.fbxx'/>
			</TEXTAREA>
		</div>
		
		<iframe name="uploadFrame" height="0px" width="opx">
		
		</iframe>
		</div>
	</body>
	<script language="javascript" type="text/javascript">
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
modifyPre();
function modifyPre(){
	if($("parMap.xxid")==null || $("parMap.xxid").value=="")return;
	$("go_add").style.display="none";
	document.getElementById("fckeditor").style.height = getSize().h - 28  - 2 + "px";
	$("_fbxx").innerText = $("t_area").value;
	if('<s:property value="parMap.fj"/>'!="0"){
				var obj = document.getElementById("fj");
				document.getElementById("fjTable").deleteRow(-1);
				obj.style.display="";
				document.getElementById("goAdd").style.display = "";
				<s:iterator value="mapOut.fjList" > 
					addFjModify('<s:property value="fjmc"/>','<s:property value="fj"/>');
				</s:iterator>
			
	}
	var objs = document.getElementsByName("lx");
	<s:iterator value="mapOut.lmList" > 
					setCheckBox(objs,'<s:property value="lmid"/>');
	</s:iterator>
	
	
}

function addFjModify(fileName,uploadOkFileName){
	var table = document.getElementById("fjTable");
		var tr = table.insertRow(-1);
	 	tr.setAttribute("upload","ok");
	 	tr.setAttribute("fj",uploadOkFileName);
	 	tr.setAttribute("fjmc",fileName);
	 	var td1 = tr.insertCell(0);
		td1.innerHTML = "<div style='width:300px;margin-left:10px;'>"+fileName+"</div>";
		td1.style.width="50%";
		var td2 = tr.insertCell(1);
		td2.innerHTML =  "<img src='images/skin0/other/del.jpg' title='删除此附件' style='cursor:hand;' onclick='delFj("+tr.rowIndex+");'/>";
 
}	

function setCheckBox(objs,val){
	var len = objs.length;
	for(var i=0;i<len;i++){
		if(objs[i].value == val){
			objs[i].checked = true;
			return;
		}
	}

}
function add(id){
	document.xxzgForm.action='rcxx!xxzg.do';
	document.xxzgForm.target="_self";
	document.xxzgForm.submit();
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
			document.xxzgForm.action = "rcxx!upload.do"
			document.xxzgForm.target = "uploadFrame";
			document.xxzgForm.submit();
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

function saveXxzg(){

	var lxs = getLxList();
	
	if(lxs.trim()==""){
		alert("请选择撰稿类型！");
		return;
	}
	if($("xxbt").value.trim()==""){
		alert("请填写撰稿标题！");
		return;
	}
	
	var fjs = getFjList();
	var oEditor = FCKeditorAPI.GetInstance("_fbxx") ;
	var ajax = new AppAjax("rcxx!saveXxzg.do",s_b);
		ajax.add("info_zg.xxbt",$("xxbt").value);
		ajax.add("info_zg.fbxx",oEditor.GetXHTML());
		ajax.add("parMap.lxList",lxs);
		ajax.add("parMap.fjList",fjs);
		ajax.add("info_zg.xxid",$("parMap.xxid").value);
		ajax.submit();
}

function s_b(data){
	if(data.code=="-1"){
		alert(data.text);
		return;
	}
	alert("信息发布成功！");
	window.location.href = 'rcxx!rcxxMore.do?parMap.lmid=<s:property value="parMap.lmid"/>';
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
</script>
</html>

