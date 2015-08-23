<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<title>常州市科技专家管理系统</title>
		<%@ include file="/common/meta.jsp"%>
		<script type="text/javascript" src="<c:url value="/js/comm.js"/>" ></script>
		<link rel="stylesheet" type="text/css" href="styles/expzj/style.css" />
		<!--[if IE 6]>
		<link rel="stylesheet" type="text/css" href="styles/expzj/iecss.css" />
		<![endif]-->
		<script src="Framework/Main.js" type="text/javascript"></script>
		<script type="text/javascript" src="js/boxOver.js"></script>
		<style>
			html,body {margin: 0px;padding: 0px;border: 0px;font-family: Verdana, Tahoma, "����";overflow: auto;}
		</style>
	</head>
<body>
<s:form name="emasForm" id="form1" method="post">
<div id="main_container">
	<div class="top_bar">
    	<div class="languages">
        	<div class="lang_text">添加至收藏加 | <a href="login!logoutZj.do"><font color=white>退出系统</font></a></div>
        </div>
    </div>
	<div id="header"></div>
    
    <div id="main_content"> 
            <div id="menu_tab">
            	<div class="left_menu_corner"></div>
                <ul class="menu">
                     <li><a href="frame!indexzj.do" class="nav1">首页</a></li>
                     <li class="divider"></li>
                     <li><a href="exp!expzj.do" class="nav4">信息维护</a></li>
                     <li class="divider"></li>
                    <li><a href="message!getMessageZj.do" class="nav3">站内短信</a></li>
                     <li class="divider"></li>
                     <li><a href="xtgl!updatepasswordzj.do" class="nav2">修改密码</a></li>
                     <li class="divider"></li>
                     
                </ul>

             	<div class="right_menu_corner"></div>
            </div><!-- end of menu tab -->
			
			<div style="height:10px;width:0px"></div>
			<div class="center_title_bar2">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   				<span>
   					
   					<input type="button" class="sbutm" onMouseOver="this.className ='sbutm_over'" onMouseout="this.className='sbutm'" value="发送短信" onclick="javascript:sendMsg();"/>
   				</span>
   			 </div>
   			 
			<s:hidden name="query.mtodm" id='q_mtodm'></s:hidden>
			<s:hidden name="query.mtype" id='q_mtype'></s:hidden>
			
			
			<div id="_tab" style="width:1000px;height:580px;border-top: solid 1px #bacddc;padding: 10px 0 10px 0;">
					<div class="Menubox">
						<ul>
						   <li id="two1" class="hover" onclick="setTab('two',1,3)" >收件箱</li>
						   <li id="two2" onclick="setTab('two',2,3)" >发件箱</li>
						   <li id="two3" onclick="setTab('two',3,3)" >重要邮件</li>
						</ul>
					</div>
					 <div class="TabContentbox">  
					   <div id="con_two_1" >
						   
						    <div class="butbar" id="butbar">
								<div class="left">
										<input type="button"  class="button" value="刷 新" id="b_message" onclick="getmessage(0)">
										<input type="button" class="button"  value="查 看" onclick="openMessage(1)">
										<input type="button" class="button" value="删 除" onclick="deleteMessage(1)">
										<input type="button" class="button" value="移至重要邮件" onclick="doremove(1)">
								</div>
						   </div>
		
							<div>
								<div id="bjmainplan" class="tableContainer"></div>
								<div id="pagerContainer" class="page" style="text-align:center;"></div>
					        </div>	
					   </div>
					   
					   <div id="con_two_2" style="display:none">
					   		<div class="butbar" id="butbar">
								<div class="left">
										 <input type="button" class="button" value="刷 新" id="b_sendmessage" onclick="getsendmessage(0)">
										 <input type="button" class="button" value="查 看" onclick="openMessage(0)">
										 <input type="button" class="button" value="删 除" onclick="deleteMessage(0)">
								</div>
						   </div>
					  
							<div>
								 <div id="sendmessage_d" class="tableContainer"></div>
								 <div id="pagerContainer_send" class="page" style="text-align:center;"></div>
					        </div>	 
					   </div>
					   
					   <div id="con_two_3" style="display:none">
					   		<div class="butbar" id="butbar">
								<div class="left">
										 <input type="button" class="button" value="刷 新" id="b_sendmessage" onclick="getZymessage(0)">
										 <input type="button" class="button" value="查 看" onclick="openMessage(0)">
										 <input type="button" class="button" value="删 除" onclick="deleteMessage(3)">
								</div>
						   </div>
					  
							<div>
								 <div id="zymainplan" class="tableContainer"></div>
								 <div id="pagerContainer_zy" class="page" style="text-align:center;"></div>
					        </div>	 
					   </div>
					   
					 </div>
					
				</div>
			
		
				<div class="footer2">
		   
					<font color=#676d77 size=2>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    	<br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					技术支持:常州市科技信息中心&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    	技术支持电话:0519-88101845&nbsp;&nbsp;&nbsp;&nbsp;85551608
					
					</font>
		       
		   		</div> 
		</div>
	</div>
	</s:form> 

</body>
<script type="text/javascript">


function setTab(name,cursel,n){
 for(i=1;i<=n;i++){
  var menu=document.getElementById(name+i);
  var con=document.getElementById("con_"+name+"_"+i);
  menu.className=i==cursel?"hover":"";
  con.style.display=i==cursel?"block":"none";
  
 }
 if(cursel == 1){
 	getmessage(0);
 }else if(cursel == 2){
 	getsendmessage(0);
 }else if(cursel == 3){
 	getZymessage(0);
 }
}

var cztype = '${query.cztype}';
if(cztype == '1'){
setTab('two',2,2);
}

getmessage(0);

function getmessage(start){
	var ajax = new AppAjax("message!getmessage.do",getmessage_callback);
		ajax.add("query.mtodm",$("q_mtodm").value);
		ajax.add("limit.length",15);
		ajax.add("limit.start",start || "0");
		ajax.submit();
}


function getmessage_callback(data){
	var tableStr = new StringBuffer('<table  cellspacing="0" ><thead>');
	tableStr.append('<tr><td align="center" style="width:7%;" ><input type="checkbox" id="allmessage" onclick="selectBoxAll()"/></td>');
	tableStr.append('<td align="center" style="width:5%;"><img src="images/skin0/other/mail.png"></td>');
	tableStr.append('<td style="width:10%;">发件人</td>');
	tableStr.append('<td style="width:64%">主题</td>');
	tableStr.append('<td style="width:11%">发送时间</td></tr></thead><tbody>');

	if(data && data["data"].length > 0){	
		var sdata = data["data"];
	    var dataLength = sdata.length;
		for(var i=0;i< dataLength ;i++){
			tableStr.append('<tr><td align="center"><input type="checkbox" name="tomessage" value='+sdata[i].mid+'> &nbsp;</td>');
			if(sdata[i].mread == 1){ //未读
				tableStr.append('<td align="center"><img src="images/skin0/other/mail.png">&nbsp;</td>');
			}else{
				tableStr.append('<td align="center"><img src="images/skin0/other/mail_open.png">&nbsp;</td>');
			}
			tableStr.append('<td>'+sdata[i].mfromname+'&nbsp;</td>');
			tableStr.append('<td> <a href="#" onclick="showMessage('+sdata[i].mid+',1)">'+sdata[i].mtitle+'&nbsp;</a></td>');
			tableStr.append('<td>'+sdata[i].mrq+'&nbsp;</td></tr>');
					}
	}else{
		tableStr.append('<tr><td colspan="13"><font color="red">查无数据!</font ></td></tr>');
	}
	tableStr.append('<tbody></table>');
	var  planstr =  tableStr.toString();

	$("bjmainplan").innerHTML=planstr;
	
	setPagination(data);
}

function setPagination(page){ 
	 if(!page)return;
	   var str = new StringBuffer("");
	 //  str.append(page.currentPageNo +"/"+page.totalPageCount);
	  //str.append(" 第<input type='text' class='pagebox' value=\""+page.currentPageNo+"\"/>/"+page.totalPageCount+"页");
	   var count = 0;
	   if(page.hasPreviousPage&&!page.hasNextPage){
		    str.append(" <input type='button' class='pagea-left' value=' ' onclick=\"goPage('"+count+"',\'1\',\'"+page.totalCount+"\',\'"+page.totalPageCount+"\');\"/> ");
		    str.append(" <input type='button' class='pagea-l' value=' '  onclick=\"goPage('"+count+"',\'"+(page.currentPageNo - 1)+"\',\'"+page.totalCount+"\',\'"+page.totalPageCount+"\');\"/>");
		   	str.append(" 第<input type='text' class='pagebox' onkeypress=\"if(event.keyCode==13)return onchange();\" onchange=\"goPage('"+count+"',this.value,\'"+page.totalCount+"\',\'"+page.totalPageCount+"\');\" value=\""+page.currentPageNo+"\"/>/"+page.totalPageCount+"页");
		    str.append(" <input type='button' class='pagea-r' value=' '/>");
		    str.append(" <input type='button' class='pagea-right' value=' '/>");
		}
		if(page.hasNextPage&&!page.hasPreviousPage){
			str.append(" <input type='button' class='pagea-left' value=' ' /> ");
		    str.append(" <input type='button' class='pagea-l' value=' '  />");
		   	str.append(" 第<input type='text' class='pagebox' onkeypress=\"if(event.keyCode==13)return onchange();\" onchange=\"goPage('"+count+"',this.value,\'"+page.totalCount+"\',\'"+page.totalPageCount+"\');\"  value=\""+page.currentPageNo+"\"/>/"+page.totalPageCount+"页");
		    str.append(" <input type='button' class='pagea-r' value=' ' onclick=\"goPage('"+count+"',\'"+(page.currentPageNo + 1)+"\',\'"+page.totalCount+"\',\'"+page.totalPageCount+"\')\"/>");
		    str.append(" <input type='button' class='pagea-right' value=' ' onclick=\"goPage('"+count+"',\'"+(page.totalPageCount)+"\',\'"+page.totalCount+"\',\'"+page.totalPageCount+"\')\"/>");
		}
		if(page.hasNextPage&&page.hasPreviousPage){
			str.append(" <input type='button' class='pagea-left' value=' '  onclick=\"goPage('"+count+"',\'1\',\'"+page.totalCount+"\')\"/> ");
		    str.append(" <input type='button' class='pagea-l' value=' ' onclick=\"goPage('"+count+"',\'"+(page.currentPageNo - 1)+"\',\'"+page.totalCount+"\')\" />");
		   	str.append(" 第<input type='text' class='pagebox' onkeypress=\"if(event.keyCode==13)return onchange();\" onchange=\"goPage('"+count+"',this.value,\'"+page.totalCount+"\',\'"+page.totalPageCount+"\');\"  value=\""+page.currentPageNo+"\"/>/"+page.totalPageCount+"页");
		    str.append(" <input type='button' class='pagea-r' value=' ' onclick=\"goPage('"+count+"',\'"+(page.currentPageNo + 1)+"\',\'"+page.totalCount+"\',\'"+page.totalPageCount+"\')\"/>");
		    str.append(" <input type='button' class='pagea-right' value=' ' onclick=\"goPage('"+count+"',\'"+(page.totalPageCount)+"\',\'"+page.totalCount+"\',\'"+page.totalPageCount+"\')\"/>");
		    
		}
		str.append("");
		

		$("pagerContainer").innerHTML = str.toString();
  }
   
 function goPage(indexed,start,size){
   getmessage(start);
}  


function selectBoxAll(){
	var obj = document.getElementsByName("tomessage");//gn("subjectCheckBox");
	var len = obj.length;
	for(var i=0;i<len;i++){
		if($("allmessage").checked){
				obj[i].checked = true;
		}else{
				obj[i].checked = false;
		}
	}
}

//getsendmessage(0);

function getsendmessage(start){
	var ajax = new AppAjax("message!getsendmessage.do",getsendmessage_callback);
		ajax.add("query.mfromdm",$("q_mtodm").value);
		ajax.add("limit.length",15);
		ajax.add("limit.start",start || "0");
		ajax.submit();
}


function getsendmessage_callback(data){

	var tableStr = new StringBuffer('<table align="center"  ><thead>');
	tableStr.append('<tr><td style="width:7%;"><input type="checkbox" id="allsendmessage" onclick="selectSendBoxAll()"/></td>');
	tableStr.append('<td style="width:10%;">收件人</td>');
	tableStr.append('<td style="width:71%;">主题</td>');
	tableStr.append('<td style="width:11%;">发送时间</td></tr></thead>');

	if(data && data["data"].length > 0){	
		var sdata = data["data"];
	    var dataLength = sdata.length;
		for(var i=0;i< dataLength ;i++){
			tableStr.append('<tr><td align="center"><input type="checkbox" name="tosendmessage" value='+sdata[i].mid+'> &nbsp;</td>');
			tableStr.append('<td>'+sdata[i].mtoname+'&nbsp;</td>');
			tableStr.append('<td><a href="#" onclick="showMessage('+sdata[i].mid+',0)">'+sdata[i].mtitle+'&nbsp;</a></td>');
			tableStr.append('<td>'+sdata[i].mrq+'&nbsp;</td></tr>');
					}
	}else{
		tableStr.append('<tr><td colspan="13"><font color="red">查无数据!</font ></td></tr>');
	}
	tableStr.append('</table>');
	var  planstr =  tableStr.toString();

	$("sendmessage_d").innerHTML=planstr;
	
	setSendPagination(data);
}

function setSendPagination(page){ 
		
		 if(!page)return;
	   var str = new StringBuffer("");
	 //  str.append(page.currentPageNo +"/"+page.totalPageCount);
	   //str.append(" 第<input type='text' class='pagebox' value=\""+page.currentPageNo+"\"/>/"+page.totalPageCount+"页");
	   var count = 0;
	   if(page.hasPreviousPage&&!page.hasNextPage){
		    str.append(" <input type='button' class='pagea-left' value=' ' onclick=\"javascript:goSendPage('"+count+"',\'1\',\'"+page.totalCount+"\',\'"+page.totalPageCount+"\');\"/> ");
		    str.append(" <input type='button' class='pagea-l' value=' '  onclick=\"javascript:goSendPage('"+count+"',\'"+(page.currentPageNo - 1)+"\',\'"+page.totalCount+"\',\'"+page.totalPageCount+"\');\"/>");
		   	str.append(" 第<input type='text' class='pagebox' onkeypress=\"if(event.keyCode==13)return onchange();\" onchange=\"javascript:goSendPage('"+count+"',this.value,\'"+page.totalCount+"\',\'"+page.totalPageCount+"\');\" value=\""+page.currentPageNo+"\"/>/"+page.totalPageCount+"页");
		    str.append(" <input type='button' class='pagea-r' value=' '/>");
		    str.append(" <input type='button' class='pagea-right' value=' '/>");
		}
		if(page.hasNextPage&&!page.hasPreviousPage){
			str.append(" <input type='button' class='pagea-left' value=' ' /> ");
		    str.append(" <input type='button' class='pagea-l' value=' '  />");
		   	str.append(" 第<input type='text' class='pagebox' onkeypress=\"if(event.keyCode==13)return onchange();\" onchange=\"javascript:goSendPage('"+count+"',this.value,\'"+page.totalCount+"\',\'"+page.totalPageCount+"\');\"  value=\""+page.currentPageNo+"\"/>/"+page.totalPageCount+"页");
		    str.append(" <input type='button' class='pagea-r' value=' ' onclick=\"javascript:goSendPage('"+count+"',\'"+(page.currentPageNo + 1)+"\',\'"+page.totalCount+"\',\'"+page.totalPageCount+"\')\"/>");
		    str.append(" <input type='button' class='pagea-right' value=' ' onclick=\"javascript:goSendPage('"+count+"',\'"+(page.totalPageCount)+"\',\'"+page.totalCount+"\',\'"+page.totalPageCount+"\')\"/>");
		}
		if(page.hasNextPage&&page.hasPreviousPage){
			str.append(" <input type='button' class='pagea-left' value=' '  onclick=\"javascript:goSendPage('"+count+"',\'1\',\'"+page.totalCount+"\')\"/> ");
		    str.append(" <input type='button' class='pagea-l' value=' ' onclick=\"javascript:goSendPage('"+count+"',\'"+(page.currentPageNo - 1)+"\',\'"+page.totalCount+"\')\" />");
		   	str.append(" 第<input type='text' class='pagebox' onkeypress=\"if(event.keyCode==13)return onchange();\" onchange=\"javascript:goSendPage('"+count+"',this.value,\'"+page.totalCount+"\',\'"+page.totalPageCount+"\');\"  value=\""+page.currentPageNo+"\"/>/"+page.totalPageCount+"页");
		    str.append(" <input type='button' class='pagea-r' value=' ' onclick=\"javascript:goSendPage('"+count+"',\'"+(page.currentPageNo + 1)+"\',\'"+page.totalCount+"\',\'"+page.totalPageCount+"\')\"/>");
		    str.append(" <input type='button' class='pagea-right' value=' ' onclick=\"javascript:goSendPage('"+count+"',\'"+(page.totalPageCount)+"\',\'"+page.totalCount+"\',\'"+page.totalPageCount+"\')\"/>");
		    
		}
		str.append("");


		$("pagerContainer_send").innerHTML = str.toString();
  }
   
 function goSendPage(indexed,start,size){
   getsendmessage(start);
}  

function doremove(){
	var rcsel = COM.checkbox('tomessage');
	if(rcsel.length == 0){
		alert("请选择相应的信件!");
		return false;
	}
	var r = new Array();
	for(var i=0;i<rcsel.length;i++){
		r.push(rcsel[i].v);
	}
	
	var ajax = new AppAjax("message!doRemoveMessage.do?mid="+r,
		function (data){
			if(data != null){
				if(data.message.code > 0){
					alert('移动成功！');
					getmessage(0);
				}else{
					alert(data.message.text);
				}
			}
		}
	);
	ajax.submit();
}

//getZymessage(0);
function getZymessage(start){
	
	var ajax = new AppAjax("message!getremovemessage.do",getzymessage_callback);
		ajax.add("query.mtodm",$("q_mtodm").value);
		ajax.add("limit.length",15);
		ajax.add("limit.start",start || "0");
		ajax.submit();
}


function getzymessage_callback(data){
	var tableStr = new StringBuffer('<table  cellspacing="0" ><thead>');
	tableStr.append('<tr><td align="center" style="width:7%;" ><input type="checkbox" id="allmessage" onclick="selectBoxAll()"/></td>');
	tableStr.append('<td align="center" style="width:5%;"><img src="images/skin0/other/mail.png"></td>');
	tableStr.append('<td style="width:10%;">发件人</td>');
	tableStr.append('<td style="width:64%">主题</td>');
	tableStr.append('<td style="width:11%">发送时间</td></tr></thead><tbody>');

	if(data && data["data"].length > 0){	
		var sdata = data["data"];
	    var dataLength = sdata.length;
		for(var i=0;i< dataLength ;i++){
			tableStr.append('<tr><td align="center"><input type="checkbox" name="tozymessage" value='+sdata[i].mid+'> &nbsp;</td>');
			if(sdata[i].mread == 1){ //未读
				tableStr.append('<td align="center"><img src="images/skin0/other/mail.png">&nbsp;</td>');
			}else{
				tableStr.append('<td align="center"><img src="images/skin0/other/mail_open.png">&nbsp;</td>');
			}
			tableStr.append('<td>'+sdata[i].mfromname+'&nbsp;</td>');
			tableStr.append('<td> <a href="#" onclick="showMessage('+sdata[i].mid+',1)">'+sdata[i].mtitle+'&nbsp;</a></td>');
			tableStr.append('<td>'+sdata[i].mrq+'&nbsp;</td></tr>');
					}
	}else{
		tableStr.append('<tr><td colspan="13"><font color="red">查无数据!</font ></td></tr>');
	}
	tableStr.append('<tbody></table>');
	var  planstr =  tableStr.toString();

	$("zymainplan").innerHTML=planstr;
	
	setzyPagination(data);
}

function setzyPagination(page){ 
	 if(!page)return;
	   var str = new StringBuffer("");
	 //  str.append(page.currentPageNo +"/"+page.totalPageCount);
	  //str.append(" 第<input type='text' class='pagebox' value=\""+page.currentPageNo+"\"/>/"+page.totalPageCount+"页");
	   var count = 0;
	   if(page.hasPreviousPage&&!page.hasNextPage){
		    str.append(" <input type='button' class='pagea-left' value=' ' onclick=\"gozyPage('"+count+"',\'1\',\'"+page.totalCount+"\',\'"+page.totalPageCount+"\');\"/> ");
		    str.append(" <input type='button' class='pagea-l' value=' '  onclick=\"gozyPage('"+count+"',\'"+(page.currentPageNo - 1)+"\',\'"+page.totalCount+"\',\'"+page.totalPageCount+"\');\"/>");
		   	str.append(" 第<input type='text' class='pagebox' onkeypress=\"if(event.keyCode==13)return onchange();\" onchange=\"gozyPage('"+count+"',this.value,\'"+page.totalCount+"\',\'"+page.totalPageCount+"\');\" value=\""+page.currentPageNo+"\"/>/"+page.totalPageCount+"页");
		    str.append(" <input type='button' class='pagea-r' value=' '/>");
		    str.append(" <input type='button' class='pagea-right' value=' '/>");
		}
		if(page.hasNextPage&&!page.hasPreviousPage){
			str.append(" <input type='button' class='pagea-left' value=' ' /> ");
		    str.append(" <input type='button' class='pagea-l' value=' '  />");
		   	str.append(" 第<input type='text' class='pagebox' onkeypress=\"if(event.keyCode==13)return onchange();\" onchange=\"gozyPage('"+count+"',this.value,\'"+page.totalCount+"\',\'"+page.totalPageCount+"\');\"  value=\""+page.currentPageNo+"\"/>/"+page.totalPageCount+"页");
		    str.append(" <input type='button' class='pagea-r' value=' ' onclick=\"gozyPage('"+count+"',\'"+(page.currentPageNo + 1)+"\',\'"+page.totalCount+"\',\'"+page.totalPageCount+"\')\"/>");
		    str.append(" <input type='button' class='pagea-right' value=' ' onclick=\"gozyPage('"+count+"',\'"+(page.totalPageCount)+"\',\'"+page.totalCount+"\',\'"+page.totalPageCount+"\')\"/>");
		}
		if(page.hasNextPage&&page.hasPreviousPage){
			str.append(" <input type='button' class='pagea-left' value=' '  onclick=\"gozyPage('"+count+"',\'1\',\'"+page.totalCount+"\')\"/> ");
		    str.append(" <input type='button' class='pagea-l' value=' ' onclick=\"gozyPage('"+count+"',\'"+(page.currentPageNo - 1)+"\',\'"+page.totalCount+"\')\" />");
		   	str.append(" 第<input type='text' class='pagebox' onkeypress=\"if(event.keyCode==13)return onchange();\" onchange=\"gozyPage('"+count+"',this.value,\'"+page.totalCount+"\',\'"+page.totalPageCount+"\');\"  value=\""+page.currentPageNo+"\"/>/"+page.totalPageCount+"页");
		    str.append(" <input type='button' class='pagea-r' value=' ' onclick=\"gozyPage('"+count+"',\'"+(page.currentPageNo + 1)+"\',\'"+page.totalCount+"\',\'"+page.totalPageCount+"\')\"/>");
		    str.append(" <input type='button' class='pagea-right' value=' ' onclick=\"gozyPage('"+count+"',\'"+(page.totalPageCount)+"\',\'"+page.totalCount+"\',\'"+page.totalPageCount+"\')\"/>");
		    
		}
		str.append("");
		$("pagerContainer_zy").innerHTML = str.toString();
  }
   
 function gozyPage(indexed,start,size){
   getZymessage(start);
}  



function selectSendBoxAll(){
	var obj = document.getElementsByName("tosendmessage");//gn("subjectCheckBox");
	var len = obj.length;
	for(var i=0;i<len;i++){
		if($("allsendmessage").checked){
				obj[i].checked = true;
		}else{
				obj[i].checked = false;
		}
	}
}

	function deleteMessage(type){
		var strid = '';
		var bjxsboxs ;
		if(type == '1'){ //收件箱
			bjxsboxs = document.getElementsByName("tomessage");
		}else if(type == '0'){ //发件箱
			bjxsboxs = document.getElementsByName("tosendmessage");
		}else if(type == '3'){
			bjxsboxs = document.getElementsByName("tozymessage");
		}
		var clen = 0;
		var len = bjxsboxs.length;
			for(var i=0;i<len;i++){
				if(bjxsboxs[i].checked){
					strid += ','+bjxsboxs[i].value;
					clen = clen + 1; 
				}
		}
			if(strid == ''){
				alert('请选择要删除的短信！');
				return ;
			}
			 strid = strid.substr(1);
		
		if(confirm("确定要删除"+clen+"条短信吗！"))
  		{
			var ajax = new AppAjax("message!deleteMessage.do",operatecall_Back);
			ajax.add("query.strid",strid);
			ajax.add("query.userdm",$('q_mtodm').value);
			ajax.setAsyn(false);
			ajax.submit();
			
			if(type == '1'){ //收件箱
				getmessage(0);
			}else if(type == '0'){ //发件箱
				getsendmessage(0);
			}else if(type == '3'){
				getZymessage(0);
			}
		}	
	} 	
		
function operatecall_Back(data){
	if(data.message != null ){
				if(data.message.code > 0){
					alert("短信删除成功！");
					
				}else{
					alert(data.message.text);
				}
			}
		}
		
function showMessage(messageid,type){
	if(type == 1){
		openWin('message!showMessage.do?query.userdm='+$('q_mtodm').value+'&query.type='+type+'&query.messageid='+messageid,'600','550')
	}else{
		openWin('message!showMessage.do?query.userdm='+$('q_mtodm').value+'&query.type='+type+'&query.messageid='+messageid,'600','400')
	}
}		


function openMessage(type){
	 var bjxsboxs;
	if(type == '1'){ //收件箱
		bjxsboxs = document.getElementsByName("tomessage");
	}else{ //发件箱
		bjxsboxs = document.getElementsByName("tosendmessage");
	}
	var len = bjxsboxs.length;
	var size = 0 ;
	for(var i=0;i<len;i++){
		if(bjxsboxs[i].checked){
			size = size + 1; 
		}
	}
	if(size == 0){
		alert('请选择要查看的短信！');
		return ;
	}
	if(size > 1){
		alert('你只能选择一条短信进行查看！');
		return ;
	}
	var id =bjxsboxs[0].value;
	
	if(type == 1){
		openWin('message!showMessage.do?query.userdm='+$('q_mtodm').value+'&query.type='+type+'&query.messageid='+id,'600','550')
	}else{
		openWin('message!showMessage.do?query.userdm='+$('q_mtodm').value+'&query.type='+type+'&query.messageid='+id,'600','400')
	}
}
function closeWin(id){
	Dialog.getInstance(id).CancelButton.onclick.apply(Dialog.getInstance(id).CancelButton,[]);
}

var diag = null;
function sendMsg(){
	diag = new Dialog("messageSendZj");
	diag.Title = "选择用户";
	diag.Width = 800;
	diag.Height = 400;
	diag.ShowMessageRow=false;
	diag.MessageTitle = "请选择相应的工作人员";
	diag.Message = "请点击《选择》选择相应的工作人员，发送信息 ";
	diag.URL = 'message!sendMessage.do?winid=messageSendZj';
	diag.show();
}
</script>

	
</html>

