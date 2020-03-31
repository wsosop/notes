package com.wck.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author 御香烤翅
 * @create 2020-03-10 15:05
 */
public class Cat  implements InitializingBean,DisposableBean {

    public Cat(){
        System.out.println("Cat 构造");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Cat destroy");

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Cat afterPropertiesSet");

    }
}
