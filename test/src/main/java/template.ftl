package com.qw.wefly.core.modules.tencent.enums;

import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ${className} {

    //
<#list fields as attr>
    ${attr.fieldName}(${attr.code} , "${attr.msg}"),
</#list>
    ;

    private final String msg;
    private final Integer code;

    private static TreeMap<Integer, String> map = new TreeMap<>();

    static {
        ${className}[] values = ${className}.values();
        if (values.length > 0) {
            for (${className} value : values) {
            map.put(value.getCode(), value.getMsg());
           }
        }
    }

    ${className}(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public Integer getCode() {
        return code;
    }

    public static TreeMap<Integer, String> getMap() {
        return map;
    }
}
