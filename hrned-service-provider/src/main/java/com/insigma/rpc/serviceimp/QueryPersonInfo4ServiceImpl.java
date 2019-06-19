package com.insigma.rpc.serviceimp;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.insigma.common.struct.Data;
import com.insigma.common.struct.R;
import com.insigma.common.struct.StructUtil;
import com.insigma.common.util.JsonUtils;
import com.insigma.rpc.ServiceCall;
import com.insigma.rpc.service.QueryPersonInfo4Service;

/**
 * @author wengsh
 *
 */
@Component("QueryPersonInfo4Service")
public class QueryPersonInfo4ServiceImpl implements QueryPersonInfo4Service {

	private Log log=LogFactory.getLog(QueryPersonInfo5ServiceImpl.class);
	
	
	public String exec(String request) {
		log.info("request=>"+request);
		Data data=JsonUtils.jsonToPojo(request, Data.class);
		   //数据访问接口参数
        //String QUERY_PARAM = "[{paramBM:\"AAC002\",paramValue:\"610425198909152612\",paramType:\"String\",paramMC:\"身份证号码\"}]";
        //方案编码
        String INTERFACE_CONFIG_ID = "queryPersonInfo4";
        //脚本编码
        String INTERFACE_SCRIPT_ID = "JB_000001";
        R r=  new ServiceCall().callService(INTERFACE_CONFIG_ID,INTERFACE_SCRIPT_ID,data.getBody());
        Data response=  StructUtil.RtoResponseData(data, r);
        String result=JsonUtils.objectToJson(response);
        log.info("result=>"+result);
        return result;
	}
}