package com.insigma.cloud.common.context;


import com.insigma.cloud.common.constants.CommonConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于threalocal的线程副本工具类
 */
public class SUserUtil {

    public static ThreadLocal<Map<String, Object>> threadLocal = new InheritableThreadLocal <Map<String, Object>>();

    public static void set(String key, Object value) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    public static Object get(String key) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        return map.get(key);
    }

    public static String getToken() {
        Object value = get(CommonConstants.CONTEXT_AUTHORIZATION);
        return returnObjectValue(value);
    }

    public static void setToken(String token) {
        set(CommonConstants.CONTEXT_AUTHORIZATION, token);
    }

    public static String getUserId() {
        Object value = get(CommonConstants.CONTEXT_USER_ID);
        return returnObjectValue(value);
    }

    public static String getUsername() {
        Object value = get(CommonConstants.CONTEXT_USERNAME);
        return returnObjectValue(value);
    }


    public static String getName() {
        Object value = get(CommonConstants.CONTEXT_NAME);
        return returnObjectValue(value);
    }


    public static void setName(String name) {
        set(CommonConstants.CONTEXT_NAME, name);
    }

    public static void setUserId(String userID) {
        set(CommonConstants.CONTEXT_USER_ID, userID);
    }

    public static void setUsername(String username) {
        set(CommonConstants.CONTEXT_USERNAME, username);
    }


    private static String returnObjectValue(Object value) {
        return value == null ? null : value.toString();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
