package com.insigma.cloud.common.advice;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insigma.cloud.common.rsa.RSAUtils_Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 返回数据解密
 * @author wengsh
 *
 */
//@ControllerAdvice
public class MyResponseBodyAdvice implements ResponseBodyAdvice {
    private final static Logger logger = LoggerFactory.getLogger(MyResponseBodyAdvice.class);

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String encodedata=null;
            objectMapper.setSerializationInclusion(Include.NON_NULL);
            String result = objectMapper.writeValueAsString(body);
            logger.debug("response data:" + result);
            encodedata = RSAUtils_Server.encryptByAesAndRsaPrivateKey(result);
            logger.debug("response data encode:"+encodedata);
            return encodedata;
        } catch (Exception e) {
            e.printStackTrace();
            //return body;
        }
        logger.debug("服务端加密失败,返回明文");
        return body;
    }
}
