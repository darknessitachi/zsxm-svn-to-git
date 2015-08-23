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
		<s:form name="spForm" action="" method="post">
     		
				<table width="100%" cellpadding="0" cellspacing="0" id="conTable" >
							<tr>
								  <td class="lefttop"></td>
								  <td width="%"  class="centertop"></td>
								  <td class="righttop"></td>
						    </tr>
							<tr>
									<td height="26" class="leftcenter">&nbsp;</td>
								    <td class="center" >
								    	撰稿类型：<s:select  id="lmlx" cssStyle="width:100px;" list="lmlxList" listKey="lmid" listValue="lmmc" headerKey="-1" headerValue="全部"/>
								    	信息状态：<select id="xxzt">
								    				<option value="-1">全部</option>
								    				<option value="1">新增</option>
								    				<option value="2">已审批</option>
								    			</select>
						    			撰稿时间：<input type="text" class="Wdate"  id="ksrq" style="text-align:left;width:100"  onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" />-
						    					<input type="text" class="Wdate"  id="jsrq" style="text-align:left;width:100"  onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" />
									    <input type="button" class="but" value="查 询" id="b_sendmessage" onclick="showZgList();" style="margin-right:5px;">
									    <input type="button" class="but" value="修 改" id="b_mod" onclick="xxglMo();" >
									    <input type="button" class="but" value="删 除" id="b_del" onclick="xxglDel()" >
									</td>
							
							   	 <td class="rightcenter"></td>
							</tr>
									
						   <tr>
							    <td class="leftbot"></td>
							    <td class="centerbot"></td>
							    <td class="rightbot"></td>
						   </tr>
					</table>
				<div class="tableContainer" id="fmiscelldw">
					
				</div>
				<div id='butbar' >
					<div class="page" id="pagerContainer"  style="text-align:right;"></div>	
				</div>
		</s:form>
	</body>
	<script>
	
var diag = null;
init();	
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
				bmstr.append('<td width="8%">状态</td><td width="12%">发布人</td><td width="15%">类型</td><td width="36%">标题</td><td width="5%">附件</td><td width="15%">日期</td><td>查看</td></tr></thead>');
	return bmstr.toString();
}
function showZgList(start){
	var ajax = new AppAjax("xxgl!showXxglList.do",getBmUser_callback);
		ajax.add("parMap.sql",getQuerySql());
		ajax.add("limit.length",25);
		ajax.add("limit.start",start || "0");
		ajax.submit();	
}
	function getBmUser_callback(data){
		var bmstr = new StringBuffer();
		var zglist = data.xxglList.data;
		var len = zglist.length;
			for(var i=0;i<len;i++){
				var d = zglist[i];
				var spzt = d.spbz || "";
				bmstr.append('<tr>');
				bmstr.append("<td align='center'>");
					bmstr.append('<input type="radio" value="'+d.xxid+','+d.lmid+'" name="xxzg_rao">');			
				bmstr.append("</td>");
				bmstr.append("<td align='center'>");
					
					var zt = "";
					if(spzt == "1" || spzt==""){
						zt = "新增";
					}else if(spzt == "2"){
						zt = "已审批";
					}else{
						zt = "回退";
					}
					bmstr.append(zt);			
				bmstr.append("</td>");
				bmstr.append("<td align='center'>");
					bmstr.append(d.fbrname);			
				bmstr.append("</td>");
				bmstr.append("<td align='left'>");
					bmstr.append(d.lmmc);			
				bmstr.append("</td>");
				bmstr.append("<td align='left'>");
					bmstr.append(d.xxbt);			
				bmstr.append("</td>");
				bmstr.append("<td align='center'>");
					var _fj = d.fj || 0;
					if(_fj>0){
						bmstr.append("<img src='images/skin0/other/fj.jpg'>");		
					}
						
				bmstr.append("</td>");
				bmstr.append("<td align='center'>");
					bmstr.append(d.rq||"");			
				bmstr.append("</td>");
				
				bmstr.append("<td align='center'>");
					bmstr.append('<a href="#" onclick=showXxzg("'+d.xxid+'")>查 看</a>');			
				bmstr.append("</td>");	
				bmstr.append("</tr>");
			}
		$("fmiscelldw").innerHTML = drawTableHead()+bmstr.toString()+"</table>";
		setPagination(data.xxglList);
	}	
	
	function showXxzg(xxid){
		
		diag = new Dialog("xxglwindows");
		diag.Title = '撰稿信息查看';
		diag.Width = 950;
		diag.Height = 540;
		diag.URL = "xxgl!showXxzg.do?parMap.xxid="+xxid+"&winid=xxglwindows";
		diag.show();
	
	}
		
		function zgsp(){
				var dmArray = getCheckBoxVal();
				if(dmArray.length > 0){
					if(confirm("您确定对["+dmArray.length+"]条记录进行审批？")){
						var ajax = new AppAjax("xxgl!saveZgsp.do",save_callback);
							ajax.add("parMap.spList",dmArray.toString());
							ajax.setBlock(false);
							ajax.setMessage(false);
							ajax.submit();	
					}
					
				}else{
					alert("请选择需要审批的记录！");
					return false;
				}
			
		}
		
		function save_callback(data){
			if(data.code=="-1"){
				alert(data.text);
				return;
			}
			showZgList();
			alert("审批成功！");
			
		}
	
		
		function xxglMo(){
			var r_v = getRadioVal().value;
			if(r_v == ""){
				alert("请选择需要操作的记录！");
				return;
			}
			var d = checkOpt("modify");
			if(d.code=="-1"){
				alert(d.text);
				return;
			}
			diag = new Dialog("xxglwindows");
			diag.Title = '撰稿信息修改';
			diag.Width = 950;
			diag.Height = 540;
			diag.URL = "xxgl!xxzgModify.do?query.xxid="+r_v.split(",")[0]+"&winid=xxglwindows";
			diag.show();
		}
		
		function xxglDel(){
			var r_v = getRadioVal().value;
			if(r_v == ""){
				alert("请选择需要操作的记录！");
				return;
			}
			
			var d = checkOpt("del");
			if(d.code=="-1"){
				alert(d.text);
				return;
			}
			var c_o = getRadioVal();
			var ri = c_o.parentNode.parentNode.rowIndex;
			if(confirm("确定要删除此条撰稿信息？")){
				var ajax = new AppAjax("xxgl!delXxzg.do",function(data){
					alert(data.text);
					if(data.code=="1"){
						//$("dwTable").deleteRow(ri);
						showZgList(0);
					}
				});
				ajax.add("parMap.xxid",r_v.split(",")[0]);
				ajax.submit();	
			}
		}
		function getQuerySql(){
			var sql = "";
			if($("lmlx").value!="-1"){
				sql += " and info_lmzg.lmid = '"+$("lmlx").value+"'";
			}
			if($("xxzt").value!="-1"){
				sql += " and info_zg.spbz='"+$("xxzt").value+"'";
			}
			if($("ksrq").value!=""){
				sql += " and info_zg.rq>='"+$("ksrq").value+"'";
			}
			if($("jsrq").value!=""){
				sql += " and info_zg.rq<='"+$("jsrq").value+"'";
			}
			return sql;
		
		}
		
		
function setPagination(page){ 
	   if(!page)return;
	   var str = new StringBuffer("");
	 //  str.append(page.currentPageNo +"/"+page.totalPageCount);
	   //str.append(" 第<input type='text' class='pagebox' value=\""+page.currentPageNo+"\"/>/"+page.totalPageCount+"页");
	   var count = 0;
	   if(page.hasPreviousPage&&!page.hasNextPage){
		    str.append(" <input type='button' class='pagea-left' value=' ' onclick=\"goPage('"+count+"',\'1\',\'"+page.totalCount+"\',\'"+page.totalPageCount+"\');\"/> ");
		    str.append(" <input type='button' class='pagea-l' value=' '  onclick=\"goPage('"+count+"',\'"+(page.currentPageNo - 1)+"\',\'"+page.totalCount+"\',\'"+page.totalPageCount+"\');\"/>");
		   	str.append(" 第<input type='text' class='pagebox' onkeypress=\"if(event.keyCode==13)return onchange();\" onchange=\"goPage('"+count+"',this.value,\'"+page.totalCount+"\',\'"+page.totalPageCount+"\');\" value=\""+page.currentPageNo+"\"/>/"+page.totalPageCount+"页");
		    str.append(" <input type='button' class='pagea-r' value=' '/>");
		    str.append(" <input type='button' class='pagea-right' value=' '/>");
		}
		if(page.hasNextPage&&!page.hasPreviousPage){
			str.append(" <input type='button' class='pagea-left' value=' ' /> ");
		    str.append(" <input type='button' class='pagea-l' value=' '  />");
		   	str.append(" 第<input type='text' class='pagebox' onkeypress=\"if(event.keyCode==13)return onchange();\" onchange=\"goPage('"+count+"',this.value,\'"+page.totalCount+"\',\'"+page.totalPageCount+"\');\"  value=\""+page.currentPageNo+"\"/>/"+page.totalPageCount+"页");
		    str.append(" <input type='button' class='pagea-r' value=' ' onclick=\"goPage('"+count+"',\'"+(page.currentPageNo + 1)+"\',\'"+page.totalCount+"\',\'"+page.totalPageCount+"\')\"/>");
		    str.append(" <input type='button' class='pagea-right' value=' ' onclick=\"goPage('"+count+"',\'"+(page.totalPageCount)+"\',\'"+page.totalCount+"\',\'"+page.totalPageCount+"\')\"/>");
		}
		if(page.hasNextPage&&page.hasPreviousPage){
			str.append(" <input type='button' class='pagea-left' value=' '  onclick=\"goPage('"+count+"',\'1\',\'"+page.totalCount+"\')\"/> ");
		    str.append(" <input type='button' class='pagea-l' value=' ' onclick=\"goPage('"+count+"',\'"+(page.currentPageNo - 1)+"\',\'"+page.totalCount+"\')\" />");
		   	str.append(" 第<input type='text' class='pagebox' onkeypress=\"if(event.keyCode==13)return onchange();\" onchange=\"goPage('"+count+"',this.value,\'"+page.totalCount+"\',\'"+page.totalPageCount+"\');\"  value=\""+page.currentPageNo+"\"/>/"+page.totalPageCount+"页");
		    str.append(" <input type='button' class='pagea-r' value=' ' onclick=\"goPage('"+count+"',\'"+(page.currentPageNo + 1)+"\',\'"+page.totalCount+"\',\'"+page.totalPageCount+"\')\"/>");
		    str.append(" <input type='button' class='pagea-right' value=' ' onclick=\"goPage('"+count+"',\'"+(page.totalPageCount)+"\',\'"+page.totalCount+"\',\'"+page.totalPageCount+"\')\"/>");
		    
		}
		str.append("");
		$("pagerContainer").innerHTML = str.toString();
   }
   
   function goPage(indexed,start,size,pageCount){
  	if(!checkNumber(start)){
  		alert("输入的页数不为数值型，请重新输入！");
  		return;
  	}
  	if(parseInt(start) > parseInt(pageCount)){
  		alert("输入的页数不能超过总页数!");
  		return;
  	}
   showZgList(start);
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

function checkOpt(flag){
	var d = null;
	
	var ajax = new AppAjax("xxgl!checkXxglOpt.do",function(data){
		d = data;
	});
	ajax.setAsyn(false);
	
	ajax.add("parMap.xxid",getRadioVal().value.split(",")[0]);
	ajax.add("parMap.lmid",getRadioVal().value.split(",")[1]);
	ajax.add("parMap.opt",flag);
	ajax.submit();
	return d;

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


