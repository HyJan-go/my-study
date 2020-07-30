package com.wefly.freemaker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @program: test
 * @description: 要获取的实体类封装
 * @author: HyJan
 * @create: 2020-05-28 10:16
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Field {

    private String fieldName;

    private String code;

    private String msg;

    /**
     * 注释
     */
    private String desc;

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
