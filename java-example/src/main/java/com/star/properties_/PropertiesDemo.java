package com.star.properties_;


import java.util.Properties;

/**
 *  java Properties 类
 *      Properties 类 继承自 {@link java.util.Hashtable} ，主要用于读取Java中的配置文件
 *
 */
public class PropertiesDemo {

    public static void main(String[] args) {
        //demo01();
    }

    // 基本使用
    public static void demo01() {
        Properties properties = new Properties();
        properties.put("name", "root");
        properties.put("password", "123456");
        properties.put("db", "test");

        System.out.println(properties.getProperty("name"));
        System.out.println(properties);
    }


    // 获取JVM 的系统配置文件
    public static void demo02() {
        // 通过key获取单个参数
        String fileSeparator = System.getProperty("file.separator");
        System.out.println("file.separator : " + fileSeparator);
        System.out.println("------------");
        // 获取所有参数
        Properties properties = System.getProperties();
        properties.list(System.out);
    }




}


