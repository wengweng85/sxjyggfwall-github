package com.insigma.cloud.common.utils;

import com.insigma.cloud.common.constants.CommonConstants;
import com.insigma.mvc.model.AccessToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * JwtUtils
 */
public class JwtUtils {

    public static final long MAX_AGE = 1000 * 60 * 60 * 24/24/12; //24小时
    /**
     * generateToken
     * @param accessToken
     * @return
     * @throws Exception
     */
    public static String generateToken(AccessToken accessToken) throws Exception {
        String token = Jwts.builder()
                .setSubject(accessToken.getUsername())
                .claim(CommonConstants.CONTEXT_USER_ID, accessToken.getUserid())
                .claim(CommonConstants.CONTEXT_NAME, accessToken.getName())
                .claim(CommonConstants.EXPIRES_TIME,new Date(System.currentTimeMillis()+MAX_AGE/2))
                .setExpiration(new Date(System.currentTimeMillis()+MAX_AGE))
                .signWith(SignatureAlgorithm.HS256, CommonConstants.JWT_PRIVATE_KEY)
                .compact();
        return token;
    }


    /**
     * getInfoFromToken
     * @param token
     * @return
     * @throws Exception
     */
    public static AccessToken getInfoFromToken(String token) throws Exception {
        Claims claims = Jwts.parser()
                .setSigningKey(CommonConstants.JWT_PRIVATE_KEY).parseClaimsJws(token)
                .getBody();
        return new AccessToken(claims.getSubject(),
                claims.get(CommonConstants.CONTEXT_USER_ID).toString(),
                claims.get(CommonConstants.CONTEXT_NAME).toString());
    }
}
