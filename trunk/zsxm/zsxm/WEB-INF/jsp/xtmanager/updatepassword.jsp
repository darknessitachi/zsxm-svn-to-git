<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
       <%@ include file="/common/meta.jsp"%>
		<style type="text/css">
			.viewstyle{
 border-bottom:1px solid  #8A96CA;
   border-left:1px solid  #8A96CA;
   border-right:1px solid  #8A96CA;
   margin-bottom:12px;

   }
.viewstyle caption{
   font-weight:bold;
   padding-left:10px;
   padding-top:5px;
   background-color:#8A96CA;
   border-top:1px solid #8A96CA;
   border-right:1px solid  #8A96CA;
   border-left:1px solid  #8A96CA;
   border-bottom:1px solid  #8A96CA; 
   color:#ffffff;
   text-align:left;
   background-image: url(images/skin0/other/style3_title.gif);


   }
.viewstyle th{
  text-align:center;
  white-space : nowrap ;
  color:#254494;
  font-weight :lighter;
  padding:4px;
  background-color:#E7EAF7;
  border-bottom:1px solid  #ccc;

}
.viewstyle td{
  padding:2px 8px;
  WORD-WRAP: break-word;
  }
  
			  .title {	background:#B8CDE3;	color:#003770;line-height:26px;height:26px;padding-left:15px;border:1px solid #fff;font-weight:bold;text-align:left;}
			  .fxtable{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
			  .fxtable2{border: 1px solid #C1DAD7;width: 100%;margin-top:1px;}
			  .fxtable2 td.cls {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;height:20px;}
		      .fxtable td {border-right: 1px solid #C1DAD7;border-bottom: 1px solid #C1DAD7;background: #fff;font-size:12px;padding: 6px 6px 6px 12px;color: #4f6b72;}
		        a.button{background:url(images/button.gif);	display:block;color:#555555;font-weight:bold;height:30px;line-height:29px;margin-bottom:14px;text-decoration:none;width:191px;}
				a:hover.button{color:#0066CC;}
		      .lens{ no-repeat 10px 8px;text-indent:30px;display:block;}
		      .btn {
		         height:22px;
		      }
		    .editor {
			    margin-top: 5px;
			    margin-bottom: 5px;
		    }
		</style>
	</head>
<body style="background-image: url(images/skin0/other/loginbg.gif);background-repeat: repeat-x;background-position: center center;">
	<s:form name="emasForm" action="xtmanager!updtePassword.do" method="post">

	<table width="80%" height="70%" align="left">
  <tr> 
    <td align="center" style="padding-right:20px; filter:progid:DXImageTransform.Microsoft.Shadow(Color=#ACB4D9,Direction=120,strength=5)"> 
      <table width="450" cellpadding="0" cellspacing="0" class="viewstyle">
        <caption>
        <img src="images/skin0/other/user_p.gif" width="8" height="12" align="absmiddle"> 请输入新密码 
        </caption>
        <tr> 
          <th align="left" style="padding:20px 30px 15px 30px;"> <fieldset style="border:1px solid #8A96CA;padding:10px;" >
            <legend><strong>密码信息</strong></legend>
            <table width="95%" >
              <tr> 
                <td nowrap><strong>原始密码：</strong></td>
                <td><input type="password" id="oldpassword0" name="query.oldpassword"></td>
              </tr>
                <tr> 
                <td nowrap><strong>最新密码：</strong></td>
                <td><input type="password" id="newpassword" ></td>
              </tr>
              <tr> 
                <td nowrap><strong>确认密码：</strong></td>
                <td><input type="password" id="password" name="query.newpassword" ></td>
              </tr>
            </table><br>
            </fieldset>
            <table width="100%" cellpadding="0" cellspacing="0" style="margin-top:14px;">
              <tr> 
                <td align="right" style="padding:0px;"> 
                  <input type="button" class="button" value="确  定" onclick="savapassword()"/>
                </td>
              </tr>
            </table></th>
        </tr>
      </table></td>
  </tr>
</table>




	</s:form>
	</body>	
	<script type="text/javascript">
		function savapassword(){
			
			var p0 = document.getElementById('oldpassword0').value;
			var np = document.getElementById('newpassword').value;
			var p = document.getElementById('password').value;
				
			if(!p0){
				alert('请输入原始密码!');
				document.getElementById('oldpassword0').focus();
				return;
			}
			if(!p){
				alert('请输入最新密码!');
				document.getElementById('newpassword').focus();
				return;
			}
				
			if(!np){
				alert('请输入确认密码!');
				document.getElementById('password').focus();
				return;
			}
				
			
            if(p != np){
            	alert('原始密码和确认密码不一致！');
            	document.getElementById('password').value='';
            	document.getElementById('password').focus();
            	return;
            }
			
			var ajax = new AppAjax("xtmanager!updtePassword.do",save_Back);
			ajax.submitForm("emasForm");
		}
		
	function save_Back(data){
		if(data.message.code >0){
			document.getElementById('oldpassword0').value='';
			document.getElementById('newpassword').value='';
			document.getElementById('password').value='';
			alert('密码修改成功!');
		}else{
			alert(data.message.text);
		}
	}
	</script>
</html>