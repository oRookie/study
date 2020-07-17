package com.study.thread.cas;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * CAS
 * Compare AND Swap 比较且交换
 */
public class TestCas {
    static Unsafe unsafe;
    public int value  = 0; //cas 自增
    public int value2 = 0; //普通自增
    public static long valueOffset;//value在内存中的偏移量
    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe)field.get(null);
            valueOffset = unsafe.objectFieldOffset(TestCas.class.getDeclaredField("value"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void incre(){
        int temp;
        do{
            temp = unsafe.getInt(this,valueOffset);
        }while(!unsafe.compareAndSwapInt(this,valueOffset,temp,temp+1));
    }
    public void incre2(){
        value2++;
    }

    public static void main(String[] args) throws InterruptedException {
        TestCas testCas = new TestCas();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                testCas.incre();
                testCas.incre2();
            }

        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                testCas.incre();
                testCas.incre2();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(testCas.value);
        System.out.println(testCas.value2);
    }
}
