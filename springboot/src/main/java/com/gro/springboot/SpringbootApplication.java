package com.gro.springboot;

import com.gro.bean.Cat;
import com.gro.configuration.MyConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringbootApplication.class, args);
        String[] names = run.getBeanDefinitionNames();
        for (String name: names) {
        System.out.println(name);
    }



    }
}
