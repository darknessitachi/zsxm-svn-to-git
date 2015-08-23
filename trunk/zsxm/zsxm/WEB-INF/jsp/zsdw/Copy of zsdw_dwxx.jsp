<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
        <script language="JavaScript"src="<s:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>		
        <script type="text/javascript" src="<c:url value="/js/czrc.js"/>" ></script>
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
	<s:form name="zsdwForm" action='zsdw.do' method="post" >

	<s:hidden name="dwid"></s:hidden>
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="opttype"></s:hidden>
 <!--个人信息 start-->
 <div style="width:100%">
 
	<div class="title "onclick=""><h2><span class="red fn">*</span> 企业基本信息</h2><div class="img_right" id="s1" ></div></div>
	
	<div class="tableContainer" id="tableContainer" style="height:90%">
    	<table class="fxtable"  cellpadding="0">
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px"><span class="red fn">*</span>单位类型</td>
				<td class="tdright"><s:select name="zsdw.dwlx" list="xtdict9" cssStyle="width:150" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择单位类型--" onchange="ctrlDwdm(this.value)"/></td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px"><span class="red fn">*</span>组织机构代码</td>
				<td class="tdright">
				<s:textfield cssStyle="width:90px" name="zsdw.dwdm_8" maxlength="8"></s:textfield>-<s:textfield cssStyle="width:30px" name="zsdw.dwdm_1" maxlength="1"></s:textfield>
				<s:hidden name="zsdw.dwdm"></s:hidden>
				</td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px"><span class="red fn">*</span>单位名称</td>
				<td>
					<s:textfield name="zsdw.dwmc" cssStyle="width:150px"></s:textfield>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">单位状态</td>
				<td class="tdright">
					<s:radio name="zsdw.dwzt" list="xtdict4" listKey="dictbh" listValue="dictname" value="zsdw.dwzt"></s:radio>
				</td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">招资项目</td>
				<td>
					<INPUT id="zzxm_button" class="selectBut2"  title="选择项目" value="<s:property value="qzzxm.xmmc==''?'选择项目':qzzxm.xmmc"/>" type=button onclick="selZzxm()">
					<s:hidden name="qzzxm.xmid"></s:hidden>
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">内/外资</td>
				<td>
					<s:radio name="zsdw.nwz" list="xtdict5" listKey="dictbh" listValue="dictname" value="zsdw.nwz"></s:radio>
				</td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">引进类别</td>
				<td colspan=3>
					<INPUT id="rcdarcxx.nation_button" class="selectBut2"  title="选择引进类别" value="选择引进类别" type=button onclick="O_D('rcdarcxx.nation_button','yjlbd','bottom');">
				</td>
			</tr>	
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">用户登入名</td>
				<td><s:textfield cssStyle="width:130px" name="zsdw.loginname"/></td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">密码</td>
				<td class="tdright"><s:textfield cssStyle="width:130px" name="zsdw.password"/></td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">法人代表</td>
				<td><s:textfield cssStyle="width:130px" name="zsdw.frdb"/></td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">成立日期</td>
				<td class="tdright"><input type="text" class="Wdate" name="zsdw.clrq" id="date" style="text-align:left;width:130" value="<s:property value="zsdw.clrq"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/></td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">单位简介</td>
				<td colspan=3><s:textarea name="zsdw.dwjj" cols="55" rows="4"></s:textarea></td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">办公地点</td>
				<td colspan=3>
				常州科教城
				<s:select name="zsdw.bgdd1" list="xtdict6" cssStyle="width:140" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" />
				<s:select name="zsdw.bgdd2" list="xtdict7" cssStyle="width:50" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--选--" />
				座
				<s:select name="zsdw.bgdd3" list="xtdict8" cssStyle="width:50" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--选--" />
				层
				<s:textfield name="zsdw.bgdd4" cssStyle="width:50"></s:textfield>
				房间
				</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">注册资本</td>
				<td class="tdright"><s:textfield cssStyle="width:130px" name="zsdw.zczb" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"/><span class="red fn">(人民币/万元)</span></td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">建筑面积</td>
				<td class="tdright"><s:textfield cssStyle="width:130px" name="zsdw.jzmj" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"/><span class="red fn">(平方/米)</span></td>
				
			</tr>
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">单位属性</td>
				<td colspan=3>
					<s:iterator value="xtdict10">
						<input type="checkbox" name="dwsx" id='dwsx<s:property value="dictbh"/>' value='<s:property value="dictbh"/>'/><s:property value="dictname"/>
					</s:iterator>
				</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">产业分类</td>
				<td colspan=3>
					<s:iterator value="xtdict11">
						<input type="checkbox" name="cyfl"  id='cyfl<s:property value="dictbh"/>' value='<s:property value="dictbh"/>'/><s:property value="dictname"/>
					</s:iterator>
				</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">技术专业</td>
				<td colspan=3>
					<s:iterator value="xtdict12">
						<s:if test="dictbh=='999'">
							<input type="checkbox" name="jszy"  id='jszy<s:property value="dictbh"/>' value='<s:property value="dictbh"/>'/><s:property value="dictname"/>
							<s:textfield name="jszyothermc"></s:textfield>
						</s:if>
						<s:else>
							<input type="checkbox" name="jszy" id='jszy<s:property value="dictbh"/>' value='<s:property value="dictbh"/>'/><s:property value="dictname"/>
						</s:else>
						
					</s:iterator>
				</td>
			</tr>
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">联系人姓名</td>
				<td class="tdright"><s:textfield cssStyle="width:130px" name="zsdw.lxrxm"/></td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">性别</td>
				<td class="tdright">
					<s:radio name="zsdw.sex" list="xtdict14" listKey="dictbh" listValue="dictname" value="zsdw.sex"></s:radio>
				</td>
			</tr>
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">职称</td>
				<td class="tdright"><s:select name="zsdw.zc" list="xtdict15" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择单位类型--" /></td>
				
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">职务</td>
				<td class="tdright"><s:textfield cssStyle="width:130px" name="zsdw.zw"/></td>	
			</tr>
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">电话</td>
				<td class="tdright"><s:textfield cssStyle="width:130px" name="zsdw.tel"/></td>
				
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">手机</td>
				<td class="tdright"><s:textfield cssStyle="width:130px" name="zsdw.phone"/></td>	
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">所属部门</td>
				<td colspan=3>
					<s:textfield cssStyle="width:350px" name="zsdw.ssbm"/>
				</td>
			</tr>
		</table>
	</div>
	</div>
	<div  style="width:770px;text-align:center">
		<input class="btn_submit1" type="button" value="保    存" onclick="javascript:doSave(1);"/>
		<input class="btn_submit1" type="button" value="保存并下一步" onclick="javascript:doSave(2);"/>
		<s:if test="opttype==3">
				<input class="btn_submit1" type="button" value="保存并退出" onclick="javascript:doSave(4);"/>
			</s:if>
			<s:else>
				<input class="btn_submit1" type="button" value="填写完成" onclick="javascript:doSave(3);"/>
		</s:else>
	</div>
	

	
<div id='yjlbd' class="fsg_nr" style="display:none;width:360;height:210;">	
	<div loading='0' id='yjlbdd' style="width:360;height:190;background-color:#E7EAF7;overflow:auto;">
		<s:iterator value="xtdict13">
			<input type="checkbox" name="yjlb" id='yjlb<s:property value="dictbh"/>' value='<s:property value="dictbh"/>'/><s:property value="dictname"/><br>
		</s:iterator>
	</div>
	
	<div class="footer" style="background:#f5f5f5" style="width:360;height:20;text-align:right">
			<input class="button3" type="button" value="确  定" onclick="selTrue();"/>
		&nbsp&nbsp&nbsp&nbsp
	</div>
	<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
</div>	
	</s:form>
	</body>

	<script type="text/javascript">
		$('tableContainer').style.height = (getSize().h - 80)+"px"; 
		
		<s:iterator value="dwsxs">
			$('dwsx<s:property value="seldm"/>').checked = true;
		</s:iterator>
		
		
		<s:iterator value="cyfls">
			$('cyfl<s:property value="seldm"/>').checked = true;
		</s:iterator>
		
		
		<s:iterator value="jszys">
			$('jszy<s:property value="seldm"/>').checked = true;
			if('<s:property value="seldm"/>'=='999'){
				$('jszyothermc').value = '<s:property value="othermc"/>';
			}
		</s:iterator>
		
		
		<s:iterator value="yjlbs">
			$('yjlb<s:property value="seldm"/>').checked = true;
		</s:iterator>
		
		
		function ctrlDwdm(v){
			if(v=='007'){
				$('zsdw.dwdm_8').value='';
				$('zsdw.dwdm_1').value='';
				$('zsdw.dwdm').value='';
				$('zsdw.dwdm_8').readOnly=true;
				$('zsdw.dwdm_1').readOnly=true;
				$('zsdw.dwdm_8').style.background="#efefef";
				$('zsdw.dwdm_1').style.background="#efefef";
			}else{
				$('zsdw.dwdm_8').readOnly=false;
				$('zsdw.dwdm_1').readOnly=false;
				$('zsdw.dwdm_8').style.background="";
				$('zsdw.dwdm_1').style.background="";
			}
		}
		function doSave(type){
			
			if($('zsdw.dwlx').value.trim() ==''){
				alert('请选择单位类型！');
				return false;
			}
			
			if($('zsdw.dwlx').value != '007'){
				if($('zsdw.dwdm_8').value.trim() == ''){
					alert('请填写组织机构代码的前<8>位!');
					return false;
				}
				if($('zsdw.dwdm_8').value.trim().length !=8 ){
					alert('前面组织机构代码必需<8>位!');
					return false;
				}
				if($('zsdw.dwdm_1').value.trim() == ''){
					alert('请填写组织机构代码的后<1>位!');
					return false;
				}
				if($('zsdw.dwdm_1').value.trim().length !=1 ){
					alert('后面组织机构代码必需<1>位!');
					return false;
				}
				$('zsdw.dwdm').value = $('zsdw.dwdm_8').value.trim()+'-'+$('zsdw.dwdm_1').value.trim();
			}else{
				$('zsdw.dwdm').value = '';
			}
			
			if($('zsdw.dwmc').value.trim() ==''){
				alert('单位名称不能为空！');
				return false;
			}
			
			
			var ajax = new AppAjax("zsdw!doSaveZsdw.do",function(data){save_Back(data,type)}).submitForm("zsdwForm");
		}
		
		function save_Back(data,type){
			if(data.message.code >0){
				$('dwid').value=data.message.text;
				parent.reload = 1;
				if(type == 2){//保存下一步
					parent.setDwid(data.message.text,2,true);
				}else if(type==3){//填写完成
					parent.setDwid('',1,true);
				}else if(type==4){
					parent.closeWindows();
				}else{
					alert('保存成功!');
					parent.setDwid(data.message.text,1,false);
				}
			}else{
				alert(data.message.text);
			}
		}
		
		function selTrue(){
			$('yjlbd').style.display = 'none';
		}
		
		function selZzxm(){
			parent.openWin("zsdw!getZsxmWaitSelect.do?pname="+$('pname').value,"650","400");
		}
		
		
		function setZzxm(id,mc){
			$('qzzxm.xmid').value=id;
			$('zzxm_button').value = mc;
		}
	</script>
</html>

