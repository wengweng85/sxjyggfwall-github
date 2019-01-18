package com.insigma.cloud.common.utils;

/**
 * @author admin
 *
 * 流水号生成器
 */
public class IdWorker {

    /**
     * 生成业务流水号 规则是basekey+分布式唯一流水号
     * @param basekey
     * @return
     */
    public static String getSerialNumber(String basekey){
        return basekey+getSerialNumber();
    }

    public static long  getSerialNumber(){
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        long id = idWorker.nextId();
        return id;
    }
}
