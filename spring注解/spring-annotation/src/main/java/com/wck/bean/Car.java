package com.wck.bean;

import org.springframework.stereotype.Component;

/**
 * @author 御香烤翅
 * @create 2020-03-10 14:43
 */
@Component
public class Car {

    public Car(){
        System.out.println("car 构造");
    }

    public void init(){
        System.out.println("car init ");
    }

    public void destory(){
        System.out.println("car destory ");
    }


}
