<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<META content=ie=7 http-equiv=x-ua-compatible/>
	<head>
		<%@ include file="/common/meta.jsp"%>
		
		<link type="text/css" href="<s:url value="/styles/rcmain/rcmain.css"/>" rel="stylesheet" />
		
		<script type="text/javascript" src="js/xwindow.js" ></script>
		<title>常州高层人才信息系统</title>
		
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

<body style="background-image: url(images/skin0/index/bodyback.bmp);" >
        
        <s:hidden name="xtuser.status"></s:hidden>
        <div style="width:100%">
	    <div class="top" style="MARGIN: 0px auto 0px;width:980px">
	      	<img src="images/skin0/index/top_lg.jpg" style="float:left" />
	      	<img src="images/skin0/index/top_dh.jpg" border="0" usemap="#Map"  onclick="javascript:;" style="float:right"/>
			<map name="Map" id="Map">
				<area shape="rect" coords="8,11,58,66" href="#" />
				<area shape="rect" coords="66,7,120,62" href="#" onclick="javascript:window.top.location.href='login!gl.do';"/>
				<area shape="rect" coords="131,9,175,63" href="#" />
				<area shape="rect" coords="187,19,243,60" href="#" />
			</map>   
	 	</div>
	    

      <div class="right" id="rightbox" style="MARGIN: 0px auto 0px;width:980px;overflow:auto">
       	<iframe id="mainFrameContainer" name="mainFrame" frameborder=0 scrolling="auto"  width="960px" src="login!doLoginInfo.do"></iframe>
      </div>
      </div>
	  <div id="ppmxdiv" style="display:none">
	  
	  </div>
 
  
   <script type="text/javascript">

setPos();
function setPos(){
	var heights=window.screen.height-240;
	document.getElementById('rightbox').style.height=(heights+10)+'px';
	$("mainFrameContainer").style.height = heights+'px';
}


    </script>
</body>
</html>