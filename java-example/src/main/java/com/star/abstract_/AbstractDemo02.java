package com.star.abstract_;

/**
 * @author Spring
 * @date 2022/9/21 17:04
 */

// 抽象类实践
public class AbstractDemo02 {
    public static void main(String[] args) {
        clz01 clz01 = new clz01();
        clz01.calculate();
        clz02 clz02 = new clz02();
        clz02.calculate();
    }
}

// 定义父抽象类
// 多态和动态绑定机制
abstract class Template {
    // 只需要定义规范,因为实际使用的是子类重写的方法逻辑
    public abstract void job();

    public void calculate() {
        long start = System.currentTimeMillis();
        job();    // 这里的job() ,哪个对象调用就是执行谁的方法(动态绑定机制)
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}

class clz01 extends Template {

    long sum;

    @Override
    public void job() {
        for (int i = 0; i < 100000000; i++) {
            sum += i;
        }
    }
}


class clz02 extends Template {

    @Override
    public void job() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}