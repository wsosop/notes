package com.wck.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * @author 御香烤翅
 * @create 2020-03-10 10:50
 */
@Component
public class Red implements ApplicationContextAware,BeanNameAware,EmbeddedValueResolverAware {

    private ApplicationContext applicationContext;


    //实现了 ApplicationContextAware
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("传入的ioc:"+applicationContext);
        this.applicationContext=applicationContext;
    }


    //实现了BeanNameAware
    @Override
    public void setBeanName(String name) {
        System.out.println("当前的bean名字"+name);
    }

    //实现了EmbeddedValueResolverAware
    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {

        String stringValue = resolver.resolveStringValue("你好${os.name},#{11+10}");
        System.out.println("解析的字符串："+stringValue);
    }
}
