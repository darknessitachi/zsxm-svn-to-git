<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<script src="js/neverModules-outlookmenu.js" type="text/javascript"></script>
		<script src="js/msgbox.js" type="text/javascript"></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
    	<script type='text/javascript' src='dwr/interface/dwrDeclarePush.js'> </script>
  		<script type='text/javascript' src='dwr/util.js'> </script>
 		<link type="text/css" href="styles/msgbox.css" rel="stylesheet" />
		<script type="text/javascript" src="js/xwindow.js" ></script>
		<title>常州科教城管理信息系统</title>
		 <script language="javascript">
        	<%String sign="" , sign2="";
			  int number=0 , number2=0;
			%>
			var menus=[
			<s:iterator value="xtmenus">
			<%=sign%>['<s:property value="menuid"/>','<s:property value="menuid"/>','<s:property value="action"/>','<s:property value="pmenuid"/>','<s:property value="title"/>','<s:property value="image"/>',<%=number%>]<%sign=",";number++;%>
			</s:iterator>
			];
		
			var old_id="";
			var fold_old_id = "";
			
			
		</script>
<style>
body{ 
				font-family: "宋体", Verdana, Arial, Helvetica, sans-serif;
				font-size: 12px;
			}
			html {
				SCROLLBAR-HIGHLIGHT-COLOR: #f2f2f2;
				SCROLLBAR-3DLIGHT-COLOR: #ffffff;
				SCROLLBAR-ARROW-COLOR: #ffffff;
				SCROLLBAR-TRACK-COLOR: #f2f2f2;
				SCROLLBAR-DARKSHADOW-COLOR: #ffffff;
				SCROLLBAR-BASE-COLOR: #e1e1e1;
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
			
			.nav{ border:1pt solid #acabab; border-top:0px; overflow:hidden; width:100%}
			.top{ background:url(images/skin0/index/top_bg.jpg); height:70px; width:100%}
			.topbar{ background:url(images/skin0/index/time_bg.jpg); height:31px; width:100%}
			.time{ background:url(images/skin0/index/time.jpg) no-repeat; height:30px; line-height:30px; float:left; padding-left:22px; width:170px}
			.people{ height:30px; line-height:30px; color:#FFFFFF; float:left; padding-left:10px}
			.cantion{ border-top:1pt solid #c1bfc0; overflow:hidden; width:100%}
			.left{float:left; width:170px; overflow:auto; overflow-x:hidden;}
			.zhedie{ width:14px; float:left; background:url(images/skin0/index/zhedie_bg.jpg) repeat-y; height:500px}
			.zhedie img{ margin-left:4px; margin-top:150px}
			.right{ background:#fff; overflow:auto; overflow-x:hidden;  height:500px}
			.daohang{ background:url(images/skin0/index/right_daohang.jpg) repeat-x; height:31px; line-height:28px; color:#8b8b8b; padding-left:10px}

			.outlookMenu               { overflow:hidden; width:170px;  }
.outlookMenu h4            { font-size:12px;cursor:default; background:url(images/skin0/index/menu.jpg) no-repeat;color:#006699; height:25px; line-height:25px; padding-left:13px;  }
.outlookMenu ul            { background-color: #dfe9f2; }
.outlookMenu ul li         {  color:#0b5ca9; margin-bottom:1px; height:23px; line-height:23px;  text-align:left;  background:#fff}
.outlookMenu ul li a       { text-decoration:none; color:#0b5ca9; display:block;height:23px; line-height:23px; background:url(images/skin0/index/menu_sub_bg.jpg) right no-repeat;padding-left:30px;}
.outlookMenu ul li a:hover { text-decoration:none; color:#069; display:block; height:23px; line-height:23px; background:url(images/skin0/index/menu_sub_bgov.jpg) right no-repeat #eaf1f6 ;padding-left:30px;}
.outlookMenu ul li a.onclicks { text-decoration:none; color:#069; display:block; background:url(images/skin0/index/menu_sub_bgov.jpg) right no-repeat #eaf1f6 ;padding-left:30px;}
.outlookMenu h4 img{ position:relative;top:4px; margin-right:4px}

ul{ list-style-type: none; margin:0px; }
ul li a{ display:block; width: 100%; background:white }
ul li a:hover{ background:url(images/skin0/index/l001.gif); }

</style>

<script type="text/javascript" language="javascript">

var timerID = null; 
var timerRunning = false; 
function stopclock(){ 
	if(timerRunning) 
		clearTimeout(timerID); 
		timerRunning = false;
	} 
	
	function startclock() { 
		stopclock(); 
		showtime(); 
	} 
	
	function showtime() { 
		var now = new Date(); 
		var hours = now.getHours(); 
		var minutes = now.getMinutes(); 
		var seconds = now.getSeconds() 
		var timeValue = now.getYear();
		timeValue += "-"+(now.getMonth() + 1) + "-";
   		timeValue += now.getDate() + "  ";
 
		
	    timeValue += ((hours >= 12) ? "下午" : "上午" ) 
		timeValue += ((hours >12) ? hours -12 :hours) 
		timeValue += ((minutes < 10) ? ":0" : ":") + minutes 
		timeValue += ((seconds < 10) ? ":0" : ":") + seconds 
		document.getElementById("thetime").innerText = timeValue; 
		timerID = setTimeout("showtime()",1000); 
		timerRunning = true; 
	} 

</script>

</head>

<body scroll="yes" onload="dwr.engine.setActiveReverseAjax(true);startclock();">
        <input type="hidden" id="mfromdm" value="${xtuser.loginname}">
        <s:hidden name="xtuser.status"></s:hidden>
	    <div class="top">
	      	<img src="images/skin0/index/top_lg.jpg" style="float:left" />
	      	<img src="images/skin0/index/top_dh.jpg" border="0" usemap="#Map"  style="float:right"/>
			<map name="Map" id="Map">
				<area shape="rect" coords="46,9,120,63" href="#" onclick="javascript:god('login!indexgl.do')"/>
				<area shape="rect" coords="131,9,210,63" href="#" onclick="javascript:window.location.href='login.do'"/>
				<area shape="rect" coords="230,9,300,63" href="#"  onclick="javascript:window.close();" />
				<area shape="rect" coords="320,9,390,63" href="#" />
			</map>   
	 	</div>
	    <div class="topbar">
	      <div class="time"><div id="thetime"> </div> </div>
	      <div class="people">当前用户：${xtuser.cnname} </div>
	    </div>
     
      <div class="left" id="leftbox" style="display:block" >
	     	 <div id="menu">
			      <h4><img src="images/skin0/index/1.png" align="left"/>人才管理</h4>
			      <ul>
			        <li><a href="#">outlookmenu</a></li>
			        <li><a href="#">autocomplete</a></li>
			        <li><a href="#">progressbar</a></li>
			        <li><a href="#">slider</a></li>
			        <li><a href="#">customlayout</a></li>
			        <li><a href="#">imagesloader</a></li>
			        <li><a href="#">code counter</a></li>
			      </ul>
			      <h4><img src="images/skin0/index/2.png" align="left"/>数据信息</h4>
			      <ul>
			        <li>Control panel</li>
			        <li>My Computer</li>
			        <li>OutLook</li>
			        <li>Network Connection</li>
			      </ul>
			      <h4><img src="images/skin0/index/3.png" align="left"/>统计管理</h4>
			      <ul>
			        <li>Email: BlueDestiny[at]126.com</li>
			 
			        <li>DHTML tutorial</li>
			        <li>OutLook Style</li>
			      </ul>
			      <h4><img src="images/skin0/index/4.png" align="left" />人才追踪</h4>
			      <ul>
			        <li>Custom Style?</li>
			        <li>Custom Animate?</li>
			        <li>Ajax Panel?</li>
			        <li>And More...</li>
			      </ul>
	
	  
	 </div>
 
   
         

      </div>
      <div class="zhedie" id="zhed"><a href="javascript:zhedie();"><img src="images/skin0/index/zhezhao1.gif" border="0px" id="zdm" /></a></div>
      <div class="right" id="rightbox" >
       	<iframe id="mainFrameContainer" name="mainFrame" frameborder=0 scrolling="auto"  width="100%" src="login!indexgl.do"></iframe>
      </div>
     
	  <div class="footer" id="fbottom" style="height:23;text-align:center">
	  	<iframe id="bottom" name="bottom" frameborder=0 scrolling="NO" width="100%" height="23px" src="login!bottom.do"></iframe>
	  </div>
      
   <script type="text/javascript">
      var oldobj=null;
function click_color(obj){
	if(oldobj != null){
		oldobj.className='';
	}
	obj.className='onclicks';
	oldobj=obj;
}
   dwrDeclarePush.setScriptSession();

function god(o){
	$('mainFrameContainer').src = o; 
}
    var o; 
      setPos();
function zhedie(){
		  if(document.getElementById('leftbox').style.display=='block'){ 
			  document.getElementById('leftbox').style.display='none'
			  document.getElementById('leftbox').style.width='0px'
			  document.getElementById('zdm').src='images/skin0/index/zhezhao2.gif'
 		 }else{
  			 document.getElementById('leftbox').style.display='block'
			 document.getElementById('leftbox').style.width='170px'
			 document.getElementById('zdm').src='images/skin0/index/zhezhao1.gif'
			}

}
loadMenu();
function loadMenu(){
	var m_str = "";
	var imgxh = 0;
	for(var i=0;i<menus.length;i++){
		if(menus[i][3]=="00"){
			var _image2 = (menus[i][5].trim() == "")?"images/skin0/index/1.png":menus[i][5];
			m_str +='<h4><img src="'+_image2+'" align="left"/>'+menus[i][4]+'</h4>';
			m_str += '<ul>';
			m_str += loadChildMenu(menus[i][1]);
			m_str += '</ul>';
		}
	}
	$("menu").update(m_str);
	 o = new neverModules.ui.outlookMenu("menu", "o")
      .setStyle("outlookMenu")
      .setAnimate({delay:0, increase:6, decrease:8})
      .render();
}
function loadChildMenu(pmenuid){
	var str = "";
	for(var i=0;i<menus.length;i++){
		if(menus[i][3]==pmenuid){
			str += '<li><a href="'+menus[i][2]+'"  onClick="javascript:click_color(this);" target="mainFrame">'+menus[i][4]+'</a></li>';
		
		}
	
	}
	return str;
}

var oldobj="";
function setBack(obj){
	if(oldobj==""){
		oldobj = obj;
	}
	oldobj.style.background = "white";
	obj.style.background ="#add8e6";
	oldobj = obj;
}
function setPos(){
	//var heights=window.screen.height-270;
	var heights = document.body.clientHeight-110 ;
	document.getElementById('leftbox').style.height=parseInt(heights)+'px';
	document.getElementById('rightbox').style.height=parseInt(heights)+'px';
	document.getElementById('zhed').style.height=parseInt(heights)+'px';
	$("mainFrameContainer").style.height = parseInt(heights-20)+'px';
}

function showmessage(res){            
	var title = res.mtitle;
	var len = title.length;
	if(len > 44)
	{
		title = "&nbsp;&nbsp;"+title.substr(0,44)+"...";
	}
 	displayMsgbox('<div  style="word-break:break-all;width:165px; overflow:auto;"><a href="#" onclick="openMessage('+res.mid+')">'+title+'</a></div>',1000);
}

function showschedule(res){            
	var title = res.title;
	var len = title.length;
	if(len > 44)
	{
		title = "&nbsp;&nbsp;"+title.substr(0,44)+"...";
	}
 	displayMsgbox('<div  style="word-break:break-all;width:165px; overflow:auto;"><a href="#" onclick="openschedule('+res.sid+')">'+title+'</a></div>',1000);
}
				
function openMessage(messageid){
	ethanMsgbox.closeMsgbox();
	openXwin('message!showMessage.do?query.userdm='+document.getElementById("mfromdm").value+'&query.type='+1+'&query.messageid='+messageid,'600','550')
}	

function openschedule(messageid){
	ethanMsgbox.closeMsgbox();
	openXwin('schedule!preView.do?sid='+messageid,"600","350");
}

var ajax = new AppAjax("message!getMessageSize.do",getSizeMessage_callback);
		ajax.add("query.userdm",document.getElementById("mfromdm").value);
		ajax.submit();

function getSizeMessage_callback(data)
{
	if(data > 0)
		displayMsgbox('<div  style="word-break:break-all;width:165px; overflow:auto;"><a href="#" onclick="openMessagesize()">您有'+data+'条短信，请点击阅读！</a></div>',1000);

}

function openMessagesize(){
		ethanMsgbox.closeMsgbox();
		openXwin('message!showMessage.do?query.userdm='+document.getElementById("mfromdm").value+'&query.type=1','600','550')
}


    </script>
</body>
</html>