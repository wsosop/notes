package com.wck.bean;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author 御香烤翅
 * @create 2020-03-09 17:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {

    //使用@Value赋值
    //1.基本数值
    //2.可以写SpEL;#{}
    //3.可以写${} 取出配置文件中的值（在运行环境变量中的值）

    @Value("wckValue")
    private String name;

    @Value("#{20-2}")
    private Integer age;

    @Value("${person.nickname}")
    private String nickname;

    public Person(String name,Integer age) {
        this.name = name;
        this.age=age;
    }
}
