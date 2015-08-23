
var oldx = 'rcda1li';
function  activeChange(acss,action){
	var x = acss+'li';
	if(oldx != x){
		  
		  if(oldx==''){oldx = 'rcda1li';}
		  if($('rcidtb').value == "" && x!='rcda1li'){
		  	  alert("请首先保存《专家信息》,再进行下一步操作！");
		  	  return false;
		  }
		  
		  $(oldx).className = "";
		  $(x).className = "on";
		  $("main_dis").src = action+"?tbtype="+$('tbtype').value+"&itype="+$('itype').value+"&rcidtb="+$('rcidtb').value+"&rcid="+$('rcid').value+"&opttype="+$('opttype').value+"&pname="+self.name+"&winid="+$("winid").value;
		  oldx = x;		 
	}
}	
	
	