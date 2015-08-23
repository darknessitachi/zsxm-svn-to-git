<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
       <%@ include file="/common/meta.jsp"%>
       <script language="JavaScript"src="<s:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>
       <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxcommon.js"></SCRIPT>
		<SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree.js"></SCRIPT>
        <link type="text/css" href="js/dhtmlxTree/dhtmlxtree.css"rel="stylesheet" >
        <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree_json.js"></SCRIPT>
        <script   type="text/javascript" src="js/dhtmlxTree/dhtmlXTreeExtend.js" ></script>
        <link type="text/css" href="<s:url value="/styles/tab.css"/>" rel="stylesheet" />
        <script type="text/javascript" src="<c:url value="/js/GTab.js"/>" ></script>
       <link type="text/css" href="js/dhtmlxTree/dhtmlxtree.css"rel="stylesheet" >
		<style type="text/css">
			.ddbox{background-color:#e5ecf9;width:150px;border-bottom:1px solid #bacddc;padding:2px 5px 2px 5px;font-size:110%;height:25px;}
			.tree { float: left;width:100%;border:2px solid #A7C5E2;height:100%}
			.main { border:2px solid #A7C5E2;width:100%;height:100%;}
			.tb{width:450px;margin-top:20px;border:1px solid #76A7DE;}
			.td1{width:110px;background:#F0F5F9;height:30px;line-height:30px;padding:2px 0 0 2px; font-size:13px; font-weight:bold;border-bottom:1px solid #76A7DE;border-right:1px solid #76A7DE;}
			.td2{height:30px; font-size:13px; font-weight:bold;padding:0 0 0 2px;border-bottom:1px solid #76A7DE;}
			<!--


/* 星级评分 */
.shop-rating {
    height: 25px;
    overflow: hidden;
    zoom: 1;
    padding: 2px 0px;
    position: relative;
    z-index: 999;
}

.shop-rating span {
    height: 23px;
    display: block;
    line-height: 23px;
    float: left;
}

.shop-rating span.title {
    width: 125px;
    text-align: right;
    margin-right: 5px;
}

.shop-rating ul {
    float: left;
	margin:0;padding:0
}

.shop-rating .result {
    margin-left: 20px;
    padding-top: 2px;
}

.shop-rating .result span {
	color: #ff6d02;
}
.shop-rating .result em {
    color: #f60;
    font-family: arial;
    font-weight: bold;
}
.shop-rating .result strong {
	color: #666666;
	font-weight: normal;
}
.rating-level,
.rating-level a {
    background: url(images/skin0/star_v2.png) no-repeat scroll 1000px 1000px;
}
.rating-level {
    background-position: 0px 0px;
    width: 120px;
    height: 23px;
    position: relative;
    z-index: 1000;
}
.rating-level li {
    display: inline;
}

.rating-level a {
    line-height: 23px;
    height: 23px;
    position: absolute;
    top: 0px;
    left: 0px;
    text-indent: -999em;
    *zoom: 1;
    outline: none;
}


.rating-level a.stars-1 {
    width: 20%;
    z-index: 6;
}

.rating-level a.stars-2 {
width: 40%;
z-index: 5;
}

.rating-level a.stars-3 {
    width: 60%;
    z-index: 4;
}

.rating-level a.stars-4 {
    width: 80%;
    z-index: 3;
}

.rating-level a.stars-5 {
    width: 100%;
    z-index: 2;
}

.rating-level .current-rating,.rating-level a:hover{background-position:0 -28px;}
.rating-level a.stars-1:hover,.rating-level a.stars-2:hover,.rating-level a.stars-1.current-rating,.rating-level a.stars-2.current-rating{background-position:0 -116px;}
.rating-level .stars-3 .current-rating,.rating-level .stars-4 .current-rating,.rating-level .stars-5 .current-rating{background-position:0 -28px;}
.selectBut2 {
				WIDTH: 129px; BACKGROUND: url(images/skin0/rcda/btn8.jpg) no-repeat;TEXT-ALIGN: left; BORDER-RIGHT-WIDTH: 0px; PADDING-LEFT: 10px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; HEIGHT: 22px; BORDER-LEFT-WIDTH: 0px; CURSOR: pointer
			}
.fsg_nr{ position:absolute; display:none;top:25px; right:-3px; background:#fff; border:1px solid #98B1C8; }	
-->
		</style>
	</head>
	
	<body >
			<s:form name="czrcForm">
			
			<div>
				<table class="fxtable" >
					<tr>
	  	 	 			<td style="background:#efefef">跟踪内容</td>
	  	 	 			<td class="td2">
	  	 	 				<!--<s:select name="parMap.gzmc" list="xtdict29" cssStyle="width:300" listKey="dictbh" listValue="dictname" />-->
	  	 	 				<INPUT id="zsxm_button" class="selectBut2"  title="选择" value="点击选择" type=button onclick="O_D('zsxm_button','xtdlb','bottom');setClickID(1);loadTree('-98');"/>
	  	 	 				<input type="hidden" name="parMap.gzmc" id="parMap.gzmc"/>
	  	 	 			</td>
	  	 	 		</tr>
				</table>
			</div>
			<div class="board" id="tab"></div>
			<s:hidden name="rcid"></s:hidden>
			<s:hidden name="winid" id="winid"></s:hidden>
			  	 	 <div class="fxtableContainer"  id="kc">
			  	 	 	<table cellpadding="0" class="fxtable" cellspacing="0" >
			  	 	 		
			  	 	 		<tr>
			  	 	 			<td class="bt">评审起始时间</td><td class="td2">
			  	 	 				<input type="text" class="Wdate"  name="parMap.gzstrdate" style="text-align:left;width:130"  onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>
			  	 	 				--
			  	 	 				<input type="text" class="Wdate"  name="parMap.gzenddate" style="text-align:left;width:130"  onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>
			  	 	 			</td>
			  	 	 		</tr>
			  	 	 		<tr>
			  	 	 			<td class="bt">评审主办单位</td><td class="td2">
			  	 	 				<s:textfield name="parMap.hdzbdw" id="parMap.hdzbdw"></s:textfield>
			  	 	 			</td>
			  	 	 		</tr>
			  	 	 		<tr>
			  	 	 			<td class="bt">评审地点</td><td class="td2">
			  	 	 				<textarea style="width:250px;height:60px;"  name="parMap.hddd"></textarea>
			  	 	 			</td>
			  	 	 		</tr>
			  	 	 		<tr>
			  	 	 			<td class="bt">备注</td><td class="td2">
			  	 	 				<textarea style="width:250px;height:60px;"  name="parMap.sm"></textarea>
			  	 	 			</td>
			  	 	 		</tr>
			  	 	 		
			  	 	 		<tr>
			  	 	 			<td class="bt">
			  	 	 				汇报情况
			  	 	 			</td>
			  	 	 			<td class="td2">
			  	 	 				<s:hidden name="parMap.jspj" id="starsp1-input"></s:hidden>
									<div class="shop-rating">
										<ul class="rating-level" id="starsp2">
											<li><a class="stars-1" id="starsp2-1" star:value="1" href="#">1</a></li>
											<li><a class="stars-2" id="starsp2-2" star:value="2" href="#">2</a></li>
											<li><a class="stars-3" id="starsp2-3" star:value="3" href="#">3</a></li>
											<!-- 
											<li><a class="stars-4" id="starsp2-4" star:value="4" href="#">4</a></li>
											<li><a class="stars-5" id="starsp2-5" star:value="5" href="#">5</a></li>
											 -->
										</ul>
										<span class="result" id="starsp2-tips"></span>
										<s:hidden name="parMap.hbpj" id="starsp2-input"></s:hidden>
									</div>
			  	 	 			</td>
			  	 	 		</tr>
			  	 	 		
			  	 	 		
			  	 	 		<tr>
			  	 	 			<td class="bt">评审情况</td>
			  	 	 			<td class="td2">
			  	 	 				<div class="shop-rating">
										<ul class="rating-level" id="stars2">
											<li><a class="stars-1" id="stars2-1" star:value="1" href="#">1</a></li>
											<li><a class="stars-2" id="stars2-2" star:value="2" href="#">2</a></li>
											<li><a class="stars-3" id="stars2-3" star:value="3" href="#">3</a></li>
											<!-- 
											<li><a class="stars-4" id="stars2-4" star:value="4" href="#">4</a></li>
											<li><a class="stars-5" id="stars2-5" star:value="5" href="#">5</a></li>
											 -->
										</ul>
										<span class="result" id="stars2-tips"></span>
										<s:hidden name="parMap.psdf" id="stars2-input"></s:hidden>
									</div>
			  	 	 			</td>
			  	 	 		</tr>
			  	 	 	
			  	 	 	</table>
			  	 	 </div>
			  	 	 
			  	 	 <div class="fxtableContainer"  id="wtfpj">
			  	 	 	<table cellpadding="0" class="fxtable" cellspacing="0" >
			  	 	 		<tr>
			  	 	 			<td class="bt">评价等级</td><td class="td2">
			  	 	 				<s:select name="wtMap.wtpjdj" id="wtMap.wtpjdj"  list="xtdict35" cssStyle="width:130" listKey="dictbh" listValue="dictname" headerKey="" headerValue="--请选择--" />
			  	 	 			</td>
			  	 	 		</tr>
			  	 	 		
			  	 	 		<tr>
			  	 	 			<td class="bt">评价意见</td><td class="td2">
			  	 	 				<textarea style="width:250px;height:60px;"  name="wtMap.wtpjyj"></textarea>
			  	 	 			</td>
			  	 	 		</tr>
			  	 	 		<tr>
			  	 	 			<td class="bt">备注</td><td class="td2">
			  	 	 				<textarea style="width:250px;height:60px;"  name="wtMap.wtsm"></textarea>
			  	 	 			</td>
			  	 	 		</tr>
			  	 	 		
			  	 	 	</table>
			  	 	 </div>
			  	 	 
			   <div class="footer">
			   		<input id="s1" class="button3"  type="button" onclick="saveKc();" value="保  存" />
		        	<input type="button" name="resetBtn" class="button3" value="关  闭" onclick="closeWin();"/>
		        	&nbsp&nbsp&nbsp&nbsp
		       </div>
		       
		       	<div loading='0' id='xtdlb' class="fsg_nr" style="display:none;width:270;height:200;overflow:auto;">
					<div id='xtdlbloadimage'>
						<img src="images/skin0/other/upload.gif">数据载入中.....
					</div>
					<div loading='0' id='xtdlbload' style="width:100%;height:195;background-color:#E7EAF7;overflow:auto;">
						
					</div>
					
					<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:100%; height:100%; z-index:-1; filter='progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)';"></iframe>
				</div>
		</s:form>
	</body>
	
	<script type="text/javascript">
		var gt ;
		var _items_tab = [
			{name: '专家评价', id : 'tab1',action : 'kc',img:'images/tab/580.gif',selected:true},
			{name: '委托方评价', id : 'tab2',action : 'wtfpj',img:'images/tab/091.gif',selected:false}
		];
		Event.observe(window, "load", function() {
			$('parMap.hdzbdw').focus();
			var Stars2 = new Stars("stars2");
			var Stars2p = new Stars("starsp2");
			gt = new GTab("tab",{item:_items_tab});
		}, true);
		
		function saveKc(){
			if($('parMap.gzmc').value==''){
				alert('请输入选择跟踪内容 ！');
				return false;
			}
			if($('parMap.hdzbdw').value==''){
				alert('请输入评审主办单位！');
				return false;
			}
			if($('parMap.gzstrdate').value==''){
				alert('请输入跟踪起始时间！');
				return false;
			}
			if($('parMap.gzenddate').value==''){
				alert('请输入跟踪结束时间！');
				return false;
			}
			if($('parMap.hddd').value==''){
				alert('请输入评审地点！');
				return false;
			}
			var ajax = new AppAjax("expgz!doGzI.do",saveKc_callback);
				ajax.submitForm("czrcForm");
		}
		
		function saveKc_callback(data){
			if(data.message.code >0){
				alert("保存成功");
				window.parent.refreshmm();
				closeWin();
			}else{
				alert(data.message.text);
			}
		}
		
		var Class = {
			create: function() {
				return function() { this.initialize.apply(this, arguments); }
			}
		}
		var Extend = function(destination, source) {
			for (var property in source) {
				destination[property] = source[property];
			}
		}
		function stopDefault( e ) {
			if ( e && e.preventDefault ){
				e.preventDefault();
			}else{
				window.event.returnValue = false;
			}
			return false;
		} 
		/**
		 * 星星打分组件
		 *
		 * @author	Yunsd
		 * @date		2010-7-5
		 */
		var Stars = Class.create();
		Stars.prototype = {
			initialize: function(star,options) {
				this.SetOptions(options); //默认属性
				var flag = 999; //定义全局指针
				var isIE = (document.all) ? true : false; //IE?
				var starlist = document.getElementById(star).getElementsByTagName('a'); //星星列表
				var input = document.getElementById(this.options.Input) || document.getElementById(star+"-input"); // 输出结果
				var tips = document.getElementById(this.options.Tips) || document.getElementById(star+"-tips"); // 打印提示
				var nowClass = " " + this.options.nowClass; // 定义选中星星样式名
				var tipsTxt = this.options.tipsTxt; // 定义提示文案
				var len = starlist.length; //星星数量
				
				if($(star+'-input').value!=''){
					var sv = $(star+'-input').value;
					$(star+'-'+sv).className = $(star+'-'+sv).className+ nowClass;
					tips.innerHTML = tipsTxt[(sv-1)];
					flag = (sv-1);
				}
				
				for(i=0;i<len;i++){ // 绑定事件 点击 鼠标滑过
					starlist[i].value = i;
					starlist[i].onclick = function(e){
						stopDefault(e);
						this.className = this.className + nowClass;
						flag = this.value;
						input.value = this.getAttribute("star:value");
						tips.innerHTML = tipsTxt[this.value];
					}
					starlist[i].onmouseover = function(){
						if (flag< 999){
							var reg = RegExp(nowClass,"g");
							starlist[flag].className = starlist[flag].className.replace(reg,"")
						}
					}
					starlist[i].onmouseout = function(){
						if (flag< 999){
							starlist[flag].className = starlist[flag].className + nowClass;
						}
					}
				};
				if (isIE){ //FIX IE下样式错误
					var li = document.getElementById(star).getElementsByTagName('li');
					for (var i = 0, len = li.length; i < len; i++) {
						var c = li[i];
						if (c) {
							c.className = c.getElementsByTagName('a')[0].className;
						}
					}
				}
			},
			//设置默认属性
			SetOptions: function(options) {
				this.options = {//默认值
					Input:			"",//设置触保存分数的INPUT
					Tips:			"",//设置提示文案容器
					nowClass:	"current-rating",//选中的样式名
					tipsTxt:		["差","中","好"]//
				};
				Extend(this.options, options || {});
			}
		}
	
	var clickid = 1;
	function setClickID(id){
		clickid = id;
	}
	
	var tree;
	function loadTree(dm){
		if($("xtdlb").loading != 1){
			$('xtdlbload').innerHTML = "";
			tree=new dhtmlXTreeObject("xtdlbload","100%","100%",0);
			tree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
			tree.setOnClickHandler(function(id){setV(id);});
			tree.enableSmartXMLParsing(true);
			tree.setDataMode("json");
			tree.enableCheckBoxes(false);
			tree.loadJSON("exp!getQueryTree.do?dm="+dm,function(){$('xtdlbloadimage').style.display="none";});
			$('xtdlb').loading = 1;			
		}
	}
	function setV(id){
		if(clickid==1){
			if(id=='root'){
				$('parMap.gzmc').value='';
			}else{
				$('parMap.gzmc').value=id;
			}
			$('xtdlb').style.display = 'none';
			$('zsxm_button').value = tree.getItemText(id);
		}else{
			if(id=='root'){
				$('wtMap.gzmc').value='';
			}else{
				$('wtMap.gzmc').value=id;
			}
			$('xtdlb').style.display = 'none';
			$('zsxm_button_wt').value = tree.getItemText(id);
		}
		
	}
	</script>
</html>