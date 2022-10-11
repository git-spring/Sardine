package com.star.innerclass_;

/**
 * @author Spring
 * @date 2022/9/22 15:16
 */



/**
 * java 匿名内部类
 */
public class InnerClassDemo04 {
    public static void main(String[] args) {
        new Outer04().dream("heaven").flyto();
        // 调用 new Outer04().dream("") 后,返回的是一个Outer04$1,这就是匿名类,由编译器创建
        Class<? extends Animal> clz = new Outer04().dream("").getClass();
        System.out.println(clz);

        new Outer04().eat();
    }
}

class Outer04 {
    private int value = 65535;


    public Animal dream(String destination) {
        // 匿名内部类 new 后面的接口/类必须存在
        return new Animal() {
            @Override
            public void flyto() {
                System.out.println(destination);
                System.out.println(Outer04.this.value);
            }
        };    // 这是return语句,这里必须有;号
        // lambda 的形式
        //return () -> System.out.println(destination);
    }

    // 基于类的匿名内部类
    void eat() {
        Dog dog =  new Dog() {

        };
        // 不一定要return,可以在内部调用
        dog.born();
    }
}


interface Animal {
    void flyto();
}

class Dog{
    public void born(){
        System.out.println("dog eat born");
    }
}
