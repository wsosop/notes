package com.wck;

import com.wck.bean.Person;
import com.wck.config.MainConfig;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 御香烤翅
 * @create 2020-03-09 17:50
 */
public class MainTest {

    public static void main(String[] args) {

        //第一种方式，xml方式
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
//        Person person = (Person) applicationContext.getBean("person");
//        System.out.println(person);

        //第二种方式，用注解的方式
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Person bean = applicationContext.getBean(Person.class);
        System.out.println(bean);
        String[] namesForType = applicationContext.getBeanNamesForType(Person.class);
        for (String s : namesForType) {
            System.out.println(s);
        }
    }
}
