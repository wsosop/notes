package com.wck.spring.framework.bean;

import com.wck.spring.framework.aop.AopConfig;
import com.wck.spring.framework.aop.AopProxy;

/**
 * @author 御香烤翅
 * @create 2020-02-16 22:35
 */
public class BeanWrapper {

    //支持事件响应，会有一个监听
    private BeanPostProcessor postProcessor;

    private AopProxy aopProxy = new AopProxy();

    //包装的对象
    private Object wrapperInstance;

    //原始的通过 反射new 出来的 ，要把它包装，存下来
    private Object originalInstance;

    public BeanPostProcessor getPostProcessor() {
        return postProcessor;
    }

    public void setPostProcessor(BeanPostProcessor postProcessor) {
        this.postProcessor = postProcessor;
    }


    public BeanWrapper(Object instance){
        //这里先把包装的和原始的都存成一样
        this.originalInstance=instance;
        /**从这里开始，要开始把动态的代码添加进来了**/
        this.wrapperInstance=aopProxy.getProxy(instance);
    }

    public Object getWrapperInstance(){
        return this.wrapperInstance;
    }

    public Object getOriginalInstance(){
        return this.originalInstance;
    }

    //返回代理以后的Class
    //可能会使 这个 $Proxy0
    public Class<?> getWrapperClass(){
        return this.wrapperInstance.getClass();
    }

    //设置aopConfig ：指的是 aop了那个方法，和具体在这个方法上要代理做哪些事情
    public void setAopConfig(AopConfig aopConfig){
        this.aopProxy.setAopConfig(aopConfig);
    }
}
