
var oldx = 'rcdali1';
function  activeChange(x){
	if(oldx != x){
		  if(document.getElementById('dwid').value == "" && x!='rcdali1'){
		  	  alert("请首先保存《企业基本信息》,再进行下一步操作！");
		  	  return false;
		  }
		  $("rcdali1").className=""; 
		  /**
		  $("rcdali2").className=""; 
		  $("rcdali3").className=""; 
		  $("rcdali4").className=""; 
		  $("rcdali5").className=""; 
		  $("rcdali6").className=""; 
		  $("rcdali7").className=""; 
		  **/
		  $("rcdali2").className=""; 
		  $("rcdali3").className=""; 
		  $("rcdali6").className=""; 
		  $("rcdali8").className=""; 
		  $("rcdali9").className=""; 
		  $("rcdali10").className=""; 
	      $("rcdali11").className=""; 
	      
		 if (x=="rcdali1")  
		 {
		 	$("rcdali1").className="on";
		 	$("rcda_dis").src = "dwshview!preZsdw.do?dwid="+document.all.dwid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 if (x=="rcdali2")  
		 {
		 	$("rcdali2").className="on";
		 	$("rcda_dis").src = "dwshview!preLxr.do?dwid="+document.all.dwid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 
		 if (x=="rcdali3")  
		 {
		 	$("rcdali3").className="on";
		 	$("rcda_dis").src = "dwshview!preGqbl.do?dwid="+document.all.dwid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 if (x=="rcdali6")  
		 {
		 	$("rcdali6").className="on";
		 	$("rcda_dis").src = "dwshview!preZscq.do?dwid="+document.all.dwid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		
		 if (x=="rcdali8")  
		 {
		 	$("rcdali8").className="on";
		 	$("rcda_dis").src = "dwshview!preCdhxxm.do?dwid="+document.all.dwid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 ////
		 if (x=="rcdali9")  
		 {
		 	$("rcdali9").className="on";
		 	$("rcda_dis").src = "dwshview!preSysjsqk.do?dwid="+document.all.dwid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 if (x=="rcdali10")  
		 {
		 	$("rcdali10").className="on";
		 	$("rcda_dis").src = "dwshview!preYfjgqk.do?dwid="+document.all.dwid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 if (x=="rcdali11")  
		 {
		 	$("rcdali11").className="on";
		 	$("rcda_dis").src = "dwshview!preHjqk.do?dwid="+document.all.dwid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 
		 oldx = x;		 
	}
}	
	
	