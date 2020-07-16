package com.study.thread.executor;


import org.junit.Test;

import java.util.concurrent.*;

public class ExecutorTest {
    ThreadLocal<String> threadLocal = new ThreadLocal<>();
    @Test
    public void testnewSingleThreadExecutor() {
        threadLocal.set("aaa");
//        ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        testCommon(threadPoolExecutor);
    }
    @Test
    public void testnewFixedThreadPool() {
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        testCommon(threadPoolExecutor);
    }

    @Test
    public void testnewFixedThreadPool1(){
//        ExecutorService executorService = Executors.newFixedThreadPool(5, new ThreadFactory() {
//            @Override
//            public Thread newThread(Runnable r) {
//                return new Thread(r);
//            }
//        });
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r);
                    }
                });
        testCommon(threadPoolExecutor);
    }

    /**
     * 缓冲线程池，没有核心线程，现用现创建
     */
    @Test
    public void testnewCachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3));
        testCommon(threadPoolExecutor);
    }


    /**
     * 带有
     */
    @Test
    public void testnewScheduledThreadPool() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, Integer.MAX_VALUE,
//                0L, TimeUnit.SECONDS,
//               new LinkedBlockingQueue<>());
//        testCommon(threadPoolExecutor);
        testSchedule(scheduledExecutorService);
    }

    public void testSchedule(ScheduledExecutorService scheduledExecutorService){
//        for (int i=0; i<15; i++){
        System.out.println(System.currentTimeMillis());
//            scheduledExecutorService.schedule(()->{
//                System.out.println(System.currentTimeMillis());
//                System.out.println("开始执行");
//                try {
//                    Thread.sleep(3000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("执行结束");
//            },5L,TimeUnit.SECONDS);
//            scheduledExecutorService.scheduleAtFixedRate(()->{
//                System.out.println(System.currentTimeMillis());
//                System.out.println("开始执行");
//                try {
//                    Thread.sleep(3000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("执行结束");
//            },1L,4L,TimeUnit.SECONDS);
//        scheduledExecutorService.scheduleWithFixedDelay(()->{
////            System.out.println(System.currentTimeMillis());
////            System.out.println("开始执行");
////            try {
////                Thread.sleep(3000L);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
////            System.out.println("执行结束");
////        },1L,4L,TimeUnit.SECONDS);
//        }
        try {
            Thread.sleep(20000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public void testCommon(ThreadPoolExecutor executor){
        for (int i=0;i<15;i++){
            executor.submit(()->{
                System.out.println(System.currentTimeMillis());
                System.out.println("开始执行");
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("执行结束");
            });
        }
        System.err.println("ThreadPool 活跃线程数："+executor.getActiveCount());
        System.err.println("ThreadPool 核心线程数："+executor.getCorePoolSize());
        System.err.println("ThreadPool 线程数："+ executor.getPoolSize());
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("ThreadPool 活跃线程数："+executor.getActiveCount());
        System.err.println("ThreadPool 核心线程数："+executor.getCorePoolSize());
        System.err.println("ThreadPool 线程数："+ executor.getPoolSize());

    }
}
