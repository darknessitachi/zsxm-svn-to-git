<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<script src="Framework/Main.js" type="text/javascript"></script>
		<script type="text/javascript" src="<c:url value="/js/comm.js"/>" ></script>
	   <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxcommon.js"></SCRIPT>
       <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree.js"></SCRIPT>
       <link type="text/css" href="js/dhtmlxTree/dhtmlxtree.css"rel="stylesheet" >
       <link type="text/css" href="styles/csstable.css"rel="stylesheet" />
       <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree_json.js"></SCRIPT>
       <script   type="text/javascript" src="js/dhtmlxTree/dhtmlXTreeExtend.js" ></script>			

<style>
.selectBut2 {
				WIDTH: 129px; BACKGROUND: url(images/skin0/rcda/btn8.jpg) no-repeat;TEXT-ALIGN: left; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 10px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 22px; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer
			}
			
th {
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

td {
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
	
	<body style="margin: 0px; margin: 2px;background:white">
		<s:form name="czrcForm">
			<div  class="fxtableContainer" id="queryDiv" style="float:left;width:300px;border: 1px solid #C1DAD7;" >
				<table class="fxtable" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td colspan=4 style="text-align:center;background:#add8e6;font:bold 12px 'lucida Grande',Verdana;">高级查询</td>
					</tr>
				   <tr>
						<td class="bt">姓名</td>
						<td class="left" colspan=3><s:textfield name="query.xm"></s:textfield></td>
					</tr>
					<tr>
						<td class="bt">国籍</td>
						<td class="left" colspan=3>
							<INPUT id="zsxm_button" class="selectBut2"  title="选择" value="点击选择" type=button onclick="O_D('zsxm_button','xtdlb','bottom');loadTree('20');"/>
							<input type="hidden" name="query.nation"/>
						</td>
					</tr>
					
					<tr>
						<td class="bt">学历</td>
						<td class="left" colspan=3><s:select name="query.xl" list="xtdict2" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" /></td>
					</tr>
					<tr>
						<td class="bt">学位</td>
						<td class="left" colspan=3><s:select name="query.xw" list="xtdict3" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" /></td>
					</tr>
					<tr>
						<td class="bt">专业技术职务</td>
						<td class="left" colspan=3><s:select name="query.zc" list="xtdict5" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" /></td>
					</tr>
					<tr>
						<td class="bt">专业技术职务等级</td>
						<td class="left" colspan=3><s:select name="query.zw" list="xtdict23" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" /></td>
					</tr>
					
					<tr>
						<td class="bt">单位性质</td>
						<td class="left" colspan=3><s:select name="query.dwxz" list="xtdict12" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" /></td>
					</tr>
					
					<tr>
						<td class="bt">年龄区间</td>
						<td class="left" colspan=3><input type="text" name="query.strnl" value="" style="width:50px" id="strnl" onblur="COM.isNumChar(this.id)"/>&nbsp&nbsp至&nbsp&nbsp<input type="text" name="query.endnl" value="" style="width:50px" id="endnl" onblur="COM.isNumChar(this.id)"/></td>
					</tr>
					<tr>
						<td class="bt">工作单位</td>
						<td class="left" colspan=3>
							<input type=text name="gzdw"/>
						</td>
					</tr>
					<tr>
						<td class="bt">所在地区</td>
						<td class="left" colspan=3>
							<input type=text name="szdq"/>
						</td>
					</tr>
					
					<tr>
						<td colspan=4 style="text-align:center;background:#add8e6;font:bold 12px 'lucida Grande',Verdana;">
							<input type="button"  class="button" value="查   询" onclick="queryV()"/>
							&nbsp&nbsp
							<input type="button"  class="button" value="统   计" onclick="tjV()"/>
							&nbsp&nbsp
							<input type="button"  class="button" value="二维统计" onclick="dwtjV()"/>
						</td>
					</tr>
				</table>
			</div>
			
			<div id="con_two_2"   style="float:right;border: 1px solid #C1DAD7;"  >
			   		
			<div id="mygrid_container" class="tjtableContainer" style="float:right;overflow:auto"></div>
		   </div>
	</s:form>	   
		   
	<div loading='0' id='xtdlb' class="fsg_nr" style="display:none;width:270;height:200;overflow:auto;">
		<div id='xtdlbloadimage'>
			<img src="images/skin0/other/upload.gif">数据载入中.....
		</div>
		<div loading='0' id='xtdlbload' style="width:100%;height:195;background-color:#E7EAF7;overflow:auto;">
			
		</div>
		
		<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
	</div>				
	</body>
	<script type="text/javascript">
	$('queryDiv').style.height = (getSize().h - 20) + "px";
	$('mygrid_container').style.height = (getSize().h - 25) + "px";
	$('mygrid_container').style.width = (getSize().w - 310) + "px";
	var diag = null;
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
			tree.loadJSON("exp!getQueryTree.do?dm="+dm,function(){$('xtdlbloadimage').style.display="none";});
			$('xtdlb').loading = 1;			
		}
	}
	function setV(id){
		if(id=='root'){
			$('query.nation').value='';
		}else{
			$('query.nation').value=id;
		}
		$('xtdlb').style.display = 'none';
		$('zsxm_button').value = tree.getItemText(id);
	}
	
	function queryV(){
		showwaitform('请稍等，数据检索中......');
		var ajax = new AppAjax("exphz!queryInfo.do",query_back).submitForm("czrcForm");
	}
	function query_back(data){
		var str = new StringBuffer('<table id="tjtable" cellspacing="0" style="width:1400px"><thead>');
		str.append('<tr><th style="width:50px;" >序号</th>');
		str.append('<th>姓名</th>');
		str.append('<th>性别</th>');
		str.append('<th>国籍</th>');
		str.append('<th>籍贯</th>');
		str.append('<th>证件类别</th>');
		str.append('<th>证件号码</th>');
		str.append('<th>专家类别</th>');
		str.append('<th>学历</th>');
		str.append('<th>学位</th>');
		str.append('<th>职称</th>');
		str.append('<th>主管部门</th>');
		str.append('<th>所学专业</th>');
		str.append('<th>从事专业</th>');
		str.append('</tr></thead><tbody>');
	
		if(data && data.info.length > 0){
			var sd = data.info;
		    var len = sd.length;
		    for(var i=0;i< len ;i++){
				str.append('<tr>');
				str.append('<th class="spec">'+(i+1)+'</th>');
				str.append('<td>'+trimN(sd[i].RCNAME)+'</td>');
				str.append('<td>'+trimN(sd[i].SEX_MC)+'</td>');
				str.append('<td>'+trimN(sd[i].NATION_MC)+'</td>');
				str.append('<td>'+trimN(sd[i].JG_MC)+'</td>');
				str.append('<td>'+trimN(sd[i].ZJLB_MC)+'</td>');
				str.append('<td>'+trimN(sd[i].ZJNO)+'</td>');
				str.append('<td>'+trimN(sd[i].RCLB_MC)+'</td>');
				str.append('<td>'+trimN(sd[i].XL_MC)+'</td>');
				str.append('<td>'+trimN(sd[i].XW_MC)+'</td>');
				str.append('<td>'+trimN(sd[i].ZC_MC)+'</td>');
				str.append('<td>'+trimN(sd[i].ZGBM_MC)+'</td>');
				str.append('<td>'+trimN(sd[i].XXZY_MC)+'</td>');
				str.append('<td>'+trimN(sd[i].CSZY_MC)+'</td>');
				str.append('</tr>');
			}
		}else{
			str.append('<tr><td colspan="14"><font color="red">查无数据!</font ></td></tr>');
		}
		str.append('</tbody></table>');
		$("mygrid_container").innerHTML=str.toString();
		hidewaitform();
	}
	
	function tjV(){
		diag = new Dialog("tjinfowindow2");
		diag.Title = "统计";
		diag.Width = 550;
		diag.Height = 230;
		diag.ShowMessageRow=true;
		diag.MessageTitle = "选择统计信息";
		diag.Message = "请选择需要统计信息";
		diag.URL = "exphz!preTj.do?winid=tjinfowindow2";
		diag.show();
	}
	
	function doTj(v){
		$("mygrid_container").innerHTML=v;
	}
	function dwtjV(){
		diag = new Dialog("tjinfowindow");
		diag.Title = "二维统计";
		diag.Width = 370;
		diag.Height = 150;
		diag.ShowMessageRow=true;
		diag.MessageTitle = "选择统计信息";
		diag.Message = "请选择横向、纵向的统计信息";
		diag.URL = "exphz!preDwtj.do?winid=tjinfowindow";
		diag.show();
	}
	
	var dlist = '';
	function doDwtj(x,y){
		showwaitform('请稍等，数据检索中......');
		var ajax = new AppAjax("exphz!doDwtj.do?xf="+x+"&yf="+y,dwtj_back);
		ajax.setAsyn(false);
		ajax.submitForm("czrcForm");
		
		if(dlist != null && dlist != ''){
			var dlen = dlist.length;
			for(var i=0;i<dlen;i++){
				$('t'+dlist[i].bh).innerHTML = dlist[i].cc;
			}
		}
		dlist = '';
	}
	function dwtj_back(data){
		if(data != null && data.xf.length > 0 && data.yf.length > 0){
			var xlen=data.xf.length;
			var ylen = data.yf.length;
			var str = new StringBuffer('<table id="tjtable" cellspacing="0" style="width:100%">');
			str.append('<tr><th  class="spec" style="width:20%">纵向--横向</th>');
			for(var i=0;i<xlen;i++){
				str.append('<th align=center>'+data.xf[i].dictname+'</th>');
			}
			str.append('</tr>');
			
			for(var i=0;i<ylen;i++){
				str.append('<tr><th align=left>'+data.yf[i].dictname+'</th>');
				for(var j=0;j<xlen;j++){
					str.append('<td align=center id="t'+data.xf[j].dictbh+'xy'+data.yf[i].dictbh+'">&nbsp</td>');
				}
				str.append('</tr>');
			}
			str.append('</table>');
			$("mygrid_container").innerHTML=str.toString();
		}
		dlist = data.info;
		hidewaitform();
	}
	
	function trimN(v){
		if(v==null || v=='null'){
			return '';
		}else{
			return v;
		}
	}


	
</script>
</html>

