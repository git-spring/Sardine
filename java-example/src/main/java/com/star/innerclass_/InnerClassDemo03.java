package com.star.innerclass_;

/**
 * @author Spring
 * @date 2022/9/23 11:18
 */

/**
 * java 静态内部类
 *
 */
public class InnerClassDemo03 {
    public static void main(String[] args) {
        Outer03.Inner03.staticShow();   // 访问静态内部类的静态方法
        Outer03.Inner03 inner03 = new Outer03.Inner03();
        inner03.show();    // 访问静态内部类的成员方法
    }
}

class Outer03 {

    public static class Inner03 {
        // 内部类的成员方法
        public void show() {
            System.out.println("java 静态内部类的show方法");
        }

        // 内部类的静态方法
        public static void staticShow() {
            System.out.println("java 静态内部类的staticShow方法");
        }
    }
}