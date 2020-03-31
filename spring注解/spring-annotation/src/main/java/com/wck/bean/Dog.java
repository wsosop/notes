package com.wck.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author 御香烤翅
 * @create 2020-03-10 15:14
 */
@Component
public class Dog implements ApplicationContextAware {

    //实现 ApplicationContextAware ，这里就可以调用 ApplicationContext
    private ApplicationContext applicationContext;

    public Dog() {
        System.out.println("Dog 构造");
    }

    @PostConstruct
    public void init(){
        System.out.println("Dog PostConstruct");
    }

    @PreDestroy
    public void destory(){
        System.out.println("Dog PreDestroy");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
