package com.dom.scheduledexcutorpack;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExcutorTest implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

public static void main(String[] args){
    ScheduledExecutorService ex = Executors.newScheduledThreadPool(10);
    ScheduledExcutorTest scheduledExcutorTest = new ScheduledExcutorTest();
    //如果前面的任务没有完成，则雕塑就不会启动
    ex.scheduleWithFixedDelay(scheduledExcutorTest,0,2, TimeUnit.SECONDS);

}

}
