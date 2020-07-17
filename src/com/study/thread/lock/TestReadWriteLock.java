package com.study.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Stream;

public class TestReadWriteLock {
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
    private Lock readLock = readWriteLock.readLock();
    private Lock writeLock = readWriteLock.writeLock();
    private int count = 0;

    public void read() {
        try{
            readLock.lock();
            System.out.println(Thread.currentThread().getName()+"读取数据："+count);
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){

        }finally {
            readLock.unlock();
        }
    }

    public void write() {
        try{
            writeLock.lock();
            count++;
            System.out.println(Thread.currentThread().getName()+"写入数据："+count);
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){

        }finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        TestReadWriteLock testReadWriteLock = new TestReadWriteLock();
        Stream.of("w1","w2").forEach(i->{new Thread(i){
            @Override
            public void run() {
                while (true){
                    testReadWriteLock.write();
                }
            }
        }.start();});
        Stream.of("r1","r2","r3","r4").forEach(i->{new Thread(i){
            @Override
            public void run() {
                while (true){
                    testReadWriteLock.read();
                }
            }
        }.start();});
    }
}
