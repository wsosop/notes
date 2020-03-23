package com.wck.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;

/**
 * @author 御香烤翅
 * @create 2020-03-21 0:03
 */
@Service
public class SpringProduce {


    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        SpringProduce bean = applicationContext.getBean(SpringProduce.class);

//        bean.jmsTemplate.send(new MessageCreator() {
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                TextMessage textMessage = session.createTextMessage("你好spring-activemq简单整合");
//                return textMessage;
//            }
//        });

        bean.jmsTemplate.send((session -> {
                TextMessage textMessage = session.createTextMessage("你好spring-activemq简单整合");
                return textMessage;
        }));

        System.out.println("spring整合发布到activeMQ完成");
    }

}
