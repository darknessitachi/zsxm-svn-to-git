<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp"%>
<title>常州市科技专家管理系统</title>
<link rel="stylesheet" type="text/css" href="styles/expzj/style.css" />
<!--[if IE 6]>
<link rel="stylesheet" type="text/css" href="styles/expzj/iecss.css" />
<![endif]-->
<script src="Framework/Main.js" type="text/javascript"></script>
<script type="text/javascript" src="js/boxOver.js"></script>

<style>
html,body {margin: 0px;padding: 0px;border: 0px;font-family: Verdana, Tahoma, "����";overflow: auto;}
.neirong{ 
width:388px;
height:200px;
text-align:center;
border:1px #6da6b1 solid;
}
.neirong ul{width:388; margin:0px auto}
.neirong ul li{ list-style-type:none;background:url(images/skin0/index/line.gif) repeat-x left bottom; height:27px; line-height:27px}
.neirong ul li span{ float:right; color:#999999; padding-right:5px}
.neirong ul li a{ float:left; overflow:hidden; background:url(images/skin0/index/icon.gif) no-repeat 6px 8px ; padding-left:15px}

</style>
</head>
<body>

<div id="main_container">
	<div class="top_bar">
    	
        <div class="languages">
        	<div class="lang_text">添加至收藏加 | <a href="login!logoutZj.do"><font color=white>退出系统</font></a></div>
        </div>
    
    </div>
	<div id="header">
        
    </div>
    
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
  
   <div class="left_content" >
    
        
     <div class="title_box">信息撰稿</div>  
     <div class="neirong" id="xx_1">
        
     </div>  
     
     
     <div class="title_box">公告</div>  
     <div class="neirong" id="xx_3">
		
     </div>  
     
    
   </div><!-- end of left content -->
   
   
   <div class="center_content">
		
	<div class="title_box">新闻</div>  
     <div class="neirong" id="xx_2">
        
     </div>  


	  <div class="title_box">通知</div>  
     <div class="neirong" id="xx_4">
		
     </div> 


   </div><!-- end of center content -->
   
   <div class="right_content">
   		<div class="shopping_cart">
        	<div class="cart_title">
				<br>
				&nbsp;&nbsp;当前专家：<s:property value="xtuser.cnname"/><br><br>
				&nbsp;&nbsp;登入时间：<s:property value="xtuser.logindate"/>
			</div>
            
        
        </div>
   
   
     
     
    <div class="title_box2">收件箱</div>
    <div  id="div_sjx">
        
    </div> 
     
   </div><!-- end of right content -->   
   
            
   </div><!-- end of main content -->
   
   
   
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
<!-- end of main_container -->
</body>
<script>
Event.observe(window, "load", function() {
	loadList();
	getMessage();
}, true);

function loadList(){
	var ajax = new AppAjax("xxgl!queryIndex.do",q_callback);
		ajax.submit();	
}
function q_callback(data){
		var xx_1 = data.xx_1;
		var str = new StringBuffer();
		if(xx_1 != null){
			for(var i=0;i<xx_1.length;i++){
				var d = xx_1[i];
				str.append('<li><span>'+d.rq+'</span><a target=_blank href="xxgl!showXxzg.do?parMap.xxid='+d.xxid+'">'+getBt(d.xxbt)+'</a></li>');			
			
			}
			$("xx_1").innerHTML = "<ul>"+str.toString()+"</ul>";//信息撰稿
		}
		
		var xx_2 = data.xx_2;
		str = new StringBuffer();
		if(xx_2 != null){
			for(var i=0;i<xx_2.length;i++){
				var d = xx_2[i];
				str.append('<li><span>'+d.rq+'</span><a target=_blank href="xxgl!showXxzg.do?parMap.xxid='+d.xxid+'">'+getBt(d.xxbt)+'</a></li>');			
			
			}
			$("xx_2").innerHTML = "<ul>"+str.toString()+"</ul>" ;//新闻
		}
		
		var xx_3 = data.xx_3;
		str = new StringBuffer();
		if(xx_3 != null){
			for(var i=0;i<xx_3.length;i++){
				var d = xx_3[i];
				str.append('<li><span>'+d.rq+'</span><a target=_blank href="xxgl!showXxzg.do?parMap.xxid='+d.xxid+'">'+getBt(d.xxbt)+'</a></li>');			
			
			}
			$("xx_3").innerHTML = "<ul>"+str.toString()+"</ul>";//通知		
		}

		
		var xx_4 = data.xx_4;
		str = new StringBuffer();
		if(xx_4 != null && xx_4 !='undefined' ){
			for(var i=0;i<xx_4.length;i++){
				var d = xx_4[i];
				str.append('<li><span>'+d.rq+'</span><a target=_blank href="xxgl!showXxzg.do?parMap.xxid='+d.xxid+'")>'+getBt(d.xxbt)+'</a></li>');			
			
			}
			$("xx_4").innerHTML = "<ul>"+str.toString()+"</ul>";//公告		
		}

	}
	
function getBt(val){
	if(val.length>20){
		return val.substring(0,20)+"…";
	}
	return val;
}

function getBt2(val){
	if(val.length>12){
		return val.substring(0,12)+"…";
	}
	return val;
}

function getMessage(){
	var ajax = new AppAjax("message!getmessage.do",getmessage_callback);
			ajax.add("query.mtodm",'<s:property value="xtuser.loginname"/>');
			ajax.add("limit.length",13);
			ajax.add("limit.start","0");
			ajax.submit();
}
function getmessage_callback(data){
  
	var tableStr = new StringBuffer('<ul class="left_menu">');

	if(data && data["data"].length > 0){	
		var sdata = data["data"];
	    var dataLength = sdata.length;
		for(var i=0;i< dataLength ;i++){
			if(i%2 == 0 ){
				tableStr.append('<li class="odd"><a href="#" onclick="showMessage('+sdata[i].mid+',1)">');
				tableStr.append(getBt2(sdata[i].mtitle));
				tableStr.append('</a></li>');
			}else{
				tableStr.append('<li class="even"><a href="#" onclick="showMessage('+sdata[i].mid+',1)">');
				tableStr.append(getBt2(sdata[i].mtitle));
				tableStr.append('</a></li>');			
			}
		}
	}else{
		tableStr.append('');
	}
	tableStr.append('</ul>');
	$("div_sjx").innerHTML=tableStr.toString();
}

var diag = null;
function showMessage(messageid,type){
	diag = new Dialog("messageRead");
	diag.Title = "阅读短信息";
	diag.Width = 600;
	diag.Height = 500;
	diag.ShowMessageRow=false;
	
	if(type==1){
		diag.URL = 'message!showMessage.do?query.userdm='+'<s:property value="xtuser.loginname"/>'+'&query.type='+type+'&query.messageid='+messageid+'&winid=messageRead';
	}else{
		diag.URL = 'message!showMessage.do?query.userdm='+'<s:property value="xtuser.loginname"/>'+'&query.type='+type+'&query.messageid='+messageid+'&winid=messageRead';
	}
	diag.show();
}

function closeWin(id){
	Dialog.getInstance(id).CancelButton.onclick.apply(Dialog.getInstance(id).CancelButton,[]);
}
		
</script>

</html>