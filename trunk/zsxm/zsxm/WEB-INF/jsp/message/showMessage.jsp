<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<script type='text/javascript' src='dwr/engine.js'></script>
    	<script type='text/javascript' src='dwr/interface/dwrDeclarePush.js'> </script>
  		<script type='text/javascript' src='dwr/util.js'> </script>
		<script type="text/javascript" src="<c:url value="/js/kindeditor/kindeditor.js"/>" ></script>
		<style>
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
		.fsg_nr{width:190px; position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
		</style>
	</head>
<script type="text/javascript">
var type = '${query.type}';
	if(type == '1'){
	    KE.show({
	        id : 'content1',
	        cssPath : './index.css',
	        items : [
	        'undo', 'redo', 'fontname', 'fontsize', 'textcolor', 'bgcolor', 'bold', 'italic', 'underline',
	        'removeformat', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
	        'insertunorderedlist']
	    });
    }

  </script>

<s:form name="emasForm"  method="post">
    <s:component template="xtitle" theme="app" value="消息查看"></s:component>
	<body>
	 <s:hidden name="query.mnr" id="nr"></s:hidden>
	  <s:hidden name="query.userdm" id="userdm"></s:hidden>
	  <s:hidden name="query.huifu" id="tixin"></s:hidden>
	  
	 <input type="hidden" id="messageid" name="query.strid" value="${listMap.MID}">
	 <input type="hidden" id="mtype" value="${query.type}">
	       <div  style="display:block;border: 1px solid #C1DAD7;" id="fxTjDiv">
	    	<div class="title">
	    		<span style='margin-left:0px;float:left'>
	    			 <div id="mtitledivid"> 标&nbsp;&nbsp;题: ${listMap.MTITLE}&nbsp; </div>
	    			<input type="hidden" name="query.mtitle" id="mtitle" value="${listMap.MTITLE}">
          		</span>
          		
	    	</div>
	    	 <div class="title">
	    		<span style='margin-left:0px;float:left'>
					<c:choose>
						<c:when test="${query.type == 1}">
							发件人: 
							${listMap.MFROMNAME}&nbsp;</td>
							<input type="hidden" name="query.usermcs" id="usermcsid" value="${listMap.MFROMNAME}">
						    <input type="hidden" name="query.userdms"   id="userdms" value="${listMap.MFROMDM}">
						    <input type="hidden" name="query.mfromdm"  id="mfromdmid" value="${listMap.MTODM}">
						    <input type="hidden" name="query.mfromname" id="mfromnameid" value="${listMap.MTONAME}">
						</c:when> 
	                    <c:otherwise>
	                    	收件人
							${listMap.MTONAME}&nbsp;
	                    </c:otherwise>
                    </c:choose>
          		</span>
	    	</div>
	    	  <div class="title">
	    		<span style='margin-left:0px;float:left'>
	    			 <div id="mrqdivid"> 时&nbsp;&nbsp;间: ${listMap.MRQ}&nbsp;</div>
          		</span>
          		<span id="fj_span" style='margin-left:0px;'></span>
	    	 </div>
	    </div>
	    <div style="height:250px;width:100%;overflow:auto;" id="mnrdivid">
	        			${listMap.MNR}
	     </div>
	 
	    <div class="editor" >
      		<textarea id="content1" name="content" style="width:100%;height:150px;visibility:hidden;">
			</textarea>
    	</div>

        <div class="footer">
	        <c:if test="${query.type ==1}">
	             
	             <input type="button" name="nextBtn" class="btn"  id="nextButton" value="下一条" onclick="getNextMessage()"/>
	        	 
	        	 <input type="button" name="saveBtn" class="btn"  value="回  复" onclick="saveMessage()"/>
	        </c:if>	
           	<input type="button" name="saveBtn" class="btn"  value="删  除" onclick="deleteMessage()"/>
          	<input type="button" name="resetBtn" class="btn" value="关  闭" onclick="closeWin(self.name);"/>
        </div>
	</body>
			 <div class="fsg_nr" style="width:220px;" id="sel_div">
					<div id="fj_div"  style="width:210px;height:140px; overflow:auto; overflow-x:hidden;">
						
					</div>
			</div>
</s:form>
	<form id="form" method="post">
		  <input type="hidden" name="filename"/>
		  <input type="hidden" name="flag"/>
		  <input type="hidden" name="xxid"/>
		   <input type="hidden" name="fjid"/>
		   <input type="hidden" name="type" value="xx"/>
		</form>		
<script type="text/javascript">
	//set_win_div_h("b_div");
	var tixin = document.getElementById("tixin").value;
	var type = document.getElementById("mtype").value;
	var messageid =document.getElementById('messageid').value;
	
	if('<s:property value="listMap.fj" />'!="0"){
		$("fj_span").update('<a href="#" id="_fja" onclick=showFj("'+document.getElementById('messageid').value+'")><font color="#2f4f4f">查看附件</font></a>&nbsp&nbsp&nbsp');
	}
	function showFj(xxid){
			var ajax = new AppAjax("message!showFj.do?parMap.xxid="+xxid,show_callback);
				ajax.setBlock(false);
				ajax.setMessage(false);
				ajax.submit();	
		}
		
		function show_callback(data){
			var str = new StringBuffer();
			str.append("<table width='100%'>");
			for(var i=0;i<data.fjList.length;i++){
			var d = data.fjList[i];
			str.append("<tr>");
				var url ="upload/" + d.FJ;  
				str.append("<td style='border-bottom:1px dashed #C3C3C7;'>").append(d.FJMC).append("</td>");
				str.append("<td style='border-bottom:1px dashed #C3C3C7;'>").append("<a href=javascript:downFile('"+d.FJ+"','"+document.getElementById('messageid').value+"','"+d.FJXH+"')>下载").append("</td>");
			str.append("</tr>");
			}
			str.append('</table>');
			
			$("fj_div").update(str.toString());
			O_D('_fja','sel_div','bottom');
		}
		
		function downFile(fileName,xxid,fjid){
		   $("filename").value = encodeURIComponent(fileName);
		   $("xxid").value = xxid;
		   $("fjid").value = fjid;
		   $("form").action="${ctx}/servlet/DownLoadServlet";
		   $("form").submit();
 	}		
	function saveMessage(){
		if(messageid){
			
	
			$('nr').value = KE.g['content1'].iframeDoc.body.innerHTML;
		  if(!$('nr').value ){
				alert('请输入短信回复内容！');
				return ;
			}
			
			var ajax = new AppAjax("message!saveMessage.do",save_Back);
			ajax.setAsyn(false);
			ajax.submitForm("emasForm");
		}
	}
	function save_Back(data){
		if(data.message.code >0){
		
			var obj = {mid:data.message.text,mtitle:'回复：'+$('mtitle').value};
			dwrDeclarePush.getMessage($('userdms').value,obj);
			alert("消息发送成功！");
			if(!tixin){
				parent.mainFrameContainer.document.all.b_message.onclick();
			}
			closeWin(self.name);
		}else{
			alert(data.message.text);
		}
	}
	
	function deleteMessage(){
		if(confirm("确定要删除这条短信吗！"))
  		{
			var ajax = new AppAjax("message!deleteMessage.do",operatecall_Back);
			ajax.add("query.strid",messageid);
			ajax.add("query.userdm",document.getElementById("userdm").value);
			ajax.setAsyn(false);
			ajax.submit();
		}	
	}
	function operatecall_Back(data){
	if(data.message != null ){
				if(data.message.code > 0){
					if(type == '1'){
						if(!tixin){
							parent.mainFrameContainer.document.all.b_message.onclick();
						}
					}else{
						if(!tixin){
							parent.mainFrameContainer.document.all.b_sendmessage.onclick();
						}
					}
				    alert("信息删除成功！");
				    closeWin(self.name);
				}else{
					alert(data.message.text);
				}
			}
		}
		
	function getNextMessage(){
		var ajax = new AppAjax("message!getNextMessage.do",getNextMessage_callback);
		ajax.add("query.userdm",document.getElementById("userdm").value);
		ajax.submit();
	}
	
	function getNextMessage_callback(data){

		if(data.MID){
			document.getElementById("mfromdmid").value = data.MFROMDM;
			document.getElementById("mfromnameid").value = data.MFROMNAME;
			document.getElementById("mtitle").value = data.MTITLE;
			document.getElementById("mtitledivid").innerHTML = '标&nbsp;&nbsp;题:'+data.MTITLE;
			document.getElementById("mnrdivid").innerHTML = data.MNR;
			document.getElementById("mrqdivid").innerHTML = '时&nbsp;&nbsp;间:'+data.MRQ;

		}
		if(!data.msize || data.msize == 0){
			document.getElementById("nextButton").disabled = true;
		}
	}
	
	
	
var ajax = new AppAjax("message!getMessageSize.do",getSizeMessage_callback);
		ajax.add("query.userdm",document.getElementById("userdm").value);
		ajax.submit();

function getSizeMessage_callback(data)
{
	if(data == 0)
		document.getElementById("nextButton").disabled = true;

}
		
</script>
</html>

