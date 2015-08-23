<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>常州市科技专家管理系统</title>
	<%@ include file="/common/meta.jsp"%>
	<script src="Framework/Main.js" type="text/javascript"></script>
	<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
	<script type="text/javascript" src="<c:url value="/js/rc_init.js"/>" ></script>
	<link rel="stylesheet" type="text/css" href="styles/expzj/style.css" />
	<!--[if IE 6]>
	<link rel="stylesheet" type="text/css" href="styles/expzj/iecss.css" />
	<![endif]-->
	<script type="text/javascript" src="js/boxOver.js"></script>
	<style>
		html,body {margin: 0px;padding: 0px;border: 0px;font-family: Verdana, Tahoma, "����";overflow: auto;}
	</style>
</head>


<body >

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
			<div style="height:10px;width:0px"></div>
			<div class="center_title_bar2">
   				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   				
   				<s:if test="shbz==1">
   					<font color=blue>专家信息审核通过</font>
   				</s:if>
   				<s:elseif test="shbz==2">
   					<font color=red>专家信息审核退回</font>
   					<a href="#" onclick="javascript:gethtyy();"><font color=blue>点击查看详情</font></a>
   				</s:elseif>
   				<s:elseif test="shbz==3">
   					数据已上报，等待审核！
   				</s:elseif>
   				<s:else>
   					注：请填写详细信息
   				</s:else>
   				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   				<span>
   					<input type="button" class="sbutm" onMouseOver="this.className ='sbutm_over'" onMouseout="this.className='sbutm'" value=" 数据提交" onclick="javascript:dosb();"/>
   				</span>
   			 </div>
			<s:hidden name="rcid" id='rcid'></s:hidden>
			<s:hidden name="opttype" id='opttype'></s:hidden>
			<s:hidden name="pname" id='pname' value="mainFrame"></s:hidden>
			<s:hidden name="winid" id="winid"></s:hidden>
			<div class="main" id="mh" >
			
				<div  class="leftmenu" >
					<ul class="nav">
						<li class="on" id="rcda1li"><span class="red">*</span><a href="javascript:" id="rcda1" onclick="activeChange(this.id,'exp!preExp.do');">基本信息</a></li><!-- 标签下的内容填写完，加over -->
						<li class="" id="rcda2li"><a href="javascript:;" id="rcda2" onclick="activeChange(this.id,'exp!preJszc.do');">专业领域</a></li>
						<li class="" id="rcda3li"><a href="javascript:;" id="rcda3" onclick="activeChange(this.id,'exp!preXxjl.do');">学习简历</a></li>
						<li class="" id="rcda4li"><a href="javascript:" id="rcda4" onclick="activeChange(this.id,'exp!preGzjl.do');">工作简历</a></li>
						<li class="" id="rcda5li"><a href="javascript:" id="rcda5" onclick="activeChange(this.id,'exp!preKtxm.do');">科研项目</a></li>
						<li class="" id="rcda6li"><a href="javascript:" id="rcda6" onclick="activeChange(this.id,'exp!preRyhj.do');">个人荣誉、获奖情况</a></li>
						<li class="" id="rcda8li"><a href="javascript:" id="rcda8" onclick="activeChange(this.id,'exp!preShjz.do');">社会兼职情况</a></li>
						<li class="" id="rcda9li"><a id="rcda9"href="javascript:" onclick="activeChange(this.id,'exp!preBpzj.do');">专家类别</a></li>
						<li class="" id="rcda10li"><a href="javascript:" id="rcda10" onclick="activeChange(this.id,'exp!preZylz.do');">主要论著论文</a></li>
						<li class="" id="rcda13li"><a href="javascript:" id="rcda13" onclick="activeChange(this.id,'exp!preZscq.do');">知识产权情况</a></li>
						<li class="" id="rcda14li"><a href="javascript:" id="rcda14" onclick="activeChange(this.id,'exp!preOtherinfo.do');">其它</a></li>
					</ul>
				</div>
				
				<div style="width:765px; height:100%; float:right;overflow:auto" id="ch">
					<iframe id='main_dis' name='main_dis' src=''  frameborder=0 marginheight=0 marginwidth=0 style='margin:0px;' scrolling='no' width='100%'></iframe>
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
</body>
	
<script type="text/javascript">
		Event.observe(window, "load", function() {
			$('main_dis').src = 'exp!preExp.do?rcid=<s:property value="rcid"/>&opttype=<s:property value="opttype"/>&pname='+self.name+"&winid="+$('winid').value;
			var reload = 0;
			$('main_dis').height= document.body.clientHeight-195;
			$('mh').style.width = '980px';
			
		}, true);
		/**
		* v:  ID 主键
		* id: 将转向的下一步操作ID
		* isr: 确定是否需跳转 一般是保存操作：就传入 false: 不跳转(留在当前页)
		*/
		function setRcid(v,id,action,isr){
			if(isr){
				$('rcid').value=v;
				$(oldx).className="";
				oldx = '';
				activeChange('rcda'+id,action);
			}else{
				$('rcid').value=v;
			}
		}
		function isactionChange(id){
			if($(id).style.display==''){
				activeChange(id);
			}
		}
		
		function refresh(){
			document.main_dis.refresh();
		}
		
		function dosb(){
			var ajax = new AppAjax("exp!doExpInfoSb.do",function(data){
				if(data.message.code >0){
					alert('数据上报成功！');
				}else{
					alert(data.message.text);
				}
			}).submit();
		}
		
		var diag = null;
		function gethtyy(){
			diag = new Dialog("htyywindows");
			diag.Title = "回退原因";
			diag.Width = 400;
			diag.Height = 150;
			diag.ShowMessageRow=true;
			diag.MessageTitle = "信息回退原因";
			diag.Message = "请根据回退因修改相应的信息";
			diag.URL = "exp!getExphtyy.do?winid=htyywindows";
			diag.show();
		}
		function closeWin(id){
			Dialog.getInstance(id).CancelButton.onclick.apply(Dialog.getInstance(id).CancelButton,[]);
		}
		function refreshmm(){
			document.main_dis.refresh();
		}
</script>
</html>

