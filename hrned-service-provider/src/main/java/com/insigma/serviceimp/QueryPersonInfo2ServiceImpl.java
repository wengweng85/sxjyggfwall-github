package com.insigma.serviceimp;


import org.springframework.stereotype.Component;

import com.insigma.common.struct.Data;
import com.insigma.common.struct.R;
import com.insigma.common.struct.StructUtil;
import com.insigma.common.util.JsonUtils;
import com.insigma.rpc.ServiceCall;
import com.insigma.service.QueryPersonInfo2Service;

/**
 * @author wengsh
 *
 */
@Component("QueryPersonInfo2Service")
public class QueryPersonInfo2ServiceImpl implements QueryPersonInfo2Service {

	public String exec(String request) {
		Data data=JsonUtils.jsonToPojo(request, Data.class);
        //方案编码
        String INTERFACE_CONFIG_ID = "queryPersonInfo2";
        //脚本编码
        String INTERFACE_SCRIPT_ID = "JB_000001";
        R result=  new ServiceCall().callService(INTERFACE_CONFIG_ID,INTERFACE_SCRIPT_ID,data.getBody());
        return StructUtil.RtoResponseData(data, result);
    
	}
}