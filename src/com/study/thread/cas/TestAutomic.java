package com.study.thread.cas;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class TestAutomic {
    public static Object MONITOR = new Object();
    public long count = 0;
    public AtomicLong atomicLong = new AtomicLong(0);
    public LongAdder longAdder = new LongAdder();
    public void testSync(){
        long start = System.currentTimeMillis();
        IntStream.range(0,3).forEach(i->new Thread("sync"+i){
            @Override
            public void run() {
                while (System.currentTimeMillis()-start<2000){
                        synchronized (MONITOR){
                            count++;
                        }
                }
                System.out.println("Synchronzied自增花费"+(System.currentTimeMillis()-start)+",自增结果："+count);
            }
        }.start());
    }
    public void testAtomicLong(){
        long start = System.currentTimeMillis();
        IntStream.range(0,3).forEach(i->new Thread("sync"+i){
            @Override
            public void run() {
                while (System.currentTimeMillis()-start<2000){
                    atomicLong.incrementAndGet();
                }
                System.out.println("AtomicLong自增花费"+(System.currentTimeMillis()-start)+",自增结果："+atomicLong.get());
            }
        }.start());
    }
    public void testLongAdder(){
        long start = System.currentTimeMillis();
        IntStream.range(0,3).forEach(i->new Thread("sync"+i){
            @Override
            public void run() {
                while (System.currentTimeMillis()-start<2000){
                    longAdder.increment();
                }
                System.out.println("longAddr自增花费"+(System.currentTimeMillis()-start)+",自增结果："+longAdder.sum());
            }
        }.start());
    }

    public static void main(String[] args) {
        new TestAutomic().testSync();
        new TestAutomic().testAtomicLong();
        new TestAutomic().testLongAdder();
    }
}
