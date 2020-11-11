package com.xsreads.conc.homework;

import java.util.concurrent.CountDownLatch;

/**
 * Created by xiushan on 2020/11/11.
 */
public class CountDownLatchWay {
    private static int result = 0;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread thread = new Thread(() -> {
            result = sum();
            countDownLatch.countDown();
        });
        thread.start();

        countDownLatch.await();

        // 得到 result 并输出
        System.out.println("异步计算结果为: " + result);
        System.out.println("使用时间: " + (System.currentTimeMillis() - start) + " ms");
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
