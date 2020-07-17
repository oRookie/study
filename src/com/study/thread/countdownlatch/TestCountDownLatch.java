package com.study.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TestCountDownLatch {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        new Thread(){
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1L);
                    System.out.println(Thread.currentThread().getName()+"执行完成...");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2L);
                    System.out.println(Thread.currentThread().getName()+"执行完成...");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(3L);
                    System.out.println(Thread.currentThread().getName()+"执行完成...");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        try {
            countDownLatch.await();
            System.out.println("3个进程全部执行完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
