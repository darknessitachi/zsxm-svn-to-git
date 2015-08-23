<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<title></title>
		<%@ include file="/common/meta.jsp"%>
	
<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
<script type="text/javascript" src="<c:url value="/js/ht_init.js"/>" ></script>

</head>


<body >
<s:hidden name="htid"></s:hidden>
<s:hidden name="opttype"></s:hidden>
<s:hidden name="pname" value="mainFrame"></s:hidden>
<div class="main" id="mh" ><!--leftmenu start--><div  class="leftmenu" >

<ul class="nav">
	<li class="on" id="rcdali1"><span class="red">*</span><a href="javascript:" id="rcda1" onclick="activeChange('rcdali1');">房租合同</a></li><!-- 标签下的内容填写完，加over -->
	<li class="" id="rcdali2"><a href="javascript:" id="rcda2" onclick="activeChange('rcdali2');">房租收取</a></li>
	<li class="" id="rcdali8"><a id="rcda7" href="javascript:" >-------</a></li>
	<li class="" id="rcdali9"><a href="javascript:" id="rcda8" >-------</a></li>
	<li class="" id="rcdali10"><a id="rcda9"href="javascript:" >-------</a></li>

</ul>

</div><!--leftmenu end--><!--center start-->
<div style="width:765px; height:100%; float:right;overflow:auto" id="ch">
	<iframe id='rcda_dis' name='rcda_dis' src=''  frameborder=0 marginheight=0 marginwidth=0 style='margin:0px;' scrolling='no' width='100%'></iframe>
</div>
</div>

</body>
	
<script type="text/javascript">
		$('rcda_dis').src = 'fzhtview!preFzht.do?htid=<s:property value="htid"/>&opttype=<s:property value="opttype"/>&pname='+self.name;
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
		function setHtid(v,id,isr){
			if(isr){
				document.all.htid.value=v;
				oldx = '';
				activeChange('rcdali'+id);
			}else{
				document.all.htid.value=v;
			}
		}
		
		function refresh(){
			document.rcda_dis.refresh();
		}
		
		function setDw(id,mc){
			document.rcda_dis.setDw(id,mc);
		}
</script>
</html>

