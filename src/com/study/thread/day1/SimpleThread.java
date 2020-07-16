package com.study.thread.day1;

public class SimpleThread extends Thread {
    private int i = 0,j=0;
    @Override
    public void run() {
        ++i;
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ++j;
    }
    public void print(){
        System.out.println("i = "+i);
        System.out.println("j = "+j);
        Thread thread = new Thread(){};
    }
}
