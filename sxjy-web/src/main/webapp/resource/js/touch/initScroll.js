
function initScroll(con_id,speed,direct){
	speed = speed ? parseInt(speed) : 30 ;
	direct = direct? direct : "top";
	if(direct == "top" || direct == "bottom"){
		scrollSZ(con_id,speed,direct);
	}
}
function scrollSZ(con_id,speed,direct){

	var con,items,heightHalf,heightAll,timer;
	speed = parseInt(speed);					//获取设置的速度参数
	con = id(con_id);
	con.style.overflow = "hidden";
	direct = (direct != "bottom")? "top" : "bottom";
	con.innerHTML =con.innerHTML + con.innerHTML + con.innerHTML + con.innerHTML;
	items = getChildNodes(con);
	if(items.length < 1)return;
	heightAll = 0;
	for(var i=0;i<items.length;i++){
		var numTop,numBottom;
		if (!!window.ActiveXObject){
			numTop = items[i].currentStyle["marginTop"];
			numBottom = items[i].currentStyle["marginBottom"];
		}else{
			numTop = document.defaultView.getComputedStyle(items[i],null)["marginTop"];
			numBottom = document.defaultView.getComputedStyle(items[i],null)["marginBottom"];
		}
		numTop = parseInt(numTop);
		numBottom = parseInt(numBottom);
		numTop += numBottom;
		if(numTop >0){
			heightAll += numTop;
		}
		heightAll += items[i].offsetHeight;
	};
	heightHalf = heightAll/2;
	if(direct == "bottom"){
		timer = setInterval(_scrollBottom,speed);		
	}else if(direct == "top"){
		timer = setInterval(_scrollTop,speed);
	};

	function _scrollTop(){
		if(con.scrollTop < heightHalf){
			con.scrollTop += 2;
		}else{
			con.scrollTop = 0;
		}
	};
	function _scrollBottom(){
		if(con.scrollTop > 0){
			con.scrollTop -= 2;
		}else{
			con.scrollTop = heightHalf;
		}
	};
};
