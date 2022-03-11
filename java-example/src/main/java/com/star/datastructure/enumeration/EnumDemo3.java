package com.star.datastructure.enumeration;


// java 枚举类 实现接口
public interface EnumDemo3 {
    String getName();

    void print();

    //在 接口内部使用枚举
    enum Duck implements EnumDemo3{
        ; // 枚举常量

        @Override
        public String getName() {
            return null;
        }

        @Override
        public void print() {

        }
    }

    enum Lion implements EnumDemo3{
        ; // 枚举常量

        @Override
        public String getName() {
            return null;
        }

        @Override
        public void print() {

        }
    }
}

enum Pets implements EnumDemo3 {
    PANDA("团团", 1), DUCK("丫丫", 2), GOLDFISH("咕噜", 3);
    // 成员变量
    String name;
    int index;

    // 构造方法
    Pets(String name, int index) {
        this.name = name;
        this.index = index;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void print() {
        System.out.println(this.index + " :" + this.name);
    }

}