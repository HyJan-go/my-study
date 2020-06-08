package main.com.qw.study.concurrent;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * @program: study
 * @description: TODO
 * @author: HyJan
 * @create: 2020-04-13 13:30
 **/
public class Main {

    public static Random random = new Random();

    public static Long t = System.currentTimeMillis();

    public static int getMoreDate(){
        System.out.println("begin to compute");
        try {
            Thread.sleep(10000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("end of the compute,pass" + (System.currentTimeMillis() - t)/1000  + "seconds");
        return random.nextInt(10000);
    }

    public static void main(String[] args) throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(Main::getMoreDate);
        CompletableFuture<Integer> f = future.whenComplete((v, t) -> {
            System.out.println(v); //数据
            System.out.println(t);
        });
        System.out.println(f.get());
        System.in.read();
    }
}
