/**
 * 触摸屏程序js
 * @author wengsh
 * @date 2013-5-1
 * 访问地址 http://host:port/rdreshall/touch/touchAction.do?method=go
 * @type 
 */
var contextPath;// 工程路径
var touchPageCodes;//页面信息
var numCount=0;//循环计数
$(function() {
	if (!contextPath) {
		var path = window.location.pathname;
		contextPath = path.substring(0, path.substr(1).indexOf('/') + 1);
	}
	document.oncontextmenu=function(){return false;};
    document.onselectstart=function(){return false;};
    //请求获取
    $.ajax({
        url: contextPath2+'/common/touchMachine',
        type: 'GET',
        dataType: "json",
        success: function (res) {
            if (res.success === 'true') {
            	if(getCookie("showTouchPage")==null||!getCookie("showTouchPage")){
            		touchPageCodes = res.obj;
            		setDPCookie("showTouchPage",1000);
            		showTouchPage();
            	}else{
            		if($("body").height()==0){
            			showTouchPage2();
            		}else{
            			//分析当前cookie里面的这个值
            			var showTouchPages = getCookie("showTouchPage").split("_");
            			if(showTouchPages.length>2){
            				for(var j=1;j<showTouchPages.length;j++){
            					showTouchPages[j];
            					//找到下一个touchPageCode后放到cookie的最前端，定时执行一次
            					if(showTouchPages[0]==showTouchPages[j].split("@@")[0]){
            						var time = showTouchPages[j].split("@@")[1];
            						var l = j%(showTouchPages.length-1)+1;
            						var touchPageCode = showTouchPages[l].split("@@")[0]
            						console.log(touchPageCode);
            						setDPCookieNext("showTouchPage",touchPageCode,1000);
            						var t=setTimeout("showTouchPage2()",time*1000)
            						return;
            					}
            				}
            			}
            		}
            	}
            }
        }
    });
});

function showTouchPage(){
	window.location.href=contextPath2+'/common/touchPage?touchPageCode='+touchPageCodes[0].touchPageCode;
}
function showTouchPage2(){
	window.location.href=contextPath2+'/common/touchPage?touchPageCode='+getCookie("showTouchPage").split("_")[0];
}

function setDPCookie(c_name,expiredays){
    var value='';
    if(touchPageCodes){
    	value=touchPageCodes[0].touchPageCode;
    	for(var i=0;i<touchPageCodes.length;i++){
    		value=value+'_'+touchPageCodes[i].touchPageCode+'@@'+touchPageCodes[i].time+'@@'+touchPageCodes[i].area;
    	}
    }
    //结束
	 var exdate=new Date();
	 exdate.setDate(exdate.getDate() + expiredays);
	 document.cookie=c_name+ "=" + escape(value) + ((expiredays==null) ? "" : ";expires="+exdate.toGMTString());
}

function setDPCookieNext(c_name,touchPageCode,expiredays){
    var value=touchPageCode;
    var showTouchPages = getCookie("showTouchPage").split("_");
    for(var j=1;j<showTouchPages.length;j++){
    	value = value+'_'+showTouchPages[j];
    }
    //结束
	 var exdate=new Date();
	 exdate.setDate(exdate.getDate() + expiredays);
	 document.cookie=c_name+ "=" + escape(value) + ((expiredays==null) ? "" : ";expires="+exdate.toGMTString());
}

// 滚动插件
(function($) {
$.fn.extend({
	Scroll : function(opt, callback) {
		// 参数初始化
		if (!opt)
		var opt = {};
		var _this = this.find("table");
		var height=this.height();
		var isScroll=(_this.height()>this.height());
		//var lineH = _this.find("tr:eq(0)").height(), // 获取行高
		line = opt.line ? parseInt(opt.line, 10) : parseInt(this.height()/ lineH, 10), // 每次滚动的行数，默认为一屏，即父容器高度
		speed = opt.speed ? parseInt(opt.speed, 10) : 500, // 卷动速度，数值越大，速度越慢（毫秒）
		timer = opt.timer ? parseInt(opt.timer, 10) : 3000 // 滚动的时间间隔（毫秒）
		if (line == 0)
			line = 1;
		//var upHeight = 0 - line * lineH-2;
		// 滚动函数
		scrollUp = function() {
			if(true){
				_this.animate({
				   marginTop : 0-line*_this.find("tr:eq(0)").height()
				}, speed, function() {
					for (i = 1; i <= line; i++) {
						_this.find("tr:eq(0)").remove();
						if(_this.height()<height){
							if(callback){
								callback();
							}
						}
					}
					_this.css({
						marginTop : 1
					});
				});
			}
		},
		timerID = setInterval("scrollUp()", timer);
		/*// 鼠标事件绑定
		_this.hover(function() {
			clearInterval(timerID);
		}, function() {
			timerID = setInterval("scrollUp()", timer);
		}).mouseout();*/
	}
  })
})(jQuery);
function pagefun(){
	var url = contextPath + "/touch/touchAction.do?method=queryJobInfo&curPage="+$('#curPage').val()+'&wwb001='+$('#wwb001').val();
	loadjobData(url)
}
function loadjobData(url){
	 $.get(url,function(responseText){
	    var response = eval('(' + responseText + ')');
        var result = response.datas;
	    for (var i=0;i<result.length;i++){
	        var o=result[i];
	        var tr=$("<tr><td width='10%'>"+o.edc423
	        +"<td  width='15%'>"+o.aca112
	        +"</td><td  width='10%'>"+o.ecc07r
	        +"</td><td  width='10%'>"+o.age
	        +"</td><td  width='10%'>"+o.aac011
	        +"</td></td><td  width='11%'>"+o.salary
	        +"</td></td><td  width='36%'>"+o.ecc079
	        +"</td></tr>");
	        var ta=$('.scrollDiv table');
	        /**
	        if(i%2==0){//奇数列背景色改变
	        	tr.css({backgroundColor:"#479AC7",color: "white"});
	        }
	        */
	        tr.appendTo(ta);
	    } 
	    if(response.datas.length>0){
		    $('#pageNumber').val(response.pageNumber);
		    $('#curPage').val(parseInt(response.curPage,10)+1);
		    if(parseInt($('#curPage').val(),10)>=parseInt($('#pageNumber').val(),10)){
	           $('#curPage').val('0');
	        }
		    $(".scrollDiv table tr:even")
	    }  	
	});
}
function saveJobInfo(){
	var wwb002=$('#wwb002').val();
	var begin=$('#begin').val();
	var end=$('#end').val();
	if(wwb002){
	   $('form').submit();
	}else{
	   alert('请输入标题！');
	}
}

/**
 * flv,f4v格式视频文件查看
 * @param {} filepath
 */
function videoplayer(player,opt){
	if (!opt)
		var opt = {};
	    flowplayer(player, 
	    	{
	    	    src:contextPath + '/flowplayer/flowplayer-3.2.16.swf',
	    	    wmode: "opaque" //这个很重要，仅在ie下会出现问题，设置后flash就不会遮住其他div或者下拉菜单
	        }, 
	    {
		clip : {
		    /**是否自动播放*/
			autoPlay : opt.autoPlay?opt.autoPlay:false,
			autoBuffer:false,
			/**视频截取长度0代表不截取*/
			duration : 0,
			onBeforePause : function(clip) {
				return false;//当点击暂停时触发事件,不能暂停视频
            }
		},
		playlist : opt.playlist,
		plugins : {
		    /**控制面板*/
			controls :opt.nocontrol?null:	
			{
				url : contextPath+ '/flowplayer/flowplayer.controls-3.2.15.swf',
				playlist : false,
				height : 30,
				progressColor:'#FD4B42',
				bufferColor: '#FF8040', 
				volumeColor:'#FD4B42',
				tooltips : {
					buttons : true,
					fullscreen : '全屏',
					play : '播放',
					stop : '停止',
					pause : '暂停',
					fullscreenExit : '退出全屏',
					next : '下一个',
					previous : '上一个',
					mute : '静音',
					unmute : '关闭静音'
				}
			}
		},      
		play : {
			label : '播放',
			replayLabel : '重新播放'
		}
	 });
   }
/**
 * 保存文本信息
 */   
function saveKindEditorTextInfo(){
   // 取得HTML内容
	html = editor.html();
	// ͬ同步数据后可以直接取得textarea的value
	editor.sync();
	html = $('#editor_id').val(); 
    var wwb002=$('#wwb002').val();
	if(wwb002==''){
		alert('请输入文章标题！');
		$('#wwb002').focus();
		return ;
	}
	if(html.length<100){
	   alert('文章内容字数不能少于100！');
	   return ;
	}
	$('form').submit();
}
/**
 * 读取配置文件
 */
function getinivalue(){
   var a;
   Tini.Set_FileName('d:\Computerid.ini'); //设置文件名
   a = Tini.get_IniKeyValue('Computer','id'); //设置读取的字段
   if(a==''){
      alert('读取配置文件失败！请检查配置文件是否配置正确');
   }else{
      var url=contextPath+'/t/ini/'+a;
      window.location.href=url;
   }
}

function showDiv(){
   $.colorbox({
    	iframe:true,
    	href:contextPath + "/touch/touchAction.do?method=queryjobinfo",
    	transition:(function(){ if($.browser.msie){return "none"}else{return "elastic"}})(),
    	scrolling:true,
    	innerWidth:1000, 
    	innerHeight:700,
    	fixed:true,
    	close:''
	});
}
function gourl(url){
 window.location.href=url;
}