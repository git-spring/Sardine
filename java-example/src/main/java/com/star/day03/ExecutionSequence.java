package com.star.day03;

/**
 * @author Spring
 * @date 2022/9/20 15:44
 */

// 测试java 的执行顺序
// 父类静态代码块 > 子类静态代码块 > main()方法 > 父类代码块 > 父类构造器 > 子类代码块 > 子类构造器
public class ExecutionSequence extends A {
    public static void main(String[] args) {
        System.out.println("main()方法");   // 此行语句的执行,不能完全代表main方法的执行顺序
        ExecutionSequence b = new ExecutionSequence();

    }
    static {
        System.out.println("子类静态代码块");
    }

    {
        System.out.println("子类代码块");
    }

    ExecutionSequence(){
        System.out.println("子类构造器");
    }
}

class A {
    static {
        System.out.println("父类静态代码块");
    }

    {
        System.out.println("父类代码块");
    }

    A(){
        System.out.println("父类构造器");
    }
}