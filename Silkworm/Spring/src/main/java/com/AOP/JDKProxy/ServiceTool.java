package com.AOP.JDKProxy;

import org.aspectj.lang.annotation.Before;

/**
 * @author liudw
 * @date 2022/12/14 14:53
 */

// 增强方法类
public class ServiceTool {

    public static void before(){
        System.out.println("前置处理 ...");
    }

    public static void after(){
        System.out.println("后置处理 ...");
    }
}