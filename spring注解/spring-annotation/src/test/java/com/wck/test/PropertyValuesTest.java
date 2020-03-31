package com.wck.test;

import com.wck.bean.Person;
import com.wck.config.MainConfigOfLifeCycle;
import com.wck.config.MainConfigOfPropertyValues;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author 御香烤翅
 * @create 2020-03-10 16:33
 */
public class PropertyValuesTest {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfPropertyValues.class);

    /**
     * 测试 @Value 赋值
     */
    @Test
    public void test01(){
        printBeanDefinitionNames();
        System.out.println("容器创建完成...");

        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);

        //获取配置文件 properties里面的值
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("person.nickname");
        System.out.println(property);

    }


    public void printBeanDefinitionNames(){
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
