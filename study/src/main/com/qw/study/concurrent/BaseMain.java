package main.com.qw.study.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @program: study
 * @description: 异步测试
 * @author: HyJan
 * @create: 2020-04-13 11:13
 **/
public class BaseMain {

    public static CompletableFuture<Integer> compute(){
        CompletableFuture<Integer> future = new CompletableFuture<>();
        return future;
    }

    public static void main(String[] args) throws Exception {
        final CompletableFuture<Integer> future = compute();

        class Client extends Thread{
            CompletableFuture<Integer> future;

            Client(String threadName,CompletableFuture<Integer> future){
                super(threadName);

                this.future = future;
            }

            @Override
            public void run() {
                try{
                    System.out.println(this.getName() + ":" + future.get());
                }catch (InterruptedException e){
                    e.printStackTrace();
                }catch (ExecutionException e){
                    e.printStackTrace();
                }
            }
        }

        new Client("client1", future).start();
        new Client("client2", future).start();

        System.out.println("waiting...");

        future.complete(1000);

        System.in.read();
    }

}
