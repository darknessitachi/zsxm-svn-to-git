<#assign title = "" />

<#assign class_head_pos = "positiontLeft" />
<#if (parameters.nameValue ? exists)>
   <#assign title = parameters.nameValue />
</#if>

<#compress>
<div class="iframehead" id="HeadDiv" onMouseDown="parent.beginDrag(self.name,event);"  style="cursor :move;"> 
  <div class="positiontLeft1"> 
    <ul>
        <li class="positiont"><img style="margin-bottom:-5px;" src="./images/skin0/xwin/positiont.gif" /><span id="spanrptname">${title}</span></li>
    </ul>
	
  </div>
<input type="button" value=" " onClick="closeWindow(self.name);"  style="background:transparent  url(./images/skin0/xwin/closed.gif) no-repeat; float:right; border:0px; height:20px; width:25px; margin-top:4px;cursor:pointer;">
</div>



</#compress>
