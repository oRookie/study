package com.study.thread.notify;

import java.util.concurrent.locks.LockSupport;

public class ParkUnpark {
    private static int flag = 0;
    private static volatile boolean isProduced = false;
    private static Object MONITOR = new Object();
    public static void main(String[] args) {
        //消费
        Thread t1 = new Thread("t1"){
            @Override
            public void run() {
//               synchronized (MONITOR){
                   while(!isProduced){
                       LockSupport.park();
                   }
                System.out.println("c --->"+flag);
                   isProduced=false;
//               }
            }
        };
        t1.start();
        //生产
        new Thread("t2"){
            @Override
            public void run() {
                while(isProduced){
                    LockSupport.park();
                }
                flag++;
                System.out.println("p --->"+flag);
                isProduced = true;
                LockSupport.unpark(t1);
            }
        }.start();
    }

}
