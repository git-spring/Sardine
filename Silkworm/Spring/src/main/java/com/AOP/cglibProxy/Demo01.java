package com.AOP.cglibProxy;


import net.sf.cglib.proxy.Enhancer;

/**
 * @author liudw
 * @date 2022/12/14 17:13
 */

public class Demo01 {
    public static void main(String[] args) {
        EmpService empService = new EmpServiceImpl();
        // 获取CGLIB动态代理对象
        Enhancer enhancer = new Enhancer();
        // 设置enhancer对象的父类
        enhancer.setSuperclass(EmpServiceImpl.class);
        // 设置enhancer的回调对象
        enhancer.setCallback(new MyInterceptor(empService));
        EmpServiceImpl o = (EmpServiceImpl)enhancer.create();
        // 通过代理对象调用方法
        o.say();
        o.say1();  // 不需要再在业务代码中手动调用前置/后置方法
        o.finalMethod();
        EmpServiceImpl.staticMethod();
    }
}