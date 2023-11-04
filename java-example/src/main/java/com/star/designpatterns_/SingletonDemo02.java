package com.star.designpatterns_;



/**
 * @author Spring
 * @date 2022/9/21 10:31
 */

// java 单例模式 懒汉式  存在线程安全问题
public class SingletonDemo02 {
    public static void main(String[] args) {
        Pig p1 = Pig.getInstance();
        Pig p2 = Pig.getInstance();
        System.out.println(p1==p2);
    }
}


class Pig {

    private String name;
    private static Pig pig;

    private Pig(String name) {
        this.name = name;
    }

    // 懒汉式, 用到的时候才创建对象
    public static Pig getInstance(){
        // 如果为空则创建
        if(pig==null){
            pig = new Pig("Page");
        }
        return pig;
    }


}