/**
		 * 星星打分组件
		 *
		 * @author	W
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
				var tips = document.getElementById(star+"-tips"); // 打印提示
				var nowClass = " " + this.options.nowClass; // 定义选中星星样式名
				var tipsTxt = this.options.tipsTxt; // 定义提示文案
				var len = starlist.length; //星星数量
				
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