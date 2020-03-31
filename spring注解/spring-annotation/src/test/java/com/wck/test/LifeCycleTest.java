package com.wck.test;

import com.wck.config.MainConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 御香烤翅
 * @create 2020-03-10 14:45
 */
public class LifeCycleTest {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);

    @Test
    public void test01(){
        System.out.println("容器创建完成");
        applicationContext.close();
    }
}
