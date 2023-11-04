package com.star.lambda_;

import static jdk.nashorn.internal.objects.NativeRegExp.test;

/**
 * @author liudw
 * @date 2022/11/4 16:59
 */

// java lambda 表达式
public class LambdaDemo01 {
    public static void main(String[] args) {
        //方法参数有多个且方法体中无返回值，则可以省略参数类型
        MoreParameterNoReturn moreParameterNoReturn = (a, b) -> {
            System.out.println("无返回值多个参数，省略参数类型：" + a + " " + b);

        };
        moreParameterNoReturn.test(20, 30);


        //方法中只有一个参数，那么小括号可以省略
        OneParameterNoReturn oneParameterNoReturn = a -> {
            System.out.println("方法中只有一个参数，那么小括号可以省略：" + a);
        };
        oneParameterNoReturn.test(10);


        //无参数无返回值，方法体中只有 一行代码的时候，可以去掉方法体的大括号
        NoParameterNoReturn noParameterNoReturn = () -> System.out.println("无参数无返回值，方法体中只有 一行代码");
        noParameterNoReturn.test();

        //方法体中只有一条语句，且是return语句，且无参数
        NoParameterReturn noParameterReturn = () -> 40;
        int ret = noParameterReturn.test();
        System.out.println(ret);


        // lambda 当作参数传递
        class A {
            public void test(MoreParameterNoReturn moreParameterNoReturn) {
                System.out.println("当作参数传递");
            }
        }

        A a = new A();
        a.test((c, b) -> System.out.println(c + b));
    }
}