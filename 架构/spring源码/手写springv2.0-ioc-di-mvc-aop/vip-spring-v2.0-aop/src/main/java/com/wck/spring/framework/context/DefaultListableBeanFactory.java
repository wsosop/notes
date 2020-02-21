package com.wck.spring.framework.context;

import com.wck.spring.framework.bean.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 御香烤翅
 * @create 2020-02-20 19:58
 */
public class DefaultListableBeanFactory extends  AbstractApplicationContext{

    //用于保存BeanDefinition,ioc 的实现
    public Map<String,BeanDefinition> beanDefinitionMap=new ConcurrentHashMap<String,BeanDefinition>();

    protected void onRefresh(){

    }

    protected  void refreshBeanFactory(){
        return ;
    }

}
