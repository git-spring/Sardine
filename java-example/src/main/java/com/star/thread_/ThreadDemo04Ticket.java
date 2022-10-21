package com.star.thread_;

/**
 * @author liudw
 * @date 2022/10/21 15:54
 */

// 多线程售票问题
// 对多种情况测试,完善代码,每次启动三个线程
public class ThreadDemo04Ticket {
    public static void main(String[] args) {

        // demo01 测试
        Demo01 demo01 = new Demo01();
        Thread thread1 = new Thread(demo01);
        Thread thread2 = new Thread(demo01);
        Thread thread3 = new Thread(demo01);
        thread1.start();
        thread2.start();
        thread3.start();


    }

    // 会出现一张票卖多次,或票数为0继续卖的情况
    static class Demo01 implements Runnable {
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


    static class Demo02 implements Runnable {

        @Override
        public void run() {

        }
    }

}