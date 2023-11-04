package com.star.abstract_;

/**
 * @author Spring
 * @date 2022/9/21 16:04
 */



/**
 * java 抽象
 *   1. 使用 abstract 关键字修饰,abstract 可以修饰类和方法,分别称为抽象类和抽象方法
 *   2. 抽象类中可以包含抽象方法,普通方法,普通属性
 *   3. 抽象类不能被实例化,因为可能存在未实现的抽象方法
 *   4. 如果一个类中包含抽象方法,则这个类必须被申明为抽象类
 *   5. 如果一个类继承另一个抽象类,则这个类需要实现父类的抽象方法或者也申明为抽象类
 *   6. abstract 不能和final,static,private 组合使用,因为抽象类/方法需要被继承/重写
 *   7. 抽象类主要用于设计
 */

public class AbstractDemo01 {
    public static void main(String[] args) {
        //Human h = new Human();  // 抽象类不能被实例化
        Student s = new Student();
        s.basketball();
        s.football();
    }
}

// 抽象类
abstract class Human {
    // 普通属性
    static String name;
    String addr;

    // 抽象方法
    public abstract void basketball();

    // 普通方法
    public static void football(){
        System.out.println("普通方法");
    }

}

// 如果一个类继承了抽象类,则要么自己申明为抽象类,要么实现父类的抽象方法
class Student extends Human{

    @Override
    public void basketball() {
        System.out.println("实现父类的抽象方法");
    }
}