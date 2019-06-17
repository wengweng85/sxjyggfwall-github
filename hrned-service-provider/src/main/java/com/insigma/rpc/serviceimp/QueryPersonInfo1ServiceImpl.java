package com.insigma.rpc.serviceimp;


import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.insigma.common.enums.JkType;
import com.insigma.common.struct.Data;
import com.insigma.common.struct.R;
import com.insigma.common.struct.StructUtil;
import com.insigma.common.util.JsonUtils;
import com.insigma.mvc.service.log.LogService;
import com.insigma.rpc.ServiceCall;
import com.insigma.rpc.service.QueryPersonInfo1Service;

/**
 * @author wengsh
 *
 */
@Component("QueryPersonInfo1Service")
public class QueryPersonInfo1ServiceImpl implements QueryPersonInfo1Service {

	@Resource
	private LogService logService;
	
	/**
	 * [{paramBM:\"AAC002\",paramValue:\"320623198912110909\",paramType:\"String\",paramMC:\"公民身份证号码\"},{paramBM:\"AAC003\",paramValue:\"刘某某\",paramType:\"String\",paramMC:\"姓名\"}]
	  body:{
	      "aac002":"320623198912110909",
	      "aac003":"刘某某"
	 }
	 */
	public String exec(String request) {
		Data data=JsonUtils.jsonToPojo(request, Data.class);
		String jkId=logService.add(data, request, JkType.queryPersonInfo1);
		   //数据访问接口参数
        //String QUERY_PARAM = "[{paramBM:\"AAC002\",paramValue:\"610425198909152612\",paramType:\"String\",paramMC:\"身份证号码\"}]";
        //方案编码
        String INTERFACE_CONFIG_ID = "queryPersonInfo1";
        //脚本编码
        String INTERFACE_SCRIPT_ID = "JB_000001";
        R r=  new ServiceCall().callService(INTERFACE_CONFIG_ID,INTERFACE_SCRIPT_ID,data.getBody());
        Data response=  StructUtil.RtoResponseData(data, r);
        String result=JsonUtils.objectToJson(response);
        logService.update(response, result, jkId);
        return result;
	}
}