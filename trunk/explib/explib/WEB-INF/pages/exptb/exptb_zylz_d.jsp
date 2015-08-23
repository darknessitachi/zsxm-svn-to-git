<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
		<link type="text/css" href="styles/csstable.css"rel="stylesheet" />
	 	<script language="JavaScript"src="<s:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>
	 	<script type="text/javascript" src="<c:url value="/js/rc_init.js"/>" ></script>		
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
	<s:hidden name="tbtname" value="EXP_ZYLZ"></s:hidden>
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
		   		<td class="tdc" ><input type="checkbox" name="dmkey" value="zzmc" /></td>
				<th class="spec">论著(论文)名称</th>
				<td class="tdc" ><s:textfield cssStyle="width:130px" name="exptb.zzmc" id="exptb.zzmc"/></td>
				<td class="tdc" ><s:property value="exp.zzmc"/>&nbsp;</td>
			</tr>

			<tr>	
				<td class="tdc" ><input type="checkbox" name="dmkey" value="cbmc" /></td>
				<th class="spec">出版社或刊物名称</th>
				<td class="tdc" ><s:textfield cssStyle="width:130px" name="exptb.cbmc"/></td>
				<td class="tdc" ><s:property value="exp.cbmc"/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdc" ><input type="checkbox" name="dmkey" value="smno" /></td>
				<th class="spec">署名顺序</th>
				<td class="tdc" >
					<s:select cssStyle="width:130px" name="exptb.smno" list="xtdict31" listKey="dictbh" listValue="dictname" />
				</td>
				<td class="tdc" ><s:property value="exp.smno_mc"/>&nbsp;</td>
			</tr>	
			<tr>
				<td class="tdc" ><input type="checkbox" name="dmkey" value="cbrq" /></td>
				<th class="spec">出版（发表）时间</th>
				<td class="tdc" ><input type="text" class="Wdate" name="exptb.cbrq" id="zylz1_rq" style="text-align:left;width:130" value="<s:property value="exptb.cbrq"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1900-01-01" readonly/></td>\
				<td class="tdc" ><s:property value="exp.cbrq"/>&nbsp;</td>
			</tr>	
    	</table>	
	</div>
	
	<div class="footer" style="background:#f5f5f5">
		<input class="button3" type="button" value="确定同步" onclick="javascript:doTB();"/>
		<input class="button3" type="button" value="退   出" onclick="closeWin();"/>
	</div>
	
	</s:form>
	</body>

	<script type="text/javascript">
		$('fxtableContainer').style.height = (getSize().h - 40)+"px"; 
		$('exptb.zzmc').focus();
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
		
	</script>
</html>

