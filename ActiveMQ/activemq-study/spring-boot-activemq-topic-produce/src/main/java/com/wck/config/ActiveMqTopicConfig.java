package com.wck.config;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Topic;

/**
 * @author 御香烤翅
 * @create 2020-03-21 15:29
 */
@Configuration
public class ActiveMqTopicConfig {

    @Value("${mytopic}")
    private String mytopic;

    @Bean
    public Topic topic(){
        return new ActiveMQTopic(mytopic);
    }


}
