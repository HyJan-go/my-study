package main.com.qw.study;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @program: study
 * @description: 人的实体类
 * @author: HyJan
 * @create: 2020-01-07 14:14
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {
    private String userName;
    private Integer age;
}
