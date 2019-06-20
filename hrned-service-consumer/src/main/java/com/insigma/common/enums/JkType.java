package com.insigma.common.enums;


/**
 * Created by wengsh on 2019/6/5.
 */
public enum  JkType {
	queryPersonInfo1("queryPersonInfo1", "queryPersonInfo1"),
	queryPersonInfo2("queryPersonInfo2", "queryPersonInfo2"),
	queryPersonInfo3("queryPersonInfo3", "queryPersonInfo3"),
	queryPersonInfo4("queryPersonInfo4", "queryPersonInfo4"),
	queryPersonInfo5("queryPersonInfo5", "queryPersonInfo5");

    private String code;
    private String name;

    private JkType(String code,String name){
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