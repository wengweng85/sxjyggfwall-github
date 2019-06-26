package com.insigma.rpc.serviceimp;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.insigma.common.struct.Data;
import com.insigma.common.struct.R;
import com.insigma.common.struct.StructUtil;
import com.insigma.common.util.JsonUtils;
import com.insigma.rpc.ServiceCall;
import com.insigma.rpc.service.QueryPersonInfo1Service;

/**
 * @author wengsh
 *
 */
@Component("QueryPersonInfo1Service")
public class QueryPersonInfo1ServiceImpl implements QueryPersonInfo1Service {

	private Log log=LogFactory.getLog(QueryPersonInfo5ServiceImpl.class);
	
	/**
	 * [{paramBM:\"AAC002\",paramValue:\"320623198912110909\",paramType:\"String\",paramMC:\"公民身份证号码\"},{paramBM:\"AAC003\",paramValue:\"刘某某\",paramType:\"String\",paramMC:\"姓名\"}]
	  body:{
	      "aac002":"320623198912110909",
	      "aac003":"刘某某"
	 }
	 */
	public String exec(String request) {
		log.info("request=>"+request);
		Data data=JsonUtils.jsonToPojo(request, Data.class);
		   //数据访问接口参数
        //String QUERY_PARAM = "[{paramBM:\"AAC002\",paramValue:\"610425198909152612\",paramType:\"String\",paramMC:\"身份证号码\"}]";
        //方案编码
        String INTERFACE_CONFIG_ID = "queryPersonInfo1";
        //脚本编码
        String INTERFACE_SCRIPT_ID = "JB_000001";
		
        R r=  new ServiceCall().callService(INTERFACE_CONFIG_ID,INTERFACE_SCRIPT_ID,data.getBody());
       /* Row row=new Row();
        row.setAac002("sfsdfsdfdsfdsf22");
        row.setAac003("翁绍辉");
        R r=R.success(row);*/
        Data response=  StructUtil.RtoResponseData(data, r);
        String result=JsonUtils.objectToJson(response);
    	log.info("result=>"+result);
        return result;
	}
}