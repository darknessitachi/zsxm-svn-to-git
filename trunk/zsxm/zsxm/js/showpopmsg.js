
   <!--  
     
  /**//*  
   **    ==================================================================================================  
   **    类名：CLASS_MSN_MESSAGE  
   **    功能：提供类似MSN消息框  
  **    示例：  
      ---------------------------------------------------------------------------------------------------  
    
             var MSG = new CLASS_MSN_MESSAGE();  
                  MSG.show();  
    
      ---------------------------------------------------------------------------------------------------  
  **    修改：feng
  **    邮件：imxufeng@hotmail.com  
  **    日期：2006-11-01  
  **    ==================================================================================================  
 */  
    
   
 /**//*  
  *    消息构造  
   */  
  function CLASS_MSN_MESSAGE(id,width,height,caption,message,target,action){  
      this.id     = id;  
      this.caption= caption;  
      this.message= message;  
      this.target = target;  
      this.action = action;  
      this.width    = width?width:200;  
      this.height = height?height:120;  
      this.timeout= 500;  
      this.speed    = 20; 
      this.step    = 1; 
      this.right    = screen.width -7;  
      this.bottom = screen.height; 
      this.left    = this.right - this.width; 
      this.top    = this.bottom - this.height; 
      this.timer    = 0; 
      this.pause    = false;
      this.close    = false;
      this.autoHide    = false;
  }  
    
  /**//*  
   *    隐藏消息方法  
   */  
  CLASS_MSN_MESSAGE.prototype.hide = function(){  
      if(this.onunload()){  
  
          var offset  = this.height>this.bottom-this.top?this.height:this.bottom-this.top; 
          var me  = this;  
   
          if(this.timer>0){   
              window.clearInterval(me.timer);  
          }  
   
          var fun = function(){  
              if(me.pause==false||me.close){
                 var x  = me.left; 
                  var y  = 0; 
                  var width = me.width; 
                  var height = 0; 
                  if(me.offset>0){ 
                      height = me.offset; 
                  } 
       
                 y  = me.bottom - height; 
       
                 if(y>=me.bottom){ 
                      window.clearInterval(me.timer);  
                      me.Pop.hide();  
                  } else { 
                      me.offset = me.offset - me.step;  
                  } 
                  me.Pop.show(x,y,width,height);    
              }             
          }  
   
          this.timer = window.setInterval(fun,this.speed)      
      }  
  }  
    
  /**//*  
   *    消息卸载事件，可以重写  
   */  
  CLASS_MSN_MESSAGE.prototype.onunload = function() {  
      return true;  
  }  
  /**//*  
   *    消息命令事件，要实现自己的连接，请重写它  
   *  
   */  
  CLASS_MSN_MESSAGE.prototype.oncommand = function(){  
      this.close = true;
      this.hide();  
 }  
   
 /**//*  
  *    消息显示方法  
  */  
 CLASS_MSN_MESSAGE.prototype.show = function(){  
 
     var oPopup = window.createPopup(); //IE5.5+  
     
     this.Pop = oPopup;  
   
     var w = this.width;  
     var h = this.height;  
   
     var str = "<DIV style='BORDER-RIGHT: #455690 1px solid; BORDER-TOP: #a6b4cf 1px solid; Z-INDEX: 99999; LEFT: 0px; BORDER-LEFT: #a6b4cf 1px solid; WIDTH: " + w + "px; BORDER-BOTTOM: #455690 1px solid; POSITION: absolute; TOP: 0px; HEIGHT: " + h + "px; BACKGROUND-COLOR: #c9d3f3'>"  
        str+="<table style=\"BORDER-TOP: #ffffff 1px solid; BORDER-LEFT: #ffffff 1px solid\" cellSpacing=0 cellPadding=0 width=\"100%\" bgColor=#AFDCF3 border=0>";
		str+="<TBODY>";
		str+="<TR bgColor=#BBDBF2>";
		str+="<TD style=\"font-size: 12px; background-image: url(''); color: #0f2c8c\" width=30 height=24></TD>";
		str+="<TD style=\"font-weight: bold; font-size: 12px;  color:#C55F5C; padding-left: 4px; padding-top: 2px\" vAlign=center width=\"100%\">提示信息:</TD>"
		str+="</TD><TD style=\"background:#BBDBF2; padding-right: 2px; padding-top: 2px\" vAlign=center align=right width=19><span title=关闭 style=\"CURSOR:hand;color:white;font-size:12px;font-weight:bold;margin-right:4px;\" id='btSysClose' >×</span></TD>"
		str+="</TR><TR>"
		str+="<TD style=\"background:#F3F6F9; padding-right: 1px; padding-bottom: 1px\" colSpan=3 height="+(h-30)+"><DIV id='btCommand' style=\"BORDER-RIGHT: #b9c9ef 1px solid; PADDING-RIGHT: 13px;overflow:auto; word-break : break-all;BORDER-TOP: #728eb8 1px solid; PADDING-LEFT: 13px; FONT-SIZE: 12px; PADDING-BOTTOM: 3px; BORDER-LEFT: #728eb8 1px solid; WIDTH: 100%; COLOR: #1f336b; PADDING-TOP: 12px; BORDER-BOTTOM: #b9c9ef 1px solid; HEIGHT: 100%\">"+this.message+"</TD>"
		str+="</TR></TBODY>";
        str+="</table>";  
        str += "</DIV>"   
   
     oPopup.document.body.innerHTML = str; 
     
   
     this.offset  = 0; 
     var me  = this;  
 
     oPopup.document.body.onmouseover = function(){me.pause=true;}
     oPopup.document.body.onmouseout = function(){me.pause=false;}
 
     var fun = function(){  
         var x  = me.left; 
         var y  = 0; 
         var width    = me.width; 
         var height    = me.height; 
  
             if(me.offset>me.height){ 
                 height = me.height; 
             } else { 
                 height = me.offset; 
             } 
  
         y  = me.bottom - me.offset; 
         if(y<=me.top){ 
             me.timeout--; 
             if(me.timeout==0){ 
                 window.clearInterval(me.timer);  
                 if(me.autoHide){
                     me.hide(); 
                 }
             } 
         } else { 
             me.offset = me.offset + me.step; 
         } 
         me.Pop.show(x,y,width,height);    
  
     }  
   
     this.timer = window.setInterval(fun,this.speed)      
   
      
   
     var btClose = oPopup.document.getElementById("btSysClose");  
   
     btClose.onclick = function(){  
         me.close = true;
         me.hide();  
     }  
   
     var btCommand = oPopup.document.getElementById("btCommand");  
     btCommand.onclick = function(){  
         me.oncommand();  
     }    
 }  
 /**//* 
 ** 设置速度方法 
 **/ 
 CLASS_MSN_MESSAGE.prototype.speed = function(s){ 
     var t = 20; 
     try { 
         t = praseInt(s); 
     } catch(e){} 
     this.speed = t; 
 } 
 /**//* 
 ** 设置步长方法 
 **/ 
 CLASS_MSN_MESSAGE.prototype.step = function(s){ 
     var t = 1; 
     try { 
         t = praseInt(s); 
     } catch(e){} 
     this.step = t; 
 } 
   
 CLASS_MSN_MESSAGE.prototype.rect = function(left,right,top,bottom){ 
     try { 
         this.left        = left    !=null?left:this.right-this.width; 
         this.right        = right    !=null?right:this.left +this.width; 
         this.bottom        = bottom!=null?(bottom>screen.height?screen.height:bottom):screen.height; 
         this.top        = top    !=null?top:this.bottom - this.height; 
     } catch(e){} 
 } 
var MSG1;
function showMsg(content,w,h,autohide){
	if(MSG1){
		MSG1.hide();
	}
	content = content || "";
	w = w || 250;
	h = h || 140;
	
	MSG1 = new CLASS_MSN_MESSAGE("showPopMsg",w,h,"信息提示：",content);  
     MSG1.rect(null,null,null,screen.height-50); 
     MSG1.speed  = 10; 
     MSG1.step  = 3; 
     if(autohide){
		if(autohide == true){
			MSG1.autoHide = true;
		}else{
			MSG1.autoHide = false;
		}	
	}
     MSG1.show(); 
}

