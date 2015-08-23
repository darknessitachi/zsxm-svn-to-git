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
		    
		/* 正文块 */
		.blkContainerPblk{padding:20px 0 0; overflow:auto; zoom:1; margin:0 1px;padding-top:5px;background-color:#F5F8FD}
		.blkContainerSblk{padding:0 34px 20px; overflow:hidden; zoom:1; width:100%;}
		 
		/* 正文标题 */
		.blkContainerSblk h1{height:35px; line-height:35px; overflow:hidden; text-align:center; font-family:"黑体"; font-size:20px; font-weight:normal; color:#03005C;}
		 
		.artInfo{padding-top:5px; overflow:hidden; line-height:14px; text-align:center; font-family:Arial, Helvetica, sans-serif;}
		.artInfo a,.artInfo a:visited{text-decoration:none;}
		.artInfo a:hover,.artInfo a:active{text-decoration:underline;}
		 
		/* 正文内容 */
		.blkContainerSblkCon{margin-top:15px; line-height:164.28%; font-size:14px;}
		
		.fsg_nr{width:190px; position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }
			.sgt_on{width:190px; height:20px; border-bottom:1px solid #eee; padding:2px 5px; color:#000; background:#F2F6FB;text-align:left;}
			.tac{text-align:right;}.p5{padding:5px;}
			.gbs1{border:1px solid #e2e2e2; float:right;}
			.c{clear:both;}
		
		</style>
	</head>
	<s:form name="emasForm" method="post">
	<body >
		<s:hidden name="parMap.xxid"></s:hidden>
		<s:hidden name="winid" id="winid"></s:hidden>
		
		<div class="blkContainerPblk" id="content_div">    
			<div class="blkContainerSblk">
				<h1 id="artibodyTitle" ><s:property value="parMap.xxbt"/> </h1>
				<div class="artInfo"><span id="art_source"><s:property value="parMap.fbrname"/></span>&nbsp;&nbsp;<span id="pub_date"><s:property value="parMap.rq"/></span>&nbsp;&nbsp;<span id="fj_span"></span></div>
				<div class="blkContainerSblkCon" >
					
					<!-- 内容模块：单图 begin -->
					<div id="artibody">
 					
					</div>
					<!-- 内容模块：单图 end -->

	   	   	
	   	</div>
		</div>
		</div>
	   <div id="hiddenDiv" style="display: none">
						<TEXTAREA id="d_area">
						<s:property value="parMap.fbxx" />
					</TEXTAREA>
					</div>
		
	   <div class="footer">
        	
        	<input type="button" name="resetBtn" class="btn" value="关  闭" onclick="closeWin();"/>
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
		</form>
	<script type="text/javascript">
		set_win_div_h("content_div");
		$("artibody").innerHTML = $("d_area").value;
		if('<s:property value="parMap.fj" />'!="0"){
			$("fj_span").update('<a href="#" id="_fja" onclick=showFj("<s:property value="parMap.xxid" />")>查看附件</a>');
		}
		
		function showFj(xxid){
			var ajax = new AppAjax("xxgl!showFj.do",show_callback);
				ajax.add("parMap.xxid",xxid);
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
				str.append("<td style='border-bottom:1px dashed #C3C3C7;'>").append("<a href=javascript:downFile('"+d.FJ+"','"+$("parMap.xxid").value+"','"+d.FJXH+"')>下载").append("</td>");
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
	</script>
</html>