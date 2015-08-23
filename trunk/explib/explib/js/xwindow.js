var zInxWin=998;
var zInxTop=zInxWin + 10;
var subfixName = 'JY';//TaskBar
var winx=1;
WinArea={
  WinOffset:80,
  getWidth: function(){return getPageSize().x;},
  getHeight:function(){return getPageSize().y;},

  TopWinName:null,
  lastX:0,
  lastY:0,
  nameArr:[]
};


function getPageSize(){
	var xScroll,yScroll;

	if (window.innerHeight && window.scrollMaxY){
	xScroll = document.body.scrollWidth;
	yScroll = window.innerHeight + window.scrollMaxY;
	} else if (document.body.scrollHeight > document.body.offsetHeight){
		xScroll = document.body.scrollWidth;
		yScroll = document.body.scrollHeight;
	} else {
	xScroll = document.body.offsetWidth;
	yScroll = document.body.offsetHeight;
	}
	 return {x:xScroll,y:yScroll};
}
function gh(){
  var windowWidth, windowHeight; 
  if (self.innerHeight){  // all except Explorer 
    windowWidth = self.innerWidth; 
    windowHeight = self.innerHeight; 
  } else if (document.documentElement && document.documentElement.clientHeight)
  { // Explorer 6 Strict Mode 
    windowWidth = document.documentElement.clientWidth; 
    windowHeight = document.documentElement.clientHeight; 
  } else if (document.body) { // other Explorers 
    windowWidth = document.body.clientWidth; 
    windowHeight = document.body.clientHeight; 
  }   
  return {x:windowWidth,y:windowHeight};
}
function setFront(sName)
{
	var e=document.getElementById(sName);
	if(e)
	{
		//e.style.display="";
		e.style.zIndex=zInxTop;
		zInxTop += 10;
	}
};

function reSize(sName,h,w,t,l){
	var e=document.getElementById(sName);
	if(h)e.style.height = h + "px";
	if(w)e.style.width = w + "px";
	if(t)e.style.top = e.offsetTop - t  + "px";
	if(l)e.style.left = e.offsetLeft - l + "px";
};
function openXwin(sURL,width,height,sName,modal){
 if(sName=='undefined'||sName==""||!sName){
	sName = 'JY_'+(winx+1);
	winx ++;
 }
 for (var i = 0;i<WinArea.nameArr.length ;i++ )
 {
	if(sName == WinArea.nameArr[i]){
		if(document.getElementById(sName).style.display=="none"){
			showWin(sName);
		}
		setFront(sName);
		return;
	}
 }
CreateMask(sName);
  WinArea.nameArr.push(sName);
  var xWinBorderCssText = "border:1px solid #91CFCE";
  
   	var wmax = WinArea.getWidth();
  	var hmax=WinArea.getHeight();
    var w;
    var h;
	
   var t=0;
   var l=0;
   var nh;
   var nw;

  if(modal){
		  w=wmax;
		  h=hmax;
	  }else{
		  if(width){
				  w=width;
				  h=height;
		          l=parseInt(wmax-w)/2;
		          t=parseInt(hmax-h)/2;
				  
		}
}
  if(t<0)t=0;
  if(l<0)l=0;
 
  if(!modal){   
   WinArea.lastX=l;
   WinArea.lastY=t;
  }
 if(!document.getElementById(sName)){
    var css='position:absolute;background-color:#fff;left:'+l+'px;top:'+t+'px;width:'+ w +'px;height:'+h+'px;z-index:'+zInxTop+';'+xWinBorderCssText;
    if(document.all)
		var ifrm=document.createElement('<iframe FRAMEBORDER="0" APPLICATION="YES" name="'+sName+'" id="'+(sName+subfixName)+'"  style="'+css+'"></iframe>');
		else
		{
			var ifrm=document.createElement('iframe');
			ifrm.name=sName;
			ifrm.id=sName;
			ifrm.style.cssText=css;
		}
		document.body.appendChild(ifrm);
		frames[sName].document.write('<html><body leftmargin=0 topmargin=0 border=0 ondblclick="closeXwin(\''+sName+'\');"><table border=0 width=100% height=100%><tr><td align=center valign=middle style="font-size:12px"><img src="images/skin0/other/pageload.gif" align="absmiddle"/>Page Loading…………</td></tr></table></body></html>');
    
   
  }
  ifrm.src=sURL;
  Show( document.getElementById('jy_mask'),1 );
  zInxTop += 10;
};

function Show(obj, bShow) {
obj = (typeof(obj) == "string" ? S(obj) : obj);
if (obj) obj.style.display= (bShow ? "" : "none");
}

function CreateMask(cid) {
	var id = "jy_mask_"+cid;
	InsertHTML(document.body, "afterBegin",
	['<div id="', id, '" style="position:absolute;top:0;left:0;z-index:998;background:#fff;opacity:0;filter:alpha(opacity=0);width:100%;height:100%;"  onkeypress="return false;" onkeydown="return false;" tabindex="0"></div>'].join(""));
}


function InsertHTML(el, where, html) {
	if ( !el )
	return false;
	try {
	
	if (el.insertAdjacentHTML) {
	el.insertAdjacentHTML( where, html );
	}
	else {
	var range = el.ownerDocument.createRange();
	var isBefore = where.indexOf("before") == 0;
	var isBegin = where.indexOf("Begin") != -1;
	if (isBefore == isBegin) {
	range[isBefore ? "setStartBefore" : "setStartAfter"](el);
	el.parentNode.insertBefore(range.createContextualFragment(html), isBegin ? el : el.nextSibling);
	}
	else {
	var obj = el[isBefore ? "lastChild" : "firstChild"];
	if (obj) {
	range[isBefore ? "setStartAfter" : "setStartBefore"](obj);
	el[isBefore ? "appendChild" : "insertBefore"](range.createContextualFragment(html), obj);
	}
	else {
	el.innerHTML = html;
	}
	}
	}
	return true;
	}
	catch( e ) {
	return false;
	}
}
function minWin(s){
	//document.getElementById(s).style.display = "none";
	document.getElementById("SetInfo").insertAdjacentHTML("afterBegin","<span id='win_span_"+s+"'><a href=javascript:showWin('"+s+"','win_span_"+s+"');>aaa</a>|&nbsp;</span>");
	CD.flyToSmall(s,'win_span_'+s);
	 Show( document.getElementById('jy_mask'),    0 );
}
function showWin(s,spid){
CD.flyToShow(s,spid);
	
	    document.getElementById("SetInfo").removeChild(document.getElementById('win_span_'+s));
		document.getElementById(s).style.zIndex=zInxTop;
		Show( document.getElementById('jy_mask'),    1 );
		zInxTop += 10;

}
function closeXwin(s)
{
	try
	{	
		var dead=document.getElementById(s);
		if(dead==null){
			dead = $(s+subfixName);
		}
		dead.style.display="none";
		dead.src="about:blank";
		document.body.removeChild(dead);
		delete dead;
		//dead=$(s+subfixName);
		//delete dead;
		for (var i = 0;i<WinArea.nameArr.length ;i++ ){
			if(s == WinArea.nameArr[i]){
					delete  WinArea.nameArr[i];	
				}
		}
		  Show( document.getElementById('jy_mask_'+s),    0 );
	}
	catch(ex)
	{
	}
	
};
var isIE = /msie/i.test(navigator.userAgent);
var isFF = /firefox/i.test(navigator.userAgent);
function beginDrag(me,evt){
	try{
	   e = evt || window.event;
	   var move_obj = document.getElementById(me);
	   move_obj.onmousemove = function (){move(evt,move_obj)};
	   if(move_obj.onmousemove == null ) return false;
	   move_obj.onmouseup = function (){up(move_obj)};
	   gPageCursorOldX = e.screenX;
	   gPageCursorOldY = e.screenY;
	   if(isIE)move_obj.setCapture();
	   if(isFF)window.captureEvents(Event.MOUSEMOVE|Event.MOUSEUP);
	}catch(ex){
		
	}

  
}
function move(evt,obj){
       e = evt || window.event;
       if(!obj)return;
		
        obj.style.left = parseInt( obj.style.left ) + e.screenX - gPageCursorOldX +"px";
		obj.style.top  = parseInt( obj.style.top.replaceAll("px")  ) + e.screenY - gPageCursorOldY +"px";
		gPageCursorOldX = e.screenX;
		gPageCursorOldY = e.screenY;
		      
   }
function up(obj){
       obj.onmousemove = null;
       obj.onmouseup = null;
       if(isIE)obj.releaseCapture();
       if(isFF)window.releaseEvents(Event.MOUSEMOVE|Event.MOUSEUP);
   }
var CD = {};
CD.flyToSmall = function(sName,spanName)
{
	var 
	sX = getElementPos(sName).x||0;
	
	sY =getElementPos(sName).y||0;
	
	document.getElementById(spanName).setAttribute("left",sX);
	document.getElementById(spanName).setAttribute("top",sY);
	sW = document.getElementById(sName).clientWidth||0;
	sH = document.getElementById(sName).clientHeight||0;
	
	document.getElementById(spanName).setAttribute("width",sW);
	document.getElementById(spanName).setAttribute("height",sH);
	eX = getElementPos(spanName).x||0;
	eY = getElementPos(spanName).y||0;
	
	sX = parseInt(sX,10)||0;
	sY = parseInt(sY ,10)||0;
	eX = parseInt(eX,10)||0;
	eY = parseInt(eY,10)||0;
	sW = parseInt(sW,10)||0;
	sH = parseInt(sH,10)||0;
	var speed = 20;
	var obj = document.getElementById(sName);

	var r = 1;
	function fly()
	{
		var iTimer = setInterval(function(){
			if(r>10)
			{
				clearInterval(iTimer);
				document.getElementById(sName).style.display = "none";
			
				return;
			}
			obj.style.width = (0-sW)*r/10 + sW + "px";
			obj.style.height = (0-sH)*r/10 + sH + "px";
			obj.style.left = (eX - sX)*r/10 + sX + "px";
			obj.style.top = (eY- sY)*r/10 + sY + "px";
			r++;
		},speed);
	}
	fly();
};
CD.flyToShow = function(sName,spanName)
{
	document.getElementById(sName).style.display = "";
	var 
	sX = getElementPos(sName).x||0,
	sY =getElementPos(sName).y||0,
	eX = document.getElementById(spanName).getAttribute("left")||0;
	eY = document.getElementById(spanName).getAttribute("top")||0;
	sW = 0;
	sH = 0;
	eW = document.getElementById(spanName).getAttribute("width");
	eH = document.getElementById(spanName).getAttribute("height");
	
	sX = parseInt(sX,10)||0;
	sY = parseInt(sY ,10)||0;
	eX = parseInt(eX,10)||0;
	eY = parseInt(eY,10)||0;
	sW = parseInt(sW,10)||0;
	sH = parseInt(sH,10)||0;
	eW = parseInt(eW,10)||0;
	eH = parseInt(eH,10)||0;
	var speed = 20;
	var obj = document.getElementById(sName);
	var r = 1;
	function fly()
	{
		var iTimer = setInterval(function(){
			if(r>10)
			{
				clearInterval(iTimer);
				return;
			}
			obj.style.width = (eW-sW)*r/10 + sW + "px";
			obj.style.height = (eH-sH)*r/10 + sH + "px";
			obj.style.left = (eX - sX)*r/10 + sX + "px";
			obj.style.top = (eY- sY)*r/10 + sY + "px";
			r++;
		},speed);
	}
	fly();
};


function getElementPos(elementId) {

	var ua = navigator.userAgent.toLowerCase();
	var isOpera = (ua.indexOf('opera') != -1);
	var isIE = (ua.indexOf('msie') != -1 && !isOpera); // not opera spoof

	var el = document.getElementById(elementId);

	if(el.parentNode === null || el.style.display == 'none') 
	{
		return false;
	}

	var parent = null;
	var pos = [];
	var box;

	if(el.getBoundingClientRect)	//IE
	{
		box = el.getBoundingClientRect();
		var scrollTop = Math.max(document.documentElement.scrollTop, document.body.scrollTop);
		var scrollLeft = Math.max(document.documentElement.scrollLeft, document.body.scrollLeft);

		return {x:box.left + scrollLeft, y:box.top + scrollTop};
	}
	else if(document.getBoxObjectFor)	// gecko
	{
		box = document.getBoxObjectFor(el);
		   
		var borderLeft = (el.style.borderLeftWidth)?parseInt(el.style.borderLeftWidth):0;
		var borderTop = (el.style.borderTopWidth)?parseInt(el.style.borderTopWidth):0;

		pos = [box.x - borderLeft, box.y - borderTop];
	}
	else	// safari & opera
	{
		pos = [el.offsetLeft, el.offsetTop];
		parent = el.offsetParent;
		if (parent != el) {
			while (parent) {
				pos[0] += parent.offsetLeft;
				pos[1] += parent.offsetTop;
				parent = parent.offsetParent;
			}
		}
		if (ua.indexOf('opera') != -1 
			|| ( ua.indexOf('safari') != -1 && el.style.position == 'absolute' )) 
		{
				pos[0] -= document.body.offsetLeft;
				pos[1] -= document.body.offsetTop;
		} 
	}
		
	if (el.parentNode) { parent = el.parentNode; }
	else { parent = null; }
  
	while (parent && parent.tagName != 'BODY' && parent.tagName != 'HTML') 
	{ // account for any scrolled ancestors
		pos[0] -= parent.scrollLeft;
		pos[1] -= parent.scrollTop;
  
		if (parent.parentNode) { parent = parent.parentNode; } 
		else { parent = null; }
	}
	return {x:pos[0], y:pos[1]};
}