<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<title>高</title>
		<%@ include file="/common/meta.jsp"%>
	
<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
<script type="text/javascript" src="<c:url value="/js/dwshview_init.js"/>" ></script>

</head>


<body >
<s:component template="xtitle" theme="app" value='单位信息审核'></s:component>
<s:hidden name="dwid" id="dwid"></s:hidden>
<s:hidden name="opttype" id="opttype"></s:hidden>
<s:hidden name="pname"></s:hidden>
<div class="main" id="mh" ><!--leftmenu start--><div  class="leftmenu" >

<ul class="nav">
	<li class="on" id="rcdali1"><span class="red">*</span><a href="javascript:" id="rcda1" onclick="activeChange('rcdali1');">入驻单位信息</a></li><!-- 标签下的内容填写完，加over -->
	<li class="" id="rcdali2"><a href="javascript:" id="rcda2" onclick="activeChange('rcdali2');">添加联系人</a></li>
	<li class="" id="rcdali3"><a href="javascript:" id="rcda3" onclick="activeChange('rcdali3');">单位股权比例</a></li>
	<li class="" id="rcdali6"><a href="javascript:" id="rcda6" onclick="activeChange('rcdali6');">拥有知识产权情况</a></li>
	<li class="" id="rcdali8"><a href="javascript:" id="rcda8" onclick="activeChange('rcdali8');">承担横向项目情况</a></li>
	<li class="" id="rcdali9"><a href="javascript:" id="rcda9" onclick="activeChange('rcdali9');">实验室研发中心建设情况</a></li>
	<li class="" id="rcdali10"><a href="javascript:" id="rcda10" onclick="activeChange('rcdali10');">研发机构孵化企业情况</a></li>
	<li class="" id="rcdali11"><a href="javascript:" id="rcda11" onclick="activeChange('rcdali11');">奖励情况</a></li>
	


</ul>

</div><!--leftmenu end--><!--center start-->
<div style="width:765px; height:100%; float:right;overflow:auto" id="ch">
	<iframe id='rcda_dis' name='rcda_dis' src=''  frameborder=0 marginheight=0 marginwidth=0 style='margin:0px;' scrolling='no' width='100%'></iframe>
</div>
</div>
<s:form name="zsdwForm" id="zsdwForm"  method="post" >
<div class="footer" style="text-align:center;background:#efefef">
		<input class="button3" type="button" value="审 核 通 过" onclick="doSave(0)"/>
		<input class="button3" type="button" value="不  通  过" onclick="doSave(1)"/>
		回退原因:<input type="text" name="thyy" id="thyy" value=""/>
</div>
</s:form>
</body>
	
<script type="text/javascript">
		$('rcda_dis').src = 'dwshview!preZsdw.do?dwid=<s:property value="dwid"/>&opttype=<s:property value="opttype"/>&pname='+self.name;
		var reload = 0;
		$('rcda_dis').height= document.body.clientHeight-80;
		if(getSize().w > 960){
			$('mh').style.width = 960+'px';
		}else{
			$('ch').style.width= getSize().w-195+'px';
		}
		
		$('mh').style.height = document.body.clientHeight-40;
		$('ch').style.height = document.body.clientHeight-40;
		
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
		function setVByTree(id_dm,id_mc,dm,mc){
			document.rcda_dis.setVByTree(id_dm,id_mc,dm,mc);
		}
		
		function doSave(type){
			if(type==1){
				if($('thyy').value==''){
					alert('请填写回退原因!');
					return false;
				}
				var ajax = new AppAjax("dwsh!doShth.do?dwid="+$('dwid').value,function(data){save_Back(data,type)}).submitForm("zsdwForm");
			}else{
				var ajax = new AppAjax("dwsh!doShtg.do?dwid="+$('dwid').value,function(data){save_Back(data,type)}).submitForm("zsdwForm");
			}
			
		}
		
		function save_Back(data,type){
			if(data.message.code >0){
			
				if(type == 1){
					alert('数据已退回');
				}else{
					alert('数据已审核通过');
				}
				getOpener().refresh();
				closeWin(self.name);
			}else{
				alert(data.message.text);
			}
		}		
</script>
</html>

