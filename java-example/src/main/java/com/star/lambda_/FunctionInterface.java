package com.star.lambda_;

/**
 * @author liudw
 * @date 2022/11/4 17:11
 */

// java 函数式接口


@FunctionalInterface
interface NoParameterNoReturn {
    //注意：只能有一个抽象方法
    void test();
}

//无返回值一个参数
@FunctionalInterface
interface OneParameterNoReturn {
    void test(int a);
}

//无返回值多个参数
@FunctionalInterface
interface MoreParameterNoReturn {
    void test(int a, int b);
}

//有返回值无参数
@FunctionalInterface
interface NoParameterReturn {
    int test();
}

//有返回值一个参数
@FunctionalInterface
interface OneParameterReturn {
    int test(int a);
}

//有返回值多参数
@FunctionalInterface
interface MoreParameterReturn {
    int test(int a, int b);
}