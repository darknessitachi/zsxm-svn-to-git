
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
	<title>常州高层人才管理中心-首页</title>
	<link rel="stylesheet" type="text/css" href="css/css.css"/>
	<style>
	body{ 
	font-family: "宋体", Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
}
html {
	
	}
a {
	font-size: 12px;
	color: #7c7c7a;
}
a:link {
	text-decoration: none;
	color: #7c7c7a;
}
a:visited {
	text-decoration: none;
	color: #7c7c7a;
}
a:hover {
	text-decoration: none;
	color: #006699;
}
a:active {
	text-decoration: none;
	color:#006699;
}
 a{blr:expression(this.onFocus=this.blur())} 
*{ margin:0px; padding:0px; list-style:none; font-size:12px}
	
.workbox{ float:left; width:48%; overflow:hidden; margin-left:5px; margin-top:3px; }
.workbox .title{ background:url(images/skin0/index/rightbox_title.png) repeat-x; height:23px}
.workbox .title .left_t{ float:left; background:url(images/skin0/index/rightbox_title_l.png) no-repeat; height:23px; width:20px;}
.workbox .title .center_t{ float:left; height:23px; line-height:23px; background:url(images/skin0/index/ico.gif) no-repeat 0px 6px; padding-left:15px}
.workbox .title .right_t{ float:right; background:url(images/skin0/index/rightbox_title_r.png) right top no-repeat; height:23px; width:50px;}
.workbox .title .right_t img{ margin-top:5px}
.workbox .neirong{ border:1pt solid #71a6e8; background:url(images/skin0/index/r_box_bg.png) bottom left repeat-x; height:160px}
.workbox .neirong ul{width:100%; margin:0px auto}
.workbox .neirong ul li{ background:url(images/skin0/index/line.gif) repeat-x left bottom; height:23px; line-height:23px}
.workbox .neirong ul li span{ float:right; color:#999999; padding-right:5px}
.workbox .neirong ul li a{ float:left; overflow:hidden; background:url(images/skin0/index/icon.gif) no-repeat 4px 8px ; padding-left:15px}
.workbox_big{ float:left; width:97%; overflow:hidden; margin-left:5px; margin-top:3px;  }
.workbox_big .title{ background:url(images/skin0/index/title_bg.png) repeat-x; height:28px; margin-right:4px}
.workbox_big .title .left_t{ float:left; background:url(images/skin0/index/title_left.png) no-repeat;line-height:28px; height:28px; width:200px; color:#fff; font-weight:700; padding-left:20px}
.workbox_big .title .right_t{ float:right; background:url(images/skin0/index/title_right.png) right top no-repeat; height:28px; width:50px;}
.workbox_big .title .right_t img{ margin-top:6px}
.workbox_big .neirong{ border:1pt solid #71a6e8; border-top:0px; background:url(images/skin0/index/r_box_bg.png) bottom left repeat-x; height:230px;margin-right:4px;margin-bottom:3px}
.workbox_big .neirong table th{ font-size:12px; text-align:center; font-weight:lighter; color:#0f2a3d; background:url(images/skin0/index/table_th.png) no-repeat top right; height:28px; line-height:28px}
.workbox_big .neirong table td{ text-align:center; line-height:19px; height:19px; border-bottom:1pt solid #cfe3ee; border-right:1pt solid #cfe3ee; padding:3px}
	
	</style>
</head>

<body style="overflow:auto;">
<input type="hidden"  id='q_mtodm' value="${xtuser.loginname}">
        <div class="workbox">
          <div class="title">
              <div class="left_t"></div>
              <div class="center_t">通知通告</div>
              <div class="right_t"><a href="#"><img src="images/skin0/index/more1.png" border="0"/></a></div>
          </div>
          <div class="neirong" id="xxzg">
          
          </div>
        </div>
        
        <div class="workbox">
          <div class="title">
              <div class="left_t"></div>
              <div class="center_t">园区新闻</div>
              <div class="right_t"><a href="#"><img src="images/skin0/index/more1.png" border="0"/></a></div>
          </div>
          <div class="neirong" id="xw">
         
          </div>
        </div>
        
        <div class="workbox">
          <div class="title">
              <div class="left_t"></div>
              <div class="center_t">一周安排</div>
              <div class="right_t"><a href="#"><img src="images/skin0/index/more1.png" border="0"/></a></div>
          </div>
          <div class="neirong" id="tz">
          
          </div>
        </div>
        
        <div class="workbox">
          <div class="title">
              <div class="left_t"></div>
              <div class="center_t">我的日程</div>
              <div class="right_t"> <a href="#"><img src="images/skin0/index/more1.png" border="0"/></a></div>
          </div>
          <div class="neirong" id="rc">
         
          </div>
        </div>
        
               
        <div class="workbox_big" style="width:48%;float:left">
         <div class="title">
              <div class="left_t">收件箱</div>
              <div class="right_t"><a href="message.do"><img src="images/skin0/index/more.png" border="0"/></a></div>
          </div>
          <div class="neirong" style="height:190px" id="divsjx">
				
          </div>
        </div>
         <div class="workbox_big" style="width:48%;float:left">
         <div class="title">
              <div class="left_t">发件箱</div>
              <div class="right_t"><a href="message.do?query.cztype=1"><img src="images/skin0/index/more1.png" border="0"/></a></div>
          </div>
           <div class="neirong" style="height:190px"  id="divfjx">
          </div>
        </div>
</body>
<script>
loadList();
function loadList(start){
	var ajax = new AppAjax("xxgl!queryIndex.do",q_callback);
		ajax.submit();	
}
function q_callback(data){
		var xxzgList = data.xxzgList;
		var str = new StringBuffer();
		for(var i=0;i<xxzgList.length;i++){
			var d = xxzgList[i];
			str.append('<li><span>'+d.rq+'</span><a href="#" onclick=showXxzg("'+d.xxid+'")>'+getBt(d.xxbt)+'</a></li>');			
		
		}
		$("xxzg").update("<ul>"+str.toString()+"</ul>");//信息撰稿
		
		var xwList = data.xwList;
		str = new StringBuffer();
		for(var i=0;i<xwList.length;i++){
			var d = xwList[i];
			str.append('<li><span>'+d.rq+'</span><a href="#" onclick=showXxzg("'+d.xxid+'")>'+getBt(d.xxbt)+'</a></li>');			
		
		}
		$("xw").update("<ul>"+str.toString()+"</ul>");//新闻
		
		var tzList = data.tzList;
		str = new StringBuffer();
		for(var i=0;i<tzList.length;i++){
			var d = tzList[i];
			str.append('<li><span>'+d.rq+'</span><a href="#" onclick=showXxzg("'+d.xxid+'")>'+getBt(d.xxbt)+'</a></li>');			
		
		}
		$("tz").update("<ul>"+str.toString()+"</ul>");//通知
		
		var schedule = data.schedule;
		str = new StringBuffer();
		for(var i=0;i<schedule.length;i++){
			var d = schedule[i];
			str.append('<li><span>'+d.strdate+'</span><a href="#" onclick=querymx("'+d.sid+'")>'+getBt(d.sbt)+'</a></li>');			
		
		}
		$("rc").update("<ul>"+str.toString()+"</ul>");//日程
		
		/**
		var ggList = data.ggList;
		str = new StringBuffer();
		for(var i=0;i<ggList.length;i++){
			var d = ggList[i];
			str.append('<li><span>'+d.rq+'</span><a href="#" onclick=showXxzg("'+d.xxid+'")>'+getBt(d.xxbt)+'</a></li>');			
		
		}
		$("
		g").update("<ul>"+str.toString()+"</ul>");//公告
		*/
	}
	
function getBt(val){
	if(val.length>20){
		return val.substring(0,20)+"…";
	}
	return val;
}

function showXxzg(xxid){
	
	var newwin = window.open("xxgl!showXxzg.do?parMap.xxid="+xxid);
	 //openWin("xxgl!showXxzg.do?parMap.xxid="+xxid,"650","600");
}
	
	function querymx(obj){
		openWin("schedule!preView.do?sid="+obj,"600","350");
	}
	
function setTab(name,cursel,n){
 for(i=1;i<=n;i++){
  var menu=document.getElementById(name+i);
  var con=document.getElementById("con_"+name+"_"+i);
  menu.className=i==cursel?"hover":"";
  con.style.display=i==cursel?"block":"none";
 }
}


var ajax = new AppAjax("message!getmessage.do",getmessage_callback);
		ajax.add("query.mtodm",$("q_mtodm").value);
		ajax.add("limit.length",6);
		ajax.add("limit.start","0");
		ajax.submit();


function getmessage_callback(data){
  
	var tableStr = new StringBuffer('<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><th >标题</th><th style="width:200px">时间</th></tr>');

	if(data && data["data"].length > 0){
		var sdata = data["data"];
	    var dataLength = sdata.length;
		for(var i=0;i< dataLength ;i++){
			tableStr.append('<tr>');
			tableStr.append('<td><a href="#" onclick="showMessage('+sdata[i].mid+',1)">');
			tableStr.append(sdata[i].mtitle);
			tableStr.append('</a></td>');
			tableStr.append('<td>'+sdata[i].mrq+'</td>');
			tableStr.append('</tr>');
		}
	}else{
		tableStr.append('');
	}
	tableStr.append('</table>');
	$("divsjx").innerHTML=tableStr.toString();
}


var ajax = new AppAjax("message!getsendmessage.do",getsendmessage_callback);
		ajax.add("query.mfromdm",$("q_mtodm").value);
		ajax.add("limit.length",6);
		ajax.add("limit.start","0");
		ajax.submit();

/**
function getsendmessage_callback(data){
  
	var tableStr = new StringBuffer('<ul>');

	if(data && data["data"].length > 0){	
		var sdata = data["data"];
	    var dataLength = sdata.length;
		for(var i=0;i< dataLength ;i++){
			tableStr.append('<li><span>');
			tableStr.append(sdata[i].mrq);
			tableStr.append('</span><a href="#" onclick="showMessage('+sdata[i].mid+',1)">');
			tableStr.append(sdata[i].mtitle);
			tableStr.append('</a></li>');
			
		}
		tableStr.append('</ul>');
	}else{
		tableStr.append('<ul><li><span>&nbsp;</span><a href="#">&nbsp;</a></li><li><span>&nbsp;</span><a href="#">&nbsp;</a></li><li><span>&nbsp;</span><a href="#">&nbsp;</a></li><li><span>&nbsp;</span><a href="#">&nbsp;</a></li><li><span>&nbsp;</span><a href="#">&nbsp;</a></li> <li><span>&nbsp;</span><a href="#">&nbsp;</a></li><li><span></span><a href="#">&nbsp;</a></li><li><span>&nbsp;</span><a href="#">&nbsp;</a></li><li><span>&nbsp;</span><a href="#">&nbsp;</a></li></ul>');
	}
	

	$("divsjx").innerHTML=tableStr;

}
**/
function getsendmessage_callback(data){
  
	var tableStr = new StringBuffer('<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><th >标题</th><th style="width:200px">时间</th></tr>');

	if(data && data["data"].length > 0){	
		var sdata = data["data"];
	    var dataLength = sdata.length;
		for(var i=0;i< dataLength ;i++){
			tableStr.append('<tr>');
			tableStr.append('<td><a href="#" onclick="showMessage('+sdata[i].mid+',1)">');
			tableStr.append(sdata[i].mtitle);
			tableStr.append('</a></td>');
			tableStr.append('<td>'+sdata[i].mrq+'</td>');
			tableStr.append('</tr>');
		}
	}else{
		tableStr.append('');
	}
	tableStr.append('</table>');
	$("divfjx").innerHTML=tableStr.toString();
}

function showMessage(messageid,type){
	if(type == 1){
		openWin('message!showMessage.do?query.userdm='+$('q_mtodm').value+'&query.type='+type+'&query.messageid='+messageid,'600','550')
	}else{
		openWin('message!showMessage.do?query.userdm='+$('q_mtodm').value+'&query.type='+type+'&query.messageid='+messageid,'600','400')
	}
}		

</script>
</html>