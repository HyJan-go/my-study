package main.com.qw.study.elasticsearch;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @program: study
 * @description: 实体测试类
 * @author: HyJan
 * @create: 2020-04-21 15:35
 **/
@Data
@AllArgsConstructor
public class Student {
    private String name;
    private Integer age;
}
