package com.wck.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @author 御香烤翅
 * @create 2020-03-20 18:07
 */
public class JmsConsummer_persistence {


    public static final String ACTIVEMQ_URL="tcp://192.168.1.102:61616";
    public static final String TOPIC_NAME="topic01";

    public static void main(String[] args) throws JMSException, IOException {
        System.out.println("---***zs号消费者：");
        //1.创建activeMQ工厂实例，传递url，使用默认的url和密码 admin/admin
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //2.通过工厂，获得connection并启动访问
        Connection connection = factory.createConnection();

        //---------------------------设置客户端id--------------------------------
        //设置客户端id，像MQ服务器注册自己的id
        connection.setClientID("zs01");




        //3.创建会话 第一个参数：事务 ，第二个参数：签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4.创建目的地，队列/主题
        Topic topic = session.createTopic(TOPIC_NAME);




        //----------------------------创建一个持久化的订阅者对象，一参是 topic，二参是 订阅者名称 -------------------------------------------
        TopicSubscriber durableSubscriber = session.createDurableSubscriber(topic, "备注信息remark");
        //之后再开启连接
        connection.start();
        Message message = durableSubscriber.receive();

        while (message !=null){
            TextMessage textMessage= (TextMessage) message;
            System.out.println("收到的持久化topic:"+textMessage.getText());
            message=durableSubscriber.receive();
        }

        session.close();
        connection.close();

        /**
         * 一定要先运行一次消费者,类似于像MQ注册,我订阅了这个主题
         * 然后再运行主题生产者
         * 无论消费着是否在线,都会接收到,在线的立即接收到,不在线的等下次上线把没接收到的接收
         */

    }
}
