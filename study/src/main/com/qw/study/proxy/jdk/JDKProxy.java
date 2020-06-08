package main.com.qw.study.proxy.jdk;

import java.lang.reflect.Proxy;

/**
 * @program: study
 * @description: 代理类
 * @author: HyJan
 * @create: 2020-06-02 15:48
 **/
public class JDKProxy {

    /***
     * 想为哪个接口就用它的classLoader，数组，handler则是他的实现类对象，handler也是要实现的，然后进行强转。
     * 如果只是想在前后做一些事情，可以在handler的相应方法在执行代理前后进行一些处理。但是这种好像AOP思想更方便。
     *
     * 优点： Java动态代理的优势是实现无侵入式的代码扩展，也就是方法的增强；让你可以在不用修改源码的情况下，
     * 增强一些方法；在方法的前后你可以做你任何想做的事情（甚至不去执行这个方法就可以，这点aop则不然）
     *
     * 2.静态代理
     * 静态代理类：由程序员创建或者由第三方工具生成，再进行编译；在程序运行之前，代理类的.class文件已经存在了。
     * 静态代理类通常只代理一个类。
     * 静态代理事先知道要代理的是什么。
     *
     * 3.动态代理
     * 动态代理类：在程序运行时，通过反射机制动态生成。
     * 动态代理类通常代理接口下的所有类。
     * 动态代理事先不知道要代理的是什么，只有在运行的时候才能确定。
     * 动态代理的调用处理程序必须事先InvocationHandler接口，及使用Proxy类中的newProxyInstance方法动态的创建代理类。
     * Java动态代理只能代理接口，要代理类需要使用第三方的CLIGB等类库。
     *
     * @return
     */
    public static IUserService getProxy() {
        IUserService proxy = (IUserService) Proxy.newProxyInstance(IUserService.class.getClassLoader(), new Class[]{IUserService.class},
                new JDKHandler(new UserServiceImpl()));
        return proxy;
    }

    public static void main(String[] args) {
        IUserService proxy = getProxy();
        proxy.get("你好啊");
    }
}
