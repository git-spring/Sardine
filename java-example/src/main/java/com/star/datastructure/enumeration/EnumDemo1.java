package com.star.datastructure.enumeration;


/**
 *  java 枚举
 *
 *
 */
public class EnumDemo1 {
    public static void main(String[] args) {
        // 直接当作常量使用
        System.out.println(Animals.DUCK);
        change();
    }
    // 在swith中使用
    public static void change(){
        Animals animals = Animals.ELEPHANT;
        switch (animals){
            case ELEPHANT:
                animals = Animals.GOLDFISH;
                break;
            case PANDA:
                animals = Animals.DOLPHIN;
                break;
        }
        System.out.println(animals);
    }

}

// 定义枚举
 enum Animals{
    PANDA,
    ELEPHANT,
    LION,
    MONKEY,
    GOLDFISH,
    DUCK,
    DOLPHIN;
}
