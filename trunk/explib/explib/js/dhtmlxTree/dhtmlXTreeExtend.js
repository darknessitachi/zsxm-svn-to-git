function compareStrDistinct(mainArr,valueArr,sChar)
	{		
		var result="";
		for (i = 0; i < valueArr.length; i++)
		{
			var isAdd = true;
			for(j = 0; j < mainArr.length; j++)
			{
				if (mainArr[j] == valueArr[i])
				{		
					isAdd = false;
					break;
				}
			}
			if (isAdd)
			{
				result = result + valueArr[i] + sChar;
			}
		}
		return result;
	}
function compareStrSame(mainArr,valueArr,sChar)
	{		
		var result="";
		for (i = 0; i < valueArr.length; i++)
		{
			for(j = 0; j < mainArr.length; j++)
			{
				if (mainArr[j] == valueArr[i])
				{		
					result = result + valueArr[i] + sChar;
					break;
				}
			}
		}
		return result;
	}

function splitStr(str,sChar)
	{
		toId=str.split(sChar);
		return toId;	
	}
dhtmlXTreeObject.prototype.getAllCheckedLeaf=function()
{
		var checked = this.getAllChecked();
		var leaves = this.getAllLeafs();
		var commas = ",";
		var checkedArr = splitStr(checked,commas);
		var leafArr = splitStr(leaves,commas);
		var result = compareStrSame(checkedArr,leafArr,commas);
		return result;
}

dhtmlXTreeObject.prototype.getAllUcCheckedLeaf=function()
{
		var unchecked = this.getAllUnchecked();
		var leaves = this.getAllLeafs();
		var commas = ",";
		var uncheckedArr = splitStr(unchecked,commas);
		var leafArr = splitStr(leaves,commas);
		var result = compareStrSame(uncheckedArr,leafArr,commas);
		return result;
}

	/*******
			file:/js/tree/dhtmlXTreeExtend.js
			author:xufeng
			用途：改写DhtmlxTree的取值方法。去除checked=disabled的值
	*******/
	dhtmlXTreeObject.prototype.getAllCheckedBranchesMy=function(){
		var temp= this._getAllCheckedMy("","",1);
		if (temp!="")temp+=this.dlmtr;
		return temp+this._getAllCheckedMy("","",2);
	};
	dhtmlXTreeObject.prototype._getAllCheckedXMLMy=function(p,list,mode)
{
		 var z=[];
		  if (mode==2)p.through("item","checked",mode,function(c)
		  {
		  	 z.push(c.get("id"))
		  }
		  ,this);
		  if (mode==1)p.through("item","id",null,function(c)
		  {
		  	
		     if (c.get("checked")&& (c.get("checked") !=-1)){
		     	if(c.get("disabled"))return;
    			z.push(c.get("id"));
    			}
		  }
		  ,this);
		  if (mode==0)p.through("item","id",null,function(c)
		  {
		    if (!c.get("checked")|| c.get("checked")==0 )
		    z.push(c.get("id"))
		  }
		  ,this);
		  return list+(list?",":"")+z.join(",");
		  if (list)return list;
		  else return ""
};
	dhtmlXTreeObject.prototype._getAllCheckedMy=function(htmlNode,list,mode)
{		
		if (!htmlNode)htmlNode=this.htmlNode;
		if (htmlNode.checkstate==mode)if (!htmlNode.nocheckbox)
		{
		if (list)list+=this.dlmtr+htmlNode.id;
		else list=htmlNode.id
		};
		var j=htmlNode.childsCount;
		for (var i=0;i<j;i++)
		{
		list=this._getAllCheckedMy(htmlNode.childNodes[i],list,mode)
		};
		if (htmlNode.unParsed)list=this._getAllCheckedXMLMy(htmlNode.unParsed,list,mode);
		if (list)return list;
		else return ""
};