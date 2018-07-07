package com.thread;

/**
 * 守护线程
 */
public class DaemonThreadTest {
    static class DaemonThread extends Thread {
        private static int i = 0;

        @Override
        public void run() {
            while (true) {

                System.out.println(i);
                try {
                    Thread.sleep(1000);
                    i++;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        DaemonThread t = new DaemonThread();
        t.setDaemon(true);//设置为守护线程，这个这只必须要在线程启动之前，否则设置守护线程就没有意义了
        t.start();
//        Thread.MAX_PRIORITY;


    }
}
