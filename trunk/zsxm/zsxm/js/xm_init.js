
var oldx = 'rcdali1';
function  activeChange(x){
	if(oldx != x){
		  if(document.all.xmid.value == "" && x!='rcdali1'){
		  	  alert("请首先保存《项目信息》,再进行下一步操作！");
		  	  return false;
		  }
		  $("rcdali1").className=""; 
		  $("rcdali2").className=""; 
		  $("rcdali3").className=""; 
		  
		 if (x=="rcdali1")  
		 {
		 	$("rcdali1").className="on";
		 	$("rcda_dis").src = "zsxm!preZsxm.do?xmid="+document.all.xmid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 if (x=="rcdali2")  
		 {
		 	$("rcdali2").className="on";
		 	$("rcda_dis").src = "zsxm!preLxr.do?xmid="+document.all.xmid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 
		 if (x=="rcdali3")  
		 {
		 	$("rcdali3").className="on";
		 	$("rcda_dis").src = "zsxm!preJzqk.do?xmid="+document.all.xmid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 
		 oldx = x;		 
	}
}	
	
	