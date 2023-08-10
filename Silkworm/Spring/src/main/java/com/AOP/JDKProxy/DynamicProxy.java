package com.AOP.JDKProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author liudw
 * @date 2022/12/14 14:31
 */

// jdk动态代理类
public class DynamicProxy implements InvocationHandler {

    private Object targetObject;

    public DynamicProxy(Object targetObject) {
        this.targetObject = targetObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        ServiceTool.before();   // 前置处理
        Object invoke = method.invoke(targetObject, args);
        ServiceTool.after();    // 后置处理
        return invoke;
    }
}