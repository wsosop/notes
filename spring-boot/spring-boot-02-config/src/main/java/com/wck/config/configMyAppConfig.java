package com.wck.config;

import com.wck.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 御香烤翅
 * @create 2020-03-13 10:55
 */

/**
 * @Configuration 指明这个类是配置类
 */
@Configuration
public class configMyAppConfig {


    @Bean
    public HelloService helloService(){
        System.out.println("配置类添加了HelloService组件");
        return new HelloService();
    }

}
