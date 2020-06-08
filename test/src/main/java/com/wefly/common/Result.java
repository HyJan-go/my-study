package com.wefly.common;

import lombok.Data;


/**
 * @program: test
 * @description: TODO
 * @author: HyJan
 * @create: 2020-06-05 17:43
 **/
@Data
public class Result {

    private Integer ret;

    private String msg;

    private DataClass data;
}
