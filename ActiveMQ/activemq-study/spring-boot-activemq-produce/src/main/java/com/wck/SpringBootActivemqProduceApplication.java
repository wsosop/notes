package com.wck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJms
@EnableScheduling
public class SpringBootActivemqProduceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootActivemqProduceApplication.class, args);
    }

}