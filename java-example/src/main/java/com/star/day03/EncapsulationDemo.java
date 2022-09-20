package com.star.day03;

/**
 * @author Spring
 * @date 2022/9/14 10:36
 */

// java 封装 encapsulation
// 封装是指一种将抽象性函式接口的实现细节部分包装、隐藏起来的方法
// 封装的优点：
//   1. 良好的封装能够减少耦合
//   2. 类内部的结构可以自由修改
//   3. 可以对成员变量进行更精确的控制
//   4. 隐藏实现细节
// 封装实现的步骤：
//   1. 修改属性的可见性来限制对属性的访问
//   2. 对每个值属性提供对外的公共方法访问，也就是创建一对赋取值方法，用于对私有属性的访问
public class EncapsulationDemo {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("Ideal");
        System.out.println(person.name);
        person.setSalary(5000.0);
        System.out.println(person.getSalary(""));

    }
}


class Person {

    public String name;
    private int age;
    private double salary;

    // 提供get 和 set 方法
    public String getName() {
        return name;
    }

    // 可以在内部进行校验
    public void setName(String name) {
        if (name.length() >= 2 && name.length() <= 6) {
            this.name = name;
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary(String pwd) {
        // 增加校验,如果密码正确才能显示
        if (pwd=="123"){
            return salary;
        }
        return -1;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}