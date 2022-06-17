package com.star.exception;

/**
 * @author Spring
 * @date 2022/4/28 15:54
 * @describe :
 */

// 自定义异常
public class TestException extends Exception {
// TODO: 2022/4/28

     String msg ;

     public TestException(String msg){
         this.msg=msg;
     }

    @Override
    public String toString() {
        return "TestException{" +
                "msg='" + msg + '\'' +
                '}';
    }
}