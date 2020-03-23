package com.wck.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * @author 御香烤翅
 * @create 2020-03-21 0:29
 */
@Service
public class SpringConsumer {

    @Autowired
    JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        SpringConsumer bean = applicationContext.getBean(SpringConsumer.class);
        String o = (String) bean.jmsTemplate.receiveAndConvert();
        System.out.println("收到的消息为："+o);

    }
}
