package com.wck.beanPostProcessor;

/**
 * @author 御香烤翅
 * @create 2020-03-15 18:11
 */
public class UserTest {
    private int id;

    private String name;

    private String beanName;

    public UserTest(){
        System.out.println("UserTest 被实例化");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("设置："+name);
        this.name = name;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
    /**
     * 自定义的初始化方法
     */
    public void start(){
        System.out.println("UserTest 中自定义的初始化方法");
    }

    @Override
    public String toString() {
        return "UserTest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", beanName='" + beanName + '\'' +
                '}';
    }
}
