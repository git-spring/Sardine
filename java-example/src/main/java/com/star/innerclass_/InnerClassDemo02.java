package com.star.innerclass_;

/**
 * @author Spring
 * @date 2022/9/22 14:54
 */

/**
 * java 局部内部类
 *      类前不能有访问修饰符,但是可以用final修饰,用final修饰后不能被继承
 *      作用域仅在方法/代码块中,只能在方法/代码块内部使用
 *      有和外部类同名的属性时, 遵循就近原则,如果想访问外部的同名属性,可以使用 外部类名.this.属性名
 */

public class InnerClassDemo02 {
    public static void main(String[] args) {
        Outer02 outer02 = new Outer02();
        outer02.oil();
    }
}

class Outer02 {
    private int age = 10;

    private static void f2() {
        System.out.println("外部类私有成员方法");
    }

    // 内部类定义在方法体中
    public void oil() {
        class Inner02 {
            private int innerAge = 20;
            private int age = 30;    // 和外部类属性相同, 遵循就近原则,如果想访问外部的同名属性,可以使用 外部类名.this.属性名

            public void innerF2() {
                System.out.println(Outer02.this.age);  // 可以使用外部类的私有属性和方法
                f2();
                System.out.println(innerAge);
            }
        }
        // 只能在方法内部使用
        Inner02 inner02 = new Inner02();
        inner02.innerF2();
    }
}