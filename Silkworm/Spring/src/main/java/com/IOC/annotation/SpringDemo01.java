package com.IOC.annotation;

import com.IOC.annotation.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author liudw
 * @date 2022/12/12 11:09
 */

public class SpringDemo01 {
    @Test
    public void testService() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        // 是用注解时,默认bean的名称是类名首字母小写
        UserService userService = context.getBean("userService", UserService.class);
        System.out.println(userService.getClass());
        userService.add();
    }

    @Test
    public void testConfigClass() {

    }
}