package com.wck.spring.demo.aspect;

/**
 * @author 御香烤翅
 * @create 2020-02-20 23:57
 */
public class LogAspect {

    public void before(){
        System.out.println("这是代理模式的前置增强before...");
    }

    public void after(){
        System.out.println("这是代理模式的后置增强after...");
    }


}
