<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
        <script language="JavaScript"src="<s:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>		

	</head>

	<body>
	<s:form name="zsxmForm" action='zsxm!doZsxmI.do' method="post" >

	<s:hidden name="rcid"></s:hidden>
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="opttype"></s:hidden>
 <!--个人信息 start-->
 <div style="width:960px">
 
	<div class="title "onclick=""><h2><span class="red fn">*</span> 项目基本信息</h2><div class="img_right" id="s1" ></div></div>
	
	<div class="xwincontent" id="xwincontent" style="overflow:auto;" >
    	<table align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td class="tdleft"><span class="red fn">*</span>项目名称</td>
				<td class="tdright"><s:textfield cssStyle="width:130px" name="rcdarcxx.xsjt"/></td>
				<td class="tdleft">日期</td>
				<td class="tdright"><input type="text" class="Wdate" name="rcdarcxx.birthday" id="date" style="text-align:left;width:130" value="<s:property value="rcdarcxx.birthday"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/></td>
			</tr>
			<tr>
				<td class="tdleft"><span class="red fn">*</span>项目类别</td>
				<td  colspan=3>
					<s:radio name="zsxm.xmlb" list="xmlbs" listKey="dictbh" listValue="dictname" value="'001'"></s:radio>
				</td>
				
			</tr>
			<tr>
				<td class="tdleft"><span class="red fn">*</span>星级</td>
				<td  colspan=3><s:radio name="zsxm.xmxj" list="xmxjs" listKey="dictbh" listValue="dictname" value="'001'"></s:radio></td>
			</tr>
			
			<tr>
				<td class="tdleft">项目概述</td>
				<td colspan=3><s:textarea name="rcdarcxx.info" cols="46" rows="2"></s:textarea></td>
			</tr>
			<tr>
				<td class="tdleft">项目进度概述</td>
				<td colspan=3><s:select name="rcdarcxx.xw" list="xmjds" cssStyle="width:200" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" /></td>
			</tr>
			<tr>
				<td class="tdleft">进展情况</td>
				<td colspan=3><s:textfield cssStyle="width:390px" name="rcdarcxx.hdzz"/></td>
			</tr>
			<tr>
				<td class="tdleft">预计投入</td>
				<td colspan=3>
				<s:textfield cssStyle="width:130px" name="rcdarcxx.hdzz"/>
				<span class="red fn">（万元）</span>
				</td>
			</tr>
			<tr>
				<td colspan=4 >项目推进中可能遇到的问题或困难</td>
				
			</tr>
			<tr>
				<td class="tdleft" style="height:0px; line-height:0px;"></td>
				<td colspan=3>
					<s:textarea name="rcdarcxx.info" cols="46" rows="2"></s:textarea>
				</td>
			</tr>
			<tr>
				<td class="tdleft">对方联系人</td>
				<td class="tdright"><s:textfield cssStyle="width:130px" name="rcdarcxx.hdzz"/></td>
				<td>对方联系人电话</td>
				<td class="tdright"><s:textfield cssStyle="width:130px" name="rcdarcxx.hdzz"/></td>
			</tr>
			<tr>
				<td>对方联系人手机</td>
				<td class="tdright"><s:textfield cssStyle="width:130px" name="rcdarcxx.hdzz"/></td>
				<td class="tdleft">项目跟踪人</td>
				<td class="tdright"><s:textfield cssStyle="width:130px" name="rcdarcxx.hdzz"/></td>
			</tr>
			<tr>
				<td class="tdleft">备注</td>
				<td colspan=3><s:textarea name="rcdarcxx.info" cols="46" rows="4"></s:textarea></td>
			</tr>
		</table>
	</div>
	<div  style="width:960px;text-align:center">
		<input class="btn_submit1" type="button" value="保    存" onclick="javascript:doSave(1);"/>
		<input class="btn_submit1" type="button" value="填写完成" onclick="javascript:doSave(3);"/>
	</div>
	
</div>	
	</s:form>
	</body>

	<script type="text/javascript">
		$('xwincontent').style.height = (getSize().h - 130)+"px"; 
		
		function doSave(type){
			if($('rcdarcxx.rcname').value.trim() == ''){
				alert('姓名不能为空！');
				return false;
			}
			if($('rcdarcxx.zjno').value.trim() == ''){
				alert('证件号码不能为空');
				return false;
			}
			if($('rcdarcxx.zgbm').value==''){
				alert('请选择主管部门');
				return false;
			}
			if($('rcdarcxx.dwdq').value==''){
				alert('请选择单位所在地区');
				return false;
			}
			
			var ajax = new AppAjax("rcda!doSaveRcxx.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
		}
		function save_Back(data,type){
			if(data.message.code >0){
				$('rcid').value=data.message.text;
				parent.reload = 1;
				if(type == 2){//保存下一步
					parent.setRcid(data.message.text,2,true);
				}else if(type==3){//填写完成
					parent.setRcid('',1,true);
				}else if(type==4){
					parent.closeWindows();
				}else{
					alert('保存成功!');
					parent.setRcid(data.message.text,1,false);
				}
			}else{
				alert(data.message.text);
			}
		}
			
	</script>
</html>

