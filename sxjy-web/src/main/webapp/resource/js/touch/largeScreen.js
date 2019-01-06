function  chagebg(){
    var type = $("#changeColer").val();
    if(type==1){
	    $("li").css("color","red");
		$(".textcolor").css("color","#ffffff");
		$("#changeColer").val('2');
    }else if(type==2){
        $("li").css("color","#ffffff");
		$(".textcolor").css("color","red");
		$("#changeColer").val('3');
    }else{
        $("li:even .textcolor").css("color","#ffffff");
	    $("li:odd").css("color","red");
		$("li:even").css("color","#ffffff");
		$("#changeColer").val('1');
    }
 }   
function stime(){  
   var da=new Date() ; 
   var h=da.getHours() ; 
   var m=da.getMinutes()  ;
   var s=da.getSeconds()  ;
   m=checktime(m)  ;
   s=checktime(s)  ;
   $("#timetxt").text(da.getFullYear()+"-"+(da.getMonth() + 1)+"-"+da.getDate()+" "+h+":"+m+":"+s); 
   
   setTimeout('stime()',1000); 
}  
function checktime(i){  
    if(i<10)  
       i="0"+i  
    return i  
}  
function gotourl(src){
  window.location.href=src;
}
function gotourl2(type,param){
  window.location.href=contextPath+'/pm/out/'+type+'?param='+param;
}
function setCookie(c_name,expiredays){
     var value=$("#width").val();
     if(value==null || value==""){
         alert('宽度不能为空！');
         return;
     }else{
         value=$("#width").val();
     }
     if($("#height").val()==null || $("#height").val()==""){
         alert('高度度不能为空！');
         return;
     }else{
         value=value+'_'+$("#height").val();
     }
     if($("#scrolltest").val()==null || $("#scrolltest").val()==""){
         alert('滚动信息不能为空！');
         return;
     }else{
         value=value+'_'+$("#scrolltest").val();
     }
      if($("#titlename").val()==null || $("#titlename").val()==""){
         alert('标题文字不能为空！');
         return;
     }else{
         value=value+'_'+$("#titlename").val();
     }
     //新增字体大小
     if($("#charactersize").val==null || $("#charactersize").val()==""){
     	alert('请选择字体大小');
     	return;
     }else{
     	 value=value+'_'+$("#charactersize").val();
     }
     //新增滚动字体大小
     if($("#btgdcharactersize").val==null || $("#btgdcharactersize").val()==""){
     	alert('请选择标题滚动字体大小');
     	return;
     }else{
     	 value=value+'_'+$("#btgdcharactersize").val();
     }
     //新增栏目字体大小
     if($("#lmcharactersize").val==null || $("#lmcharactersize").val()==""){
     	alert('请选择栏目字体大小');
     	return;
     }else{
     	 value=value+'_'+$("#lmcharactersize").val();
     }
       //新增滚动速度大小
     if($("#speed").val==null || $("#speed").val()==""){
     	alert('请选择滚动速度大小');
     	return;
     }else{
     	 value=value+'_'+$("#speed").val();
     }
     //结束
     
     var str=document.getElementsByName("checkbox");
	 var objarray=str.length;	 
	 for (var i=0;i<objarray;i++){
	   if(str[i].checked == true){
	    value=value+'_'+str[i].value;
	   }
	 }
	 var exdate=new Date();
	 exdate.setDate(exdate.getDate() + expiredays);
	 document.cookie=c_name+ "=" + escape(value) + ((expiredays==null) ? "" : ";expires="+exdate.toGMTString());
}
function delCookie(name){
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null)
    document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}
function getCookie(name){
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return (arr[2]);
    else
        return null;
}

function init(){
	var height = (document.body.offsetHeight-130)+"px";
	$("#con_2").css("height",height);
    if(getCookie("hgjywebLed")==null || getCookie("hgjywebLed")==""){
      //$("#cshtext").text("请先在下方配置初始化信息！");
      var wwb001 = $('#wwb001').val();
      var url = contextPath + '/common/LargeScreenAction.do?method=queryTouchLEDInfo&wwb001='+wwb001;
      zj.AjaxParams(url,null, setLEDInfo,null,false);
   }else{
      $("#hgjyled").show();
      setpm();
      $("#cshing").hide();
      $("#changeColer").val('1');
	//  initScroll("con_2",100,"top"); 
	  $("li:odd").css("color","#ffffff");
	  $("li:even").css("color","red");
	  stime();
//	  $(".changebtn").show();
//	  $("#area").show();
   }
}

function setpm(){

      var cookies = getCookie("hgjywebLed").split("_");
//      var ledw = cookies[0];
//      var ledh = cookies[1];
      var value = decodeURIComponent(unescape(cookies[2]));
      var titlename = decodeURIComponent(unescape(cookies[3]));
      var charactersize = cookies[4];//字体大小
      var btgdcharactersize = cookies[5];//字体大小
      var lmcharactersize = cookies[6];//字体大小
      var speed = cookies[7];//滚动速度
      initScroll("con_2",speed,"top"); 
      
//      $("#title0").attr("width",ledw);
//      
//      $("#con_2").css("width",ledw);
//      
//      $("#hgjyled").css("width",ledw);
//      $("#title").css("width",ledw);
//      $("#con_2").css("height",ledh);
//      
//      $(".position").attr("width",ledw);
//      
//      $("#bootomtime").attr("width",ledw);
//      $("#scrollmsg").attr("width",ledw-200);
      
      $("#scrollmsg").text(value);
      $("#title_name").text(titlename);
      document.getElementById('title_name').style.fontSize=btgdcharactersize+'px';
      document.getElementById('scrollmsg').style.fontSize=btgdcharactersize+'px';
      //document.getElementById('timetxt').style.fontSize=btgdcharactersize+'px';
      document.getElementById('speed').value=speed;
       
//       $("#width").val(ledw);
//       $("#height").val(ledh);
      $("#titlename").val(titlename);
      $("#scrolltest").val(value);
      $("#scrolltest").attr("font-size",btgdcharactersize);
       
      $("#charactersize").val(charactersize);//字体大小
      $(".position").find("td").css("fontSize", charactersize+"px");
      $(".type1size").css("fontSize", charactersize+"px");//第二种模式字体大小
      $("#btgdcharactersize").val(btgdcharactersize);//标题滚动字体大小
      $("#lmcharactersize").val(lmcharactersize);//栏目字体大小
       
      for(var i=5;i<cookies.length;i++) {
          $("#title"+cookies[i]).show();
      }
      for(var i=1;i<11;i++) {
          document.getElementById('title'+[i]).style.fontSize=lmcharactersize+'px';
      }
}

function reload(){
  window.location.reload();
}
function changeType(){
	if($("#changeType").val()=="1"){
		 $(".type2").hide();
		 $(".type1").show();
		 $("#title0").hide();
		 $("#changeType").val("2")
	}else{
		 $(".type1").hide();
		 $(".type2").show();
		 $("#title0").show();
		 $("#changeType").val("1")
	}
}