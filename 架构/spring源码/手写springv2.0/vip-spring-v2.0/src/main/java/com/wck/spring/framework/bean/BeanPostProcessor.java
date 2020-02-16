package com.wck.spring.framework.bean;



/**
 * @author 御香烤翅
 * @create 2020-02-16 22:34
 */
public class BeanPostProcessor {

    //为在Bean的初始化前提供回调入口
    public Object postProcessBeforeInitialization(Object bean, String beanName){
        return bean;
    }


    //为在Bean的初始化之后提供回调入口
    public Object postProcessAfterInitialization(Object bean, String beanName){
        return bean;
    }


}
