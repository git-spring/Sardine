package com.star.day03;

/**
 * @author Spring
 * @date 2022/9/20 10:14
 */

// java 静态变量/方法
public class StaticMethodDemo {
    public static void main(String[] args) {
        Animal a = new Animal("Cat");
        Animal b = new Animal("Dog", 10);

        System.out.println(Animal.name);
        a.age = 10;

        a.sleep();
        Animal.sleep();

        System.out.println("当前有 " + Animal.count + " 个小动物");
    }
}

class Animal {
    // 静态变量
    static String name;
    static int count;
    // 普通成员变量
    int age;

    // 静态方法
    static void sleep() {
        // age = 10;  // Non-static field 'age' cannot be referenced from a static context 静态方法中不能使用非静态变量
        System.out.println("zZ~");
    }

    // 普通方法
    void eat() {
        System.out.println("🐟");
    }

    Animal(String name) {
        this(name, 0);
    }

    Animal(String name, int age) {
        this.name = name;
        this.age = age;
        this.count += 1;
    }

}

