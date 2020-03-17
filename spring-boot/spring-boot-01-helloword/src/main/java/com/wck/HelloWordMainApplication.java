package com.wck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 御香烤翅
 * @create 2020-03-08 23:31
 */

//@SpringBootApplication 来标注一个主程序类，表明这是一个 spring boot应用
@SpringBootApplication
public class HelloWordMainApplication {

    public static void main(String[] args) {

        //spring应用启动起来
        SpringApplication.run(HelloWordMainApplication.class,args);
    }

}
