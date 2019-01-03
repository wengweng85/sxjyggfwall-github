package com.insigma.cloud.common.context;


import com.insigma.cloud.common.constants.CommonConstants;
import com.insigma.mvc.model.SUser;

import java.util.HashMap;
import java.util.Map;

/**
 * SUserUtil
 */
public class SUserUtil {

    public static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

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

    /**
     *  setCurrentUser
     * @param suser
     */
    public static void setCurrentUser(SUser suser) {
        set(CommonConstants.CURRENT_USER_INFO,suser);
    }


    /**
     *  setCurrentUser
     */
    public static void removeCurrentUser() {
        set(CommonConstants.CURRENT_USER_INFO,null);
    }

    /**
     * getCurrentUser
     * @return
     */
    public static SUser getCurrentUser() {
        Object value = get(CommonConstants.CURRENT_USER_INFO);
        return (SUser)value;
    }

    public static String getToken() {
        Object value = get(CommonConstants.CONTEXT_TOKEN);
        return returnObjectValue(value);
    }

    public static void setToken(String token) {
        set(CommonConstants.CONTEXT_TOKEN, token);
    }


    private static String returnObjectValue(Object value) {
        return value == null ? null : value.toString();
    }

    public static void remove() {
        threadLocal.remove();
    }

}
