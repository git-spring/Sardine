package com.star.designpatterns_;

/**
 * @author Spring
 * @date 2022/9/21 10:15
 */

// java 单例模式  饿汉式
public class SingletonDemo01 {
    public static void main(String[] args) {
        Single01 instance1 = Single01.getInstance();
        Single01 instance2 = Single01.getInstance();
        System.out.println(instance1==instance2);  // 多次获取实例得到的是同一个实例
    }
}

class Single01 {
    private String name;

    // 饿汉式, 类加载时就将对象创建好了
    private static Single01 single01 = new Single01();

    // 构造器私有化
    private Single01(){ }

    // 返回实例对象,需要将方法申明为static,方便用类直接调用方法
    public static Single01 getInstance() {
        return single01;
    }
}