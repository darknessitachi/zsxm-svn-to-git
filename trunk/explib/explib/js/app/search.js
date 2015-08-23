/*---------------------------------------------------------------------------*\
|  Subject:       Search Class                                               |
|  Version:       1.0.0                                                      |
|  Author:        Wangxj                                                     |
|  FileName:      search.js                                                  |
|  Created:       2011-5-19                                                  |
|  Comment:       对高级搜索的封装                								 |
\*---------------------------------------------------------------------------*/
SQ = Class.create();
SQ.prototype = {
    initialize : function (oid){
    	this.Index = 1;
    	this.OBJID = oid;
    	this.TableId = 'SQ_TABLE_';
    	this.SHOWX = parseInt(getPageSizeMe().x-parseInt($(oid).style.width.replaceAll('px','')))/2;
		this.SHOWY = parseInt(getPageSizeMe().y-parseInt($(oid).style.height.replaceAll('px','')))/2;
		this.FieldStr = '';
		this.initField();
		$(this.OBJID).innerHTML += this.createTable();
		this.OldInnerHTML = $(this.OBJID).innerHTML;
	},
    createTable:function(){
    	var str = new StringBuffer('<div style="width:100%;height:'+($(this.OBJID).style.height.replaceAll('px','')-80)+'px;overflow:auto"><table class="fxtable" id="'+this.TableId+'" >');
    	str.append('<tr>');
    	str.append('<td class="bt" style="width:8%">行号</td>');
    	str.append('<td class="bt" style="width:30%">字段</td>');
    	str.append('<td class="bt" style="width:15%">条件</td>');
    	str.append('<td class="bt" style="width:35%">字段值</td>');
    	str.append('<td class="bt" style="width:12%">逻辑符</td>');
    	str.append('</tr>');
    	str.append('<tr>');
    	str.append('<td>'+this.Index+'</td>');
    	str.append('<td>'+this.getField()+'</td>');
    	str.append('<td>'+this.getWhere()+'</td>');
    	str.append('<td>'+this.getInput()+'</td>');
    	str.append('<td>'+this.getRelation()+'</td>');
    	str.append('</tr>');
    	str.append('</table></div>');
    	++this.Index;
    	return str.toString();
    },
    initField:function(){
    	var ss = '';
    	var index = this.Index;
    	var ajax = new AppAjax("sys!getSearchField.do",function(data){
    		ss = data;
    	});
    	ajax.setAsyn(false);
    	ajax.submit();
    	this.FieldStr =  ss; 
    },
    getField:function(){
    	var data = this.FieldStr;
    	var str = new StringBuffer();
    	str.append('<select name="selfield" id="selfield'+this.Index+'" style="width:130px" onchange="SQFun.changeSel(this.value,\''+this.Index+'\')">');
    	if(data != null && data.data != null ){
    		var d = data.data;
    		for(var i=0;i<d.length;i++){
    			str.append('<option value="'+d[i].swhere+'">'+d[i].smc+'</option>');
    		}
    	}
    	str.append('</select>');
    	return str.toString();
    },
    getWhere:function(){
    	var str = new StringBuffer();
    	str.append('<select name="selwhere" id="selwhere'+this.Index+'">');
		str.append('<option value="=">等于</option>');
		str.append('<option value="like">近似于</option>');
		str.append('<option value=">=">大于等于</option>');
		str.append('<option value="<=">小于等于</option>');
		str.append('<option value="!=">不等于</option>');
		str.append('<option value="in">包含</option>');
		str.append('</select>');
		return str.toString();
    },
    getInput:function(){
    	var str = new StringBuffer();
    	str.append('<div id="dist'+this.Index+'"><input name="selvalue" id="selvalue'+this.Index+'" style="width:180px"/></div>');
    	return str.toString();
    },
    getRelation:function(){
    	var str = new StringBuffer();
    	str.append('<select  name="selappend" id="selappend'+this.Index+'">');
    	str.append('<option value="and">并且</option>');
    	str.append('<option value="or">或者</option>');
    	str.append('</select>');
    	return str.toString();
    },
    show:function(){
    	$(this.OBJID).style.left = (this.SHOWX-30) + "px";
		$(this.OBJID).style.top = (this.SHOWY-50) + "px";
    	$(this.OBJID).style.display = 'block';
    },
    close:function(){
    	$(this.OBJID).style.display = "none";
    },
    destroy:function(){
    	$(this.OBJID).innerHTML = '';
    	this.Index = 1;
    },
    appendRow:function(){
    	var table= $(this.TableId);
		var newRow = table.insertRow(-1);
		newCell = newRow.insertCell(0);
		newCell.style.textAlign = "left";
		newCell.innerHTML = this.Index; //td的内容
		
		newCell1 = newRow.insertCell(1);
		newCell1.style.textAlign = "left";
		newCell1.innerHTML = this.getField(); //td的内容
		
		newCell2 = newRow.insertCell(2);
		newCell2.style.textAlign = "left";
		newCell2.innerHTML = this.getWhere(); //td的内容
		
		newCell3 = newRow.insertCell(3);
		newCell3.style.textAlign = "left";
		newCell3.innerHTML = this.getInput(); //td的内容
		
		newCell4 = newRow.insertCell(4);
		newCell4.style.textAlign = "left";
		newCell4.innerHTML = this.getRelation(); //td的内容
		++this.Index;
	},
	deleteRow:function(){
		if(this.Index > 2){
			var table = $(this.TableId);
	      	table.deleteRow(--this.Index);
      	}
	}
};

var _sq_ = null;
var SQFun = {
	appendRow:function(){
		_sq_.appendRow();
	},
	deleteRow:function(){
		_sq_.deleteRow();
	},
	changeSel:function(v,n){
		var vs = v.split('#');
		if(vs[1] == 'text'){
			$('dist'+n).innerHTML = '<input name="selvalue" id="selvalue'+n+'" style="width:180px"/>';
		}else if(vs[1] == 'date'){
			$('dist'+n).innerHTML = '<input type="text" class="Wdate" name="selvalue" id="selvalue'+n+'" style="text-align:left;width:180" value="" onfocus="new WdatePicker(this,\'%Y-%M-%D\')" MINDATE="1930-01-01" readonly/>&nbsp;';
		}else if(vs[1] == 'custom'){
			var s = new StringBuffer();
			s.append('<select style="width:180px" name="selvalue" id="selvalue'+n+'">');
			var d = vs[2].split(',');
			for(var i=0;i<d.length;i++){
				var dd = d[i];
				s.append('<option value="'+dd.split('&')[0]+'">'+dd.split('&')[1]+'</option>');	
			}
			s.append('</select>');
			$('dist'+n).innerHTML = s.toString()+'&nbsp;';
		}else if(vs[1] == 'select'){
			var ajax = new AppAjax("sys!getChangeSel.do?lbid="+vs[2],function(data){
				var str = new StringBuffer("<select name='selvalue' id='selvalue"+(n)+"' style='width:215px'>");
				if(data != null && data.data.length>0){
					for(var i=0;i<data.data.length;i++){
						str.append('<option value="'+data.data[i].dm+'">'+data.data[i].dm+'-'+data.data[i].mc+'</option>');
					}
				}
				str.append('</select>');
				$('dist'+(n)).innerHTML = str.toString();
			}).submit();
		}
	},
	search:function(objid){
		if(!_sq_){
			_sq_ = new SQ(objid);
		}
		_sq_.show();
	},
	destory:function(){
		_sq_.Index = 2;
		$(_sq_.OBJID).innerHTML = _sq_.OldInnerHTML;
	},
	getSQL:function(){
		if(!_sq_){
			return "";
		}
		var TX = _sq_.Index-1;
		if(TX == 0){
			return "";
		}
		if(TX == 1){
			if($('selvalue'+TX).value.trim() == '' && $('selwhere').value == '='){
				return "";
			}
		}
		var str = new StringBuffer();
		for(var i=1;i<=TX;i++){
			var vs = $('selfield'+i).value.split('#');
			if(vs[0].indexOf('@') >= 0){
				str.append(' rcid in (select distinct rcid from '+vs[3]+' where ');
				if(vs[1] == 'date'){
					str.append("  convert(varchar(20),"+vs[0].replaceAll('@','') +",23) "+ $('selwhere'+i).value );
					if( $('selwhere'+i).value=='like' ){
						str.append("  '%"+$('selvalue'+i).value+"%' ");
					}else{
						str.append("  '"+$('selvalue'+i).value+"' ");
					}
				}else if(vs[1] == 'select'){
					str.append("  isnull("+vs[0].replaceAll('@','') +",'') "+ $('selwhere'+i).value );
					if( $('selwhere'+i).value=='like' ){
						str.append("  '"+$('selvalue'+i).value+"%' ");
					}else{
						str.append("  '"+$('selvalue'+i).value+"' ");
					}
				}else{
					str.append("  isnull("+vs[0].replaceAll('@','') +",'') "+ $('selwhere'+i).value );
					if( $('selwhere'+i).value=='like' ){
						str.append("  '%"+$('selvalue'+i).value+"%' ");
					}else{
						str.append("  '"+$('selvalue'+i).value+"' ");
					}
				}
				str.append( ' ) ' );
			}else{
				if(vs[1] == 'date'){
					str.append("  convert(varchar(20),"+vs[0] +",23) "+ $('selwhere'+i).value );
					if( $('selwhere'+i).value=='like' ){
						str.append("  '%"+$('selvalue'+i).value+"%' ");
					}else{
						str.append("  '"+$('selvalue'+i).value+"' ");
					}
				}else if(vs[1] == 'select'){
					str.append("  isnull("+vs[0] +",'') "+ $('selwhere'+i).value );
					if( $('selwhere'+i).value=='like' ){
						str.append("  '"+$('selvalue'+i).value+"%' ");
					}else{
						str.append("  '"+$('selvalue'+i).value+"' ");
					}
				}else{
					str.append("  isnull("+vs[0] +",'') "+ $('selwhere'+i).value );
					if( $('selwhere'+i).value=='like' ){
						str.append("  '%"+$('selvalue'+i).value+"%' ");
					}else{
						str.append("  '"+$('selvalue'+i).value+"' ");
					}
				}
				
			}
			if( i != TX ){
				str.append("  "+$('selappend'+i).value+"  ");
			}
		}
		
		return str.toString();
	}
}