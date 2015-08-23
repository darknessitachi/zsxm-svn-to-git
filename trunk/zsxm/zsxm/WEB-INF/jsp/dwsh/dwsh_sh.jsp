<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
        <script language="JavaScript"src="<s:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>		
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
	<s:component template="xtitle" theme="app" value='单位信息审核'></s:component>
	<s:form name="zsdwForm" id="zsdwForm" action='zsxm!preZsxmU.do' method="post" >

	<s:hidden name="dwid"></s:hidden>
	<s:hidden name="pname"></s:hidden>
	<s:hidden name="opttype"></s:hidden>
 <!--个人信息 start-->

<div id="zmain" style="width:100%px;overflow:auto" >
	<div class="title "onclick=""><h2><span class="red fn">*</span> 单位基本信息</h2><div class="img_right" id="s1" ></div></div>
	
	<div class="tableContainer" id="tableContainer" >
    	<table class="fxtable"  cellpadding="0">
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px"><span class="red fn">*</span>单位类型</td>
				<td class="tdright">
				<s:property value="maps.dwxx.dwlx_mc"/>&nbsp
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px"><span class="red fn">*</span>组织机构代码</td>
				<td class="tdright">
				<s:property value="maps.dwxx.dwdm"/>&nbsp
				</td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px"><span class="red fn">*</span>单位名称</td>
				<td>
					<s:property value="maps.dwxx.dwmc"/>&nbsp
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">单位状态</td>
				<td class="tdright">
					<s:property value="maps.dwxx.dwzt_mc"/>&nbsp
				</td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">招资项目</td>
				<td>
					<s:property value="maps.dwxx.xm_mc"/>&nbsp
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">内/外资</td>
				<td>
					<s:property value="maps.dwxx.nwz_mc"/>&nbsp
				</td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">引进类别</td>
				<td colspan=3>
					<s:iterator value="maps.yjlbs">
						<s:property value="seldm_mc"/>&nbsp|&nbsp
					</s:iterator>
				</td>
			</tr>	
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">法人代表</td>
				<td><s:property value="maps.dwxx.frdb"/>&nbsp</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">成立日期</td>
				<td class="tdright">
				<s:property value="maps.dwxx.clrq"/>&nbsp
				</td>
				
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">单位简介</td>
				<td colspan=3>
				<s:property value="maps.dwxx.dwjj"/>&nbsp
				</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">办公地点</td>
				<td colspan=3>
				常州科教城
				<s:property value="maps.dwxx.bgdd1_mc"/>-<s:property value="maps.dwxx.bgdd2_mc"/>
				-<s:property value="maps.dwxx.bgdd3_mc"/>-<s:property value="maps.dwxx.bgdd4"/>
				
				</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">注册资本</td>
				<td class="tdright">
				<s:property value="maps.dwxx.zczb"/>&nbsp
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">建筑面积</td>
				<td class="tdright">
				<s:property value="maps.dwxx.jzmj"/>&nbsp
				</td>
				
			</tr>
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">行业分类</td>
				<td colspan=3>
					<s:iterator value="maps.dwsxs">
						<s:property value="seldm_mc"/>&nbsp|&nbsp
					</s:iterator>
				</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">产业分类</td>
				<td colspan=3>
					<s:iterator value="maps.cyfls">
						<s:property value="seldm_mc"/>&nbsp|&nbsp
					</s:iterator>
				</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">技术专业</td>
				<td colspan=3>
					<s:iterator value="maps.jszys">
						<s:if test="dictbh=='999'">
							<s:property value="seldm_mc"/>
							(<s:property value="othermc"/>)
						</s:if>
						<s:else>
							<s:property value="seldm_mc"/>
						</s:else>
						&nbsp|&nbsp
					</s:iterator>
				</td>
			</tr>
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">税务管理码</td>
				<td class="tdright">
				<s:property value="maps.dwxx.swglm"/>&nbsp
				</td>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">纳税人识别号</td>
				<td class="tdright">
					<s:property value="maps.dwxx.nsrsbh"/>&nbsp
				</td>
			</tr>
			
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:130px">海归团队批次</td>
				<td class="tdright" colspan=3>
				<s:property value="maps.dwxx.hgtdpc"/>&nbsp
				</td>
				
			</tr>
			
		</table>
	</div>
	

	<div class="footer" style="text-align:center">
		<input class="btn_submit1" type="button" value="审核通过" onclick="doSave(0)"/>
		<input class="btn_submit1" type="button" value="不通过" onclick="doSave(1)"/>
		回退原因:<input type="text" name="thyy" value=""/>
	</div>
	
	</s:form>
	
	<script type="text/javascript">
		$('tableContainer').style.height = (getSize().h -120)+"px"; 
		function doSave(type){
			if(type==1){
				if($('thyy').value==''){
					alert('请填写回退原因!');
					return false;
				}
				var ajax = new AppAjax("dwsh!doShth.do",function(data){save_Back(data,type)}).submitForm("zsdwForm");
			}else{
				var ajax = new AppAjax("dwsh!doShtg.do",function(data){save_Back(data,type)}).submitForm("zsdwForm");
			}
			
		}
		
		function save_Back(data,type){
			if(data.message.code >0){
			
				if(type == 1){
					alert('数据已退回');
				}else{
					alert('数据已审核通过');
				}
				getOpener().refresh();
				closeWin(self.name);
			}else{
				alert(data.message.text);
			}
		}
	</script>
	</body>

</html>

