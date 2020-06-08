package main.com.qw.study;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @program: study
 * @description: 测试实体类
 * @author: HyJan
 * @create: 2020-04-23 09:56
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher extends Person{
    private Long number;
    private LocalDateTime createTime;

    @Override
    public String toString() {
        //让父类属性加载进来
        super.toString();

        return "Teacher{" +
                "number=" + number +
                ", createTime=" + createTime +
                '}';
    }
}
