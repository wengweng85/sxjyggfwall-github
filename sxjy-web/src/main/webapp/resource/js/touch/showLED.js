function ajaxShowLED(wwb001){
	$.ajax({
        url: contextPath2 + '/common/touchLED?wwb001='+wwb001,
        type: 'GET',
        dataType: "json",
        success: function (res) {
            if (res.success === 'true') {
            	queryJobSuccess(res.obj);
            }
        }
    });
}
function queryJobSuccess(response){
	var touchLED = response.touchLED;
	
	var html = "<table border=\"0\" width=\"100%\"  cellpadding=\"0\" cellspacing=\"0\"  id=\"hgjyled\" style=\"margin-top: 0px;\" bgcolor=\"#000000\"><tr><td  align=\"left\" colspan=\"2\">";
	//生成头部信息
	html += modelhead(touchLED);
	//生成体信息
	html += modelbody(response);
	//生成体信息
	html += modelfoot(touchLED);
	
	html += "</td></tr></table>";
	$('#showLEDId').append(html);
	stime();
	setpm(touchLED);
	var divH = $('#showLEDId').css('height');
	divH = divH.substr(0,divH.length-2)-150;
	$('#con_2').css('height',divH+'px');
}
function modelhead(touchLED){
	var htmladd = "<table width=\"100%\" height=\"50\" class=\"title_name\" style=\"font-size: 30px;position: relative;top: 0px;\" id=\"title\"><tr><td align=\"center\"><strong><font color=\"#FFC125\"  id=\"title_name\">"+touchLED.ledTitle+"</font></strong></td></tr></table>";
	htmladd += "<table width=\"100%\" height=\"30\" style=\"font-size: 23px;margin-bottom: 0px;position: relative;\" id=\"title0\"  bgcolor=\"#FFB90F\" ><tr>";
	if(touchLED.aae100==0){
		htmladd += "<td align=\"center\" width=\"6%\"  id=\"title1\" valign=\"center\" bgcolor=\"#2E6B2E\"  class=\"titleflag\"><strong><font color=\"#ffffff\" >序号</font></strong></td>";
	}else{
		htmladd += "<td align=\"center\" width=\"6%\"  id=\"title1\" valign=\"center\" bgcolor=\"#2E6B2E\"  class=\"titleflag\"><strong><font color=\"#ffffff\" >̯摊位号</font></strong></td>";
	}
	htmladd = htmladd + "<td align=\"center\" width=\"14%\"  id=\"title2\" valign=\"center\" bgcolor=\"#2E6B2E\"  class=\"titleflag\"><strong><font color=\"#ffffff\" >单位名称</font></strong></td>"	
			+"<td align=\"center\" width=\"12%\"  id=\"title3\" valign=\"center\" bgcolor=\"#2E6B2E\" class=\"titleflag\"><strong><font color=\"#ffffff\" >招聘岗位</font></strong></td>"
			+"<td align=\"center\" width=\"10%\" id=\"title4\" valign=\"center\" bgcolor=\"#2E6B2E\" class=\"titleflag\"><strong><font color=\"#ffffff\" >招聘人数</font></strong></td>"
			+"<td align=\"center\" width=\"8%\" id=\"title5\" valign=\"center\" bgcolor=\"#2E6B2E\" class=\"titleflag\"><strong><font color=\"#ffffff\" >学历</font></strong></td>"
			+"<td align=\"center\" width=\"8%\" id=\"title6\" valign=\"center\" bgcolor=\"#2E6B2E\" class=\"titleflag\"><strong><font color=\"#ffffff\" >性别</font></strong></td>"
			+"<td align=\"center\" width=\"8%\" id=\"title7\" valign=\"center\" bgcolor=\"#2E6B2E\" class=\"titleflag\"><strong><font color=\"#ffffff\" >年龄</font></strong></td>"
			+"<td align=\"center\" width=\"12%\"  id=\"title8\" valign=\"center\" bgcolor=\"#2E6B2E\" class=\"titleflag\"><strong><font color=\"#ffffff\" >薪酬待遇</font></strong></td>"
			+"<td align=\"center\" width=\"8%\"   id=\"title9\" valign=\"center\" bgcolor=\"#2E6B2E\" class=\"titleflag\"><strong><font color=\"#ffffff\" >联系人</font></strong></td>"
			+"<td align=\"center\" width=\"14%\"  id=\"title10\" valign=\"center\" bgcolor=\"#2E6B2E\" class=\"titleflag\"><strong><font color=\"#ffffff\" >联系方式</font></strong></td>"
			+"</tr></table>";
	return htmladd;
}
function modelbody(response){
	var htmladd = "<div class=\"con_2\" id=\"con_2\" ><table id=\"table2\" class=\"position\" style=\"border-spacing: 0px;text-align: center;vertical-align: top;border: 1px solid #2E6B2E;margin: 5px 0px;border-collapse: collapse;table-layout: fixed;WORD-BREAK: break-all; WORD-WRAP: break-word;\">";
	var result = response.details;
	var charactersize = response.touchLED.ledCneterSize;
	var html  = '';
	for (var i = 0; i < result.length; i++) {
		var cd01 = result[i].cd01;
		var cd20 = result[i].cd20;
		if(i%2==0){
			for (var j = 0; j < cd20.length; j++) { 
			html += '<tr>';
			if (j == 0) {
					html += "<td align=\"center\"  valign=\"center\" style=\"height: 40px;font-size:"+charactersize+"px;border: 1px solid #2E6B2E;padding: .2em 3px;\" font-size:"+charactersize+"px;\" width=\"6%\" valign=\"center\" rowspan=\""+cd20.length+"\" ><span class=\"LEDShow_spancolor\">"+(i+1)+"</span></td>";
				html += "<td align=\"center\"  valign=\"center\" style=\"height: 40px;font-size:"+charactersize+"px;border: 1px solid #2E6B2E;padding: .2em 3px;\" width=\"14%\" valign=\"center\" rowspan=\""+cd20.length+"\" ><span class=\"LEDShow_spancolor\">"+cd01.aab004+"</span></td>";
				
			}	
				html += "<td align=\"center\"  valign=\"center\" style=\"height: 40px;font-size:"+charactersize+"px;border: 1px solid #2E6B2E;padding: .2em 3px;\" font-size:"+charactersize+"px;\" width=\"12%\" valign=\"center\"><span class=\"LEDShow_spancolor\">"+cd20[j].aca112_name+"</span></td>"
				 + "<td align=\"center\"  valign=\"center\" style=\"height: 40px;font-size:"+charactersize+"px;border: 1px solid #2E6B2E;padding: .2em 3px;\" font-size:"+charactersize+"px;\" width=\"10%\" valign=\"center\"><span class=\"LEDShow_spancolor\">"+cd20[j].ecc07n+"</span></td>"
				+  "<td align=\"center\"  valign=\"center\" style=\"height: 40px;font-size:"+charactersize+"px;border: 1px solid #2E6B2E;padding: .2em 3px;\" font-size:"+charactersize+"px;\" width=\"8%\" valign=\"center\"><span class=\"LEDShow_spancolor\">"+cd20[j].aac011+"</span></td>"
				+  "<td align=\"center\"  valign=\"center\" style=\"height: 40px;font-size:"+charactersize+"px;border: 1px solid #2E6B2E;padding: .2em 3px;\" font-size:"+charactersize+"px;\" width=\"8%\" valign=\"center\"><span class=\"LEDShow_spancolor\">"+cd20[j].ecc07r+"</span></td>"
				+  "<td align=\"center\"  valign=\"center\" style=\"height: 40px;font-size:"+charactersize+"px;border: 1px solid #2E6B2E;padding: .2em 3px;\" font-size:"+charactersize+"px;\" width=\"8%\" valign=\"center\"><span class=\"LEDShow_spancolor\">"+cd20[j].acb221+"-"+cd20[j].acb222+"</span></td>"
				+  "<td align=\"center\"  valign=\"center\" style=\"height: 40px;font-size:"+charactersize+"px;border: 1px solid #2E6B2E;padding: .2em 3px;\" font-size:"+charactersize+"px;\" width=\"12%\" valign=\"center\"><span class=\"LEDShow_spancolor\">"+cd20[j].ecd217+"</span></td>"
				+ "<td align=\"center\"  valign=\"center\" style=\"height: 40px;font-size:"+charactersize+"px;border: 1px solid #2E6B2E;padding: .2em 3px;\" font-size:"+charactersize+"px;\" width=\"8%\" valign=\"center\"><span class=\"LEDShow_spancolor\">"+cd20[j].aae045+"</span></td>"
				+  "<td align=\"center\"  valign=\"center\" style=\"height: 40px;font-size:"+charactersize+"px;border: 1px solid #2E6B2E;padding: .2em 3px;\" font-size:"+charactersize+"px;\" width=\"14%\" valign=\"center\"><span class=\"LEDShow_spancolor\">"+cd20[j].eac153+"</span></td>"
				+ "</tr>";
			}
		}else{
			for (var j = 0; j < cd20.length; j++) { 
			html += '<tr>';
			if (j == 0) {
					html += "<td align=\"center\"  valign=\"center\" style=\"height: 40px;font-size:"+charactersize+"px;border: 1px solid #2E6B2E;padding: .2em 3px;\"  width=\"6%\" valign=\"center\" rowspan=\""+cd20.length+"\" ><span class=\"LEDShow_spancolor1\">"+(i+1)+"</span></td>";
				html += "<td align=\"center\"  valign=\"center\" style=\"height: 40px;font-size:"+charactersize+"px;border: 1px solid #2E6B2E;padding: .2em 3px;\"  width=\"14%\" valign=\"center\" rowspan=\""+cd20.length+"\" ><span class=\"LEDShow_spancolor1\">"+cd01.aab004+"</span></td>";
				
			}	
				html += "<td align=\"center\"  valign=\"center\" style=\"height: 40px;font-size:"+charactersize+"px;border: 1px solid #2E6B2E;padding: .2em 3px;\" width=\"12%\" valign=\"center\"><span class=\"LEDShow_spancolor1\">"+cd20[j].aca112_name+"</span></td>"
				 + "<td align=\"center\"  valign=\"center\" style=\"height: 40px;font-size:"+charactersize+"px;border: 1px solid #2E6B2E;padding: .2em 3px;\" width=\"10%\" valign=\"center\"><span class=\"LEDShow_spancolor1\">"+cd20[j].ecc07n+"</span></td>"
				+  "<td align=\"center\"  valign=\"center\" style=\"height: 40px;font-size:"+charactersize+"px;border: 1px solid #2E6B2E;padding: .2em 3px;\" width=\"8%\" valign=\"center\"><span class=\"LEDShow_spancolor1\">"+cd20[j].aac011+"</span></td>"
				+  "<td align=\"center\"  valign=\"center\" style=\"height: 40px;font-size:"+charactersize+"px;border: 1px solid #2E6B2E;padding: .2em 3px;\" width=\"8%\" valign=\"center\"><span class=\"LEDShow_spancolor1\">"+cd20[j].ecc07r+"</span></td>"
				+  "<td align=\"center\"  valign=\"center\" style=\"height: 40px;font-size:"+charactersize+"px;border: 1px solid #2E6B2E;padding: .2em 3px;\" width=\"8%\" valign=\"center\"><span class=\"LEDShow_spancolor1\">"+cd20[j].acb221+"-"+cd20[j].acb222+"</span></td>"
				+  "<td align=\"center\"  valign=\"center\" style=\"height: 40px;font-size:"+charactersize+"px;border: 1px solid #2E6B2E;padding: .2em 3px;\" width=\"12%\" valign=\"center\"><span class=\"LEDShow_spancolor1\">"+cd20[j].ecd217+"</span></td>"
				+ "<td align=\"center\"  valign=\"center\" style=\"height: 40px;font-size:"+charactersize+"px;border: 1px solid #2E6B2E;padding: .2em 3px;\" width=\"8%\" valign=\"center\"><span class=\"LEDShow_spancolor1\">"+cd20[j].aae045+"</span></td>"
				+  "<td align=\"center\"  valign=\"center\" style=\"height: 40px;font-size:"+charactersize+"px;border: 1px solid #2E6B2E;padding: .2em 3px;\" width=\"14%\" valign=\"center\"><span class=\"LEDShow_spancolor1\">"+cd20[j].eac153+"</span></td>"
				+ "</tr>";
			}
		}
		
	}
	htmladd += html +"</table></div>";
	
	return htmladd;
}
function modelfoot(){
	var htmladd = "<table border=\"0\"  width=\"100%\" height=\"50\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#000000\" id=\"bootomtime\">"
		+"<tr><td  bgcolor=\"#000000\" align=\"left\"  style=\"color: #ffffff;font-size: 20px;\" width=\"20%\" id=\"timetxt\"></td>"
		+"<td  bgcolor=\"#000000\" align=\"left\" style=\"color: #ffffff;font-size: 31px;\" ><marquee width=\"80%\" behavior=\"scroll\"   direction=\"left\" scrollamount=\"1\" scrolldelay=\"1\" id=\"scrollmsg\">"
		+"</marquee></td></tr></table>";
	
	return htmladd;
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
       i="0"+i;
    return i;
}
function setpm(touchLED){

    var value = touchLED.ledFoot;
    var titlename = touchLED.ledTitle;
    var charactersize = touchLED.ledCneterSize;//字体大小
    var btgdcharactersize = touchLED.ledTitleSize;//字体大小
    var lmcharactersize = touchLED.ledColumnSize;//字体大小
    var speed = touchLED.ledSpeed;//滚动速度
    initScroll("con_2",speed,"top"); 
    
    
    $("#scrollmsg").text(value);
    $("#title_name").text(titlename);
    document.getElementById('title_name').style.fontSize=btgdcharactersize+'px';
    document.getElementById('scrollmsg').style.fontSize=btgdcharactersize+'px';
    document.getElementById('timetxt').style.fontSize=btgdcharactersize+'px';
     
    $("#titlename").val(titlename);
    $("#scrolltest").val(value);
    $("#scrolltest").attr("font-size",btgdcharactersize);
     
    $("#charactersize").val(charactersize);//字体大小
    $(".position").find("td").css("fontSize", charactersize+"px");
    $(".type1size").css("fontSize", charactersize+"px");//第二种模式字体大小
    $("#btgdcharactersize").val(btgdcharactersize);//标题滚动字体大小
    $("#lmcharactersize").val(lmcharactersize);//栏目字体大小
    $(".titleflag").css("fontSize", lmcharactersize+"px");//栏目标题字体大小
}