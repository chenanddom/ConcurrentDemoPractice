package com.thread;

/**
 * 线程同步测试
 */
public class synchronizeThreadTest implements Runnable {

        static synchronizeThreadTest lock = new synchronizeThreadTest();
        static int i = 0;

        public synchronized void increase() {
            i++;
        }

        public void run() {
            for (int n = 0; n < 10000; n++) {
//                synchronized (lock) {
                    i++;
//                }
            }
        }


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new synchronizeThreadTest());
        Thread t2 = new Thread(new synchronizeThreadTest());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("sum:" + i);

    }
}

