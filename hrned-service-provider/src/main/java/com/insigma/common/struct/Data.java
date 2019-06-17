package com.insigma.common.struct;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.insigma.common.constraint.SMContraint;
import com.insigma.common.util.JsonUtils;
import com.insigma.common.util.SM4Utils;

/**
 * Created by wengsh on 2019/6/5.
 */
public class Data {
    private Header header;
    private String body;

    public Data(String body){
        this.header=initHeader();
        this.body=body;
        sign();
    }

    public Data(){
      
    }
    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }


    public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	private Header initHeader(){
        Header header=new Header();
        header.setServiceCode(SMContraint.SERVICEAREACODE);
        header.setAppCode(SMContraint.APPCODE);
        header.setServiceAreaCode(SMContraint.SERVICEAREACODE);
        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMddhhmmss");
        header.setServiceReqTime(dateFormat.format(calendar.getTime()));
        dateFormat= new SimpleDateFormat("yyyyMMdd");
        header.setServiceReqId(SMContraint.APPCODE+dateFormat.format(calendar.getTime()));
        return header;
    }

    private void sign(){
        this.header.setSignature(SM4Utils.encryptData_ECB(JsonUtils.objectToJson(body)));
    }

    public String beanToJson(){
       return JsonUtils.objectToJson(this);
    }

    public Object jsonToBean(String json){
       return JsonUtils.jsonToPojo(json,Data.class);
    }

    public static void main(String [] args){
        Data data=new Data("{\"aac002\":\"362326198702244545\",\"aac003\":\"翁绍辉\"}");
        System.out.println(data.beanToJson());
        String result=data.beanToJson();
        data.jsonToBean(result);
    }
}
