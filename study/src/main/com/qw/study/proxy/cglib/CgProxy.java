package main.com.qw.study.proxy.cglib;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @program: study
 * @description: 分离的代理类
 * @author: HyJan
 * @create: 2020-06-02 17:26
 **/
public class CgProxy implements CallbackFilter {

    private Object target;

    public CgProxy(Object target){
        this.target = target;
    }

    /**
     * 返回的0或者是1是前面的callbacks数组的索引值
     * 然后前面索引的操作存放位置跟返回索引有直接的关系
     * @param method
     * @return
     */
    @Override
    public int accept(Method method) {
        int result = 1;
        if (Objects.equals(method.getName(),"getName")){
            result = 0;
        }
        return result;
    }
}
