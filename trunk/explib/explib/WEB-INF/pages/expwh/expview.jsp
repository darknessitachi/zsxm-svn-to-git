<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	
	<head>
		<title>专家信息预览</title>
		<%@ include file="/common/meta.jsp"%>
		<script type="text/javascript">
			JSLoader.loadStyleSheet("styles/cv_rc.css");
			JSLoader.loadJavaScript("js/comm.js");
		</script>
		
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

	<body style="overflow:auto;TEXT-ALIGN: center;">
	<s:form name="czrcForm"  method="post" >
	<s:hidden name="rcid" id='rcid' ></s:hidden>
	<div style="width:960px;heigth:100%;" >
	<br><br>
	<div>
		<font size=5><b>专家信息预览</b></font>
	</div>
	<br><br>
	<div id="xwincontent"  class="tableContainer"  >
	<table class="fxtable" cellspacing="0"  cellpadding="0">
		<tr>
			<td style="text-align:left;font:bold 12px 'lucida Grande',Verdana;background:#efefef">专家基本信息</td>
		</tr>
	</table>
	
	<table id='rcjbxx' class="fxtable" cellspacing="0" cellpadding="0">
    		<tr>
    			<td colspan=4 style="height:30px;background:#efefef">
    				<font color="#8b0000"><b>专家类型：</b></font>
    				<b>
    					<s:checkboxlist name="exp.exptype"  list="xtdict36" listKey="dictbh" listValue="dictname" value="dmkey" ></s:checkboxlist>
    				</b>
    			</td>
    		</tr>
    		<tr  id="dis002" style="display:none">
				<td class="bt">擅长投资行业<font color=red>*</font>&nbsp;</td>
				<td colspan=3>
					<s:checkboxlist name="exp.sctzhy"  list="xtdict" listKey="dictbh" listValue="dictname" value="dmkey2" ></s:checkboxlist>
				</td>
				
			</tr>
			<tr>
				<td class="bt">姓名<span class="red fn">*</span>&nbsp</td>
				<td colspan=2>
					<s:property value="maps.rcdaxx1.rcname"/>
				</td>
				<td  align="center" rowspan=5>
					<s:if test="maps.rcdaxx1.fjmc==''">
						<img src="images/skin0/boy.gif" id="imagespig" style="width:100;height:100;"/>
					</s:if>
					<s:else>
						<img src="upload/<s:property value="maps.rcdaxx1.fjmc"/>" id="imagespig" style="width:110;height:130;"/>
					</s:else>
				</td>
			</tr>
			<tr>
				<td class="bt">曾用名&nbsp</td>
				<td colspan=2><s:property value="maps.rcdaxx1.oldname"/>&nbsp</td>
			</tr>
			<tr>
				<td class="bt" >性别<span class="red fn">*</span>&nbsp</td>
				<td colspan=2><s:property value="maps.rcdaxx1.sex_mc"/>&nbsp</td>
			</tr>
			<tr>
				<td class="bt">出生年月<span class="red fn">*</span>&nbsp</td>
				<td colspan=2><s:property value="maps.rcdaxx1.birthday"/>&nbsp</td>
			</tr>
			
			<tr>
				<td class="bt">工作单位<span class="red fn">*</span>&nbsp</td>
				<td colspan=2><s:property value="maps.rcdaxx1.workunit"/>&nbsp</td>
				
			</tr>
			<tr>
				<td class="bt">证件类别&nbsp</td>
				<td><s:property value="maps.rcdaxx1.zjlb_mc"/>&nbsp</td>
				<td class="bt">证件号码<span class="red fn">*</span>&nbsp</td>
				<td>
					<s:property value="maps.rcdaxx1.zjno"/>
				&nbsp</td>
			</tr>
			
			<tr>
				<td class="bt">学历&nbsp</td>
				<td><s:property value="maps.rcdaxx1.xl_mc"/>&nbsp</td>
				<td class="bt">学位&nbsp</td>
				<td><s:property value="maps.rcdaxx1.xw_mc"/>&nbsp</td>
			</tr>
			
			
			<tr>
				<td class="bt">联系电话<span class="red fn">*</span>&nbsp</td>
				<td><s:property value="maps.rcdaxx1.officetel"/>&nbsp</td>
				<td class="bt">手机<span class="red fn">*</span>&nbsp</td>
				<td><s:property value="maps.rcdaxx1.ptel"/>&nbsp</td>
			</tr>
			
			
 	 		<tbody id="fluserdiv">
 	 			
 	 		</tbody>
			<tr>
				<td class="bt">专家学术成就简介&nbsp;</td>
				<td colspan=3>
					<s:textarea name="exp.info" cols="55" rows="15"></s:textarea>
				</td>
			</tr>
			<tr>
				<td class="bt">专业技术职务&nbsp</td>
				<td><s:property value="maps.rcdaxx1.zc_mc"/>&nbsp</td>
				<td class="bt">专业技术职务等级&nbsp</td>
				<td><s:property value="maps.rcdaxx1.zw_mc"/>&nbsp</td>
			</tr>
			<tr>
				<td class="bt">行政职务&nbsp</td>
				<td>
					<s:property value="maps.rcdaxx1.xzzw"/>
				</td>
				<td colspan=2>&nbsp</td>
			</tr>
			<tr>
				<td class="bt">所学专业&nbsp</td>
				<td>
				<s:property value="maps.rcdaxx1.xxzy_mc"/>
				&nbsp</td>
				<td class="bt">从事专业&nbsp</td>
				<td>
				<s:property value="maps.rcdaxx1.cszy_mc"/>
				&nbsp</td>
			</tr>
			<tr>
				<td class="bt">熟悉专业</td>
				<td colspan=3>
				<s:property value="maps.rcdaxx1.sxzy1_mc"/>
				&nbsp;&nbsp;
				<s:property value="maps.rcdaxx1.sxzy2_mc"/>
				&nbsp;&nbsp;
				<s:property value="maps.rcdaxx1.sxzy3_mc"/>
				
				&nbsp</td>
			</tr>
			
			<tr>
				<td class="bt">所学领域&nbsp</td>
				<td>
				<s:property value="exp.sxly_mc"/>
				&nbsp</td>
				<td class="bt">从事领域&nbsp</td>
				<td>
				<s:property value="exp.csly_mc"/>
				&nbsp</td>
			</tr>
			
			<tr>
				<td class="bt">毕业学校&nbsp</td>
				<td><s:property value="maps.rcdaxx1.byxx"/>&nbsp</td>
				<td class="bt">毕业时间&nbsp</td>
				<td><s:property value="maps.rcdaxx1.byrq"/>&nbsp</td>
			</tr>
			<tr>
				<td class="bt">留学国家&nbsp</td>
				<td id='lxgjid' style="width:240px">
					<s:iterator value="maps.rcdalxgj" id="lxgjlist">
						<s:property value="gjmc"/><br>
					</s:iterator>
				&nbsp</td>
				<td colspan=2></td>
			</tr>
			
			<tr>
				<td class="bt">籍贯&nbsp</td>
				<td>
				<s:property value="maps.rcdaxx1.jg_mc"/>
				&nbsp</td>
				
				
				<td class="bt">所在城市&nbsp</td>
				<td>
					<s:property value="maps.rcdaxx1.szdq_mc"/>
				&nbsp</td>
				
			</tr>
			<tr>
				<td class="bt">单位性质&nbsp</td>
				<td><s:property value="maps.rcdaxx1.dwxz_mc"/>&nbsp</td>
				
				<td class="bt">单位地址&nbsp</td>
				<td><s:property value="maps.rcdaxx1.dwaddr"/>&nbsp</td>
				
			</tr>
			
			<tr>
				<td class="bt">家庭地址&nbsp</td>
				<td><s:property value="maps.rcdaxx1.jtaddr"/>&nbsp</td>
				<td class="bt">邮政编码&nbsp</td>
				<td><s:property value="maps.rcdaxx1.jtcode"/>&nbsp</td>
			</tr>
			
			<tr>
				
				<td class="bt">家庭电话&nbsp</td>
				<td><s:property value="maps.rcdaxx1.jttel"/>&nbsp</td>
				
				<td class="bt">电子信箱&nbsp</td>
				<td><s:property value="maps.rcdaxx1.email"/>&nbsp</td>
			</tr>
			
		</table>
		<div style="height:5px"></div>
		
		<div  class="tableContainer" style="width:100%">
			<table class="fxtable" cellspacing="0"  cellpadding="0">
				<tr>
					<td style="text-align:left;font:bold 12px 'lucida Grande',Verdana;background:#efefef">专业领域</td>
				</tr>
			</table>
			
			<table>
				<thead>
					<tr>
						
						<td>熟悉领域</td>
						<td width="200px">熟悉程度</td>
					</tr>
				</thead>
				<s:iterator value="maps.jszc">
					<tr>
						
						<td>
							<s:if test="lyother=='999'">
								<s:property value="ly_mc"/>:(<s:property value="lymc"/>)
							</s:if>
							<s:else>
								<s:property value="ly_mc"/>
							</s:else>
						</td>
						<td>
							<s:property value="sxcd"/>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div style="height:5px"></div>
		
		<div  class="tableContainer" style="width:100%">
			<table class="fxtable" cellspacing="0"  cellpadding="0">
				<tr>
					<td style="text-align:left;font:bold 12px 'lucida Grande',Verdana;background:#efefef">学习简历</td>
				</tr>
			</table>
			<table>
				<thead>
					<tr>
						<td>开始日期</td>
						<td>结束日期</td>
						<td>院校</td>
						<td>所学专业</td>
						<td>学历</td>
						<td>学位</td>
						<td>毕(结、肄)业</td>
					</tr>
				</thead>
				<s:iterator value="maps.xxjl">
					<tr>
						<td><s:property value="brq"/></td>
						<td><s:property value="erq"/></td>
						<td><s:property value="yx"/></td>
						<td><s:property value="sxzy_mc"/></td>
						<td><s:property value="xl_mc"/></td>
						<td><s:property value="xw_mc"/></td>
						<td><s:property value="byjy"/></td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div style="height:5px"></div>
		
		<div  class="tableContainer" style="width:100%">
		
			<table class="fxtable" cellspacing="0"  cellpadding="0">
				<tr>
					<td style="text-align:left;font:bold 12px 'lucida Grande',Verdana;background:#efefef">工作简历</td>
				</tr>
			</table>
			
			<table>
				<thead>
					<tr>
						<td>开始日期</td>
						<td>结束日期</td>
						<td>所在单位及部门</td>
						<td>职务</td>
						
					</tr>
				</thead>
				<s:iterator value="maps.gzjl">
					<tr>
						<td><s:property value="brq"/></td>
						<td>
						<s:if test="nowbz==1">
								至今
							</s:if>
							<s:else>
								<s:property value="erq"/>
							</s:else>
						</td>
						<td><s:property value="dwbm"/></td>
						<td><s:property value="zw"/></td>
						
					</tr>
				</s:iterator>
			</table>
		</div>						

		<div style="height:5px"></div>
		
		<div  class="tableContainer" style="width:100%">
		
			<table class="fxtable" cellspacing="0"  cellpadding="0">
				<tr>
					<td style="text-align:left;font:bold 12px 'lucida Grande',Verdana;background:#efefef">科研项目</td>
				</tr>
			</table>
			
			<table>
				<thead>
					<tr>
						<td>获资助项目类别</td>
						<td>获资助项目名称</td>
						<td>项目来源</td>
						<td>本人作用</td>
						<td>完成情况</td>
						<td  width="100px">完成时间</td>
						
					</tr>
				</thead>
				<s:iterator value="maps.ktxm">
					<tr>
						<td><s:property value="zzxmlb_mc"/></td>
						<td><s:property value="xmmc"/></td>
						<td><s:property value="xmly_mc"/></td>
						<td><s:property value="brzy_mc"/></td>
						<td><s:property value="wcqk_mc"/></td>
						<td><s:property value="wcrq"/> 至 <s:property value="wcendrq"/></td>
						
					</tr>
				</s:iterator>
			</table>
		</div>		
		<div style="height:5px"></div>
		
		<div  class="tableContainer" style="width:100%">
			<table class="fxtable" cellspacing="0"  cellpadding="0">
				<tr>
					<td style="text-align:left;font:bold 12px 'lucida Grande',Verdana;background:#efefef">获奖情况</td>
				</tr>
			</table>
			
			<table>
				<thead>
					<tr>
						<td>奖项名称</td>
						<td>获奖等级</td>
						<td width="100px">获奖时间</td>
						<td>授予部门</td>
						
					</tr>
				</thead>
				<s:iterator value="maps.ryhj">
					<tr>
						<td><s:property value="jxmc"/></td>
						<td><s:property value="hjdj_mc"/></td>
						<td><s:property value="hjrq"/></td>
						<td><s:property value="bjbm"/></td>
					</tr>
				</s:iterator>
			</table>
		</div>			
		<div style="height:5px"></div>
		
		<div  class="tableContainer" style="width:100%">
			<table class="fxtable" cellspacing="0"  cellpadding="0">
				<tr>
					<td style="text-align:left;font:bold 12px 'lucida Grande',Verdana;background:#efefef">社会兼职情况</td>
				</tr>
			</table>
			<table>
				<thead>
					<tr>
						<td width="100px">开始日期</td>
						<td width="100px">结束日期</td>
						<td>兼职（聘用）单位</td>
						<td>兼（聘）职身份</td>
						<td>备注</td>
						
					</tr>
				</thead>
				<s:iterator value="maps.shjz">
					<tr>
						<td><s:property value="brq"/></td>
						<td><s:property value="erq"/></td>
						<td><s:property value="jsdw"/></td>
						<td><s:property value="jssf"/></td>
						<td><s:property value="sm"/></td>
					</tr>
				</s:iterator>
			</table>
		</div>		
		
<div style="height:5px"></div>
		
		<div  class="tableContainer" style="width:100%">
			<table class="fxtable" cellspacing="0"  cellpadding="0">
				<tr>
					<td style="text-align:left;font:bold 12px 'lucida Grande',Verdana;background:#efefef">主要论著论文</td>
				</tr>
			</table>
			<table>
				<thead>
					<tr>
						<td>论著（论文）名称</td>
						<td>出版社或刊物名称</td>
						<td>署名顺序</td>
						<td>出版（发表）时间</td>
					</tr>
				</thead>
				<s:iterator value="maps.zylz">
					<tr>
						<td><s:property value="zzmc"/></td>
						<td><s:property value="cbmc"/></td>
						<td><s:property value="smno_mc"/></td>
						<td><s:property value="cbrq"/></td>
					</tr>
				</s:iterator>
			</table>
		</div>		
		<div style="height:5px"></div>
		
		<div  class="tableContainer" style="width:100%">
			<table class="fxtable" cellspacing="0"  cellpadding="0">
				<tr>
					<td style="text-align:left;font:bold 12px 'lucida Grande',Verdana;background:#efefef">知识产权情况</td>
				</tr>
			</table>
			<table>
				<thead>
					<tr>
						<td>知识产权名称</td>
						<td>知识产权类型</td>
						<td>获得时间</td>
						<td>来常前知识产权情况</td>
						<td>注册地点</td>
						<td>备注</td>
					</tr>
				</thead>
				<s:iterator value="maps.zscq">
					<tr>
						<td><s:property value="zsmc"/></td>
						<td><s:property value="zsno_mc"/></td>
						<td width="100px"><s:property value="hdrq"/></td>
						<td><s:property value="iscz==2?'来常后':'来常前'"/></td>
						<td><s:property value="zcdd==2?'国外':'国内'"/></td>
						<td><s:property value="sm"/></td>
					</tr>
				</s:iterator>
			</table>
		</div>	
</div>	

	</div>

	</s:form>
	</body>

	<script type="text/javascript">
	//	$('xwincontent').style.height=getSize().h-100+'px';
		Event.observe(window, "load", function() {
			initExp();
			window.setTimeout(initExpfl,0);
		}, true);
		function initExpfl(){
			var ajax = new AppAjax("exp!getExpflListView.do?rcid="+$('rcid').value,function(data){
				$('fluserdiv').update(data.expfl);
			}).submit();

		}
		
		function initExp(){
			var d = COM.checkbox('exp.exptype');
			for(var i=0;i<d.length;i++){
				if(d[i].v == '002'){
					$('dis002').style.display = '';
				}
			}
			
		}
	</script>
</html>

