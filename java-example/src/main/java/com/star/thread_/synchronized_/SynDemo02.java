package com.star.thread_.synchronized_;

/**
 * @author liudw
 * @date 2022/11/2 14:42
 */

// java 对象锁 类锁
// 可以指定多把锁(但必须是同一个对象), 谁拿到锁就执行哪一段代码
// 如果在synchronized 里面指定 类.class ,则为类锁
public class SynDemo02 implements Runnable {
    static SynDemo02 synDemo02 = new SynDemo02();
    // 创建两把锁
    Object block1 = new Object();
    Object block2 = new Object();


    public static void main(String[] args) {
        Thread thread1 = new Thread(synDemo02);
        thread1.start();
        Thread thread2 = new Thread(synDemo02);
        thread2.start();
    }


    static boolean loop = true;
    static int nums = 20;

    @Override
    public void run() {
        while (loop) {
            method();
        }
    }


    public void method() {

//        synchronized (block1) {  // 同步代码块
//            if (nums <= 0) {
//                System.out.println("代码块1 锁执行结束");
//                loop = false;
//                return;
//            }
//            try {
//                Thread.sleep(1240);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("   同步代码1 -- " + Thread.currentThread().getName() + "------  " + (--nums));
//        }

        synchronized (/*block1*/ new Object() ) {  //传入block1 是同一个对象,线程安全, 如果这里传入 new Object() 就不是同一个对象锁, 不是线程安全的
            if (nums <= 0) {
                System.out.println("代码块2 锁执行结束");
                loop = false;
                return;
            }
            try {
                Thread.sleep(1240);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("   同步代码2 -- " + Thread.currentThread().getName() + "------  " + (--nums));
        }

    }
}