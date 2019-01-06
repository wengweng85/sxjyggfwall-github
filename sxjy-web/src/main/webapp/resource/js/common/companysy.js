
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var path = window.location.pathname;
contextPath = path.substring(0, path.substr(1).indexOf('/') + 1);
$(document).ready(function(){
	parseTab(); 
});
function queryCompany(){
 var companyName = $.trim($("#companyName").val());
 if(companyName.length<4){
		 alert("至少输入四个字");
		 $("#companyName").focus();
		 return ;
	 }
 $.ajax({
	 "url":contextPath+'/comm/companyquery?companyName='+companyName,
	 "dataType":"json",
	 "type":"get",
     "success":function(data){
	             addHead();
				$(data).each(function(){ 
					addBody(this.name,this.id);
				}); 
					 parseTab();
				 },
					 "error":function(data){addHead();}
				 });
			 }
			function makesure(){
				var v = $("input[name='company']:checked").val();
				var name = $("input[name='company']:checked").attr("dis");
				if((v == "")||(v == null)|| (typeof(v) == "undefined")){
					alert("请选择一个单位！");
				}else{
					parent.$('#aab001_sy').val(v);
					parent.$('#aab004sy_name').val(name);
					parent.$('#aab004sy_name').blur();
					parent.layer.close(index);
					
				}
			}
			function clean(){
				parent.$('#aab001_sy').val("");
				parent.$('#aab004sy_name').val("");
				parent.$('#aab004sy_name').blur();
				parent.layer.close(index);
			}
			function parseTab(){
				$("#codeTab tr:odd").addClass("odd");
				$("#codeTab tr:even").addClass("even");
			}
			
			function addHead(){
				$("#codeTab").html("");
				$("#codeTab").append("<tr><td width='10%'></td><td>单位名称</td><td>单位编码</td></tr>");
			}
			function addBody(name,code){
				$("#codeTab").append("<tr><td><input type='radio' name='company' value='"+code+"' dis='"+name+"'/></td><td>"+name+"</td><td>"+code+"</td></tr>");
			}
