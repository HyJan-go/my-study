package main.com.qw.study.proxy.cglib;

import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * @program: study
 * @description: cglib 代理类
 * @author: HyJan
 * @create: 2020-06-02 16:50
 **/
public class CglibProxy {

    /**
     *  总结：
     * 1. 静态代理实现较简单，只要代理对象对目标对象进行包装，即可实现增强功能，但静态代理只能为一个目标对象服务，
     * 如果目标对象过多，则会产生很多代理类。
     * 2. JDK动态代理需要目标对象实现业务接口，代理类只需实现InvocationHandler接口。
     * 3. 静态代理在编译时产生class字节码文件，可以直接使用，效率高。
     * 4. 动态代理必须实现InvocationHandler接口，通过反射代理方法，比较消耗系统性能，但可以减少代理类的数量，使用更灵活。
     * 5. cglib代理无需实现接口，通过生成类字节码实现代理，比反射稍快，不存在性能问题，但cglib会继承目标对象，
     * 需要重写方法，所以目标对象不能为final类
     *
     */
    public static void main(String[] args) {
        // 创建一个Enhancer对象
        Enhancer enhancer = new Enhancer();
        // 设置被代理的类（使其成为生成类的父类）
        enhancer.setSuperclass(Student.class);
        // 创建一个回调接口
        Callback interceptor = new MethodInterceptor(){

            @Override
            public Object intercept(Object obj, Method method, Object[] objects, MethodProxy proxy) throws Throwable {
                System.out.println("原方法名称:::" + method.getName());
                System.out.println("原方法所在的类是 ：" + method.getDeclaringClass());
                //调用的是父类，也就是委托类的方法
                System.out.println("我是：" + (String)proxy.invokeSuper(obj,args));
                return null;
            }
        };

//        enhancer.setCallback(interceptor);
        // 第一个是没执行方法，第二个倒是执行了方法
        Callback[] callbacks = {NoOp.INSTANCE , interceptor};
        enhancer.setCallbackFilter(new CgProxy(new Student()));
        enhancer.setCallbacks(callbacks);
        Student student = (Student) enhancer.create();
        student.getName();
    }

}
