package com.xsreads.conc.homework;

import java.util.concurrent.*;

/**
 * Created by xiushan on 2020/11/11.
 */
public class FutureWay {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> result = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return sum();
            }
        });
        executorService.shutdown();

        try {
            System.out.println("异步计算结果为: " + result.get());
            System.out.println("使用时间: " + (System.currentTimeMillis() - start) + " ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("主线程执行结束。。。");
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }
}
