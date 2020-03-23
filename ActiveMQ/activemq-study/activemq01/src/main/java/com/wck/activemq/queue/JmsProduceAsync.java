package com.wck.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;

import javax.jms.*;
import java.util.UUID;

/**
 * 消息的生产者
 * @author 御香烤翅
 * @create 2020-03-18 21:47
 */
public class JmsProduceAsync {

    //tcp
    public static final String ACTIVEMQ_URL="tcp://192.168.1.102:61616";
    public static final String QUEUE_NAME="async";

    public static void main(String[] args) throws JMSException {

        //1.创建activeMQ工厂实例，传递url，使用默认的url和密码 admin/admin
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);

        //这里设置异步投递
        factory.setUseAsyncSend(true);


        //2.通过工厂，获得connection并启动访问
        Connection connection = factory.createConnection();
        connection.start();
        //3.创建会话 第一个参数：事务 ，第二个参数：签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4.创建目的地，队列/主题
        Queue queue = session.createQueue(QUEUE_NAME);
        //5.创建消息的生产者


        //需要使用ActiveMQMessageProducer实现类来接受session创建的生产者
        ActiveMQMessageProducer activeMQMessageProducer = (ActiveMQMessageProducer) session.createProducer(queue);


        activeMQMessageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);//持久化
//        messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);//非持久化
        //6.使用MessageProducer生产者生产3条消息发送到MQ队列
        for (int i = 1; i <=3 ; i++) {
            //7.创建消息
            TextMessage textMessage = session.createTextMessage("msg---" + i);//理解为一个字符串
            //消息头
//            textMessage.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
            //添加一个属性
//            textMessage.setStringProperty("c1","vip");
            textMessage.setJMSMessageID(UUID.randomUUID().toString().substring(0,5)+"flag+"+i);
            String messageID=textMessage.getJMSMessageID();
            //8.发送消息
            activeMQMessageProducer.send(textMessage,new AsyncCallback(){
                @Override
                public void onSuccess() {
                    System.out.println("已经发送成功"+messageID);
                }
                @Override
                public void onException(JMSException exception) {
                    System.out.println("已经发送失败"+messageID);

                }
            });
        }

        //9.关闭资源
        activeMQMessageProducer.close();
        session.close();
        connection.close();

        System.out.println("消息发送到MQ完毕");
    }

}
