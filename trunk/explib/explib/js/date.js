
Date.prototype.getRealYear = function () {
	return (this.getYear() < 1000) ? this.getYear() + 1900 : this.getYear();
};

String.prototype.replaceAll = function(search, replace){
	 var regex = new RegExp(search, "g");
	 return this.replace(regex, replace);
};

$BindAsEventListener = function (sender, listener) {
	var __method = sender;
	return function (event) {
		return __method.call(listener, event || window.event);
	};
};
var CDate = {now:new Date(), date_months:["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"], date_dayCounts:[31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31], date_dayNames:["\u65e5", "\u4e00", "\u4e8c", "\u4e09", "\u56db", "\u4e94", "\u516d"], startYear:1910, endYear:2011, setdate:new Date(), inputdate:new Date(), dropdown:null, cal:null, inp:null, killEvent:this.killDropdown,
 Show:function (input,doEvent) {
	input.className += " default";
	input.maxLength = 10;
	var iv = input.value.replaceAll("-","");
	if(this.isDate(iv)){
	var iy = iv.substring(0,4);
	var im = iv.substring(4,6);
	var id = iv.substring(6,8);
	this.inputdate.setYear(parseFloat(iy));
	this.inputdate.setMonth(parseFloat(im) - 1);
	this.inputdate.setDate(parseFloat(id));
	this.setdate.setYear(parseFloat(iy));
	this.setdate.setMonth(parseFloat(im) - 1);
	this.setdate.setDate(parseFloat(id));
}
	this.createDropdown(input);
	
}, createDropdown:function (inpObj) {
	this.killDropdown();
	Event.observe(document, "mousedown", CDate.killDropdown);
	this.dropdown = document.createElement("div");
	this.dropdown.style.position = "absolute";
	this.dropdown.style.left = Position.page(inpObj)[0] + "px";
	this.dropdown.style.top = Position.page(inpObj)[1] + inpObj.offsetHeight + "px";
	this.dropdown.className = "dropdown";
	var monthsel = null;
	var yearsel = null;
	monthsel = document.createElement("select");
	monthsel.className = "month";
	for (i = 0; i < this.date_months.length; i++) {
		var optionObj = new Option(this.date_months[i], i);
		monthsel.options[monthsel.options.length] = optionObj;
		if (i == this.inputdate.getMonth()) {
			monthsel.options[i].selected = true;
		}
	}
	monthsel.onchange = function () {
		CDate.setdate.setMonth(this.value);
		CDate.render();
	};
	this.dropdown.appendChild(monthsel);
	yearsel = document.createElement("select");
	yearsel.className = "year";
	var p = 0;
	for (y = this.startYear; y < this.endYear; y++) {
		var optionObj = new Option(y, y);
		yearsel.options[yearsel.options.length] = optionObj;
		if (y == this.inputdate.getRealYear()) {
			yearsel.options[p].selected = true;
		}
		p++;
	}
	yearsel.onchange = function () {
		CDate.setdate.setYear(this.value);
		CDate.render();
	};
	this.dropdown.appendChild(yearsel);
	closeButton = document.createElement("button");
	closeButton.appendChild(document.createTextNode("关闭"));
	closeButton.className = "close";
	closeButton.onclick = function () {
		CDate.killDropdown();
		return false;
	};
	this.dropdown.appendChild(closeButton);
	this.dropdown.onmousedown = function (evt) {
		evt = evt || window.event;
		if (evt.stopPropagation) {
			evt.stopPropagation();
		} else {
			evt.cancelBubble = true;
		}
	};
	this.render();
	this.inp = inpObj;
	this.inp.parentNode.appendChild(this.dropdown);
}, isDate:function (value) {
	if(value.trim() == ""){
		return false;
	}
	if (value.length != 8 || !this.isNumber(value)) {
		return false;
	}
	var year = value.substring(0, 4);
	if (year > "2100" || year < "1900") {
		return false;
	}
	var month = value.substring(4, 6);
	if (month > "12" || month < "01") {
		return false;
	}
	var day = value.substring(6, 8);
	if (day > this.getMaxDay(year, month) || day < "01") {
		return false;
	}
	return true;
}, getMaxDay :function (year,month) {
	if(month==4||month==6||month==9||month==11)
		return "30";
	if(month==2)
		if(year%4==0&&year%100!=0 || year%400==0)
			return "29";
		else
			return "28";
	return "31";
},

isNumber :function ( s ){   
	var regu = "^[0-9]+$";
	var re = new RegExp(regu);
	if (s.search(re) != -1) {
	   return true;
	} else {
	   return false;
	}
},dayCell:function (text) {
	var label = text || " ";
	var cell = document.createElement("td");
	cell.date = this.setdate.getRealYear() + "-" + this.padout(this.setdate.getMonth() + 1) + "-" + this.padout(text);
	if (text) {
		cell.onmouseover = function () {
			cell.className += " hover";
		};
		cell.onmouseout = function () {
			cell.className = cell.className.replace(/ ?hover\b/, "");
		};
		cell.onclick = function () {
			CDate.inp.className = CDate.inp.className.replace(/ ?default\b/, "");
			CDate.inp.value = cell.date;
			CDate.killDropdown();
				var onPicked = new Function(CDate.inp.getAttribute("onpicked"));
					onPicked = $BindAsEventListener(onPicked, CDate.inp);
					onPicked();
				
			
		};
		cell.title = cell.date;
	}
	cell.innerHTML = label;
	var tmpdate = new Date(this.setdate);
	tmpdate.setDate(text);
	if ((tmpdate.getDay() == 0) || (tmpdate.getDay() == 6)) {
		cell.className += " weekend";
	}
	if (this.now.getFullYear() == this.setdate.getFullYear() && this.now.getMonth() == this.setdate.getMonth() && this.now.getDate() == text) {
		cell.className += " today";
		cell.title += " (今天)";
	}
	if (this.setdate.getFullYear() == this.inputdate.getFullYear() && this.setdate.getMonth() == this.inputdate.getMonth() && this.setdate.getDate() == text) {
		cell.className += " selected";
	}
	return cell;
}, render:function () {
	if (this.cal != null) {
		this.cal.parentNode.removeChild(this.cal);
	}
	this.cal = document.createElement("table");
	this.cal.cellSpacing = 0;
	var row = document.createElement("tr");
	var head = document.createElement("thead");
	for (i = 0; i < this.date_dayNames.length; i++) {
		day = document.createElement("th");
			//day.appendChild(document.createTextNode(this.date_dayNames[i].substr(0,3)));
		row.appendChild(day);
		day.innerHTML = this.date_dayNames[i].substr(0, 3);
	}
	head.appendChild(row);
	this.cal.appendChild(head);
	if (this.cal.getElementsByTagName("tbody").length > 0) {
		this.cal.removeChild(this.cal.getElementsByTagName("tbody")[0]);
	}
	body = document.createElement("tbody");
	cells = [];
	if (this.now.getDay() < 7) {//返回一个月的第一天在星期几
		var firstDayOfMonth = new Date(this.setdate);
		firstDayOfMonth.setDate(1);
		for (var i = 1; i <= firstDayOfMonth.getDay(); i++) {
			cells.push(this.dayCell());
		}
	}
	var days = this.date_dayCounts[this.setdate.getMonth()];
	if (this.setdate.getMonth() == 1 && this.setdate.getFullYear() % 4 == 0) {
		days++;
	}
	for (var i = 0; i < days; i++) {
		cells.push(this.dayCell(i + 1));
	}
	while (cells.length > 0) {
		var row = document.createElement("tr");
		for (var i = 0; i < 7; i++) {
			var cell = cells.length > 0 ? cells.shift() : this.dayCell();
			row.appendChild(cell);
		}
		body.appendChild(row);
	}
	body.appendChild(row);
	this.cal.appendChild(body);
	this.dropdown.appendChild(this.cal);
}, padout:function (number) {
	return (number < 10) ? "0" + number : number;
}, killDropdown:function (dropdown) {
	if (CDate.dropdown != null) {
		if (CDate.dropdown.parentNode) {
			CDate.dropdown.parentNode.removeChild(CDate.dropdown);
		}
		CDate.dropdown = null;
		Event.stopObserving(document, "mousedown", CDate.killDropdown);
	}
}};

