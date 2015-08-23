function isIE() { //ie?
	if (window.navigator.userAgent.toLowerCase().indexOf("msie") >= 1)
		return true;
	else
		return false;
}

if (!isIE()) { //firefox innerText define
	HTMLElement.prototype.__defineGetter__("innerText", function() {
		var anyString = "";
		var childS = this.childNodes;
		for ( var i = 0; i < childS.length; i++) {
			if (childS[i].nodeType == 1)
				// anyString += childS[i].tagName=="BR" ? "\n" :
				// childS[i].innerText;
			anyString += childS[i].innerText;
		else if (childS[i].nodeType == 3)
			anyString += childS[i].nodeValue;
	}
	return anyString;
}	);
	HTMLElement.prototype.__defineSetter__("innerText", function(sText) {
		this.textContent = sText;
	});
}
function StringBuffer() {
	this._strings = [];
	if (arguments.length == 1) {
		this._strings.push(arguments[0]);
	}
};
StringBuffer.prototype = {
	append : function(str) {
		this._strings.push(str);
		return this;
	},
	clear : function() {
		this._strings = [];
	},
	length : function() {
		var str = this._strings.join("");
		return str.length;
	},
	del : function(num) {
		var len = this.length();
		var str = this.toString();
		str = str.slice(0, len - num);
		this._strings = [];
		this._strings.push(str);
	},
	toString : function() {
		return this._strings.join("");
	}
};
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};
String.prototype.replaceAll = function(search, replace) {
	var regex = new RegExp(search, "g");
	return this.replace(regex, replace);
};
/**
function openWin(sURL, width, height, sName, modal) {
	parent.openXwin(sURL, width, height, sName, modal);
}
**/
function getTopWindow(){
	var pw = window;
	while(pw!=pw.parent){
		if(!pw.parent.topWin){
			return pw;
		}
		pw = pw.parent;
	}
	return pw;
}

function openXwin(title,action,w,h,rowObj){
	parent.parent.openXwin(title,action,w,h,rowObj);
}

function closeXwin(){
	setTimeout(_closeXwin,10);
}

function _closeXwin(){
	parentDialog.close();
}

function sd(id) {
	document.mainFrame.document.getElementById(id).style.height = gh() - 28
			- 20 - 2 + "px";
}
/**
function closeWin(sid) {
	parent.closeXwin(sid);
}
function closeWindow(sid){
	parent.closeXwin(sid);
}**/
function minWin(sid) {
	parent.minWin(sid);
}
function winMove(sid, e, flag) {
	parent.MyMove.Move(sid, e, flag);
}
function getSize() {//取得frame高度
	var windowWidth, windowHeight;
	if (self.innerHeight) {
		windowWidth = self.innerWidth;
		windowHeight = self.innerHeight;
	} else if (document.documentElement
			&& document.documentElement.clientHeight) {
		windowWidth = document.documentElement.clientWidth;
		windowHeight = document.documentElement.clientHeight;
	} else if (document.body) {
		windowWidth = document.body.clientWidth;
		windowHeight = document.body.clientHeight;
	}
	return {
		h : windowHeight,
		w : windowWidth
	};
}

function gFW() {//取得frame宽度
	var windowWidth, windowHeight;
	if (self.innerHeight) {
		windowWidth = self.innerWidth;
		windowHeight = self.innerHeight;
	} else if (document.documentElement
			&& document.documentElement.clientHeight) {
		windowWidth = document.documentElement.clientWidth;
		windowHeight = document.documentElement.clientHeight;
	} else if (document.body) {
		windowWidth = document.body.clientWidth;
		windowHeight = document.body.clientHeight;
	}
	return windowWidth;
}

function getPageSizeMe() {
	var xScroll, yScroll;

	if (window.innerHeight && window.scrollMaxY) {
		xScroll = document.body.scrollWidth;
		yScroll = window.innerHeight + window.scrollMaxY;
	} else if (document.body.scrollHeight > document.body.offsetHeight) {
		sScroll = document.body.scrollWidth;
		yScroll = document.body.scrollHeight;
	} else {
		xScroll = document.body.offsetWidth;
		yScroll = document.body.offsetHeight;
	}
	return {
		x : xScroll,
		y : yScroll
	};
}
function O_D(obj1, obj2, location) { //打开div在打开元素的什么位置，obj1:点击的元素，obj2:要打开的div;location:left,top,right,bottom
	var btn = document.getElementById(obj1);
	var obj = document.getElementById(obj2);
	var h = btn.offsetHeight;
	var w = btn.offsetWidth;
	var x = btn.offsetLeft;
	var y = btn.offsetTop;

	while (btn = btn.offsetParent) {
		y += btn.offsetTop;
		x += btn.offsetLeft;
	}

	var hh = obj.offsetHeight;
	var ww = obj.offsetWidth;
	var xx = obj.offsetLeft;// style.left;
	var yy = obj.offsetTop;// style.top;

	var obj2location = location.toLowerCase();

	var showx, showy;

	if (obj2location == "left" || obj2location == "l" || obj2location == "top"
			|| obj2location == "t" || obj2location == "u"
			|| obj2location == "b" || obj2location == "r"
			|| obj2location == "up" || obj2location == "right"
			|| obj2location == "bottom") {
		if (obj2location == "left" || obj2location == "l") {
			showx = x - ww;
			showy = y;
		}
		if (obj2location == "top" || obj2location == "t" || obj2location == "u") {
			showx = x;
			showy = y - hh;
		}
		if (obj2location == "right" || obj2location == "r") {
			showx = x + w;
			showy = y;
		}
		if (obj2location == "bottom" || obj2location == "b") {
			showx = x;
			showy = y + h;
		}
	} else {
		showx = xx;
		showy = yy;
	}
	obj.style.left = showx + "px";
	obj.style.top = showy + "px";
	if (obj.style.display == "block") {
		obj.style.display = "none";
	} else {
		obj.style.display = "block";
	}
}

function setSelectDisplay(flag) {
	var objs = document.getElementsByTagName("select");
	for ( var i = 0; i < objs.length; i++) {
		if (flag) {
			objs[i].style.display = "";
		} else {
			objs[i].style.display = "none";
		}
	}

}

function set_win_div_h(id) {
	document.getElementById(id).style.height = getSize().h - 28 - 30 - 2 + "px";

}

function winReSize(sid,h,w,t,l){
	parent.reSize(sid,h,w,t,l);
}

function getOpener() {
	return parent.document.mainFrame;
}

function setBodyInnerHtml(table, content, mode, iconSrc) {
	var len = table.rows.length;
	for ( var i = 1; i < len; i++) {
		table.deleteRow(i);
	}

	var tr = table.insertRow(-1);
	var td = tr.insertCell(0);
	td.colSpan = table.rows[0].cells.length;
	td.align = "center";
	td.height = "80px;"
	var itl = "";
	if (mode) {
		if (mode.toLowerCase() == "exclam")
			itl = "<img src='images/exclam.gif' style='height:60px;vertical-align: middle;'>";
	}
	if (iconSrc) {
		itl = "<img src='" + iconSrc
				+ "' style='height:60px;vertical-align: middle;'>";
	}
	td.innerHTML = itl + content;
}
function roMoveTableRows(table) {
	var len = table.rows.length;
	for ( var i = 1; i < len; i++) {
		table.deleteRow(i);
	}
}

function InsertHTML(el, where, html) {
	if (!el)
		return false;
	try {

		if (el.insertAdjacentHTML) {
			el.insertAdjacentHTML(where, html);

		} else {

			var range = el.ownerDocument.createRange();
			var isBefore = where.indexOf("before") == 0;
			var isBegin = where.indexOf("Begin") != -1;
			if (isBefore == isBegin) {
				range[isBefore ? "setStartBefore" : "setStartAfter"](el);
				el.parentNode.insertBefore(
						range.createContextualFragment(html), isBegin ? el
								: el.nextSibling);
			} else {
				var obj = el[isBefore ? "lastChild" : "firstChild"];
				if (obj) {
					range[isBefore ? "setStartAfter" : "setStartBefore"](obj);
					el[isBefore ? "appendChild" : "insertBefore"](range
							.createContextualFragment(html), obj);
				} else {
					el.innerHTML = html;
				}
			}
		}
		return true;
	} catch (e) {
		alert(e);
		return false;
	}
}

function rt() {
	if (!parent.title_html || self.name != 'mainFrame')
		return;

	var _html = parent.title_html;
	var w_html = '<div class="pagetitle">所在位置：';
	w_html += _html;
	w_html += '</div>';
	document.write(w_html);
}
rt();

function setTitle(title){
	 title = title || "";
	 parent.fxTitle = title;
}
function getTitle(){
	var _ttt =  parent.fxTitle||"-";
	 parent.fxTitle = "";
	return _ttt;
}
function setSelected(id, val) {
	if (!id)
		return;
	var obj = $(id);
	if (!val)
		return;
	for ( var i = 0; i < obj.options.length; i++) {
		if (obj.options[i].value == val) {
			obj.options[i].selected = true;
			return;
		}
	}
}
//'openWin("score!testaa.do","400","400","ssss",false);'

function TimeCom(dateValue) {
	dateValue = formatDate(dateValue);
	dateValue = dateValue.replace("-","/");
	
	var newCom = new Date(dateValue);
	this.year = newCom.getFullYear();
	this.month = newCom.getMonth() + 1;
	this.day = newCom.getDate();
	this.hour = newCom.getHours();
	this.minute = newCom.getMinutes();
	this.second = newCom.getSeconds();
	this.msecond = newCom.getMilliseconds();
	this.week = newCom.getDay();
}
function DateDiff(interval, date1, date2) {
	var TimeCom1 = new TimeCom(date1);
	var TimeCom2 = new TimeCom(date2);
	var result;
	
	switch (String(interval).toLowerCase()) {
	case "y":
	case "year":
		result = TimeCom1.year - TimeCom2.year;
		break;
	case "n":
	case "month":
		result = (TimeCom1.year - TimeCom2.year) * 12
				+ (TimeCom1.month - TimeCom2.month);
		break;
	case "d":
	case "day":
		result = Math.round((Date.UTC(TimeCom1.year, TimeCom1.month - 1,
				TimeCom1.day) - Date.UTC(TimeCom2.year, TimeCom2.month - 1,
				TimeCom2.day))
				/ (1000 * 60 * 60 * 24));
		break;
	case "h":
	case "hour":
		result = Math.round((Date.UTC(TimeCom1.year, TimeCom1.month - 1,
				TimeCom1.day, TimeCom1.hour) - Date.UTC(TimeCom2.year,
				TimeCom2.month - 1, TimeCom2.day, TimeCom2.hour))
				/ (1000 * 60 * 60));
		break;
	case "m":
	case "minute":
		result = Math.round((Date.UTC(TimeCom1.year, TimeCom1.month - 1,
				TimeCom1.day, TimeCom1.hour, TimeCom1.minute) - Date.UTC(
				TimeCom2.year, TimeCom2.month - 1, TimeCom2.day, TimeCom2.hour,
				TimeCom2.minute))
				/ (1000 * 60));
		break;
	case "s":
	case "second":
		result = Math
				.round((Date.UTC(TimeCom1.year, TimeCom1.month - 1,
						TimeCom1.day, TimeCom1.hour, TimeCom1.minute,
						TimeCom1.second) - Date.UTC(TimeCom2.year,
						TimeCom2.month - 1, TimeCom2.day, TimeCom2.hour,
						TimeCom2.minute, TimeCom2.second)) / 1000);
		break;
	case "ms":
	case "msecond":
		result = Date.UTC(TimeCom1.year, TimeCom1.month - 1, TimeCom1.day,
				TimeCom1.hour, TimeCom1.minute, TimeCom1.second,
				TimeCom1.msecond)
				- Date.UTC(TimeCom2.year, TimeCom2.month - 1, TimeCom2.day,
						TimeCom2.hour, TimeCom2.minute, TimeCom2.second,
						TimeCom1.msecond);
		break;
	case "w":
	case "week":
		result = Math.round((Date.UTC(TimeCom1.year, TimeCom1.month - 1,
				TimeCom1.day) - Date.UTC(TimeCom2.year, TimeCom2.month - 1,
				TimeCom2.day))
				/ (1000 * 60 * 60 * 24)) % 7;
		break;
	default:
		result = "invalid";
	}
	return (result);
}
function formatDate(str) {
	if (str && str.length == 8) {
		return str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8);
	}
	return str;
}

function isNumber(val) {
	var regu = "^[-]{0,1}[0-9]+[.]{0,1}[0-9]{0,3}$";
	var re = new RegExp(regu);
	return !(val.search(re) == -1);
}


var EventTable = {
	add: function(obj,type,fn) {
		if (obj.attachEvent) {
			obj['e'+type+fn] = fn;
			obj[type+fn] = function() { obj['e'+type+fn](window.event); }
			obj.attachEvent('on'+type,obj[type+fn]);
		} else
		obj.addEventListener(type,fn,false);
	},
	remove: function(obj,type,fn) {
		if (obj.detachEvent) {
			obj.detachEvent('on'+type,obj[type+fn]);
			obj[type+fn] = null;
		} else
		obj.removeEventListener(type,fn,false);
	}
}

function $Table() {
	var elements = new Array();
	for (var i=0;i<arguments.length;i++) {
		var element = arguments[i];
		if (typeof element == 'string') element = document.getElementById(element);
		if (arguments.length == 1) return element;
		elements.push(element);
	}
	return elements;
}

function setTrTop(el){
	//document.getElementById(el).style.top = $("fmiscell").scrollTop;

}
function addClassName(el,className) {
	removeClassName(el,className);
	el.className = (el.className + " " + className).trim();
}

function removeClassName(el,className) {
	el.className = el.className.replace(className,"").trim();
}
var ZebraTable = {
	bgcolor: '',
	classname: '',
	o_r_z : null,
	stripe: function(el,o_over,o_click) {//是否设置over事件，是否设置click事件,1:是，0：否
		if (!$Table(el)) return;
		var on_o = o_over||"0";
		var on_c = o_click||"0";
		var rows = $(el).getElementsByTagName('tr');
		for (var i=1,len=rows.length;i<len;i++) {
				 if (i % 2 == 0) rows[i].className = 'alt';
				 if(on_o==1){
					EventTable.add(rows[i],'mouseover',function() { ZebraTable.mouseover(this); });
					EventTable.add(rows[i],'mouseout',function() { ZebraTable.mouseout(this); });
				}
				if(on_c==1){
					EventTable.add(rows[i],'click',function() { ZebraTable.click(this); });
				}
			}
	},
	mouseover: function(row) {
		this.bgcolor = row.style.backgroundColor;
		this.classname = row.className;
		addClassName(row,'over');
	},
	click: function(row) {
		if(this.o_r_z==row)return;
		this.bgcolor = row.style.backgroundColor;
		this.classname = row.className;
		addClassName(row,'click');
		if(this.o_r_z)removeClassName(this.o_r_z,'click');
		this.o_r_z = row;
	},
	mouseout: function(row) {
		removeClassName(row,'over');
		addClassName(row,this.classname);
		row.style.backgroundColor = this.bgcolor;
	}
}

function isNumber (val) {
	var regu = "^[-]{0,1}[0-9]+[.]{0,1}[0-9]{0,3}$";
	var re = new RegExp(regu);
	return !(val.search(re) == -1);
};

function showwaitform(s)
	{
		if(s==""|s==null)
		{
			s="正在处理，请稍候……";
		}
		if(!$('_waitform'))
		{
			var sfrm="<table style='background-color:#DDEEEE;border:2px solid #42BDFF;width:250px; height:60px;'>"+"<tr align=center valign=middle>"+'<td><img src="images/skin0/other/load_page.gif" align="absmiddle">&nbsp;<span id="_waitmsg" style="font-size:14px;">'+s+"</span></td>"+"</tr></table>";
			var s_s="LEFT:0px;WIDTH:100%;POSITION:absolute;TOP:0px;HEIGHT:100%;";
			var div=document.createElement("div");
			div.id="_waitform";
			div.style.cssText="FILTER:Alpha(opacity=75);Z-INDEX:12000;background-color:#EEEEEE;"+s_s;
			div.innerHTML='<iframe APPLICATION="YES" frameborder=0 marginheight=0 marginwidth=0 hspace=0 vspace=0 scrolling=no width=100% height=100%></iframe>';
			document.body.appendChild(div);
			var divv=document.createElement("div");
			divv.id="__waitform";
			divv.style.cssText="Z-INDEX:12001;"+s_s;
			divv.innerHTML="<table width=100% height=100%><tr><td align=center valign=middle>"+sfrm+"</td></tr></table>";
			document.body.appendChild(divv);
		}
		else
		{
			$('_waitmsg').innerHTML=s;
			$('_waitform').style.display="";
			$('__waitform').style.display="";
		}
	}
	function hidewaitform()
	{
		try
		{
			$('_waitform').style.display="none";
			$('__waitform').style.display="none";
		}
		catch(ex)
		{
		}
	}
	//关闭窗口
	function closeWin(){
		window.parent.closeWin($('winid').value);
	}
