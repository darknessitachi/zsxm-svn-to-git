<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
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
	<s:component template="xtitle" theme="app" value='个人日程安排'></s:component>
	<s:form name="czrcForm" action='schedule!preScheduleI.do' method="post" >
	<s:hidden name="mt.sid"></s:hidden>
	<div  id="tableContainer">
    	<table class="fxtable" cellspacing="0" cellpadding="0">
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">
					日程标题
				</td>
				<td>
					<s:textfield cssStyle="width:350px" name="mt.sbt"/>
				</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">
					开始时间
				</td>
				<td>
					<input type="text" class="Wdate" name="mt.strdate" id="strdate" style="text-align:left;width:130" value="<s:property value="mt.strdate"/>" onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>
					<s:select name="mt.strhour" list="listhour" listKey="dm" listValue="mc"/>
					<s:select name="mt.strminute" list="listminute" listKey="dm" listValue="mc"/>
				</td>
				<input type="hidden" name="mt.enddate" value=""/>
				<input type="hidden" name="mt.endhour" value=""/>
				<input type="hidden" name="mt.endminute" value=""/>
			</tr>

			<!-- 
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">
					结束时间
				</td>
				<td>
					<input type="text" class="Wdate" name="mt.enddate" id="enddate" style="text-align:left;width:130" value='<s:property value="mt.enddate"/>' onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>
					<s:select name="mt.endhour" list="listhour" listKey="dm" listValue="mc"/>
					<s:select name="mt.endminute" list="listminute" listKey="dm" listValue="mc"/>
				</td>
			</tr>
			 -->
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">
					&nbsp
				</td>
				<td>
					<s:if test="mt.tsbz==1">
						<input type="checkbox" value="1" name="mt.tsbz" checked>
					</s:if>
					<s:else>
					<input type="checkbox" value="1" name="mt.tsbz">
					</s:else>
					提醒:&nbsp&nbsp
					提前：
					<select name="mt.remindtime">
						<s:if test="mt.remindtime=='0.000000'">
							<option value="0.000000" selected>0分钟</option>
						</s:if>
						<s:else>
							<option value="0.000000">0分钟</option>
						</s:else>
						<s:if test="mt.remindtime=='0.003470'">
							<option value="0.003470" selected>5分钟</option>
						</s:if>
						<s:else>
							<option value="0.003470">5分钟</option>
						</s:else>
						<s:if test="mt.remindtime=='0.006940'">
							<option value="0.006940"selected>10分钟</option>
						</s:if>
						<s:else>
							<option value="0.006940">10分钟</option>
						</s:else>
						<s:if test="mt.remindtime=='0.010410'">
							<option value="0.010410" selected>15分钟</option>
						</s:if>
						<s:else>
							<option value="0.010410">15分钟</option>
						</s:else>
						<s:if test="mt.remindtime=='0.013890'">
							<option value="0.013890" selected>20分钟</option>
						</s:if>
						<s:else>
							<option value="0.013890">20分钟</option>
						</s:else>
						<s:if test="mt.remindtime=='0.017360'">
							<option value="0.017360" selected>25分钟</option>
						</s:if>
						<s:else>
							<option value="0.017360">25分钟</option>
						</s:else>
						<s:if test="mt.remindtime=='0.020830'">
							<option value="0.020830" selected>30分钟</option>
						</s:if>
						<s:else>
							<option value="0.020830">30分钟</option>
						</s:else>
						<s:if test="mt.remindtime=='0.024310'">
							<option value="0.024310" selected>35分钟</option>
						</s:if>
						<s:else>
							<option value="0.024310">35分钟</option>
						</s:else>
						<s:if test="mt.remindtime=='0.027780'">
							<option value="0.027780" selected>40分钟</option>
						</s:if>
						<s:else>
							<option value="0.027780">40分钟</option>
						</s:else>
						
						<s:if test="mt.remindtime=='0.031250'">
							<option value="0.031250"selected>45分钟</option>
						</s:if>
						<s:else>
							<option value="0.031250">45分钟</option>
						</s:else>
						<s:if test="mt.remindtime=='0.034720'">
							<option value="0.034720"selected>50分钟</option>
						</s:if>
						<s:else>
							<option value="0.034720">50分钟</option>
						</s:else>
						
						<s:if test="mt.remindtime=='0.038190'">
							<option value="0.038190"selected>55分钟</option>
						</s:if>
						<s:else>
							<option value="0.038190">55分钟</option>
						</s:else>
						<s:if test="mt.remindtime=='0.041670'">
							<option value="0.041670"selected>1小时</option>
						</s:if>
						<s:else>
							<option value="0.041670">1小时</option>
						</s:else>
						<s:if test="mt.remindtime=='0.062500'">
							<option value="0.062500"selected>1.5小时</option>
						</s:if>
						<s:else>
							<option value="0.062500">1.5小时</option>
						</s:else>
						<s:if test="mt.remindtime=='0.083300'">
							<option value="0.083300" selected>2小时</option>
						</s:if>
						<s:else>
							<option value="0.083300">2小时</option>
						</s:else>
						<s:if test="mt.remindtime=='0.104200'">
							<option value="0.104200" selected>2.5小时</option>
						</s:if>
						<s:else>
							<option value="0.104200">2.5小时</option>
						</s:else>
						<s:if test="mt.remindtime=='0.125000'">
							<option value="0.125000" selected>3小时</option>
						</s:if>
						<s:else>
							<option value="0.125000">3小时</option>
						</s:else>
						<s:if test="mt.remindtime=='0.145800'">
							<option value="0.145800" selected>3.5小时</option>
						</s:if>
						<s:else>
							<option value="0.145800">3.5小时</option>
						</s:else>
						<s:if test="mt.remindtime=='0.145800'">
							<option value="0.145800" selected>3.5小时</option>
						</s:if>
						<s:else>
							<option value="0.145800">3.5小时</option>
						</s:else>
						
						
						<option value="0.166700" <s:if test="mt.remindtime=='0.166700'">selected</s:if>>4小时</option>
						<option value="0.187500" <s:if test="mt.remindtime=='0.187500'">selected</s:if>>4.5小时</option>
						<option value="0.208330" <s:if test="mt.remindtime=='0.208330'">selected</s:if>>5小时</option>
						<option value="0.229170" <s:if test="mt.remindtime=='0.229170'">selected</s:if>>5.5小时</option>
						<option value="0.250000" <s:if test="mt.remindtime=='0.250000'">selected</s:if>>6小时</option>
						<option value="0.270830" <s:if test="mt.remindtime=='0.270830'">selected</s:if>>6.5小时</option>
						<option value="0.291670" <s:if test="mt.remindtime=='0.291670'">selected</s:if>>7小时</option>
						<option value="0.312500" <s:if test="mt.remindtime=='0.312500'">selected</s:if>>7.5小时</option>
						<option value="0.333330" <s:if test="mt.remindtime=='0.333330'">selected</s:if>>8小时</option>
						<option value="0.354170" <s:if test="mt.remindtime=='0.354170'">selected</s:if>>8.5小时</option>
						<option value="0.375000" <s:if test="mt.remindtime=='0.375000'">selected</s:if>>9小时</option>
						<option value="0.395830" <s:if test="mt.remindtime=='0.395830'">selected</s:if>>9.5小时</option>
						<option value="0.416670" <s:if test="mt.remindtime=='0.416670'">selected</s:if>>10小时</option>
					</select>
				</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:100px">
					日程内容
				</td>
				<td>
					<s:textarea name="mt.snr" cols="46" rows="6"></s:textarea>
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

	<script type="text/javascript">
		// $('xwincontent').style.height = (getSize().h - 105)+"px"; 
		var reload = 0;
		function closeWindows(){closeWin(self.name);}
		function closeWin(sid){
			if(reload == 1){
				getOpener().refresh();
			}
			parent.closeXwin(sid);
		}
		
		function doSave(type){
			if($('mt.sbt').value.trim()==''){
				alert('日程标题不能为空！');
				return false;
			}
			
			$('mt.enddate').value=$('mt.strdate').value;
			$('mt.endhour').value=$('mt.strhour').value;
			$('mt.endminute').value=$('mt.strminute').value;
			if(type == 3){
				var ajax = new AppAjax("schedule!doScheduleB.do",function(data){save_Back(data,type)}).submitForm("czrcForm");
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

