<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<%@ include file="/common/meta.jsp"%>
	<script src="Framework/Main.js" type="text/javascript"></script>
	<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
<title>常州市专家管理系统</title>
<style type="text/css">
td,input,button,select,body {font-family:"lucida Grande",Verdana;font-size:12px;}
html,body{
 overflow:hidden;
 height:100%;
 margin:0;
 padding:0;
 margin-left: 0px;
 margin-right: 0px;
 font:14px/1.8 Georgia, Arial, Simsun;
}
html{
 _padding:98px 0;
}
#hd{
 position:absolute;
 top:0;
 left:0;
 width:100%;
 height:98px;
 background:#999;
 overflow:hidden;
}
#bd{
 position:relative;
 
 right:0;
 
 left:0;
 overflow:hidden;
 width:100%;
 _height:100%;

}
#side{
 position:absolute;
 top:0;
 left:0;
 bottom:0;
 overflow:auto;
 width:200px;
 _height:100%;
 background:#666;
}
#main{
 position:absolute;
 _position:static;
 top:0;
 right:0;
 bottom:0;
 left:210px;
 overflow:auto;
 _overflow:hidden;
 _height:100%;
 _margin-left:210px;
 background:#666;
}
#content{
 _overflow:auto;
 _width:100%;
 _height:100%;
}
#ft{
 position:absolute;
 bottom:0;
 left:0;
 width:100%;
 height:22px;
 background:#999;
}
h5 { font-size: 13px; font-family:Simsun, sans-serif; font-weight: bold; }
</style>
</head>
<body>

<table border="0" cellpadding="0" cellspacing="0" height="100%" width="100%">
	<tr>
		<td  style="height:98px;background:#efefef">
			<div id="hd">
				<iframe name="topframe" scrolling="no" height="98" width="100%" border="0" frameborder="0" src="frame!topgl.do"> 浏览器不支持嵌入式框架，或被配置为不显示嵌入式框架。</iframe>
			</div>
		</td>
	</tr>

	<tr>
		<td  style="background:yellow">
			<div id="bd" style="background:#efefef;width:100%;height:100%;">
				<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
				  <tr>
				    <td><iframe name="mainframe"  id="mainframe" height="100%" width="100%" border="0" frameborder="0" src="frame!middlegl.do"> 浏览器不支持嵌入式框架，或被配置为不显示嵌入式框架。</iframe></td>
				    <td width="6" bgcolor="#1873aa" style=" width:6px;">&nbsp;</td>
				  </tr>
				</table>
			</div>
		</td>
	</tr>
    
	<tr>
		<td  style="height:22px;width:100%;background:#efefef">
			<div id="ft">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
				  	
				    <td height=22 background="images/main/main_61.gif">
				    	<font color=white size=2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    	技术支持:常州市科技信息中心&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    	技术支持电话:0519-88101845&nbsp;&nbsp;&nbsp;&nbsp;
				    	85551608
				    	</font>
				    </td>
				    
				  </tr>
				</table>
			</div>
		</td>
	</tr>
</table>
	<script>
		//var heights=window.screen.height-110;
		//document.getElementById("mainframe").style.height = heights;
		function closeWin(id){
			Dialog.getInstance(id).CancelButton.onclick.apply(Dialog.getInstance(id).CancelButton,[]);
		}
		
		function refreshP(){
			document.mainframe.mainframe.refresh();
		}
		
		function refreshother(t){
			if(t==1){
				document.mainframe.mainframe.loadTree();
			}else if(t==2){
				document.mainframe.mainframe.refreshfl();
			}
		}
		
		function refreshmm(){
			var iflen = document.body.getElementsByTagName("iframe").length;
			var iswh = false;
			for(var i=0;i<iflen ;i++){
				if (document.body.getElementsByTagName("iframe")[i].name=='_DialogFrame_expwindows'){
					iswh = true;
					break;
				}
			}
			if(iswh){
				document._DialogFrame_expwindows.refresh();
			}else{
				document.mainframe.mainframe.refresh();
			}
		}
	</script>
</body>
</html>