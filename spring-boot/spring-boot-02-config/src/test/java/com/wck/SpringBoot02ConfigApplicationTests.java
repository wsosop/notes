package com.wck;

import com.wck.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot02ConfigApplicationTests {

    @Autowired
    private Person person;

    @Autowired
    private ApplicationContext ioc;


    @Test
    public void testHelloService(){
        boolean containsBean = ioc.containsBean("helloService");
        System.out.println(containsBean);

    }

    @Test
    public void contextLoads() {
        System.out.println(person);
    }

}
