package com.study.thread.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class TestSemaphore {
    private final Semaphore semaphore = new Semaphore(3);

    public void goWc(){
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName()+"上厕所中....");
            TimeUnit.SECONDS.sleep(3L);
            System.out.println(Thread.currentThread().getName()+"完事....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }

    }


    public static void main(String[] args) {
        TestSemaphore testSemaphore = new TestSemaphore();
        IntStream.range(0,10).forEach(i->{new Thread(){
            @Override
            public void run() {
                testSemaphore.goWc();
            }
        }.start();});
    }
}
