<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<title>高</title>
		<%@ include file="/common/meta.jsp"%>
	
<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
<script type="text/javascript" src="<c:url value="/js/rc_init.js"/>" ></script>

</head>


<body >
<s:hidden name="dwid" id="dwid"></s:hidden>
<s:hidden name="opttype" id="opttype"></s:hidden>
<s:hidden name="pname" value="mainFrame"></s:hidden>
<div class="main" id="mh" ><!--leftmenu start--><div  class="leftmenu" >

<ul class="nav">
	<li class="on" id="rcdali1"><span class="red">*</span><a href="javascript:" id="rcda1" onclick="activeChange('rcdali1');">入驻单位信息</a></li><!-- 标签下的内容填写完，加over -->
	<li class="" id="rcdali2"><a href="javascript:" id="rcda2" onclick="activeChange('rcdali2');">添加联系人</a></li>
	<li class="" id="rcdali3"><a href="javascript:" id="rcda3" onclick="activeChange('rcdali3');">单位股权比例</a></li><!-- 选中的标签，加on -->
	<li class="" id="rcdali4"><a href="javascript:" id="rcda4" onclick="activeChange('rcdali4');">承担纵向项目情况</a></li>
	<li class="" id="rcdali5"><a href="javascript:" id="rcda5" onclick="activeChange('rcdali5');">拥有知识产权情况</a></li>
	<li class="" id="rcdali6"><a href="javascript:" id="rcda6" onclick="activeChange('rcdali6');">奖励情况</a></li>
	<li class="" id="rcdali7"><a href="javascript:" id="rcda7" onclick="activeChange('rcdali7');">承担横向项目情况</a></li>
	<li class="" id="rcdali8"><a href="javascript:" id="rcda8" onclick="activeChange('rcdali8');">机构人员情况</a></li>
	<li class="" id="rcdali9"><a href="javascript:" id="rcda9" >-------</a></li>
	<li class="" id="rcdali10"><a id="rcda10"href="javascript:" >-------</a></li>

</ul>

</div><!--leftmenu end--><!--center start-->
<div style="width:765px; height:100%; float:right;overflow:auto" id="ch">
	<iframe id='rcda_dis' name='rcda_dis' src=''  frameborder=0 marginheight=0 marginwidth=0 style='margin:0px;' scrolling='no' width='100%'></iframe>
</div>
</div>

</body>
	
<script type="text/javascript">
		$('rcda_dis').src = 'zsdw!preZsdw.do?dwid=<s:property value="dwid"/>&opttype=<s:property value="opttype"/>&pname='+self.name;
		var reload = 0;
		$('rcda_dis').height= document.body.clientHeight-40;
		if(getSize().w > 960){
			$('mh').style.width = 960+'px';
		}else{
			$('ch').style.width= getSize().w-170+'px';
		}
		
		/**
		* v:  单位ID 主键
		* id: 将转向的下一步操作ID
		* isr: 确定是否需跳转 一般是保存操作：就传入 false: 不跳转(留在当前页)
		*/
		function setDwid(v,id,isr){
			if(isr){
				document.all.dwid.value=v;
				oldx = '';
				activeChange('rcdali'+id);
			}else{
				document.all.dwid.value=v;
			}
		}
		
		function refresh(){
			document.rcda_dis.refresh();
		}
		
		function setZzxm(id,mc){
			document.rcda_dis.setZzxm(id,mc);
		}
</script>
</html>

