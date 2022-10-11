package com.star.wrapper_;

/**
 * @author Spring
 * @date 2022/10/10 15:49
 */

/**
 * java 中的包装类
 * java 中的基本数据类型不是对象,里面不能使用方法,而包装类对象可以使用方法
 *
 * 基本数据类型         包装类
 *     byte            Byte
 *     short           Short
 *     int             Integer
 *     long            Long
 *     float           Float
 *     double          Double
 *     char            Character
 *     boolean         Boolean
 *
 * 这里以 int 和 Integer 为例,其他包装类类似
 */
public class Integer01 {
    public static void main(String[] args) {
        int num = 100;

        // 基本数据类型 转 包装类 (装箱)
        Integer numObj = Integer.valueOf(num);
        Integer numObj1 = new Integer(num);

        // 包装类 转 基本数据类型 (拆箱)
        int num1 = numObj.intValue();
        // JDK 1.5 之后可以自动装箱、拆箱
        Integer numObj2 = 666;    // 自动装箱
        int num2 = numObj;        // 自动拆箱

        // 字符串转成数字,字符串内容必须为数字
        String str = "36";
        int value = Integer.parseInt(str);
        Integer value1 = Integer.valueOf(str);
        // 数字转字符串
        String s = String.valueOf(100);
        String s1 = value1.toString();

    }
}