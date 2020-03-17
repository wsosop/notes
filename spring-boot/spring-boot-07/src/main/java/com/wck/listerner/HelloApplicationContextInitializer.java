package com.wck.listerner;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author 御香烤翅
 * @create 2020-03-17 22:30
 */
public class HelloApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {

//        applicationContext.getBeanDefinitionCount();
        System.out.println("HelloApplicationContextInitializer...initialize..."+applicationContext);
    }
}
