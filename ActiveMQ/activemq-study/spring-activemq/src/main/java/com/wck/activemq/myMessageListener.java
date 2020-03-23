package com.wck.activemq;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author 御香烤翅
 * @create 2020-03-21 0:47
 */
@Component
public class myMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {

        //获取消息
        TextMessage textMessage= (TextMessage) message;
        String text = null;
        try {
            text = textMessage.getText();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        System.out.println(text);


    }
}
