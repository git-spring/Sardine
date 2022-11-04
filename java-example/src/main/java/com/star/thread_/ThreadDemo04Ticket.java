package com.star.thread_;

/**
 * @author liudw
 * @date 2022/10/21 15:54
 */

// 多线程售票问题
// 对多种情况测试,完善代码,每次启动三个线程
public class ThreadDemo04Ticket {
    public static void main(String[] args) {
        ThreadDemo04Ticket threadDemo04Ticket = new ThreadDemo04Ticket();


        // demo01 测试
        //Demo01 demo01 = threadDemo04Ticket.new Demo01();
        //Thread thread1 = new Thread(demo01);
        //Thread thread2 = new Thread(demo01);
        //Thread thread3 = new Thread(demo01);
        //thread1.start();
        //thread2.start();
        //thread3.start();

        // demo02 测试
        Demo02 demo02 = threadDemo04Ticket.new Demo02();
        Thread thread1 = new Thread(demo02);
        thread1.start();
        Thread thread2 = new Thread(demo02);
        thread2.start();
        Thread thread3 = new Thread(demo02);
        thread3.start();

    }

    // 线程没有同步 会出现一张票卖多次,或票数为0继续卖的情况
     class Demo01 implements Runnable {
        int ticketNums = 20;   // 初始票数

        @Override
        public void run() {
           while (true){

               try {
                   Thread.sleep(500);
                   System.out.println("窗口 "+Thread.currentThread().getName()+" 卖出了一张票," +
                           "余票 "+ (--ticketNums));
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

               if (ticketNums<=0){
                   System.out.println("余票为0, 售票结束...");
                   break;
               }
           }
        }
    }

    // 使用 synchronized 实现线程同步
    class Demo02 implements Runnable {
        boolean loop = true;   // 循环变量,控制run方法里面的循环

        int ticketNums = 20;

        @Override
        public void run() {
            while (loop) {
                sell();
            }
        }

        public synchronized void sell() {
            if (ticketNums <= 0) {
                System.out.println("余票为0, 售票结束...");
                loop = false;
                return;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("窗口 " + Thread.currentThread().getName() + " 卖出了一张票," +
                    "余票 " + (--ticketNums));
        }
    }

}