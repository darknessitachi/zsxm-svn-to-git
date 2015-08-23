<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<script type="text/javascript">
			JSLoader.loadStyleSheet("styles/cv_rc.css");
			JSLoader.loadStyleSheet("js/dhtmlxTree/dhtmlxtree.css");
			JSLoader.loadStyleSheet("styles/cv_rc.css");
			JSLoader.loadJavaScript("js/dhtmlxTree/dhtmlxcommon.js");
			JSLoader.loadJavaScript("js/dhtmlxTree/dhtmlxtree.js");
			JSLoader.loadJavaScript("js/dhtmlxTree/dhtmlxtree_json.js");
			JSLoader.loadJavaScript("js/dhtmlxTree/dhtmlXTreeExtend.js");
			JSLoader.loadJavaScript("js/My97DatePicker/WdatePicker.js");
			JSLoader.loadJavaScript("js/comm.js");

		</script>
		
		<style>
			.fsg_nr{ position:absolute; display:none;top:25px;text-align:left; right:-3px; background:#fff; border:4px solid #98B1C8; }
			.fsg_nr td{ height:0px; line-height:14px;font-size:12px; color:#006699;}
	
		</style>
	</head>

	<body>
	<s:form name="czrcForm" action='exp!preExp.do' method="post"  enctype="multipart/form-data">
	<s:hidden name="roledm" id='roledm' ></s:hidden>
	<s:hidden name="rcid" id='rcid' ></s:hidden>
	<s:hidden name="pname" id='pname' ></s:hidden>
	<s:hidden name="opttype" id='opttype'></s:hidden>
	<s:hidden name="winid" id="winid"></s:hidden>
 <!--个人信息 start-->
	<div class="title "onclick=""><h2><span class="red fn">*</span> 专家基本信息
	
	</h2><div class="img_right" id="s1" ></div>
	</div>
	<div id="fxtableContainer" class="fxtableContainer" style="width:100%;heigth:100%;overflow:auto;" >
    	<table id='rcjbxx' class="fxtable" cellspacing="0" cellpadding="0">
    		<tr>
    			<td colspan=4 style="height:30px;background:#efefef">
    				<font color="#8b0000"><b>专家类型：</b></font>
    				<b>
    					<s:checkboxlist name="exp.exptype"  list="xtdict36" listKey="dictbh" listValue="dictname" value="dmkey" onclick="clickExptype(this)"></s:checkboxlist>
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
					<s:textfield cssStyle="width:130px" name="exp.rcname" id="exp.rcname"/>&nbsp
					<font color=red>姓名必须填写</font>
				</td>
				<td  align="center" rowspan=5>
					
					
					<s:hidden name="exp.fid"></s:hidden>
					<img src="images/skin0/boy.gif" id="imagespig" style="width:100;height:100;"/>
					<br>
					<font color=red>格式:gif,jpg,bmp,png</font><br>
					<input type="file" name="upload" value="" id="uploadf" style="width:110px" onchange="uploadFile(this);"/>
				</td>
			</tr>
			<tr>
				<td class="bt">曾用名&nbsp</td>
				<td colspan=2><s:textfield cssStyle="width:130px" name="exp.oldname"/>&nbsp</td>
			</tr>
			<tr>
				<td class="bt" >性别<span class="red fn">*</span>&nbsp</td>
				<td colspan=2><s:select name="exp.sex" list="xtdict4" cssStyle="width:130" listKey="dictbh" listValue="dictname" />&nbsp</td>
			</tr>
			<tr>
				<td class="bt">出生年月<span class="red fn">*</span>&nbsp</td>
				<td colspan=2><input type="text" class="Wdate" name="exp.birthday" id="date" style="text-align:left;width:130" value="<s:property value="exp.birthday"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1920-01-01" readonly/>&nbsp</td>
			</tr>
			
			<tr>
				<td class="bt">工作单位<span class="red fn">*</span>&nbsp</td>
				<td colspan=2><s:textfield cssStyle="width:200px" name="exp.workunit"/>&nbsp</td>
				
			</tr>
			<tr>
				<td class="bt">证件类别&nbsp</td>
				<td><s:select name="exp.zjlb" list="xtdict1" cssStyle="width:130" listKey="dictbh" listValue="dictname" />&nbsp</td>
				<td class="bt">证件号码<span class="red fn">*</span>&nbsp</td>
				<td>
					<s:textfield name="exp.zjno" cssStyle="width:130" />
				&nbsp</td>
			</tr>
			
			<tr>
				<td class="bt">学历&nbsp</td>
				<td><s:select name="exp.xl" list="xtdict2" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" />&nbsp</td>
				<td class="bt">学位&nbsp</td>
				<td><s:select name="exp.xw" list="xtdict3" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" />&nbsp</td>
			</tr>
			
			
			<tr>
				<td class="bt">联系电话<span class="red fn">*</span>&nbsp</td>
				<td><s:textfield cssStyle="width:130px" name="exp.officetel" />&nbsp</td>
				<td class="bt">手机<span class="red fn">*</span>&nbsp</td>
				<td><s:textfield cssStyle="width:130px" name="exp.ptel" onblur="COM.isNumChar(this.id)" maxlength="12"/>&nbsp</td>
			</tr>
			<tr>
				<td class="bt">专家分类&nbsp</td>
				<td>
				<INPUT id="exp.zjfl_button" class=selectBut2  title=专家分类 value="选择专家分类" type=button  onclick="O_D('exp.zjfl_button','flwhsel','center');">
				<s:hidden name="exp.zjfl"></s:hidden>
				&nbsp;</td>
				<td class="bt">个人荣誉&nbsp;</td>
				<td>
					<INPUT id="exp.grry_button" class=selectBut2  title=个人荣誉 value="选择个人荣誉" type=button  onclick="O_D('exp.grry_button','grrysel','center');">
					
				</td>
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
				<td><s:select name="exp.zc" list="xtdict5" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" />&nbsp</td>
				<td class="bt">专业技术职务等级&nbsp</td>
				<td><s:select name="exp.zw" list="xtdict23" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" />&nbsp</td>
			</tr>
			<tr>
				<td class="bt">行政职务&nbsp</td>
				<td>
					<s:textfield cssStyle="width:200px" name="exp.xzzw"/>
				</td>
				<td colspan=2>&nbsp</td>
			</tr>
			<tr>
				<td class="bt">所学专业&nbsp</td>
				<td>
				<INPUT id="exp.xxzy_button" class=selectBut2  title=所学专业 value="<s:property value="exp.xxzymc"/>" type=button  onclick="O_D('exp.xxzy_button','xtdlb13','center');set13WithTreeID('exp.xxzy');loadRcTree(13);">
				<s:hidden name="exp.xxzy"></s:hidden>
				&nbsp</td>
				<td class="bt">从事专业&nbsp</td>
				<td>
				<INPUT id="exp.cszy_button" class=selectBut2  title=从事专业 value="<s:property value="exp.cszymc"/>" type=button  onclick="O_D('exp.cszy_button','xtdlb13','center');set13WithTreeID('exp.cszy');loadRcTree(13);">
				<s:hidden name="exp.cszy"></s:hidden>
				&nbsp</td>
			</tr>
			<tr>
				<td class="bt">熟悉专业</td>
				<td colspan=3>
				<INPUT id="exp.sxzy1_button" class=selectBut2  title=熟悉专业1 value="<s:property value="exp.sxzy1mc"/>" type=button  onclick="O_D('exp.sxzy1_button','xtdlb13','center');set13WithTreeID('exp.sxzy1');loadRcTree(13);">
				<s:hidden name="exp.sxzy1"></s:hidden>
				
				<INPUT id="exp.sxzy2_button" class=selectBut2  title=熟悉专业2 value="<s:property value="exp.sxzy2mc"/>" type=button  onclick="O_D('exp.sxzy2_button','xtdlb13','center');set13WithTreeID('exp.sxzy2');loadRcTree(13);">
				<s:hidden name="exp.sxzy2"></s:hidden>
				
				<INPUT id="exp.sxzy3_button" class=selectBut2  title=熟悉专业3 value="<s:property value="exp.sxzy3mc"/>" type=button  onclick="O_D('exp.sxzy3_button','xtdlb13','center');set13WithTreeID('exp.sxzy3');loadRcTree(13);">
				<s:hidden name="exp.sxzy3"></s:hidden>
				
				&nbsp</td>
			</tr>
			
			<tr>
				<td class="bt">所学领域&nbsp</td>
				<td>
				
				<INPUT id="exp.sxly_button" class=selectBut2  title=所学领域 value="<s:property value="exp.sxlymc"/>" type=button  onclick="O_D('exp.sxly_button','xtdlb14','center');set14WithTreeID('exp.sxly');loadLyTree(14);">
				<s:hidden name="exp.sxly"></s:hidden>
				&nbsp</td>
				<td class="bt">从事领域&nbsp</td>
				<td>
				<INPUT id="exp.csly_button" class=selectBut2  title=从事领域 value="<s:property value="exp.cslymc"/>" type=button  onclick="O_D('exp.csly_button','xtdlb14','center');set14WithTreeID('exp.csly');loadLyTree(14);">
				<s:hidden name="exp.csly"></s:hidden>
				&nbsp</td>
			</tr>
			
			<tr>
				<td class="bt">毕业学校&nbsp</td>
				<td><s:textfield cssStyle="width:130px" name="exp.byxx"/>&nbsp</td>
				<td class="bt">毕业时间&nbsp</td>
				<td><input type="text" class="Wdate" name="exp.byrq" id="date" style="text-align:left;width:130" value="<s:property value="exp.byrq"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>&nbsp</td>
			</tr>
			<tr>
				<td class="bt">留学国家&nbsp</td>
				<td id='lxgjid' style="width:240px">
					<s:if test="qlist.size > 0">
						<s:iterator value="qlist" id="qlist">
							<input type="text" style="width:200px" id='lxgjxh<s:property id="qlist" value="xh"/>' name="lxgjgjmc" value='<s:property id="qlist" value="gjmc"/>'/>
							<a href="javascript:" onclick="dellLxgj('lxgjxh<s:property id="qlist" value="xh"/>',this)">删除</a>
						</s:iterator>
					</s:if>
					<s:else>
						<s:textfield cssStyle="width:200px" id="lxgjxh1" name="lxgjgjmc"/>
					</s:else>
				&nbsp</td>
				<td colspan=2><a href="javascript:" onclick="addLxgj()"><font color=blue>增加留学国家(<font color=black>点击</font>)</font></a>&nbsp</td>
			</tr>
			
			<tr>
				<td class="bt">籍贯&nbsp</td>
				<td>
				<s:if test="exp.nation=='001'">
					<INPUT id="exp.jg_button" class="selectBut2"  title="选择籍贯" value="<s:property value="exp.jgmc"/>" type=button onclick="O_D('exp.jg_button','xtdlb10','center');loadJgTree(10,'exp.jg');">
					<s:hidden name="exp.jg"></s:hidden>
					<!--<s:textfield name="exp.jg" cssStyle="width:130;" />-->
				</s:if>
				<s:else>
					<INPUT id="exp.jg_button" class="selectBut2"  title="选择籍贯" value="<s:property value="exp.jgmc==''?'选择籍贯':exp.jgmc"/>" type=button onclick="O_D('exp.jg_button','xtdlb10','center');loadJgTree(10,'exp.jg');">
					<s:hidden name="exp.jg"></s:hidden>
					<!--<s:textfield name="exp.jg" cssStyle="width:130;background:#efefef" readonly="true" />-->
				</s:else>
				&nbsp</td>
				
				
				<td class="bt">所在城市&nbsp</td>
				<td>
					<INPUT id="exp.szdq_button" class="selectBut2"  title="选择" value="<s:property value="exp.szdqmc==''?'选择':exp.szdqmc"/>" type=button onclick="O_D('exp.szdq_button','xtdlb10','center');loadJgTree(10,'exp.szdq');">
					<s:hidden name="exp.szdq"></s:hidden>
					
				&nbsp</td>
				
			</tr>
			<tr>
				<td class="bt">单位性质&nbsp</td>
				<td><s:select name="exp.dwxz" list="xtdict12" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" />&nbsp</td>
				
				<td class="bt">单位地址&nbsp</td>
				<td><s:textfield cssStyle="width:130px" name="exp.dwaddr"/>&nbsp</td>
				
			</tr>
			
			<tr>
				<td class="bt">家庭地址&nbsp</td>
				<td><s:textfield cssStyle="width:130px" name="exp.jtaddr"/>&nbsp</td>
				<td class="bt">邮政编码&nbsp</td>
				<td><s:textfield cssStyle="width:130px" name="exp.jtcode" id="exp.jtcode" onblur="COM.isNumChar(this.id)"/>&nbsp</td>
			</tr>
			
			<tr>
				
				<td class="bt">家庭电话&nbsp</td>
				<td><s:textfield cssStyle="width:130px" name="exp.jttel" />&nbsp</td>
				
				<td class="bt">电子信箱&nbsp</td>
				<td><s:textfield cssStyle="width:130px" name="exp.email"/>&nbsp</td>
			</tr>
			
		</table>
		
	</div>
	
	<div style="background:#add8e6;width:100%;">
		&nbsp;&nbsp;&nbsp;&nbsp;
		关键字&nbsp;:&nbsp;<s:textfield name="exp.expgjbs" cssStyle="width:200px"></s:textfield>
	</div>
	<div class="footer" style="background:#efefef;">
		
		<input class="btn_submit1" type="button" value="保    存" onclick="javascript:doSave(1);"/>
		<input class="btn_submit1" type="button" value="保存下一步" onclick="javascript:doSave(2);"/>
		<s:if test="opttype==3">
			<input class="btn_submit1" type="button" value="保存退出" onclick="javascript:doSave(4);"/>
		</s:if>
		<s:else>
			<s:if test="xtuser.loginbz == 1">
				<input class="btn_submit1" type="button" value="填写完成" onclick="javascript:doSave(3);"/>
			</s:if>
		</s:else>
	</div>

	
	<!--个人信息 以下占未用信息end --> 
	<s:hidden name="exp.nation"></s:hidden>
	<s:hidden name="exp.exphbdf" id="starsp2-input"></s:hidden>
	<s:hidden name="exp.expjsdf" id="starsp1-input"></s:hidden>
	<s:hidden name="exp.rclb"></s:hidden>
	<s:hidden name="exp.dwcode"></s:hidden>
	<s:hidden name="exp.fax"></s:hidden>
	<s:hidden name="exp.zgbs"></s:hidden>
	<s:hidden name="exp.xsjt"></s:hidden>
	<s:hidden name="exp.hdzz"></s:hidden>
	<s:hidden name="exp.ykbh"></s:hidden>
	<s:hidden name="exp.sbkh"></s:hidden>
	<s:hidden name="exp.jhkh"></s:hidden>
	<s:hidden name="exp.zgbm"></s:hidden>
	
	
	<div loading='0' id='xtdlb14' class="fsg_nr"  style="display:none;width:340;height:360;overflow:auto;">
		<div id="xtdlb14button" style="background:#4682b4;width:100%;height:25;text-align:right">
			<input type='button' value='关  闭' onclick="javascript:$('xtdlb14').style.display='none'"/>
			&nbsp;&nbsp;
		</div>
		<div id="xtdlb14load"><img src="images/skin0/other/upload.gif">数据载入中.....</div>
		<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
	</div>
		
		
	
	<div loading='0' id='xtdlbfl14' class="fsg_nr" style="display:none;width:340;height:360;overflow:auto;">
		<div id="xtdlbfl14button" style="background:#4682b4;width:100%;height:25;text-align:right">
			<input type='button' value='关  闭' onclick="javascript:$('xtdlbfl14').style.display='none'"/>
			&nbsp;&nbsp;
		</div>
		<div id='xtdlbfl14load'><img src="images/skin0/other/upload.gif">数据载入中.....</div>
		<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
	</div>
		
	<div loading='0' id='xtdlbfl13' class="fsg_nr" style="display:none;width:340;height:360;overflow:auto;">
		<div id="xtdlbfl13button" style="background:#4682b4;width:100%;height:25;text-align:right">
			<input type='button' value='关  闭' onclick="javascript:$('xtdlbfl13').style.display='none'"/>
			&nbsp;&nbsp;
		</div>
		<div id='xtdlbfl13load'><img src="images/skin0/other/upload.gif">数据载入中.....</div>
		<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
	</div>
	
	<div loading='0' id='xtdlb13' class="fsg_nr" style="display:none;width:340;height:360;overflow:auto;">
		<div id="xtdlb13button" style="background:#4682b4;width:100%;height:25;text-align:right">
			<input type='button' value='关  闭' onclick="javascript:$('xtdlb13').style.display='none'"/>
			&nbsp;&nbsp;
		</div>
		<div id="xtdlb13load"><img src="images/skin0/other/upload.gif">数据载入中.....</div>
		<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
	</div>
	

	
	
	<div loading='0' id='xtdlb10' class="fsg_nr" style="display:none;width:340;height:360;overflow:auto;">
		<div id="xtdlb10button" style="background:#4682b4;width:100%;height:25;text-align:right">
			<input type='button' value='关  闭' onclick="javascript:$('xtdlb10').style.display='none'"/>
			&nbsp;&nbsp;
		</div>
		<div id="xtdlb10load"><img src="images/skin0/other/upload.gif">数据载入中.....</div>
		<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
	</div>
	
	<div loading='0' id='rclbtree' class="fsg_nr" style="display:none;width:340;height:360;overflow:auto;">
		<div id="rclbtreebutton" style="background:#4682b4;width:100%;height:25;text-align:right">
			<input type='button' value='关  闭' onclick="javascript:$('rclbtree').style.display='none'"/>
			&nbsp;&nbsp;
		</div>
		<div id="rclbtreeload"><img src="images/skin0/other/upload.gif">数据载入中.....</div>
		<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
	</div>
	
	<div  id='flwhsel' class="fsg_nr" style="display:none;width:340;height:360;overflow:auto;">
		<div id="flwhselbutton" style="background:#4682b4;width:100%;height:25;text-align:right">
			<input type='button' value='关  闭' onclick="javascript:$('flwhsel').style.display='none'"/>
			&nbsp;&nbsp;
		</div>
		<div loading='0' id="treesel" style="width:100%;height:290;overflow:auto;">
			<s:iterator value="expfllist" id="expfllist">
				&nbsp;<input type="checkbox" name="expfls" value="<s:property value="dm"/>" <s:if test="isc>=1">checked</s:if> >&nbsp;<s:property value="mc"/><br>
			</s:iterator>
		</div>
		<div class="footer" style="background:#f5f5f5" style="width:100%;height:30;">
			<input class="button3" type="button" value="确  定" onclick="doseltrue('flwhsel')"/>
			&nbsp&nbsp&nbsp&nbsp
		</div>
		<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
	</div>
	
	<div  id='grrysel' class="fsg_nr" style="display:none;width:340;height:360;overflow:auto;">
		<div id="flwhselbutton" style="background:#4682b4;width:100%;height:25;text-align:right">
			<input type='button' value='关  闭' onclick="javascript:$('grrysel').style.display='none'"/>
			&nbsp;&nbsp;
		</div>
		<div loading='0' id="treesel" style="width:100%;height:290;overflow:auto;">
			<s:iterator value="xtdict25" id="xtdict25">
				&nbsp;<input type="checkbox" name="exp.grry" id="exp.grry<s:property value="dictbh"/>" value="<s:property value="dictbh"/>">&nbsp;<s:property value="dictname"/><br>
			</s:iterator>
		</div>
		<div class="footer" style="background:#f5f5f5" style="width:100%;height:30;">
			<input class="button3" type="button" value="确  定" onclick="doseltrue('grrysel')"/>
			&nbsp&nbsp&nbsp&nbsp
		</div>
		<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
	</div>
		
</s:form>

<iframe name="uploadFrame" height="0px" width="opx"></iframe>
</body>
	
	<script type="text/javascript">
		Event.observe(window, "load", function() {
			$('exp.rcname').focus();
			$('fxtableContainer').style.height = (getSize().h -94)+"px";
			if('<s:property value="exp.fid"/>' != '' && '<s:property value="exp.fid"/>' != null  && '<s:property value="exp.fid"/>' != '0'){
				$('imagespig').src = 'file!download.do?fid=<s:property value="exp.fid"/>';
			}
			initExp();
			window.setTimeout(initExpfl,0);
			
		}, true);
		
		
		function closeWindows(){
			window.parent.parent.refreshP();
			closeWin();
		}
		
		function initExpfl(){
			var ajax = new AppAjax("exp!getExpflList.do?rcid="+$('rcid').value,function(data){
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
			var g = '<s:property value="exp.grry"/>';
			if(g != null && g!=''){
				var gs = g.split(',');
				for(var i=0;i<gs.length;i++){
					$('exp.grry'+gs[i]).checked=true;
				}
			}
		}
		
		var select13id = '',select14id='',selectfl14id='',selectfl13id='';
		function set13WithTreeID(id){
			select13id = id;
		}
		function set14WithTreeID(id){
			select14id = id;
		}
		function setFl14WithTreeID(id){
			selectfl14id = id;
		}
		function setFl13WithTreeID(id){
			selectfl13id = id;
		}
		
		function doSave(type){
			if(COM.checkbox('exp.exptype').length == 0){
				alert('请选择专家类型!');
				return false;
			}
			if($('dis002').style.display == ''){
				if(COM.checkbox('exp.sctzhy').length == 0){
					alert('请选择擅长投资行业');
					return false;
				}
			}
			if($('exp.rcname').value.trim() == ''){
				alert('姓名不能为空！');
				return false;
			}
			if($('exp.zjno').value.trim() == ''){
				alert('证件号码不能为空');
				return false;
			}
			if($('exp.birthday').value.trim() == ''){
				alert('出生年月不能为空');
				return false;
			}
			
			if($('exp.workunit').value.trim() == ''){
				alert('工作单位不能为空');
				return false;
			}
			if($('exp.officetel').value.trim() == ''){
				alert('办公电话不能为空');
				return false;
			}
			if($('exp.ptel').value.trim() == ''){
				alert('手机不能为空');
				return false;
			}
			if(COM.checkbox('expfls').length == 0 && $('roledm').value != '01'){
				alert('请选择专家分类');
				return false;
			}
			
			
			$('uploadf').outerHTML=$('uploadf').outerHTML;
			var ajax = new AppAjax("exp!doSaveExp.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
		}
		function save_Back(data,type){
			if(data.message.code >0){
				
				$('rcid').value=data.message.text;
				parent.reload = 1;
				if(type == 2){//保存下一步
					parent.setRcid(data.message.text,2,'exp!preJszc.do',true);
				}else if(type==3){//填写完成
					parent.setRcid('',1,'exp!preExp.do',true);
				}else if(type==4){
					closeWindows();
				}else{
					alert('保存成功!');
				}
			}else{
				alert(data.message.text);
			}
		}
				
		var tree;
		function loadRcTree(lbid){
			if($("xtdlb13").loading != 1){
				tree=new dhtmlXTreeObject("xtdlb13","100%","90%",0);
				tree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
				tree.setOnClickHandler(function(id){setVByTreeZy(id);});
				tree.enableSmartXMLParsing(true);
				tree.setDataMode("json");
				tree.enableCheckBoxes(false);
				tree.loadJSON("exp!loadrcTree.do?lbid="+lbid,function(){$('xtdlb13load').style.display="none";});
				$('xtdlb13').loading = 1;			
			}else{
				if($(select13id).value == ''){
					tree.closeAllItems(0);
					tree.openItem('root');
				}else{
					tree.closeAllItems(0);
					tree.openItem(tree.getParentId($(select13id).value));
				}
				
			}
		}
		
		
		var rclbtree;
		function loadRclbTree(){
			if($("rclbtree").loading != 1){
				rclbtree=new dhtmlXTreeObject("rclbtree","100%","90%",0);
				rclbtree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
				rclbtree.setOnClickHandler(function(id){setVByRclb(id);});
				rclbtree.enableSmartXMLParsing(true);
				rclbtree.setDataMode("json");
				rclbtree.enableCheckBoxes(false);
				rclbtree.loadJSON("exp!loadRclbTree.do",function(){$('rclbtreeload').style.display="none";});
				$('rclbtree').loading = 1;			
			}
		}
		
		
		var jgtree;
		var _bid = '';
		function loadJgTree(lbid,bid){
			_bid = bid;
			if($("xtdlb10").loading != 1){
				jgtree=new dhtmlXTreeObject("xtdlb10","100%","90%",0);
				jgtree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
				jgtree.setOnClickHandler(function(id){setVByTree(id);});
				jgtree.enableSmartXMLParsing(true);
				jgtree.setDataMode("json");
				jgtree.enableCheckBoxes(false);
				jgtree.loadJSON("exp!loadrcTree.do?lbid="+lbid,function(){$('xtdlb10load').style.display="none";});
				$('xtdlb10').loading = 1;			
			}
		}
		
		var lytree;
		function loadLyTree(lbid){
			if($("xtdlb14").loading != 1){
				lytree=new dhtmlXTreeObject("xtdlb14","100%","90%",0);
				lytree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
				lytree.setOnClickHandler(function(id){setVByTreeLy(id);});
				lytree.enableSmartXMLParsing(true);
				lytree.setDataMode("json");
				lytree.enableCheckBoxes(false);
				lytree.loadJSON("exp!loadrcTree.do?lbid="+lbid,function(){$('xtdlb14load').style.display="none";});
				$('xtdlb14').loading = 1;			
			}else{
				if($(select14id).value == ''){
					lytree.closeAllItems(0);
					lytree.openItem('root');
				}else{
					lytree.closeAllItems(0);
					lytree.openItem(lytree.getParentId($(select14id).value));
				}
				
			}
		}	
		
		
		
		var fllytree;
		function loadFlLyTree(lbid,fldm){
			$('xtdlbfl14').innerHTML = '<div id="xtdlbfl14button" style="background:#4682b4;width:100%;height:25;text-align:right"><input type=\'button\' value=\'关  闭\' onclick="javascript:$(\'xtdlbfl14\').style.display=\'none\'"/>&nbsp;&nbsp;</div><iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter=\'progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)\';"></iframe>';
			if($('xtdlbfl14').style.display == 'block'){
				fllytree=new dhtmlXTreeObject("xtdlbfl14","100%","90%",0);
				fllytree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
				fllytree.setOnClickHandler(function(id){setVByTreeFlLy(id);});
				fllytree.enableSmartXMLParsing(true);
				fllytree.setDataMode("json");
				fllytree.enableCheckBoxes(false);
				fllytree.loadJSON("exp!loadrcFlTree.do?lbid="+lbid+"&fldm="+fldm);
				$('xtdlbfl14').loading = 1;
			}
		}	
		function setVByTreeFlLy(id){
			if(fllytree.hasChildren(id)==0){
				if(selectfl14id.indexOf('000000004')>0 ){
					setV4(fllytree.getItemText(id.substring(0,3))+'-'+fllytree.getItemText(id),id,selectfl14id,'xtdlbfl14');
				}else{
					setV(fllytree.getItemText(id),id,selectfl14id,'xtdlbfl14');
				}
				
			}
		}
		
		var flzytree;
		var oldfldm = ''; 
		function loadFlZyTree(lbid,fldm){
			if(fldm != oldfldm){
				$('xtdlbfl13').loading = 0;
				oldfldm = fldm;
				$('xtdlbfl13').innerHTML = '<div id="xtdlbfl13button" style="background:#4682b4;width:100%;height:25;text-align:right"><input type=\'button\' value=\'关  闭\' onclick="javascript:$(\'xtdlbfl13\').style.display=\'none\'"/>&nbsp;&nbsp;</div><iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter=\'progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)\';"></iframe>';
				
			}
			if($('xtdlbfl13').loading != 1){
				flzytree=new dhtmlXTreeObject("xtdlbfl13","100%","90%",0);
				flzytree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
				flzytree.setOnClickHandler(function(id){setVByTreeFlZy(id);});
				flzytree.enableSmartXMLParsing(true);
				flzytree.setDataMode("json");
				flzytree.enableCheckBoxes(false);
				flzytree.loadJSON("exp!loadrcFlTree.do?lbid="+lbid+"&fldm="+fldm,function(){});
				$('xtdlbfl13').loading = 1;
			}else{
				if($(selectfl13id).value == ''){
					flzytree.closeAllItems(0);
					flzytree.openItem('root');
				}else{
					flzytree.closeAllItems(0);
					flzytree.openItem(flzytree.getParentId($(selectfl13id).value));
				}
				
			}
		}	
		function setVByTreeFlZy(id){
			if(flzytree.hasChildren(id)==0){
				setV(flzytree.getItemText(id),id,selectfl13id,'xtdlbfl13');
			}
		}
		
		function setTableDisplay(obj,tableid){
			if($(tableid).style.display == 'none'){
				obj.className = "close";
				$(tableid).style.display = "";
			}else{
				obj.className = "open";
				$(tableid).style.display = "none";
			}
		}
		
		
		var lxgjxh = 1;
		function addLxgj(){
			++lxgjxh;
			$('lxgjid').innerHTML += '<input type="text" name="lxgjgjmc" style="width:200px" id="lxgjxh'+lxgjxh+'_a" value=""/><a href="javascript:" onclick="dellLxgj(\'lxgjxh'+lxgjxh+'_a\',this)">删除</a>';
		}
		
		
		//设置对应 setByTree 的 TREE 的值 
		function setVByTreeZy(id){
			if(tree.hasChildren(id)==0){
				setV(tree.getItemText(id),id,select13id,'xtdlb13');
			}
		}
		function setVByTreeLy(id){
			if(lytree.hasChildren(id)==0){
				setV(lytree.getItemText(id),id,select14id,'xtdlb14');
			}
		}
		function setVByRclb(id){
			if(rclbtree.hasChildren(id)==0){
				setV(rclbtree.getItemText(id),id,'exp.rclb','rclbtree');
			}
		}
		
		function setVByZjfl(id){
			if(zjfltree.hasChildren(id)==0){
				setV(zjfltree.getItemText(id),id,'exp.zjfl','zjfltree');
			}
		}
		
		function setVByTree(id){
			if(jgtree.hasChildren(id)==0){
				setV(jgtree.getItemText(id),id,_bid,'xtdlb10');
			}
		}
		
		function setV(n,v,id,noneid){
			$(id).value = v;
			$(id+'_button').value=n;
			noneDiv(noneid);
		}
		
		function setV4(n,v,id,noneid){
			$(id).value = v;
			if(v.substring(3,6) == '999'){
				var ss = (id.substring(6,11)).replaceAll('_','');
				$(id+'_button').innerHTML=n+'<input type=text name="exp.'+ss+'o" value="">';
			}else{
				$(id+'_button').innerHTML=n+'<input type=hidden name="exp.'+ss+'o" value="">';
			}
			noneDiv(noneid);
		}
		
		function noneDiv(id){
			$(id).style.display='none';
		}
		
		function isOptionJg(){
			if($('exp.nation').value == '001'){
			}else{
			}
		}
		
		function dellLxgj(delid,obj){
			 var _element = $(delid);
	         var _parentElement = _element.parentNode;
	         if(_parentElement){
	                _parentElement.removeChild(_element);
	         }
	         obj.style.display = 'none';
		}
		
		function uploadFile(obj){
			if($('uploadf').value.toLowerCase().indexOf('.gif') == -1 && $('uploadf').value.toLowerCase().indexOf('.jpg')==-1 && $('uploadf').value.toLowerCase().indexOf('.bmp')==-1 && $('uploadf').value.toLowerCase().indexOf('.png')==-1){
				alert('只可以上传(gif、jpg、bmp、png)图片格式！');
				return false;
			}
			document.czrcForm.action = "file!uploadfile.do?fid="+$('exp.fid').value;
			document.czrcForm.target = "uploadFrame";
			document.czrcForm.submit();
		}
		function buf(code,text,fid){
			$('uploadf').outerHTML=$('uploadf').outerHTML;
			$('exp.fid').value = fid;
			$('imagespig').src = 'file!download.do?fid='+fid;
		}
		
		function doseltrue(id){
			$(id).style.display="none";
		}
		
		function clickExptype(obj){
			if(obj.value == '002'){
				if(obj.checked){
					$('dis002').style.display='';
				}else{
					$('dis002').style.display='none';
				}
			}
		}
		
	function O_D(obj1, obj2, location) { //打开div在打开元素的什么位置，obj1:点击的元素，obj2:要打开的div;location:left,top,right,bottom
		var btn = document.getElementById(obj1);
		var obj = document.getElementById(obj2);
		var h = btn.offsetHeight;
		var w = btn.offsetWidth;
		var x = btn.offsetLeft;
		var y = btn.offsetTop;
		
		
		var bodyh = getPageSizeMe().y;
		var bodyw = getPageSizeMe().x;
		
		while (btn = btn.offsetParent) {
			y += btn.offsetTop;
			x += btn.offsetLeft;
		}
	
		var hh = obj.offsetHeight;
		var ww = obj.offsetWidth;
		var xx = obj.offsetLeft;// style.left;
		var yy = obj.offsetTop;// style.top;
	
		var obj2location = location.toLowerCase();
	
		var showx, showy;
	
		if (obj2location == "left" || obj2location == "l" || obj2location == "top"
				|| obj2location == "t" || obj2location == "u"
				|| obj2location == "b" || obj2location == "r"
				|| obj2location == "up" || obj2location == "right"
				|| obj2location == "bottom") {
			if (obj2location == "left" || obj2location == "l") {
				showx = x - ww;
				showy = y;
			}
			if (obj2location == "top" || obj2location == "t" || obj2location == "u") {
				showx = x;
				showy = y - hh;
			}
			if (obj2location == "right" || obj2location == "r") {
				showx = x + w;
				showy = y;
			}
			if (obj2location == "bottom" || obj2location == "b") {
				showx = x;
				showy = y + h;
			}
		}else if(obj2location == 'center'){
			showx=parseInt(bodyw-parseInt(obj.style.width.replaceAll('px','')))/2;
		    showy=parseInt(bodyh-parseInt(obj.style.height.replaceAll('px','')))/2;
		    
			//showx = (document.documentElement.scrollLeft+(document.documentElement.clientWidth-ww)/2);
			//showy = (document.documentElement.scrollTop+(document.documentElement.clientHeight-hh)/2);
			
		} else {
			showx = xx;
			showy = yy;
		}
		obj.style.left = showx + "px";
		obj.style.top = (showy) + "px";
		
		if (obj.style.display == "block") {
			obj.style.display = "none";
		} else {
			obj.style.display = "block";
		}
	}

	</script>
</html>
