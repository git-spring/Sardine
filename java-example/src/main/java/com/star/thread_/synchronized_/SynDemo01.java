package com.star.thread_.synchronized_;

/**
 * @author liudw
 * @date 2022/11/2 11:00
 */


// java 中的方法锁
public class SynDemo01 implements Runnable{
    public static void main(String[] args) {
        SynDemo01 synDemo01 = new SynDemo01();
        Thread thread1 = new Thread(synDemo01);
        thread1.start();
        Thread thread2 = new Thread(synDemo01);
        thread2.start();

    }

    static boolean loop = true;   // 循环变量,控制run方法里面的循环
    static int nums = 20;

    @Override
    public void run() {
        while (loop) {
            method();
            //staticMethod();
        }
    }
    // synchronized 修饰普通方法,锁住的是当前对象,锁对象默认是this
    public synchronized void method() {
        if (nums <= 0) {
            System.out.println("执行结束");
            loop = false;
            return;
        }
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() +"------  "+ (--nums));
    }


    // synchronized 修饰静态方法时, 锁住的是当前类(类.class)
    public synchronized static void staticMethod() {

        if (nums <= 0) {
            System.out.println("静态方法线程同步执行结束");
            loop = false;
            return;
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() +"------"+ (--nums));
    }

}