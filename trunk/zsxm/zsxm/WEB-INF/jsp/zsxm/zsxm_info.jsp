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
</style>
	</head>

	<body>
	
	<s:form name="zsxmForm" action='zsxm!preZsxmU.do' method="post" >

	<s:hidden name="xmid"></s:hidden>
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="opttype"></s:hidden>
 <!--个人信息 start-->
 <div style="width:930px" id="mh">
 
	<div class="title "onclick=""><h2><span class="red fn">*</span> 项目基本信息</h2><div class="img_right" id="s1" ></div></div>
	
	<div id="tableContainer" style="height:90%;overflow:auto">
    	<table class="fxtable" cellspacing="0"  cellpadding="0">
    		<tr>
    			<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:150px"><span class="red fn">*</span>项目编号</td>
				<td style="width:230px" colspan=3>
					<s:property value="zsxm.xmbh"/>&nbsp;
					<s:hidden name="zsxm.xmbh"></s:hidden>
				</td>
				
    		</tr>
    		
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:150px"><span class="red fn">*</span>项目名称</td>
				<td style="width:230px"><s:textfield cssStyle="width:200px" name="zsxm.xmmc"/></td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:150px">日期</td>
				<td class="tdright"><input type="text" class="Wdate" name="zsxm.rq" id="date" style="text-align:left;width:130" value="<s:property value="zsxm.rq"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/></td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:150px"><span class="red fn">*</span>项目类别</td>
				<td  colspan=3>
					<s:radio name="zsxm.xmlb" list="xmlbs" listKey="dictbh" listValue="dictname" value="zsxm.xmlb"></s:radio>
				</td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:150px"><span class="red fn">*</span>星级</td>
				<td  colspan=3><s:radio name="zsxm.xmxj" list="xmxjs" listKey="dictbh" listValue="dictname" value="zsxm.xmxj"></s:radio></td>
			</tr>
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:150px">项目概述</td>
				<td colspan=3><s:textarea name="zsxm.xmgs" cols="60" rows="3"></s:textarea></td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:150px"><span class="red fn">*</span>项目进度概述</td>
				<td colspan=3><s:select name="zsxm.xmjdgs" list="xmjds" cssStyle="width:200" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" /></td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:150px">进展情况</td>
				<td colspan=3><s:textarea name="zsxm.xmjzqk" cols="60" rows="3"></s:textarea></td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:150px">预计投入</td>
				<td colspan=3>
				<s:textfield cssStyle="width:130px" name="zsxm.yjtr" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"/>
				<span class="red fn">（人民币/万元）</span>
				</td>
			</tr>
			<tr>
				<td colspan=4 style="text-align:left;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;">项目推进中可能遇到的问题或困难</td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:150px" style="height:0px; line-height:0px;"></td>
				<td colspan=3>
					<s:textarea name="zsxm.xmwt" cols="60" rows="3"></s:textarea>
				</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:150px">对方联系人</td>
				<td class="tdright"><s:textfield cssStyle="width:130px" name="zsxm.dflxr"/></td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:150px">对方联系人电话</td>
				<td class="tdright"><s:textfield cssStyle="width:130px" name="zsxm.dflxrdh"/></td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:150px">对方联系人手机</td>
				<td class="tdright"><s:textfield cssStyle="width:130px" name="zsxm.dflxrsj"/></td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:150px">项目跟踪人</td>
				<td class="tdright">
					<s:select name="zsxm.xmgzr"  list="xtusers" cssStyle="width:150" listKey="userid" listValue="cnname"  headerKey="" headerValue="--请选择--"/>
					<!-- 
					<s:if test="xtuser.roledm=='000'">
						<s:select name="zsxm.xmgzr"  list="xtusers" cssStyle="width:150" listKey="userid" listValue="cnname"  headerKey="" headerValue="--请选择--"/>
					</s:if>
					<s:else>
						<s:hidden name="zsxm.xmgzr"></s:hidden>
						<s:property value="xtuser.cnname"/>
					</s:else>
					 -->
				</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:150px" style="height:0px; line-height:0px;">拟入驻单位名称</td>
				<td colspan=3>
					<s:textfield name="zsxm.ndwmc"></s:textfield>
				</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:150px">备注</td>
				<td colspan=3><s:textarea name="zsxm.bzxx" cols="60" rows="4"></s:textarea></td>
			</tr>
		</table>
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
	
</div>	
	</s:form>
	</body>

	<script type="text/javascript">
		if(getSize().w > 930){
			$('mh').style.width = 930+'px';
		}else{
			$('mh').style.width= getSize().w+'px';
		}
		$('tableContainer').style.height = (getSize().h - 100)+"px"; 
		
		function doSave(type){
			if($('zsxm.xmmc').value.trim() == ''){
				alert('项目名称不能为空！');
				return false;
			}
			
			if($('zsxm.xmjdgs').value==''){
				alert('请选择相应的项目进度概述');
				return false;
			}
			if($('zsxm.xmgzr').value==''){
				alert('请选择项目跟踪人！');
				return false;
			}
			var ajax = new AppAjax("zsxm!doSaveZsxm.do",function(data){save_Back(data,type)}).submitForm("zsxmForm");
		}
		
		function save_Back(data,type){
			if(data.message.code >0){
				$('xmid').value=data.message.text;
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
		
		function closeT(){
			getOpener().refresh();
			closeWindow(self.name);
		}
		
	</script>
</html>

