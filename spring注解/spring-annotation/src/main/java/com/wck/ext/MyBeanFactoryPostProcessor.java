package com.wck.ext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author 御香烤翅
 * @create 2020-03-11 23:25
 */

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanFactoryPostProcessor...postProcessBeanFactory...");
        int beanDefinitionCount = beanFactory.getBeanDefinitionCount();

        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();

        System.out.println("当前的beanFactory中有"+beanDefinitionCount+"个beanDefinition");
        System.out.println("beanDefinition都为："+Arrays.asList(beanDefinitionNames));
    }
}
