package com.insigma.serviceimp;


import org.springframework.stereotype.Component;

import com.insigma.common.struct.Data;
import com.insigma.common.struct.R;
import com.insigma.common.struct.StructUtil;
import com.insigma.common.util.JsonUtils;
import com.insigma.rpc.ServiceCall;
import com.insigma.service.QueryPersonInfo3Service;

/**
 * @author wengsh
 *
 */
@Component("QueryPersonInfo3Service")
public class QueryPersonInfo3ServiceImpl implements QueryPersonInfo3Service {

	public String exec(String request) {
		Data data=JsonUtils.jsonToPojo(request, Data.class);
        //方案编码
        String INTERFACE_CONFIG_ID = "queryPersonInfo3";
        //脚本编码
        String INTERFACE_SCRIPT_ID = "JB_000001";
        R result=  new ServiceCall().callService(INTERFACE_CONFIG_ID,INTERFACE_SCRIPT_ID,data.getBody());
        return StructUtil.RtoResponseData(data, result);
	}
}