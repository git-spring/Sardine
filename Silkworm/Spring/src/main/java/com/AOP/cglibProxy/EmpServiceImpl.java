package com.AOP.cglibProxy;

import org.aspectj.lang.annotation.Around;

/**
 * @author liudw
 * @date 2022/12/14 17:05
 */

public class EmpServiceImpl implements EmpService {

    @Override
    public void say() {
        System.out.println("say ... ");
    }

    @Override
    public void say1() {
        System.out.println("say1 ... ");
    }

    public final void finalMethod() {
        System.out.println("final method");
    }

    public static void staticMethod() {
        System.out.println("static method");
    }
}