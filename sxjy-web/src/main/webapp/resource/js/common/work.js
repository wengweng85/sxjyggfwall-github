
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
var path = window.location.pathname;
contextPath = path.substring(0, path.substr(1).indexOf('/') + 1);
$(document).ready(function(){
	parseTab(); 
});
function queryWork(){
 var workName = $.trim($("#workName").val());
 $.ajax({
	 "url":contextPath+'/comm/workquery?workName='+workName,
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
				var v = $("input[name='work']:checked").val();
				var name = $("input[name='work']:checked").attr("dis");
				if((v == "")||(v == null)|| (typeof(v) == "undefined")){
					alert("请选择一项职业资格！");
				}else{
					parent.$('#aca111').val(v);
					parent.$('#aca111_name').val(name);
					parent.$('#aca111_name').blur();
					parent.layer.close(index);
					
				}
			}
			function clean(){
				parent.$('#aca111').val("");
				parent.$('#aca111_name').val("");
				parent.$('#aca111_name').blur();
				parent.layer.close(index);
			}
			function parseTab(){
				$("#codeTab tr:odd").addClass("odd");
				$("#codeTab tr:even").addClass("even");
			}
			
			function addHead(){
				$("#codeTab").html("");
				$("#codeTab").append("<tr><td width='10%'></td><td>职业资格名称</td><td>职业资格代码</td></tr>");
			}
			function addBody(name,code){
				$("#codeTab").append("<tr><td><input type='radio' name='work' value='"+code+"' dis='"+name+"'/></td><td>"+name+"</td><td>"+code+"</td></tr>");
			}
