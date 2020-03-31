package com.wck.test;

/**
 * @author 御香烤翅
 * @create 2020-03-12 21:41
 */

import com.wck.testdaili.WckPerson;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 代理测试类
 */
public class DailiTest {


    @Test
    public void testDaili(){

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beansTestdaili.xml");
//        WckPerson wckPerson = (WckPerson) applicationContext.getBean("wckPerson");
//        System.out.println(wckPerson.getClass().getClass());


        Object wckPerson = applicationContext.getBean("wckPerson");
        System.out.println(wckPerson.getClass().getName());


    }

}
