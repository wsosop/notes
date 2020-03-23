package com.wck.activemq.broker;

import org.apache.activemq.broker.BrokerService;

/**
 * @author 御香烤翅
 * @create 2020-03-20 23:30
 */
public class EmbedBroker {

    public static void main(String[] args) throws Exception {
        //ActiveMQ也支持在vm中通信基于嵌入的broker
        BrokerService brokerService = new BrokerService();
        brokerService.setPopulateJMSXUserID(true);
        brokerService.addConnector("tcp://127.0.0.1:61616");
        brokerService.start();
    }
}
