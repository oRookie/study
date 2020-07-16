package com.study.thread.notify;

import java.util.stream.Stream;

public class ResumeSuspend {
    private static int flag = 0;
    private volatile boolean isProduced = false;
    private final Object MONITOR = new Object();

    public void produce(){
//      synchronized (MONITOR){
          while(isProduced){
              Thread.currentThread().suspend();
          }
          flag++;
        isProduced = true;
        System.out.println("p ---> "+flag);
//          Thread.currentThread().resume();
//      }
    }

    public void consume(){
//        synchronized (MONITOR){
            while(!isProduced){
                Thread.currentThread().suspend();
            }
            isProduced = false;
//            Thread.currentThread().resume();
            System.out.println("c ---> "+flag);
//        }
    }

    public static void main(String[] args) throws InterruptedException {
        ResumeSuspend resumeSuspend = new ResumeSuspend();
        Stream.of("p1","p2","p3").forEach(i->{new Thread(i){
            @Override
            public void run() {
                resumeSuspend.produce();
            }
        }.start();});
        Stream.of("c1","c2","c3","c4").forEach(i->{new Thread(i){
            @Override
            public void run() {
                resumeSuspend.consume();
            }
        }.start();});

    }
}
