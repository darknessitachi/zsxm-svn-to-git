<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<%@ include file="/common/meta.jsp"%>
 <link type="text/css" href="styles/csstable.css"rel="stylesheet" />
<script src="Framework/Main.js" type="text/javascript"></script>
<script type="text/javascript">
	Server.importScript("js/indexInit.js");
</script>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #EEF2FB;
}
-->
.left_topbg {
	background-image: url(images/skin0/content-bg.gif);
	background-repeat: repeat-x;
}
.titlebt {
	font-size: 12px;
	line-height: 26px;
	font-weight: bold;
	color: #000000;
	background-image: url(images/skin0/top_bt.jpg);
	background-repeat: no-repeat;
	display: block;
	text-indent: 15px;
	padding-top: 5px;
}
.content {
	border: #8db355 0px solid;
	font-size: 13px;
	padding: 12px 16px;
	line-height: 25px;
	color: #333;
	height:85px;
	overflow:auto;
}
.spanC{border-bottom:0px solid #8db355;cursor:pointer;color:#5D82AE;}
.spanC2{cursor:pointer;color:blue;}
.spanC4{border-bottom:1px solid #8db355;cursor:pointer;color:#5D82AE;}
.spanC3{cursor:pointer;color:#2A5F91; font: italic  Times, serif}
</style>
<body style="width:100%;height:100%">
	<table style="width:100%;height:100%">
	
	<tr valign="top">
		<td id="td_index">
			
		</td>
	</tr>
	</table>
</body>
<script>

	Event.observe(window, "load", function() {
		new drawIndexTabInit("td_index",{
			table:[["xtxxfb","新闻公告","50%"],["exptb","专家更新","50%"]]
		});
		getInfo();
		getExptb();
		var h = $('xtxxfb').style.height;
		$('xxggdiv').style.height = "240px";
	}, true);
	
			
	
	
	function getInfo(){
		var str = new StringBuffer('<div id="xxggdiv" class="content">');
		str.append("<p>");
		str.append("<span class='spanC2' >【新闻公告】</span><span class='spanC3' >2010.08.19</span>--<span class='spanC4'>五中全会强调以更大决心和勇气推进各领域改革</span><br/>");
		str.append("<span class='spanC2' >【新闻公告】</span><span class='spanC3' >2010.09.19</span>--<span class='spanC4'>中共十七届五中全会增补习近平为军委副主席</span><br/>");
		str.append("<span class='spanC2' >【新闻公告】</span><span class='spanC3' >2010.09.20</span>--<span class='spanC4'>专家：十二五开始我国发展模式将不能重复过去</span><br/>");
		str.append("<span class='spanC2' >【新闻公告】</span><span class='spanC3' >2010.09.20</span>--<span class='spanC4'>五中全会解读：五年发展目标首次未提GDP</span><br/>");
		str.append("<span class='spanC2' >【新闻公告】</span><span class='spanC3' >2010.09.20</span>--<span class='spanC4'>专家称产业升级节能减排将是十二五规划重点</span><br/>");
		str.append("</p>");
		str.append("</div>");
		$('xtxxfb').innerHTML = str.toString();
	}
	
	
	function getExptb(){
		var str = new StringBuffer('<table id="tjtable" cellspacing="0" style="width:100%"><thead>');
		str.append('<tr><th class="thc" style="width:50px;" >序号</th>');
		str.append('<th class="thc" >姓名</th>');
		str.append('<th class="thc" >性别</th>');
		str.append('<th class="thc" >证件号码</th>');
		str.append('<th class="thc">学历</th>');
		str.append('<th class="thc">所学专业</th>');
		str.append('<th class="thc">从事专业</th>');
		str.append('<th class="thc">操作</th>');
		str.append('</tr></thead><tbody>');
		str.append('<tr><th class="spec" style="width:50px;" >1</th>');
		str.append('<td class="tdc" >李明</td>');
		str.append('<td class="tdc" >男</td>');
		str.append('<td class="tdc" >234234203490234</td>');
		str.append('<td class="tdc">大专</td>');
		str.append('<td class="tdc">电子</td>');
		str.append('<td class="tdc">计算机</td>');
		str.append('<td class="tdc"><a>数据同步</a></td>');
		
				str.append('<tr><th class="spec" style="width:50px;" >2</th>');
		str.append('<td class="tdc" >李明</td>');
		str.append('<td class="tdc" >男</td>');
		str.append('<td class="tdc" >234234203490234</td>');
		str.append('<td class="tdc">大专</td>');
		str.append('<td class="tdc">电子</td>');
		str.append('<td class="tdc">计算机</td>');
		str.append('<td class="tdc"><a>数据同步</a></td>');
		
				str.append('<tr><th class="spec" style="width:50px;" >3</th>');
		str.append('<td class="tdc" >李明</td>');
		str.append('<td class="tdc" >男</td>');
		str.append('<td class="tdc" >234234203490234</td>');
		str.append('<td class="tdc">大专</td>');
		str.append('<td class="tdc">电子</td>');
		str.append('<td class="tdc">计算机</td>');
		str.append('<td class="tdc"><a>数据同步</a></td>');
		
				str.append('<tr><th class="spec" style="width:50px;" >4</th>');
		str.append('<td class="tdc" >李明</td>');
		str.append('<td class="tdc" >男</td>');
		str.append('<td class="tdc" >234234203490234</td>');
		str.append('<td class="tdc">大专</td>');
		str.append('<td class="tdc">电子</td>');
		str.append('<td class="tdc">计算机</td>');
		str.append('<td class="tdc"><a>数据同步</a></td>');
		
				str.append('<tr><th class="spec" style="width:50px;" >5</th>');
		str.append('<td class="tdc" >李明</td>');
		str.append('<td class="tdc" >男</td>');
		str.append('<td class="tdc" >234234203490234</td>');
		str.append('<td class="tdc">大专</td>');
		str.append('<td class="tdc">电子</td>');
		str.append('<td class="tdc">计算机</td>');
		str.append('<td class="tdc"><a>数据同步</a></td>');
		str.append('</tbody>');
		str.append('</table>');
		$('exptb').innerHTML = str.toString();
	}
</script>
</html>