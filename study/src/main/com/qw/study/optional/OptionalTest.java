package main.com.qw.study.optional;

import java.util.Objects;
import java.util.Optional;

/**
 * @program: study
 * @description: 测试java8新特性之optional 大概率避免空指针异常
 * @author: HyJan
 * @create: 2020-04-20 17:05
 **/
public class OptionalTest {

    public static void main(String[] args) {

        // 参数不能是null
        Optional<Integer> param1 = Optional.of(1);

        // 参数可以不为null
        Optional<Integer> param2 = Optional.ofNullable(2);

        // 参数可以为null
        Optional<Integer> param3 = Optional.ofNullable(null);

        // Optional.empty()：所有null包装成的Optional对象
        Optional<Integer> param4 = Optional.ofNullable(null);
        Optional<Integer> param5 = Optional.ofNullable(null);
        Optional<Integer> param6 = Optional.empty();
        System.out.println(param1 == param2); //false
        System.out.println(param4 == param5); //true
        System.out.println(param3 == param6);  //true
        System.out.println(param3 == Optional.<Integer>empty());  //true

        Object o1 = Optional.<Integer>empty();
        Object o2 = Optional.<String>empty();
        System.out.println(o1 == o2); //true

        /** =================================================================== */

        //isPresent()：判断值是否存在
        System.out.println(param1.isPresent()); // true
        System.out.println(param3.isPresent()); // false

        //ifPresent(Consumer consumer)：如果option对象保存的值不是null，则调用consumer对象，否则不调用
        param1.ifPresent(System.out::println);

        //没有值，不做任何操作，支持lambda表达式
        param3.ifPresent(System.out::println);

        //orElse(value)：如果optional对象保存的值不是null，则返回原来的值，否则返回value
        Integer integer = param1.orElse(500);
        System.out.println(integer); //1

        Integer integer1 = param3.orElse(20);
        System.out.println(integer1); //20

        //orElseGet(Supplier supplier)：功能与orElse一样，只不过orElseGet参数是一个对象  ps:发现supplier的写法都是 () -> {}，不可缺省
        Integer integer2 = param1.orElseGet(() -> {
            return 100;
        });
        System.out.println(integer2); // 1

        Integer integer3 = param3.orElseGet(() -> {
            return 120;
        });
        System.out.println(integer3); //120

        
        //orElseThrow()：值不存在则抛出异常，存在则什么不做，有点类似Guava的Precoditions
//       try {
//           param3.orElseThrow(() -> {
//               //指定抛出的错误类型
//               throw new IllegalStateException();
//           });
//       }catch (IllegalStateException e){
//           e.printStackTrace();
//       }


       //filter(Predicate)：判断Optional对象中保存的值是否满足Predicate，并返回新的Optional。 ps:Predicate的写法，不要{}
        Optional<Integer> filter1 = param1.filter((a) ->
            a == null
        ); //符合条件就返回值，符合的值，否则返回null

        Optional<Integer> filter2 = param1.filter((a) -> a == 1);
        System.out.println(filter1.isPresent()); //false 值为null
        System.out.println(filter2.isPresent()); //true 值为1

        Optional<Integer> filter3 = param3.filter((a) -> a == null); //如果是null相等，返回的还是null
        System.out.println(filter3.isPresent()); //false


        //map(Function)：对Optional中保存的值进行函数运算，并返回新的Optional(可以是任何类型)

        //如果值不为null
        Optional<String> s = param1.map((a) -> a + "haha");
        System.out.println(s.get());

        //如果值为null
        Optional<String> s1 = param3.map((a) -> a + "yeyeye");
        System.out.println(s1.isPresent()); //false
//        System.out.println(s1.get()); //没有值get会报错


        //flatMap()：功能与map()相似，差别请看如下代码。
        // flatMap方法与map方法类似，区别在于mapping函数的返回值不同。
        // map方法的mapping函数返回值可以是任何类型T，而flatMap方法的mapping函数必须是Optional。
        Optional<Integer> optional = Optional.ofNullable(152);
        Optional<String> s2 = optional.map(Objects::toString);
        System.out.println(s2.get());
        Optional<Optional<String>> s3 = s2.map((a) -> {
            return Optional.of("key" + a);
        });

        //与上面的返回值类型是不一样的
        Optional<String> s4 = s2.flatMap((a) -> {
            return Optional.of("key" + a);
        });
        System.out.println(s4.get());
    }
    
}
