package com.insigma.cloud.common.advice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.insigma.cloud.common.rsa.RSAUtils_Server;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;


/**
 * 请求数据加密
 * @author wengsh
 *
 */
//@ControllerAdvice
public class MyRequestBodyAdvice implements RequestBodyAdvice {

    private final static Logger logger = LoggerFactory.getLogger(MyRequestBodyAdvice.class);


    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        try {
            return new MyHttpInputMessage(inputMessage,parameter,targetType,converterType);
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    class MyHttpInputMessage implements HttpInputMessage {
       
    	private HttpHeaders headers;

        private InputStream body;

        public MyHttpInputMessage(HttpInputMessage inputMessage,MethodParameter parameter, Type targetType,Class<? extends HttpMessageConverter<?>> converterType) throws Exception {
            this.headers = inputMessage.getHeaders();
            String data="";
            String sign="";
            String body = IOUtils.toString(inputMessage.getBody());
            logger.debug("request body:"+body);
            JSONObject jsonobject = JSON.parseObject(body);
            sign= jsonobject.getString("sign");
            data= jsonobject.getString("data");
            if(sign!=null&&data!=null){
                data = RSAUtils_Server.decryptByAesAndRsaPrivateKey(data, sign);
                logger.debug("request body encode :" + data);
                this.body = IOUtils.toInputStream(data, "UTF-8");
            }else{
                this.body=IOUtils.toInputStream(body);;
            }
        }

        @Override
        public InputStream getBody() throws IOException {
            return body;
        }

        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }
    }
}