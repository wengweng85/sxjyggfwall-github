package com.insigma.common.enums;


/**
 * Created by wengsh on 2019/6/5.
 */
public enum  BusiStatus {
    SUCCESS("1", "ҵ��ɹ�"),
    NODATA("0", "ҵ��ʧ��-û������");

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