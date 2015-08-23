<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<title></title>
		<%@ include file="/common/meta.jsp"%>
	
<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
<script type="text/javascript" src="<c:url value="/js/xm_init.js"/>" ></script>

</head>


<body >
<s:component template="xtitle" theme="app" value='项目信息修改'></s:component>
<s:hidden name="xmid"></s:hidden>
<s:hidden name="opttype"></s:hidden>
<s:hidden name="pname"></s:hidden>
<div class="main" id="mh" ><!--leftmenu start--><div  class="leftmenu" >

<ul class="nav">
	<li class="on" id="rcdali1"><span class="red">*</span><a href="javascript:" id="rcda1" onclick="activeChange('rcdali1');">招商项目信息</a></li><!-- 标签下的内容填写完，加over -->
	
	<li class="" id="rcdali2"><a href="javascript:" id="rcda2" onclick="activeChange('rcdali2');">对方联系人</a></li><!-- 选中的标签，加on -->
	<li class="" id="rcdali3"><a href="javascript:" id="rcda3" onclick="activeChange('rcdali3');">进展情况</a></li>

</ul>

</div><!--leftmenu end--><!--center start-->
<div style="width:775px; height:100%; float:right;overflow:auto" id="ch">
	<iframe id='rcda_dis' name='rcda_dis' src=''  frameborder=0 marginheight=0 marginwidth=0 style='margin:0px;' scrolling='no' width='100%'></iframe>
</div>
</div>

</body>
	
<script type="text/javascript">
		$('rcda_dis').src = 'zsxm!preZsxm.do?xmid=<s:property value="xmid"/>&opttype=<s:property value="opttype"/>&pname='+self.name;
		var reload = 0;
		$('rcda_dis').height= document.body.clientHeight-40;
		if(getSize().w > 960){
			$('mh').style.width = 960+'px';
		}else{
			$('ch').style.width= getSize().w-170+'px';
		}

		function closeWindows(){closeWin(self.name);}
		function closeWin(sid){
			if(reload == 1){
				getOpener().refresh();
			}
			parent.closeXwin(sid);
		}
		/**
		* v:  单位ID 主键
		* id: 将转向的下一步操作ID
		* isr: 确定是否需跳转 一般是保存操作：就传入 false: 不跳转(留在当前页)
		*/
		function setDwid(v,id,isr){
			if(isr){
				document.all.xmid.value=v;
				oldx = '';
				activeChange('rcdali'+id);
			}else{
				document.all.xmid.value=v;
			}
		}
		
		function refresh(){
			document.rcda_dis.refresh();
		}
		
		function setZzxm(id,mc){
			document.rcda_dis.setZzxm(id,mc);
		}
		function setVByTree(id_dm,id_mc,dm,mc){
			document.rcda_dis.setVByTree(id_dm,id_mc,dm,mc);
		}
</script>
</html>

