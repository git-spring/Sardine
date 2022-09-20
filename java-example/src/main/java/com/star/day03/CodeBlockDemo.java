package com.star.day03;

/**
 * @author Spring
 * @date 2022/9/20 15:19
 */


/**
 * Java 代码块
 * 在 Java中,使用 { } 括起来的代码被称为代码块(Code block) , java 中的四种代码块
 *     1. 普通代码块
 *         写在方法中的代码
 *     2. 静态代码块
 *         定义在类中,使用 static关键字修饰,随着类加载而加载,不能实例化多少个对象都只会执行一次
 *     3. 实例代码块
 *         定义在类中,没有关键字修饰,实例化多少个对象就会执行多少次
 *     4. 同步代码块
 *         使用 synchronized 关键字修饰
 */

public class CodeBlockDemo {

    public static void main(String[] args) {
        // 静态代码块只会执行一次,而且是在构造器之前执行
        // 实例代码块会执行两次,也是比构造器先执行
        CodeBlockDemo c1 = new CodeBlockDemo();
        CodeBlockDemo c2 = new CodeBlockDemo();
    }

    // 写在方法中的为普通代码块
    public static void commonCodeBlock() {
        System.out.println("普通代码块");
    }

    // 静态代码块
    static {
        System.out.println("静态代码块");
        System.out.println("静态代码块会随着类的加载而加载,只会执行一次");
        System.out.println("------------------");
    }

    // 实例代码块
    {
        System.out.println("实例代码块");
        System.out.println("实例代码块是实例化的时候才会被调用,每次实例化都会调用一次实例代码块");
    }

    // 同步代码块
    public void run() {
        synchronized (this) {
            System.out.println("同步代码块");
        }
    }

    private CodeBlockDemo(){
        System.out.println(" 构造器~");
        System.out.println("==================");
    }

}