package main.com.qw.study;


import java.time.Instant;

/**
 * @program: study
 * @description: 测试类
 * 测试是否等于null的时候，在java中变量在左边还是右边都是一样的
 * 测试 equals 方法的时候，常量放前面有效防止NPE， java7之后建议使用Object的equals校验方式。
 * @author: HyJan
 * @create: 2020-01-07 14:15
 **/
public class Test {

    public static void main(String[] args) {
        Person person = new Person();
        person = null;
        if (person == null){
            System.out.println("我就是这么写，你能把我怎么样");
        }else {
            System.out.println("就是不能这么写");
        }

//        person.equals("haha");  //此时就会NPE

        // 这时不会报错NPE （有空可以细致考究）
        "haha".equals(person);


        // 具体的时间相关操作，推荐使用Instant类
        System.out.println(Instant.now());

        System.out.println(Instant.MAX);

        /** ===============================  Map（TreeMap） ==========================================  */

    }
}
