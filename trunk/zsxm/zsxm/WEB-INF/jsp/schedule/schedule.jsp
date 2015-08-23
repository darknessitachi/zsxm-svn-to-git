<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/meta.jsp"%>
		<script type="text/javascript" src="<c:url value="/js/calendar.js"/>" ></script>
<style>
  .fxtable{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
  .fxtable2{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
  .fxtable2 td.cls {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;}
     .fxtable td {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;}
       a.button{background:url(images/button.gif);	display:block;color:#555555;font-weight:bold;height:30px;line-height:29px;margin-bottom:14px;text-decoration:none;width:191px;}
	a:hover.button{color:#0066CC;}
     .lens{ no-repeat 10px 8px;text-indent:30px;display:block;}
     
    .menu{
		background:url(images/skin0/other/topnavbg.gif);
	}
	#topnav{float:right;
	padding-right:50px;
	}
	#topnav_l{float:left;
		padding-left:30px;
	}
	#topnav a{
	padding:5px 9px 3px 9px;
	height:20px;
	border-right:1px solid #fff;
	color:#fff;
	TEXT-DECORATION: none;
	}
	
	#topnav a:hover{
		background:url(images/skin0/other/topnav_bg_current.gif);
		text-decoration : none;
		color:#fff;
	}
	
	.topnavcurrent{
		background:url(images/skin0/other/topnav_bg_current.gif);
		color:#fff;
		background-color:#B2CBE2;
	}
	#toptool{
	float:right;
	padding-top:3px;
	}
	#toptool a{
	padding:4px 8px;
	color:#fff;
	text-decoration : none;
}

</style>
	</head>

	<body>
	<s:form name="zsxmForm" action='schedule.do' method="post" >
	<input type="hidden" name="today" id="today" value='<s:property value="xtuser.logindate"/>'/>
	<div id="maint" style="folat:left;">
	<div class="title " style="folat:left;width:100%">
	
			<table width="100%" cellpadding="0" cellspacing="0">

			<tr>
				<td width="100%" class="menu">
					<div id="topnav_l">
						<input type="button" class="button3" value="新增日程" onclick="addrc()">
					</div>
					<div id="topnav">
						<a id="rst" href="#" onClick="ShowChildMenu(0)" class="topnavcurrent">日 视 图</a>
						<a id="zst" href="#" onclick="ShowChildMenu(1)">周 视 图</a>
						<a id="yst" href="#" onClick="ShowChildMenu(2)">月 视 图</a>
						<a id="yst" href="#" onClick="displaySt()">左右视图</a>
					</div>
				</td>
			</tr>
			
		</table>
	</div>
	
	<div  id="tableContainerrst" style="overflow:auto;width:100%">
    	<table class="fxtable" cellspacing="0" cellpadding="0">
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:50px">8:00</td>
				<td id='t08'>
				&nbsp
				</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:50px">9:00</td>
				<td id='t09'>&nbsp</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:50px">10:00</td>
				<td id='t10'>&nbsp</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:50px">11:00</td>
				<td id='t11'>&nbsp</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:50px">12:00</td>
				<td id='t12'>&nbsp</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:50px">13:00</td>
				<td id='t13'>&nbsp</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:50px">14:00</td>
				<td id='t14'>&nbsp</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:50px">15:00</td>
				<td id='t15'>&nbsp</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:50px">16:00</td>
				<td id='t16'>&nbsp</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:50px">17:00</td>
				<td id='t17'>&nbsp</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:50px">18:00</td>
				<td id='t18'>&nbsp</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:50px">19:00</td>
				<td id='t19'>&nbsp</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:50px">20:00</td>
				<td id='t20'>&nbsp</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:50px">21:00</td>
				<td id='t21'>&nbsp</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:50px">22:00</td>
				<td id='t22'>&nbsp</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:50px">23:00</td>
				<td id='t23'>&nbsp</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:50px">0:00</td>
				<td id='t00'>&nbsp</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:50px">1:00</td>
				<td id='t01'>&nbsp</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:50px">2:00</td>
				<td id='t02'>&nbsp</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:50px">3:00</td>
				<td id='t03'>&nbsp</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:50px">4:00</td>
				<td id='t04'>&nbsp</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:50px">5:00</td>
				<td id='t05'>&nbsp</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:50px">6:00</td>
				<td id='t06'>&nbsp</td>
			</tr>
			<tr>
				<td style="text-align:right;background:#E7F2FC;font:bold 12px 'lucida Grande',Verdana;width:50px">7:00</td>
				<td id='t07'>&nbsp</td>
			</tr>
		</table>
	</div>
	<div  id="tableContainerzst" style="overflow:auto;width:100%;display:none">
    	
    </div>	
    <div  id="tableContaineryst" style="overflow:auto;width:100%;display:none">
    	
    </div>	
	</div>
	<div id="rightst" style="float:right;width:38%;">
	<script>
		var CalendarWebControl = new atCalendarControl();
		CalendarWebControl.show(this,'<s:property value="xtuser.logindate"/>');
	</script>
	</div>
	</s:form>
	</body>

	<script type="text/javascript">
		
		var curst = 1 ;//当前视图
		$('maint').style.width = (getSize().w - 260)+"px";
		$('tableContainerrst').style.height = (getSize().h - 50)+"px"; 
		$('tableContainerzst').style.height = (getSize().h - 50)+"px";
		$('tableContaineryst').style.height = (getSize().h - 50)+"px";
		
		
		getCurSchedule();
		function getCurSchedule(){
			for(var i=0;i<24;i++){
				if(i<10){
					$('t0'+i).innerHTML = '&nbsp';
				}else{
					$('t'+i).innerHTML = '&nbsp';
				}
			}
			var ajax = new AppAjax("schedule!getScheduleByDay.do?day="+$('today').value,sch_Back).submit();
		}
		
		function sch_Back(data){
			if(data != null && data.info != null){
				var len = data.info.length;
				for(var i=0;i<len;i++){
					$('t'+data.info[i].strhour).innerHTML += (data.info[i].strminute)+'m:<a href="javascript:;" onclick="preS('+data.info[i].sid+')">'+data.info[i].sbt+'</a>&nbsp&nbsp&nbsp';
				}
			}
		}
		
		function getSchedule(v){
			$('today').value=v;
			if(curst ==1){
				getCurSchedule();
			}else if(curst ==2){
				getWeekByDay();
			}else if(curst ==3){
				getMonthByDay();
			}
		}
		
		function refresh(){
			if(curst ==1){
				getCurSchedule();
			}else if(curst ==2){
				getWeekByDay();
			}else if(curst ==3){
				getMonthByDay();
			}		
		}
		
		function addrc(){
			openWin("schedule!preScheduleI.do","600","350");
		}
		
		function doSave(type){
			if($('zsxm.xmmc').value.trim() == ''){
				alert('项目名称不能为空！');
				return false;
			}
			
			if($('zsxm.xmjdgs').value==''){
				alert('请选择相应的项目进度概述');
				return false;
			}
			
			var ajax = new AppAjax("zsxm!doSaveZsxm.do",function(data){save_Back(data,type)}).submitForm("zsxmForm");
		}
		
		function save_Back(data,type){
			if(data.message.code >0){
				$('xmid').value=data.message.text;
				if(type == 3){//填写完毕
					$('xmid').value = '';
					document.all.zsxmForm.submit();
				}else{
					alert('保存成功!');
				}
			}else{
				alert(data.message.text);
			}
		}
		
	function displaySt(){
		if($('rightst').style.display=='none'){
			$('rightst').style.display='';
			$('maint').style.width = (getSize().w- 260 )+"px";
		}else{
			$('rightst').style.display='none';
			$('maint').style.width = (getSize().w )+"px";
		}
	}	
		
	function ShowChildMenu(v){
		document.getElementById("rst").className="";
		document.getElementById("zst").className="";
		document.getElementById("yst").className="";
		document.getElementById("tableContainerrst").style.display='none';
		document.getElementById("tableContainerzst").style.display='none';
		document.getElementById("tableContaineryst").style.display='none';
		switch(v){
			case 0:
				document.getElementById("rst").className="topnavcurrent";
				document.getElementById("tableContainerrst").style.display='';
				curst = 1;
				getCurSchedule();
				break;
			case 1:
				document.getElementById("zst").className="topnavcurrent";
				document.getElementById("tableContainerzst").style.display='';
				curst = 2;
				getWeekByDay();
				break;
			case 2:
				document.getElementById("yst").className="topnavcurrent";
				document.getElementById("tableContaineryst").style.display='';
				curst = 3;
				getMonthByDay();
				break;
		}
		
	}
	
	function getWeekByDay(){
		var ajax = new AppAjax("schedule!getWeekByDay.do?day="+$('today').value,getWeek_Back).submit();
	}
	
	function getWeek_Back(data){
		if(data !=null && data.week!=null){
			var str = new StringBuffer();
			str.append('<table class="fxtable" cellspacing="0" cellpadding="0">');
			str.append('<tr><td style="text-align:left;background:'+(data.week.week1==$('today').value?'#7fffd4':'#E7F2FC')+';font:bold 12px \'lucida Grande\',Verdana;width:50%">'+data.week.week1+'&nbsp&nbsp星期一</td>');
			str.append('<td style="text-align:left;background:'+(data.week.week2==$('today').value?'#7fffd4':'#E7F2FC')+';font:bold 12px \'lucida Grande\',Verdana;width:50%">'+data.week.week2+'&nbsp&nbsp星期二</td></tr>');
			str.append('<tr><td id="tz'+data.week.week1+'" style="height:'+(getSize().h/3-85)+'">&nbsp</td><td id="tz'+data.week.week2+'">&nbsp</td></tr>');
			
			str.append('<tr><td style="text-align:left;background:'+(data.week.week3==$('today').value?'#7fffd4':'#E7F2FC')+';font:bold 12px \'lucida Grande\',Verdana;width:50%">'+data.week.week3+'&nbsp&nbsp星期三</td>');
			str.append('<td style="text-align:left;background:'+(data.week.week4==$('today').value?'#7fffd4':'#E7F2FC')+';font:bold 12px \'lucida Grande\',Verdana;width:50%">'+data.week.week4+'&nbsp&nbsp星期四</td></tr>');
			str.append('<tr><td id="tz'+data.week.week3+'" style="height:'+(getSize().h/3-85)+'">&nbsp</td><td id="tz'+data.week.week4+'">&nbsp</td></tr>');
			
			str.append('<tr><td style="text-align:left;background:'+(data.week.week5==$('today').value?'#7fffd4':'#E7F2FC')+';font:bold 12px \'lucida Grande\',Verdana;width:50%">'+data.week.week5+'&nbsp&nbsp星期五</td>');
			str.append('<td style="text-align:left;background:'+(data.week.week6==$('today').value?'#7fffd4':'#E7F2FC')+';font:bold 12px \'lucida Grande\',Verdana;width:50%">'+data.week.week6+'&nbsp&nbsp星期六</td></tr>');
			str.append('<tr><td id="tz'+data.week.week5+'" style="height:'+(getSize().h/3-85)+'">&nbsp</td><td id="tz'+data.week.week6+'">&nbsp</td></tr>');
			
			str.append('<tr><td style="text-align:left;background:'+(data.week.week7==$('today').value?'#7fffd4':'#ff6347')+';font:bold 12px \'lucida Grande\',Verdana;width:50%">'+data.week.week7+'&nbsp&nbsp星期日</td>');
			str.append('<td></td></tr>');
			str.append('<tr><td id="tz'+data.week.week7+'" style="height:'+(getSize().h/3-85)+'">&nbsp</td><td>&nbsp</td></tr>');
			str.append('</table>');
			$('tableContainerzst').innerHTML = str.toString();
			
			if(data.info != null && data.info.length > 0){
				var len = data.info.length;
				for(var i=0;i<len;i++){
					$('tz'+data.info[i].strdate).innerHTML += (data.info[i].strhour+":"+data.info[i].strminute)+'m:<a href="javascript:;" onclick="preS('+data.info[i].sid+')">'+data.info[i].sbt+'</a>&nbsp&nbsp&nbsp';
				}
			}
			
		}
	}
	
	function getMonthByDay(){
		var ajax = new AppAjax("schedule!getMonthByDay.do?day="+$('today').value,getMonth_Back).submit();
	}
	
	var today2 = $('today').value.split('-')[2];
	function getMonth_Back(data){
		
		if(data !=null && data.cmonth!=null){
			var str = new StringBuffer();
			str.append('<table class="fxtable" cellspacing="0" cellpadding="0">');
			str.append('<tr>');
			str.append('<td style="text-align:left;background:#E7F2FC;font:bold 12px \'lucida Grande\',Verdana;width:14%">星期一</td>');
			str.append('<td style="text-align:left;background:#E7F2FC;font:bold 12px \'lucida Grande\',Verdana;width:14%">星期二</td>');
			str.append('<td style="text-align:left;background:#E7F2FC;font:bold 12px \'lucida Grande\',Verdana;width:14%">星期三</td>');
			str.append('<td style="text-align:left;background:#E7F2FC;font:bold 12px \'lucida Grande\',Verdana;width:14%">星期四</td>');
			str.append('<td style="text-align:left;background:#E7F2FC;font:bold 12px \'lucida Grande\',Verdana;width:14%">星期五</td>');
			str.append('<td style="text-align:left;background:#E7F2FC;font:bold 12px \'lucida Grande\',Verdana;width:14%">星期六</td>');
			str.append('<td style="text-align:left;background:#ff6347;font:bold 12px \'lucida Grande\',Verdana;width:14%">星期日</td>');
			str.append('</tr>');
			
			str.append('<tr>');
			var strmonth = 0;
			var curmonth = $('today').value.substring(0,8);
			
			for(var i=1;i<=7;i++){
				if(i>=parseInt(data.strweek)){
					str.append('<td id="ty'+(curmonth+'0'+(++strmonth))+'" style="height:'+(getSize().h/6)+';'+(strmonth==today2?'background:#7fffd4':'')+'" valign=top><b>'+strmonth+'</b><br></td>');
				}else{
					str.append('<td>&nbsp</td>');
				}
			}
			str.append('</tr>');
			var cm = parseInt(data.cmonth);
			for( var i=0;i<5;i++){
				if(strmonth >= cm){
					break;
				}
				str.append('<tr>');
				for(var j=0;j<7;j++){
					if(strmonth >= cm){
						break;
					}
					++strmonth;
					if(strmonth < 10){
						str.append('<td id="ty'+(curmonth+'0'+(strmonth))+'" style="height:'+(getSize().h/6)+';'+(strmonth==today2?'background:#7fffd4':'')+'" valign=top><b>'+strmonth+'</b><br></td>');
					}else{
						str.append('<td id="ty'+(curmonth+''+(strmonth))+'" style="height:'+(getSize().h/6)+';'+(strmonth==today2?'background:#7fffd4':'')+'" valign=top><b>'+strmonth+'</b><br></td>');
					}
				}
				str.append('</tr>');
			}
			str.append('</table>');
			$('tableContaineryst').innerHTML = str.toString();
			
			if(data.info != null && data.info.length > 0){
				var len = data.info.length;
				for(var i=0;i<len;i++){
					$('ty'+data.info[i].strdate).innerHTML += (data.info[i].strhour+":"+data.info[i].strminute)+'m:<a href="javascript:;" onclick="preS('+data.info[i].sid+')">'+data.info[i].sbt+'</a><br>';
				}
			}
		}
	}
	
	function preS(sid){
		openWin("schedule!preScheduleU.do?sid="+sid+"&opttype=3","600","350");
	}
	</script>
</html>

