package com.wck.spring.framework.aop;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 御香烤翅
 * @create 2020-02-20 22:55
 */
//默认使用 JDK的动态代理
public class AopProxy implements InvocationHandler {

    //代理的目标的对象
    private Object target;

    private AopConfig aopConfig;

//    public Object getProxy(){
//        return null;
//    }

    //设置aopConfig ：指的是 aop了那个方法，和具体在这个方法上要代理做哪些事情
    public void setAopConfig(AopConfig aopConfig){
        this.aopConfig=aopConfig;
    }

    public Object getProxy(Object instance){

        this.target=instance;
        Class<?> clazz=instance.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("代理类的实例 proxy ："+proxy.getClass().toString());
        System.out.println("初始化的时候代理的方法名称："+method.getName());

        //在原始方法调用以前要执行增强的代码
        //这里需要通过原生方法去找，通过代理方法去Map中是找不到的
        Method m=this.target.getClass().getMethod(method.getName(),method.getParameterTypes());
//        Method m=this.target.getClass().getMethod(method.getName(),method.getTypeParameters());

        if(this.aopConfig.contains(m)){
            //使用前置before方法
            AopConfig.Aspect aspect=this.aopConfig.get(m);
            aspect.getPoints()[0].invoke(aspect.getAspect());
        }

        //首先使用增强的前置 Before

        //使用正常的代理
        Object obj=method.invoke(this.target,args);
        System.out.println("这是正常执行的方法...");
        //System.out.println("======>>>>obj:"+obj);

        //最后使用增强的后置 after
        if(this.aopConfig.contains(m)){
            //使用前置before方法
            AopConfig.Aspect aspect=this.aopConfig.get(m);
            aspect.getPoints()[1].invoke(aspect.getAspect());
        }

        //将最原始的值返回出去
        return obj;
    }
}
