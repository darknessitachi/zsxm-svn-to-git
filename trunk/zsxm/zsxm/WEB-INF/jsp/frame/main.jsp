<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<META content=ie=7 http-equiv=x-ua-compatible/>
	<head>
		<%@ include file="/common/meta.jsp"%>
		
		<script src="js/neverModules-outlookmenu.js" type="text/javascript"></script>
		<link type="text/css" href="<s:url value="/styles/rcmain/rcmain.css"/>" rel="stylesheet" />
		<script src="js/msgbox.js" type="text/javascript"></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
    	<script type='text/javascript' src='dwr/interface/dwrDeclarePush.js'> </script>
  		<script type='text/javascript' src='dwr/util.js'> </script>
 		<link type="text/css" href="styles/msgbox.css" rel="stylesheet" />
		<script type="text/javascript" src="js/xwindow.js" ></script>
		<title>常州科教城管理信息系统</title>
		
		 <script language="javascript">

			var old_id="";
			var fold_old_id = "";
			
			
		</script>
<style>

			body{ 
				font-family: "宋体", Verdana, Arial, Helvetica, sans-serif;
				font-size: 12px;
			}
			a {
				font-size: 12px;
				color: #7c7c7a;
			}
			a:link {
				text-decoration: none;
				color: #7c7c7a;
			}
			.nav{ border:1pt solid #acabab; border-top:0px; overflow:hidden; width:100%}
			.top{ background:url(images/skin0/index/top_bg.jpg); height:70px; width:960}
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
			
</style>


</head>

<body style="background-image: url(images/skin0/index/bodyback.bmp);" onload="dwr.engine.setActiveReverseAjax(true);">
        <input type="hidden" id="mfromdm" value="${xtuser.loginname}"/>
        <s:hidden name="xtuser.loginbz"></s:hidden>
        <s:hidden name="xtuser.xtrcid"></s:hidden>
        <div style="width:100%">
	    <div class="top" style="MARGIN: 0px auto 0px;width:960px">
	      	<img src="images/skin0/index/top_lg.jpg" style="float:left" />
	      	<img src="images/skin0/index/top_dh.jpg" border="0" usemap="#Map"  onclick="javascript:;" style="float:right"/>
			<map name="Map" id="Map">
				<area shape="rect" coords="46,9,120,63" href="#" onclick="javascript:god('login!index.do')"/>
				<area shape="rect" coords="131,9,210,63" href="#" onclick="javascript:window.location.href='login.do'"/>
				<area shape="rect" coords="230,9,300,63" href="#"  onclick="javascript:window.close();" />
				<area shape="rect" coords="320,9,390,63" href="#" />
			</map>   
	 	</div>
	    <DIV id=ChannelMenu>
		<DIV class=Shell>
		<DIV class=Tab  id="mainmenu">
			
		</DIV>
		</DIV>
		</DIV>
		
		<DIV id=ProductMenu>
		<DIV class=Shell>
		<DIV class=Tab id="mxmenu">
	
		</DIV>
		</DIV>
		</DIV>

      <div class="right" id="rightbox" style="MARGIN: 0px auto 0px;width:960px;overflow:auto">
       	<iframe id="mainFrameContainer" name="mainFrame" frameborder=0 scrolling="auto"  width="960px" src="login!indexgl.do"></iframe>
      </div>
      </div>
	  <div id="ppmxdiv" style="display:none">
	  
	  </div>
 
  
   <script type="text/javascript">

dwrDeclarePush.setScriptSession();

function god(o){
	if(o!=null && o!= ''){
		$('mainFrameContainer').src = o+"?rcid="+$('xtuser.xtrcid').value;
	} 
}

setPos();
function setPos(){
	var heights=window.screen.height-300;
	document.getElementById('rightbox').style.height=(heights+5)+'px';
	$("mainFrameContainer").style.height = heights+'px';
}

var maindiv = new StringBuffer('<UL class=clearfix><LI id="m00" class="Current"><a href="javascript:;" onclick="dismenu(\'00\');god(\'login!index.do\')"><span>首   页</span></a>');
var mxdiv = new StringBuffer();
var pid = '00';
var xx = 0;
<s:iterator value="xtmenus">
	if('<s:property value="pmenuid"/>'=='00'){
		pid = '<s:property value="menuid"/>';
		maindiv.append('<LI id="m<s:property value="menuid"/>"><a href="javascript:;" onclick="dismenu(\'<s:property value="menuid"/>\')"><span><s:property value="title"/></span></a>');
		if(xx == 1){
			mxdiv.append('</UL></div>');
			xx = 0;
		}
		mxdiv.append('<div id="div'+pid+'"><UL>');
	}

	if(pid == '<s:property value="pmenuid"/>'){
		xx = 1;
		mxdiv.append('<LI id="mx<s:property value="menuid"/>"><a href="javascript:;" onclick="dismxmenu(\'<s:property value="menuid"/>\');god(\'<s:property value="action"/>\')"><span><s:property value="title"/></span></a>');
	}
</s:iterator>
maindiv.append('</UL>');
mxdiv.append('</UL></div>');
$('mainmenu').innerHTML = maindiv.toString();
$('ppmxdiv').innerHTML = mxdiv.toString();

var oldm = '00';
function dismenu(did){
	$('m'+oldm).className="";
	$('m'+did).className="Current";
	oldm = did;
	
	if(did == '00'){
		$('mxmenu').innerHTML = "";
	}else if(did=='01'){
		$('mxmenu').innerHTML = $('div'+did).innerHTML;
		dismxmenu('0101');
		god('rcdaxxwh!preView.do');
	}else{
		$('mxmenu').innerHTML = $('div'+did).innerHTML;
	}
	
}

var oldmx = '';
function dismxmenu(did){
	if(oldmx != ''){
		$('mx'+oldmx).className="";
	}
	$('mx'+did).className="Current";
	oldmx = did;
}

//loadMenu();


function showmessage(res){            
	
			var title = res.mtitle;
			var len = title.length;
				if(len > 44)
				{
					title = "&nbsp;&nbsp;"+title.substr(0,44)+"...";
				}
	  		displayMsgbox('<div  style="word-break:break-all;width:165px; overflow:auto;"><a href="#" onclick="openMessage('+res.mid+')">'+title+'</a></div>',1000);
}

				
function openMessage(messageid){
		ethanMsgbox.closeMsgbox();
		openXwin('message!showMessage.do?query.userdm='+document.getElementById("mfromdm").value+'&query.type='+1+'&query.messageid='+messageid,'600','550')
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