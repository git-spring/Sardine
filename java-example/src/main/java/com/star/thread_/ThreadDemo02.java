package com.star.thread_;

/**
 * @author liudw
 * @date 2022/10/21 14:45
 */

// java Runnable 实现多线程
public class ThreadDemo02 {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Thread thread = new Thread(dog);
        thread.start();    // 开启一个线程
        Thread thread1 = new Thread(dog);   // 传入的同一个对象,两个对象执行同一个run方法,适合资源共享
        thread1.start();
    }

    static class Dog implements Runnable {  // 可以避免java单继承带来的限制
        int count = 0;

        @Override
        public void run() {
            while (true) {

                try {
                    System.out.println(Thread.currentThread().getName() + "--" + (++count));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (count >= 10) {
                    break;
                }
            }
        }
    }
}