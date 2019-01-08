package com.insigma.cloud.common.utils;



import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonInclude;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * jwt������
 *
 * @author admin
 */
public class JWT_Server {

    private static final String SECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQK 6yhn^YHNabcdefg>?N<:{LWPW";

    private static final String EXP = "exp";

    private static final String PAYLOAD = "payload";

    public static final long MAX_AGE = 1000 * 60 * 60 * 24; //1��
    //���ܣ�����һ���������Ч��
    public static <T> String sign(T object) throws Exception {
        final JWTSigner signer = new JWTSigner(SECRET);
        final Map<String, Object> claims = new HashMap<>(4);
        ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        String jsonString = mapper.writeValueAsString(object);
        claims.put(PAYLOAD, jsonString);
        claims.put(EXP, System.currentTimeMillis() + MAX_AGE);
        return signer.sign(claims);
    }



    //���ܣ�����һ�����ܺ��token�ַ����ͽ��ܺ������
    public static <T> T unsign(String jwt, Class<T> classT) throws Exception{
        final JWTVerifier verifier = new JWTVerifier(SECRET);
        final Map<String, Object> claims = verifier.verify(jwt);
        if (claims.containsKey(EXP) && claims.containsKey(PAYLOAD)) {
            long exp = (Long) claims.get(EXP);
            long currentTimeMillis = System.currentTimeMillis();
            if (exp > currentTimeMillis) {
                String json = (String) claims.get(PAYLOAD);
                ObjectMapper objectMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
                T t=objectMapper.readValue(json, classT);
                return t;
            }else{
                throw new Exception("token�ѹ���");
            }
        }else{
            throw new Exception("�Ƿ�token");
        }
    }

    public static void main(String [] args){
        System.out.println( new Date(1546934648905L).toLocaleString());
        System.out.println( new Date().toLocaleString());

    }

}