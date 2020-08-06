package com.study.thread.juc.fockjoin;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author lk
 * <p>
 * Fock/Join的原理就是将一个任务拆分成多个子任务，然后分别执行，再合并
 * 双端队列，任务线程从队头取任务执行，fock从队尾取任务执行
 */
public class TestForkJoin {
    /**
     * ForkJoinPool
     * ForkJoin框架中的调度器，内部实现了自己的线程池
     */
    @Test
    public void testForkJoinPool() {
        //构造器1
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        /**
         * parallelism 并行度级别
         * factory 创建线程的工厂类
         * handler 报错后的处理程序
         * asyncMode 如果为true,为未执行的任务执行一个先进先出的模式
         * ForkJoinPool(int parallelism,
         *                          ForkJoinWorkerThreadFactory factory,
         *                          UncaughtExceptionHandler handler,
         *                          int mode,
         *                          String workerNamePrefix)
         */
        ForkJoinPool forkJoinPool1 = new ForkJoinPool(Math.min(0x7fff, Runtime.getRuntime().availableProcessors()),
                ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, false);

    }

    /**
     * 异步执行给定的任务
     * public void execute(ForkJoinTask<?> task)
     * public void execute(Runnable task)
     */
    @Test
    public void test() throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        long now = System.currentTimeMillis();
        for (long i = 0; i < 100000000L; i++) {
        }
        System.out.println(System.currentTimeMillis() - now);

        ForkJoinTask forkJoinTask1 = new SumTask(0, 100000000L);
        now = System.currentTimeMillis();
        Long invoke = (Long) forkJoinPool.invoke(forkJoinTask1);
        System.out.println(invoke);
        System.out.println(System.currentTimeMillis() - now);
    }

    class SumTask extends RecursiveTask {
        private long max;
        private long i;
        private final int THRESHOLD = 10000000;

        public SumTask(long i, long max) {
            this.i = i;
            this.max = max;
        }

        @Override
        protected Long compute() {
            if (max - i <= THRESHOLD) {
                long sum = 0;
                for (long j = i; j < max; j++) {
                    sum++;
                }
                return sum;
            }
            long middle = max / 2;
            SumTask task1 = new SumTask(i, middle);
            SumTask task2 = new SumTask(middle, max);
            task1.fork();
            task2.fork();
            Long result1 = (Long) task1.join();
            Long result2 = (Long) task2.join();
            System.out.println(result1 + " : " + result2);
            System.out.println("sum:" + (result1 + result2));
            return result1 + result2;
        }
    }

}
