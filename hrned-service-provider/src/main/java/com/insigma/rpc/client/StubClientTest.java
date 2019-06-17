package com.insigma.rpc.client;


import com.insigma.rpc.ServiceCall;

/**
 * Created by admin
 */
public class StubClientTest {
    public static void main(String[] args) throws Exception {
        //数据访问接口参数
        //String QUERY_PARAM = "[{paramBM:\"AAC002\",paramValue:\"610425198909152612\",paramType:\"String\",paramMC:\"身份证号码\"}]";
        //方案编码
        String INTERFACE_CONFIG_ID = "queryJyCyInfo";
        //脚本编码
        String INTERFACE_SCRIPT_ID = "JB_000001";
        //请求数据
        String map="aac002:610425198909152612";
        new ServiceCall().callService(INTERFACE_CONFIG_ID,INTERFACE_SCRIPT_ID,map);
    }
}
