package com.wefly.common;

import lombok.Data;

import java.util.List;

/**
 * @program: test
 * @description: 腾讯字段的返回数据
 * @author: HyJan
 * @create: 2020-06-05 17:15
 **/
@Data
public class TencentModelField {
   private String name;
   private String displayName;
   private String tip;
   private String func;
   private List<String> category;
   private String type;
   private List<String> plat;
   private List<String> aliases;
}
