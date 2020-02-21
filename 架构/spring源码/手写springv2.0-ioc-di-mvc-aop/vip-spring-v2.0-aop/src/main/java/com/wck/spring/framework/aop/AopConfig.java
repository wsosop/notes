package com.wck.spring.framework.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 御香烤翅
 * @create 2020-02-20 23:02
 */

/**
 * 这个类就是，对Aop的一个描述，包含着对那个方法增强，且增强的内容是什么
 */

//这个类是对application中表达式（expression）的封装
//目标代理对象的一个方法要增强
//将由自己实现的业务逻辑进行增强
//配置文件的目的：告诉spring，哪些类的哪些方法需要增强，增强的内容是什么
//对配置文件中所体现的内容进行封装
public class AopConfig {

    //以目标对象需要增强的方法作为key,需要增强的代码内容作为 value
    private Map<Method,Aspect> points=new HashMap<>();

    //放入一个代理增强的对象
    public void put(Method method,Object aspect,Method[] points){
        this.points.put(method,new Aspect(aspect,points));
    }

    //获取指定方法所对应的代理对象
    public Aspect get(Method method){
        return this.points.get(method);
    }

    //返回增强的方法是否存在于此增强Map中
    public boolean contains(Method method){
        return this.points.containsKey(method);
    }


    //对增强的代码进行封装
    class Aspect{
        private Object aspect;//需要增强的对象，待会将LogAspect这个对象赋值给它
        private Method[] points;//需要增强的点，例如 before 、after

        public Aspect(Object aspect, Method[] points) {
            this.aspect = aspect;
            this.points = points;
        }

        public Object getAspect() {
            return aspect;
        }

        public Method[] getPoints() {
            return points;
        }
    }
}
