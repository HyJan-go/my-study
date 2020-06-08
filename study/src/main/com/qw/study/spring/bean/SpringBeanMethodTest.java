package main.com.qw.study.spring.bean;

import main.com.qw.study.Person;
import main.com.qw.study.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;

/**
 * @program: study
 * @description: 测试spring bean给我们带来的便利方法
 * @author: HyJan
 * @create: 2020-04-23 09:54
 **/
@Slf4j
public class SpringBeanMethodTest {

    public static void main(String[] args) {
        testBeanCopyMethod();
    }


    /**
     * 测试spring的实体类复制方法
     * 实体类的属性拷贝, 对于名称相同的属性直接复制。浅拷贝
     * Java开发手册强烈不建议使用apache的beanUtil，可以用spring的
     * Apache BeanUtils 性能较差
     */
    public static void testBeanCopyMethod(){
        Person person = new Person("xiaoMing",25);
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(person,teacher);
        // 更快速的处理方法
//        BeanCopier.create();
        System.out.println(teacher.getAge());
        System.out.println(teacher.getUserName());
    }

}
