package com.wck.consume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @author 御香烤翅
 * @create 2020-03-21 16:02
 */

@Service
public class BootActiveMqTopicConsume {

    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "${mytopic}")
    public void consumeMsg(TextMessage textMessage) throws JMSException {
        String text = textMessage.getText();
        System.out.println("topic 获得到的消息为："+text);
    }


}
