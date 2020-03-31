package com.wck.test;

import com.wck.aop.MathCalculator;
import com.wck.config.MainConfigOfAOP;
import com.wck.config.MainConfigOfAutoWired;
import com.wck.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 御香烤翅
 * @create 2020-03-10 19:36
 */
public class AOPTest {


    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);

    /**
     * 测试 @Value 赋值
     */
    @Test
    public void test01(){

        System.out.println("-------------------------------");
        MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);
        System.out.println(mathCalculator.getClass().getName());
        int div = mathCalculator.div(10, 1);
        System.out.println(div);


    }


    public void printBeanDefinitionNames(){
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
