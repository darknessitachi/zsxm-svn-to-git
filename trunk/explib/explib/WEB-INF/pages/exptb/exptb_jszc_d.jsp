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
        <link type="text/css" href="styles/csstable.css"rel="stylesheet" />
        <script type="text/javascript" src="<c:url value="/js/comm.js"/>" ></script>
	<style>
		th.thc {
						font: bold 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
						color: #4f6b72;
						border-right: 1px solid #C1DAD7;
						border-bottom: 1px solid #C1DAD7;
						border-top: 1px solid #C1DAD7;
						letter-spacing: 2px;
						text-transform: uppercase;
						text-align: left;
						padding: 6px 6px 6px 12px;
						background: #CAE8EA url(images/bg_header.jpg) no-repeat;
					}
			
			td.tdc {
				border-right: 1px solid #C1DAD7;
				border-bottom: 1px solid #C1DAD7;
				background: #fff;
				font-size:12px;
				padding: 6px 6px 6px 12px;
				color: #4f6b72;
			}		
			.fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
	</style>
	</head>

	<body style="background:white">
	
	<s:form name="czrcForm" action='exp!classinfo.do' method="post" >
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="rcid"></s:hidden>
	<s:hidden name="rcidtb"></s:hidden>
	<s:hidden name="xh"></s:hidden>
	<s:hidden name="xhtb"></s:hidden>
	<s:hidden name="winid" id="winid"></s:hidden>
	<s:hidden name="tbtname" value="EXP_JSZC"></s:hidden>
 <!--个人信息 start-->
	
	<div class="tjtableContainer" id="fxtableContainer" style="overflow:auto;width:100%;heigth:100%">
    	<table id="tjtable" cellspacing="0" cellpadding="0" style="width:100%">
    		<thead>
    		<tr>
    			<th class="thc"  style="width:20px"><input type="checkbox" id="allcheckbox" onclick="COM.checkboxAll('dmkey',this.checked)"></th>
    			<th class="thc"  style="width:130px">字段</th>
    			<th class="thc" >征集专家信息</th>
    			<th class="thc" >现有专家信息</th>
    		</tr>
    		</thead>
    		
    		<tr>
    			<td class="tdc" ><input type="checkbox" name="dmkey" value="ly" /></td>
    			<th class="spec">熟悉领域</th>
    			<td class="tdc" >
					<INPUT id="lyid1_button" class="selectBut2"  title="选择领域" value="<s:property value="exptb.ly_mc"/>" type=button onclick="O_D('lyid1_button','lydiv','bottom');loadTree(16,1,'lyid1');">
					<s:hidden name="exptb.ly" id="lyid1"></s:hidden>
					<s:if test="exp.lyother=='999'">
						<s:textfield id="lyid1_text" name="exptb.lymc" cssStyle="width:130;"></s:textfield>
					</s:if>
					<s:else>
						<s:textfield id="lyid1_text" name="exptb.lymc" cssStyle="width:130;display:none"></s:textfield>
					</s:else>
				</td>
				<td class="tdc" ><s:property value="exp.ly_mc"/>&nbsp;</td>
    		</tr>
    		
    		<tr>
    			<td class="tdc" ><input type="checkbox" name="dmkey" value="sxcd" /></td>
    			<th class="spec">熟悉程度</th>
    			<td class="tdc" >
    				<select name="exptb.sxcd" id="exptb.sxcd" style="width:130">
						<option value="较熟悉">较熟悉</option>
						<option value="熟悉">熟悉</option>
						<option value="一般熟悉">一般熟悉</option>
					</select>
    			</td>
    			<td class="tdc" ><s:property value="exp.sxcd"/>&nbsp;</td>
    		</tr>
    		
    	</table>	
	</div>
	
	<div class="footer" style="background:#f5f5f5">
		<input class="button3" type="button" value="确定同步" onclick="javascript:doTB();"/>
		<input class="button3" type="button" value="退   出" onclick="closeWin();"/>
	</div>
	<div loading='0' id='lydiv' class="fsg_nr" style="display:none;width:240;height:140;overflow:auto">
		<div id="rclbtreeload"><img src="images/skin0/other/upload.gif">数据载入中.....</div>
	</div>
	</s:form>
	</body>

	<script type="text/javascript">
		$('fxtableContainer').style.height = (getSize().h - 40)+"px"; 
		$('exptb.sxcd').value = '<s:property value="exptb.sxcd"/>';
		var reload = 0;
		function doTB(){
			var rcsel = COM.checkbox('dmkey');
				
			if(rcsel.length == 0 ){
				alert('请选择需要同步的信息');
				return false;
			}
			if(window.confirm("您确定要同步信息至专家库中?")){
				var ajax = new AppAjax("exptb!doExpTbMx.do",
				function (data){
					if(data != null && data.message.code > 0){
						alert('同步成功！');
						closeWin();
					}else{
						alert(data.message.text);
					}
				}).submitForm("czrcForm");
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
		
	</script>
</html>

