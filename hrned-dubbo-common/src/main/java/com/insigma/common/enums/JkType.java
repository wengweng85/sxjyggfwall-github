package com.insigma.common.enums;


/**
 * Created by wengsh on 2019/6/5.
 */
public enum  JkType {
	queryPersonInfo1("queryPersonInfo1", "查询就业登记状态和就业形式"),
	queryPersonInfo2("queryPersonInfo2", "查询职业资格等级、专业技术职务和学历"),
	queryPersonInfo3("queryPersonInfo3", "查询就业援助对象的类型和就业援助对象认定日期"),
	queryPersonInfo4("queryPersonInfo4", "查询享受政策类型"),
	queryPersonInfo5("queryPersonInfo5", "查询发证机构和发证日期");

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