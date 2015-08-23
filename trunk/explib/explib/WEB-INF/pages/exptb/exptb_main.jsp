<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
		<SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxcommon.js"></SCRIPT>
        <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree.js"></SCRIPT>
        <link type="text/css" href="js/dhtmlxTree/dhtmlxtree.css"rel="stylesheet" >
        <link type="text/css" href="styles/csstable.css"rel="stylesheet" />
        <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree_json.js"></SCRIPT>
        <script   type="text/javascript" src="js/dhtmlxTree/dhtmlXTreeExtend.js" ></script>
        <script language="JavaScript"src="<s:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>	
        <script type="text/javascript" src="<c:url value="/js/comm.js"/>" ></script>
        <script src="Framework/Main.js" type="text/javascript"></script>
        
		<style>
									
			th.thc {
				font: bold 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
				color: #4f6b72;
				border-right: 1px solid #C1DAD7;
				border-bottom: 1px solid #C1DAD7;
				border-top: 1px solid #C1DAD7;
				letter-spacing: 2px;
				text-transform: uppercase;
				text-align: left;
				padding: 6px 6px 6px 12px;
				background: #CAE8EA url(images/bg_header.jpg) no-repeat;
			}
			
			td.tdc {
				border-right: 1px solid #C1DAD7;
				border-bottom: 1px solid #C1DAD7;
				background: #fff;
				font-size:12px;
				padding: 6px 6px 6px 12px;
				color: #4f6b72;
			}		
			.fsg_nr{ position:absolute; display:none;top:25px;text-align:left; right:-3px; background:#fff; border:1px solid #98B1C8; }
			.fsg_nr td{ height:0px; line-height:14px;font-size:12px; color:#006699;}
		</style>
	</head>

	<body>
	<s:form name="czrcForm" action='exp!preExp.do' method="post"  enctype="multipart/form-data">
	<s:hidden name="rcidtb" id='rcidtb' ></s:hidden>
	<s:hidden name="exptb.itype" id='exptb.itype' ></s:hidden>
	<s:hidden name="exptb.fldm" id='exptb.fldm' ></s:hidden>
	<s:hidden name="rcid" id='rcid' ></s:hidden>
	<s:hidden name="pname" id='pname' ></s:hidden>
	<s:hidden name="opttype" id='opttype'></s:hidden>
	<s:hidden name="tbtype" id='tbtype'></s:hidden>
	<s:hidden name="winid" id="winid"></s:hidden>
 <!--个人信息 start-->
	
	<div id="fxtableContainer" class="tjtableContainer" style="width:100%;heigth:100%;overflow:auto;" >
    	<table id="tjtable" cellspacing="0" cellpadding="0">
    		<thead>
    		<tr>
    			<th class="thc"  style="width:20px"><input type="checkbox" id="allcheckbox" onclick="COM.checkboxAll('dmkey',this.checked)"></th>
    			<th class="thc"  style="width:130px">字段</th>
    			<th class="thc" >征集专家信息</th>
    			<th class="thc" >现有专家信息
    			&nbsp;&nbsp;&nbsp;
    			<img style="height:17px" onclick="javascript:searchExp();" src="images/skin0/searchexp.gif"/></th>
    		</tr>
    		</thead>
    		<tr>
    			<td class="tdc" ><input type="checkbox" name="dmkey" value="rcname" /></td>
    			<th class="spec">姓名</th>
    			<td class="tdc" ><s:textfield cssStyle="width:130px" name="exptb.rcname" id="exptb.rcname"/></td>
    			<td class="tdc" ><s:property value="exp.rcname"/>&nbsp;</td>
    		</tr>
			
    		<tr>
    			<td class="tdc" ><input type="checkbox" name="dmkey" value="sex" /></td>
    			<th   class="spec">性别</th>
    			<td class="tdc" ><s:select name="exptb.sex" list="xtdict4" cssStyle="width:130" listKey="dictbh" listValue="dictname" /></td>
    			<td class="tdc" ><s:property value="exp.sex_mc"/>&nbsp;</td>
    		</tr>
    		
    		<tr>
    			<td class="tdc" ><input type="checkbox" name="dmkey" value="birthday" /></td>
    			<th   class="spec">出生年月</th>
    			<td class="tdc" ><input type="text" class="Wdate" name="exptb.birthday" id="date" style="text-align:left;width:130" value="<s:property value="exptb.birthday"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1920-01-01" readonly/></td>
    			<td class="tdc" ><s:property value="exp.birthday"/>&nbsp;</td>
    		</tr>
    		<tr>
				<td class="tdc" ><input type="checkbox" name="dmkey" value="workunit" /></td>
				<th   class="spec">工作单位&nbsp</th>
				<td class="tdc" >
					<s:textfield cssStyle="width:130px" name="exptb.workunit"/>
				</td>
				<td class="tdc" ><s:property value="exp.workunit"/>&nbsp</td>
			</tr>
    		
    		<tr>
    			<td class="tdc" ><input type="checkbox" name="dmkey" value="zjlb" /></td>
    			<th   class="spec">证件类别</th>
    			<td class="tdc" >
    				<s:select name="exptb.zjlb" list="xtdict1" cssStyle="width:130" listKey="dictbh" listValue="dictname" />&nbsp
    			</td>
    			<td class="tdc" ><s:property value="exp.zjlb_mc"/>&nbsp;</td>
    		</tr>
    		
    		<tr>
    			<td class="tdc" ><input type="checkbox" name="dmkey" value="zjno" /></td>
    			<th   class="spec">证件号码</th>
    			<td class="tdc" >
    				<s:textfield name="exptb.zjno" cssStyle="width:130" />&nbsp
    			</td>
    			<td class="tdc" ><s:property value="exp.zjno"/>&nbsp;</td>
    		</tr>
    		
			<tr>
    			<td class="tdc" ><input type="checkbox" name="dmkey" value="xl" /></td>
    			<th   class="spec">学历</th>
    			<td class="tdc" >
    				<s:select name="exptb.xl" list="xtdict2" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" />&nbsp
    			</td>
    			<td class="tdc" ><s:property value="exp.xl_mc"/>&nbsp;</td>
    		</tr>
    		
    		<tr>
    			<td class="tdc" ><input type="checkbox" name="dmkey" value="xw" /></td>
    			<th   class="spec">学位</th>
    			<td class="tdc" >
    				<s:select name="exptb.xw" list="xtdict3" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" />&nbsp
    			</td>
    			<td class="tdc" ><s:property value="exp.xw_mc"/>&nbsp;</td>
    		</tr>
    		<tr>
				<td class="tdc" ><input type="checkbox" name="dmkey" value="officetel" /></td>
				<th   class="spec">联系电话&nbsp</th>
				<td class="tdc" >
					<s:textfield cssStyle="width:130px" name="exptb.officetel"/>
				</td>
				<td class="tdc" ><s:property value="exp.officetel"/>&nbsp</td>
			</tr>
			
			<tr>
				<td class="tdc" ><input type="checkbox" name="dmkey" value="ptel" /></td>
				<th   class="spec">手机&nbsp</th>
				<td class="tdc" >
					<s:textfield cssStyle="width:130px" name="exptb.ptel"/>
				</td>
				<td class="tdc" ><s:property value="exp.ptel"/>&nbsp</td>
			</tr>
    		<tr>
    			<td class="tdc" ><input type="checkbox" name="dmkey" value="zc" /></td>
    			<th   class="spec">专业技术职务</th>
    			<td class="tdc" >
    				<s:select name="exptb.zc" list="xtdict5" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" />&nbsp
    			</td>
    			<td class="tdc" ><s:property value="exp.zc_mc"/>&nbsp;</td>
    		</tr>
    		<s:if test="exptb.fldm==\"000000001\"">
    			<tr>
	    			<td class="tdc" ><input type="checkbox" name="dmkey" value="sxly_fl" /></td>
	    			<th   class="spec">所学领域</th>
	    			<td class="tdc" >
	    				<s:property value="exptb.sxly_fl_1_mc"/>&nbsp;
	    			</td>
	    			<td class="tdc" ><s:property value="exp.sxly_fl_1_mc"/>&nbsp;</td>
	    		</tr>
	    		
	    		<tr>
	    			<td class="tdc" ><input type="checkbox" name="dmkey" value="csly_fl" /></td>
	    			<th   class="spec">从事领域</th>
	    			<td class="tdc" >
	    				<s:property value="exptb.csly_fl_1_mc"/>&nbsp;
	    			</td>
	    			<td class="tdc" ><s:property value="exp.csly_fl_1_mc"/>&nbsp;</td>
	    		</tr>
	    		<tr>
	    			<td class="tdc" ><input type="checkbox" name="dmkey" value="sxly1_fl" /></td>
	    			<th   class="spec">熟悉领域1</th>
	    			<td class="tdc" >
	    				<s:property value="exptb.sxly1_fl_1_mc"/>&nbsp;
	    			</td>
	    			<td class="tdc" ><s:property value="exp.sxly1_fl_1_mc"/>&nbsp;</td>
	    		</tr>
	    		<tr>
	    			<td class="tdc" ><input type="checkbox" name="dmkey" value="sxly2_fl" /></td>
	    			<th   class="spec">熟悉领域2</th>
	    			<td class="tdc" >
	    				<s:property value="exptb.sxly2_fl_1_mc"/>&nbsp;
	    			</td>
	    			<td class="tdc" ><s:property value="exp.sxly2_fl_1_mc"/>&nbsp;</td>
	    		</tr>
	    		<tr>
	    			<td class="tdc" ><input type="checkbox" name="dmkey" value="sxly3_fl" /></td>
	    			<th   class="spec">熟悉领域3</th>
	    			<td class="tdc" >
	    				<s:property value="exptb.sxly3_fl_1_mc"/>&nbsp;
	    			</td>
	    			<td class="tdc" ><s:property value="exp.sxly3_fl_1_mc"/>&nbsp;</td>
	    		</tr>
    		</s:if>
    		<s:elseif test="exptb.fldm==\"000000002\"">
    		
    		</s:elseif>
    		<s:elseif test="exptb.fldm==\"000000003\"">
    			<tr>
	    			<td class="tdc" ><input type="checkbox" name="dmkey" value="sxzy_fl" /></td>
	    			<th   class="spec">所学专业</th>
	    			<td class="tdc" >
	    				<s:property value="exptb.sxzy_fl_mc"/>&nbsp;
	    			</td>
	    			<td class="tdc" ><s:property value="exp.sxzy_fl_mc"/>&nbsp;</td>
	    		</tr>
	    		
	    		<tr>
	    			<td class="tdc" ><input type="checkbox" name="dmkey" value="cszy_fl" /></td>
	    			<th   class="spec">从事专业</th>
	    			<td class="tdc" >
	    				<s:property value="exptb.cszy_fl_mc"/>&nbsp;
	    			</td>
	    			<td class="tdc" ><s:property value="exp.cszy_fl_mc"/>&nbsp;</td>
	    		</tr>
	    		<tr>
	    			<td class="tdc" ><input type="checkbox" name="dmkey" value="sxzy1_fl" /></td>
	    			<th   class="spec">熟悉专业1</th>
	    			<td class="tdc" >
	    				<s:property value="exptb.sxzy1_fl_mc"/>&nbsp;
	    			</td>
	    			<td class="tdc" ><s:property value="exp.sxzy1_fl_mc"/>&nbsp;</td>
	    		</tr>
	    		<tr>
	    			<td class="tdc" ><input type="checkbox" name="dmkey" value="sxzy2_fl" /></td>
	    			<th   class="spec">熟悉专业2</th>
	    			<td class="tdc" >
	    				<s:property value="exptb.sxzy2_fl_mc"/>&nbsp;
	    			</td>
	    			<td class="tdc" ><s:property value="exp.sxzy2_fl_mc"/>&nbsp;</td>
	    		</tr>
	    		<tr>
	    			<td class="tdc" ><input type="checkbox" name="dmkey" value="sxzy3_fl" /></td>
	    			<th   class="spec">熟悉专业3</th>
	    			<td class="tdc" >
	    				<s:property value="exptb.sxzy3_fl_mc"/>&nbsp;
	    			</td>
	    			<td class="tdc" ><s:property value="exp.sxzy3_fl_mc"/>&nbsp;</td>
	    		</tr>
    		</s:elseif>
    		<s:elseif test="exptb.fldm==\"000000004\"">
    			<tr>
	    			<td class="tdc" ><input type="checkbox" name="dmkey" value="sxly_fl" /></td>
	    			<th   class="spec">所学领域</th>
	    			<td class="tdc" >
	    				<s:property value="exptb.sxly_fl_4_mc"/>&nbsp;(<s:property value="exptb.sxlyo_fl_4"/>)
	    			</td>
	    			<td class="tdc" ><s:property value="exp.sxly_fl_4_mc"/>&nbsp;(<s:property value="exptb.sxlyo_fl_4"/>)</td>
	    		</tr>
	    		
	    		<tr>
	    			<td class="tdc" ><input type="checkbox" name="dmkey" value="csly_fl" /></td>
	    			<th   class="spec">从事领域</th>
	    			<td class="tdc" >
	    				<s:property value="exptb.csly_fl_4_mc"/>&nbsp;(<s:property value="exptb.cslyo_fl_4"/>)
	    			</td>
	    			<td class="tdc" ><s:property value="exp.csly_fl_4_mc"/>&nbsp;(<s:property value="exptb.cslyo_fl_4"/>)</td>
	    		</tr>
	    		<tr>
	    			<td class="tdc" ><input type="checkbox" name="dmkey" value="sxly1_fl" /></td>
	    			<th   class="spec">熟悉领域1</th>
	    			<td class="tdc" >
	    				<s:property value="exptb.sxly1_fl_4_mc"/>&nbsp;(<s:property value="exptb.sxly1o_fl_4"/>)
	    			</td>
	    			<td class="tdc" ><s:property value="exp.sxly1_fl_4_mc"/>&nbsp;(<s:property value="exptb.sxly1o_fl_4"/>)</td>
	    		</tr>
	    		<tr>
	    			<td class="tdc" ><input type="checkbox" name="dmkey" value="sxly2_fl" /></td>
	    			<th   class="spec">熟悉领域2</th>
	    			<td class="tdc" >
	    				<s:property value="exptb.sxly2_fl_4_mc"/>&nbsp;(<s:property value="exptb.sxly2o_fl_4"/>)
	    			</td>
	    			<td class="tdc" ><s:property value="exp.sxly2_fl_4_mc"/>&nbsp;(<s:property value="exptb.sxly2o_fl_4"/>)</td>
	    		</tr>
	    		<tr>
	    			<td class="tdc" ><input type="checkbox" name="dmkey" value="sxly3_fl" /></td>
	    			<th   class="spec">熟悉领域3</th>
	    			<td class="tdc" >
	    				<s:property value="exptb.sxly3_fl_4_mc"/>&nbsp;(<s:property value="exptb.sxly3o_fl_4"/>)
	    			</td>
	    			<td class="tdc" ><s:property value="exp.sxly3_fl_4_mc"/>&nbsp;(<s:property value="exptb.sxly3o_fl_4"/>)</td>
	    		</tr>
    		</s:elseif>
    		<!-- 
			<tr>
    			<td class="tdc" ><input type="checkbox" name="dmkey" value="zw" /></td>
    			<th class="spec">专业技术职务等级</th>
    			<td class="tdc" >
    				<s:select name="exptb.zw" list="xtdict23" cssStyle="width:130" listKey="dictbh" listValue="dictname"  headerKey="" headerValue="--请选择--" />&nbsp
    			</td>
    			<td class="tdc" ><s:property value="exp.zw_mc"/>&nbsp;</td>
    		</tr>
    		
			<tr>
				<td class="tdc" ><input type="checkbox" name="dmkey" value="xzzw" /></td>
				<th   class="spec">行政职务&nbsp</th>
				<td class="tdc" >
					<s:textfield cssStyle="width:200px" name="exptb.xzzw"/>
				</td>
				<td class="tdc" ><s:property value="exp.xzzw"/>&nbsp</td>
			</tr>
			 
			<tr>
    			<td class="tdc" ><input type="checkbox" name="dmkey" value="xxzy" /></td>
    			<th   class="spec">所学专业</th>
    			<td class="tdc" >
    				<INPUT id="exp.xxzy_button" class=selectBut2  title=所学专业 value="<s:property value="exptb.xxzymc"/>" type=button  onclick="O_D('exp.xxzy_button','xtdlb13','bottom');set13WithTreeID('exptb.xxzy');loadRcTree(13);">
					<s:hidden name="exptb.xxzy"></s:hidden>
    			</td>
    			<td class="tdc" ><s:property value="exp.xxzy_mc"/>&nbsp;</td>
    		</tr>
    		
    		<tr>
    			<td class="tdc" ><input type="checkbox" name="dmkey" value="cszy" /></td>
    			<th   class="spec">从事专业</th>
    			<td class="tdc" >
    				<INPUT id="exp.cszy_button" class=selectBut2  title=从事专业 value="<s:property value="exptb.cszymc"/>" type=button  onclick="O_D('exp.cszy_button','xtdlb13','bottom');set13WithTreeID('exptb.cszy');loadRcTree(13);">
					<s:hidden name="exptb.cszy"></s:hidden>
    			</td>
    			<td class="tdc" ><s:property value="exp.cszy_mc"/>&nbsp;</td>
    		</tr>
    		<tr>
    			<td class="tdc" ><input type="checkbox" name="dmkey" value="cszy" /></td>
    			<th   class="spec">熟悉专业1</th>
    			<td class="tdc" >
    				<INPUT id="exp.sxzy1_button" class=selectBut2  title=从事专业 value="<s:property value="exptb.sxzy1mc"/>" type=button  onclick="O_D('exp.sxzy1_button','xtdlb13','bottom');set13WithTreeID('exptb.sxzy1');loadRcTree(13);">
					<s:hidden name="exptb.sxzy1"></s:hidden>
    			</td>
    			<td class="tdc" ><s:property value="exp.sxzy1_mc"/>&nbsp;</td>
    		</tr>
    		<tr>
    			<td class="tdc" ><input type="checkbox" name="dmkey" value="cszy" /></td>
    			<th   class="spec">熟悉专业2</th>
    			<td class="tdc" >
    				<INPUT id="exp.sxzy2_button" class=selectBut2  title=从事专业 value="<s:property value="exptb.sxzy2mc"/>" type=button  onclick="O_D('exp.sxzy2_button','xtdlb13','bottom');set13WithTreeID('exptb.sxzy2');loadRcTree(13);">
					<s:hidden name="exptb.sxzy2"></s:hidden>
    			</td>
    			<td class="tdc" ><s:property value="exp.sxzy2_mc"/>&nbsp;</td>
    		</tr>
    		<tr>
    			<td class="tdc" ><input type="checkbox" name="dmkey" value="cszy" /></td>
    			<th   class="spec">熟悉专业3</th>
    			<td class="tdc" >
    				<INPUT id="exp.sxzy3_button" class=selectBut2  title=从事专业 value="<s:property value="exptb.sxzy3mc"/>" type=button  onclick="O_D('exp.sxzy3_button','xtdlb13','bottom');set13WithTreeID('exptb.sxzy3');loadRcTree(13);">
					<s:hidden name="exptb.sxzy3"></s:hidden>
    			</td>
    			<td class="tdc" ><s:property value="exp.sxzy3_mc"/>&nbsp;</td>
    		</tr>
    		<tr>
				<td class="tdc" ><input type="checkbox" name="dmkey" value="byxx" /></td>
				<th   class="spec">毕业学校&nbsp</th>
				<td class="tdc" >
					<s:textfield cssStyle="width:130px" name="exptb.byxx"/>
				</td>
				<td class="tdc" ><s:property value="exp.byxx"/>&nbsp</td>
			</tr>
			
			<tr>
				<td class="tdc" ><input type="checkbox" name="dmkey" value="byrq" /></td>
				<th   class="spec">毕业时间&nbsp</th>
				<td class="tdc" >
					<input type="text" class="Wdate" name="exptb.byrq" id="date" style="text-align:left;width:130" value="<s:property value="exptb.byrq"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>&nbsp
				</td>
				<td class="tdc" ><s:property value="exp.byrq"/>&nbsp</td>
			</tr>
			-->
			
			<tr>
				<td class="tdc" ><input type="checkbox" name="dmkey" value="szdq" /></td>
				<th   class="spec">所在地区&nbsp</th>
				<td class="tdc" >
    				<s:if test="exptb.szdq=='001'">
						<INPUT id="exptb.szdq_button" class="selectBut2"  title="选择城市" value="<s:property value="exptb.szdqmc"/>" type=button onclick="O_D('exptb.szdq_button','xtdlb10','bottom');loadJgTree(10);">
						<s:hidden name="exptb.szdq"></s:hidden>
						<!--<s:textfield name="exp.jg" cssStyle="width:130;" />-->
					</s:if>
					<s:else>
						<INPUT id="exptb.szdq_button" class="selectBut2"  title="选择城市" value="<s:property value="exptb.szdqmc==''?'选择城市':exptb.szdqmc"/>" type=button onclick="O_D('exptb.szdq_button','xtdlb10','bottom');loadJgTree(10);">
						<s:hidden name="exptb.szdq"></s:hidden>
						<!--<s:textfield name="exp.jg" cssStyle="width:130;background:#efefef" readonly="true" />-->
					</s:else>
    			</td>
    			
				<td class="tdc" ><s:property value="exp.szdq_mc"/>&nbsp</td>
			</tr>
			<!-- 
			<tr>
				<td class="tdc" ><input type="checkbox" name="dmkey" value="dwaddr" /></td>
				<th   class="spec">单位地址&nbsp</th>
				<td class="tdc" >
					<s:textfield cssStyle="width:130px" name="exptb.dwaddr"/>
				</td>
				<td class="tdc" ><s:property value="exp.dwaddr"/>&nbsp</td>
			</tr>
			 -->
			<tr>
				<td class="tdc" ><input type="checkbox" name="dmkey" value="jtaddr" /></td>
				<th   class="spec">地址&nbsp</th>
				<td class="tdc" >
					<s:textfield cssStyle="width:130px" name="exptb.jtaddr"/>
				</td>
				<td class="tdc" ><s:property value="exp.jtaddr"/>&nbsp</td>
			</tr>
			
			<tr>
				<td class="tdc" ><input type="checkbox" name="dmkey" value="jtcode" /></td>
				<th   class="spec">邮政编码&nbsp</th>
				<td class="tdc" >
					<s:textfield cssStyle="width:130px" name="exptb.jtcode"/>
				</td>
				<td class="tdc" ><s:property value="exp.jtcode"/>&nbsp</td>
			</tr>
			<!-- 
			<tr>
				<td class="tdc" ><input type="checkbox" name="dmkey" value="officetel" /></td>
				<th   class="spec">家庭电话&nbsp</th>
				<td class="tdc" >
					<s:textfield cssStyle="width:130px" name="exptb.officetel"/>
				</td>
				<td class="tdc" ><s:property value="exp.officetel"/>&nbsp</td>
			</tr>
			 -->
			
			
			<tr>
				<td class="tdc" ><input type="checkbox" name="dmkey" value="email" /></td>
				<th   class="spec">电子信箱&nbsp</th>
				<td class="tdc" >
					<s:textfield cssStyle="width:130px" name="exptb.email"/>
				</td>
				<td class="tdc" ><s:property value="exp.email"/>&nbsp</td>
			</tr>
			<!-- 
			<tr>
				<td class="tdc" ><input type="checkbox" name="dmkey" value="info" /></td>
				<th   class="spec">专家简介&nbsp</th>
				<td class="tdc" >
					<s:textarea name="exptb.info" cols="25" rows="6"></s:textarea>
				</td>
				<td class="tdc" ><s:textarea name="exp.info" cols="25" rows="6"></s:textarea>&nbsp</td>
			</tr>
			 -->
		</table>

	<div loading='0' id='xtdlb13' class="fsg_nr" style="display:none;width:270;height:200;overflow:auto;">
		<div id="xtdlb13load"><img src="images/skin0/other/upload.gif">数据载入中.....</div>
		<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
	</div>
	
	<div loading='0' id='xtdlb10' class="fsg_nr" style="display:none;width:270;height:200;overflow:auto;">
		<div id="xtdlb10load"><img src="images/skin0/other/upload.gif">数据载入中.....</div>
		<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
	</div>
	
	<div loading='0' id='rclbtree' class="fsg_nr" style="display:none;width:300;height:200;overflow:auto;">
		<div id="rclbtreeload"><img src="images/skin0/other/upload.gif">数据载入中.....</div>
		<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
	</div>	
	
	<div loading='0' id='xtdlb20' class="fsg_nr" style="display:none;width:360;height:200;background-color:#E7EAF7;overflow:auto;">
		<table cellpadding="3" cellspacing="3">
		<script>
       		var html = "",row = 1,islast=0;
       		<s:iterator value="xtdict20" id="xtdict20">
       			if(row != 5){
       				islast = 0;
       				if(row == 1){
       					html += '<tr><td style="width:30px">&nbsp</td>';
       				}
       				html += '<td style="width:70px"><a href="javascript:" onclick="setV(\'<s:property value="dictname"/>\',\'<s:property value="dictbh"/>\',\'exp.nation\',\'xtdlb20\');isOptionJg()"><s:property value="dictname"/></a></td>';
       				++row;
       			}else{
       				html += '<td style="width:70px"><a href="javascript:" onclick="setV(\'<s:property value="dictname"/>\',\'<s:property value="dictbh"/>\',\'exp.nation\',\'xtdlb20\');isOptionJg()"><s:property value="dictname"/></a></td>';
       				html += '</tr>';
       				islast = 1;
       				row =1;
       			}
       		</s:iterator>
       		if(islast == 0){
       			html += '</tr>';
       		}
       		document.write(html);
	    </script>
	    </table>  
	    <iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>  	
	</div>
				
	</div>
	<div class="footer" style="background:#efefef;">
	
		<input class="btn_submit1" type="button" value="审核通过" onclick="javascript:doShtg();"/>
		<s:if test="tbtype!='WB'">
			<input class="btn_submit1" type="button" value="审核退回" onclick="javascript:doShth();"/>
		</s:if>
		<input class="btn_submit1" type="button" value="同步信息" onclick="javascript:doTb();"/>
		
		<input class="btn_submit1" type="button" value="退    出" onclick="javascript:window.parent.closeWin();"/>
		
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type=checkbox value="1" name="alltbbz"/>所有数据全部同步
	</div>

<!--个人信息 以下占未用信息end --> 
<s:hidden name="exp.rclb"></s:hidden>
<s:hidden name="exp.dwcode"></s:hidden>
<s:hidden name="exp.fax"></s:hidden>
<s:hidden name="exp.zgbs"></s:hidden>
<s:hidden name="exp.xsjt"></s:hidden>
<s:hidden name="exp.hdzz"></s:hidden>
<s:hidden name="exp.ykbh"></s:hidden>
<s:hidden name="exp.sbkh"></s:hidden>
<s:hidden name="exp.jhkh"></s:hidden>
</s:form>
<iframe name="uploadFrame" height="0px" width="opx"></iframe>
</body>
	
	<script type="text/javascript">
		Event.observe(window, "load", function() {
			$('exptb.rcname').focus();
			$('fxtableContainer').style.height = (getSize().h -40)+"px";
			
		}, true);
		
		function closeWindows(){
			window.parent.parent.refreshP();
			closeWin();
		}
		
		var select13id = '';
		function set13WithTreeID(id){
			select13id = id;
		}
		
		function doTb(){
			var rcsel = COM.checkbox('dmkey');
			var allsel = COM.checkbox('alltbbz');
			
			if(rcsel.length == 0 && allsel.length == 0){
				alert('请选择需要同步的信息');
				return false;
			}
			if(window.confirm("您确定要同步信息至专家库中?")){
				var ajax = new AppAjax("exptb!doExpTbMain.do",
				function (data){
					if(data != null && data.message.code > 0){
						alert('同步成功！');
						if($('rcid').value == null || $('rcid').value == ''){
							parent.setRcid($('rcidtb').value,'','',false);
							parent.refreshMain();
						}else{
							document.location.reload();
						}
					}else{
						alert(data.message.text);
					}
				}).submitForm("czrcForm");
			}
		}
		var tree;
		function loadRcTree(lbid){
			if($("xtdlb13").loading != 1){
				tree=new dhtmlXTreeObject("xtdlb13","100%","100%",0);
				tree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
				tree.setOnClickHandler(function(id){setVByTree(id);});
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
				rclbtree=new dhtmlXTreeObject("rclbtree","100%","100%",0);
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
		function loadJgTree(lbid){
			if($("xtdlb10").loading != 1){
				jgtree=new dhtmlXTreeObject("xtdlb10","100%","100%",0);
				jgtree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
				jgtree.setOnClickHandler(function(id){setVByjgTree(id);});
				jgtree.enableSmartXMLParsing(true);
				jgtree.setDataMode("json");
				jgtree.enableCheckBoxes(false);
				jgtree.loadJSON("exp!loadrcTree.do?lbid="+lbid,function(){$('xtdlb10load').style.display="none";});
				$('xtdlb10').loading = 1;			
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
		function setVByTree(id){
			if(tree.hasChildren(id)==0){
				setV(tree.getItemText(id),id,select13id,'xtdlb13');
			}
		}
		
		function setVByRclb(id){
			if(rclbtree.hasChildren(id)==0){
				setV(rclbtree.getItemText(id),id,'exp.rclb','rclbtree');
			}
		}
		
		function setVByjgTree(id){
			if(jgtree.hasChildren(id)==0){
				setV(jgtree.getItemText(id),id,'exp.jg','xtdlb10');
			}
		}
		
		function setV(n,v,id,noneid){
			$(id).value = v;
			$(id+'_button').value=n;
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
		
	function O_D(obj1, obj2, location) { //打开div在打开元素的什么位置，obj1:点击的元素，obj2:要打开的div;location:left,top,right,bottom
		var btn = document.getElementById(obj1);
		var obj = document.getElementById(obj2);
		var h = btn.offsetHeight;
		var w = btn.offsetWidth;
		var x = btn.offsetLeft;
		var y = btn.offsetTop;
		
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
	
	function doShtg(){
		if(window.confirm('确定便审核通过？')){
			var ajax = new AppAjax("exptb!doExpshtg.do",
				function(data){
					if(data != null ){
						window.parent.parent.refreshP();
						closeWin();
					}
				}
			)
			ajax.add('tbtype',$('tbtype').value);
			ajax.add('rcidtb',$('rcidtb').value+"#"+$('exptb.itype').value);
			ajax.submit();
		}
	}
	
	var diag ;
	function doShth(){
		diag = new Dialog("exptbwindows_th");
		diag.Title = '退回';
		diag.Width = 400;
		diag.Height = 150 ;
		diag.ShowMessageRow=true;
		diag.MessageTitle = "专家审核退回";
		diag.Message = "请写明相应不符合要求的信息！";
		diag.URL = "exptb!preExpth.do?rcidtb="+$('rcidtb').value+"&winid=exptbwindows_th&tbtype";
		diag.show();
	}
	
	function searchExp(){
		diag = new Dialog("exptbwindows_Search");
		diag.Title = '查找专家';
		diag.Width = 980;
		diag.Height = getSize().h-20 ;
		diag.ShowMessageRow=true;
		diag.MessageTitle = "查找相应的专家";
		diag.Message = "请查找同一专家进行数据同步！";
		diag.URL = "exptb!preExpSearch.do?winid=exptbwindows_Search";
		diag.show();
	}
	</script>
</html>
