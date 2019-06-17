package com.insigma.common.enums;


/**
 * Created by wengsh on 2019/6/5.
 */
public enum  CommStatus {

    OK("00", "成功"),
    FAIL("10", "业务失败"),
    ERROR("20", "系统错误"),
    SIGNFAIL("30", "签名失败"),
    DecryptionFail("40", "解密失败"),
    INSUFFICIENTpRIVILEGES("50", "权限不足"),
    OTHERFAIL("90", "其他错误");

    private String code;
    private String name;

    private CommStatus(String code,String name){
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