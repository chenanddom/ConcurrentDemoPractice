package com.dom.reentrylockpack;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class ReentryLockTest implements Runnable {


    public static ReentrantLock lock = new ReentrantLock();

    public static int n = 0;

    public void run() {
        for (int i = 0; i < 100000; i++) {
            lock.lock();
//            lock.lock();
            try {
                n++;
            }finally {
                lock.unlock();
//                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentryLockTest tl = new ReentryLockTest();
        Thread t1 = new Thread(tl);
        Thread t2 = new Thread(tl);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(n);

//        Semaphore
    }
}
