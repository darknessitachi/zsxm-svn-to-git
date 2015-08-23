function drawIndexTabInit(renderId,opt){
	this.renderDiv = renderId;
	this.sb = new StringBuffer();
	this.options = {
		table : []
	}
	Object.extend(this.options,opt);
	this._init();
}

drawIndexTabInit.prototype={
	_init : function(){
		var _h = 0;
		var _flag = true;//
		for(var i=0;i<this.options.table.length;i++){
			var one = this.options.table[i];
			var id = one[0];
			var title = one[1];
			var height = one[2];
			var _height = 0;
			if(height.indexOf("%")>-1 && i==0){
				_flag = true;
			}
			if(height.indexOf("px")>-1 && i==0){
				_flag = false;
			}
			if(_flag){
				_height = (getSize().h*parseFloat(height.replaceAll("%",""))/100).toFixed(0)-70+"px";
			}else{
				_height = height;
			}
			//_h += parseInt(height.replaceAll("px","").replaceAll("%",""));
			this._initTable(id,title,_height);
		}
		this.drawTable();
	},
	_initTable : function(id,title,height){
		this.sb.append('<table  width="100%" style="margin-top:3px;"  border="0" cellpadding="0" cellspacing="0">\n');
		  this.sb.append('<tr>\n');
		    this.sb.append('<td width="17" valign="top" background="images/skin0/mail_leftbg.gif"><img src="images/skin0/left-top-right.gif" width="17" height="29" /></td>\n');
		    this.sb.append('<td valign="top" background="images/skin0/content-bg.gif">');
		    this.sb.append('<table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" >\n');
		      this.sb.append('<tr>\n');
		        this.sb.append('<td height="31"><div class="titlebt">'+title+'</div></td>\n');
		      this.sb.append('</tr>\n');
		    this.sb.append('</table></td>\n');
		    this.sb.append('<td width="16" valign="top" background="images/skin0/mail_rightbg.gif"><img src="images/skin0/nav-right-bg.gif" width="16" height="29" /></td>\n');
		  this.sb.append('</tr>\n');
		  this.sb.append('<tr>\n');
				this.sb.append('<td valign="middle" background="images/skin0/mail_leftbg.gif">&nbsp;</td>\n');
				this.sb.append('<td valign="top" bgcolor="#F7F8F9">\n');
					this.sb.append('<div id="'+id+'" style="height:'+height+';" >\n');
					this.sb.append('</div>\n');
				this.sb.append('</td>\n');
				this.sb.append('<td background="images/skin0/mail_rightbg.gif">&nbsp;</td>\n');
		  this.sb.append('</tr>\n');
		  this.sb.append('<tr>\n');
		    this.sb.append('<td valign="bottom" background="images/skin0/mail_leftbg.gif"><img src="images/skin0/buttom_left2.gif" width="17" height="17" /></td>\n');
		    this.sb.append('<td background="images/skin0/buttom_bgs.gif"><img src="images/skin0/buttom_bgs.gif" width="17" height="17"></td>\n');
		    this.sb.append('<td valign="bottom" background="images/skin0/mail_rightbg.gif"><img src="images/skin0/buttom_right2.gif" width="16" height="17" /></td>\n');
		  this.sb.append('</tr>\n');
		this.sb.append('</table>\n');
	},
	drawTable : function(){
		$(this.renderDiv).innerHTML = this.sb.toString();
	}
}