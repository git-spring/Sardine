package com.star.thread_;

/**
 * @author liudw
 * @date 2022/10/24 15:40
 */

// java 多线程常用方法1
public class ThreadDemo05 {
    public static void main(String[] args) throws InterruptedException {
        Computer computer = new Computer();
        Thread thread = new Thread(computer);
        thread.setName("DELL");          // 设置线程名称
        thread.start();

        System.out.println("当前线程名称 : "+thread.getName());   // 获取线程名称

        for (int i = 0; i < 5; i++) {
            System.out.println("主线程休眠中");
            Thread.sleep(1000);
        }

        System.out.println(thread.getPriority());   // 获取线程优先级
        thread.setPriority(1);  // 设置线程优先级

        thread.interrupt();    // 中断线程
    }


    static class Computer implements Runnable {

        @Override
        public void run() {
            while (true){
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName()+"  运行中"+i);
                }

                try {
                    System.out.println(Thread.currentThread().getName()+"  休眠中...");
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName()+"  被中断了...ops!");
                }
            }

        }
    }
}