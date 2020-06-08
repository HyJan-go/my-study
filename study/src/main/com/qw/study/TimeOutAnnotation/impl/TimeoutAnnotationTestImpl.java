package main.com.qw.study.TimeOutAnnotation.impl;

import lombok.extern.slf4j.Slf4j;
import main.com.qw.study.TimeOutAnnotation.TimeoutAnnotationTest;
import org.springframework.stereotype.Service;

/**
 * @program: study
 * @description: 过时注解的测试
 * 过时注解可以加，但是不能出现另一个新的一模一样的方法名，只能在调用方看到过期了
 * @author: HyJan
 * @create: 2020-04-26 14:22
 **/
@Slf4j
@Service
public class TimeoutAnnotationTestImpl implements TimeoutAnnotationTest {

    /**
     * 打印一些信息
     */
    @Override
    @Deprecated
    public void print() {
        log.info("这是还没过期的方法");
        System.out.println("第一个方法");
    }
}
