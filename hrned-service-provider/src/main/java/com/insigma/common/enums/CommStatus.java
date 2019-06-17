package com.insigma.common.enums;


/**
 * Created by wengsh on 2019/6/5.
 */
public enum  CommStatus {

    OK("00", "�ɹ�"),
    FAIL("10", "ҵ��ʧ��"),
    ERROR("20", "ϵͳ����"),
    SIGNFAIL("30", "ǩ��ʧ��"),
    DecryptionFail("40", "����ʧ��"),
    INSUFFICIENTpRIVILEGES("50", "Ȩ�޲���"),
    OTHERFAIL("90", "��������");

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