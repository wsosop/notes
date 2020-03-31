package com.wck.test;

import com.wck.aop.MathCalculator;
import com.wck.config.MainConfigOfAOP;
import com.wck.ext.ExtConfig;
import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 御香烤翅
 * @create 2020-03-10 19:36
 */
public class ExtTest {



    /**
     * 测试 @Value 赋值
     */
    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ExtConfig.class);

        System.out.println("-------------------------------");

        Object str="我发布了一个事件";
        //发布了一个事件
        applicationContext.publishEvent(new ApplicationEvent(str) {
        });

        applicationContext.close();
    }


//    public void printBeanDefinitionNames(){
//        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            System.out.println(beanDefinitionName);
//        }
//    }
}
