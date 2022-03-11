package com.gro;

import com.gro.bean.Cat;
import com.gro.configuration.MyConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BootBootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(BootBootApplication.class, args);
        String[] names = run.getBeanDefinitionNames();
        for (String name: names) {
            System.out.println(name);
        }


        Cat tom = run.getBean("tom", Cat.class);
        MyConfiguration bean = run.getBean(MyConfiguration.class);
        System.out.println(tom);
        System.out.println(bean);
    }

}
