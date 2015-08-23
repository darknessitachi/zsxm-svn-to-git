<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
		<script language="JavaScript"src="<s:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>		
		<SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxcommon.js"></SCRIPT>
        <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree.js"></SCRIPT>
        <link type="text/css" href="js/dhtmlxTree/dhtmlxtree.css"rel="stylesheet" >
        <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree_json.js"></SCRIPT>
        <script   type="text/javascript" src="js/dhtmlxTree/dhtmlXTreeExtend.js" ></script>	
	<style>
			  .fxtable{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
			  .fxtable2{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
			  .fxtable2 td.cls {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;}
		      .fxtable td {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;}
		        a.button{background:url(images/button.gif);	display:block;color:#555555;font-weight:bold;height:30px;line-height:29px;margin-bottom:14px;text-decoration:none;width:191px;}
				a:hover.button{color:#0066CC;}
		      .lens{ no-repeat 10px 8px;text-indent:30px;display:block;}
		.fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
	</style>
	</head>

	<body>
	<s:component template="xtitle" theme="app" value='高级查询'></s:component>
	<s:form name="czrcForm" action='rcda!preJszcI.do' method="post" >
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="xmid"></s:hidden>
	<s:hidden name="xh"></s:hidden>
 <!--个人信息 start-->

	<div id="tableContainer" style="height:90%;overflow:auto">
				<table class="fxtable"  cellpadding="0" cellspacing="0">
					<tr>
						<td style="text-align:center;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:25px">行号</td>
						<td style="text-align:center;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:150px">字段名</td>
						<td style="text-align:center;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:40px">条件</td>
						<td style="text-align:center;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">字段值</td>
						<td style="text-align:center;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:40px">逻辑符</td>
					</tr>
					<tbody id='searchbody'>
					</tbody>
					
				</table>
	</div>	
	
	
	
	
	<div class="footer" style="background:#f5f5f5">
		<input class="button3" type="button" value="确   定" onclick="javascript:doTrue();"/>
		<input class="button3" type="button" value="退   出" onclick="closeWindows();"/>
	</div>
	
	
	<div loading='0' id='xtdlb' class="fsg_nr" style="display:none;width:270;height:200;overflow:auto;">
		<div id='xtdlbloadimage'>
			<img src="images/skin0/other/upload.gif">数据载入中.....
		</div>
		<div loading='0' id='xtdlbload' style="width:100%;height:195;background-color:#E7EAF7;overflow:auto;">
			
		</div>
		
		<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
	</div>
	
	</s:form>
	</body>
<script type="text/javascript" src="<c:url value="/js/rc_init.js"/>" ></script>	
	<script type="text/javascript">
		// $('xwincontent').style.height = (getSize().h - 105)+"px"; 
		var reload = 0;
		function closeWindows(){closeWin(self.name);}
		function closeWin(sid){
			if(reload == 1){
				var xx = $('pname').value
				var yy= eval('parent.document.'+xx);
				yy.refresh();
			}
			parent.closeXwin(sid);
		}
		$('tableContainer').style.height = (getSize().h - 70)+"px"; 
		
		var SLEN = 5;//选择循环
		init();
		function init(){
			var str = new StringBuffer();
			for(var i=1;i<=SLEN;i++){
				str.append('<tr>');
				str.append('<td style="background:#efefef;width:30px">'+i+'</td>');
				str.append('<td style="width:200px">'+getFieldSelect(i)+'</td>');
				str.append('<td style="width:60px">'+getWhereSelect(i)+'</td>');
				str.append('<td style="width:200px" id="input'+i+'">&nbsp;</td>');
				str.append('<td style="width:50px">'+getLjSelect(i)+'</td>');
				str.append('</tr>');
			}
			$('searchbody').update(str.toString());
		}
		
		function getFieldSelect(x){
			var str = new StringBuffer();
			str.append('<select style="width:180px" name="filedselect" id="filedselect'+x+'" onchange="changeFiledselect('+x+',this.value)">');
			str.append('<option value="">--请选择--</option>');
			str.append('<option value="xmmc#text">项目名称</option>');
			str.append('<option value="dwmc#text">项目承担单位名称</option>');
			str.append('<option value="xmwh#text">文号</option>');
			str.append('<option value="xmbh#text">立项编号</option>');
			str.append('<option value="strdate#date">起始日期</option>');
			str.append('<option value="enddate#date">结束日期</option>');
			str.append('<option value="lxrq#select">立项年份</option>');
			str.append('<option value="xmjb#select">立项级别</option>');
			str.append('<option value="jhlb#select">计划类别</option>');
			str.append('</select>');
			return str.toString();
		}
		
		function changeFiledselect(x,v){
			if(v.split('#')[1]=='text'){
				$('input'+x).update('<input type="text" name="inputvalue" id="inputvalue'+x+'" style="width:150px" value="">');
			}else if(v.split('#')[1]=='date'){
				$('input'+x).update('<input type="text" class="Wdate" name="inputvalue" id="rq'+x+'" style="text-align:left;width:130" value="" onfocus="new WdatePicker(this,\'%Y-%M-%D\')" MINDATE="1960-01-01" readonly/>');
			}else if(v.split('#')[1]=='select'){
				if(v.split('#')[0] == 'lxrq'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strlxrq.toString()+'</select>');					
				}else if(v.split('#')[0] == 'xmjb'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strxmjb.toString()+'</select>');
				}else if(v.split('#')[0] == 'jhlb'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strjhlb.toString()+'</select>');
				}else{
					$('input'+x).update('&nbsp;');
				}
			}else{
				$('input'+x).update('&nbsp;');
			}
		}
		
		var strlxrq = new StringBuffer();
		var strxmjb = new StringBuffer();
		var strjhlb = new StringBuffer();
		initSelect();
		function initSelect(){
			<s:iterator value="xtdict19">
				strlxrq.append('<option value="<s:property value="dictbh"/>"><s:property value="dictname"/></option>');		
			</s:iterator>
			<s:iterator value="xtdict16">
				strxmjb.append('<option value="<s:property value="dictbh"/>"><s:property value="dictname"/></option>');		
			</s:iterator>
			<s:iterator value="xtdict20">
				strjhlb.append('<option value="<s:property value="dictbh"/>"><s:property value="dictname"/></option>');		
			</s:iterator>
			
		}
		
		
		
		function getWhereSelect(x){
			var str = new StringBuffer();
			str.append('<select style="width:60px" name="whereselect" id="whereselect'+x+'">');
			str.append('<option value="=">等于</option>');
			str.append('<option value=">">大于</option>');
			str.append('<option value=">=">大于等于</option>');
			str.append('<option value="<">小于</option>');
			str.append('<option value="<=">小于等于</option>');
			str.append('<option value="!=">不等于</option>');
			str.append('<option value="like">近似于</option>');
			str.append('</select>');
			return str.toString();
		}
		
		function getLjSelect(x){
			var str = new StringBuffer();
			str.append('<select style="width:50px" name="ljselect" id="ljselect'+x+'">');
			str.append('<option value="and">并且</option>');
			str.append('<option value="or">或</option>');
			str.append('</select>');
			return str.toString();
		}
		
		function doTrue(){
			var RH = '';
			for(var i=1 ;i<=SLEN;i++){
				if($('filedselect'+i).value != ''){
					if($('inputvalue'+i).value != ''){
						var fv = $('filedselect'+i).value.split('#')[0];
						if(fv == 'strdate'){
							RH += " convert(varchar(20),strdate,23) "+$('whereselect'+i).value+" '"+$('inputvalue'+i).value.trim()+"' "+$('ljselect'+i).value;
						}else if(fv == 'enddate'){
							RH += " convert(varchar(20),enddate,23) "+$('whereselect'+i).value+" '"+$('inputvalue'+i).value.trim()+"' "+$('ljselect'+i).value;
						}else{
							if($('whereselect'+i).value == 'like' ){
								RH += " "+fv+" "+$('whereselect'+i).value+" '%"+$('inputvalue'+i).value.trim()+"%' "+$('ljselect'+i).value;
							}else{
								RH += " "+fv+" "+$('whereselect'+i).value+" '"+$('inputvalue'+i).value.trim()+"' "+$('ljselect'+i).value;
							}
						}
					}
				}
			}
			if(RH != ''){
				RH += ' 1=1 '
			}
			getOpener().setHSearchT(RH);
			closeWindows();
		}
		function doTrue_(){
			var RH = ' 1=1 ';
			if($('strdate').value != '' ){
				RH += " and convert(varchar(20),strdate,23) >='"+$('strdate').value+"'";
			}
			if($('enddate').value != '' ){
				RH += " and convert(varchar(20),enddate,23) <='"+$('enddate').value+"'";
			}
			if($('xmmc').value.trim() != '' ){
				RH += " and xmmc like '%"+$('xmmc').value.trim()+"%'";
			}
			
			if($('dwmc').value.trim() != '' ){
				RH += " and dwmc like '%"+$('dwmc').value.trim()+"%'";
			}
			
			if($('xmwh').value.trim() != '' ){
				RH += " and xmwh like '%"+$('xmwh').value.trim()+"%'";
			}
			
			if($('xmbh').value.trim() != '' ){
				RH += " and xmbh like '%"+$('xmbh').value.trim()+"%'";
			}
			
			if($('lxrq').value != '' ){
				RH += " and year(lxrq) = '"+$('lxrq').value+"'";
			}
			if($('xmjb').value != '' ){
				RH += " and xmjb = '"+$('xmjb').value+"'";
			}
			if($('jhlb').value != '' ){
				RH += " and jhlb like '"+$('jhlb').value+"%'";
			}
			
			getOpener().setHSearchT(RH);
			closeWindows();
		}
		
	</script>
</html>

