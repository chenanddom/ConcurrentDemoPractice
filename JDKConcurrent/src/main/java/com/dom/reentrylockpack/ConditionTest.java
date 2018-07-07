package com.dom.reentrylockpack;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest implements Runnable{
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    @Override
    public void run() {
        try {
            lock.lock();
            condition.await();
            System.out.println("Thread is going on....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }



    public static void main(String[] args) throws InterruptedException {
        ConditionTest t = new ConditionTest();
        Thread t1 = new Thread(t);
        t1.start();
        Thread.sleep(5000);
        //通知线程继续执行
        lock.lock();
        condition.signal();
        lock.unlock();

    }


}
