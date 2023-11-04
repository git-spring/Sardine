package com.star.day01;

// java 的变量

/**
 *
 * 类变量: 独立于方法之外的变量,用 static 修饰,也称为静态变量
 *     类变量属于类,类被加载则变量会被加载,可以使用类名直接调用
 *     无论一个类创建了多少个对象，类只拥有类变量的一份拷贝
 *
 * 实例变量: 独立于方法之外的变量,不过没有 static 修饰
 *     实例变量属于实例对象,对象实列化后才可以使用对象
 * 局部变量: 类的方法中的变量
 *    局部变量属于方法,方法执行完则会销毁
 *    局部变量没有默认值,所以局部变量被声明后,必须经过初始化,才可以使用
 *    访问修饰符不能用于局部变量
 *
 */
public class Variable {
    // 类变量
    public static String classVariable = "这是一个类变量";
    // 实例变量
    public String instanceVariable = "这是一个实例变量";

    public static void test(){
        String localVariable = "这是一个局部变量";
        System.out.println(localVariable);
    }

    public static void main(String[] args){
        // 实例化对象
        Variable obj = new Variable();

        System.out.println(Variable.classVariable); // 类变量可以用类名直接调用,也可以用实例对象调用
        System.out.println(obj.classVariable);

        System.out.println(obj.instanceVariable);  // 实例变量使用实例对象调用

        test();  // 局部方法只能在方法内部使用

    }

}
