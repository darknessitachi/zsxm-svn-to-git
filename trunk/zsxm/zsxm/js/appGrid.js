var appGrids = [];

function AppGrid(t,opt){
   this.container = t ;
   this.render = false;
   this.indexed = appGrids.length;
   this.options = {
		url : "#",
		param : {},
		colModel : [],
		buttons :[],
		usepager : true,
		pagesize : 25,
		width : "100%",
		height : "400px"
		   
   };
   Object.extend(this.options,opt);

   appGrids[this.indexed] = this;
}

AppGrid.prototype = {
   bindTo : function(target,opt){
      var t = target || this.container;
     
      Object.extend(this.options,opt);
      
      if(!this.render){
         $(t).update(this.toHtml());
         this.render = true;
      }
      if(this.options["load"]){
    	 this.loadata();
      }
      return this;
   },
   load : function(param){
	  var pager = {"limit.start":1,"limit.length":this.pagesize};
	  Object.extend(this.options.param,pager); 
	  Object.extend(this.options.param,param); 
	  this.loadata();
   },
   toHtml : function(){
	   var sb = new StringBuffer("<div class='butbar' id='butbar'>");

       if(this.options["buttons"] && this.options["buttons"].length > 0){
    	   sb.append('<div class="left">');
    	   for(var i =0 ;i < this.options["buttons"].length ; i ++){
              var one_btn = this.options["buttons"][i];
              sb.append('<input type="button"  class="').append(one_btn["bclass"]).append('" value="').append(one_btn["name"]).append('" ');
              if(one_btn["onpress"]){
            	  sb.append(" onclick='").append(one_btn["onpress"]).append(";' ");
              }
              sb.append(" /> ");
    	   }
		   sb.append('</div>');
       }
       if(this.options["usepager"]){
    	   sb.append('<div id="pagerContainer" class="right"></div>');
       }
       sb.append("</div>");
       
       sb.append('<div class="tableContainer" style="height:').append(this.options.height).append('\" ><table id="pagegrid"  cellspacing="0">');
       sb.append("<thead><tr>");

       if(this.options["colModel"]){
          var colmodel = this.options["colModel"];
          var len = colmodel.length;
          for(var i =0 ;i < len ; i ++){
              var col = colmodel[i];
              sb.append('<td align="center"');
              if(col["width"]){
            	  sb.append(' width="').append(col["width"]).append('"');
              }
              sb.append(">");
              if(col["type"] && col["type"] == "checkbox"){
            	  sb.append(" <input type=checkbox onclick='AppGrid.Toggle(this);'>");
              }else{
            	  sb.append(col["display"]);
              }
              sb.append("</td>");
    	  }
       }
       
       sb.append("</tr></thead>");
       sb.append("<tbody id='tb'>");
       sb.append("</tbody>");
       sb.append('</table></div>');
   
       return sb.toString();
   },
   
   genPagination : function(page){ 
	   var str = new StringBuffer("");
	   str.append(page.currentPageNo +"/"+page.totalPageCount);
	   if(page.hasPreviousPage&&!page.hasNextPage){
		    str.append(" <a href=\"javascript:AppGrid.goPage('"+this.indexed+"',\'1\',\'"+page.totalCount+"\');\">首页</a>");
		    str.append(" <a href=\"javascript:AppGrid.goPage('"+this.indexed+"',\'"+(page.currentPageNo - 1)+"\',\'"+page.totalCount+"\')\">上页</a>");
		    str.append(" <a href=\"javascript:\">下页</a>");
		    str.append(" <a href=\"javascript:\">末页</a>");
		}
		if(page.hasNextPage&&!page.hasPreviousPage){
			str.append(" <a href=\"javascript:\">首页</a>");
		    str.append(" <a href=\"javascript:\">上页</a>");
			str.append(" <a href=\"javascript:AppGrid.goPage('"+this.indexed+"',\'"+(page.currentPageNo + 1)+"\',\'"+page.totalCount+"\')\">下页</a>");
			str.append(" <a href=\"javascript:AppGrid.goPage('"+this.indexed+"',\'"+(page.totalPageCount)+"\',\'"+page.totalCount+"\')\">末页</a>");
		}
		if(page.hasNextPage&&page.hasPreviousPage){
			str.append(" <a href=\"javascript:AppGrid.goPage('"+this.indexed+"',\'1\',\'"+page.totalCount+"\')\">首页</a>");
		    str.append(" <a href=\"javascript:AppGrid.goPage('"+this.indexed+"',\'"+(page.currentPageNo - 1)+"\',\'"+page.totalCount+"\')\">上页</a>");
		    str.append(" <a href=\"javascript:AppGrid.goPage('"+this.indexed+"',\'"+(page.currentPageNo + 1)+"\',\'"+page.totalCount+"\')\">下页</a>");
			str.append(" <a href=\"javascript:AppGrid.goPage('"+this.indexed+"',\'"+(page.totalPageCount)+"\',\'"+page.totalCount+"\')\">末页</a>");
		}
		str.append("");
		return str.toString();
   },
   
   loadata : function(){
	  var loader = this;
	  var pager = {"limit.length":loader.options.pagesize};
	  Object.extend(loader.options.param,pager); 
      var ajax = new AppAjax(this.options.url,function(data){
          loader.cleardata();
          if(data){
             var tb = $("tb");
             var res = data["data"] || data;

             var len = res.length;
             var tbStr = new StringBuffer();
             for(var i = 0 ;i < len ; i ++){
                 var o = res[i];
                 tbStr.append("<tr>");
                 var tdlen = loader.options["colModel"].length;
                
                 for(var k = 0 ; k < tdlen ; k ++){
                     var cm = loader.options["colModel"][k];
                   
             		 tbStr.append("<td  ");
             		 
                	 if(cm["width"]){
                	 	//tbStr.append("width:"+cm["width"]+";");
                	 	 
                     }
                	 if(cm["align"]){
                	 	tbStr.append("style='text-align:");
                	 	tbStr.append(cm["align"]);
                	 	tbStr.append("'");               	 
                     }
                     
                     tbStr.append(" >");
                    
                	 if(cm["type"] && cm["type"] == "checkbox"){                		 
                		  tbStr.append(" <input type=checkbox value='"+(o[cm["name"]] || "")+"'>");
                     }else{
                    	 if(cm["tpl"]){
                    		 var tpl = cm["tpl"].replaceAll("#v#",(o[cm["name"]] || ""));
                    		 tbStr.append(tpl);
                    	 }else{
                    	 	 tbStr.append(o[cm["name"]] || "");
                    	 }
                     }
                      tbStr.append("</td>");
                 }
                  tbStr.append("</tr>");
       	     }
       	     tb.update(tbStr.toString());
             if(loader.options["usepager"]){
            	 $("pagerContainer").update(loader.genPagination(data)); 
             }
          }
      });
    
      ajax.setBlock(false);
      ajax.add(this.options.param);
      ajax.submit();
   },
   cleardata : function(){
		var tb = $("tb");
	    for(var i = tb.rows.length -1 ; i >=0 ; i--){
	       tb.deleteRow(i);
	    }
   },
   getSelected : function(isA){
	   var a = isA || true;
	   var retval = [];
	   var selects = $$("#tb input[type='checkbox']");
	   for(var i = 0 ;i < selects.length ; i++){
		   var sel = selects[i];
		   if(sel.checked == true){
		      retval.push(sel.value);
		   }
	   }
	   if(a){
		   return retval;
	   }
       return retval.join(",");
   }
}
	
AppGrid.Toggle = function(o){
   var loader = o;
   var retval = [];
   var selects = $$("#tb input[type='checkbox']");
   
   $A(selects).each(function(emt){
	  emt.checked = loader.checked;
   });
}

AppGrid.goPage = function(indexed,start,size){
   var target = appGrids[indexed];
   var pager = {"limit.start":start,"limit.length":target.pagesize,'limit.count':size};
   Object.extend(target.options.param,pager); 
   target.loadata();
}