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
	<s:hidden name="tbtname" value="EXP_XXJL"></s:hidden>
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
    			<td class="tdc" ><input type="checkbox" name="dmkey" value="brq" /></td>
				<th class="spec">开始日期</th>
				<td class="tdc" ><input type="text" class="Wdate" name="exptb.brq" id="exptb.brq" style="text-align:left;width:130" value="<s:property value="exptb.brq"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1900-01-01" readonly/></td>
				<td class="tdc" ><s:property value="exp.brq"/>&nbsp;</td>
			</tr>
			
			<tr>
    			<td class="tdc" ><input type="checkbox" name="dmkey" value="erq" /></td>
				<th class="spec">结束日期</th>
				<td class="tdc" ><input type="text" class="Wdate" name="exptb.erq" id="exptb.erq" style="text-align:left;width:130" value="<s:property value="exptb.erq"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1900-01-01" readonly/></td>
				<td class="tdc" ><s:property value="exp.erq"/>&nbsp;</td>
			</tr>
			
			<tr>
    			<td class="tdc" ><input type="checkbox" name="dmkey" value="yx" /></td>
				<th class="spec">院校</th>
				<td class="tdc" ><s:textfield cssStyle="width:130" name="exptb.yx" id="exptb.yx"/></td>
				<td class="tdc" ><s:property value="exp.yx"/>&nbsp;</td>
			</tr>
			
			<tr>
    			<td class="tdc" ><input type="checkbox" name="dmkey" value="sxzy" /></td>
				<th class="spec">所学专业</th>
				<td class="tdc" >
					<INPUT id="xxjl1_button" class=selectBut2  title=所学专业 value="<s:property value="exptb.sxzy_mc"/>" type=button onclick="O_D('xxjl1_button','xtdlb13','bottom');set13WithTreeID('xxjl1');loadRcTree(13,'xxjl1');">
					<s:hidden id="xxjl1" name="exptb.sxzy"></s:hidden>
				</td>
				<td class="tdc" ><s:property value="exp.sxzy_mc"/>&nbsp;</td>
			</tr>
			
			<tr>
    			<td class="tdc" ><input type="checkbox" name="dmkey" value="xl" /></td>
				<th class="spec">学历</th>
				<td class="tdc" ><s:select name="exptb.xl" list="xtdict2" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" /></td>
				<td class="tdc" ><s:property value="exp.xl_mc"/>&nbsp;</td>
			</tr>
			
			<tr>
    			<td class="tdc" ><input type="checkbox" name="dmkey" value="xw" /></td>
				<th class="spec">学位</th>
				<td class="tdc" ><s:select name="exptb.xw" list="xtdict2" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" /></td>
				<td class="tdc" ><s:property value="exp.xw_mc"/>&nbsp;</td>
			</tr>
			
			<tr>
    			<td class="tdc" ><input type="checkbox" name="dmkey" value="byjy" /></td>
				<th class="spec">毕(结、肄)业</th>
				<td class="tdc" ><s:textfield cssStyle="width:130"  name="exptb.byjy"/></td>
				<td class="tdc" ><s:property value="exp.byjy"/>&nbsp;</td>
			</tr>
			
    	</table>	
	</div>
	
	<div class="footer" style="background:#f5f5f5">
		<input class="button3" type="button" value="确定同步" onclick="javascript:doTB();"/>
		<input class="button3" type="button" value="退   出" onclick="closeWin();"/>
	</div>
	<div loading='0' id='xtdlb13' class="fsg_nr" style="display:none;width:300;height:130;overflow:auto">
		<div id="xtdlb13load"><img src="images/skin0/other/upload.gif">数据载入中.....</div>
		<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
	</div>
	</s:form>
	</body>

	<script type="text/javascript">
		$('fxtableContainer').style.height = (getSize().h - 40)+"px"; 
		$('exptb.yx').focus();
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
		
		
	</script>
</html>

