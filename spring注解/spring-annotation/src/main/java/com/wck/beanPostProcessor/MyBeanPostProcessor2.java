package com.wck.beanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author 御香烤翅
 * @create 2020-03-15 18:12
 */
public class MyBeanPostProcessor2 implements BeanPostProcessor,ApplicationContextAware {

    private ApplicationContext applicationContext;

    /**
     * 实例化、依赖注入完毕，在调用显示的初始化之前完成一些定制的初始化任务
     * 注意：方法返回值不能为null
     * 如果返回null那么在后续初始化方法将报空指针异常或者通过getBean()方法获取不到bena实例对象
     * 因为后置处理器从Spring IoC容器中取出bean实例对象没有再次放回IoC容器中
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("初始化 before--实例化的bean对象:"+bean+"\t"+beanName);
        // 可以根据beanName不同执行不同的处理操作
        if(beanName.equals("userTest")){
            UserTest userTest= (UserTest) bean;
            userTest.setId(10);
            userTest.setBeanName("postProcessBeforeInitialization设置的值");
            //((UserTest) applicationContext.getBean(beanName)).setId(8);
        }
        return bean;
    }

    /**
     * 实例化、依赖注入、初始化完毕时执行
     * 注意：方法返回值不能为null
     * 如果返回null那么在后续初始化方法将报空指针异常或者通过getBean()方法获取不到bena实例对象
     * 因为后置处理器从Spring IoC容器中取出bean实例对象没有再次放回IoC容器中
     */


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("初始化 after...实例化的bean对象:"+bean+"\t"+beanName);
        // 可以根据beanName不同执行不同的处理操作
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
