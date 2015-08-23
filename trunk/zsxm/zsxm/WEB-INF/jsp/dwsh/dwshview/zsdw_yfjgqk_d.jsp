<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
		<script language="JavaScript"src="<s:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>		
	<script type="text/javascript" src="<c:url value="/js/czrc.js"/>" ></script>
	<style>
		.fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
	</style>
	</head>

	<body>
	<s:component template="xtitle" theme="app" value='研发机构孵化企业情况表'></s:component>
	<s:form name="czrcForm" action='rcda!preJszcI.do' method="post" >
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="dwid"></s:hidden>
	<s:hidden name="xh"></s:hidden>
 <!--个人信息 start-->

	<div class="xwincontent" id="xwincontent" >

		<table align="center" cellpadding="0" cellspacing="0" id="tab2">
      		<tr>
      			<td class="tdleft">孵化企业名称</td>
				<td>
					<s:textfield  name="zsdw.rhqymc" cssStyle="width:130;"></s:textfield>
				</td>
				<td class="tdleft">内资/外资</td>
				<td>
					<s:if test="zsdw.nwz==2">
						<input type="radio" name="zsdw.nwz" value="1" />内资
						&nbsp;&nbsp;
						<input type="radio" name="zsdw.nwz" value="2" checked/>外资
					</s:if>
					<s:else>
						<input type="radio" name="zsdw.nwz" value="1" checked/>内资
						&nbsp;&nbsp;
						<input type="radio" name="zsdw.nwz" value="2"/>外资
					</s:else>
				</td>
				
				
			</tr>
			
			<tr>	
				<td class="tdleft">注册资本</td>
				<td>
					<s:textfield  name="zsdw.zczb" cssStyle="width:130;" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
					<font color=red>(万元)</font>
				</td>
				
				<td class="tdleft">成立日期</td>
				<td>
					<input type="text" class="Wdate" name="zsdw.clrq" id="date1" style="text-align:left;width:130" value="<s:property value="zsdw.clrq"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>
				</td>
				
			</tr>
			<tr>
				<td>研发机构占股</td>
				<td>
					<s:textfield  name="zsdw.yfjgzg" cssStyle="width:130;" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
					<font color=red>%</font>
				</td>
				<td>其他股东占股</td>
				<td>
					<s:textfield  name="zsdw.qtgdzg" cssStyle="width:130;" onfocus="javascript:this.select();" onblur="javascript:RC.isNum(this.id);"></s:textfield>
					<font color=red>%</font>
				</td>
			</tr>
			<tr>
      			<td class="tdleft">主要联络人</td>
				<td>
					<s:textfield  name="zsdw.zylxr" cssStyle="width:130;"></s:textfield>
				</td>
				<td class="tdleft">联系电话</td>
				<td>
					<s:textfield  name="zsdw.lxtel" cssStyle="width:130;"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="tdleft">备注</td>
				<td colspan=3>
					<s:textfield  name="zsdw.bz" cssStyle="width:260;"></s:textfield>
				</td>
			</tr>
		</table>	


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
		function closeWindows(){closeWin(self.name);}
		function closeWin(sid){
			if(reload == 1){
				var xx = $('pname').value
				var yy= eval('parent.document.'+xx);
				yy.refresh();
			}
			parent.closeXwin(sid);
		}
		
		function doSave(type){
			if($('zsdw.rhqymc').value.trim() ==''){
				alert('孵化企业名称名称不能为空！请填写');
				return false;
			}
			
			if(type ==1){
				var ajax = new AppAjax("zsdw!doYfjgqkI.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}else if(type == 2){
				var ajax = new AppAjax("zsdw!doYfjgqkI.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
			}else if(type == 3){
				var ajax = new AppAjax("zsdw!doYfjgqkU.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
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
		
	</script>
</html>

