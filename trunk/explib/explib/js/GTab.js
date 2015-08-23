

var _lastTabId = "";
var _lastAction = "";
function GTab(rDiv,tabs){
	this.rDiv = rDiv;
	this.DefaultAction = "";
	this.DefalutId = "";
	this.SelectedCss = "divchildtabCurrent";
	this.NormalCss = "divchildtab";
	this.TabFrameName = "_tabframe";
	this.Height = (getSize().h-40);
	this.Width = (getSize().w-10);
	this.DefalutMode = '';
	this.DefalutGid = '';
	this.param = [];
	this.options = {
		id: "",
		name:"",
		action:"",
		selected:false,
		model:'',
		conId:'',
		height:'',
		width:''
	};
	this._createTab = function(){
		var sb = new StringBuffer();
		
		var _options = this.options.item;
		var _len = _options.length;
		this.Height = this.options.height || (getSize().h-40);
		this.Width = this.options.width || (getSize().w-10);
		
		sb.append('<table width="100%" border="0" cellpadding="6" cellspacing="0" class="blockTable">');
		sb.append('<tr>');
		sb.append('<td height="26" valign="middle" class="blockTd"> ');
		sb.append("<table width='100%' border='0' cellpadding='0' cellspacing='0' style='background:url(images/tab/divchildtabBarBg.gif) repeat-x left bottom; margin-bottom:1px;repeat-x left bottom; margin-bottom:1px;'>");
		sb.append('<tr> ');
		sb.append('<td valign="bottom" height="30" style="padding:0 0 0 4px;_padding:0 0 0 2px;">');
		for(var i=0;i<_len; ++i){
			var tab = _options[i];
			var tabid = (tab.id||('_tab_'+(i+1)));
			var img = (tab.img||'images/tab/icon003a8.gif');
			sb.append("<div style='-moz-user-select:none;' class='"+(tab.selected?this.SelectedCss:this.NormalCss)+"' onselectstart='return false' id='"+tabid+"' xh='"+i+"' model='"+(tab.model=='ajax'?"1":"0")+"' action='"+tab.action+"' gid='"+tab.gid+"' onclick=\"_setTab(this.id,'"+this.SelectedCss+"','"+this.NormalCss+"','"+this.TabFrameName+"','"+this.Height+"','"+this.Width+"','"+tab.action+"')\" onMouseOver=\"Tab.onChildTabMouseOver(this);_setTab(this.id,'"+this.SelectedCss+"','"+this.NormalCss+"','"+this.TabFrameName+"','"+this.Height+"','"+this.Width+"','"+tab.action+"')\" onMouseOut='Tab.onChildTabMouseOut(this)'>");
			sb.append("<img src='"+img+"'/>");
			sb.append("<b>"+tab.name+"</b>");
			sb.append("</div>");
			if(tab.selected || i==0){this.DefalutId=tabid;this.DefaultAction = tab.action;_lastAction=this.DefaultAction;_lastTabId=tabid ;this.DefalutMode=(tab.model=='ajax'?"1":"0");this.DefalutGid=tab.gid;}
		}
		sb.append("</td>");
		sb.append("</tr>");
		sb.append("</table>");
		sb.append("</td>");
		sb.append("</tr>");
		
		sb.append("<tr>");
		sb.append("<td>");
		
		for(var i=0;i<_len;++i){
			var tab = _options[i];
			var issel = tab.selected||false;
			if($(tab.action) != null && $(tab.action)!= 'undefined'){
				if(issel){
					$(tab.action).style.display = '';
				}else{
					$(tab.action).style.display = 'none';
				}
				//$(tab.action).style.height = tab.height||this.Height;
				//$(tab.action).style.width = tab.width||this.Width;
			}else{
				//sb.append("<div id='"+tab.action+"' style='display:"+(issel?'\"\"':'none')+"' ></div>");			
			}
			
		}
		
		sb.append("</td>");
		sb.append("</tr>");
		sb.append("</table>");
		
		$(rDiv).innerHTML = sb.toString();
	};
	this.setAction = function(_id,_a){
		if(!$(_id))
			return ;
		$(_id).onclick = '_setTab(\"'+_id+'\",\"'+this.SelectedCss+'\",\"'+this.NormalCss+'\",\"'+this.TabFrameName+'\",\"'+_a+'\")';
	};
	this.setName = function(_id,_n){
		if(!$(_id))
			return ;
		$(_id).innerText = _n;
	};
	this.removeTab = function(_id){
		if(!$(_id))
			return ;
		$(_id).removeNode(true);
	};
	this.add = function(_id,name,value){
		if(!$(_id))
			return ;
		if(name instanceof Object){
		 for(var o in name){
			 if(typeof o == "function")
				continue;
			this.param[this.param.length] = [o,name[o]];
		 }  
	   }else{
	   	this.param[this.param.length] = [name,(typeof value == "undefined") ? $F(name) : value];
       }
       $(_id).actoin = $(_id).action.split('?')[0]+'?'+AppAjaxUtils.toQueryString(this.param);
    };
    
	this.clear = function(){
       this.param = [];
       return this;
    };
	this.init = function(){
		Object.extend(this.options,tabs);
		//$(rDiv).style.width = this.options.width || (getSize().w-10);
  		this._createTab();
  	};
  	this.init();
  	
}

function _setTab(curid,sn,nn,tf,h,w,curact){
	if(_lastTabId != curid){
		$(_lastTabId).className = nn;
		$(curid).className = sn;
		$(_lastAction).style.display = 'none';
		$(curact).style.display = '';
		_lastTabId = curid;
		_lastAction = curact;
	}
	
	if(typeof tabclick != 'undefined'){
		tabclick(curid);
	}
}

var Tab = {};
Tab.onChildTabMouseOver = function(ele){
	if(ele.className=="divchildtab")	ele.className="divchildtabHover"
}
Tab.onChildTabMouseOut = function(ele){
	if(ele.className=="divchildtabHover")	ele.className="divchildtab"
}
Tab.getChildTab = function(id){
	return $("_ChildTabFrame_"+id);
}
