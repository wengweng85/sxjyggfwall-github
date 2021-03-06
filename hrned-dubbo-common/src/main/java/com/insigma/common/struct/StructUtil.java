package com.insigma.common.struct;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.insigma.common.constraint.SMContraint;

public class StructUtil {
	
	/**
	 * 工具转换类 将返回结果转换成接口传输数据
	 * @param request
	 * @param r
	 * @return
	 */
	public static Data RtoResponseData(Data request,R r){
	    Data response=new Data();
        Header header=request.getHeader();
        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMddhhmmss");
        header.setServiceResTime(dateFormat.format(calendar.getTime()));
        dateFormat= new SimpleDateFormat("yyyyMMdd");
        header.setServiceResId(SMContraint.SERVER_APPCODE+dateFormat.format(calendar.getTime())+SerialNumberTool.getInstance().generaterNextNumber(9));
        response.setHeader(request.getHeader());
        header.setCommStatus(r.getCommStatus());
        header.setBusiStatus(r.getBusiStatus());
        header.setMsg(r.getMessage());
        header.setSignature("");
        response.setHeader(header);
        response.setBody(r.getBody());
        return response;
	}

}
