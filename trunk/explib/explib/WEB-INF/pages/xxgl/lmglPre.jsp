<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<script src="Framework/Main.js" type="text/javascript"></script>
		<script language="JavaScript"src="<s:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>	
		<style type="text/css"></style>
	</head>

	<body style="margin: 0px; overflow: hidden; margin: 2px;">
		 <div class="butbar" id="butbar">
								<div class="left">
								<input type="button" class="sbutm" onMouseOver="this.className ='sbutm_over'" onMouseout="this.className='sbutm'" value="添加栏目" onclick="lmglDetail('add');">
								<input type="button" class="sbutm" onMouseOver="this.className ='sbutm_over'" onMouseout="this.className='sbutm'" value="修改栏目" onclick="lmglDetail('mod');">	
								<input type="button" class="sbutm" onMouseOver="this.className ='sbutm_over'" onMouseout="this.className='sbutm'" value="删除栏目" onclick="delLminfo();">
								</div>
		</div>
		<s:form name="spForm" action="" method="post">
				<div class="tableContainer" id="fmiscelldw">
					
				</div>
		
		</s:form>
	</body>
	<script>
init();	
var diag = null;
var seldwdm = "";
function init(){
	setpos();
	showZgList();
}
function setpos(){
	$("fmiscelldw").style.height=(getSize().h-70)+"px" ;
}

function drawTableHead(){
	var bmstr = new StringBuffer();
	bmstr.append('<table id="dwTable" cellspacing="0">');
		bmstr.append('<thead>');
			bmstr.append('<tr>');
				bmstr.append('<td width="4%">');
					bmstr.append('选择');
				bmstr.append('</td>');
				bmstr.append('<td width="30%">栏目名称</td><td width="50%">栏目权限</td><td width="10%">设置权限</td></tr></thead>');
	return bmstr.toString();
}

function showZgList(start){
	var ajax = new AppAjax("xxgl!queryLminfo.do",q_callback);
		ajax.submit();	
}
	function q_callback(data){
		var list = data.lmglList;
		var len = list.length;
		var bmstr = new StringBuffer();
			for(var i=0;i<len;i++){
				var d = list[i];
				bmstr.append("<tr>");
				bmstr.append("<td align='center'>");
					bmstr.append('<input type="radio" value="'+d.lmid+'" name="xxzg_rao">');			
				bmstr.append("</td>");
				bmstr.append("<td align='left'>");
					bmstr.append(d.lmmc);			
				bmstr.append("</td>");
				bmstr.append("<td align='left'>");
					bmstr.append(d.lmqx);			
				bmstr.append("</td>");
				
				bmstr.append("<td align='center'>");
					bmstr.append('<a href="#" onclick=szDetail("'+d.lmid+'")>设 置</a>');			
				bmstr.append("</td>");	
				bmstr.append("</tr>");
			}
		$("fmiscelldw").innerHTML = drawTableHead()+bmstr.toString()+"</table>";
		
	}	
	
	function lmglDetail(flag){
		var sv = "";
		if(flag=="mod"){
			var c_v = getRadioVal().value;
			if(c_v==""){
				alert("请选择一条记录进行修改！");
				return;
			}
			sv = c_v;
		}
		diag = new Dialog("lmglwindows");
		diag.Title = '栏目管理';
		diag.Width = 450;
		diag.Height = 360;
		diag.URL = "xxgl!lmglDetail.do?parMap.lmid="+sv+"&winid=lmglwindows";
		diag.show();
	}
	function szDetail(lmid){
		diag = new Dialog("lmglwindows");
		diag.Title = '栏目管理';
		diag.Width = 450;
		diag.Height = 360;
		diag.URL = "xxgl!lmglDetail.do?parMap.lmid="+lmid+"&winid=lmglwindows";
		diag.show();
	}
	function delLminfo(){
		var c_v = getRadioVal().value;
		var sv = "";
				
				if(c_v==""){
					alert("请选择一条记录进行修改！");
					return;
				}
				sv = c_v;
			
		var c_o = getRadioVal();
		var ri = c_o.parentNode.parentNode.rowIndex;
		if(confirm("是否确认删除此条栏目！")){
			var ajax = new AppAjax("xxgl!delLmgl.do",function(data){
					alert(data.text);
					if(data.code=="1"){
						$("dwTable").deleteRow(ri);
					}
				});
			ajax.add("parMap.lmid",sv);
			ajax.submit();	
		
		}
	}
		
		
function checkNumber ( s ){   
	var regu = "^[0-9]+$";
	var re = new RegExp(regu);
	if (s.search(re) != -1) {
	   return true;
	} else {
	   return false;
	}
}
function getRadioVal(){
			var c_objs = document.getElementsByName("xxzg_rao");
			var c_len = c_objs.length;
			for(var i=0;i<c_len;i++){
				if(c_objs[i].checked)return c_objs[i];
			}
			
			return "";
		}
	
</script>
</html>


