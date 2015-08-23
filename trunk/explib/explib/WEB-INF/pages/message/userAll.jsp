<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
		<script type="text/javascript" src="<c:url value="/js/comm.js"/>" ></script>	
		<SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxcommon.js"></SCRIPT>
       <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree.js"></SCRIPT>
       <link type="text/css" href="js/dhtmlxTree/dhtmlxtree.css"rel="stylesheet" >
        <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree_json.js"></SCRIPT>
        <script   type="text/javascript" src="js/dhtmlxTree/dhtmlXTreeExtend.js" ></script>			
	<style>
		.fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
	</style>
	</head>

	<body>
	<s:form name="czrcForm" action='rcda!preCpjs.do' method="post" >
	<s:hidden name="rcid"></s:hidden>
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="opttype"></s:hidden>
	<s:hidden name="winid" id="winid"></s:hidden>
	<div class="butbar" id="butbar">
		<div class="left">	
			<input type="checkbox"  onclick="COM.checkboxAll('userdm1',this.checked);selgz_a(this.checked);"/><b>选择工作人员</b>
		</div>
	</div>	
	<div  class="tableContainer" id="tableContainer1" style="width:100%">
		<table id="tac">
			<script>
				var su = new StringBuffer();
				var i = 0;
				<s:iterator value="userlist">
					if(i==0) {su.append('<tr>');}
					++i;
					su.append('<td><input type=checkbox id="userdm1" value="<s:property value="userdm"/>##<s:property value="usermc"/>" onclick="selGz(\'<s:property value="userdm"/>##<s:property value="usermc"/>\',this.checked)"/><s:property value="usermc"/></td>');
					if(i==5){
						su.append('</tr>');
						i=0;
					}
				</s:iterator>
				if(i != 5){su.append('</tr>');}	
				document.write(su.toString());
			</script>
		</table>
	</div>
	<div class="butbar" id="butbar">
		<div class="left">	
		<input type="checkbox"  onclick="COM.checkboxAll('userdm2',this.checked);selrc_a(this.checked);"/><b>选择专家</b>
		&nbsp&nbsp&nbsp&nbsp
		字段:
    	<select name="query.name" style="width:100px" onchange="changefield(this.value)">
   						<option value="rcname">姓名</option>
			    		<option value="sex">性别</option>
			    		<option value="nation">国籍</option>
			    		<option value="zjlb">专家类别</option>
			    		<option value="zgbm">主管部门</option>
			    		<option value="xl">学历</option>
			    		<option value="xw">学位</option>
		</select>
    	<span id="xmdis">
    		<s:textfield name="query.value" id="query.value" cssStyle="width:100px"></s:textfield>
    	</span>
    	&nbsp
		<input type="button" class="button3"  value="查   询" onclick="queryxx()"/>
		</div>
	</div>
	<div  class="tableContainer" id="tableContainer2" style="width:100%">
		<table id="tac">
			<script>
				var su = new StringBuffer();
				var i = 0;
				<s:iterator value="rcList">
					if(i==0) {su.append('<tr>');}
					++i;
					su.append('<td><input type=checkbox id="userdm2" value="<s:property value="userdm"/>##<s:property value="usermc"/>" onclick="selRc(\'<s:property value="userdm"/>##<s:property value="usermc"/>\',this.checked)"/><s:property value="usermc"/></td>');
					if(i==6){
						su.append('</tr>');
						i=0;
					}
				</s:iterator>
				if(i != 6){su.append('</tr>');}	
				document.write(su.toString());
			</script>
		</table>
	</div>
	<div  class="tableContainer" id="tableContainer3" style="width:100%;height:80px">
	</div>
	
	<div loading='0' id='xtdlb' class="fsg_nr" style="display:none;width:270;height:200;overflow:auto;">
	<div id='xtdlbloadimage'>
		<img src="images/skin0/other/upload.gif">数据载入中.....
	</div>
	<div loading='0' id='xtdlbload' style="width:100%;height:195;background-color:#E7EAF7;overflow:auto;">
		
	</div>
	
	<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
</div>				
	<div class="footer">
		<input type="button" class="button3" value="确 定 选 择" onclick="getUser()"/>
        <input type="button" name="resetBtn" class="button3" value="关    闭" onclick="closeWin(self.name);"/>
	</div>	
	</s:form>
	</body>

	<script type="text/javascript">
		$('tableContainer1').style.height = 100+"px"; 
		$('tableContainer2').style.height = (getSize().h - 330)+"px";
		$('tableContainer3').style.height = 80+"px";
		function queryxx(){
			var ajax = new AppAjax("message!getUserByDx.do?query.name="+$('query.name').value+"&query.value="+$('query.value').value,query_back).submit();
		}
		
		function query_back(data){
			var qstr = new StringBuffer('<table>');
			var ii=0;
			if(data.data!=null){
				var len = data.data.length;
				for(var k=0;k<len;k++){
					if(ii==0){
						qstr.append('<tr>');
					}
					++ii;
					qstr.append('<td><input type=checkbox name="userdm2" id="userdm2" value="'+data.data[k].userdm+'##'+data.data[k].usermc+'"  onclick="selRc(\''+data.data[k].userdm+'##'+data.data[k].usermc+'\',this.checked);selgz_a(this.checked);"/>'+data.data[k].usermc+'</td>');
					if(ii==6){
						qstr.append('</tr>');
						ii=0;
					}
				}
				if(ii != 6){qstr.append('</tr>');}
				qstr.append('</table>');
				$('tableContainer2').innerHTML = qstr.toString();
			}
		}
		
		function getUser(){
			var xtsel = COM.checkbox('dmkey');
			//var rcsel = RC.checkbox('userdm2');
			
			if(xtsel.length==0){
				alert("请选择收件人！");
				return false;
			}
			
			var dms = '',names = '';
			for(var j=0;j<xtsel.length;j++){
				dms += ','+xtsel[j].v.split('##')[0];
				names += ','+xtsel[j].v.split('##')[1];
			}
			
			
			dms = dms.substr(1);
			names = names.substr(1);
			parent.mainframe.mainframe.document.all.tosend.value=names;
			parent.mainframe.mainframe.document.all.userdms.value=dms;
			parent.mainframe.mainframe.document.all.textusermcs.value = names;
			closeWin(self.name);
		}
		
		
		
	function changefield(v){
		$('xtdlb').loading = 0;
		if(v != 'rcname'){
			var dm = '';
			if(v=='sex'){
				dm=4;
			}else if(v=='nation'){
				dm=20;
			}else if(v=='zjlb'){
				dm= 25;
			}else if(v=='zgbm'){
				dm=21;
			}else if(v=='dwdq'){
				dm=22;
			}else if(v=='xl'){
				dm=2;
			}else if(v=='xw'){
				dm=3;
			}
			$('xmdis').innerHTML = '<INPUT id="zsxm_button" class="selectBut2"  title="选择" value="点击选择" type=button onclick="O_D(\'zsxm_button\',\'xtdlb\',\'bottom\');loadTree(\''+dm+'\');"/><input type="hidden" name="query.value" id="query.value"/>';
		}else{
			$('xmdis').innerHTML = '<s:textfield name="query.value" id="query.vlaue" cssStyle="width:100px"></s:textfield>';
		}
	}
	
var tree;
	function loadTree(dm){
		if($("xtdlb").loading != 1){
			$('xtdlbload').innerHTML = "";
			tree=new dhtmlXTreeObject("xtdlbload","100%","100%",0);
			tree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
			tree.setOnClickHandler(function(id){setV(id);});
			tree.enableSmartXMLParsing(true);
			tree.setDataMode("json");
			tree.enableCheckBoxes(false);
			tree.loadJSON("rcda!getQueryTree.do?dm="+dm,function(){$('xtdlbloadimage').style.display="none";});
			$('xtdlb').loading = 1;			
		}
	}
	function setV(id){
		if(id=='root'){
			$('query.value').value='';
		}else{
			$('query.value').value=id;
		}
		$('xtdlb').style.display = 'none';
		$('zsxm_button').value = tree.getItemText(id);
	}
	
	function selGz(v,b){
		var dm = v.split('##')[0];
		var mc = v.split('##')[1];
				
		if($('g'+dm)){
			$('g'+dm).checked = b;
			if(b){
				$('sg'+dm).style.display = '';
			}else{
				$('sg'+dm).style.display = 'none';
			}
		}else{
			$('tableContainer3').innerHTML += "<span id='sg"+dm+"'><input type='checkbox' id='g"+dm+"' name=dmkey checked value ='"+v+"'>"+mc+"&nbsp|&nbsp</span>";
		}
	}
	
	function selRc(v,b){
		var dm = v.split('##')[0];
		var mc = v.split('##')[1];
		
		if($('r'+dm)){
			$('r'+dm).checked = b;
			if(b){
				$('sr'+dm).style.display = '';
			}else{
				$('sr'+dm).style.display = 'none';
			}
		}else{
			$('tableContainer3').innerHTML += "<span id='sr"+dm+"'><input type='checkbox' id='r"+dm+"' name=dmkey checked value ='"+v+"'>"+mc+"&nbsp|&nbsp</span>";
		}
	}
	
	function selgz_a(b){
		var a = COM.checkbox('userdm1');
		var len = a.length;
		var dm = '';
		var mc = '';
		if(b){
			for(var i=0;i<len;i++){
				dm = a[i].v.split('##')[0];
				mc = a[i].v.split('##')[1];
			
				if($('g'+dm)){
					$('g'+dm).checked = b;
					if(b){
						$('sg'+dm).style.display = '';
					}else{
						$('sg'+dm).style.display = 'none';
					}
				}else{
					$('tableContainer3').innerHTML += "<span id='sg"+dm+"'><input type='checkbox' id='g"+dm+"' name=dmkey checked value ='"+a[i].v+"'>"+mc+"&nbsp|&nbsp</span>";
				}
			}
		}else{
			var n = document.getElementsByName("dmkey");
			var l = n.length;
			for(var i=0;i<l;i++){
				if(n[i].id.indexOf('g')>=0){
					n[i].checked =b;
					$('s'+n[i].id).style.display = 'none';
				}
			}
			
		}
	}
	
	function selrc_a(b){
		var a = COM.checkbox('userdm2');
		var len = a.length;
		var dm = '';
		var mc = '';
		if(b){
			for(var i=0;i<len;i++){
				dm = a[i].v.split('##')[0];
				mc = a[i].v.split('##')[1];
			
				if($('r'+dm)){
					$('r'+dm).checked = b;
					if(b){
						$('sr'+dm).style.display = '';
					}else{
						$('sr'+dm).style.display = 'none';
					}
				}else{
					$('tableContainer3').innerHTML += "<span id='sr"+dm+"'><input type='checkbox' id='r"+dm+"' name=dmkey checked value ='"+a[i].v+"'>"+mc+"&nbsp|&nbsp</span>";
				}
			}
		}else{
			var n = document.getElementsByName("dmkey");
			var l = n.length;
			for(var i=0;i<l;i++){
				if(n[i].id.indexOf('r')>=0){
					n[i].checked =b;
					$('s'+n[i].id).style.display = 'none';
				}
			}
		}
	}
	</script>
</html>

