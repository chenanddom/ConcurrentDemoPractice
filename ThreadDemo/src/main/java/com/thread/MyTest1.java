package com.thread;

public class MyTest1 {

public static void main(String[] args){

    MyThread t1 = new MyThread();
    MyThread t2 = new MyThread();
    t1.start();

    t2.start();
    t1.interrupt();//设置中断标志位
    t2.interrupt();


}
static class  MyThread extends Thread{
    private static int i=0;
    @Override
    public void run() {
        while (true){
            boolean flag = Thread.currentThread().isInterrupted();
            if (flag){
                break;
            }
            if (i==100){
                Thread.currentThread().interrupt();//设置中断标志位
            }
            i++;
            System.out.println("Thread:"+Thread.currentThread().getName());
            Thread.yield();//放权，和其他的线程再一起竞争执行的权利
        }
    }
}

static class MyThread2 extends Thread{
    @Override
    public void run() {
        while (true){
            boolean flag = Thread.currentThread().isInterrupted();
            if (flag){
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("睡眠时被中断了");
                //如果需要真正的中断，这里还需要设置一个中断的标志位，因为在进入中断异常的catch里面就已经把先前设置的中断标志位清除了。
                Thread.currentThread().interrupt();//这里仍然需要设置中断标志位。
            }
            System.out.println("Thread:"+Thread.currentThread().getName());
            Thread.yield();//放权，和其他的线程再一起竞争执行的权利
        }
    }
}


}
