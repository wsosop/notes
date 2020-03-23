package com.wck.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * 消息的消费者
 * @author 御香烤翅
 * @create 2020-03-19 0:37
 */
public class JmsConsumer {

    //tcp
   // public static final String ACTIVEMQ_URL="tcp://192.168.1.102:61616";
    //nio
//    public static final String ACTIVEMQ_URL="nio://192.168.1.102:61618";
//    public static final String ACTIVEMQ_URL="tcp://127.0.0.1:61616";

    //使用自动 auto
//    public static final String ACTIVEMQ_URL="tcp://192.168.1.102:61608";

    //集群使用
    public static final String ACTIVEMQ_URL="tcp://192.168.1.102:61616";
    public static final String QUEUE_NAME="delay";

    public static void main(String[] args) throws JMSException, IOException {
        System.out.println("---***2号消费者：");
        //1.创建activeMQ工厂实例，传递url，使用默认的url和密码 admin/admin
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //2.通过工厂，获得connection并启动访问
        Connection connection = factory.createConnection();
        connection.start();
        //3.创建会话 第一个参数：事务 ，第二个参数：签收
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        //4.创建目的地，队列/主题
        Queue queue = session.createQueue(QUEUE_NAME);
        //5.创建消费者
        MessageConsumer messageConsumer = session.createConsumer(queue);

        //6.消费消息
        /*第一种方式：
        //同步阻塞方式（receive()）
        //订阅者或接受者调用MessageConsumer的receive()方法来接收消息，
        // receive方法在能够接收到消息之前（或者超时之前）一直阻塞
        while (true){
            //一直等候
            //TextMessage textMessage= (TextMessage) messageConsumer.receive();//消费消息
            //过时不候
            TextMessage textMessage= (TextMessage) messageConsumer.receive(3000L);//消费消息
            if (textMessage != null){
                System.out.println("----***接收到的消息为："+textMessage.getText());
            }else {
                break;
            }
        }

        //7.关闭连接
        messageConsumer.close();
        session.close();
        connection.close();*/

        //第二种方式
        //  通过监听的方式来获取消息 MessageConsumer messageConsumer = session.createConsumer(queue);
        //  异步非阻塞方式（监听器onMessage（））
        //  订阅者或接受者通过MessageConsumer的setMessageListener(MessageListener listener)注册一个消息监听器
        //  当消息到达之后，系统自动调用监听器MessageListener的onMessage(Message message)方法
        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if(message != null && message instanceof TextMessage){
                    TextMessage textMessage= (TextMessage) message;
                    try {
                        System.out.println("----***接收到的textMessage消息为："+textMessage.getText());
                        message.acknowledge();

//                        System.out.println("----***接收到的textMessage属性消息为："+textMessage.getStringProperty("c1"));
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        // 让主线程不要结束。因为一旦主线程结束了，其他的线程（如此处的监听消息的线程）也都会被迫结束。
        // 实际开发中，我们的程序会一直运行，这句代码都会省略。
        System.in.read();
        messageConsumer.close();
        session.close();
        connection.close();

        /**
         * 1 先生产,只启动1号消费者。问题：1号消费者能消费消息吗？
         *      Y
         * 2 先生产。先启动1号消费者，再启动2号消费者。问题：2号消费者还能消费消息吗？
         *      2.1:   1号 Y
         *      2.2:   2号 N
         * 3 先启动2 个消费者，再生产6条消息，问题：消费如何？
         *      3.1 每个人都是6条
         *      3.2 先到先得
         *      3.3 每人一半 Y (这个正确)
         *
         */

    }
}
