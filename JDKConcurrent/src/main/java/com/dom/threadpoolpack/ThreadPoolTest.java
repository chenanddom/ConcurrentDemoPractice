package com.dom.threadpoolpack;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest implements Runnable {

    @Override
    public void run() {
        System.out.println(System.currentTimeMillis() + ":Thread ID:" + Thread.currentThread().getId());
        try {
            Thread.sleep(1000);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        ThreadPoolTest threadPoolTest = new ThreadPoolTest();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            executorService.submit(threadPoolTest);
        }


    }


}
