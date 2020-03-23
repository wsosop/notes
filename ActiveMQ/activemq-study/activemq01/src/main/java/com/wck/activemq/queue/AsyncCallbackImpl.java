package com.wck.activemq.queue;

import org.apache.activemq.AsyncCallback;

import javax.jms.JMSException;

/**
 * @author 御香烤翅
 * @create 2020-03-23 0:16
 */
public class AsyncCallbackImpl implements AsyncCallback {

    private String msgId;

    public AsyncCallbackImpl(String msgId){
        this.msgId=msgId;
    }

    @Override
    public void onSuccess() {
        System.out.println("这一条已经发送成功"+msgId);

    }

    @Override
    public void onException(JMSException exception) {
        System.out.println("这一条已经发送失败"+msgId);
    }
}
