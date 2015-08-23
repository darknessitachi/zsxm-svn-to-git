<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
       <%@ include file="/common/meta.jsp"%>
       <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxcommon.js"></SCRIPT>
       <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree.js"></SCRIPT>
        <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree_json.js"></SCRIPT>
        <script   type="text/javascript" src="js/dhtmlxTree/dhtmlXTreeExtend.js" ></script>
       <link type="text/css" href="js/dhtmlxTree/dhtmlxtree.css"rel="stylesheet" >
		<style type="text/css">
			.tree { float: left;width:200px;border:2px solid #A7C5E2;}
			.title {background:#B8CDE3;	color:#003770;line-height:26px;height:26px;padding-left:15px;border:1px solid #fff;font-weight:bold;text-align:left;}
		</style>
	</head>
	<s:form>
	<body >
		<s:hidden name="winid"id="winid"></s:hidden>
	    <div id="xwincontent">
	    	<div class="title">
		    	<span style='margin-left:0px;float:left'>用户名称：&nbsp;&nbsp;${username}</span>
	    	</div>
			<div id="treeBox" style="overflow:auto;"></div>
	   </div>
	   <div class="footer">
        	<input type="button" class="button3" value="确   定" onclick="saveFun()"/>
        	<input type="button" name="resetBtn" class="button3" value="关   闭" onclick="closeWin();"/>
       </div>
	</body>
	</s:form>
	<script type="text/javascript">
		loadTree();

		var tree;

		function loadTree(){
			tree=new dhtmlXTreeObject("treeBox","97%",(getSize().h - 90 +"px"),0);
			tree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
			tree.enableCheckBoxes(true);
			tree.setDataMode("json");
			tree.enableThreeStateCheckboxes(true);
			tree.loadJSON("quto!loadMenu.do?userid=${userid}",function(){});
		}	

		function saveFun(){
			 var s ,dms ='',names='';
			 s = tree.getAllCheckedBranches().split(",");
			
			 var len = s.length;
			 for(var i=0;i<len;i++){
			 	if(s[i] != "" && s[i]!="root" && s[i]!="#1#" && s[i]!="#2#"){
			 		dms +=',' + s[i];
			 	}
			 }
			dms = dms.substr(1);
			if(dms == "")
				return;
			var ajax = new AppAjax("quto!saveMenu.do?userid=${userid}",function(data){
               if(data){
                  if(data.code == "-1"){
                     alert(data.text);
                     return;
                  }else{
                	 alert(data.text);
                	 closeWin();
                  }
               }
			});
			ajax.add("menuIds",dms);
			ajax.submit();
		}
	</script>
</html>