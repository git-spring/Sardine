package com.star.thread_;

/**
 * @author liudw
 * @date 2022/10/24 16:25
 */

// java 多线程 用户线程(非守护线程)和守护线程
// 用户线程 :
// 守护线程 : 当进程中不存在非守护线程了,则守护线程自动销毁
public class ThreadDemo07 {
    public static void main(String[] args) throws InterruptedException {
        T7 t7 = new T7();
        // 将子线程设置为守护线程
        // 主线程执行完,则子线程会自动停止,如果不设置,则主线程执行完后,子线程会一直执行
        t7.setDaemon(true);   // 需要先设置成守护线程,再start,不然报错
        t7.start();

        for (int i = 0; i < 10; i++) {
            System.out.println("主线程  学生在自习" + i);
            Thread.sleep(500);
        }
    }

    static class T7 extends Thread {
        @Override
        public void run() {
            for (; ; ) {

                try {
                    System.out.println("子线程 老师在辅导自习");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}