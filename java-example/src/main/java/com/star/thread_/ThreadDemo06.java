package com.star.thread_;

/**
 * @author liudw
 * @date 2022/10/24 16:02
 */

// java 多线程常用方法2
// join : 线程加入, 线程加入后,会优先执行,加入的线程执行完后再执行其他的线程
// yield: 线程礼让, 不一定礼让成功,具体看cpu的调度
public class ThreadDemo06 {
    public static void main(String[] args) throws InterruptedException {
        T6 t6 = new T6();
        t6.start();

        for (int i = 0; i <= 20; i++) {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName()+ "     主线程 " + i);

            if (i==5){
                System.out.println("子线程join ,接下来会让子线程先执行");
                t6.join();

                //System.out.println("主线程yield ,但是不一定其他线程优先执行");
                //Thread.yield();
            }
        }

    }


    static class T6 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i <= 20; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName()+ " 子线程 " + i);
            }
        }
    }
}