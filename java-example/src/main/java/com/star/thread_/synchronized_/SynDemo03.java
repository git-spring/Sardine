package com.star.thread_.synchronized_;

/**
 * @author liudw
 * @date 2022/11/3 11:29
 */

// java 死锁
public  class SynDemo03 implements Runnable{
    static SynDemo03 synDemo03_1 = new SynDemo03(false);
    static SynDemo03 synDemo03_2 = new SynDemo03(true);
    // 创建两把锁
    static Object o1 = new Object();
    static Object o2 = new Object();

    boolean flag ;
    public static void main(String[] args) {
        Thread thread1 = new Thread(synDemo03_1);
        thread1.start();
        Thread thread2 = new Thread(synDemo03_2);
        thread2.start();
    }

    public SynDemo03() {}

    public SynDemo03(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        method();
    }

    private void method() {
        if (flag) {
            synchronized (o1) {
                System.out.println(Thread.currentThread().getName() + "  持有了锁o1,接下来视图获取锁o2...");
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName() + "  获取到了锁o2");
                }
            }
        } else {
            synchronized (o2) {
                System.out.println(Thread.currentThread().getName() + "  持有了锁o2,接下来视图获取锁o1...");
                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + "  获取到了锁o1");
                }
            }
        }
    }
}

//Thread-1持有了锁o1,接下来视图获取锁o2...
//Thread-0持有了锁o2,接下来视图获取锁o1...
//