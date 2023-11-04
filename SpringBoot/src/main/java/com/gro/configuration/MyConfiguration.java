package com.gro.configuration;

import com.gro.bean.Cat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 配置类
@Configuration
public class MyConfiguration {
    // 把组件添加到容器中
    @Bean("tom")
    public Cat tom(){
        return new Cat("tom","white");
    }


}
