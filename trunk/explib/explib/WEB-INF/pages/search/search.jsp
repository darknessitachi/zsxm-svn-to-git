<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
	 <script language="JavaScript"src="<s:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>
	 <script type="text/javascript" src="<c:url value="/js/rc_init.js"/>" ></script>		
	<style>
			.selectBut2 {
								WIDTH: 129px; BACKGROUND: url(images/skin0/rcda/btn8.jpg) no-repeat;TEXT-ALIGN: left; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 10px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 22px; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer
							}
			.fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }	
	</style>
	</head>

	<body>
	
	<s:form name="czrcForm" action='search.do' method="post" >
	
	<s:hidden name="winid" id="winid"></s:hidden>
	<s:hidden name="swhere"></s:hidden>
 <!--个人信息 start-->
	
	<div class="fxtableContainer" id="fxtableContainer" style="overflow:auto;">
    			
				<table align="center" class="fxtable"  cellpadding="0" cellspacing="0"  id="tab4" style="">
					<tr>
						<td colspan=7 style="background:#efefef">
							条件名称：
							<input type="text" name="smc" id="smc" style="width:200px"/>
							&nbsp;&nbsp;&nbsp;&nbsp;
							条件选择：
							<select name="swh">
							</select>
						</td>
					</tr>
	       			<tr>
						<td class="bt" style="width:30px">行号</td>
						<td class="bt" style="width:30px">括号</td>
						<td class="bt" style="width:180px">字段名</td>
						<td class="bt" style="width:60px">条件</td>
						<td class="bt" style="width:290px">字段值</td>
						<td class="bt" style="width:30px">括号</td>
						<td class="bt" style="width:50px">逻辑符</td>
					</tr>
					
					<tr>
						<td>1</td>
						<td>
							<select name="slgh" id="slgh1">
								<option></option>
								<option value="(">(</option>
								<option value=")">)</option>
							</select>
						</td>
						<td>
							<s:select name="sfield" id="sfield1" list="sfields" cssStyle="width:165" listKey="dm" listValue="mc"  headerKey="" headerValue="--请选择--" onchange="changeSel(1);"/>
						</td>
						<td>
							<select name="stj" id="stj1">
								<option value="=">等于</option>
								<option value="!=">不等于</option>
								<option value=">">大于</option>
								<option value=">=">大于等于</option>
								<option value="<">小于</option>
								<option value="<=">小于等于</option>
								<option value="like">近似</option>
							</select>
						</td>
						<td>
							<span id="xmdis1">
					    		<s:textfield name="svalue" id="svalue1" cssStyle="width:225px"></s:textfield>
					    	</span>
						</td>
						<td>
							<select name="srgh" id="srgh1">
								<option></option>
								<option value="(">(</option>
								<option value=")">)</option>
							</select>
						</td>
						<td>
							<select name="slg" id="slg1">
								<option></option>
								<option value="and">并且</option>
								<option value="or">或者</option>
							</select>
						</td>
					</tr>
					
					<tr>
						<td>2</td>
						<td>
							<select name="slgh" id="slgh2">
								<option></option>
								<option value="(">(</option>
								<option value=")">)</option>
							</select>
						</td>
						<td>
							<s:select name="sfield" id="sfield2" list="sfields" cssStyle="width:165" listKey="dm" listValue="mc"  headerKey="" headerValue="--请选择--"  onchange="changeSel(2);"/>
						</td>
						<td>
							<select name="stj" id="stj2">
								<option value="=">等于</option>
								<option value="!=">不等于</option>
								<option value=">">大于</option>
								<option value=">=">大于等于</option>
								<option value="<">小于</option>
								<option value="<=">小于等于</option>
								<option value="like">近似</option>
							</select>
						</td>
						<td>
							<span id="xmdis2">
					    		<s:textfield name="svalue" id="svalue2" cssStyle="width:225px"></s:textfield>
					    	</span>
						</td>
						<td>
							<select name="srgh" id="srgh2">
								<option></option>
								<option value="(">(</option>
								<option value=")">)</option>
							</select>
						</td>
						<td>
							<select name="slg" id="slg2">
								<option></option>
								<option value="and">并且</option>
								<option value="or">或者</option>
							</select>
						</td>
					</tr>
					
					<tr>
						<td>3</td>
						<td>
							<select name="slgh" id="slgh3">
								<option></option>
								<option value="(">(</option>
								<option value=")">)</option>
							</select>
						</td>
						<td>
							<s:select name="sfield" id="sfield3" list="sfields" cssStyle="width:165" listKey="dm" listValue="mc"  headerKey="" headerValue="--请选择--"  onchange="changeSel(3);"/>
						</td>
						<td>
							<select name="stj" id="stj3">
								<option value="=">等于</option>
								<option value="!=">不等于</option>
								<option value=">">大于</option>
								<option value=">=">大于等于</option>
								<option value="<">小于</option>
								<option value="<=">小于等于</option>
								<option value="like">近似</option>
							</select>
						</td>
						<td>
							<span id="xmdis3">
					    		<s:textfield name="svalue" id="svalue3" cssStyle="width:225px"></s:textfield>
					    	</span>
						</td>
						<td>
							<select name="srgh" id="srgh3">
								<option></option>
								<option value="(">(</option>
								<option value=")">)</option>
							</select>
						</td>
						<td>
							<select name="slg" id="slg3">
								<option></option>
								<option value="and">并且</option>
								<option value="or">或者</option>
							</select>
						</td>
					</tr>
					
					<tr>
						<td>4</td>
						<td>
							<select name="slgh" id="slgh4">
								<option></option>
								<option value="(">(</option>
								<option value=")">)</option>
							</select>
						</td>
						<td>
							<s:select name="sfield" id="sfield4" list="sfields" cssStyle="width:165" listKey="dm" listValue="mc"  headerKey="" headerValue="--请选择--"  onchange="changeSel(4);"/>
						</td>
						<td>
							<select name="stj" id="stj4">
								<option value="=">等于</option>
								<option value="!=">不等于</option>
								<option value=">">大于</option>
								<option value=">=">大于等于</option>
								<option value="<">小于</option>
								<option value="<=">小于等于</option>
								<option value="like">近似</option>
							</select>
						</td>
						<td>
							<span id="xmdis4">
					    		<s:textfield name="svalue" id="svalue4" cssStyle="width:225px"></s:textfield>
					    	</span>
						</td>
						<td>
							<select name="srgh" id="srgh4">
								<option></option>
								<option value="(">(</option>
								<option value=")">)</option>
							</select>
						</td>
						<td>
							<select name="slg" id="slg4">
								<option></option>
								<option value="and">并且</option>
								<option value="or">或者</option>
							</select>
						</td>
					</tr>
					
					<tr>
						<td>5</td>
						<td>
							<select name="slgh" id="slgh5">
								<option></option>
								<option value="(">(</option>
								<option value=")">)</option>
							</select>
						</td>
						<td>
							<s:select name="sfield" id="sfield5" list="sfields" cssStyle="width:165" listKey="dm" listValue="mc"  headerKey="" headerValue="--请选择--"  onchange="changeSel(5);"/>
						</td>
						<td>
							<select name="stj" id="stj5">
								<option value="=">等于</option>
								<option value="!=">不等于</option>
								<option value=">">大于</option>
								<option value=">=">大于等于</option>
								<option value="<">小于</option>
								<option value="<=">小于等于</option>
								<option value="like">近似</option>
							</select>
						</td>
						<td>
							<span id="xmdis5">
					    		<s:textfield name="svalue" id="svalue5" cssStyle="width:225px"></s:textfield>
					    	</span>
						</td>
						<td>
							<select name="srgh" id="srgh5">
								<option></option>
								<option value="(">(</option>
								<option value=")">)</option>
							</select>
						</td>
						<td>
							<select name="slg" id="slg5">
								<option></option>
								<option value="and">并且</option>
								<option value="or">或者</option>
							</select>
						</td>
					</tr>
					
					
				</table>
				
	</div>
	
	<div class="footer" style="background:#f5f5f5">
		<input class="button3" type="button" value="确定查询" onclick="javascript:doQuery(1);"/>
		<input class="button3" type="button" value="保存条件" onclick="javascript:doSave(3);"/>
		<input class="button3" type="button" value="退   出" onclick="closeWin();"/>
	</div>
	
	</s:form>
	</body>
</select>
	<script type="text/javascript">
		$('smc').focus();
		$('fxtableContainer').style.height = (getSize().h - 35)+"px"; 
		
		function doQuery(t){
			
		}
		
		function doSave(type){
			var c = check();
			if(c){
				var ajax = new AppAjax("search!doSave.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}
		}
		
		
		function save_Back(data,type){
			if(data.message.code >0){
				window.parent.refreshmm();
				closeWin();
			}else{
				alert(data.message.text);
			}
		}
		
		function changeSel(t){
			$('xmdis'+t).innerHTML = '<INPUT id="zsxm_button" class="selectBut2"  title="选择" value="点击选择" type=button onclick="O_D(\'zsxm_button\',\'xtdlb\',\'bottom\');loadTree(\''+dm+'\');"/><input type="hidden" name="name"/>';
		}
		
		function check(){
			var iii=0,kkk=0,ttt='';
	        var lgh=0,rgh=0,lg=0;
	        
	    	for(i=0;i<5;i++)
	    	{
	    	   if(document.getElementsByName('sfield')[i].value!='')
	    	   {
	    	   	  if(document.getElementsByName('svalue')[i].value!='')
	    	   	  {
	    	   	  	  ++kkk;
	    	   	  	  if(document.getElementsByName('slgh')[i].value=='(')
	    	   	  	  {
	    	   	  	  		++lgh;
	    	   	  	  }else if(document.getElementsByName('slgh')[i].value==')')
	    	   	  	  {
	    	   	  	  		++rgh;
	    	   	  	  }
	    	   	  	  if(document.getElementsByName('srgh')[i].value=='(')
	    	   	  	  {
	    	   	  	  		++lgh;
	    	   	  	  }else if(document.getElementsByName('srgh')[i].value==')')
	    	   	  	  {
	    	   	  	  		++rgh;
	    	   	  	  }
	    	   	  	  if(document.getElementsByName('slg')[i].value!='')
	    	   	  	  {
	    	   	  	  	  ++lg;
	    	   	  	  }
	    	   	  }
	    	   	  ++iii;
	    	   }
	    	   
	    	}
	    	if(iii==0)
	    	{
				alert('相应的条件选项不允许为空');
	    	}else if(kkk != iii)
	    	{
	    		alert('相应的字段值不能为空！');
	    	}else if(lgh>rgh)
	    	{
	    		alert('缺少右括号！请查看！');
	    	}else if(lgh<rgh)
	    	{
	    		alert('缺少左括号！请查看！');
	    	}else if(iii-lg>1)
	    	{
	    		alert('缺少相应的逻辑符！请查看！');
	    	}else if(iii-lg<1)
	    	{
	    		alert('多了一个逻辑符！请查看！');
	    	}else
	    	{
				return true;
	    	}
	    	return false;
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
			tree.loadJSON("search!getQueryTree.do?lbid="+dm,function(){$('xtdlbloadimage').style.display="none";});
			$('xtdlb').loading = 1;			
		}
	}		
</script>
</html>

