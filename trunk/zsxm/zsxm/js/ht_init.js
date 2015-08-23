
var oldx = 'rcdali1';
function  activeChange(x){
	if(oldx != x){
		  if(document.all.htid.value == "" && x!='rcdali1'){
		  	  alert("请首先保存《房租合同信息》,再进行下一步操作！");
		  	  return false;
		  }
		  $("rcdali1").className=""; 
		  $("rcdali2").className=""; 
		  
		 if (x=="rcdali1")  
		 {
		 	$("rcdali1").className="on";
		 	$("rcda_dis").src = "fzht!preFzht.do?htid="+document.all.htid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 if (x=="rcdali2")  
		 {
		 	$("rcdali2").className="on";
		 	$("rcda_dis").src = "fzht!preFzsq.do?htid="+document.all.htid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 /*
		 if (x=="rcdali3")  
		 {
		 	$("rcdali3").className="on";
		 	$("rcda_dis").src = "zsdw!preGqbl.do?dwid="+document.all.dwid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 
		 if (x=="rcdali4")  
		 {
		 	$("rcdali4").className="on";
		 	$("rcda_dis").src = "zsdw!preCdxm.do?dwid="+document.all.dwid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 if (x=="rcdali5")  
		 {
		 	$("rcdali5").className="on";
		 	$("rcda_dis").src = "zsdw!preZscq.do?dwid="+document.all.dwid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 if (x=="rcdali6")  
		 {
		 	$("rcdali6").className="on";
		 	$("rcda_dis").src = "zsdw!preHjqk.do?dwid="+document.all.dwid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 if (x=="rcdali7")  
		 {
		 	$("rcdali7").className="on";
		 	$("rcda_dis").src = "zsdw!preCdhxxm.do?dwid="+document.all.dwid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 
		 if (x=="rcdali6")  
		 {
		 	$("rcdali6").className="on";
		 	$("rcda_dis").src = "rcda!preRyhj.do?rcid="+document.all.rcid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 if (x=="rcdali7")  
		 {
		 	$("rcdali7").className="on";
		 	$("rcda_dis").src = "rcda!preCpjs.do?rcid="+document.all.rcid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 if (x=="rcdali8") 
		 {
		 	$("rcdali8").className="on";
		 	$("rcda_dis").src = "rcda!preShjz.do?rcid="+document.all.rcid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 if (x=="rcdali9")  
		 {	
		 	$("rcdali9").className="on";
		 	$("rcda_dis").src = "rcda!preBpzj.do?rcid="+document.all.rcid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 if (x=="rcdali10")  
		 {
		 	$("rcdali10").className="on";
		 	$("rcda_dis").src = "rcda!preZylz.do?rcid="+document.all.rcid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 if (x=="rcdali11")  
		 {
		 	$("rcdali11").className="on";
		 	$("rcda_dis").src = "rcda!preZscq.do?rcid="+document.all.rcid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 if (x=="rcdali12")  
		 {
		 	$("rcdali12").className="on";
		 	$("rcda_dis").src = "rcda!preXsrz.do?rcid="+document.all.rcid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 if (x=="rcdali13")  
		 {
		 	$("rcdali13").className="on";
		 	$("rcda_dis").src = "rcda!preJjbz.do?rcid="+document.all.rcid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }	*/
		 oldx = x;		 
	}
}	
	
	