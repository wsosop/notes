package com.wck.produce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.jms.Queue;
import javax.jms.TextMessage;
import java.util.Random;
import java.util.UUID;

/**
 * @author 御香烤翅
 * @create 2020-03-21 14:35
 */

@Service
public class BootActiveMqProduce {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue queue;

    public void queueMsg(){
        jmsTemplate.convertAndSend(queue,UUID.randomUUID().toString().substring(1,6));
    }

    @Scheduled(fixedDelay = 3000L)
    public void queueMsgSchedule(){
        jmsTemplate.convertAndSend(queue,UUID.randomUUID().toString().substring(1,6));
        System.out.println("定时发送成功间隔 3秒钟");
    }
}
