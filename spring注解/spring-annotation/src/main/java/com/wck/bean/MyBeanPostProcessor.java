package com.wck.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author 御香烤翅
 * @create 2020-03-10 15:29
 */

/**
 * 后置处理器：初始化前后进行处理工作
 * 将后置处理器加入ioc容器之中
 */

/**
 *
 *
 * 方法	                                说明
 * postProcessBeforeInitialization	    实例化、依赖注入完毕，
 *                                      在调用显示的初始化之前完成一些定制的初始化任务
 * ------------------------------------------------------------------------------
 * postProcessAfterInitialization	    实例化、依赖注入、初始化完毕时执行
 *
 *
 */

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization:"+beanName+"-->"+bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization:"+beanName+"-->"+bean);
        return bean;
    }
}
