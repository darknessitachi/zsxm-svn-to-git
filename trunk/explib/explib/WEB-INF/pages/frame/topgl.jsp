<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>无标题文档</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {
	font-size: 12px;
	color: #FFFFFF;
}
.STYLE2 {font-size: 9px}
.STYLE3 {
	color: #033d61;
	font-size: 12px;
}

.search{
	padding-right:2px;
	padding-left:10px;
	padding-top:1px;
	padding-bottom:1px;
	width: 250px;
	float:left;
	position:relative;
}
.search .inputsearch{
	padding-top:1px;
	height:18px;
	width: 180px;
	border: 1px solid #0086C6;
	font-size:14px;
	
}
.search .btn{
}
.label{color:#ccc;position:absolute;left:12px;top:6px;display:block;height:22px;line-height:22px;font-size:12px;}
span.submit{cursor:pointer;position:absolute;left:171px;top:6px;width:20px;height:20px;text-indent:-999em;}

-->
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
 
		
	    //timeValue += ((hours >= 12) ? "下午" : "上午" ) 
		timeValue += ((hours >12) ? hours -12 :hours) 
		timeValue += ((minutes < 10) ? ":0" : ":") + minutes 
		timeValue += ((seconds < 10) ? ":0" : ":") + seconds 
		document.getElementById("thetime").innerText = timeValue; 
		timerID = setTimeout("showtime()",1000); 
		timerRunning = true; 
	} 

</script>
</head>

<body onload="startclock();">
<table width="100%;height:100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="70" background="images/skin0/index/top_bg.jpg">
    	<img src="images/skin0/index/top_lg.jpg" style="float:left" />
    	<img src="images/skin0/index/top-dh-2.jpg" border="0" style="float:right"  onclick="javascript:logout();"/>
    	<img src="images/skin0/index/top-dh-1.jpg" border="0" style="float:right"  onclick="javascript:index();"/>
		
		&nbsp;&nbsp;&nbsp;
    </td>
  </tr>
  <tr>
    <td height="28" background="images/main/main_36.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="177" height="28" background="images/main/main_32.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="20%"  height="22">&nbsp;</td>
            <td width="59%" valign="bottom"><div align="center" id="thetime" class="STYLE1"></div></td>
            <td width="21%">&nbsp;</td>
          </tr>
        </table></td>
        <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="left" height="28"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="23" align="left" valign="bottom">
                <table border="0" align="left" cellpadding="0" cellspacing="0">
                  <tr> 
                    <td height="20"> <div align="center" class="STYLE3">
                    	&nbsp;&nbsp;&nbsp;<img src="images/login/uesr.gif" height="12px"/>&nbsp;&nbsp;当前用户：<s:property value="xtuser.cnname"/>
                    </div></td>
                  </tr>
                </table>
                </td>
                <td height="23" align="right" valign="bottom">
                <table border="0" align="right" cellpadding="0" cellspacing="0">
                  <tr> 
                  <td>
                  	<font style="font-size:12px;" color="#033d61">
                  		<input type=radio name="searchtype" id="searchtype1" value=1 checked><label for="searchtype1">关键字</label>
                   	 	<input type=radio name="searchtype" id="searchtype2" value=2><label for="searchtype2">全文</label>
                    </font>
                  </td>
                    <td height="20" > 
                    	<div class="search">
                    		<input name="searchword" type="text" class="inputsearch" id="searchword" value=""  style="float:left;" />
							<img src="images/skin0/index/sear.gif" width="39" height="17" onclick="javascript:formSearch();">
						</div>
					</td>
                  </tr>
                </table>
                </td>
              </tr>
            </table>
            </td>
           
          </tr>
        </table></td>
        <td width="21"><img src="images/main/main_37.gif" width="21" height="28"></td>
      </tr>
    </table></td>
  </tr>
</table>

<script type="text/javascript" language="javascript">
	function formSearch(){
		window.parent.mainframe.mainframe.frameElement.src = 'expquery.do';
	}
	
	function index(){
		window.parent.mainframe.mainframe.frameElement.src = 'frame!indexgl.do';
	}
	
	function logout(){
		window.parent.location.href = 'frame!logout.do';
	}
</script>
</body>

</html>
