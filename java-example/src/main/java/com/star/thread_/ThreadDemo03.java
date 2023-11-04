package com.star.thread_;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author liudw
 * @date 2022/10/21 15:17
 */

// java Callable 接口实现多线程
public class ThreadDemo03 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        C3 c3 = new C3();  // 创建实现Callable接口的对象
        FutureTask<Integer> futureTask = new FutureTask(c3);   // 将实现类对象传入FutureTask中
        FutureTask<Integer> futureTask1 = new FutureTask(c3);
        new Thread(futureTask).start();   // 创建Thread对象,启动多线程
        new Thread(futureTask1).start();  // 启动另一个线程



        // 获取返回值
        Integer sum = futureTask.get();
        System.out.println("sum= " + sum);
    }

    static class C3 implements Callable<Integer> {
        int count = 0;
        int sum = 0;

        @Override
        public Integer call() throws InterruptedException {
            while (true) {
                System.out.println(Thread.currentThread().getName() + "--" + (++count));
                sum += count;
                Thread.sleep(500);
                if (count >= 10) {
                    break;
                }
            }
            return sum;
        }
    }
}