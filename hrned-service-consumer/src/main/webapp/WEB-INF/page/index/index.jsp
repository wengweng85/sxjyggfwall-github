<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
        String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>dubbo测试</title>
    <link href="<%=path%>/resource/webjars/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=path%>/resource/webjars/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=path%>/resource/webjars/css/style.min.css" rel="stylesheet">
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
         <!-- 查询条件开始 -->
         <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>入参</h5>
                <div class="ibox-tools">
                    <a class="collapse-link">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                </div>
            </div>
            <div class="ibox-content">
                      <div class="form-group">
                          <label for="exampleInputEmail2" class="sr-only">身份证号码</label>
                          <input type="text" placeholder="请输入身份证号码" id="aac002"  name="aac002"  value="110104197303300827" class="form-control">
                      </div>
                      <div class="form-group">
                          <label for="exampleInputPassword2" class="sr-only">姓名</label>
                          <input type="text" placeholder="请输入姓名" id="aac003" name="aac003" value="刘亚娜" class="form-control">
                      </div>
                      <button class="btn btn-primary"  onclick="dubbotest1()">1.查询就业登记状态和就业形式</button>
                      <button class="btn btn-primary"  onclick="dubbotest2()">2.查询职业资格等级、专业技术职务和学历</button>
                      <button class="btn btn-primary"  onclick="dubbotest3()">3.查询就业援助对象的类型和就业援助对象认定日期</button>
                      <button class="btn btn-primary"  onclick="dubbotest4()">4.查询享受政策类型</button>
                      <button class="btn btn-primary"  onclick="dubbotest5()">5.查询发证机构和发证日期</button>
              </div>
        </div>
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>出参</h5>
                <div class="ibox-tools">
                    <a class="collapse-link">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                </div>
            </div>
         <div class="ibox-content">
                      <div class="form-group">
                          <textarea rows="20" cols="100"  id="outparam"></textarea>
                      </div>
              </div>
        </div>
 </div>
 <script src="<%=path%>/resource/webjars/js/jquery-2.0.0.min.js"></script>
 <script src="<%=path%>/resource/webjars/js/bootstrap.min.js"></script>
 <script src="<%=path%>/resource/webjars/js/handlebars.min.js"></script>
 <script src="<%=path%>/resource/webjars/js/layer/layer.min.js"></script>
 <script src="<%=path%>/resource/webjars/js/rc.all-2.0.js"></script>
 <script type="text/javascript">
 function dubbotest1(){
    	 var aac002=$('#aac002').val();
    	 var aac003=$('#aac003').val();
    	 if(aac002&&aac003){
    		 var url= "<%=path%>/dubbotest1?aac002="+aac002+"&aac003="+aac003;
  			 rc.ajax(url, null,function (response) {
  				$('#outparam').text(response);
  			});
    	 }else{
    		 layer.alert('请输入姓名及名称');
    	 }
 }
 
 function dubbotest2(){
	 var aac002=$('#aac002').val();
	 var aac003=$('#aac003').val();
	 if(aac002&&aac003){
		 var url= "<%=path%>/dubbotest2?aac002="+aac002+"&aac003="+aac003;
			 rc.ajax(url, null,function (response) {
				$('#outparam').text(response);
			});
	 }else{
		 layer.alert('请输入姓名及名称');
	 }
}
 
 function dubbotest3(){
	 var aac002=$('#aac002').val();
	 var aac003=$('#aac003').val();
	 if(aac002&&aac003){
		 var url= "<%=path%>/dubbotest3?aac002="+aac002+"&aac003="+aac003;
			 rc.ajax(url, null,function (response) {
				$('#outparam').text(response);
			});
	 }else{
		 layer.alert('请输入姓名及名称');
	 }
}
 
 function dubbotest4(){
	 var aac002=$('#aac002').val();
	 var aac003=$('#aac003').val();
	 if(aac002&&aac003){
		 var url= "<%=path%>/dubbotest4?aac002="+aac002+"&aac003="+aac003;
			 rc.ajax(url, null,function (response) {
				$('#outparam').text(response);
			});
	 }else{
		 layer.alert('请输入姓名及名称');
	 }
}
 
 function dubbotest5(){
	 var aac002=$('#aac002').val();
	 var aac003=$('#aac003').val();
	 if(aac002&&aac003){
		 var url= "<%=path%>/dubbotest5?aac002="+aac002+"&aac003="+aac003;
			 rc.ajax(url, null,function (response) {
				$('#outparam').text(response);
			});
	 }else{
		 layer.alert('请输入姓名及名称');
	 }
}
 </script>
</body>
</html>