package com.dom.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

/**
 * 对多个线程做计数，只有这多个线程都完成了这些任务之后才可以进行下面的任务
 */
public class CountDownLatchTest implements Runnable {
    static final CountDownLatch end = new CountDownLatch(10);//表示有是个要技术的线程
    static final CountDownLatchTest countDownLatchTest = new CountDownLatchTest();
    @Override
    public void run() {
        try {
            //模拟检查任务
            Thread.sleep(new Random().nextInt(10) * 1000);
            System.out.println("check complete");
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            service.execute(countDownLatchTest);
        }
        //等待检查
        end.await();
        //发射点火
        System.out.println("发射。。。。。");
        service.shutdown();
//        LockSupport

    }
}
