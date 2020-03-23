package com.wck.produce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.jms.Topic;
import java.util.UUID;

/**
 * @author 御香烤翅
 * @create 2020-03-21 15:28
 */
@Service
public class BootActiveMqTopicProduce {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Topic topic;

    @Scheduled(fixedDelay = 3000L)
    public void topicMsg(){
        jmsTemplate.convertAndSend(topic,"这是topic消息："+UUID.randomUUID().toString().substring(0,6));
    }

}
