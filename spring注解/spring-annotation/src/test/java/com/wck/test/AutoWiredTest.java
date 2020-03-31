package com.wck.test;

import com.wck.bean.Person;
import com.wck.config.MainConfigOfAutoWired;
import com.wck.config.MainConfigOfPropertyValues;
import com.wck.dao.BookDao;
import com.wck.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author 御香烤翅
 * @create 2020-03-10 19:36
 */
public class AutoWiredTest {


    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutoWired.class);

    /**
     * 测试 @Value 赋值
     */
    @Test
    public void test01(){

        System.out.println("-------------------------------");
        BookService bean = applicationContext.getBean(BookService.class);
        Object obj = applicationContext.getBean("bookController");

        System.out.println("这个对象====>>>>"+obj.getClass().getName());

        System.out.println("---------------------");
        System.out.println("测试打印出的applicationContext:"+applicationContext);



    }


    public void printBeanDefinitionNames(){
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
