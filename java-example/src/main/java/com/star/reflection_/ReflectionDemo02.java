package com.star.reflection_;

import java.lang.reflect.*;

/**
 * @author liudw
 * @date 2022/11/21 10:17
 */

// 反射常用方法
public class ReflectionDemo02 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class clz = Class.forName("com.star.reflection_.A");

        // 获取包名,类名
        String name = clz.getPackage().getName();  // 获取包名
        String simpleName = clz.getSimpleName();   // 获取类名
        String name1 = clz.getName();              // 获取完整类名

        // 使用反射获取构造方法,新建实例
        Constructor[] constructors ;
        constructors = clz.getConstructors();      // 获取公共的构造方法
        constructors = clz.getDeclaredConstructors();  // 获取所有的构造方法

        Constructor con = clz.getDeclaredConstructor(String.class); // 获取特定方法签名的构造方法
        con.setAccessible(true);    // 暴力访问,可以访问私有属性
        A obj = (A)con.newInstance("reflection"); // 新建实例

        // 获取成员属性
        Field[] fields ;
        fields = clz.getDeclaredFields();   // 获取所有属性
        Field name6 = clz.getDeclaredField("name");
        name6.setAccessible(true);
        name6.set(obj,"zhangsan");   // 设置属性值
        Object o = name6.get(obj);   // 获取属性值

        fields = clz.getFields();    // 获取所有公共的属性
        // Field field = clz.getField("name");  // 获取公共属性,指定属性名称

        /*getField和getDeclaredField的区别
          getField 只能获取public的，包括从父类继承来的字段。
          getDeclaredField 可以获取本类所有的字段，包括private的，但是不能获取继承来的字段。 (注： 这里只能获取到private的字段，但并不能访问该private字段的值,除非加上setAccessible(true))
        */


        // 获取成员方法
        Method[] methods;
        methods = clz.getDeclaredMethods();   // 获取所有成员方法
        Method m = clz.getDeclaredMethod("setAge", int.class);
        m.invoke(obj, 100);   // 调用成员方法
        Method getAge = clz.getDeclaredMethod("getAge");
        Object invoke = getAge.invoke(obj);

    }
}