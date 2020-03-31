package com.wck.ext;

import com.wck.bean.Blue;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.stereotype.Component;

/**
 * @author 御香烤翅
 * @create 2020-03-11 23:53
 */
//@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {


    //BeanDefinitionRegistryPostProcessor接口实现的方法
    //BeanDefinitionRegistry 就是BeanDefinition的保存中心（bean定义信息的保存中心）
    //以后 BeanFactory 就是按照BeanDefinitionRegistry 里面保存的每一个bean定义信息（BeanDefinition）创建bean实例
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        System.out.println("MyBeanDefinitionRegistryPostProcessor...postProcessBeanDefinitionRegistry," +
                "beanDefinition的数量："+registry.getBeanDefinitionCount());

        //第一种方法创建 BeanDefinition
//        RootBeanDefinition beanDefinition = new RootBeanDefinition(Blue.class);

        //第二种方法创建 BeanDefinition
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(Blue.class).getBeanDefinition();

        registry.registerBeanDefinition("hello",beanDefinition);
    }


    //BeanFactoryPostProcessor接口的实现方法
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanDefinitionRegistryPostProcessor...postProcessBeanFactory," +
                "beanDefinition的数量："+beanFactory.getBeanDefinitionCount());
    }
}
