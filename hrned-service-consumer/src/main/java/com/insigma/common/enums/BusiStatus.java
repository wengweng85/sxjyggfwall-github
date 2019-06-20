package com.insigma.common.enums;


/**
 * Created by wengsh on 2019/6/5.
 */
public enum  BusiStatus {
    SUCCESS("1", "业务成功"),
    NODATA("0", "业务失败-没有数据");

    private String code;
    private String name;

    private BusiStatus(String code,String name){
        this.code=code;
        this.name=name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}