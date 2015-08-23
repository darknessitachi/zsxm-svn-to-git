
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
	
	<style>
		.fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
	</style>
	</head>

	<body>
	
	<s:form name="czrcForm" action='exp!preJszcI.do' method="post" >
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="rcid"></s:hidden>
	<s:hidden name="xh"></s:hidden>
	<s:hidden name="winid" id="winid"></s:hidden>
 <!--个人信息 start-->

	<div class="fxtableContainer" id="fxtableContainer" style="overflow:auto;">
		<!--<s:hidden name="exp.sxcd" value="000"></s:hidden>-->
		<table align="center"  class="fxtable" cellpadding="0" cellspacing="0" id="tab2">
      			<tr>
				
				<td class="bt">熟悉领域<font color=red>*</font></td>
				<td  colspan=3>
				<INPUT id="lyid1_button" class="selectBut2"  title="选择领域" value="<s:property value="exp.ly_mc"/>" type=button onclick="O_D('lyid1_button','lydiv','bottom');loadTree(14,1,'lyid1');">
				<s:hidden name="exp.ly" id="lyid1"></s:hidden>
				<s:if test="exp.lyother=='999'">
					<s:textfield id="lyid1_text" name="exp.lymc" cssStyle="width:130;"></s:textfield>
				</s:if>
				<s:else>
					<s:textfield id="lyid1_text" name="exp.lymc" cssStyle="width:130;display:none"></s:textfield>
				</s:else>
				 
				</td>
			</tr>
			
			<tr>	
				
				<td class="bt">熟悉程度</td>
				<td  colspan=3>
					<select name="exp.sxcd" id="exp.sxcd" style="width:130">
						<option value="较熟悉">较熟悉</option>
						<option value="熟悉">熟悉</option>
						<option value="一般熟悉">一般熟悉</option>
					</select>
				</td>
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

	<div loading='0' id='lydiv' class="fsg_nr" style="display:none;width:240;height:140;overflow:auto">
		<div id="rclbtreeload"><img src="images/skin0/other/upload.gif">数据载入中.....</div>
		<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
	</div>
	
	</s:form>
	</body>
<script type="text/javascript" src="<c:url value="/js/rc_init.js"/>" ></script>	
	<script type="text/javascript">
		$('fxtableContainer').style.height = (getSize().h - 35)+"px"; 
		$('exp.sxcd').value = '<s:property value="exp.sxcd"/>';
		var reload = 0;
		
		function doSave(type){
			if($('lyid1').value == ''){
				alert('请选择相应的领域！');
				return false;
			}
			if(type ==1){
				var ajax = new AppAjax("exp!doJszcI.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}else if(type == 2){
				var ajax = new AppAjax("exp!doJszcI.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}else if(type == 3){
				var ajax = new AppAjax("exp!doJszcU.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}
		}
		
		function save_Back(data,type){
			if(data.message.code >0){
				if(type == 1){//保存继续
					reload = 1;
					document.all.czrcForm.reset();
					$('lyid1_button').value='';
					$('lyid1_z_button').value='';
					$('lyid1_fx_button').value='';
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
		
		var tree;
		function loadTree(lbid,t,lyid){
			var dm = "";
			
			$('lydiv').innerHTML = "";
			tree=new dhtmlXTreeObject("lydiv","100%","100%",0);
			tree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
			tree.setOnClickHandler(function(id){setVByTree(id,lyid);});
			tree.enableSmartXMLParsing(true);
			tree.setDataMode("json");
			tree.enableCheckBoxes(false);
			tree.loadJSON("exp!loadrcTree.do?lbid="+lbid,function(){});
		}
		
		function setVByTree(id,lyid){
			if(id!='root' && tree.hasChildren(id)==0){
				if(id.substring(id.length-3,id.length)=='999'){
					$(lyid+'_text').style.display='';
				}else{
					$(lyid+'_text').style.display='none';
					$(lyid+'_text').value="";
				}
				setV(tree.getItemText(id),id,lyid,'lydiv');
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
		
		function changeLy(id,v,zid){
			if(zid.substring(zid.length-2,zid.length)!='fx'){
				if(v.substring(v.length-3,v.length)=='999'){
					$(zid).options.length=1;
					$(zid+'_fx').options.length=1;
					$(zid).style.display='none';
					$(zid+'_text').style.display='';
				}else{
					$(zid).style.display='';
					$(zid+'_text').style.display='none';
					if(v==null || v == '' ){
						$(zid).options.length=1;
						$(zid+'_fx').options.length=1;
					}else{
						new AppAjax("exp!getDictTreeWithStep.do?lbid="+id+"&dm="+v,function(data){change_back(data,zid);}).submit();
					}
				}			
			}else{
				if(v.substring(v.length-3,v.length)=='999'){
					$(zid).options.length=1;
					$(zid).style.display='none';
					$(zid+'_text').style.display='';
				}else{
					$(zid).style.display='';
					$(zid+'_text').style.display='none';
					if(v==null || v == '' ){
						$(zid).options.length=1;
					}else{
						new AppAjax("exp!getDictTreeWithStep.do?lbid="+id+"&dm="+v,function(data){change_back(data,zid);}).submit();
					}
				}			
			}

			

		}
		function change_back(data,zid){
			
			if(data != null && data.info.length>0){
				$(zid).options.length=1;
				if(zid.substring(zid.length-2,zid.length)!='fx'){
					//$(zid).options[0] =  new Option('--请选择--','');
					for(var i=1;i<=data.info.length;i++){
						$(zid).options[i] =  new Option(data.info[i].dictname,data.info[i].dictbh);
					}
				}else{
					for(var i=1;i<data.info.length;i++){
						$(zid).options[i] =  new Option(data.info[i].dictname,data.info[i].dictbh);
					}
				}
			}
		}
	</script>
</html>

