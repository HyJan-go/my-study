package com.wefly.freemaker;

/**
 * @program: test
 * @description: TODO
 * @author: HyJan
 * @create: 2020-05-28 10:16
 **/
public class Field {

    private String fieldName;

    private String code;

    private String msg;

    public Field(String fieldName, String code, String msg) {
        this.fieldName = fieldName;
        this.code = code;
        this.msg = msg;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
