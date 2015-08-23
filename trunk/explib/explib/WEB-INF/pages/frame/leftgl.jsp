<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
	<script src="js/neverModules-outlookmenu.js" type="text/javascript"></script>

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
.STYLE3 {
	font-size: 12px;
	color: #033d61;
}

-->

.menu_title SPAN {
	FONT-WEIGHT: bold; LEFT: 3px; COLOR: #ffffff; POSITION: relative; TOP: 2px 
}
.menu_title2 SPAN {
	FONT-WEIGHT: bold; LEFT: 3px; COLOR: #FFCC00; POSITION: relative; TOP: 2px
}
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
.left{float:left; width:166px; overflow:auto; overflow-x:hidden;}
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
<script language="javascript">
    <%String sign="" , sign2="";
	  int number=0 , number2=0;
	%>
	var menus=[
	<s:iterator value="xtmenus">
	<%=sign%>['<s:property value="menuid"/>','<s:property value="menuid"/>','<s:property value="action"/>','<s:property value="pmenuid"/>','<s:property value="title"/>','<s:property value="image"/>',<%=number%>]<%sign=",";number++;%>
	</s:iterator>
	];
</script>
</head>
<body>
<table width="170" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="26" background="images/main/main_40.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="19%">&nbsp;</td>
        <td width="81%" height="20" align=left><span class="STYLE1"><b>系统菜单</b></span></td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td valign="top"><table width="170" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td>
            
     	 	<div class="left" id="leftbox" style="display:block" >
	     	 <div id="menu">
			 </div>
      </div>
     </td>
      </tr>
    </table></td>
  </tr>
 
</table>
</td>
</tr>
</table>
</body>
<script>
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
			str += '<li><a href="#"  onClick="javascript:click_color(this);god(\''+menus[i][2]+'\')">'+menus[i][4]+'</a></li>';
		}
	}
	return str;
}

function showsubmenu(sid)
{
whichEl = eval("submenu" + sid);
imgmenu = eval("imgmenu" + sid);
if (whichEl.style.display == "none")
{
eval("submenu" + sid + ".style.display=\"\";");
imgmenu.background="images/main/main_47.gif";
}
else
{
eval("submenu" + sid + ".style.display=\"none\";");
imgmenu.background="images/main/main_48.gif";
}
}

var oldobj=null;
function click_color(obj){
	if(oldobj != null){
		oldobj.className='';
	}
	obj.className='onclicks';
	oldobj=obj;
}

function god(o){
	if(o!=null && o!= ''){
		//parent.parent.parent.window.location.href='login.do';
		parent.document.getElementById('mainframe').src = o;
	}
}

</script>
</html>
