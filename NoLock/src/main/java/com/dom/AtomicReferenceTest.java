package com.dom;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {
    static AtomicReference<String> reference = new AtomicReference<String>("zhangsan");

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                public void run() {

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (reference.compareAndSet("zhangsan", "lisi")) {
                        System.out.println("Thread:" + Thread.currentThread().getName() + "update the variable is success");
                    } else {
                        System.out.println("Thread:" + Thread.currentThread().getName() + "update the variable is failure");
                    }
                }
            }).start();
        }
    }
}
