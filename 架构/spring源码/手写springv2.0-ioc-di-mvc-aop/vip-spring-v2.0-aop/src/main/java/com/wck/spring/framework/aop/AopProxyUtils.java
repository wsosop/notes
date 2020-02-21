package com.wck.spring.framework.aop;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**
 * @author 御香烤翅
 * @create 2020-02-21 0:06
 */

//这个类用于把代理的类，找出真的类
public class AopProxyUtils {


    //传入代理的对象，获取实际的对象
    public static Object getTargetObject(Object proxy) throws  Exception{
        if(!isAopProxy(proxy)){
            return proxy;
        }
        return getProxyTargetObject(proxy);
    }


    //判断是否是代理类的对象
    private static boolean isAopProxy(Object object){
        return Proxy.isProxyClass(object.getClass());
    }

    //
    private static Object getProxyTargetObject(Object proxy) throws Exception{
        Field h=proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        AopProxy aopProxy= (AopProxy) h.get(proxy);

        Field target=aopProxy.getClass().getDeclaredField("target");
        target.setAccessible(true);
        return target.get(aopProxy);
    }


}
