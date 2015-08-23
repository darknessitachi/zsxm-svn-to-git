<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
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
	<s:hidden name="tbtname" value="EXP_KTXM"></s:hidden>
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
    			<td class="tdc" ><input type="checkbox" name="dmkey" value="zzxmlb" /></td>
		      	<th class="spec">获资助项目类别</th>
				<td class="tdc" >
					<s:select name="exptb.zzxmlb" list="xtdict28" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--"/>
				</td>					
				<td class="tdc" ><s:property value="exp.zzxmlb_mc"/>&nbsp;</td>
			</tr>
      		<tr>
      			<td class="tdc" ><input type="checkbox" name="dmkey" value="xmmc" /></td>
				<th class="spec">科研项目名称</th>
				<td class="tdc" ><s:textfield cssStyle="width:130px" name="exptb.xmmc" id="exptb.xmmc"/></td>
				<td class="tdc" ><s:property value="exp.xmmc"/>&nbsp;</td>
			</tr>

			<tr  id="kytr2">	
				<td class="tdc" ><input type="checkbox" name="dmkey" value="kylws" /></td>
				<th class="spec">论文</th>
				<td class="tdc" ><s:textfield cssStyle="width:90px" name="exptb.kylws" onfocus="javascript:this.select();" onblur="COM.isNumChar(this.id)"/>篇</td>
				<td class="tdc" ><s:property value="exp.kylws"/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdc" ><input type="checkbox" name="dmkey" value="kyzzs" /></td>
				<th class="spec">著作</th>
				<td class="tdc" ><s:textfield cssStyle="width:130px" name="exptb.kyzzs" onfocus="javascript:this.select();" onblur="COM.isNumChar(this.id)"/>部</td>
				<td class="tdc" ><s:property value="exp.kyzzs"/>&nbsp;</td>
			</tr>
			<tr  id="kytr3" >	
				<td class="tdc" ><input type="checkbox" name="dmkey" value="kycz" /></td>
				<th class="spec">产值</th>
				<td class="tdc" ><s:textfield cssStyle="width:130px" name="exptb.kycz" onfocus="javascript:this.select();" onblur="COM.isNum(this.id)"/>万元</td>
				<td class="tdc" ><s:property value="exp.kycz"/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdc" ><input type="checkbox" name="dmkey" value="kyzls" /></td>
				<th class="spec">专利</th>
				<td class="tdc" ><s:textfield cssStyle="width:130px" name="exptb.kyzls" onfocus="javascript:this.select();" onblur="COM.isNumChar(this.id)"/>件</td>
				<td class="tdc" ><s:property value="exp.kyzls"/>&nbsp;</td>
			</tr>
			<tr  id="kytr4">
				<td class="tdc" ><input type="checkbox" name="dmkey" value="kyqtcg" /></td>	
				<th class="spec">其他成果</th>
				<td class="tdc" ><s:textfield cssStyle="width:130px" name="exptb.kyqtcg"/></td>
				<td class="tdc" ><s:property value="exp.kyqtcg"/>&nbsp;</td>
			</tr>

			<tr>
				<td class="tdc" ><input type="checkbox" name="dmkey" value="xmly" /></td>	
				<th class="spec">项目来源</th>
				<td class="tdc" ><s:select name="exptb.xmly" list="xtdict26" cssStyle="width:130" listKey="dictbh" listValue="dictname" /></td>
				<td class="tdc" ><s:property value="exp.xmly_mc"/>&nbsp;</td>
			</tr>
			<tr>						
				<td class="tdc" ><input type="checkbox" name="dmkey" value="brzy" /></td>
				<th class="spec">本人作用</th>
				<td class="tdc" ><s:select name="exptb.brzy" list="xtdict24" cssStyle="width:130" listKey="dictbh" listValue="dictname" /></td>
				<td class="tdc" ><s:property value="exp.brzy_mc"/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdc" ><input type="checkbox" name="dmkey" value="wcqk" /></td>
				<th class="spec">完成情况</th>
				<td class="tdc" ><s:select name="exptb.wcqk" list="xtdict19" cssStyle="width:130" listKey="dictbh" listValue="dictname" /></td>
				<td class="tdc" ><s:property value="exp.wcqk_mc"/>&nbsp;</td>
			</tr>
			<tr>
				<td class="tdc" ><input type="checkbox" name="dmkey" value="zzje" /></td>	
				<th class="spec">获资助金额</th>
				<td class="tdc" ><s:textfield cssStyle="width:130px" name="exptb.zzje" onfocus="javascript:this.select();" onblur="COM.isNum(this.id)"/></td>
				<td class="tdc" ><s:property value="exp.zzje"/>&nbsp;</td>
			</tr>
			<tr>	
				<td class="tdc" ><input type="checkbox" name="dmkey" value="wcrq" /></td>	
				<th class="spec">项目起始时间</th>
				<td class="tdc" >
					<input type="text" class="Wdate" name="exptb.wcrq" id="exptb.wcrq" style="text-align:left;width:130" value="<s:property value="exptb.wcrq"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1900-01-01" readonly/>
				</td>
				<td class="tdc" ><s:property value="exp.wcrq"/>&nbsp;</td>
			<tr>	
				<td class="tdc" ><input type="checkbox" name="dmkey" value="wcendrq" /></td>	
				<th class="spec">项目结束时间</th>
				<td class="tdc" >
					<input type="text" class="Wdate" name="exptb.wcendrq" id="exptb.wcendrq" style="text-align:left;width:130" value="<s:property value="exptb.wcendrq"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1900-01-01" readonly/>
				</td>
				<td class="tdc" ><s:property value="exp.wcendrq"/>&nbsp;</td>
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
		$('exptb.xmmc').focus();
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

