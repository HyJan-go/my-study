package main.com.qw.study.proxy.jdk;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @program: study
 * @description: 调用处理程序类
 * 主要的负责处理类，实现这个InvocationHandler接口，就是JDKProxy委托给了InvocationHandler（jdk）代理
 * @author: HyJan
 * @create: 2020-06-02 15:31
 **/
@Slf4j
public class JDKHandler implements InvocationHandler {

    // 目标对象
    private Object target;

    public JDKHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("this is the proxy handler ...");
        log.info("method class = {}",method.getDeclaringClass());
        System.out.println("开始执行代理方法");
        // 执行代理方法
        Object result = method.invoke(target, args);
        System.out.println("执行代理方法结束");
        System.out.println("返回的结果是：：：" + result);
        log.info("method is invoke end ...");
        // 返回执行之后返回的结果
        return result;
    }

}
