package com.wck.test;

import com.wck.aop.MathCalculator;
import com.wck.config.MainConfigOfAOP;
import com.wck.tx.TxConfig;
import com.wck.tx.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 御香烤翅
 * @create 2020-03-10 19:36
 */
public class TxTest {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TxConfig.class);
    /**
     * 测试 @Value 赋值
     */
    @Test
    public void test01(){
        System.out.println("-------------------------------");
        UserService userService = applicationContext.getBean(UserService.class);
        userService.insert();
    }


    public void printBeanDefinitionNames(){
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
