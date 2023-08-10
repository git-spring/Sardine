package com.AOP.cglibProxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author liudw
 * @date 2022/12/14 17:08
 */

public class MyInterceptor implements MethodInterceptor {
    private Object targetObject ;

    public MyInterceptor(Object targetObject) {
        this.targetObject = targetObject;
    }

    /**
     **
     * @param obj           代理对象
     * @param method       被代理对象的方法
     * @param args         方法入参
     * @param methodProxy  代理方法
     * @return
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib 前置 ... ");   // 可设置前置方法
        Object invoke = methodProxy.invokeSuper(obj, args);
        System.out.println("cglib 后置 ... ");   // 可设置后置方法
        return invoke;
    }
}