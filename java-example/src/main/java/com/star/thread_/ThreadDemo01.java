package com.star.thread_;


/**
 * @author Spring
 * @date 2022/10/20 15:00
 */



/**
 * java 多线程
 * java 创建多线程的方式
 *    1. 继承 Thread 类
 *        创建一个继承于Thread类的子类
 *        重写run()方法
 *        创建Thread类的子类的对象
 *        通过此对象调用start()来启动一个线程
 *    2. 实现 Runnable 接口
 *        创建一个实现Runnable接口的类。
 *        实现类去实现Runnable接口中的抽象方法：run()。
 *        创建实现类的对象。
 *        将此对象作为参数传到Thread类的构造器中，创建Thread类的对象。
 *        通过Thread类的对象调用start()方法。
 *    3. 实现 Callable 接口
 *        相比run()方法，可以有返回值
 *        方法可以抛出异常
 *        支持泛型的返回值
 *        需要借助FutureTask类，比如获取返回结果
 *
 */
public class ThreadDemo01 {
    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.start();        // 启动一个线程
        new Cat().start();  // 再启动一个线程
        System.out.println("主线程");
    }

    // 继承Thread 类 实现多线程
    static class Cat extends Thread {

        int count = 0;   // 用static 修饰可以实现资源共享

        @Override
        public void run() {
            //super.run();   // Thread 类没有run方法,是实现的Runnable中的
            while (true) {
                try {
                    Thread.sleep(500);
                    System.out.println(Thread.currentThread().getName() + "--" + (++count));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (count == 10) {
                    break;
                }
            }
        }
    }


    // 查看电脑的线程数
    public static void getCPUProcessors() {
        Runtime runtime = Runtime.getRuntime();
        int cpuNums = runtime.availableProcessors();
        System.out.println("线程数： " + cpuNums);
    }
}

