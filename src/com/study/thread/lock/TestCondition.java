package com.study.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

/**
 * 使用Condition中的await 和 signalAll 实现生产者消费者模式
 * 使用注意：
 * 1。await 和 signal 必须在获得lock的前提下，也就是在lock 和 unlock之间
 */
public class TestCondition {
    private Lock lock = new ReentrantLock();
    private Condition produce = lock.newCondition();
    private Condition consume = lock.newCondition();
    private final int MAX_COUNT = 10;

    private volatile int count = 0;

    public void produce() {
        while(true){
            lock.lock();
            try {
                while (count >= MAX_COUNT) {
                    produce.await();
                }
                count++;
                System.out.println(Thread.currentThread().getName() + "生产数据 --- 库存：" + count);
                consume.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void consume() {
        while(true){
            lock.lock();
            try {
                while (count <= 0) {
                    consume.await();
                }
                count--;
                System.out.println(Thread.currentThread().getName() + "消费数据 --- 库存：" + count);
                produce.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TestCondition testCondition = new TestCondition();
        Stream.of("p1", "p2", "p3").forEach(p -> {
            new Thread(p) {
                @Override
                public void run() {
                    testCondition.produce();
                }
            }.start();
        });
        Stream.of("c1", "c2").forEach(p -> {
            new Thread(p) {
                @Override
                public void run() {
                    testCondition.consume();
                }
            }.start();
        });
    }
}
