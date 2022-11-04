package com.star.innerclass_;

/**
 * @author Spring
 * @date 2022/9/22 14:35
 */

/**
 * java 成员内部类
 *     成员内部类定义在类的成员位置,相当于外部类的一个成员属性
 */
public class InnerClassDemo01 {
    public static void main(String[] args) {
        Outer01 outer01 = new Outer01();
        Outer01.Inner01 inner01 = outer01.new Inner01();   // 创建内部类对象
        inner01.show();
        inner01.f1();
    }
}

// 成员内部类
class Outer01 {
     private int value = 100;
    // 定义在成员位置,可以扩展java的单继承机制
    public class Inner01 extends A {
        int value = 200;
        public void show() {
            System.out.println("内部类的show方法");
            System.out.println(value);
            System.out.println(Outer01.this.value);   // 访问外部类的私有属性
        }
    }
}

class A {
    public static void f1(){
        System.out.println("内部类的父类f1方法");
    }
}