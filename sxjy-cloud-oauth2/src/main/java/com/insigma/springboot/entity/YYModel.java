package com.insigma.springboot.entity;


import com.alibaba.fastjson.JSONObject;

/**
 */
public class YYModel {
    private String yy;
    private int zz;

    public String getYy() {
        return yy;
    }

    public void setYy(String yy) {
        this.yy = yy;
    }

    public int getZz() {
        return zz;
    }

    public void setZz(int zz) {
        this.zz = zz;
    }

    public String toJSONString(){
        return JSONObject.toJSONString(this);
    }
}
