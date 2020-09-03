package com.study.thread.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.Stream;

/**
 * @author lik
 */
public class TestCyclicBarrier {

    /**
     * CyclicBarrier
     * 循环屏障，直到所有的线程都到达这个屏障，才会开始执行
     * 主要用于处理程序有一个固定大小的线程，各个线程必须偶尔等待对方一起到达以后才可以做某些事情，比如斗地主
     * @param args
     */
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        Stream.of(1,2).forEach(i->{new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName()+"进入,等待人数："+cyclicBarrier.getNumberWaiting());
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName()+ "开始执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();});
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName()+"进入,等待人数："+cyclicBarrier.getNumberWaiting());
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName()+ "开始执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
