package com.dom.semaphorepack;

import jdk.internal.org.objectweb.asm.tree.FieldInsnNode;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReadWriteLock;

public class SemaphoreTest implements Runnable{
final Semaphore semaphore = new Semaphore(5);//分配五个许可

    @Override
    public void run() {
        try {
            semaphore.acquire();//获取一个许可
//            semaphore.acquire(2);//允许没给线程获取2个许可
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId()+":DONE");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();//释放信号量
        }
    }

public static void main(String[] args){
    ExecutorService executorService = Executors.newFixedThreadPool(20);//创建20个线程
    final SemaphoreTest demoThread = new SemaphoreTest();
    for (int i=0;i<20;i++){
        executorService.execute(demoThread);
    }



}

}
