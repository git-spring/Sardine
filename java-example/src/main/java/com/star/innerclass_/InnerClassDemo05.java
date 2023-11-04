package com.star.innerclass_;

/**
 * @author Spring
 * @date 2022/9/23 14:39
 */

/**
 * 匿名内部类当作参数传递
 */
public class InnerClassDemo05 {
    public static void main(String[] args) {
        // new Cat(){} 是一个匿名内部类,当作参数传递给f1
        f1(new Cat() {
            @Override
            public void catchMouse(int num) {
                System.out.println(num);
            }
        });
    }

    // 静态方法,形参时接口类型
    public static void f1(Cat cat) {
        cat.catchMouse(10);
    }

}


interface Cat {
    void catchMouse(int num);
}