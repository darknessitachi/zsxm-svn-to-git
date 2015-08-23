
var oldx = 'rcdali1';
function  activeChange(x){
	if(oldx != x){
		  if(document.all.dwid.value == "" && x!='rcdali1'){
		  	  alert("请首先保存《企业基本信息》,再进行下一步操作！");
		  	  return false;
		  }
		  $("rcdali1").className=""; 
		  $("rcdali2").className=""; 
		  $("rcdali3").className=""; 
		  $("rcdali4").className=""; 
		  $("rcdali5").className=""; 
		  $("rcdali6").className=""; 
		  $("rcdali7").className=""; 
		  $("rcdali8").className=""; 
		  $("rcdali9").className=""; 
		  $("rcdali10").className=""; 
		  $("rcdali11").className=""; 
		  $("rcdali12").className=""; 
		  $("rcdali13").className=""; 
		 if (x=="rcdali1")  
		 {
		 	$("rcdali1").className="on";
		 	$("rcda_dis").src = "zsdwview!preZsdw.do?dwid="+document.all.dwid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 if (x=="rcdali2")  
		 {
		 	$("rcdali2").className="on";
		 	$("rcda_dis").src = "zsdwview!preLxr.do?dwid="+document.all.dwid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 
		 if (x=="rcdali3")  
		 {
		 	$("rcdali3").className="on";
		 	$("rcda_dis").src = "zsdwview!preGqbl.do?dwid="+document.all.dwid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 if (x=="rcdali4")  
		 {
		 	$("rcdali4").className="on";
		 	$("rcda_dis").src = "zsdwview!preYqyhtj.do?dwid="+document.all.dwid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		  if (x=="rcdali5")  
		 {
		 	$("rcdali5").className="on";
		 	$("rcda_dis").src = "zsdwview!preRyxx.do?dwid="+document.all.dwid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 if (x=="rcdali6")  
		 {
		 	$("rcdali6").className="on";
		 	$("rcda_dis").src = "zsdwview!preZscq.do?dwid="+document.all.dwid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 if (x=="rcdali7")  
		 {
		 	$("rcdali7").className="on";
		 	$("rcda_dis").src = "zsdwview!preCdxm.do?dwid="+document.all.dwid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 if (x=="rcdali8")  
		 {
		 	$("rcdali8").className="on";
		 	$("rcda_dis").src = "zsdwview!preCdhxxm.do?dwid="+document.all.dwid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 ////
		 if (x=="rcdali9")  
		 {
		 	$("rcdali9").className="on";
		 	$("rcda_dis").src = "zsdwview!preSysjsqk.do?dwid="+document.all.dwid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 if (x=="rcdali10")  
		 {
		 	$("rcdali10").className="on";
		 	$("rcda_dis").src = "zsdwview!preYfjgqk.do?dwid="+document.all.dwid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 if (x=="rcdali11")  
		 {
		 	$("rcdali11").className="on";
		 	$("rcda_dis").src = "zsdwview!preHjqk.do?dwid="+document.all.dwid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 if (x=="rcdali12")  
		 {
		 	$("rcdali12").className="on";
		 	$("rcda_dis").src = "zsdwview!preLssj.do?dwid="+document.all.dwid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 if (x=="rcdali13")  
		 {
		 	$("rcdali13").className="on";
		 	$("rcda_dis").src = "zsdwview!preYqfw.do?dwid="+document.all.dwid.value+"&opttype="+document.all.opttype.value+"&pname="+self.name;
		 }
		 
		 oldx = x;		 
	}
}	
	
	