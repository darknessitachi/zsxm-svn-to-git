<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
	</head>
	<body  style="overflow:auto">
		<div class="butbar" id="butbar">
		   <div class="left"> 菜单权限配置</div>
		</div>
		<div>
			<div id="bjmainplan"  class="tableContainer" style="width:99.5%"></div>
			<div id="pagerContainer"  class="page" style="text-align:right;"></div>
	    </div>
	</body>
	<script type="text/javascript">
	${'bjmainplan'}.style.height = window.screen.availHeight * 0.6;
	getuser(0);
	                                      
	function getuser(start){
		var ajax = new AppAjax("quto!getAlluser.do",getuser_callback);
			ajax.add("limit.length",25);
			ajax.submit();
	}

	
	function getuser_callback(data){
		var tableStr = new StringBuffer('<table align="center" ><thead>');
		tableStr.append('<tr><td style="width:4%;">序号</td>');
		tableStr.append('<td style="width:20%;">用户登录名</td>');
		tableStr.append('<td style="width:20%;">用户姓名</td>');
		tableStr.append('<td style="width:10%;">菜单权限</td>');
		tableStr.append('<td style="width:50%;">&nbsp;</td></tr></thead><tbody>');
	
		if(data && data["data"].length > 0){	
			var sdata = data["data"];
		    var dataLength = sdata.length;
			for(var i=0;i< dataLength ;i++){
				tableStr.append('<tr><td align="center">'+(i+1)+' &nbsp;</td>');
				tableStr.append('<td>'+sdata[i].LOGINNAME+'&nbsp;</td>');
				tableStr.append('<td> '+sdata[i].CNNAME+'&nbsp;</td>');
				tableStr.append('<td align=center><a href="javascript:getMenu('+sdata[i].USERID+')">配置</a><td>&nbsp;</td></tr>');
			}
		}else{
			tableStr.append('<tr><td colspan="13"><font color="red">查无数据!</font ></td></tr>');
		}
		tableStr.append('</tbody></table>');
		var  planstr =  tableStr.toString();
	
		$("bjmainplan").innerHTML=planstr;
		
		setPagination(data);
	}

	function getMenu(userId){
       if(!userId) return false;
       openWin("quto!menu.do?userid="+userId,"450","400");
	}
	
	function setPagination(page){ 
		   if(!page)return;
		   var str = new StringBuffer("");
		   //str.append(page.currentPageNo +"/"+page.totalPageCount);
		   str.append(" 第<input type='text' class='pagebox' value=\""+page.currentPageNo+"\"/>/"+page.totalPageCount+"页");
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
			$("pagerContainer").update(str.toString());
	  }
	   
	 function goPage(indexed,start,size){
	   getuser(start);
	}  
	
	</script>
</html>

