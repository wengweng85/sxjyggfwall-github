package com.insigma.cloud.common.constants;

/**
 * 全局参数
 */
public class CommonConstants {
    //jwt相关参数
    public final static String CONTEXT_AUTHORIZATION ="Authorization";
    public final static String CONTEXT_BEARE ="Bearer ";
    public final static String CURRENT_USER_INFO="CURRENT_USER_INFO";
    public final static String CONTEXT_USERNAME="username";
    public final static String CONTEXT_USER_ID="userid";
    public final static String CONTEXT_NAME="name";
    public final static String JWT_PRIVATE_KEY ="jwt-private-key-cloud";
    public final static String EXPIRES_TIME =  "expires";
    public final static String CONTEXT_TOKEN = "token";

    //appkey相关参数
    public final static String CONTEXT_APPKEY="appkey";

    //签名相关参数
    public final static String SIGN_SIGNATURE="signature";
    public final static String SIGN_TIMESTAMP="timestamp";
    public final static String SIGN_NONCE="nonce";

    //response重定向
    public final static String RESPONSE_HEADER_STATUSCODE="statuscode";
    public final static String RESPONSE_TOKEN_ERROR = "token_error";
    public final static String RESPONSE_HEADER_REDIRECTURL = "redirecturl";
    public final static String RESPONSE_REDIRECTURL = "/web/gotologin";
}
