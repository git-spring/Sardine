package com.AOP.JDKProxy;

/**
 * @author liudw
 * @date 2022/12/14 14:28
 */

// 实现类
public class EmpServiceImpl implements EmpService {

    @Override
    public void StockUp() {
        System.out.println("UP 👆");
    }

}