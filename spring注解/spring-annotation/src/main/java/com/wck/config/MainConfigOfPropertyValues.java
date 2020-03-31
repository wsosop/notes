package com.wck.config;

import com.wck.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author 御香烤翅
 * @create 2020-03-10 16:28
 */

/**
 * 本类有： @Value
 */
@Configuration
//使用@PropertySource读取外部配置文件中的key value ,保存到 运行的环境变量中
//加载完外部的配置文件以后使用 @Value("${person.nickname}") 取出值，详见 Person（com.wck.bean.Person） 这个bean
@PropertySource(value = {"classpath:person.properties"}) //导入配置文件
public class MainConfigOfPropertyValues {

    @Bean
    public Person person(){
        return new Person();
    }



}
