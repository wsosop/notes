package com.wck.condition;

import com.wck.bean.RainBow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author 御香烤翅
 * @create 2020-03-10 11:13
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     *
     * @param importingClassMetadata  当前类上的注解信息
     * @param registry  BeanDefinition注册类，把所有需要添加到bean容器中的bean,
     *                  使用BeanDefinitionRegistry.registerBeanDefinition,手动注册进来
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        //判断com.wck.bean.Blue 是否在 BeanDefinitionRegistry 里面
        boolean blue = registry.containsBeanDefinition("com.wck.bean.Blue");
        boolean red = registry.containsBeanDefinition("com.wck.bean.Red");

//        System.out.println(blue);
//        System.out.println(red);
        if(blue && red){
            //注册一个bean
            registry.registerBeanDefinition("rainBow",new RootBeanDefinition(RainBow.class));
        }


    }
}
