package com.thread;

public class WaitNotifyThreadTest {
    static WaitNotifyThreadTest object = new WaitNotifyThreadTest();

    static class Thread1 extends Thread {

        @Override
        public void run() {

            synchronized (object) {
                System.out.println("Thread1 is start.....");
                try {
                    object.wait();//直接在这里释放了这把锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread1 is end........");
            }

        }

    }

    static class Thread2 extends Thread {

        @Override
        public void run() {

            synchronized (object) {
                System.out.println("Thread2 is start.....");
                try {
                    object.notify();//直接唤醒在当前对象上的线程，但是这里的唤醒并不意味着Thread1线程就可以马上获取得到这个锁，
                    // 这里只是唤醒了，但是Thread1要往下执行还是需要获取当前的锁，二该锁还在Thread2上，需要执行完了Thread2后面的才可以把该锁释放。
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread2 is end........");
            }
        }
    }


    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        thread2.start();


    }
}
