<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
       <%@ include file="/common/meta.jsp"%>
		<style type="text/css">
			
			  .title {	background:#B8CDE3;	color:#003770;line-height:26px;height:26px;padding-left:15px;border:1px solid #fff;font-weight:bold;text-align:left;}
			  .fxtable{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
			  .fxtable2{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
			  .fxtable2 td.cls {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;height:20px;}
		      .fxtable td {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;}
		        a.button{background:url(images/button.gif);	display:block;color:#555555;font-weight:bold;height:30px;line-height:29px;margin-bottom:14px;text-decoration:none;width:191px;}
				a:hover.button{color:#0066CC;}
		      .lens{ no-repeat 10px 8px;text-indent:30px;display:block;}
		      .btn {
		         height:22px;
		      }
		    .editor {
			    margin-top: 5px;
			    margin-bottom: 5px;
		    }
		    .fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
		    .selectBut2 {
				WIDTH: 129px; BACKGROUND: url(images/skin0/rcda/btn8.jpg) no-repeat;TEXT-ALIGN: left; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 10px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 22px; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer
			}
		</style>
		<SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxcommon.js"></SCRIPT>
		 <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree.js"></SCRIPT>
        <link type="text/css" href="js/dhtmlxTree/dhtmlxtree.css"rel="stylesheet" >
        <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree_json.js"></SCRIPT>
        <script   type="text/javascript" src="js/dhtmlxTree/dhtmlXTreeExtend.js" ></script>
	</head>
	<s:form name="emasForm" action="xtmanager!updteUser.do" method="post">
	<body >
		<s:hidden name="query.userid" id="query.userid"></s:hidden>
		<s:hidden name="winid" id="winid"></s:hidden>
	    <div id="xwincontent">
		    <table class="fxtable" cellspacing="0" cellpadding="0">
		    	
		    	
		    	<tr>
		    		<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">
		    			用户名称：
		    		</td>
		    		<td>
		    			<s:textfield cssStyle="width:200" name="query.cnname" id="query.cnname"></s:textfield>
		    			
		    		</td>
		    	</tr>
		    	<tr>
		    		<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">
		    			登录名称：
		    		</td>
		    		<td>
		    			<s:textfield cssStyle="width:200" name="query.loginname" id="query.loginname"></s:textfield>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">
		    			登录密码：
		    		</td>
		    		<td>
		    			<input type="password" name="query.password" id="query.password"  value="<s:property value="query.password"/>"/>
		    			
		    		</td>
		    	</tr>
		    	<tr>
		    		<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">
		    			所属分类：
		    		</td>
		    		<td>
		    			<INPUT id="queryfs" class="selectBut2"  title="选择分类" value="选择分类" type=button onclick="O_D('queryfs','flwhsel','bottom');loadSelFlTree();"> 
						<s:hidden name="query.userfl" id="query.userfl"></s:hidden>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">
		    			部&nbsp&nbsp&nbsp&nbsp门：
		    		</td>
		    		<td>
		    			<s:textfield cssStyle="width:200" name="query.bmmc" id="query.bmmc"></s:textfield>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">
		    			职&nbsp&nbsp&nbsp&nbsp务：
		    		</td>
		    		<td>
		    			<s:textfield cssStyle="width:200" name="query.zw" id="query.zw"></s:textfield>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">
		    			角色分配：
		    		</td>
		    		<td>
		    			<s:select id="query.roledm" name="query.roledm" cssStyle="width:200px;margin-right:4px;" list="listMap.role" listKey="ROLEDM" listValue="ROLEMC" />
		    		</td>
		    	</tr>
		    	<tr>
		    		<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">
		    			排序号
		    		</td>
		    		<td>
		    			<s:textfield name="query.px"></s:textfield>
		    		</td>
		    	</tr>
		    </table>
	    	
	   </div>
	   
	<div id='flwhsel' class="fsg_nr" style="display:none;width:300;height:210;overflow:auto;">	
		<div loading='0' id="treesel" style="width:290;height:160;overflow:auto;">
			<div id="flwhselload"><img src="images/skin0/other/upload.gif">数据载入中.....</div>
		</div>
		<div class="footer" style="background:#f5f5f5" style="width:290;height:30;">
			<input class="button3" type="button" value="确  定" onclick="doseltrue()"/>
			&nbsp&nbsp&nbsp&nbsp
		</div>
		<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
	</div>
		   
	   <div class="footer">			
	        <input type="button" class="button3" value="保  存" onclick="editUser()"/>
			<input type="button" name="resetBtn" class="button3" value="关  闭" onclick="closeWin();"/>
       </div>
	</body>
	</s:form>
	<script type="text/javascript">
		document.getElementById("query.cnname").focus();
		var bmtype = '';
		
		function editUser(){
			
			if(!document.getElementById('query.cnname').value){
				alert('请输入用户名称!');
				return;
			}
				
			if(!document.getElementById('query.loginname').value){
				alert('请输入登录名称!');
				return;
			}
				
			if(!document.getElementById('query.password').value){
				alert('请输入登录密码!');
				return;
			}
			if(!document.getElementById('query.roledm').value){
				alert('请选择角色代码!');
				return;
			}
			
			var ajax = new AppAjax("xtgl!editUser.do",save_Back);
			ajax.submitForm("emasForm");
		}
		
		function save_Back(data){
			
			if(data.message.code >0){
				alert('保存成功！');
				window.parent.refreshP();
				closeWin();	
			}else if(data.message.code == 0) {
				
				alert(data.message.text);
				
			}else{
				alert(data.message.text);
			}
		}
		
		var seltree;
		function loadSelFlTree(){
			if($("treesel").loading != 1){
				seltree=new dhtmlXTreeObject("treesel","100%","100%",0);
				seltree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
				//seltree.setOnClickHandler(function(id){setVByTree(id);});
				seltree.enableSmartXMLParsing(true);
				seltree.setDataMode("json");
				seltree.enableCheckBoxes(true);
				seltree.enableCheckBoxes(1);
				seltree.enableThreeStateCheckboxes(true);
				seltree.loadJSON("expflwh!getRcUserflwhTree.do?query.userid="+$('query.userid').value,function(){$('flwhselload').style.display="none";});
				$('treesel').loading = 1;		
			}
		}
		function doseltrue(){
			var bhs = seltree.getAllChecked();
			$("query.userfl").value = bhs;
			$('flwhsel').style.display="none";
		}
	
	</script>
</html>