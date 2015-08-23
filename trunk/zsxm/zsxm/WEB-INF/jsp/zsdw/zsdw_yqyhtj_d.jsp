<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
		<script type="text/javascript" src="<c:url value="/js/czrc.js"/>" ></script>
		<script language="JavaScript"src="<s:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>		
	<style>
		.fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
	</style>
	</head>

	<body>
	<s:component template="xtitle" theme="app" value='园区承诺优惠条件'></s:component>
	<s:form name="czrcForm" action='rcda!preJszcI.do' method="post" >
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="dwid"></s:hidden>
	<s:hidden name="xh"></s:hidden>
 <!--个人信息 start-->

	<div class="xwincontent" id="xwincontent" >

		<table align="center" cellpadding="0" cellspacing="0" id="tab2">
      		<tr>
				<td class="tdleft">项目执行期</td>
				<td colspan=3>
					<input type="text" class="Wdate" name="zsdw.stryhq" id="date1" style="text-align:left;width:130" value="<s:property value="zsdw.stryhq"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>
					-
					<input type="text" class="Wdate" name="zsdw.endyhq" id="date2" style="text-align:left;width:130" value="<s:property value="zsdw.endyhq"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>
				</td>
				
				
			</tr>
			<tr>
				<td class="tdleft">优惠依据</td>
				<td colspan=3>
					<INPUT id="zsdw.nation_button" class="selectBut2"  title="" value='' type=button onclick="O_D('zsdw.nation_button','xtdict49d','bottom');"/>
					<br>
					<span id='xtdict49mc'></span>
				</td>
			</tr>	
			<tr>
				<td class="tdleft">优惠类别</td>
				<td>
					<s:select name="zsdw.yhlb" list="xtdict48" cssStyle="width:130" listKey="dictname" listValue="dictname"  headerKey="" headerValue="--请选择--" />
				</td>
				<td class="tdleft">优惠金额</td>
				<td>
					<s:textfield  name="zsdw.yhje" cssStyle="width:130;"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="tdleft">优惠内容</td>
				<td colspan=3>
					<s:textfield  name="zsdw.yhnr" cssStyle="width:260;"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="tdleft">园区承诺人 </td>
				<td colspan=3>
					<s:select name="zsdw.yhcnr" list="xtusers" cssStyle="width:130" listKey="userid" listValue="cnname" headerKey="" headerValue="--请选择--" />
				</td>
			</tr>	
			<tr>	
				<td class="tdleft">备注</td>
				<td colspan=3><s:textarea name="zsdw.sm" cols="30" rows="3"></s:textarea></td>
			</tr>
			
		</table>	

			
	</div>
	
	<div  id='xtdict49d' class="fsg_nr" style="display:none;width:250;height:150px;text-align:left">
			<div  id='xtdict49dd' style="width:250;height:120px;align:left;background-color:#E7EAF7;overflow:auto;">
				<s:iterator value="xtdict49" id="xtdict49">
					<input type="checkbox" name="xtdict49s" id="xtdict49s<s:property value="dictbh"/>" value='<s:property value="dictbh"/>'/>
					<span id='xtdict49<s:property value="dictbh"/>'><s:property value="dictname"/></span><br>
				</s:iterator>
			</div>
			<div class="footer" style="background:#6495ed" style="width:250;text-align:right">
				<input type="button" value="确  定" onclick="selTrue('xtdict49');"/>
			</div>
			<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
	</div>
	<div class="footer" style="background:#f5f5f5">
		<s:if test="opttype==3">
			<input class="button3" type="button" value="保   存" onclick="javascript:doSave(3);"/>
		</s:if>
		<s:else>
			<input class="button3" type="button" value="保存继续" onclick="javascript:doSave(1);"/>
			<input class="button3" type="button" value="保存退出" onclick="javascript:doSave(2);"/>
		</s:else>
		
		<input class="button3" type="button" value="退   出" onclick="closeWindows();"/>
	</div>


	</s:form>
	</body>
<script type="text/javascript" src="<c:url value="/js/rc_init.js"/>" ></script>	
	<script type="text/javascript">
		// $('xwincontent').style.height = (getSize().h - 105)+"px"; 
		var reload = 0;
		init();
		function closeWindows(){closeWin(self.name);}
		function closeWin(sid){
			if(reload == 1){
				var xx = $('pname').value
				var yy= eval('parent.document.'+xx);
				yy.refresh();
			}
			parent.closeXwin(sid);
		}
		
		function init(){
			<s:iterator value="yqyjs">
				$('xtdict49s<s:property value="dictbh"/>').checked=true;
			</s:iterator>
			
		}
		
		function doSave(type){
			if($('date1').value.trim() == '' || $('date2').value.trim() == ''){
				alert('日期不能为空，请填写！');
				return false;
			}
			
			if(type ==1){
				var ajax = new AppAjax("zsdw!doYqyhtjI.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}else if(type == 2){
				var ajax = new AppAjax("zsdw!doYqyhtjI.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}else if(type == 3){
				var ajax = new AppAjax("zsdw!doYqyhtjU.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}
		}
		
		function save_Back(data,type){
			if(data.message.code >0){
				if(type == 1){//保存继续
					reload = 1;
					document.all.czrcForm.reset();
				}else if(type==2){//保存退出
					reload = 1;
					closeWindows();
				}else if(type==3){
					reload = 1;
					closeWindows();
				}
			}else{
				alert(data.message.text);
			}
		}
		
		function selTrue(v){
			$(v+'d').style.display = 'none';
			var c = RC.checkbox(v+'s');
			var len = c.length;
			$(v+'mc').innerHTML = '';
			for(var y=0;y<len;y++){
				$(v+'mc').innerHTML += $(v+c[y].v).innerHTML +'&nbsp|&nbsp';
			}
		}
		
	</script>
</html>

