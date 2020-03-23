package com.wck.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息的生产者
 * @author 御香烤翅
 * @create 2020-03-18 21:47
 */
public class JmsProduce_topic {

    public static final String ACTIVEMQ_URL="tcp://192.168.1.102:61616";
    public static final String TOPIC_NAME="topic01";

    public static void main(String[] args) throws JMSException {

        //1.创建activeMQ工厂实例，传递url，使用默认的url和密码 admin/admin
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //2.通过工厂，获得connection并启动访问
        Connection connection = factory.createConnection();
        connection.start();
        //3.创建会话 第一个参数：事务 ，第二个参数：签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4.创建目的地，队列/主题
        Topic topic = session.createTopic(TOPIC_NAME);
        //5.创建消息的生产者
        MessageProducer messageProducer = session.createProducer(topic);
        //6.使用MessageProducer生产者生产3条消息发送到MQ队列
        for (int i = 1; i <=3 ; i++) {
            //7.创建消息
            TextMessage textMessage = session.createTextMessage("TOPIC_NAME" + i);//理解为一个字符串
            //8.发送消息
            messageProducer.send(textMessage);
        }

        //9.关闭资源
        messageProducer.close();
        session.close();
        connection.close();

        System.out.println("TOPIC_NAME消息发送到MQ完毕");
    }

}
