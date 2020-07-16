package com.study.thread.notify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

public class LockStock {
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();
    private  int count = 0;

    private void produce(){
        lock.lock();
        //满了
        while (count >= 10){
            try {
                notFull.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ++count;
        System.out.println("生产数据--- 库存剩余:"+count);
        notEmpty.signal();
        lock.unlock();
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void consume(){
        lock.lock();
        while(count<=0){
            try {
                notEmpty.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        --count;
        System.out.println("消费数据--- 库存剩余:"+count);
        notFull.signal();
        lock.unlock();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LockStock lockStock = new LockStock();
        Stream.of("p1","p2","p3").forEach(i->{new Thread(i){
            @Override
            public void run() {
                while (true){
                    lockStock.produce();
                }
            }
        }.start();});
        Stream.of("c1").forEach(i->{new Thread(i){
            @Override
            public void run() {
                while (true){
                    lockStock.consume();
                }
            }
        }.start();});
    }
}
