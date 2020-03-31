package com.wck.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author 御香烤翅
 * @create 2020-03-10 10:08
 * 自定义Condition 需要实现 Condition接口
 */
public class WindowsCondition implements Condition {

    /**
     *
     * @param context 当前运行的上下文context
     * @param metadata 标注注解的原数据
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {


        //1.获取到ioc使用的beanFactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

        //2.获取类的加载信息
        ClassLoader classLoader = context.getClassLoader();

        //3.获取当前环境信息
        Environment environment = context.getEnvironment();

        //4.获取bean定义的注册类
        BeanDefinitionRegistry registry = context.getRegistry();

        //获取到操作系统的标识信息
        String property = environment.getProperty("os.name");

        if(property.contains("Windows")){
            return true;
        }

        return false;
    }
}
