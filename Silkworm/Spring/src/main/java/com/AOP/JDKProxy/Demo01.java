package com.AOP.JDKProxy;



import java.lang.reflect.Proxy;

/**
 * @author liudw
 * @date 2022/12/14 14:45
 */

public class Demo01 {
    public static void main(String[] args) {
        EmpService empService = new EmpServiceImpl();
        DynamicProxy dynamicProxy = new DynamicProxy(empService);
        EmpService proxy = (EmpService) Proxy.newProxyInstance(empService.getClass().getClassLoader(), empService.getClass().getInterfaces(), dynamicProxy);
        // 通过代理方法执行,会调用handler中的invoke
        proxy.StockUp();
    }
}