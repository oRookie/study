package com.study.thread.notify;

import java.util.concurrent.Semaphore;
import java.util.stream.Stream;

public class SemaphoreStock {
    // 互斥量，控制共享数据的互斥访问
    private Semaphore mutex = new Semaphore(1);

    //可以生产的数量
    private Semaphore canProduce = new Semaphore(10);

    //可以消费的数量
    private Semaphore canConsume = new Semaphore(1);

    private volatile int count = 0;

    private void produce(){
        try {
            canProduce.acquire();
            mutex.acquire();
            ++count;
            System.out.println(Thread.currentThread().getName()+"正在生产数据 ---库存剩余："+count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            mutex.release();
            canConsume.release();
        }
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void consume(){
        try {
            canConsume.acquire();
            mutex.acquire();
            --count;
            System.out.println(Thread.currentThread().getName()+"正在消费数据 ---库存剩余："+count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            mutex.release();
            canProduce.release();
        }
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SemaphoreStock lockStock = new SemaphoreStock();
        Stream.of("p1","p2","p3").forEach(i->{new Thread(i){
            @Override
            public void run() {
                while (true){
                    lockStock.produce();
                }
            }
        }.start();});
        Stream.of("c1","c2").forEach(i->{new Thread(i){
            @Override
            public void run() {
                while (true){
                    lockStock.consume();
                }
            }
        }.start();});
    }
}
