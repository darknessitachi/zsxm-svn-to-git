<html>
	<head>
		<script language="JavaScript">
		    var appWin = null;
		    var w=window.screen.availWidth  ;
		    var h=window.screen.availHeight ;
		    var sFeatures = "width="+w+",height="+h+",toolbar=0,location=0,directories=0,status=1,menubar=0,scrollbars=0,resizable=0,center=1,help=0";	
			setTimeout(function(){
				appWin = window.open("login.do", "main", sFeatures);
				appWin.resizeTo(w,h);
			 	appWin.moveTo(0,0);
				appWin.focus();
				window.opener=null;
				window.open('','_self');
		        window.close();
			},1);
		</script>
		<style type="text/css">
		  div span {
		    font:12px;
		  }
		</style>
	</head>
	<body>
	  <div align="center"><span>Loading page,Please Wait......</span></div>
	</body>
</html>
