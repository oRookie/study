package com.study.thread.day1;

/**
 * 优雅的停止线程
 */
public class Demo2 {
    private static  boolean flag = true;
    public static void main(String[] args) throws InterruptedException {
        SimpleThread thread = new SimpleThread();
        thread.start();
        Thread.sleep(1000);
//        thread.stop();
        thread.interrupt();
        thread.print();

         new Thread(){
            @Override
            public void run() {
                while (flag){
                    System.out.println("运行中");
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

         Thread.sleep(3000l);
         flag = false;
    }
}
