package com.wefly.freemaker;

import java.util.TreeMap;

public enum Template {
    ACTIVE(1, "活跃"),

    PAID(2, "付费");

    private final String msg;
    private final Integer code;

    private static TreeMap<Integer, String> map = new TreeMap<>();

    static {
        Template[] values = Template.values();
        if (values.length > 0) {
            for (Template value : values) {
                map.put(value.getCode(), value.getMsg());
            }
        }
    }

    Template(Integer code, String msg) {
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
