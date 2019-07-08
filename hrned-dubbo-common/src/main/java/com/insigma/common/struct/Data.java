package com.insigma.common.struct;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.insigma.common.constraint.SMContraint;
import com.insigma.common.util.JsonUtils;

/**
 * Created by wengsh on 2019/6/5.
 */
public class Data {
    private Header header;
    private String body;

    public Data(String body){
        this.header=initHeader();
        this.body=body;
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
        header.setServiceCode(SMContraint.CLIENT_SERVICEAREACODE);
        header.setAppCode(SMContraint.CLIENT_APPCODE);
        header.setServiceAreaCode(SMContraint.CLIENT_SERVICEAREACODE);
        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMddhhmmss");
        header.setServiceReqTime(dateFormat.format(calendar.getTime()));
        dateFormat= new SimpleDateFormat("yyyyMMdd");
        header.setServiceReqId(SMContraint.CLIENT_APPCODE+dateFormat.format(calendar.getTime()));
        header.setSignature("nosign");
        return header;
    }
  

    public String beanToJson(){
       return JsonUtils.objectToJson(this);
    }

    public Object jsonToBean(String json){
       return JsonUtils.jsonToPojo(json,Data.class);
    }

    public static void main(String [] args){
        Data data=new Data("{\"aac002\":\"362326198702244545\",\"aac003\":\"ŒÃ…‹ª‘\"}");
        System.out.println(data.beanToJson());
        String result=data.beanToJson();
        data.jsonToBean(result);
    }
}
