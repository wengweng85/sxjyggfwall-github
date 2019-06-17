package com.insigma.mvc.service.log;

import com.insigma.common.enums.JkType;
import com.insigma.common.struct.Data;



/**
 * 日志服务
 */
public interface LogService {

    public String add(Data data,String request, JkType jktype);
    public void update(Data data,String respose,String jkId);
}
