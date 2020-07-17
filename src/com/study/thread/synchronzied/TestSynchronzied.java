package com.study.thread.synchronzied;

import org.junit.Test;

public class TestSynchronzied {
    private static int i = 0;

    /**
     * 修饰实例方法，作用于当前实例
     */
    public synchronized void increment(){
       i++;
    }

    /**
     * 修饰静态方法，作用于当前class对象
     */
    public static synchronized  void increentstatic(){
        i++;
    }

    /**
     * 修改代码块
     */
    public void incrementt(){
        synchronized (this){
            i++;
        }
    }

    @Test
    public void test1() throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    incrementt();
                }
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    incrementt();
                }
            }
        };
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }

}
