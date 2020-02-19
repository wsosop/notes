package com.wck.spring.framework.bean;

/**
 * @author 御香烤翅
 * @create 2020-02-16 19:55
 */
public class BeanDefinition {

    //默认配置的是单例模式

    //获取beanClassName
    private String beanClassName;
    //是否懒加载
    private boolean lazyInit = false;
    //工厂名称
    private String factoryBeanName;

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public String getFactoryBeanName() {
        return factoryBeanName;
    }

    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }
}
