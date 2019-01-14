package com.insigma.cloud.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * CasUser
 * @author  wengsh
 */
public class CasUser {

    @JsonProperty("id")
    @NotNull
    private String username;

    @JsonProperty("@class")
    //需要返回实现org.apereo.cas.authentication.principal.Principal的类名接口
    private String clazz = "org.apereo.cas.authentication.principal.SimplePrincipal";


    @JsonProperty("attributes")
    private Map<String, Object> attributes = new HashMap<String, Object>();

    @JsonIgnore
    @NotNull
    private String password;

    @JsonIgnore
    //用户是否不可用
    private boolean disable = false;


    @JsonIgnore
    //用户是否过期
    private boolean expired = false;

    @JsonIgnore
    //是否锁定
    private boolean locked = false;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
