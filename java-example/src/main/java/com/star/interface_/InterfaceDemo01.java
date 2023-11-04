package com.star.interface_;

/**
 * @author Spring
 * @date 2022/9/22 9:57
 */

/**
 * java interface 接口
 *     java 接口是一系列抽象方法和属性的集合
 *     在接口中可以包含属性,抽象方法,(jdk8及以后可以有默认方法和静态方法)
 *     接口是被实现,使用 implements 关键字,可以多实现
 *     两个接口之间可以使用 extends 继承
 *     接口不能直接被实例化
 *
 *     
 * 接口有以下特性：
 *     接口是隐式抽象的,当声明一个接口的时候,不必使用abstract关键字
 *     接口中每一个方法也是隐式抽象的,声明时同样不需要abstract关键字
 *     接口中的方法和属性都是公有(public)的
 */

public interface InterfaceDemo01 {
    // 可以不用public和abstract修饰
    public int i = 1;
    public abstract void hi();

    // jdk8及以后可以有默认方法和静态方法
    default void hello(){
        System.out.println("hello!");
    }

    static void say(){
        System.out.println("say hello");
    }
}

