package com.wck.consume;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @author 御香烤翅
 * @create 2020-03-21 15:07
 */
@Service
public class BootActiveMqConsume {
    @JmsListener(destination = "${myqueue}")
    public void revice(TextMessage textMessage) throws JMSException {
        String text = textMessage.getText();
        System.out.println("消费者接收到的信息为："+text);
    }
}
