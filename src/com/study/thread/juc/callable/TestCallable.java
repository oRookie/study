package com.study.thread.juc.callable;

import org.junit.Test;

import java.util.concurrent.*;

public class TestCallable {
    @Test
    public void testCallable1(){
        ExecutorService executorService = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000L);
                return "test call";
            }
        };
        Future<String> submit = executorService.submit(callable);
        try {
            submit.get(2,TimeUnit.SECONDS);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }
}
