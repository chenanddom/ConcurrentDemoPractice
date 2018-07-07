package com.dom.reentrylockpack;


import com.dom.deadlock.DeadlockCheck;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁的可中断，阻止了程序遇到死锁无法处理
 */
public class ReentryLockInterruptiblyTest implements Runnable {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    /**
     * 控制加锁顺序，方便构造死锁
     *
     * @param lock
     */
    public ReentryLockInterruptiblyTest(int lock) {
        this.lock = lock;
    }

    public void run() {

        try {
            if (lock == 1) {
                lock1.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                lock2.lockInterruptibly();
            } else {
                lock2.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                lock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getId() + ":线程退出");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentryLockInterruptiblyTest rL1 = new ReentryLockInterruptiblyTest(1);
        ReentryLockInterruptiblyTest rL2 = new ReentryLockInterruptiblyTest(2);
        Thread t1 = new Thread(rL1);
        Thread t2 = new Thread(rL2);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        //中断其中一个线程
        DeadlockCheck.check();
    }
}
