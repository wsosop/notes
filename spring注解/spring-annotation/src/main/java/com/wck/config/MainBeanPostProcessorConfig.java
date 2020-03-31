package com.wck.config;

import com.wck.beanPostProcessor.MyBeanPostProcessor2;
import com.wck.beanPostProcessor.UserTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 御香烤翅
 * @create 2020-03-15 18:15
 */

@Configuration
@ComponentScan("com.wck.beanPostProcessor")
public class MainBeanPostProcessorConfig {


    @Bean(initMethod = "start")
    public UserTest userTest(){
        UserTest user = new UserTest();
        user.setName("御香烤翅");
        return user;
    }

    @Bean
    public MyBeanPostProcessor2 myBeanPostProcessor2(){
        return new MyBeanPostProcessor2();
    }

}
