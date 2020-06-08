package main.com.qw.study.TimeOutAnnotation.test;

import main.com.qw.study.TimeOutAnnotation.TimeoutAnnotationTest;
import main.com.qw.study.TimeOutAnnotation.impl.TimeoutAnnotationTestImpl;

/**
 * @program: study
 * @description: 测试类
 * @author: HyJan
 * @create: 2020-04-26 14:24
 **/
public class TimeOutTest {

    public static void main(String[] args) {
        TimeoutAnnotationTestImpl timeOutTest = new TimeoutAnnotationTestImpl();
        timeOutTest.print();
    }


}
