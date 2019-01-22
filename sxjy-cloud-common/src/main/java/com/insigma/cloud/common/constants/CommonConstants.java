package com.insigma.cloud.common.constants;

/**
 * 全局异常
 */
public class CommonConstants {
    //jwt相关参数
    public final static String CONTEXT_TOKEN="Authorization";
    public final static String CURRENT_USER_INFO="CURRENT_USER_INFO";
    public final static String CONTEXT_USERNAME="username";
    public final static String CONTEXT_USER_ID="userid";
    public final static String CONTEXT_NAME="name";
    public final static String JWT_PRIVATE_KEY ="sxjy-cloud";
    public final static String EXPIRES_TIME =  "expires";
    public final static String TOKEN = "token";

    //appkey相关参数
    public final static String CONTEXT_APPKEY="appkey";

    //签名相关参数
    public final static String SIGN_SIGNATURE="signature";
    public final static String SIGN_TIMESTAMP="timestamp";
    public final static String SIGN_NONCE="nonce";
}
