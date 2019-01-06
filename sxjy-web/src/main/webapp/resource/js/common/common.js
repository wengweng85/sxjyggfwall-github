//回调方法
function _callback(response){
	if(response.success){
		layer.msg('保存成功');
       	rc.clean();
	}
	else{
		var code = response.code;
		if("" !=code){
			layer.msg(response.message);  
			return false;
			
		}else{
			if(isJsonString(response.message)){
				var obj = JSON.parse(response.message);
				var state=obj.returnResultStr[0].state;
				if(state=="0"){
					layer.msg(obj.returnResultStr[0].msg);
					return false;
				}
			}
		}
		
		layer.msg(response.message);
			
	}
};

function isJsonString(str) {
    try {
        if (typeof JSON.parse(str) == "object") {
            return true;
        }
    } catch(e) {
    	 
    }
    return false;

}
//点击取消按钮
function resert(){
	rc.clean();
}