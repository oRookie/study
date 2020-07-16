package com.study.thread.notify;

import java.util.stream.Stream;

public class NotifyWait {
    private  int i = 0;
    private final Object MONITOR = new Object();
    private volatile boolean isProduced = false;

    public void produce(){
        synchronized (MONITOR){
            while(isProduced){
                try {
                    MONITOR.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("p --->" + (++i));
            isProduced = true;
            MONITOR.notifyAll();
        }
    }

    public void consume(){
        synchronized (MONITOR){
            while (!isProduced){
                try {
                    MONITOR.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("c --->" + i);
            isProduced = false;
            MONITOR.notifyAll();
        }
    }

    public static void main(String[] args) {
        NotifyWait produceConsumerVersion2 = new NotifyWait();

        Stream.of("p1","p2","p3").forEach((n)->{
            new Thread(n){
                @Override
                public void run() {
                    while (true){
                        try {
                            Thread.sleep(10l);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        produceConsumerVersion2.produce();
                    }
                }
            }.start();
        });
        Stream.of("c1","c2","c3","c4").forEach((n)->{
            new Thread(n){
                @Override
                public void run() {
                    while (true){
                        try {
                            Thread.sleep(10l);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        produceConsumerVersion2.consume();
                    }
                }
            }.start();
        });

    }
}
