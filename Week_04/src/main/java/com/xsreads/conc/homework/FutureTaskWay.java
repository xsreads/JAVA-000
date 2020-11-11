package com.xsreads.conc.homework;

import java.util.concurrent.*;

/**
 * Created by xiushan on 2020/11/11.
 */
public class FutureTaskWay {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        // 第一种方式
//        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                return sum();
//            }
//        });
//        new Thread(task).start();

        // 第二种方式
        ExecutorService executor = Executors.newSingleThreadExecutor();
        FutureTask<Integer> task = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return sum();
            }
        });
        executor.submit(task);

        try {
            System.out.println("异步计算结果为: " + task.get());
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
