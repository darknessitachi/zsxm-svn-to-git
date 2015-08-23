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
		
		var SLEN = 8;//选择循环
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
			str.append('<option value="dwdm#text">机构代码</option>');
			str.append('<option value="dwmc#text">单位名称</option>');
			str.append('<option value="xm_mc#text">招资项目</option>');
			str.append('<option value="dabh#text">档案编号</option>');
			str.append('<option value="isljx#select">领军型人才</option>');
			str.append('<option value="isgqbl#select">股权比例</option>');
			str.append('<option value="bgdd1#select">办公地点(楼)</option>');
			str.append('<option value="bgdd2#select">办公地点(座)</option>');
			str.append('<option value="bgdd3#select">办公地点(层)</option>');
			str.append('<option value="bgdd4#text">办公地点(房间)</option>');
			str.append('<option value="dwlx#select">单位类型</option>');
			str.append('<option value="dwzt#select">单位状态</option>');
			str.append('<option value="nwz#select">内外资</option>');
			str.append('<option value="frdb#text">法人代表</option>');
			str.append('<option value="clrq#date">成立日期</option>');
			str.append('<option value="dwgzr#select">跟踪人</option>');
			str.append('<option value="rzyqsj#select">入驻时间</option>');
			str.append('<option value="zczb#select">注册资本</option>');
			str.append('<option value="jzmj#select">建筑面积</option>');
			str.append('<option value="snsssrs#select">上年销售收入</option>');
			str.append('<option value="sssrzzs#select">销售收入增长率</option>');
			str.append('<option value="gxjsqy#select">高新技术企业</option>');
			str.append('<option value="snjnss#select">上年缴纳税收</option>');
			str.append('<option value="jnsszc#select">缴纳税收增长率</option>');
			str.append('<option value="sscpc#select">省双创批次</option>');
			str.append('<option value="sndygs#select">上年底员工数</option>');
			str.append('<option value="ygszzl#select">员工增长率</option>');
			str.append('<option value="tdpc#select">市双创批次</option>');
			str.append('<option value="rjqy#select">软件企业</option>');
			str.append('<option value="dmqy#select">动漫企业</option>');
			str.append('<option value="lxsqy#select">留学生企业</option>');
			str.append('<option value="gjcxkjyq#select">国家创新型科技园区</option>');
			str.append('<option value="gjqdxxcy#select">国家七大新兴产业</option>');
			str.append('<option value="xdfwy#select">现代服务业</option>');
			str.append('<option value="fwwb#select">服务外包</option>');
			str.append('<option value="lhrh#select">两化融合</option>');
			str.append('<option value="cmmi#select">CMMI认证</option>');
			str.append('<option value="dwsb#select">商标</option>');
			str.append('<option value="dwzycp#text">主营产品</option>');
			str.append('<option value="ssctd#select">省双创团队</option>');
			str.append('<option value="sssctd#select">市双创团队</option>');
			str.append('<option value="njdw#select">年检单位</option>');
			str.append('<optgroup label="承担纵向项目情况"></optgroup> ');
			str.append('<option value="cdlxrq#select">立项年份</option>');
			str.append('<option value="cdxmjb#select">立项级别</option>');
			str.append('<option value="cdjhlb#select">计划类别</option>');
			str.append('<optgroup label="园区服务记录"></optgroup> ');
			str.append('<option value="yqrq#date">日期</option>');
			str.append('<option value="yqfwr#select">服务单位人</option>');
			str.append('</select>');
			return str.toString();
		}
		
		function changeFiledselect(x,v){
			if(v.split('#')[1]=='text'){
				$('input'+x).update('<input type="text" name="inputvalue" id="inputvalue'+x+'" style="width:150px" value="">');
			}else if(v.split('#')[1]=='date'){
				$('input'+x).update('<input type="text" class="Wdate" name="inputvalue" id="inputvalue'+x+'" style="text-align:left;width:130" value="" onfocus="new WdatePicker(this,\'%Y-%M-%D\')" MINDATE="1960-01-01" readonly/>');
			}else if(v.split('#')[1]=='select'){
				var v0 = v.split('#')[0];
				if(v0 == 'isljx' || v0 == 'isgqbl' ){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+stris1.toString()+'</select>');					
				}else if(v0 == 'bgdd1'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strbgdd1.toString()+'</select>');
				}else if(v0 == 'bgdd2'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strbgdd2.toString()+'</select>');
				}else if(v0 == 'bgdd3'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strbgdd3.toString()+'</select>');
				}else if(v0 == 'dwlx'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strdwlx.toString()+'</select>');
				}else if(v0 == 'dwzt'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strdwzt.toString()+'</select>');
				}else if(v0 == 'nwz'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strnwz.toString()+'</select>');
				}else if(v0 == 'dwgzr'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strdwgzr.toString()+'</select>');
				}else if(v0 == 'rzyqsj'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strrzyqsj.toString()+'</select>');
				}else if(v0 == 'zczb'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strzczb.toString()+'</select>');
				}else if(v0 == 'jzmj'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strjzmj.toString()+'</select>');
				}else if(v0 == 'snsssrs'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strsnsssrs.toString()+'</select>');
				}else if(v0 == 'sssrzzs'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strsssrzzs.toString()+'</select>');
				}else if(v0 == 'gxjsqy'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strgxjsqy.toString()+'</select>');
				}else if(v0 == 'snjnss'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strsnjnss.toString()+'</select>');
				}else if(v0 == 'jnsszc'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strjnsszc.toString()+'</select>');
				}else if(v0 == 'sscpc'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strsscpc.toString()+'</select>');
				}else if(v0 == 'sndygs'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strsndygs.toString()+'</select>');
				}else if(v0 == 'ygszzl'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strygszzl.toString()+'</select>');
				}else if(v0 == 'tdpc'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strtdpc.toString()+'</select>');
				}else if(v0 == 'rjqy'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strrjqy.toString()+'</select>');
				}else if(v0 == 'dmqy'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strdmqy.toString()+'</select>');
				}else if(v0 == 'lxsqy' ||v0 == 'ssctd'||v0 == 'sssctd'||v0=='njdw'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+stris2.toString()+'</select>');
				}else if(v0 == 'gjcxkjyq'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strgjcxkjyq.toString()+'</select>');
				}else if(v0 == 'gjqdxxcy'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strgjqdxxcy.toString()+'</select>');
				}else if(v0 == 'xdfwy'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strxdfwy.toString()+'</select>');
				}else if(v0 == 'fwwb'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strfwwb.toString()+'</select>');
				}else if(v0 == 'lhrh'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strlhrh.toString()+'</select>');
				}else if(v0 == 'cmmi'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strcmmi.toString()+'</select>');
				}else if(v0 == 'dwsb'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strdwsb.toString()+'</select>');
				}else if(v0 == 'cdlxrq'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strcdlxrq.toString()+'</select>');
				}else if(v0 == 'cdxmjb'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strcdxmjb.toString()+'</select>');
				}else if(v0 == 'cdjhlb'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+strcdjhlb.toString()+'</select>');
				}else if(v0 == 'yqfwr'){
					$('input'+x).update('<select type="select" name="inputvalue" id="inputvalue'+x+'" style="width:150px">'+stryqfwr.toString()+'</select>');
				}else{
					$('input'+x).update('&nbsp;');
				}
			}else{
				$('input'+x).update('&nbsp;');
			}
		}
		
		var stris1 = new StringBuffer();
		stris1.append('<option value=1>有</option>');
		stris1.append('<option value=0>无</option>');
		
		var stris2 = new StringBuffer();
		stris2.append('<option value=1>是</option>');
		stris2.append('<option value=0>否</option>');
		
		var strbgdd1 = new StringBuffer();
		var strbgdd2 = new StringBuffer();
		var strbgdd3 = new StringBuffer();
		
		var strdwlx = new StringBuffer();
		var strdwzt = new StringBuffer();
		var strnwz = new StringBuffer();
		var strdwgzr = new StringBuffer();
		var strzczb = new StringBuffer();
		var strjzmj = new StringBuffer();
		var strsnsssrs = new StringBuffer();
		var strsssrzzs = new StringBuffer();
		var strgxjsqy = new StringBuffer();
		var strsnjnss = new StringBuffer();
		var strsscpc = new StringBuffer();
		var strsndygs = new StringBuffer();
		var strygszzl = new StringBuffer();
		var strtdpc = new StringBuffer();
		var strrjqy = new StringBuffer();
		var strdmqy = new StringBuffer();
		var strgjcxkjyq = new StringBuffer();
		var strgjqdxxcy = new StringBuffer();
		var strxdfwy = new StringBuffer();
		var strfwwb = new StringBuffer();
		var strlhrh = new StringBuffer();
		var strcmmi = new StringBuffer();
		var strdwsb = new StringBuffer();
		var strcdlxrq = new StringBuffer();
		var strcdxmjb = new StringBuffer();
		var strcdjhlb = new StringBuffer();
		var stryqfwr = new StringBuffer();
		var strjnsszc = new StringBuffer();
		var strrzyqsj = new StringBuffer();
		initSelect();
		function initSelect(){
			<s:iterator value="xtdict6">
				strbgdd1.append('<option value="<s:property value="dictbh"/>"><s:property value="dictname"/></option>');		
			</s:iterator>
			<s:iterator value="xtdict7">
				strbgdd2.append('<option value="<s:property value="dictbh"/>"><s:property value="dictname"/></option>');		
			</s:iterator>
			<s:iterator value="xtdict8">
				strbgdd3.append('<option value="<s:property value="dictbh"/>"><s:property value="dictname"/></option>');		
			</s:iterator>
			
			<s:iterator value="xtdict9">
				strdwlx.append('<option value="<s:property value="dictbh"/>"><s:property value="dictname"/></option>');		
			</s:iterator>
			<s:iterator value="xtdict4">
				strdwzt.append('<option value="<s:property value="dictbh"/>"><s:property value="dictname"/></option>');		
			</s:iterator>
			<s:iterator value="xtdict5">
				strnwz.append('<option value="<s:property value="dictbh"/>"><s:property value="dictname"/></option>');		
			</s:iterator>
			<s:iterator value="xtusers">
				strdwgzr.append('<option value="<s:property value="userid"/>"><s:property value="cnname"/></option>');		
			</s:iterator>
			<s:iterator value="zczbs">
				strzczb.append('<option value="<s:property value="dm"/>"><s:property value="mc"/></option>');		
			</s:iterator>
			<s:iterator value="jzmzs">
				strjzmj.append('<option value="<s:property value="dm"/>"><s:property value="mc"/></option>');		
			</s:iterator>
			<s:iterator value="snsssrs">
				strsnsssrs.append('<option value="<s:property value="dm"/>"><s:property value="mc"/></option>');		
			</s:iterator>
			<s:iterator value="sssrzzs">
				strsssrzzs.append('<option value="<s:property value="dm"/>"><s:property value="mc"/></option>');		
			</s:iterator>
			<s:iterator value="xtdict33">
				strrzyqsj.append('<option value="<s:property value="dictbh"/>"><s:property value="dictname"/></option>');		
			</s:iterator>
			<s:iterator value="xtdict34">
				strgxjsqy.append('<option value="<s:property value="dictbh"/>"><s:property value="dictname"/></option>');		
			</s:iterator>
			<s:iterator value="snjnss">
				strsnjnss.append('<option value="<s:property value="dm"/>"><s:property value="mc"/></option>');		
			</s:iterator>
			<s:iterator value="jnsszc">
				strjnsszc.append('<option value="<s:property value="dn"/>"><s:property value="mc"/></option>');		
			</s:iterator>
			<s:iterator value="xtdict45">
				strsscpc.append('<option value="<s:property value="dictbh"/>"><s:property value="dictname"/></option>');		
			</s:iterator>
			<s:iterator value="sndygs">
				strsndygs.append('<option value="<s:property value="dm"/>"><s:property value="mc"/></option>');		
			</s:iterator>strygszzl
			<s:iterator value="ygszzl">
				strygszzl.append('<option value="<s:property value="dm"/>"><s:property value="mc"/></option>');		
			</s:iterator>
			<s:iterator value="xtdict30">
				strtdpc.append('<option value="<s:property value="dictbh"/>"><s:property value="dictname"/></option>');		
			</s:iterator>
			<s:iterator value="xtdict35">
				strrjqy.append('<option value="<s:property value="dictbh"/>"><s:property value="dictname"/></option>');		
			</s:iterator>
			<s:iterator value="xtdict36">
				strdmqy.append('<option value="<s:property value="dictbh"/>"><s:property value="dictname"/></option>');		
			</s:iterator>
			<s:iterator value="xtdict37">
				strgjcxkjyq.append('<option value="<s:property value="dictbh"/>"><s:property value="dictname"/></option>');		
			</s:iterator>
			<s:iterator value="xtdict38">
				strgjqdxxcy.append('<option value="<s:property value="dictbh"/>"><s:property value="dictname"/></option>');		
			</s:iterator>
			<s:iterator value="xtdict39">
				strxdfwy.append('<option value="<s:property value="dictbh"/>"><s:property value="dictname"/></option>');		
			</s:iterator>
			<s:iterator value="xtdict40">
				strfwwb.append('<option value="<s:property value="dictbh"/>"><s:property value="dictname"/></option>');		
			</s:iterator>
			<s:iterator value="xtdict41">
				strlhrh.append('<option value="<s:property value="dictbh"/>"><s:property value="dictname"/></option>');		
			</s:iterator>
			<s:iterator value="xtdict42">
				strcmmi.append('<option value="<s:property value="dictbh"/>"><s:property value="dictname"/></option>');		
			</s:iterator>
			<s:iterator value="xtdict43">
				strdwsb.append('<option value="<s:property value="dictbh"/>"><s:property value="dictname"/></option>');		
			</s:iterator>
			<s:iterator value="xtdict19">
				strcdlxrq.append('<option value="<s:property value="dictbh"/>"><s:property value="dictname"/></option>');		
			</s:iterator>
			<s:iterator value="xtdict16">
				strcdxmjb.append('<option value="<s:property value="dictbh"/>"><s:property value="dictname"/></option>');		
			</s:iterator>
			<s:iterator value="xtdict20">
				strcdjhlb.append('<option value="<s:property value="dictbh"/>"><s:property value="dictname"/></option>');		
			</s:iterator>
			<s:iterator value="xtusers">
				stryqfwr.append('<option value="<s:property value="userid"/>"><s:property value="cnname"/></option>');		
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
						var iv = $('inputvalue'+i).value.trim();
						if(fv=='lxsqy' || fv == 'clrq' || fv=='bgdd1' || fv=='bgdd2' || fv=='bgdd3'){
							RH += " isnull("+fv+",0) "+$('whereselect'+i).value+" '"+$('inputvalue'+i).value.trim()+"' "+$('ljselect'+i).value;
						}else if(fv == 'dabh'){
							RH += " isnull(dabh,'')+isnull(rzyqsj,'') like '"+$('inputvalue'+i).value.trim()+"%' "+$('ljselect'+i).value;
						}else if(fv == 'isljx'){
							if($('inputvalue'+i).value.trim() == '1'){
								RH += " dwid in ( select distinct dwid from DW_RYXX_TR ) "+$('ljselect'+i).value;
							}else{
								RH += " dwid not in ( select distinct dwid from DW_RYXX_TR ) "+$('ljselect'+i).value;
							}
						} else if(fv == 'ssctd' ){
							if($('inputvalue'+i).value.trim() == 1){
								RH += "  isnull(sscpc,'') !='' "+$('ljselect'+i).value;
							}else{
								RH += "  isnull(sscpc,'') = '' "+$('ljselect'+i).value;
							}
						} else if(fv == 'sssctd' ){
							if($('inputvalue'+i).value.trim() == 1){
								RH += "  isnull(tdpc,'') !='' "+$('ljselect'+i).value;
							}else{
								RH += "  isnull(tdpc,'') = '' "+$('ljselect'+i).value;
							}
						} else if(fv == 'isgqbl' ){
							if($('inputvalue'+i).value.trim() == '1'){
								RH += " dwid in ( select distinct dwid from dw_gqbl where isnull(tzlx,'')<>'')  "+$('ljselect'+i).value;
							}else{
								RH += " dwid not in ( select distinct dwid from dw_gqbl where isnull(tzlx,'')<>'' ) "+$('ljselect'+i).value;
							}
						}else if(fv == 'zczb' ){
							var s = iv.split('-');
							var strv = s[0];
							var endv = s[1];
							RH += " (convert(numeric(20,2),isnull(zczb,0)) >= "+strv+"";
							if(endv !='以上'){
								RH += " and convert(numeric(20,2),isnull(zczb,0)) <= "+endv+"";
							}
							RH += ") "+$('ljselect'+i).value;
						}else if(fv == 'jzmj' ){
							var s = iv.split('-');
							var strv = s[0];
							var endv = s[1];
							RH += "  (convert(numeric(20,2),isnull(jzmj,0)) >= "+strv+"";
							if(endv !='以上'){
								RH += "  convert(numeric(20,2),isnull(jzmj,0)) <= "+endv+"";
							}
							RH += ") "+$('ljselect'+i).value;
						}else if(fv == 'snsssrs' ){
							var s = iv.split('-');
							var strv = s[0];
							var endv = s[1];
							RH += "  (convert(numeric(20,2),isnull(SNXSSR,0)) >= "+strv+"";
							if(endv !='以上'){
								RH += " and convert(numeric(20,2),isnull(SNXSSR,0)) <= "+endv+"";
							}
							RH += ") "+$('ljselect'+i).value;
						}else if(fv == 'sssrzzs'  ){
							var s = iv.split('-');
							var strv = s[0];
							var endv = s[1];
							RH += "  (((isnull(convert(numeric(20,2),SNXSSR),0)-isnull(SNXSSR_,0))/isnull(nullif(SNXSSR_,0),1))*100.00>=convert(numeric(20,2),'"+strv+"')";
							if(endv !='以上'){
								RH += " and ((isnull(convert(numeric(20,2),SNXSSR),0)-isnull(SNXSSR_,0))/isnull(nullif(SNXSSR_,0),1))*100.00<=convert(numeric(20,2),'"+endv+"')";
							}
							RH += ") "+$('ljselect'+i).value;
						}else if(fv == 'snjnss' ){
							var s = iv.split('-');
							var strv = s[0];
							var endv = s[1];
							RH += "  (convert(numeric(20,2),isnull(snjnss,0)) >= "+strv+"";
							if(endv !='以上'){
								RH += " and convert(numeric(20,2),isnull(snjnss,0)) <= "+endv+"";
							}
							RH += ") "+$('ljselect'+i).value;
						}else if(fv == 'jnsszc' ){
							var s = iv.split('-');
							var strv = s[0];
							var endv = s[1];
							RH += "  (((isnull(convert(numeric(20,2),snjnss),0)-isnull(snjnss_,0))/isnull(nullif(snjnss_,0),1))*100.00>=convert(numeric(20,2),'"+strv+"')";
							if(endv !='以上'){
								RH += " and ((isnull(convert(numeric(20,2),snjnss),0)-isnull(snjnss_,0))/isnull(nullif(snjnss_,0),1))*100.00<=convert(numeric(20,2),'"+endv+"')";
							}
							RH += ") "+$('ljselect'+i).value;
						}else if(fv == 'sndygs' ){
							var s = iv.split('-');
							var strv = s[0];
							var endv = s[1];
							RH += "  (convert(numeric(20,2),isnull(sndygs,0)) >= "+strv+"";
							if(endv !='以上'){
								RH += " and convert(numeric(20,2),isnull(sndygs,0)) <= "+endv+"";
							}
							RH += ") "+$('ljselect'+i).value;
						}else if(fv == 'ygszzl' ){
							var s = iv.split('-');
							var strv = s[0];
							var endv = s[1];
							RH += "  (((isnull(convert(numeric(20,2),sndygs),0)-isnull(sndygs_,0))/isnull(nullif(sndygs_,0),1))*100.00>=convert(numeric(20,2),'"+strv+"')";
							if(endv !='以上'){
								RH += " and ((isnull(convert(numeric(20,2),sndygs),0)-isnull(sndygs_,0))/isnull(nullif(sndygs_,0),1))*100.00<=convert(numeric(20,2),'"+endv+"')";
							}
							RH += ") "+$('ljselect'+i).value;
						}else if(fv == 'cdlxrq' ){
							RH += "  dwid in ( select dwid from dw_cdxm where YEAR(LXRQ)='"+iv+"') "+$('ljselect'+i).value;
						}else if(fv == 'cdxmjb'){
							RH += "  dwid in ( select dwid from dw_cdxm where xmjb='"+iv+"') "+$('ljselect'+i).value;
						}else if(fv == 'cdjhlb'){
							RH += "  dwid in ( select dwid from dw_cdxm where jhlb like '"+iv+"%') "+$('ljselect'+i).value;
						}else if(fv == 'yqrq' ){
							RH += "  dwid in (select dwid from dw_yqfw where convert(varchar(20),rq,23) >='"+iv+"'";
							
							RH += ")  "+$('ljselect'+i).value;
						}else if(fv == 'yqfwr' ){
							RH += "  dwid in ( select dwid from dw_yqfw where yqfwr = '"+iv+"' "+$('ljselect'+i).value;
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
			if($('dwdm').value != '' ){
				RH += " and dwdm like '%"+$('dwdm').value+"%'";
			}
			if($('dwmc').value != '' ){
				RH += " and dwmc like '%"+$('dwmc').value+"%'";
			}
			if($('xm_mc').value != '' ){
				RH += " and xm_mc like '"+$('xm_mc').value+"'";
			}
			if($('dwlx').value != '' ){
				RH += " and dwlx = '"+$('dwlx').value+"'";
			}
			if($('dwzt').value != '' ){
				RH += " and dwzt = '"+$('dwzt').value+"'";
			}
			if($('nwz').value != '' ){
				RH += " and nwz = '"+$('nwz').value+"'";
			}
			if($('frdb').value != '' ){
				RH += " and frdb like '%"+$('frdb').value+"%'";
			}
			
			if($('dwgzr').value != '' ){
				RH += " and dwgzr = '"+$('dwgzr').value+"'";
			}
			if($('rzyqsj').value != '' ){
				RH += " and rzyqsj = '"+$('rzyqsj').value+"'";
			}
			
			if($('gxjsqy').value != '' ){
				RH += " and gxjsqy = '"+$('gxjsqy').value+"'";
			}
			
			if($('sscpc').value != '' ){
				RH += " and sscpc = '"+$('sscpc').value+"'";
			}
			
			if($('tdpc').value != '' ){
				RH += " and tdpc = '"+$('tdpc').value+"'";
			}
			
			if($('rjqy').value != '' ){
				RH += " and rjqy = '"+$('rjqy').value+"'";
			}
			
			if($('dmqy').value != '' ){
				RH += " and dmqy = '"+$('dmqy').value+"'";
			}
			
			if($('lxsqy').value != '' ){
				RH += " and isnull(lxsqy,0) = '"+$('lxsqy').value+"'";
			}
			if($('clrq').value != '' ){
				RH += " and isnull(clrq,'') like '"+$('clrq').value+"%'";
			}
			
			if($('dabh').value.trim() != ''){
				RH += " and isnull(dabh,'')+isnull(rzyqsj,'') like '"+$('dabh').value+"%'";
			}
			
			if($('isljx').value != ''){
				if($('isljx').value == '1'){
					RH += " and dwid in ( select distinct dwid from DW_RYXX_TR )";
				}else{
					RH += " and dwid not in ( select distinct dwid from DW_RYXX_TR )";
				}
			}
			
			if($('isgqbl').value != ''){
				if($('isgqbl').value == '1'){
					RH += " and dwid in ( select distinct dwid from dw_gqbl where isnull(tzlx,'')<>'')";
				}else{
					RH += " and dwid not in ( select distinct dwid from dw_gqbl where isnull(tzlx,'')<>'' )";
				}
			}
			
			if($('bgdd1').value != '' ){
				RH += " and isnull(bgdd1,'') = '"+$('bgdd1').value+"'";
			}
			
			if($('bgdd2').value != '' ){
				RH += " and isnull(bgdd2,'') = '"+$('bgdd2').value+"'";
			}
			if($('bgdd3').value != '' ){
				RH += " and isnull(bgdd3,'') = '"+$('bgdd3').value+"'";
			}
			
			if($('bgdd4').value.trim() != '' ){
				RH += " and isnull(bgdd4,'') = '"+$('bgdd4').value.trim()+"'";
			}
			
			
			if($('zczb').value != '' ){
				var s = $('zczb').value.split('-');
				var strv = s[0];
				var endv = s[1];
				RH += " and (convert(numeric(20,2),isnull(zczb,0)) >= "+strv+"";
				if(endv !='以上'){
					RH += " and convert(numeric(20,2),isnull(zczb,0)) <= "+endv+"";
				}
				RH += ')';
			}
			
			if($('jzmj').value != '' ){
				var s = $('jzmj').value.split('-');
				var strv = s[0];
				var endv = s[1];
				RH += " and (convert(numeric(20,2),isnull(jzmj,0)) >= "+strv+"";
				if(endv !='以上'){
					RH += " and convert(numeric(20,2),isnull(jzmj,0)) <= "+endv+"";
				}
				RH += ')';
			}
			
			if($('snsssrs').value != '' ){
				var s = $('snsssrs').value.split('-');
				var strv = s[0];
				var endv = s[1];
				RH += " and (convert(numeric(20,2),isnull(SNXSSR,0)) >= "+strv+"";
				if(endv !='以上'){
					RH += " and convert(numeric(20,2),isnull(SNXSSR,0)) <= "+endv+"";
				}
				RH += ')';
			}
			
			if($('sssrzzs').value != '' ){
				var s = $('sssrzzs').value.split('-');
				var strv = s[0];
				var endv = s[1];
				RH += " and (((isnull(convert(numeric(20,2),SNXSSR),0)-isnull(SNXSSR_,0))/isnull(nullif(SNXSSR_,0),1))*100.00>=convert(numeric(20,2),'"+strv+"')";
				if(endv !='以上'){
					RH += " and ((isnull(convert(numeric(20,2),SNXSSR),0)-isnull(SNXSSR_,0))/isnull(nullif(SNXSSR_,0),1))*100.00<=convert(numeric(20,2),'"+endv+"')";
				}
				RH += ')';
			}
			if($('snjnss').value != '' ){
				var s = $('snjnss').value.split('-');
				var strv = s[0];
				var endv = s[1];
				RH += " and (convert(numeric(20,2),isnull(snjnss,0)) >= "+strv+"";
				if(endv !='以上'){
					RH += " and convert(numeric(20,2),isnull(snjnss,0)) <= "+endv+"";
				}
				RH += ')';
			}
			
			
			if($('jnsszc').value != '' ){
				var s = $('jnsszc').value.split('-');
				var strv = s[0];
				var endv = s[1];
				RH += " and (((isnull(convert(numeric(20,2),snjnss),0)-isnull(snjnss_,0))/isnull(nullif(snjnss_,0),1))*100.00>=convert(numeric(20,2),'"+strv+"')";
				if(endv !='以上'){
					RH += " and ((isnull(convert(numeric(20,2),snjnss),0)-isnull(snjnss_,0))/isnull(nullif(snjnss_,0),1))*100.00<=convert(numeric(20,2),'"+endv+"')";
				}
				RH += ')';
			}
			
			if($('sndygs').value != '' ){
				var s = $('sndygs').value.split('-');
				var strv = s[0];
				var endv = s[1];
				RH += " and (convert(numeric(20,2),isnull(sndygs,0)) >= "+strv+"";
				if(endv !='以上'){
					RH += " and convert(numeric(20,2),isnull(sndygs,0)) <= "+endv+"";
				}
				RH += ')';
			}
			
			
			if($('ygszzl').value != '' ){
				var s = $('ygszzl').value.split('-');
				var strv = s[0];
				var endv = s[1];
				RH += " and (((isnull(convert(numeric(20,2),sndygs),0)-isnull(sndygs_,0))/isnull(nullif(sndygs_,0),1))*100.00>=convert(numeric(20,2),'"+strv+"')";
				if(endv !='以上'){
					RH += " and ((isnull(convert(numeric(20,2),sndygs),0)-isnull(sndygs_,0))/isnull(nullif(sndygs_,0),1))*100.00<=convert(numeric(20,2),'"+endv+"')";
				}
				RH += ')';
			}
			
			if($('gjcxkjyq').value != '' ){
				RH += " and gjcxkjyq like '"+$('gjcxkjyq').value+"%'";
			}
			
			
			if($('gjqdxxcy').value != '' ){
				RH += " and gjqdxxcy like '"+$('gjqdxxcy').value+"%'";
			}
			
			if($('xdfwy').value != '' ){
				RH += " and xdfwy like '"+$('xdfwy').value+"%'";
			}
			
			if($('fwwb').value != '' ){
				RH += " and fwwb like '"+$('fwwb').value+"%'";
			}
			
			if($('lhrh').value != '' ){
				RH += " and lhrh = '"+$('lhrh').value+"'";
			}
			
			
			if($('cmmi').value != '' ){
				RH += " and cmmi = '"+$('cmmi').value+"'";
			}
			
			if($('dwsb').value != '' ){
				RH += " and cmmi = '"+$('dwsb').value+"'";
			}
			
			if($('dwzycp').value != '' ){
				RH += " and dwzycp like '%"+$('dwzycp').value+"%'";
			}
			
			if($('ssctd').value != '' ){
				if($('ssctd').value == 1){
					RH += " and isnull(sscpc,'') !='' ";
				}else{
					RH += " and isnull(sscpc,'') = ''";
				}
			}
			
			if($('sssctd').value != '' ){
				if($('sssctd').value == 1){
					RH += " and isnull(tdpc,'') !='' ";
				}else{
					RH += " and isnull(tdpc,'') = ''";
				}
			}
			
			if($('cdlxrq').value != '' ){
				RH += " and dwid in ( select dwid from dw_cdxm where YEAR(LXRQ)='"+$('cdlxrq').value+"')";
			}
			
			if($('cdxmjb').value != '' ){
				RH += " and dwid in ( select dwid from dw_cdxm where xmjb='"+$('cdxmjb').value+"')";
			}
			
			if($('cdjhlb').value != '' ){
				RH += " and dwid in ( select dwid from dw_cdxm where jhlb like '"+$('cdjhlb').value+"%')";
			}
			
			if($('yqrq1').value != '' ){
				RH += " and dwid in (select dwid from dw_yqfw where convert(varchar(20),rq,23) >='"+$('yqrq1').value+"'";
				if($('yqrq2').value != '' ){
					RH += " and convert(varchar(20),rq,23) <='"+$('yqrq2').value+"'";
				}
				RH += ')';
			}
			
			if($('yqfwr').value != '' ){
				RH += " and dwid in ( select dwid from dw_yqfw where yqfwr = '"+$('yqfwr').value+"')";
			}
			
			getOpener().setHSearchT(RH);
			closeWindows();
		}
		
	</script>
</html>

