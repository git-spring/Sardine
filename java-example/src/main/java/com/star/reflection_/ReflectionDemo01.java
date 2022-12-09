package com.star.reflection_;

/**
 * @author liudw
 * @date 2022/11/15 16:36
 */

// java 反射
public class ReflectionDemo01 {
    public static void main(String[] args) throws ClassNotFoundException {
        // 获取到类的字节码对象 (三种方法)
        A a = new A();
        Class aClass = a.getClass();  // 1  都有实例对象了,没必要用反射
        Class aClass1 = A.class;      // 2
        Class clazz = Class.forName("com.star.reflection_.A");  // 3 最常用
    }
}