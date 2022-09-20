package com.star.day03;

/**
 * @author Spring
 * @date 2022/9/16 8:53
 */

/**
 * java 多态
 * 多态就是同一个行为具有多个不同表现形式或形态的能力
 * 多态的必要条件:
 * 1. 继承
 * 2. 重写
 * 3. 父类引用指向子类对象：Parent p = new Child();
 */


public class PolymorphicDemo01 {
    public static void main(String[] args) {

        //定义父类数组
        Wine[] wines = new Wine[2];
        //定义两个子类
        JNC jnc = new JNC();
        JGJ jgj = new JGJ();

        //父类引用指向子类对象,编译看=号左边,运行看右边, 所以编译时是父类类型,运行时是指向的子类类型,可以调用子类对应的方法
        wines[0] = jnc;
        wines[1] = jgj;

        for (int i = 0; i < 2; i++) {
            System.out.println(wines[i].toString() + "--" + wines[i].drink());
        }
        System.out.println("-------------------------------");

    }
}


class Wine {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Wine() {
    }

    public String drink() {
        return "喝的是 " + getName();
    }

    public String toString() {
        return null;
    }
}

class JNC extends Wine {
    public JNC() {
        setName("JNC");
    }

    //重写父类方法，实现多态
    public String drink() {
        return "喝的是 " + getName();
    }

    public String toString() {
        return "Wine : " + getName();
    }
}

class JGJ extends Wine {
    public JGJ() {
        setName("JGJ");
    }

    // 重写父类方法，实现多态
    public String drink() {
        return "喝的是 " + getName();
    }

    public String toString() {
        return "Wine : " + getName();
    }
}

