package com.XML;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author liudw
 * @date 2022/12/2 17:24
 */

public class HelloWorld {
    public static void main(String[] args) {
        // 加载spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取配置创建的对象
        User user = context.getBean("user", User.class);
        user.info();

        User user1 = context.getBean("user1", User.class);
        System.out.println(user1.toString());

        User user2 = context.getBean("user2", User.class);
        System.out.println(user2.toString());

        Book book = context.getBean("bookA", Book.class);

        User user3 = context.getBean("user3", User.class);  // 注入外部属性
        System.out.println(user3.toString());
    }
}

