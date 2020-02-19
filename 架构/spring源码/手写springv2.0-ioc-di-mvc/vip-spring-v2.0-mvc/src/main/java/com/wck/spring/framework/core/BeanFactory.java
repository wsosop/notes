package com.wck.spring.framework.core;

/**
 * @author 御香烤翅
 * @create 2020-02-16 19:31
 */
public interface BeanFactory {
    //根据bean的名字，获取在IOC容器中得到bean实例
    Object getBean(String beanName);
}
