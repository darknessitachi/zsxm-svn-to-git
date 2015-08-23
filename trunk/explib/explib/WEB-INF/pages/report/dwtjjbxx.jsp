<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
	 <link type="text/css" href="<s:url value="/styles/cv_rc.css"/>" rel="stylesheet" />
		<%@ include file="/common/meta.jsp"%>
	

<style>
			  .fxtable{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
			  .fxtable2{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
			  .fxtable2 td.cls {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;}
		      .fxtable td {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;}
.selectBut2 {
				WIDTH: 129px; BACKGROUND: url(images/skin0/rcda/btn8.jpg) no-repeat;TEXT-ALIGN: left; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 10px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 22px; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer
			}
	
</style>		
	</head>
	
	<body style="margin: 0px; margin: 2px;">
		<s:form name="czrcForm">
			<s:hidden name="winid" id="winid"></s:hidden>
			<div  class="tableContainer" id="queryDiv" style="float:left;width:100%;border: 1px solid #C1DAD7;" >
				<table class="fxtable" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td colspan=2 style="text-align:center;background:#add8e6;font:bold 12px 'lucida Grande',Verdana;">请选择需要统计字段</td>
					</tr>
				   	
					<tr>
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">横向</td>
						<td>
							<s:select name="xf" list="dmmcs" cssStyle="width:130" listKey="dm" listValue="mc"  headerKey="" headerValue="--请选择--" />
						</td>
						
					</tr>
					
					<tr>
						<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">纵向</td>
						<td>
							<s:select name="yf" list="dmmcs" cssStyle="width:130" listKey="dm" listValue="mc"  headerKey="" headerValue="--请选择--" />
						</td>
						
					</tr>
					
				</table>
			</div>
			<div class="footer" style="background:#f5f5f5;">
				<input type="button"  class="button" value="统   计" onclick="tjV()"/>
					&nbsp&nbsp
				<input type="button"  class="button" value="退   出" onclick="javascript:closeWin();"/>
				&nbsp&nbsp
			</div>
	</s:form>	   
		 
	</body>
	<script type="text/javascript">
	//$('queryDiv').style.height = (getSize().h - 20) + "px";
	function tjV(){
		if($('xf').value==''){
			alert('请选择横向统计数据');
			return false;
		}
		if($('yf').value==''){
			alert('请选择纵向统计数据');
			return false;
		}
		if($('xf').value==$('yf').value){
			alert("横向、纵向统计数据不能相同！");
			return false;
		}
		window.parent.mainframe.mainframe.doDwtj($('xf').value,$('yf').value);
		closeWin();
	}
	
</script>
</html>

