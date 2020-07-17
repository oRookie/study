package com.study.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

public class TestLock {
    private Lock lock = new ReentrantLock();
    /**
     *  void lock();
     * 获取锁，有以下三种情况：
     *
     * 锁空闲：直接获取锁并返回，同时设置锁持有者数量为：1；
     * 当前线程持有锁：直接获取锁并返回，同时锁持有者数量递增1；
     * 其他线程持有锁：当前线程会休眠等待，直至获取锁为止；
     */
    public  void testLock(){
        lock.lock();
        try {
            long start = System.currentTimeMillis();
            System.out.println("lock处理数据");
            Thread.sleep(2000L);
            System.out.println("lock处理完成");
            System.out.println("处理时间："+(System.currentTimeMillis() - start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


    /**
     * boolean tryLock();
     *
     * 锁空闲：直接获取锁并返回：true，同时设置锁持有者数量为：1；
     * 当前线程持有锁：直接获取锁并返回：true，同时锁持有者数量递增1；
     * 其他线程持有锁：获取锁失败，返回：false；
     */
    public void testTryLock(){
        boolean b = lock.tryLock();
        System.out.println("尝试获取锁："+b);
        try {
            if (b){
                System.out.println("tryLock处理数据");
                Thread.sleep(2000L);
                System.out.println("tryLock处理完成");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    /**
     *void lockInterruptibly() throws InterruptedException;
     *
     */
    public void testLockInterruptibly(){
        try {
            lock.lockInterruptibly();
            long start = System.currentTimeMillis();
            System.out.println("testLockInterruptibly处理数据");
            Thread.sleep(2000L);
            System.out.println("testLockInterruptibly处理完成");
            System.out.println("处理时间："+(System.currentTimeMillis() - start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }



    public static void main(String[] args) throws InterruptedException {
        TestLock testLock = new TestLock();
        Stream.of("t1","t2","t3").forEach(i->new Thread(i){
            @Override
            public void run() {
                testLock.testLock();
            }
        }.start());
//        new Thread(()->{  testLock.testTryLock(); }).start();
        Thread t1 = new Thread(()->{
            testLock.testLock();
        });
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        t1.interrupt();
    }
}
