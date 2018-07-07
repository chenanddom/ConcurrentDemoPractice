package com.thread;

/**
 * 线程同步测试,对当前的实例枷锁
 */
public class synchronizeThreadTest_3 implements Runnable {
        static int i = 0;

        public static synchronized void increase() {
            i++;
        }

        public void run() {
            for (int n = 0; n < 10000; n++) {
                increase();
            }
        }


    public static void main(String[] args) throws InterruptedException {
/*        synchronizeThreadTest_3 t= new synchronizeThreadTest_3();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);*/
        Thread t1 = new Thread(new synchronizeThreadTest_3());
        Thread t2 = new Thread(new synchronizeThreadTest_3());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("sum:" + i);

    }
}

