package com.qw.wefly.core.modules.tencent.enums;

import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ${className} {

    //
<#list fields as attr>
    ${attr.fieldName}("${attr.fieldName}","${attr.msg}"),
</#list>
    ;

    private final String msg;

    private final String key;

    private static TreeMap<String, String> map = new TreeMap<>();

    static {
        ${className}[] values = ${className}.values();
        if (values.length > 0) {
            for (${className} value : values) {
            map.put(value.name(), value.getMsg());
           }
        }
    }

    ${className}(String key,String msg) {
        this.key = key;
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getKey() {
        return this.key;
    }

    public static TreeMap<String, String> getMap() {
        return map;
    }
}
