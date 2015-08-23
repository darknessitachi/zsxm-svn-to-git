/**
* 	wangxj
**/
var  COM = {
	/**
	*  检查 checkbox 返回Array
	*  id: checkbox 的位置
	*  v:  checkbox 的值	
	**/
	checkbox: function(id){
		var reV = new Array() ;
		var ele = document.getElementsByName(id);
		if(ele){
			if(ele.length > 0){
				for(var i=0;i<ele.length;++i){
					if(ele[i].checked == true){
						reV.push({id:i,v:ele[i].value});
					}
				}
			}else{
					if(ele.checked == true)
					{
						reV.push({id:1,v:ele.value});
					}
			}
		}
		return reV;
	},
	/**
	*选择所有checkbox
	*/
	checkboxAll:function(id,type){
		var ele = eval('document.all.'+id);
		if(ele){
			if(ele.length > 1){
				for(var i=0;i<ele.length;i++){
					ele[i].checked = type;
				}
			}else{
				ele.checked=type;
			}
		}
	}
	,
	/**
	*	检测所输入代码是否符合相应的级次 (333)
	*   id: 值
	*	level: 传入的级次
	*/
	checklevel:function(id,level){
		if(level != '' && id != ''){
			var idlen = id.length;
			var lastlevel = 0;
			for(var i =0;i<level.length;i++){
				if( (parseFloat(level.substring(i,i+1))+parseFloat(lastlevel)) == idlen){
					return true;
				}
				lastlevel += parseFloat(level.substring(i,i+1));
			}
			return false;
		}
	}
	,
	/**
	*	获取当前代码相应的（上 或 下）一 级次
	*   id: 值 (如果传入的值是空： 则返回第一级)
	*	level: 传入的级次
	*   bz:  1:  获取当前代码的下一级长度 (如果当前代码已是最后一级：则返回：-1)
	*		 2:  获取当前代码的上一级长度 (如果当前代码是第一级：则返回：-2)
	*/
	getlevel:function(id,level,bz){
		if(id == null || id == ''){ return level.substring(0,1)}
		if(level != '' && id != ''){
			var idlen = id.length;
			var lastlevel = 0;
			for(var i =0;i<level.length;i++){
				if( (parseFloat(level.substring(i,i+1))+parseFloat(lastlevel)) == idlen){
					if(bz == 1){
						if( (i+1) < level.length ){
							return level.substring(i+1,i+2);
						}else{
							return -1;
						}
					}else{
						if( i!=0 ){
							return level.substring(i-1,i);
						}else{
							return -2;
						}
					}
				}
				lastlevel += parseFloat(level.substring(i,i+1));
			}
			return false;
		}
	}
	,
	getRadioV:function(name){
		var temp=document.getElementsByName(name);
		if (temp != null && temp != 'undefined'){
			for (i=0;i<temp.length;i++){  
				if(temp[i].checked){
					return temp[i].value;
				}
			}
		}
		return "";
	},
	/*
	*	数值输入验证
	*/
	isNum:function(id)
	{
		var e = document.getElementById(id);
			
		if(e.value != "")
		{
			if(isNaN(e.value))
			{//非法数字		
				alert("该项输入要求数值型！");
				e.select();
				e.focus();
				return false;
			}
		}else{e.value = '0.00';}

	}
	,
	isDate : function (value, Dilimeter)
{	 if(Dilimeter==null || Dilimeter =="")Dilimeter="-";
	  value = value.replaceAll(Dilimeter,"");
	  if(value.length!=8 || !this.isNumber(value)) return false;  
		var year = value.substring(0,4);
		if(year>"2100" || year< "1900")	return false;
		
		var month = value.substring(4,6);
		if(month>"12" || month< "01") return false;
		
		var day = value.substring(6,8);
		if(day>this.getMaxDay(year,month) || day< "01") return false;
		
		return true;  
},
 isNumber : function( s ){   
	var regu = "^[0-9]+$";
	var re = new RegExp(regu);
	if (s.search(re) != -1) {
	   return true;
	} else {
	   return false;
	}
},
formatDate :function (obj){
   var datetime = obj.value;
   if(datetime == "")
       return true;
   datetime = datetime.replaceAll("-","");
   if(datetime.length!=8||!this.isDate(datetime))
   {
      alert("输入的时间格式不正确,输入格式应为:YYYYMMDD或YYYY-MM-DD");
      obj.value ="";
      obj.focus();
      return false;    
   }
   obj.value = datetime.substring(0,4)+"-"+datetime.substring(4,6)+"-"+datetime.substring(6,8); 
   return true;
},
getMaxDay :function (year,month) {
	if(month==4||month==6||month==9||month==11)
		return "30";
	if(month==2)
		if(year%4==0&&year%100!=0 || year%400==0)
			return "29";
		else
			return "28";
	return "31";
},
	isNumChar:function(id){
		var e = document.getElementById(id);
			
		if(e.value != "")
		{
			if(isNaN(e.value))
			{//非法数字		
				alert("该项输入要求数值类型！");
				e.select();
				e.focus();
				return false;
			}
		}
	},
	/**
	* 格式化金额
	*/
	  formatNum : function (num, digit) {
		if(digit){
			digit = digit;
		}else{
			digit = 2;
		}
		num = num + ",";
		num = this.replaceComma(num);
		num = parseFloat(num).toFixed(digit);
		if (!/^(\+|-)?(\d+)(\.\d+)?$/.test(num)) {
			return "0.00";
		}
		var a = RegExp.$1, b = RegExp.$2, c = RegExp.$3;
		var re = new RegExp().compile("(\\d)(\\d{3})(,|$)");
		while (re.test(b)) { 
			b = b.replace(re, "$1,$2$3");
		}
		if (c && digit && new RegExp("^.(\\d{" + digit + "})(\\d)").test(c)) {
			if (RegExp.$2 > 4) {
				c = (parseFloat(RegExp.$1) + 1) / Math.pow(10, digit);
			} else {
				c = "." + RegExp.$1;
			}
		}
		var l_num = a + "" + b + "" + (c + "").substr((c + "").indexOf("."));
		if (l_num.indexOf(".") < 0) {
			l_num += ".00";
		}
		return l_num;
	},
	/**
	**去除金额中的逗号
	*/
	 replaceComma  : function (n) {
		var l_n = "";
		for (var i = 0; i < n.length; i++) {
			var str = n.substring(i, i + 1);
			l_n += str.replace(",", "");
		}
		return l_n;
	},
	checkIsMoney : function (val) {
		var regu = "^[0-9]+[.]{0,1}[0-9]{0,3}$";
		var re = new RegExp(regu);
		return re.test(val);
	},
	 checkIsNumber : function (val) {
		var regu = "^[-]{0,1}[0-9]+[.]{0,1}[0-9]{0,3}$";
		var re = new RegExp(regu);
		return !(val.search(re) == -1);
	},
	/*
 * 根据传入的运算符号对两个金额作运算 n1:传入的参数 n2:传入的参数 symbol:计算符号 "+","-","*","/" fix:保留尾数
 */
 operateMoneyEx : function(n1,n2,symbol,fix){
   var l_n1 = (n1 == "")? "0.00":this.replaceComma(n1);
   var l_n2 = (n2 == "")? "0.00":this.replaceComma(n2);
   var l_n = "0.00";
   switch(symbol)
   {
		   case "+":
			   l_n=(parseFloat(l_n1)+parseFloat(l_n2)).toFixed(fix);
			   break;
		   case "-":
			   l_n=(parseFloat(l_n1)-parseFloat(l_n2)).toFixed(fix);
			   break;
		   case "*":
			   l_n=(parseFloat(l_n1)*parseFloat(l_n2)).toFixed(fix);
			   break;
		   case "/":
	           l_n=(parseFloat(l_n1)/parseFloat(l_n2)).toFixed(fix);
		       break;
		   case "%":
	           l_n=(parseFloat(l_n1)%parseFloat(l_n2)).toFixed(fix);
	   }
	   return this.formatNum(l_n,0);
	}
}