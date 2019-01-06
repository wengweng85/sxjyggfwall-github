/*
tool method list
version 1.0.1
*/
id = function(obj){return (typeof(obj) == "string") ? document.getElementById(obj) : obj;}
addE = function(elem,type,handle){(document.addEventListener) ? elem.addEventListener(type,handle,false):elem.attachEvent("on"+type,handle);}
function hideElem(obj){
	var elem = id(obj);
	elem && (elem.style.display = "none");
}
function showElem(obj){
	var elem = id(obj);
	elem && (elem.style.display = "block");
}

/*----------- method about cookie ------------*/

	//destroy cookie by cookie name used key
function destroyCookie(key){
	var today = new Date();
	document.cookie = key + "=;expires="+today.toUTCString();
}

	//set cookie and escape the value
function setEscapeCookie(key,value,expiresDays){
	var expiresDate = new Date();
	expiresDate.setTime(expiresDate.getTime() + (expiresDays*24*60*60*1000));
	document.cookie = key + "=" + escape(value) + ((expiresDays == null)? "" : ";expires=" + expiresDate.toUTCString());
}

	//get cookie by name and unescape the value
function getEscapeCookie(key){
	var begin,end,cookieStr = document.cookie,searchStr = key + "=";
	begin = cookieStr.indexOf(searchStr);
	if(begin!=-1){
		begin += searchStr.length;
		end = cookieStr.indexOf(";",begin);
		end = (end == -1) ? cookieStr.length : cookieStr.indexOf(";",begin);
		return unescape(document.cookie.substring(begin,end));
	}
}
	//set cookie
function setCookie(key,value,expiresDays){					
	var expiresDate = new Date();
	expiresDate.setTime(expiresDate.getTime() + (expiresDays*24*60*60*1000));
	document.cookie = key + "=" + value + ((expiresDays == null)? "" : ";expires=" + expiresDate.toUTCString());
}
	//get cookie by name
function getCookie(key){
	var begin,end,cookieStr = document.cookie,searchStr = key + "=";
	begin = cookieStr.indexOf(searchStr);
	if(begin!=-1){
		begin += searchStr.length;
		end = cookieStr.indexOf(";",begin);
		end = (end == -1) ? cookieStr.length : cookieStr.indexOf(";",begin);
		return document.cookie.substring(begin,end);
	}
}

/*----------- method about nodes ------------*/
getChildNodes = function(obj){						//获取元素直接子元素节点
	obj = id(obj);
	if(!obj)return false;
	var childList = new Array,list = obj.childNodes;
	if(list.length>0){
		for(var i=0;i<list.length;i++){(list[i].nodeType == 1) && childList.push(list[i]);}
		return (childList.length>0) ? childList : false;
	}else{
		return false;
	}
}

/*获取obj中不含子元素的leafTag标签指定的leafTag元素；
leafTag没有时就是获取obj的直接子元素中，不含子元素的节点
*/
function getLeafNodes(obj,leafTag){
	var leafTagItems,leafItems = new Array();
	obj = id(obj);
	if(!obj)return false;
	leafTagItems = leafTag ? obj.getElementsByTagName(leafTag) : getChildNodes(obj);
	if(leafTagItems.length>0){
		for(var i=0;i<leafTagItems.length;i++){(!getChildNodes(leafTagItems[i])) && leafItems.push(leafTagItems[i]);}
		return (leafItems.length>0) ? leafItems : false ;
	}else{
		return false;
	}
};
/*获取obj中标签为leafTag的元素，且内部不含subTag标签指定的元素*/
function getSpecialLeafNodes(obj,leafTag,subTag){
	var leafTagItems,leafItems = new Array(),subs;
	obj = id(obj);
	if(!obj)return false;
	leafTagItems = obj.getElementsByTagName(leafTag);
	if(leafTagItems.length>0){
		for(var i=0;i<leafTagItems.length;i++){
			subs = leafTagItems[i].getElementsByTagName(subTag);
			(subs.length==0)&&leafItems.push(leafTagItems[i]);
		}
		return leafItems.length ? leafItems : false;
	}else{
		return false;
	}
};

/*与getSpecialLeafNodes相反，getSpecialConNodes是获取obj中所有leafTag标签指定的元素中，内部含有subTag标签指定类型的leafTag元素构成的数组*/
function getSpecialConNodes(obj,leafTag,subTag){
	var leafTagItems,leafItems = new Array(),subs;
	obj = id(obj);
	leafTagItems = obj.getElementsByTagName(leafTag);
	if(leafTagItems.length>0){
		for(var i=0;i<leafTagItems.length;i++){
			subs = leafTagItems[i].getElementsByTagName(subTag);
			(subs.length>0) && leafItems.push(leafTagItems[i]);
		}
		return (leafItems.length>0) ? leafItems : false;
	}else{
		return false;
	}
};


function addClass(obj,className){
	obj = id(obj);
	if(!obj)return false;
	var classStr = (obj.className)? obj.className : "",classItems;
	classItems = classStr.split(/[ ]+/i);
	obj.className += (obj.className.indexOf(className) == -1) ? (" "+className) : "";
};
function removeClass(obj,className){
	if(typeof(className) != "string" || className.length < 1)return false;
	var classStr,classItems,newItems = new Array();
	obj = id(obj);
	if(!obj)return false;
	classStr = (obj.className)? obj.className : "";
	if(classStr=="")return false;
	classItems = classStr.split(/[ ]+/i);
	for(var i=0;i<classItems.length;i++){
		(classItems[i] != className) ? newItems.push(classItems[i]) : "";
	}
	(newItems) && (obj.className = newItems.join(" "));
};
function toggleClass(obj,className1,className2){
	obj = id(obj);
	if(!obj)return false;
	if(typeof(className1) != "string")return false;
	if(typeof(className2) != "string")return false;
	if(obj.className.indexOf(className1) == -1 && obj.className.indexOf(className2) == -1)
	return false;
	if(obj.className.indexOf(className1) == -1){
		removeClass(obj,className2);
		addClass(obj,className1);
	}else{
		removeClass(obj,className1);
		addClass(obj,className2);
	}
};
function getNextSibling(obj){
	obj = id(obj);
	if(!obj)return false;
	var s = obj.nextSibling;
	while(s.nodeType!=1){
		s = s.nextSibling;
	}
	return (s.nodeType == 1)? s : false;
}
function getPrevSibling(obj){
	obj = id(obj);
	if(!obj)return false;
	var s = obj.previousSibling;
	while(s.nodeType!=1){
		s = s.previousSibling;
	}
	return (s.nodeType == 1)? s : false;
}
function getStyle(obj,styleName){
	return (!!window.ActiveXObject)? obj.currentStyle[styleName] : document.defaultView.getComputedStyle(obj,null)[styleName];
}
function getSizeHeight(obj,flag){
	obj = id(obj);
	if(!obj)return false;
	var height = obj.offsetHeight,numTop,numBottom;
	if(!flag)return height;
	numTop = getStyle(obj,"marginTop");
	numBottom = getStyle(obj,"marginBottom");
	height += parseInt(numTop) + parseInt(numBottom);
	return height;
}
function getSizeWidth(obj,flag){
	obj = id(obj);
	if(!obj)return false;
	var width = obj.offsetWidth,numLeft,numRight;
	if(!flag)return width;
	numLeft = getStyle(obj,"marginLeft");
	numRight = getStyle(obj,"marginRight");
	width += parseInt(numLeft) + parseInt(numRight);
	return width;
}

