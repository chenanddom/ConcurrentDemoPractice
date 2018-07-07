package com.dom;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 *数组的操作
*/
public class AtomicIntegerArrayTest {

    static AtomicIntegerArray array = new AtomicIntegerArray(10);
    public static class AddThread implements  Runnable{

        public void run() {
            for (int i=0;i<100000;i++){
                array.getAndIncrement(i%array.length());//给每一个元素+1
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
    Thread[] ts = new Thread[10];
    for (int i=0;i<10;i++){
        ts[i]=new Thread(new AddThread());
    }
    for (int i=0;i<10;i++){
        ts[i].start();
    }
        for (int i=0;i<10;i++){
            ts[i].join();
        }
    System.out.println(array);
    }
}
