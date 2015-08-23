<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
       <%@ include file="/common/meta.jsp"%>
		<SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxcommon.js"></SCRIPT>
        <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree.js"></SCRIPT>
        <link type="text/css" href="js/dhtmlxTree/dhtmlxtree.css"rel="stylesheet" >
        <SCRIPT LANGUAGE="Javascript" SRC="js/dhtmlxTree/dhtmlxtree_json.js"></SCRIPT>
        <script   type="text/javascript" src="js/dhtmlxTree/dhtmlXTreeExtend.js" ></script>
       <script language="JavaScript"src="<s:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>
		<style type="text/css">
			.ddbox{background-color:#e5ecf9;width:150px;border-bottom:1px solid #bacddc;padding:2px 5px 2px 5px;font-size:110%;height:25px;}
			.tree { float: left;width:180px;border:2px solid #A7C5E2;}
			.main { border:2px solid #A7C5E2;width:100%;height:100%;}

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
	
	-->
		</style>
	</head>
	<s:form name="czrcForm">
	<body >
			<s:hidden name="rcid"></s:hidden>
			<s:hidden name="kcid"></s:hidden>
			<s:hidden name="winid" id="winid"></s:hidden>
			<div style="width:100%;">
			<div class="tree" id="treeDiv">
					<div class="ddbox" style="width:100%;margin-bottom:3px;">
						<div>
							
						</div>
					</div>
					<div id="disTree" style="overflow:auto;">
					</div>
			</div>
			<div class="fxtableContainer" id="fxtableContainer">
	  	 	 	<table cellpadding="0" cellspacing="0" class="fxtable">
			  	 	 		<tr>
			  	 	 			<td class="bt">跟踪内容</td><td>
			  	 	 				<s:select name="parMap.gzmc" list="xtdict29" cssStyle="width:300" listKey="dictbh" listValue="dictname" />
			  	 	 			</td>
			  	 	 		</tr>
			  	 	 		<tr>
			  	 	 			<td class="bt">评审起始时间</td><td>
			  	 	 				<input type="text" class="Wdate"  name="parMap.gzstrdate" style="text-align:left;width:130"  onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>
			  	 	 				--
			  	 	 				<input type="text" class="Wdate"  name="parMap.gzenddate" style="text-align:left;width:130"  onfocus="new WdatePicker(this,'%Y-%M-%D')" MINDATE="1960-01-01" readonly/>
			  	 	 			</td>
			  	 	 		</tr>
			  	 	 		<tr>
			  	 	 			<td class="bt">评审主办单位</td><td>
			  	 	 				<s:textfield name="parMap.hdzbdw" id="parMap.hdzbdw"></s:textfield>
			  	 	 			</td>
			  	 	 		</tr>
			  	 	 		<tr>
			  	 	 			<td class="bt">评审地点</td><td>
			  	 	 				<textarea style="width:250px;height:60px;"  name="parMap.hddd"></textarea>
			  	 	 			</td>
			  	 	 		</tr>
			  	 	 		<tr>
			  	 	 			<td class="bt">备注</td><td>
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
											<li><a class="stars-4" id="starsp2-4" star:value="4" href="#">4</a></li>
											<li><a class="stars-5" id="starsp2-5" star:value="5" href="#">5</a></li>
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
											<li><a class="stars-4" id="stars2-4" star:value="4" href="#">4</a></li>
											<li><a class="stars-5" id="stars2-5" star:value="5" href="#">5</a></li>
										</ul>
										<span class="result" id="stars2-tips"></span>
										<s:hidden name="parMap.psdf" id="stars2-input"></s:hidden>
									</div>
			  	 	 			</td>
			  	 	 		</tr>
			  	 	 				  	 	 		
	  	 	 	</table>
			</div>
			</div>
			<div class="footer">
		   		<input type="button" name="resetBtn" class="button3" value="关  闭" onclick="closeWin();"/>
	        	&nbsp&nbsp&nbsp&nbsp
			</div>
	</body>
	</s:form>
	<script type="text/javascript">
		var Stars2 ,Stars1p,Stars2p;
		Event.observe(window, "load", function() {
			$("treeDiv").style.height=(getSize().h - 80)+"px" ;
			$('fxtableContainer').style.width = (getSize().w - 200)+"px";
			$('parMap.hdzbdw').focus();
			Stars2 = new Stars("stars2");
			Stars2p = new Stars("starsp2");
		}, true);
		
		var tree;
		loadTree();
		function loadTree(){
			$('disTree').innerHTML = "";
			tree=new dhtmlXTreeObject("disTree","100%","95%",0);
			tree.setImagePath("js/dhtmlxTree/imgs/csh_scbrblue/");
			tree.setOnClickHandler(function(id){getGzkc(id);});
			tree.enableSmartXMLParsing(true);
			tree.setDataMode("json");
			tree.enableCheckBoxes(false);
			tree.loadJSON("expgz!getGzTreeZj.do?rcid="+$('rcid').value,function(){});
		}
		$("disTree").style.height=(getSize().h - 75)+"px" ;
		var kcid = "";
		function getGzkc(id){
			if(id=='root'){
				kcid = "";
						$('parMap.gzstrdate').value = "";
						$('parMap.gzenddate').value = "";
						$('parMap.hddd').value = "";
						$('parMap.hdzbdw').value = "";
						$('parMap.sm').value = "";
						$('parMap.jspj').value = '';
						$('parMap.hbpj').value = '';
						$('parMap.psdf').value = '';
						Stars2 = new Stars("stars2");
						Stars1p = new Stars("starsp1");
						Stars2p = new Stars("starsp2");
			}else{
				kcid = id;
				var ajax = new AppAjax("expgz!getGzByGzid.do?gzid="+id,
				function(data){
					if(data!=null){
						$('parMap.gzmc').value = data.message.GZMC;
						$('parMap.gzstrdate').value = data.message.GZSTRDATE;
						$('parMap.gzenddate').value = data.message.GZENDDATE;
						$('parMap.hddd').value = data.message.HDDD;
						$('parMap.hdzbdw').value = data.message.HDZBDW;
						$('parMap.sm').value = data.message.SM;
						$('parMap.jspj').value = data.message.JSPJ;
						$('parMap.hbpj').value = data.message.HBPJ;
						$('parMap.psdf').value = data.message.PSDF;
						Stars2 = new Stars("stars2");
						Stars2p = new Stars("starsp2");
					}
				});
				ajax.submit();
			}
			$("kcid").value = kcid;
		}
		function saveKc(){
			if($('parMap.gzmc').value==''){
				alert('请输入选择跟踪内容 ！');
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
			
			var ajax = new AppAjax("expgz!doGzU.do",saveKc_callback);
				ajax.submitForm("czrcForm");
		}
		function saveKc_callback(data){
			if(data != null ){
				if(data.message.code > 0){
					alert("保存成功");
					loadTree();
				}else{
					alert(data.message.text);
				}
			}
		}
		
		function delkc(){
			if(kcid == ""){
				alert("请选择相应的考察");
				return false;
			}
			if(window.confirm("您确定要删除当前的考察")){
				var ajax = new AppAjax("expgz!doGzD.do?gzid="+kcid,
				function(data){
					if(data!=null){
						if(data.message.code > 0){
							alert("删除成功");
							loadTree();
						}else{
							alert(data.message.text);
						}
					}
				});
				ajax.submit();			
			}
		}
		
		/**
		 * 星星打分组件
		 *
		 * @author	Yunsd
		 * @date		2010-7-5
		 */
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
				for(i=0;i<len;i++){
					starlist[i].value = i;
					starlist[i].className = 'stars-'+(i+1) ;
					tips.innerHTML ='';
				}
				
				if($(star+'-input').value!='' && $(star+'-input').value!='0'){
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
			setStart: function(star){
				var starlist = document.getElementById(star).getElementsByTagName('a'); //星星列表
				var nowClass = " " + this.options.nowClass; // 定义选中星星样式名
				var len = starlist.length; //星星数量
				for(i=0;i<len;i++){
					if( $(star+'-input').value == starlist[i].value){	
						starlist[i].className = starlist[i].className + nowClass;
						flag = parseInt(starlist[i].value-1);
						tips.innerHTML = this.options.tipsTxt[flag];
					}else{
						starlist[i].className = 'stars-'+starlist[i].value;
						tips.innerHTML = '';
					}
				}
			},
			//设置默认属性
			SetOptions: function(options) {
				this.options = {//默认值
					Input:			"",//设置触保存分数的INPUT
					Tips:			"",//设置提示文案容器
					nowClass:	"current-rating",//选中的样式名
					tipsTxt:		["1分-较差","2分-差","3分-一般","4分-好","5分-较好"]//
				};
				Extend(this.options, options || {});
			}
		}
		
		
	</script>
</html>