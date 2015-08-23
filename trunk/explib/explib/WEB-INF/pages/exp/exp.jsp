<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title></title>
	<%@ include file="/common/meta.jsp"%>
	<script src="Framework/Main.js" type="text/javascript"></script>
	<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
	<script type="text/javascript" src="<c:url value="/js/rc_init.js"/>" ></script>

</head>


<body >
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
			<li class="" id="rcda6li"><a href="javascript:" id="rcda6" onclick="activeChange(this.id,'exp!preRyhj.do');">获奖情况</a></li>
			<li class="" id="rcda8li"><a href="javascript:" id="rcda8" onclick="activeChange(this.id,'exp!preShjz.do');">社会兼职情况</a></li>
			<!-- <li class="" id="rcda9li"><a id="rcda9"href="javascript:" onclick="activeChange(this.id,'exp!preBpzj.do');">专家类别</a></li> -->
			<li class="" id="rcda10li"><a href="javascript:" id="rcda10" onclick="activeChange(this.id,'exp!preZylz.do');">主要论著论文</a></li>
			<li class="" id="rcda13li"><a href="javascript:" id="rcda13" onclick="activeChange(this.id,'exp!preZscq.do');">知识产权情况</a></li>
			<li class="" id="rcda14li"><a href="javascript:" id="rcda14" onclick="activeChange(this.id,'exp!preOtherinfo.do');">其它</a></li>
		</ul>
	</div>
	
	<div style="width:765px; height:100%; float:right;overflow:auto" id="ch">
		<iframe id='main_dis' name='main_dis' src=''  frameborder=0 marginheight=0 marginwidth=0 style='margin:0px;' scrolling='no' width='100%'></iframe>
	</div>
</div>

</body>
	
<script type="text/javascript">
		Event.observe(window, "load", function() {
			$('main_dis').src = 'exp!preExp.do?rcid=<s:property value="rcid"/>&opttype=<s:property value="opttype"/>&pname='+self.name;
			var reload = 0;
			$('main_dis').height= document.body.clientHeight-40;
			if(getSize().w > 960){
				$('mh').style.width = 960+'px';
			}else{
				$('ch').style.width= getSize().w-200+'px';
			}
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
		

</script>
</html>

