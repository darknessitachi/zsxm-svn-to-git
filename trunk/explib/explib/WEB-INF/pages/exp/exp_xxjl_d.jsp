<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
		 <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxcommon.js"></SCRIPT>
       <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree.js"></SCRIPT>
       <link type="text/css" href="js/dhtmlxTree/dhtmlxtree.css"rel="stylesheet" >
        <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree_json.js"></SCRIPT>
        <script   type="text/javascript" src="js/dhtmlxTree/dhtmlXTreeExtend.js" ></script>
        <script language="JavaScript"src="<s:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>		
	<style>
		.fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
	</style>
	</head>

	<body>

	<s:form name="czrcForm" action='exp!preXxjlI.do' method="post" >
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="rcid"></s:hidden>
	<s:hidden name="xh"></s:hidden>
	<s:hidden name="winid" id="winid"></s:hidden>
 <!--个人信息 start-->

	
	<div class="fxtableContainer" id="fxtableContainer" style="overflow:auto;">
				
	    <table align="center" class="fxtable" cellpadding="0" cellspacing="0" id="tab3" >
      			<tr>
				<td class="bt">开始日期</td>
				<td ><input type="text" class="Wdate" name="exp.brq" id="exp.brq" style="text-align:left;width:130" value="<s:property value="exp.brq"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1900-01-01" readonly/></td>
				<td class="bt">结束日期</td>
				<td ><input type="text" class="Wdate" name="exp.erq" id="exp.erq" style="text-align:left;width:130" value="<s:property value="exp.brq"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1900-01-01" readonly/></td>
			</tr>
			
			<tr>	
				<td class="bt">院校<font color=red>*</font></td>
				<td  colspan=3><s:textfield cssStyle="width:300" name="exp.yx" id="exp.yx"/></td>
			</tr>
			<tr>	
				<td class="bt">所学专业</td>
				<td colspan=3>
				<INPUT id="xxjl1_button" class=selectBut2  title=所学专业 value="<s:property value="exp.sxzy_mc"/>" type=button onclick="O_D('xxjl1_button','xtdlb13','bottom');set13WithTreeID('xxjl1');loadRcTree(13,'xxjl1');">
				<s:hidden id="xxjl1" name="exp.sxzy"></s:hidden>
				</td>
				
			</tr>
			<tr>	
				<td class="bt">学历</td>
				<td ><s:select name="exp.xl" list="xtdict2" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" /></td>
				<td class="bt">学位</td>
				<td ><s:select name="exp.xw" list="xtdict3" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" /></td>
			</tr>	
			<tr>	
				<td class="bt">毕(结、肄)业</td>
				<td  colspan=3><s:textfield cssStyle="width:300"  name="exp.byjy"/></td>
			</tr>					
		</table>

	</div>
	
	<div class="footer" style="background:#f5f5f5">
		<s:if test="opttype==3">
			<input class="button3" type="button" value="保   存" onclick="javascript:doSave(3);"/>
		</s:if>
		<s:else>
			<!-- <input class="button3" type="button" value="保   存" onclick="javascript:doSave(1);"/> -->
			<input class="button3" type="button" value="保   存" onclick="javascript:doSave(2);"/>
		</s:else>
		<input class="button3" type="button" value="退   出" onclick="closeWin();"/>
	</div>
	
	<div loading='0' id='xtdlb13' class="fsg_nr" style="display:none;width:300;height:130;overflow:auto">
		<div id="xtdlb13load"><img src="images/skin0/other/upload.gif">数据载入中.....</div>
		<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
	</div>
	</s:form>
	</body>

	<script type="text/javascript">
		$('fxtableContainer').style.height = (getSize().h - 35)+"px"; 
		$('exp.yx').focus();
		var reload = 0;
		var select13id = '';
		function set13WithTreeID(id){
			select13id = id;
		}
		 
		var tree;
		function loadRcTree(lbid,meid){
			if($("xtdlb13").loading != 1){
				tree=new dhtmlXTreeObject("xtdlb13","100%","100%",0);
				tree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
				tree.setOnClickHandler(function(id){setVByTree(id);});
				tree.enableSmartXMLParsing(true);
				tree.setDataMode("json");
				tree.enableCheckBoxes(false);
				tree.loadJSON("exp!loadrcTree.do?lbid="+lbid,function(){$('xtdlb13load').style.display="none";});
				$('xtdlb13').loading = 1;			
			}
		}
		
		//设置对应 setByTree 的 TREE 的值 
		function setVByTree(id){
			if(tree.hasChildren(id)==0){
				setV(tree.getItemText(id),id,select13id,'xtdlb13');
			}
		}
		
		function setV(n,v,id,noneid){
			$(id).value = v;
			$(id+'_button').value=n;
			noneDiv(noneid);
		}
		
		function noneDiv(id){
			$(id).style.display='none';
		}
		
		
		function doSave(type){
			if($('exp.brq').value>$('exp.erq').value){
				alert('开始日期不能小于结束日期！');
				return false;
			}
			if($('exp.yx').value.trim()==''){
				alert('所在院校不能为空！');
				return false;
			}
			if(type ==1){
				var ajax = new AppAjax("exp!doXxjlI.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}else if(type == 2){
				var ajax = new AppAjax("exp!doXxjlI.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}else if(type == 3){
				var ajax = new AppAjax("exp!doXxjlU.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}
		}
		
		function save_Back(data,type){
			if(data.message.code >0){
				if(type == 1){//保存继续
					reload = 1;
					document.all.czrcForm.reset();
					$('xxjl1_button').value="";
				}else if(type==2){//保存退出
					reload = 1;
					window.parent.refreshmm();
					closeWin();
				}else if(type==3){
					reload = 1;
					window.parent.refreshmm();
					closeWin();
				}
			}else{
				alert(data.message.text);
			}
		}		
	</script>
</html>

