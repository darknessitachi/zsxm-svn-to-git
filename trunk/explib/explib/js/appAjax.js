<!--
/*---------------------------------------------------------------------------*\
|  Subject:       AppAjax Class                                               |
|  Version:       1.0.0                                                       |
|  Author:        DarGoner                                                    |
|  FileName:      AppAjax.js                                                  |
|  Created:       2007-8-19                                                   |
|  Modify:        2007-8-19                                                   |
|  Comment:       封装了Ajax对于JSON的调用,此类依赖于 prototype.js                 |
\*---------------------------------------------------------------------------*/

AppAjax = Class.create();

AppAjax.constant = {
	message  : {
	  open  : true,
	  image  : "images/skin0/other/load_page.gif",
      text  : "Loding..."
	}
};

AppAjax.prototype = {
    initialize : function (action , callback , vcallback ,ecallback){
        this.action = (action) ? action : null; 
	    this.callback = (callback) ? callback : null;
	    this.vcallback = (vcallback) ? vcallback : null;
		this.ecallback = (ecallback) ? ecallback : null;
	    this.updater = null;
	    this.evalScript = false;
	    this.evalResult = true;
        this.param = [];
	    this.block = false;
		this.xml = false;
		this.asynchronous = true;
		this.o = null;
		//this.loader = new Loader();
	    this.message = { 
		   open : AppAjax.constant.message.open ,
		   text : AppAjax.constant.message.text
	    };
    },

    setAction : function (bizAction){
       this.action = bizAction;
    },
	
	setXml : function (xml){
	   this.xml = xml;
	},

	setAsyn:function(as){
	   this.asynchronous = as;
	},
   
    setCallBack : function (callback){
       this.callback = callback;
    },

	setVCallBack : function (vcallback){
       this.vcallback = vcallback;
    },
	
	setECallBack : function (ecallback){
       this.ecallback = ecallback;
    },
    
    setEvalResult : function (evalOk){
       this.evalResult = evalOk;
    },
    
	setBlock : function (isBlock){
	   this.block = isBlock;
	},

	setMessage : function (isopen,message){
	   this.message.open = isopen ;
	   if(typeof message == "string" && message != ""){ 
		   this.message.text = message;
	   }
	},
	
	setUpdater : function (updaterElement){
	   this.updater = updaterElement;
	},
	
	setEvalScript: function (evalScript){
	   this.evalScript = evalScript;
	},
    
    add : function (name,value){
	   if(name instanceof Object){
		 for(var o in name){
			if(o == "toJSONString")
				continue;
			this.param[this.param.length] = [o,name[o]];
		 }  
	   }else{
         this.param[this.param.length] = [name,(typeof value == "undefined") ? $F(name) : value];
       }
    },
   
    clear : function(){
       this.param = [];
    },
	
    submit : function (){
       return this._submit(AppAjaxUtils.toQueryString(this.param));
    },

    submitForm : function (frmName){
       return this._submit(Form.serialize(frmName));
    },
	
    _submit : function(param){
 
       if(!this.action){
	       alert("\u8bf7\u5148\u8bbe\u7f6e\u63d0\u4ea4\u7684 Action !");
		   return false;
	   }
	   if(this.block && Ajax.activeRequestCount > 0){
	       alert("\u8bf7\u5148\u7b49\u5f85\u5df2\u8fd0\u884c\u5904\u7406\u5b8c\u6210 !");
		   return false;
	   }
	   var options = {
	       method : "post",
	       requestHeaders : ["if-modifed-since","0"],
		   parameters : param,
		   asynchronous : this.asynchronous,
		   onLoading : this.onLoading.bind(this) ,
		   onComplete : this.onSuccess.bind(this),
		   onFailure : this.onFailure.bind(this),
		   onException : this.onException.bind(this)
	   };
	   if(!this.updater){
	       new Ajax.Request(this.action,options);
	   }else{
	       if(this.evalScript){
	          options.evalScripts = true;
	       }
	       new Ajax.Updater(this.updater,this.action,options);
	   }
	   return true;
    },
    
	onLoading  : function(request){
	   if(this.message.open){
		  AppAjaxUtils.showMessage(this.message.text);
	   }
	  // this.loader.setStatus(1,event);
	},
	onSuccess : function (request){
	   if(!request.status || (request.status < 200 || request.status >= 300)){
	   	   return ;
	   }
	  // this.loader.setStatus(0);
	   if(this.message.open){
		  AppAjaxUtils.hideMessage();
	   }
	   try{
	       try{
	          if(request.responseText.indexOf("<script>") >= 0){
	             eval(AppAjaxUtils.removeHtml(request.responseText)); //主要是防止登录超时时的页面定位问题
	          }
	       }catch(e){
	          //don't do it
	       }
		   if(this.callback){
		      if(!this.updater){
		         try{
		            if(this.evalResult && !this.xml){
					    request = eval('(' + request.responseText + ')');
					}else{
						request = request.responseXML;
					}
		         }catch(e){}
		      }
			  this.callback(request);
		   }
	   }catch(e){
	   }
	   if(this.vcallback){
		  this.vcallback();
	   }
	},
    
	onFailure : function (request){
		// this.loader.setStatus(0);
	   if(this.message.open){
		  AppAjaxUtils.hideMessage();
	   }
	   if(this.ecallback){
	   	  this.ecallback(request);
	   }
	   alert("数据通讯异常,请重试!");
	},
	onException : function(loader,e){
		 //this.loader.setStatus(0);
	  if(e && e.message){
	     alert(e.message);
	  }
	  if(Ajax.activeRequestCount > 0){
	     Ajax.activeRequestCount --;
	  }
	} 
};

AppAjaxUtils = Prototype.emptyFunction;

AppAjaxUtils.useLoadingMessage = function (isopen,message){
    AppAjax.constant.message.open = isopen;
	if(typeof message == "string" && message != ""){
	   AppAjax.constant.message.text = message;
	}
};

AppAjaxUtils.showMessage = function (message){
    var loadingMessage = message || AppAjax.constant.message.text;
	var processZone = $("processZone");
	if (!processZone) {
		processZone = document.createElement("div");
		processZone.setAttribute("id", "processZone");
		var imageZone = document.createElement("img");
		imageZone.src=AppAjax.constant.message.image;
		imageZone.align="absmiddle";
		var messageZone = document.createElement("span");
		messageZone.setAttribute("id", "messageZone");
		processZone.appendChild(imageZone);
		processZone.appendChild(messageZone);
		document.body.appendChild(processZone);
	} 
	Element.update("messageZone", "&nbsp;" + loadingMessage);
	Element.show(processZone);
};

AppAjaxUtils.hideMessage = function (){
    if($("processZone")){
	   Element.hide("processZone");
	}
};

AppAjaxUtils.toQueryString = function (array){
    var dest = [];
    if (!array || !(array instanceof Array)) 
		return "";
	array.each(function (sub){
       if(!(sub instanceof Array) || sub.length < 2) 
		   return;
	   dest.push(sub[0] + "="+ encodeURIComponent(sub[1]));
    });
	return dest.join("&");
} 
AppAjaxUtils.removeHtml = function (html){
   if(html == null)
      return "";
   html = html.replace(/\<[^>]*>[1-9]/g,"<");
   html = html.replace(/\<[^>]*>[1-9]/g,"<");
   html = html.replace(/\<[^>]*>/g,"");
   html = html.replace(/&nbsp;/g,"");
   return html;
} ;

function Loader() {
  var self = this;
  this.setStatus = function(s,e) {
    if (s) {
      self.show(e);
    } else {
      self.o.display = 'none';
      self.hide();
    }
  }
  this.show = function(e) {
    try {
      self.move(e); // need event for safari - remove try .. catch later
    } catch(e) {
      // d'oh - will fire on initial/scripted show() call
      return false;
    }
    self.o.style.display = 'block';
    Event.observe(document, "mousemove", self.move);
  }
  this.hide = function() {
    self.o.style.display = 'none';
    Event.stopObserving(document, "mousemove", self.move);
  }
  this.move = function(e) {
    e = e?e:event;
    if (e) {
   
      var offX = 0;
      var offY = 0;
      self.o.style.left = (e.clientX+2+offX)+'px';
      self.o.style.top = (e.clientY-22+offY)+(document.documentElement.scrollTop||document.body.scrollTop||0)+'px';
    }
  }
  this.o = document.createElement('div');
  this.o.id = 'loader';
  this.o.className = 'png';
  this.oFP = document.createElement('div');
  this.oFP.className = 'faceplate';
  this.oP = document.createElement('div');
  this.oP.className = 'progress';
  this.o.appendChild(this.oFP);
  this.o.appendChild(this.oP);
  document.body.appendChild(self.o);
}

//-->