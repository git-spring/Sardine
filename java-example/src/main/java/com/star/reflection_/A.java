package com.star.reflection_;

/**
 * @author liudw
 * @date 2022/11/21 10:13
 */

public class A {
    private String name ;
    private int age ;

    public static void info(){

    }

    public A(){}
    private A(String name){
        this.name = name;
    }
    public A(String name,int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "A{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}