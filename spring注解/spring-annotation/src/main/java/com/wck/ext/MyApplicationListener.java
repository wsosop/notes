package com.wck.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author 御香烤翅
 * @create 2020-03-12 0:33
 */
@Component
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {

    //当容器中，发布此事件之后，该方法会被触发
    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        System.out.println("收到事件："+event);

    }
}
