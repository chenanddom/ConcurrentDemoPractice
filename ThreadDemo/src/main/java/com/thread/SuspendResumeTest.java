package com.thread;

public class SuspendResumeTest {

public static void main(String[] args) throws InterruptedException {
    MyThread myThread = new MyThread();
    MyThread myThread2 = new MyThread();
    myThread.start();
    Thread.sleep(1000);//这里设置睡眠是为了该线程正常的进入执行的方法。
    myThread2.start();
    myThread.resume();
    myThread2.resume();
    myThread.join();//线程加入了主线程，主线程要等待该线程的执行完成，会阻塞在这里
    myThread2.join();//
}
}
class MyThread extends Thread{
    static Object obj = new Object();
    @Override
    public void run() {
        synchronized (obj){
            System.out.println("thread:"+getName());
            Thread.currentThread().suspend();//线程怪气
        }
    }
}