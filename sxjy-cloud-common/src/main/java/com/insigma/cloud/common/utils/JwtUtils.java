package com.insigma.cloud.common.utils;

import com.insigma.cloud.common.constants.CommonConstants;
import com.insigma.mvc.model.AccessToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * JwtUtils
 */
public class JwtUtils {

    private final static Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    //public static final long MAX_AGE = 1000 * 60 * 60 * 24/24/12; //24小时
    public static final long MAX_AGE = 1000 * 60 * 60 * 24; //24小时
    /**
     * generateToken
     * @param accessToken
     * @return
     * @throws Exception
     */
    public static String generateToken(AccessToken accessToken) throws Exception {
        Date expiredate=new Date(System.currentTimeMillis()+MAX_AGE);

        String token = Jwts.builder()
                .setSubject(accessToken.getUsername())
                .claim(CommonConstants.CONTEXT_USER_ID, accessToken.getUserid())
                .claim(CommonConstants.CONTEXT_NAME, accessToken.getName())
                .claim(CommonConstants.EXPIRES_TIME, expiredate)
                .setExpiration(expiredate)
                .signWith(SignatureAlgorithm.HS256, CommonConstants.JWT_PRIVATE_KEY)
                .compact();
        logger.debug("token expire date ="+expiredate.toLocaleString());
        logger.debug("set token  ="+token);
        return token;
    }


    /**
     * getInfoFromToken
     * @param token
     * @return
     * @throws Exception
     */
    public static AccessToken getInfoFromToken(String token) throws Exception {
        logger.debug("get token  ="+token);
        Claims claims = Jwts.parser()
                .setSigningKey(CommonConstants.JWT_PRIVATE_KEY).parseClaimsJws(token)
                .getBody();
        return new AccessToken(claims.getSubject(),
                claims.get(CommonConstants.CONTEXT_USER_ID).toString(),
                claims.get(CommonConstants.CONTEXT_NAME).toString());
    }

    /**
     * 工具测试
     * @param args
     */
    public static void main(String [] args){
        try{
            AccessToken accessToken=new AccessToken();
            accessToken.setUserid("123456");
            accessToken.setName("测试");
            String token=generateToken(accessToken);
            //token="eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyaWQiOiIxMjM0NTYiLCJuYW1lIjoi5ai05ayt55ivIiwiZXhwaXJlcyI6MTU0ODMwMzMwNDU5MSwiZXhwIjoxNTQ4MzAzMzA0fQ.k00_1J48KofgpWCwASAWWUmfmnC41rO_DMTusly7gwY";
            AccessToken newaccessToken=getInfoFromToken(token);
            System.out.println(newaccessToken.getUserid());
            System.out.println(newaccessToken.getName());
        }catch (Exception ex){
            ex.printStackTrace();
        }


    }
}
