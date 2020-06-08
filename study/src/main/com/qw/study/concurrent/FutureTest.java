package main.com.qw.study.concurrent;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * @program: study
 * @description: java 8并发测试
 * <p>
 * 这里学习Future 在java8之后的新特性 CompletableFuture 特性
 * <p>
 * 可以根据方法的参数的类型来加速你的记忆。Runnable类型的参数会忽略计算的结果，Consumer是纯消费计算结果，BiConsumer会
 * 组合另外一个CompletionStage纯消费，Function会对计算结果做转换，BiFunction会组合另外一个CompletionStage的计算结果做转换。
 * @author: HyJan
 * @create: 2020-04-07 18:52
 **/
public class FutureTest {

    public static Random random = new Random();

    public static void main(String[] args) throws Exception {

//        testItsMethods();

//        testConvert();

//        testAction();

//        testRunAfterBoth();

//        testRunnable();

//        testThenCompose();

//        testThenCombine();

//        testEither();

        testAllOrAny();
    }

    public static void testItsMethods() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int i = 1 / 0;
            return i;
        });

        future.get();
//        future.join();
    }

    //测试future的结果类型转换，仅仅是在第一次计算完成之后，才会接着执行
    //需要注意的是，这些转换并不是马上执行的，也不会阻塞，而是在前一个stage完成后继续执行。
    public static void testConvert() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        //先异步再同步进行转换(由原来的int转化成了string)
        CompletableFuture<String> future1 = future.thenApplyAsync(i -> i * 10).thenApply(i -> i.toString());
        System.out.println(future1.get());
    }

    //测试只对结果执行Action,而不返回新的计算值，只返回对数据的操作，但不返回结果
    public static void testAction() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        //对数据进行打印
        CompletableFuture<Void> future1 = future.thenAccept(System.out::println);
        //获取数据打印发现结果是null，原因是没有返回结果
        System.out.println(future1.get());
    }

    //测试 runAfterBoth是当两个CompletionStage都正常完成计算的时候,执行一个Runnable，这个Runnable要使用计算的结果（当参数）,但是action没有结果返回。
    public static void testRunAfterBoth() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 100;
        });

        //第二个参数为action,前面两个执行完成才执行，action一定要两个参数，第一个参数是第一个的futurn的结果（第二一样），但是可以用可以不用
        CompletableFuture<Void> future1 = future.thenAcceptBoth(CompletableFuture.completedFuture(10), (x, y) -> System.out.println("哈哈"));

        System.out.println(future1.get());
    }

    //测试 当计算完成的时候会执行一个Runnable,与thenAccept不同，Runnable并不使用CompletableFuture计算的结果。
    public static void testRunnable() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 100;
        });

        //紧跟其后的进行执行，只有顺序上的联系，对于结果没有任何的关系，都不用当成参数传递进去
        CompletableFuture<Void> finished = future.thenRun(() -> {
            System.out.println("finished");
        });

        System.out.println(finished.get());
    }

    //接受一个Function作为参数，这个Function的输入是当前的CompletableFuture的计算值，
    // 返回结果将是一个新的CompletableFuture，这个新的CompletableFuture会组合原来的CompletableFuture和函数返回的CompletableFuture。
    //记住，thenCompose返回的对象并不一是函数fn返回的对象，如果原来的CompletableFuture还没有计算出来，它就会生成一个新的组合后的CompletableFuture。
    public static void testThenCompose() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 100;
        });

        //进行组合
        CompletableFuture<String> future1 = future.thenCompose(i -> {
            return CompletableFuture.supplyAsync(() -> {
                return i * 58 + "";
            });
        });

        System.out.println(future1.get());
    }

    //方法thenCombine用来复合另外一个CompletionStage的结果,两个CompletionStage是并行的
    //两个CompletionStage是并行执行的，它们之间并没有先后依赖顺序，other并不会等待先前的CompletableFuture执行完毕后再执行。
    //其实从功能上来讲,它们的功能更类似thenAcceptBoth，只不过thenAcceptBoth是纯消费，它的函数参数没有返回值，而thenCombine的函数参数fn有返回值。
    public static void testThenCombine() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 100;
        });

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            return "abc";
        });

        //由于这个方法是一定要有返回值的，所以action里面的函数一定要有东西，但是你可以不对参数进行操作
        CompletableFuture<Integer> future2 = future.thenCombine(future1, (x, y) -> 1 + 2);

        System.out.println(future2.get());
    }


    //当任意一个CompletableFuture计算完成的时候就会执行的两个方法。
    // acceptEither方法是当任意一个CompletionStage完成的时候，action这个消费者就会被执行。这个方法返回CompletableFuture<Void>
    //applyToEither方法是当任意一个CompletionStage完成的时候，fn会被执行，它的返回值会当作新的CompletableFuture<U>的计算结果。
    public static void testEither() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000 + random.nextInt(10000));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 200;
        });

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000 + random.nextInt(10000));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 500;
        });

        CompletableFuture<String> future2 = future.applyToEither(future1, l -> l.toString());

        System.out.println(future2.get());
    }

    //测试 辅助方法 allOf 和 anyOf
    //allOf方法是当所有的CompletableFuture都执行完后执行计算。
    //anyOf方法是当任意一个CompletableFuture执行完后就会执行计算，计算的结果相同。
    public static void testAllOrAny() throws Exception{
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000 + random.nextInt(10000));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 100;
        });

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000 + random.nextInt(10000));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "abc";
        });

        //可以接收无限多个future,个人觉得可以连着成功就执行下一步的方法来配合使用
        CompletableFuture<Void> future2 = CompletableFuture.allOf(future, future1);
        //将最早成功执行的其中一个CompletableFuture的计算结果返回
        CompletableFuture<Object> future3 = CompletableFuture.anyOf(future, future1);

        System.out.println(future2.get());

        System.out.println(future3.get());
    }
}
