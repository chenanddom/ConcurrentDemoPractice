package com.dom;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 使用一个充值和消费的操作来体校这个操作
 */
public class AtomicStampReferenceTest {
    static AtomicStampedReference<Integer> mony = new AtomicStampedReference<Integer>(19, 0);

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            final int timeStamp = mony.getStamp();
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        while (true) {

                            Integer m = mony.getReference();
                            if (m < 20) {
                                if (mony.compareAndSet(m, m + 20, timeStamp, timeStamp + 1)) {
                                    System.out.println("余额效于20元，充值成功，余额:" + mony.getReference() + "元");
                                    break;
                                }

                            } else {

                                break;
                            }
                        }
                    }
                }
            }.start();

        }

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    while (true) {
                        int timeStamp = mony.getStamp();
                        Integer m = mony.getReference();
                        if (m > 10) {
                            System.out.println("大于10元");
                            if (mony.compareAndSet(m, m - 10, timeStamp, timeStamp + 1)) {
                                System.out.println("成功消费10元，余额：" + mony.getReference());
                                break;
                            }
                        } else {
                            System.out.println("没有足够的金额");
                            break;
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();


    }
}
