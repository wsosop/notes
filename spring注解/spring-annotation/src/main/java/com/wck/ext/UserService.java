package com.wck.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author 御香烤翅
 * @create 2020-03-12 9:53
 */
@Service
public class UserService {

    @EventListener(classes = {ApplicationEvent.class})
    public void listener(ApplicationEvent event){
        System.out.println("UserService监听到的事件为："+event);

    }

}
