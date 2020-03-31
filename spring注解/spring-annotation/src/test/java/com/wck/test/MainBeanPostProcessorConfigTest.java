package com.wck.test;

import com.wck.config.MainBeanPostProcessorConfig;
import com.wck.beanPostProcessor.UserTest;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 御香烤翅
 * @create 2020-03-10 19:36
 */
public class MainBeanPostProcessorConfigTest {

    /**
     * 测试 @Value 赋值
     */
    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainBeanPostProcessorConfig.class);

        UserTest bean = applicationContext.getBean(UserTest.class);
        System.out.println(bean);

    }


}
