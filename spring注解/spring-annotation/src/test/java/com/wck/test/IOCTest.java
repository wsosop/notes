package com.wck.test;

import com.wck.bean.Person;
import com.wck.config.MainConfig;
import com.wck.config.MainConfig2;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

/**
 * @author 御香烤翅
 * @create 2020-03-09 19:52
 */
public class IOCTest {


    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);


    /**
     * 用于测试 FactoryBean
     */
    @Test
    public void test5(){
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            System.out.println(beanDefinitionName);
//        }

        //工厂Bean获取的是调用 getObject()创建的对象
        Object colorFactoryBean = applicationContext.getBean("colorFactoryBean");
        Object colorFactoryBean2 = applicationContext.getBean("colorFactoryBean");
        System.out.println(colorFactoryBean.getClass());
        System.out.println(colorFactoryBean == colorFactoryBean2);

        Object colorFactoryBean3 = applicationContext.getBean("&colorFactoryBean");
        System.out.println(colorFactoryBean3.getClass());


    }


    /**
     * 用于测试 @Import 注解
     */
    @Test
    public void test4(){
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

    }

    /**
     * 用于测试 @Conditional 注解
     */
    @Test
    public void test3(){

        //获取运行的环境信息
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("os.name");
        System.out.println(property);
        Map<String, Person> personMap = applicationContext.getBeansOfType(Person.class);
        System.out.println(personMap);
    }



    /**
     * 用于测试@Scope这个注解的作用是调整作用域
     */
    @Test
    public void test2(){
        applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
//        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
//        for (String name : beanDefinitionNames) {
//            System.out.println(name);
//        }
//
        Object person1 = applicationContext.getBean("person");
        Object person2 = applicationContext.getBean("person");
        System.out.println(person1 == person2);



    }

    /**
     * 用于测试@ComponentScan(value = "com.wck")这个注解的作用
     */
    @Test
    public void test1(){
        applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }

    }
}
