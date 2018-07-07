package com.dom;

import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;

public class CASTest {
    static AtomicInteger atomicInteger = new AtomicInteger();
    static class WorkThread implements Runnable{

        public void run() {
        for (int i=0;i<10000;i++){
            atomicInteger.incrementAndGet();
        }
        }
    }


public static void main(String[] args) throws InterruptedException {
    Thread[] threads=new Thread[10];
        for (int i=0;i<10;i++){
           threads[i] = new Thread(new WorkThread());
        }
    for (int j=0;j<10;j++){
        threads[j].start();
    }
    for (int j=0;j<10;j++){
        threads[j].join();
    }
    System.out.print(atomicInteger);
}
}
